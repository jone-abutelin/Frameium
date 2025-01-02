package com.frameium.stepdef.D365_CRM;

import com.frameium.genericfunctions.GenericFunctions;
import com.frameium.pageobject.D365_CRM.D365AccountPage;
import com.frameium.pageobject.D365_CRM.D365LeadPage;
import com.frameium.pageobject.D365_CRM.D365LoginPage;
import com.frameium.stepdef.Hooks;
import com.frameium.stepdef.TestSetUp;
import com.frameium.utilities.ClientSidePerformanceReportUtils;
import cucumber.annotation.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.net.MalformedURLException;
import java.time.Duration;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
public class D365LeadTest extends GenericFunctions{

    Hooks hooks = new Hooks();

    TestSetUp setUp;
    private GenericFunctions genericFunctions;
    private D365LeadPage leadPage;
    private WebDriver driver;

    public D365LeadTest(TestSetUp setUp) throws MalformedURLException {
        this.setUp = setUp;
        genericFunctions = new GenericFunctions(setUp.baseTest.driver);
        leadPage=new D365LeadPage(setUp.baseTest.driver);
        // Assign the driver from setUp to the local driver variable
        this.driver = setUp.baseTest.driver;

    }


    @When("User clicks on the {string} button")
    public void user_clicks_on_the_button(String buttonName) throws InterruptedException {

        leadPage.clickButton(buttonName);
        //ClientSidePerformanceReportUtils clientSidePerformanceReportUtils = new ClientSidePerformanceReportUtils(driver);
        //clientSidePerformanceReportUtils.generateReport();
    }
    @When("the User clicks on the {string} button to begin a new lead creation process")
    public void theUserClicksOnTheButtonToBeginANewLeadCreationProcess(String buttonName) throws InterruptedException {
        leadPage.clickNewButton(buttonName);
        Thread.sleep(4000);
        hooks.takeScreenshot(hooks.scenario);
    }
    @When("User fills text in form fields")
    public void user_fills_text_in_form_fields(io.cucumber.datatable.DataTable dataTable) {
        D365LeadPage.Table textFieldTable = convertDataTableToTable(dataTable);
        leadPage.fillTextFields(textFieldTable);
    }

    @When("User fills dropdown in the form fields")
    public void userFillsDropdownInTheFormFields(io.cucumber.datatable.DataTable dataTable) {
        D365LeadPage.Table dropdownTable = convertDataTableToTable(dataTable);
        leadPage.fillDropdowns(dropdownTable);
    }
    @When("User fills lookupfields in form")
    public void user_fills_lookupfields_in_form(io.cucumber.datatable.DataTable dataTable) {
        // Convert the Cucumber DataTable to a list of maps with String keys and values
        List<Map<String, String>> data = dataTable.asMaps(String.class, String.class);

        // Loop through each row in the data table
        for (Map<String, String> row : data) {
            String fieldName = row.get("Field");
            String lookupValue = row.get("Value");

            // Call the fillLookupFields method with the extracted values
            leadPage.fillLookupFields(fieldName, lookupValue);
        }
    }
    @When("User fills Select Date in form")
    public void user_fills_select_date_in_form(io.cucumber.datatable.DataTable dataTable) throws InterruptedException {
        D365LeadPage.Table dateTable = convertDataTableToTable(dataTable);
        leadPage.FillDateFieldDetails(dateTable.getRows());
        Thread.sleep(1000);
        hooks.takeScreenshot(hooks.scenario);
    }


