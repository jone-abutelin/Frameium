package com.frameium.pageobject.SalesforceService;

import com.frameium.genericfunctions.GenericFunctions;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;
import java.util.List;
import java.util.NoSuchElementException;

/**
 * This class provides methods for managing cases in Salesforce, including creating new cases,
 * setting case properties, changing case owners, and handling omnichannel status.
 */

public class SkillBasedRouting extends GenericFunctions {


    private By navigationMenu = By.xpath("//button[@title='Show Navigation Menu']");
    private By caseBtn = By.xpath("//div[@id='navMenuList']/div/ul/li/div/a/span[2]/span[text()='Cases']");
    private By newButton = By.xpath("//*[@id='brandBand_1']/div/div/div/div/div[1]/div[1]/div[2]/ul/li[1]/a");
    private By priority = By.xpath("//button[contains(@aria-label,'Priority ')]");
    private By caseReason = By.xpath("//button[contains(@aria-label,'Case Reason')]");
    private By caseType = By.xpath("//button[contains(@aria-label,'Type')]");
    private By editOwner = By.xpath("//button[@title='Edit Case Owner']");
    private By deleteIcon = By.xpath("//a[@class='deleteAction']/span[@class='deleteIcon']/parent::*");
    private By ownerNameList = By.xpath("//div[@class='undefined lookup__menu uiAbstractList uiAutocompleteList uiInput uiAutocomplete uiInput--default uiInput--lookup']/div[@class='listContent']/ul/li/a/div[2]/div/lightning-formatted-rich-text/span");
    private By saveBtn = By.xpath("//button[@title='Save']");
    private By omnichannelBtn = By.xpath("//div[@class='oneUtilityBar slds-utility-bar_container oneUtilityBarContent']/ul/li[1]/div/div/button");
    private By changeOmniChannelStatusBtn = By.xpath("//div[contains(@class,'slds-is-open') and @role='dialog']/div[2]/div/div[2]/div[1]/div/button");
    private By availableBtn = By.xpath("//span[text()='Available - Case']");
    private By minimizeBtn = By.xpath("//div[contains(@class,'slds-is-open') and @role='dialog']/div/div[2]/span/button");

    private By queues = By.xpath("//div[contains(@class,'uiMenuList--default visible positioned')]/div[@class='entityMenuList']/ul/li/a[@title='Queues']");
    private By caseDetails = By.xpath("(//h2/a[text()='Case Details'])[3]");
    private By search = By.xpath("//input[@title='Search Queues']");
    private By caseNum = By.xpath("//div[@class='record-layout-container']/descendant::records-record-layout-row[2]/slot/records-record-layout-item[1]/div/div/dd/div/span[1]");
    private By queNameList = By.xpath("//div[@class='undefined lookup__menu uiAbstractList uiAutocompleteList uiInput uiAutocomplete uiInput--default uiInput--lookup']/div[2]/ul/li/a/div[2]/div[@title='AutoRouting']/lightning-formatted-rich-text/span/*");

    private By detailsBtn = By.xpath("(//li[@title='Details'])[3]");
    private By typeFieldValue = By.xpath("//div[@class='record-layout-container']/descendant::records-record-layout-row[5]/slot/records-record-layout-item[1]/div/div/dd/div/span[1]");
    private By reasonFieldValue = By.xpath("//div[@class='record-layout-container']/descendant::records-record-layout-row[6]/slot/records-record-layout-item[1]/div/div/dd/div/span");

    public static String fetchedData;
    private WebDriver driver;
    private Case cs;

    /**
     * Constructor for initializing the WebDriver and Case object.
     *
     * @param driver The WebDriver instance used to interact with the web page.
     */
    public SkillBasedRouting(WebDriver driver) {
        this.driver = driver;
        cs = new Case(driver);
    }

