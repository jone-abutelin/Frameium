package com.frameium.genericfunctions;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.io.*;
import java.util.*;

import com.assertthat.selenium_shutterbug.core.Capture;
import com.assertthat.selenium_shutterbug.core.Shutterbug;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.cucumber.adapter.ExtentCucumberAdapter;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import org.apache.log4j.Logger;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.json.JSONArray;
import org.json.JSONObject;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import org.openqa.selenium.safari.SafariDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import org.openqa.selenium.support.ui.*;

import org.openqa.selenium.WebElement;
//import org.openqa.selenium.WindowType;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxBinary;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.ie.InternetExplorerOptions;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.Reporter;

import com.aventstack.extentreports.gherkin.model.Scenario;
import com.deque.axe.AXE;
import com.epam.healenium.SelfHealingDriver;
import com.frameium.accessibility.AxeDeque;
import com.frameium.baseclass.TestBase;
import com.frameium.browserconfiguration.BrowserType;
import com.frameium.browserconfiguration.FirefoxBrowser;
import com.frameium.configuration.ObjectReader;
import com.frameium.configuration.PropertyReader;
import com.frameium.logger.LoggerHelper;
import com.frameium.pageobject.Payload;
import com.frameium.resource.ResourceHelper;
import com.github.dockerjava.transport.DockerHttpClient.Response;

import io.appium.java_client.android.AndroidDriver;
import io.github.bonigarcia.wdm.WebDriverManager;

import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.file.Files;
import java.text.SimpleDateFormat;
import java.time.Duration;

import org.apache.log4j.Logger;
//import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import java.text.DateFormat;
import java.util.List;

import org.openqa.selenium.support.ui.ExpectedCondition;

public class GenericFunctions {

	private Logger log = LoggerHelper.getLogger(GenericFunctions.class);
	protected static AndroidDriver androiddriver;
	private static FileInputStream file;
	public static Properties OR;
	public static WebDriver driver;
	public static SelfHealingDriver driver2;

	private static XSSFSheet ExcelWSheet;
	private static XSSFWorkbook ExcelWBook;
	public static File reportDirectery;
	private static URL scriptUrl;
	int totalViolation;
	int critical;
	int serious;
	int moderate;
	int minor;

	private static final String KEY_SAFARI_WEBDRIVER = "webdriver.safari.driver";
	private static final String SAFARI_WEBDRIVER_PATH = "/usr/bin/safaridriver";
	private static String allureBinPath = System.getProperty("user.dir")
			+ "\\.allure\\allure-2.8.1\\bin";
	private static String allureResultsPath = System.getProperty("user.dir")
			+ "\\allure-results";
	public static String selfhealingoption;
	List<Integer> Severitylist = new ArrayList<>();


	public GenericFunctions(WebDriver driver){
		this.driver = driver;
	}

	public GenericFunctions(){

	}


	
	public void DblClick(By element)
	{
		Actions act = new Actions(driver);         

		
        //Double click on element
        WebElement ele = findElement(element);
        act.doubleClick(ele).perform();
	}

	/**
	 * This method will check element is selected
	 */
	public boolean isselectedBy(By element) {
		try {
			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
			//WebDriverWait wait = new WebDriverWait(driver, 30);
			WebElement Element = wait.until(ExpectedConditions.visibilityOfElementLocated(element));
			Element.isSelected();
			log.info("element is selected.." + Element.getText());
			return true;
		} catch (Exception e) {
			log.error("element is not selected..", e.getCause());
			return false;
		}
	}

	/**
	 * This method will replace string with value
	 */
	public By stringReplace(String old, String newValue) {
		String str = old.replace("+Data+", newValue);
		By Xpath = By.xpath(str);
		return Xpath;

	}

	/**
	 * This method will find element in page
	 */
	public WebElement findElement(By byElement) {
		//WebDriverWait wait = new WebDriverWait(driver, 30);
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(3000));
		WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(byElement));
		return element;
	}
	/*
	 * Method to automate command line to run allure report
	 *
	 * */
//	public static void openCmdPromptAndRunAllureServe() {
//		try {
//			// Open command prompt
//			ProcessBuilder processBuilder = new ProcessBuilder("cmd.exe", "/c", "start");
//			Process process = processBuilder.start();
//			process.waitFor();
//			System.out.println("Command prompt opened. Exit Value: " + process.exitValue());
//			Thread.sleep(2000);
//			// Type "allure serve" using Robot class
//			typeAllureServe();
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//	}
//
//	private static void typeAllureServe() throws AWTException {
//		Robot robot = new Robot();
//		// Type "allure serve"
//		typeString(robot, "allure serve");
//		robot.keyPress(KeyEvent.VK_ENTER);
//		robot.keyRelease(KeyEvent.VK_ENTER);
//	}
//
//	private static void typeString(Robot robot, String str) {
//		for (char c : str.toCharArray()) {
//			int keyCode = KeyEvent.getExtendedKeyCodeForChar(c);
//			robot.keyPress(keyCode);
//			robot.keyRelease(keyCode);
//		}
//	}
//To run and display allure report
public static void generateAllureReport() {
	try {
		// Change directory and run allure serve with the results path
		ProcessBuilder processBuilder = new ProcessBuilder(
				"cmd.exe", "/c",
				"cd /d " + allureBinPath + " && allure.bat serve " + allureResultsPath
		);
		processBuilder.redirectErrorStream(true);
		Process process = processBuilder.start();

		// Optional: Print any output from the process
		BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
		String line;
		boolean reportGenerated = false;
		while ((line = reader.readLine()) != null) {
			System.out.println(line);
			// Check for a specific output that indicates the report is generated
			if (line.contains("Server started at")) {
				reportGenerated = true;
				break;
			}
		}

		// If the report is generated, destroy the process
		if (reportGenerated) {
			process.destroy();
			System.out.println("Allure report generated and process terminated.");
		}

		// Wait for the process to complete
		int exitCode = process.waitFor();
		System.out.println("Command completed with exit code: " + exitCode);

	} catch (Exception e) {
		e.printStackTrace();
	}
}




	public void setDriver() {
		//System.setProperty("webdriver.chrome.driver",
				//ResourceHelper.getResourcePath("src/main/resources/drivers/chromedriver.exe"));
		//ChromeDriver driver = new ChromeDriver();
		//log.info("Created instance for Chrome driver");

		String browsertype = getConfigProperty("browserType");
		String headless = getConfigProperty("headless");
		String selfhealingoption = getConfigProperty("selfhealing");

		if (browsertype.toString().equalsIgnoreCase("Chrome")) {

			// Setting webdriver.chrome.driver property
			System.setProperty("webdriver.chrome.driver",
					ResourceHelper.getResourcePath("src/main/resources/drivers/chromedriver.exe"));
			// Create a new instance of the Chrome driver
			ChromeOptions chromeoptions = new ChromeOptions();
			if (headless.equalsIgnoreCase("yes")) {
				chromeoptions.addArguments("--headless=new", "window-size=1920,1080", "--no-sandbox");
				chromeoptions.addArguments("--disable-gpu");
				//chromeoptions.setHeadless(true);
				chromeoptions.addArguments("window-size=1920,1080");
				chromeoptions.addArguments("--disable-gpu");
				
			}
			chromeoptions.addArguments("--disable-notifications");
			chromeoptions.addArguments("--remote-allow-origins=*");
			//chromeoptions.addArguments("--user-data-dir=C:/Users/RijuJohn/AppData/Local/Google/Chrome2/Chrome/UserData/");
			if (selfhealingoption.equalsIgnoreCase("no")){
			driver = new ChromeDriver(chromeoptions); //(uncomment and comment below 2)}
			//public static WebDriver driver;
			//public static SelfHealingDriver driver2;
			}
			else {
			driver = driver2;
			WebDriver delegate = new ChromeDriver(chromeoptions);
			driver = SelfHealingDriver.create(delegate);
			//driver2.get("https://www.google.com/"); SelfHealingDriver
			//ChromeDriver driver2 = new ChromeDriver();
			log.info("Created instance for Chrome driver");}
			
			/////Trial////////////
			/*
			driver.get("https://www.saudiexchange.sa/");
			WebElement element1 = driver.findElement(By.className("highcharts-graph"));
			String df = element1.getAttribute("d");
			System.out.print(df);
			
			WebElement element = driver.findElement(By.id("tasiHomeGraph"));
	        
	        // Get the size of the element
	        int elementWidth = element.getSize().getWidth();
	        
	        // Get the starting position of the element
	        int startX = element.getLocation().getX();
	        int startY = element.getLocation().getY();
	        
	        // Define the offset to move the mouse
	        int offset = elementWidth / 10; // Adjust as needed
	        
	        // Perform the hover action
	        Actions actions = new Actions(driver);
	        
	        // Start hovering from left to right
	        actions.moveToElement(element, 0, 0);
	        actions.moveToElement(element, 10, 25).click().build().perform();
	        
	        
	        element.click();
	        for (int i = 0; i < 1000; i++) {
	        	actions.moveToElement(element, i, 10).click().build().perform();
	            //actions.moveByOffset(offset, 0).perform();
	            // Optionally, you can add a small pause between movements
	            try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
	        }
			
			
			int y = 5;
			
			
			
			
			
			*/
			///////Trial///////////

		}


		else if(browsertype.toString().equalsIgnoreCase("Safari")){

			System.setProperty(KEY_SAFARI_WEBDRIVER, SAFARI_WEBDRIVER_PATH);

			// Instantiate a SafariDriver class.
			driver = new SafariDriver();

		}
		else if (browsertype.toString().equalsIgnoreCase("Edges")) {

			// Setting webdriver.chrome.driver property
			
			System.setProperty("webdriver.edge.driver",
					ResourceHelper.getResourcePath("src/main/resources/drivers/msedgedriver.exe"));
			// Create a new instance of the Edge driver
			// WebDriverManager.edgedriver().setup();
			EdgeOptions edgeOptions = new EdgeOptions();
			edgeOptions.addArguments("--disable-notifications");
			edgeOptions.addArguments("--remote-allow-origins=*");
			driver = new EdgeDriver(edgeOptions); //(uncomment and comment below 2)
			//WebDriver delegate = new EdgeDriver(edgeOptions);
			//driver = SelfHealingDriver.create(delegate);
			
			
			log.info("Created instance for Edge driver");
			
		} else if (browsertype.toString().equalsIgnoreCase("Edge")) {
			
			System.setProperty("webdriver.ie.driver",
					ResourceHelper.getResourcePath("src/main/resources/drivers/IEDriverServer.exe"));
			//System.setProperty("webdriver.edge.driver",
					//ResourceHelper.getResourcePath("src/main/resources/drivers/msedgedriver.exe"));
			
			//EdgeOptions edgeOptions = new EdgeOptions();
			//edgeOptions.setCapability("ie.browserCommandLineArguments", "-ieMode");
			//edgeOptions.addArguments("--disable-notifications");
			//edgeOptions.addArguments("--remote-allow-origins=*");
	        //edgeOptions.setCapability("ie.usePerProcessProxy", true);
	        //edgeOptions.setCapability("ie.ensureCleanSession", true);

	        // Launch Edge WebDriver with desired capabilities
	        //driver = new EdgeDriver(edgeOptions);
			
			
			
			InternetExplorerOptions ieOptions = new InternetExplorerOptions();
			ieOptions.attachToEdgeChrome();
			ieOptions.ignoreZoomSettings();
			//ieOptions.withInitialBrowserUrl("https://www.google.com/");
			//ieOptions.setCapability(InternetExplorerDriver.NATIVE_EVENTS, false);
			ieOptions.withInitialBrowserUrl("http://www.bing.com");
			ieOptions.withEdgeExecutablePath("C:/Program Files (x86)/Microsoft/Edge/Application/msedge.exe");  
			//ieOptions.withEdgeExecutablePath("src/main/resources/drivers/IEDriverServer.exe");
			driver = new InternetExplorerDriver(ieOptions);
	        //driver.get("http://www.bing.com");
	        WebElement elem = driver.findElement(By.id("sb_form_q"));
	        //elem.sendKeys("WebDriver", Keys.RETURN);
	        elem.sendKeys("WebDriver");
			
	        //WebElement textSearch = driver.findElement(By.name("q"));
	        //WebElement btnSearch = driver.findElement(By.name("btnG"));
	        //textSearch.sendKeys("TestHouse",Keys.RETURN);
	        //textSearch.sendKeys("TestHouse");
	        //btnSearch.click();
	        WebElement tt = driver.findElement(By.className("1"));
	        tt.click();
	        
	        //elem.sendKeys("WebDriver", Keys.RETURN);
			
			// Setting webdriver.chrome.driver property
			
			//System.setProperty("webdriver.edge.driver",
					//ResourceHelper.getResourcePath("src/main/resources/drivers/msedgedriver.exe"));
			// Create a new instance of the Edge driver
			// WebDriverManager.edgedriver().setup();
			//EdgeOptions edgeOptions = new EdgeOptions();
			//edgeOptions.addArguments("--disable-notifications");
			//edgeOptions.addArguments("--remote-allow-origins=*");
			//driver = new EdgeDriver(edgeOptions); //(uncomment and comment below 2)
			//WebDriver delegate = new EdgeDriver(edgeOptions);
			//driver = SelfHealingDriver.create(delegate);
			
			
			log.info("Created instance for Edge driver WITH IE Compatibility");	

		} else if (browsertype.toString().equalsIgnoreCase("Firefox")) {

			// Setting webdriver.gecko.driver property
			System.setProperty("webdriver.gecko.driver",
					ResourceHelper.getResourcePath("src/main/resources/drivers/geckodriver.exe"));
			// Create a new instance of the Firefox driver
			FirefoxOptions firefoxoptions = new FirefoxOptions();
			if (headless.equalsIgnoreCase("yes")) {
				firefoxoptions.addArguments("--headless");
			}

			//driver = new FirefoxDriver(firefoxoptions);(uncomment and comment below 2)
			WebDriver delegate = new FirefoxDriver(firefoxoptions);
			driver = SelfHealingDriver.create(delegate);
			log.info("Created instance for Firefox driver");

		} else {
			log.info("Missing driver value! Please setup driver through config file");
		}
	}

	/**
	 * This method will find element
	 */
	public WebElement element(By byElement) {
		WebElement ele = driver.findElement(byElement);
		return ele;
	}
	/**
	 * Clean folder
	 */
	public void cleanFolder(String folderPath) {
		File folder = new File(folderPath);
		if (folder.exists() && folder.isDirectory()) {
			File[] files = folder.listFiles();
			if (files != null) {
				for (File file : files) {
					file.delete();
				}
			}
		}
	}
	
	public WebElement clearElement(By byElement) {


        WebElement textBox = findElement(byElement);

        textBox.clear();

        return textBox;
	}
	/*
	 * Click an element using JS
	 * */
	public void clickElementUsingJS(By byElement) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(2000));
		WebElement element = wait.until(ExpectedConditions.elementToBeClickable(byElement));
		// Scroll to the element
		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", element);
		// Small wait to ensure the scroll has completed
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			Thread.currentThread().interrupt();
		}
		// Click the element using JavaScriptExecutor
		((JavascriptExecutor) driver).executeScript("arguments[0].click();", element);
	}

	/*
	 * Salesforce calendar
	 *
	 * */

	public void enterDateCalendar(By dateInputLocator, By yearDropdownLocator, By monthDropdownLocator,
								  By previousMonthButtonLocator, By nextMonthButtonLocator,
								  String date) throws InterruptedException {
		// Split the date string to extract day, month, and year
		String[] parts = date.split("-");
		String day = parts[0];
		String month = parts[1];
		String year = parts[2];

		// Click on the date input field to open the date picker
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(1000));
		WebElement dateInput = wait.until(ExpectedConditions.presenceOfElementLocated(dateInputLocator));
		//dateInput.click();
		clickElement(dateInput);
		Thread.sleep(1000);
		// Click on the year dropdown to open the dropdown menu
		WebElement yearDropdown = wait.until(ExpectedConditions.elementToBeClickable(yearDropdownLocator));
		yearDropdown.click();

		// Select the year
		By selectYear = By.xpath("//div[@class='slds-select_container']//select[@class='slds-select']/option[@value='" + year + "']");
		WebElement yearOption = wait.until(ExpectedConditions.presenceOfElementLocated(selectYear));
		yearOption.click();

		// Select the month
		Thread.sleep(1000);
		WebElement monthDropdown = driver.findElement(monthDropdownLocator);
		String currentMonth = monthDropdown.getText();
		while (!currentMonth.equalsIgnoreCase(month)) {
			// Check if we need to navigate to the previous or next month
			int currentMonthIndex = getMonthIndex(currentMonth);
			int targetMonthIndex = getMonthIndex(month);

			if (currentMonthIndex > targetMonthIndex) {
				driver.findElement(previousMonthButtonLocator).click();
			} else {
				driver.findElement(nextMonthButtonLocator).click();
			}

			currentMonth = monthDropdown.getText();
		}

		// Scroll to and select the day
		Thread.sleep(3000);
		int monthNumber = getMonthInNum(month);
		String monthNum = monthNumber < 10 ? "0" + monthNumber : String.valueOf(monthNumber);
		String formattedDate = year + "-" + monthNum + "-" + day;
		System.out.println("Formatted Date: " + formattedDate);

		By dateLocator = By.xpath("//table[contains(@class,'datepicker')]/tbody/tr/td[@data-value='" + formattedDate + "']");
		WebElement dateElement = driver.findElement(dateLocator);
		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", dateElement);
		wait.until(ExpectedConditions.elementToBeClickable(dateElement)).click();
	}

	private int getMonthInNum(String month) {
		// Create a mapping of month names to their corresponding numbers
		Map<String, String> monthMap = new HashMap<>();
		monthMap.put("January", "01");
		monthMap.put("February", "02");
		monthMap.put("March", "03");
		monthMap.put("April", "04");
		monthMap.put("May", "05");
		monthMap.put("June", "06");
		monthMap.put("July", "07");
		monthMap.put("August", "08");
		monthMap.put("September", "09");
		monthMap.put("October", "10");
		monthMap.put("November", "11");
		monthMap.put("December", "12");

		// Return the month number corresponding to the given month name
		return Integer.parseInt(monthMap.get(month));
	}

	private int getMonthIndex(String monthName) {
		String[] months = {"January", "February", "March", "April", "May", "June", "July", "August", "September", "October", "November", "December"};
		for (int i = 0; i < months.length; i++) {
			if (months[i].equalsIgnoreCase(monthName)) {
				return i;
			}
		}
		return -1;
	}

	/*
	 *Scroll till page last
	 */
	public void scrollTillPageLast() throws InterruptedException {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		while (!(Boolean) js.executeScript("return document.documentElement.scrollHeight <= document.documentElement.scrollTop + window.innerHeight")) {
			js.executeScript("window.scrollTo(0, document.documentElement.scrollHeight)");
			Thread.sleep(2000);

		}
	}
	

	/**
	 * This method will generate random number
	 */
	public String getRandomNumberString(String prefix) {
		Random rnd = new Random();
		return prefix + String.valueOf(rnd.nextInt((9999 - 1000)) + 1000);
	}

	/**
	 * This method will click on element
	 */
	public WebElement clickElement(By byElement) {
		
		//WebDriverWait wait = new WebDriverWait(driver, 30);
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10000));
		//By EmailAddress = By.xpath("//input[@type='email']");
		//WebElement element = driver.findElement(EmailAddress);
		WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(byElement));
		element.click();
		return element;
	}

	/**
	 * This method will click element which is interactable
	 */
	public WebElement clickElementtobeInteractable(By byElement) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(60));
		//WebDriverWait wait = new WebDriverWait(driver, 60);
		WebElement element = wait.until(ExpectedConditions.elementToBeClickable(byElement));
		element.click();
		return element;
	}

	/**
	 * This method will maximize window
	 */
	public void maximize() {
		driver.manage().window().maximize();
	}

	/**
	 * This method will gets url of application
	 */
	public String getApplicationUrl(String url) {
		System.out.println("driver----->" + driver);
		driver.get(url);
		driver.manage().window().maximize();
		//Results results = axeSelenium.run(axedriver);
		//scriptUrl = AxeDeque.class.getResource("/axe.min.js");
		//List<String> results = AXE.Builder.with(driver).analyze();
		//JSONObject responseJson = new AXE.Builder(driver, scriptUrl).analyze();
		// logExtentReport("navigating to ..."+url);
		return url;
	}
	// to click on dynamically changing elements (index changes)

	public void clickDynamicElement(By xpath) throws InterruptedException {
		Thread.sleep(4000);
		List<WebElement> dynamicBtns = driver.findElements(xpath);
		System.out.println("No.of dynamic buttons found >> "+ dynamicBtns.size());
		for (WebElement element : dynamicBtns) {
			if (element.isDisplayed()) {
				clickElementUsingJavaScript(element);
				System.out.println("Clicked on dynamic button.... " );
				break;
			} else {
				System.out.println("Element not interactable!!!" );
			}
		}
	}

