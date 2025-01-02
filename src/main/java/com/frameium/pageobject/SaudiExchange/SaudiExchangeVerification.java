package com.frameium.pageobject.SaudiExchange;





import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import com.aventstack.extentreports.cucumber.adapter.ExtentCucumberAdapter;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import lombok.extern.flogger.Flogger;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;

import com.frameium.genericfunctions.GenericFunctions;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import org.testng.Reporter;
import org.testng.asserts.SoftAssert;
import io.cucumber.datatable.DataTable;


public class SaudiExchangeVerification extends GenericFunctions
{

	private WebDriver driver;

	 public SaudiExchangeVerification(WebDriver driver){
		 this.driver = driver;
	 }

	
      By OurMarkets=By.xpath("//*[text()='Our Markets']");
      By OurMarketArabic=By.xpath("//*[text()='الأسواق']");
	  By List=By.xpath("//*[text()='Listing']");
	  By ConvertTOArabic=By.xpath("(//*[@title='العربية'])[1]");
	  By Elements = By.xpath("//*[contains(text(), '\" + item + \"')]");

     
      By MyWatchlist=By.xpath("(//*[@id='watchlistNotLoggedUserId'])[1]");
      By Pricedown=By.xpath("(//*[@class='priceDown'])[4]");
      By listedSecurity=By.xpath("//*[text()='200 ‏‏‎ورقة مالية مدرجة ']");
     
      //By DateAndTime=By.xpath("(//*[@class='dateTime'])[1]");
	  By DateAndTime=By.xpath("//div[@class='dateTime']/strong");
      By markStatus=By.xpath("//div[@class='mktStatus']/strong[contains(text(),'Market Status')]");
	//*[contains(text(), '" + text + "')]")
      By SearchSymbol=By.xpath("//div[@class='searchtxt']/strong[text()='Search ']");
	  By priceElement=By.xpath("//*[@id='marketStatusbar2']/ul/li[1]/div[2]/div[2]");
	  By priceElementTASI=By.xpath("//*[@id='marketStatusbar2']//div[text()='TASI']/parent::div/following-sibling::div/div[2]");
	  By priceElementMT30=By.xpath("//*[@id='marketStatusbar2']//div[text()='MT30']/parent::div/following-sibling::div/div[2]");
	  By priceElementNomuC= By.xpath("//*[@id='marketStatusbar2']//div[text()='NomuC']/parent::div/following-sibling::div/div[2]");
	  By MT30=By.xpath("//*[@id=\"marketStatusbar2\"]/ul/li[2]/div[2]/div[2]");
	  By NomuC=By.xpath("//*[@id=\"marketStatusbar2\"]/ul/li[3]/div[2]/div[2]");
	  By ParallelMarket=By.xpath("(//*[text()='Parallel Market Watch'])[2]");
      By CaptialGoods=By.xpath("(//*[text()='Capital Goods'])[2]");
      By IndustryGroup=By.xpath("//*[text()='View All']");
      By Tableview=By.xpath("//*[@name='tableViewParameter']");
      By PriceData=By.xpath("//*[text()='Price Data ']");
      By MarketWatch=By.xpath("//table[@class='col-md-12 responsive nowrap table-bordered table-striped table-condensed cf no-footer dataTable']/tbody/tr/td[1]");


	public  static List<List<String>> productionTableData = new ArrayList<>();
	public  static List<List<String>> uatTableData = new ArrayList<>();




	//click on the Arabic convert button
	public void convertButton() throws InterruptedException
	{
		Thread.sleep(5000);
		driver.findElement(ConvertTOArabic).click();
	}
	
	private boolean isTextPresent(String text) {
        List<WebElement> elements = driver.findElements(By.xpath("//*[contains(text(), '" + text + "')]"));
        return !elements.isEmpty();
	}

