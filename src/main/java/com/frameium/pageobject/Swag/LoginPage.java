package com.frameium.pageobject.Swag;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import com.frameium.genericfunctions.GenericFunctions;

public class LoginPage extends GenericFunctions {

	private By usernameLocator = By.id("user-name");
	private By passwordLocator = By.id("password");
	private By loginButtonLocator = By.id("login-button");
	private By errorMessageLocator = By.xpath("//*[@data-test='error']");
	private WebDriver driver;

	public LoginPage(WebDriver driver) {
		this.driver = driver;
	}

	public boolean performValidLogin(String username, String password) throws InterruptedException {
		
		enterKeys(usernameLocator,username);
		enterKeys(passwordLocator,password);
		clickObject(loginButtonLocator);
	    //driver.findElement(usernameLocator).sendKeys(username);
	    //driver.findElement(passwordLocator).sendKeys(password);
	    //driver.findElement(loginButtonLocator).click();

	    Thread.sleep(2000); // Assuming some wait time for the login process

	    return isLoginSuccessful();
	}


	public boolean performInvalidLogin(String username, String password) throws InterruptedException {
		clearAndEnterKeys(usernameLocator,username);
		clearAndEnterKeys(passwordLocator,password);
		clickObject(loginButtonLocator);
		//driver.findElement(usernameLocator).sendKeys("invalid_username");
		//driver.findElement(passwordLocator).sendKeys("invalid_password");
		//driver.findElement(loginButtonLocator).click();

		Thread.sleep(2000); // Assuming some wait time for the login process

		return errorMessageDisplayed();
	}

	private void clearAndEnterKeys(By locator, String keys) {
		driver.findElement(locator).clear();
		driver.findElement(locator).sendKeys(keys);
	}

	public boolean isLoginSuccessful() {
		try {
			return !driver.findElement(errorMessageLocator).isDisplayed();
		} catch (org.openqa.selenium.NoSuchElementException e) {
			return true; // Assuming the absence of the error message indicates success
		}
	}

	public boolean errorMessageDisplayed() {
		try {
			return driver.findElement(errorMessageLocator).isDisplayed();
		} catch (org.openqa.selenium.NoSuchElementException e) {
			return false; // Error message is not displayed
		}
	}

	public boolean performLockedUserLogin() throws InterruptedException {
		return performInvalidLogin("lllocked_out_user", "anypassword");
	}

	public boolean performEmptyLogin() {
		driver.findElement(loginButtonLocator).click();
		return errorMessageDisplayed();
	}

	public boolean performSpecialCharacterLogin(String username, String password) throws InterruptedException {
		enterKeys(usernameLocator,username);
		enterKeys(passwordLocator,password);
		clickObject(loginButtonLocator);
		
		
		//driver.findElement(usernameLocator).sendKeys(username);
		//driver.findElement(passwordLocator).sendKeys(password);
		//driver.findElement(loginButtonLocator).click();

		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		wait.until(ExpectedConditions.visibilityOfElementLocated(errorMessageLocator));

		return errorMessageDisplayed();
	}
}
