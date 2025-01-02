package com.frameium.stepdef.SalesforceService;

import com.frameium.genericfunctions.GenericFunctions;
import com.frameium.pageobject.SalesforceService.AcceptCase;
import com.frameium.pageobject.SalesforceService.Case;
import com.frameium.pageobject.SalesforceService.SkillBasedRouting;
import com.frameium.stepdef.Hooks;
import com.frameium.stepdef.TestSetUp;
import com.frameium.utilities.SharedData;
import io.cucumber.java.en.*;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;

import java.net.MalformedURLException;


/**
 * Step definition class for accepting and validating cases in Salesforce Service Console.
 * Uses Cucumber's Given/When/Then annotations for step definitions.
 */
public class AcceptCaseTest {

    private Case cases;
    private AcceptCase acptCase;
    private SkillBasedRouting skill;
    private WebDriver driver;
    TestSetUp setUp;
    private GenericFunctions genericFunctions;
    Hooks hooks = new Hooks();


    /**
     * Constructor to initialize the page objects and setup.
     * @param setUp Test setup instance to initialize WebDriver and other configurations.
     */
    public AcceptCaseTest(TestSetUp setUp) throws MalformedURLException {
        this.setUp = setUp;
        genericFunctions = new GenericFunctions(setUp.baseTest.driver);
        acptCase = new AcceptCase(setUp.baseTest.driver);
        skill = new SkillBasedRouting(setUp.baseTest.driver);
        // Assign the driver from setUp to the local driver variable
        this.driver = setUp.baseTest.driver;
    }

    /**
     * Step definition for navigating to the created case page.
     */
    @Given("I am on created case page")

    public void I_am_on_created_case_page() {
        //acptCase.navigateToDetails();
    }

    /**
     * Step definition for navigating to the omnichannel widget.
     */
    @Then("I navigate to omnichannel widget")
    public void i_navigate_to_omnichannel_widget() throws InterruptedException {
        acptCase.selectOmniChannel();
        hooks.takeScreenshot(hooks.scenario);

    }


    /**
     * Step definition to verify that the omnichannel widget is updated with the routed case.
     * Compares the actual case ID from the widget with the expected case ID.
     */
    @Then("I Verify that omnichannel widget is updated with the routed case")
    public void i_verify_that_omnichannel_widget_is_updated_with_the_routed_case() {
        String actcaseid = skill.getCaseNumber();
        String expcaseid = acptCase.getExpectedCaseId();
        SharedData.elementText = actcaseid;
        System.out.println("The actual case ID is >>>>> " + actcaseid);
        System.out.println("The expected case ID is >>>>> " + expcaseid);
        try {
            Assert.assertEquals(actcaseid, expcaseid, "Case ID is not as expected");
            genericFunctions.logToExtentReport("Pass", "Case ID verification successful. Expected and actual names match.");
        } catch (AssertionError e) {
            genericFunctions.logToExtentReport("Fail", "Case ID verification failed. Expected: " + expcaseid + ", but found: " + actcaseid);
            throw e;
        }
    }

    /**
     * Step definition for clicking the button to accept the case.
     */
    @Then("I Click on accept the case")
    public void i_click_on_accept_the_case() throws InterruptedException {
        //acptcase.verifyCaseId();
        acptCase.acptTheCase();
    }
}