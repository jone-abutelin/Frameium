package com.frameium.pageobject.mobile.android;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

import java.util.ArrayList;
import java.util.List;

public class SaudiLandingPage  {

    WebDriver driver;

    public SaudiLandingPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(new AppiumFieldDecorator(driver), this);
    }

    @FindBy(xpath = "//a[@class='bx-next next4']")
    private WebElement btnArrowNext;

    @FindBy(xpath = "//a[contains(text(),'Read More ')]")
    private WebElement btnReadMore;

    @FindBy(xpath = "//a[text()='Market Watch']")
    private WebElement btnMarketWatch;

    @FindBy(xpath = "//*[text()='Which Market Are You Interested In?']/parent::*/ul/li/a[text()='Main Market Watch']")
    private WebElement linkMainMarketWatch;


    @FindBy(xpath = "//div[@class='personalize']/a")
    private WebElement btnPersonalize;


    @FindBy(xpath = "//a[text()='Apply Selected']")
    private WebElement btnApplySelected;



    public WebElement getbtnPersonalize() {
        return btnPersonalize;
    }
   public WebElement getbtnApplySelected() {
        return btnApplySelected;
    }

    public WebElement getBtnMarketWatch() {
        return btnMarketWatch;
    }

    public WebElement getLinkMainMarketWatch() {
        return linkMainMarketWatch;
    }

    public WebElement getBtnArrowNext() {
        return btnArrowNext;
    }

    public WebElement getBtnReadMore() {
        return btnReadMore;
    }


    public void choosePersonalizeMenuItems(String menuName) {


        // unselect all checkboxes before proceed to select new one
        List<WebElement> allLi = driver.findElements(By.xpath("//form[@id='selectIndicesForm']/ul/li"));

        for (WebElement li:allLi){

            if(li.getAttribute("class").equals("select")){
                li.click();
            }

        }

        if (menuName.contains("#")) {

            for (String menu : menuName.split("#")) {
                System.out.println("menu " + menu);
                driver.findElement(By.xpath("//form[@id='selectIndicesForm']/ul/li/div/strong[text()='" + menu + "']")).click();
            }

        } else {
            driver.findElement(By.xpath("//form[@id='selectIndicesForm']/ul/li/div/strong[text()='" + menuName + "']")).click();
        }



    }

    public void verifySelectedPersonalizeMenuItems(String expectedUiItems){

        List<String> expectedList = new ArrayList<>();

        if (expectedUiItems.contains("#")) {

            for (String menu : expectedUiItems.split("#")) {
                expectedList.add(menu);
            }

        }else {
            expectedList.add(expectedUiItems);
        }

        List<String> actualUIList = new ArrayList<>();
        List<WebElement>  selectedIndices = driver.findElements(By.xpath("//ul[@id='selectedIndices']/li/div[@class='name']"));

        for(WebElement wb: selectedIndices){
            actualUIList.add(wb.getText().trim());
        }

        System.out.println("actualUIList "+ actualUIList);
        System.out.println(" expectedList "+ expectedList );

        boolean equal = areListsEqual(actualUIList, expectedList);

        Assert.assertEquals(equal,true,"Actual and Expected lists are not equal");

    }





    public static boolean areListsEqual(List<String> list1, List<String> list2) {

        boolean isEqual = false;

        // Step 1: Check if sizes are equal
        if (list1.size() != list2.size()) {
            return false;
        }
        // Step 2: Iterate through lists and compare elements
        for (int i = 0; i < list1.size(); i++) {

            for (int j = 0; j < list2.size(); j++) {

                if (list1.get(i).equals(list2.get(j))) {
                    isEqual = true;
                    break;
                }

            }

        }

        return isEqual;
    }



}
