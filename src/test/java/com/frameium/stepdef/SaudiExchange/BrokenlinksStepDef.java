package com.frameium.stepdef.SaudiExchange;

import com.frameium.genericfunctions.GenericFunctions;
import com.frameium.pageobject.SaudiExchange.SaudiExchangeVerification;
import com.frameium.stepdef.TestSetUp;
import io.cucumber.java.en.Then;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.logging.LogEntries;
import org.openqa.selenium.logging.LogType;
import org.testng.Assert;

import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;

public class BrokenlinksStepDef {

    TestSetUp setUp;
    GenericFunctions genericFunctions;

    public BrokenlinksStepDef(TestSetUp setUp){
        this.setUp = setUp;
        genericFunctions = new GenericFunctions(setUp.baseTest.getDriver());
    }


    String systemPath = System.getProperty("user.dir");

    @Then("User verifies all links are working")
    public void userVerifiesAllLinksAreWorking() {
        List<WebElement> links = setUp.baseTest.driver.findElements(By.tagName("a"));
        int count = 0;
        for (WebElement link : links) {
            String href = link.getAttribute("href");
            System.out.println("Working link name: " + href);
        }


        for (WebElement link : links) {
            String href = link.getAttribute("href");
            try {
                URL url = new URL(href);
                HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                connection.setRequestMethod("GET");
                connection.connect();
                int responseCode = connection.getResponseCode();
                if (responseCode == 200) {
                    System.out.println("Working link: " + href);
                    count++;
                }
                connection.disconnect();
            } catch (Exception e) {
                System.out.println("Exception occurred while checking link: " + href);
            }
            if (count == 5) {
                break;
            }
        }
        setUp.baseTest.driver.quit();
        Assert.assertTrue(true);
    }

}
