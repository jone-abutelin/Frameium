package com.frameium.pageobject.D365;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.frameium.genericfunctions.GenericFunctions;

public class Sales extends GenericFunctions {

	private By username = By.xpath("//input[@name='loginfmt']");
	private By password = By.xpath("//input[@type='password']");
	private By submit = By.id("idSIButton9");
	private By usernameError = By.id("usernameError");
	private By passwordError = By.id("passwordError");
	private By submit1 = By.id("idSubmit_ProofUp_Redirect");
	private By skipsubmit = By.xpath("//a[@class='a6b2BSrznMwPrVB6dvBqGQ==']");
	private By buttonNo = By.id("idBtn_Back");

//logout

	private By userIcon = By.xpath("//button[@aria-label='Account manager for Auto TestUser6']");
	private By signout = By.xpath("//button[@aria-label='Sign out of this account']");

	//public Sales(WebDriver driver) {
		//this.driver = driver; // Initialize the driver directly in the subclass
	//}
	
	public Sales(WebDriver driver) {
		driver = this.driver;
	}

	 public boolean validLogin(String usernameValue, String passwordValue) throws InterruptedException {
	        enterKeys(username, usernameValue);
	        clickObject(submit);

	        // Wait for the password field or check for username error
	        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
	        try {
	            wait.until(ExpectedConditions.visibilityOfElementLocated(password));
	        } catch (org.openqa.selenium.TimeoutException e) {
	            if (isErrorDisplayed(usernameError)) {
	                return false; // Username error found, login failed
	            }
	        }

	        // If the password field is found, enter the password
	        enterKeys(password, passwordValue);
	        clickObject(submit);

	        Thread.sleep(2000); // Assuming some wait time for the login process
	        clickObject(submit1);
	        clickObject(skipsubmit);
	        clickObject(buttonNo);

	        Thread.sleep(6000);

	        return isLoginSuccess();
	    }

	    public boolean logout() {
	        clickObject(userIcon);
	        clickObject(signout);
	        return true;
	    }

	    private boolean isErrorDisplayed(By errorLocator) {
	        try {
	            return findElement(errorLocator).isDisplayed();
	        } catch (org.openqa.selenium.NoSuchElementException e) {
	            return false;
	        }
	    }

	    private boolean isLoginSuccess() {
	        try {
	            // Check if error messages
	            return !driver.findElement(usernameError).isDisplayed() && !driver.findElement(passwordError).isDisplayed();
	        } catch (org.openqa.selenium.NoSuchElementException e) {
	            return true; // Assuming the absence of the error messages indicates success
	        }
	    }
}
