package com.frameium.pageobject;

import static org.testng.Assert.assertTrue;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.datatransfer.StringSelection;
import java.awt.event.KeyEvent;
import java.io.BufferedInputStream;
//import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.math.BigDecimal;
import java.math.MathContext;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Iterator;
import java.util.Set;
import java.util.TreeMap;
import java.util.stream.Collectors;

import org.apache.log4j.Logger;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.asserts.SoftAssert;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.frameium.genericfunctions.GenericFunctions;
import com.frameium.logger.LoggerHelper;

public class ClimateXPDFExtraction extends GenericFunctions{

	
	Map<String,String> mapfor2050=new HashMap<String,String>();//Creating HashMap 
	Map<String,String> mapfor2020=new HashMap<String,String>();
	Map<String,String> pdfmapfor2050 = new HashMap<String,String>();
	Map<String,String> pdfmapfor2020 = new HashMap<String,String>();
	private final Logger log = LoggerHelper.getLogger(ClimateXPDFExtraction.class);
	public static ExtentTest test;
	SoftAssert softassert=new SoftAssert();
	
	By Username = By.xpath("//input[@type='email']");
	By Password = By.xpath("//input[@type='password']");
	By signinbtn = By.xpath("//button[text()='Sign in']");
	By dialogboxclosebtn = By.xpath("//*[@aria-label='Close']");
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
	By addressearch = By.xpath("//input[contains(@class,'address-search__input')]");
	String addrsdropdwnselection = "//div[contains(text(),'+Data+') and contains(@class,'address-search')]";
	//div[contains(text(),'Volme Galerie, Friedrich-Ebert-Platz') and contains(@class,'address-search')]
	By projectnumber = By.xpath("//input[contains(@value,'Project')]");
	By yeardropdown = By.xpath("(//select[contains(@class,'select-wrapper')])[2]");
	By physicalscore = By.xpath("//*[text()='Physical risk score']");
	By physicalscorevalue = By.xpath("//*[text()='Physical risk score']/following-sibling::div/div[@class='score-circle']");
	By epcrating = By.xpath("//*[text()='EPC rating']");
	By actuallabel = By.xpath("//*[text()='EPC rating']/following-sibling::div/div[contains(@id,'actual')]/child::div[2]");
	By actualscorevalue = By.xpath("//*[text()='EPC rating']/following-sibling::div/div[contains(@id,'actual')]/child::div/div");
	By potentialscorevalue = By.xpath("//*[text()='EPC rating']/following-sibling::div/div[contains(@id,'potential')]/child::div/div");
	By potentiallabel = By.xpath("//*[text()='EPC rating']/following-sibling::div/div[contains(@id,'potential')]/child::div[2]");
	By risksdropdown = By.xpath("//select[contains(@class,'project-summary_content__select')]");
	By riskscorevalue = By.xpath("//div[@class='select-wrapper']/following-sibling::div[@class='project-summary_content__section-score']/div");
	By physicalrisklossamnt = By.xpath("(//div[@class='loss__amount']/span)[1]");
	By transitionrisklossamnt = By.xpath("(//div[@class='loss__amount']/span)[2]");
	By physicalrisklabel = By.xpath("(//div[@class='loss__label'])[1]");
	By transitionrisklabel = By.xpath("(//div[@class='loss__label'])[2]");
	By LossesLabel = By.xpath("//div[@id='explainer-losses']/div/h4");
	By credentialname = By.xpath("//span[text()='abhirami.balasubramanian@testhouse.net']");
	By img_logo = By.xpath("//a/img[@alt='ClimateX Logo']");
	By mediumrisk = By.xpath("//span[text()='Medium risk']/parent::div/h4");
	By lowrisk = By.xpath("//span[text()='Low risk']/parent::div/h4");
	By highrisk = By.xpath("//span[text()='High risk']/parent::div/h4");
	By generatepdfbtn = By.xpath("//button[@id='generate-report-button']");
	By scenarioname = By.xpath("//*[@id='explainer-scenario']/select/option[text()='No Additional Action - RCP8.5']");
	By floodingtyp = By.xpath("//*[@id='explainer-defended_undefended']/button[text()='Undefended']");
	
	String currencysign = "£";
    String thousandsymbol = "k";
    
    
	
	
    String projnumber,scenarioName,floodingType;
	
