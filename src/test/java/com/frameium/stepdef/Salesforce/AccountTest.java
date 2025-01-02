package com.frameium.stepdef.Salesforce;

import com.frameium.genericfunctions.GenericFunctions;
import com.frameium.pageobject.Salesforce.Account;
import com.frameium.pageobject.Salesforce.Lead;
import com.frameium.stepdef.Hooks;
import com.frameium.stepdef.TestSetUp;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;

import java.net.MalformedURLException;

import static com.frameium.genericfunctions.GenericFunctions.driver;

/**
 * Represents the step definitions for Account-related test scenarios.
 */
public class AccountTest {

    private Account acnt;
    public String exptdTitle = "GetCloudy Pvt LTD | Account | Salesforce";
    Hooks hooks = new Hooks();
    TestSetUp setUp;
    private GenericFunctions genericFunctions;
    private Lead lead;
    private WebDriver driver;

    /**
     * Constructs an AccountTest instance and initializes necessary objects.
     *
     * @param setUp The TestSetUp instance for initializing the test environment.
     * @throws MalformedURLException If a malformed URL is encountered.
     */
    public AccountTest(TestSetUp setUp) throws MalformedURLException {
        this.setUp = setUp;
        genericFunctions = new GenericFunctions(setUp.baseTest.driver);
        lead=new Lead(setUp.baseTest.driver);
        acnt=new Account(setUp.baseTest.driver);
        // Assign the driver from setUp to the local driver variable
        this.driver = setUp.baseTest.driver;

    }

    /**
     * Step definition for navigating to the sales page.
     */

    @Given("I am on the page of sales")
    public void i_go_to_the_page() {
    }

    /**
     * Step definition for navigating to the accounts page and creating a new account.
     *
     * @param accountname The name of the account to be created.
     * @throws InterruptedException If the thread is interrupted while sleeping.
     */

    @And("I go to the accounts page and create new account with name {string}")
    public void i_go_to_the_accounts_page(String accountname) throws InterruptedException {
        acnt.accountsBtnClick();
        System.out.println("New account creation");
        acnt.newAccountCreation(accountname);
       // hooks.takeScreenshot(hooks.scenario);

    }

    /**
     * Step definition for verifying that the created account is displayed.
     *
     * @throws InterruptedException If the thread is interrupted while sleeping.
     */

    @Then("The created account should be shown")
    public void the_created_account_should_be_shown() throws InterruptedException {
        Thread.sleep(3000);
        String actlTitle = driver.getTitle();
        System.out.println("the title is **************" + actlTitle);
        Assert.assertEquals(actlTitle,exptdTitle,"Account not created");
       // hooks.takeScreenshot(hooks.scenario);

    }

}