// to click on dynamically changing elements (index changes) and send data to it using sendkeys

	public void dynamicElementPassInput(By xpath, String keys) throws InterruptedException {
		Thread.sleep(4000);
		List<WebElement> dynamicBtns = driver.findElements(xpath);
		System.out.println("No.of remove buttons found >> "+ dynamicBtns.size());
		for (WebElement element : dynamicBtns) {
			if (element.isDisplayed()) {
				clickElementUsingJavaScript(element);
				element.sendKeys(keys);
				System.out.println("Clicked on remove button.... " );
				break;
			} else {
				System.out.println("Element not interactable!!!" );
			}
		}
	}

	/**
	 * This method will set report
	 */
	public void setReport() {
		ObjectReader.reader = new PropertyReader();
		reportDirectery = new File(ResourceHelper.getResourcePath("src/main/resources/screenShots"));
	}

	/**
	 * This method will set excel file
	 */
	public static void setExcelFile(String Path, String SheetName) throws Exception {
		try {
			// Open the Excel file
			FileInputStream ExcelFile = new FileInputStream(Path);
			// Access the required test data sheet
			ExcelWBook = new XSSFWorkbook(ExcelFile);
			ExcelWSheet = ExcelWBook.getSheet(SheetName);
		} catch (Exception e) {
			throw (e);
		}
	}

	/**
	 * This method will move to element
	 */
	public void MoveToElement(By by) {
		try {
			Actions actions = new Actions(driver);
			WebElement element = driver.findElement(by);
			actions.moveToElement(element).perform();
		} catch (Exception e) {

		}
	}

	// Click on WebElement
	public WebElement click1(WebElement element) {
		element.click();
		return element;
	}

	// Passing value using Send Keys
	public void enterKeys(By element, String key) {
		WebElement textBox = findElement(element);
		textBox.sendKeys(key);
	}

	// Passing value using Send Keys
	public String EnterKeys(By element, String key) {
		WebElement textBox = findElement(element);
		textBox.sendKeys(key);
		return key;
	}

	// Passing value using Send Keys
	public void enterKeys(WebElement element, String key) {
		element.sendKeys(key);
	}

	// Keyboard Actions
	public void keyAction(String action) {
		Actions act = new Actions(driver);
		if (action == "ENTER" || action == "Enter") {
			act.sendKeys(Keys.ENTER).build().perform();
		} else if (action == "PAGE_DOWN") {
			act.sendKeys(Keys.PAGE_DOWN).build().perform();
		}
	}

	/**
	 * this method will do Slider Movement
	 * 
	 * @param sliderObj,moveCount
	 */

	public void sliderMovement(WebElement sliderObj, String movecount) {
		Actions actions = new Actions(driver);
		for (int initialCount = 1000; initialCount < Integer.parseInt(movecount); initialCount += 50) {
			sliderObj.sendKeys(Keys.ARROW_RIGHT);
		}
	}

	/**
	 * this method will switchToFrame based on frame index
	 * 
	 * @param frameIndex
	 */
	public void switchToFrame(int frameIndex) {
		driver.switchTo().frame(frameIndex);
		log.info("switched to :" + frameIndex + " frame");
	}

	/**
	 * this method will switchToFrame based on frame name
	 * 
	 * @param frameName
	 */
	public void switchToFrame(String frameName) {
		driver.switchTo().frame(frameName);
		log.info("switched to :" + frameName + " frame");
	}

	public void switchToFrame(WebElement element) {
		driver.switchTo().frame(element);
	}

	public void switchToDefault() {
		driver.switchTo().defaultContent();
	}

	/**
	 * These methods will drop down using select class
	 */
	public void selectUsingValue(WebElement element, String value) {
		Select select = new Select(element);
		log.info("selectUsingValue and value is: " + value);
		select.selectByValue(value);
	}

	public void selectUsingIndex(WebElement element, int index) {
		Select select = new Select(element);
		log.info("selectUsingIndex and index is: " + index);
		select.selectByIndex(index);
	}

	public void selectUsingVisibleText(WebElement element, String visibleText) {
		Select select = new Select(element);
		log.info("selectUsingVisibleText and visibleText is: " + visibleText);
		select.selectByVisibleText(visibleText);
	}

	public void deSelectUsingValue(WebElement element, String value) {
		Select select = new Select(element);
		log.info("deSelectUsingValue and value is: " + value);
		select.deselectByValue(value);
	}

	public void deSelectUsingIndex(WebElement element, int index) {
		Select select = new Select(element);
		log.info("deSelectUsingIndex and index is: " + index);
		select.deselectByIndex(index);
	}

	public void deSelectUsingVisibleText(WebElement element, String visibleText) {
		Select select = new Select(element);
		log.info("deselectByVisibleText and visibleText is: " + visibleText);
		select.deselectByVisibleText(visibleText);
	}

	public List<String> getAllDropDownData(WebElement element) {
		Select select = new Select(element);
		List<WebElement> elementList = select.getOptions();
		List<String> valueList = new LinkedList<String>();
		for (WebElement ele : elementList) {
			log.info(ele.getText());
			valueList.add(ele.getText());
		}
		return valueList;
	}

	/// Window manager
	/**
	 * This method will switch to parent window
	 */
	public void switchToParentWindow() {
		log.info("switching to parent window...");
		driver.switchTo().defaultContent();
	}

	/**
	 * This method will switch to child window based on index
	 * 
	 * @param index
	 */
	public void switchToWindow(int index) {
		Set<String> windows = driver.getWindowHandles();
		int i = 1;
		for (String window : windows) {
			if (i == index) {
				log.info("switched to : " + index + " window");
				driver.switchTo().window(window);
			} else {
				i++;
			}
		}
	}

	/**
	 * This method will close all tabbed window and switched to main window
	 */
	public void closeAllTabsAndSwitchToMainWindow() {
		Set<String> windows = driver.getWindowHandles();
		String mainwindow = driver.getWindowHandle();

		for (String window : windows) {
			if (!window.equalsIgnoreCase(mainwindow)) {
				driver.close();
			}
		}
		log.info("switched to main window");
		driver.switchTo().window(mainwindow);
	}

	public void closeBrowser() {
				driver.close();
	}

	/**
	 * This method will do browser back navigation
	 */
	public void navigateBack() {
		log.info("navigating back");
		driver.navigate().back();
	}

	/**
	 * This method will do browser forward navigation
	 */
	public void navigateForward() {
		log.info("navigating forward");
		driver.navigate().forward();
	}

	public void browserRefresh() {
		log.info("navigating back");
		driver.navigate().refresh();
	}

	//// JavaScript
	/**
	 * This method will execute java script
	 * 
	 * @param script
	 * @return
	 */
	public Object executeScript(String script) {
		JavascriptExecutor exe = (JavascriptExecutor) driver;
		return exe.executeScript(script);
	}

	/**
	 * This method will execute Java script with multiple arguments
	 * 
	 * @param script
	 * @param args
	 * @return
	 */
	public Object executeScript(String script, Object... args) {
		JavascriptExecutor exe = (JavascriptExecutor) driver;
		return exe.executeScript(script, args);
	}

	
	
	
	/**
	 * This method will scroll till element
	 * 
	 * @param element
	 */
	public void scrollToElement(WebElement element) {
		log.info("scroll to WebElement...");
		executeScript("window.scrollTo(arguments[0],arguments[1])", element.getLocation().x, element.getLocation().y);
	}

	/**
	 * Scroll till element and click
	 * 
	 * @param element
	 */
	public void scrollToElementAndClick(WebElement element) {
		scrollToElement(element);
		element.click();
		log.info("element is clicked: " + element.toString());
	}

	/**
	 * Scroll till element view
	 * 
	 * @param element
	 */
	public void scrollIntoView(WebElement element) {
		log.info("scroll till web element");
		executeScript("arguments[0].scrollIntoView()", element);
	}

	/**
	 * Scroll till element view and click
	 * 
	 * @param element
	 */
	public void scrollIntoViewAndClick(WebElement element) {
		scrollIntoView(element);
		element.click();
		log.info("element is clicked: " + element.toString());
	}

	/**
	 * This method will scroll down vertically
	 */
	public void scrollDownVertically() {
		log.info("scrolling down vertically...");
		executeScript("window.scrollTo(0,document.body.scrollHeight)");
	}

	/**
	 * This method will scroll up vertically
	 */
	public void scrollUpVertically() {
		log.info("scrolling up vertically...");
		executeScript("window.scrollTo(0,-document.body.scrollHeight)");
	}

	/**
	 * This method will scroll till given pixel (e.g=1500)
	 * 
	 * @param pixel
	 */
	public void scrollDownByPixel(int pixel) {
		executeScript("window.scrollBY(0," + pixel + ")");
	}

	public void scrollUpByPixel(int pixel) {
		executeScript("window.scrollBY(0,-" + pixel + ")");
	}

	/**
	 * This method will zoom screen by 100%
	 */
	public void zoomInBy100Percentage() {
		executeScript("document.body.style.zoom='100%'");
	}

	/**
	 * This method will zoom screen by 60%
	 */
	public void zoomInBy60Percentage() {
		executeScript("document.body.style.zoom='40%'");
	}

	/**
	 * This method will click on element
	 * 
	 * @param element
	 */
	public void clickElement(WebElement element) {
		executeScript("arguments[0].click();", element);
		System.out.println("clickElement action is completed :::::::::    ");
	}
	public void clickElementUsingJavaScript(WebElement element) {
		//WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(40));
		//wait.until(ExpectedConditions.elementToBeClickable(element));
		// Scroll to the element
		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", element);
		// Small wait to ensure the scroll has completed
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			Thread.currentThread().interrupt();
		}
		// Click the element using JavaScriptExecutor
		((JavascriptExecutor) driver).executeScript("arguments[0].click();", element);
	}

	/// Alerts
	public Alert getAlert() {
		log.info("alert test: " + driver.switchTo().alert().getText());
		return driver.switchTo().alert();
	}

	public void acceptAlert() {
		getAlert().accept();
		log.info("accept Alert is done...");
	}

	public void dismissAlert() {
		getAlert().dismiss();
		log.info("dismiss Alert is done...");
	}

	public String getAlertText() {
		String text = getAlert().getText();
		log.info("alert text: " + text);
		return text;
	}

	public boolean isAlertPresent() {
		try {
			driver.switchTo().alert();
			log.info("alert is present");
			return true;
		} catch (NoAlertPresentException e) {
			log.info(e.getCause());
			return false;
		}
	}

	public void acceptAlertIfPresent() {
		if (isAlertPresent()) {
			acceptAlert();
		} else {
			log.info("Alert is not present..");
		}
	}

	public void dismissAlertIfPresent() {
		if (isAlertPresent()) {
			dismissAlert();
		} else {
			log.info("Alert is not present..");
		}
	}

	public void acceptPrompt(String text) {
		if (isAlertPresent()) {
			Alert alert = getAlert();
			alert.sendKeys(text);
			alert.accept();
			log.info("alert text: " + text);
		}
	}

	// Action clas
	public void mouseOver(WebElement element) {
		Actions builder = new Actions(driver);
		Action mouseOverHome = builder.moveToElement(element).build();

	}

	/**
	 * This method will check element displayed
	 */
	public boolean isDisplayedBy(By element) {
		try {
			//WebDriverWait wait = new WebDriverWait(driver, 30);
			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
			WebElement Element = wait.until(ExpectedConditions.visibilityOfElementLocated(element));
			Element.isDisplayed();
			log.info("element is Displayed.." + Element.getText());
			return true;
		} catch (Exception e) {
			log.error("element is not Displayed..", e.getCause());
			return false;
		}
	}

	public boolean clickObject(By element) {
		try {
			//WebDriverWait wait = new WebDriverWait(driver, 30);
			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
			WebElement Element = wait.until(ExpectedConditions.visibilityOfElementLocated(element));
			Element.click();
			//log.info("element is clicked.." + Element.getText());
			return true;
		} catch (Exception e) {
			//log.error("element is not Displayed..", e.getCause());
			return false;
		}
	}
	public boolean isDisplayed(WebElement element) {
		try {
			element.isDisplayed();
			log.info("element is Displayed.." + element.getText());
//			TestBase.logExtentReport("element is Displayed.."+element.getText());
			return true;
		} catch (Exception e) {
			log.error("element is not Displayed..", e.getCause());
//			TestBase.logExtentReport("element is not Displayed.."+e.getMessage());
			return false;
		}
	}

	public boolean isEnabled(WebElement element) {
		try {
			element.isEnabled();
			// log.info("element is Enabled.."+element.getText());
//			TestBase.logExtentReport("element is Displayed.."+element.getText());
			return true;
		} catch (Exception e) {

			log.error("element is not Displayed..", e.getCause());
//			TestBase.logExtentReport("element is not Displayed.."+e.getMessage());
			return false;
		}
	}

	/**
	 * This method will check element not displayed
	 */
	public boolean isNotDisplayed(WebElement element) {
		try {
			element.isDisplayed();
			log.info("element is present.." + element.getText());
//			TestBase.logExtentReport("element is present.."+element.getText());
			return false;
		} catch (Exception e) {
			log.error("element is not present..");
			return true;
		}
	}

	public  String getCurrentUrl() {
		return driver.getCurrentUrl();
	}

	public void BrokenLinks() {

		String homePage = getCurrentUrl();
		String url = "";
		HttpURLConnection huc = null;
		int respCode = 200;

//		driver = new ChromeDriver();
//
//		driver.manage().window().maximize();

		// driver.get(homePage);

		List<WebElement> links = driver.findElements(By.tagName("a"));
		System.out.println("Total links on the Wb Page: " + links.size());

		System.out.println(url);
		Iterator<WebElement> iterator = links.iterator();
		while (iterator.hasNext()) {
			url = iterator.next().getText();
			System.out.println(url);
		}

		if (url == null || url.isEmpty() || url == "") {
			System.out.println("URL is either not configured for anchor tag or it is empty");

		} else {

			if (url.contains("http") == false || url.contains("https") == false) {
				System.out.println(url);
				url = "https://" + url;
			} else if (!url.startsWith(homePage)) {
				System.out.println("URL belongs to another domain, skipping it.");

			}

			try {
				huc = (HttpURLConnection) (new URL(url).openConnection());

				huc.setRequestMethod("HEAD");

				huc.connect();

				respCode = huc.getResponseCode();

				if (respCode >= 400) {
					System.out.println(url + " is a broken link");
				} else {
					System.out.println(url + " is a valid link");
				}

			} catch (MalformedURLException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}

		}

//		driver.close();
//		driver.quit();

	}

	/**
	 * These method used for Accessibility testing
	 */
	private static void appendFixes(final StringBuilder sb, final JSONArray arr, final String heading) {
		if (arr != null && arr.length() > 0) {

			sb.append("    ").append(heading).append(lineSeparator);

			for (int i = 0; i < arr.length(); i++) {
				JSONObject fix = arr.getJSONObject(i);

				sb.append("      ").append(fix.get("message")).append(lineSeparator);
			}

			sb.append(lineSeparator);
		}
	}


	static String[][] data2;
	private static final String lineSeparator = System.getProperty("line.separator");

	public static String report(final JSONArray violations) {
		final StringBuilder sb = new StringBuilder();
		sb.append("Found ").append(violations.length()).append(" accessibility violations:");

		for (int i = 0; i < violations.length(); i++) {
			JSONObject violation = violations.getJSONObject(i);
			sb.append(lineSeparator).append(i + 1).append(") ").append(violation.getString("help"));

			if (violation.has("helpUrl")) {
				String helpUrl = violation.getString("helpUrl");
				sb.append(": ").append(helpUrl);
			}

			JSONArray nodes = violation.getJSONArray("nodes");

			for (int j = 0; j < nodes.length(); j++) {
				JSONObject node = nodes.getJSONObject(j);
				sb.append(lineSeparator).append("  ").append(getOrdinal(j + 1)).append(") ")
						.append(node.getJSONArray("target")).append(lineSeparator);

				JSONArray all = node.getJSONArray("all");
				JSONArray none = node.getJSONArray("none");

				for (int k = 0; k < none.length(); k++) {
					all.put(none.getJSONObject(k));
				}

				appendFixes(sb, all, "Fix all of the following:");
				appendFixes(sb, node.getJSONArray("any"), "Fix any of the following:");

			}
		}

		return sb.toString();
	}

	public List<Integer> violationSeverityList(JSONArray violationsArray) {

		JSONArray violations = violationsArray;
		JSONObject violation = null;
		int length = violations.length();
		if (length != 0) {
			totalViolation = totalViolation + length;
			for (int j = 0; j < length; j++) {
				// System.out.println("#########################################");
				String k = violations.get(j).toString();
				// System.out.println(j + 1 + "-->");

				String[] arrOfStr1 = k.split("helpUrl");

				String[] fixes = arrOfStr1[0].split("message");
				// Fixes count

			}

			final StringBuilder sb = new StringBuilder();
			final StringBuilder sb2 = new StringBuilder();

			sb.append("Found ").append(violations.length()).append(" accessibility violations:");
			// data2 = new String[greaterfixedCount][7];
			data2[0][0] = "Violation";
			data2[0][1] = "Help URL";
			data2[0][2] = "Target";
			data2[0][3] = "Fix All of the following";
			data2[0][4] = "Fix Any of the following";

			data2[0][5] = "Severity/Impact";
			data2[0][6] = "Standard/Guidelines";

			String target = "", All_Fixes = "", Any_Fixes = "";
			for (int i = 0; i < violations.length(); i++) {

				int startindex = i + 1;
				JSONObject node = null;
				violation = violations.getJSONObject(i);
				// sb.append(lineSeparator).append(i + 1).append(")
				// ").append(violation.getString("help"));

				// data2[startindex][0] = violation.getString("help");
				data2[startindex][5] = violation.getString("impact");
				// data2[startindex][6] = violation.getJSONArray("tags").toString();

				JSONArray all = null;
				JSONArray none = null, any = null;
				if (violation.has("helpUrl")) {
					String helpUrl = violation.getString("helpUrl");
					data2[startindex][1] = helpUrl;

					sb.append(": ").append(helpUrl);
				}

				JSONArray nodes = violation.getJSONArray("nodes");

				for (int j = 0; j < nodes.length(); j++) {

					sb2.setLength(0);
					node = nodes.getJSONObject(j);
					sb.append(lineSeparator).append("  ").append(getOrdinal(j + 1)).append(") ")
							.append(node.getJSONArray("target")).append(lineSeparator);
					target = node.getJSONArray("target").toString() + target;

					all = node.getJSONArray("all");
					none = node.getJSONArray("none");
					any = node.getJSONArray("any");

					if (all.length() > 0) {
						appendFixes(sb2, all, "Fix all of the following:");
						int startindex_all = sb2.indexOf("Fix all of the following:");
						All_Fixes = sb2.substring(startindex_all + 26) + All_Fixes;

						All_Fixes = "<br>" + All_Fixes;

					}
					if (any.length() > 0) {
						appendFixes(sb2, any, "Fix any of the following:");
						int startindex_any = sb2.indexOf("Fix any of the following:");
						Any_Fixes = sb2.substring(startindex_any + 26) + Any_Fixes;

						Any_Fixes = "<br>" + Any_Fixes;

						// Any_Fixes = "<br>"+Any_Fixes;

					}

				}

				data2[startindex][2] = target;

				data2[startindex][3] = All_Fixes;

				data2[startindex][4] = Any_Fixes;

				System.out.println("full violation severity list ........." + data2[0][5]);
				String severity = violation.getString("impact");
				System.out.println(violation.getString("impact"));
				if (severity.equalsIgnoreCase("serious")) {
					serious++;
				} else if (severity.equalsIgnoreCase("critical")) {
					critical++;
				} else if (severity.equalsIgnoreCase("moderate")) {
					moderate++;
				} else if (severity.equalsIgnoreCase("minor")) {
					minor++;
				}

			}
			Severitylist.add(0, critical);
			Severitylist.add(1, serious);
			Severitylist.add(2, moderate);
			Severitylist.add(3, minor);

			System.out.println("values of the list");

			System.out.println(Severitylist.get(0));
			System.out.println(Severitylist.get(1));
			System.out.println(Severitylist.get(2));
			System.out.println(Severitylist.get(3));

		}
//		System.out.println("full violation severity list ........."+data2[0][5]);
//		System.out.println(violation.getString("impact"));
		System.out.println(Severitylist);
		return Severitylist;

	}
	private static String getOrdinal(int number) {
		String ordinal = "";

		int mod;

		while (number > 0) {
			mod = (number - 1) % 26;
			ordinal = (char) (mod + 97) + ordinal;
			number = (number - mod) / 26;
		}

		return ordinal;
	}

	public JSONArray performAccessibility() throws Exception {

		scriptUrl = AxeDeque.class.getResource("/axe.min.js");

		JSONObject responseJson = new AXE.Builder(driver, scriptUrl).analyze();
		JSONArray violations = responseJson.getJSONArray("violations"); //violations
		int length = violations.length();
		System.out.println(length);
		System.out.println("Printing violations...");

		System.out.println(violations.toString());

		int fixesCount, greaterfixedCount = 0;

		if (length != 0) {
			for (int j = 0; j < length; j++) {
				System.out.println("#########################################");
				String k = violations.get(j).toString();
				System.out.println(j + 1 + "-->");

				String[] arrOfStr1 = k.split("helpUrl");
//                  System.out.println(arrOfStr1[0]);
//                  System.out.println(arrOfStr1[1]);
				String[] fixes = arrOfStr1[0].split("message");
				// Fixes count

				fixesCount = fixes.length;
				System.out.println("fixes count " + fixesCount);

				if (fixesCount > greaterfixedCount)//

				{
					greaterfixedCount = fixesCount + 3;
				}

			}

			final StringBuilder sb = new StringBuilder();
			final StringBuilder sb2 = new StringBuilder();

			sb.append("Found ").append(violations.length()).append(" accessibility violations:");
			data2 = new String[greaterfixedCount][7];
			data2[0][0] = "Violation";
			data2[0][1] = "Help URL";
			data2[0][2] = "Target";
			data2[0][3] = "Fix All of the following";
			data2[0][4] = "Fix Any of the following";

			data2[0][5] = "Severity/Impact";
			data2[0][6] = "Standard/Guidelines";

			String target = "", All_Fixes = "", Any_Fixes = "";
			int hj = violations.length();
			for (int i = 0; i < violations.length(); i++) {

				int startindex = i + 1;
				JSONObject node = null;
				JSONObject violation = violations.getJSONObject(i);
				sb.append(lineSeparator).append(i + 1).append(") ").append(violation.getString("help"));

				data2[startindex][0] = violation.getString("help");
				data2[startindex][5] = violation.getString("impact");
				data2[startindex][6] = violation.getJSONArray("tags").toString();
				


				JSONArray all = null;
				JSONArray none = null, any = null;
				String failuresummary = null, html = null;
				if (violation.has("helpUrl")) {
					String helpUrl = violation.getString("helpUrl");
					data2[startindex][1] = helpUrl;

					sb.append(": ").append(helpUrl);
				}

				JSONArray nodes = violation.getJSONArray("nodes");
				int fd = nodes.length();
				for (int j = 0; j < nodes.length(); j++) {

					sb2.setLength(0);
					node = nodes.getJSONObject(j);
					sb.append(lineSeparator).append("  ").append(getOrdinal(j + 1)).append(") ")
							.append(node.getJSONArray("target")).append(lineSeparator);
					target = node.getJSONArray("target").toString() + target;

					all = node.getJSONArray("all");
					none = node.getJSONArray("none");
					any = node.getJSONArray("any");
					failuresummary = node.getString("failureSummary");
					html = node.getString("html");

					if (all.length() > 0) {
						appendFixes(sb2, all, "Fix all of the following:");
						int startindex_all = sb2.indexOf("Fix all of the following:");
						All_Fixes = sb2.substring(startindex_all + 26) + All_Fixes;

					}
					if (any.length() > 0) {
						appendFixes(sb2, any, "Fix any of the following:");
						int startindex_any = sb2.indexOf("Fix any of the following:");
						Any_Fixes = sb2.substring(startindex_any + 26) + Any_Fixes;

					}

				}

				data2[startindex][2] = target;

				data2[startindex][3] = All_Fixes;

				data2[startindex][4] = Any_Fixes;

//				System.out.println("====================+++++++++++++++++++++====================");
//				System.out.println(violation.getString("help"));
//				System.out.println(violation.getString("helpUrl"));
//				System.out.println(violation.getString("impact"));
//				System.out.println(violation.getJSONArray("tags").toString());
//				System.out.println(target);
//				System.out.println(All_Fixes);
//				System.out.println(Any_Fixes);
//				System.out.println("====================+++++++++++++++++++++====================");

				target = "";
				All_Fixes = "";
				Any_Fixes = "";

			}

			return violations;
			// Assert.assertTrue(false, AXE.report(violations));

		} else {
			Assert.assertTrue(true);
		}

		if (violations.length() > 0) {
			Assert.assertTrue(false, AXE.report(violations));
		}

		return violations;

	}


	HashMap<String, String> urltitle = new HashMap<String, String>();

	public HashMap<String, String> PageUrltitleCollector(ArrayList urlList, ArrayList titleList) {
		String currentUrl = getCurrentUrl();
		if (!(urlList.contains(currentUrl)) && currentUrl.startsWith("http")) {
			String pageTitle = driver.getTitle();
			urlList.add(currentUrl);
			titleList.add(pageTitle);
			urltitle.put(currentUrl, pageTitle);
		}
		return urltitle;
	}

	/**
	 * This method will generate report for Performance testing
	 */
	public void createPerformanceReport(int indexCount, ArrayList AllValues) throws IOException {

		Date date = new Date();
		ArrayList PageUrl = new ArrayList();
		ArrayList currentTitle = new ArrayList();
		ArrayList PerformanceScore = new ArrayList();
		ArrayList ContentfulTime = new ArrayList();
		ArrayList TimeTointeractive = new ArrayList();
		ArrayList SpedIndex = new ArrayList();
		ArrayList totalBlockingTime = new ArrayList();
		ArrayList largestContentfulPaint = new ArrayList();
		ArrayList CumulativeLayoutShift = new ArrayList();

		String[] value2;
		String Pageurl2;
		String Pagetitle2, title, titlefull, Score, contentfultime, spedindex, timetointeract, largestcont, cumlat,
				totalblock;
		String performancescore2;
		String ContentfulTime2;
		String TimeTointeractive2;
		String SpedIndex2;
		String totalBlockingTime2;
		String largestContentfulPaint2;
		String CumulativeLayoutShift2;
		int averagescore = 0;
		int total = 0;
		int icontent, ispeed, itime, ilarge, icumlat, itotal, iTitle, iScore;
		String pattern = "dd-MMM-yyyy HH-mm-ss";
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
		String reportDate = simpleDateFormat.format(new Date());
		// System.out.println(date);
		reportDate = reportDate.replace(" ", "_");
		String reportpath = System.getProperty("user.dir");
		String resultFile = reportpath + "\\Reports\\ClientSidePerformanceReport-" + reportDate + ".html";
		// String resultFile = "D:\\Reports\\Performancehtmlcustomreport.html";

		File file = new File(resultFile);
		System.out.println(file.exists());

		if (file.exists()) {
			file.delete();
		}

		if (!file.exists()) {
			FileWriter fw = new FileWriter(file.getAbsoluteFile(), true);
			BufferedWriter bw1 = new BufferedWriter(fw);
		}

		BufferedWriter bw = new BufferedWriter(new FileWriter(file, true));

		for (int i = 0; i <= AllValues.size() - 1; i++) {
			value2 = (String[]) AllValues.get(i);
			Pageurl2 = value2[0];
			Pagetitle2 = value2[1];
			performancescore2 = value2[2];
			ContentfulTime2 = value2[3];
			TimeTointeractive2 = value2[4];
			SpedIndex2 = value2[5];
			totalBlockingTime2 = value2[6];
			largestContentfulPaint2 = value2[7];
			CumulativeLayoutShift2 = value2[8];
			PageUrl.add(Pageurl2);
			currentTitle.add(Pagetitle2);
			PerformanceScore.add(performancescore2);
			ContentfulTime.add(ContentfulTime2);
			TimeTointeractive.add(TimeTointeractive2);
			SpedIndex.add(SpedIndex2);
			totalBlockingTime.add(totalBlockingTime2);
			largestContentfulPaint.add(largestContentfulPaint2);
			CumulativeLayoutShift.add(CumulativeLayoutShift2);

		}

		bw.write("<!DOCTYPE HTML>" + "\n");
		bw.write("<html>" + "\n");

		// String titlenew = currentTitle.replaceAll("[^a-zA-Z]+", "-");
		// block2 - Pages Accessed
		// String title1 = currentTitle1.replaceAll("[^a-zA-Z]+", "-");
		// String title2 = currentTitle2.replaceAll("[^a-zA-Z]+", "-");
		// html

		bw.write("<head>" + "\n");
		bw.write("<div class=\"triangle-right\"></div>" + "\n");
		bw.write(
				"<div class=\"logo\"><img src=\"https://www.testhouse.net/wp-content/uploads/2021/08/LOGO_Testhouse.png\" width=\"350\" alt=\"My Image\" /></div>"
						+ "\n");
		bw.write("<div class=\"triangle-left\"></div>" + "\n");

		// Heading

		bw.write("<h1 style=\"text-align:center;\">Client-Side Performance Test Report </h1><br><br>" + "\n");

		// block3 - Summary

		bw.write("<div class=\"bloc3\">" + "\n");
		bw.write("<h2>Summary</h2>" + "\n");
		bw.write("<ul><li>Reviewed on:&nbsp; <div class=\"date\" style=\"display:inline;\"><span></span></div></li><br>"
				+ "\n");
		bw.write(
				"<li>Analyzed Page URL:&nbsp; <div class=\"url\" style=\"display:inline;\"><span></span></div></li><br>"
						+ "\n");
		bw.write(
				"<li>Total Pages Accessed:&nbsp; <div class=\"pagecount\" style=\"display:inline;\"><span></span></div></li><br>"
						+ "\n");
		bw.write("</ul><br>" + "\n");
		bw.write("</div>" + "\n");

		bw.write("<div class=\"bloc2\">" + "\n");
		bw.write("<h2>Pages Accessed</h2>" + "\n");
		bw.write("<div>" + "\n");
		bw.write("<ul>" + "\n");
		for (iTitle = 0; iTitle <= currentTitle.size() - 1; iTitle++) {
			title = currentTitle.get(iTitle).toString().replaceAll("[^a-zA-Z]+", "-");
			bw.write("<li><div class=\"" + title + "\"><span></span></div></li><br>" + "\n");
		}
		bw.write("</ul> " + "\n");
		bw.write("</div>" + "\n");
		bw.write("</div>" + "\n");
		bw.write("<br>" + "\n");
		bw.write("<br>" + "\n");
		bw.write("<br>" + "\n");
		bw.write("<br>" + "\n");
		String ZingChart=reportpath + "\\src\\main\\resources\\ZingChart.js";
		//C:\\Users\\THI2201882\\Downloads\\testCucumber (3)\\testCucumber\\src\\main\\resources\\ZingChart.js
		//bw.write("<script nonce=\"undefined\" src=\"\"></script>" + "\n");
		bw.write("<script nonce=\"undefined\" src=\""+ZingChart +"\"></script>" + "\n");
		bw.write("</head>" + "\n");
		bw.write("<body>" + "\n");
		bw.write("<br><br><br><br><br><br><br><br><br><br><br><br><br>" + "\n");

		// blockgraph - Horizontal Bar Graph

		bw.write("<div class=\"blockgraph\">" + "\n");
		bw.write("<div class=\"skill-bars\" style=\"align:center\">" + "\n");
		bw.write("<h2 class=\"heading\"> PageWise Performance</h2><br><br>" + "\n");
		for (iTitle = 0; iTitle <= currentTitle.size() - 1; iTitle++) {
			title = currentTitle.get(iTitle).toString().replaceAll("[^a-zA-Z]+", "-");
			titlefull = currentTitle.get(iTitle).toString();
			bw.write("<div class=\"bar\">" + "\n");
			bw.write("<div class=\"info\">" + "\n");
			bw.write("<span>" + titlefull + "</span>" + "\n");
			bw.write("</div>" + "\n");
			bw.write("<div class=\"progress-line " + title + "\">" + "\n");
			bw.write("<span></span>" + "\n");
			bw.write("</div>" + "\n");
			bw.write("</div>" + "\n");
		}
		bw.write("</div>" + "\n");
		bw.write("</div>" + "\n");

		// blockgauge - Performance Gauge Meter
		
		bw.write("<div class=\"blockgauge\"><div id='myChart'></div></div>" + "\n");
		
		bw.write("<br>" + "\n");
		bw.write("<div class=\"score\">SCORE:&nbsp;<span></span></div>" + "\n");
		bw.write(
				"<br> <br><br> <br><br> <br><br> <br><br> <br><br> <br> <br><br> <br><br> <br><br> <br><br> <br><br><br><br>"
						+ "\n");

		// Metrics

		bw.write("<h2 align =\"center\">Metrics</h2>" + "\n");
		bw.write("<div class=\"liststyle\">" + "\n");
		bw.write("<ul style=\"font-size:large;\">" + "\n");
		bw.write(
				"<li><span class=\"metric\">FCP </span>- Time taken to load the first piece of DOM content after a user navigates to your page.</li>"
						+ "\n");
		bw.write(
				"<li><span class=\"metric\">LCP  </span>- LCP measures the time from when the user initiates loading the page until the largest image or text block is rendered within the viewport.</li>"
						+ "\n");
		bw.write("<li><span class=\"metric\">CLS </span> - calculates the visual stability of a given web page.</li>"
				+ "\n");
		bw.write(
				"<li><span class=\"metric\">Speed Index  </span>- measures how quickly your page is visually complete above-the-fold </li>"
						+ "\n");
		bw.write(
				"<li><span class=\"metric\">Time to Interactive  </span>- metric that captures how quickly a site is ready for user interaction after it loads</li>"
						+ "\n");
		bw.write(
				"<li><span class=\"metric\">Total Blocking Time </span> - measures the usability of the web page during the loading </li>"
						+ "\n");
		bw.write("</ul> </div> <br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br>" + "\n");

		// PageWise Metric Values

		for (iTitle = 0; iTitle <= currentTitle.size() - 1; iTitle++) {
			title = currentTitle.get(iTitle).toString().replaceAll("[^a-zA-Z]+", "-");
			bw.write("<h2><div class=" + title + " style=\"text-align:center;\"><span></span></div></h2>" + "\n");
			bw.write("<br><br>" + "\n");
			bw.write("<div><table align =\"center\">" + "\n");
			bw.write("<tr>" + "\n");
			bw.write("<td><b>First Contentful Paint (FCP)</b></td>" + "\n");
			for (icontent = 0; icontent <= ContentfulTime.size() - 1; icontent++) {
				if (iTitle == icontent) {
					contentfultime = ContentfulTime.get(icontent).toString();
					bw.write("<td>" + contentfultime + "</td>" + "\n");
					System.out.println(icontent + '-' + contentfultime);
					break;
				}
			}
			// icontent++;
			bw.write("<td><b>Speed Index</b></td>" + "\n");
			for (ispeed = 0; ispeed <= SpedIndex.size() - 1; ispeed++) {

				if (iTitle == ispeed) {
					spedindex = SpedIndex.get(ispeed).toString();
					bw.write("<td>" + spedindex + "</td>" + "\n");
					System.out.println(ispeed + '-' + spedindex);
					break;
				}
			}

			bw.write("<td><b>Largest Contentful Paint (LCP)</b></td>" + "\n");
			for (ilarge = 0; ilarge <= largestContentfulPaint.size() - 1; ilarge++) {
				if (iTitle == ilarge) {
					largestcont = largestContentfulPaint.get(ilarge).toString();
					bw.write("<td>" + largestcont + "</td>" + "\n");
					System.out.println(ilarge + '-' + largestcont);
					break;
				}
			}

			bw.write("</tr>" + "\n");
			bw.write("<tr>" + "\n");
			bw.write("<td><b>Time to Interactive</b></td>" + "\n");
//					for(itime=0;ilarge<=TimeTointeractive.size()-1;itime++)
//	                {
//					
//					   if(iTitle==itime)
//	                   {
//					        timetointeract=TimeTointeractive.get(itime).toString();

			bw.write("<td>" + "NA" + "</td>" + "\n");
			//bw.write("<td>" + "0" + "</td>" + "\n");

//		                    System.out.println(itime+'-'+timetointeract);
//		                    break;
//	                   }
//	                }

			bw.write("<td><b>Total Blocking Time</b></td>" + "\n");
			for (itotal = 0; itotal <= totalBlockingTime.size() - 1; itotal++) {

				if (iTitle == itotal) {
					totalblock = totalBlockingTime.get(itotal).toString();
					bw.write("<td>" + totalblock + "</td>" + "\n");
					System.out.println(itotal + '-' + totalblock);
					break;
				}
			}

			bw.write("<td><b>Cumulative Layout Index(CLS)</b></td>" + "\n");

			for (icumlat = 0; icumlat <= CumulativeLayoutShift.size() - 1; icumlat++) {

				if (iTitle == itotal) {
					cumlat = CumulativeLayoutShift.get(icumlat).toString();
					bw.write("<td>" + cumlat + "</td>" + "\n");
					System.out.println(icumlat + '-' + cumlat);
					break;
				}
			}

			bw.write("</tr> </table> </div><br>" + "\n");
		}

		bw.write("<br><br><br>" + "\n");
		bw.write("<br> <br> <br>" + "\n");

		// Javascript - Gauge Meter

		// calculate average performance score

		for (iScore = 0; iScore <= PerformanceScore.size() - 1; iScore++) {

			Score = PerformanceScore.get(iScore).toString();
			total = total + Integer.parseInt(Score);

		}
		averagescore = total / indexCount;
		bw.write("<script>" + "\n");
		bw.write("ZC.LICENSE = [\"569d52cefae586f634c54f86dc99e6a9\", \"b55b025e438fa8a98e32482b5f768ff5\"];" + "\n");
		bw.write("var myConfig7 = {" + "\n");
		bw.write("\"type\": \"gauge\"," + "\n");
		bw.write("\"background-color\": \"transparent\"," + "\n");
		bw.write("\"horizontalAlign\": \"center\"," + "\n");
		bw.write("\"scale-r\": {" + "\n");
		bw.write("\"aperture\": 200," + "\n");
		bw.write("\"values\": \"0:100:20\"," + "\n");
		bw.write("\"center\": {" + "\n");
		bw.write("\"size\": 18," + "\n");
		bw.write("\"border-color\": \"none\"" + "\n");
		bw.write("}," + "\n");
		bw.write("\"ring\": {" + "\n");
		bw.write("\"size\": 25," + "\n");
		bw.write("\"rules\": [{" + "\n");
		bw.write("\"rule\": \"%v >= 0 && %v <= 20\"," + "\n");
		bw.write("\"background-color\": \"red\"" + "\n");
		bw.write("}," + "\n");
		bw.write("{" + "\n");
		bw.write("\"rule\": \"%v >= 20 && %v <= 40\"," + "\n");
		bw.write("\"background-color\": \"orange\"" + "\n");
		bw.write("}," + "\n");
		bw.write("{" + "\n");
		bw.write("\"rule\": \"%v >= 40 && %v <= 60\"," + "\n");
		bw.write("\"background-color\": \"yellow\"" + "\n");
		bw.write("}," + "\n");
		bw.write("{" + "\n");
		bw.write("\"rule\": \"%v >= 60 && %v <= 80\"," + "\n");
		bw.write("\"background-color\": \"blue\"" + "\n");
		bw.write("}," + "\n");
		bw.write("{" + "\n");
		bw.write("\"rule\": \"%v >= 80 && %v <=100\"," + "\n");
		bw.write("\"background-color\": \"green\"" + "\n");
		bw.write("}" + "\n");
		bw.write("]" + "\n");
		bw.write("}," + "\n");
		bw.write("\"labels\": [\"Very Poor\", \"Poor\", \"Fair\", \"Good\", \"Great\", \"Excellent\"],  //Scale Labels"
				+ "\n");
		bw.write("item: {    //Scale Label Styling" + "\n");
		bw.write("'font-color': \"white\"," + "\n");
		bw.write("'font-family': \"Georgia, serif\"," + "\n");
		bw.write("'font-size':13," + "\n");
		bw.write("'font-weight': \"bold\"," + "\n");
		bw.write("'font-style': \"normal\"," + "\n");
		bw.write("'offset-r': -6,    //To adjust the placement of your scale labels." + "\n");
		bw.write("}" + "\n");
		bw.write("}," + "\n");
		bw.write("\"plot\": {" + "\n");
		bw.write("\"csize\": \"6%\"," + "\n");
		bw.write("\"size\": \"100%\"," + "\n");
		bw.write("\"background-color\": \"white\"," + "\n");
		bw.write("'value-box': { //Value Boxes" + "\n");
		bw.write("placement: \"center\",  //Specify placement at \"center\", \"tip\", or \"edge\"." + "\n");
		bw.write("text: \"%v\"," + "\n");
		bw.write("'font-color': \"black\"," + "\n");
		bw.write("'font-size':18," + "\n");
		bw.write("}" + "\n");
		bw.write("}," + "\n");
		bw.write("\"series\": [{" + "\n");
		bw.write("\"values\": [" + averagescore + "]" + "\n");
		bw.write("}]" + "\n");
		bw.write("};" + "\n");
		bw.write("zingchart.render({" + "\n");
		bw.write("id: 'myChart'," + "\n");
		bw.write("data: myConfig7," + "\n");
		bw.write("height: \"90%\"," + "\n");
		bw.write("width: \"220%\"," + "\n");
		bw.write("});" + "\n");
		bw.write("</script>" + "\n");

		// Style

		bw.write("<style>" + "\n");

		bw.write(".metric {" + "\n");
		bw.write("font-size: large;" + "\n");
		bw.write("}" + "\n");

		bw.write("#myChart-license-text{" + "\n");
		bw.write("content-visibility: hidden;" + "\n");
		bw.write("backface-visibility: hidden;" + "\n");
		bw.write("}" + "\n");

		bw.write(".triangle-right {" + "\n");
		bw.write("width: 0;" + "\n");
		bw.write("height: 0;" + "\n");
		bw.write("border-left: 1450px solid white;" + "\n");
		bw.write("border-bottom: 250px solid transparent;" + "\n");
		bw.write("position: relative;" + "\n");
		bw.write("}" + "\n");

		bw.write(".triangle-left {" + "\n");
		bw.write("width: 0;" + "\n");
		bw.write("height: 0;" + "\n");
		bw.write("border-top: 150px solid transparent;" + "\n");
		bw.write("border-right: 850px solid #d3003f;" + "\n");
		bw.write("border-bottom: 40px solid transparent;" + "\n");
		bw.write("top: 6px;" + "\n");
		bw.write("right:4px;" + "\n");
		bw.write("position: absolute;" + "\n");
		bw.write("}" + "\n");

		bw.write(".pagecount span::after{content: \"" + indexCount + "\";}" + "\n");

		bw.write(".logo{" + "\n");
		bw.write("position: absolute;" + "\n");
		bw.write("z-index: 3;" + "\n");
		bw.write("top: 70px;" + "\n");
		bw.write("left:60px;" + "\n");
		bw.write("float:left;" + "\n");
		bw.write("}" + "\n");

		bw.write("body{" + "\n");
		bw.write("background-color: #1f1f1f;" + "\n");
		bw.write("background-repeat: no-repeat;" + "\n");
		bw.write("background-attachment: fixed;" + "\n");
		bw.write("background-size: cover;" + "\n");
		bw.write("}" + "\n");

		bw.write("table {" + "\n");
		bw.write("border-collapse: collapse;" + "\n");
		bw.write("font-family: Tahoma, Geneva, sans-serif;" + "\n");
		bw.write("background-color: transparent; border-color: white;" + "\n");
		bw.write("}" + "\n");

		bw.write("table, tbody, tr, th, td {" + "\n");
		bw.write("background-color: rgba(0, 0, 0, 0.0)!important;" + "\n");
		bw.write("}" + "\n");

		bw.write("table td {" + "\n");
		bw.write("padding: 15px;" + "\n");
		bw.write("}" + "\n");

		bw.write("table th {" + "\n");
		bw.write("background-color: white;" + "\n");
		bw.write("}" + "\n");

		bw.write("table thead td {" + "\n");
		bw.write("background-color: white;" + "\n");
		bw.write("color: #ffffff;" + "\n");
		bw.write("font-weight: bold;" + "\n");
		bw.write("font-size: 13px;" + "\n");
		bw.write("border: 1px solid #54585d;" + "\n");
		bw.write("}" + "\n");

		bw.write("table tbody td {" + "\n");
		bw.write("color: white;" + "\n");
		bw.write("border: 1px solid black;" + "\n");
		bw.write("border-width: 3px;" + "\n");
		bw.write("}" + "\n");

		bw.write("table tbody tr {" + "\n");
		bw.write("background-color: white;" + "\n");
		bw.write("}" + "\n");

		bw.write("table tbody tr:nth-child(odd) {" + "\n");
		bw.write("background-color: white;" + "\n");
		bw.write("}" + "\n");

		bw.write("h1 {" + "\n");
		bw.write("color: white;" + "\n");
		bw.write("font-family: Tahoma, Geneva, sans-serif;" + "\n");
		bw.write("font-size: 250%;" + "\n");
		bw.write("}" + "\n");

		bw.write(
				".skill-bars{width: 550px;background: transparent;border-radius: 10px; left: 120px; position: absolute; border-width: 3px;}"
						+ "\n");
		bw.write(".skill-bars .bar{margin: 20px 0; margin-left:10px;}" + "\n");
		bw.write(".skill-bars .bar:first-child{margin-top: 0px;}" + "\n");
		bw.write(".skill-bars .bar .info{margin-bottom: 5px;}" + "\n");
		bw.write(
				".skill-bars .bar .info span{font-weight: 500;font-size: 17px;opacity: 0;animation: showText 0.5s 1s linear forwards;color: white;}"
						+ "\n");
		bw.write("@keyframes showText {100%{opacity: 1;}}" + "\n");
		bw.write(
				".skill-bars .bar .progress-line{height: 10px;width: 500px;background: #f0f0f0;position: relative;transform: scaleX(0);transform-origin: left;border-radius: 10px;box-shadow: inset 0 1px 1px rgba(0,0,0,0.05),0 1px rgba(255,255,255,0.8);animation: animate 1s cubic-bezier(1,0,0.5,1) forwards;}"
						+ "\n");
		bw.write("@keyframes animate {100%{transform: scaleX(1);}}" + "\n");
		bw.write(
				".bar .progress-line span{height: 100%;position: absolute;border-radius: 10px;transform: scaleX(0);transform-origin: left;animation: animate 1s 1s cubic-bezier(1,0,0.5,1) forwards;}"
						+ "\n");

		bw.write(
				".progress-line span::before{position: absolute; top: -10px;right: 0;height: 0;width: 0;border: 7px solid transparent;border-bottom-width: 0px;border-right-width: 0px; opacity: 0;animation: showText2 0.5s 1.5s linear forwards;}"
						+ "\n");
		bw.write(
				".progress-line span::after{position: absolute;top: -28px;right: -40px;font-weight: 500;background: white;color: black;padding: 1px 8px;font-size: 12px;border-radius: 3px;opacity: 0;animation: showText2 0.5s 1.5s linear forwards;}"
						+ "\n");
		bw.write("@keyframes showText2 {100%{opacity: 1;}}" + "\n");

		bw.write(".bloc3" + "\n");
		bw.write("{" + "\n");
		bw.write("width: 40%;" + "\n");
		bw.write("color: white;" + "\n");
		bw.write("margin-right: 5px;" + "\n");
		bw.write("margin-left: 30px;" + "\n");
		bw.write("float: left;" + "\n");
		bw.write("height:230px;" + "\n");
		bw.write("padding-bottom: 10px;" + "\n");
		bw.write("padding: 25px 30px;" + "\n");
		bw.write("background-color: transparent;" + "\n");
		bw.write("font-family: Tahoma, Geneva, sans-serif;" + "\n");
		bw.write("border-radius: 10px;" + "\n");
		bw.write("border-style: double;" + "\n");
		bw.write("border-color: white;" + "\n");
		bw.write("margin-left:30px;" + "\n");
		bw.write("}" + "\n");

		bw.write(".bloc2" + "\n");
		bw.write("{" + "\n");
		bw.write("background-color: transparent;" + "\n");
		bw.write("margin-right: 30px;" + "\n");
		bw.write("width: 40%;" + "\n");
		bw.write("height:230px;" + "\n");
		bw.write("float:right;" + "\n");
		bw.write("right:70px;" + "\n");
		bw.write("padding-bottom: 10px;" + "\n");
		bw.write("margin-left:50px;" + "\n");
		bw.write("z-index:1;" + "\n");
		bw.write("border-style: double;" + "\n");
		bw.write("border-color: white;" + "\n");
		bw.write("padding: 25px 30px;" + "\n");
		bw.write("border-radius: 10px;" + "\n");
		bw.write("}" + "\n");

		bw.write(".blockgauge{" + "\n");
		bw.write("background-color: transparent;" + "\n");
		bw.write("margin-right: 30px;" + "\n");
		bw.write("width: 40%;" + "\n");
		bw.write("height:350px;" + "\n");
		bw.write("float:right;" + "\n");
		bw.write("right:70px;" + "\n");
		bw.write("padding-bottom: 10px;" + "\n");
		bw.write("margin-left:50px;" + "\n");
		bw.write("border-style: double;" + "\n");
		bw.write("border-color: white;" + "\n");
		bw.write("padding: 25px 30px;" + "\n");
		bw.write("border-radius: 10px;" + "\n");
		bw.write("}" + "\n");

		bw.write(".blockgraph{" + "\n");
		bw.write("background-color: transparent;" + "\n");
		bw.write("margin-right: 30px;" + "\n");
		bw.write("width: 40%;" + "\n");
		bw.write("height:350px;" + "\n");
		bw.write("float:left;" + "\n");
		bw.write("right:70px;" + "\n");
		bw.write("padding-bottom: 10px;" + "\n");
		bw.write("margin-left:30px;" + "\n");
		bw.write("border-style: double;" + "\n");
		bw.write("border-color: white;" + "\n");
		bw.write("padding: 25px 30px;" + "\n");
		bw.write("border-radius: 10px;" + "\n");
		bw.write("}" + "\n");

		bw.write(".dummy" + "\n");
		bw.write("{" + "\n");
		bw.write("width: 1600px;" + "\n");
		bw.write("height:20px;" + "\n");
		bw.write("background-color: black;" + "\n");
		bw.write("top: 1px;" + "\n");
		bw.write("z-index:2;" + "\n");
		bw.write("position: relative;" + "\n");
		bw.write("}" + "\n");

		bw.write("h3,li,h2{" + "\n");
		bw.write("color: white;" + "\n");
		bw.write("font-family: Tahoma, Geneva, sans-serif;" + "\n");
		bw.write("}" + "\n");

		bw.write(".heading{" + "\n");
		bw.write("text-align:center;" + "\n");
		bw.write("}" + "\n");

		bw.write(".liststyle{" + "\n");
		bw.write("padding: 10px;" + "\n");
		bw.write("margin: 20px;" + "\n");
		bw.write(" font-size: 15px;" + "\n");
		bw.write("left: 200px;" + "\n");
		bw.write(" position: absolute;" + "\n");
		bw.write("font-family: Verdana;" + "\n");
		bw.write("text-align: left;" + "\n");
		bw.write("border-radius: 10px;" + "\n");
		bw.write("width: 70%;" + "\n");
		bw.write("list-style: none;" + "\n");
		bw.write("background-color:transparent;" + "\n");
		bw.write("border-style: double;" + "\n");
		bw.write(" border-color: white;" + "\n");
		bw.write("padding: 25px 30px;" + "\n");
		bw.write("border-radius: 10px;}" + "\n");

		bw.write(".score{" + "\n");
		bw.write("width:8%;" + "\n");
		bw.write("height:1px;" + "\n");
		bw.write("background-color: white;" + "\n");
		bw.write("position: absolute;" + "\n");
		bw.write("top: 1020px;" + "\n");
		bw.write("left: 1045px;" + "\n");
		bw.write("font-size:20px;" + "\n");
		bw.write("font-family:Verdana;" + "\n");
		bw.write("text-align:center;" + "\n");
		bw.write("color: black;" + "\n");
		bw.write("line-height: 12px;" + "\n");
		bw.write("padding: 20px;" + "\n");
		bw.write("border-style: double;" + "\n");
		bw.write("border-color: black;" + "\n");
		bw.write("border-radius: 10px;" + "\n");
		bw.write("}" + "\n");

		bw.write(".date span::after{content: \"" + date + "\";}" + "\n");
		String urlpage = null;
		// String urlpage=PageUrl.get().toString();
		for (int iUrl = 0; iUrl <= PageUrl.size() - 1; iUrl++) {
			urlpage = urlpage + PageUrl.get(iUrl).toString() + ",";
		}
		String urlPage = urlpage.replace("null", "");
		int urlLength = urlPage.length();
		String pageUrl = urlPage.substring(0, urlLength - 1);
		bw.write(".url span::after{content: \"" + pageUrl + "\";}" + "\n");

		bw.write(".metric{" + "\n");
		bw.write("color: #d3003f;" + "\n");
		bw.write("font-size:15px;" + "\n");
		bw.write("font-family:Verdana;" + "\n");
		bw.write("font-style:bold;" + "\n");
		bw.write("}" + "\n");

		bw.write(".score span::after{content: \"" + averagescore + "\";}" + "\n");
		bw.write(".pagecount span::after{content: \"" + indexCount + "\";}" + "\n");

		for (iTitle = 0; iTitle <= currentTitle.size() - 1; iTitle++) {

			for (iScore = 0; iScore <= PerformanceScore.size() - 1; iScore++) {

				if (iTitle == iScore) {
					Score = PerformanceScore.get(iScore).toString();
					int percentagenew = Integer.parseInt(Score);

					title = currentTitle.get(iTitle).toString().replaceAll("[^a-zA-Z]+", "-");
					bw.write(".bar .progress-line." + title + " span{width: " + percentagenew * 5
							+ "px; background: red;}" + "\n");
					bw.write("." + title + " span::after{content: \"" + title + "\";}" + "\n");
					bw.write(".progress-line." + title + " span::after{content: \"" + percentagenew + "\";}" + "\n");
					break;

				}

			}

		}

		bw.write("</style>" + "\n");

		// html closing

		bw.write("</body>" + "\n");
		bw.write("</html>" + "\n");

		bw.flush();
		bw.close();

	}

	By pageHeader = By.xpath("//div[@class='lh-scores-header']");
	By ellipsisBtn = By.xpath("//button[@id='lh-tools-button']");
	By saveAsHTML = By.xpath("//a[text()='Save as HTML']");

	/**
	 * This method used for Lighthouse report viewer
	 */
	public String[] LighthouseMain(String currentUrl, String currentTitle) throws Exception {

		// currentUrl = driver.getCurrentUrl();
		// ArrayList<String> tabs2 = new ArrayList<String> (driver.getWindowHandles());
		// By Page = By.xpath(xpath);
		// clickElement(Page);
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(200));
		//WebDriverWait wait = new WebDriverWait(driver, 200);
		// driver.navigate().back();
		// String currentUrl1 = driver.getCurrentUrl();
		// currentTitle = driver.getTitle();

		// driver.switchTo().newWindow(WindowType.TAB);
		// driver.findElement(By.xpath(xpath)).sendKeys(Keys.CONTROL + "\t");

		driver.get("https://googlechrome.github.io/lighthouse/viewer/?psiurl=" + currentUrl);

		@SuppressWarnings("deprecation")
		WebDriverWait wait1 = new WebDriverWait(driver, Duration.ofSeconds(200));
		//WebDriverWait wait1 = new WebDriverWait(driver, 200);
		WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(pageHeader));
		String Perfpercentage;
		By performncestatus = By.xpath("(//div[@Class ='lh-scores-header']/a)[1]");
		String Perfstatus = findElement(performncestatus).getAttribute("class");
		System.out.println(Perfstatus);
		if (Perfstatus.contains("pass")) {
			By percentage = By.xpath(
					"//div[@Class ='lh-scores-header']/a[@class='lh-gauge__wrapper lh-gauge__wrapper--pass']//div[@class='lh-gauge__percentage']");
			Perfpercentage = findElement(percentage).getText();
			System.out.println("Performance score is :" + Perfpercentage);
			Thread.sleep(1000);
		} else if (Perfstatus.contains("fail")) {

			By percentage = By.xpath(
					"//div[@Class ='lh-scores-header']/a[@class='lh-gauge__wrapper lh-gauge__wrapper--fail']//div[@class='lh-gauge__percentage']");
			Perfpercentage = findElement(percentage).getText();
			System.out.println("Performance score is :" + Perfpercentage);
			Thread.sleep(1000);
		} else {
			By percentage = By.xpath(
					"(//div[@Class ='lh-scores-header']/a[@class='lh-gauge__wrapper lh-gauge__wrapper--average']//div[@class='lh-gauge__percentage'])[1]");
			Perfpercentage = findElement(percentage).getText();
			System.out.println("Performance score is :" + Perfpercentage);
			Thread.sleep(1000);
		}
		By firstContentfulTime = By.xpath("//span[text()='First Contentful Paint']/..//div[@class='lh-metric__value']");
		String contentfulTimeValue = findElement(firstContentfulTime).getText();
		System.out.println("First contentful time : " + contentfulTimeValue);
		Thread.sleep(1000);