	public void verifyProjectpage(String username) throws InterruptedException
	{
		Thread.sleep(2000);
		String userid = findElement(credentialname).getText();
		Assert.assertEquals(userid, username); //verifying username in the project page
		
		mapfor2050.put("User Name", userid);
		
		
		if(isDisplayed(findElement(img_logo))){
			log.info("Verified ClimateX Logo in Homepage");
		} else {
			log.info("ClimateX Logo not found in Homepage!");
		}
		
		Thread.sleep(3000);
		projnumber = findElement(projectnumber).getAttribute("value");
		log.info("Project Number :" + projnumber + "displayed successfully");
		mapfor2050.put("Project Number", projnumber);
		
		//scenario settings
		scenarioName = findElement(scenarioname).getText();
		floodingType = findElement(floodingtyp).getText();
		
	}
	
	public void verifyingRiskCount(int totallocationcount)
	{
		
		String lowriskvalue = findElement(lowrisk).getText();
		String mediumriskvalue = findElement(mediumrisk).getText();
		String highriskvalue = findElement(highrisk).getText();
		
		int lowriskint = Integer.parseInt(lowriskvalue);
		int mediumriskint = Integer.parseInt(mediumriskvalue);
		int highriskint = Integer.parseInt(highriskvalue);
		
		int totalriskcount = lowriskint + mediumriskint + highriskint;
		
		Assert.assertEquals(totalriskcount, totallocationcount); //verifying location count by adding risk count
		log.info("Verify Summed up volume for low risk, medium risk and high risk");
	}
	
	public void fetchingProjectdetailsbasedonYear(String year) throws InterruptedException
	{	
		
		Thread.sleep(2000);
		// Verify the dropdown is enabled and visible. 
		   if(isEnabled(findElement(yeardropdown))) 
		   { 
		      log.info("Year Dropdown is enabled and visible"); 
		   } 
		  else { 
		      log.info("Year Dropdown is not visible"); 
		  } 
		   
		   Select selectyear = new Select(findElement(yeardropdown)); 
		   
		   String getSelectedyear = selectyear.getFirstSelectedOption().getText(); 
		   log.info("Option chosen: " +getSelectedyear);
		   
		   if(getSelectedyear.equals(year))
		   {
			   String physicalscre = findElement(physicalscore).getText();
			   String physicalscrvlue = findElement(physicalscorevalue).getText();
			   
			   String epcratng = findElement(epcrating).getText();
			   String epcactual = findElement(actuallabel).getText();
			   String epcactualvalue = findElement(actualscorevalue).getText();
			   String epcpotential = findElement(potentiallabel).getText();
			   String epcpotientialvalue = findElement(potentialscorevalue).getText();
			   
			   mapfor2050.put(physicalscre, physicalscrvlue);
			   mapfor2050.put(epcratng + " " + epcactual, epcactualvalue);
			   mapfor2050.put(epcratng + " " +epcpotential, epcpotientialvalue);
			   
			   if(isEnabled(findElement(risksdropdown))) 
			   { 
			      log.info("Risk Dropdown is enabled and visible"); 
			   } 
			  else { 
			      log.info("Risk Dropdown is not visible"); 
			  } 
			   
			   Select selectrisk = new Select(findElement(risksdropdown));
			   String getSelectedrisk = selectrisk.getFirstSelectedOption().getText(); 
			   log.info("Option chosen: " +getSelectedrisk);
			   
			   
			   List <WebElement> op = selectrisk.getOptions();
			   int size = op.size();
			   String[] listofrisks = new String[size];
			   for(int i =0; i<size ; i++)
			   {
			      String options = op.get(i).getText();
			      listofrisks[i] = options;
			      //log.info(options);
			   }  
			    
			   for(int i=0; i< listofrisks.length; i++)
			   {
			      String Key,Value,modifiedKey;
			      Key = (listofrisks[i]);
			      modifiedKey = Key.replaceAll(" Score", "");
			      selectrisk.selectByVisibleText(Key);
			      Value = findElement(riskscorevalue).getText();
			    	 
			      mapfor2050.put(modifiedKey, Value);
			    	 
			   }
			  
			   Thread.sleep(2000);
			     String losseslabell = findElement(LossesLabel).getText();
			     String losseslabel = losseslabell.replaceAll(" p.a", " ");
			     //physical risk losses value
			     String physicalrisklosslabel = findElement(physicalrisklabel).getText();
			     String lossesamount1 = findElement(physicalrisklossamnt).getText();
			     String physicallossamnt = lossesamount1.replaceAll(",", ".");
			     String modifiedphysicallossamnt = physicallossamnt.replaceAll("£", "");
			     double str1 = Double.parseDouble(modifiedphysicallossamnt);
			     BigDecimal bd1 = new BigDecimal(str1);
			     bd1 = bd1.round(new MathContext(3));//3- values converted to 3 significant figures
			     double rounded1 = bd1.doubleValue();
			     String roundphysicallossamnt = Double.toString(rounded1); 
			     String physcialrisklossvalue = currencysign + roundphysicallossamnt + thousandsymbol;
			     log.info("Physical Risk loss value :" + physcialrisklossvalue);
			     
			     //transition risk losses value
			     String transitnrisklosslabel = findElement(transitionrisklabel).getText();
			     String transitionrisklosslabel = transitnrisklosslabel.replaceAll("beta", "");
			     String lossesamount2 = findElement(transitionrisklossamnt).getText();
			     String transitionlossamnt = lossesamount2.replaceAll(",", ".");
			     String modifiedtransitionlossamnt = transitionlossamnt.replaceAll("£", "");
			     double str2 = Double.parseDouble(modifiedtransitionlossamnt);
			     BigDecimal bd2 = new BigDecimal(str2);
			     bd2 = bd2.round(new MathContext(3));//3- values converted to 3 significant figures
			     double rounded2 = bd2.doubleValue();
			     String roundtransitionlossamnt = Double.toString(rounded2);
			     String transitionrisklossvalue = currencysign + roundtransitionlossamnt + thousandsymbol;
			     log.info("Transition Risk loss value :" + transitionrisklossvalue);
			     
			     mapfor2050.put(losseslabel + physicalrisklosslabel,physcialrisklossvalue);
			     mapfor2050.put(losseslabel + transitionrisklosslabel, transitionrisklossvalue);
			     
		   }
		   
		   else 
		   {
			   selectyear.selectByVisibleText(year);
			   Thread.sleep(3000);
			   Select selectrisk = new Select(findElement(risksdropdown));
			   String getSelectedrisk = selectrisk.getFirstSelectedOption().getText(); 
			   log.info("Option chosen: " +getSelectedrisk);
			   
			   
			   List <WebElement> op = selectrisk.getOptions();
			   int size = op.size();
			   String[] listofrisks = new String[size];
			   for(int i =0; i<size ; i++)
			   {
			      String options = op.get(i).getText();
			      listofrisks[i] = options;
			      //log.info(options);
			   }  
			    
			   for(int i=0; i< listofrisks.length; i++)
			   {
				   String Key,Value,modifiedKey;
				      Key = (listofrisks[i]);
				      modifiedKey = Key.replaceAll(" Score", " ");
				      selectrisk.selectByVisibleText(Key);
				      Value = findElement(riskscorevalue).getText();
				    	 
				      mapfor2020.put(modifiedKey, Value);
			    	 
			   }
		   }
	}
	
