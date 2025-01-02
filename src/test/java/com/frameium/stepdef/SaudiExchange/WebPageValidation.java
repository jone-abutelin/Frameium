package com.frameium.stepdef.SaudiExchange;

import com.frameium.genericfunctions.GenericFunctions;
import com.frameium.pageobject.SaudiExchange.HomePage;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

import java.io.IOException;

public class WebPageValidation extends GenericFunctions {

    String systemPath = System.getProperty("user.dir");

    @When("I am on the home page of SaudiExchange in production")
    public void i_am_on_the_home_page_of_saudi_exchange_in_production() {
        String expected = "Home";
        String actual = driver.getTitle();
        Assert.assertEquals(actual, expected);
    }

    @Then("I go to webPage and I save the webPage by giving name as the {string} from production")
    public void i_go_to_web_page_and_i_save_the_web_page_by_giving_name_as_the_from_production(String name) {
        HomePage hm = new HomePage(driver);
        WebElement marquee = hm.marquee();
        hideElement(marquee);
        String path = systemPath + ConfigManager.getProperty("actual_webpage");
        System.out.println(path);
        boolean flag = shootWebPage(name, path);
        if (flag) {
            logToExtentReport("Pass", "WebPage successfully saved.");
            System.out.println("WebPage is saved");
        } else {
            logToExtentReport("Fail", "Some error occured webPage is not Saved");
            System.out.println("WebPage is not saved");
        }
        Assert.assertTrue(flag);

    }

    @When("I am on the home page of SaudiExchange in UAT")
    public void i_am_on_the_home_page_of_saudi_exchange_in_uat() {
        String expected = "Home";
        String actual = driver.getTitle();
        Assert.assertEquals(actual, expected);
    }

    @Then("I go to webPage and I save the webPage by giving name as the {string} from uat")
    public void i_go_to_web_page_and_i_save_the_web_page_by_giving_name_as_the_from_uat(String name) {
        HomePage hm = new HomePage(driver);
        WebElement marquee = hm.marquee();
        hideElement(marquee);
        String path = systemPath + ConfigManager.getProperty("expected_webpage");
        System.out.println(path);
        boolean flag = false;
        if (name.equals("webPageuat")) {
            flag = shootWebPage(name, path);
            System.out.print("I am on the main Home page");
        } else if (name.contains("webPageuat1")) {
            hm.clickOntransportaion();
            flag = shootWebPage(name, path);
            System.out.print("I am on the main Home page for parallel");
        }

        if (flag) {
            logToExtentReport("Pass", "WebPage successfully saved.");
            System.out.println("WebPage saved Successfully");
        } else {
            logToExtentReport("Fail", "Some error occured webPage is not Saved");
            System.out.println("WebPage is not saved");
        }
        Assert.assertTrue(flag);
    }

    @Then("I Compare the {string} and {string} both WebPages It must be same and if it is different then it should create a differencewebPage by name {string}")
    public void i_compare_the_and_both_web_pages_it_must_be_same_and_if_it_is_different_then_it_should_create_a_differenceweb_page_by_name(String actualWebPage, String expectedWebPage, String differneceWebPage) throws IOException {
        String actual_WebPage_path = systemPath + ConfigManager.getProperty("actual_webpage") + actualWebPage + ".png";
        System.out.println(actual_WebPage_path);
        String expected_WebPage_path = systemPath + ConfigManager.getProperty("expected_webpage") + expectedWebPage
                + ".png";
        System.out.println(expected_WebPage_path);
        String difference_WebPage_Path = systemPath + ConfigManager.getProperty("difference_webpage");
        System.out.println(difference_WebPage_Path);
        String differencePath = difference_WebPage_Path + differneceWebPage;
        HomePage hm = new HomePage(driver);
        boolean flag = hm.equalsWithDifference(actual_WebPage_path, expected_WebPage_path, differencePath, 0.003);
        if (flag) {
            logToExtentReport("Pass", "As both the webPages are same so no difference image will be created.");
            System.out.println("Both the webPages are same");
        } else {
            logToExtentReport("Fail", "As both the webPages are differnet so a difference image will be created");
            System.out.println("Both the webPages are different");
        }
        Assert.assertTrue(flag);
    }

}
