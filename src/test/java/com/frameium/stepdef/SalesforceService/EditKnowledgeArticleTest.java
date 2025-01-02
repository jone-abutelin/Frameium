package com.frameium.stepdef.SalesforceService;
import com.frameium.genericfunctions.GenericFunctions;
import com.frameium.pageobject.SalesforceService.EditKnowledgeArticle;
import com.frameium.pageobject.SalesforceService.KnowledgeArticle;
import com.frameium.stepdef.Hooks;
import com.frameium.stepdef.TestSetUp;
import io.cucumber.java.en.*;
import org.openqa.selenium.WebDriver;

import java.net.MalformedURLException;

/**
 * Step definition class for testing the functionality of editing knowledge articles in Salesforce Service Console.
 * Utilizes Cucumber's Given/When/Then annotations for step definitions.
 */
public class EditKnowledgeArticleTest {
    private EditKnowledgeArticle editka;
    private KnowledgeArticle ka;
    private WebDriver driver;
    TestSetUp setUp;
    private GenericFunctions genericFunctions;
    Hooks hooks = new Hooks();

    /**
     * Constructor to initialize the page objects, WebDriver, and test setup.
     * @param setUp Test setup instance used to initialize WebDriver and other configurations.
     */

    public EditKnowledgeArticleTest(TestSetUp setUp) throws MalformedURLException {
        this.setUp = setUp;
        genericFunctions = new GenericFunctions(setUp.baseTest.driver);
        editka = new EditKnowledgeArticle(setUp.baseTest.driver);
        ka = new KnowledgeArticle(setUp.baseTest.driver);
        // Assign the driver from setUp to the local driver variable
        this.driver = setUp.baseTest.driver;
    }

    /**
     * Step definition for verifying that the user is on the created knowledge article screen.
     * This step is typically used to ensure that the test starts on the correct page.
     */
    @Given("user is on created knowledge article screen")
    public void user_is_on_created_knowledge_article_screen() {

    }

    /**
     * Step definition for clicking the "Edit" button on the knowledge article.
     */
    @When("I click on edit")
    public void i_click_on_edit() throws InterruptedException {
        editka.clickEdit();
    }

    /**
     * Step definition for editing the answer field of the knowledge article.
     * This includes clicking the edit button and then interacting with the answer field.
     */
    @When("I am  able to edit answer field")
    public void i_am_able_to_edit_answer_field() throws InterruptedException {
        editka.clickEdit();
        editka.clickEditAnswer();
        hooks.takeScreenshot(hooks.scenario);
    }
    /**
     * Step definition for saving the changes made to the knowledge article.
     */
    @Then("I click on save")
    public void i_click_on_save() {
        ka.saveKnowledge();
    }
}