package com.frameium.stepdef.Jira;

import com.frameium.stepdef.TestSetUp;
import com.frameium.utilities.apiutils.Api;
import com.google.gson.JsonObject;
import io.cucumber.java.en.*;
import io.restassured.response.Response;
import org.openqa.selenium.WebDriver;


import java.net.MalformedURLException;

public class IssueStep {
    private WebDriver driver;
    private Response response;
    private static String createdIssueId;


    public Api api;
    TestSetUp setUp;
    public IssueStep(TestSetUp setUp) throws MalformedURLException {
        this.setUp = setUp;
        api=new Api(setUp.baseTest.driver);
        // Assign the driver from setUp to the local driver variable
        this.driver = setUp.baseTest.driver;
    }

    @When("a {string} request is sent to {string} with payload from filepath {string}")
    public void a_request_is_sent_to_with_payload_from_filepath(String requestType, String endpoint, String filePath) {
        JsonObject payloadJson = api.readPayloadFromFile(filePath);
        response = api.sendRequest(requestType, endpoint, payloadJson);
    }
    @Then("the status code of creating Issue in JIRA should be {int}")
    public void the_status_code_should_be(int expectedStatusCode) {
        api.verifyStatusCode(response, expectedStatusCode);
        System.out.println("Status code verification passed.");
    }
    @Then("the API response of GET Issue in JIRA should contain the item {string} with value {string}")
    public void the_api_response_of_get_issue_should_contain_the_item_with_value(String item, String expectedValue) {
        api.verifyResponseContainsItem(response, item, expectedValue);
    }
    @Then("the status code of updating JIRA Issue should be {int}")
    public void the_status_update_code_should_be(int expectedStatusCode) {
        api.verifyStatusCode(response, expectedStatusCode);
        System.out.println("Status code verification passed.");
    }
    @Then("the status code of deleting JIRA Issue should be {int}")
    public void the_status_update_code_delete_should_be(int expectedStatusCode) {
        api.verifyStatusCode(response, expectedStatusCode);
        System.out.println("Status code verification passed.");
    }
    @When("a {string} request is sent to {string}")
    public void aRequestIsSentTo(String  requestType, String endpoint) {
        response = api.sendRequest(requestType, endpoint, null);
    }

    //Jira User management
    @When("user creates a new JIRA issue with details from {string}")
    public void user_creates_a_new_issue_with_details_from(String filePath) {
        JsonObject payloadJson = api.readPayloadFromFile(filePath);
        response = api.sendRequest("Post", "/rest/api/3/issue", payloadJson);
        api.verifyStatusCode(response, 201);
        createdIssueId = response.jsonPath().getString("id");
        System.out.println("Created Issue ID: " + createdIssueId);
    }
    @Then("user retrieves the created JIRA issue")
    public void user_retrieves_the_created_issue() {
        response = api.sendRequest("GET", "/rest/api/3/issue/" + createdIssueId, null);
        api.verifyStatusCode(response, 200);
        api.printResponse(response);
    }
    @Then("user updates the created JIRA issue with details from {string}")
    public void user_updates_the_created_issue_with_details_from(String filePath) {
        JsonObject payloadJson = api.readPayloadFromFile(filePath);
        response=api.sendRequest("Put","/rest/api/3/issue/" + createdIssueId, payloadJson );
        api.verifyStatusCode(response, 204);
    }
}
