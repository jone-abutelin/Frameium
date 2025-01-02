package com.frameium.stepdef.SalesforceService;

import com.frameium.genericfunctions.GenericFunctions;
import com.frameium.pageobject.SalesforceService.Case;
import com.frameium.stepdef.Hooks;
import com.frameium.stepdef.TestSetUp;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;

import java.net.MalformedURLException;

/**
 * Step definition class for testing the case functionality in Salesforce Service Console.
 * Utilizes Cucumber's Given/When/Then annotations for step definitions.
 */
public class CaseTest  {

    private Case cases;
    private WebDriver driver;
    Hooks hooks = new Hooks();
    String exptdSubject = "Solar panel installation failed";
    String exptdOwner = "Platinum Support";

    TestSetUp setUp;

    private GenericFunctions genericFunctions;

    /**
     * Constructor to initialize the page objects, WebDriver, and test setup.
     * @param setUp Test setup instance used to initialize WebDriver and other configurations.
     */
    public CaseTest(TestSetUp setUp) throws MalformedURLException {
        this.setUp = setUp;
        genericFunctions = new GenericFunctions(setUp.baseTest.driver);
        cases=new Case(setUp.baseTest.driver);
        this.driver = setUp.baseTest.driver;
    }

    // New Case Creation

    /**
     * Step definition for navigating to the app launcher and accessing the service console page.
     */
    @When("I navigate to the app launcher and go to service console page")
    public void i_navigate_to_the_app_launcher_and_go_to_service_console_page() throws InterruptedException {

        cases.appLauncherSearch();
    }

    /**
     * Step definition for navigating to the case page.
     */
    @Then("I go to the case page")
    public void i_go_to_the_case_page() throws InterruptedException {
        cases.goToCasePage();
    }

    /**
     * Step definition for clicking the "New Case" button to create a new case.
     */
    @And("I click on new case button")
    public void i_click_on_new_case_button() throws InterruptedException {
        cases.clickNew();
        hooks.takeScreenshot(hooks.scenario);
    }

    /**
     * Step definition for creating a new case with specified contact and account names and verifying it.
     * @param contactname The name of the contact for the new case.
     * @param accountname The name of the account for the new case.
     */
    @Then("I will create a new case with {string} and {string} and verify it")
    public void i_will_create_a_new_case_and_verify_it(String contactname, String accountname) throws InterruptedException {
        cases.createCase(contactname,accountname);
        hooks.takeScreenshot(hooks.scenario);
        cases.clickSaveBtn();
        String actlSubject = cases.getActualSubject();
        System.out.println("The expected subject is >>>>> " + exptdSubject);
        System.out.println("The actual subject is >>>>> " + actlSubject);
        try {
            Assert.assertEquals(actlSubject, exptdSubject, "Subject is not as expected");
            genericFunctions.logToExtentReport("Pass", "Subject verification successful. Expected and actual names match.");
        } catch (AssertionError e) {
            genericFunctions.logToExtentReport("Fail", "Subject verification failed. Expected: " + exptdSubject + ", but found: " + actlSubject);
            throw e;
        }

    }

    // Edit Case Owner

    /**
     * Step definition for clicking the "Edit Case Owner" option to change the owner.
     */

    @When("I click on Edit Case Owner")
    public void i_click_on_edit_case_owner() throws InterruptedException {
        cases.clickEditOwner();
    }

    /**
     * Step definition for removing the current owner of the case.
     */
    @And("I remove Owner")
    public void i_remove_owner() throws InterruptedException {
        cases.deleteOwner();
    }

    /**
     * Step definition for clicking the "Users" option to select a new owner.
     */
    @And("I click Users")
    public void i_click_users() throws InterruptedException {
        cases.clickUsers();
        hooks.takeScreenshot(hooks.scenario);

    }

    /**
     * Step definition for selecting "Queues" from a dropdown menu.
     */
    @And("I select Queues from dropdown")
    public void i_select_queues() throws InterruptedException {
        cases.changeToQueues();
        hooks.takeScreenshot(hooks.scenario);
    }
    /**
     * Step definition for searching for a specific owner by name.
     * @param ownerName The name of the owner to search for.
     */
    @When("I search for Owner {string}")
    public void i_search_for_owner(String ownerName) throws InterruptedException {
        cases.clickSearch(ownerName);

    }

    /**
     * Step definition for selecting an owner from a dropdown and saving the changes.
     */
    @And("I click on Owner from dropdown and save it")
    public void i_click_on_owner() throws InterruptedException {
        cases.selectOwnerFromDropdown();
        hooks.takeScreenshot(hooks.scenario);
    }

    /**
     * Step definition for verifying the updated case owner.
     */
    @Then("I verify Case Owner")
    public void i_verify_case_owner() throws InterruptedException {
        String actlOwner = cases.checkOwner();
        System.out.println(">>>>>>>>>>>>>>>>>" +actlOwner);
//        Assert.assertEquals(actlOwner,exptdOwner,"Owner name not match");

        try {
            Assert.assertEquals(actlOwner,exptdOwner,"Owner name not match.Case assignment failed");
            genericFunctions.logToExtentReport("Pass", "Owner name matches and Case assignment passed");
        } catch (AssertionError e) {
            genericFunctions.logToExtentReport("Fail", "Owner name verification failed. Expected: " + exptdOwner + ", but found: " + actlOwner);
            throw e;
        }
    }
}

