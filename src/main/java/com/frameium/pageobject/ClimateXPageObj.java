package com.frameium.pageobject;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.datatransfer.StringSelection;
import java.awt.event.KeyEvent;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileFilter;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.testng.asserts.*;

import com.frameium.genericfunctions.GenericFunctions;
import com.frameium.utilities.ExcelReader;

import org.apache.commons.io.FileUtils;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class ClimateXPageObj extends GenericFunctions{
	
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
	
	ArrayList<String[]> Excellist = new ArrayList<String[]>();
	ArrayList<String[]> Tablelist = new ArrayList<String[]>();
	String downloadDir1 = System.getProperty("user.home")+ "\\Downloads";
	
	
	String downloadDir = System.getProperty("user.dir") + "\\src\\test\\resources\\Downloads";
	String downloadFilename;
	

	By data_tab = By.xpath("//div[text()='Data']");
	By btn_exportcsv = By.xpath("//button[text()='Export CSV']");
	By btn_Download = By.xpath("//button[text()='Download']");
	By SignInPage = By.xpath("//h1[text()='Sign in']");
	By EmailAddress = By.xpath("//input[@type='email']");
	By PasswordField = By.xpath("//input[@type='password']");
	By SignInButton = By.xpath("//button[text()='Sign in']");
	By EvaluateAssetField = By.xpath("//h1[text()='Evaluate an asset']");
	By CloseButton = By.xpath("//button[@aria-label='Close']");
	By UploadCSVbutton = By.xpath("//*[@data-icon='arrow-up']");
	By UploadDataFromFile = By.xpath("//button[text()='Upload data from file']");
	By iframeUploadDataFile = By.xpath("(//iframe)[2]");
	By FileUploaded = By.xpath("//*[text()='File uploaded']");
	By LoadedSheets = By.xpath("//*[text()='Loaded sheets']");
	By ContinueButton = By.xpath("//button[text()='Continue']");
	By YesButton = By.xpath("//button[text()='Yes']");
	By MoveToCity = By.xpath("(//button[text()='Confirm mapping'])[3]");
	By ReviewButton = By.xpath("//button[text()='Review']");
	By SubmitField = By.xpath("//h4[text()='Are you ready to submit?']");
	By DoneField = By.xpath("//h4[text()='Done!']");
	By OkButton = By.xpath("//button[text()='Ok']");
	By TakeMeToTheResults = By.xpath("//h2[text()='Take me to the results']");
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
	
	
	
	
	public void loginToApplication(String Username, String Password) throws InterruptedException
	{
		if(isDisplayedBy(SignInPage))
		{
			System.out.println("Login page displayed successfully");
			Thread.sleep(5000);
			clickElement(EmailAddress);
			enterKeys(EmailAddress, Username);
			clickElement(PasswordField);
			enterKeys(PasswordField, Password);
			System.out.println("Credentials entered successfully");
			clickElement(SignInButton);
			System.out.println("Sign in successfully");
			Thread.sleep(1000);
			clickElement(CloseButton);
		}
		else
		{
			System.out.println("Login page not displayed successfully");
		}
	}
	
	public void verificationOfUploadPage() throws InterruptedException
	{
		Thread.sleep(1000);
		if(isDisplayedBy(EvaluateAssetField))
		{
			System.out.println("User successfully navigated to CSV uploading page");
		}
		else
		{
			System.out.println("The CSV upload page is not displayed");
		}
	}
	
	public void selectUploadCSVbutton() throws InterruptedException 
	{
		
		Thread.sleep(1000);
		clickElement(UploadCSVbutton);
		System.out.println("Upload CSV button clicked successfully");
	}
	
	public void selectUploadDataFromFileButton() throws InterruptedException
	{
		Thread.sleep(1500);
		switchToFrame(findElement(iframeUploadDataFile));
		clickElement(UploadDataFromFile);
		System.out.println("Upload data from file button clicked successfully");
	}
	
	public void uploadFile() throws AWTException, InterruptedException
	{
		 String path="\"C:\\Users\\AnjithaSudha\\Downloads\\Test Data-Addresses-EUandUS-06Apr2023-10Assets (2).xlsx\"";
		 Thread.sleep(3000);
		 StringSelection ss = new StringSelection(path);
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
			System.out.println("File uploaded successfully");
		}
		else
		{
			System.out.println("File is not uploaded");
		}		
	}
	
	public void selectContinueButton() throws InterruptedException
	{		
		Thread.sleep(1000);
		clickElement(ContinueButton);
		System.out.println("Continue button clicked succesfully");
		Thread.sleep(1500);
		if(isDisplayedBy(YesButton))
		{
			clickElement(YesButton);
			System.out.println("Yes button clicked successfully");
		}
		else
		{
			System.out.println("Yes button not displayed");
		}
	}
	
	public void selectReviewButton()
	{	
		scrollToElementAndClick(findElement(ReviewButton));	
		System.out.println("Review button clicked successfully");
	}

	public ArrayList<String[]> verifyUploadedDataAndExcelData() throws InvalidFormatException, IOException
	{
		
		ExcelReader reader = new ExcelReader();
		List<Map<String, String>> testdata = reader.getData("C:\\Users\\AnjithaSudha\\Downloads\\Test Data-Addresses-EUandUS-06Apr2023-10Assets (2).xlsx", "Spectra_Template");
		int totalRows = reader.totalRowCount("C:\\Users\\AnjithaSudha\\Downloads\\Test Data-Addresses-EUandUS-06Apr2023-10Assets (2).xlsx", "Spectra_Template");
		
		String[] Id= new String[totalRows-1];
		String[] Country = new String[totalRows-1];
		String[] City = new String[totalRows-1];
		String[] Street = new String[totalRows-1];
		String[] PostCode = new String[totalRows-1];
		String[] UPRN = new String[totalRows-1];
		String[] Buildingnumber = new String[totalRows-1];
		String[] Buildingname = new String[totalRows-1];
		String[] Subbuilding = new String[totalRows-1];
		String[] Latitude = new String[totalRows-1];
		String[] Longitude = new String[totalRows-1];
		System.out.println(totalRows-1);
		System.out.println(testdata);
		
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
			Latitude[i] = testdata.get(i).get("Latitude");
			Longitude[i] = testdata.get(i).get("Longitude");
		}
	        
		    System.out.println(Arrays.toString(Id));
			System.out.println(Arrays.toString(Country));
			System.out.println(Arrays.toString(City));
			System.out.println(Arrays.toString(Buildingnumber));
			System.out.println(Arrays.toString(Buildingname));
			System.out.println(Arrays.toString(Street));
			System.out.println(Arrays.toString(PostCode));
			System.out.println(Arrays.toString(UPRN));
			System.out.println(Arrays.toString(Subbuilding));
			System.out.println(Arrays.toString(Latitude));
			System.out.println(Arrays.toString(Longitude));
		
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
			
			return Excellist;
	}
	
	public ArrayList<String[]> ReadTable() throws InterruptedException
	{
	
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
		System.out.println(Arrays.toString(tablearray));
		System.out.println(tablearray.length);
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
		
		
		System.out.println(Arrays.toString(Idtable));
		System.out.println(Arrays.toString(Countrytable));
		System.out.println(Arrays.toString(Citytable));
		System.out.println(Arrays.toString(BuildingNumbertable));
		System.out.println(Arrays.toString(Buildingnametable));
		System.out.println(Arrays.toString(Streettable));
		System.out.println(Arrays.toString(Postcodetable));
		System.out.println(Arrays.toString(UPRNtable));
		System.out.println(Arrays.toString(Subbuildingtable));
		System.out.println(Arrays.toString(latitudetable));
		System.out.println(Arrays.toString(longitudetable));
		
		
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
				for(int j=0;j<Excellist.get(i).length-1;j++)
				{
					if(Excellist.get(i)[j].toString()!="")
							{
					if((Excellist.get(i)[j].toString()).equalsIgnoreCase(Tablelist.get(i)[j].toString()))
					{
						System.out.println(Tablelist.get(i)[j]);
						System.out.println("Excel data  matches Table data");
						CompareDiff=CompareDiff+1;
					}
					else
					{
						System.out.println(Excellist.get(i)[j]);
						System.out.println(Tablelist.get(i)[j]);
						System.out.println("Excel data  matches Table data");
						
					}
							}
				}
				
			}
			
		}
		else
		{
			
		System.out.println("Size does not match Excel data  matches Table data");
	}
		
	}
	
	
		public void continueForSubmit() throws InterruptedException
		  {
			scrollToElementAndClick(findElement(ContinueButton));
			Thread.sleep(1000);
			if(isDisplayedBy(SubmitField))
			{
				clickElement(YesButton);
				System.out.println("Yes button clicked successfully");
				System.out.println("Data submitted successfully");
			}
			else
			{
				System.out.println("Yes button not displayed");
				System.out.println("Data is not submitted");
			}
			  
		  }
		
		public void VerifyUploadingAndSelectDone()
		{
			if(isDisplayedBy(DoneField))
			{
				System.out.println("Done option is displayed");
				clickElement(OkButton);
				System.out.println("File uploaded successfully");
			}
			else
			{
				System.out.println("Done option is not displayed");
			}
		}
		
		public void ViewResultsAndVerify() throws InterruptedException
		{
			Thread.sleep(3000);
		    
			if(isDisplayedBy(TakeMeToTheResults))
			{
				System.out.println("take me to the results popup displayed");
				clickElement(YesButton);			
			}
			else
			{
				System.out.println("take me to the results popup is not displayed");
			}
		}
		
		
		public void clearDowloadFolder() throws Exception {
			File directory = new File(downloadDir);
	        FileUtils.cleanDirectory(directory);
		}
		
		public void clickcsvExport() throws InterruptedException {
			
			try {
				
				clearDowloadFolder();
				
			} catch (Exception e) {
				System.out.println("Folder not found");
			}
			
			clickElement(data_tab);
			clickElement(btn_exportcsv);
			
			clickElement(btn_Download);
			Thread.sleep(10000);
				
		}
		
		public String getDowloadedFile() {
			
		        File fl = new File(downloadDir1);
		        String choice = null;
		        if (fl.listFiles().length>0) {
		            File[] files = fl.listFiles(new FileFilter() {
		                public boolean accept(File file) {
		                    return file.isFile();
		                }
		            });
		            long lastMod = Long.MIN_VALUE;

		            for (File file : files) {
		                if (file.lastModified() > lastMod) {
		                    choice = file.getName();
		                    lastMod = file.lastModified();
		                }
		            }
		        }
		        return choice;
		}
		 public void VerifyDowloadedFile() throws Exception {
			 //time for complete the download
			 Thread.sleep(4000);
			 downloadFilename=getDowloadedFile();
			 System.out.println("Downloaded File name is "+downloadFilename);
			 
			 if(downloadFilename!=null) {
				 System.out.println("File dowloaded successfully");
			 }else {
				 System.out.println("File dowload failed");
			 }
			 
		 }
		 
		 ExcelReader er=new ExcelReader();
		 List<Map<String, String>>exceldata;
		 
		 public void readDataFromExcel() throws Exception, Exception {
			 String csvfilepath=downloadDir1+"\\"+downloadFilename;
			 System.out.println("csvfilepath ="+csvfilepath);
			 
			 String sheetname=downloadFilename.substring(0, 31);
			 System.out.println("sheetname ="+sheetname);
			 System.out.println("csvfilepath ="+csvfilepath);
			 
			 
			 BufferedReader br = new BufferedReader(new FileReader(csvfilepath));
			    String line =  null;
			    HashMap<String,String> map = new HashMap<String, String>();
			    String splitBy = ","; 
			    int Totalrowcountt=0;
			      
			       
			    while ((line = br.readLine()) != null)   //returns a Boolean value  
			    {  
			    	Totalrowcountt++;
			    	String[] columheading = line.split(splitBy);    // use comma as separator
			    	//System.out.println(columheading.toString());
			    }  
			 
			 
			    System.out.println("Totalrowcountt ="+Totalrowcountt);
			    
		 }

		
		
	}
	
	
	

