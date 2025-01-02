package com.frameium.stepdef;

import java.io.File;
import java.net.MalformedURLException;

import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.frameium.baseclass.TestBase;
import com.frameium.pageobject.PCloudy;

import io.appium.java_client.android.AndroidDriver;

import io.cucumber.java.en.*;



public class Appium extends TestBase{
	
	PCloudy pcloudy = new PCloudy();
	@Test(priority = 0)
	@Given("Open PCloudy application")
	public void open_p_cloudy_application() throws Exception  {
			
		
		//deviceConnection1();
		deviceConnection();
		Thread.sleep(10000);
		
	}
	

	
//	@Test(priority = 0)
//	@Given("User opens application {string}")
//	public void user_opens_application(String appName) throws MalformedURLException, InterruptedException {
//		
//		Thread.sleep(50000);
//		deviceConnection(appName); 
//	}
//	
	
	
	@Test(priority = 1)
	@And("Click on accept button")
	public void click_on_accept_button() {
		pcloudy.pcloudyDemoScreen();   
	}
	@Test(priority = 2)
	@And("Click on Login button")
	public void click_on_login_button() {
		pcloudy.pcloudyLogin();
	}
//	@Test(priority = 3,dependsOnMethods = {"click_on_login_button"})
//	@Given("Enter {string},{string}")
//	public void enter() throws Exception {
//		Thread.sleep(5000);
//		pcloudy.enterUserNameAndPassword("1234","1234");
//	}
	@Test(priority = 3)
	@Parameters({"username","password"})
	@Given("Enter {string},{string}")
	public void enter(String username,String password) throws InterruptedException {
		Thread.sleep(5000);
		pcloudy.enterUserNameAndPassword("username","password");
	}

	
	@Test(priority = 4)
	@Then("Select Login button")
	public void select_login_button() {
		pcloudy.selectLogin();
	}
	@Test(priority = 5)
	@Then("Enter required details")
	public void enter_required_details() {
		pcloudy.requiredDetails();
	}
	@Test(priority = 6)
	@Then("Search flights")
	public void search_flights() {
		pcloudy.searchFlight();
	}
	
	
	/**
	* Description : Check if the text retrieved from tool tip matches with 
	* expected text.
	* @author J Smith
	* @param text - that is expected at the tool tip.
	* @return boolean (true if actual and expected matches)
	*/
	public void verifyToolTipText(String text) {}
	
	/**
	* Description : Tests if the user login functionality works.
	* Functional Test Name : tc001_verify_user_login
	* Data Requirement : Tests uses a valid user id.This needs to be created beforehand.
	* Precondition : User navigates to landing page 
	* @author J Smith
	* @param userid, password  - valid user id and password (password must be in encoded format).
	* @return boolean (true if actual and expected matches)
	*/
	public void testUserLogin(String userid, String password) {}

}