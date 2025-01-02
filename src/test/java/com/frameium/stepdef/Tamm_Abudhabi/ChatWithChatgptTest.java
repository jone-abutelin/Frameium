package com.frameium.stepdef.Tamm_Abudhabi;
import com.frameium.genericfunctions.GenericFunctions;
import com.frameium.pageobject.Tamm_Abudhabi.ChatWithChatgptPage;
import com.frameium.stepdef.Hooks;
import com.frameium.stepdef.TestSetUp;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import org.openqa.selenium.WebDriver;

import java.net.MalformedURLException;

import static org.junit.Assert.assertTrue;

public class ChatWithChatgptTest extends GenericFunctions {
    Hooks hooks = new Hooks();
    TestSetUp setUp;
    private GenericFunctions genericFunctions;
    private  ChatWithChatgptPage  Chatwithchatgptpage;
    private WebDriver driver;

    public ChatWithChatgptTest(TestSetUp setUp) throws MalformedURLException {
        this.setUp = setUp;
        genericFunctions = new GenericFunctions(setUp.baseTest.driver);
        Chatwithchatgptpage = new ChatWithChatgptPage(setUp.baseTest.driver);
        // Assign the driver from setUp to the local driver variable
        this.driver = setUp.baseTest.driver;
    }
    @Then("click on the support option")
    public void clickOnTheSupportOption() {
        Chatwithchatgptpage.support();
    }

    @Then("click on the tammchatbot option")
    public void clickOnTheTammchatbotOption() {
        Chatwithchatgptpage.Tammchatgpt();
    }

    @Then("choose the submit and track your case")
    public void chooseTheSubmitAndTrackYourCase() {
        Chatwithchatgptpage.submiTrack();
        Chatwithchatgptpage.submitcase();
    }

    @Then("click the submit case and fill the Application form")
    public void clickTheSubmitCaseAndFillTheApplicationForm() {
        Chatwithchatgptpage.FirstName();
        Chatwithchatgptpage.Lastname();
        Chatwithchatgptpage.Email();
        Chatwithchatgptpage.casedescription();
    }

    @And("click the submit option")
    public void clickTheSubmitOption() {
        Chatwithchatgptpage.submit();
    }
    @And("click Confirm and send")
    public void clickConfirmAndSend() throws InterruptedException {
        Chatwithchatgptpage.confirm();
        Chatwithchatgptpage.help();
        Chatwithchatgptpage.CloseConfirms();
    }


    @And("click the Yes button to exit the chat.")
    public void clickTheYesButtonToExitTheChat() {
        Chatwithchatgptpage.yesConfirmationButton();
    }

    @Then("user navigates to the Home page and see the heading {string}")
    public void userNavigatesToTheHomePageAndSeeTheHeadingHowCanWeHelpYou(String expectedText) {
        boolean isTextDisplayed = Chatwithchatgptpage.isHomePageTextDisplayed();
        assertTrue("Expected text '" + expectedText + "' is not displayed.", isTextDisplayed);
    }
}
