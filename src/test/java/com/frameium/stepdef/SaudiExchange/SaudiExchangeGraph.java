package com.frameium.stepdef.SaudiExchange;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.util.List;

import com.frameium.pageobject.SaudiExchange.SaudiExchangeVerification;
import com.frameium.stepdef.TestSetUp;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.testng.asserts.SoftAssert;

import com.frameium.genericfunctions.GenericFunctions;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;

public class SaudiExchangeGraph {
    SaudiExchangeVerification saudiexchange;
    List<WebElement> yaxisList,xaxisList;
    List<WebElement> yaxisList2,xaxisList2;
    List <String> graphDataPoint,graphDataPoint2;

    TestSetUp setUp;
    private GenericFunctions genericFunctions;

    public SaudiExchangeGraph(TestSetUp setUp) throws MalformedURLException {
        this.setUp = setUp;
        genericFunctions = new GenericFunctions(setUp.baseTest.driver);
        saudiexchange=new SaudiExchangeVerification(setUp.baseTest.driver);
    }


    @Then("User opens Stating Url {string}")
    public void user_opens_Staging_url(String string) throws Exception {
        genericFunctions.getApplicationUrl(string);
        Thread.sleep(10000);
    }

    @Then("User is in Saudi Exchange home page")
    public void user_is_in_saudiexchange_home_page() throws InterruptedException, IOException {
        saudiexchange.verifyUserInHome();
    }

    @Then("Move to graph and get YAxis data")
    public void get_graph_yaxis_data() throws InterruptedException, IOException {
        saudiexchange.moveToGraphElement();
        yaxisList = saudiexchange.getGraphYAxisData();
    }

    @Then("Move to graph and get XAxis data")
    public void get_graph_xaxis_data() throws InterruptedException, IOException {
        saudiexchange.moveToGraphElement();
        xaxisList = saudiexchange.getGraphXAxisData();
    }

    @Then("Move to graph and get YAxis data for second site")
    public void get_graph_yaxis_data_2() throws InterruptedException, IOException {
        yaxisList2 = saudiexchange.getGraphYAxisData();
    }

    @Then("Move to graph and get XAxis data for second site")
    public void get_graph_xaxis_data_2() throws InterruptedException, IOException {
        xaxisList2 = saudiexchange.getGraphXAxisData();
    }

    @Then("Compare graph yaxis data between two sites")
    public void compare_graph_yaxis_data() throws InterruptedException, IOException {
        saudiexchange.compareListItems(yaxisList,yaxisList2);
    }

    @Then("Compare graph xaxis data between two sites")
    public void compare_graph_xaxis_data() throws InterruptedException, IOException {
        saudiexchange.compareListItems(xaxisList,xaxisList2);
    }

    @Then("User is in saudiexchange Home Page uat")
    public void user_is_in_saudiexchange_home_page_uat() throws InterruptedException, IOException {
        saudiexchange.verifyUserInHome();
        yaxisList2 = saudiexchange.getGraphYAxisData();
    }
    @Then("User Verify the YAxis data with data from Prod")
    public void user_verify_the_y_axis_data_in_graph() throws InterruptedException {

        List<WebElement> yaxisList2 = saudiexchange.getGraphYAxisData();


    }

    @Then("Get the highcharts-tracker-line data in Graph")
    public void user_get_the_highcharts_tracker_line_data_in_graph() throws InterruptedException {

        Thread.sleep(2000);
        graphDataPoint = saudiexchange.getGraphDataPoints();

    }

    @Then("Get the highcharts-tracker-line data in Graph for second site")
    public void user_get_the_highcharts_tracker_line_data_in_graph_2() throws InterruptedException {

        Thread.sleep(2000);
        graphDataPoint2 = saudiexchange.getGraphDataPoints();

    }

    @Then("Compare graph data points between two sites")
    public void compare_graph_data_points() throws InterruptedException, IOException {
        saudiexchange.compareListItemsString(graphDataPoint,graphDataPoint2);
    }



