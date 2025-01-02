package com.frameium.baseclass;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.Status;
import com.epam.healenium.SelfHealingDriver;
import com.frameium.genericfunctions.GenericFunctions;
import com.frameium.logger.LoggerHelper;
import com.frameium.resource.ResourceHelper;
import com.frameium.utilities.ExtentReportManager;
import io.appium.java_client.android.AndroidDriver;
import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.ie.InternetExplorerOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.safari.SafariDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import static com.frameium.genericfunctions.GenericFunctions.getConfigProperty;
import static com.frameium.genericfunctions.GenericFunctions.getMobileDeviceProperty;

public class BaseTest {

    public static WebDriver driver;

    public final static int TIMEOUT = 10;

    public static SelfHealingDriver driver2;
    private static final String KEY_SAFARI_WEBDRIVER = "webdriver.safari.driver";
    private static final String SAFARI_WEBDRIVER_PATH = "/usr/bin/safaridriver";
    private Logger log = LoggerHelper.getLogger(GenericFunctions.class);

    public BaseTest(){
    }




    public WebDriver getDriver() {

        String platform = getConfigProperty("run.platform");
        String deviceOS = getConfigProperty("device.os");
        String webOS = getConfigProperty("web.os");
        String browsertype = getConfigProperty("browserType");
        String headless = getConfigProperty("headless");
        String selfhealingoption = getConfigProperty("selfhealing");


        if (driver == null) {

            switch (platform) {

                case "mobile":

                    if (deviceOS.equalsIgnoreCase("Android")) {

                        try {

                            //Set the Desired Capabilities
                            DesiredCapabilities caps = new DesiredCapabilities();
                            //caps.setCapability("deviceName", "");
                            caps.setCapability("udid", getMobileDeviceProperty("udid")); //Give Device ID of your mobile phone
                            caps.setCapability("platformName", getMobileDeviceProperty("platformName"));
                            caps.setCapability("platformVersion", getMobileDeviceProperty("platformVersion"));
                            caps.setCapability("appium:automationName", "uiautomator2");

                            caps.setCapability("app", "D:\\frameium\\june04_cloned\\Frameium\\Frameium\\apk\\app-release.apk");




                            //Instantiate Appium Driver
                            System.out.println("<<<<<<<<<< initDriver >>>>>>>>>>> ");

                            try {
                                driver = new AndroidDriver(new URL("http://127.0.0.1:4723"), caps);
                            } catch (MalformedURLException e) {
                                System.out.println(e.getMessage());
                            }

                            driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(60));

                        } catch (Exception e) {
                            e.printStackTrace();
                        }

                    } else if (deviceOS.equalsIgnoreCase("Android_Chrome")) {

                        try {

                            //Set the Desired Capabilities
                            DesiredCapabilities caps = new DesiredCapabilities();
                            //caps.setCapability("deviceName", "");
                            caps.setCapability("udid", getMobileDeviceProperty("udid")); //Give Device ID of your mobile phone
                            caps.setCapability("platformName", getMobileDeviceProperty("platformName"));
                            caps.setCapability("platformVersion", getMobileDeviceProperty("platformVersion"));
                            caps.setCapability("browserName", getMobileDeviceProperty("browserName"));
                            caps.setCapability("appium:automationName", "uiautomator2");


                            //Instantiate Appium Driver
                            System.out.println("<<<<<<<<<< initDriver >>>>>>>>>>> ");

                            try {
                                driver = new AndroidDriver(new URL("http://127.0.0.1:4723"), caps);
                            } catch (MalformedURLException e) {
                                System.out.println(e.getMessage());
                            }

                            //Open URL in Chrome Browser
                            // driver.get("https://www.saudiexchange.sa/");

                            driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(60));

                        } catch (Exception e) {
                            e.printStackTrace();
                        }


                    } else if (deviceOS.equalsIgnoreCase("IOS")) {


                    } else {
                        System.out.println("=====>>>>>> device OS Config value not found ");
                        System.exit(0);
                    }

                    break;

                case "web":

                    if (browsertype.toString().equalsIgnoreCase("Chrome")) {


                        if(webOS.equalsIgnoreCase("mac")){




                            // Path to Chrome application in macOS
                            String chromePath = "/Applications/Google Chrome.app";

                            // Check if Chrome application exists
                            File chromeFile = new File(chromePath);
                            System.out.println("<<<<<< %%%%%%% Chrome is installed on this system. >>+ "+  chromeFile.exists());


                            System.setProperty("webdriver.chrome.driver",
                                    ResourceHelper.getResourcePath("src/main/resources/drivers/chromedriver"));

                             // Initialize Chrome driver
                             driver = new ChromeDriver();

                        }else {
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
                            if (selfhealingoption.equalsIgnoreCase("no")) {
                                driver = new ChromeDriver(chromeoptions); //(uncomment and comment below 2)}
                            } else {
                                driver = driver2;
                                WebDriver delegate = new ChromeDriver(chromeoptions);
                                driver = SelfHealingDriver.create(delegate);
                            }

                        }

                    } else if (browsertype.toString().equalsIgnoreCase("Safari")) {

                        System.setProperty(KEY_SAFARI_WEBDRIVER, SAFARI_WEBDRIVER_PATH);

                        // Instantiate a SafariDriver class.
                        driver = new SafariDriver();

                    } else if (browsertype.toString().equalsIgnoreCase("Edges")) {

                        // Setting webdriver.chrome.driver property

                        System.setProperty("webdriver.edge.driver",
                                ResourceHelper.getResourcePath("src/main/resources/drivers/msedgedriver.exe"));
                        // Create a new instance of the Edge driver
                        // WebDriverManager.edgedriver().setup();
                        EdgeOptions edgeOptions = new EdgeOptions();
                        edgeOptions.addArguments("--disable-notifications");
                        edgeOptions.addArguments("--remote-allow-origins=*");
                        String downloadFilepath1 = System.getProperty("user.dir") + "\\downloads\\OutlookAttachments";
                        Map<String, Object> prefs = new HashMap<String, Object>();
                        prefs.put("download.default_directory",downloadFilepath1);
                        edgeOptions.setExperimentalOption("prefs", prefs);

                        driver = new EdgeDriver(edgeOptions); //(uncomment and comment below 2)
                        //WebDriver delegate = new EdgeDriver(edgeOptions);
                        //driver = SelfHealingDriver.create(delegate);


                        log.info("Created instance for Edge driver");

                    } else if (browsertype.toString().equalsIgnoreCase("Edge")) {

                        System.setProperty("webdriver.ie.driver",
                                ResourceHelper.getResourcePath("src/main/resources/drivers/IEDriverServer.exe"));

                        InternetExplorerOptions ieOptions = new InternetExplorerOptions();
                        ieOptions.attachToEdgeChrome();
                        ieOptions.ignoreZoomSettings();

                        ieOptions.withInitialBrowserUrl("http://www.bing.com");
                        ieOptions.withEdgeExecutablePath("C:/Program Files (x86)/Microsoft/Edge/Application/msedge.exe");
                        //ieOptions.withEdgeExecutablePath("src/main/resources/drivers/IEDriverServer.exe");
                        driver = new InternetExplorerDriver(ieOptions);

                        log.info("Created instance for Edge driver WITH IE Compatibility");

                    } else if (browsertype.toString().equalsIgnoreCase("Firefox")) {

                        // Setting webdriver.gecko.driver property
                        System.setProperty("webdriver.gecko.driver",
                                ResourceHelper.getResourcePath("src/main/resources/Drivers/geckodriver.exe"));
                        // Create a new instance of the Firefox driver
                        FirefoxOptions firefoxoptions = new FirefoxOptions();
                        firefoxoptions.setBinary("C:\\Program Files\\Mozilla Firefox\\firefox.exe");

                        if (headless.equalsIgnoreCase("yes")) {
                            firefoxoptions.addArguments("--headless");
                        }

                        driver = new FirefoxDriver(firefoxoptions);
                        log.info("Created instance for Firefox driver");

                    } else {
                        log.info("Missing driver value! Please setup driver through config file");
                    }
                    break;

            }

        }

        return driver;
    }


    public ExtentReports reports;
    public ExtentTest test;

    public void flushReports() {
        if (reports != null) {
            reports.flush();
        }
    }

    public void createExtendInstance() throws IOException {
        reports = ExtentReportManager.getInstance(System.getProperty("user.dir"));
    }


    public void initReports(String scenarioName) {
        //reports = ExtentReportManager.getInstance(System.getProperty("user.dir"));
        test = reports.createTest(scenarioName);
        //test.log(Status.INFO, "Starting Test : " + scenarioName);
    }

    public void infoLog(String msg) {
        test.log(Status.INFO, msg);
    }

    public void reportPass(String msg) {
        test.log(Status.PASS, msg);
    }

    public void reportSkip(String msg) {
        test.log(Status.SKIP, msg);
    }

    public void reportFailure(String msg) {
        test.log(Status.FAIL, msg);
        // takeScreenshot();
    }

    public void stepPass(String msg) {
        test.log(Status.PASS, msg);
    }


    public void stepFail(String msg) {
        test.log(Status.FAIL, msg);
        takeScreenshot();
    }



    public void stepSkip(String msg) {
        test.log(Status.SKIP, msg);
        takeScreenshot();
    }

    public void takeScreenshot() {

        Date date = new Date();
        String screenshotFile = date.toString().replaceAll(":","_").replaceAll(" ","_")+".png";
        File scrFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        try {

            FileUtils.copyFile(scrFile, new File(ExtentReportManager.screenshotFolderPath + screenshotFile));

           // test.log(Status.FAIL, "", MediaEntityBuilder.createScreenCaptureFromPath(ExtentReportManager.screenshotFolderPath + screenshotFile).build());
            test.log(Status.INFO, "", MediaEntityBuilder.createScreenCaptureFromPath("./screenshots/" + screenshotFile).build());

        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public void onDemandTakeScreenshot(String infoMsg) {
        Date date = new Date();
        String screenshotFile = date.toString().replaceAll(":","_").replaceAll(" ","_")+".png";
        File scrFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        try {
            FileUtils.copyFile(scrFile, new File(ExtentReportManager.screenshotFolderPath + screenshotFile));

            String platform = getConfigProperty("run.machine");

            if (platform.equalsIgnoreCase("jenkins")) {
                test.log(Status.INFO, "", MediaEntityBuilder.createScreenCaptureFromPath("./" + screenshotFile).build());
            }
            else{
                test.log(Status.INFO, "", MediaEntityBuilder.createScreenCaptureFromPath("./screenshots/" + screenshotFile).build());
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }




    public void initAndroidAppiumDriver() throws MalformedURLException {

        String env = "local";
        String plaftform = "mobileWeb"; // or native

        switch (env) {

            case "local":

                switch (plaftform) {

                    case "mobileWeb":

                        //Set the Desired Capabilities
                        DesiredCapabilities caps = new DesiredCapabilities();
                        //caps.setCapability("deviceName", "");
                        caps.setCapability("udid", getMobileDeviceProperty("udid")); //Give Device ID of your mobile phone
                        caps.setCapability("platformName", getMobileDeviceProperty("platformName"));
                        caps.setCapability("platformVersion", getMobileDeviceProperty("platformVersion"));
                        caps.setCapability("browserName", getMobileDeviceProperty("browserName"));
                        caps.setCapability("appium:automationName", "uiautomator2");


                        //Instantiate Appium Driver
                        System.out.println("<<<<<<<<<< initDriver >>>>>>>>>>> ");

                        try {
                            driver = new AndroidDriver(new URL("http://127.0.0.1:4723"), caps);
                        } catch (MalformedURLException e) {
                            System.out.println(e.getMessage());
                        }

                        //Open URL in Chrome Browser
                       // driver.get("https://www.saudiexchange.sa/");

                        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(60));
                        break;

                    case "native":

/*
                        UiAutomator2Options options = new UiAutomator2Options();
                        options.setPlatformVersion ("10");
                        options.setApp("D:\\OneDrive - Testhouse Limited\\Automation\\MobileAutomation\\Riju_Demo_Project\\eclipse_appium (2)\\eclipse_appium\\Mobile\\src\\test\\java\\Resources\\ApiDemos-debug.apk");
                        //options.setApp("D:\\OneDrive - Testhouse Limited\\Automation\\MobileAutomation\\Riju_Demo_Project\\eclipse_appium (2)\\eclipse_appium\\Mobile\\src\\test\\java\\Resources\\flutter-samples.apk");
                        //options.setApp("D:\\OneDrive - Testhouse Limited\\Automation\\MobileAutomation\\Riju_Demo_Project\\eclipse_appium (2)\\eclipse_appium\\Mobile\\src\\test\\java\\Resources\\Quantum.apk");
                        //options.setApp("D:\\OneDrive - Testhouse Limited\\Automation\\MobileAutomation\\Riju_Demo_Project\\eclipse_appium (2)\\eclipse_appium\\Mobile\\src\\test\\java\\Resources\\temenos-quantum.apk");
                        options.setDeviceName("Android SDK built for x86");
                        //options.setDeviceName("sdk_gphone64_x86_64");


                        driver = new AndroidDriver(new URL("http://127.0.0.1:4723"),options);
*/
                        break;

                }
                break;

            case "sauceLab":

                //Run in Sauce Lab
                MutableCapabilities caps = new MutableCapabilities();
                caps.setCapability("platformName", "Android");
                caps.setCapability("appium:app", "storage:filename=Quantum.apk");  // The filename of the mobile app
                caps.setCapability("appium:deviceName", "Android GoogleAPI Emulator");
                caps.setCapability("appium:platformVersion", "12.0");
                caps.setCapability("appium:automationName", "UiAutomator2");
                MutableCapabilities sauceOptions = new MutableCapabilities();
                sauceOptions.setCapability("username", "oauth-rijusamueljohn-62637");
                sauceOptions.setCapability("accessKey", "0381e226-827b-46ac-8655-d78abf7df24b");
                sauceOptions.setCapability("build", "appium-build-F5SWB");
                sauceOptions.setCapability("name", "<your test name>");
                sauceOptions.setCapability("deviceOrientation", "PORTRAIT");
                caps.setCapability("sauce:options", sauceOptions);
                ///Sauce Lab Settings
                URL url = new URL("https://ondemand.eu-central-1.saucelabs.com:443/wd/hub");
                driver = new AndroidDriver(url, caps);

                break;
        }

    }
    public void waitForPageToLoad(WebDriver driver) {

        try {
            ExpectedCondition<Boolean> javascriptDone = new ExpectedCondition<Boolean>() {
                public Boolean apply(WebDriver d) {
                    try {
                        return ((JavascriptExecutor) driver).executeScript("return document.readyState").equals("complete");
                    } catch (Exception e) {
                        return Boolean.FALSE;
                    }
                }
            };
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(90));
            wait.until(javascriptDone);

        }catch (TimeoutException exception){
            exception.printStackTrace();
        }

    }




















}
