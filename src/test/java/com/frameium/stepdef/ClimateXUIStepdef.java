package com.frameium.stepdef;

import java.awt.AWTException;
import java.io.IOException;
import java.net.MalformedURLException;
import java.util.List;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.json.JSONArray;

import com.frameium.configuration.ObjectReader;
import com.frameium.genericfunctions.GenericFunctions;
import com.frameium.pageobject.ClimateXExcelDataPage;
import com.frameium.pageobject.ClimateXHomePage;
import com.frameium.pageobject.ClimateXLoginPage;
import com.frameium.pageobject.ClimateXPDFExtraction;
import com.frameium.utilities.ExcelReader;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.Scenario;


public class ClimateXUIStepdef {
	
	ExcelReader reader = new ExcelReader();
	ClimateXExcelDataPage climateX = new ClimateXExcelDataPage();
	ClimateXPDFExtraction climatexpdf = new ClimateXPDFExtraction();
	ClimateXHomePage homePage = new ClimateXHomePage();
	ClimateXLoginPage climatexlogin = new ClimateXLoginPage();
	String folderpath = System.getProperty("user.dir") + "/src/main/resources/heatmap-screenshots/";
	int totalRows;

	TestSetUp setUp;
	private GenericFunctions genericFunctions;
	public ClimateXUIStepdef(TestSetUp setUp) throws MalformedURLException {
		System.out.println("<<<<<<< SaudiExchangeTest01StepDef >>>>>>>>>>>");
		this.setUp = setUp;
		genericFunctions = new GenericFunctions(setUp.baseTest.driver);
	}





 @Then("User opens ClimateX staging Url {string}")
	public void user_opens_climate_x_staging_url(String string) {
	 genericFunctions.getApplicationUrl((ObjectReader.reader.getClimateXUrl()));
	}
	
 	@Then("User opens Url {string}")
	public void user_opens_url(String string) throws Exception {
		//getApplicationUrl((ObjectReader.reader.getClimateXUrl()));
		genericFunctions.getApplicationUrl(string);
		genericFunctions.waitForPageToLoad(6000);
	}
 

	@Then("Enter Username and Password")
	public void enter_username_and_password() throws InterruptedException  {
		
		String Username = ObjectReader.reader.getUserName();
		String Password = ObjectReader.reader.getPassword();
		climateX.loginToApplication(Username,Password);
  
	}
	
	@Then("Enter a {string} and {string}")
	public void enter_a_username_and_password(String username,String password) throws InterruptedException  {
		climateX.loginToTheApplication(username,password);
  
	}
	
	@Then("Invalid user message appears")
	public void verify_invalid_user_message() throws InterruptedException  {
		climateX.verifySignInError();
  
	}
	
	@Then("Verify ClimateX home - Evaluate an asset page")
	public void verify_climate_x_home_evaluate_an_asset_page() throws InterruptedException {
		
		climateX.verificationOfUploadPage();
	}

	@Then("Click on Upload CSV button")
	public void click_on_upload_csv_button() throws InterruptedException {
		
		climateX.selectUploadCSVbutton();
	}

	@Then("Import data from CSV file")
	public void import_data_from_csv_file() throws InterruptedException {
		
		climateX.selectUploadDataFromFileButton();
	}

	@Then("Select CSV file from File explorer")
	public void select_csv_file_from_file_explorer() throws AWTException, InterruptedException {
		
		climateX.uploadFile();
	}

	@Then("Verify the file uploading status")
	public void verify_the_file_uploading_status() throws InterruptedException {
		
		climateX.verificationOfImportParameters();
	}
	
	@Then("Select worksheet and click on Continue button")
	public void select_worksheet_and_click_on_continue_button() throws InterruptedException {
		
		climateX.selectContinueButton();
	}		
	

	@Then("Click on Review button")
	public void click_on_review_button() {
		
		climateX.selectReviewButton();
	}
	
	//Healinium
	@Then("Select Checkbox1")
	public void select_checkbox() {
		climateX.selectCheckBox_Heal();
	}

	@Then("Enter Text In First Field")
	public void fill_in_text_Heal() {
		climateX.fillText_Heal();
	}
	
	@Then("Click Contact In Header")
	public void clickContactHeal() {
		climateX.clickContactHeal();
	}
	
	@Then("Click Home In Header")
	public void clickHomeHeal() {
		climateX.clickHomeHeal();
	}
	
	@Then("Enter Customer Name")
	public void fillNameHeal() {
		climateX.fillNameHeal();
	}
	
	@Then("Enter Customer Email")
	public void fillEmailHeal() {
		climateX.fillEmailHeal();
	}

	@Then("Enter Customer Phone")
	public void fillPhoneNumberHeal() {
		climateX.fillPhoneNumberHeal();
	}
	
	@Then("Enter Message")
	public void fillMessageHeal() {
		climateX.fillMessageHeal();
	}
	
	@Then("Click Submit Option")
	public void clickSubmitHeal() {
		climateX.clickSubmitHeal();
	}
	
