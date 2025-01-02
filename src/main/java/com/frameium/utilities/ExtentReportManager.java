package com.frameium.utilities;

import com.aventstack.extentreports.AnalysisStrategy;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

import java.io.File;
import java.io.IOException;
import java.util.Date;

import static com.frameium.genericfunctions.GenericFunctions.getConfigProperty;

public class ExtentReportManager {

    private static ExtentReports extent;
    public static String screenshotFolderPath;

    public static ExtentReports getInstance(String reportPath) throws IOException {

      String platform = getConfigProperty("run.machine");

        if (extent == null) {

            if(platform.equalsIgnoreCase("jenkins")) {

                String fileName = "Report.html";
                new File(reportPath + "//ExtentReports").mkdirs();
                //new File(reportPath + "//ExtentReports//screenshots").mkdirs();
                reportPath = reportPath + "//ExtentReports//";
                //screenshotFolderPath = reportPath + "screenshots//";
                screenshotFolderPath = reportPath;
                createInstance(reportPath + fileName);

            }else {
                String fileName = "Report.html";
                Date date = new Date();
                String folderName = date.toString().replace(":", "_").replace(" ", "_");
                new File(reportPath + "//ExtentReports//" + folderName + "//screenshots").mkdirs();
                reportPath = reportPath + "//ExtentReports//" + folderName + "//";
                screenshotFolderPath = reportPath + "screenshots//";
                createInstance(reportPath + fileName);
            }

        }
        return extent;
    }


    public void writeToReport() {
        if (extent != null) {
            extent.flush();
        }
    }

    private static ExtentReports createInstance(String fileName) throws IOException {
        ExtentSparkReporter spark = new ExtentSparkReporter(fileName);
        //spark.config().setDocumentTitle("Saudi Exchange");

        spark.loadXMLConfig(System.getProperty("user.dir")+ "/src/main/resources/ConfigFiles/extent-config.xml");

        extent = new ExtentReports();
        extent.attachReporter(spark);
        extent.setAnalysisStrategy(AnalysisStrategy.SUITE);


        return extent;
    }





}
