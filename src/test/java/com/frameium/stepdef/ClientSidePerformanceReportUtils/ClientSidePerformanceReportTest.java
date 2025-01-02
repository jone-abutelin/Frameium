package com.frameium.stepdef.ClientSidePerformanceReportUtils;

import com.frameium.stepdef.Hooks;
import com.frameium.utilities.ClientSidePerformanceReportUtils;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import com.frameium.genericfunctions.GenericFunctions;
import com.frameium.pageobject.Tamm_Abudhabi.GovernmentEntitiesPage;
import com.frameium.stepdef.TestSetUp;
import org.openqa.selenium.WebDriver;

import java.net.MalformedURLException;

import static org.junit.Assert.assertTrue;

import static org.junit.Assert.assertTrue;

public class ClientSidePerformanceReportTest  extends GenericFunctions {
    Hooks hooks = new Hooks();

    TestSetUp setUp;
    private GenericFunctions genericFunctions;
    private GovernmentEntitiesPage governmentEntitiesPage;
    private WebDriver driver;
    public ClientSidePerformanceReportTest(TestSetUp setUp) throws MalformedURLException {
        this.setUp = setUp;
        genericFunctions = new GenericFunctions(setUp.baseTest.driver);
        governmentEntitiesPage= new  GovernmentEntitiesPage(setUp.baseTest.driver);
        // Assign the driver from setUp to the local driver variable
        this.driver = setUp.baseTest.driver;

    }

    @When("User Click the Government Entities")
    public void user_click_the_government_entities() {

        governmentEntitiesPage.GovernmentEntities();

        // Note: ClientSidePerformanceReportUtils might not work for this webpage as FCP (First Contentful Paint) may not be available.
    }

    @And("User Click AbuDhabi Pension FundButton")
    public void user_click_abu_dhabi_pension_fund_button() {
        governmentEntitiesPage.pensionFund();
        // This utility will be used to generate a performance report for the client-side of the application,
        // capturing performance data based on the current state and URL of the WebDriver.
        ClientSidePerformanceReportUtils clientSidePerformanceReportUtils = new ClientSidePerformanceReportUtils(driver);
        clientSidePerformanceReportUtils.generateReport();
    }
    @And("User Click Type button")
    public void user_click_type_button() {
        governmentEntitiesPage.type();

    }
    @And("User Selects Visitor from the checkbox")
    public void user_selects_visitor_from_the_checkbox() {
        governmentEntitiesPage.visitor();

    }
    @And("User Click Pension Increase Calculatorbutton")
    public void user_click_pension_increase_calculatorbutton() {
        governmentEntitiesPage.pensionIncreaseCalculator();
    }
    @Then("User Should be successfully navigated to the page {string}")
    public void user_should_be_successfully_navigated_to_the_page(String expectedText) throws Exception {
        boolean isTextDisplayed = governmentEntitiesPage.ispensionIncreaseCalculator();
        assertTrue("Expected text '" + expectedText + "' is not displayed.", isTextDisplayed);
        String url = setUp.baseTest.driver.getCurrentUrl();
        // This utility will be used to generate a performance report for the client-side of the application,
        // capturing performance data based on the current state and URL of the WebDriver.
        ClientSidePerformanceReportUtils clientSidePerformanceReportUtils = new ClientSidePerformanceReportUtils(driver);
        clientSidePerformanceReportUtils.generateReport();
    }

}
