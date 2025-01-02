package com.frameium.stepdef.Swag;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import io.github.bonigarcia.wdm.WebDriverManager;
import io.cucumber.java.en.Then;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;

import com.frameium.pageobject.Swag.AboutPage;
import com.frameium.pageobject.Swag.LoginPage;

public class AboutSteps {

	private WebDriver driver;
	private LoginPage loginPage;
	private AboutPage aboutPage;
	private static final String BASE_URL = "https://www.saucedemo.com/";

	// @Before
	public void setUp() {
		// Set up WebDriver
		WebDriverManager.chromedriver().setup();
		driver = new ChromeDriver();
		driver.get(BASE_URL);
	}

	// @After
	public void tearDown() {
		// Close the browser
		driver.quit();
	}

	// Given-When-Then structure for scenarios in AboutPage.feature

	@Given("I login with username {string} and password {string}")
	public void iLoginWithUsernameAndPassword(String username, String password) throws InterruptedException {
		loginPage = new LoginPage(driver);
		Assert.assertTrue(loginPage.performValidLogin(username, password), "loginfailed");
	}

	@When("I navigate to the About page of swagdemo")
	public void INavigateToTheAboutPage() throws InterruptedException {
		aboutPage = new AboutPage(driver);
		aboutPage.navigateToAboutPage();
	}

	@Then("I should be on the About page")
	public void iShouldBeOnTheAboutPage() {
		if (driver != null) {
			String currentUrl = driver.getCurrentUrl();
			// Assert that the current URL is as expected
			Assert.assertEquals(currentUrl, "https://saucelabs.com/");
		} else {
			// Log or handle the case where the driver is null
			System.out.println("Driver is null");
		}
	}

	@Given("I am in the About page")
	public void IAmInTheAboutPage() {
		// No action needed here, as it's just a marker step
	}

	@Then("The About page title should be {string}")
	public void theAboutPageTitleShouldBe(String expectedTitle) {
		if (aboutPage != null) {
	        aboutPage.verifyPageTitle(expectedTitle);
	    } else {
	        System.out.println("AboutPage instance is null, initializing...");
	        aboutPage = new AboutPage(driver);
	        aboutPage.verifyPageTitle(expectedTitle);
	    }
	}
}