	public void clickmenu() throws InterruptedException
	{
		Thread.sleep(4000);
        String Menuname = findElement(OurMarkets).getText();
        if (Menuname.equals("Our Markets")) {
            clickElement(OurMarkets);
            //System.out.println("Our Markets menu is there ");
			logToExtentReport("Log","Clicked on Our Markets dropdown");
			//ExtentCucumberAdapter.getCurrentStep().log(Status.PASS, MarkupHelper.createLabel("Log", ExtentColor.GREY));
			//ExtentCucumberAdapter.getCurrentStep().log(Status.PASS,"Clicked on Our Markets dropdown");
        } else {
            //System.out.println("Our Markets is not found ");
			logToExtentReport("Fail","Our Markets dropdown not found");
			//ExtentCucumberAdapter.getCurrentStep().log(Status.FAIL, MarkupHelper.createLabel("Log", ExtentColor.GREY));
			//ExtentCucumberAdapter.getCurrentStep().log(Status.FAIL," Our Markets dropdown not found");
        }
        Thread.sleep(4000);
	}
	public void OurMarketmenu() throws InterruptedException
	{
		Thread.sleep(4000);
        String Menuname = findElement(OurMarketArabic).getText();
        if (Menuname.equals("الأسواق")) {
            clickElement(OurMarketArabic);
			logToExtentReport("Pass","Clicked on الأسواق dropdown");
            //System.out.println("Our Markets menu is there ");
        } else {
			logToExtentReport("Fail","Unable to click on الأسواق dropdown");
            //System.out.println("Our Markets is not found ");
        }
	}
	public void ClickListMenu() throws InterruptedException
	{
		//Thread.sleep(4000);
        String Menuname = findElement(List).getText();
        if (Menuname.equals("Listing")) {
            clickElement(List);
            //System.out.println("Our List menu is there ");
			logToExtentReport("Log","Clicked on Listing dropdown");
			//ExtentCucumberAdapter.getCurrentStep().log(Status.PASS, MarkupHelper.createLabel("Pass", ExtentColor.GREEN));
			//ExtentCucumberAdapter.getCurrentStep().log(Status.PASS,"Clicked on Listing dropdown");
        } else {
			logToExtentReport("Fail","Listing dropdown not found");
            //System.out.println("Our List is not found ");
			//ExtentCucumberAdapter.getCurrentStep().log(Status.FAIL, MarkupHelper.createLabel("Fail", ExtentColor.RED));
			//ExtentCucumberAdapter.getCurrentStep().log(Status.FAIL,"Listing dropdown not found");
        }

	}
	public void verifyTexts(List<String> textsToVerify) throws InterruptedException {
	    
		
		boolean allTextsPresent = true;
        for (String text : textsToVerify) {
        	

            if (isTextPresent(text)) {
                
                //System.out.println(text + " is present on the webpage.");
                Assert.assertTrue(true, text + " is present on the webpage.");
				logToExtentReport("Pass","The text - '"+ text +"' is present in the webpage.");
				//ExtentCucumberAdapter.getCurrentStep().log(Status.PASS, MarkupHelper.createLabel("Pass", ExtentColor.GREEN));
				//ExtentCucumberAdapter.getCurrentStep().log(Status.PASS,"The text - '"+ text +"' is present in the webpage." );
                
            } else {
                System.out.println("Verifying text: " + text);
                System.out.println(text + " is not present on the webpage.");
                allTextsPresent = false;
				logToExtentReport("Fail","The text - '"+ text +"' is not present in the webpage.");
				//ExtentCucumberAdapter.getCurrentStep().log(Status.FAIL, MarkupHelper.createLabel("FAIL", ExtentColor.RED));
				//ExtentCucumberAdapter.getCurrentStep().log(Status.FAIL,"The text - '"+text+"' is not present in the webpage.");
                Assert.assertTrue(false);
               
            }
	        
        }
        
        
	}
	
	
	public String ColorOfMarketStatus() {
		  
	    String expectedColor = "rgba(255, 255, 255, 1)";
	    String MarketStatus = getTextColor(driver,markStatus);
	    
	    if ( MarketStatus.equals(expectedColor)) {
	        System.out.println(" market Status color is white");
			logToExtentReport("Pass","Text colour for header text 'Market Status' appears correctly.Actual Colour code - " + MarketStatus + "Expected Colour code - " + expectedColor);

	    } else {
	        System.out.println("market Status color is not white!");
			assert false;
			logToExtentReport("Fail","Text colour for header text 'Market Status' does not appear correctly.Actual Colour code - " + MarketStatus + "Expected Colour code - " + expectedColor);
	    }
	    return  MarketStatus ;
	   }
	
