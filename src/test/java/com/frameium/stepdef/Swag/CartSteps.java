package com.frameium.stepdef.Swag;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.github.bonigarcia.wdm.WebDriverManager;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;

import com.frameium.pageobject.Swag.CartHandling;
import com.frameium.pageobject.Swag.LoginPage;



public class CartSteps {

	private WebDriver driver;
	private LoginPage loginPage;
	private CartHandling cartPage;
	private static final String BASE_URL = "https://www.saucedemo.com/";

	//@Before
	public void setUp() {
		// Set up WebDriver
		WebDriverManager.chromedriver().setup();
		driver = new ChromeDriver();
		driver.get(BASE_URL);
	}

	//@After
	public void tearDown() {
		// Close the browser
		driver.quit();
	}

	// Given-When-Then structure for scenarios in CartPage.feature

	@Given("I login with the given username {string} and password {string}")
	public void ILoginWithGivenUsernameAndPassword(String username, String password) throws InterruptedException {
		loginPage = new LoginPage(driver);
		Assert.assertTrue(loginPage.performValidLogin(username, password), "loginfailed");
	}

	@When("I navigate to the cart")
	public void INavigateToTheCart() throws InterruptedException {
		cartPage = new CartHandling(driver);
		cartPage.goToCart();
	}

	@Then("I should be on the cart page")
	public void iShouldBeOnTheCartPage() {
		if (driver != null) {
			String currentUrl = driver.getCurrentUrl();
			// Assert that the current URL is as expected
			Assert.assertEquals(currentUrl, "https://www.saucedemo.com/cart.html");
		} else {
			// Log or handle the case where the driver is null
			System.out.println("Driver is null");
		}	
	}

	@Given("I am in the cart page")
	public void IAmInTheCartPage() {
		// Assume the navigation is already done
	}

	@Then("I check whether the cart is empty")
	public void ICheckWhetherTheCartIsEmpty() {
        cartPage = new CartHandling(driver); 
	    Assert.assertTrue(cartPage.isCartEmpty(), "Cart is not Empty");
	}
}
