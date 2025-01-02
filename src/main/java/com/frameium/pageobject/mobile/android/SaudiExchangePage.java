package com.frameium.pageobject.mobile.android;

import com.frameium.genericfunctions.GenericFunctions;
import com.frameium.genericfunctions.MobileGenericFunctions;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

public class SaudiExchangePage  {

    private AndroidDriver driver;

    private MobileGenericFunctions mobileGenericFunctions;

    String destination;


    public SaudiExchangePage(AndroidDriver driver) {
        PageFactory.initElements(new AppiumFieldDecorator(driver), this);
        this.driver = driver;
        mobileGenericFunctions = new MobileGenericFunctions(driver);
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

        btnPersonalize.click();
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

        btnApplySelected.click();

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

    public static void deleteFiles(File dirPath) {
        File filesList[] = dirPath.listFiles();

        if(filesList.length > 0){
            for(File file : filesList) {
                if(file.isFile()) {
                    file.delete();
                } else {
                    deleteFiles(file);
                }
            }
        }

    }
    public void cleanUpProjectFolder(String env) throws IOException {
        //before download, delete all existing files from Download folder in mobile

        String destination = System.getProperty("user.dir")+ "\\Mobile_Downloads\\Download\\"+env+"\\Download";
        System.out.println("destination "+ destination);
        File file = new File(destination);

        //List of all files and directories
        deleteFiles(file);
        System.out.println("Files deleted........");


    }





    public void deleteAllFilesFromMobileDownloadFolder() throws IOException {
        Runtime run = Runtime.getRuntime();
        run.exec("adb shell rm -r /storage/emulated/0/Download");
    }



    public void clickOnDownloadBtnFromMarketDocument(String docName,String env) throws IOException, InterruptedException {

        List<WebElement> allli = driver.findElements(By.xpath("//div[@class='marketDocument']/ul/li/h2"));

        for (WebElement li:allli){

            String lih2 = li.getText().trim();

            if(lih2.equals(docName)){
                li.findElement(By.xpath("following-sibling::div[2]/a[2]")).click();
                System.out.println( " href >>> "+ li.findElement(By.xpath("following-sibling::div[2]/a[2]")).getAttribute("href") );
                break;
            }

        }


    }

    public void handleFileDownloadPopup() throws InterruptedException {

        switchToNativeContext();

        if(driver.findElement(By.xpath("//android.widget.CheckBox[@resource-id=\"com.android.chrome:id/show_again_checkbox\"]")).getAttribute("checked").equals("true")){
            driver.findElement(By.xpath("//android.widget.CheckBox[@resource-id=\"com.android.chrome:id/show_again_checkbox\"]")).click();
        }

        String fileName = driver.findElement(By.xpath("//android.widget.EditText[@resource-id=\"com.android.chrome:id/file_name\"]")).getText();
        System.out.println("fileName ??? " + fileName);

        driver.findElement(By.xpath("//android.widget.Button[@resource-id=\"com.android.chrome:id/positive_button\"]")).click();
        Thread.sleep(5000);

    }






    public void pullFilesFromMobileDownloadFolder(String env) throws IOException {
        Runtime run = Runtime.getRuntime();
        destination = System.getProperty("user.dir")+ "\\Mobile_Downloads\\Download\\"+env;
        File dir = new File(destination);
        dir.mkdirs();
        String cmd = "adb pull /storage/emulated/0/Download "+destination;
        System.out.println( "cmd " + cmd);
        run.exec(cmd);
        switchToWebContext();

    }




    public void extractZipFile(String env) {

        destination = System.getProperty("user.dir")+ "\\Mobile_Downloads\\Download\\"+env+"\\Download";

        System.out.println(" destination >>>> :::::: " + destination);

        File folder = new File(destination);
        File[] listOfFiles = folder.listFiles();

        for (File file : listOfFiles) {
            if (file.isFile()) {
                System.out.println(" %%%%%%%%%%%% "+ file.getName());

                unzip(file.getPath(),destination);

            }
        }

    }

    private static void unzip(String zipFilePath, String destDir) {
        File dir = new File(destDir);
        // create output directory if it doesn't exist
        if(!dir.exists()) dir.mkdirs();
        FileInputStream fis;
        //buffer for read and write data to file
        byte[] buffer = new byte[1024];
        try {
            fis = new FileInputStream(zipFilePath);
            ZipInputStream zis = new ZipInputStream(fis);
            ZipEntry ze = zis.getNextEntry();
            while(ze != null){
                String fileName = ze.getName();
                File newFile = new File(destDir + File.separator + fileName);
                System.out.println("Unzipping to >>>>>>  "+newFile.getAbsolutePath());
                //create directories for sub directories in zip
                new File(newFile.getParent()).mkdirs();
                FileOutputStream fos = new FileOutputStream(newFile);
                int len;
                while ((len = zis.read(buffer)) > 0) {
                    fos.write(buffer, 0, len);
                }
                fos.close();
                //close this ZipEntry
                zis.closeEntry();
                ze = zis.getNextEntry();
            }
            //close last ZipEntry
            zis.closeEntry();
            zis.close();
            fis.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }


    protected boolean switchToWebContext() {
        ArrayList<String> contexts = new ArrayList(driver.getContextHandles());
        for (String context : contexts) {
            System.out.println(context);
            if (context.contains("WEBVIEW")) {
                driver.context(context);
                return true;
            }
        }
        return false;
    }


    protected boolean switchToNativeContext() {
        ArrayList<String> contexts = new ArrayList(driver.getContextHandles());
        for (String context : contexts) {
            System.out.println(context);
            if (context.contains("NATIVE_APP")) {
                driver.context(context);
                return true;
            }
        }
        return false;
    }



    public void clickNewsTab(String tabName) {
        mobileGenericFunctions.scrollIntoView(driver.findElement(By.xpath("//div[@class='newsBoxTab']/ul/li/a[text()='"+tabName+"']")));
        mobileGenericFunctions.clickElement(driver.findElement(By.xpath("//div[@class='newsBoxTab']/ul/li/a[text()='"+tabName+"']")));

    }

    public String getLastNewsClassName(String tabName){

        String className = "";

        if(tabName.equals("All news")){
        className = "allNews";
        } else if (tabName.equals("Market News")) {
            className = "marketNews";
        }else if (tabName.equals("Company Announcements")) {
            className = "cmpnyAnnmnts";
        }else{
            System.out.println("<<<< Invalid class name provided.>>>> ");
        }

        return className;
    }


    public Map<Integer, String>  fetchUITextLatestNews(String selectedLatestNewsTab) {
        Map<Integer, String> uiNewsText = new HashMap<>();

        System.out.println(">>>> xpath "+ "//div[@class='newsBox']/div[@class='newsBoxTabDtl']/div[contains(@class, '"+getLastNewsClassName(selectedLatestNewsTab)+" select')]/ul");

        WebElement parent = driver.findElement(By.xpath("//div[@class='newsBox']/div[@class='newsBoxTabDtl']/div[contains(@class, '"+getLastNewsClassName(selectedLatestNewsTab)+" select')]/ul"));

        List<WebElement> listLI = parent.findElements(By.tagName("li"));

        for (int i = 0; i < listLI.size(); i++) {
            //System.out.println(">>>> ::::"+ listLI.get(i).getText() +"::::");

            String text = listLI.get(i).getText().replaceAll("\\s+", "");
            System.out.println( " Row :"+ i+ " output :::: "+ text + "::::" );

            uiNewsText.put(i,text);

        }
        System.out.println("out put map "+ uiNewsText.values());


        return uiNewsText;
    }

    public void compareTwoHashMaps(Map<Integer, String> mapMarketNews, Map<Integer, String> mapMarketNewsUAT) {

        System.out.println("are they equal? " + mapMarketNews.equals(mapMarketNewsUAT));
        Assert.assertEquals(mapMarketNews.equals(mapMarketNewsUAT), true, "Prod and UAT News text contents are different");

    }


    public boolean compareHTMLFiles(String prod, String uat) {

        String prodHtml = System.getProperty("user.dir")+ "\\Mobile_Downloads\\Download\\Prod\\Download\\"+prod;
        String uatHtml = System.getProperty("user.dir")+ "\\Mobile_Downloads\\Download\\UAT\\Download\\"+uat;

        try {

            // Read the content of the HTML files
            String actualHtml = readFileContent(prodHtml);
            String expectedHtml = readFileContent(uatHtml);
            if (actualHtml == null || expectedHtml == null) {
                System.out.println("One or both HTML files not found.");
                return false;
            }
            // Logging to debug the content of the HTML files
            System.out.println("Production HTML content:");
            System.out.println(prodHtml);
            System.out.println("UAT HTML content:");
            System.out.println(uatHtml);

            // Parse the HTML strings into Jsoup Documents
            Document prodDoc = Jsoup.parse(actualHtml);
            Document uatDoc = Jsoup.parse(expectedHtml);

            // Normalize the documents to ensure consistent formatting
            prodDoc.normalise();
            uatDoc.normalise();

            // Compare the HTML structure
            boolean areEqual = prodDoc.outerHtml().equals(uatDoc.outerHtml());

            // Log the result of the comparison
            if (areEqual) {
                System.out.println("HTML contents match.");
            } else {
                System.out.println("HTML contents do not match.");

            }

            return areEqual;
        } catch (Exception e) {
            System.err.println("Error comparing HTML files: " + e.getMessage());
            e.printStackTrace();
            return false;
        }

    }

    private String readFileContent(String filePath) throws IOException {
        StringBuilder contentBuilder = new StringBuilder();
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = br.readLine()) != null) {
                contentBuilder.append(line);
            }
        }
        return contentBuilder.toString();
    }



}
