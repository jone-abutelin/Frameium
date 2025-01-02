package com.frameium.pageobject.SalesforceService;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

import com.frameium.genericfunctions.GenericFunctions;

/**
 * This class provides methods to interact with the Omni-Channel Supervisor functionality in Salesforce.
 * It includes actions for navigating the app, setting availability, and managing queues and assigned work.
 */
public class OmniSupervisor extends GenericFunctions {

    // Locators for various elements in the Omni-Channel Supervisor interface

    private By clickOmnichannel = By.xpath("//span[text()='Omni-Channel (Offline)']");
    private By changeOmniChannelStatusBtn = By.xpath("//div[contains(@class,'slds-is-open') and @role='dialog']/div[2]/div/div[2]/div[1]/div/button");
    private By setAvailable = By.xpath("//li[@title='Online Status']");
    private By minimizeBtn = By.xpath("//div[contains(@class,'slds-is-open') and @role='dialog']/div/div[2]/span/button");
    private By showNavigationMenu = By.xpath("//button[@title='Show Navigation Menu']");
    private By clickOmniSup = By.xpath("//span[text()='Omni Supervisor']");
    private By gotoAssignedWork = By.xpath("//a[@data-label='Assigned Work']");
    private By verifyQueue = By.xpath("//tr[@class='rowOdd omniWorksByQueueRow']/th/a");
    private By gotoQueue = By.xpath("//a[text()='AutoRouting']");
    private By verifyCaseDetails = By.xpath("//div[contains(text(),'Medium | New |')]/preceding-sibling::span");

    /**
     * Constructor to initialize the WebDriver.
     *
     * @param driver The WebDriver instance used for interacting with the web page.
     */
    public OmniSupervisor(WebDriver driver) {

        this.driver = driver;
    }

    /**
     * Sets the Omni-Channel status to 'Available'.
     */
    public void setOmnichannelAvailable() throws InterruptedException {
        Thread.sleep(2000);
        clickElement(clickOmnichannel);
        clickElementUsingJavaScript(driver.findElement(changeOmniChannelStatusBtn));
        clickElement(setAvailable);
        Thread.sleep(3000);
        clickElement(minimizeBtn);
    }

    /**
     * Switches to the Omni Supervisor tab from the navigation menu.
     */
    public void switchToOmniSupervisor() throws InterruptedException {
        Thread.sleep(2000);
        clickElement(showNavigationMenu);
        clickElement(clickOmniSup);
    }

    /**
     * Navigates to the 'Assigned Work' section in the Omni Supervisor.
     */
    public void navigateToAssignedWork() throws InterruptedException {
        Thread.sleep(3000);
        clickElement(gotoAssignedWork);
    }

    /**
     * Verifies the queue name for AutoRouting.
     *
     * @return The text of the queue name.
     */
    public String verifyAutoRoutingQueue() {
        waitForByElement(verifyQueue);
        return driver.findElement(verifyQueue).getText();
    }

    /**
     * Navigates to the 'AutoRouting' queue.
     */
      public void navigateToQueue() {
          clickElement(gotoQueue);

      }

    /**
     * Checks the details of a case in the Omni Supervisor.
     *
     * @return The details of the case.
     */
    public String checkCaseDetails() throws InterruptedException {

        waitForByElement(verifyCaseDetails);
        return driver.findElement(verifyCaseDetails).getText();
    }


  }


