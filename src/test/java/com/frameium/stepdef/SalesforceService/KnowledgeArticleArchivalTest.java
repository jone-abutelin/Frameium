package com.frameium.stepdef.SalesforceService;

import com.frameium.genericfunctions.GenericFunctions;
import com.frameium.pageobject.SalesforceService.KnowledgeArticle;
import com.frameium.pageobject.SalesforceService.KnowledgeArticleArchival;
import com.frameium.stepdef.Hooks;
import com.frameium.stepdef.TestSetUp;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;

import java.net.MalformedURLException;

/**
 * Step definition class for testing the archival functionality of knowledge articles in Salesforce Service Console.
 * Utilizes Cucumber's Given/When/Then annotations for step definitions.
 */
public class KnowledgeArticleArchivalTest {

    private final KnowledgeArticleArchival knowledgeArch;
    private KnowledgeArticle knowledge;
    private WebDriver driver;
    private String exptdStatus = "Archived";


    TestSetUp setUp;

    private GenericFunctions genericFunctions;
    Hooks hooks = new Hooks();

    /**
     * Constructor to initialize the page objects, WebDriver, and test setup.
     * @param setUp Test setup instance used to initialize WebDriver and other configurations.
     */
    public KnowledgeArticleArchivalTest(TestSetUp setUp) throws MalformedURLException {
        this.setUp = setUp;
        genericFunctions = new GenericFunctions(setUp.baseTest.driver);
        knowledgeArch = new KnowledgeArticleArchival(setUp.baseTest.driver);
        // Assign the driver from setUp to the local driver variable
        this.driver = setUp.baseTest.driver;
    }

    /**
     * Step definition for navigating to the published knowledge article screen.
     */
    @Then("I go to the published article")
    public void i_go_to_the_published_article() {
        knowledgeArch.navigateBackToKnowledge();

    }

    /**
     * Step definition for clicking the "Archive" button to archive the knowledge article
     */
    @And("I click on Archive button")
    public void i_click_on_archive_button() throws InterruptedException {
        knowledgeArch.clickArchive();
        hooks.takeScreenshot(hooks.scenario);
    }


    /**
     * Step definition for verifying the publication status of the knowledge article.
     */
    @Then("I verify publication status")
    public void i_verify_publication_status() throws InterruptedException {

        // Retrieve the actual publication status from the page
        String actlStatus = knowledgeArch.checkPublicationStatus();
        System.out.println(">>>>>>>>>>>>>>>>>" +actlStatus);

        // Compare the actual status with the expected status
        try {
            Assert.assertEquals(actlStatus,exptdStatus,"Publication status doesn't match and Archival failed");
            genericFunctions.logToExtentReport("Pass", "Publication status matches and Archival passed");
        } catch (AssertionError e) {
            genericFunctions.logToExtentReport("Fail", "Publication status verification failed. Expected: " + exptdStatus + ", but found: " + actlStatus);
            throw e;
        }
    }
    }