	public boolean ColorDateAndTime_de() {
		  
	    String expectedColor = "rgba(33, 37, 41, 1)";
	    boolean isDateandTime = isTextColor(driver,DateAndTime, expectedColor);
	    
	    if (isDateandTime ) {
	        //System.out.println("Date and time showing in white color");

			ExtentCucumberAdapter.getCurrentStep().log(Status.PASS, MarkupHelper.createLabel("Pass", ExtentColor.GREEN));
			ExtentCucumberAdapter.getCurrentStep().log(Status.PASS,"Colour A");
	    } else {
	        System.out.println("Date and time color is not white!");
	    }
	    return isDateandTime ;
	   }


	public String ColorDateAndTime() {

		String expectedColor = "rgba(255, 255, 255, 1)";
		String MarketStatus = getTextColor(driver,DateAndTime);

		if ( MarketStatus.equals(expectedColor)) {
			//System.out.println(" markStatu color is white");
			logToExtentReport("Pass","Text colour for header text 'Date and Time' appears correctly.Actual Colour code - " + MarketStatus + "Expected Colour code - " + expectedColor);


		} else {
			logToExtentReport("Fail","Text colour for header text 'Date and Time' does not appear correctly.Actual Colour code - " + MarketStatus + "Expected Colour code - " + expectedColor);
			assert false;

		}
		return  MarketStatus ;
	}
	public boolean ColorSearchSymbol_de() {
		  
	    String expectedColor = "rgba(33, 37, 41, 1)";
	    boolean issymbol = isTextColor(driver,SearchSymbol, expectedColor);
	    
	    if ( issymbol) {
	        System.out.println("Search symbol color is white color");
	    } else {
	        System.out.println("Search symbol color is not white!");
	    }
	    return  issymbol ;
	   }


	public String ColorSearchSymbol() {

		String expectedColor = "rgba(255, 255, 255, 1)";
		String MarketStatus = getTextColor(driver,SearchSymbol);

		if ( MarketStatus.equals(expectedColor)) {
			//System.out.println(" markStatu color is white");
			logToExtentReport("Pass","Text colour for header text 'Search' appears correctly.Actual Colour code - " + MarketStatus + "Expected Colour code - " + expectedColor);

		} else {
			//System.out.println("markStatu color is not white!");
			logToExtentReport("Fail","Text colour for header text 'Search' does not appear correctly.Actual Colour code - " + MarketStatus + "Expected Colour code - " + expectedColor);
			assert false;
		}
		return  MarketStatus ;
	}
	public boolean isTextBlue() {
	    String expectedColor = "rgba(0, 69, 227, 1)"; // Blue color in RGB format
	    boolean isBlue = isTextColor(driver,MyWatchlist, expectedColor);
	    
	    if (isBlue) {
	        System.out.println("My Watchlist text color is blue");
			ExtentCucumberAdapter.getCurrentStep().log(Status.PASS, MarkupHelper.createLabel("Pass", ExtentColor.GREEN));
			ExtentCucumberAdapter.getCurrentStep().log(Status.PASS,"Clicked on Listing dropdown");
	    } else {
	        System.out.println("My Watchlist text color is not blue!");
	    }
	    
	    return isBlue;
	}
	public boolean isPriceUp() {
	    String expectedColor = "rgba(5, 185, 89, 1)";// Green color
	    return isTextColor(driver,priceElement, expectedColor);
	}

