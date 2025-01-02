package com.frameium.pageobject.D365;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.JavascriptExecutor;

import com.frameium.genericfunctions.GenericFunctions;

public class Account extends GenericFunctions {

	private By ok = By.xpath("//button[@aria-label='Close']");
	private By accountIcon = By.xpath("//span[contains(text(), 'Accounts')]");
	private By newClick = By.cssSelector("[title='New']");
	private By name = By.xpath("//input[@aria-label='Account Name']");
	private By save = By.cssSelector("[title='Save & Close']");
	private By industry = By.xpath("//select[@aria-label='Industry']/option[contains(text(),'Banking')]");
	private By address1 = By.xpath("//input[@aria-label='Address 1: Country/Region']");

	private By select = By.xpath("//label[@class='ms-Checkbox-label label-204']");
	private By delete = By.xpath("//span[contains(text(), 'Delete')]");
	private By deleteButton = By.xpath("//button[@aria-label='Delete']");


	public Account(WebDriver driver) {
		driver = this.driver;
	}
	public boolean newAccountCreate(String accountName, String industryValue, String addressLine1)
			throws InterruptedException {

		clickObject(accountIcon);
		clickObject(newClick);
		handlePopup();
		enterAccountDetails(accountName, industryValue, addressLine1);
		boolean saveResult = saveAndCloseAccount();
		if (saveResult) {
			System.out.println("Account saved and closed successfully.");
		} else {
			System.out.println("Failed to save and close account.");
		}
		return saveResult;
	}
	
	public String getTheBrowserTitle() {
		return driver.getTitle();
	}

	 public boolean deleteAccount(String accountName) {
	        clickObject(accountIcon);
	        selectAccountCheckbox(accountName);
	        clickDelete();
	        return !isNoDataAvailableDisplayed();
	    }

	    private void enterAccountDetails(String accountName, String industryValue, String addressLine1) {
	        enterKeys(name, accountName);
	        selectIndustry(industryValue);
	        enterKeys(address1, addressLine1);
	    }

	    private void selectIndustry(String industryValue) {
	        WebElement industryElement = driver.findElement(industry);
	        industryElement.click();
	    }

	    private void handlePopup() {
	        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
	        try {
	            WebElement cancelButton = wait.until(ExpectedConditions.visibilityOfElementLocated(ok));
	            cancelButton.click();
	            System.out.println("Popup appeared and canceled.");
	        } catch (TimeoutException e) {
	            System.out.println("Popup did not appear.");
	        }
	    }

	    private boolean saveAndCloseAccount() {
	        System.out.println("Attempting to click the save button...");
	        try {
	            clickObject(save);
	            System.out.println("Save button clicked successfully.");
	            return true;
	        } catch (Exception e) {
	            System.out.println("Failed to click save button: " + e.getMessage());
	            return false;
	        }
	    }

	    private void selectAccountCheckbox(String accountName) {
	        WebElement accountNameElement = driver.findElement(By.xpath("//span[@class='spanWrapper-231' and text()='"
	                + accountName + "']"));
	        WebElement accountRow = (WebElement) ((JavascriptExecutor) driver)
	                .executeScript("return arguments[0].closest('.ag-row')", accountNameElement);
	        WebElement checkbox = accountRow.findElement(select); 
	        checkbox.click();
	    }

	    private void clickDelete() {
	        clickObject(delete);
	        clickObject(deleteButton);
	    }

	    public boolean isNoDataAvailableDisplayed() {
	        try {
	            WebElement noDataElement = driver.findElement(By.xpath(
	                    "//div[@class='noRowsContainer-241']//span[@class='noRowsLabel-243' and text()='No data available']"));
	            return noDataElement.isDisplayed();
	        } catch (NoSuchElementException e) {
	            return false;
	        }
	    }
}
