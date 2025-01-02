package com.frameium.utilities.OutlookIntegration;

import com.frameium.genericfunctions.GenericFunctions;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

/**
 * This class contains methods for interacting with the email system in Outlook.
 */
public class EmailUtils extends GenericFunctions {

    // Locators for various elements in the email interface
    private By navigateToInbox = By.xpath("//span[text()='Inbox']");
    private By searchBox = By.xpath("//input[@id='topSearchInput']");
    private By getMessage = By.xpath("//div[@aria-label='Message body']");
    private By mailList = By.xpath("//div[@class='yMBVD']//div/i/span/i/following::div[contains(@class,'NxTZN')]/parent::*");
    private By allEmails = By.xpath("//div[@class='jGG6V']");
    private By filterButton = By.xpath("//button[@id='mailListFilterMenu']");
    private By hasFiles = By.cssSelector("div[title='Has files']");
    private By moreActionBtn = By.xpath("(//button[@aria-label='More actions'])[2]");
    private By downloadBtn = By.xpath("//button[@name='Download']");
    private By deleteBtn = By.xpath("//div[@title='Delete']");
    private By confirmDelete = By.xpath("//span[text()='OK']");

    /**
     * Constructor to initialize the WebDriver.
     *
     * @param driver The WebDriver instance used for interacting with the web page.
     */
    public EmailUtils(WebDriver driver) throws InterruptedException {
        driver = this.driver;
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    /**
     * Clicks the 'Inbox' button to navigate to the Inbox page.
     */
    public void setNavigateToInbox() {
        clickElement(navigateToInbox);
    }

    /**
     * Retrieves and prints the latest email from the list of all emails.
     */
    public void getLatestEmail()
    {
        List<WebElement> emails = driver.findElements(allEmails);

        // Get the latest email
        if (emails.size() > 0) {
            WebElement latestEmail = emails.get(0);
            System.out.println("Latest Email Subject: " + latestEmail.getText());
        } else {
            System.out.println("No emails found in the inbox.");
        }
    }

    /**
     * Sets the subject to search for in the email inbox.
     *
     * @param subject The subject to search for.
     */
    public void enterSubject(String subject) throws InterruptedException {
        Subject(subject);
    }

    /**
     * Enters the subject into the search box.
     *
     * @param subject The subject to be entered.
     */
    public void Subject(String subject) {
        inputSubject(subject);
    }

    /**
     * Searches for emails with a specific subject and clicks on the matching email.
     *
     * @param subject The subject to search for.
     */
    public void searchSubject(String subject) throws InterruptedException {
       WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(10));
       wait.until(ExpectedConditions.presenceOfElementLocated(mailList));

        List<WebElement> searchResults = driver.findElements(mailList);
        for (WebElement result : searchResults) {

            // Clean up the text for comparison
            // Remove specific Unicode character \uE91A
            // Remove any characters that are not alphanumeric or spaces
            String cleanedString = result.getText().replaceAll("\uE91A", "")
                    .replaceAll("[^a-zA-Z0-9\\s]", "")
                    .replaceAll("\n"," ")
                    .trim();
            if (cleanedString.equalsIgnoreCase(subject)) {
                result.click();
                break;
            }
        }
    }

    /**
     * Opens an email based on the provided search term.
     *
     * @param mailSearch The search term to locate the email.
     */
    public void openMail(String mailSearch) {
        By clickMail = By.xpath("//span[text()='" + mailSearch + "']");
        clickElement(clickMail);
    }

    /**
     * Retrieves and prints the message body of the currently opened email.
     *
     * @return The text of the email's message body.
     */
    public String getMessageBody()  {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.presenceOfElementLocated(getMessage));
        String message = driver.findElement(getMessage).getText();
        System.out.println(">>>>>>>>>>>>>>>>>>>>>>>Message Body:\n" + message);
        return message;

    }

    /**
     * Downloads the attachment of an email with a specific subject.
     *
     * @param emailSubject The subject of the email whose attachment is to be downloaded.
     */
    public void getAttachment(String emailSubject) {
        clickElement(filterButton);

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOfElementLocated(hasFiles));
        clickElement(hasFiles);


        wait.until(ExpectedConditions.visibilityOfElementLocated(allEmails));

        List<WebElement> searchResults1 = driver.findElements(allEmails);
        for (WebElement result1 : searchResults1) {

            String resultString = result1.getText();
            System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>*************" + resultString);

            if (resultString.contains(emailSubject)) {
                result1.click();
                break;
            }
        }

        clickElement(moreActionBtn);

        WebDriverWait wait1 = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait1.until(ExpectedConditions.visibilityOfElementLocated(downloadBtn));
        clickElement(downloadBtn);
    }

    /**
     * Deletes the currently selected email.
     */
    public void deleteMail() {
        clickElement(deleteBtn);
    }

    /**
     * Confirms the deletion of an email in the confirmation dialog.
     */
    public void clickConfirmDelete() {

        try {
            WebDriverWait wait2 = new WebDriverWait(driver, Duration.ofSeconds(10));
            WebElement confirmDeleteButton = wait2.until(ExpectedConditions.visibilityOfElementLocated(confirmDelete));
            confirmDeleteButton.click();
            System.out.println("Clicked the Confirm Delete button.");
        } catch (Exception e) {
            System.out.println("Confirm Delete popup did not appear.");
        }
    }

    /**
     * Inputs the subject into the search box.
     *
     * @param subject The subject to be entered.
     */
    public void inputSubject(String subject)
    {
        enterKeys(searchBox,subject);
    }
}


