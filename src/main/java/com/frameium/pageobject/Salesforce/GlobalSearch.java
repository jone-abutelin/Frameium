package com.frameium.pageobject.Salesforce;

import com.frameium.genericfunctions.GenericFunctions;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import java.util.List;

/**
 * This class represents the global search for contact functionality in Salesforce. (for contact)
 */
public class GlobalSearch extends GenericFunctions {
    private WebDriver driver;

    // Locators for various elements on the lead page

    private By searchbar = By.xpath("//button[@aria-label='Search']");
    private By contact = By.xpath("(//div[@class='testonly-outputNameWithHierarchyIcon slds-grid sfaOutputNameWithHierarchyIcon'])[3]");

    /**
     * Constructor for GlobalSearch.
     *
     * @param driver The WebDriver instance to interact with the browser.
     */
    public GlobalSearch(WebDriver driver) {

        this.driver = driver;
    }

    /**
     * Enters a search term into the global search bar.
     *
     * @param contact The search term to enter into the search bar.
     * @throws InterruptedException If the thread is interrupted while sleeping.
     */
    public void enterSearchBar(String contact) throws InterruptedException {
        Thread.sleep(4000);
        //clickElement(searchbar);
        clickElement(driver.findElement(By.xpath("//button[@aria-label='Search']")));
        Thread.sleep(4000);
        driver.findElement(By.xpath("//input[contains(@class, 'slds-input') and ((contains(@placeholder, 'Search Leads and more...') or contains(@placeholder, 'Search...')) and (contains(@type, 'search')) and (contains(@part, 'input')))]")).click();
        driver.findElement(By.xpath("//input[contains(@class, 'slds-input') and ((contains(@placeholder, 'Search Leads and more...') or contains(@placeholder, 'Search...')) and (contains(@type, 'search')) and (contains(@part, 'input')))]")).sendKeys(contact);
        Thread.sleep(4000);
    }

    /**
     * Verifies search results and clicks on the matching result based on contact name and role.
     *
     * @param contactName The name of the contact to match in the search results.
     * @param role        The role to match in the search results.
     */
    public void verifySearchResutlsAndClick(String contactName, String role) {

        List<WebElement> searchResults = driver.findElements(By.xpath("//div[contains(@id,'suggestionsList')]/search_dialog-instant-result-item/div/div[2]"));

        for (WebElement result : searchResults) {

            String nameDiv = result.findElement(By.xpath("span")).getText();
            System.out.println("nameDiv "+ nameDiv);

            if(nameDiv.equals(contactName)){

                String roleDiv = result.findElement(By.xpath("div")).getText();
                System.out.println("roleDiv "+ roleDiv);

                if(roleDiv.contains(role)){

                    clickElement(result.findElement(By.xpath("div")));

                    System.out.println( );
                    break;
                }
            }
        }
    }
}