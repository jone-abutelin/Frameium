package com.frameium.stepdef.Tamm_Abudhabi;
import com.frameium.genericfunctions.GenericFunctions;
import com.frameium.pageobject.Tamm_Abudhabi.ServicePage;
import com.frameium.stepdef.Hooks;
import com.frameium.stepdef.TestSetUp;
import io.cucumber.java.en.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.ExpectedConditions;
import java.net.MalformedURLException;
import java.time.Duration;

import static org.junit.Assert.assertTrue;


public class ServiceTest extends GenericFunctions{
    Hooks hooks = new Hooks();

    TestSetUp setUp;
    private GenericFunctions genericFunctions;
    private ServicePage servicePage;
    private WebDriver driver;

    public ServiceTest(TestSetUp setUp) throws MalformedURLException {
        this.setUp = setUp;
        genericFunctions = new GenericFunctions(setUp.baseTest.driver);
        servicePage = new ServicePage(setUp.baseTest.driver);
        // Assign the driver from setUp to the local driver variable
        this.driver = setUp.baseTest.driver;

    }
    @When("user click Services button")
    public void user_click_services_button() {
       servicePage.clickService();
    }
    @When("user click Agriculture and livestock")
    public void user_click_agriculture_and_livestock() {
        servicePage.clickAgricutlure();
    }


    @Then("the user should see the text {string}")
    public void the_user_should_see_the_text(String expectedText) {
        boolean isTextDisplayed = servicePage.isAgricultureTextDisplayed();
        assertTrue("Expected text '" + expectedText + "' is not displayed.", isTextDisplayed);
    }

    @When("user click Work & Education button")
    public void userClickWorkEducationButton() {
        servicePage.clickWorkEducation();
    }

    @When("user click Type button from Work & Education")
    public void userClickTypeButtonFromWorkEducation() {
        servicePage.clickType();
    }

    @When("user selects Emirati from the checkbox")
    public void userSelectsEmiratiFromTheCheckbox() {
        servicePage.clickEmirati();

    }



    @When("user clicks the Calculate Previous Service Amount button")
    public void userClicksTheCalculatePreviousServiceAmountButton() {
        servicePage.clickCalculatePreviousServiceAmount();
    }

    @Then("the user should be successfully navigated to the {string} page")
    public void theUserShouldBeSuccessfullyNavigatedToThePage(String  expectedText) {
        boolean isTextDisplayed = servicePage.iscalculatePreviousServiceAmountTextDisplayed();
        assertTrue("Expected text '" + expectedText + "' is not displayed.", isTextDisplayed);
    }

    @When("user click popup button")
    public void userClickPopupButton() {
        servicePage.clickPopup();
    }

    @When("user click dropdown button")
    public void userClickDropdownButton() {
        servicePage.clickDropdownicon();
    }



    @When("user Selects  Add Period one by one from the options")
    public void userSelectsAddPeriodOneByOneFromTheOptions() {
        servicePage.clickAddPeriodOnebyOne();
    }

    @When("user selects the date {string} in the form")
    public void user_selects_the_date_in_the_form(String fromDate) {
        servicePage.selectFromDate(fromDate);
    }

    @When("user selects the  To date {string} in the form")
    public void userSelectsTheToDateInTheForm(String ToDate) {

        servicePage.selectToDate(ToDate);
    }

    @When("user click Add button")
    public void userClickAddButton() {
        servicePage.clickAddButton();
    }

    @When("user click Amount button")
    public void userClickAmountButton() {
        servicePage.clickAmount();
    }

    @When("user enters an {string}")
    public void userEntersAn(String amount) {
        servicePage.enterAmount(amount);
    }

    @When("user click Calculate button")
    public void userClickCalculateButton() {
        servicePage.clickCalculate();
    }

    @When("User rates their effort with smiley number {string}")
    public void userRatesTheirEffortWithSmileyNumber(String smileyNumber) {
        servicePage.clickOnSmileyIcon1(smileyNumber);
    }

    @When("User rates their satisfaction with smiley number {string}")
    public void userRatesTheirSatisfactionWithSmileyNumber(String smileyNumber) {
        servicePage.clickOnSmileyIcon2(smileyNumber);
    }

    @When("user click next button")
    public void userClickNextButton() {
        servicePage.clickNextButton();
    }

