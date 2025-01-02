package com.frameium.utilities;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class ClientSidePerformanceReportUtils {
    private WebDriver driver;
    ArrayList urlList = new ArrayList();
    ArrayList titleList = new ArrayList();
    protected int indexCount = 0;
    public HashMap<String, String> UrlTitle = new HashMap<String, String>();
    public static ArrayList Allvalues = new ArrayList();


    public ClientSidePerformanceReportUtils(WebDriver driver) {
        this.driver = driver;
    }

    By pageHeader = By.xpath("//div[@class='lh-scores-header']");
    By ellipsisBtn = By.xpath("//button[@id='lh-tools-button']");
    By saveAsHTML = By.xpath("//a[text()='Save as HTML']");

    /**
     * This method used for Lighthouse report viewer
     */
    public String[] LighthouseMain(String currentUrl, String currentTitle) throws Exception {

        // currentUrl = driver.getCurrentUrl();
        // ArrayList<String> tabs2 = new ArrayList<String> (driver.getWindowHandles());
        // By Page = By.xpath(xpath);
        // clickElement(Page);
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(200));
        //WebDriverWait wait = new WebDriverWait(driver, 200);
        // driver.navigate().back();
        // String currentUrl1 = driver.getCurrentUrl();
        // currentTitle = driver.getTitle();

        // driver.switchTo().newWindow(WindowType.TAB);
        // driver.findElement(By.xpath(xpath)).sendKeys(Keys.CONTROL + "\t");

        driver.get("https://googlechrome.github.io/lighthouse/viewer/?psiurl=" + currentUrl);
        driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(2000));
        Thread.sleep(2000); // add a 2-second wait statement
        // System.out.println(setUp.baseTest.driver.getPageSource());
        // System.out.println("Current URL: " + setUp.baseTest.driver.getCurrentUrl());
        //System.out.println("Page title: " + setUp.baseTest.driver.getTitle());

        @SuppressWarnings("deprecation")
        WebDriverWait wait1 = new WebDriverWait(driver, Duration.ofSeconds(200));
        //wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//div[@id='lh-log' and contains(text(), 'Waiting for Lighthouse results ...')]")));
        //WebDriverWait wait1 = new WebDriverWait(driver, 200);
        WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(pageHeader));

        String Perfpercentage;


        By performncestatus = By.xpath("(//div[@Class ='lh-scores-header']/a)[1]");
        String Perfstatus = findElement(performncestatus).getAttribute("class");
        System.out.println(Perfstatus);
        if (Perfstatus.contains("pass")) {
            By percentage = By.xpath(
                    "//div[@Class ='lh-scores-header']/a[@class='lh-gauge__wrapper lh-gauge__wrapper--pass']//div[@class='lh-gauge__percentage']");
            Perfpercentage = findElement(percentage).getText();
            System.out.println("Performance score is :" + Perfpercentage);
            Thread.sleep(1000);
        } else if (Perfstatus.contains("fail")) {

            By percentage = By.xpath(
                    "//div[@Class ='lh-scores-header']/a[@class='lh-gauge__wrapper lh-gauge__wrapper--fail']//div[@class='lh-gauge__percentage']");
            Perfpercentage = findElement(percentage).getText();
            System.out.println("Performance score is :" + Perfpercentage);
            Thread.sleep(1000);
        } else {
            By percentage = By.xpath(
                    "(//div[@Class ='lh-scores-header']/a[@class='lh-gauge__wrapper lh-gauge__wrapper--average']//div[@class='lh-gauge__percentage'])[1]");
            Perfpercentage = findElement(percentage).getText();
            System.out.println("Performance score is :" + Perfpercentage);
            Thread.sleep(1000);
        }


        By firstContentfulTime = By.xpath("//span[text()='First Contentful Paint']/..//div[@class='lh-metric__value']");
        String contentfulTimeValue = findElement(firstContentfulTime).getText();
        System.out.println("First contentful time : " + contentfulTimeValue);
        Thread.sleep(1000);

