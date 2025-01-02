package com.frameium.pageobject.SalesforceService;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.NoSuchElementException;

import com.frameium.genericfunctions.GenericFunctions;

/**
 * This class contains methods for interacting with the Knowledge Page in Salesforce.
 */
public class KnowledgeArticle extends GenericFunctions {

    private By showNavigationMenu = By.xpath("//button[@title='Show Navigation Menu']");
    private By clickKnowledge = By.xpath("//a[@data-itemid='Knowledge__kav']");
    private By clickNewBtn = By.xpath("//div[@title='New']");
    private By title = By.xpath("//label[text()='Title']");
    private By enterTitle = By.xpath("//input[@name='Title']");
    private By enterURL = By.xpath("//input[@name='UrlName']");
    private By goToiFrame1 = By.xpath("(//iframe[@title='Rich Text Area'])[1]");
    private By enterQuestion = By.xpath("(//body[contains(@aria-label,'Rich Text Area.')])[1]");
    private By goToiFrame2 = By.xpath("(//iframe[@title='Rich Text Area'])[2]");
    private By enterAnswer = By.xpath("//body[contains(@aria-label,'Rich Text Area.')]");
    private By saveBtn = By.xpath("//button[text()='Save']");
    private By verifyTitle = By.xpath("//span[text()='Title']/parent::div/parent::dt/following-sibling::dd/div/span/slot/lightning-formatted-text");

    /**
     * Constructor to initialize the WebDriver.
     *
     * @param driver The WebDriver instance used for interacting with the web page.
     */
    public KnowledgeArticle(WebDriver driver) {

        this.driver = driver;
    }

    /**
     * Clicks the Knowledge button from Navigation Menu to navigate to the Knowledge page.
     */
    public void switchToKnowledge() throws InterruptedException {
        clickElement(showNavigationMenu);
        clickElement(clickKnowledge);
        Thread.sleep(3000);
    }

    /**
     * Clicks the 'New' button to create a new Knowledge Article.
     */

    public void clickNewBtn() {
        try {
            // Wait until the 'New' button is clickable
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
            WebElement newButton = wait.until(ExpectedConditions.elementToBeClickable(clickNewBtn));

            newButton.click();

            // Wait until the 'Title' field is visible
            WebDriverWait newPageWait = new WebDriverWait(driver, Duration.ofSeconds(30));
            newPageWait.until(ExpectedConditions.visibilityOfElementLocated(title));

        } catch (NoSuchElementException e) {
            System.err.println("The 'New' button element not found: " + e.getMessage());
        } catch (Exception e) {
            System.err.println("An error occurred while clicking the 'New' button: " + e.getMessage());
        }
    }

    /**
     * Creates a new Knowledge Article by entering the title, URL, question, and answer.
     *
     * @param title The title of the Knowledge Article.
     * @param url   The URL name of the Knowledge Article.
     */
    public void createKnowledge(String title, String url) throws InterruptedException {
        Title(title);
        Url(url);
        question();
        answer();
    }
    /**
     * Sets the title of the Knowledge Article.
     *
     * @param title The title to be set.
     */
    public void Title(String title) {
        clickElement(enterTitle);
        inputTitle(title);
    }

    /**
     * Sets the URL name of the Knowledge Article.
     *
     * @param url The URL name to be set.
     */

    public void Url(String url) throws InterruptedException {
        clickElement(enterURL);
        driver.findElement(enterURL).clear();
        Thread.sleep(3000);
        inputURL(url);
    }

    /**
     * Sets the question in the Knowledge Article.
     */
    public void question() throws InterruptedException {
        Thread.sleep(5000);

        WebElement eleFrame1 = driver.findElement(goToiFrame1);
        driver.switchTo().frame(eleFrame1);
        clickElementUsingJavaScript(driver.findElement(enterQuestion));
        inputQuestion("How to reset password for the community site");
        driver.switchTo().defaultContent();
    }

    /**
     * Sets the answer in the Knowledge Article.
     */

    public void answer() throws InterruptedException {

        WebElement eleFrame2 = driver.findElement(goToiFrame2);
        driver.switchTo().frame(eleFrame2);
        clickElementUsingJavaScript(driver.findElement(enterAnswer));
        inputAnswer("\n" +
                "To reset the password for a Community site in Salesforce, you typically follow these steps:\n" +
                "Access the Community Login Page: Go to the login page of your Community site. This is usually the URL of your Community site appended with \"/login\". For example, if your Community site URL is \"https://yourcommunity.force.com\"");
        driver.switchTo().defaultContent();
    }

    /**
     * Clicks the 'Save' button to save the Knowledge Article.
     */
    public void saveKnowledge() {
        clickElement(saveBtn);
    }

    /**
     * Inputs the title into the title field.
     *
     * @param title The title to be entered.
     */

    public void inputTitle(String title)
    {
         enterKeys(enterTitle,title);
    }
    /**
     * Inputs the URL into the URL field.
     *
     * @param url The URL to be entered.
     */
    public void inputURL(String url)
    {
        enterKeys(enterURL,url);
    }

    /**
     * Inputs the question into the question field.
     *
     * @param question The question to be entered.
     */
    public void inputQuestion(String question)
    {
        enterKeys(enterQuestion,question);
    }

    /**
     * Inputs the answer into the answer field.
     *
     * @param answer The answer to be entered.
     */
    public void inputAnswer(String answer)
    {
        enterKeys(enterAnswer,answer);
    }

    /**
     * Retrieves the actual title of the Knowledge Article from the page.
     *
     * @return The text of the title.
     */
    public String getActTitle(){
        waitForByElement(verifyTitle);
        return driver.findElement(verifyTitle).getText();
    }
}

