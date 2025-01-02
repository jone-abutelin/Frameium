package com.frameium.utilities.apiutils;

import org.json.JSONObject;
import org.json.JSONArray;
import org.openqa.selenium.WebDriver;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.HashSet;
import java.util.Set;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import org.json.JSONObject;

public class Zephyr {

    private String apiBaseUrl;
    private String apiToken;
    public WebDriver driver;
    private Set<String> createdFolders;


    public Zephyr(WebDriver driver) {
        this.driver = driver;
        this.createdFolders = new HashSet<>();

    }
    public void ZephyrAPI(String apiBaseUrl, String apiToken) {
        this.apiBaseUrl = apiBaseUrl;
        this.apiToken = apiToken;
    }

    public void addTestCasesToZephyrScale(String projectKey, JSONObject scenario, String folderType) {
        Set<String> processedTestCases = new HashSet<>(); // Track processed test cases

        String testScenario = scenario.getString("testScenario");
        JSONArray testCases = scenario.getJSONArray("testCases");

        // Create folder for the test scenario
        String folderId = createFolder(projectKey, testScenario, folderType);
        if (folderId != null && !processedTestCases.contains(folderId)) {
            for (int j = 0; j < testCases.length(); j++) {
                JSONObject testCase = testCases.getJSONObject(j);
                String testCaseName = testCase.getString("testCaseName");

                // Check if this test case has already been processed
                if (!processedTestCases.contains(testCaseName)) {
                    String testCaseKey = createTestCase(projectKey, testCase, folderId);
                    if (testCaseKey != null) {
                        addTestSteps(testCaseKey, testCase.getJSONArray("steps"));
                        processedTestCases.add(testCaseName); // Mark as processed
                    }
                }
            }
        }
    }

