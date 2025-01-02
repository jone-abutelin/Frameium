package com.frameium.stepdef.Salesforce;

import com.frameium.genericfunctions.GenericFunctions;
import com.frameium.pageobject.Salesforce.Account;
import com.frameium.pageobject.Salesforce.Lead;
import com.frameium.pageobject.Salesforce.ListView;
import com.frameium.stepdef.Hooks;
import com.frameium.stepdef.TestSetUp;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;

import java.net.MalformedURLException;
import java.util.List;

import static org.testng.FileAssert.fail;

/**
 * This class contains step definitions for ListView related scenarios in Salesforce.
 */

public class ListViewTest extends GenericFunctions {
    private ListView listview;
    Hooks hooks = new Hooks();
    TestSetUp setUp;
    private GenericFunctions genericFunctions;
    private WebDriver driver;

    /**
     * Constructor to initialize the test setup, driver, and page objects.
     *
     * @param setUp the TestSetUp object containing the base test setup
     * @throws MalformedURLException if there is a URL error
     */

    public ListViewTest(TestSetUp setUp) throws MalformedURLException {
        this.setUp = setUp;
        genericFunctions = new GenericFunctions(setUp.baseTest.driver);
        listview=new ListView(setUp.baseTest.driver);
        // Assign the driver from setUp to the local driver variable
        this.driver = setUp.baseTest.driver;

    }

    /**
     * Navigates to the opportunities page.
     */
    @Then("I will go to the opportunity page")
    public void i_will_go_to_the_opportunity_page() {
        listview.goToOpportunities();
    }

    /**
     * Creates a new list view with the given name and adds a new filter.
     *
     * @param listviewname the name of the new list view
     * @throws InterruptedException if interrupted during sleep
     */

    @And("I will create a new listview with {string} and new filter")
    public void i_will_create_a_new_listview_with_and_new_filter(String listviewname) throws InterruptedException {
        listview.createListView(listviewname);
        hooks.takeScreenshot(hooks.scenario);
        listview.createFilter();
        listview.clickSave();
    }

    /**
     * Verifies that the created list view contains the specified opportunity.
     *
     * @param opportunityName the name of the opportunity to verify
     * @throws InterruptedException if interrupted during sleep
     */

    @Then("Verify that the created listview contains the opportunity {string}")
    public void verify_that_the_created_listview_contains_the_opportunity(String opportunityName) throws InterruptedException {
        Thread.sleep(2000);
        try {
            boolean isOpportunityPresent = listview.opportunityVerify(opportunityName);
            if (isOpportunityPresent) {
                logToExtentReport("Pass", "Opportunity Name '" + opportunityName + "' is found in the list view.");
            } else {
                logToExtentReport("Fail", "Opportunity Name '" + opportunityName + "' is not found in the list view.");
                Assert.fail("Opportunity Name '" + opportunityName + "' is not found in the list view.");
            }
        } catch (Exception e) {
            e.printStackTrace();
            logToExtentReport("Fail", "An exception occurred during verification: " + e.getMessage());
            Assert.fail("An exception occurred during verification: " + e.getMessage());

        }
        hooks.takeScreenshot(hooks.scenario);
    }
}