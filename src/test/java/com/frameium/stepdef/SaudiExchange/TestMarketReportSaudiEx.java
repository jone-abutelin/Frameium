package com.frameium.stepdef.SaudiExchange;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.testng.Assert;

import com.frameium.genericfunctions.GenericFunctions;
import com.frameium.pageobject.SaudiExchange.MarketReportSaudiEx;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;

public class TestMarketReportSaudiEx extends GenericFunctions {

	private static String zipFilePath = null;
	private MarketReportSaudiEx marketReport;
	private List<String[]> valuesProd;
	private List<String[]> valuesUat;
	
	 public TestMarketReportSaudiEx() {
	        valuesProd = new ArrayList<>();
	        valuesUat = new ArrayList<>();
	    }
	@Given("I am on the Saudi Stock Exchange Market Reports page")
	public void i_am_on_the_saudi_stock_exchange_market_reports_page() {
		marketReport = new MarketReportSaudiEx(driver);

	}

	@And("I should download the detailed daily report successfully")
	public void i_should_download_the_detailed_daily_report_successfully() throws InterruptedException {
		File latestFile = marketReport.downloadDetailedDailyReport();
		Assert.assertTrue(latestFile.exists(), "Latest file not available");
		zipFilePath = latestFile.getAbsolutePath();

	}

	@Then("I should unzip the zipped folder to {string} with custom name {string}")
	public void i_unzip_the_zipped_folder_to(String unzippedFilePath, String customName) {
		boolean unzipDownloadedReport = marketReport.unzipFile(zipFilePath, unzippedFilePath, customName);
		Assert.assertTrue(unzipDownloadedReport, "Report is not unzipped and extracted");
	}

	@Then("the downloaded and unzipped HTML contents of prod {string} should match UAT {string}")
	public void the_downloaded_and_unzipped_html_contents_of_prod_should_match_uat(String prodHtml, String uatHtml) {
		marketReport = new MarketReportSaudiEx(driver);
		boolean areHtmlEqual = marketReport.compareHTML(prodHtml, uatHtml);
		Assert.assertTrue(areHtmlEqual, "HTML contents do not match.");
	}

	@Then("I should get the values of market summary from production HTML")
	public void i_should_get_the_values_of_market_summary_from_production_html() {
		marketReport = new MarketReportSaudiEx(driver);
		valuesProd = marketReport.getMarketSummaryValues();
		Assert.assertEquals(valuesProd.get(0)[0], "Open", "Key mismatch for Open");
		Assert.assertEquals(valuesProd.get(0)[1], "12,682.96", "Value mismatch for Open");
	}

	@Then("I should get the values of market summary from UAT HTML")
	public void i_should_get_the_values_of_market_summary_from_uat_html() {
		marketReport = new MarketReportSaudiEx(driver);
		valuesUat = marketReport.getMarketSummaryValues();
		Assert.assertEquals(valuesUat.get(0)[0], "Open", "Key mismatch for Open");
		Assert.assertEquals(valuesUat.get(0)[1], "12,682.96", "Value mismatch for Open");
	}

	@Then("I should compare the values of market summary extracted from prod and UAT HTML")
	public void i_should_compare_the_values_of_market_summary_extracted_from_prod_and_uat_html() {
		// Compare values from production and UAT
		Assert.assertEquals(valuesProd.size(), valuesUat.size(), "Number of entries mismatch");

		for (int i = 0; i < valuesProd.size(); i++) {
			String[] prodEntry = valuesProd.get(i);
			String[] uatEntry = valuesUat.get(i);

			Assert.assertEquals(prodEntry[0], uatEntry[0], "Key mismatch");
			Assert.assertEquals(prodEntry[1], uatEntry[1], "Value mismatch");
		}
	}

}
