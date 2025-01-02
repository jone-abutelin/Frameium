package com.frameium.pageobject.SalesforceService;

import com.frameium.genericfunctions.GenericFunctions;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
/**
 * This class contains methods for versioning Knowledge Articles in Salesforce, including editing of Knowledge Article,
 * saving, publishing, and verifying versions.
 */

public class KnowledgeArticleVersioning extends GenericFunctions {

    // Locators for various elements related to article versioning

    private By goToiFrame2 = By.xpath("(//iframe[@title='Rich Text Area'])[2]");
    private By editAsDraftBtn = By.xpath("//button[text()='Edit as Draft']");
    private By confirmEditBtn = By.xpath("//button[@title='Edit as Draft']");
    private By updateAnswer = By.xpath("//body[contains(@aria-label,'Rich Text Area.')]");
    private By verifyVer = By.xpath("(//p[@title='Version Number']/parent::*/p[2]/slot/lightning-formatted-number)[2]");
    private By saveBtn = By.xpath("//button[text()='Save']");
    private By publishBtn = By.xpath("//button[text()='Publish']");
    private By clickPublish = By.xpath("//span[text()='Publish']");

    /**
     * Constructor to initialize the WebDriver.
     *
     * @param driver The WebDriver instance used for interacting with the web page.
     */

    public KnowledgeArticleVersioning(WebDriver driver) {

        this.driver = driver;
    }

    /**
     * Clicks the 'Edit as Draft' button to start editing the article as a draft, then confirms the action.
     */
    public void clickEditBtn() throws InterruptedException {
        Thread.sleep(3000);
        clickElementUsingJavaScript(driver.findElement(editAsDraftBtn));

        Thread.sleep(3000);
        clickElementUsingJavaScript(driver.findElement(confirmEditBtn));
    }


    /**
     * Makes changes to the Knowledge Article by updating the answer field.
     */
    public void makeChanges() throws InterruptedException {
        Thread.sleep(3000);

        // Wait until the iframe containing the answer field is visible
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(60));
        wait.until(ExpectedConditions.visibilityOfElementLocated(goToiFrame2));

        // Switches to the iframe and updates the answer field
        WebElement eleFrame2 = driver.findElement(goToiFrame2);
        driver.switchTo().frame(eleFrame2);
        clickElementUsingJavaScript(driver.findElement(updateAnswer));
        inputAnswer("\nUpdate Answer field : Versioning the article to V2. Adding guidance to enter OTP to company portal\n" +
                "As per the new  security procedure, after password reset user has to follow additional steps by logging into the company portal for registering the OTP received in their registered contact number.");

        driver.switchTo().defaultContent();

    }

    /**
     * Clicks the 'Save' button, then publishes the article.
     */
    public void clickSaveBtn() throws InterruptedException {
        Thread.sleep(3000);

        // Wait until the 'Save' button is visible and clicks it
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(60));
        wait.until(ExpectedConditions.visibilityOfElementLocated(saveBtn));
        clickElementUsingJavaScript(driver.findElement(saveBtn));

        Thread.sleep(3000);
        // Wait until the 'Publish' button is visible, then clicks it to publish the article
        waitForByElement(publishBtn);
        clickElementUsingJavaScript(driver.findElement(publishBtn));
        clickElement(clickPublish);
    }

    /**
     * Verifies the version number of the Knowledge Article.
     *
     * @return The version number of the article.
     */
    public String verifyVersion() throws InterruptedException {
        Thread.sleep(5000);
        isDisplayedBy(verifyVer);
        String versionNum =  driver.findElement(verifyVer).getText();
        System.out.println ( "versionNum : " + versionNum);
        return versionNum;
    }

    /**
     * Inputs the updated answer into the answer field.
     *
     * @param answer The answer to be entered.
     */
    public void inputAnswer(String answer)
    {
        enterKeys(updateAnswer,answer);
    }

}
