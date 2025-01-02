package com.frameium.pageobject.Salesforce;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.frameium.genericfunctions.GenericFunctions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

/**
 * This class encapsulates the functionality for logging into Salesforce.
 */
public class Login extends GenericFunctions {

	// Locators for various elements on the lead page
	private By usernameLocator = By.id("username");
	private By passwordLocator = By.id("password");
	private By loginButtonLocator = By.id("Login");
	private String expectedTitle = "Home | Salesforce";
	private WebDriver driver;

	/**
	 * Constructor for Login.
	 *
	 * @param driver The WebDriver instance to interact with the browser.
	 */
	public Login(WebDriver driver) {
		this.driver = driver;
	}

	/**
	 * Logs into Salesforce with the provided username and password.
	 *
	 * @param username The username to log in.
	 * @param password The password to log in.
	 * @throws InterruptedException If the thread is interrupted while sleeping.
	 */
	public void login(String username, String password) throws InterruptedException {
		enterCredentials(username, password);
		clickLogin();
	}

	/**
	 * Enters the provided username and password into the login fields.
	 *
	 * @param username The username to enter.
	 * @param password The password to enter.
	 * @throws InterruptedException If the thread is interrupted while sleeping.
	 */
	public void enterCredentials(String username, String password) throws InterruptedException {
		enterKeys(usernameLocator, username);
		enterKeys(passwordLocator, password);
	}

	/**
	 * Clicks on the login button.
	 */
	public void clickLogin() {
		clickElement(loginButtonLocator);

	}

	/**
	 * Waits for the page title to contain the expected title and returns the actual title.
	 *
	 * @return The actual title of the page after login.
	 */
    public String getActualTitle() {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(1000));
		wait.until(ExpectedConditions.titleContains(expectedTitle));
		return driver.getTitle();
    }
}
