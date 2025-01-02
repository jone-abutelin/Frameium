package com.frameium.stepdef.OutlookIntegrationTest;


import com.frameium.genericfunctions.GenericFunctions;
import com.frameium.utilities.OutlookIntegration.Login;
import com.frameium.stepdef.TestSetUp;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import org.openqa.selenium.WebDriver;

/**
 * This class contains step definitions for testing the login functionality of Outlook.
 */
public class LoginTest {
    private Login outlookLogin;


    private WebDriver driver;
    TestSetUp setUp;
    private GenericFunctions genericFunctions;

    /**
     * Constructor to initialize the LoginTest class with required dependencies.
     * @param setUp The TestSetUp instance used to initialize the test environment.
     */
    public LoginTest(TestSetUp setUp) {
        this.driver = setUp.baseTest.driver;
        this.genericFunctions = new GenericFunctions(driver);
        outlookLogin = new Login(driver, "indhulekha.jaisankar@testhouse.net");
    }

    /**
     * Opens the Outlook URL and waits for the page to load.
     * @param url The URL of the Outlook login page.
     */
    @Then("User opens Url Outlook {string}")
    public void user_opens_url(String url) throws Exception {
        genericFunctions.getApplicationUrl(url);
        genericFunctions.waitForPageToLoad(6000);
    }

    /**
     * Checks if the user is on the Outlook Pick an Account page.
     */
    @Given("I am on the Outlook Pick an account page")
    public void i_am_on_the_outlook_pick_an_account_page() {
    }

    /**
     * Logs into Outlook by picking the email account.
     */
    @Then("I pick the email account")
    public void I_pick_the_email_account() throws InterruptedException {
        outlookLogin.loginToOutlook();
    }
}



