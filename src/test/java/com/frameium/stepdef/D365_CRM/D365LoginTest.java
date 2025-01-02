package com.frameium.stepdef.D365_CRM;

import com.frameium.genericfunctions.GenericFunctions;
import com.frameium.pageobject.D365_CRM.D365LoginPage;

import com.frameium.stepdef.Hooks;
import com.frameium.stepdef.TestSetUp;
import cucumber.annotation.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.testng.Assert;
import org.openqa.selenium.WebDriver;

import java.net.MalformedURLException;


public class D365LoginTest {
    Hooks hooks = new Hooks();

    TestSetUp setUp;
    private GenericFunctions genericFunctions;
    private D365LoginPage d365LoginTest;
    private WebDriver driver;


    public D365LoginTest(TestSetUp setUp) throws MalformedURLException {
        this.setUp = setUp;
        genericFunctions = new GenericFunctions(setUp.baseTest.driver);
        d365LoginTest = new D365LoginPage(setUp.baseTest.driver);
        // Assign the driver from setUp to the local driver variable
        this.driver = setUp.baseTest.driver;
    }


    @When("User enters valid {string} and {string} credentials and clicks the Sign In button")
    public void user_enters_valid_credentials_and_clicks_sign_in_button(String email, String password) {
        d365LoginTest.loginWithValidCredentials(email, password);
    }

    @When("User clicks the Next button")
    public void user_clicks_next_button() {
        d365LoginTest.clickButtonWithValue("Next");
    }

    @When("User clicks the Skip link")
    public void user_clicks_skip_link() {
        d365LoginTest.clickSkipLink();
    }

    @When("User click the Yes button to confirm")
    public void user_click_the_yes_button_to_confirm() {
        d365LoginTest.clickButtonWithValue("Yes");
        d365LoginTest.clickDynamics365InFrame();
    }

    @Then("User should be successfully logged in and navigated to the Dynamics 365 Sales Hub CRM page")
    public void user_should_be_successfully_logged_in_and_navigated_to_the_Dynamics_365_Sales_Hub_CRM_page() {
        // Define the expected part of the URL for the Dynamics 365 Sales Hub CRM page
        String expectedUrlPart = "https://thdynamics365sandbox.crm4.dynamics.com/main.aspx";

        // Get the current URL from the browser
        String currentUrl = driver.getCurrentUrl();

        // Assert that the current URL contains the expected part for the Dynamics 365 Sales Hub CRM page
        Assert.assertTrue(currentUrl.contains(expectedUrlPart));
    }


    @When("User click on the D365 app")
    public void userClickOnTheDApp() {
        d365LoginTest.clickDynamics365InFrame();
    }
}
