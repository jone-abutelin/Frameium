package com.frameium.pageobject;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.lang.reflect.Array;
import java.time.Instant;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

import javax.imageio.ImageIO;

import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebElement;
//import org.openqa.selenium.interactions.ClickAction;
import org.openqa.selenium.interactions.*;
import org.testng.Assert;
import org.testng.asserts.SoftAssert;

import com.frameium.genericfunctions.GenericFunctions;
import com.frameium.logger.LoggerHelper;

import ru.yandex.qatools.ashot.AShot;
import ru.yandex.qatools.ashot.Screenshot;
import ru.yandex.qatools.ashot.comparison.ImageDiff;
import ru.yandex.qatools.ashot.comparison.ImageDiffer;
import ru.yandex.qatools.ashot.coordinates.WebDriverCoordsProvider;
import ru.yandex.qatools.ashot.shooting.ShootingStrategies;

public class ClimateXHomePage  extends GenericFunctions {

	LocalDate date;
	private final Logger log = LoggerHelper.getLogger(ClimateXHomePage.class);
	
	By btn_close = By.xpath("//button[@title='Close']");
	By img_logo = By.xpath("//a/img[@alt='ClimateX Logo']");
	By lbl_heading1 = By.xpath("//h1[text()='Evaluate an asset']");
	
	By btn_heatmap = By.xpath("//button[text()=\"Heatmap\"]");
	By canvas = By.tagName("canvas");
	WebElement canvasElement;
	
	By lbl_risk1 = By.xpath("//div[text()='River flooding']");
	By lbl_risk2 = By.xpath("//div[text()='Surface flooding']");
	By lbl_risk3 = By.xpath("//div[text()='Coastal flooding']");
	By lbl_risk4 = By.xpath("//div[text()='Subsidence']");
	By lbl_risk5 = By.xpath("//div[text()='Landslide']");
	By lbl_risk6 = By.xpath("//div[text()='Wildfire']");
	By lbl_risk7 = By.xpath("//div[text()='Storm']");
	By lbl_risk8 = By.xpath("//div[text()='Hurricane']");
	By lbl_risk9 = By.xpath("//div[text()='Drought']");
	By lbl_risk10 = By.xpath("//div[text()='Extreme heat']");
	
	By PageWrapper=By.xpath("//div[@class='page-wrapper__gutter']");
	By addressearch = By.xpath("//input[contains(@class,'address-search__input')]");
	String addrsdropdwnselection = "//div[contains(text(),'+Data+') and contains(@class,'address-search')]";
	//div[contains(text(),'Volme Galerie, Friedrich-Ebert-Platz') and contains(@class,'address-search')]
	
    String Risk[] ={"River flooding","Surface flooding","Coastal flooding","Subsidence","Landslide","Wildfire","Storm","Hurricane","Drought","Extreme heat"};
     
    String RiskFilter;
    BufferedImage previousImage,currentImage;
    
    
	
	public void verifyHomepage() {
		
		if(isDisplayed(findElement(btn_close))){
			clickElement(btn_close);
		} 
		
		if(isDisplayed(findElement(lbl_heading1))){
			log.info("Verified ClimateX Logo in Homepage");
		} else {
			log.info("ClimateX Logo not found in Homepage!");
		}
	}
	
	public void verifyLogo() {
		if(isDisplayed(findElement(img_logo))){
			log.info("Verified ClimateX Logo in Homepage");
		} else {
			log.info("ClimateX Logo not found in Homepage!");
		}
	}
	
	public void clickHeatMapButton() {
		clickElement(btn_heatmap);
	}

	public void viewHeatmap() {
		canvasElement = findElement(canvas);
		if(isDisplayed(canvasElement)) {
			clickElement(canvas);		
		}
	}
	
