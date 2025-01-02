package com.frameium.pageobject.Salesforce;

import com.frameium.genericfunctions.GenericFunctions;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

/**
 * This class encapsulates the functionality for managing list views in Salesforce.
 */
public class ListView extends GenericFunctions {

    // Locators for various elements on the lead page
    private By opportunityBtn = By.xpath("/html/body/div[4]/div[1]/section/div[1]/div[1]/one-appnav/div/one-app-nav-bar/nav/div/one-app-nav-bar-item-root[2]");
    private By listViewControlsBtn = By.xpath("//button[@title='List View Controls']");
    private By newBtn = By.xpath("(//div[@class='slds-dropdown slds-dropdown_right'])[1]/ul/li[2]");
    private By listNameField = By.xpath("//input[@name='title']");
    private By listAPINameField = By.xpath("//input[@name='developerName']");
    private By saveBtn = By.xpath("//button[@class='slds-button slds-button--neutral test-confirmButton uiButton--default uiButton--brand uiButton']");
    private By addFilter = By.xpath("//a[@class=' addFilter']");
    private By field = By.xpath("//button[contains(@aria-label,'Field')]");
    private By fieldOption = By.xpath("//div[@class='slds-listbox slds-listbox_vertical slds-dropdown slds-dropdown_fluid slds-dropdown_left slds-dropdown_length-with-icon-7']/lightning-base-combobox-item[@data-value='StageName']");
    private By value = By.xpath("//a[contains(text(),'0 options selected')]");
    private By valueOption = By.xpath("//li[@data-name='Value Proposition']/a[@title='Value Proposition']");
    private By doneBtn = By.xpath("//button[@class='slds-button slds-button--neutral doneButton uiButton']");
    private By filterSaveBtn = By.xpath("//button[text()='Save']");
    private By closeFilterBtn = By.xpath("//button[@class='slds-button slds-button_icon slds-panel__close slds-button_icon-bare']");
    private By rowOpportunity = By.xpath("//table[@class='slds-table forceRecordLayout slds-table--header-fixed slds-table--edit slds-table--bordered resizable-cols slds-table--resizable-cols uiVirtualDataTable']/tbody/tr");
    private WebDriver driver;

    /**
     * Constructor for ListView.
     *
     * @param driver The WebDriver instance to interact with the browser.
     */
    public ListView(WebDriver driver) {

        this.driver = driver;
    }

    /**
     * Navigates to the Opportunities section.
     */
    public void goToOpportunities() {
        clickElement(opportunityBtn);
    }

    /**
     * Creates a new List View with the specified name.
     *
     * @param name The name of the List View to create.
     * @throws InterruptedException If the thread is interrupted while sleeping.
     */
    public void createListView(String name) throws InterruptedException {
        // goToOpportunities();
        clickElement(listViewControlsBtn);
        clickElement(newBtn);
        enterKeys(listNameField, name);
        clickElement(listAPINameField);
        clickElement(saveBtn);
    }

    /**
     * Creates a filter for the List View.
     *
     * @throws InterruptedException If the thread is interrupted while sleeping.
     */
    public void createFilter() throws InterruptedException {
        clickElement(addFilter);
        selectField();
        selectValue();
        clickDone();
    }

    /**
     * Selects a field for the filter.
     */
    public void selectField() {

        // Click on the field to open the dropdown
        clickElement(field);

        // Scroll to the dropdown option element
        WebElement dropdownElement = driver.findElement(fieldOption);
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", dropdownElement);

        // Click on the dropdown option element
        dropdownElement.click();

    }

    /**
     * Selects a value for the filter.
     *
     * @throws InterruptedException If the thread is interrupted while sleeping.
     */
    public void selectValue() throws InterruptedException {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(4000));
        clickElement(driver.findElement(value));
        // Scroll to the dropdown option element
        wait.until(ExpectedConditions.presenceOfElementLocated(valueOption));
        clickElement(driver.findElement(valueOption));
        Thread.sleep(4000);
    }

    /**
     * Clicks the Done button after selecting filter options.
     *
     * @throws InterruptedException If the thread is interrupted while sleeping.
     */
    public void clickDone() throws InterruptedException {
        Thread.sleep(2000);
        clickElementUsingJavaScript(driver.findElement(doneBtn));
        Thread.sleep(1000);
    }

    /**
     * Clicks the Save button to save the filter.
     */
    public void clickSave() {
        clickElement(filterSaveBtn);
    }

    /**
     * Verifies if the specified opportunity is present in the list view.
     *
     * @param opportunityName The name of the opportunity to verify.
     * @return true if the opportunity is present, false otherwise.
     * @throws InterruptedException If the thread is interrupted while sleeping.
     */
    public boolean opportunityVerify(String opportunityName) throws InterruptedException {

            Thread.sleep(2000);
            List<WebElement> rows = driver.findElements(rowOpportunity);

            // Iterate over each row
            for (int i = 0; i < rows.size(); i++) {
                // Extract the text from the row
                String rowText = rows.get(i).getText();
                System.out.println("Table Items: >>> " + rowText); // Print the row text

                if (rowText.contains(opportunityName)) {
                    WebElement oppName = (driver.findElement(By.xpath("(//table[@class='slds-table forceRecordLayout slds-table--header-fixed slds-table--edit slds-table--bordered resizable-cols slds-table--resizable-cols uiVirtualDataTable']/tbody/tr/th//a[text()])[" + (i + 1) + "]")));
                    if (oppName.isDisplayed()) {
                        System.out.println("Opportunity Name '" + opportunityName + "' is present: ");
                        return true;
                    } else {
                        System.out.println("Opportunity Name '" + opportunityName + "' is not present: ");
                    }
                    break;
                }
            }
            return false;
    }
}


