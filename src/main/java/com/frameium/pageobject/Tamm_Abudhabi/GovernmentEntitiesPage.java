package com.frameium.pageobject.Tamm_Abudhabi;

import com.frameium.genericfunctions.GenericFunctions;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;

import java.time.Duration;

public class GovernmentEntitiesPage extends GenericFunctions {

    public GovernmentEntitiesPage(WebDriver driver) {
        this.driver = driver;
    }

    By GovernmentLocator = By.xpath("//a[h5[text()='Government Entities']] ");
    By departmentOfHealthLocator =By.xpath("//div[text()='Department of Health']");
    By healthLocator = By.xpath("//div[@class='ui-lib-side-panel__label' and text()='Healthcare (3)']");
    By initialApprovalLocator =By.xpath("//div[contains(@class, 'ui-lib-link-card__title') and text()='Initial Approval for Health Licence']");
    By initialApprovalTextLocator =By.xpath("//div//h1[text()='Initial Approval for Health Licence']");
    By cookieTrayButtonLocator = By.xpath("//button[@aria-label='Accept']");////.ui-lib-cookie-tray button
    By otherButtonLocator = By.cssSelector(".ui-lib-button");
    By typeLocator =By.xpath("//button[@aria-label='button']/div[text()='Type']");
    By pensionFundLocator =By.xpath("//div[text()='Abu Dhabi Pension Fund']");
    By visitorLocator = By.xpath("//span[@class='ui-lib-checkbox__label-text' and text()='Visitor (1)']");
    By increaseCalculatorLocator = By.xpath("//div[@class='ui-lib-link-card__title ui-lib-link-card__title_vertical_card' and text()='Pension Increase Calculator']");
    By increaseCalculatorTextLocator= By.xpath("//div//h1[text()='Pension Increase Calculator']");

    public void GovernmentEntities() {
        closeCookieTray(); // Close the cookie tray before clicking
        //waitForElementToBeClickable(serviceLocator);
        // clickObject(serviceLocator);
        WebElement serviceElement = driver.findElement(GovernmentLocator);
        clickElement(serviceElement);

    }
    public void DepartmentOfHealth() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(60));
        wait.until(ExpectedConditions.presenceOfElementLocated(departmentOfHealthLocator));
        driver.findElement(departmentOfHealthLocator).click();

    }
    public void Health() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(60));
        wait.until(ExpectedConditions.presenceOfElementLocated(healthLocator));
        driver.findElement(healthLocator).click();
        //clickElementUsingJavaScript(driver.findElement(healthLocator));

    }
    public void initialApproval() throws InterruptedException{
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(60));
        wait.until(ExpectedConditions.presenceOfElementLocated(initialApprovalLocator));
        driver.findElement(initialApprovalLocator).click();
        //Thread.sleep(2000);

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

    public boolean isInitialApprovalTextDisplayed() {
        scrollToTop();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(1000));
        try {
            WebElement agricultureText = wait.until(ExpectedConditions.visibilityOfElementLocated(initialApprovalTextLocator));
            return agricultureText.isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }
    public void scrollToTop() {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("window.scrollTo(0, 0);");
    }
    public void pensionFund() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(60));
        wait.until(ExpectedConditions.presenceOfElementLocated(pensionFundLocator));
        driver.findElement(pensionFundLocator).click();

    }
    public void type() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(60));
        wait.until(ExpectedConditions.presenceOfElementLocated(typeLocator));
        driver.findElement(typeLocator).click();


    }
    public void visitor() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(60));
        wait.until(ExpectedConditions.presenceOfElementLocated(visitorLocator));
        driver.findElement(visitorLocator).click();
        //clickElementUsingJavaScript(driver.findElement(visitorLocator));

    }
    public void pensionIncreaseCalculator() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(60));
        wait.until(ExpectedConditions.presenceOfElementLocated(increaseCalculatorLocator));
        driver.findElement(increaseCalculatorLocator).click();

    }
    public boolean ispensionIncreaseCalculator() {
        scrollToTop();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(1000));
        try {
            WebElement agricultureText = wait.until(ExpectedConditions.visibilityOfElementLocated(increaseCalculatorTextLocator));
            return agricultureText.isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }
}
