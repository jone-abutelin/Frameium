package com.frameium.stepdef.Jira;
import com.frameium.stepdef.TestSetUp;
import com.frameium.utilities.apiutils.Zephyr;
import io.cucumber.java.en.*;
import org.json.JSONObject;
import org.json.JSONArray;
import org.openqa.selenium.WebDriver;

import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.Map;


public class ZephyrTest {

    private Zephyr zephyrPage;

    private List<Map<String, Object>> testScenarios;
    TestSetUp setUp;
    private WebDriver driver;
    private String apiBaseUrl;
    private String apiToken;
    private JSONObject testCasesPayload;
    private String token = "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJjb250ZXh0Ijp7ImJhc2VVcmwiOiJodHRwczovL2ZyYW1laXVtLXRlYW10aC5hdGxhc3NpYW4ubmV0IiwidXNlciI6eyJhY2NvdW50SWQiOiI3MTIwMjA6YjFmMjVkOTItYjQ4NS00ZWM2LTgwNGMtNWZkNTBlMDRkODlhIiwidG9rZW5JZCI6ImY1MzdmY2Q0LWU1YWItNDA2ZC05NGRlLTQ5MzMzNmRiZTc5NCJ9fSwiaXNzIjoiY29tLmthbm9haC50ZXN0LW1hbmFnZXIiLCJzdWIiOiI0ZWQyY2IwNS0yNWU3LTM4YzgtOTc1NC03ZTRlNWFiNjdmOGQiLCJleHAiOjE3NjQ5MTIzMjksImlhdCI6MTczMzM3NjMyOX0.R8SKig6lEoeotqRSWh7LssAGpv6Whbi9w7KB31Mkpac";
    private JSONObject testCyclePayload;
    private static String testCaseFolder = "TEST_CASE";
    private static String testCycleFolder = "TEST_CYCLE";
    private JSONObject testExecutionPayload;


    public ZephyrTest(TestSetUp setUp) throws MalformedURLException {
        this.setUp = setUp;
        zephyrPage = new Zephyr(setUp.baseTest.driver);
        // Assign the driver from setUp to the local driver variable
        this.driver = setUp.baseTest.driver;
    }


    @Given("the API base URL is {string}")
    public void the_api_base_url_is(String baseUrl) {
        this.apiBaseUrl = baseUrl;
    }

    @Given("the API token is set")
    public void the_api_token_is_set() {
        this.apiToken = token;
    }

    @And("the test cases payload is loaded from {string}")
    public void theTestCasesPayloadIsLoadedFrom(String filePath) throws Exception {
        String content = new String(Files.readAllBytes(Paths.get(filePath)));
        this.testCasesPayload = new JSONObject(content);
    }

// Create test scenarios, test cases and add test steps from JSON payload
    @When("User creates test cases and add test steps in Zephyr")
    public void iCreateTestScenariosAndTestCasesFromThePayload() {
        JSONArray scenarios = testCasesPayload.getJSONArray("testScenarios");
        for (int i = 0; i < scenarios.length(); i++) {
            JSONObject scenario = scenarios.getJSONObject(i);
            String projectKey = scenario.getString("projectKey");

            // Ensure API is configured with base URL and token
            zephyrPage.ZephyrAPI(apiBaseUrl, apiToken);

            // Process each scenario individually
            zephyrPage.addTestCasesToZephyrScale(projectKey, scenario, testCaseFolder);
        }
    }