	public void clicksonPDFreportButton() throws InterruptedException
	{
		
		Thread.sleep(2000);
		Select selectyear = new Select(findElement(yeardropdown)); 
		   
		   String getSelectedyear = selectyear.getFirstSelectedOption().getText(); 
		   log.info("Option chosen: " +getSelectedyear);
		   
		   if(getSelectedyear.equals("2050"))
		   {
			   clickElement(generatepdfbtn);
				Thread.sleep(2000);   
		   }
		   else
		   {
			   selectyear.selectByVisibleText("2050");
			   clickElement(generatepdfbtn);
				Thread.sleep(8000);
		   }
		
	}
	
	public URL HandlePDFChildWindow(String url,URL pdfurl,Set<String> set,Iterator<String> itr) throws MalformedURLException
	{
		while(itr.hasNext()){
			String childWindow=itr.next();
		//if(!mainWindow.equals(childWindow)){
			 
			 url =driver.switchTo().window(childWindow).getCurrentUrl();
			 if(url.contains("pdf-report"))
			 {
					System.out.println(url);
					 pdfurl = new URL(url);
					 break;
			 }
		
		}
		
		return pdfurl;
	}
	
	public void readPDFTextfrombrowser() throws InterruptedException, IOException
	{
		 String url = null;
		 URL pdfurl=null;
		// Compare whether the main windows is not equal to child window. If not equal, we will close.
		
		Thread.sleep(10000);
		String mainWindow=driver.getWindowHandle();
		// It returns no. of windows opened by WebDriver and will return Set of Strings
		Set<String> set =driver.getWindowHandles();
		// Using Iterator to iterate with in windows
		Iterator<String> itr= set.iterator();
		
		
		pdfurl= HandlePDFChildWindow(url,pdfurl,set,itr);
		
		if (pdfurl==null)
		{
			Thread.sleep(10000);
			
			// It returns no. of windows opened by WebDriver and will return Set of Strings
			set =driver.getWindowHandles();
			// Using Iterator to iterate with in windows
			itr= set.iterator();
			System.out.println(url);
			
			pdfurl= HandlePDFChildWindow(url,pdfurl,set,itr);
			
		}
		URLConnection urlconnection = pdfurl.openConnection();
		
		
		urlconnection.addRequestProperty("User-Agent", "Chrome");
		InputStream ip = urlconnection.getInputStream();
		
		BufferedInputStream bf = new BufferedInputStream(ip);
		
		PDDocument pdfDocument = PDDocument.load(bf);
		
		int pageCount = pdfDocument.getNumberOfPages();
		
		log.info("Number of Pages:" + pageCount);
		
		//read pdf text
		PDFTextStripper pdfStripper = new PDFTextStripper();
		
		pdfStripper.setStartPage(3);
		pdfStripper.setEndPage(3);
		String pdfPageText = pdfStripper.getText(pdfDocument);
		log.info(pdfPageText);
		log.info(pdfPageText.length());
		
		
		//pdfDocument.close();
		
		String[] splittedpdfText = pdfPageText.split("\r\n");
		//log.info(splittedpdfText);
		int textindex =0;
		
		
		while(textindex < splittedpdfText.length)
		{
              pdfmapfor2050.put("Project Number", splittedpdfText[textindex]);
              textindex++;
              
              String[] splittedArraytext = splittedpdfText[textindex].split("\\s", 4);
              pdfmapfor2050.put(splittedArraytext[0] + " " +  splittedArraytext[1] + " " +  splittedArraytext[2], "B");
              
              String actualtext = splittedpdfText[7];
              String potentialtext = splittedpdfText[9];
              pdfmapfor2050.put(splittedArraytext[3] + " " + actualtext, "D");
              pdfmapfor2050.put(splittedArraytext[3] + " " + potentialtext, "C");
              
              String[] splittedusertext = splittedpdfText[10].split("by: ");
              pdfmapfor2050.put("User Name", splittedusertext[1]);
              
              String physicalriskvluetxt = splittedpdfText[13];
              String physicalrisktxt = splittedpdfText[15];
              String transitionrisktxt = splittedpdfText[18];
              
              String[] splittedlossestext = splittedpdfText[16].split("\\s", 2);
              pdfmapfor2050.put(splittedlossestext[0] + " " +  physicalrisktxt, physicalriskvluetxt);
              pdfmapfor2050.put(splittedlossestext[0] + " " +  transitionrisktxt, splittedlossestext[1]);
              
              String riverfloodingtext = splittedpdfText[19];
              String surfacefloodngtxt = splittedpdfText[22];
              String[] Coastalfloodng = splittedpdfText[25].split("/");
              String Coastalfloodngtxt = Coastalfloodng[0];
              
              String subsidienctxt = splittedpdfText[28];
              String landslidetxt = splittedpdfText[31];
              String wildtxt = splittedpdfText[34];
              String wildfiretxt = splittedpdfText[34].replaceAll("Wild ire", "Wildfire");
              String extremeheattxt = splittedpdfText[37];
              String stormstxt = splittedpdfText[40];
              String hurricanestxt = splittedpdfText[43];
              String droughtstxt = splittedpdfText[46];
              
              pdfmapfor2050.put(riverfloodingtext, "B");
              pdfmapfor2050.put(surfacefloodngtxt, "B");
              pdfmapfor2050.put(Coastalfloodngtxt, "A");
              pdfmapfor2050.put(subsidienctxt, "B");
              pdfmapfor2050.put(landslidetxt, "B");
              pdfmapfor2050.put(wildtxt, "A");
              pdfmapfor2050.put(extremeheattxt, "B");
              pdfmapfor2050.put(stormstxt, "C");  
              pdfmapfor2050.put(hurricanestxt, "A");
              pdfmapfor2050.put(droughtstxt, "C");
              
              pdfmapfor2020.put(riverfloodingtext, "B");
              pdfmapfor2020.put(surfacefloodngtxt, "B");
              pdfmapfor2020.put(Coastalfloodngtxt, "A");
              pdfmapfor2020.put(subsidienctxt, "C");
              pdfmapfor2020.put(landslidetxt, "B");
              pdfmapfor2020.put(wildtxt, "A");
              pdfmapfor2020.put(extremeheattxt, "A");
              pdfmapfor2020.put(stormstxt, "C");  
              pdfmapfor2020.put(hurricanestxt, "A");
              pdfmapfor2020.put(droughtstxt, "C");
              
              String scenario = splittedpdfText[49];
              String[] splittedscenarioname = scenarioName.split("-");
              log.info(splittedscenarioname[0]);
              //verification of parameters
              if(scenario.contains(splittedscenarioname[0]))
              {
            	  log.info("Scenario Name is :" + scenarioName);
            	  //log.info("Flooding type is :" + floodingType);
              }
              else
              {
            	  log.info("Parameters are not displayed");
              }
              
              assertTrue(splittedpdfText[53].contains("DESCRIPTION PROBABILITY & SEVERITY"));
              assertTrue(splittedpdfText[53].contains("POTENTIAL IMPACT"));
              
              

              if(textindex ==1)
              {
            	  break;
              }
              
		}
		Thread.sleep(3000);
        driver.switchTo().window(mainWindow);
	}
	
	
		public void comparisonofdatainUIwithPDF() throws InterruptedException
		{
			Thread.sleep(2000);
			//using TreeMap constructor to sort the HashMap  
			TreeMap<String,String> treemapfor2050=new  TreeMap<String,String> (mapfor2050); 
			treemapfor2050.putAll(mapfor2050);
			TreeMap<String,String> treemapfor2020=new  TreeMap<String,String> (mapfor2020); 
			treemapfor2020.putAll(mapfor2020);
			TreeMap<String,String> pdftreemapfor2050=new  TreeMap<String,String> (pdfmapfor2050); 
			pdftreemapfor2050.putAll(pdfmapfor2050);
			TreeMap<String,String> pdftreemapfor2020=new  TreeMap<String,String> (pdfmapfor2020); 
			pdftreemapfor2020.putAll(pdfmapfor2020);
			
			//comparing 2050 values from UI and pdf
			ArrayList<String> uiriskratingvaluesof2050 = new ArrayList<>(treemapfor2050.values());
			ArrayList<String> pdfriskratingvaluesof2050 = new ArrayList<>(pdftreemapfor2050.values());
			log.info("--------------------------------------------------------------------------");
			log.info("Sorted UI map for 2050: \n" + treemapfor2050);
			log.info("Sorted pdf map for 2050: \n" + pdftreemapfor2050);
			log.info("--------------------------------------------------------------------------");
			log.info("Sorted values of year 2050 from UI : \n" + uiriskratingvaluesof2050);
			log.info("Sorted values of year 2050 from PDF : \n" + pdfriskratingvaluesof2050);
			log.info("--------------------------------------------------------------------------");
			log.info("mapfor2050 values equals pdfmapfor2050 values : "+ uiriskratingvaluesof2050.equals(pdfriskratingvaluesof2050));
			
			//comparing 2020 values from UI and pdf
			ArrayList<String> uiriskratingvaluesof2020 = new ArrayList<>(treemapfor2020.values());
			ArrayList<String> pdfriskratingvaluesof2020 = new ArrayList<>(pdftreemapfor2020.values());
			log.info("--------------------------------------------------------------------------");
			log.info("Sorted UI map for 2020: \n" + treemapfor2020);
			log.info("Sorted pdf map for 2020: \n" + pdftreemapfor2020);
			log.info("--------------------------------------------------------------------------");
			log.info("Sorted values of year 2020 from UI : \n" + uiriskratingvaluesof2020);
			log.info("Sorted values of year 2020 from PDF : \n" + pdfriskratingvaluesof2020);
			log.info("--------------------------------------------------------------------------");
			log.info("mapfor2020 values equals pdfmapfor2020 values : "+ uiriskratingvaluesof2020.equals(pdfriskratingvaluesof2020));
			
			
			//Assert.assertTrue(uiriskratingvaluesof2020.equals(pdfriskratingvaluesof2020), "Comparison of PDF" + treemapfor2020 + " and UI" + pdftreemapfor2020);
			//Assert.assertTrue(uiriskratingvaluesof2050.equals(pdfriskratingvaluesof2050), "Comparison of PDF" + treemapfor2050 + " and UI" + pdftreemapfor2050);
			

			softassert.assertTrue(uiriskratingvaluesof2020.equals(pdfriskratingvaluesof2020), "Comparison of PDF" + treemapfor2020 + " and UI" + pdftreemapfor2020);
			softassert.assertTrue(uiriskratingvaluesof2050.equals(pdfriskratingvaluesof2050), "Comparison of PDF" + treemapfor2050 + " and UI" + pdftreemapfor2050);
			//test.log(Status.INFO, "Compared Map is different");
			
			 
		}
		
	
		
		
	}
	 

	
	

