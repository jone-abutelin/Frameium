package com.frameium.testscripts;


import static io.restassured.RestAssured.given;

import java.io.IOException;
import org.apache.log4j.Logger;
import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.frameium.logger.LoggerHelper;
import com.frameium.pageobject.Payload;

import io.restassured.RestAssured;
import io.restassured.parsing.Parser;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

public class APIGenericFunctions {

private Logger log = LoggerHelper.getLogger(APIGenericFunctions.class);
public  Response TokenGeneration(String payload)
	{	
	Response response= given()
	        .header("Content-type", "application/json")
	        .and()
	        .body(payload)
	        .when().log().all()
	        .post("/oauth/token");
	Assertions.assertEquals(200, response.getStatusCode());
    	    
	return response;
		
	
	}

public  Response Carrefour(String accessToken,String url,String endpoint,String xapikey,String payload)

{
	 RestAssured.baseURI = url;
	    
	  Response response= given()
	            .header("Content-type", "application/json")
	            .header("Authorization",accessToken)
	            .header("x-api-key",xapikey)
	            .and()
	            .body(payload)
	            .when().log().all()
	            .post(endpoint);
	    
	  
return response;
	

}

public  Response ZephyrTestExecution(String accessToken,String url,String endpoint,String payload)
	{
	     RestAssured.baseURI = url;

	      Response response= given()
	                .header("Content-type", "application/json")
	                .header("Authorization",accessToken)
	                .and()
	                .body(payload)
	                .when().log().all()
	                .post(endpoint);

	return response;
	}
	
	
	public  Response ZephyrTestScript(String accessToken,String url,String endpoint) 
	{
	     RestAssured.baseURI = url;

	      Response response= given()
	                .header("Content-type", "application/json")
	                .header("Authorization",accessToken)
	                .and()
	                .when().log().all()
	                .get(endpoint).then()
	                .extract().response();
	return response;
	 
}

}