    @Then("User Verify the YAxis data in graph {string},{string},{string},{string},{string},{string},{string},")
    public void user_verify_the_y_axis_data_in_graph(String Data1,String Data2, String Data3, String Data4, String Data5,String Data6,String Data7) throws InterruptedException {

        yaxisList.size();

        SoftAssert softAssertion= new SoftAssert();
        List<WebElement> AllList=setUp.baseTest.driver.findElements(By.xpath("//*[@text-anchor='start']"));

        for(int i=0;i<AllList.size();i++) {

            String txt=AllList.get(i).getText();
            System.out.println(txt);
            Thread.sleep(3000);
            if(txt.equals(Data1)) {

                System.out.println("Data Mathched: " +Data1);
            }
            if(txt.equals(Data2)) {

                System.out.println("Data Mathched: " +Data2);
            }
            if(txt.equals(Data3)) {

                System.out.println("Data Mathched: " +Data3);
            }
            if(txt.equals(Data4)) {

                System.out.println("Data Mathched: " +Data4);
            }

            if(txt.equals(Data5)) {

                System.out.println("Data Mathched: " +Data5);
            }

            if(txt.equals(Data6)) {

                System.out.println("Data Mathched: " +Data6);
            }

            softAssertion.assertAll();

        }

    }

    @Then("User is in Saudiexchange Home Page")
    public void user_is_in_Saudiexchange_home_page() throws InterruptedException {

        Thread.sleep(5000);
        JavascriptExecutor js = (JavascriptExecutor) setUp.baseTest.driver;
        js.executeScript("window.scrollBy(0,900)");
        Thread.sleep(5000);
        List<WebElement> AllListData=setUp.baseTest.driver.findElements(By.xpath("//*[@y='425']"));

        for(int i=0;i<AllListData.size();i++) {

            String txt=AllListData.get(i).getText();
            System.out.println(txt);
            Thread.sleep(3000);
            System.out.println("HorizontalYAxis Data displayed ");
        }

        Thread.sleep(2000);
    }

    @Then("User Verify the HorizontalYAxis data in graph {string},{string},{string},{string},{string},")
    public void user_verify_the_horizontal_y_axis_data_in_graph(String Data1, String Data2, String Data3, String Data4, String Data5) throws InterruptedException {

        SoftAssert softAssertion= new SoftAssert();
        List<WebElement> AllListData=setUp.baseTest.driver.findElements(By.xpath("//*[@y='425']"));

        for(int i=0;i<AllListData.size();i++) {

            String txt=AllListData.get(i).getText();
            System.out.println(txt);
            Thread.sleep(3000);
            if(txt.equals(Data1)) {
                System.out.println("Data Mathched: " +Data1);
            }
            if(txt.equals(Data2)) {
                System.out.println("Data Mathched: " +Data2);
            }
            if(txt.equals(Data3)) {
                System.out.println("Data Mathched: " +Data3);
            }
            if(txt.equals(Data4)) {
                System.out.println("Data Mathched: " +Data4);
            }

            if(txt.equals(Data5)) {
                System.out.println("Data Mathched: " +Data5);
            }

            softAssertion.assertAll();

        }

    }

    @Then("User is in StagingSaudiexchange Home Page")
    public void user_is_in_staging_saudiexchange_home_page() throws InterruptedException {

        Thread.sleep(5000);
        JavascriptExecutor js = (JavascriptExecutor) setUp.baseTest.driver;
        js.executeScript("window.scrollBy(0,900)");
        Thread.sleep(5000);
        List<WebElement> AllListData=setUp.baseTest.driver.findElements(By.xpath("//*[@y='425']"));

        for(int i=0;i<AllListData.size();i++) {

            String txt=AllListData.get(i).getText();
            System.out.println(txt);
            Thread.sleep(3000);
            System.out.println("HorizontalYAxis Data displayed ");
        }


    }

    @Then("User Verify the GraphLine data in Graph")
    public void user_verify_the_graph_line_data_in_graph() throws InterruptedException {

        Thread.sleep(10000);
        WebElement AllTracker = setUp.baseTest.driver.findElement(By.xpath("//*[local-name()='svg']//*[name()='g' and @class='highcharts-series-group']//*[@transform='translate(10,45) scale(1 1)']//*[name()='path' and @class='highcharts-graph']"));

        String txtdata=AllTracker.getAttribute("d");
        System.out.println(txtdata);
    }


}



