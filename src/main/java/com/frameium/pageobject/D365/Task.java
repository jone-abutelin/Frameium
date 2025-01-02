package com.frameium.pageobject.D365;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.frameium.genericfunctions.GenericFunctions;

public class Task extends GenericFunctions {

	private By activities = By.xpath("//span[contains(text(), 'Activities')]");
	private By task = By.xpath("//button[@aria-label='Task']");
	private By subject = By.xpath("//input[@aria-label='Subject']");
	private By saveAndClose = By.cssSelector("[title='Save & Close']");

	@SuppressWarnings("static-access")
	//public Task(WebDriver driver) {
		//this.driver = driver;
	//}
	
	public Task(WebDriver driver) {
		driver = this.driver;
	}

	public void createNewTask(String name) {
		clickObject(activities);
		clickObject(task);
		fillTaskDetails(name);
		saveAndCloseTask();
	}

	public void fillTaskDetails(String name) {
		enterKeys(subject, name);
		//setDuration(duration);
	}

	/*public void setDuration(String durationValue) {
		// Click on the dropdown button to expand the dropdown
		WebElement durationDropdownButton = driver.findElement(By.xpath(
				"//div[@id='id-16654191-19ad-48ca-9e46-c4b4fbdad76f-4-actualdurationminutesaa987274-ce4e-4271-a803-66164311a958-actualdurationminutes.fieldControl-duration-combobox_button']"));
		durationDropdownButton.click();
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
		String durationInputXpath = "//input[@id='id-16654191-19ad-48ca-9e46-c4b4fbdad76f-4-actualdurationminutesaa987274-ce4e-4271-a803-66164311a958-actualdurationminutes.fieldControl-duration-combobox_textInput' and @title='15 minutes']";
		WebElement durationInput = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(durationInputXpath)));
		durationInput.click();
	}*/

	public boolean saveAndCloseTask() {
		try {
			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
			WebElement saveButton = wait.until(ExpectedConditions.visibilityOfElementLocated(saveAndClose));
			saveButton.click();
			// Check if error message is displayed
	        boolean errorMessageDisplayed = errorMessageDisplayed();
	        
	        // If no error message is displayed, return true
	        return !errorMessageDisplayed;
	        } catch (Exception e) {
			System.out.println("Failed to save and close task: " + e.getMessage());
			return false;
		}
	}

	public boolean isErrorMessageDisplayed() {
		clickObject(activities);
		clickObject(task);
		clickObject(saveAndClose);
		return errorMessageDisplayed();
	}

	public boolean errorMessageDisplayed() {
		try {
			WebElement errorMessageElement = driver
					.findElement(By.xpath("//span[contains(text(), 'Subject: Required fields must be filled in.')]"));
			return errorMessageElement.isDisplayed();
		} catch (NoSuchElementException e) {
			return false;
		}
	}

}
