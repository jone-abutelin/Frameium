package com.frameium.pageobject.SalesforceService;

import com.frameium.genericfunctions.GenericFunctions;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

/**
 * This class contains methods for publishing Knowledge Articles in Salesforce.
 * It includes actions for clicking the publish button, verifying publication status,
 * and retrieving article details.
 */
public class PublishKnowledgeArticle extends GenericFunctions {

    // Locators for various elements related to publishing Knowledge Articles

    private By publishClick = By.xpath("//button[text()='Publish']");
    private By publishBtn = By.xpath("//span[text()='Publish']");
    private By publishMsg = By.xpath("//span[contains(text(),'published')]");

    private By status = By.xpath("//p[text()='Publication Status']/parent::div/child::p[2]/slot/lightning-formatted-text");
    private By artNum = By.xpath("//p[@title='Article Number']/following-sibling::p/slot/lightning-formatted-text");


    /**
     * Constructor to initialize the WebDriver.
     *
     * @param driver The WebDriver instance used for interacting with the web page.
     */
    public PublishKnowledgeArticle(WebDriver driver){
        this.driver = driver;

    }

    /**
     * Clicks the 'Publish' button to initiate the publication of the Knowledge Article.
     */

    public void clickPublish() throws InterruptedException {
        waitForByElement(publishClick);
        clickDynamicElement(publishClick);
    }


    /**
     * Clicks the secondary 'Publish' button to confirm the publication action.
     */
    public void clickPublishBtn(){
        clickElement(publishBtn);
    }

    /**
     * Verifies the publication message displayed after the article is published.
     *
     * @return The text of the publication message.
     */
    public String verifyPublishMsg() throws InterruptedException {
        Thread.sleep(3000);
        waitForByElement(publishMsg);
        return driver.findElement(publishMsg).getText();
    }

    /**
     * Verifies the publication status of the Knowledge Article.
     *
     * @return The publication status text.
     */
    public String verifyStatus(){
        waitForByElement(status);
        return driver.findElement(status).getText();

    }

    /**
     * Retrieves the article number of the Knowledge Article.
     *
     * @return The article number.
     */

    public String articleId(){
        waitForByElement(artNum);
        return driver.findElement(artNum).getText();
    }

}