package com.frameium.stepdef.SaudiExchange;

import com.frameium.genericfunctions.GenericFunctions;
import com.frameium.pageobject.SaudiExchange.HomePage;
import com.frameium.stepdef.Hooks;
import com.frameium.stepdef.TestSetUp;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

import java.io.IOException;

public class GraphValidationTest  {
    String systemPath = System.getProperty("user.dir");

    TestSetUp setUp;
    GenericFunctions genericFunctions;

    Hooks hooks = new Hooks();


    public GraphValidationTest(TestSetUp setUp) throws IOException {
        this.setUp = setUp;
        genericFunctions = new GenericFunctions(setUp.baseTest.getDriver());
    }


    @When("I am on the home page of SaudiExchange in production for graph")
    public void i_am_on_the_home_page_of_saudi_exchange_in_production_for_graph() {
        String expected = "Home";
        String actual = setUp.baseTest.driver.getTitle();
        Assert.assertEquals(actual, expected);
    }

    @Then("I go to graph and copy graph by giving name as the {string} and I save the Graph_Image from production")
    public void i_go_to_graph_and_copy_graph_by_giving_name_as_the_and_i_save_the_graph_image_from_production(String name) throws IOException, InterruptedException {
        HomePage hm = new HomePage(setUp.baseTest.driver);
        WebElement element = null;
        if (name.contains("main")) {
            element = hm.graph();
        } else if (name.contains("parallel")) {
            element = hm.graphOnParallel();
        }
        String path = systemPath + ConfigManager.getProperty("actual_graph");
        boolean result = hm.takeScreenshot(element, name, path);

        if (result){
            hooks.attachScreenshot(hooks.scenario,path+"/"+name+".png");
        }
        Assert.assertTrue(result);
    }

    @When("I am on the home page of SaudiExchange in UAT for graph")
    public void i_am_on_the_home_page_of_saudi_exchange_in_uat_for_graph() {
        String expected = "Home";
        String actual = setUp.baseTest.driver.getTitle();
        Assert.assertEquals(actual, expected);
    }

    @Then("I go to graph and copy graph by giving name as the {string} and I save the Graph_Image from uat")
    public void i_go_to_graph_and_copy_graph_by_giving_name_as_the_and_i_save_the_graph_image_from_uat(String name) throws IOException, InterruptedException {
        HomePage hm = new HomePage(setUp.baseTest.driver);
        WebElement element = null;
        if (name.contains("main")) {
            element = hm.graph();
        } else if (name.contains("parallel")) {
            element = hm.graphOnParallel();
        }
        String path = systemPath + ConfigManager.getProperty("expected_graph");
        boolean result = hm.takeScreenshot(element, name, path);
        if (result){
            hooks.attachScreenshot(hooks.scenario,path+"/"+name+".png");
        }
        Assert.assertTrue(result);
    }


    @Then("I Compare the {string} and {string} both the graph must be same and  if it is different then it should create a differenceGraphImage by name {string}")
    public void i_compare_the_and_both_the_graph_must_be_same_and_if_it_is_different_then_it_should_create_a_difference_graph_image_by_name(String actualGraph, String expectedGraph, String differenceGraphImage) throws IOException {
        String actual_Graph_path = systemPath + ConfigManager.getProperty("actual_graph") + actualGraph + ".png";

        String expected_Graph_path = systemPath + ConfigManager.getProperty("expected_graph") + expectedGraph + ".png";

        String difference_Graph_Path = systemPath + ConfigManager.getProperty("difference_graph");

        String differencePath = difference_Graph_Path + differenceGraphImage;

        genericFunctions.logToExtentReport("Log", "Actual Graph Image");
        hooks.attachScreenshot(hooks.scenario,actual_Graph_path);
        genericFunctions.logToExtentReport("Log", "Expected Graph Image");
        hooks.attachScreenshot(hooks.scenario,expected_Graph_path);

        HomePage hm = new HomePage(setUp.baseTest.driver);
        boolean flag = hm.equalsWithDifference(actual_Graph_path, expected_Graph_path, differencePath, 0);
        if (flag) {
            genericFunctions.logToExtentReport("Pass", "As both the Graph_images are same so no difference image will be created.");

            System.out.println("Both the Graph are same");
        } else {

            genericFunctions.logToExtentReport("Fail", "As both the Graph_images are differnet so a difference image will be created");
            genericFunctions.logToExtentReport("Log", "Difference Graph Image");
            hooks.attachScreenshot(hooks.scenario,differencePath + ".png");
            System.out.println("Both the Graph are different");
        }
        Assert.assertTrue(flag);
    }

}
