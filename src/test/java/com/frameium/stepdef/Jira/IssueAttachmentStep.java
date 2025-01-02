package com.frameium.stepdef.Jira;

import com.frameium.stepdef.TestSetUp;
import com.frameium.utilities.apiutils.Api;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.*;
import io.restassured.response.Response;
import org.openqa.selenium.WebDriver;

import java.io.File;
import java.net.MalformedURLException;
import java.util.HashMap;
import java.util.Map;

public class IssueAttachmentStep {
    private WebDriver driver;
    private Response response;
    private Map<String, String> headers = new HashMap<>();
    String authHeader = "Basic am9uZS5hYnV0ZWxpbkB0ZXN0aG91c2UubmV0OkFUQVRUM3hGZkdGMEQycDVlN0FoQkFVYWNWbV83dXRZcE8wdGhFRWpOTElTbDJaSzZHTHJnWF9yZm5IZWt2LXFrc1pSZHoxOUgwbHJoNWEtQ21aU09OejR2SThoQlRpcEo1R3JLb3BZNFI0NEo3VGpaY0xtNmxaV084bFZ6YUNla1lCNUM4bzY0ZEpGdXFNZ1pmSDVwbGM3dy1XLWx6SS1fTWdUTFNZV0lwdHQxSkxmdHpmbGVwWT1FM0Q2Njg1MA==";

    private Api api;
    TestSetUp setUp;
    public IssueAttachmentStep(TestSetUp setUp) throws MalformedURLException {
        this.setUp = setUp;
        api=new Api(setUp.baseTest.driver);
        // Assign the driver from setUp to the local driver variable
        this.driver = setUp.baseTest.driver;
    }
    @Given("the additional headers are set")
    public void the_additional_headers_are(DataTable headerTable) {
        headers.putAll(headerTable.asMap(String.class, String.class));
    }
    @When("a {string} request for JIRA issue attachment is sent to {string} with file {string}")
    public void a_request_is_sent_to_with_file(String requestType, String endpoint, String filePath) {
        response = api.sendRequestAttachment(requestType, endpoint, headers, new File(filePath));
    }
    @Then("the response status code should be {int}")
    public void the_response_status_code_should_be(int expectedStatusCode) {
        api.verifyStatusCode(response, expectedStatusCode);
    }
    @Then("the response should contain the JIRA attachment details")
    public void the_response_should_contain_the_attachment_details() {
        String responseBody = response.getBody().asString();
        System.out.println("Response Body: " + responseBody);
    }

}
