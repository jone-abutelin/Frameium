package com.frameium.stepdef.D365_CRM;

import com.frameium.genericfunctions.GenericFunctions;
import com.frameium.pageobject.D365_CRM.D365AccountPage;
import com.frameium.pageobject.D365_CRM.D365LeadPage;
import com.frameium.pageobject.D365_CRM.D365LoginPage;
import com.frameium.stepdef.Hooks;
import com.frameium.stepdef.TestSetUp;
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

public class D365AccountTest {

    Hooks hooks = new Hooks();

    TestSetUp setUp;
    private GenericFunctions genericFunctions;
    private D365AccountPage accountPage;
    private WebDriver driver;


    public D365AccountTest(TestSetUp setUp) throws MalformedURLException {
        this.setUp = setUp;
        genericFunctions = new GenericFunctions(setUp.baseTest.driver);
        accountPage=new D365AccountPage(setUp.baseTest.driver);
        // Assign the driver from setUp to the local driver variable
        this.driver = setUp.baseTest.driver;

    }
    @When("User clicks on the {string} button in the account")
    public void userClicksOnTheButtonInTheAccount(String buttonName) {
        accountPage.clickButton(buttonName);
    }
    @When("the User clicks on the {string} button to begin a new  account creation process")
    public void theUserClicksOnTheButtonToBeginANewAccountCreationProcess(String buttonName) {
        accountPage.clickNewButton(buttonName);
    }



    @When("User fills textfields in the account")
    public void userFillsTextfieldsInTheAccount(io.cucumber.datatable.DataTable dataTable) {
        D365AccountPage.Table textFieldTable = convertDataTableToTable(dataTable);
        accountPage.fillTextFields(textFieldTable);
    }
    @When("User fills dropdownfields in the account")
    public void userFillsDropdownfieldsInTheAccount(io.cucumber.datatable.DataTable dataTable) {
        D365AccountPage.Table dropdownTable = convertDataTableToTable(dataTable);
        accountPage.fillDropdowns(dropdownTable);
    }
    @When("User fills lookupfield in the account")
    public void userFillsLookupfieldInTheAccount(io.cucumber.datatable.DataTable dataTable) {
        // Convert the Cucumber DataTable to a list of maps with String keys and values
        List<Map<String, String>> data = dataTable.asMaps(String.class, String.class);

        // Loop through each row in the data table
        for (Map<String, String> row : data) {
            String fieldName = row.get("Field");
            String lookupValue = row.get("Value");

            // Call the fillLookupFields method with the extracted values
            accountPage.fillLookupFields(fieldName, lookupValue);
        }
    }


    @Then("User should see the new account saved successfully")
    public void user_should_see_the_new_account_saved_successfully() {
        // Define the expected title
        String expectedTitle = "Accounts My Active Accounts - Dynamics 365";

        // Wait for the page title to contain the expected text
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20)); // Adjust the timeout as needed
        wait.until(ExpectedConditions.titleContains(expectedTitle));

        // Get the actual page title
        String actualTitle = driver.getTitle();

        // Perform assertion to compare actual and expected titles
        Assert.assertEquals(actualTitle, expectedTitle, "Expected page title: '" + expectedTitle + "', but found: '" + actualTitle + "'");
    }


    // Utility method to convert Cucumber DataTable to D365AccountPage.Table object
    private D365AccountPage.Table convertDataTableToTable(io.cucumber.datatable.DataTable dataTable) {
        List<Map<String, String>> rows = dataTable.asMaps(String.class, String.class);
        List<D365AccountPage.Row> d365Rows = new java.util.ArrayList<>();
        for (Map<String, String> row : rows) {
            String field = row.get("Field");
            String value = row.get("Value");
            d365Rows.add(new D365AccountPage.Row(field, value));
        }
        return new D365AccountPage.Table(d365Rows);
    }

    @When("User clicks the Delete button")
    public void user_clicks_the_delete_button() {
        accountPage.clickDeleteButton();
    }

    @Then("User should be in the {string} page")
    public void userShouldBeInThePage(String expectedTitle) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20)); // Adjust the timeout as needed
        wait.until(ExpectedConditions.titleContains(expectedTitle));

        // Get the actual page title
        String actualTitle = driver.getTitle();

        // Perform assertion to compare actual and expected titles
        Assert.assertEquals(actualTitle, expectedTitle, "Expected page title: '" + expectedTitle + "', but found: '" + actualTitle + "'");

    }



}