//	    By timeToInteractive = By.xpath("//span[text()='Time to Interactive']/..//div[@class='lh-metric__value']");
//	    String timeTointeractiveValue = findElement(timeToInteractive).getText();
//	    System.out.println("Time to interactive : "+timeTointeractiveValue);
//	    Thread.sleep(1000);

        By speedIndex = By.xpath("//span[text()='Speed Index']/..//div[@class='lh-metric__value']");
        String spedIndexValue = findElement(speedIndex).getText();
        System.out.println("Speed index : " + spedIndexValue);
        Thread.sleep(1000);

        By totalBlockingTime = By.xpath("//span[text()='Total Blocking Time']/..//div[@class='lh-metric__value']");
        String totalBlockingTimeValue = findElement(totalBlockingTime).getText();
        System.out.println("Total blocking time : " + totalBlockingTimeValue);
        Thread.sleep(1000);

        By largestContentfulPaint = By
                .xpath("//span[text()='Largest Contentful Paint']/..//div[@class='lh-metric__value']");
        String largestContentfulPaintValue = findElement(largestContentfulPaint).getText();
        System.out.println("Largest contentful paint : " + largestContentfulPaintValue);

        Thread.sleep(1000);
        By CumulativeLayoutShift = By
                .xpath("//span[text()='Cumulative Layout Shift']/..//div[@class='lh-metric__value']");
        String CumulativeLayoutShiftValue = findElement(CumulativeLayoutShift).getText();
        System.out.println("Cumulative layout shift : " + CumulativeLayoutShiftValue);

        /*
         * clickElement(ellipsisBtn); clickElement(saveAsHTML); driver.close();
         * driver.switchTo().window(tabs2.get(0));
         */
        System.out.println("Performance details of " + currentUrl);
        System.out.println("Performance details of " + currentTitle);
        System.out.println("===================================================");
        System.out.println("Performance score is :" + Perfpercentage);
        System.out.println("First contentful time : " + contentfulTimeValue);
        // System.out.println("Time to interactive : "+timeTointeractiveValue);
        System.out.println("Speed index : " + spedIndexValue);
        System.out.println("Total blocking time : " + totalBlockingTimeValue);
        System.out.println("Largest contentful paint : " + largestContentfulPaintValue);
        System.out.println("Cumulative layout shift : " + CumulativeLayoutShiftValue);

        System.out.println("===================================================");

        // driver.switchTo().window(tabs2.get(0));

        driver.navigate().back();
