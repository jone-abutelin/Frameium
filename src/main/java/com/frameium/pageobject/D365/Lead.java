package com.frameium.pageobject.D365;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.frameium.genericfunctions.GenericFunctions;

public class Lead extends GenericFunctions {

	private By lead = By.xpath("//span[contains(text(), 'Leads')]");
	private By clickNew = By.cssSelector("[title='New']");
	private By opportunityName = By.xpath(
			"//input[@id='id-e3b6ddb7-8df0-4410-ac7b-fd32e5053d38-1-subject4273edbd-ac1d-40d3-9fb2-095c621b552d-subject.fieldControl-text-box-text']");
	private By salutation = By.cssSelector("[aria-label='Salutation']");
	private By firstName = By.xpath(
			"//*[@id='id-e3b6ddb7-8df0-4410-ac7b-fd32e5053d38-3-fullname_compositionLinkControl_firstname4273edbd-ac1d-40d3-9fb2-095c621b552d-fullname_compositionLinkControl_firstname.fieldControl-text-box-text']");
	private By lastName = By.xpath(
			"//*[@id='id-e3b6ddb7-8df0-4410-ac7b-fd32e5053d38-5-fullname_compositionLinkControl_lastname4273edbd-ac1d-40d3-9fb2-095c621b552d-fullname_compositionLinkControl_lastname.fieldControl-text-box-text']");
	private By jobTitle = By.xpath(
			"//*[@id='id-e3b6ddb7-8df0-4410-ac7b-fd32e5053d38-6-jobtitle4273edbd-ac1d-40d3-9fb2-095c621b552d-jobtitle.fieldControl-text-box-text']");
	private By businessPhone = By.xpath(
			"//*[@id='id-e3b6ddb7-8df0-4410-ac7b-fd32e5053d38-7-telephone14273edbd-ac1d-40d3-9fb2-095c621b552d-telephone1.fieldControl-phone-text-input']");
	private By salesRegion = By.xpath("//select[@aria-label='Sales Region' and option[@value='881940001']]");
	private By serviceLineDetail = By
			.xpath("//select[@aria-label='Service Line Detail']/option[text()='Dynamics Testing']");
	private By industry = By
			.xpath("//select[@aria-label='Industry']/option[contains(text(),'Tech - software and services')]");
	private By email = By.xpath(
			"//*[@id='id-e3b6ddb7-8df0-4410-ac7b-fd32e5053d38-20-emailaddress1ada2203e-b4cd-49be-9ddf-234642b43b52-emailaddress1.fieldControl-mail-text-input']");
	private By country = By.cssSelector("[aria-label='Country']");
	private By save = By.cssSelector("[title='Save & Close']");
	private By allCheckbox = By.xpath("//input[@aria-label='Toggle selection of all rows']");
	private By delete = By.cssSelector("button[title='Delete']");
	private By deleteConfirm = By.xpath("//button[contains(@aria-label, 'Delete')]");

	@SuppressWarnings("static-access")
	public Lead(WebDriver driver) {
		driver = this.driver;
	}
	public void createNewLead(String leadName, String salutationText, String firstNameText, String lastNameText,
			String jobTitleText, String businessPhoneText, String emailText, String countryText)
			throws InterruptedException {
		clickObject(lead);
		clickObject(clickNew);
		fillLeadDetails(leadName, salutationText, firstNameText, lastNameText, jobTitleText, businessPhoneText,
				emailText, countryText);
		saveAndCloseLead();
	}

	private void fillLeadDetails(String leadName, String salutationText, String firstNameText, String lastNameText,
			String jobTitleText, String businessPhoneText, String emailText, String countryText)
			throws InterruptedException {
		
	    @SuppressWarnings("unused")
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20)); 

		enterKeys(opportunityName, leadName);
		selectSalutation(salutationText);
		enterKeys(firstName, firstNameText);
		enterKeys(lastName, lastNameText);

		Thread.sleep(2000);
		waitForOverlayToDisappear();

		enterKeys(jobTitle, jobTitleText);
		enterKeys(businessPhone, businessPhoneText);
		selectSalesRegion();
		selectServiceLineDetail();
		selectIndustry();
		enterKeys(email, emailText);
		enterKeys(country, countryText);
	}

	private void selectSalutation(String salutationText) {
		WebElement salutationElement = driver.findElement(salutation);
		selectUsingVisibleText(salutationElement, salutationText);
	}

	private void selectSalesRegion() {
		WebElement salesRegionElement = driver.findElement(salesRegion);
		selectUsingValue(salesRegionElement, "881940001");
	}

	private void selectServiceLineDetail() {
		WebElement serviceLineDetailElement = driver.findElement(serviceLineDetail);
		serviceLineDetailElement.click();
	}

	private void selectIndustry() {
		WebElement industryElement = driver.findElement(industry);
		industryElement.click();
	}

	public boolean saveAndCloseLead() {
		try {
			clickObject(save);
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	private void waitForOverlayToDisappear() throws InterruptedException {
		By overlayLocator = By.cssSelector(".ms-crm-InlineDialog-Lightbox");
		int timeoutInSeconds = 10;
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeoutInSeconds));
		try {
			wait.until(ExpectedConditions.invisibilityOfElementLocated(overlayLocator));
		} catch (TimeoutException e) {
			driver.findElement(By.cssSelector(".ms-crm-InlineDialog-Lightbox .ms-crm-InlineDialog-CloseButton"))
					.click();
		}
	}

	public boolean deletingAllLead() {
		clickObject(lead);
		clickObject(allCheckbox);
		clickObject(delete);
		clickObject(deleteConfirm);

		return isLeadEmpty();
	}

	private boolean isLeadEmpty() {
	        // Wait for the next page to load after deletion
	        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
	        WebElement nextPageElement = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//span[@class='noRowsLabel-255']")));
	        if (nextPageElement.isDisplayed()) {
	            return false; // Lead is not empty
	        } else {
	            return true; // Lead is empty
	        }
	    } 
}
