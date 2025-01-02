package com.frameium.utilities.apiutils;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class Api {

    private static String baseURI;
    private Response response;
    private static Map<String, String> headers = new HashMap<>();

    private WebDriver driver;
    public Api(WebDriver driver) {

        this.driver = driver;
    }
    public void setBaseURIValue(String baseURI) {
        this.baseURI = baseURI;
    }
    public static void setHeaders(Map<String, String> headers) {
        Api.headers = headers;
    }
    public static void setDemoHeader(Map<String, String> headers) {
        Api.headers = headers;
    }
    public Response sendRequest(String method, String endpoint, JsonObject payloadJson) {
        String requestURI = baseURI + endpoint;

        try {
            System.out.println("Request URI: " + requestURI);
            System.out.println("Request Headers: " + headers);
            switch (method.toUpperCase()) {
                case "GET":
                    response = RestAssured.given().headers(headers).get(requestURI);
                    break;
                case "POST":
                    if (payloadJson != null) {
                        response = RestAssured.given().headers(headers).body(payloadJson.toString()).post(requestURI);
                    } else {
                        response = RestAssured.given().headers(headers).post(requestURI);
                    }
                    break;
                case "PUT":
                    if (payloadJson != null) {
                        response = RestAssured.given().headers(headers).body(payloadJson.toString()).put(requestURI);
                    } else {
                        response = RestAssured.given().headers(headers).put(requestURI);
                    }
                    break;
                case "DELETE":
                    response = RestAssured.given().headers(headers).delete(requestURI);
                    break;
                default:
                    throw new IllegalArgumentException("Unsupported HTTP method: " + method);
            }
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
        return response;
    }

    public JsonObject readPayloadFromFile(String filePath) {
        JsonObject payloadJson = null;
        try {
            if (filePath != null && !filePath.isEmpty()) {
                FileReader reader = new FileReader(filePath);
                payloadJson = JsonParser.parseReader(reader).getAsJsonObject();
                reader.close();
            }
        } catch (IOException e) {
            System.out.println("Error reading payload file: " + e.getMessage());
        }
        return payloadJson;
    }

    public void verifyStatusCode(Response response, int expectedStatusCode) {
        if (response != null) {
            response.then().statusCode(expectedStatusCode);
        } else {
            throw new IllegalStateException("Response object is null. Request might have failed.");
        }
    }

    public void printResponse(Response response) {
        if (response != null) {
            System.out.println("\nResponse received: " + response.getBody().asPrettyString());
        } else {
            throw new IllegalStateException("Response object is null. Request might have failed.");
        }
    }

//    public void verifyResponseContainsItem(Response response, String item) {
//        Assert.assertTrue(response.getBody().asString().contains(item));
////        System.out.println(">>>>>>" + response.getBody().asPrettyString());
//    }
    public void verifyResponseContainsItem(Response response, String item, String expectedValue) {
        if (response != null) {
            String responseBody = response.getBody().asString();
            JsonObject responseJson = JsonParser.parseString(responseBody).getAsJsonObject();

            if (responseJson.has(item)) {
                String actualValue = responseJson.get(item).getAsString();
                // Print actual and expected values for debugging
                System.out.println("Actual Value: " + actualValue);
                System.out.println("Expected Value: " + expectedValue);
                Assert.assertEquals(actualValue, expectedValue,
                        "Expected value for '" + item + "' is '" + expectedValue + "', but got '" + actualValue + "'");
                System.out.println("Actual and expected value of key matched");
            } else {
                Assert.fail("Response does not contain the item '" + item + "'");
            }
        } else {
            throw new IllegalStateException("Response object is null. Request might have failed.");
        }
    }
    public Response sendRequestAttachment(String requestType, String endpoint, Map<String, String> headers, File file) {
        String requestURI = baseURI + endpoint;

        try {
            switch (requestType.toUpperCase()) {
                case "POST":
                    if (file != null) {
                        response = RestAssured.given()
                                .headers(headers)
                                .multiPart("file", file)
                                .when()
                                .post(requestURI);
                    } else {
                        throw new IllegalArgumentException("File is required for POST requests");
                    }
                    break;
                // Handle other methods if needed
                default:
                    throw new IllegalArgumentException("Unsupported HTTP method: " + requestType);
            }
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
        return response;
    }
}
