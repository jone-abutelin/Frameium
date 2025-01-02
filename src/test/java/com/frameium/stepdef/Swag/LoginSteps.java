package com.frameium.stepdef.Swag;

import com.frameium.genericfunctions.GenericFunctions;
import com.frameium.stepdef.TestSetUp;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.github.bonigarcia.wdm.WebDriverManager;
import com.frameium.pageobject.Swag.LoginPage;
import com.frameium.pageobject.Swag.LogoutPage;

import java.net.MalformedURLException;

public class LoginSteps {

    private WebDriver driver;
    private LoginPage loginPage;
    private LogoutPage logoutPage;
    private static final String BASE_URL = "https://www.saucedemo.com/";
    private GenericFunctions genericFunctions;

    TestSetUp setUp;

    public LoginSteps(TestSetUp setUp) throws MalformedURLException {

        this.setUp = setUp;
        genericFunctions = new GenericFunctions(setUp.baseTest.driver);

        logoutPage = new LogoutPage(setUp.baseTest.driver);

        loginPage = new LoginPage(setUp.baseTest.driver);

        // Assign the driver from setUp to the local driver variable

        this.driver = setUp.baseTest.driver;

    }


    // Scenario: Valid login and logout
    @Given("I am on the login page")
    public void navigateToLoginPage() {
        loginPage = new LoginPage(driver);
    }

    @When("I perform a valid login")
    public void performValidLogin() throws InterruptedException {
        Assert.assertTrue(loginPage.performValidLogin("standard_user", "secret_sauce"), "Login failed");
    }

    @Then("I should be logged in successfully")
    public void verifyLoginSuccess() {
        Assert.assertTrue(loginPage.isLoginSuccessful(), "Login failed");
    }

    @And("I perform a logout")
    public void performLogout() throws InterruptedException {
        Assert.assertTrue(logoutPage.performLogoutFromIcon(), "Logout failed");
    }

    @Then("I should be logged out")
    public void verifyLogoutSuccess() {
        Assert.assertTrue(logoutPage.isLoggedOut());
    }

    // Scenario: Login with special characters
    @Given("I am on the login page for special character login")
    public void navigateToLoginPageForSpecialCharacterLogin() {
    }

    @When("I perform a login with special characters")
    public void performSpecialCharacterLogin() throws InterruptedException {
        Assert.assertTrue(loginPage.performSpecialCharacterLogin("!@#$", "!@#$"), "Special character login failed");
    }

    @Then("I should see an error message for special character login")
    public void verifyErrorMessageForSpecialCharacterLogin() {
        Assert.assertTrue(loginPage.errorMessageDisplayed(), "Error message is not displayed");
    }

    // Scenario: Locked user login
    @Given("I am on the login page for locked user login")
    public void navigateToLoginPageForLockedUserLogin() {
    }

    @When("I perform a login for a locked-out user")
    public void performLockedUserLogin() throws InterruptedException {
        Assert.assertTrue(loginPage.performLockedUserLogin(), "Locked user login failed");
    }

    @Then("I should see an error message for locked user login")
    public void verifyErrorMessageForLockedUserLogin() {
        Assert.assertTrue(loginPage.errorMessageDisplayed(), "Error message is not displayed");
    }

    // Scenario: Invalid login
    @Given("I am on the login page for invalid login")
    public void navigateToLoginPageForInvalidLogin() {
    }

    @When("I perform an invalid login")
    public void performInvalidLogin() throws InterruptedException {
        Assert.assertTrue(loginPage.performInvalidLogin("invalid_username", "invalid_password"),
                "Invalid login failed");
    }

    @Then("I should see an error message for invalid login")
    public void verifyErrorMessageForInvalidLogin() {
        Assert.assertTrue(loginPage.errorMessageDisplayed(), "Error message is not displayed");
    }

    // Scenario: Empty login
    @Given("I am on the login page for empty login")
    public void navigateToLoginPageForEmptyLogin() {
    }

    @When("I perform an empty login")
    public void performEmptyLogin() {
        Assert.assertTrue(loginPage.performEmptyLogin(), "Empty login failed");
    }

    @Then("I should see an error message for empty login")
    public void verifyErrorMessageForEmptyLogin() {
        Assert.assertTrue(loginPage.errorMessageDisplayed(), "Error message is not displayed");
    }

}
