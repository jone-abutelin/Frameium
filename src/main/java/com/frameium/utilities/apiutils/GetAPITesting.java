package com.frameium.utilities.apiutils;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import java.util.Map;

public class GetAPITesting {

    private String baseURI;
    private Response response;

    public void setBaseURI(String baseURI) {
        this.baseURI = baseURI;
    }

    public void sendGetRequest(String endpoint) {
        Map<String, String> headers = Map.of("Content-Type", "application/json");
        String requestURI = baseURI + endpoint;

        try {
            System.out.println("Request URI: " + requestURI);
            System.out.println("Request Headers: " + headers);

            response = RestAssured.given().headers(headers).get(requestURI);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public String getResponseValue(String key) {
        if (response != null) {
            JsonObject responseBody = JsonParser.parseString(response.getBody().asString()).getAsJsonObject();
            return responseBody.getAsJsonObject("data").get(key).getAsString();
        } else {
            throw new IllegalStateException("Response object is null. Request might have failed.");
        }
    }
}
