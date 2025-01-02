package com.frameium.stepdef.SaudiExchange;

import com.frameium.genericfunctions.GenericFunctions;
import com.frameium.pageobject.SaudiExchange.HomePage;
import com.frameium.stepdef.Hooks;
import com.frameium.stepdef.TestSetUp;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class SortingDataValidationTest {

    private static List<WebElement> actualList = new ArrayList<>();
    private static List<WebElement> expectedList = new ArrayList<>();
    Hooks hooks;

    TestSetUp setUp;
    GenericFunctions genericFunctions;

    public SortingDataValidationTest(TestSetUp setUp) throws IOException {
        hooks = new Hooks();
        this.setUp = setUp;
        genericFunctions = new GenericFunctions(setUp.baseTest.getDriver());
    }


    public SortingDataValidationTest() throws IOException {
    }

    @When("User is on the Market Performance page")
    public void user_is_on_the_market_performance_page() throws InterruptedException {
        HomePage hm = new HomePage(setUp.baseTest.driver);
        hm.ourMarketMenu().click();

        hm.marketPerformancepage().click();
        Thread.sleep(1000);
        genericFunctions.waitForPageToLoad(8000);
        //;
        String expected = "Market Performance";
        String actual = setUp.baseTest.driver.getTitle();
        hooks.takeScreenshot(hooks.scenario);
        if (expected.equals(actual)) {
            genericFunctions.logToExtentReport("Pass", "The user is on the Market performance page");
        } else {
            genericFunctions.logToExtentReport("Fail", "The user is not on the Market performance page");
        }
        Assert.assertEquals(actual, expected);
    }

    @Then("I store the data of Highest column in a list and sort it by java function")
    public void i_store_the_data_of_highest_column_in_a_list_and_sort_it_by_java_function() {
        boolean flag = false;
        HomePage hm = new HomePage(setUp.baseTest.driver);
        expectedList = hm.highestdata();
        hooks.takeScreenshot(hooks.scenario);
        for (WebElement ele : expectedList) {
            System.out.println(ele.getText());
            genericFunctions.logToExtentReport("Log", "UnSorted Items In Page " + ele.getText());
        }
        if (expectedList != null && !isSortedDescending(expectedList) == false) {
            genericFunctions.logToExtentReport("Log", "List of unsorted data from site is extracted from highest Column");
        }
        genericFunctions.logToExtentReport("Log", "List of unsorted data from site is extracted from highest Column");
        System.out.println(expectedList.size());

        // Sorting the list of elements using a custom comparator
        Collections.sort(expectedList, new Comparator<WebElement>() {
            @Override
            public int compare(WebElement element1, WebElement element2) {
                // Implement your comparison logic here
                // For example, comparing text content of the elements
                double num1 = Double.parseDouble(element1.getText());
                double num2 = Double.parseDouble(element2.getText());
                return Double.compare(num2, num1); // for descending order
            }
        });

        System.out.println("-------------SORTED DATA--------");
        for (WebElement ele : expectedList) {
            System.out.println(ele.getText());
            genericFunctions.logToExtentReport("Log", "Items after sorting using test function " + ele.getText());
        }
        System.out.println("-------------SORTED DATA--------");
        System.out.println(expectedList.size());

        if (expectedList != null && isSortedDescending(expectedList)) {
            genericFunctions.logToExtentReport("Pass", "The List of Highest data column from Market Performance is  Sorted in Descending order by Java function ");
            flag = true;
        } else {
            genericFunctions.logToExtentReport("Fail", "The List of Highest data column from Market Performance is  not Sorted in Descending order by Java function ");
        }


        Assert.assertTrue(flag);
    }

    @Then("I sort the data of highest column from website and store it in a list")
    public void i_sort_the_data_of_highest_column_from_website_and_store_it_in_a_list() {
        boolean flag = false;
        HomePage hm = new HomePage(setUp.baseTest.driver);
        Actions act = new Actions(setUp.baseTest.driver);
        WebElement element = hm.sortHighestdata();
        act.doubleClick(element).perform();
        actualList = hm.highestdata();
        hooks.takeScreenshot(hooks.scenario);
        for (WebElement ele : actualList) {
            System.out.println(ele.getText());
            genericFunctions.logToExtentReport("Log", "Items retrieved from application after table sorting" + ele.getText());
        }
        System.out.println(actualList.size());
        if (actualList != null && isSortedDescending(actualList)) {
            flag = true;
            genericFunctions.logToExtentReport("Pass", "The List of Highest data column from Market Performance is  Sorted in Descending order by from site ");
        }else {
            genericFunctions.logToExtentReport("Fail", "The List of Highest data column from Market Performance is not  Sorted in Descending order by from site ");
        }

        Assert.assertTrue(flag);
    }

    @Then("I compare both the list it must be matched")
    public void i_compare_both_the_list_it_must_be_matched() {
        if(actualList.equals(expectedList)) {
            genericFunctions.logToExtentReport("Pass", "Both Expected List and actual List are equals");
        }
        else {
            genericFunctions.logToExtentReport("Fail", "Both Expected List and actual List are not equals");
        }

        Assert.assertEquals(actualList, expectedList);
    }

    public boolean isSortedDescending(List<WebElement> expectedList2) {
        for (int i = 0; i < expectedList2.size() - 1; i++) {
            if (Double.parseDouble(expectedList2.get(i).getText()) < Double.parseDouble(expectedList2.get(i + 1).getText())) {
                return false;
            }
        }
        return true;
    }

}
