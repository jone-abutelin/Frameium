package com.frameium.stepdef.mobile.android;

import com.frameium.pageobject.mobile.android.SaudiExchangePage;
import com.frameium.pageobject.mobile.android.SaudiLandingPage;
import com.frameium.stepdef.TestSetUp;
import io.appium.java_client.android.AndroidDriver;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.cucumber.java.en.Given;
import org.testng.Assert;

import java.io.IOException;
import java.net.MalformedURLException;
import java.util.HashMap;
import java.util.Map;

public class SaudiExchangeTest01StepDef  {

    public SaudiExchangePage saudiExchange;

    public SaudiLandingPage saudiLandingPage;
    TestSetUp setUp;

    private String selectedLatestNewsTab = "";

    public Map<Integer,String> mapProdUIText;
    public  Map<Integer,String> mapUatUiText;


    public SaudiExchangeTest01StepDef(TestSetUp setUp) throws MalformedURLException {
       System.out.println("<<<<<<< SaudiExchangeTest01StepDef >>>>>>>>>>>");
        this.setUp = setUp;
        AndroidDriver androidDriver = (AndroidDriver) setUp.baseTest.driver;
        saudiExchange = new SaudiExchangePage(androidDriver);
        saudiLandingPage = new SaudiLandingPage(androidDriver);
    }


    @When("using click on Personalize button")
    public void usingClickOnPersonalizeButton() {
        saudiExchange.choosePersonalizeMenuItems("Transportation");
    }

    @Then("verify list of personalize options displayed")
    public void verifyListOfPersonalizeOptionsDisplayed() {
        saudiExchange.verifySelectedPersonalizeMenuItems("Transportation");
    }

    @Given("user navigates to {string}")
    public void userNavigatesTo(String url) {
        setUp.baseTest.getDriver().get(url);
        setUp.baseTest.waitForPageToLoad(setUp.baseTest.getDriver());
    }

    @When("user click on Download button from Main Market and Document name is {string} and Environment is {string}")
    public void userClickOnDownloadButtonFromMainMarketAndDocumentNameIs(String docName,String env) throws IOException, InterruptedException {

        saudiExchange.cleanUpProjectFolder(env);
        saudiExchange.deleteAllFilesFromMobileDownloadFolder();
        saudiExchange.clickOnDownloadBtnFromMarketDocument(docName,env);
        setUp.baseTest.onDemandTakeScreenshot("Click on Download button from Main Market and Document name is "+ docName);
        saudiExchange.handleFileDownloadPopup();
        saudiExchange.pullFilesFromMobileDownloadFolder(env);

    }

    @When("use click on {string} from Latest News")
    public void useClickOnFromLatestNews(String tabName) {
        saudiExchange.clickNewsTab(tabName);
        selectedLatestNewsTab = tabName;
    }


   @Then("user fetch Production News UI text content and store it into Prod HashMap")
    public void userFetchProductionLatestNewsUITextContentAndStoreItIntoProdHashMap() {
       setUp.baseTest.onDemandTakeScreenshot("");
       mapProdUIText = new HashMap<>();
       mapProdUIText =   saudiExchange.fetchUITextLatestNews(selectedLatestNewsTab);
       System.out.println("mapProdUIText "+ mapProdUIText.entrySet());

       setUp.baseTest.infoLog("Production News UI text content >>>> : "+ mapProdUIText.entrySet());

    }


    @When("user navigate to {string}")
    public void userNavigateTo(String url) {
        setUp.baseTest.getDriver().get(url);
        setUp.baseTest.waitForPageToLoad(setUp.baseTest.getDriver());
    }

    @Then("user fetch UAT News UI text content and store it into Prod HashMap")
    public void userFetchUATLatestNewsUITextContentAndStoreItIntoProdHashMap() {
        setUp.baseTest.onDemandTakeScreenshot("");
        mapUatUiText= new HashMap<>();
        mapUatUiText =   saudiExchange.fetchUITextLatestNews(selectedLatestNewsTab);
        System.out.println("mapUatUiText "+ mapUatUiText.entrySet());
        setUp.baseTest.infoLog("UAT News UI text content >>>> : "+ mapUatUiText.entrySet());
    }


    @Then("user compares Production and UATMarket News UI text content")
    public void userComparesProductionAndUATLatestNewsUITextContent() {
        saudiExchange.compareTwoHashMaps(mapProdUIText,mapUatUiText);
    }

    @When("use click on Personalize button")
    public void useClickOnPersonalizeButton() {
        saudiLandingPage.getbtnPersonalize().click();
    }

    @And("User choose personalize options as {string}")
    public void userChoosePersonalizeOptionsAs(String checkBoxName) {
        saudiLandingPage.choosePersonalizeMenuItems(checkBoxName);
        setUp.baseTest.onDemandTakeScreenshot("");
    }

    @Then("verify selected {string} item is displayed on HomePage")
    public void verifySelectedItemIsDisplayedOnHomePage(String selectedItem) {
        saudiLandingPage.verifySelectedPersonalizeMenuItems(selectedItem);
        setUp.baseTest.onDemandTakeScreenshot("");
    }


    @And("user click on Apply Selected button")
    public void userClickOnApplySelectedButton() {
        saudiLandingPage.getbtnApplySelected().click();
    }


    @And("Extract the {string} zip file and open it")
    public void extractTheZipFileAndOpenIt(String env) {
        saudiExchange.extractZipFile(env);
    }

    @Then("compare  {string} and {string} and HTML file extracted from Zip")
    public void compareAndAndHTMLFileExtractedFromZip(String prod, String uat) {
       boolean areEqual = saudiExchange.compareHTMLFiles(prod,uat);

        if (areEqual) {
            setUp.baseTest.infoLog("UAT and Prod HTML contents match.");
        } else {
            System.out.println("HTML contents do not match.");
            setUp.baseTest.stepFail("HTML contents do not match.");
            Assert.assertEquals(areEqual,true,"HTML contents do not match.");
        }

    }
}

