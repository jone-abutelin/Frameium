package com.frameium.stepdef.SaudiExchange;

import com.frameium.genericfunctions.GenericFunctions;
import com.frameium.pageobject.SaudiExchange.HomePage;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.testng.Assert;

import java.io.File;
import java.io.IOException;

public class LogoValidationTest extends GenericFunctions {

    String systemPath = System.getProperty("user.dir");

    @When("I am on the home page of SaudiExchange in production from Logo")
    public void i_am_on_the_home_page_of_saudi_exchange_in_production_from_logo() {
        String expected = "Home";
        String actual = driver.getTitle();
        Assert.assertEquals(actual, expected);
    }

    @Then("I go to logo and copy logo by giving name as {string} and I save the logo from production")
    public void i_go_to_logo_and_copy_logo_by_giving_name_as_and_i_save_the_logo_from_production(String name) {
        HomePage hm = new HomePage(driver);
        String linkOfLogo = null;
        if (name.equals("saudiExLogoOnToppro")) {
            linkOfLogo = hm.linkOfSaudiExLogoOnTop();
        } else if (name.equals("saudiTadawulLogopro")) {
            linkOfLogo = hm.linkOfSaudiTadawulLogo();
        }
        String path = systemPath + ConfigManager.getProperty("actual_logo");
        boolean result = hm.downloadImage(linkOfLogo, name, path);
        Assert.assertTrue(result);

    }

    @When("I am on the home page of SaudiExchange in uat from Logo")
    public void i_am_on_the_home_page_of_saudi_exchange_in_uat_from_logo() {
        String expected = "Home";
        String actual = driver.getTitle();
        Assert.assertEquals(actual, expected);
    }

    @Then("I go to logo and copy logo by giving name as {string} and I save the logo from uat")
    public void i_go_to_logo_and_copy_logo_by_giving_name_as_and_i_save_the_logo_from_uat(String name) {
        HomePage hm = new HomePage(driver);
        String linkOfLogo = null;
        if (name.equals("saudiExLogoOnTopuat")) {
            linkOfLogo = hm.linkOfSaudiExLogoOnTop();
        } else if (name.equals("eddaLogouat")) {
            linkOfLogo = hm.linkOfEddaLogo();
        }
        String path = systemPath + ConfigManager.getProperty("expected_logo");
        boolean result = hm.downloadImage(linkOfLogo, name, path);
        Assert.assertTrue(result);
    }

    @Then("I Compare the {string} and {string} both the Logo must be same")
    public void i_compare_the_and_both_the_logo_must_be_same(String actualLogo, String expectedLogo) throws IOException {
        String actual_Logo_path = systemPath + ConfigManager.getProperty("actual_logo") + actualLogo + ".png";

        String expected_Logo_path = systemPath + ConfigManager.getProperty("expected_logo") + expectedLogo + ".png";

        HomePage hm = new HomePage(driver);

        File actual = new File(actual_Logo_path);
        File expected = new File(expected_Logo_path);
        int percentage = hm.getImagePercentage(actual, expected);
        System.out.println(percentage);
        boolean flag = percentage == 100;
        if (flag) {
            logToExtentReport("Pass", "Both the Logo are same");
            System.out.println("Both the Logo are same");
        } else {
            logToExtentReport("Fail", "Both the Logo are different");
            System.out.println("Both the Logo are different");
        }
        Assert.assertTrue(flag);
    }

}


