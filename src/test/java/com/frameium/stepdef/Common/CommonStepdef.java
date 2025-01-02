package com.frameium.stepdef.Common;

import com.frameium.stepdef.TestSetUp;
import com.frameium.utilities.apiutils.Api;
import com.frameium.utilities.apiutils.Zephyr;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.restassured.response.Response;
import org.apache.xmlbeans.impl.xb.xsdschema.Public;
import org.openqa.selenium.WebDriver;

import java.net.MalformedURLException;
import java.util.HashMap;
import java.util.Map;

public class CommonStepdef {
    private WebDriver driver;
    private Map<String, String> headers = new HashMap<>();
    private Map<String, String> headers1 = new HashMap<>();
    private Map<String, String>  jiraAttachmentHeaders = new HashMap<>();

    String authHeader = "Basic am9uZS5hYnV0ZWxpbkB0ZXN0aG91c2UubmV0OkFUQVRUM3hGZkdGMEQycDVlN0FoQkFVYWNWbV83dXRZcE8wdGhFRWpOTElTbDJaSzZHTHJnWF9yZm5IZWt2LXFrc1pSZHoxOUgwbHJoNWEtQ21aU09OejR2SThoQlRpcEo1R3JLb3BZNFI0NEo3VGpaY0xtNmxaV084bFZ6YUNla1lCNUM4bzY0ZEpGdXFNZ1pmSDVwbGM3dy1XLWx6SS1fTWdUTFNZV0lwdHQxSkxmdHpmbGVwWT1FM0Q2Njg1MA==";

    String apiTokenZephyr = "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJjb250ZXh0Ijp7ImJhc2VVcmwiOiJodHRwczovL2ZyYW1laXVtLXRlYW0uYXRsYXNzaWFuLm5ldCIsInVzZXIiOnsiYWNjb3VudElkIjoiNzEyMDIwOjRlMTA4YWEzLTEzZTQtNDZjZS05ZDNlLTFkOTUxYjk5MmFiZiJ9fSwiaXNzIjoiY29tLnRoZWQuemVwaHlyLmplIiwic3ViIjoiNzlmMTMxMjAtMDE2MC0zNDcyLWI1YWQtNTRiY2RkMjU0MTFlIiwiZXhwIjoxNzU0NjY3MTgzLCJpYXQiOjE3MjMxMzExODN9.sALes8J5KtRNG622kZpWOlKY5UZewjH3NPHgbtkbnwc";
    public Api api;
    public Zephyr zephyr;


    TestSetUp setUp;
    public CommonStepdef(TestSetUp setUp) throws MalformedURLException {
        this.setUp = setUp;
        api=new Api(setUp.baseTest.driver);
      //  zephyr = new Zephyr(setUp.baseTest.driver);
        // Assign the driver from setUp to the local driver variable
        this.driver = setUp.baseTest.driver;
    }
    @Given("The base URI of JIRA is {string}")
    public void the_base_uri_of_jira_is(String baseURI) {
        api.setBaseURIValue(baseURI);
    }

    @And("the header of JIRA is set")
    public void setAuthorizationHeader() {
        headers.put("Content-Type", "application/json");
        headers.put("Authorization", authHeader);
        Api.setHeaders(headers);    }
    @And("the header of JIRA issue attachment is set")
    public void setAttachmentHeader() {
        jiraAttachmentHeaders.put("Content-Type", "application/json");
        jiraAttachmentHeaders.put("Authorization", authHeader);
        jiraAttachmentHeaders.put("X-Atlassian-Token", "no-check");
        Api.setHeaders(jiraAttachmentHeaders);    }

    @And("the header of demo_api is set")
    public void setApiHeader() {
        headers1.put("Content-Type", "application/json");
        Api.setDemoHeader(headers1);
    }

}