    @Then("the test scenarios and test cases should be created successfully")
    public void theTestScenariosAndTestCasesShouldBeCreatedSuccessfully() {
        System.out.println("Test scenarios and test cases have been created successfully.");
    }
    @And("the test cycle payload is loaded from {string}")
    public void the_test_cycle_payload_is_loaded_from(String filePath) throws IOException {
        String content = new String(Files.readAllBytes(Paths.get(filePath)));
        this.testCyclePayload = new JSONObject(content);
    }
    @Then("User creates test cycle in Zephyr")
    public void i_create_test_cycle() {
        try {
            // Ensure Zephyr API is configured with base URL and token
            zephyrPage.ZephyrAPI(apiBaseUrl, apiToken);

            // Step 1: Create the test cycle
            String projectKey = testCyclePayload.getString("projectKey");
            String cycleId = zephyrPage.createTestCycle(testCyclePayload, testCycleFolder);

            if (cycleId == null) {
                throw new RuntimeException("Failed to create test cycle.");
            }
        } catch (Exception e) {
            throw new RuntimeException("Error occurred while creating test cycle", e);
        }
    }
    @When("User link the JIRA issue with ID {string} to the Zephyr test cycle with ID {string}")
    public void iLinkTheIssueToTheTestCycleWithId(String issueId, String testCycleId) {
        try {
            // Ensure Zephyr API is configured with base URL and token
            zephyrPage.ZephyrAPI(apiBaseUrl, apiToken);

            // Call utility to link the issue
            boolean isLinked = zephyrPage.linkIssueToTestCycle(issueId, testCycleId);

            if (!isLinked) {
                throw new RuntimeException("Failed to link the issue " + issueId + " to the test cycle " + testCycleId);
            }

            System.out.println("Issue " + issueId + " successfully linked to test cycle " + testCycleId);
        } catch (Exception e) {
            throw new RuntimeException("Error occurred while linking the issue to the test cycle", e);
        }
    }
    @And("the test execution payload is loaded from {string}")
    public void the_test_execution_payload_is_loaded_from(String filePath) throws IOException {
        String content = new String(Files.readAllBytes(Paths.get(filePath)));
        this.testExecutionPayload = new JSONObject(content);
    }
//    @Then("I link the testcase {string} to the test cycle with ID {string}")
//    public void i_link_the_testcase_to_the_test_cycle_with_id(String testcaseKey, String testCycleKey) {
//        try {
//            // Ensure Zephyr API is configured with base URL and token
//            zephyrPage.ZephyrAPI(apiBaseUrl, apiToken);
//            String projectKey = testExecutionPayload.getString("projectKey");
//            // Call utility method to link the test case
//            boolean isLinked = zephyrPage.linkTestCaseToTestCycle(projectKey, testcaseKey, testCycleKey);
//
//            if (!isLinked) {
//                throw new RuntimeException("Failed to link the testcaseKey " + testcaseKey + " to the test cycle " + testCycleKey);
//            }
//
//            System.out.println("Testcase " + testcaseKey + " successfully linked to test cycle " + testCycleKey);
//        } catch (Exception e) {
//            System.err.println("Error while linking testcase: " + e.getMessage());
//            throw new RuntimeException("Error occurred while linking the testcase to the test cycle", e);
//        }
//    }
@Then("User link the testcases {string} to the zephyr test cycle with ID {string}")
public void i_link_the_testcases_to_the_test_cycle_with_id(String testCases, String testCycleKey) {
    try {
        // Ensure Zephyr API is configured with base URL and token
        zephyrPage.ZephyrAPI(apiBaseUrl, apiToken);
        String projectKey = testExecutionPayload.getString("projectKey");

        // Split the test cases by comma
        String[] testCaseKeys = testCases.split(",");

        // Iterate over the test case keys and link each one to the test cycle
        for (String testcaseKey : testCaseKeys) {
            testcaseKey = testcaseKey.trim();  // Clean up any leading/trailing spaces
            boolean isLinked = zephyrPage.linkTestCaseToTestCycle(projectKey, testcaseKey, testCycleKey);

            if (!isLinked) {
                throw new RuntimeException("Failed to link the testcaseKey " + testcaseKey + " to the test cycle " + testCycleKey);
            }

            System.out.println("Testcase " + testcaseKey + " successfully linked to test cycle " + testCycleKey);
        }
    } catch (Exception e) {
        System.err.println("Error while linking testcases: " + e.getMessage());
        throw new RuntimeException("Error occurred while linking the testcases to the test cycle", e);
    }
}
}
