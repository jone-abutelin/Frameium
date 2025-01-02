package com.frameium.pageobject.SalesforceService;

import com.frameium.genericfunctions.GenericFunctions;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

/**
 * This class represents actions related to accepting cases in Salesforce Omni-Channel.
 */
public class AcceptCase extends GenericFunctions {

    private By selectOnmi = By.xpath("//div[@class='oneUtilityBar slds-utility-bar_container oneUtilityBarContent']/ul/li[1]/div/div/button");
    private By expcaseid = By.xpath("//div[contains(@class,'slds-is-open') and @role='dialog']/div[2]/div/div[2]/div[2]/div/section/div/ul/li/div/div/div[4]/span[3]");
    private By minimizeBtn = By.xpath("//div[contains(@class,'slds-is-open') and @role='dialog']/div/div[2]/span/button");


    private WebDriver driver;
    private SkillBasedRouting sbroute;


    /**
     * Constructor to initialize the WebDriver and SkillBasedRouting instance.
     * @param driver WebDriver instance used for interacting with the browser.
     */
    public AcceptCase(WebDriver driver) {

        this.driver = driver;
        sbroute = new SkillBasedRouting(driver);
    }

    /**
     * Retrieves the case ID of the current case.
     * @return The case ID as a string.
     */
    public String verifyCaseId(){
        String actId = sbroute.getCaseNumber();
        return actId;
    }

    /**
     * Retrieves the expected case ID from the UI and compares it with the actual case ID.
     * @return The expected case ID if it matches the actual case ID, otherwise null.
     */
    public String getExpectedCaseId() {
        waitForByElement(expcaseid);
        String actCaseId = verifyCaseId();
        String expCaseId = "//div[contains(@class,'slds-is-open') and @role='dialog']/div[2]/div/div[2]/div[2]/div/section/div/ul/li/div/div/div[4]/span[3]";
        List<WebElement> elements = driver.findElements(By.xpath(expCaseId));
        String exp = null;
        for (WebElement element : elements) {
            String text = element.getText();
            System.out.println(element.getText());
            if (text.equals(actCaseId)) {
                exp = text;
            }
        }
        return exp;
    }

    /**
     * Clicks the Omni-Channel button to open the Omni-Channel view.
     */
    public void selectOmniChannel(){
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(60));
        WebElement omni = wait.until(ExpectedConditions.presenceOfElementLocated(selectOnmi));
        clickElement(omni);
    }

    /**
     * Accepts the case based on the case ID and minimizes the dialog.
     */
    public void acptTheCase() throws InterruptedException {
        String actcaseid = verifyCaseId();
        String expCasePath = "//div[contains(@class,'slds-is-open') and @role='dialog']/div[2]/div/div[2]/div[2]/div/section/div/ul/li/div/div/div[4]/span[3]";
        List<WebElement> listItems = driver.findElements(By.xpath(expCasePath));
        String buttonXPath = "//div[contains(@class,'slds-is-open') and @role='dialog']/div[2]/div/div[2]/div[2]/div/section/div/ul/li/div/div[2]/button";
        List<WebElement> buttons = driver.findElements(By.xpath(buttonXPath));
        if (listItems.size() != buttons.size()) {
            System.out.println("Mismatch in the number of list items and buttons.");
            return;
        }
        // Iterate through list items and buttons to find the correct case ID
        for (int i = 1; i <= listItems.size(); i++) {
            String expCasePath1 = "(//div[contains(@class,'slds-is-open') and @role='dialog']/div[2]/div/div[2]/div[2]/div/section/div/ul/li/div/div/div[4]/span[3])[" + i + "]";
            WebElement listItem1 = driver.findElement(By.xpath(expCasePath1));
            String buttonXPath1 = "(//div[contains(@class,'slds-is-open') and @role='dialog']/div[2]/div/div[2]/div[2]/div/section/div/ul/li/div/div[2]/button)[" + i + "]";
            WebElement button1 = driver.findElement(By.xpath(buttonXPath1));
            String text = listItem1.getText();
            if (text.equals(actcaseid)) {

                clickElementUsingJavaScript(button1);
                System.out.println("Case with ID :" +text+ " is accepted");
                break;
            }
        }
        clickElement(minimizeBtn);
    }
}