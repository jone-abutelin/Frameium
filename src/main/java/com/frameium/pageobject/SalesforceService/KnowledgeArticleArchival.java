package com.frameium.pageobject.SalesforceService;


import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;
import com.frameium.genericfunctions.GenericFunctions;

/**
 * This class contains methods for interacting with the archival process of Knowledge Articles in Salesforce.
 */

public class KnowledgeArticleArchival extends GenericFunctions {
    private By clickArchiveBtn = By.xpath("//button[@name='ArchiveKnowledgeArticleVersion']");
    private By confirmArchBtn = By.xpath("//span[text()='Archive']/parent::*");
    private By verifyStatus = By.xpath("//p[@title='Publication Status']/following-sibling::p/slot/lightning-formatted-text");

    /**
     * Constructor to initialize the WebDriver.
     *
     * @param driver The WebDriver instance used for interacting with the web page.
     */
    public KnowledgeArticleArchival(WebDriver driver) {

        this.driver = driver;
    }

    /**
     * Navigates back to the previous page.
     */
    public void navigateBackToKnowledge(){
        driver.navigate().back();
    }

    /**
     * Clicks the 'Archive' button to start the archival process and confirms the action.
     */
    public void clickArchive() throws InterruptedException {
        Thread.sleep(3000);

        // Wait until the 'Archive' button is present in the DOM
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(60));
        wait.until(ExpectedConditions.presenceOfElementLocated(clickArchiveBtn));

        clickDynamicElement(clickArchiveBtn);

        Thread.sleep(3000);
        clickElement(confirmArchBtn);

    }

    /**
     * Checks the publication status of the Knowledge Article.
     *
     * @return The text of the publication status.
     */
    public String checkPublicationStatus() throws InterruptedException {

        Thread.sleep(3000);
        waitForByElement(verifyStatus);
        return driver.findElement(verifyStatus).getText();
    }
}

