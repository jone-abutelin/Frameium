package com.frameium.AzureDevOps;

import com.frameium.logger.LoggerHelper;
import com.frameium.stepdef.Hooks;
import com.frameium.utilities.ConfigurationManager;
import io.cucumber.java.Scenario;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpPatch;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.apache.log4j.Logger;
import org.json.JSONArray;
import org.json.JSONObject;

import java.util.Optional;

public class AzureDevOpsFunctions {
    static ConfigurationManager config = ConfigurationManager.createConfigurationManager("src/test/resources/AzureDevOps.properties");
    private static final String organization = config.getProperty("azure_devops.organization");
    private static final String project = config.getProperty("azure_devops.project");
    private static final String pat = config.getProperty("azure_devops.pat_token");
    private static Logger log = LoggerHelper.getLogger(Hooks.class);


    /**
     * Retrieves the Azure Test Case Data from the given Cucumber scenario's tags.
     * @param scenario The Cucumber Scenario object containing test metadata.
     * @return The Azure Test Case Data Containing Plan Id , Suite Id and Test Case if found; otherwise, will return empty array of string.
     */

    private static String [] getAzureTestCaseData(Scenario scenario) {
        // Look for a tag that starts with '@ado_tc_id=' in the scenario's tags
        String adoTcIdTag = scenario.getSourceTagNames().stream()
                .filter(t -> t.startsWith("@ado_tc_id="))  // Find tags that start with "@ado_tc_id="
                .map(t -> t.split("=")[1])  // Extract the part after the '=' (the actual test case ID)
                .findFirst()  // Get the first matching tag, if any
                .orElse("0");  // If no tag is found, return "0"

        String [] azureIds  = adoTcIdTag.split("-");

        // Print the extracted ID for debugging purposes
        System.out.println(adoTcIdTag);

        // Return the test case ID as a string
        return azureIds;
    }

    /**
     * Retrieves the test point ID for a given test case in Azure DevOps.
     *
     * @param testCaseId  The ID of the test case for which the test point ID is needed.
     * @return The test point ID associated with the specified test case.
     * @Description :This method:
     * 1. Sets the base URI for the Azure DevOps REST API.
     * 2. Sends a GET request to fetch the test point ID for the given test case.
     * 3. Extracts and returns the first test point ID from the response.
     */
    public static String getTestPointId(String planId , String suiteId , String testCaseId) {
        RestAssured.baseURI = "https://dev.azure.com/" + organization  + "/" + project;

        log.info("Initiating API call to fetch Test Point ID...");
        log.info("Test Plan ID: " + planId + ", Suite ID: " + suiteId + ", Test Case ID: " + testCaseId);

        Response response = RestAssured.given()
                .auth()
                .preemptive()
                .basic("", pat)
                .get("/_apis/test/plans/" + planId + "/suites/" + suiteId + "/points?testCaseId=" + testCaseId + "&api-version=6.0");
        String testPointId = null ;

        int statusCode = response.getStatusCode();
        // Log status code
        log.info("API response received. Status Code: " + statusCode);

        if (statusCode == 200 ) {
            testPointId = response.jsonPath().getString("value[0].id");
            if (testPointId != null) {
                log.info("Test Point ID found: " + testPointId);
            } else {
                log.warn("Test Point ID not found. Empty response for Test Case ID: " + testCaseId);
            }
        }else if (statusCode == 404) {
            String errorMessage = response.jsonPath().getString("message");
            log.error("Error 404: " + errorMessage + ". Test Plan ID: " + planId + ", Suite ID: " + suiteId + ", Test Case ID: " + testCaseId);
        }else if (statusCode == 203){
            log.error("Authorization error (Status Code: " + statusCode + "). Check PAT and permissions.");
        }else {
            log.error("Unexpected error occurred. Status Code: " + statusCode);
            return "API_ERROR";
        }
        return testPointId;
    }

