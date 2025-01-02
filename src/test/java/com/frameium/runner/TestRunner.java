package com.frameium.runner;

import io.cucumber.testng.CucumberOptions;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;
import com.frameium.stepdef.Hooks;
import io.cucumber.testng.*;
import org.testng.annotations.*;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;
import java.io.File;

import static com.frameium.genericfunctions.GenericFunctions.getConfigProperty;





@CucumberOptions(features = {"src/test/resources/Features/Swag/LoginPage.feature"},

        glue = {"com.frameium.stepdef"},
     //   tags = "@testExecution",
        dryRun = false,
        monochrome = true

        , plugin = {"pretty", "io.qameta.allure.cucumber6jvm.AllureCucumber6Jvm","com.frameium.reportportal.attributes.CustomAttributeReporter","json:Cucumber-reports/Cucumber.json", "html:Cucumber-reports/Cucumber.html", "com.aventstack.extentreports.cucumber.adapter.ExtentCucumberAdapter:"}
        , publish = true)
//,plugin = { "pretty", "io.qameta.allure.cucumber6jvm.AllureCucumber6Jvm","com.frameium.reportportal.attributes.CustomAttributeReporter","json:target/cucumber-reports/Cucumber.json","html:target/cucumber-reports/Cucumber.html","com.aventstack.extentreports.cucumber.adapter.ExtentCucumberAdapter:" }


public class TestRunner extends Hooks {

    private TestNGCucumberRunner testNGCucumberRunner;

    @BeforeClass(alwaysRun = true)
    public void setUpClass() {
        testNGCucumberRunner = new TestNGCucumberRunner(this.getClass());
    }

    @SuppressWarnings("unused")
    @Test(groups = "cucumber", description = "Runs Cucumber Scenarios", dataProvider = "scenarios")
    public void runScenario(PickleWrapper pickleWrapper, FeatureWrapper featureWrapper) {
        // the 'featureWrapper' parameter solely exists to display the feature
        // file in a test report
        testNGCucumberRunner.runScenario(pickleWrapper.getPickle());
    }

    /**
     * Returns two dimensional array of {@link PickleWrapper}s with their
     * associated {@link FeatureWrapper}s.
     *
     * @return a two dimensional array of scenarios features.
     */
    @DataProvider
    public Object[][] scenarios() {
        if (testNGCucumberRunner == null) {
            return new Object[0][0];
        }
        return testNGCucumberRunner.provideScenarios();
    }

    @AfterClass(alwaysRun = true)
    public void tearDownClass() {
        if (testNGCucumberRunner == null) {
            return;
        }
        testNGCucumberRunner.finish();
    }


    String platform = "";

    @BeforeSuite
    public void cleanUpForJenkins() {

        platform = getConfigProperty("run.machine");

        if (platform.equalsIgnoreCase("jenkins")) {
            // Delete TestReport folder before running tests
            String destination = System.getProperty("user.dir") + "\\test-output\\TestReport";
            System.out.println("destination " + destination);
            File file = new File(destination);
            deleteFilesAndFolders(file);
        }
    }


    @AfterSuite
    public void zipTestReportForJenkins() {

        if (platform.equalsIgnoreCase("jenkins")) {

            String sourceFolderPath = System.getProperty("user.dir") + "\\test-output\\TestReport";

            File sourceParentZip = new File(System.getProperty("user.dir") + "\\TestReport");
            sourceParentZip.mkdir();

            SimpleDateFormat startDateTime = new SimpleDateFormat("MM-d-yyyy_HH-mm-ss");
            System.out.println(startDateTime);
            Date date = Calendar.getInstance().getTime();
            DateFormat dateFormat = new SimpleDateFormat("MM_d-yyyy_HH_mm_ss");
            String strDate = dateFormat.format(date);

            String zipFilePath = System.getProperty("user.dir") + "\\TestReport\\ExtentReport_" + strDate + ".zip";
            System.out.println("zipFilePath >>> " + zipFilePath);


            try {
                zipFolder(sourceFolderPath, zipFilePath);
                System.out.println("Folder zipped successfully.");
            } catch (Exception e) {
                System.out.println("Error zipping folder: " + e.getMessage());
            }
        }

    }

    public static void deleteFiles(File dirPath) {

        try {
            File filesList[] = dirPath.listFiles();

            if (filesList.length > 0) {
                for (File file : filesList) {
                    if (file.isFile() && !file.getName().equals(".ingore")) {
                        file.delete();
                    } else {
                        deleteFiles(file);
                    }
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

    }


    public static void zipFolder(String sourceFolderPath, String zipFilePath) throws IOException {
        FileOutputStream fos = new FileOutputStream(zipFilePath);
        ZipOutputStream zos = new ZipOutputStream(fos);
        File sourceFolder = new File(sourceFolderPath);
        zipFile(sourceFolder, sourceFolder.getName(), zos);
        zos.close();
        fos.close();
    }

    public static void deleteFilesAndFolders(File file) {
        if (file.isDirectory()) {
            // If the file is a directory, list all its contents and recursively delete them
            File[] files = file.listFiles();
            if (files != null) {
                for (File f : files) {
                    deleteFilesAndFolders(f);
                }
            }
        }
        // Delete the file or empty directory
        file.delete();
        System.out.println("Deleted: " + file.getAbsolutePath());
    }

    private static void zipFile(File fileToZip, String fileName, ZipOutputStream zos) throws IOException {

        try {
            if (fileToZip.isHidden()) {
                return;
            }
            if (fileToZip.isDirectory()) {
                if (fileName.endsWith("/")) {
                    zos.putNextEntry(new ZipEntry(fileName));
                    zos.closeEntry();
                } else {
                    zos.putNextEntry(new ZipEntry(fileName + "/"));
                    zos.closeEntry();
                }
                File[] children = fileToZip.listFiles();
                if (children != null) {
                    for (File childFile : children) {
                        zipFile(childFile, fileName + "/" + childFile.getName(), zos);
                    }
                }
                return;
            }
            FileInputStream fis = new FileInputStream(fileToZip);
            ZipEntry zipEntry = new ZipEntry(fileName);
            zos.putNextEntry(zipEntry);
            byte[] buffer = new byte[1024];
            int length;
            while ((length = fis.read(buffer)) >= 0) {
                zos.write(buffer, 0, length);
            }
            fis.close();

        } catch (Exception e) {
            System.out.println("Error zipping folder: " + e.getMessage());
        }


    }


}