    private String createFolder(String projectKey, String folderName, String folderType) {
        try {
            URL url = new URL(apiBaseUrl + "/folders");
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("POST");
            conn.setRequestProperty("Authorization", "Bearer " + apiToken);
            conn.setRequestProperty("Accept", "application/json");
            conn.setRequestProperty("Content-Type", "application/json");
            conn.setDoOutput(true);

            JSONObject payload = new JSONObject();
            payload.put("name", folderName);
            payload.put("projectKey", projectKey);
            payload.put("folderType", folderType);

            try (OutputStream os = conn.getOutputStream()) {
                byte[] input = payload.toString().getBytes("utf-8");
                os.write(input, 0, input.length);
            }
            int responseCode = conn.getResponseCode();
            if (responseCode == HttpURLConnection.HTTP_CREATED) {
                BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getInputStream(), StandardCharsets.UTF_8));
                StringBuilder response = new StringBuilder();
                String line;
                while ((line = reader.readLine()) != null) {
                    response.append(line.trim());
                }

                JSONObject jsonResponse = new JSONObject(response.toString());
                String folderId = jsonResponse.optString("id", null);
                System.out.println("Folder ID: " + folderId );
                return folderId;
            } else {
                throw new RuntimeException("Failed to create folder. Status code: " + responseCode);
            }
        } catch (Exception e) {
            throw new RuntimeException("Error occurred while creating folder: " + folderName, e);
        }
    }


    private String createTestCase(String projectKey, JSONObject testCase, String folderId) {
        try {
            // API call for test case creation (same as your existing logic)
            URL url = new URL(apiBaseUrl + "/testcases");
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("POST");
            conn.setRequestProperty("Authorization", "Bearer " + apiToken);
            conn.setRequestProperty("Accept", "application/json");
            conn.setRequestProperty("Content-Type", "application/json");
            conn.setDoOutput(true);

            JSONObject payload = new JSONObject();
            payload.put("projectKey", projectKey);
            payload.put("name", testCase.getString("testCaseName"));
            payload.put("objective", testCase.getString("purpose"));
            payload.put("precondition", testCase.getString("preConditions"));
            payload.put("priorityName", testCase.getString("testCasePriority"));

            // payload.put("owner", testCase.getString("designer"));
            payload.put("estimatedTime", 300000);  // 5 minutes in milliseconds
            payload.put("folderId", folderId);
            payload.put("labels", new JSONArray());

            try (OutputStream os = conn.getOutputStream()) {
                byte[] input = payload.toString().getBytes(StandardCharsets.UTF_8);
                os.write(input, 0, input.length);
            }

            // Read the response from the API
            int responseCode = conn.getResponseCode();
            BufferedReader br = new BufferedReader(new InputStreamReader(
                    responseCode >= 400 ? conn.getErrorStream() : conn.getInputStream(), StandardCharsets.UTF_8
            ));
            StringBuilder response = new StringBuilder();
            String responseLine;
            while ((responseLine = br.readLine()) != null) {
                response.append(responseLine.trim());
            }

            System.out.println("API Response: " + response);  // Debugging output

            // Check if the test case was created successfully
            if (responseCode == HttpURLConnection.HTTP_CREATED) {
                JSONObject jsonResponse = new JSONObject(response.toString());

                // Confirming if the key and ID are present
                if (jsonResponse.has("key") && jsonResponse.has("id")) {
                    String testCaseKey = jsonResponse.getString("key");
                    String testCaseId = jsonResponse.getString("id"); // Extracting ID
                    System.out.println("Test case created successfully with key: " + testCaseKey);
                    System.out.println("Test case ID: " + testCaseId); // Printing ID
                    System.out.println("Response: " + response);
                    return testCaseKey;
                } else {
                    System.out.println("Test case created, but key or ID not found in response. Response: " + response);
                    return null;
                }
            } else {
                System.out.println("Failed to create test case: " + testCase.getString("testCaseName") + ". Status code: " + responseCode + ", Response: " + response);
                return null;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    private void addTestSteps(String testCaseKey, JSONArray steps) {
        try {
            // API call to add test steps
            URL url = new URL(apiBaseUrl + "/testcases/" + testCaseKey + "/teststeps");
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("POST");
            conn.setRequestProperty("Authorization", "Bearer " + apiToken);
            conn.setRequestProperty("Accept", "application/json");
            conn.setRequestProperty("Content-Type", "application/json");
            conn.setDoOutput(true);

            JSONArray items = new JSONArray();
            for (int i = 0; i < steps.length(); i++) {
                JSONObject step = steps.getJSONObject(i);
                JSONObject inline = new JSONObject();
                inline.put("description", step.getString("description"));
                inline.put("testData", step.optString("testData", ""));
                inline.put("expectedResult", step.getString("expectedResult"));

                JSONObject item = new JSONObject();
                item.put("inline", inline);
                items.put(item);
            }

            JSONObject payload = new JSONObject();
            payload.put("mode", "OVERWRITE");
            payload.put("items", items);

            try (OutputStream os = conn.getOutputStream()) {
                byte[] input = payload.toString().getBytes("utf-8");
                os.write(input, 0, input.length);
            }

            int responseCode = conn.getResponseCode();
            String responseMessage = conn.getResponseMessage();

            if (responseCode == HttpURLConnection.HTTP_CREATED) {
                System.out.println("Successfully added steps to test case: " + testCaseKey);
            } else {
                System.out.println("Failed to add steps to test case: " + testCaseKey + ". Status code: " + responseCode + ", Response: " + responseMessage);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public String createTestCycle(JSONObject testCyclePayload, String folderType) {

        try {
            // Extract projectKey and folderName from the payload or define them
            String projectKey = testCyclePayload.getString("projectKey");
            String folderName = testCyclePayload.optString("folderName", "Default Cycle Folder"); // Default folder name if not provided

            // Call the createFolder method to create a folder
            String folderId = createFolder(projectKey, folderName, folderType);

            // Check if folder was created successfully
            if (folderId == null) {
                System.out.println("Failed to create folder for the test cycle.");
                return null; // Handle failure to create folder
            }

            // Set the folderId in the test cycle payload
            testCyclePayload.put("folderId", folderId);

            // API call for test cycle creation
            URL url = new URL(apiBaseUrl + "/testcycles");
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("POST");
            conn.setRequestProperty("Authorization", "Bearer " + apiToken);
            conn.setRequestProperty("Accept", "application/json");
            conn.setRequestProperty("Content-Type", "application/json");
            conn.setDoOutput(true);

            // Construct the payload
            JSONObject payload = new JSONObject();
            payload.put("projectKey", testCyclePayload.getString("projectKey"));
            payload.put("name", testCyclePayload.getString("name"));
            payload.put("description", testCyclePayload.optString("description", ""));
            payload.put("plannedStartDate", testCyclePayload.optString("plannedStartDate", ""));
            payload.put("plannedEndDate", testCyclePayload.optString("plannedEndDate", ""));
            payload.put("jiraProjectVersion", testCyclePayload.optInt("jiraProjectVersion", 0));
            payload.put("statusName", testCyclePayload.optString("statusName", ""));
            payload.put("folderId", testCyclePayload.optInt("folderId", 0));

            // Adding custom fields
            JSONObject customFields = testCyclePayload.optJSONObject("customFields");
            if (customFields != null) {
                payload.put("customFields", customFields);
            }

            // Send the payload to the server
            try (OutputStream os = conn.getOutputStream()) {
                byte[] input = payload.toString().getBytes(StandardCharsets.UTF_8);
                os.write(input, 0, input.length);
            }

            // Read the response from the API
            int responseCode = conn.getResponseCode();
            BufferedReader br = new BufferedReader(new InputStreamReader(
                    responseCode >= 400 ? conn.getErrorStream() : conn.getInputStream(), StandardCharsets.UTF_8
            ));
            StringBuilder response = new StringBuilder();
            String responseLine;
            while ((responseLine = br.readLine()) != null) {
                response.append(responseLine.trim());
            }

            // Check if the test cycle was created successfully
            if (responseCode == HttpURLConnection.HTTP_CREATED) {
                JSONObject jsonResponse = new JSONObject(response.toString());
                if (jsonResponse.has("id")) {
                    Object idObject = jsonResponse.get("id");
                    String cycleId = (idObject instanceof Number) ? String.valueOf(idObject) : jsonResponse.optString("id", null);
                    if (cycleId != null) {
                        System.out.println("Test cycle created successfully with ID: " + cycleId);
                        return cycleId;
                    }
                }
            }

            // Log failure details
            System.out.println("Failed to create test cycle. Status code: " + responseCode + ", Response: " + response);

        } catch (Exception e) {
            System.err.println("An error occurred while creating the test cycle:");
            e.printStackTrace();
        }

        return null; // Return null if creation fails
    }

    public boolean linkIssueToTestCycle(String issueId, String testCycleId) {
        try {
            // Construct API URL
            URL url = new URL(apiBaseUrl + "/testcycles/" + testCycleId + "/links/issues");

            // Open HTTP connection
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("POST");
            conn.setRequestProperty("Authorization", "Bearer " + apiToken);
            conn.setRequestProperty("Accept", "application/json");
            conn.setRequestProperty("Content-Type", "application/json");
            conn.setDoOutput(true);

            // Prepare payload with issueId
            JSONObject payload = new JSONObject();
            payload.put("issueId", issueId); // Use issueId instead of issueKey

            // Write payload to request body
            try (OutputStream os = conn.getOutputStream()) {
                byte[] input = payload.toString().getBytes(StandardCharsets.UTF_8);
                os.write(input, 0, input.length);
            }

            // Read the response
            int responseCode = conn.getResponseCode();
            BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream(), StandardCharsets.UTF_8));
            StringBuilder response = new StringBuilder();
            String responseLine;
            while ((responseLine = br.readLine()) != null) {
                response.append(responseLine.trim());
            }

            if (responseCode == HttpURLConnection.HTTP_CREATED) {  // 201 Created
                System.out.println("Issue " + issueId + " linked successfully to test cycle " + testCycleId);
                return true;
            } else if (responseCode == HttpURLConnection.HTTP_BAD_REQUEST) {  // 400 Bad Request
                System.out.println("Failed to link issue. Status Code: " + responseCode + ", Response: " + response);
                return false;
            }

            // Handle any unexpected status codes
            System.out.println("Unexpected status code: " + responseCode);
            return false;
        } catch (Exception e) {
            throw new RuntimeException("Error occurred while linking issue to test cycle", e);
        }
    }
    public boolean linkTestCaseToTestCycle(String projectKey, String testCaseKey, String testCycleKey) {
        try {
            // Construct API URL for test executions
            URL url = new URL(apiBaseUrl + "/testexecutions");

            // Open HTTP connection
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("POST");
            conn.setRequestProperty("Authorization", "Bearer " + apiToken);
            conn.setRequestProperty("Accept", "application/json");
            conn.setRequestProperty("Content-Type", "application/json");
            conn.setDoOutput(true);

            // Prepare payload
            JSONObject payload = new JSONObject();
            payload.put("projectKey", projectKey);
            payload.put("testCaseKey", testCaseKey);
            payload.put("testCycleKey", testCycleKey);
            payload.put("statusName", "In Progress");
    //        payload.put("environmentName", "Chrome Latest Version");
            payload.put("executionTime", 120000);
            payload.put("actualEndDate", "2023-05-20T13:15:13Z");
            payload.put("comment", "Test execution details for " + testCaseKey);

            JSONArray testScriptResults = new JSONArray();
            JSONObject testScriptResult = new JSONObject();
            testScriptResult.put("statusName", "In Progress");
            testScriptResult.put("actualEndDate", "2023-05-20T13:15:13Z");
            testScriptResult.put("actualResult", "User logged in successfully");
            testScriptResults.put(testScriptResult);
            payload.put("testScriptResults", testScriptResults);

            // Log API URL and Payload
            System.out.println("API URL: " + url);
            System.out.println("Payload: " + payload.toString());

            // Write payload to request body
            try (OutputStream os = conn.getOutputStream()) {
                byte[] input = payload.toString().getBytes(StandardCharsets.UTF_8);
                os.write(input, 0, input.length);
            }

            // Read the response
            int responseCode = conn.getResponseCode();
            BufferedReader br = new BufferedReader(new InputStreamReader(
                    responseCode >= 400 ? conn.getErrorStream() : conn.getInputStream(), StandardCharsets.UTF_8
            ));
            StringBuilder response = new StringBuilder();
            String responseLine;
            while ((responseLine = br.readLine()) != null) {
                response.append(responseLine.trim());
            }

            // Log response
            System.out.println("Response: " + response);

            if (responseCode == HttpURLConnection.HTTP_CREATED) {  // 201 Created
                System.out.println("Testcase " + testCaseKey + " linked successfully to test cycle " + testCycleKey);
                return true;
            } else if (responseCode == HttpURLConnection.HTTP_BAD_REQUEST) {  // 400 Bad Request
                System.out.println("Failed to link testcase. Status Code: " + responseCode + ", Response: " + response);
                return false;
            }

            // Handle any unexpected status codes
            System.out.println("Unexpected status code: " + responseCode + ", Response: " + response);
            return false;
        } catch (Exception e) {
            throw new RuntimeException("Error occurred while linking testcase to test cycle", e);
        }
    }


}



  
    


