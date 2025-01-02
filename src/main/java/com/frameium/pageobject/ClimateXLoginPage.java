package com.frameium.pageobject;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;

import com.frameium.genericfunctions.GenericFunctions;
import com.frameium.logger.LoggerHelper;

public class ClimateXLoginPage extends GenericFunctions {

	By txt_email = By.xpath("//input[@type='email']");
	By txt_password = By.xpath("//input[@type='password']");
	By btn_signin = By.xpath("//button[text()='Sign in']");
	
	private final Logger log = LoggerHelper.getLogger(ClimateXLoginPage.class);
	
	By uploadcsvbtn = By.xpath("//button[contains(@class,'upload-csv')]");
	By uploaddatafromfilebtn = By.xpath("//*[text()='Upload data from file']");
	By fileuploaded = By.xpath("//*[text()='File uploaded']");
	By loadedsheets = By.xpath("//*[text()='Loaded sheets']");
	By upload = By.xpath("//*[text()='Upload']");
	By continuebtn = By.xpath("//*[text()='Continue']");
	By readytosubmt = By.xpath("//*[contains(text(),'Are you ready to submit')]");
	By reviewbtn = By.xpath("//*[text()='Review']");
	By done = By.xpath("//*[text()='Done!']");
	By okbtn = By.xpath("//*[text()='Ok']");
	By takemetoresult = By.xpath("//*[contains(text(),'Take me to the results')]");
	By yesbtn = By.xpath("//*[text()='Yes']");
	By usernamedrpdwn = By.xpath("//button[contains(@class,'user-fullname dropdown')]");
	By logout = By.xpath("//*[text()='Log out']");
	
	public void userLogin(String email, String password){
		clickElement(txt_email);
		enterKeys(txt_email, email);
		log.info("Username entered successfully");
		
		clickElement(txt_password);
		enterKeys(txt_password, password);
		log.info("Password entered successfully");
		
		if(isDisplayed(findElement(btn_signin))){
			clickElement(btn_signin);
			log.info("Login button clicked successfully");
		} else {
			log.info("Login button not displayed");
		}
		
		//MFA token manually enter for now
		/*try {
			Thread.sleep(20000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}*/

	}
	
	public void Logout() throws InterruptedException
	{
		clickElement(usernamedrpdwn);
		clickElement(logout);
		log.info("User logged out of application");
	}
	
}
