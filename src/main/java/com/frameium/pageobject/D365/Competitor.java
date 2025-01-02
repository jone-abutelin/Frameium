package com.frameium.pageobject.D365;
import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.frameium.genericfunctions.GenericFunctions;

public class Competitor extends GenericFunctions {

	private By competitorIcon = By.xpath("//span[contains(text(), 'Competitors')]");
	private By newClick = By.cssSelector("[title='New']");
	private By name = By.xpath("//input[@aria-label='Name']");
	private By save = By.cssSelector("[title='Save & Close']");

	@SuppressWarnings("static-access")
	//public Competitor(WebDriver driver) {
		//this.driver = driver; // Initialize the driver 
	//}
	
	public Competitor(WebDriver driver) {
		driver = this.driver;
	}

	public boolean createNewCompetitor(String competitorName) throws InterruptedException {
		clickObject(competitorIcon);
		clickObject(newClick);
		@SuppressWarnings("unused")
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20)); 
		enterCompetitorName(competitorName);
		clickSaveAndClose();
		Thread.sleep(2000);
		return saveAndCloseCompetitor();
	}

	private void enterCompetitorName(String name) {
		enterKeys(this.name, name);
	}

	private void clickSaveAndClose() {
		clickObject(save);
	}

	// clickObject(save);
	public boolean saveAndCloseCompetitor() {
		try {
			clickObject(save);
			return true;
		} catch (Exception e) {
			return false;
		}

	}
}