    @When("User provides feedback reason {string}")
    public void userProvidesFeedbackReason(String reason) {
        servicePage.selectFeedbackReason(reason);
    }

    @Then("the user should add an additional comment about their experience {string}")
    public void theUserShouldAddAnAdditionalCommentAboutTheirExperience(String comment) {
        servicePage.provideFeedbackReason(comment);
    }

    @When("user click confirm button")
    public void userClickConfirmButton() {
        servicePage.clickConfirmButton();
    }

    @Then("the user should successfully see the title {string}")
    public void theUserShouldSuccessfullySeeTheTitleThankYouForYourFeedback(String expectedText) {
        boolean isTextDisplayed = servicePage.isThankyouTextDisplayed();
        assertTrue("Expected text '" + expectedText + "' is not displayed.", isTextDisplayed);
    }

    @When("user click My Tamm button")
    public void userClickMyTammButton() {
        servicePage.clickMyTamm();
    }

    @Then("the user should be successfully navigated to the home page and the URL should be {string}")
    public void theUserShouldBeSuccessfullyNavigatedToTheHomePageAndTheURLShouldBe(String expectedUrlStart) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(60));
        wait.until(ExpectedConditions.urlMatches(".*" + expectedUrlStart + ".*"));
        // Get the current URL
        String currentUrl = driver.getCurrentUrl();

        // Assert that the current URL starts with the expected URL start
        if (currentUrl.startsWith(expectedUrlStart)) {
            System.out.println("Successfully navigated to the home page with URL starting with: " + expectedUrlStart);
        } else {
            System.out.println("Expected URL starting with: " + expectedUrlStart + " but found: " + currentUrl);
            // You might throw an assertion error or handle it based on your testing framework
        }
    }


    @Then("click the pension calculator Option")
    public void clickThePensionCalculatorOption() {
        servicePage.clickPensionCalculator();
    }
    @When("user is on the pension calculator page{string}")
    public void userIsOnThePensionCalculatorPage(String text) {
        servicePage.isPensionTextDisplayed(text);
    }
    @Then("user select the {string}")
    public void userSelectThe(String dateofbirth) {
        servicePage.selectDateOfBirth( dateofbirth);
    }
    @Then("User Select the gender")
    public void userSelectTheGender() {
        servicePage.gender();
    }
    @Then("user selects the service period")
    public void userSelectsTheServicePeriod() {
        servicePage.years();
        servicePage.months();
        servicePage.Days();
    }

    @Then("user enter the  last pension salary {string}")
    public void userEnterTheLastPensionSalary(String salary) throws InterruptedException {
       // Thread.sleep(1000);
        servicePage.enterpensionsalary(salary);
    }

    @Then("user enter the  Average pension salary {string}")
    public void userEnterTheAveragePensionSalary(String salary) throws InterruptedException {
        Thread.sleep(1000);
       servicePage.enteravgsalary(salary);
    }
    @Then("choose the business option")
    public void chooseTheBusinessOption() {
        servicePage.Business();
    }
    @Then("select the start business option")
    public void selectTheStartBusinessOption() {
        servicePage.StartBusiness();
    }
    @Then("select start a business option")
    public void selectStartABusinessOption() {
        servicePage.StartaBusiness();
    }
    @When("select start new journey with existing licence")
    public void selectStartNewJourneyWithExistingLicence() {
        servicePage.StartJourney();
    }
    @When("select commercial option")
    public void selectCommercialOption() {
        servicePage.Commercial();
    }
    @And("click the proceed option")
    public void clickTheProceedOption()  {
        servicePage.ProceedButton();
    }
    @Then("click on comparison tool")
    public void clickOnComparisonTool() {
        servicePage.ComparisonTool();
    }
    @Then("choose licence option")
    public void chooseLicenceOption() throws InterruptedException {
        //  Thread.sleep(3000);
        String childWindow = "Comparison Tool - Select Forms";
        System.out.println(driver.getTitle());
        switchToWindowByTitle(childWindow);
        System.out.println(driver.getTitle());
        servicePage.LicenceTex1();
    }
    @Then("select the four types of licences")
    public void selectTheFourTypesOfLicences() {
        servicePage.selectLicenseCheckBox("Dual Licence");
    }
    @And("click the compare option")
    public void clickTheCompareOption(){
        servicePage.Compar();
    }


}