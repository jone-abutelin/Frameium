package com.frameium.pageobject.Tamm_Abudhabi;

import com.frameium.genericfunctions.GenericFunctions;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class ChatWithChatgptPage  extends GenericFunctions {
    public ChatWithChatgptPage(WebDriver driver) {
        this.driver = driver;
    }

    private By support = By.xpath("//h5[text()='Support']");
    private By tammchatbot = By.xpath("//h5[text()='TAMM Chatbot']");
    private By submitandtrack = By.xpath("//button[text()='Submit and track your case']");
    private By submitcase = By.xpath("//button[text()='Submit a case']");
    private By FirstName = By.xpath("//input[@id='FirstNameVal']");
    private By LastName = By.xpath("//input[@id='LastNameVal']");
    private By EmailId = By.xpath("//input[@id='EmailVal']");
    private By casedescrption = By.xpath("//textarea[@id='CaseDescVal']");
    private By Submit = By.xpath("//input[@type='submit']");
    private By confirm = By.xpath("//button[text()='Confirm and send']");
    private By help = By.xpath("//button[text()='No']");
    private By cookieTrayButtonLocator = By.xpath("//button[@aria-label='Accept']");////.ui-lib-cookie-tray button
    private By otherButtonLocator = By.cssSelector(".ui-lib-button");
    private By CloseConfirmation = By.xpath("//div[@class='bot-header__close-btn']");
    private By yesConfirmationButton = By.xpath("//a[text()='Yes']");

    private By homePageTextLocator = By.xpath("//div[text()='How Can We Help You?']");


    public void support() {
        closeCookieTray();
        WebElement suppor = findElement(support);
        scrollIntoView(suppor);
        clickElement(suppor);
    }

    public void Tammchatgpt() {
        WebElement Tammchatbot = findElement(tammchatbot);
        scrollIntoView(Tammchatbot);
        clickElement(Tammchatbot);
    }

    public void submiTrack() {
        WebElement submittrac = findElement(submitandtrack);
        scrollIntoView(submittrac);
        clickElement(submittrac);
    }

    public void submitcase() {
        WebElement submicase = findElement(submitcase);
        scrollIntoView(submicase);
        clickElement(submicase);
    }

    public void FirstName() {
        WebElement Fname = findElement(FirstName);
        scrollIntoView(Fname);
        Fname.sendKeys("TammAbudhabi");

    }

    public void Lastname() {
        WebElement lname = findElement(LastName);
        scrollIntoView(lname);
        lname.sendKeys("Saudi");
    }

    public void Email() {
        WebElement Email = findElement(EmailId);
        scrollIntoView(Email);
        Email.sendKeys("abc@gmail.com");
    }

    public void casedescription() {
        WebElement casedescriptio = findElement(casedescrption);
        scrollIntoView(casedescriptio);
        casedescriptio.sendKeys("Find Soon as possible");
    }

    public void submit() {
        WebElement submi = findElement(Submit);
        scrollIntoView(submi);
        clickElement(submi);
    }

    public void confirm() {
        WebElement confir = findElement(confirm);
        scrollIntoView(confir);
        clickElement(confir);
    }

    public void help() {

        WebElement help1 = findElement(help);
        scrollIntoView(help1);
        clickElement(help1);
    }
    public void yesConfirmationButton() {

        WebElement yesConfirm = findElement(yesConfirmationButton);
        scrollIntoView(yesConfirm);
        clickElement(yesConfirm);
    }

    public void CloseConfirms() {
        WebElement closeConfirmation = findElement(CloseConfirmation);
        scrollIntoView(closeConfirmation);
        //clickElement(closeConfirmation);
        clickElementUsingJavaScript(driver.findElement(CloseConfirmation));
    }
    public void closeCookieTray() {
        try {

            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(60));
            clickElementUsingJavaScript(driver.findElement(cookieTrayButtonLocator));
            clickElementUsingJavaScript(driver.findElement(otherButtonLocator));

        } catch (Exception e) {
            //  e.printStackTrace();
        }
    }
    public boolean isHomePageTextDisplayed() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(1000));
        try {
            WebElement homepageText = wait.until(ExpectedConditions.visibilityOfElementLocated(homePageTextLocator));
            scrollIntoView(homepageText);
            return homepageText.isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

}