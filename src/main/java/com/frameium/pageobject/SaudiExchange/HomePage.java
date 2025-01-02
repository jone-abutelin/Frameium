package com.frameium.pageobject.SaudiExchange;

import java.awt.image.BufferedImage;
import java.awt.image.DataBuffer;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

import javax.imageio.ImageIO;

import org.openqa.selenium.By;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.io.FileHandler;

import com.assertthat.selenium_shutterbug.core.CaptureElement;
import com.assertthat.selenium_shutterbug.core.Shutterbug;
import com.assertthat.selenium_shutterbug.utils.image.ImageProcessor;
import com.frameium.genericfunctions.GenericFunctions;
public class HomePage extends GenericFunctions {
    private By saudiExLogoOnTop = By.xpath("//*[@id=\"wrapper\"]/header/nav/div/a/img");
    private By saudiTadawulLogo = By
            .xpath("//*[@id=\"layoutContainers\"]/div/section[6]/div[2]/div/section/ul/li[1]/div[1]/img");
    private By eddaLogo = By
            .xpath("//*[@id=\"layoutContainers\"]/div/section[6]/div[2]/div/section/ul/li[2]/div[1]/img");
    private By muqassaLogo = By
            .xpath("//*[@id=\"layoutContainers\"]/div/section[6]/div[2]/div/section/ul/li[3]/div[1]/img");
    private By wamidLogo = By
            .xpath("//*[@id=\"layoutContainers\"]/div/section[6]/div[2]/div/section/ul/li[4]/div[1]/img");
    private By saudiExLogoOnBottom = By.xpath("//*[@id=\"wrapper\"]/footer/div[1]/div[1]/a/img");

    private By image1OnRU = By.cssSelector(
            "#layoutContainers > div > section.mainCont > div.component-container.wpthemeRow.lookingFor.id-Z7_EGAQOM013GEP8UM4IFVAI4HC40 > div.component-control.id-Z7_5A602H80OGCJ40QNAM09E92046 > section > div.lookingFor > div > div > div > div:nth-child(1) > img");

    private By image1OnLooking = By.xpath(
            "//*[@id=\"layoutContainers\"]/div/section[2]/div[3]/div[1]/section/div[3]/div/div/div/div[2]/img");

    private By graphonpage = By.xpath(" //div[@id='mainMarket']//div[@class='smryTabDtlLft']");

    private By clickOnParallel = By
            .xpath("//*[@id=\"layoutContainers\"]/div/section[2]/div[1]/div/section/div[4]/ul[1]/li[2]/div[1]");

    private By graphOnParallelMarket = By.xpath("//div[@id='nomuMarket']//div[@class='smryTabDtlLft']");

    private By ourMarketMenu = By.xpath("//*[@id=\"wrapper\"]/header/nav/div/div[3]/ul/li[2]/a/span[2]");

    private By parallelMarketWatch = By.linkText("Parallel Market Watch");

    private By parallelMarcketCompanyWiseDataList = By.xpath("//table[@id = 'marketWatchTable1']//tbody//tr");

    private By marketPerformancepage = By
            .xpath("//*[@id=\"wrapper\"]/header/nav/div/div[3]/ul/li[2]/ul/li[2]/ul/li[3]/a");

    private By highestdata = By.xpath("//table[@id='marketPerformanceTable1']//tr//td[3]");

    private By sortHighestdata = By
            .xpath("//*[@id=\"marketPerformanceTable1_wrapper\"]/div[1]/div[1]/div/table/thead/tr/th[3]");

    private By marquee = By.cssSelector("#wrapper > div.newsTicker.btmAlign");

    private By transportaion = By.xpath("//*[@id=\"selectedIndices\"]/li[2]");

    public HomePage(WebDriver driver) {
        driver = this.driver;
    }

    // WebElement for Logo

    public WebElement saudiExLogoOnTop() {
        WebElement ele = findElement(saudiExLogoOnTop);
        scrollIntoView(ele);
        return ele;
    }

    public WebElement saudiTadawulLogo() {
        WebElement ele = findElement(saudiTadawulLogo);
        scrollIntoView(ele);
        return ele;
    }

    public WebElement eddaLogo() {
        WebElement ele = findElement(eddaLogo);
        scrollIntoView(ele);
        return ele;
    }

    public WebElement muqassaLogo() {
        WebElement ele = findElement(muqassaLogo);
        scrollIntoView(ele);
        return ele;
    }

    public WebElement wamidLogo() {
        WebElement ele = findElement(wamidLogo);
        scrollIntoView(ele);
        return ele;
    }

    public WebElement saudiExLogoOnBottom() {
        WebElement ele = findElement(saudiExLogoOnBottom);
        scrollIntoView(ele);
        return ele;
    }

    // WebElement of Images
    public WebElement image1OnRU() {
        WebElement ele = findElement(image1OnRU);
        scrollIntoView(ele);
        return ele;
    }

    public WebElement image1OnLooking() {
        WebElement ele = findElement(image1OnLooking);
        scrollIntoView(ele);
        return ele;
    }

    // WebElement for graph

    public WebElement graph() throws InterruptedException {
        WebElement graph = findElement(graphonpage);
        scrollIntoView(graph);
        Thread.sleep(3000);
        return graph;

    }

