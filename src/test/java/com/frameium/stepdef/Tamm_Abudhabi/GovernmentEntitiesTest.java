package com.frameium.stepdef.Tamm_Abudhabi;
import com.frameium.genericfunctions.GenericFunctions;
import com.frameium.pageobject.Tamm_Abudhabi.GovernmentEntitiesPage;
import com.frameium.stepdef.Hooks;
import com.frameium.stepdef.TestSetUp;
import com.frameium.utilities.AccessibilityReportUtils;
import com.frameium.utilities.ClientSidePerformanceReportUtils;
import org.openqa.selenium.*;
import io.cucumber.java.en.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.net.MalformedURLException;
import java.time.Duration;

import static org.junit.Assert.assertTrue;

public class GovernmentEntitiesTest extends GenericFunctions{
    Hooks hooks = new Hooks();

    TestSetUp setUp;
    private WebDriverWait wait;
    private GenericFunctions genericFunctions;
    private GovernmentEntitiesPage governmentEntitiesPage;
    private WebDriver driver;

public GovernmentEntitiesTest(TestSetUp setUp) throws MalformedURLException {
    this.setUp = setUp;
    genericFunctions = new GenericFunctions(setUp.baseTest.driver);
    governmentEntitiesPage= new  GovernmentEntitiesPage(setUp.baseTest.driver);
    // Assign the driver from setUp to the local driver variable
    this.driver = setUp.baseTest.driver;
    this.wait = new WebDriverWait(driver, Duration.ofSeconds(60));

}
    @When("user click Government Entities button")
    public void user_click_government_entities_button() {
        governmentEntitiesPage.GovernmentEntities();

    }
    @When("user click Department of Health button")
    public void userClickDepartmentOfHealthButton() {
        governmentEntitiesPage.DepartmentOfHealth();
        //ClientSidePerformanceReportUtils clientSidePerformanceReportUtils = new ClientSidePerformanceReportUtils(driver);
        //clientSidePerformanceReportUtils.generateReport();
    }
    @When("user click  Health button")
    public void userClickHealthButton() {
        governmentEntitiesPage.Health();
        String currentUrl = driver.getCurrentUrl();
        driver.get("https://googlechrome.github.io/lighthouse/viewer/?psiurl=" + currentUrl);
        //ClientSidePerformanceReportUtils clientSidePerformanceReportUtils = new ClientSidePerformanceReportUtils(driver);
       // clientSidePerformanceReportUtils.generateReport();
    }
    @When("user click  initial Approval button")
    public void userClickInitialApprovalButton() throws InterruptedException {
        governmentEntitiesPage.initialApproval();
    }

    @Then("the user should be successfully navigated and see the heading {string}")
    public void theUserShouldBeSuccessfullyNavigatedAndSeeTheHeadingInitialApprovalForHealthLicence(String expectedText) throws Exception {
        boolean isTextDisplayed = governmentEntitiesPage.isInitialApprovalTextDisplayed();
        assertTrue("Expected text '" + expectedText + "' is not displayed.", isTextDisplayed);
        String url = setUp.baseTest.driver.getCurrentUrl();
       // AccessibilityReportUtils reportUtils = new AccessibilityReportUtils(setUp.baseTest.getDriver());
       // reportUtils.generateAccessibilityReport(url,Hooks.scenario);
        //ClientSidePerformanceReportUtils clientSidePerformanceReportUtils = new ClientSidePerformanceReportUtils(driver);
        //clientSidePerformanceReportUtils.generateReport();
    }
    @When("user click AbuDhabi Pension Fund button")
    public void userClickAbuDhabiPensionFundButton() throws InterruptedException {

    governmentEntitiesPage.pensionFund();
        ClientSidePerformanceReportUtils clientSidePerformanceReportUtils = new ClientSidePerformanceReportUtils(driver);
        clientSidePerformanceReportUtils.generateReport();

    }
    @When("user click Type button")
    public void userClickTypeButton() {
        governmentEntitiesPage.type();
    }


    @When("the user selects Visitor from the checkbox")
    public void theUserSelectsVisitorFromTheCheckbox() {
        governmentEntitiesPage.visitor();
    }

    @When("user click Pension Increase Calculatorbutton")
    public void userClickPensionIncreaseCalculatorbutton() {
        governmentEntitiesPage.pensionIncreaseCalculator();
        ClientSidePerformanceReportUtils clientSidePerformanceReportUtils = new ClientSidePerformanceReportUtils(driver);
        clientSidePerformanceReportUtils.generateReport();
    }

    @Then("the user should be successfully navigated and see the page {string}")
    public void theUserShouldBeSuccessfullyNavigatedAndSeeThePagePensionIncreaseCalculator(String expectedText) throws Exception {
        boolean isTextDisplayed = governmentEntitiesPage.ispensionIncreaseCalculator();
        assertTrue("Expected text '" + expectedText + "' is not displayed.", isTextDisplayed);

    }

}