    /**
     * Creates a new case with the specified contact, account, type, and reason.
     *
     * @param contact The contact name for the case.
     * @param account The account name for the case.
     * @param type    The type of the case.
     * @param reason  The reason for the case.
     * @throws InterruptedException If the thread is interrupted during sleep.
     */
    public void createNewCase(String contact, String account, String type, String reason) throws InterruptedException {
        goToCasePage();
        clickNew();
        createCase(contact, account, type, reason);
    }

    /**
     * Navigates to the Cases page from the navigation menu.
     */
    public void goToCasePage() {
        clickElement(navigationMenu);
        waitForByElement(caseBtn);
        clickElement(caseBtn);
    }

    /**
     * Clicks the 'New' button to create a new case.
     */
    public void clickNew() {
        try {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
            WebElement newButtonElement = wait.until(ExpectedConditions.elementToBeClickable(newButton));
            newButtonElement.click();
            WebDriverWait newPageWait = new WebDriverWait(driver, Duration.ofSeconds(2));
            newPageWait.until(ExpectedConditions.visibilityOfElementLocated(priority));

        } catch (NoSuchElementException e) {
            System.err.println("The 'New' button element not found: " + e.getMessage());
        } catch (Exception e) {
            System.err.println("An error occurred while clicking the 'New' button: " + e.getMessage());
        }
    }

    /**
     * Creates a case with the provided details.
     *
     * @param contact The contact name for the case.
     * @param account The account name for the case.
     * @param type    The type of the case.
     * @param reason  The reason for the case.
     */

    public void createCase(String contact, String account, String type, String reason) throws InterruptedException {
        cs.selectPriority();
        cs.selectCaseOrigin();
        cs.enterContactName(contact);
        cs.enterAccountName(account);
        selectType(type);
        selectCaseReason(reason);
        cs.enterSubject("Drilling Equipment is too complex to operate due to its tight bolt");
        cs.enterDescription("Customer requires more details about the product specifications");
        cs.clickSaveBtn();
    }

    /**
     * Selects the type of the case from a dropdown menu.
     *
     * @param type The type of the case to select.
     */
    public void selectType(String type) {
        WebElement typeField = driver.findElement(caseType);
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", typeField);
        WebElement typeField1 = driver.findElement(By.xpath("//lightning-base-combobox-item[@data-value='" + type + "']"));
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", typeField1);
    }

    /**
     * Selects the reason for the case from a dropdown menu.
     *
     * @param reason The reason for the case to select.
     */

    public void selectCaseReason(String reason) {
        WebElement caseReasonField = driver.findElement(caseReason);
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", caseReasonField);

        WebElement caseReasonField1 = driver.findElement(By.xpath("//lightning-base-combobox-item[@data-value='" + reason + "']"));
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", caseReasonField1);
    }

    /**
     * Removes the current owner of the case.
     */
    public void removeOwner() throws InterruptedException {
        waitForByElement(editOwner);
        clickElementUsingJavaScript(driver.findElement(editOwner));
        Thread.sleep(2000);
        clickDynamicElement(deleteIcon);
    }

    /**
     * Changes the owner of the case to the specified owner.
     *
     * @param owner The name of the new owner.
     */

    public void changeOwnerName(String owner) throws InterruptedException {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
        wait.until(ExpectedConditions.visibilityOfElementLocated(ownerNameList));
        Thread.sleep(2000);
        List<WebElement> searchResults = driver.findElements(ownerNameList);

        for (WebElement result : searchResults) {

            String text = result.getText();
            System.out.println("Owner name >>>> " + text);

            if (text.equals(owner)) {
                clickElementUsingJavaScript(result);
            }
        }
        clickDynamicElement(saveBtn);
    }

    /**
     * Sets up the omnichannel status to 'Available - Case'
     */
    public void omnichannelSetup() throws InterruptedException {
        clickElementUsingJavaScript(driver.findElement(omnichannelBtn));

        clickElementUsingJavaScript(driver.findElement(changeOmniChannelStatusBtn));
        clickElementUsingJavaScript(driver.findElement(availableBtn));
        Thread.sleep(4000);
        clickElement(minimizeBtn);
    }