    public WebElement graphOnParallel() throws InterruptedException {
        findElement(clickOnParallel).click();
        WebElement ele = findElement(graphOnParallelMarket);
        scrollIntoView(ele);
        Thread.sleep(3000);
        return ele;
    }

    // Marquee
    public WebElement marquee() {
        WebElement ele = findElement(marquee);
        scrollIntoView(ele);
        return ele;
    }

    // Link of Logo

    public String linkOfSaudiExLogoOnTop() {
        return saudiExLogoOnTop().getAttribute("src");
    }

    public String linkOfSaudiTadawulLogo() {
        return saudiTadawulLogo().getAttribute("src");
    }

    public String linkOfEddaLogo() {
        return eddaLogo().getAttribute("src");
    }

    public String linkOfMuqassaLogo() {
        return muqassaLogo().getAttribute("src");
    }

    public String linkOfWamidLogo() {
        return wamidLogo().getAttribute("src");
    }

    public String linkOfSaudiExLogoOnBottom() {
        return saudiExLogoOnBottom().getAttribute("src");
    }

    // Link of Images

    public String linkOfImage1OnRU() {
        return image1OnRU().getAttribute("src");
    }

    public String linkOfImage1OnLooking() {
        return image1OnLooking().getAttribute("src");
    }

    // WebElement for Click actions on menu and there options

    public WebElement ourMarketMenu() {
        WebElement ele = findElement(ourMarketMenu);
        scrollIntoView(ele);
        return ele;
    }

    public WebElement parallelMarketWatch() {
        WebElement ele = findElement(parallelMarketWatch);
        scrollIntoView(ele);
        return ele;
    }

    public WebElement marketPerformancepage() throws InterruptedException {
        WebElement ele = findElement(marketPerformancepage);
        scrollIntoView(ele);

        return ele;
    }

    public WebElement sortHighestdata() {
        WebElement ele = findElement(sortHighestdata);
        scrollIntoView(ele);
        return ele;
    }

    public void clickOntransportaion() {
        WebElement ele = findElement(transportaion);
        scrollIntoView(ele);
        ele.click();
    }

    // WebElement list of parallelMarcketCompanyWiseDataList
    public List<WebElement> parallelMarcketCompanyWiseDataList() {
        return driver.findElements(parallelMarcketCompanyWiseDataList);
    }

    // WebElement list of highestdata
    public List<WebElement> highestdata() {
        return driver.findElements(highestdata);
    }

    /*
     * This method will save the file as .jpg on the path provided in parameter by
     * thelink of image and if file will be saved then it will return true else
     * false
     */
    public boolean downloadImage(String src, String name, String path) {
        boolean result = false;
        try (InputStream in = new URL(src).openStream()) {

            Files.copy(in, Paths.get(path + name + ".png"));
            System.out.println("Image downloded Successfully");
            result = true;
        } catch (IOException e) {
            System.out.println("Error Occured during Image Downloading");
            result = false;
            e.printStackTrace();

        }
        return result;
    }

    /*
     * This method will return true if screenshot of webElement will be saved else
     * false. This method can take screenshot of scrollable element
     */
    public boolean takeScreenshot(WebElement element, String name, String path) {
        boolean result = false;
        try {
            Shutterbug.shootElement(driver, element, CaptureElement.HORIZONTAL_SCROLL).withName(name).save(path);
            result = true;
            System.out.println("Image Saved Successfully");
        } catch (Exception e) {

            System.err.println("Error saving screenshot: " + e.getMessage());
            result = false;
        }
        return result;
    }

    // This method will compare the two images will return matching percentage
    public int getImagePercentage(File fileA, File fileB) throws IOException {
        int percentage = 0;
        BufferedImage biA = ImageIO.read(fileA); // reads fileA into bufferedImage
        DataBuffer dbA = biA.getData().getDataBuffer();
        int sizeA = dbA.getSize();
        BufferedImage biB = ImageIO.read(fileB); // reads fileA into bufferedImage
        DataBuffer dbB = biB.getData().getDataBuffer();
        int sizeB = dbB.getSize();
        int count = 0;
        // compare data-buffer objects //
        if (sizeA == sizeB) { // checks the size of the both the bufferedImage

            for (int i = 0; i < sizeA; i++) {

                if (dbA.getElem(i) == dbB.getElem(i)) { // checks bufferedImage array is same in both the image
                    count = count + 1;
                }
            }
            percentage = (count * 100) / sizeA; // calculates matching percentage
        } else {
            System.out.println("Both the images are not of same size");
        }
        return percentage; // returns the matching percentage value
    }

    // Shutterbug compare method
    public boolean equalsWithDifference(String actualImagepath, String expectedImagepath, String resultingImagePath,
                                        double deviation) throws IOException {
        BufferedImage expected = ImageIO.read(new File(expectedImagepath));
        BufferedImage actual = ImageIO.read(new File(actualImagepath));
        if (expected == actual)
            return true;
        return actual != null ? ImageProcessor.imagesAreEqualsWithDiff(actual, expected, resultingImagePath, deviation)
                : expected == null;
    }
}
