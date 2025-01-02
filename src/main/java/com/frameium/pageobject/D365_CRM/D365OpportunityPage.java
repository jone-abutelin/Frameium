package com.frameium.pageobject.D365_CRM;

import org.openqa.selenium.WebDriver;
import com.frameium.genericfunctions.GenericFunctions;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.Duration;
import java.util.List;

public class D365OpportunityPage extends GenericFunctions {


    public D365OpportunityPage(WebDriver driver) {
        this.driver = driver;
    }
////div/span[contains(@title,'Identify Stakeholders ')]

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
    public void clickNextButton(String buttonName) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
        By buttonLocator = By.cssSelector("[title='" + buttonName + "']");
        wait.until(ExpectedConditions.elementToBeClickable(buttonLocator)).click();
        WaitForButtonActionToComplete();
    }

    /**
     * Generates a dynamic locator for a field based on the provided field name.
     * The method uses a predefined map of field names to their corresponding locators.
     * If the field name is not found in the map, it constructs a default locator
     * using the field name itself.
     *
     * @param fieldName The name of the field for which to generate the locator.
     * @return A By object representing the locator for the specified field.
     */

    private By generateDropdownLocator(String fieldName) {
        return By.cssSelector("[aria-label='" + fieldName + "']");
    }
    private By generateTextFieldLocator(String fieldName) {
        return By.xpath("//*[@aria-label='" + fieldName + "']");
    }

    private By generateLookupFieldLocator(String fieldName) {
        String ariaLabel = fieldName + ", Lookup";
        return By.cssSelector("[aria-label='" + ariaLabel + "']");
    }
    private By generateDateFieldLocator(String fieldName) {
        System.out.println(">>>>>>>>>>>>>>>>>>>%%%%%%%% "+ "input[aria-label='Date of " + fieldName + "']" );
        return By.cssSelector("input[aria-label='Date of " + fieldName + "']");
    }
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

    /**
     * Fills lookup fields with the specified lookup value for the given field name.
     *
     * @param fieldName    The name or label of the lookup field.
     * @param lookupValue  The value to be entered into the lookup field.
     */
    public void fillLookupFields(String fieldName, String lookupValue) {
        try {
            // Locate the lookup field
            By lookUpLocator = generateLookupFieldLocator(fieldName);

            // Scroll the lookup field into the center of the viewport
            WebElement lookupField = driver.findElement(lookUpLocator);
            ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView({block: 'center'});", lookupField);

            // Click the lookup field and enter keys
            clickObject(lookUpLocator);
            enterKeys(lookUpLocator, lookupValue);

            // Wait for the list to load and be visible
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
            By listItemLocator = By.xpath("//li[contains(@aria-label, '" + lookupValue + "')]");
            wait.until(ExpectedConditions.visibilityOfElementLocated(listItemLocator));

            // Find the list item
            WebElement listItem = driver.findElement(listItemLocator);

            // Scroll the list item into view
            ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", listItem);

            // Click the list item
            clickObject(listItemLocator);
        } catch (StaleElementReferenceException e) {
            // Handle stale element by re-finding and clicking
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
            By listItemLocator = By.xpath("//li[contains(@aria-label, '" + lookupValue + "')]");
            wait.until(ExpectedConditions.visibilityOfElementLocated(listItemLocator));

            // Find the list item again
            WebElement listItem = driver.findElement(listItemLocator);

            // Scroll the list item into view
            ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", listItem);

            // Click the list item
            clickObject(listItemLocator);
        } catch (Exception e) {
            // Handle error messages or exceptions here
            System.out.println("Error occurred: " + e.getMessage());
            e.printStackTrace(); // Print stack trace for debugging
        }
    }

    /**
     * Waits for a field to be filled, ensuring visibility and clickability.
     *
     * @param fieldLocator The locator of the field to wait for.
     */
    private void waitForFieldToBeFilled(By fieldLocator) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(60));
        wait.until(ExpectedConditions.visibilityOfElementLocated(fieldLocator));
        wait.until(ExpectedConditions.elementToBeClickable(fieldLocator));
    }

