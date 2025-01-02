package com.frameium.stepdef.Salesforce;

import com.frameium.genericfunctions.GenericFunctions;
import com.frameium.pageobject.Salesforce.GlobalSearch;
import com.frameium.pageobject.Salesforce.Lead;
import com.frameium.stepdef.Hooks;
import com.frameium.stepdef.TestSetUp;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;

import java.net.MalformedURLException;

/**
 * Represents the step definitions for Global Search-related test scenarios in Salesforce.
 */
public class GlobalSearchTest{
    private GlobalSearch globalsearch;
    Hooks hooks = new Hooks();
    private WebDriver driver;

    TestSetUp setUp;

    private GenericFunctions genericFunctions;

    /**
     * Constructs a GlobalSearchTest instance and initializes necessary objects.
     *
     * @param setUp The TestSetUp instance for initializing the test environment.
     * @throws MalformedURLException If a malformed URL is encountered.
     */
    public GlobalSearchTest(TestSetUp setUp) throws MalformedURLException {

        this.setUp = setUp;

        genericFunctions = new GenericFunctions(setUp.baseTest.driver);

        lead=new Lead(setUp.baseTest.driver);

        // Assign the driver from setUp to the local driver variable

        this.driver = setUp.baseTest.driver;

    }
    private Lead lead;
    private String exptdContact = "John Thomas | Contact | Salesforce";
    private String exptdaccount = "GetCloudy Logistics | Account | Salesforce";
    private String exptdactlopportunity ="GetCloudy Logistics- | Opportunity | Salesforce";


    /**
     * Step definition for navigating to the Salesforce leads page.
     *
     * @throws InterruptedException If the thread is interrupted while sleeping.
     */

    @And("I am on the salesforce leads page")
    public void i_am_on_the_salesforce_leads_page() throws InterruptedException {
        lead.appLauncherSearch();
    }

    /**
     * Step definition for navigating to the sales leads page.
     *
     * @throws InterruptedException If the thread is interrupted while sleeping.
     */

    @And("I am on the sales leads page")
    public void i_am_on_the_sales_leads_page() throws InterruptedException {
        lead.appLauncherSearch();
        lead.leadslogin();
    }

    /**
     * Step definition for navigating to the leads page of Salesforce.
     *
     * @throws InterruptedException If the thread is interrupted while sleeping.
     */

    @Given("I am on the leads page of salesforce")
    public void i_am_on_the_leads_page_salesforce() throws InterruptedException {
        lead.appLauncherSearch();
        lead.leadslogin();
    }

    /**
     * Step definition for navigating to the leads page of sales.
     *
     * @throws InterruptedException If the thread is interrupted while sleeping.
     */

    @Given("I am on the leads page of sales")
    public void i_am_on_the_leads_page() throws InterruptedException {
        lead.leadslogin();
    }

    /**
     * Step definition for searching the contact name on the search bar.
     *
     * @param contactname The name of the contact to search for.
     * @throws InterruptedException If the thread is interrupted while sleeping.
     */

    @When("I search the contact name {string} on the search bar")
    public void i_search_the_contact_name_on_the_search_bar(String contactname) throws InterruptedException {
        globalsearch.enterSearchBar(contactname);
        hooks.takeScreenshot(hooks.scenario);

    }

    /**
     * Step definition for verifying if the user is present in the search results and clicking the result.
     *
     * @param contactName The name of the contact to verify and click.
     * @param role        The role of the contact.
     */

    @Then("verify user present on the search results and click {string} and Role {string}")
    public void verifyUserPresentOnTheSearchResultsAndClickAndRole(String contactName, String role) {
        globalsearch.verifySearchResutlsAndClick(contactName,role);
    }

    @Then("I should verify the contact name")
    public void i_should_verify_the_contact_name() throws InterruptedException {
        Thread.sleep(3000);
        String actlcontact = driver.getTitle();
        System.out.println(actlcontact);
        try {
            Assert.assertEquals(actlcontact, exptdContact, "Contact name does not match");
            genericFunctions.logToExtentReport("Pass", "Contact name matches expected value: " + exptdContact);
        } catch (AssertionError e) {
            genericFunctions.logToExtentReport("Fail", "Contact name does not match expected value: " + exptdContact);
            throw e;
        }        hooks.takeScreenshot(hooks.scenario);

    }

    /**
     * Step definition for verifying the contact name in the search results.
     *
     * @throws InterruptedException If the thread is interrupted while sleeping.
     */
    @Then("I should verify the account name")
    public void i_should_verify_the_account_name() throws InterruptedException {
        Thread.sleep(3000);
        String actlaccount = driver.getTitle();
        //   System.out.println(actlaccount);
        try {
            Assert.assertEquals(actlaccount, exptdaccount, "Account name does not match");
            genericFunctions.logToExtentReport("Pass", "Account name matches expected value: " + exptdaccount);
        } catch (AssertionError e) {
            genericFunctions.logToExtentReport("Fail", "Account name does not match expected value: " + exptdaccount);
            throw e;
        }
        hooks.takeScreenshot(hooks.scenario);

    }

    /**
     * Step definition for verifying the account name in the search results.
     *
     * @throws InterruptedException If the thread is interrupted while sleeping.
     */

    @Then("I should verify the opportunity name")
    public void i_should_verify_the_opportunity_name() throws InterruptedException {
        Thread.sleep(3000);
        String actlopportunity = driver.getTitle();
        System.out.println(actlopportunity);
        try {
            Assert.assertEquals(actlopportunity, exptdactlopportunity, "Opportunity name does not match");
            genericFunctions.logToExtentReport("Pass", "Opportunity name matches expected value: " + exptdactlopportunity);
        } catch (AssertionError e) {
            genericFunctions.logToExtentReport("Fail", "Opportunity name does not match expected value: " + exptdactlopportunity);
            throw e;
        }
        hooks.takeScreenshot(hooks.scenario);

    }

}