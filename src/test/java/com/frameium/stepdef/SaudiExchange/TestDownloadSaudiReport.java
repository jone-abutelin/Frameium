package com.frameium.stepdef.SaudiExchange;

import static org.junit.Assert.assertTrue;

import java.io.File;
import java.io.IOException;

import com.frameium.stepdef.Hooks;
import com.frameium.stepdef.TestSetUp;
import org.testng.Assert;

import com.frameium.genericfunctions.GenericFunctions;
import com.frameium.pageobject.SaudiExchange.PdfSaudiEx;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class TestDownloadSaudiReport {

	private String URL;
	private PdfSaudiEx pdfAnnualReport;
	private static String downloadPath = "C:/Users/RijuJohn/Downloads/";
	Hooks hooks = new Hooks();
	TestSetUp setUp;
	GenericFunctions genericFunctions;

	public TestDownloadSaudiReport(TestSetUp setUp) throws IOException {
		this.setUp = setUp;
		genericFunctions = new GenericFunctions(setUp.baseTest.getDriver());
	}


	@Given("I am on the Saudi Stock Exchange Annual Reports page")
	public void i_am_on_the_saudi_stock_exchange_annual_reports_page() {
		String expectedTitle = "Periodical publications";
		String actualTitle = setUp.baseTest.driver.getTitle();
		Assert.assertEquals(actualTitle, expectedTitle, "Mismatch");
		hooks.takeScreenshot(hooks.scenario);


	}

	@Then("I should see a list of annual reports")
	public void i_should_see_a_list_of_annual_reports() {
		pdfAnnualReport = new PdfSaudiEx(setUp.baseTest.driver);
		assertTrue("No annual reports found", pdfAnnualReport.isAnnualReportsListNotEmpty());
	}

	@When("I select a specific annual report for {string}, {string}, {string}")
	public void i_select_a_specific_annual_report_for(String year, String month, String reportType) {
		pdfAnnualReport.selectAnnualReport(year, month, reportType);
		hooks.takeScreenshot(hooks.scenario);
	}

	@Then("the annual report should be downloaded successfully")
	public void the_annual_report_should_be_downloaded_successfully() throws InterruptedException {
		File latestFile = pdfAnnualReport.downloadAnnualReport(downloadPath);
		Assert.assertTrue(latestFile.exists(), "Latest file not available");
		String fileName = latestFile.getName();
		Assert.assertEquals(fileName, "2024_02__REPORT");

	}

	@Then("the annual report of prod should be downloaded successfully to {string}")
	public void the_annual_report_of_prod_should_be_downloaded_successfully_to(String downloads)
			throws InterruptedException {
		File pdfFile = pdfAnnualReport.downloadReportProd(downloads);
		Assert.assertTrue(pdfFile.exists(), "Pdf file not available");

	}

	@Then("the annual report of UAT should be downloaded successfully to {string}")
	public void the_annual_report_of_uat_should_be_downloaded_successfully_to(String download) throws InterruptedException {
		File reportFile = pdfAnnualReport.downloadReportUAT(download);
		Assert.assertTrue(reportFile.exists(), "Pdf file not available");
	}
	
	@And("I copy the downloaded report from {string} to new folder {string} with custom name {string}")
	public void i_copy_the_downloaded_report_to_new_folder(String downloaded, String report, String customPdfName) {
	 //  String customPdfName = "SaudiEx.pdf";
	   File copiedFile = pdfAnnualReport.copyFile(downloaded, report , customPdfName);
	   Assert.assertTrue(copiedFile.exists(), " Copied Pdf file not available");
	}
}