public void FillDateFieldDetails(List<Row> table) throws InterruptedException {
    WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
    for (Row row : table) {
        Thread.sleep(2000);
        String fieldName = row.getField();
        String dateValue = row.getValue();
        String[] dateParts = dateValue.split("/");

        System.out.println(">>>>>>>>>>>>>>> %%%%% fieldName "+ fieldName );
        System.out.println(">>>>>>>>>>>>>>> %%%%% dateValue "+ dateValue );

        By dateFieldLocator = generateDateFieldLocator(fieldName);
        waitForFieldToBeFilled(dateFieldLocator);
        clickElement(driver.findElement(dateFieldLocator));


        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[contains(@class,'monthAndYear')]")));
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[contains(@class,'monthAndYear')]")));
        // Click the month and year element to show the year
        clickObject(By.xpath("//*[contains(@class,'monthAndYear')]"));

        // Extract year from the element and select the year
        String yearText = driver.findElement(By.xpath("//button[contains(@class,'currentItemButton')]")).getText().trim();
        int currentYear = Integer.parseInt(yearText);
        int desiredYear = Integer.parseInt(dateParts[2]);
        SelectYearInRange(currentYear, desiredYear);

        // Continue with month and day selection
        LocalDate date = LocalDate.parse(dateValue, DateTimeFormatter.ofPattern("dd/MM/yyyy"));
        String monthAbbreviation = date.getMonth().name().substring(0, 1).toUpperCase() + date.getMonth().name().substring(1, 3).toLowerCase();
        SelectMonth(monthAbbreviation);
        // Remove leading zero from day
        String day = Integer.toString(Integer.parseInt(dateParts[0]));
        SelectDay(day);

    }
}

    /**
     * Selects the desired year from the date picker based on the provided year and field name.
     * This method creates dynamic locators for date picker elements.
     *
     * @param currentYear  The current year displayed in the date picker.
     * @param desiredYear  The desired year to select in the date picker.
     */
    private void SelectYearInRange(int currentYear, int desiredYear) {
        try {
            String yearPickerXPath = "//button[contains(@class,'currentItemButton')]";
            By yearPicker = By.xpath(yearPickerXPath);
            clickObject(yearPicker);

            while (true) {
                String currentYearRange = driver.findElement(By.xpath(yearPickerXPath)).getText().trim();
                String[] rangeParts = currentYearRange.split("-");
                int startYear, endYear;

                if (rangeParts.length == 2) {
                    startYear = Integer.parseInt(rangeParts[0].trim());
                    endYear = Integer.parseInt(rangeParts[1].trim());
                } else {
                    startYear = endYear = Integer.parseInt(currentYearRange.trim());
                }

                if (desiredYear >= startYear && desiredYear <= endYear) {
                    // Desired year is within the current range, select it
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
                    return;
                }

                // Move to the next range
                if (desiredYear < startYear) {
                    clickObject(By.xpath("//button[contains(@class,'navigationButton') and .//i[contains(@data-icon-name,'Up')]]"));
                } else {
                    clickObject(By.xpath("//button[contains(@class,'navigationButton') and .//i[contains(@data-icon-name,'Down')]]"));
                }

                // Small delay to allow UI to update
                Thread.sleep(1000);
            }
        } catch (Exception ex) {
            System.out.println("Error selecting year: " + ex.getMessage());
            ex.printStackTrace();
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
    public void clickFirstNameElement(String identifier) {
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
    public By generateNeedsAnalysisButtonLocator(String titleAttribute) {
        return By.xpath("//div[contains(@title, '" + titleAttribute + "')]");
    }

    /**
     * Clicks the NeedsAnalysis button identified by its title attribute.
     *
     * @param titleAttribute The title attribute of the qualification button to click.
     */
    public void clickPipelineButton(String titleAttribute) {
        By NeedsAnalysisButtonLocator = generateNeedsAnalysisButtonLocator(titleAttribute);
        //WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
        // WebElement qualificationButton = wait.until(ExpectedConditions.elementToBeClickable(qualificationButtonLocator));
        //qualificationButton.click();
        clickObject(NeedsAnalysisButtonLocator );
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
