package com.frameium.pageobject.Swag;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import com.frameium.genericfunctions.GenericFunctions;

public class LogoutPage extends GenericFunctions {

	private By usericon = By.xpath("//*[@id='react-burger-menu-btn']");
	private By logoutOption = By.xpath("//*[@id='logout_sidebar_link']");
	private By loginButton = By.id("login-button");

	// private WebDriver driver;

	public LogoutPage(WebDriver driver) {
		driver = this.driver;
	}

	public boolean performLogoutFromIcon() throws InterruptedException {
		Thread.sleep(1000);
		clickObject(usericon);
		Thread.sleep(1000);
		clickObject(logoutOption);
		Thread.sleep(1000);
		return isLoggedOut();
	}

	public boolean isLoggedOut() {
		return isDisplayedBy(loginButton);

	}
}
