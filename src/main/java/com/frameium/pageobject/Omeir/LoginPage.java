package com.frameium.pageobject.Omeir;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.frameium.genericfunctions.GenericFunctions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
public class LoginPage extends GenericFunctions {

    private By signIn = By.linkText("Sign In");
    private By username = By.xpath("//input[@placeholder=\"Email\"]");
    private By password = By.xpath("//input[@placeholder=\"Password\"]");
    private By loginButton = By.xpath("//button[@class=\"button\"]");
    private String expectedTitle = "OmeirTravel | Online Booking for Flights & Hotels";
    private WebDriver driver;

    public LoginPage(WebDriver driver) {
        this.driver = driver;
    }

    public void clickSignin() {
        clickElement(signIn);

    }

    public void EnterCredentials(String Username, String Password) {
        enterKeys(username, Username);
        enterKeys(password, Password);
    }

    public void clickLogin() {
        clickElement(loginButton);

    }

    public String getActualTitle() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(60));
        wait.until(ExpectedConditions.titleContains(expectedTitle));
        return driver.getTitle();
    }
}