/*
        String[] score = new String[9];
        score[0] = currentUrl;
        score[1] = currentTitle;
        score[2] = Perfpercentage;
        score[3] = contentfulTimeValue;
        // score[4] = timeTointeractiveValue;
        score[5] = spedIndexValue;
        score[6] = totalBlockingTimeValue;
        score[7] = largestContentfulPaintValue;
        score[8] = CumulativeLayoutShiftValue;
*/

        String[] score = new String[9];
        score[0] = currentUrl;
        score[1] = currentTitle;
        score[2] = Perfpercentage;
        score[3] = contentfulTimeValue;
        // score[4] = timeTointeractiveValue;
        score[5] = spedIndexValue;
        score[6] = totalBlockingTimeValue;
        score[7] = largestContentfulPaintValue;
        score[8] = CumulativeLayoutShiftValue;

        return score;

}
    /**
     * This method will generate report for Performance testing
     */
    public void createPerformanceReport(int indexCount, ArrayList AllValues) throws IOException {

        Date date = new Date();
        ArrayList PageUrl = new ArrayList();
        ArrayList currentTitle = new ArrayList();
        ArrayList PerformanceScore = new ArrayList();
        ArrayList ContentfulTime = new ArrayList();
        ArrayList TimeTointeractive = new ArrayList();
        ArrayList SpedIndex = new ArrayList();
        ArrayList totalBlockingTime = new ArrayList();
        ArrayList largestContentfulPaint = new ArrayList();
        ArrayList CumulativeLayoutShift = new ArrayList();
        String[] value2;
        String Pageurl2;
        String Pagetitle2, title, titlefull, Score, contentfultime, spedindex, timetointeract, largestcont, cumlat,
                totalblock;
        String performancescore2;
        String ContentfulTime2;
        String TimeTointeractive2;
        String SpedIndex2;
        String totalBlockingTime2;
        String largestContentfulPaint2;
        String CumulativeLayoutShift2;
        int averagescore = 0;
        int total = 0;

        int icontent, ispeed, itime, ilarge, icumlat, itotal, iTitle, iScore,ifid, ittfb, iinp;
        String pattern = "dd-MMM-yyyy HH-mm-ss";
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
        String reportDate = simpleDateFormat.format(new Date());
        // System.out.println(date);
        reportDate = reportDate.replace(" ", "_");
        String reportpath = System.getProperty("user.dir");
        String resultFile = reportpath + "\\Reports\\ClientSidePerformanceReport-" + reportDate + ".html";
        // String resultFile = "D:\\Reports\\Performancehtmlcustomreport.html";

        File file = new File(resultFile);
        System.out.println(file.exists());

        if (file.exists()) {
            file.delete();
        }

        if (!file.exists()) {
            FileWriter fw = new FileWriter(file.getAbsoluteFile(), true);
            BufferedWriter bw1 = new BufferedWriter(fw);
        }

        BufferedWriter bw = new BufferedWriter(new FileWriter(file, true));

        for (int i = 0; i <= AllValues.size() - 1; i++) {
            value2 = (String[]) AllValues.get(i);
            Pageurl2 = value2[0];
            Pagetitle2 = value2[1];
            performancescore2 = value2[2];
            ContentfulTime2 = value2[3];
            TimeTointeractive2 = value2[4];
            SpedIndex2 = value2[5];
            totalBlockingTime2 = value2[6];
            largestContentfulPaint2 = value2[7];
            CumulativeLayoutShift2 = value2[8];


            PageUrl.add(Pageurl2);
            currentTitle.add(Pagetitle2);
            PerformanceScore.add(performancescore2);
            ContentfulTime.add(ContentfulTime2);
            TimeTointeractive.add(TimeTointeractive2);
            SpedIndex.add(SpedIndex2);
            totalBlockingTime.add(totalBlockingTime2);
            largestContentfulPaint.add(largestContentfulPaint2);
            CumulativeLayoutShift.add(CumulativeLayoutShift2);


        }

        bw.write("<!DOCTYPE HTML>" + "\n");
        bw.write("<html>" + "\n");

        // String titlenew = currentTitle.replaceAll("[^a-zA-Z]+", "-");
        // block2 - Pages Accessed
        // String title1 = currentTitle1.replaceAll("[^a-zA-Z]+", "-");
        // String title2 = currentTitle2.replaceAll("[^a-zA-Z]+", "-");
        // html

        bw.write("<head>" + "\n");
        bw.write("<meta name=\"viewport\" content=\"width=device-width, initial-scale=1\">" + "\n");//added 05/08/2024
        bw.write("<div class=\"triangle-right\"></div>" + "\n");
        bw.write(
                "<div class=\"logo\"><img src=\"https://www.testhouse.net/wp-content/uploads/2021/08/LOGO_Testhouse.png\" width=\"350\" alt=\"My Image\" /></div>"
                        + "\n");
        bw.write("<div class=\"triangle-left\"></div>" + "\n");

        // Heading

        bw.write("<h1 style=\"text-align:center;\">Client-Side Performance Test Report </h1><br><br>" + "\n");

        // block3 - Summary

        bw.write("<div class=\"bloc3\">" + "\n");
        bw.write("<h2>Summary</h2>" + "\n");
        bw.write("<ul><li>Reviewed on:&nbsp; <div class=\"date\" style=\"display:inline;\"><span></span></div></li><br>"
                + "\n");
        bw.write(
                "<li>Analyzed Page URL:&nbsp; <div class=\"url\" style=\"display:inline;\"><span></span></div></li><br>"
                        + "\n");
        bw.write(
                "<li>Total Pages Accessed:&nbsp; <div class=\"pagecount\" style=\"display:inline;\"><span></span></div></li><br>"
                        + "\n");
        bw.write("</ul><br>" + "\n");
        bw.write("</div>" + "\n");

        bw.write("<div class=\"bloc2\">" + "\n");
        bw.write("<h2>Pages Accessed</h2>" + "\n");
        bw.write("<div>" + "\n");
        bw.write("<ul>" + "\n");
        for (iTitle = 0; iTitle <= currentTitle.size() - 1; iTitle++) {
            title = currentTitle.get(iTitle).toString().replaceAll("[^a-zA-Z]+", "-");
            bw.write("<li><div class=\"" + title + "\"><span></span></div></li><br>" + "\n");
        }
        bw.write("</ul> " + "\n");
        bw.write("</div>" + "\n");
        bw.write("</div>" + "\n");
        bw.write("<br>" + "\n");
        bw.write("<br>" + "\n");
        bw.write("<br>" + "\n");
        bw.write("<br>" + "\n");
        String ZingChart=reportpath + "\\src\\main\\resources\\ZingChart.js";
        //C:\\Users\\THI2201882\\Downloads\\testCucumber (3)\\testCucumber\\src\\main\\resources\\ZingChart.js
        //bw.write("<script nonce=\"undefined\" src=\"\"></script>" + "\n");
        bw.write("<script nonce=\"undefined\" src=\""+ZingChart +"\"></script>" + "\n");
        bw.write("</head>" + "\n");
        bw.write("<body>" + "\n");
        bw.write("<br><br><br><br><br><br><br><br><br><br><br><br><br>" + "\n");

        // blockgraph - Horizontal Bar Graph

        bw.write("<div class=\"blockgraph\">" + "\n");
        bw.write("<div class=\"skill-bars\" style=\"align:center\">" + "\n");
        bw.write("<h2 class=\"heading\"> PageWise Performance</h2><br><br>" + "\n");
        for (iTitle = 0; iTitle <= currentTitle.size() - 1; iTitle++) {
            title = currentTitle.get(iTitle).toString().replaceAll("[^a-zA-Z]+", "-");
            titlefull = currentTitle.get(iTitle).toString();
            bw.write("<div class=\"bar\">" + "\n");
            bw.write("<div class=\"info\">" + "\n");
            bw.write("<span>" + titlefull + "</span>" + "\n");
            bw.write("</div>" + "\n");
            bw.write("<div class=\"progress-line " + title + "\">" + "\n");
            bw.write("<span></span>" + "\n");
            bw.write("</div>" + "\n");
            bw.write("</div>" + "\n");
        }
        bw.write("</div>" + "\n");
        bw.write("</div>" + "\n");

        // blockgauge - Performance Gauge Meter

        bw.write("<div class=\"blockgauge\"><div id='myChart'></div></div>" + "\n");

        bw.write("<br>" + "\n");
        bw.write("<div class=\"score\">SCORE:&nbsp;<span></span></div>" + "\n");
        bw.write(
                "<br> <br><br> <br><br> <br><br> <br><br> <br><br> <br> <br><br> <br><br> <br><br> <br><br> <br><br><br><br>"
                        + "\n");

        // Metrics

        bw.write("<h2 align =\"center\">Metrics</h2>" + "\n");
        bw.write("<div class=\"liststyle\">" + "\n");
        bw.write("<ul style=\"font-size:large;\">" + "\n");
        bw.write(
                "<li><span class=\"metric\">FCP </span>- Time taken to load the first piece of DOM content after a user navigates to your page.</li>"
                        + "\n");
        bw.write(
                "<li><span class=\"metric\">LCP  </span>- LCP measures the time from when the user initiates loading the page until the largest image or text block is rendered within the viewport.</li>"
                        + "\n");
        bw.write("<li><span class=\"metric\">CLS </span> - calculates the visual stability of a given web page.</li>"
                + "\n");
        bw.write(
                "<li><span class=\"metric\">Speed Index  </span>- measures how quickly your page is visually complete above-the-fold </li>"
                        + "\n");
        bw.write(
                "<li><span class=\"metric\">Time to Interactive  </span>- metric that captures how quickly a site is ready for user interaction after it loads</li>"
                        + "\n");
        bw.write(
                "<li><span class=\"metric\">Total Blocking Time </span> - measures the usability of the web page during the loading </li>"
                        + "\n");
        bw.write("</ul> </div> <br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br>" + "\n");

        // PageWise Metric Values

        for (iTitle = 0; iTitle <= currentTitle.size() - 1; iTitle++) {
            title = currentTitle.get(iTitle).toString().replaceAll("[^a-zA-Z]+", "-");
            bw.write("<h2><div class=" + title + " style=\"text-align:center;\"><span></span></div></h2>" + "\n");
            bw.write("<br><br>" + "\n");
            bw.write("<div><table align =\"center\">" + "\n");
            bw.write("<tr>" + "\n");
            bw.write("<td><b>First Contentful Paint (FCP)</b></td>" + "\n");
            for (icontent = 0; icontent <= ContentfulTime.size() - 1; icontent++) {
                if (iTitle == icontent) {
                    contentfultime = ContentfulTime.get(icontent).toString();
                    bw.write("<td>" + contentfultime + "</td>" + "\n");
                    System.out.println(icontent + '-' + contentfultime);
                    break;
                }
            }
            // icontent++;
            bw.write("<td><b>Speed Index</b></td>" + "\n");
            for (ispeed = 0; ispeed <= SpedIndex.size() - 1; ispeed++) {

                if (iTitle == ispeed) {
                    spedindex = SpedIndex.get(ispeed).toString();
                    bw.write("<td>" + spedindex + "</td>" + "\n");
                    System.out.println(ispeed + '-' + spedindex);
                    break;
                }
            }

            bw.write("<td><b>Largest Contentful Paint (LCP)</b></td>" + "\n");
            for (ilarge = 0; ilarge <= largestContentfulPaint.size() - 1; ilarge++) {
                if (iTitle == ilarge) {
                    largestcont = largestContentfulPaint.get(ilarge).toString();
                    bw.write("<td>" + largestcont + "</td>" + "\n");
                    System.out.println(ilarge + '-' + largestcont);
                    break;
                }
            }





            bw.write("</tr>" + "\n");
            bw.write("<tr>" + "\n");
            bw.write("<td><b>Time to Interactive</b></td>" + "\n");
//					for(itime=0;ilarge<=TimeTointeractive.size()-1;itime++)
//	                {
//
//					   if(iTitle==itime)
//	                   {
//					        timetointeract=TimeTointeractive.get(itime).toString();

            bw.write("<td>" + "NA" + "</td>" + "\n");
            //bw.write("<td>" + "0" + "</td>" + "\n");

//		                    System.out.println(itime+'-'+timetointeract);
//		                    break;
//	                   }
//	                }

            bw.write("<td><b>Total Blocking Time</b></td>" + "\n");
            for (itotal = 0; itotal <= totalBlockingTime.size() - 1; itotal++) {

                if (iTitle == itotal) {
                    totalblock = totalBlockingTime.get(itotal).toString();
                    bw.write("<td>" + totalblock + "</td>" + "\n");
                    System.out.println(itotal + '-' + totalblock);
                    break;
                }
            }

            bw.write("<td><b>Cumulative Layout Index(CLS)</b></td>" + "\n");

            for (icumlat = 0; icumlat <= CumulativeLayoutShift.size() - 1; icumlat++) {

                if (iTitle == itotal) {
                    cumlat = CumulativeLayoutShift.get(icumlat).toString();
                    bw.write("<td>" + cumlat + "</td>" + "\n");
                    System.out.println(icumlat + '-' + cumlat);
                    break;
                }
            }

            bw.write("</tr> </table> </div><br>" + "\n");
        }

        bw.write("<br><br><br>" + "\n");
        bw.write("<br> <br> <br>" + "\n");

        // Javascript - Gauge Meter

        // calculate average performance score

        for (iScore = 0; iScore <= PerformanceScore.size() - 1; iScore++) {

            Score = PerformanceScore.get(iScore).toString();
            total = total + Integer.parseInt(Score);

        }
        averagescore = total / indexCount;
        bw.write("<script>" + "\n");
        bw.write("ZC.LICENSE = [\"569d52cefae586f634c54f86dc99e6a9\", \"b55b025e438fa8a98e32482b5f768ff5\"];" + "\n");
        bw.write("var myConfig7 = {" + "\n");
        bw.write("\"type\": \"gauge\"," + "\n");
        bw.write("\"background-color\": \"transparent\"," + "\n");
        bw.write("\"horizontalAlign\": \"center\"," + "\n");
        bw.write("\"scale-r\": {" + "\n");
        bw.write("\"aperture\": 200," + "\n");
        bw.write("\"values\": \"0:100:20\"," + "\n");
        bw.write("\"center\": {" + "\n");
        bw.write("\"size\": 18," + "\n");
        bw.write("\"border-color\": \"none\"" + "\n");
        bw.write("}," + "\n");
        bw.write("\"ring\": {" + "\n");
        bw.write("\"size\": 25," + "\n");
        bw.write("\"rules\": [{" + "\n");
        bw.write("\"rule\": \"%v >= 0 && %v <= 20\"," + "\n");
        bw.write("\"background-color\": \"red\"" + "\n");
        bw.write("}," + "\n");
        bw.write("{" + "\n");
        bw.write("\"rule\": \"%v >= 20 && %v <= 40\"," + "\n");
        bw.write("\"background-color\": \"orange\"" + "\n");
        bw.write("}," + "\n");
        bw.write("{" + "\n");
        bw.write("\"rule\": \"%v >= 40 && %v <= 60\"," + "\n");
        bw.write("\"background-color\": \"yellow\"" + "\n");
        bw.write("}," + "\n");
        bw.write("{" + "\n");
        bw.write("\"rule\": \"%v >= 60 && %v <= 80\"," + "\n");
        bw.write("\"background-color\": \"blue\"" + "\n");
        bw.write("}," + "\n");
        bw.write("{" + "\n");
        bw.write("\"rule\": \"%v >= 80 && %v <=100\"," + "\n");
        bw.write("\"background-color\": \"green\"" + "\n");
        bw.write("}" + "\n");
        bw.write("]" + "\n");
        bw.write("}," + "\n");
        bw.write("\"labels\": [\"Very Poor\", \"Poor\", \"Fair\", \"Good\", \"Great\", \"Excellent\"],  //Scale Labels"
                + "\n");
        bw.write("item: {    //Scale Label Styling" + "\n");
        bw.write("'font-color': \"white\"," + "\n");
        bw.write("'font-family': \"Georgia, serif\"," + "\n");
        bw.write("'font-size':13," + "\n");
        bw.write("'font-weight': \"bold\"," + "\n");
        bw.write("'font-style': \"normal\"," + "\n");
        bw.write("'offset-r': -6,    //To adjust the placement of your scale labels." + "\n");
        bw.write("}" + "\n");
        bw.write("}," + "\n");
        bw.write("\"plot\": {" + "\n");
        bw.write("\"csize\": \"6%\"," + "\n");
        bw.write("\"size\": \"100%\"," + "\n");
        bw.write("\"background-color\": \"white\"," + "\n");
        bw.write("'value-box': { //Value Boxes" + "\n");
        bw.write("placement: \"center\",  //Specify placement at \"center\", \"tip\", or \"edge\"." + "\n");
        bw.write("text: \"%v\"," + "\n");
        bw.write("'font-color': \"black\"," + "\n");
        bw.write("'font-size':18," + "\n");
        bw.write("}" + "\n");
        bw.write("}," + "\n");
        bw.write("\"series\": [{" + "\n");
        bw.write("\"values\": [" + averagescore + "]" + "\n");
        bw.write("}]" + "\n");
        bw.write("};" + "\n");
        bw.write("zingchart.render({" + "\n");
        bw.write("id: 'myChart'," + "\n");
        bw.write("data: myConfig7," + "\n");
        bw.write("height: \"90%\"," + "\n");
        bw.write("width: \"220%\"," + "\n");
        bw.write("});" + "\n");
        bw.write("</script>" + "\n");

        // Style

        bw.write("<style>" + "\n");

        bw.write(".metric {" + "\n");
        bw.write("font-size: large;" + "\n");
        bw.write("}" + "\n");

        bw.write("#myChart-license-text{" + "\n");
        bw.write("content-visibility: hidden;" + "\n");
        bw.write("backface-visibility: hidden;" + "\n");
        bw.write("}" + "\n");

        bw.write(".triangle-right {" + "\n");
        bw.write("width: 0;" + "\n");
        bw.write("height: 0;" + "\n");
        bw.write("border-left: 1450px solid white;" + "\n");
        bw.write("border-bottom: 250px solid transparent;" + "\n");
        bw.write("position: relative;" + "\n");
        bw.write("}" + "\n");

        bw.write(".triangle-left {" + "\n");
        bw.write("width: 0;" + "\n");
        bw.write("height: 0;" + "\n");
        bw.write("border-top: 150px solid transparent;" + "\n");
        bw.write("border-right: 850px solid #d3003f;" + "\n");
        bw.write("border-bottom: 40px solid transparent;" + "\n");
        bw.write("top: 6px;" + "\n");
        bw.write("right:4px;" + "\n");
        bw.write("position: absolute;" + "\n");
        bw.write("}" + "\n");

        bw.write(".pagecount span::after{content: \"" + indexCount + "\";}" + "\n");

        bw.write(".logo{" + "\n");
        bw.write("position: absolute;" + "\n");
        bw.write("z-index: 3;" + "\n");
        bw.write("top: 70px;" + "\n");
        bw.write("left:60px;" + "\n");
        bw.write("float:left;" + "\n");
        bw.write("}" + "\n");

        bw.write("body{" + "\n");
        bw.write("background-color: #1f1f1f;" + "\n");
        bw.write("background-repeat: no-repeat;" + "\n");
        bw.write("background-attachment: fixed;" + "\n");
        bw.write("background-size: cover;" + "\n");
        bw.write("}" + "\n");

        bw.write("table {" + "\n");
        bw.write("border-collapse: collapse;" + "\n");
        bw.write("font-family: Tahoma, Geneva, sans-serif;" + "\n");
        bw.write("background-color: transparent; border-color: white;" + "\n");
        bw.write("}" + "\n");

        bw.write("table, tbody, tr, th, td {" + "\n");
        bw.write("background-color: rgba(0, 0, 0, 0.0)!important;" + "\n");
        bw.write("}" + "\n");

        bw.write("table td {" + "\n");
        bw.write("padding: 15px;" + "\n");
        bw.write("}" + "\n");

        bw.write("table th {" + "\n");
        bw.write("background-color: white;" + "\n");
        bw.write("}" + "\n");

        bw.write("table thead td {" + "\n");
        bw.write("background-color: white;" + "\n");
        bw.write("color: #ffffff;" + "\n");
        bw.write("font-weight: bold;" + "\n");
        bw.write("font-size: 13px;" + "\n");
        bw.write("border: 1px solid #54585d;" + "\n");
        bw.write("}" + "\n");

        bw.write("table tbody td {" + "\n");
        bw.write("color: white;" + "\n");
        bw.write("border: 1px solid black;" + "\n");
        bw.write("border-width: 3px;" + "\n");
        bw.write("}" + "\n");

        bw.write("table tbody tr {" + "\n");
        bw.write("background-color: white;" + "\n");
        bw.write("}" + "\n");

        bw.write("table tbody tr:nth-child(odd) {" + "\n");
        bw.write("background-color: white;" + "\n");
        bw.write("}" + "\n");

        bw.write("h1 {" + "\n");
        bw.write("color: white;" + "\n");
        bw.write("font-family: Tahoma, Geneva, sans-serif;" + "\n");
        bw.write("font-size: 250%;" + "\n");
        bw.write("}" + "\n");

        bw.write(
                ".skill-bars{width: 550px;background: transparent;border-radius: 10px; left: 120px; position: absolute; border-width: 3px;}"
                        + "\n");
        bw.write(".skill-bars .bar{margin: 20px 0; margin-left:10px;}" + "\n");
        bw.write(".skill-bars .bar:first-child{margin-top: 0px;}" + "\n");
        bw.write(".skill-bars .bar .info{margin-bottom: 5px;}" + "\n");
        bw.write(
                ".skill-bars .bar .info span{font-weight: 500;font-size: 17px;opacity: 0;animation: showText 0.5s 1s linear forwards;color: white;}"
                        + "\n");
        bw.write("@keyframes showText {100%{opacity: 1;}}" + "\n");
        bw.write(
                ".skill-bars .bar .progress-line{height: 10px;width: 500px;background: #f0f0f0;position: relative;transform: scaleX(0);transform-origin: left;border-radius: 10px;box-shadow: inset 0 1px 1px rgba(0,0,0,0.05),0 1px rgba(255,255,255,0.8);animation: animate 1s cubic-bezier(1,0,0.5,1) forwards;}"
                        + "\n");
        bw.write("@keyframes animate {100%{transform: scaleX(1);}}" + "\n");
        bw.write(
                ".bar .progress-line span{height: 100%;position: absolute;border-radius: 10px;transform: scaleX(0);transform-origin: left;animation: animate 1s 1s cubic-bezier(1,0,0.5,1) forwards;}"
                        + "\n");

        bw.write(
                ".progress-line span::before{position: absolute; top: -10px;right: 0;height: 0;width: 0;border: 7px solid transparent;border-bottom-width: 0px;border-right-width: 0px; opacity: 0;animation: showText2 0.5s 1.5s linear forwards;}"
                        + "\n");
        bw.write(
                ".progress-line span::after{position: absolute;top: -28px;right: -40px;font-weight: 500;background: white;color: black;padding: 1px 8px;font-size: 12px;border-radius: 3px;opacity: 0;animation: showText2 0.5s 1.5s linear forwards;}"
                        + "\n");
        bw.write("@keyframes showText2 {100%{opacity: 1;}}" + "\n");

        bw.write(".bloc3" + "\n");
        bw.write("{" + "\n");
        bw.write("width: 40%;" + "\n");
        bw.write("color: white;" + "\n");
        bw.write("margin-right: 5px;" + "\n");
        bw.write("margin-left: 30px;" + "\n");
        bw.write("float: left;" + "\n");
        bw.write("height:230px;" + "\n");
        bw.write("padding-bottom: 10px;" + "\n");
        bw.write("padding: 25px 30px;" + "\n");
        bw.write("background-color: transparent;" + "\n");
        bw.write("font-family: Tahoma, Geneva, sans-serif;" + "\n");
        bw.write("border-radius: 10px;" + "\n");
        bw.write("border-style: double;" + "\n");
        bw.write("border-color: white;" + "\n");
        bw.write("margin-left:30px;" + "\n");
        bw.write("}" + "\n");

        bw.write(".bloc2" + "\n");
        bw.write("{" + "\n");
        bw.write("background-color: transparent;" + "\n");
        bw.write("margin-right: 30px;" + "\n");
        bw.write("width: 40%;" + "\n");
        bw.write("height:230px;" + "\n");
        bw.write("float:right;" + "\n");
        bw.write("right:70px;" + "\n");
        bw.write("padding-bottom: 10px;" + "\n");
        bw.write("margin-left:50px;" + "\n");
        bw.write("z-index:1;" + "\n");
        bw.write("border-style: double;" + "\n");
        bw.write("border-color: white;" + "\n");
        bw.write("padding: 25px 30px;" + "\n");
        bw.write("border-radius: 10px;" + "\n");
        bw.write("}" + "\n");

        bw.write(".blockgauge{" + "\n");
        bw.write("background-color: transparent;" + "\n");
        bw.write("margin-right: 30px;" + "\n");
        bw.write("width: 40%;" + "\n");
        bw.write("height:350px;" + "\n");
        bw.write("float:right;" + "\n");
        bw.write("right:70px;" + "\n");
        bw.write("padding-bottom: 10px;" + "\n");
        bw.write("margin-left:50px;" + "\n");
        bw.write("border-style: double;" + "\n");
        bw.write("border-color: white;" + "\n");
        bw.write("padding: 25px 30px;" + "\n");
        bw.write("border-radius: 10px;" + "\n");
        bw.write("}" + "\n");

        bw.write(".blockgraph{" + "\n");
        bw.write("background-color: transparent;" + "\n");
        bw.write("margin-right: 30px;" + "\n");
        bw.write("width: 40%;" + "\n");
        bw.write("height:350px;" + "\n");
        bw.write("float:left;" + "\n");
        bw.write("right:70px;" + "\n");
        bw.write("padding-bottom: 10px;" + "\n");
        bw.write("margin-left:30px;" + "\n");
        bw.write("border-style: double;" + "\n");
        bw.write("border-color: white;" + "\n");
        bw.write("padding: 25px 30px;" + "\n");
        bw.write("border-radius: 10px;" + "\n");
        bw.write("}" + "\n");

        bw.write(".dummy" + "\n");
        bw.write("{" + "\n");
        bw.write("width: 1600px;" + "\n");
        bw.write("height:20px;" + "\n");
        bw.write("background-color: black;" + "\n");
        bw.write("top: 1px;" + "\n");
        bw.write("z-index:2;" + "\n");
        bw.write("position: relative;" + "\n");
        bw.write("}" + "\n");

        bw.write("h3,li,h2{" + "\n");
        bw.write("color: white;" + "\n");
        bw.write("font-family: Tahoma, Geneva, sans-serif;" + "\n");
        bw.write("}" + "\n");

        bw.write(".heading{" + "\n");
        bw.write("text-align:center;" + "\n");
        bw.write("}" + "\n");

        bw.write(".liststyle{" + "\n");
        bw.write("padding: 10px;" + "\n");
        bw.write("margin: 20px;" + "\n");
        bw.write(" font-size: 15px;" + "\n");
        bw.write("left: 200px;" + "\n");
        bw.write(" position: absolute;" + "\n");
        bw.write("font-family: Verdana;" + "\n");
        bw.write("text-align: left;" + "\n");
        bw.write("border-radius: 10px;" + "\n");
        bw.write("width: 70%;" + "\n");
        bw.write("list-style: none;" + "\n");
        bw.write("background-color:transparent;" + "\n");
        bw.write("border-style: double;" + "\n");
        bw.write(" border-color: white;" + "\n");
        bw.write("padding: 25px 30px;" + "\n");
        bw.write("border-radius: 10px;}" + "\n");

        bw.write(".score{" + "\n");
        bw.write("width:8%;" + "\n");
        bw.write("height:1px;" + "\n");
        bw.write("background-color: white;" + "\n");
        bw.write("position: absolute;" + "\n");
        bw.write("top: 1020px;" + "\n");
        bw.write("left: 1045px;" + "\n");
        bw.write("font-size:20px;" + "\n");
        bw.write("font-family:Verdana;" + "\n");
        bw.write("text-align:center;" + "\n");
        bw.write("color: black;" + "\n");
        bw.write("line-height: 12px;" + "\n");
        bw.write("padding: 20px;" + "\n");
        bw.write("border-style: double;" + "\n");
        bw.write("border-color: black;" + "\n");
        bw.write("border-radius: 10px;" + "\n");
        bw.write("}" + "\n");

        bw.write(".date span::after{content: \"" + date + "\";}" + "\n");
        String urlpage = null;
        // String urlpage=PageUrl.get().toString();
        for (int iUrl = 0; iUrl <= PageUrl.size() - 1; iUrl++) {
            urlpage = urlpage + PageUrl.get(iUrl).toString() + ",";
        }
        String urlPage = urlpage.replace("null", "");
        int urlLength = urlPage.length();
        String pageUrl = urlPage.substring(0, urlLength - 1);
        bw.write(".url span::after{content: \"" + pageUrl + "\";}" + "\n");

        bw.write(".metric{" + "\n");
        bw.write("color: #d3003f;" + "\n");
        bw.write("font-size:15px;" + "\n");
        bw.write("font-family:Verdana;" + "\n");
        bw.write("font-style:bold;" + "\n");
        bw.write("}" + "\n");

        bw.write(".score span::after{content: \"" + averagescore + "\";}" + "\n");
        bw.write(".pagecount span::after{content: \"" + indexCount + "\";}" + "\n");

        for (iTitle = 0; iTitle <= currentTitle.size() - 1; iTitle++) {

            for (iScore = 0; iScore <= PerformanceScore.size() - 1; iScore++) {

                if (iTitle == iScore) {
                    Score = PerformanceScore.get(iScore).toString();
                    int percentagenew = Integer.parseInt(Score);

                    title = currentTitle.get(iTitle).toString().replaceAll("[^a-zA-Z]+", "-");
                    bw.write(".bar .progress-line." + title + " span{width: " + percentagenew * 5
                            + "px; background: red;}" + "\n");
                    bw.write("." + title + " span::after{content: \"" + title + "\";}" + "\n");
                    bw.write(".progress-line." + title + " span::after{content: \"" + percentagenew + "\";}" + "\n");
                    break;

                }

            }

        }
        // Add the media query code here
        bw.write("@media only screen and (max-width: 600px) {" + "\n");/////added05/08/2024 start
        bw.write("  h1 {" + "\n");
        bw.write("    font-size: 2rem;" + "\n");
        bw.write("  }" + "\n");
        bw.write("  .bloc3 {" + "\n");
        bw.write("    width: 100%;" + "\n");
        bw.write("  }" + "\n");
        bw.write("  .bloc2 {" + "\n");
        bw.write("    width: 100%;" + "\n");
        bw.write("  }" + "\n");
        bw.write("}" + "\n");///Added 05/08/2024 end


        bw.write("</style>" + "\n");

        // html closing

        bw.write("</body>" + "\n");
        bw.write("</html>" + "\n");

        bw.flush();
        bw.close();

    }

    public void generateReport() {
        HashMap<String, String> UrlTitle = PageUrltitleCollector(urlList, titleList);
        if (UrlTitle.size() > indexCount) {
            indexCount++;
        }
        for (Map.Entry<String, String> entry : UrlTitle.entrySet()) {
            String url = entry.getKey();
            try {
                String[] values = LighthouseMain(url, entry.getValue());
                Allvalues.add(values);
            } catch (Exception e) {
                System.err.println("Error generating report for URL " + url + ": " + e.getMessage());
            }
        }
        System.out.println("Allvalues: " + Allvalues);
        System.out.println("Size of Allvalues: " + Allvalues.size());
        indexCount = Allvalues.size();
        try {
            createPerformanceReport(indexCount, Allvalues);
        } catch (IOException e) {
            System.err.println("Error creating performance report: " + e.getMessage());
        }
    }


    /**
     * This method will find element in page
     */
    public WebElement findElement(By byElement) {
        //WebDriverWait wait = new WebDriverWait(driver, 30);
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(3000));
        WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(byElement));
        return element;
    }


    public  HashMap<String, String> urltitle = new HashMap<String, String>();

    public HashMap<String, String> PageUrltitleCollector(ArrayList urlList, ArrayList titleList) {
        String currentUrl = getCurrentUrl();
        if (!(urlList.contains(currentUrl)) && currentUrl.startsWith("http")) {
            String pageTitle = driver.getTitle();
            urlList.add(currentUrl);
            titleList.add(pageTitle);
            urltitle.put(currentUrl, pageTitle);
        }
        return urltitle;
    }

    public  String getCurrentUrl() {
        return driver.getCurrentUrl();
    }

}