    /**
     * Selects a queue for the case.
     *
     * @param queue The name of the queue to select.
     */
    public void selectQueue(String queue) throws InterruptedException {
        clickElementUsingJavaScript(driver.findElement(caseDetails));
        System.out.println("<<<<<<<<<<< scroll finished >>>>>> ");

        removeOwner();
        clickOnCaseOwnerDropdownBtn();
        clickElement(queues);

// To search the queue
        List<WebElement> searchBoxes = driver.findElements(search);

        for (WebElement element : searchBoxes) {

            if (element.isDisplayed() && element.isEnabled()) {
                clickElementUsingJavaScript(element);
                element.sendKeys(queue);
                System.out.println("Clicked on search bar..." );
                break;
            } else {
                System.out.println("Element not interactable!!! (search bar) " );
            }
        }


        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
        wait.until(ExpectedConditions.presenceOfElementLocated(queNameList));
        Thread.sleep(2000);
        List<WebElement> searchResults = driver.findElements(queNameList);

        for (WebElement result : searchResults) {

            String text = result.getText();
            System.out.println("text >> " + text);
            if (text.equals(queue)) {
                clickElementUsingJavaScript(result);
            }
        }
        clickDynamicElement(saveBtn);
    }

    /**
     * Gets the case number of the created case.
     *
     * @return The case number.
     */

    public String getCaseNumber(){
        fetchedData =  driver.findElement(caseNum).getText();
        return fetchedData;
    }

    /**
     * Verifies the owner name, type, and reason of the case.
     *
     * @param type   The expected type of the case.
     * @param reason The expected reason for the case.
     * @return The owner name if it matches the expected type and reason, otherwise null.
     */

    public String verifyOwnerName(String type, String reason) throws InterruptedException {
        clickElement(detailsBtn);

        waitForByElement(typeFieldValue);
        String typeValue = driver.findElement(typeFieldValue).getText();
        System.out.println(">>>>>  Type value :: >>>>>  " + typeValue );

        String caseReasonValue = driver.findElement(reasonFieldValue).getText();
        System.out.println(">>>>>  Reason value :: >>>>>  " + caseReasonValue );

        if (type.equals(typeValue) && reason.equals(caseReasonValue)) {
            Thread.sleep(2000);
            String text = driver.findElement(By.xpath("//div[@class='record-layout-container']/descendant::records-record-layout-row[1]/descendant::records-hoverable-link/div/a/span/slot/span/slot")).getAttribute("innerText");
            System.out.println(">>>>>> ???? text "+ text );
            return text;

        }
        return null;
    }

    /**
     * Clicks on the Case Owner dropdown button, retrying with different indices if necessary.
     */
    public void clickOnCaseOwnerDropdownBtn() {
        int i = 0;  // XPath indexing starts from 1
        boolean found = false;

        while (!found) {
            try {
                Thread.sleep(2000);
                By clickIconAtIndex = By.xpath("(//a[contains(@aria-label,'Case Owner—Current Selection:')]/lightning-icon)["+ i +"]");
                WebElement element1 = driver.findElement(clickIconAtIndex);
                if (element1.isDisplayed() && element1.isEnabled()) {
                    clickElementUsingJavaScript(element1);
                    found = true;
                    System.out.println("Clicked element at index: " + i);
                    return;
                } else {
                    System.out.println("Element not interactable at index: " + i);
                }
            } catch (NoSuchElementException e) {
                System.out.println("No element found at index: " + i);
                break;
            }catch (Exception e) {
                System.out.println("Exception at index: " + i + ", " + e.getMessage());
            }
            i++;
        }
        if (!found) {
            System.out.println("No clickable element found.");
        }
    }

}