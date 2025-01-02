package com.frameium.stepdef.D365_CRM;

import com.frameium.genericfunctions.GenericFunctions;
import com.frameium.pageobject.D365_CRM.D365LeadPage;
import com.frameium.stepdef.Hooks;
import com.frameium.utilities.ExcelReader;
import io.cucumber.java.en.*;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import java.io.IOException;
import java.time.Duration;
import java.util.List;
import java.util.Map;

import static com.frameium.stepdef.Hooks.setUp;

public class D365LeadExcelTest extends GenericFunctions {
    Hooks hooks = new Hooks();
    private D365LeadPage leadPage;
    private ExcelReader excelReader;

    public D365LeadExcelTest() {

        leadPage = new D365LeadPage(setUp.baseTest.driver);
        this.excelReader = new ExcelReader();
        this.driver = setUp.baseTest.driver;
    }
    @When("User fills the form fields from Excel {string} sheet {string} module {string}")
    public void userFillsTheFormFieldsFromExcel(String filePath, String sheetName, String moduleName) throws IOException, InvalidFormatException {
        List<Map<String, String>> excelData = excelReader.getData(filePath, sheetName);

        for (Map<String, String> row : excelData) {
            String fieldName = row.get("Field");
            String value = row.get("Value");
            String fieldType = row.get("Type");
            String module = row.get("Module");
            // Debugging logs
            System.out.println("Field: " + fieldName);
            System.out.println("Value: " + value);
            System.out.println("Type: " + fieldType);
            System.out.println("Module: " + module);

            if (moduleName.equalsIgnoreCase(module)) {
                if (fieldType == null) {
                    System.out.println("Error: FieldType is null for FieldName: " + fieldName);
                    continue;
                }

                if ("QualifyLead".equalsIgnoreCase(moduleName)) {
                    handleQualifyLeadField(fieldName, fieldType, value);
                } else if ("LeadCreation".equalsIgnoreCase(moduleName)) {
                    handleLeadCreationField(fieldName, fieldType, value);
                }
            }
        }
    }

    private void handleLeadCreationField(String fieldName, String fieldType, String value) {
        switch (fieldType.toLowerCase()) {
            case "text":
                leadPage.fillTextFields(fieldName, value);
                break;
            case "dropdown":
                leadPage.fillDropdowns(fieldName, value);
                break;
            case "lookup":
                leadPage.fillLookupFields(fieldName, value);
                break;
            case "date":
                leadPage.FillDateFieldDetails(fieldName, value);
                break;
            default:
                System.out.println("Unsupported field type: " + fieldType);
                return; // Exit method if fieldType is not recognized
        }

    }

    private void handleQualifyLeadField(String fieldName, String fieldType, String value) {
        switch (fieldType.toLowerCase()) {
            case "text":
                leadPage.fillNewTextFields(fieldName, value);
                break;
            // Future expansion for other field types can be added here
            default:
                System.out.println("Unsupported field type: " + fieldType);
                return; // Exit method if fieldType is not recognized
        }

    }
    // Add a wait after each field fill operation
    @Then("the lead should not be created and an error message should be displayed")
    public void theLeadShouldNotBeCreatedAndAnErrorMessageShouldBeDisplayed() throws InterruptedException {
        String expectedTitle = "Lead: Lead: New Lead - Dynamics 365";
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20)); // Adjust the timeout as needed
        wait.until(ExpectedConditions.titleContains(expectedTitle));
        String actualTitle = driver.getTitle();
        try {
            Assert.assertEquals(actualTitle, expectedTitle, "Title mismatch");
            logToExtentReport("Pass", "Expected title: " + expectedTitle + " matches actual title: " + actualTitle + ". Lead created successfully.");
        } catch (AssertionError e) {
            logToExtentReport("Fail", "Expected title: " + expectedTitle + ", but found: " + actualTitle);
            throw e;
        }
        Thread.sleep(2000);
        hooks.takeScreenshot(hooks.scenario);
    }



}

