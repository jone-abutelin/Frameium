package com.frameium.baseclass;

import java.io.File;
import java.io.FileInputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.file.Files;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.json.JSONArray;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.ITestResult;
import org.testng.Reporter;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.gherkin.model.Scenario;
//import com.frameium.configuration.ObjectReader;
import com.frameium.configuration.*;
//import com.frameium.configuration.PropertyReader;
import com.frameium.genericfunctions.GenericFunctions;
//import com.frameium.logger.LoggerHelper;
import com.frameium.logger.LoggerHelper;
import com.frameium.resource.ResourceHelper;
import com.frameium.utilities.ExtentManager;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.service.local.AppiumDriverLocalService;
import io.appium.java_client.service.local.AppiumServiceBuilder;
import io.cucumber.java.After;
import io.cucumber.java.AfterStep;
import io.cucumber.java.Before;
import io.cucumber.testng.FeatureWrapper;
import io.cucumber.testng.PickleWrapper;
import io.cucumber.testng.TestNGCucumberRunner;
import io.github.bonigarcia.wdm.WebDriverManager;

public class TestBase extends GenericFunctions {

	public static ExtentReports extent;
	public static ExtentTest test;
	private Logger log = LoggerHelper.getLogger(TestBase.class);
	

	/*******************************************Android device Setup*****************************************************************************/

	public static AndroidDriver deviceConnection() throws MalformedURLException {

		// start the web server
//		AppiumDriverLocalService appiumlocalservice = new AppiumServiceBuilder().usingAnyFreePort().build();
//		appiumlocalservice.start();

		File rootPath = new File(System.getProperty("user.dir"));
		File app = new File(rootPath, "pCloudy Appium Demo.apk");

		if (androiddriver == null) {
			String AndroidDriver = null;
			DesiredCapabilities capabilities = new DesiredCapabilities();
			capabilities.setCapability("deviceName", getProperty("deviceName"));
			capabilities.setCapability("platformName", getProperty("platformName"));
			capabilities.setCapability("platformVersion", getProperty("platformVersion"));
			capabilities.setCapability("automationName", getProperty("automationName"));
			capabilities.setCapability("appPackage", getProperty("appPackage"));
			capabilities.setCapability("appActivity", getProperty("appActivity"));
			//capabilities.setCapability("fullReset", true);
			capabilities.setCapability("app", app.getAbsolutePath());
			androiddriver = new AndroidDriver(new URL("http://127.0.0.1:4723/wd/hub"),capabilities);
			//androiddriver = new AndroidDriver(appiumlocalservice, capabilities);
		}

		else if (androiddriver != null) {
			androiddriver.quit();
			String AndroidDriver = null;
			DesiredCapabilities capabilities = new DesiredCapabilities();
			capabilities.setCapability("deviceName", getProperty("deviceName"));
			capabilities.setCapability("platformName", getProperty("platformName"));
			capabilities.setCapability("platformVersion", getProperty("platformVersion"));
			capabilities.setCapability("automationName", getProperty("automationName"));
			capabilities.setCapability("appPackage", getProperty("appPackage"));
			capabilities.setCapability("appActivity", getProperty("appActivity"));
			//capabilities.setCapability("fullReset", true);
			capabilities.setCapability("app", app.getAbsolutePath());
			androiddriver = new AndroidDriver(new URL("http://127.0.0.1:4723/wd/hub"), capabilities);
		}

		return androiddriver;
	}
	
	public AndroidDriver initiateDriver(String deviceName,String realDeviceName, String platformVersion, String platformName, String appPackage, String appActivity) throws MalformedURLException
    {
        DesiredCapabilities capabilities = new DesiredCapabilities();
        GenericFunctions ag = new GenericFunctions();
        
        
        if(deviceName == ag.getProperty("emulator"))
        {
        capabilities.setCapability("deviceName",ag.getProperty("emulator"));
        capabilities.setCapability("platformVersion",ag.getProperty(platformVersion));
        capabilities.setCapability("platformName",ag.getProperty(platformName));
        capabilities.setCapability("appPackage",ag.getProperty(appPackage));
        capabilities.setCapability("appActivity",ag.getProperty(appActivity));
        androiddriver = new AndroidDriver(new URL("http://127.0.0.1:4723/wd/hub"), capabilities);
        }
        else if(realDeviceName == ag.getProperty("realDevice"))
        {
            capabilities.setCapability("deviceName",ag.getProperty("realDevice"));
            capabilities.setCapability("platformVersion",ag.getProperty(platformVersion));
            capabilities.setCapability("platformName",ag.getProperty(platformName));
            capabilities.setCapability("appPackage",ag.getProperty(appPackage));
            capabilities.setCapability("appActivity",ag.getProperty(appActivity));
            androiddriver = new AndroidDriver(new URL("http://172.168.26.20:4725/wd/hub"), capabilities);  
        }
        return androiddriver;
    }
	 
	 
	 @Parameters({"Pixel 4 API 30","realDeviceName","platformVersion","platformName","appPackage","appActivity"})
	 public void setAndroidDriver() throws MalformedURLException {
         
		 androiddriver = initiateDriver("deviceName","realDeviceName","platformVersion","platformName","appPackage","appActivity");
     }

}

