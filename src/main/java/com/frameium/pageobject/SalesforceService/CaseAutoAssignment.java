package com.frameium.pageobject.SalesforceService;

import com.frameium.genericfunctions.GenericFunctions;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;


/**
 * This class handles actions related to auto-assignment of Case in Salesforce Service Console.
 */
public class CaseAutoAssignment extends GenericFunctions {

    // Locator for various UI elements

    private By ownerName = By.xpath("//span[text()='Case Owner']/parent::div[@class='test-id__field-label-container slds-form-element__label']/following-sibling::div/span/a");
    private By assignCheckbox = By.xpath("//span[text()='Assign using active assignment rule']");
    private By sendCheckbox = By.xpath("//span[text()='Send notification email to contact']");
    private By saveBtn = By.xpath("//button[@name='SaveEdit']");


    /**
     * Constructor to initialize the WebDriver and SkillBasedRouting instance.
     * @param driver WebDriver instance used for interacting with the browser.
     */
    public CaseAutoAssignment(WebDriver driver) {

        this.driver = driver;
    }

    /**
     * Ticks the checkboxes for assignment rules and sending notification email.
     */
    public void tickCheckbox(){
        clickElement(assignCheckbox);
        clickElement(sendCheckbox);
    }

    /**
     * Clicks the Save button to save changes.
     */
    public void clickSaveBtn(){
        clickElement(saveBtn);
    }

    /**
     * Retrieves the actual case owner's name from the UI.
     * @return The case owner's name as a string.
     */
    public String getActualOwnerName() {
        waitForByElement(ownerName);
        return driver.findElement(ownerName).getText();
    }
}
