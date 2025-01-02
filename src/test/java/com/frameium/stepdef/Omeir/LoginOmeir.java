package com.frameium.stepdef.Omeir;
import com.frameium.configuration.ObjectReader;
import com.frameium.genericfunctions.GenericFunctions;
import com.frameium.pageobject.Omeir.LoginPage;
import com.frameium.stepdef.Hooks;
import com.frameium.stepdef.TestSetUp;
import com.frameium.utilities.ClientSidePerformanceReportUtils;
import io.cucumber.java.en.When;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;

import java.net.MalformedURLException;

public class LoginOmeir extends GenericFunctions {
    private String expectedTitle = "OmeirTravel | Online Booking for Flights & Hotels";
    Hooks hooks = new Hooks();
    private WebDriver driver;
    TestSetUp setUp;
    private GenericFunctions genericFunctions;
    private LoginPage login;

    public LoginOmeir(TestSetUp setUp) throws MalformedURLException {
        this.setUp = setUp;
        genericFunctions = new GenericFunctions(setUp.baseTest.driver);

        login = new LoginPage(setUp.baseTest.driver);
        // Assign the driver from setUp to the local driver variable
        this.driver = setUp.baseTest.driver;

    }

    @Given("User login to page {string}")
    public void user_login_to_page(String Url) {

        // Write code here that turns the phrase above into concrete actions
        genericFunctions.getApplicationUrl(Url);
    }

    @When("I click on sign in")
    public void i_click_on_sign_in() {
        // Write code here that turns the phrase above into concrete actions
        login.clickSignin();
    }

    @When("enter my username {string} and password {string}")
    public void enter_my_username_and_password(String Username, String Password) {
        // Write code here that turns the phrase above into concrete actions
        login.EnterCredentials(Username, Password);
    }

    @When("click on the login button")
    public void click_on_the_login_button() {
        // Write code here that turns the phrase above into concrete actions
        login.clickLogin();
        ClientSidePerformanceReportUtils clientSidePerformanceReportUtils = new ClientSidePerformanceReportUtils(driver);
        clientSidePerformanceReportUtils.generateReport();
    }


    @Then("I should be logged in successfully logged in to home page")
    public void i_should_be_logged_in_successfully_logged_in_to_home_page() {
        // Write code here that turns the phrase above into concrete actions
        String actualTitle = login.getActualTitle();
        System.out.println(actualTitle);
        Assert.assertEquals(actualTitle, expectedTitle, "Login Success");
    }

}