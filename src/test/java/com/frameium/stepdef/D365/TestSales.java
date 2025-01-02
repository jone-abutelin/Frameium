//package com.frameium.stepdef.D365;
//
//import java.time.Duration;
//
//import org.openqa.selenium.WebDriver;
//import org.openqa.selenium.chrome.ChromeDriver;
//import org.openqa.selenium.chrome.ChromeOptions;
//import org.openqa.selenium.support.ui.ExpectedConditions;
//import org.openqa.selenium.support.ui.WebDriverWait;
//import org.testng.Assert;
//import org.testng.annotations.AfterTest;
//import org.testng.annotations.BeforeTest;
//import org.testng.annotations.Test;
//
//import com.frameium.genericfunctions.GenericFunctions;
//import com.frameium.pageobject.D365.Account;
//import com.frameium.pageobject.D365.Competitor;
//import com.frameium.pageobject.D365.Lead;
//import com.frameium.pageobject.D365.Sales;
//import com.frameium.pageobject.D365.Task;
////import com.frameium.pageobject.Swag.LoginPage;
//
//import io.cucumber.java.en.Given;
//import io.cucumber.java.en.Then;
//import io.github.bonigarcia.wdm.WebDriverManager;
//
//public class TestSales extends GenericFunctions {
//	//private WebDriver driver;
//	private Sales loginPage;
//	private Account account;
//	private static final String BASE_URL = "https://thdynamics365sandbox.crm4.dynamics.com/";
//
//	//@BeforeTest
//	public void setUp() {
//		// Set up WebDriver
//		WebDriverManager.chromedriver().setup();
//		ChromeOptions options = new ChromeOptions();
//		options.addArguments("--remote-allow-origins=*");
//		driver = new ChromeDriver(options);
//		driver.manage().window().maximize();
//		driver.get(BASE_URL);
//	}
//
//	//@AfterTest
//	public void tearDown() {
//		// Close the browser
//		driver.quit();
//	}
//
//	@Test(priority = 1)
//	@Given("I am on the landing page of D365")
//	public void testLoginSalesD365() throws InterruptedException {
//
//		String expectedUrl = "https://thdynamics365sandbox.crm4.dynamics.com/main.aspx?appid=e6d40712-f933-e811-a95f-000d3a2acf58&forceUCI=1&pagetype=entitylist&etn=activitypointer&viewid=00000000-0000-0000-00aa-000010001899&viewType=1039";
//		loginPage = new Sales(driver);
//		// Perform valid login
//		boolean loginResult = loginPage.validLogin("AutoTestUser6@testhouse.net", "Loj03015");
//		Assert.assertTrue(loginResult, "Login failed!");
//		//WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10)); // Wait up to 10 seconds
//		//wait.until(ExpectedConditions.urlContains(expectedUrl));
//		//String currentUrl = driver.getCurrentUrl();
//		// Print the current URL for debugging
//		//System.out.println("Current URL: " + currentUrl);
//		// Assert if the expected link is present in the URL
//		//Assert.assertTrue(currentUrl.contains(expectedUrl), "Link not opened after successful login");
//	}
//
//	@Test(priority = 2)
//	@Then("I should be able to create an account")
//	public void createAccount() throws InterruptedException {
//		account = new Account(driver);
//		@SuppressWarnings("unused")
//		boolean accountCreated = account.newAccountCreate("Test Account", "Banking", "123 Main St");
//		String expectedTitle = driver.getTitle();
//	    //String expectedTitle = account.getTheBrowserTitle();
//		// Specify the expected title
//		String nextPageTitle = "Account: Account: New Account - Dynamics 365";
//		// Assert if the next opened page title matches the expected title
//		Assert.assertEquals(nextPageTitle, expectedTitle, "Next opened page title does not match expected title");
//	}
//
//	@Test(priority = 3)
//	@Then("I should be able to delete an account")
//	public void deleteExistingAccount() throws InterruptedException {
//		//account = new Account(driver);
//		@SuppressWarnings("unused")
//		boolean accountDeleted = account.deleteAccount("Johan"); // Replace "Johan" with the actual account name to
//																	// delete
//		// Check if the page with the text "No data available" is displayed
//		boolean noDataDisplayed = account.isNoDataAvailableDisplayed();
//		Assert.assertTrue(noDataDisplayed,
//				"No data available message not displayed. Account deletion verification failed.");
//	}
//
//	@Test(priority = 4)
//	@Then("I should be able to create a competitor")
//	public void createCompetitorMandatory() throws InterruptedException {
//		Competitor competitorPage = new Competitor(driver);
//		@SuppressWarnings("unused")
//		boolean competitorCreated = competitorPage.createNewCompetitor("Thomas");
//		String nextPageTitle = driver.getTitle();
//		// Specify the expected title
//		String expectedTitle = "Competitor: Competitor: New Competitor - Dynamics 365";
//		// Assert if the next opened page title matches the expected title
//		Assert.assertEquals(nextPageTitle, expectedTitle, "Next opened page title does not match expected title");
//	}
//
//	@Test(priority = 5)
//	@Then("I should be able to create a lead")
//	public void createLead() throws InterruptedException {
//		Lead leadCreation = new Lead(driver);
//
//		// Call createNewLead method with required parameters
//		leadCreation.createNewLead("LeadName", "Mr.", "John", "Doe", "Manager", "1234567890", "example@example.com",
//				"India");
//		boolean saveAndCloseSuccessful = leadCreation.saveAndCloseLead();
//		Assert.assertTrue(saveAndCloseSuccessful, "Save & Close failed!");
//	}
//
//	@Test(priority = 6)
//	@Then("I should be able to create a task")
//	public void createTask() {
//		Task taskCreation = new Task(driver);
//
//		taskCreation.createNewTask("NewTask");
//		boolean saveAndCloseSuccessful = taskCreation.saveAndCloseTask();
//		Assert.assertTrue(saveAndCloseSuccessful, "Save & Close failed!");
//
//	}
//
//	@Test(priority = 7)
//	@Then("I should be able to verify the error message")
//	public void verifyErrorMessage() {
//		Task errorVerification = new Task(driver);
//		boolean errorDisplayed = errorVerification.isErrorMessageDisplayed();
//		Assert.assertTrue(errorDisplayed, "Error message not displayed!");
//	}
//
//	@Test(priority = 8)
//	@Then("I should be able to delete all leads")
//	private void deleteAllLead() {
//		Lead allLeadDeletion = new Lead(driver);
//		boolean leadDeleted = allLeadDeletion.deletingAllLead();
//        Assert.assertTrue(leadDeleted, "All leads should be deleted");
//	}
//
//	@Test(priority = 9)
//	@Then("I should be able to logout")
//	public void testLogoutSalesD365() throws InterruptedException {
//		Sales salesPage = new Sales(driver);
//		// Perform logout
//		boolean logoutResult = salesPage.logout();
//		Assert.assertTrue(logoutResult, "Logout failed!");
//	}
//
//}