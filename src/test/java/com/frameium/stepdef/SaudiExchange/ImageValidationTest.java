package com.frameium.stepdef.SaudiExchange;

import com.frameium.genericfunctions.GenericFunctions;
import com.frameium.pageobject.SaudiExchange.HomePage;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.testng.Assert;

import java.io.IOException;

public class ImageValidationTest extends GenericFunctions {

    String systemPath = System.getProperty("user.dir");

    @When("I am on the home page of SaudiExchange in production for image")
    public void i_am_on_the_home_page_of_saudi_exchange_in_production_for_image() {
        String expected = "Home";
        String actual = driver.getTitle();
        Assert.assertEquals(actual, expected);
    }

    @Then("I go to image and copy image by giving name as the {string} and I save the Image from production")
    public void i_go_to_image_and_copy_image_by_giving_name_as_the_and_i_save_the_image_from_production(String name) {
        HomePage hm = new HomePage(driver);
        String linkOfImage = null;
        if (name.contains("image1OnRU")) {
            linkOfImage = hm.linkOfImage1OnRU();
        } else if (name.contains("image1OnLooking")) {
            linkOfImage = hm.linkOfImage1OnLooking();
        }
        String path = systemPath + ConfigManager.getProperty("actual_image");
        boolean result = hm.downloadImage(linkOfImage, name, path);
        Assert.assertTrue(result);
    }

    @When("I am on the home page of SaudiExchange in UAT for image")
    public void i_am_on_the_home_page_of_saudi_exchange_in_uat_for_image() {
        String expected = "Home";
        String actual = driver.getTitle();
        Assert.assertEquals(actual, expected);
    }

    @Then("I go to image and copy image by giving name as the {string} and I save the Image from uat")
    public void i_go_to_image_and_copy_image_by_giving_name_as_the_and_i_save_the_image_from_uat(String name) {
        HomePage hm = new HomePage(driver);
        String linkOfImage = null;
        if (name.contains("image1OnRU")) {
            linkOfImage = hm.linkOfImage1OnRU();
        } else if (name.contains("image1OnLooking")) {
            linkOfImage = hm.linkOfImage1OnLooking();
        }
        String path = systemPath + ConfigManager.getProperty("expected_image");
        boolean result = hm.downloadImage(linkOfImage, name, path);
        Assert.assertTrue(result);
    }

    @Then("I Compare the {string} and {string} both the images must be same and if it is different then it should create a differenceImage by name {string}")
    public void i_compare_the_and_both_the_images_must_be_same_and_if_it_is_different_then_it_should_create_a_difference_image_by_name(String actualImage, String expectedImage, String differenceImage) throws IOException {
        String actual_Image_path = systemPath + ConfigManager.getProperty("actual_image") + actualImage + ".png";

        String expected_Image_path = systemPath + ConfigManager.getProperty("expected_image") + expectedImage + ".png";

        String difference_Image_Path = systemPath + ConfigManager.getProperty("difference_image");

        String differencePath = difference_Image_Path + differenceImage;

        HomePage hm = new HomePage(driver);
        boolean flag = hm.equalsWithDifference(actual_Image_path, expected_Image_path, differencePath, 0);
        if (flag) {
            logToExtentReport("Pass", "As both the Images are same so no difference image will be created.");
            System.out.println("Both the images are same");
        } else {
            logToExtentReport("Fail", "As both the Images are different so a difference image will be created");
            System.out.println("Both the images are different");
        }
        Assert.assertTrue(flag);
    }


}