	public void checkPriceColour(String priceValue) {
		if (priceValue.equals("TASI")){
			validatePriceColour(priceElement);
		}
		if (priceValue.equals("MT30")){
			validatePriceColour(priceElementMT30);
		}
		if (priceValue.equals("NomuC")){
			validatePriceColour(priceElementNomuC);
		}
	}
	public void validatePriceColour(By priceElement) {
		String greenColor = "rgba(5, 185, 89, 1)";// Green color
		String redColor = "rgba(246, 60, 60, 1)";// Green color
		String textColor =  getTextColor(driver,priceElement);
		String price = driver.findElement(priceElement).getText();
		if (price.contains("-")){
			if (textColor.equals(redColor)){
				logToExtentReport("Pass","Price is "+ price + " price colour is red");
			}else{
				logToExtentReport("Fail","Price is "+ price + " price colour is not red");
				assert false;
			}}
			if (!price.contains("-")){
				if (textColor.equals(greenColor)){
					logToExtentReport("Pass","Price is "+ price + " price colour is green");
				}else{
					logToExtentReport("Fail","Price is "+ price + " price colour is not green");
					assert false;
				}}

		//}
	}

	public boolean MT30PriceUp() {
	    String expectedColor = "rgba(5, 185, 89, 1)";// Green color
	    return isTextColor(driver,MT30, expectedColor);
	}
	public boolean NomucPriceUp() {
	    String expectedColor = "rgba(5, 185, 89, 1)";// Green color
	    return isTextColor(driver,NomuC, expectedColor);
	}
	public boolean isPriceDown() {
	    String expectedColor = "rgba(246, 60, 60, 1)"; // Red color
	    return isTextColor(driver,priceElement, expectedColor);
	}
	public boolean MT30PriceDown() {
	    String expectedColor = "rgba(246, 60, 60, 1)"; // Red color
	    return isTextColor(driver,MT30, expectedColor);
	}
	public boolean NomucPriceDown() {
	    String expectedColor = "rgba(246, 60, 60, 1)"; // Red color
	    return isTextColor(driver,NomuC, expectedColor);
	}
	
	public void checkPriceStatus() {

	    if (isPriceUp()) {
	        System.out.println("Price is up (green color)");
			//sd = driver.findElement(priceElement).getText();
			logToExtentReport("Log","Price is up (green color)");

		} else if (isPriceDown()) {
	        System.out.println("Price is down (red color)");
			logToExtentReport("Log","Price is down (red color)");
	        
	    } else {
	        System.out.println("Color does not match expected values");
			assert false;
	        
	    }
	}
	public void checkPriceStatusMT30() {
	    if (MT30PriceUp()) {
	        System.out.println("Price is up (green color)");
	        
	    } else if ( MT30PriceDown()) {
	        System.out.println("Price is down (red color)");
	        
	    } else {
	        System.out.println("Color does not match expected values");
	       
	    }
	}
	
	public void checkPriceStatusNomuC() {
	    if (NomucPriceUp()) {
	        System.out.println("Price is up (green color)");
	       
	    } else if (NomucPriceDown() ) {
	        System.out.println("Price is down (red color)");
	       
	    } else {
	        System.out.println("Color does not match expected values");
	        
	    }
	    
	}
	public void ParallelMarket() throws InterruptedException
	{
		//Thread.sleep(3000);
		clickElement(OurMarkets);
		logToExtentReport("Log","Clicked on 'Our Markets' dropdown");
		//Thread.sleep(2000);
		 clickElement(ParallelMarket);
		logToExtentReport("Log","Clicked on 'Parallel Market Watch'");
	}
	public void SelectCaptialwoods() throws InterruptedException
	{
		Thread.sleep(6000);
		clickElement(IndustryGroup);
		logToExtentReport("Log","Clicked on 'Industry Group' dropdown");
		Thread.sleep(2000);
		clickElement( CaptialGoods);
		logToExtentReport("Log","Clicked on dropdown item - 'Capital Goods'");
	}
	public void Tableview() throws InterruptedException
	{
		//Thread.sleep(4000);
		clickElement(Tableview);
		logToExtentReport("Log","Clicked on 'Table View' dropdown");
		//Thread.sleep(2000);
		clickElement(PriceData);
		logToExtentReport("Log","Clicked on dropdown item - 'Price Data'");
		
		Thread.sleep(2000);
	}
	 public void  getvalue()
	 {
		 java.util.List<WebElement> cells = driver.findElements((MarketWatch));

	        // Iterate through each cell in the first column
	        for (WebElement cell : cells) {
	            // Print the text of each cell
	            System.out.println(cell.getText());
	        }

	 }
	 
