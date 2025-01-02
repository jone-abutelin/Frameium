package com.frameium.pageobject.SalesforceService;

import com.frameium.genericfunctions.GenericFunctions;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;


/**
 * This class represents actions related to editing a Knowledge Article in Salesforce.
 */
public class EditKnowledgeArticle extends GenericFunctions {

    // Locator for various UI elements
    private By editbtn = By.xpath("//button[text()='Edit']");
    private By enterAnswer = By.xpath("//body[contains(@aria-label,'Rich Text Area.')]");
    private By goToiFrame2 = By.xpath("(//iframe[@title='Rich Text Area'])[2]");

    /**
     * Constructor to initialize the WebDriver instance.
     * @param driver WebDriver instance used for interacting with the browser.
     */
    public EditKnowledgeArticle(WebDriver driver){
        this.driver = driver;
    }

    /**
     * Clicks on the 'Edit' button to start editing the Knowledge Article.
     */
    public void clickEdit() throws InterruptedException {
        Thread.sleep(2000);
        clickDynamicElement(editbtn);
    }

    /**
     * Inputs text into the rich text area of the Knowledge Article.
     * @param answer The text to be entered into the rich text area.
     */
    public void inputAnswer(String answer)
    {
        enterKeys(enterAnswer,answer);
    }

    /**
     * Switches to the iframe context and inputs the specified answer into the rich text area
     *  Switches back to the default content of the page
     */
    public void clickEditAnswer() throws InterruptedException {
        Thread.sleep(5000);
        scrollDownVertically();
        WebElement eleFrame2 = driver.findElement(goToiFrame2);
        driver.switchTo().frame(eleFrame2);
        clickElementUsingJavaScript(driver.findElement(enterAnswer));
        inputAnswer("\n" +
                "Click on reset password");
        driver.switchTo().defaultContent();
    }
}
