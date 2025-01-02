package com.frameium.stepdef.SalesforceService;

import com.frameium.genericfunctions.GenericFunctions;
import com.frameium.pageobject.SalesforceService.CaseResolution;
import com.frameium.stepdef.Hooks;
import com.frameium.stepdef.TestSetUp;
import com.frameium.utilities.SharedData;
import io.cucumber.java.en.*;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;

import java.net.MalformedURLException;

/**
 * Step definition class for testing the case resolution functionality in Salesforce Service Console.
 * Uses Cucumber's Given/When/Then annotations for step definitions.
 */
public class CaseResolutionTest {

    private WebDriver driver;
    private GenericFunctions genericFunctions;
    private CaseResolution cr;
    private String expName = "Password Reset";
    TestSetUp setUp;
    Hooks hooks = new Hooks();

    /**
     * Constructor to initialize the page objects, WebDriver, and setup.
     * @param setUp Test setup instance to initialize WebDriver and other configurations.
     */
    public CaseResolutionTest(TestSetUp setUp)throws MalformedURLException {
        this.setUp = setUp;
        genericFunctions = new GenericFunctions(setUp.baseTest.driver);
        cr = new CaseResolution(setUp.baseTest.driver);
        this.driver = setUp.baseTest.driver;
    }


    /**
     * Step definition for opening a case that needs to be resolved.
     */
    @When("I open the case to  be resolved")
    public void i_open_the_case_to_be_resolved() throws InterruptedException {
        String elementText = SharedData.elementText;
        System.out.println("Text from element: " + elementText);
        cr.navigateToOpenCase(elementText);
    }

    /**
     * Step definition for searching knowledge articles using a keyword and selecting a matching article.
     * @param keyword The keyword to search for in knowledge articles.
     * @param article The name of the article to select from search results.
     */
    @Then("I search for knowledge using keyword {string} and select matching article {string}")
    public void i_search_for_knowledge_using_keyword(String keyword, String article) throws InterruptedException {
        cr.enterKeyword(keyword, article);
        hooks.takeScreenshot(hooks.scenario);
    }

    /**
     * Step definition for verifying the opened knowledge article.
     */
    @Then("I navigate to the article")
    public void i_navigate_to_the_article() throws InterruptedException {
        String actName = cr.verifyKnowledgeOpened();
        System.out.println("The actual Knowledge article opened is >>>>> " + actName);
        try {
            Assert.assertEquals(actName, expName, "Knowledge Article opened is not expected");
            genericFunctions.logToExtentReport("Pass", "Opened knowledge article verification is successful. Expected and actual names match.");
        } catch (AssertionError e) {
            genericFunctions.logToExtentReport("Fail", "Opened knowledge article verification is failed. Expected: " + expName + ", but found: " + actName);
            throw e;
        }

    }
    /**
     * Step definition for updating the case status.
     */
    @Then("I update the case status")
    public void i_update_the_case_status() throws InterruptedException {
        cr.updateStatus();
        hooks.takeScreenshot(hooks.scenario);
    }

    /**
     * Step definition for clicking the save button to save changes.
     */
    @Then("I click on save button")
    public void i_click_on_save_button() throws InterruptedException {
        cr.clickSave();
    }
}
