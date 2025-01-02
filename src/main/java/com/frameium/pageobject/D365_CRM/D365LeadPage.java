package com.frameium.pageobject.D365_CRM;

import com.frameium.genericfunctions.GenericFunctions;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;


import java.time.Duration;
import java.util.List;
import java.util.Map;



public class D365LeadPage extends GenericFunctions {

    public D365LeadPage(WebDriver driver) {
        this.driver = driver;


    }

    /**
     * Clicks the button identified by the given button name.
     *
     * @param buttonName The name or label of the button to click.
     */
    public void clickButton(String buttonName) {
        clickObject(By.cssSelector("[aria-label='" + buttonName + "']"));
        WaitForButtonActionToComplete();
    }

    /**
     * Waits for the completion of any action associated with a button.
     */
    private void WaitForButtonActionToComplete() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
        wait.until(driver -> ((JavascriptExecutor) driver)
                .executeScript("return document.readyState").equals("complete"));
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
        WaitForButtonActionToComplete();
    }

    /**
     * Fills text fields with values specified in the provided table.
     *
     * @param textFieldTable The table containing field names and corresponding values to fill.
     */
    public void fillTextFields(Table textFieldTable) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
        // Wait for the fields to be visible after clicking the button
        wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.xpath("//input[@aria-label]")));
        for (Row row : textFieldTable.getRows()) {
            String fieldName = row.getField();
            String fieldValue = row.getValue();
            By fieldLocator =generateTextFieldLocator(fieldName);
            // Scroll the element into view
            WebElement element = driver.findElement(fieldLocator);
            ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", element);
            waitForFieldToBeFilled(fieldLocator);
            // Interact with the element
            clickObject(fieldLocator);
            enterKeys(fieldLocator, fieldValue);
            // Wait for the field to be filled (assuming the field value gets updated immediately)
            wait.until(driver -> driver.findElement(fieldLocator).getAttribute("value").equals(fieldValue));

        }
    }
    private By generateTextFieldLocator(String fieldName) {
        return By.xpath("//input[@aria-label='" + fieldName + "']");
    }


    /**
     * Fills dropdown fields with values specified in the provided table.
     *
     * @param table The table containing dropdown field names and corresponding option values to select.
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
     * Selects an option from a dropdown element based on the visible text.
     *
     * @param dropdownLocator The locator of the dropdown element.
     * @param optionValue     The visible text of the option to select.
     */
    private void selectDropdownOptionByText(By dropdownLocator, String optionValue) {
        var dropdown = new Select(driver.findElement(dropdownLocator));
        dropdown.selectByVisibleText(optionValue);
    }

    private By generateDropdownLocator(String fieldName) {

        return By.cssSelector("[aria-label='" + fieldName + "']");
    }


    /**
     * Fills lookup fields with the specified lookup value for the given field name.
     *
     * @param fieldName    The name or label of the lookup field.
     * @param lookupValue  The value to be entered into the lookup field.
     */
    public void fillLookupFields(String fieldName, String lookupValue) {
       try {
           By lookUpLocator = generateLookupFieldLocator(fieldName);
        clickObject(lookUpLocator);
        enterKeys(lookUpLocator, lookupValue);
        try {
            Thread.sleep(1000);
            clickObject(By.xpath("//li[contains(@aria-label, '" + lookupValue + "')]"));
        } catch (StaleElementReferenceException | InterruptedException e) {
            clickObject(By.xpath("//li[contains(@aria-label, '" + lookupValue + "')]"));
        }
       } catch (Exception e) {
        // Handle error messages or exceptions here
        System.out.println("Error occurred: " + e.getMessage());
        e.printStackTrace(); // Print stack trace for debugging

       }
    }
    private By generateLookupFieldLocator(String fieldName) {
        String ariaLabel = fieldName + ", Lookup";
        return By.cssSelector("[aria-label='" + ariaLabel + "']");
    }
    /**
     * Waits for a field to be filled, ensuring visibility and clickability.
     *
     * @param fieldLocator The locator of the field to wait for.
     */
    private void waitForFieldToBeFilled(By fieldLocator) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
        wait.until(ExpectedConditions.visibilityOfElementLocated(fieldLocator));
        wait.until(ExpectedConditions.elementToBeClickable(fieldLocator));
    }

    private By generateDateFieldLocator(String fieldName) {
        return By.cssSelector("input[aria-label='Date of " + fieldName + "']");
    }
    /**
     * Fills date field details based on the provided table containing field names and corresponding date values.
     *
     * @param table The table containing date field names and corresponding date values.
     */
    public void FillDateFieldDetails(List<Row> table) {
        for (Row row : table) {
            String fieldName = row.getField();
            String dateValue = row.getValue();
            String[] dateParts = dateValue.split("/");
            By dateFieldLocator =generateDateFieldLocator(fieldName);
            clickObject(dateFieldLocator);
            SelectYear(fieldName, Integer.parseInt(dateParts[2]));
            java.time.LocalDate date = java.time.LocalDate.parse(dateValue, java.time.format.DateTimeFormatter.ofPattern("dd/MM/yyyy"));
            String monthAbbreviation = date.getMonth().name().substring(0, 3).toUpperCase();
            SelectMonth(monthAbbreviation);
            SelectDay(dateParts[0]);
        }
    }

    /**
     * Selects the desired year from the date picker based on the provided year and field name.
     * This method creates dynamic locators for date picker elements.
     *
     * @param fieldName    The name or label of the date field.
     * @param desiredYear  The desired year to select in the date picker.
     */
    private void SelectYear(String fieldName, int desiredYear) {
        try {
            String yearPickerXPath = "//button[contains(@class,'currentItemButton')]";
            By yearPicker = By.xpath(yearPickerXPath);
            clickObject(yearPicker);
            SelectYearInRange(desiredYear);
        } catch (Exception ex) {
            System.out.println("Error selecting year: " + ex.getMessage());
        }
    }

    private void SelectYearInRange(int desiredYear) {
        try {
            String currentYearRange = driver.findElement(By.xpath("//button[contains(@class,'currentItemButton')]")).getText().trim();
            String[] rangeParts = currentYearRange.split("-");
            if (rangeParts.length == 2) {
                int startYear = Integer.parseInt(rangeParts[0].trim());
                int endYear = Integer.parseInt(rangeParts[1].trim());

                while (desiredYear < startYear || desiredYear > endYear) {
                    int increment = desiredYear > endYear ? 10 : -10;
                    if (increment < 0) {
                        clickObject(By.xpath("//button[contains(@class,'navigationButton') and .//i[contains(@data-icon-name,'Up')]]"));
                    } else {
                        clickObject(By.xpath("//button[contains(@class,'navigationButton') and .//i[contains(@data-icon-name,'Down')]]"));
                    }
                    Thread.sleep(1000); // Wait for the navigation to complete
                    currentYearRange = driver.findElement(By.xpath("//button[contains(@class,'currentItemButton')]")).getText().trim();
                    rangeParts = currentYearRange.split("-");
                    startYear = Integer.parseInt(rangeParts[0].trim());
                    endYear = Integer.parseInt(rangeParts[1].trim());
                }

                // Once we are within the range, find and click the desired year
                List<WebElement> yearButtons = driver.findElements(By.cssSelector("[role='gridcell']"));
                for (WebElement button : yearButtons) {
                    String buttonText = button.getText().trim();
                    try {
                        int buttonYear = Integer.parseInt(buttonText);
                        if (buttonYear == desiredYear) {
                            button.click();
                            System.out.println("Clicked desired year: " + desiredYear);
                            return;
                        }
                    } catch (NumberFormatException e) {
                        System.out.println("Skipping non-year button text: " + buttonText);
                    }
                }
                System.out.println("Desired year " + desiredYear + " not found within the visible range");
            } else {
                System.out.println("Error: Unable to parse current year range");
            }
        } catch (Exception ex) {
            System.out.println("Error selecting year: " + ex.getMessage());
        }
    }

    /**
     * Selects the desired month in the date picker based on the provided month abbreviation.
     * This method creates dynamic locators for date picker elements.
     *
     * @param month The abbreviation of the desired month (e.g., "Jan", "Feb", etc.).
     */
    private void SelectMonth(String month) {
        try {
            List<WebElement> monthButtons = driver.findElements(By.xpath("//div[contains(@class,'buttonRow')]/button[contains(@class,'itemButton')]"));
            for (WebElement button : monthButtons) {
                String buttonText = button.getAttribute("aria-label");
                if (buttonText != null && buttonText.startsWith(month)) {
                    button.click();
                    return;
                }
            }
            System.out.println("Desired month " + month + " not found");
        } catch (Exception ex) {
            System.out.println("Error selecting month: " + ex.getMessage());
        }
    }

    /**
     * Selects the desired day in the date picker based on the provided day.
     * This method creates dynamic locators for date picker elements.
     *
     * @param day The day to be selected in the date picker.
     */
    private void SelectDay(String day) {
        try {
            List<WebElement> dayButtons = driver.findElements(By.xpath("//tr[contains(@class,'weekRow')]/td/button[contains(@class,'dayButton')]"));
            for (WebElement button : dayButtons) {
                String buttonText = button.getAttribute("aria-label");
                if (buttonText != null && buttonText.startsWith(day + ",")) {
                    try {
                        button.click();
                    } catch (ElementClickInterceptedException e) {
                        // Retry clicking the button
                        Thread.sleep(1000);
                        button.click();
                    }
                    return;
                }
            }
            System.out.println("Desired day " + day + " not found");
        } catch (Exception ex) {
            System.out.println("Error selecting day: " + ex.getMessage());
        }
    }


    /**
     * Performs a double click action on the element corresponding to the specified identifier,
     * typically representing the first name. This action is usually followed by clicking
     * the checkbox associated with the first name.
     *
     * @param identifier The identifier associated with the first name element.
     */
    public void doubleClickFirstNameElement(String identifier) {
        By firstNameLocator = generateCheckboxLocatorForFirstName(identifier);
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
        WebElement Element = wait.until(ExpectedConditions.visibilityOfElementLocated(firstNameLocator));
        //clickElementJS(driver.findElement(firstNameLocator));
        clickElement(Element);
        //clickObject(firstNameLocator);
    }

    /**
     * Generates a dynamic locator for the checkbox associated with the first name.
     * This method creates dynamic locators for checkboxes.
     *
     * @param identifier The identifier associated with the first name.
     * @return The By locator for the checkbox associated with the first name.
     */
    private By generateCheckboxLocatorForFirstName(String identifier) {
        return By.xpath("//span[contains(@class, 'spanWrapper') and contains(text(), '" + identifier + "')]");
    }

    /**
     * Generates a dynamic locator for the qualification button based on its title attribute.
     * This method creates dynamic locators for qualification buttons.
     *
     * @param titleAttribute The title attribute of the qualification button.
     * @return The By locator for the qualification button.
     */
    public By generateQualificationButtonLocator(String titleAttribute) {
        return By.xpath("//div[contains(@title, '" + titleAttribute + "')]");
    }

    /**
     * Clicks the qualification button identified by its title attribute.
     *
     * @param titleAttribute The title attribute of the qualification button to click.
     */
    public void clickQualificationButton(String titleAttribute) {
        By qualificationButtonLocator = generateQualificationButtonLocator(titleAttribute);
        clickObject(qualificationButtonLocator );
    }

    /**
     * Enters the provided first name into the filter field identified by its aria-label.
     *
     * @param ariaLabel The aria-label attribute of the filter field.
     * @param firstName The first name to enter into the filter field.
     */
    public void enterNameIntoFilter(String ariaLabel, String firstName) {
        // Construct the dynamic CSS selector for the filter field using the provided aria-label
        By filterFieldLocator = By.cssSelector("[aria-label='" + ariaLabel + "']");

        // Enter the provided first name into the filter field
        enterKeys(filterFieldLocator, firstName);
        driver.findElement(filterFieldLocator).sendKeys(Keys.RETURN);
    }
    public void fillNewTextFields(Table textFieldTable) {
        for (Row row : textFieldTable.getRows()) {
            String fieldName = row.getField();
            String fieldValue = row.getValue();
            By textFieldlocator = generateFieldLocator(fieldName);
            waitForFieldToBeFilled(textFieldlocator);
            clickObject(textFieldlocator);
            enterKeys(textFieldlocator, fieldValue);

        }
    }
    private By generateFieldLocator(String fieldName) {
        return By.xpath("//*[@aria-label='" + fieldName + "']");
    }
    //for excel
    public void fillTextFields(String fieldName, String fieldValue) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
        By fieldLocator = generateTextFieldLocator(fieldName);
        WebElement element = driver.findElement(fieldLocator);
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", element);
        waitForFieldToBeFilled(fieldLocator);
        clickObject(fieldLocator);
        enterKeys(fieldLocator, fieldValue);
        wait.until(driver -> driver.findElement(fieldLocator).getAttribute("value").equals(fieldValue));
    }
    public void fillDropdowns(String fieldName, String optionValue) {
        By dropdownLocator = generateDropdownLocator(fieldName);
        waitForFieldToBeFilled(dropdownLocator);
        driver.findElement(dropdownLocator).click();
        selectDropdownOptionByText(dropdownLocator, optionValue);
    }

    public void FillDateFieldDetails(String fieldName, String dateValue) {
        if (dateValue == null || dateValue.trim().isEmpty()) {
            System.out.println("Error: Date value is null or empty for field: " + fieldName);
            return;
        }

        String[] dateParts;
        if (dateValue.contains("/")) {
            dateParts = dateValue.split("/");
        } else if (dateValue.contains("-")) {
            dateParts = dateValue.split("-");
        } else {
            System.out.println("Error: Date format not recognized for field: " + fieldName);
            return;
        }

        if (dateParts.length != 3) {
            System.out.println("Error: Invalid date format for field: " + fieldName);
            return;
        }

        By dateFieldLocator = generateDateFieldLocator(fieldName);
        clickObject(dateFieldLocator);
        SelectYear(fieldName, Integer.parseInt(dateParts[2]));
        java.time.LocalDate date = java.time.LocalDate.parse(dateParts[0] + "/" + dateParts[1] + "/" + dateParts[2], java.time.format.DateTimeFormatter.ofPattern("dd/MM/yyyy"));
        String monthAbbreviation = date.getMonth().name().substring(0, 3).toUpperCase();
        SelectMonth(monthAbbreviation);
        SelectDay(dateParts[0]);
    }
    public void fillNewTextFields(String fieldName, String fieldValue) {
        By textFieldLocator = generateFieldLocator(fieldName);
        waitForFieldToBeFilled(textFieldLocator);
        clickObject(textFieldLocator);
        enterKeys(textFieldLocator, fieldValue);
    }

    // Nested class representing a table
    public static class Table {
        private List<Row> rows;

        public Table(List<Row> rows) {
            this.rows = rows;
        }

        public List<Row> getRows() {
            return rows;
        }
    }

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
