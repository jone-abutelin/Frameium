package com.frameium.stepdef.D365_CRM;

import com.frameium.genericfunctions.GenericFunctions;
import com.frameium.pageobject.D365_CRM.D365ContactPage;
import com.frameium.pageobject.D365_CRM.D365LoginPage;
import com.frameium.stepdef.Hooks;
import com.frameium.stepdef.TestSetUp;
import cucumber.annotation.en.And;
import io.cucumber.java.en.When;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.net.MalformedURLException;
import java.time.Duration;
import java.util.List;
import java.util.Map;

public class D365ContactTest {

    Hooks hooks = new Hooks();

    TestSetUp setUp;
    private GenericFunctions genericFunctions;
    private D365ContactPage contactPage;
    private WebDriver driver;


    public D365ContactTest(TestSetUp setUp) throws MalformedURLException {
        this.setUp = setUp;
        genericFunctions = new GenericFunctions(setUp.baseTest.driver);
        contactPage =new D365ContactPage(setUp.baseTest.driver);
        // Assign the driver from setUp to the local driver variable
        this.driver = setUp.baseTest.driver;

    }

    @When("User clicks on the {string} button in the contact")
    public void userClicksOnTheButtonInTheContact(String buttonName) {
        contactPage.clickButton(buttonName);
    }

    @When("the User clicks on the {string} button to begin a new contact creation process")
    public void theUserClicksOnTheButtonToBeginANewContactCreationProcess(String buttonName) {
        contactPage.clickNewButton(buttonName);
    }
    @When("User fills textfields in the contact")
    public void userFillsTextfieldsInTheContact(io.cucumber.datatable.DataTable dataTable) {
        D365ContactPage.Table textFieldTable = convertDataTableToTable(dataTable);
        contactPage.fillTextFields(textFieldTable);
    }

    @When("User fills dropdownfields in the contact")
    public void userFillsDropdownfieldsInTheContact(io.cucumber.datatable.DataTable dataTable) {
        D365ContactPage.Table dropdownTable = convertDataTableToTable(dataTable);
        contactPage.fillDropdowns(dropdownTable);
    }

    @When("Contact User fills lookupfields in the contact")
    public void contactUserFillsLookupfieldsInTheContact(io.cucumber.datatable.DataTable dataTable) {
        // Convert the Cucumber DataTable to a list of maps with String keys and values
        List<Map<String, String>> data = dataTable.asMaps(String.class, String.class);

        // Loop through each row in the data table
        for (Map<String, String> row : data) {
            String fieldName = row.get("Field");
            String lookupValue = row.get("Value");


            // Call the fillLookupFields method with the extracted values
            contactPage.fillLookupFields(fieldName, lookupValue);
        }
    }


    @When("New contact should be successfully created")
    public void newContactShouldBeSuccessfullyCreated() {
        String expectedTitle = "Contacts My Active Contacts - Dynamics 365";

        // Wait for the page title to contain the expected text
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20)); // Adjust the timeout as needed
        wait.until(ExpectedConditions.titleContains(expectedTitle));
        // Get the actual page title
        String actualTitle = driver.getTitle();
        // Perform assertion to compare actual and expected titles
        Assert.assertEquals(actualTitle, expectedTitle, "Expected page title: '" + expectedTitle + "', but found: '" + actualTitle + "'");
    }

    // Utility method to convert Cucumber DataTable to D365AccountPage.Table object
    private D365ContactPage.Table convertDataTableToTable(io.cucumber.datatable.DataTable dataTable) {
        List<Map<String, String>> rows = dataTable.asMaps(String.class, String.class);
        List<D365ContactPage.Row> d365Rows = new java.util.ArrayList<>();
        for (Map<String, String> row : rows) {
            String field = row.get("Field");
            String value = row.get("Value");
            d365Rows.add(new D365ContactPage.Row(field, value));
        }
        return new D365ContactPage.Table(d365Rows);
    }


}