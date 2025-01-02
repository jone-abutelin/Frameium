package com.frameium.pageobject.D365_CRM;

import com.frameium.genericfunctions.GenericFunctions;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;
import java.util.Map;
import org.openqa.selenium.support.ui.WebDriverWait;

public class D365ContactPage extends GenericFunctions {

    public D365ContactPage (WebDriver driver) {
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
     * Fills in text fields with values from the provided table.
     *
     * @param textFieldTable A table containing field names and values to be entered.
     */
    public void fillTextFields(Table textFieldTable) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
        wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.xpath("//input[@aria-label]")));
        for (Row row : textFieldTable.getRows()) {
            String fieldName = row.getField();
            String fieldValue = row.getValue();
            By fieldLocator =generateTextFieldLocator(fieldName);
            waitForFieldToBeFilled(fieldLocator);
            clickObject(fieldLocator);
            enterKeys(fieldLocator, fieldValue);

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
            By dropdownLocator = generateDropdownLocator(fieldName);
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
    private By generateDropdownLocator(String fieldName) {
        return By.cssSelector("[aria-label='" + fieldName + "']");
    }
    private By generateTextFieldLocator(String fieldName) {
        return By.xpath("//input[@aria-label='" + fieldName + "']");
    }

    private By generateLookupFieldLocator(String fieldName) {
        String ariaLabel = fieldName + ", Lookup";
        return By.cssSelector("[aria-label='" + ariaLabel + "']");
    }



    /**
     * Fills a lookup field with the specified value.
     *
     * @param fieldName The name of the lookup field.
     * @param lookupValue The value to be entered into the lookup field.
     */
    public void fillLookupFields(String fieldName, String lookupValue) {
        By lookupField =generateLookupFieldLocator(fieldName);
        clickObject(lookupField);
        enterKeys(lookupField, lookupValue);
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
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(60));
        wait.until(ExpectedConditions.visibilityOfElementLocated(fieldLocator));
        wait.until(ExpectedConditions.elementToBeClickable(fieldLocator));
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
