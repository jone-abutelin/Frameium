package com.frameium.stepdef;

import java.util.Random;

import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.By;

import com.frameium.baseclass.TestBase;
import com.frameium.configuration.ObjectReader;
import com.frameium.pageobject.Payload;
import com.frameium.testscripts.APIGenericFunctions;

import io.cucumber.java.en.*;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

public class API extends TestBase {

APIGenericFunctions generic=new APIGenericFunctions();
String accessToken, Carrefour_Web_Full_Burn_payload,Carrefour_Web_Full_Earn_payload,Carrefour_Web_Split_Pay_payload;
	Payload payload=new Payload();
	Response Carrefour_Web_Full_Earn_response,Carrefour_Web_Split_Pay_response,Carrefour_Web_Full_Burn_response;
	
	@Given("^User has access token generated$")
	public void User_has_access_token_generated() {
		 RestAssured.baseURI = "https://sit.maf-dev.auth0.com";
		 String strresponse =generic.TokenGeneration(payload.TokenGeneration()).asString();
		 JsonPath jsonPath = new JsonPath(strresponse);
		 accessToken = "Bearer "+jsonPath.getString("access_token");		
	}


	@And("^Provide payload for Carrefour Web Full Burn$")
	public void Provide_payload_for_Carrefour_Web_Full_Burn() {
			 Carrefour_Web_Full_Burn_payload=payload.Carrefour_Web_Full_Burn_transaction_using_points_only();
			Random rnd = new Random();
			Carrefour_Web_Full_Burn_payload=Carrefour_Web_Full_Burn_payload.replace("2022-06-19","2023-"+String.valueOf(rnd.nextInt((12 - 1)) + 1) +"-"+ String.valueOf(rnd.nextInt((30 - 1)) + 1));
			Carrefour_Web_Full_Burn_payload=Carrefour_Web_Full_Burn_payload.replace("0329",String.valueOf(rnd.nextInt((9999 - 1000)) + 1000));		 
	}

	
	@Then("Execute POST Request for Carrefour Web Full Burn {string} and {string}")
	public void Execute_POST_Request_for_Carrefour_Web_Full_Burn(String URL,String EndPoint) {	
		Carrefour_Web_Full_Burn_response = generic.Carrefour(accessToken,URL, EndPoint, "kKUiKT2BBM5YaQDxZxHzANko2lTlf4hi",Carrefour_Web_Full_Burn_payload);
	}
	

	@And("^Provide payload for Carrefour Web Full Earn$")
	public void Provide_payload_for_Carrefour_Web_Full_Earn() {
		Carrefour_Web_Full_Earn_payload=payload.Carrefour_Web_Full_Burn_transaction_using_points_only();
			Random rnd = new Random();
			Carrefour_Web_Full_Earn_payload=Carrefour_Web_Full_Earn_payload.replace("2022-06-19","2023-"+String.valueOf(rnd.nextInt((12 - 1)) + 1) +"-"+ String.valueOf(rnd.nextInt((30 - 1)) + 1));
			Carrefour_Web_Full_Earn_payload=Carrefour_Web_Full_Earn_payload.replace("0329",String.valueOf(rnd.nextInt((9999 - 1000)) + 1000));		 
	}

	
	@Then("Execute POST Request for Carrefour Web Full Earn {string} and {string}")
	public void Execute_POST_Request_for_Carrefour_Web_Full_Earn(String URL,String EndPoint) {	
		Carrefour_Web_Full_Earn_response = generic.Carrefour(accessToken,URL, EndPoint, "kKUiKT2BBM5YaQDxZxHzANko2lTlf4hi",Carrefour_Web_Full_Earn_payload);
	}

	@And("^Provide payload for Carrefour Web Split Pay$")
	public void Provide_payload_for_Carrefour_Web_Split_Pay() {
		Carrefour_Web_Split_Pay_payload=payload.Carrefour_Web_Full_Burn_transaction_using_points_only();
			Random rnd = new Random();
			Carrefour_Web_Split_Pay_payload=Carrefour_Web_Split_Pay_payload.replace("2022-06-19","2023-"+String.valueOf(rnd.nextInt((12 - 1)) + 1) +"-"+ String.valueOf(rnd.nextInt((30 - 1)) + 1));
			Carrefour_Web_Split_Pay_payload=Carrefour_Web_Split_Pay_payload.replace("0329",String.valueOf(rnd.nextInt((9999 - 1000)) + 1000));		 
	}

	
	@Then("Execute POST Request for Carrefour Web Split Pay {string} and {string}")
	public void Execute_POST_Request_for_Carrefour_Web_Split_Pay(String URL,String EndPoint) {	
		Carrefour_Web_Split_Pay_response = generic.Carrefour(accessToken,URL, EndPoint, "kKUiKT2BBM5YaQDxZxHzANko2lTlf4hi",Carrefour_Web_Split_Pay_payload);
	}

	@And("^Validate response status code is 201$")
	public void Validate_response_status_code_is_201() {
		Assertions.assertEquals(201, Carrefour_Web_Full_Burn_response.getStatusCode());    
	}
	
	
	
	@And("^Validate response status code for Carrefour Web Full Earn 201$")
	public void Validate_response_status_code_for_Carrefour_Web_Full_Earn_201() {
		Assertions.assertEquals(201, Carrefour_Web_Full_Earn_response.getStatusCode());    
	}
	
	@And("^Validate response status code for Carrefour Web Split Pay 201$")
	public void Validate_response_status_code_for_Carrefour_Web_Split_Pay_201() {
		Assertions.assertEquals(201, Carrefour_Web_Split_Pay_response.getStatusCode());    
	}

	
}