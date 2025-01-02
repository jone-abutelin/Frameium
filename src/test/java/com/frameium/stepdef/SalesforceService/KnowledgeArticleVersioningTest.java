package com.frameium.stepdef.SalesforceService;

import com.frameium.genericfunctions.GenericFunctions;
import com.frameium.pageobject.SalesforceService.KnowledgeArticleVersioning;
import com.frameium.stepdef.Hooks;
import com.frameium.stepdef.TestSetUp;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;

import java.net.MalformedURLException;

/**
 * Step definition class for testing the versioning functionality of Knowledge Articles in Salesforce Service Console.
 * Utilizes Cucumber's Given/When/Then annotations for defining test steps.
 */
public class KnowledgeArticleVersioningTest {

    private KnowledgeArticleVersioning knowledgeVer;
    private WebDriver driver;
    private String exptdVer = "2";
    TestSetUp setUp;
    private GenericFunctions genericFunctions;
    Hooks hooks = new Hooks();

    /**
     * Constructor to initialize the page objects, WebDriver, and setup.
     * @param setUp Test setup instance used to initialize WebDriver and other configurations.
     */
    public KnowledgeArticleVersioningTest(TestSetUp setUp) throws MalformedURLException {
        this.setUp = setUp;
        genericFunctions = new GenericFunctions(setUp.baseTest.driver);
        knowledgeVer = new KnowledgeArticleVersioning(setUp.baseTest.driver);
        // Assign the driver from setUp to the local driver variable
        this.driver = setUp.baseTest.driver;
    }

    /**
     * Step definition for clicking the Edit button and navigating to the edit page of the Knowledge Article.
     */
    @When("I click Edit button and navigate to the edit page")
    public void i_click_edit_button_and_navigate_to_the_edit_page() throws InterruptedException {
        knowledgeVer.clickEditBtn();
    }

    /**
     * Step definition for making changes to the Knowledge Article and saving the changes.
     */
    @And("I make some changes and save it")
    public void i_make_some_changes_and_save_it() throws InterruptedException {
        knowledgeVer.makeChanges();
        hooks.takeScreenshot(hooks.scenario);
        knowledgeVer.clickSaveBtn();

    }

    /**
     * Step definition for publishing the Knowledge Article and verifying the incremented version number.
     */
    @Then("I publish and verify the incremented version number")
    public void i_verify_the_incremented_version_number() throws InterruptedException {
        String actlVer = knowledgeVer.verifyVersion();
        System.out.println(">>>>>>>>>>>>>>>>>" + actlVer);

        // Compare the actual version number with the expected version number
        try {
            Assert.assertEquals(actlVer, exptdVer, "Version does not match");
            genericFunctions.logToExtentReport("Pass", "Version verification successful. Expected and actual versions match.");
        } catch (AssertionError e) {
            genericFunctions.logToExtentReport("Fail", "Version verification failed. Expected: " + exptdVer + ", but found: " + actlVer);
            throw e;
        }

    }
}
