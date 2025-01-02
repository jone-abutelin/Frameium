package com.frameium.stepdef.D365_CRM;

import com.frameium.genericfunctions.GenericFunctions;
import com.frameium.pageobject.D365_CRM.D365CompetitorPage;
import com.frameium.pageobject.D365_CRM.D365LoginPage;
import com.frameium.stepdef.Hooks;
import com.frameium.stepdef.TestSetUp;
import com.frameium.utilities.ClientSidePerformanceReportUtils;
import cucumber.annotation.en.And;
import io.cucumber.java.en.When;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.net.MalformedURLException;
import java.time.Duration;

public class D365CompetitorTest {
    private D365CompetitorPage competitorPage;
    Hooks hooks = new Hooks();
    private WebDriver driver;

    TestSetUp setUp;
    private GenericFunctions genericFunctions;
    public D365CompetitorTest(TestSetUp setUp) throws MalformedURLException {
        this.setUp = setUp;
        genericFunctions = new GenericFunctions(setUp.baseTest.driver);
        competitorPage=new D365CompetitorPage(setUp.baseTest.driver);
        // Assign the driver from setUp to the local driver variable
        this.driver = setUp.baseTest.driver;
    }

    @When("User clicks on the {string} button in the competitor")
    public void userClicksOnTheButtonInTheCompetitor(String buttonName) {

        competitorPage.clickButton(buttonName);
    }

    @When("the User clicks on the {string} button to begin a new competitor creation process")
    public void theUserClicksOnTheButtonToBeginANewCompetitorCreationProcess(String buttonName) throws InterruptedException {
        competitorPage.clickNewButton(buttonName);
        //ClientSidePerformanceReportUtils clientSidePerformanceReportUtils = new ClientSidePerformanceReportUtils(driver);
        //clientSidePerformanceReportUtils.generateReport();
    }

    @When("User fills textfields in the competitor")
    public void userFillsTextfieldsInTheCompetitor(io.cucumber.datatable.DataTable dataTable) throws InterruptedException {
        D365CompetitorPage.Table textFieldTable = convertDataTableToTable(dataTable);
        competitorPage.fillTextFields(textFieldTable);

    }



    @When("User should see the competitor saved successfully")
    public void userShouldSeeTheCompetitorSavedSuccessfully() {
        String expectedTitle = "Competitors All Competitors (summary) - Dynamics 365";

        // Wait for the page title to contain the expected text
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20)); // Adjust the timeout as needed
        wait.until(ExpectedConditions.titleContains(expectedTitle));

        // Get the actual page title
        String actualTitle = driver.getTitle();

        // Perform assertion to compare actual and expected titles
        Assert.assertEquals(actualTitle, expectedTitle, "Expected page title: '" + expectedTitle + "', but found: '" + actualTitle + "'");
    }
    private D365CompetitorPage.Table convertDataTableToTable(io.cucumber.datatable.DataTable dataTable) {
        java.util.List<java.util.Map<String, String>> rows = dataTable.asMaps(String.class, String.class);
        java.util.List<D365CompetitorPage.Row> d365Rows = new java.util.ArrayList<>();
        for (java.util.Map<String, String> row : rows) {
            String field = row.get("Field");
            String value = row.get("Value");
            d365Rows.add(new D365CompetitorPage.Row(field, value));
        }
        return new D365CompetitorPage.Table(d365Rows);
    }

}
