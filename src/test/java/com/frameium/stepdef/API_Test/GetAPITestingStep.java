package com.frameium.stepdef.API_Test;


import com.frameium.utilities.apiutils.GetAPITesting;
import io.cucumber.java.en.*;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.testng.Assert;

public class GetAPITestingStep {

//    private Response response;
    private GetAPITesting getapi;

    public GetAPITestingStep() {

        getapi = new GetAPITesting();
    }

    @Given("The base URI is {string}")
    public void the_base_uri_is(String baseURI) {
        getapi.setBaseURI(baseURI);
    }

    @When("a {string} request is sent to the base URI with end point {string}")
    public void sendRequest(String method, String endPoint) {
        if (method.equalsIgnoreCase("GET")) {
            getapi.sendGetRequest(endPoint);
        } else {
            throw new IllegalArgumentException("Unsupported HTTP method: " + method);
        }
    }

    @Then("I should get the following keys {string} and values {string} from the json response")
    public void checkResponse(String key, String value) {

        String actualValue = getapi.getResponseValue(key);
        Assert.assertEquals(actualValue,value,"The value for the key '" + key + "' does not match.");
    }
}
