package com.frameium.stepdef.Swag;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import io.github.bonigarcia.wdm.WebDriverManager;
import com.frameium.pageobject.Swag.CartHandling;
import com.frameium.pageobject.Swag.LoginPage;

public class Cart {

    private WebDriver driver;
    private LoginPage loginPage;
    private CartHandling cartHandling;

    //@Before
    public void setUp() {
        // Code to set up WebDriver before scenarios
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
    }

    //@After
    public void tearDown() {
        // Code to close the WebDriver after scenarios
        if (driver != null) {
            driver.quit();
        }
    }

    @Given("I am on the login page for Cart Handling")
    public void navigateToLoginPageForCartHandling() {
        //loginPage = new LoginPage(driver);
    }

    @When("I perform a valid login for cart handling")
    public void performValidLoginForCartHandling() throws InterruptedException {
        Assert.assertTrue(loginPage.performValidLogin("standard_user", "secret_sauce"), "Login failed");
    }

    @And("I go to the Cart")
    public void goToCart() throws InterruptedException {
        cartHandling = new CartHandling(driver);
        cartHandling.goToCart();
    }

    @Then("I should be on the Cart page")
    public void verifyCartPageNavigation() {
    	String expectedUrl = "https://www.saucedemo.com/cart.html";
        String currentUrl = driver.getCurrentUrl();
        Assert.assertEquals(currentUrl, expectedUrl, "Unexpected page after navigating to the About page");
    }

    @And("I should verify the Cart is empty")
    public void verifyCartEmptyStatus() {
        Assert.assertTrue(cartHandling.isCartEmpty(), "Cart is not empty");
    }
}
