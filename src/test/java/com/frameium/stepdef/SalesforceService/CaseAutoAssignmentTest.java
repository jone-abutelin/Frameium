package com.frameium.stepdef.SalesforceService;

import com.frameium.genericfunctions.GenericFunctions;

import com.frameium.pageobject.SalesforceService.Case;
import com.frameium.pageobject.SalesforceService.CaseAutoAssignment;
import com.frameium.stepdef.Hooks;
import com.frameium.stepdef.TestSetUp;
import io.cucumber.java.en.*;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;

import java.net.MalformedURLException;

/**
 * Step definition class for testing automatic case assignment functionality in Salesforce Service Console.
 * Uses Cucumber's Given/When/Then annotations for step definitions.
 */
public class CaseAutoAssignmentTest {
    private CaseAutoAssignment auto;
    private WebDriver driver;

    TestSetUp setUp;

    private GenericFunctions genericFunctions;
    //private String exptype = "Mechanical";
    private String expownerName = "Thomas Arun";
    private Case cases;
    Hooks hooks = new Hooks();


    /**
     * Constructor to initialize the page objects and setup.
     * @param setUp Test setup instance to initialize WebDriver and other configurations.
     */
    public CaseAutoAssignmentTest(TestSetUp setUp) throws MalformedURLException {
        this.setUp = setUp;
        genericFunctions = new GenericFunctions(setUp.baseTest.driver);
        auto=new CaseAutoAssignment(setUp.baseTest.driver);
        cases = new Case(setUp.baseTest.driver);
        // Assign the driver from setUp to the local driver variable
        this.driver = setUp.baseTest.driver;
    }

    /**
     * Step definition for creating a new case with auto-assignment and verifying it.
     * @param contactname Name of the contact for the new case.
     * @param accountname Name of the account for the new case.
     */

    @Then("I will create a new case with {string} and {string} with auto-assignment and verify it")
    public void i_will_create_a_new_case_and_verify(String contactname, String accountname) throws InterruptedException {
        cases.createCase(contactname,accountname);
        auto.tickCheckbox();
        hooks.takeScreenshot(hooks.scenario);
        auto.clickSaveBtn();
    }

    /**
     * Step definition for verifying the auto-assigned case owner.
     */
    @Then("I will verify the auto assigned case owner")
    public void i_will_verify_the_auto_assigned_case_owner() throws InterruptedException {
        String actlOwnerName = auto.getActualOwnerName();
        System.out.println("The expected Owner name is >>>>> " + expownerName);
        System.out.println("The actual contract name is >>>>> " + actlOwnerName);
        try {
            Assert.assertEquals(actlOwnerName, expownerName, "Owner name is not as expected");
            genericFunctions.logToExtentReport("Pass", "Expected and actual names match.Owner name verification successful and case auto-assignment passed. ");
        } catch (AssertionError e) {
            genericFunctions.logToExtentReport("Fail", "Owner name verification failed. Expected: " + expownerName + ", but found: " + actlOwnerName);
            throw e;
        }
    }
}

