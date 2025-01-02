package com.frameium.pageobject.Swag;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.frameium.genericfunctions.GenericFunctions;
import com.typesafe.config.ConfigException.Generic;

public class AboutPage extends GenericFunctions {
	
	private By userIcon = By.xpath("//*[@id='react-burger-menu-btn']");
	private By aboutLink = By.xpath("//*[@id='about_sidebar_link']");
	

	public AboutPage(WebDriver driver) {
		driver = this.driver;
	}

	public void navigateToAboutPage() throws InterruptedException {
		Thread.sleep(1000);
		clickObject(userIcon);
		Thread.sleep(1000);
        clickObject(aboutLink);
		
	}

	public void verifyPageTitle(String expectedTitle) {
		String actualTitle = driver.getTitle();
		assert actualTitle.equals(expectedTitle) : "Expected title: " + expectedTitle + ", but got: " + actualTitle;
	}
}
