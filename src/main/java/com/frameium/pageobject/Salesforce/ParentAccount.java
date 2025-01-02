package com.frameium.pageobject.Salesforce;

import com.frameium.genericfunctions.GenericFunctions;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

import org.testng.Assert;

import javax.validation.constraints.AssertTrue;

/**
 * Represents the Parent Account page in Salesforce, providing methods to interact with parent account functionalities.
 */
public class ParentAccount extends GenericFunctions {
    // Locators for various elements on the lead page

    //private By DetailsBtn = By.xpath("//a[@data-tab-value='detailTab']");
    private By parentEditBtn = By.xpath("//button[@title='Edit Parent Account']");
    private By saveBtn = By.xpath("//button[@name='SaveEdit']");
    private By hierarchyBtn = By.xpath("//button[@title='View Account Hierarchy']");
    // private By clickDetails = By.xpath("//li[@title='Details']/a");
    //a[@class='slds-tabs_default__link' and @id='detailTab__item']
    //
    //   private By clickDetails = By.xpath("a[@class='slds-tabs_default__link' and @id='detailTab__item']");
    private By clickDetails = By.xpath(" (//li[@title='Related']/following-sibling::li[@title='Details'])[2]");

    private WebDriver driver;

    /**
     * Constructs a ParentAccount page object.
     *
     * @param driver The WebDriver instance used for browser automation.
     */
    public ParentAccount(WebDriver driver) {

        this.driver = driver;
    }

    /**
     * Clicks the Details button to navigate to the details tab.
     *
     * @throws InterruptedException If the thread is interrupted while sleeping.
     */
    public void detailsBtnClick() throws InterruptedException {
        //   WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(1000));
        //  wait.until(ExpectedConditions.presenceOfElementLocated(clickDetails));
        Thread.sleep(3000);
        clickElement(driver.findElement(clickDetails));

    }

    /**
     * Edits the parent account field by searching for the specified account name.
     *
     * @param parentaccountname The name of the parent account to search for.
     * @throws InterruptedException If the thread is interrupted while sleeping.
     */
    public void editParent(String parentaccountname) throws InterruptedException {
        clickElement(parentEditBtn);
        driver.findElement(By.xpath("//input[@placeholder='Search Accounts...']")).click();
        driver.findElement(By.xpath("//input[@placeholder='Search Accounts...']")).sendKeys(parentaccountname);

        // WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(4000));
        // wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//input[@placeholder='Search Accounts...']/parent::*/parent::*/parent::*/following-sibling::*/ul/li/lightning-base-combobox-item/span/span/span")));
        Thread.sleep(3000);
        List<WebElement> searchResults = driver.findElements(By.xpath("//input[@placeholder='Search Accounts...']/parent::*/parent::*/parent::*/following-sibling::*/ul/li/lightning-base-combobox-item/span/span"));

        System.out.println("searchResults " + searchResults.size());

        for (WebElement result : searchResults) {

            String text = result.getText();
            System.out.println("text >> " + text);
            if (text.equals(parentaccountname)) {

                clickElement(result);
            }
        }
    }

    /**
     * Clicks the Save button to save changes made to the parent account.
     *
     * @throws InterruptedException If the thread is interrupted while sleeping.
     */
    public void clickSave() throws InterruptedException {
        //Thread.sleep(2000);
        clickElement(saveBtn);
        Thread.sleep(2000);
    }

    /**
     * Verifies the relationship between a parent and child account hierarchy.
     *
     * @param parent The name of the parent account.
     * @param child  The name of the child account.
     */
    public void verifyHierarchy(String parent, String child) {

//        String parent = "GetCloudy Pvt LTD";
//        String child = "GetCloudy Logistics";

        clickElement(hierarchyBtn);

        waitForByElement(By.xpath("//div[contains(@class,'windowViewMode-normal')]/div/div[2]/div[2]/div[3]/div/div/table/tbody"));

        WebElement table = driver.findElement(By.xpath("//div[contains(@class,'windowViewMode-normal')]/div/div[2]/div[2]/div[3]/div/div/table/tbody"));
        List<WebElement> rows = table.findElements(By.tagName("tr"));

        for (int i = 1; i <= rows.size(); i++) {

            String rowText = driver.findElement(By.xpath("//div[contains(@class,'windowViewMode-normal')]/div/div[2]/div[2]/div[3]/div/div/table/tbody/tr[" + i + "]/th/div/div/a")).getText();
            System.out.println("rowText >>>>> ?  " + rowText);

            String level = driver.findElement(By.xpath("//div[contains(@class,'windowViewMode-normal')]/div/div[2]/div[2]/div[3]/div/div/table/tbody/tr[" + i + "]")).getAttribute("aria-level");
            System.out.println("level  >>> ??? " + level);

            if (rowText.equals(parent)) {

                String rowTextChild = driver.findElement(By.xpath("//div[contains(@class,'windowViewMode-normal')]/div/div[2]/div[2]/div[3]/div/div/table/tbody/tr[" + (i + 1) + "]/th/div/div/a")).getText();
                System.out.println("rowTextChild >>>>> ?  " + rowTextChild);

                String levelChild = driver.findElement(By.xpath("//div[contains(@class,'windowViewMode-normal')]/div/div[2]/div[2]/div[3]/div/div/table/tbody/tr[" + (i + 1) + "]")).getAttribute("aria-level");
                System.out.println("levelChild  >>> ??? " + levelChild);

                if (rowTextChild.equals(child)) {

                    int parentLevel = Integer.parseInt(level);
                    int childLevel = Integer.parseInt(levelChild);

                    Assert.assertTrue(parentLevel < childLevel, "No parent-child relation between these accounts");
                }
            }
        }
    }
}