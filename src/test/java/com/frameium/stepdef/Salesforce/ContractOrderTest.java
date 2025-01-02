package com.frameium.stepdef.Salesforce;
import com.frameium.genericfunctions.GenericFunctions;
import com.frameium.pageobject.Salesforce.Account;
import com.frameium.pageobject.Salesforce.ContractOrder;

import com.frameium.pageobject.Salesforce.GlobalSearch;
import com.frameium.pageobject.Salesforce.Lead;
import com.frameium.stepdef.Hooks;
import com.frameium.stepdef.TestSetUp;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.net.MalformedURLException;
import java.time.Duration;
import java.util.NoSuchElementException;

/**
 * Represents the step definitions for Contract and Order-related test scenarios.
 */
public class ContractOrderTest extends GenericFunctions {
    private ContractOrder contract;
    public static String contractNum = "";
    public static String orderNum = "";
    private String exptdContractName = "GetCloudy Logistics";
    private String exptdOrderName = "GetCloudy Logistics";
    private String contractClickDate = "//input[@name='StartDate']";
    Hooks hooks = new Hooks();
    TestSetUp setUp;
    private GenericFunctions genericFunctions;
    private WebDriver driver;

    /**
     * Constructs a ContractOrderTest instance and initializes necessary objects.
     *
     * @param setUp The TestSetUp instance for initializing the test environment.
     * @throws MalformedURLException If a malformed URL is encountered.
     */

    public ContractOrderTest(TestSetUp setUp) throws MalformedURLException {
        this.setUp = setUp;
        genericFunctions = new GenericFunctions(setUp.baseTest.driver);
        contract=new ContractOrder(setUp.baseTest.driver);
        // Assign the driver from setUp to the local driver variable
        this.driver = setUp.baseTest.driver;

    }

    /**
     * Step definition for navigating to the quote page.
     */

    @Given("I am on the quote page")
    public void i_am_on_quote_page() {
    }

    /**
     * Step definition for navigating to the contract page and creating a contract.
     *
     * @param accountname  The name of the account associated with the contract.
     * @param date         The start date of the contract.
     * @param contractTerm The term of the contract.
     * @throws InterruptedException If the thread is interrupted while sleeping.
     */

    @When("I go to the contract page and create a contract with {string}, {string} and {string}")
    public void i_go_to_the_contract_page_and_create_a_contract(String accountname, String date, String contractTerm) throws InterruptedException {
        contract.goToContractPage();
        contract.createContract(accountname,date,contractTerm);
        Thread.sleep(2000);
        hooks.takeScreenshot(hooks.scenario);
        contract.contractClickSave();
    }

    /**
     * Step definition for verifying the newly created contract.
     */

    @Then("I should verify the newly created contract")
    public void i_should_verify_the_newly_created_contract() {
        String actlContractName = contract.getActualContractName();
        logToExtentReport("Log", "Expected contract name: " + exptdContractName);
        logToExtentReport("Log", "Actual contract name: " + actlContractName);
        System.out.println("The expected contract name is >>>>> " + exptdContractName);
        System.out.println("The actual contract name is >>>>> " + actlContractName);
        try {
            Assert.assertEquals(actlContractName, exptdContractName, "Contract is not created");
            logToExtentReport("Pass", "Contract name verification successful. Expected and actual names match.");
        } catch (AssertionError e) {
            logToExtentReport("Fail", "Contract name verification failed. Expected: " + exptdContractName + ", but found: " + actlContractName);
            throw e;
        }
        contractNum = contract.getContractNumber();
        logToExtentReport("Log", "Contract number: " + contractNum);
        System.out.println("The contract num is >>>>>  " + contractNum);
    }

    /**
     * Step definition for navigating to the created contract page.
     */

    @Given("I am on the created contract page")
    public void i_am_on_the_created_contract_page() {
    }

    /**
     * Step definition for creating a new order associated with the contract.
     *
     * @param ordername The name of the order to be created.
     * @param date      The date associated with the order.
     * @throws InterruptedException If the thread is interrupted while sleeping.
     */

