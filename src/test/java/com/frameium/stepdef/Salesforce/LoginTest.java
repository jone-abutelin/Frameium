package com.frameium.stepdef.Salesforce;

import static org.testng.Assert.assertTrue;

import java.net.MalformedURLException;
import java.time.Duration;

import com.frameium.stepdef.Hooks;
import com.frameium.stepdef.TestSetUp;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import com.frameium.genericfunctions.GenericFunctions;
import com.frameium.pageobject.Salesforce.Login;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

/**
 * This class contains step definitions for login related scenarios in Salesforce.
 */

public class LoginTest extends GenericFunctions{

	private Login salesforce;
	private String expectedTitle = "Home | Salesforce";
	Hooks hooks = new Hooks();
	private WebDriver driver;
	TestSetUp setUp;
	private GenericFunctions genericFunctions;

	/**
	 * Constructor to initialize the test setup, driver, and page objects.
	 *
	 * @param setUp the TestSetUp object containing the base test setup
	 * @throws MalformedURLException if there is a URL error
	 */

	public LoginTest(TestSetUp setUp) throws MalformedURLException {
		this.setUp = setUp;
		genericFunctions = new GenericFunctions(setUp.baseTest.driver);
		salesforce=new Login(setUp.baseTest.driver);
		// Assign the driver from setUp to the local driver variable
		this.driver = setUp.baseTest.driver;
	}

	/**
	 * Step definition to navigate to the Salesforce login page.
	 */

	@Given("I am on the Salesforce login page")
	public void i_am_on_the_salesforce_login_page() {
	}

	/**
	 * Step definition to enter username and password.
	 *
	 * @param username the username for login
	 * @param password the password for login
	 * @throws InterruptedException if interrupted during sleep
	 */

	@When("I enter my username {string} and password {string}")
	public void i_enter_my_username_and_password(String username, String password) throws InterruptedException {
		salesforce.enterCredentials(username, password);
	}

	/**
	 * Step definition to click the login button.
	 */

	@When("I click on the login button")
	public void i_click_on_the_login_button() {
		salesforce.clickLogin();

	}

	/**
	 * Step definition to verify successful login to Salesforce.
	 */

	@Then("I should be logged in to Salesforce successfully")
	public void i_should_be_logged_in_to_salesforce_successfully() {
		String actualTitle = salesforce.getActualTitle();
		System.out.println(actualTitle);
		try {
			Assert.assertEquals(actualTitle, expectedTitle, "Login to Salesforce was not successful.");
			logToExtentReport("Pass", "Expected title: " + expectedTitle + " matches actual title: " + actualTitle + ". Login to Salesforce was successful.");
		} catch (AssertionError e) {
			logToExtentReport("Fail", "Expected title: " + expectedTitle + ", but found: " + actualTitle);
			throw e;
		}		hooks.takeScreenshot(hooks.scenario);
	}
}
