package com.frameium.pageobject.D365_CRM;

import com.frameium.genericfunctions.GenericFunctions;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

public class D365AccountPage extends GenericFunctions {

    public D365AccountPage (WebDriver driver) {
        this.driver = driver;
    }


    /**
     * Clicks a button identified by its aria-label attribute.
     *
     * @param buttonName The aria-label of the button to click.
     */
    public void clickButton(String buttonName) {
        clickObject(By.cssSelector("[aria-label='" + buttonName + "']"));
        waitForButtonActionToComplete();
    }

    /**
     * Waits for the page to fully load by checking the document.readyState property.
     */
    private void waitForButtonActionToComplete() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
        wait.until(driver -> ((JavascriptExecutor) driver).executeScript("return document.readyState").equals("complete"));
    }


    /**
     * Clicks a button identified by its title attribute.
     *
     * @param buttonName The title attribute of the button to click.
     */
    public void clickNewButton(String buttonName) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
        By buttonLocator = By.cssSelector("[title='" + buttonName + "']");
        wait.until(ExpectedConditions.elementToBeClickable(buttonLocator)).click();
        waitForButtonActionToComplete();
    }

    /**
     * Generates a locator for a field based on its name.
     * If the field name is "Parent Account", it constructs a specific aria label.
     * Otherwise, it constructs a generic aria label based on the field name.
     *
     * @param fieldName The name of the field.
     * @return By object representing the locator for the field.
     */
    public By generateFieldLocator(String fieldName) {
        if (fieldName.equals("Parent Account")) {
            return By.cssSelector("[aria-label='Parent Account, Lookup']");
        } else {
            return By.cssSelector("[aria-label='" + fieldName + "']");
        }
    }


    /**
     * Fills in text fields with values from the provided table.
     *
     * @param textFieldTable A table containing field names and values to be entered.
     */
    public void fillTextFields(Table textFieldTable) {
        for (Row row : textFieldTable.getRows()) {
            String fieldName = row.getField();
            String fieldValue = row.getValue();
            waitForFieldToBeFilled(generateFieldLocator(fieldName));
            clickObject(generateFieldLocator(fieldName));
            enterKeys(generateFieldLocator(fieldName), fieldValue);

        }
    }

    /**
     * Fills dropdown fields with values from the provided table.
     *
     * @param table A table containing dropdown field names and values to be selected.
     */
    public void fillDropdowns(Table table) {
        for (Row row : table.getRows()) {
            String fieldName = row.getField();
            String optionValue = row.getValue();
            By dropdownLocator = generateFieldLocator(fieldName);
            waitForFieldToBeFilled(dropdownLocator);
            driver.findElement(dropdownLocator).click();
            selectDropdownOptionByText(dropdownLocator, optionValue);
        }
    }

    /**
     * Selects an option from a dropdown field by its visible text.
     *
     * @param dropdownLocator The locator of the dropdown field.
     * @param optionValue The visible text of the option to be selected.
     */
    private void selectDropdownOptionByText(By dropdownLocator, String optionValue) {
        var dropdown = new Select(driver.findElement(dropdownLocator));
        dropdown.selectByVisibleText(optionValue);
    }

    /**
     * Fills a lookup field with the specified value.
     *
     * @param fieldName The name of the lookup field.
     * @param lookupValue The value to be entered into the lookup field.
     */
    public void fillLookupFields(String fieldName, String lookupValue) {
        clickObject(generateFieldLocator(fieldName));
        enterKeys(generateFieldLocator(fieldName), lookupValue);
        try {
            Thread.sleep(1000);
            clickObject(By.xpath("//li[contains(@aria-label, '" + lookupValue + "')]"));
        } catch (StaleElementReferenceException | InterruptedException e) {
            clickObject(By.xpath("//li[contains(@aria-label, '" + lookupValue + "')]"));
        }
    }

    /**
     * Waits for a field to be filled and ready for interaction.
     *
     * @param fieldLocator The locator of the field to wait for.
     */
    private void waitForFieldToBeFilled(By fieldLocator) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
        wait.until(ExpectedConditions.visibilityOfElementLocated(fieldLocator));
        wait.until(ExpectedConditions.elementToBeClickable(fieldLocator));
    }


    /**
     * Clicks a checkbox identified by its data-icon-name attribute.
     *
     * @param checkboxIdentifier The data-icon-name attribute of the checkbox to click.
     */
    public void clickCheckbox(String checkboxIdentifier) {
        By checkboxLocator = By.xpath("//div[i[@data-icon-name='" + checkboxIdentifier + "']]");
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
        WebElement checkboxElement = wait.until(ExpectedConditions.elementToBeClickable(checkboxLocator));
        checkboxElement.click();
    }

    /**
     * Clicks the delete button identified by its aria-label attribute.
     *
     * @param buttonIdentifier The aria-label of the delete button to click.
     */
    public void ClickDeleteButton(String buttonIdentifier) {
        By deleteButtonLocator = By.xpath("//li[@aria-label='" + buttonIdentifier + "']");
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
        WebElement deleteButtonElement = wait.until(ExpectedConditions.elementToBeClickable(deleteButtonLocator));
        deleteButtonElement.click();
    }

    /**
     * Clicks the delete confirmation button identified by its aria-label attribute.
     *
     * @param buttonIdentifier The aria-label of the delete confirmation button to click.
     */
    public void clickDeleteConfirmationButton(String buttonIdentifier) {
        By deleteConfirmationButtonLocator = By.xpath("//button[@aria-label='" + buttonIdentifier + "']");
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
        WebElement deleteConfirmationButtonElement = wait.until(ExpectedConditions.elementToBeClickable(deleteConfirmationButtonLocator));
        deleteConfirmationButtonElement.click();
    }

    /**
     * Clicks the delete button, confirms the action, and handles any potential exceptions.
     */
    public void clickDeleteButton() {
       // WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(60));

        try {
            clickCheckbox("CheckMark");

            Thread.sleep(2000); // Consider using a more robust wait mechanism

            ClickDeleteButton("Delete");

            clickDeleteConfirmationButton("Delete");
        } catch (StaleElementReferenceException | InterruptedException e) {
            clickDeleteButton(); // Retry if there's a stale element or interruption
        }
    }

    /**
     * Represents a table containing rows of field names and values.
     */
    public static class Table {
        private List<Row> rows;

        public Table(List<Row> rows) {
            this.rows = rows;
        }

        public List<Row> getRows() {
            return rows;
        }
    }

    /**
     * Represents a row in a table with a field name and value.
     */
    public static class Row {
        private String field;
        private String value;

        public Row(String field, String value) {
            this.field = field;
            this.value = value;
        }

        public String getField() {
            return field;
        }

        public String getValue() {
            return value;
        }
    }
}
