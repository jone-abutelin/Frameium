package com.frameium.pageobject;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.datatransfer.StringSelection;
import java.awt.event.KeyEvent;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import org.testng.asserts.*;

import com.frameium.configuration.ObjectReader;
import com.frameium.genericfunctions.GenericFunctions;
import com.frameium.logger.LoggerHelper;
import com.frameium.utilities.ExcelReader;

import org.apache.log4j.Logger;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class ClimateXExcelDataPage extends GenericFunctions{
	private final Logger log = LoggerHelper.getLogger(ClimateXExcelDataPage.class);
	
	String [] Idtable = new String[10];
	String [] Countrytable = new String[10];
	String [] Citytable = new String[10];
	String [] BuildingNumbertable = new String[10];
	String [] Buildingnametable = new String[10];
	String [] Streettable = new String[10];
	String [] Postcodetable = new String[10];
	String [] UPRNtable = new String[10];
	String [] Subbuildingtable = new String[10];
	String [] latitudetable = new String[10];
	String [] longitudetable = new String[10];
	int i,totalRows;
	String excelval,tableval,temp1,temp2;
	ArrayList<String[]> Excellist = new ArrayList<String[]>();
	ArrayList<String[]> Tablelist = new ArrayList<String[]>();
	SoftAssert softassert=new SoftAssert();
	By SignInPage = By.xpath("//h1[text()='Sign in']");
	By SignInPageError = By.xpath("//div[text()='Error: Something went wrong.']");  //By.xpath("//div[text()='Error: The username and/or password used are invalid']");
	By EmailAddress = By.xpath("//input[@type='email']");
	By PasswordField = By.xpath("//input[@type='password']");
	By SignInButton = By.xpath("//button[text()='Sign in']");
	By EvaluateAssetField = By.xpath("//h1[text()='Evaluate an asset']");
	By SearchAssetField = By.xpath("//label[@class='address-search__label']");
	By TemplateButton = By.xpath("//a[@href='address-search__label']");
	By CloseButton = By.xpath("//button[@aria-label='Close']");
	By UploadCSVbutton = By.xpath("//*[@data-icon='arrow-up']");
	By UploadDataFromFile = By.xpath("//button[text()='Upload data from file']");
	By iframeUploadDataFile = By.xpath("(//iframe)[2]");
	By FileUploaded = By.xpath("//*[text()='File uploaded']");
	By LoadedSheets = By.xpath("//*[text()='Loaded sheets']");
	By ContinueButton = By.xpath("//button[text()='Continue']");
	By YesButton = By.xpath("//button[text()='Yes']");
	By MoveToCity = By.xpath("(//button[text()='Confirm mapping'])[3]");
	//By MoveToCity = By.xpath("(//div[text()='100% of your rows have a value for this column'])[3]");
	By ReviewButton = By.xpath("//button[text()='Review']");
	By SubmitField = By.xpath("//h4[text()='Are you ready to submit?']");
	By DoneField = By.xpath("//h4[text()='Done!']");
	By OkButton = By.xpath("//button[text()='Ok']");
	By TakeMeToTheResults = By.xpath("//*[contains(text(),'Take me')]");
	By Table = By.xpath("(//table[@class='htCore'])[1]");
	
	By IdxField = By.xpath("(//th[text()='Id']/ancestor::table/tbody/tr/td)[1]");
	By IdyField = By.xpath("(//th[text()='Id']/ancestor::table/tbody/tr/td)[2]");
	By CountryxField = By.xpath("(//th[text()='Country']/ancestor::table/tbody/tr/td)[1]");
	By CountryyField = By.xpath("(//th[text()='Country']/ancestor::table/tbody/tr/td)[2]");
	By CityxField = By.xpath("(//th[text()='City']/ancestor::table/tbody/tr/td)[1]");
	By CityyField = By.xpath("(//th[text()='City']/ancestor::table/tbody/tr/td)[2]");
	By StreetxField = By.xpath("(//th[text()='Street']/ancestor::table/tbody/tr/td)[1]");
	By StreetyField = By.xpath("(//th[text()='Street']/ancestor::table/tbody/tr/td)[2]");
	By PostCodexField = By.xpath("(//th[text()='Post Code']/ancestor::table/tbody/tr/td)[1]");
	//By PostCodeyField = By.xpath("(//th[text()='Post Code']/ancestor::table/tbody/tr/td)[2]");
	//By PostCodeyField = By.xpath("(//th[text()='Post Code']/ancestor::table/tbody/tr/td)[2]");
	//By PostCodeyField = By.xpath("(//th[text()='Post Code']/ancestor::table/tbody/tr/td)[2]");
	//Heal
	By tickCheckBox =By.xpath("//input[@id='form_checked1']");
	By firstTextBox = By.xpath("//input[@id='change_id']");
	By secondTextBox = By.xpath("//input[@class='test_class']");
	By changeLocatorButton2 = By.xpath("//ul//li//a[text()='Features']");
	By changeLocatorButton3 = By.xpath("//input[@id='twotabsearchtextbox']");
	
	By contactButton = By.xpath("//a[@href='/contact']");
	By homeButton = By.xpath("//a[@href='/']");
	By nameText = By.xpath("//input[@name='name']");
	By emailText = By.xpath("//input[@type='email']");
	By phoneNumberText = By.xpath("//input[@placeholder='Phone Number']");
	By messageText = By.xpath("//textarea[@name='message']");
	By submitButton = By.xpath("//input[@value='submit']");
	By contactPageText = By.xpath("//span[text()='Our Team']");
	By orderOnlineButton = By.xpath("//a[text()='Order Online']");
	
	//d365
	By LeadButton = By.xpath("//span[text()='Leads']");
	By NewButton = By.xpath("//span[text()='New']");
	By Salutation = By.xpath("//button[@aria-label='Salutation']");
	By FirstName = By.xpath("//input[@aria-label='First Name']");
	By LastName = By.xpath("//input[@aria-label='Last Name']");
	By JobTitle = By.xpath("//input[@aria-label='Job Title']");
	By BusinessPhone = By.xpath("//input[@aria-label='Business Phone']");
	By Fax = By.xpath("//input[@aria-label='Fax']");
	By MobilePhone = By.xpath("//input[@aria-label='Mobile Phone']");
	
	
	 String rootpath = System.getProperty("user.dir");
	 String excelfilepath= rootpath + getConfigProperty("climatextestdatapath");



	
	
	public void loginToApplication(String Username, String Password) throws InterruptedException
	{
		if(isDisplayedBy(SignInPage))
		{
			log.info("Login page displayed successfully");
			Thread.sleep(5000);
			clickElement(EmailAddress);
			enterKeys(EmailAddress, Username);
			clickElement(PasswordField);
			enterKeys(PasswordField, Password);
			log.info("Credentials entered successfully");
			clickElement(SignInButton);
			log.info("Sign in successfully");
			Thread.sleep(1000);
			clickElement(CloseButton);
		}
		else
		{
			log.info("Login page not displayed successfully");
		}
	}
	
	public void loginToTheApplication(String Username, String Password) throws InterruptedException
	{
		if(isDisplayedBy(SignInPage))
		{
			log.info("Login page displayed successfully");
			Thread.sleep(5000);
			clickElement(EmailAddress);
			enterKeys(EmailAddress, Username);
			clickElement(PasswordField);
			enterKeys(PasswordField, Password);
			log.info("Credentials entered successfully");
			clickElement(SignInButton);
			log.info("Clicked Sign In Button");
			Thread.sleep(1000);
			//clickElement(CloseButton);
		}
		else
		{
			log.info("Login page not displayed successfully");
		}
	}
	
	public void verifySignInError() throws InterruptedException
	{
		if(isDisplayedBy(SignInPageError))
		{
			assert true;
		}
		else
		{	
			assert false;
			log.info("Login error page not displayed successfully");
		}
	}
	
	public void verificationOfUploadPage() throws InterruptedException
	{
		Thread.sleep(1000);
		if(isDisplayedBy(EvaluateAssetField))
		{
			log.info("User successfully navigated to CSV uploading-Evaluate an asset page");
		}
		else
		{
			log.info("CSV upload-Evaluate an asset page is not displayed");
		}
		if(isDisplayedBy(SearchAssetField))
		{
			log.info("User successfully navigated to CSV uploading-Evaluate an asset page");
		}
		else
		{
			log.info("CSV upload-Evaluate an asset page is not displayed");
		}
		
	}
	
	public void selectUploadCSVbutton() throws InterruptedException 
	{
		
		Thread.sleep(1000);
		clickElement(UploadCSVbutton);
		log.info("Upload CSV button clicked successfully");
	}
	
	public void selectUploadDataFromFileButton() throws InterruptedException
	{
		Thread.sleep(1500);
		switchToFrame(findElement(iframeUploadDataFile));
		clickElement(UploadDataFromFile);
		log.info("Upload data from file button clicked successfully");
	}
	
	public void uploadFile() throws AWTException, InterruptedException
	{
	
		 
		 log.info(excelfilepath+ "file to be uploaded");
		 Thread.sleep(3000);
		 StringSelection ss = new StringSelection(excelfilepath);
		 Toolkit.getDefaultToolkit().getSystemClipboard().setContents(ss, null);



		//native key strokes for CTRL, V and ENTER keys
		Robot robot = new Robot();
	    robot.setAutoDelay(500);
		robot.keyPress(KeyEvent.VK_CONTROL);
		robot.keyPress(KeyEvent.VK_V);
		robot.keyRelease(KeyEvent.VK_V);
		robot.keyRelease(KeyEvent.VK_CONTROL);
		robot.keyPress(KeyEvent.VK_ENTER);
		robot.keyRelease(KeyEvent.VK_ENTER);
	}
	
	public void verificationOfImportParameters() throws InterruptedException
	{
		Thread.sleep(1500);
		if(isDisplayedBy(FileUploaded)&& isDisplayedBy(LoadedSheets))
		{
			log.info("File uploaded successfully");
		}
		else
		{
			log.info("File is not uploaded");
		}		
	}
	
	public void selectContinueButton() throws InterruptedException
	{		
		Thread.sleep(1000);
		clickElement(ContinueButton);
		log.info("Continue button clicked succesfully");
		Thread.sleep(1500);
		if(isDisplayedBy(YesButton))
		{
			clickElement(YesButton);
			log.info("Yes button clicked successfully");
		}
		else
		{
			log.info("Yes button not displayed");
		}
	}
	
	public void selectReviewButton()
	{	
		scrollToElementAndClick(findElement(ReviewButton));	
		log.info("Review button clicked successfully");
	}
	
	
	///Healinium
	public void selectCheckBox_Heal()
	{	
		clickElement(tickCheckBox);
		log.info("Check box clicked successfully");
	}
	
	public void fillText_Heal()
	{	
		enterKeys(firstTextBox,"sfsf");
		log.info("Check box clicked successfully");
	}
	
	public void clickChangeLocator_Heal()
	{	
		clickElement(changeLocatorButton3);
		//log.info("Check box clicked successfully");
	}
	
	public void clickContactHeal()
	{	
		clickElement(contactButton );
		//log.info("Check box clicked successfully");
	}
	
	public void clickHomeHeal()
	{	
		clickElement(homeButton);
		//log.info("Check box clicked successfully");
	}


	public void fillNameHeal()
	{	
		enterKeys(nameText ,"Justin");
	}
	
	public void fillEmailHeal()
	{	
		enterKeys(emailText,"justin@tr.com");
	}
	
	public void fillPhoneNumberHeal()
	{	
		enterKeys(phoneNumberText,"1021178211");
	}
	
	public void fillMessageHeal()
	{	
		enterKeys(messageText,"Order Delayed");
	}
	
	public void clickOrderOnlineHeal()
	{	
		clickElement(orderOnlineButton);
		//clickElement(submitButton);
	}

	public void clickSubmitHeal()
	{	
		MoveToElement(submitButton);
	}
	
	
	///D365
	public void clickLeadsCRM()
	{	
		clickElement(LeadButton);
		//log.info("Check box clicked successfully");
	}
	
	public void clickNewCRM()
	{	
		clickElement(NewButton);
		//log.info("Check box clicked successfully");
	}
	
	public void fillSummaryCRM()
	{	
		enterKeys(FirstName,"Riju");
		enterKeys(LastName,"John");
		enterKeys(JobTitle,"Eng");
		enterKeys(BusinessPhone,"982423");
		enterKeys(Fax,"2144");
		enterKeys(MobilePhone,"14214214");
		//log.info("Check box clicked successfully");
	}
	
	public void checkContactPageTitle()
	{
		if(isDisplayedBy(contactPageText))
				{assert(true);
				}
		else 
		{ assert(false);
			}
	}

	
	
	//
	
	
	
//	public void GetRowcountExcel() throws InvalidFormatException, IOException
//	{
//		ExcelReader reader = new ExcelReader();
//		List<Map<String, String>> testdata = reader.getData("C:\\Users\\AnjithaSudha\\Downloads\\Test Data-Addresses-EUandUS-06Apr2023-10Assets (2).xlsx", "Spectra_Template");
//
//		 totalRows = reader.totalRow;
//		//return totalRows;
//	}
	

	public int verifyUploadedDataAndExcelData() throws InvalidFormatException, IOException
	{
		
		ExcelReader reader = new ExcelReader();
		List<Map<String, String>> testdata = reader.getData(excelfilepath, "Spectra_Template");
		int totalRows = reader.totalRowCount(excelfilepath, "Spectra_Template");
		
		String[] Id= new String[totalRows-1];
		String[] Country = new String[totalRows-1];
		String[] City = new String[totalRows-1];
		String[] Street = new String[totalRows-1];
		String[] PostCode = new String[totalRows-1];
		String[] UPRN = new String[totalRows-1];
		String[] Buildingnumber = new String[totalRows-1];
		String[] Buildingname = new String[totalRows-1];
		String[] Subbuilding = new String[totalRows-1];
		//String[] UnitorFlat = new String[totalRows-1];
		//String[] PremiseUse = new String[totalRows-1];
		//String[] ReplacementCost = new String[totalRows-1];
		//String[] GrossPremiseArea = new String[totalRows-1];
		String[] Latitude = new String[totalRows-1];
		String[] Longitude = new String[totalRows-1];
		log.info(totalRows-1);
		log.info(testdata);
		
		for (i=0; i< totalRows-1; i++)
		{
			
			Id[i]= testdata.get(i).get("Id");
			Country[i] = testdata.get(i).get("Country");
			City[i] = testdata.get(i).get("City");
			Street[i] = testdata.get(i).get("Street");
			PostCode[i] = testdata.get(i).get("Post Code");
			UPRN[i] = testdata.get(i).get("UPRN");
			Buildingnumber[i] = testdata.get(i).get("Building number");
			Buildingname[i] = testdata.get(i).get("Building name");
			Subbuilding[i] = testdata.get(i).get("Sub-building");
			//UnitorFlat[i] = testdata.get(i).get("UnitorFlat");
			//PremiseUse[i] = testdata.get(i).get("PremiseUse");
			//ReplacementCost[i] = testdata.get(i).get("ReplacementCost");
			//GrossPremiseArea[i] = testdata.get(i).get("GrossPremiseArea");
			Latitude[i] = testdata.get(i).get("Latitude");
			Longitude[i] = testdata.get(i).get("Longitude");
		}
		
		
	        
		    log.info(Arrays.toString(Id));
			log.info(Arrays.toString(Country));
			log.info(Arrays.toString(City));
			log.info(Arrays.toString(Buildingnumber));
			log.info(Arrays.toString(Buildingname));
			log.info(Arrays.toString(Street));
			log.info(Arrays.toString(PostCode));
			/*log.info(Arrays.toString(UPRN));
			log.info(Arrays.toString(Subbuilding));
			log.info(Arrays.toString(Latitude));
			log.info(Arrays.toString(Longitude));*/
			
//			log.info(Arrays.toString(UnitorFlat));
//			log.info(Arrays.toString(PremiseUse));
//			log.info(Arrays.toString(ReplacementCost));
//			log.info(Arrays.toString(GrossPremiseArea));
		

		
			Excellist.add(Id);
			Excellist.add(Country);
			Excellist.add(City);
			Excellist.add(Buildingnumber);
			Excellist.add(Buildingname);
			Excellist.add(Street);
			Excellist.add(PostCode);
			Excellist.add(UPRN);
			Excellist.add(Subbuilding);
			Excellist.add(Latitude);
			Excellist.add(Longitude);
			
			
			return totalRows;
	}
	
	public ArrayList<String[]> ReadTable() throws InterruptedException
	{
	//	WebElement tableElement = driver.findElement(Table);
		Thread.sleep(3000);
		int rowNum = driver.findElements(By.xpath("//*[@id=\"hot\"]/div[1]/div/div/div/table/tbody/tr")).size();
    	int colNum = driver.findElements(By.xpath("//*[@id=\"hot\"]/div[1]/div/div/div/table/tbody/tr[1]/td")).size() - 1;
		String [] tablearray = new String [rowNum * colNum ] ;
		int j,k;
		int varcount =0;
		String tablevalue;
		
		for(k=2;k<=colNum;k++)
		{
    	    for(j=1;j<=rowNum;j++)
    	     {
    		
    			tablevalue = driver.findElement(By.xpath("//*[@id='hot']/div[1]/div/div/div/table/tbody/tr["+j+"]/td["+k+"]")).getText();
    			tablearray[varcount] = tablevalue;
    			
    			varcount++;
    		}
    	}
		log.info(Arrays.toString(tablearray));
		//log.info(tablearray.length);
		int idcount =0;
		int countrycount =0;
		int citycount =0;
		int buildingnocount =0;
		int buildingnamecount =0;
		int streetcount =0;
		int postcodecount =0;
		int uprncount =0;
		int subbuildingcount =0;
		int latitudecount =0;
		int longitudecount =0;
		
		for(int i =0; i<tablearray.length; i++)
		{
			
			if(i>= 0 && i<10)
			{
				
			    Idtable[idcount]= tablearray[i];
			    idcount++;
			}
			if (i>=10 && i<20) 
			{
				Countrytable[countrycount] = tablearray[i];
				countrycount++;
			}
			 if (i>=20 && i<30)
			{
				Citytable[citycount] = tablearray[i];
				citycount++;
			}
			 if (i>=30 && i<40)
			{
				BuildingNumbertable[buildingnocount] = tablearray[i];
				buildingnocount++;
			}
			 if (i>=40 && i<50)
			{
				Buildingnametable[buildingnamecount] = tablearray[i];
				buildingnamecount++;
			}
			 if (i>=50 && i<60)
			{
				Streettable[streetcount] = tablearray[i];
				streetcount++;
			}
			if (i>=60 && i<70)
			{
				Postcodetable[postcodecount] = tablearray[i];
				postcodecount++;
			}
		   if (i>=70 && i<80)
			{
				UPRNtable[uprncount] = tablearray[i];
				uprncount++;
			}
		   if (i>=80 && i<90)
			{
				Subbuildingtable[subbuildingcount] = tablearray[i];
				subbuildingcount++;
			}
		   if (i>=90 && i<100)
			{
			   
				latitudetable[latitudecount] = tablearray[i];
				latitudecount++;
			}
			if (i>=100 && i<110)
			{
				if(tablearray[i] == null) 
				{
				longitudetable[longitudecount] = "";
					longitudecount++;
				}
			else 
				{
					longitudetable[longitudecount] = tablearray[i];
					longitudecount++;
				}
				
			}
			
			
		}
		
//		String [] IdTable1 = new String[10];
//		IdTable1=	Arrays.toString(Idtable);
		
		log.info(Arrays.toString(Idtable));
		log.info(Arrays.toString(Countrytable));
		log.info(Arrays.toString(Citytable));
		log.info(Arrays.toString(BuildingNumbertable));
		log.info(Arrays.toString(Buildingnametable));
		log.info(Arrays.toString(Streettable));
		log.info(Arrays.toString(Postcodetable));
		/*log.info(Arrays.toString(UPRNtable));
		log.info(Arrays.toString(Subbuildingtable));
		log.info(Arrays.toString(latitudetable));
		log.info(Arrays.toString(longitudetable));*/
		
		
		Tablelist.add(Idtable);
		Tablelist.add(Countrytable);
		Tablelist.add(Citytable);
		Tablelist.add(BuildingNumbertable);
		Tablelist.add(Buildingnametable);
		Tablelist.add(Streettable);
		Tablelist.add(Postcodetable);
		Tablelist.add(UPRNtable);
		Tablelist.add(Subbuildingtable);
		Tablelist.add(latitudetable);
		Tablelist.add(longitudetable);
		
		return Tablelist;
	}
	
	@SuppressWarnings("deprecation")
	public void CompareData()
	{
		
		int CompareDiff=0;
		if(Tablelist.size()==Excellist.size())
		{
			for(int i=0;i<Excellist.size()-1;i++)
			{
				//for(String excelitem:Excellist.get(i))
				for(int j=0;j<Excellist.get(i).length-1;j++)
				{
					if(Excellist.get(i)[j].toString()!="")
							{
						
						 excelval="Excel value-" + Excellist.get(i)[j];
						 tableval="Table value-" + Tablelist.get(i)[j];
						 
					if((Excellist.get(i)[j].toString()).equalsIgnoreCase(Tablelist.get(i)[j].toString()))
					{
						
						
						log.info("Excel data  matches Table data-->" + Tablelist.get(i)[j]);
						CompareDiff=CompareDiff+1;
					}
					else
					{
						log.info("Excel value-" + Excellist.get(i)[j]);
						log.info("Table value-" + Tablelist.get(i)[j]);
						log.info("Excel data does not matches Table data");
						
						temp1 =excelval + temp1;
						temp2 =tableval + temp2;
						
						
					}
							}
				}
				
			}
			
			
			 softassert.assertTrue(CompareDiff==Excellist.size(),"Excel data  matches Table data" + temp1 + temp2);
					
	
			
		}
		else
		{
			
			 softassert.assertTrue(Tablelist.size()==Excellist.size(),"Size does not match Excel data  matches Table data");
	}
		
	
	
	}
	

		
		public void continueForSubmit() throws InterruptedException
		  {
			scrollToElementAndClick(findElement(ContinueButton));
			Thread.sleep(1000);
			if(isDisplayedBy(SubmitField))
			{
				clickElement(YesButton);
				log.info("Yes button clicked successfully");
				log.info("Data submitted successfully");
			}
			else
			{
				log.info("Yes button not displayed");
				log.info("Data is not submitted");
			}
			  
		  }
		
		public void VerifyUploadingAndSelectDone()
		{
			if(isDisplayedBy(DoneField))
			{
				log.info("Done option is displayed");
				clickElement(OkButton);
				log.info("File uploaded successfully");
			}
			else
			{
				log.info("Done option is not displayed");
			}
		}
		
		public void ViewResultsAndVerify() throws InterruptedException
		{
			Thread.sleep(8000);
			switchToDefault();
			if(isDisplayedBy(TakeMeToTheResults))
			{
				log.info("Take me to the results popup displayed");
				clickElement(YesButton);			
			}
			else
			{
				log.info("Take me to the results popup is not displayed");
			}
		}
	}
	
	
	

