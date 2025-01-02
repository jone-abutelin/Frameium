package com.frameium.pageobject.Swag;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class OrderCheckout {

	private WebDriver driver;

	public OrderCheckout(WebDriver driver) {
		this.driver = driver;
	}

	public void checkout() {
		WebElement checkoutButton = driver.findElement(By.id("checkout"));
		checkoutButton.click();
	}

	public void fillOrderInformation(String firstName, String lastName, String zipCode) {
		WebElement firstNameField = driver.findElement(By.id("first-name"));
		WebElement lastNameField = driver.findElement(By.id("last-name"));
		WebElement zipCodeField = driver.findElement(By.id("postal-code"));
		WebElement continueButton = driver.findElement(By.id("continue"));

		firstNameField.sendKeys(firstName);
		lastNameField.sendKeys(lastName);
		zipCodeField.sendKeys(zipCode);
		continueButton.click();
	}

	public void completeOrder() {
		WebElement finishButton = driver.findElement(By.id("finish"));
		finishButton.click();
	}
}