	 public void verifyData(DataTable dataTable) {
	        List<List<String>> rows = dataTable.asLists(String.class);

	        // Iterate over each row in the DataTable
	        for (List<String> row : rows) {
	            // Extract the data from each column
	            String materialName = row.get(0);
	            String materialCode = row.get(1);
	            String price = row.get(2);
	            String changeValue = row.get(3);
	            String changePercentage = row.get(4);
	            String open = row.get(5);
	            String high = row.get(6);
	            String low = row.get(7);

	            System.out.println("Material Name: " + materialName);
	            System.out.println("Material Code: " + materialCode);
	            System.out.println("Price: " + price);
	            System.out.println("Change Value: " + changeValue);
	            System.out.println("Change Percentage: " + changePercentage);
	            System.out.println("Open: " + open);
	            System.out.println("High: " + high);
	            System.out.println("Low: " + low);

	         // Assertions
	            Assert.assertNotNull("Material name should not be null", materialName);
	            Assert.assertNotNull("Material code should not be null", materialCode);
	            Assert.assertNotNull("Price should not be null", price);
	            Assert.assertNotNull("Change value should not be null", changeValue);
	            Assert.assertNotNull("Change percentage should not be null", changePercentage);
	            Assert.assertNotNull("Open should not be null", open);
	            Assert.assertNotNull("High should not be null", high);
	            Assert.assertNotNull("Low should not be null", low);
	            System.out.println("Assertions for Material: " + materialName);
	            System.out.println("Assertions passed: All values are not null");
	        }
	        
	        
	    }


		//Graph

	public void verifyUserInHome(){
		//Thread.sleep(5000);
		waitForByElement(priceElement);

	}

	public void moveToGraphElement  () throws InterruptedException{
		WebElement element = driver.findElement(By.id("tasiHomeGraph"));
		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", element);
		Thread.sleep(500);
	}
	public List<WebElement> getGraphYAxisData(){
		//Thread.sleep(5000);
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("window.scrollBy(0,900)");
		//Thread.sleep(5000);
		List<WebElement> AllList=driver.findElements(By.xpath("//div[@id='tasiHomeGraph']//*[contains(@class,'highcharts-yaxis-labels')]//*"));

		for(int i=0;i<AllList.size();i++) {

			String txt=AllList.get(i).getText();
			System.out.println(txt);
			logToExtentReport("Log","Y axis data - " + txt);
			//Thread.sleep(3000);
			//logToExtentReport("Log","Graph");
			System.out.println("YAxis Data displayed ");

		}
		return AllList;
	}

	public List<WebElement> getGraphXAxisData(){
		//Thread.sleep(5000);
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("window.scrollBy(0,900)");
		//Thread.sleep(5000);
		List<WebElement> AllList=driver.findElements(By.xpath("//div[@id='tasiHomeGraph']//*[contains(@class,'highcharts-xaxis-labels')]//*"));
		//AllList.get(1).click();
		for(int i=0;i<AllList.size();i++) {

			String txt=AllList.get(i).getText();
			System.out.println(txt);
			logToExtentReport("Log","X axis data - " + txt);
			//Thread.sleep(3000);
			//logToExtentReport("Log","Graph");
			System.out.println("XAxis Data displayed ");

		}
		return AllList;
	}

	public void compareListItems(List<WebElement> lista, List <WebElement> listb){

		for (int i=0; i< lista.size(); i++) {
			if (listb.get(i).getText().equals(lista.get(i).getText())) {
				logToExtentReport("Pass","Items match. Actual - " +listb.get(i).getText() + "Expected - "+lista.get(i).getText() );
				System.out.println("Equals..: " + listb.get(i));
			}
			else{
				logToExtentReport("Fail","Item mismatch. Actual - " +listb.get(i).getText() + "Expected - "+lista.get(i).getText() );
				assert false;
			}
		}

	}

