package com.frameium.pageobject.SalesforceService;

import com.frameium.genericfunctions.GenericFunctions;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

/**
 * This class handles the resolution of cases in the Salesforce Service Console.
 */
public class CaseResolution extends GenericFunctions {

    // Locator for various UI elements

    private By globalSearch = By.xpath("//button[@class='slds-button slds-button_neutral search-button slds-truncate']");
    private By inputField = By.xpath("//input[@class='slds-input']");
    private By searchArticle = By.xpath("(//input[@title='Search Knowledge...'])[3]");
    private By findArticle = By.xpath("//div[@class='listContent']/ul[@role='presentation']/li");
    private By verifyArticle = By.xpath("//lightning-formatted-text[@slot='primaryField' and text()='Password Reset']");
    private By statusClick = By.xpath("//button[@title='Edit Status']");
    private By statusDropdown = By.xpath("//a[@role='combobox' and text()='New']");
    private By statusSelect = By.xpath("//a[@title='Closed']");
    private  By saveBtn = By.xpath("//button[@title='Save']");
    private com.frameium.pageobject.SalesforceService.SkillBasedRouting skill;

    /**
     * Constructor to initialize the WebDriver and SkillBasedRouting instance.
     * @param driver WebDriver instance used for interacting with the browser.
     */
    public CaseResolution(WebDriver driver){
        this.driver = driver;
        skill = new com.frameium.pageobject.SalesforceService.SkillBasedRouting(driver);
    }

    /**
     * Navigates to a specific case by performing a search with the given case ID.
     * @param elementText The case ID or text to search for.
     */

    public void navigateToOpenCase(String elementText) throws InterruptedException {
        searchForCase();
        searchWithCaseId(elementText);
    }

    /**
     * Clicks the global search button to start a search.
     */
    public void searchForCase(){
        waitForByElement(globalSearch);
        clickElementUsingJS(globalSearch);

    }

    /**
     * Searches for a case by its ID and clicks on it from the search results.
     * @param elementText The case ID to search for.
     */
    public void searchWithCaseId(String elementText) throws InterruptedException {
        dynamicElementPassInput(inputField,elementText);
        WebElement clickCase = driver.findElement(By.xpath("//span[@title='" +elementText+ "']"));
        Thread.sleep(2000);
        clickElementUsingJavaScript(clickCase);
    }

    /**
     * Searches for a knowledge article by keyword and clicks on the desired article.
     * @param keyword The keyword to search for.
     * @param article The title of the article to select.
     */

    public void enterKeyword(String keyword, String article) throws InterruptedException {

        dynamicElementPassInput(searchArticle,keyword);
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(60));
        wait.until(ExpectedConditions.visibilityOfElementLocated(findArticle));
        Thread.sleep(2000);
        List<WebElement> searchResults = driver.findElements(findArticle);

        for (WebElement result : searchResults) {

            String text = result.getText();
            System.out.println(" text >> " + text);
            System.out.println("article >> " + article);
            if (text.equals(article)) {
                Thread.sleep(2000);
                clickElementUsingJavaScript(result);
                break;
            }
        }
    }

    /**
     * Verifies if the knowledge article is opened and then navigates back.
     * @return The name of the opened article.
     */

    public String verifyKnowledgeOpened() throws InterruptedException {
        Thread.sleep(2000);
        String exptName = getActualArticleName();
//        System.out.println(">>>>>*******>>>>>>>>" +exptName);
        Thread.sleep(2000);
        driver.navigate().back();
        return exptName;
    }

    /**
     * Updates the status of the case to "Closed".
     */

    public void updateStatus() throws InterruptedException {
        //waitForByElement(statusClick);
        scrollToElement(driver.findElement(statusClick));
        clickElementUsingJavaScript(driver.findElement(statusClick));
        //waitForByElement(statusDropdown);
        clickDynamicElement(statusDropdown);
        //wait.until(ExpectedConditions.visibilityOfElementLocated(statusSelect));
        clickDynamicElement(statusSelect);

    }

    /**
     * Clicks the Save button to save changes.
     */
    public void clickSave() throws InterruptedException {
        clickDynamicElement(saveBtn);
    }

    /**
     * Retrieves the actual name of the knowledge article from the UI.
     * @return The name of the knowledge article.
     */
    public String getActualArticleName(){
        //waitForByElement(verifyArticle);
        return driver.findElement(verifyArticle).getText();
    }

}
