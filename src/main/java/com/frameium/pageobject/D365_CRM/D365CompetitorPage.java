package com.frameium.pageobject.D365_CRM;

import com.frameium.genericfunctions.GenericFunctions;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

public class D365CompetitorPage extends GenericFunctions {

    public D365CompetitorPage (WebDriver driver) {
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
    public void clickNewButton(String buttonName) throws InterruptedException {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
        By buttonLocator = By.cssSelector("[title='" + buttonName + "']");
        wait.until(ExpectedConditions.elementToBeClickable(buttonLocator)).click();
        waitForButtonActionToComplete();
        Thread.sleep(1000);
    }

    /**
     * Generates a locator for a field based on its name.
     *
     * @param fieldName The name of the field.
     * @return By object representing the locator for the field.
     */
    public By generateFieldLocator(String fieldName) {
        return By.cssSelector("[aria-label='" + fieldName + "']");
    }

    /**
     * Fills in text fields with values from the provided table.
     *
     * @param textFieldTable A table containing field names and values to be entered.
     */
    public void fillTextFields(Table textFieldTable) throws InterruptedException {
         Thread.sleep(3000);
        for (Row row : textFieldTable.getRows()) {
            String fieldName = row.getField();
            String fieldValue = row.getValue();
            By fieldLocator = generateFieldLocator(fieldName);
            // Scroll into view
            WebElement element = driver.findElement(fieldLocator);
            ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", element);

            clickObject(fieldLocator);
            enterKeys(fieldLocator, fieldValue);
            waitForFieldToBeFilled(fieldLocator);



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
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(60));

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
