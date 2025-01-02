package com.frameium.stepdef.SalesforceService;

import com.frameium.genericfunctions.GenericFunctions;
import com.frameium.pageobject.SalesforceService.OmniSupervisor;
import com.frameium.stepdef.Hooks;
import com.frameium.stepdef.TestSetUp;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;

import java.net.MalformedURLException;

/**
 * Step definition class for testing Omni-Channel Supervisor functionality in Salesforce Service Console.
 * Utilizes Cucumber's Given/When/Then annotations to define the steps for Omni-Channel management and case handling.
 */
public class OmniSupervisorTest {

    private OmniSupervisor omniSup;
    private WebDriver driver;
    private String exptdCase = "Drilling Equipment is too complex to operate due to its tight bolt";
    private String exptdQueue = "AutoRouting";
    TestSetUp setUp;
    Hooks hooks = new Hooks();
    private GenericFunctions genericFunctions;

    /**
     * Constructor to initialize the page objects, WebDriver, and setup.
     * @param setUp Test setup instance used to initialize WebDriver and other configurations.
     */
    public OmniSupervisorTest(TestSetUp setUp) throws MalformedURLException {
        this.setUp = setUp;
        genericFunctions = new GenericFunctions(setUp.baseTest.driver);
        omniSup = new OmniSupervisor(setUp.baseTest.driver);
        // Assign the driver from setUp to the local driver variable
        this.driver = setUp.baseTest.driver;
    }

    /**
     * Step definition for setting the Omni-Channel to available state.
     */
    @When("I set Omni-Channel to available state")
    public void i_set_omni_channel_to_available_state() throws InterruptedException {
        omniSup.setOmnichannelAvailable();
    }

    /**
     * Step definition for navigating to the Omni Supervisor page.
     */
    @And("I navigate to Omni Supervisor page")
    public void i_navigate_to_Omni_Supervisor_page() throws InterruptedException {
        omniSup.switchToOmniSupervisor();
    }

    /**
     * Step definition for navigating to the Assigned Work section.
     * Takes a screenshot after navigation.
     */
    @And("I navigate to Assigned Work")
    public void i_navigate_to_Assigned_work() throws InterruptedException {
        omniSup.navigateToAssignedWork();
        hooks.takeScreenshot(hooks.scenario);
    }

    /**
     * Step definition for verifying the AutoRouting queue.
     * Compares the actual queue name with the expected queue name and logs the result.
     */
    @Then("I verify the AutoRouting Queue")
    public void i_verify_the_auto_routing_queue() {
        String actualQueue = omniSup.verifyAutoRoutingQueue();
        System.out.println("Actual AutoRouting Queue: " + actualQueue);

        // Compare the actual queue name with the expected queue name
        try {
            Assert.assertEquals(actualQueue, exptdQueue, "AutoRouting queue is not as expected");
            genericFunctions.logToExtentReport("Pass", "AutoRouting queue verification successful. Expected and actual queues match.");
        } catch (AssertionError e) {
            genericFunctions.logToExtentReport("Fail", "AutoRouting queue verification failed. Expected: " + exptdQueue + ", but found: " + actualQueue);
            throw e;
        }

    }

    /**
     * Step definition for clicking on the Queue section.
     * Takes a screenshot after clicking.
     */
    @When("I click on Queue")
   public void i_click_on_Queue() {
        omniSup.navigateToQueue();
        hooks.takeScreenshot(hooks.scenario);
 }

    /**
     * Step definition for verifying case details after selecting the Queue.
     * Compares the actual case details with the expected case details and logs the result.
     */
    @Then("I verify Case Details")
    public void i_verify_case_details() throws InterruptedException {
        String actlCase = omniSup.checkCaseDetails();
        System.out.println(">>>>>>>>>>>>>>>>>" +actlCase);

        // Compare the actual case details with the expected case details
        try {
            // Assert that the actual case details match the expected case details
            Assert.assertEquals(actlCase, exptdCase, "Case details do not match");
            genericFunctions.logToExtentReport("Pass", "Case details verification successful. Expected and actual details match.");
        } catch (AssertionError e) {
            genericFunctions.logToExtentReport("Fail", "Case details verification failed. Expected: " + exptdCase + ", but found: " + actlCase);
            throw e;
        }
        omniSup.checkCaseDetails();
        hooks.takeScreenshot(hooks.scenario);
    }
}


