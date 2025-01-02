package com.frameium.pageobject.D365_CRM;

import com.frameium.genericfunctions.GenericFunctions;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class D365LoginPage extends GenericFunctions {


    public D365LoginPage (WebDriver driver) {
        driver = this.driver;
    }

    // Locator for a specific application item
    private  By AppItem = By.id("AppModuleTileSec_1_Item_1");

    /**
     * Clicks a button identified by its value attribute.
     *
     * @param value The value attribute of the button to click.
     */
    public void clickButtonWithValue(String value) {
        By buttonLocator = By.cssSelector("input[value='" + value + "']");
        clickObject(buttonLocator);
        waitForButtonActionToComplete();
    }

    /**
     * Waits for the page to fully load by checking the document.readyState property.
     */
    private void waitForButtonActionToComplete() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
        wait.until(webDriver -> ((JavascriptExecutor) driver).executeScript("return document.readyState").equals("complete"));
    }

    /**
     * Types a value into an input field identified by its type attribute.
     *
     * @param type The type attribute of the input field.
     * @param value The value to type into the input field.
     */
    public void typeInputFieldWithLocator(String type, String value) {
        By inputLocator = By.cssSelector("input[type='" + type + "']");
        enterKeys(inputLocator, value);
    }

    /**
     * Clicks a link identified by its text.
     *
     * @param linkText The visible text of the link to click.
     */
    public void clickLinkByText(String linkText) {
        By linkLocator = By.linkText(linkText);
        clickObject(linkLocator);
        waitForButtonActionToComplete();
    }

    /**
     * Logs in using valid credentials.
     *
     * @param email The email address to use.
     * @param password The password to use.
     */
    public void loginWithValidCredentials(String email, String password) {
        typeInputFieldWithLocator("email", email);
        clickButtonWithValue("Next");
        typeInputFieldWithLocator("password", password);
        clickButtonWithValue("Sign in");
    }

    /**
     * Clicks the "Skip setup" link.
     */
    public void clickSkipLink() {
        clickLinkByText("Skip setup");
    }


    /**
     * Clicks on the Dynamics 365 application within an iframe.
     */
    public void clickDynamics365InFrame() {
        By iframeLocator = By.id("AppLandingPage");
        WebElement iframeElement = findElement(iframeLocator);
        driver.switchTo().frame(iframeElement);
        clickObject(AppItem);
        driver.switchTo().defaultContent();
    }

    public void loginWithInvalidCredentials(String email, String password) {
        typeInputFieldWithLocator("email", email);
        clickButtonWithValue("Next");
        clickButtonWithValue("Back");
        typeInputFieldWithLocator("password", password);
        clickButtonWithValue("Sign in");
    }

    public void clickBackButton() {
        clickButtonWithValue("Back");
    }
}
