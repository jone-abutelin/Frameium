package com.frameium.stepdef.API_Test;

import com.frameium.stepdef.TestSetUp;
import com.frameium.utilities.apiutils.Api;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.response.Response;

import com.google.gson.JsonObject;

public class APITestingStep {

    private Api apitest;

    private Response response;
    TestSetUp setUp;


    public APITestingStep() {

        apitest=new Api(setUp.baseTest.driver);
    }

    @Given("the base URI is {string}")
    public void the_base_uri_is(String baseURI) {
        apitest.setBaseURIValue(baseURI);
    }

    @When("A {string} request is sent to {string}")
    public void sendRequestWithoutPayload(String method, String endpoint) {
        response = apitest.sendRequest(method, endpoint, null);
    }

    @When("A {string} request is sent to {string} with payload from filepath {string}")
    public void sendRequestFromFile(String method, String endpoint, String filePath) {
        JsonObject payloadJson = apitest.readPayloadFromFile(filePath);
        response = apitest.sendRequest(method, endpoint, payloadJson);
    }

    @Then("the status code should be {int}")
    public void the_status_code_should_be(int expectedStatusCode) {
        apitest.verifyStatusCode(response, expectedStatusCode);
    }

    @Then("I should get the response")
    public void i_should_get_the_response() {
        apitest.printResponse(response);
    }

    @Then("the API response should contain the item {string}")
    public void theAPIResponseShouldContainTheItemItem(String item) {
        apitest.verifyResponseContainsItem(response, item, null);
    }


}