	public void zoomoutMap() {
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e1) {
			e1.printStackTrace();
		}
		JavascriptExecutor executor = (JavascriptExecutor)driver;
		executor.executeScript("document.querySelector(\"#walkthrough-map > div.mapboxgl-canvas-container.mapboxgl-interactive.mapboxgl-touch-drag-pan.mapboxgl-touch-zoom-rotate > canvas\").style.zoom = '-100%'");
	    clickElement(PageWrapper);
	}
	
	public void captureScreenshot(String folderpath) throws IOException, InterruptedException {
		
		int ImagesDiff=0;
		Boolean OldFile=true;
		SoftAssert softassert=new SoftAssert();
		
		
		for(int iRisk=0;iRisk<Risk.length-1;iRisk++)
		{	
		RiskFilter=driver.findElement(By.xpath("//div[text()='"+Risk[iRisk]+"']")).getText();
		clickElement(By.xpath("//div[text()='"+Risk[iRisk]+"']"));
		Thread.sleep(7000);
		date = LocalDate.now();
		Instant now = Instant.now();
		Instant yesterday = now.minus(1, ChronoUnit.DAYS);
		log.info(now.toString().substring(0, 10));
		log.info(yesterday.toString().substring(0, 10));
		TakesScreenshot scrShot =((TakesScreenshot)findElement(canvas));
		//Call getScreenshotAs method to create image file
		File SrcFile=scrShot.getScreenshotAs(OutputType.FILE);
		
		//Copy file at destination
		
		//Screenshot logoImageScreenshot = new AShot().shootingStrategy(ShootingStrategies.viewportPasting(1000)).coordsProvider(new WebDriverCoordsProvider()).takeScreenshot(driver,findElement(canvas));
		// logoImageScreenshot = new AShot().coordsProvider(new WebDriverCoordsProvider())
			//	.takeScreenshot(driver,findElement(canvas));
		

		
		try {
			//ImageIO.write(logoImageScreenshot.getImage(), "png", new File(folderpath+RiskFilter+"-"+now.toString().substring(0, 13)+".png"));		
		 
		  File newfile = new File(folderpath + RiskFilter+"-"+now.toString().substring(0, 10)+".png");
		  FileUtils.copyFile(SrcFile, newfile);
		  File oldfile = new File(folderpath + RiskFilter+"-"+yesterday.toString().substring(0, 10)+".png");
			log.info("File path: "+newfile.getPath());
				if (oldfile.exists()) {
					currentImage = ImageIO.read(new File(folderpath+RiskFilter+"-"+now.toString().substring(0, 10)+".png"));			      	 
					log.info(RiskFilter + "Image File Captured");
					
					if(oldfile.exists())
					{
						 previousImage = ImageIO.read(new File(folderpath + RiskFilter+"-"+ yesterday.toString().substring(0, 10) +".png"));   
					      ImageDiffer imgDiff = new ImageDiffer();
					      ImageDiff diff = imgDiff.makeDiff(previousImage, currentImage);
//					      if(diff.hasDiff()==true)
//					      {
					      softassert.assertTrue(diff.hasDiff()==true, RiskFilter + "Heatmap Images should be Same");
					       log.info(RiskFilter + "Heatmap Images should be Same");
					      //}
//					      else {
//					    	  ImagesDiff=ImagesDiff+1;
//					       log.info(RiskFilter + " Images are Same");
//					      }
					}
				} else {
					OldFile=false;
					softassert.assertAll("Old images are not available to compare with new images");
					log.info("Old image file does not exist");
					
				}
				
		} catch (IOException e) {
			log.info("Errorrr: "+e.getMessage());
		}

		}
		
		//softassert.assertTrue((Risk.length==ImagesDiff) || (OldFile==false),"Images should be same. Check the folder" + folderpath );

		
	}
	
	public void compareScreenshotsCaptured(String folderpath) throws IOException {
	  
	  BufferedImage expectedImage = ImageIO.read(new File(folderpath+"Screenshot 2023-04-12 115200 - Copy.png"));
        	
	  BufferedImage actualImage = ImageIO.read(new File(folderpath + "Screenshot 2023-04-12 115200.png"));   
      ImageDiffer imgDiff = new ImageDiffer();
      ImageDiff diff = imgDiff.makeDiff(actualImage, expectedImage);
      if(diff.hasDiff()==true)
      {
       log.info("Images are Not Same");
      }
      else {
       log.info("Images are Same");
      }
	}
	
	public void searchForAddress(String address) throws InterruptedException
	{
		
		Thread.sleep(2000);
		if(isDisplayed(findElement(addressearch)))
		{
			clickElement(addressearch);
			enterKeys(addressearch, address);
			clickElement(stringReplace(addrsdropdwnselection, address));
		}
		else
		{
			log.info("Address search bar not displayed");
		}
	}
	
}