	@Then("Click Order Online")
	public void clickOrderOnlineHeal() {
		climateX.clickOrderOnlineHeal();
	}
	
	@Then("Check Contact Page Title")
	public void checkContactPageTitle() {
		climateX.checkContactPageTitle();
	}
	
	
	///D365
	@Then("Click Leads CRM")
	public void clickLeadeCRM() {
		climateX.clickLeadsCRM();
	}
	
	@Then("Click New CRM")
	public void clickNewCRM() {
		climateX.clickNewCRM();
	}
	
	
	@Then("Fill Summary CRM")
	public void fillSummaryCRM() {
		climateX.fillSummaryCRM();
	}
	
	@Then("Verify Excel file and uploaded data")
	public void verify_excel_file_and_the_uploaded_data() throws InvalidFormatException, IOException, InterruptedException {
		
		climateX.ReadTable();
		//climateX.GetRowcountExcel();
		totalRows = climateX.verifyUploadedDataAndExcelData();
		climateX.CompareData();
	}
	
	@Then("Click on Continue")
	public void click_on_continue() throws InterruptedException {
		
		climateX.continueForSubmit();
	}


	@Then("Verify uploading is Done and Click on Ok button")
	public void verify_uploading_is_done_and_click_on_ok_button() {
		
		climateX.VerifyUploadingAndSelectDone();
	}

	@Then("Verify popup Take me to the results and Click on Yes button")
	public void verify_popup_take_me_to_the_results_and_click_on_yes_button() throws InterruptedException {
		 
		climateX.ViewResultsAndVerify();
	}
	
	@Then("User verify project page {string}")
	public void user_verify_project_page(String username) throws InterruptedException {
		totalRows = totalRows - 1;
	    climatexpdf.verifyProjectpage(username);
	    climatexpdf.verifyingRiskCount(totalRows);
	}

	@Then("User fetch risk scores based on year {string}, {string}")
	public void User_fetch_risk_scores_based_on_year(String year1, String year2) throws InterruptedException {
	    climatexpdf.fetchingProjectdetailsbasedonYear(year1);
	    climatexpdf.fetchingProjectdetailsbasedonYear(year2);
	}

	@Then("User generates pdf report")
	public void user_generates_pdf_report() throws InterruptedException, IOException {
		climatexpdf.clicksonPDFreportButton();
	
	}

	@Then("User extract text from pdf file")
	public void user_extract_text_from_pdf_file() throws InterruptedException, IOException {
		climatexpdf.readPDFTextfrombrowser();
	}

	@Then("User compare risk scores of ui with pdf based on the years")
	public void User_compare_risk_scores_of_ui_with_pdf_based_on_the_years() throws InterruptedException {
		climatexpdf.comparisonofdatainUIwithPDF();
	}
	
	@And("Select Risk Filter and HeatMap should be displayed")
	public void Select_Risk_Filter_and_HeatMap_should_be_displayed() {
		homePage.zoomoutMap();
		homePage.viewHeatmap();
	}
	

	@Then("Capture heat map image and compare map with previous date captured image")
	public void Capture_heat_map_image_and_compare_map_with_previous_date_captured_image() throws IOException, InterruptedException{
		homePage.captureScreenshot(folderpath);
	}

	@Then("User log out from ClimateX application")
	public void user_log_out_from_climate_x_application() throws InterruptedException {
		climatexlogin.Logout();
	}
	

 	@Then("Run User Accessibility Check")
	public void call_acc_rept() throws Exception {
 		int indexCount = 0;
 		int failedCount = 0;
 		int passedCount = 0;
 		int skippedCount = 0;
 		int totalViolations=0;
 		int sevCritical = 0;
 		int sevSerious = 0;
 		int sevModerate = 0;
 		int sevMinor = 0;
 		JSONArray violationArray =  genericFunctions.performAccessibility();
		totalViolations = totalViolations + violationArray.length();
		System.out.println("...............total number of violations........"+totalViolations);
		System.out.println("...............total pages accessed.............."+indexCount);
		List<Integer> Severitylist1 = genericFunctions.violationSeverityList(violationArray);
		sevCritical = sevCritical + Severitylist1.get(0);
		sevSerious = sevSerious + Severitylist1.get(1);
		sevModerate = sevModerate + Severitylist1.get(2);
		sevMinor = sevMinor + Severitylist1.get(3);
		
		
		System.out.println(sevCritical);
		System.out.println(sevSerious);
		System.out.println(sevModerate);
		System.out.println(sevMinor);

		String url = setUp.baseTest.driver.getCurrentUrl();//"https://www.canada.ca/en/employment-social-development/services/sin.html";

		genericFunctions.createAccessibilityReport(violationArray,indexCount,passedCount,failedCount,skippedCount,sevCritical,sevSerious,sevModerate,sevMinor,totalViolations,url,setUp.baseTest.getDriver());

	}
 	
	
	
	
	
	//////
	
}
