package com.frameium.pageobject.Salesforce;

import com.frameium.genericfunctions.GenericFunctions;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

/**
 * This class contains methods for interacting with the Account page in Salesforce.
 */
public class Account extends GenericFunctions {
    // Locators for various elements on the lead page

    private By accountsButton = By.xpath("/html/body/div[4]/div[1]/section/div[1]/div[1]/one-appnav/div/one-app-nav-bar/nav/div/one-app-nav-bar-item-root[6]");
    private By newBtn = By.xpath("//li[@data-target-selection-name='sfdc:StandardButton.Account.New']");
    private By accountNameField = By.xpath("//input[@name='Name']");
    private By saveBtn = By.xpath("//button[@name='SaveEdit']");
    private WebDriver driver;

    /**
     * Constructor to initialize the WebDriver.
     *
     * @param driver The WebDriver instance used for interacting with the web page.
     */
    public Account(WebDriver driver) {

        this.driver = driver;
    }

    /**
     * Clicks the Accounts button to navigate to the Accounts page.
     */
    public void accountsBtnClick() {

        clickElement(accountsButton);
    }

    /**
     * Creates a new account in Salesforce by clicking the "New" button,
     * entering the account name, and clicking the "Save" button.
     *
     * @param acntName The name of the account to be created.
     * @throws InterruptedException If the thread is interrupted while sleeping.
     */
    public void newAccountCreation(String acntName) throws InterruptedException {
        Thread.sleep(10000);
        clickElement(newBtn);
        enterKeys(accountNameField, acntName);
        clickElement(saveBtn);
    }

}
