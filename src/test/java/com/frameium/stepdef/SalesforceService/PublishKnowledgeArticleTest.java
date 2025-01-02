package com.frameium.stepdef.SalesforceService;

import com.frameium.genericfunctions.GenericFunctions;
import com.frameium.pageobject.SalesforceService.PublishKnowledgeArticle;
import com.frameium.stepdef.Hooks;
import com.frameium.stepdef.TestSetUp;
import io.cucumber.java.en.*;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;

import java.net.MalformedURLException;


/**
 * Step definition class for testing the publishing functionality of Knowledge Articles in Salesforce.
 * Utilizes Cucumber's Given/When/Then annotations to define the steps for publishing a Knowledge Article and verifying the results.
 */
public class PublishKnowledgeArticleTest {

    private PublishKnowledgeArticle pka;
    private WebDriver driver;
    private String expPublishMsg = "Success! \"Password Reset\" has been published.";
    private String expStatus = "Published";
    TestSetUp setUp;
    private GenericFunctions genericFunctions;
    Hooks hooks = new Hooks();

    /**
     * Constructor to initialize the page objects, WebDriver, and setup.
     * @param setUp Test setup instance used to initialize WebDriver and other configurations.
    */
    public PublishKnowledgeArticleTest(TestSetUp setUp) throws MalformedURLException {
        this.setUp = setUp;
        genericFunctions = new GenericFunctions(setUp.baseTest.driver);
        pka = new PublishKnowledgeArticle(setUp.baseTest.driver);
        this.driver = setUp.baseTest.driver;
    }

    /**
     * Step definition for clicking the publish button to initiate the publishing process.
     * Takes a screenshot after clicking the button.
     */

    @When("I click on publish")
    public void i_click_on_publish() throws InterruptedException {
        pka.clickPublish();
        hooks.takeScreenshot(hooks.scenario);
    }


    /**
     * Step definition for clicking the actual publish button to complete the publishing process.
     * Takes a screenshot after clicking the button.
     */
    @And("I Click on publish button")
    public void i_click_on_publish_button() {
        pka.clickPublishBtn();
        hooks.takeScreenshot(hooks.scenario);
    }

    /**
     * Step definition for verifying the publish message after attempting to publish the Knowledge Article.
     * Compares the actual publish message with the expected message and logs the result.
     */
    @Then("I verify the Publish message")
    public void i_verify_the_publish_message() throws InterruptedException {
        String actPublishMsg = pka.verifyPublishMsg();
        System.out.println("The actual Message is >>>>>>" + actPublishMsg);

        // Compare the actual publish message with the expected message
        try {
            Assert.assertEquals(actPublishMsg, expPublishMsg, "Publish message is not as expected");
            genericFunctions.logToExtentReport("Pass", "Publish message verification successful. Expected and actual messages match.");
        } catch (AssertionError e) {
            genericFunctions.logToExtentReport("Fail", "Publish message verification failed. Expected: " + expPublishMsg + ", but found: " + actPublishMsg);
            throw e;
        }
    }

    /**
     * Step definition for verifying the publication status of the Knowledge Article.
     * Compares the actual status with the expected status and logs the result.
     */
    @And("I verify Publication Status")
    public void i_verify_Publication_Status() throws InterruptedException {
        Thread.sleep(2000);
        String actStatus = pka.verifyStatus();
        System.out.println("The actual status is>>>>>>>>"+actStatus);

        // Compare the actual publication status with the expected status
        try {
            Assert.assertEquals(actStatus, expStatus, "Status of the knowledge article is not as expected");
            genericFunctions.logToExtentReport("Pass", "Status verification successful. Expected and actual statuses match.");
        } catch (AssertionError e) {
            genericFunctions.logToExtentReport("Fail", "Status verification failed. Expected: " + expStatus + ", but found: " + actStatus);
            throw e;
        }
    }
}