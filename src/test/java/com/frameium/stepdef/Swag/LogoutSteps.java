package com.frameium.stepdef.Swag;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.github.bonigarcia.wdm.WebDriverManager;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;

import com.frameium.pageobject.Swag.LoginPage;
import com.frameium.pageobject.Swag.LogoutPage;


public class LogoutSteps {

    private WebDriver driver;
    private LoginPage loginPage;
    private LogoutPage logoutPage;
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
	
	// Given-When-Then structure for scenarios in LogoutPage.feature

    @Given("I am on the Saucedemo login page")
    public void IamOnTheSaucedemoLoginPage() {
        loginPage = new LoginPage(driver); 
        logoutPage = new LogoutPage(driver);  // Add this line to initialize logoutPage

    }

    @When("I log in with the credentials: {string} and password {string}")
    public void ILogInWithTheCredentialsAndPassword(String username, String password) throws InterruptedException {
    	Assert.assertTrue(loginPage.performValidLogin(username, password), "loginfailed");
    }

    @And("I perform logout action from the user icon")
    public void IPerformLogoutActionFromTheUserIcon() throws InterruptedException {
    	Assert.assertTrue(logoutPage.performLogoutFromIcon(), "Logout failed");
    }

    @Then("I should see the login button on the main page")
    public void iShouldBeLoggedOutSuccessfully() {
        Assert.assertTrue(logoutPage.isLoggedOut(), "Logout was not successful");
    }
    
    
    @Given("I am on the Saucedemo login page to login")
    public void SaucedemoLoginPage() {
		loginPage = new LoginPage(driver);
        logoutPage = new LogoutPage(driver);
    }

    @When("I log in with username {string} and password {string}")
    public void LogInWithTheCredentialsAndPassword(String username, String password) throws InterruptedException {
    	Assert.assertTrue(loginPage.performValidLogin(username, password), "loginfailed");
    }

    @And("I perform logout from icon")
    public void PerformLogoutActionFromTheUserIcon() throws InterruptedException {
    	Assert.assertTrue(logoutPage.performLogoutFromIcon(), "Logout failed");
    }

    @Then("I attempt to access the main page after logout")
    public void iAttemptToAccessTheMainPageAfterLogout() {
    	 if (driver != null) {
    	        String currentUrl = driver.getCurrentUrl();
    	        // Assert that the current URL is as expected
    	        Assert.assertEquals(currentUrl, "https://www.saucedemo.com/");
    	    } else {
    	        // Log or handle the case where the driver is null
    	        System.out.println("Driver is null");
    	    }
   }
}
