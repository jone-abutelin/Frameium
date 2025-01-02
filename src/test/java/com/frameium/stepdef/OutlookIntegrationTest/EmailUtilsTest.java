package com.frameium.stepdef.OutlookIntegrationTest;

import com.frameium.genericfunctions.GenericFunctions;
import com.frameium.stepdef.TestSetUp;
import com.frameium.utilities.OutlookIntegration.EmailUtils;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import org.openqa.selenium.WebDriver;
import java.net.MalformedURLException;

/**
 * This class contains step definitions for testing email functionalities
 * using Cucumber and Selenium WebDriver.
 */
public class EmailUtilsTest {

    private EmailUtils emailUtils;

    private WebDriver driver;
    TestSetUp setUp;
    private GenericFunctions genericFunctions;

    /**
     * Constructor to initialize the test class with required dependencies.
     *
     * @param setUp The TestSetUp instance used to initialize the test environment.
     */
    public EmailUtilsTest(TestSetUp setUp) throws MalformedURLException, InterruptedException {
        this.setUp = setUp;
        genericFunctions = new GenericFunctions(setUp.baseTest.driver);
        emailUtils = new EmailUtils(setUp.baseTest.driver);
        this.driver = setUp.baseTest.driver;
    }

    /**
     * Navigates to the Inbox from the Home page.
     */
    @Given("I navigate to Inbox from the Home page")
    public void i_navigate_to_inbox_from_the_home_page() throws InterruptedException {
        emailUtils = new EmailUtils(driver);
    }

    /**
     * Fetches the latest email in the Inbox, opens the mail with a specific subject,
     * and downloads its attachment.
     * @param emailSubject The subject of the email to download the attachment from.
     */
    @Then("I fetch latest email in Inbox and open mail with {string} and download attachment")
    public void i_fetch_latest_email_in_inbox_and_open_mail_with_and_download_attachment(String emailSubject) throws InterruptedException {
        emailUtils.setNavigateToInbox();
        emailUtils.getLatestEmail();
        emailUtils.getAttachment(emailSubject);
    }

    /**
     * Fetches emails in the Inbox with a specific subject, opens a specific email,
     * and verifies the status in the email body.
     * @param subject The subject to search for in the Inbox.
     * @param mailSearch The specific email to open based on the search.
     */
    @Then("I fetch Emails in Inbox with {string} and open mail with {string}")
        public void i_fetch_emails_in_inbox_with_and_open_mail_with(String subject, String mailSearch) throws InterruptedException {
            emailUtils.enterSubject(subject);
            emailUtils.searchSubject(subject);
            emailUtils.openMail(mailSearch);
            String messageBody = emailUtils.getMessageBody();
            if (messageBody.contains("Status : Approved")) {
                System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>>Status Verified");
            }
            emailUtils.deleteMail();
            emailUtils.clickConfirmDelete();
        }
    }