    @Then("the lead is created successfully")
    public void the_lead_is_created_successfully() throws InterruptedException {
        String expectedTitle = "Leads My Open Leads - Dynamics 365";
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20)); // Adjust the timeout as needed
        wait.until(ExpectedConditions.titleContains(expectedTitle));
        String actualTitle = driver.getTitle();
        try{
            Assert.assertEquals(actualTitle, expectedTitle, "Title mismatch");
            logToExtentReport("Pass", "Expected title: " + expectedTitle + " matches actual title: " + actualTitle + ". Lead created successfully.");
        } catch (AssertionError e) {
            logToExtentReport("Fail", "Expected title: " + expectedTitle + ", but found: " + actualTitle);
            throw e;
        }
        Thread.sleep(2000);
        hooks.takeScreenshot(hooks.scenario);
    }


    @When("User clicks the {string} and enter the {string}")
    public void user_clicks_the_and_enter_the(String buttonName, String firstName) throws InterruptedException {
        // Click the specified button
        leadPage.clickButton(buttonName);
        // Enter the provided first name into the filter field
        leadPage.enterNameIntoFilter( buttonName, firstName);
        Thread.sleep(2000);
        hooks.takeScreenshot(hooks.scenario);
    }
    @When("User double click the FirstName {string}")
    public void userDoubleClickTheFirstName(String name) {
        try {
            leadPage.doubleClickFirstNameElement(name);
            logToExtentReport("Log", "User double clicked the FirstName: " + name);
        } catch (Exception e) {
            logToExtentReport("Fail", "An unexpected error occurred while double clicking the FirstName: " + name + ". Error: " + e.getMessage());
            throw e;
        }
    }

    @When("User fills text in the qualification form fields")
    public void userFillsTextInTheQualificationFormFields(io.cucumber.datatable.DataTable dataTable) {
        D365LeadPage.Table textFieldTable = convertDataTableToTable(dataTable);
        leadPage.fillNewTextFields(textFieldTable);
    }
    @Then("the user should be redirected to the {string}")
    public void the_user_should_be_redirected_to_the(String expectedTitle) throws InterruptedException {
        // Implementation of waiting for the page title and asserting
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20)); // Adjust the timeout as needed
        wait.until(ExpectedConditions.titleContains(expectedTitle));
        // Get the actual page title
        String actualTitle = driver.getTitle();
        try{
            Assert.assertEquals(actualTitle, expectedTitle, "Title mismatch");
            logToExtentReport("Pass", "Expected title: " + expectedTitle + " matches actual title: " + actualTitle + ". Redirected to the specified lead page.");
        } catch (AssertionError e) {
            logToExtentReport("Fail", "Expected title: " + expectedTitle + ", but found: " + actualTitle);
            throw e;
        }
        Thread.sleep(2000);
        hooks.takeScreenshot(hooks.scenario);

    }

    @When("User clicks the Qualification  button")
    public void user_clicks_the_qualification_button() {
        leadPage.clickQualificationButton("Qualification (20%)");
    }




    @Then("the user should be redirected to the Opportunity {string} Page")
    public void theUserShouldBeRedirectedToTheOpportunityPage(String expectedTitle) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30)); // Adjust the timeout as needed
        wait.until(ExpectedConditions.titleContains(expectedTitle));
        // Get the actual page title
        String actualTitle = driver.getTitle();
        try{
            Assert.assertEquals(actualTitle, expectedTitle, "Title mismatch");
            logToExtentReport("Pass", "Expected title: " + expectedTitle + " matches actual title: " + actualTitle + ". Lead Qualified.");
        } catch (AssertionError e) {
            logToExtentReport("Fail", "Expected title: " + expectedTitle + ", but found: " + actualTitle);
            throw e;
        }  hooks.takeScreenshot(hooks.scenario);

        // Perform assertion to compare actual and expected titles
        //Assert.assertEquals(actualTitle, expectedTitle, "Expected page title: '" + expectedTitle + "', but found: '" + actualTitle + "'");

    }

    // Utility method to convert Cucumber DataTable to D365LeadPage.Table object
    private D365LeadPage.Table convertDataTableToTable(io.cucumber.datatable.DataTable dataTable) {
        List<Map<String, String>> rows = dataTable.asMaps(String.class, String.class);
        List<D365LeadPage.Row> d365Rows = rows.stream()
                .map(row -> new D365LeadPage.Row(row.get("Field"), row.get("Value")))
                .collect(Collectors.toList());
        return new D365LeadPage.Table(d365Rows);
    }


}