//	    By timeToInteractive = By.xpath("//span[text()='Time to Interactive']/..//div[@class='lh-metric__value']");
//	    String timeTointeractiveValue = findElement(timeToInteractive).getText();
//	    System.out.println("Time to interactive : "+timeTointeractiveValue);
//	    Thread.sleep(1000);

		By speedIndex = By.xpath("//span[text()='Speed Index']/..//div[@class='lh-metric__value']");
		String spedIndexValue = findElement(speedIndex).getText();
		System.out.println("Speed index : " + spedIndexValue);
		Thread.sleep(1000);

		By totalBlockingTime = By.xpath("//span[text()='Total Blocking Time']/..//div[@class='lh-metric__value']");
		String totalBlockingTimeValue = findElement(totalBlockingTime).getText();
		System.out.println("Total blocking time : " + totalBlockingTimeValue);
		Thread.sleep(1000);

		By largestContentfulPaint = By
				.xpath("//span[text()='Largest Contentful Paint']/..//div[@class='lh-metric__value']");
		String largestContentfulPaintValue = findElement(largestContentfulPaint).getText();
		System.out.println("Largest contentful paint : " + largestContentfulPaintValue);

		Thread.sleep(1000);
		By CumulativeLayoutShift = By
				.xpath("//span[text()='Cumulative Layout Shift']/..//div[@class='lh-metric__value']");
		String CumulativeLayoutShiftValue = findElement(CumulativeLayoutShift).getText();
		System.out.println("Cumulative layout shift : " + CumulativeLayoutShiftValue);

		/*
		 * clickElement(ellipsisBtn); clickElement(saveAsHTML); driver.close();
		 * driver.switchTo().window(tabs2.get(0));
		 */
		System.out.println("Performance details of " + currentUrl);
		System.out.println("Performance details of " + currentTitle);
		System.out.println("===================================================");
		System.out.println("Performance score is :" + Perfpercentage);
		System.out.println("First contentful time : " + contentfulTimeValue);
		// System.out.println("Time to interactive : "+timeTointeractiveValue);
		System.out.println("Speed index : " + spedIndexValue);
		System.out.println("Total blocking time : " + totalBlockingTimeValue);
		System.out.println("Largest contentful paint : " + largestContentfulPaintValue);
		System.out.println("Cumulative layout shift : " + CumulativeLayoutShiftValue);
		System.out.println("===================================================");

		// driver.switchTo().window(tabs2.get(0));

		driver.navigate().back();

		String[] score = new String[9];
		score[0] = currentUrl;
		score[1] = currentTitle;
		score[2] = Perfpercentage;
		score[3] = contentfulTimeValue;
		// score[4] = timeTointeractiveValue;
		score[5] = spedIndexValue;
		score[6] = totalBlockingTimeValue;
		score[7] = largestContentfulPaintValue;
		score[8] = CumulativeLayoutShiftValue;

		return score;

	}

	/**
	 * This method used for capture screen
	 */
	public String captureScreen(String fileName, WebDriver driver) {
		if (driver == null) {

			String output = "driver is not null";
			// log.info("driver is null..");
			return output;
		}
		if (fileName == "") {
			fileName = "blank";
		}
		Reporter.log("captureScreen method called");
		File destFile = null;
		Calendar calendar = Calendar.getInstance();
		SimpleDateFormat formater = new SimpleDateFormat("dd_MM_yyyy_hh_mm_ss");
		File screFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
		//final byte[] screenshot = ((TakesScreenshot) driver.getDelegate()).getScreenshotAs(OutputType.BYTES);
		try {
			destFile = new File(reportDirectery + "/" + fileName + "_" + formater.format(calendar.getTime()) + ".png");
			Files.copy(screFile.toPath(), destFile.toPath());
			Reporter.log("<a href='" + destFile.getAbsolutePath() + "'><img src='" + destFile.getAbsolutePath()
					+ "'height='100' width='100'/></a>");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return destFile.toString();
	}


	/*****************************************************
	 * Android Generic Functions
	 ***********************************************************************************/

	/**
	 * This method will find element
	 */
	public WebElement androidElement(By byElement) {
		WebElement ele = androiddriver.findElement(byElement);
		return ele;
	}

	/**
	 * This method will click element
	 */
	public WebElement androdiclickElement(By byElement) {
		//WebDriverWait wait = new WebDriverWait(androiddriver, 60);
		WebDriverWait wait = new WebDriverWait(androiddriver, Duration.ofSeconds(60));
		WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(byElement));
		element.click();
		return element;
	}

	/**
	 * This method will perform key action
	 */
	public void androidkeyAction(String action) {
		Actions act = new Actions(androiddriver);
		if (action == "ENTER" || action == "Enter") {
			act.sendKeys(Keys.ENTER).build().perform();
		} else if (action == "PAGE_DOWN") {
			act.sendKeys(Keys.PAGE_DOWN).build().perform();
		}
	}

	/**
	 * This method will do slider action
	 */
	public void androidsliderMovement(WebElement sliderObj, String movecount) {
		Actions actions = new Actions(androiddriver);
		for (int initialCount = 1000; initialCount < Integer.parseInt(movecount); initialCount += 50) {
			sliderObj.sendKeys(Keys.ARROW_RIGHT);
		}
	}

	/**
	 * this method will switchToFrame based on frame index
	 * 
	 * @param frameIndex
	 */
	public void androidswitchToFrame(int frameIndex) {
		androiddriver.switchTo().frame(frameIndex);
		log.info("switched to :" + frameIndex + " frame");
	}

	/**
	 * this method will switchToFrame based on frame name
	 * 
	 * @param frameName
	 */
	public void androidswitchToFrame(String frameName) {
		androiddriver.switchTo().frame(frameName);
		log.info("switched to :" + frameName + " frame");
	}

	public void androidswitchToFrame(WebElement element) {
		androiddriver.switchTo().frame(element);
	}

	public void androidswitchToDefault() {
		androiddriver.switchTo().defaultContent();
	}

	public static String getProperty(String key) {
		try {

			String filePath = ResourceHelper
					.getResourcePath("src\\main\\resources\\ConfigFiles\\Desiredcapabilities.properties");
			file = new FileInputStream(new File(filePath));
			OR = new Properties();
			OR.load(file);

			String a = OR.getProperty(key);

		} catch (Exception e) {
			e.printStackTrace();
		}
		return OR.getProperty(key);
	}

	public void androidenterKeys(By element, String key) {
		WebElement textBox = androidfindElement(element);
		textBox.sendKeys(key);

	}

	/**
	 * This method will find element in page
	 */
	public WebElement androidfindElement(By byElement) {
		//WebDriverWait wait = new WebDriverWait(androiddriver, 30);
		WebDriverWait wait = new WebDriverWait(androiddriver, Duration.ofSeconds(30));
		WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(byElement));
		return element;
	}
	/* Function to wait for the page to load. otherwise it will fail the test*/
	public void waitForPageToLoad(AndroidDriver driver) {
		ExpectedCondition<Boolean> javascriptDone = new ExpectedCondition<Boolean>() {
			public Boolean apply(WebDriver d) {
				try {
					return ((JavascriptExecutor) driver).executeScript("return document.readyState").equals("complete");
				} catch (Exception e) {
					return Boolean.FALSE;
				}
			}
		};
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(60));
		wait.until(javascriptDone);
	}


	public static String getMobileDeviceProperty(String key) {
		try {

			String filePath = ResourceHelper
					.getResourcePath("src\\main\\resources\\ConfigFiles\\mobileDeviceConfig.properties");
			file = new FileInputStream(new File(filePath));
			OR = new Properties();
			OR.load(file);

			String a = OR.getProperty(key);

		} catch (Exception e) {
			e.printStackTrace();
		}
		return OR.getProperty(key);
	}


	public static String getConfigProperty(String key) {
		try {

			String filePath = ResourceHelper
					.getResourcePath("src/main/resources/ConfigFiles/config.properties");
			file = new FileInputStream(new File(filePath));
			OR = new Properties();
			OR.load(file);

			String a = OR.getProperty(key);

		} catch (Exception e) {
			e.printStackTrace();
		}
		return OR.getProperty(key);
	}




	//Athira
	//public class GenericFunctions {

		// Function to validate the color of a WebElement
			public static String getTextColor(WebDriver driver, By elementLocator) {
		        try {
		        	WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5000));
		            WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(elementLocator));
		            String actualColor = element.getCssValue("color");
		            System.out.println("Actual color of the element: " + actualColor);
		            //return actualColor.equalsIgnoreCase(expectedColor);
					return actualColor;
		        } catch (Exception e) {
		            e.printStackTrace();
		            return "Not Found";
		        }
		    }
		//}

			// Function to validate the color of a WebElement
			public static boolean isTextColor(WebDriver driver, By elementLocator, String expectedColor) {
				try {
					WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5000));
					WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(elementLocator));
					String actualColor = element.getCssValue("color");
					System.out.println("Actual color of the element: " + actualColor);
					return actualColor.equalsIgnoreCase(expectedColor);
				} catch (Exception e) {
					e.printStackTrace();
					return false;
				}
			}

	public void waitForPageToLoad(Integer maxduration) throws InterruptedException {
		ExpectedCondition <Boolean> javascriptDone = new ExpectedCondition<Boolean>() {
			public Boolean apply(WebDriver d) {
				try {
					return ((JavascriptExecutor) driver).executeScript("return document.readyState").equals("complete");
				} catch (Exception e) {
					return Boolean.FALSE;
				}
			}
		};
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(maxduration));
		wait.until(javascriptDone);
		Thread.sleep(1000);
	}

	public void waitForByElement(By elementLocator){
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5000));
		wait.until(ExpectedConditions.visibilityOfElementLocated(elementLocator));

	}
	public void logToExtentReport(String stepStatus,String logMessage) {
		if (stepStatus.equals("Pass")) {
		ExtentCucumberAdapter.getCurrentStep().log(Status.PASS, MarkupHelper.createLabel("Pass", ExtentColor.GREEN));
		ExtentCucumberAdapter.getCurrentStep().log(Status.PASS,logMessage);
		}
		else if (stepStatus.equals("Fail")){
			ExtentCucumberAdapter.getCurrentStep().log(Status.FAIL, MarkupHelper.createLabel("Fail", ExtentColor.RED));
			ExtentCucumberAdapter.getCurrentStep().log(Status.FAIL,logMessage);
		}
		else if (stepStatus.equals("Log")){
			ExtentCucumberAdapter.getCurrentStep().log(Status.INFO, MarkupHelper.createLabel("Log", ExtentColor.GREY));
			ExtentCucumberAdapter.getCurrentStep().log(Status.INFO,logMessage);
		}
		}
	public static void switchToWindowByTitle(String windowTitle) {
		// Get the handle of the current window
		String currentWindowHandle = driver.getWindowHandle();

		// Get the handles of all the windows
		Set<String> windowHandles = driver.getWindowHandles();

		// Switch to the window by title
		for (String handle : windowHandles) {
			driver.switchTo().window(handle);
			if (driver.getTitle().equals(windowTitle)) {
				break;
			}
		}
	}
	// To Hide Element
	public void hideElement(WebElement element) {
		((JavascriptExecutor) driver).executeScript("arguments[0].style.display = 'none'", element);
	}

	// Taking Screenshot of webpage
	public boolean shootWebPage(String name, String path) {
		boolean flag = false;
		try {
			Shutterbug.shootPage(driver, Capture.FULL_SCROLL, 1000, true).withName(name).save(path);
			flag = true;
			System.out.println("WebPage Saved Successfully");
		} catch (Exception e) {

			System.err.println("Error saving screenshot: " + e.getMessage());
			flag = false;
		}

		return flag;
	}
	/**
	 * This method will generate report for Accessibility
	 */
	public void createAccessibilityReport(JSONArray violationsArray, int indexCount, int passedCount, int failedCount,
										  int skippedCount, int sevCritical, int sevSerious, int sevModerate, int sevMinor, int totalViolations,String url,WebDriver driver)
			throws Exception {
		int totalVio = violationsArray.length();
		driver.navigate().back();
		//String url = getCurrentUrl();
		System.out.println(url);
		SimpleDateFormat startDateTime = new SimpleDateFormat("MM-d-yyyy_HH-mm-ss");
		System.out.println(startDateTime);
		Date date = Calendar.getInstance().getTime();
		DateFormat dateFormat = new SimpleDateFormat("MM-d-yyyy_HH-mm-ss");
		String strDate = dateFormat.format(date);

		String fixesAll = "";
		String fixesAll2 = "";
		String finalAllFixes = "";
		String reportpath = System.getProperty("user.dir");
		String resultFile = reportpath + "\\Reports\\accessibilitycustomreport"+strDate+".html";
		File file = new File(resultFile);
		System.out.println(file.exists());

		if (file.exists()) {
			file.delete();
		}

		if (!file.exists()) {
			FileWriter fw = new FileWriter(file.getAbsoluteFile(), true);
			BufferedWriter bw1 = new BufferedWriter(fw);
		}
		BufferedWriter bw = new BufferedWriter(new FileWriter(file, true));
		if (indexCount == 1) {
			// BufferedWriter bw = new BufferedWriter(new FileWriter(file, true));
			bw.write("<!DOCTYPE HTML>" + "\n");
			bw.write("<html>" + "\n");

			bw.write("<style>" + "\n");

			// adding new codes 06/01/23
			bw.write(".triangle-right {" + "\n");
			bw.write("width: 0;" + "\n");
			bw.write("height: 0;" + "\n");

			bw.write(";border-left: 1450px solid white;" + "\n");
			bw.write(";border-bottom: 250px solid transparent;" + "\n");
			bw.write("position: relative;" + "\n");
			bw.write("}" + "\n");
			bw.write(".triangle-left {" + "\n");
			bw.write("width: 0;" + "\n");
			bw.write("height: 0;" + "\n");
			bw.write("border-top: 150px solid transparent;" + "\n");
			bw.write("border-right: 850px solid #d3003f;" + "\n");
			bw.write("border-bottom: 40px solid transparent;" + "\n");
			bw.write("top: 6px;" + "\n");
			bw.write("right:4px;" + "\n");
			bw.write("position: absolute;" + "\n");
			bw.write("}" + "\n");
			bw.write(".triangle-bottomright {" + "\n");
			bw.write("width: 0;" + "\n");
			bw.write("height: 0;" + "\n");
			bw.write("border-top: 150px solid transparent;" + "\n");
			bw.write("border-right: 850px solid #d3003f;" + "\n");
			bw.write("border-bottom: 4px solid transparent;" + "\n");
			bw.write("position: absolute;" + "\n");
			bw.write("right: 0px;" + "\n");
			bw.write("bottom: 0px;" + "\n");
			bw.write("}" + "\n");
			bw.write(".triangle-bottomleft {" + "\n");
			bw.write("width: 0;" + "\n");
			bw.write("height: 0px;" + "\n");
			bw.write("border-left: 1px solid transparent;" + "\n");
			bw.write("border-right: 850px solid transparent;" + "\n");
			bw.write("border-bottom: 200px solid #d3003f;" + "\n");
			bw.write("}" + "\n");
			bw.write(".logo{" + "\n");
			bw.write("position: absolute;" + "\n");
			bw.write("z-index: 1;" + "\n");
			bw.write("top: 30px;" + "\n");

			bw.write("}" + "\n");
			bw.write("body{" + "\n");

			bw.write("background-color: #1f1f1f;" + "\n");
			bw.write("background-repeat: no-repeat;" + "\n");
			bw.write("background-attachment: fixed;" + "\n");
			bw.write("background-size: cover;" + "\n");
			bw.write("}" + "\n");

			bw.write(".canvasjs-chart-credit{" + "\n");
			bw.write("content-visibility: hidden;" + "\n");
			bw.write("backface-visibility: hidden;" + "\n");
			bw.write("}" + "\n");
			bw.write("table {" + "\n");
			bw.write("border-collapse: collapse;" + "\n");
			bw.write("font-family: Tahoma, Geneva, sans-serif;" + "\n");
			bw.write("}" + "\n");
			bw.write("table td {" + "\n");
			bw.write("padding: 15px;" + "\n");
			bw.write("}" + "\n");
			bw.write("table th {" + "\n");
			bw.write("background-color: #db4e44;" + "\n");
			bw.write("}" + "\n");
			bw.write("able thead td {" + "\n");
			bw.write("background-color: #32CD32;" + "\n");
			bw.write("color: #ffffff;" + "\n");
			bw.write("font-weight: bold;" + "\n");
			bw.write("font-size: 13px;" + "\n");
			bw.write("border: 1px solid #54585d;" + "\n");
			bw.write("}" + "\n");
			bw.write("table tbody td {" + "\n");
			bw.write("color: #636363;" + "\n");
			bw.write("border: 1px solid #dddfe1;" + "\n");
			bw.write("}" + "\n");
			bw.write("table tbody tr {" + "\n");
			bw.write("background-color: #f9fafb;" + "\n");
			bw.write("}" + "\n");
			bw.write("table tbody tr:nth-child(odd) {" + "\n");
			bw.write("background-color: #ffffff;" + "\n");
			bw.write("}" + "\n");

			bw.write("h1 {" + "\n");
			bw.write("color: white;" + "\n");
			bw.write("font-family: Tahoma, Geneva, sans-serif;" + "\n");
			bw.write("font-size: 250%;" + "\n");
			bw.write("}" + "\n");

			bw.write(".skill-bars{width: 350px;background: transparent;border-radius: 10px;}" + "\n");
			bw.write(".skill-bars .bar{margin: 20px 0;}" + "\n");
			bw.write(".skill-bars .bar:first-child{margin-top: 0px;}" + "\n");
			bw.write(".skill-bars .bar .info{margin-bottom: 5px;}" + "\n");
			bw.write(
					".skill-bars .bar .info span{font-weight: 500;font-size: 17px;opacity: 0;animation: showText 0.5s 1s linear forwards;color: white;}"
							+ "\n");
			bw.write("@keyframes showText {100%{opacity: 1;}}" + "\n");
			bw.write(
					".skill-bars .bar .progress-line{height: 10px;width: 100%;background: #f0f0f0;position: relative;transform: scaleX(0);transform-origin: left;border-radius: 10px;box-shadow: inset 0 1px 1px rgba(0,0,0,0.05),0 1px rgba(255,255,255,0.8);animation: animate 1s cubic-bezier(1,0,0.5,1) forwards;}"
							+ "\n");
			bw.write("@keyframes animate {100%{transform: scaleX(1);}}" + "\n");
			bw.write(
					".bar .progress-line span{height: 100%;position: absolute;border-radius: 10px;transform: scaleX(0);transform-origin: left;animation: animate 1s 1s cubic-bezier(1,0,0.5,1) forwards;}"
							+ "\n");

			bw.write(".bar .progress-line.critical span{width: " + sevCritical * 10 + "px;background: red;}" + "\n");
			bw.write(".bar .progress-line.serious span{width: " + sevSerious * 10 + "px;background: orange;}" + "\n");
			bw.write(".bar .progress-line.moderate span{width: " + sevModerate * 10 + "px;background: yellow;}" + "\n");
			bw.write(".bar .progress-line.minor span{width: " + sevMinor * 10 + "px;background: blue;}" + "\n");

			bw.write(
					".progress-line span::before{position: absolute;content: \"\";top: -10px;right: 0;height: 0;width: 0;border: 7px solid transparent;border-bottom-width: 0px;border-right-width: 0px;border-top-color: #000;opacity: 0;animation: showText2 0.5s 1.5s linear forwards;}"
							+ "\n");
			bw.write(
					".progress-line span::after{position: absolute;top: -28px;right: -200px;font-weight: 500;background: white;color: black;padding: 1px 8px;font-size: 12px;border-radius: 3px;opacity: 0;animation: showText2 0.5s 1.5s linear forwards;}"
							+ "\n");
			bw.write("@keyframes showText2 {100%{opacity: 1;}}" + "\n");

			String criticalSevValue = Integer.toString(sevCritical);
			String seriousSevValue = Integer.toString(sevSerious);
			String moderateSevValue = Integer.toString(sevModerate);
			String minorSevValue = Integer.toString(sevMinor);

			bw.write(".progress-line.critical span::after{content: \"" + criticalSevValue + "\";}" + "\n");
			bw.write(".progress-line.serious span::after{content: \"" + seriousSevValue + "\";}" + "\n");
			bw.write(".progress-line.moderate span::after{content: \"" + moderateSevValue + "\";}" + "\n");
			bw.write(".progress-line.minor span::after{content: \"" + minorSevValue + "\";}" + "\n");

			// summary
			bw.write(".bloc3" + "\n");
			bw.write("{" + "\n");

			bw.write("width: 28%;" + "\n");
			bw.write("color: white;" + "\n");
			bw.write("float: left;" + "\n");
			bw.write("height:360px;" + "\n");
			bw.write("padding-bottom: 10px;" + "\n");
			bw.write("padding: 25px 30px;" + "\n");
			bw.write("background-color: transparent;" + "\n");
			bw.write("font-family: Tahoma, Geneva, sans-serif;" + "\n");
			bw.write("border-radius: 10px;" + "\n");
			bw.write("border-style: double;" + "\n");
			bw.write("border-color: white;" + "\n");
			bw.write("}" + "\n");

			// bar graph
			bw.write(".bloc1" + "\n");
			bw.write("{" + "\n");

			bw.write("width: 28%;" + "\n");
			bw.write("height:360px;" + "\n");
			bw.write("float: left;" + "\n");
			bw.write("padding-bottom: 10px;" + "\n");
			bw.write("padding: 25px 30px;" + "\n");
			bw.write("border-radius: 10px;" + "\n");
			bw.write("border-style: double;" + "\n");
			bw.write("border-color: white;" + "\n");
			bw.write("}" + "\n");
			// canvas chart
			bw.write(".bloc2" + "\n");
			bw.write("{" + "\n");
			bw.write("background-color: transparent; " + "\n");
			bw.write("width: 30%;" + "\n");
			bw.write("height:360px;" + "\n");
			bw.write("float:left;" + "\n");
			bw.write("padding-bottom: 10px;" + "\n");
			bw.write("overflow: hidden;" + "\n");
			bw.write("z-index:1;" + "\n");
			bw.write("border-style: double;" + "\n");
			bw.write("border-color: white;" + "\n");
			bw.write("padding: 25px 30px;" + "\n");
			bw.write("border-radius: 10px;" + "\n");
			bw.write("}" + "\n");

			bw.write(".dummy" + "\n");
			bw.write("{" + "\n");
			bw.write("width: 55%;" + "\n");
			bw.write("height:100px;" + "\n");
			bw.write("float:left;" + "\n");
			bw.write("background-color: #100014;" + "\n");
			bw.write("top: -20px;" + "\n");
			bw.write("z-index:2;" + "\n");
			bw.write("position: relative;" + "\n");
			bw.write("}" + "\n");

			bw.write("h3{" + "\n");
			bw.write("color: white;" + "\n");
			bw.write("font-family: Tahoma, Geneva, sans-serif;" + "\n");
			bw.write("}" + "\n");
			// bw.write(".pagecount span::after{content: \"5\";}"+"\n");
			// bw.write(".violationscount span::after{content: \"15\";}"+"\n");
			bw.write(".pagecount span::after{content: \"" + indexCount + "\";}" + "\n");
			bw.write(".violationscount span::after{content: \"" + totalViolations + "\";}" + "\n");

			bw.write("</style>" + "\n");
			bw.write("<head>" + "\n");

			bw.write("<div class=\"triangle-right\"></div>" + "\n");
			bw.write(
					"<div class=\"logo\"><img src=\"https://www.testhouse.net/wp-content/uploads/2021/08/LOGO_Testhouse.png\" width=\"350\" alt=\"My Image\" /></div>"
							+ "\n");
			bw.write("<div class=\"triangle-left\" style=\"float:right;\"></div>" + "\n");

			bw.write("<h1 align =\"center\">Accessibility Violation Test Report </h1><br>" + "\n");

			bw.write("<div class=\"bloc3\">" + "\n");
			bw.write("<h3>Summary</h3>" + "\n");
			// date here
			Date d1 = new Date();
			// bw.write("<ul><li>Reviewed on:&nbsp; Mon May 17 19.00.00 IST 2021
			// </span></li><br>"+"\n");
			bw.write("<ul><li>Reviewed on:&nbsp;  " + d1 + " </span></li><br>" + "\n");
			// url here
			bw.write("<li>Analyzed Page URL: &nbsp;" + url + " </span></li><br>" + "\n");
			// index count here
			// bw.write("<li>Total Pages Accessed:&nbsp; 3</span></li><br>"+"\n");
			// Total ule violation here
			// bw.write("<li>Total Rule Violations:&nbsp; 15</span></li></ul><br>"+"\n");

			bw.write(
					"<li>Total Pages Accessed:&nbsp;<div class=\"pagecount\" style=\"display:inline;\"><span></span></div></li><br>"
							+ "\n");
			bw.write(
					"<li>Total Rule Violations:&nbsp; <div class=\"violationscount\" style=\"display:inline;\"><span></span></div></li></ul><br>"
							+ "\n");

			bw.write("</div>" + "\n");

			bw.write("<div class=\"bloc1\"><div class=\"skill-bars\">" + "\n");
			bw.write("<h3>Accessibility Violation by Severity</h3>" + "\n");
			bw.write("<div class=\"bar\">" + "\n");
			bw.write("<div class=\"info\">" + "\n");
			bw.write("<span>Critical</span>" + "\n");
			bw.write("</div>" + "\n");
			bw.write("<div class=\"progress-line critical\">" + "\n");
			bw.write("<span></span>" + "\n");
			bw.write("</div>" + "\n");
			bw.write("</div>" + "\n");
			bw.write("<div class=\"bar\">" + "\n");
			bw.write("<div class=\"info\">" + "\n");
			bw.write("<span>Serious</span>" + "\n");
			bw.write("</div>" + "\n");
			bw.write("<div class=\"progress-line serious\">" + "\n");
			bw.write("<span></span>" + "\n");
			bw.write("</div>" + "\n");
			bw.write("</div>" + "\n");
			bw.write("<div class=\"bar\">" + "\n");
			bw.write("<div class=\"info\">" + "\n");
			bw.write("<span>Moderate</span>" + "\n");
			bw.write("</div>" + "\n");
			bw.write("<div class=\"progress-line moderate\">" + "\n");
			bw.write("<span></span>" + "\n");
			bw.write("</div>" + "\n");
			bw.write("</div>" + "\n");
			bw.write("<div class=\"bar\">" + "\n");
			bw.write("<div class=\"info\">" + "\n");
			bw.write("<span>Minor</span>" + "\n");
			bw.write("</div>" + "\n");
			bw.write("<div class=\"progress-line minor\">" + "\n");
			bw.write("<span></span>" + "\n");
			bw.write("</div>" + "\n");
			bw.write("</div>" + "\n");
			bw.write("</div>" + "\n");
			bw.write("</div></div>" + "\n");

			bw.write("<div class=\"bloc2\">" + "\n");
			bw.write("<h3>Overall Accessibility Coverage</h3>" + "\n");
			bw.write("<div id=\"chartContainer\" style=\"height: 350px; width:55%;\">" + "\n");
			bw.write("</div>" + "\n");
			bw.write("<div class=\"dummy\"></div>" + "\n");
			bw.write("</div>" + "\n");
			bw.write("<br>" + "\n");

			// 2/1/2023 edited
			bw.write("<br>" + "\n");
			bw.write("<br>" + "\n");
			bw.write("<br>" + "\n");

			int p = passedCount;
			int f = failedCount;
			int s = skippedCount;

			bw.write("<script type=\"text/javascript\">" + "\n");

			bw.write("window.onload = function () {" + "\n");

			bw.write("var chart = new CanvasJS.Chart(\"chartContainer\"," + "\n");
			bw.write("{" + "\n");
			/* removed from script and added new 04/01/20223 */
			bw.write("backgroundColor: \"transparent\"," + "\n");
			bw.write("legend:{" + "\n");
			bw.write("fontColor: 'white'," + "\n");
			bw.write("verticalAlign: \"top\"," + "\n");
			bw.write("horizontalAlign: \"center\"," + "\n");
			bw.write("fontstyle: 'Tahoma, Geneva, sans-serif'" + "\n");
			bw.write("}," + "\n");
			/* end */
			bw.write("data: [" + "\n");
			bw.write("{" + "\n");
			// startAngle: 45,
			bw.write("type: \"doughnut\"," + "\n");
			bw.write("showInLegend: true," + "\n");
			bw.write("dataPoints: [" + "\n");
//				bw.write("{  y: 5, legendText:\"Passed 5\", color: \"Green\"},"+"\n");
//				bw.write("{  y: 3, legendText:\"Failed 3\", color: \"#d3003f\" },"+"\n");
//				bw.write("{  y: 2, legendText:\"Skipped 2\", color: \"Yellow\" }"+"\n");
			bw.write("{  y: " + p + ", legendText:\"Passed " + p + "\"" + ", color: \"Green\"}," + "\n");
			bw.write("{  y: " + f + ", legendText:\"Failed " + f + "\"" + ", color: \"Red\" }," + "\n");
			bw.write("{  y: " + s + ", legendText:\"Skipped " + s + "\"" + ", color: \"Yellow\" }" + "\n");
			bw.write("]" + "\n");
			bw.write("}" + "\n");
			bw.write("]" + "\n");
			bw.write("});" + "\n");
			bw.write("chart.render();" + "\n");
			bw.write("}" + "\n");
			bw.write("</script>" + "\n");

			bw.write(
					"<script type=\"text/javascript\" src=\"https://canvasjs.com/assets/script/canvasjs.min.js\"></script></head>"
							+ "\n");
			bw.write("<body>" + "\n");

			bw.write("<br> <br> <br>" + "\n");
			String pageUrl = driver.getTitle();
			bw.write("<h3>PageUrl::" + pageUrl + "</h3>");
			// start of table
			bw.write("<table align =\"center\">" + "\n");
			// heading row
			bw.write("<tr>" + "\n");
			bw.write("<th>Sl.No</th>" + "\n");
			bw.write("<th>Violations</th>" + "\n");
			bw.write("<th>Standards Violated</th>" + "\n");
			bw.write("<th>Severity</th>" + "\n");
			// bw.write("<th>Page url</th>"+"\n");
			bw.write("<th>Help URL</th>" + "\n");
			bw.write("<th>Fixes</th>" + "\n"); //--> removing for now
			bw.write("</tr>" + "\n");
			// heading row closed

			JSONArray violations = violationsArray;

			int fixesCount, greaterfixedCount = 0;
			int length = violations.length();
			int slNo = 0;
			String standards = "";
			if (length != 0) {
				for (int j = 0; j < length; j++) {
					// System.out.println("#########################################");
					String k = violations.get(j).toString();
					// System.out.println(j + 1 + "-->");

					String[] arrOfStr1 = k.split("helpUrl");

					String[] fixes = arrOfStr1[0].split("message");
					// Fixes count

					fixesCount = fixes.length;
					System.out.println("fixes count " + fixesCount);

					if (fixesCount > greaterfixedCount)//

					{
						greaterfixedCount = fixesCount + 3;
					}

				}

				final StringBuilder sb = new StringBuilder();
				final StringBuilder sb2 = new StringBuilder();

				sb.append("Found ").append(violations.length()).append(" accessibility violations:");
				data2 = new String[greaterfixedCount][7];
				data2[0][0] = "Violation";
				data2[0][1] = "Help URL";
				data2[0][2] = "Target";
				data2[0][3] = "Fix All of the following";
				data2[0][4] = "Fix Any of the following";

				data2[0][5] = "Severity/Impact";
				data2[0][6] = "Standard/Guidelines";

				String target = "", All_Fixes = "", Any_Fixes = "";
				for (int i = 0; i < violations.length(); i++) {

					int startindex = i + 1;
					JSONObject node = null;
					JSONObject violation = violations.getJSONObject(i);
					sb.append(lineSeparator).append(i + 1).append(") ").append(violation.getString("help"));

					data2[startindex][0] = violation.getString("help");
					data2[startindex][5] = violation.getString("impact");
					data2[startindex][6] = violation.getJSONArray("tags").toString();

					JSONArray all = null;
					JSONArray none = null, any = null;
					if (violation.has("helpUrl")) {
						String helpUrl = violation.getString("helpUrl");
						data2[startindex][1] = helpUrl;

						sb.append(": ").append(helpUrl);
					}

					JSONArray nodes = violation.getJSONArray("nodes");

					for (int j = 0; j < nodes.length(); j++) {

						sb2.setLength(0);
						node = nodes.getJSONObject(j);
						sb.append(lineSeparator).append("  ").append(getOrdinal(j + 1)).append(") ")
								.append(node.getJSONArray("target")).append(lineSeparator);
						target = node.getJSONArray("target").toString();
						//+ target;

						all = node.getJSONArray("all");
						none = node.getJSONArray("none");
						any = node.getJSONArray("any");

						//failuresummary = node.getString("failureSummary");//new add
						//html = node.getString("html");

						if (all.length() > 0) {
							appendFixes(sb2, all, "Fix all of the following:");
							int startindex_all = sb2.indexOf("Fix all of the following:");
							//All_Fixes = sb2.substring(startindex_all + 26) + All_Fixes;
							Any_Fixes = sb2.substring(startindex_all + 26) + "<br>" + "Target Element " + target + Any_Fixes;

							All_Fixes = "<br>" + All_Fixes;

						}
						if (any.length() > 0) {
							appendFixes(sb2, any, "Fix any of the following:");
							int startindex_any = sb2.indexOf("Fix any of the following:");
							Any_Fixes = sb2.substring(startindex_any + 26) + "<br>" + "Target Element " + target + Any_Fixes;
							Any_Fixes = "<br>" + Any_Fixes;

							// Any_Fixes = "<br>"+Any_Fixes;

						}

					}

					data2[startindex][2] = target;

					data2[startindex][3] = All_Fixes;

					data2[startindex][4] = Any_Fixes;

//						System.out.println("====================+++++++++++++++++++++====================");
//						System.out.println(violation.getString("help"));
//						System.out.println(violation.getString("helpUrl"));
//						System.out.println(violation.getString("impact"));
//						System.out.println(violation.getJSONArray("tags").toString());
//						System.out.println(target);
//						System.out.println(All_Fixes);
//						System.out.println(Any_Fixes);
//						System.out.println("<<<<<<<<<<<<<<<<<<<<<<<<<<");

					fixesAll2 = "<br>" + fixesAll2 + All_Fixes + "\n";

					fixesAll = "<br>" + fixesAll + Any_Fixes + "\n";

					if (All_Fixes.isBlank() && Any_Fixes.isBlank()) {
						finalAllFixes = "";
					}
					if (All_Fixes.isBlank() == false && Any_Fixes.isBlank()) {
						finalAllFixes = "Fix all of the following : \n" + fixesAll2;
					}
					if (All_Fixes.isBlank() && Any_Fixes.isBlank() == false) {
						finalAllFixes = "Fix any of the following :\n" + fixesAll;
					}
					if (All_Fixes.isBlank() == false && Any_Fixes.isBlank() == false) {
						finalAllFixes = "Fix all of the following : \n" + fixesAll2 + "Fix any of the following :\n"
								+ fixesAll;
					}
					// finalAllFixes = "Fix all of the following : \n"+fixesAll2 + "Fix any of the
					// following :\n"+fixesAll;
//						System.out.println(Any_Fixes = "\n" + Any_Fixes + "\n");
//						System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>");
//						System.out.println("====================+++++++++++++++++++++====================");

					slNo = slNo + 1;
					bw.write("<tr>" + "\n");
					bw.write("<td>" + slNo + ".</td>" + "\n");
					// bw.write("<td>Elements must have sufficient color contrast</td>"+"\n");
					bw.write("<td>" + violation.getString("help") + "</td>" + "\n");
//						bw.write("<td>"
//								+ "\"cat.forms\",\"wcag2a\",\"wcag332\",\"wcag131\",\"section508\",\"section508.22.n\""
//								+ ".</td>" + "\n");
					Boolean flag = false;
					String items = "";
					for (int m = 1; m < violation.getJSONArray("tags").length(); m++) {
						items = violation.getJSONArray("tags").getString(m);
						if (m == 1) {
							standards = standards + items;
						} else {
							standards = standards + "," + items;
						}

						if (m == violation.getJSONArray("tags").length() - 1) {
							flag = true;
						}
					}
					// System.out.println("****************************************************************"+standards);
					standards = standards.toUpperCase();
					// bw.write("<td>" + violation.getJSONArray("tags").toString() + "</td>" +
					// "\n");
					bw.write("<td>" + standards + "</td>" + "\n");
					// bw.write("<td>" + violation.getString("impact") + "\n");
					String impact = violation.getString("impact");
					impact = impact.substring(0, 1).toUpperCase() + impact.substring(1);
					// String res = s.substring(0, 1).toUpperCase() + s.substring(1);
					// bw.write("<td>" + violation.getString("impact") + "\n");
					bw.write("<td>" + impact + "\n");

					bw.write("<td><a href=" + violation.getString("helpUrl") + ">Click here</a> </td>" + "\n");
					bw.write("<td>" + finalAllFixes + "\n"); //--> removing for now

					bw.write("</td>" + "\n");
					bw.write("</tr>" + "\n");
//						System.out.println("*********************************");
//						System.out.println(fixesAll);
//						System.out.println("*********************************");
					target = "";
					All_Fixes = "";
					Any_Fixes = "";

					fixesAll = "";
					if (flag == true) {
						standards = "";
					}

				}

				bw.write("</table>" + "\n");
				bw.write("</body>" + "\n");
				bw.write("</html>" + "\n");

			}

			else {
				Assert.assertTrue(true);
			}

		}

		if (indexCount > 1) {

			bw.write("<!DOCTYPE HTML>" + "\n");
			bw.write("<html>" + "\n");

			bw.write("<style>" + "\n");

			// adding new codes 06/01/23
			bw.write(".triangle-right {" + "\n");
			bw.write("width: 0;" + "\n");
			bw.write("height: 0;" + "\n");

			bw.write(";border-left: 1450px solid white;" + "\n");
			bw.write(";border-bottom: 250px solid transparent;" + "\n");
			bw.write("position: relative;" + "\n");
			bw.write("}" + "\n");
			bw.write(".triangle-left {" + "\n");
			bw.write("width: 0;" + "\n");
			bw.write("height: 0;" + "\n");
			bw.write("border-top: 150px solid transparent;" + "\n");
			bw.write("border-right: 850px solid #d3003f;" + "\n");
			bw.write("border-bottom: 40px solid transparent;" + "\n");
			bw.write("top: 6px;" + "\n");
			bw.write("right:4px;" + "\n");
			bw.write("position: absolute;" + "\n");
			bw.write("}" + "\n");
			bw.write(".triangle-bottomright {" + "\n");
			bw.write("width: 0;" + "\n");
			bw.write("height: 0;" + "\n");
			bw.write("border-top: 150px solid transparent;" + "\n");
			bw.write("border-right: 850px solid #d3003f;" + "\n");
			bw.write("border-bottom: 4px solid transparent;" + "\n");
			bw.write("position: absolute;" + "\n");
			bw.write("right: 0px;" + "\n");
			bw.write("bottom: 0px;" + "\n");
			bw.write("}" + "\n");
			bw.write(".triangle-bottomleft {" + "\n");
			bw.write("width: 0;" + "\n");
			bw.write("height: 0px;" + "\n");
			bw.write("border-left: 1px solid transparent;" + "\n");
			bw.write("border-right: 850px solid transparent;" + "\n");
			bw.write("border-bottom: 200px solid #d3003f;" + "\n");
			bw.write("}" + "\n");
			bw.write(".logo{" + "\n");
			bw.write("position: absolute;" + "\n");
			bw.write("z-index: 1;" + "\n");
			bw.write("top: 30px;" + "\n");

			bw.write("}" + "\n");
			bw.write("body{" + "\n");

			bw.write("background-color: #1f1f1f;" + "\n");
			bw.write("background-repeat: no-repeat;" + "\n");
			bw.write("background-attachment: fixed;" + "\n");
			bw.write("background-size: cover;" + "\n");
			bw.write("}" + "\n");

			bw.write(".canvasjs-chart-credit{" + "\n");
			bw.write("content-visibility: hidden;" + "\n");
			bw.write("backface-visibility: hidden;" + "\n");
			bw.write("}" + "\n");
			bw.write("table {" + "\n");
			bw.write("border-collapse: collapse;" + "\n");
			bw.write("font-family: Tahoma, Geneva, sans-serif;" + "\n");
			bw.write("}" + "\n");
			bw.write("table td {" + "\n");
			bw.write("padding: 15px;" + "\n");
			bw.write("}" + "\n");
			bw.write("table th {" + "\n");
			bw.write("background-color: #db4e44;" + "\n");
			bw.write("}" + "\n");
			bw.write("able thead td {" + "\n");
			bw.write("background-color: #32CD32;" + "\n");
			bw.write("color: #ffffff;" + "\n");
			bw.write("font-weight: bold;" + "\n");
			bw.write("font-size: 13px;" + "\n");
			bw.write("border: 1px solid #54585d;" + "\n");
			bw.write("}" + "\n");
			bw.write("table tbody td {" + "\n");
			bw.write("color: #636363;" + "\n");
			bw.write("border: 1px solid #dddfe1;" + "\n");
			bw.write("}" + "\n");
			bw.write("table tbody tr {" + "\n");
			bw.write("background-color: #f9fafb;" + "\n");
			bw.write("}" + "\n");
			bw.write("table tbody tr:nth-child(odd) {" + "\n");
			bw.write("background-color: #ffffff;" + "\n");
			bw.write("}" + "\n");

			bw.write("h1 {" + "\n");
			bw.write("color: white;" + "\n");
			bw.write("font-family: Tahoma, Geneva, sans-serif;" + "\n");
			bw.write("font-size: 250%;" + "\n");
			bw.write("}" + "\n");

			bw.write(".skill-bars{width: 350px;background: transparent;border-radius: 10px;}" + "\n");
			bw.write(".skill-bars .bar{margin: 20px 0;}" + "\n");
			bw.write(".skill-bars .bar:first-child{margin-top: 0px;}" + "\n");
			bw.write(".skill-bars .bar .info{margin-bottom: 5px;}" + "\n");
			bw.write(
					".skill-bars .bar .info span{font-weight: 500;font-size: 17px;opacity: 0;animation: showText 0.5s 1s linear forwards;color: white;}"
							+ "\n");
			bw.write("@keyframes showText {100%{opacity: 1;}}" + "\n");
			bw.write(
					".skill-bars .bar .progress-line{height: 10px;width: 100%;background: #f0f0f0;position: relative;transform: scaleX(0);transform-origin: left;border-radius: 10px;box-shadow: inset 0 1px 1px rgba(0,0,0,0.05),0 1px rgba(255,255,255,0.8);animation: animate 1s cubic-bezier(1,0,0.5,1) forwards;}"
							+ "\n");
			bw.write("@keyframes animate {100%{transform: scaleX(1);}}" + "\n");
			bw.write(
					".bar .progress-line span{height: 100%;position: absolute;border-radius: 10px;transform: scaleX(0);transform-origin: left;animation: animate 1s 1s cubic-bezier(1,0,0.5,1) forwards;}"
							+ "\n");

			bw.write(".bar .progress-line.critical span{width: " + sevCritical * 10 + "px;background: red;}" + "\n");
			bw.write(".bar .progress-line.serious span{width: " + sevSerious * 10 + "px;background: orange;}" + "\n");
			bw.write(".bar .progress-line.moderate span{width: " + sevModerate * 10 + "px;background: yellow;}" + "\n");
			bw.write(".bar .progress-line.minor span{width: " + sevMinor * 10 + "px;background: blue;}" + "\n");

			bw.write(
					".progress-line span::before{position: absolute;content: \"\";top: -10px;right: 0;height: 0;width: 0;border: 7px solid transparent;border-bottom-width: 0px;border-right-width: 0px;border-top-color: #000;opacity: 0;animation: showText2 0.5s 1.5s linear forwards;}"
							+ "\n");
			bw.write(
					".progress-line span::after{position: absolute;top: -28px;right: -200px;font-weight: 500;background: white;color: black;padding: 1px 8px;font-size: 12px;border-radius: 3px;opacity: 0;animation: showText2 0.5s 1.5s linear forwards;}"
							+ "\n");
			bw.write("@keyframes showText2 {100%{opacity: 1;}}" + "\n");

			String criticalSevValue = Integer.toString(sevCritical);
			String seriousSevValue = Integer.toString(sevSerious);
			String moderateSevValue = Integer.toString(sevModerate);
			String minorSevValue = Integer.toString(sevMinor);

			bw.write(".progress-line.critical span::after{content: \"" + criticalSevValue + "\";}" + "\n");
			bw.write(".progress-line.serious span::after{content: \"" + seriousSevValue + "\";}" + "\n");
			bw.write(".progress-line.moderate span::after{content: \"" + moderateSevValue + "\";}" + "\n");
			bw.write(".progress-line.minor span::after{content: \"" + minorSevValue + "\";}" + "\n");

			// summary
			bw.write(".bloc3" + "\n");
			bw.write("{" + "\n");

			bw.write("width: 28%;" + "\n");
			bw.write("color: white;" + "\n");
			bw.write("float: left;" + "\n");
			bw.write("height:360px;" + "\n");
			bw.write("padding-bottom: 10px;" + "\n");
			bw.write("padding: 25px 30px;" + "\n");
			bw.write("background-color: transparent;" + "\n");
			bw.write("font-family: Tahoma, Geneva, sans-serif;" + "\n");
			bw.write("border-radius: 10px;" + "\n");
			bw.write("border-style: double;" + "\n");
			bw.write("border-color: white;" + "\n");
			bw.write("}" + "\n");

			// bar graph
			bw.write(".bloc1" + "\n");
			bw.write("{" + "\n");

			bw.write("width: 28%;" + "\n");
			bw.write("height:360px;" + "\n");
			bw.write("float: left;" + "\n");
			bw.write("padding-bottom: 10px;" + "\n");
			bw.write("padding: 25px 30px;" + "\n");
			bw.write("border-radius: 10px;" + "\n");
			bw.write("border-style: double;" + "\n");
			bw.write("border-color: white;" + "\n");
			bw.write("}" + "\n");
			// canvas chart
			bw.write(".bloc2" + "\n");
			bw.write("{" + "\n");
			bw.write("background-color: transparent; " + "\n");
			bw.write("width: 30%;" + "\n");
			bw.write("height:360px;" + "\n");
			bw.write("float:left;" + "\n");
			bw.write("padding-bottom: 10px;" + "\n");
			bw.write("overflow: hidden;" + "\n");
			bw.write("z-index:1;" + "\n");
			bw.write("border-style: double;" + "\n");
			bw.write("border-color: white;" + "\n");
			bw.write("padding: 25px 30px;" + "\n");
			bw.write("border-radius: 10px;" + "\n");
			bw.write("}" + "\n");

			bw.write(".dummy" + "\n");
			bw.write("{" + "\n");
			bw.write("width: 55%;" + "\n");
			bw.write("height:100px;" + "\n");
			bw.write("float:left;" + "\n");
			bw.write("background-color: #100014;" + "\n");
			bw.write("top: -20px;" + "\n");
			bw.write("z-index:2;" + "\n");
			bw.write("position: relative;" + "\n");
			bw.write("}" + "\n");

			bw.write("h3{" + "\n");
			bw.write("color: white;" + "\n");
			bw.write("font-family: Tahoma, Geneva, sans-serif;" + "\n");
			bw.write("}" + "\n");

			bw.write(".pagecount span::after{content: \"" + indexCount + "\";}" + "\n");
			bw.write(".violationscount span::after{content: \"" + totalViolations + "\";}" + "\n");

			bw.write("</style>" + "\n");

			bw.write("<head>" + "\n");

			int p = passedCount;
			int f = failedCount;
			int s = skippedCount;

			bw.write("<script type=\"text/javascript\">" + "\n");

			bw.write("window.onload = function () {" + "\n");

			bw.write("var chart = new CanvasJS.Chart(\"chartContainer\"," + "\n");
			bw.write("{" + "\n");
			/* removed from script and added new 04/01/20223 */
			bw.write("backgroundColor: \"transparent\"," + "\n");
			bw.write("legend:{" + "\n");
			bw.write("fontColor: 'white'," + "\n");
			bw.write("verticalAlign: \"top\"," + "\n");
			bw.write("horizontalAlign: \"center\"," + "\n");
			bw.write("fontstyle: 'Tahoma, Geneva, sans-serif'" + "\n");
			bw.write("}," + "\n");
			/* end */
			bw.write("data: [" + "\n");
			bw.write("{" + "\n");
			// startAngle: 45,
			bw.write("type: \"doughnut\"," + "\n");
			bw.write("showInLegend: true," + "\n");
			bw.write("dataPoints: [" + "\n");
//				bw.write("{  y: 5, legendText:\"Passed 5\", color: \"Green\"},"+"\n");
//				bw.write("{  y: 3, legendText:\"Failed 3\", color: \"#d3003f\" },"+"\n");
//				bw.write("{  y: 2, legendText:\"Skipped 2\", color: \"Yellow\" }"+"\n");
			bw.write("{  y: " + p + ", legendText:\"Passed " + p + "\"" + ", color: \"Green\"}," + "\n");
			bw.write("{  y: " + f + ", legendText:\"Failed " + f + "\"" + ", color: \"Red\" }," + "\n");
			bw.write("{  y: " + s + ", legendText:\"Skipped " + s + "\"" + ", color: \"Yellow\" }" + "\n");
			bw.write("]" + "\n");
			bw.write("}" + "\n");
			bw.write("]" + "\n");
			bw.write("});" + "\n");
			bw.write("chart.render();" + "\n");
			bw.write("}" + "\n");
			bw.write("</script>" + "\n");

			bw.write(
					"<script type=\"text/javascript\" src=\"https://canvasjs.com/assets/script/canvasjs.min.js\"></script></head>"
							+ "\n");
			bw.write("<body>" + "\n");

			bw.write("<br> <br> <br>" + "\n");

			String pageUrl = driver.getTitle();
			bw.write("<h3>PageUrl::" + pageUrl + "</h3>");
			// start of table
			bw.write("<table align =\"center\">" + "\n");
			// heading row
			bw.write("<tr>" + "\n");
			bw.write("<th>Sl.No</th>" + "\n");
			bw.write("<th>Violations</th>" + "\n");
			bw.write("<th>Standards Violated</th>" + "\n");
			bw.write("<th>Severity</th>" + "\n");
			// bw.write("<th>Page url</th>"+"\n");
			bw.write("<th>Help URL</th>" + "\n");
			bw.write("<th>Fixes</th>" + "\n"); //--> removing for now
			bw.write("</tr>" + "\n");
			// heading row closed

			JSONArray violations = violationsArray;

//					}
			int fixesCount, greaterfixedCount = 0;
			int length = violations.length();
			int slNo = 0;
			String standards1 = "";
			if (length != 0) {
				for (int j = 0; j < length; j++) {
					// System.out.println("#########################################");
					String k = violations.get(j).toString();
					// System.out.println(j + 1 + "-->");

					String[] arrOfStr1 = k.split("helpUrl");

					String[] fixes = arrOfStr1[0].split("message");
					// Fixes count

					fixesCount = fixes.length;
					// System.out.println("fixes count " + fixesCount);

					if (fixesCount > greaterfixedCount)//

					{
						greaterfixedCount = fixesCount;
					}

				}

				final StringBuilder sb = new StringBuilder();
				final StringBuilder sb2 = new StringBuilder();

				sb.append("Found ").append(violations.length()).append(" accessibility violations:");
				data2 = new String[greaterfixedCount][7];
				data2[0][0] = "Violation";
				data2[0][1] = "Help URL";
				data2[0][2] = "Target";
				data2[0][3] = "Fix All of the following";
				data2[0][4] = "Fix Any of the following";

				data2[0][5] = "Severity/Impact";
				data2[0][6] = "Standard/Guidelines";

				String target = "", All_Fixes = "", Any_Fixes = "";
				for (int i = 0; i < violations.length(); i++) {

					int startindex = i + 1;
					JSONObject node = null;
					JSONObject violation = violations.getJSONObject(i);
					sb.append(lineSeparator).append(i + 1).append(") ").append(violation.getString("help"));

					data2[startindex][0] = violation.getString("help");
					data2[startindex][5] = violation.getString("impact");
					data2[startindex][6] = violation.getJSONArray("tags").toString();

					JSONArray all = null;
					JSONArray none = null, any = null;
					if (violation.has("helpUrl")) {
						String helpUrl = violation.getString("helpUrl");
						data2[startindex][1] = helpUrl;

						sb.append(": ").append(helpUrl);
					}

					JSONArray nodes = violation.getJSONArray("nodes");

					for (int j = 0; j < nodes.length(); j++) {

						sb2.setLength(0);
						node = nodes.getJSONObject(j);
						sb.append(lineSeparator).append("  ").append(getOrdinal(j + 1)).append(") ")
								.append(node.getJSONArray("target")).append(lineSeparator);
						target = node.getJSONArray("target").toString() + target;

						all = node.getJSONArray("all");
						none = node.getJSONArray("none");
						any = node.getJSONArray("any");

						if (all.length() > 0) {
							appendFixes(sb2, all, "Fix all of the following:");
							int startindex_all = sb2.indexOf("Fix all of the following:");
							All_Fixes = sb2.substring(startindex_all + 26) + All_Fixes;

							All_Fixes = "<br>" + All_Fixes;

						}
						if (any.length() > 0) {
							appendFixes(sb2, any, "Fix any of the following:");
							int startindex_any = sb2.indexOf("Fix any of the following:");
							Any_Fixes = sb2.substring(startindex_any + 26) + Any_Fixes;

							Any_Fixes = "<br>" + Any_Fixes;

							// Any_Fixes = "<br>"+Any_Fixes;

						}

					}

					data2[startindex][2] = target;

					data2[startindex][3] = All_Fixes;

					data2[startindex][4] = Any_Fixes;

//						System.out.println("====================+++++++++++++++++++++====================");
//						System.out.println(violation.getString("help"));
//						System.out.println(violation.getString("helpUrl"));
//						System.out.println(violation.getString("impact"));
//						System.out.println(violation.getJSONArray("tags").toString());
//						System.out.println(target);
//						System.out.println(All_Fixes);
//						System.out.println(Any_Fixes);
//						System.out.println("<<<<<<<<<<<<<<<<<<<<<<<<<<");

					fixesAll2 = "<br>" + fixesAll2 + All_Fixes + "\n";

					fixesAll = "<br>" + fixesAll + Any_Fixes + "\n";

					if (All_Fixes.isBlank() && Any_Fixes.isBlank()) {
						finalAllFixes = "";
					}
					if (All_Fixes.isBlank() == false && Any_Fixes.isBlank()) {
						finalAllFixes = "Fix all of the following : \n" + fixesAll2;
					}
					if (All_Fixes.isBlank() && Any_Fixes.isBlank() == false) {
						finalAllFixes = "Fix any of the following :\n" + fixesAll;
					}
					if (All_Fixes.isBlank() == false && Any_Fixes.isBlank() == false) {
						finalAllFixes = "Fix all of the following : \n" + fixesAll2 + "Fix any of the following :\n"
								+ fixesAll;
					}

//						System.out.println(Any_Fixes = "\n" + Any_Fixes + "\n");
//						System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>");
//						System.out.println("====================+++++++++++++++++++++====================");

					slNo = slNo + 1;
					bw.write("<tr>" + "\n");
					bw.write("<td>" + slNo + ".</td>" + "\n");
					// bw.write("<td>Elements must have sufficient color contrast</td>"+"\n");
					bw.write("<td>" + violation.getString("help") + "</td>" + "\n");

					Boolean flag = false;
					String items = "";
					for (int m = 1; m < violation.getJSONArray("tags").length(); m++) {
						items = violation.getJSONArray("tags").getString(m);
						if (m == 1) {
							standards1 = standards1 + items;
						} else {
							standards1 = standards1 + "," + items;
						}

						if (m == violation.getJSONArray("tags").length() - 1) {
							flag = true;
						}
					}
					standards1 = standards1.toUpperCase();
					// bw.write("<td>" + violation.getJSONArray("tags").toString() + "</td>" +
					// "\n");
					bw.write("<td>" + standards1 + "</td>" + "\n");
					String impact = violation.getString("impact");
					impact = impact.substring(0, 1).toUpperCase() + impact.substring(1);
					// String res = s.substring(0, 1).toUpperCase() + s.substring(1);
					// bw.write("<td>" + violation.getString("impact") + "\n");
					bw.write("<td>" + impact + "\n");

					bw.write("<td><a href=" + violation.getString("helpUrl") + ">Click here</a> </td>" + "\n");
					// bw.write("<td>" + finalAllFixes + "\n");--> removing for now

					bw.write("</td>" + "\n");
					bw.write("</tr>" + "\n");
//						System.out.println("*********************************");
//						System.out.println(fixesAll);
//						System.out.println("*********************************");
					target = "";
					All_Fixes = "";
					Any_Fixes = "";

					fixesAll = "";
					if (flag == true) {
						standards1 = "";
					}

				}

				// createAccessibilityReport(length);

				// Markup m = MarkupHelper.createTable(data2);
				// TestBase.test.log(Status.FAIL, m);

				bw.write("</table>" + "\n");
				bw.write("</body>" + "\n");
				bw.write("</html>" + "\n");

			}
//				totalViolation =totalViolation+totalVio;
//				System.out.println("total violations are ................" +totalViolation);
		}

		bw.flush();
		bw.close();


	}



}
