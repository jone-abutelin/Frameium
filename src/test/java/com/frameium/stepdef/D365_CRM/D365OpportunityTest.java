package com.frameium.stepdef.D365_CRM;
import com.frameium.genericfunctions.GenericFunctions;
import com.frameium.pageobject.D365_CRM.D365LeadPage;
import com.frameium.pageobject.D365_CRM.D365OpportunityPage;
import java.lang.Thread;
import java.lang.InterruptedException;
import com.frameium.stepdef.Hooks;
import com.frameium.stepdef.TestSetUp;
import cucumber.annotation.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import static org.junit.Assert.assertTrue;

import java.net.MalformedURLException;
import java.time.Duration;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class D365OpportunityTest {
    Hooks hooks = new Hooks();

    TestSetUp setUp;
    private GenericFunctions genericFunctions;
    private D365OpportunityPage opportunityPage;
    private WebDriver driver;

    public D365OpportunityTest(TestSetUp setUp) throws MalformedURLException {
        this.setUp = setUp;
        genericFunctions = new GenericFunctions(setUp.baseTest.driver);
        opportunityPage=new D365OpportunityPage(setUp.baseTest.driver);
        // Assign the driver from setUp to the local driver variable
        this.driver = setUp.baseTest.driver;

    }
    @When("User clicks on the {string} button in the opportunity")
    public void userClicksOnTheButtonInTheOpportunity(String buttonName) throws InterruptedException {
        Thread.sleep(2000);
        opportunityPage.clickButton(buttonName);

    }
    @When("User selects the opportunity named {string}")
    public void user_selects_the_opportunity_named(String name) {

        opportunityPage.clickFirstNameElement(name);
    }
    @Then("User should see the {string} details page")
    public void user_should_see_the_details_page(String expectedTitle) {
        // Implementation of waiting for the page title and asserting
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(1000)); // Adjust the timeout as needed
        wait.until(ExpectedConditions.titleContains(expectedTitle));
        // Get the actual page title
        String actualTitle = driver.getTitle();
        // Perform assertion to compare actual and expected titles
        Assert.assertEquals(actualTitle, expectedTitle, "Expected page title: '" + expectedTitle + "', but found: '" + actualTitle + "'");

    }
    @When("User updates textfields in opportunity form")
    public void user_updates_textfields_in_opportunity_form(io.cucumber.datatable.DataTable dataTable) {
        D365OpportunityPage.Table textFieldTable = convertDataTableToTable(dataTable);
        opportunityPage.fillTextFields(textFieldTable);
    }
    @When("User updates dropdownfields in opportunity form")
    public void user_updates_dropdownfields_in_opportunity_form(io.cucumber.datatable.DataTable dataTable) {
        D365OpportunityPage.Table dropdownTable = convertDataTableToTable(dataTable);
        opportunityPage.fillDropdowns(dropdownTable);
    }
    @When("User updates lookupfields in opportunity form")
    public void user_updates_lookupfields_in_opportunity_form(io.cucumber.datatable.DataTable dataTable) {

        // Convert the Cucumber DataTable to a list of maps with String keys and values
        List<Map<String, String>> data = dataTable.asMaps(String.class, String.class);

        // Loop through each row in the data table
        for (Map<String, String> row : data) {
            String fieldName = row.get("Field");
            String lookupValue = row.get("Value");

            // Call the fillLookupFields method with the extracted values
            opportunityPage.fillLookupFields(fieldName, lookupValue);
        }
    }
    @When("User Updates Select Dates in opportunity form")
    public void user_updates_select_dates_in_opportunity_form(io.cucumber.datatable.DataTable dataTable) throws InterruptedException {
        D365OpportunityPage.Table dateTable = convertDataTableToTable(dataTable);
        opportunityPage.FillDateFieldDetails(dateTable.getRows());
    }
    // Utility method to convert Cucumber DataTable to D365LeadPage.Table object
    private D365OpportunityPage.Table convertDataTableToTable(io.cucumber.datatable.DataTable dataTable) {
        List<Map<String, String>> rows = dataTable.asMaps(String.class, String.class);
        List<D365OpportunityPage.Row> d365Rows = rows.stream()
                .map(row -> new D365OpportunityPage.Row(row.get("Field"), row.get("Value")))
                .collect(Collectors.toList());
        return new D365OpportunityPage.Table(d365Rows);
    }
    @When("User Updates Select Est. Close Date in opportunity form")
    public void userUpdatesSelectEstCloseDateInOpportunityForm(io.cucumber.datatable.DataTable dataTable) throws InterruptedException {
        D365OpportunityPage.Table dateTable = convertDataTableToTable(dataTable);
        opportunityPage.FillDateFieldDetails(dateTable.getRows());
    }
    @When("User clicks the Needs Analysis button")
    public void userClicksTheNeedsAnalysisButton() {
        opportunityPage.clickPipelineButton("Needs Analysis (40%)");
    }
    @When("User select on the {string}")
    public void userSelectOnThe(String buttonName)throws InterruptedException {
        opportunityPage.clickNextButton(buttonName);

    }


    @Then("User should see the updated opportunity details saved successfully")
    public void user_should_see_the_updated_opportunity_details_saved_successfully() throws InterruptedException {
        // Define the locator for the element
        Thread.sleep(3000);
        By opportunityLocator = By.xpath("//div[@role='presentation' and contains(text(),'Needs Analysis (40%)')]/parent::*");

        // Wait for the element to be present and visible
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(6000));
        WebElement opportunityElement = wait.until(ExpectedConditions.visibilityOfElementLocated(opportunityLocator));

        // Get the text content of the element
        String elementText = opportunityElement.getText().trim();

        // Assert that the text content is exactly "Needs Analysis (40%)"
        if (elementText.equals("Needs Analysis (40%)")) {
            System.out.println("Opportunity details saved successfully: " + elementText);
        } else {
            // If the text is not exactly "Needs Analysis (40%)", fail the test and print the details
            System.out.println("Element text is not as expected. Found: " + elementText);
            Assert.fail("Element contains unexpected text: " + elementText);

        }


    }

    @When("User clicks the Proposal & Price Quote button")
    public void userClicksTheProposalPriceQuoteButton() {
        opportunityPage.clickPipelineButton("Proposal & Price Quote (60%)");
    }

    @Then("the user should see the updated Proposal & Price Quote details saved successfully")
    public void theUserShouldSeeTheUpdatedProposalPriceQuoteDetailsSavedSuccessfully() throws InterruptedException {
        Thread.sleep(2000);
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(6000));
        WebElement negotiationReviewElement = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@role='presentation' and contains(text(),'Negotiation & Review (75%)')]")));

        // Perform assertion
        Assert.assertTrue(negotiationReviewElement.isDisplayed(), "Negotiation & Review element is not displayed");

        // If you want to further assert text
        String actualText = negotiationReviewElement.getText();
        String expectedText = "Negotiation & Review (75%)";
        Assert.assertTrue(actualText.contains(expectedText), "Negotiation & Review element text is incorrect");



    }



    @When("User clicks the Negotiation & Review button")
    public void userClicksTheNegotiationReviewButton() {
        opportunityPage.clickPipelineButton("Negotiation & Review (75%)");
    }

    @Then("the user should see the updated Negotiation & Review  details saved successfully")
    public void theUserShouldSeeTheUpdatedNegotiationReviewDetailsSavedSuccessfully() throws InterruptedException {
        Thread.sleep(2000);
        // Define the locator for the element
        By negotiationLocator = By.xpath("//div[@role='presentation' and contains(text(),'Negotiation & Review (75%)')]/parent::*");

        // Wait for the element to be present and visible
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(6000));
        WebElement opportunityElement = wait.until(ExpectedConditions.visibilityOfElementLocated(negotiationLocator));

        // Get the text content of the element
        String elementText = opportunityElement.getText().trim();

        // Assert that the text content is exactly "Negotiation & Review (75%)"
        if (elementText.equals("Negotiation & Review (75%)")) {
            System.out.println("Opportunity details saved successfully: " + elementText);
        } else {
            // If the text is not exactly "Negotiation & Review (75%)", fail the test and print the details
            System.out.println("Element text is not as expected. Found: " + elementText);
            Assert.fail("Element contains unexpected text: " + elementText);

        }


    }

    @When("User clicks the Completion button")
    public void userClicksTheCompletionButton() {
        opportunityPage.clickPipelineButton("Complete");
    }

    @Then("the user should see that the opportunity details have been successfully saved and marked as completed.")
    public void theUserShouldSeeThatTheOpportunityDetailsHaveBeenSuccessfullySavedAndMarkedAsCompleted() throws InterruptedException {
        Thread.sleep(2000);
        // Define the locator for the element
        By negotiationLocator = By.xpath("//div[@role='presentation' and contains(text(),'Complete')]/parent::*");

        // Wait for the element to be present and visible
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(6000));
        WebElement opportunityElement = wait.until(ExpectedConditions.visibilityOfElementLocated(negotiationLocator));

        // Get the text content of the element
        String elementText = opportunityElement.getText().trim();

        // Assert that the text content is exactly "Negotiation & Review (75%)"
        if (elementText.equals("Complete")) {
            System.out.println("Opportunity details saved successfully: " + elementText);
        } else {
            // If the text is not exactly "Complete", fail the test and print the details
            System.out.println("Element text is not as expected. Found: " + elementText);
            Assert.fail("Element contains unexpected text: " + elementText);

        }
    }
}
