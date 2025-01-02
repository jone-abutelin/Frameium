package com.frameium.stepdef.SalesforceService;

import com.frameium.genericfunctions.GenericFunctions;
import com.frameium.pageobject.SalesforceService.KnowledgeArticle;
import com.frameium.stepdef.Hooks;
import com.frameium.stepdef.TestSetUp;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;

import java.net.MalformedURLException;

/**
 * Step definition class for testing the functionality of Knowledge Articles in Salesforce Service Console.
 * Utilizes Cucumber's Given/When/Then annotations for defining test steps.
 */
public class KnowledgeArticleTest  {

    private KnowledgeArticle knowledge;
    private WebDriver driver;
    private String exptdTitle = "Password Reset";
    private String exptdVer = "2";
    TestSetUp setUp;
    private GenericFunctions genericFunctions;
    Hooks hooks = new Hooks();

    /**
     * Constructor to initialize the page objects, WebDriver, and setup.
     * @param setUp Test setup instance used to initialize WebDriver and other configurations.
     */
    public KnowledgeArticleTest(TestSetUp setUp) throws MalformedURLException {
        this.setUp = setUp;
        genericFunctions = new GenericFunctions(setUp.baseTest.driver);
        knowledge = new KnowledgeArticle(setUp.baseTest.driver);
        // Assign the driver from setUp to the local driver variable
        this.driver = setUp.baseTest.driver;
    }

    /**
     * Step definition for navigating to the Knowledge page.
     */
    @Then("I go to the Knowledge page")
    public void i_go_to_the_knowledge_page() throws InterruptedException {
        knowledge.switchToKnowledge();
    }

    /**
     * Step definition for clicking the "New" button to create a new Knowledge article.
     */
    @And("I click on New button")
    public void i_click_on_new_button() {
        knowledge.clickNewBtn();
        hooks.takeScreenshot(hooks.scenario);
    }

    /**
     * Step definition for creating a new Knowledge article with the given title and URL.
     * @param title The title of the new Knowledge article.
     * @param url The URL associated with the Knowledge article.
     */
    @Then("I will create a new Knowledge article with {string} and {string}")
    public void i_will_create_a_new_knowledge_article_and_verify_it(String title, String url) throws InterruptedException {
        knowledge.createKnowledge(title,url);
        hooks.takeScreenshot(hooks.scenario);
        knowledge.saveKnowledge();
        }


    /**
     * Step definition for verifying the created Knowledge article's title.
     * @param title The expected title of the Knowledge article.
     */
    @Then("I verify the created knowledge with {string}")
    public void iVerifyTheCreatedKnowledgeWith(String title) {

        // Retrieve the actual title of the created Knowledge article
        String actTitle = knowledge.getActTitle();
        System.out.println(">>>>>>>>>>>>>>>>>" + actTitle);

        // Compare the actual title with the expected title
        try {
            Assert.assertEquals(actTitle, title, "Title name does not match");
            genericFunctions.logToExtentReport("Pass", "Title verification successful. Expected and actual titles match.");
        } catch (AssertionError e) {
            genericFunctions.logToExtentReport("Fail", "Title verification failed. Expected: " + title + ", but found: " + actTitle);
            throw e;
        }
    }
}