    /**
     * Updates the test result in Azure DevOps for the given scenario.
     *
     * @param scenario  The Cucumber Scenario object to extract test case details.
     * @param outcome   The outcome of the test (e.g., Passed, Failed).
     *
     *  @Description : This method updates the test result in Azure DevOps by:
     * 1. Retrieving the test case and test point IDs.
     * 2. Updating related work item fields.
     * 3. Sending a PATCH request to update the test outcome.
     *
     * The response details and request body are logged for debugging.
     */
    public static void updateTestResultInAzureDevOps( Scenario scenario , String outcome ) {
        String []testCaseData = getAzureTestCaseData(scenario);
        if(testCaseData.length!=3){
            if(testCaseData[0].equals("0") && testCaseData.length == 1){
                log.error("Test result update failed : No valid tag available. The tag seems to be missing or not initialized properly.Example : 11-12-13");
            } else if (testCaseData.length==1 ) {
                log.error("Test result update failed : Tag is incomplete. Expected format includes plan ID , Suite ID and Test Case ID, but only one value is provided. Example : 11-12-13");
            } else if (testCaseData.length==2) {
                log.error("Test result update failed : Incomplete tag. Plan ID and Suite ID value provided, but Test Case ID is missing.Example : 11-12-13");
            }else {
                log.error("Test result update failed : Too many values in the tag. Expected format includes only Plan ID , Suite ID and Test Case ID.Example : 11-12-13");
            }
            return;
        }
        String planId = testCaseData[0];
        String suiteId = testCaseData[1];
        String testCaseID = testCaseData[2];

        String testPointID = getTestPointId(planId , suiteId ,testCaseID);

        if(testPointID==null || testPointID.equals("API_ERROR")){
            return;
        }

        RestAssured.baseURI = String.format( config.getProperty("azure_devops.UpdateTestPointResultUri"), organization, project, Integer.parseInt(planId), Integer.parseInt(suiteId));
        System.out.println(RestAssured.baseURI);

        //------------------------------------------------------
            updateWorkItemFields(scenario , testCaseID);
        //------------------------------------------------------


        // Create a JSON object with the required fields
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("id", testPointID);

        JSONObject resultsObject = new JSONObject();
        resultsObject.put("outcome", outcome);
        jsonObject.put("results", resultsObject);

        // Add the JSON object to a JSON array
        JSONArray jsonArray = new JSONArray();
        jsonArray.put(jsonObject);

        // Create the request specification
        RequestSpecification request = RestAssured.given();
        request.auth().preemptive().basic("", pat);
        request.contentType(ContentType.JSON);
        request.queryParam("api-version", "6.0-preview.2");
        request.body(jsonArray.toString());
        System.out.println(request);

        // Send the PATCH request
        Response response = request.patch();

        // Print response details for debugging
        int statusCode = response.getStatusCode();
        String responseBody = response.getBody().asString();

        if (statusCode == 200) {
            log.info("Test result updated successfully in Azure DevOps for test case ID: " + testCaseID);
        } else {
            log.error("Error occurred while updating the test result. Possible issue with the PAT or another factor.");
        }

        System.out.println("Request body: " + jsonArray);
        System.out.println("Status code: " + statusCode);
        System.out.println("Response body: " + responseBody);

        log.debug("Request body: " + jsonArray);
        log.debug("Status code: " + statusCode);
        log.debug("Response body: " + responseBody);
    }

    /**
     * Updates the specified work item in Azure DevOps with the automated test details.
     *
     * @param scenario     The Cucumber Scenario object that provides the test name and ID.
     * @param workItemId   The ID of the work item to be updated in Azure DevOps.
     *
     * This method sends an HTTP PATCH request to update the fields of the work item
     * with the following details:
     * 1. Automated Test Name - Derived from the scenario name.
     * 2. Automated Test Storage - Derived from the scenario ID.
     * 3. Automated Test Type - Hardcoded as "Regression Testing".
     *
     * The method uses the Azure DevOps REST API (version 6.0) to update the fields.
     * It constructs the JSON patch body and sends it to the Azure DevOps API endpoint.
     *
     * Authentication is handled using a Personal Access Token (PAT) encoded in Base64.
     *
     * If the request is successful, the response from the server is printed to the console.
     * If an error occurs during the process, the stack trace is printed for debugging purposes.
     *
     * Note: Ensure that the work item ID, organization, project, and PAT are correctly configured.
     */
    public static void updateWorkItemFields(Scenario scenario , String workItemId) {
        String automatedTestName = scenario.getName();
        String automatedTestStorage = scenario.getId();
        String automatedTestType = "Regression Testing" ;
        String url = String.format("https://dev.azure.com/%s/%s/_apis/wit/workitems/%s?api-version=6.0", organization, project, workItemId);

        // Define the JSON patch body
        String jsonBody = String.format(
                "["
                        + "  {\"op\": \"add\", \"path\": \"/fields/Microsoft.VSTS.TCM.AutomatedTestName\", \"value\": \"%s\"},"
                        + "  {\"op\": \"add\", \"path\": \"/fields/Microsoft.VSTS.TCM.AutomatedTestStorage\", \"value\": \"%s\"},"
                        + "  {\"op\": \"add\", \"path\": \"/fields/Microsoft.VSTS.TCM.AutomatedTestType\", \"value\": \"%s\"}"
                        + "]",
                automatedTestName, automatedTestStorage, automatedTestType
        );

        try (CloseableHttpClient httpClient = HttpClients.createDefault()) {
            HttpPatch httpPatch = new HttpPatch(url);
            httpPatch.setHeader("Content-Type", "application/json-patch+json");
            httpPatch.setHeader("Authorization", "Basic " + java.util.Base64.getEncoder().encodeToString((":" + pat).getBytes()));
            httpPatch.setEntity(new StringEntity(jsonBody));

            HttpResponse response = httpClient.execute(httpPatch);
            String responseString = EntityUtils.toString(response.getEntity());
            System.out.println("Response: " + responseString);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
