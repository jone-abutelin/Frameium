package com.frameium.stepdef.SaudiExchange;

import java.io.IOException;
import java.net.MalformedURLException;
import java.util.List;

import com.aventstack.extentreports.cucumber.adapter.ExtentCucumberAdapter;
import com.frameium.pageobject.SaudiExchange.*;
import com.frameium.configuration.ObjectReader;
import com.frameium.genericfunctions.GenericFunctions;

import com.frameium.pageobject.mobile.android.SaudiExchangePage;
import com.frameium.pageobject.mobile.android.SaudiLandingPage;
import com.frameium.stepdef.Hooks;
import com.frameium.stepdef.TestSetUp;
import io.appium.java_client.android.AndroidDriver;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.datatable.DataTable;
import org.testng.Assert;

public class SaudiExchange  {

	private SaudiExchangeVerification saudiexchange;

	Hooks hooks = new Hooks();

	TestSetUp setUp;
	private GenericFunctions genericFunctions;

	public SaudiExchange(TestSetUp setUp) throws MalformedURLException {
		this.setUp = setUp;
		genericFunctions = new GenericFunctions(setUp.baseTest.driver);
		saudiexchange=new SaudiExchangeVerification(setUp.baseTest.driver);
	}
	@Then("Close browser")
	public void close_browser()
	{
		close_browser();
	}

	@Then("navigate to the application URL {string}")
	public void navigate_to_the_application_url(String string)
	{
		//getApplicationUrl(ObjectReader.reader.SaudiExchange());
		int i = 1;
	}

	@Then("User opens the Url {string}")
	public void user_opens_url(String string) throws Exception {
		genericFunctions.getApplicationUrl(string);
		genericFunctions.waitForPageToLoad(6000);
		hooks.takeScreenshot(hooks.scenario);
		setUp.baseTest.onDemandTakeScreenshot("<<<<< >>>>>");
	}

	@Then("Click on the Our Markets dropdown menu")
	public void click_on_the_our_markets_dropdown_menu() throws InterruptedException
	{
		saudiexchange.clickmenu();
	}

	@Then("Verify Following Items appears under Our Market Section > Main Market Section:")
	public void verify_items_under_our_market_section_main_market_section(List<String> textsToVerify) throws InterruptedException, IOException {
		hooks.takeScreenshot(hooks.scenario);
		saudiexchange.verifyTexts(textsToVerify);
		hooks.takeScreenshot(hooks.scenario);

	}

	@Then("click on the our List dropdown menu")
	public void click_on_the_our_list_dropdown_menu() throws InterruptedException
	{
		saudiexchange.ClickListMenu();
		hooks.takeScreenshot(hooks.scenario);

	}

	@Then("Verify Following Items appears under Listing > Become An Issuer Section:")
	public void verify_items_under_listing_become_an_issuer_section(List<String> textsToVerify) throws InterruptedException
	{
		saudiexchange.verifyTexts(textsToVerify);
		hooks.takeScreenshot(hooks.scenario);

	}


	@Then("Verify date appears in white")
	public void verify_date_appears_in_white()
	{
		saudiexchange.ColorDateAndTime();
		hooks.takeScreenshot(hooks.scenario);

	}
	@Then("Verify Market Status appears in white")
	public void verify_market_status_appears_in_white()
	{
		saudiexchange.ColorOfMarketStatus();
	}
	@And("Verify Search symbol appears in white")
	public void verify_search_symbol_appears_in_white() {


		saudiexchange.ColorSearchSymbol();
		hooks.takeScreenshot(hooks.scenario);

	}
	@Then("Verify that the color appears green when the market is up and red when the market is down for TASI")
	public void Verify_that_the_color_appears_green_when_the_market_is_up_and_red_when_the_market_is_down_for_TASI()
	{
		//saudiexchange.checkPriceStatus();
		saudiexchange.checkPriceColour("TASI");

	}
	@And("Verify that the color appears green when the market is up and red when the market is down for MT30")
	public void verify_that_the_color_appears_green_when_the_market_is_up_and_red_when_the_market_is_down_for_mt30() {
		//saudiexchange.checkPriceStatusMT30();
		saudiexchange.checkPriceColour("MT30");
	}
	@And("Verify that the color appears green when the market is up and red when the market is down for Nomuc")
	public void verify_that_the_color_appears_green_when_the_market_is_up_and_red_when_the_market_is_down_for_nomuc() {
		//saudiexchange.checkPriceStatusNomuC();
		saudiexchange.checkPriceColour("NomuC");
	}
	@Then("Click on Parallel Market Watch menu from Our Market dropdown menu")
	public void Click_on_Parallel_Market_Watch_menu_from_Our_Market_dropdown_menu() throws InterruptedException {

		saudiexchange.ParallelMarket();
	}

	@Then("Select Capital Goods from Industry Group dropdown list")
	public void Select_Capital_Goods_from_Industry_Group_dropdown_list() throws InterruptedException {
		saudiexchange.SelectCaptialwoods();
	}
	@And("Select Price data from Table View dropdown list")
	public void select_price_data_from_table_view_dropdown_list() throws InterruptedException {
		saudiexchange.Tableview();
	}


	@Then("Verify the heading of the each column in table:")
	public void verify_the_heading_of_the_each_column_in_table(List<String> textsToVerify) throws InterruptedException {
		saudiexchange.verifyTableHeaders(textsToVerify);
	}
	@Then("verify the following data under captial goods:")
	public void verify_the_following_data_under_captial_goods(DataTable table)
	{
		saudiexchange.verifyData(table);
	}



	@Then("Click on the Arabic Convert button")
	public void click_on_the_arabic_convert_button() throws InterruptedException {
		saudiexchange.convertButton();
	}
	@Then("Click on the Arabic Our Markets dropdown menu")
	public void click_on_the_arabic_our_markets_dropdown_menu() throws InterruptedException {
		saudiexchange.OurMarketmenu();

	}
	@Then("Verify Items under Our Market Section > Main Market Section in Arabic:")
	public void verify_items_under_our_market_section_main_market_section_in_Arabic(List<String> textsToVerify) throws InterruptedException {

		saudiexchange.verifyTexts(textsToVerify);
	}

	@Then("user Fetch Production Table data and store it in list")
	public void userFetchProductionTableDataAndStoreItInList() {
		saudiexchange.getProdmarketWatchTable2Data();
	}


	@Then("user Fetch UAT Table data and store it in list")
	public void userFetchUATTableDataAndStoreItInList() {
		saudiexchange.getUATmarketWatchTable2Data();
	}

	@Then("user compare prod table data with uat table")
	public void userCompareProdTableDataWithUatTable() {
		Assert.assertTrue(saudiexchange.compareTables(SaudiExchangeVerification.productionTableData,SaudiExchangeVerification.uatTableData),"prod table data and uat table data both are different");
	}




}