	public void compareListItemsString(List<String> lista, List <String> listb){
		//int sizeOfTheShortestList = Math.min(lista.size(), listb.size());
		for (int i=0; i< lista.size(); i++) {
			if (listb.get(i).equals(lista.get(i))) {
				logToExtentReport("Pass","Items match. Actual - " +listb.get(i) + "Expected - "+lista.get(i) );
			}
			else{
				logToExtentReport("Fail","Item mismatch. Actual - " +listb.get(i) + "Expected - "+lista.get(i) );
				assert false;

			}
		}

	}

	public List<String> getGraphDataPoints() throws InterruptedException {
		List <String> graphDataPoint = new ArrayList<String>();
		Thread.sleep(2000);
		WebElement element= driver.findElement(By.id("tasiHomeGraph"));
		int elementWidth = element.getSize().getWidth();
		Actions actions = new Actions(driver);
		actions.moveToElement(element, 0, 0);
		actions.moveToElement(element, 10, 25).click().build().perform();

		element.click();
		for (int i = 0; i < 50; i += 4) {
			actions.moveToElement(element, i, 10).click().build().perform();

			WebElement Graphdata=driver.findElement(By.xpath("//div[@id='tasiHomeGraph']//*[local-name()='svg'][1]//*[name()='g'][19]//*[name()='g'][2]//*[name()='text']"));
			String graphDataPoints=Graphdata.getText();
			System.out.println(graphDataPoints);
			try {
				Thread.sleep(100);
				graphDataPoint.add(graphDataPoints);
				logToExtentReport("Log","Datapoint in graph - " + graphDataPoints );
			} catch (InterruptedException e) {

				e.printStackTrace();
			}

		}

		return graphDataPoint;
	}

	public void verifyTableHeaders(List<String> textsToVerify) {

		List<String> uiHeadText = new ArrayList<>();
		List<WebElement> uiHeaders = driver.findElements(By.xpath("//div[contains(@id,'marketWatchTable')]/div/div/div/table/thead/tr/th"));

		for(WebElement wb:uiHeaders){
			uiHeadText.add(wb.getText());
		}
		compareListItemsString(uiHeadText,textsToVerify);
	}


	public void getProdmarketWatchTable2Data() {

		WebElement table = driver.findElement(By.xpath("//table[@id='marketWatchTable2']"));
		List<WebElement> rows = table.findElements(By.tagName("tr"));

		for (WebElement row : rows) {

			List<String> rowData = new ArrayList<>();
			List<WebElement> cells = row.findElements(By.tagName("td"));

			for (WebElement cell : cells) {
				rowData.add(cell.getText());
			}
			// Add the row data to the table data
			productionTableData.add(rowData);
		}

		System.out.println("productionTableData : "+ productionTableData);

	}


	public void getUATmarketWatchTable2Data() {

		WebElement table = driver.findElement(By.xpath("//table[@id='marketWatchTable2']"));
		List<WebElement> rows = table.findElements(By.tagName("tr"));

		for (WebElement row : rows) {

			List<String> rowData = new ArrayList<>();
			List<WebElement> cells = row.findElements(By.tagName("td"));

			for (WebElement cell : cells) {
				rowData.add(cell.getText());
			}
			// Add the row data to the table data
			uatTableData.add(rowData);
		}

		System.out.println("uatTableData : "+ uatTableData);

	}




	public boolean compareTables(List<List<String>> table1, List<List<String>> table2) {
		// Check if the tables have the same number of rows
		if (table1.size() != table2.size()) {
			return false;
		}

		// Check each row and column
		for (int i = 0; i < table1.size(); i++) {
			List<String> row1 = table1.get(i);
			List<String> row2 = table2.get(i);
			//System.out.println("row1: "+ row1);
			//System.out.println("row2: "+ row2);
			// Check if rows have the same number of columns
			if (row1.size() != row2.size()) {
				return false;
			}
			// Compare each cell
			for (int j = 0; j < row1.size(); j++) {
				String cell1 = row1.get(j);
				String cell2 = row2.get(j);

				// Compare cell values
				if (!cell1.equals(cell2)) {
					return false;
				}
			}
		}
		// Tables are identical
		return true;
	}




}