    @When("I create a new order with {string}, {string}")
    public void i_create_a_new_order(String ordername, String date) throws InterruptedException {
        Thread.sleep(5000);
        contract.goToOrderrPage();
        contract.createOrder(ordername, date,contractNum);
        hooks.takeScreenshot(hooks.scenario);
        contract.saveOrder();
        orderNum = contract.getOrderNumber();
        logToExtentReport("Log", "Order number: " + orderNum);
        System.out.println("The order num is >>>>>  " + orderNum );

    }

    /**
     * Step definition for verifying that the created order is associated with the contract number.
     *
     * @throws InterruptedException If the thread is interrupted while sleeping.
     */

    @Then("The created order should be verified with the contract number")
    public void theCreatedOrderShouldBeVerified() throws InterruptedException {
        Thread.sleep(1000);
        String actlContractNumber = contract.getActualContractNumber();
        logToExtentReport("Log", "Actual contract number: : " + actlContractNumber);
        logToExtentReport("Log", "Expected contract number: : " + contractNum);
        System.out.println("Contract Number is displayed: UI " + actlContractNumber);
        System.out.println("Contract Number is displayed: Previous " + contractNum);
        try {
            Assert.assertEquals(actlContractNumber, contractNum, "Contract Number is not displayed correctly.");
            logToExtentReport("Pass", "Contract number verification successful. UI contract number matches previous contract number.");
        } catch (AssertionError e) {
            logToExtentReport("Fail", "Contract number verification failed. Expected: " + contractNum + ", but found: " + actlContractNumber);
            throw e;
        }
    }

    /**
     * Step definition for navigating to a particular opportunity and verifying search results.
     *
     * @param contactname The name of the contact to be searched.
     * @param role        The role associated with the contact.
     * @throws InterruptedException If the thread is interrupted while sleeping.
     */

    @And("I will go to the particular opportunity {string} with {string} in the opportunity page")
    public void i_will_go_to_the_particular_opportunity_in_the_opportunity_page(String contactname,String role) throws InterruptedException {
        contract.goToOpportunities();
        contract.enterSearchBar(contactname);
        contract.verifySearchResutlsAndClick(contactname,role);
    }

    /**
     * Step definition for linking the order field with the order number.
     *
     * @throws InterruptedException If the thread is interrupted while sleeping.
     */

    @Then("I should link order field with the number")
    public void i_should_link_order_field_with_the_number() throws InterruptedException {
        try {
            contract.linkOrder(orderNum);
            logToExtentReport("Pass", "Order number '" + orderNum + "' is linked successfully.");
        } catch (Exception e) {
            e.printStackTrace();
            logToExtentReport("Fail", "Failed to link order number '" + orderNum + "': " + e.getMessage());
            Assert.fail("Failed to link order number '" + orderNum + "': " + e.getMessage());
        }
        hooks.takeScreenshot(hooks.scenario);
        contract.saveOrderInDetails();

    }

    //   Scenario: Setting Closed to opportunity stages

    /**
     * Step definition for navigating to the opportunity page.
     */
    @Given("I am on the opportunity page")
    public void i_am_on_the_opportunity_page() {
    }

    /**
     * Step definition for clicking and selecting the closed stage.
     */

    @When("I click and select closed stage")
    public void i_click_on_closed_stage() {
        contract.clickClosed();
        contract.clickSelectClosedStage();
    }

    /**
     * Step definition for changing the stage to the specified stage.
     *
     * @param stage The stage to change to.
     */

    @And("I change the stage to {string}")
    public void i_select_closed_stage_and_change_to_stage_won(String stage) {

        contract.changeStage(stage);
        hooks.takeScreenshot(hooks.scenario);

    }

    /**
     * Step definition for verifying that the stages are closed successfully.
     *
     * @throws InterruptedException If the thread is interrupted while sleeping.
     */

    @Then("the stages should be closed successfully")
    public void the_stages_should_be_closed_successfully() throws InterruptedException {
     contract.saveChangedStage();
    }
}

