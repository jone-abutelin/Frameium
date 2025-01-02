package com.frameium.utilities;

import com.deque.axe.AXE;
import com.frameium.accessibility.AxeDeque;
import io.cucumber.java.Scenario;
import org.json.JSONArray;
import org.json.JSONObject;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.UnsupportedEncodingException;
import java.net.URL;
import java.net.URLDecoder;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class AccessibilityReportUtils {
    private WebDriver driver;
    private static URL scriptUrl;
    private static int totalViolation = 0;
    private static int critical = 0;
    private static int serious = 0;
    private static int moderate = 0;
    private static int minor = 0;
    private static List<Integer> Severitylist = new ArrayList<>(); // Make this static
    public AccessibilityReportUtils(WebDriver driver) {
        this.driver = driver;
    }

    public static JSONArray performAccessibility(WebDriver driver) throws Exception {

        scriptUrl = AxeDeque.class.getResource("/axe.min.js");
        System.out.println("Driver object in performAccessibility method: " + driver);
        JSONObject responseJson = new AXE.Builder(driver, scriptUrl).analyze();
        JSONArray violations = responseJson.getJSONArray("violations"); //violations
        int length = violations.length();
        System.out.println(length);
        System.out.println("Printing violations...");

        System.out.println(violations.toString());

        int fixesCount, greaterfixedCount = 0;

        if (length != 0) {
            for (int j = 0; j < length; j++) {
                System.out.println("#########################################");
                String k = violations.get(j).toString();
                System.out.println(j + 1 + "-->");

                String[] arrOfStr1 = k.split("helpUrl");
//                  System.out.println(arrOfStr1[0]);
//                  System.out.println(arrOfStr1[1]);
                String[] fixes = arrOfStr1[0].split("message");
                // Fixes count

                fixesCount = fixes.length;
                System.out.println("fixes count " + fixesCount);

                if (fixesCount > greaterfixedCount)//

                {
                    greaterfixedCount = fixesCount + 3;
                }

            }

            final StringBuilder sb = new StringBuilder();
            final StringBuilder sb2 = new StringBuilder();

            sb.append("Found ").append(violations.length()).append(" accessibility violations:");
            data2 = new String[greaterfixedCount][7];
            data2[0][0] = "Violation";
            data2[0][1] = "Help URL";
            data2[0][2] = "Target";
            data2[0][3] = "Fix All of the following";
            data2[0][4] = "Fix Any of the following";

            data2[0][5] = "Severity/Impact";
            data2[0][6] = "Standard/Guidelines";

            String target = "", All_Fixes = "", Any_Fixes = "";
            int hj = violations.length();
            for (int i = 0; i < violations.length(); i++) {

                int startindex = i + 1;
                JSONObject node = null;
                JSONObject violation = violations.getJSONObject(i);
                sb.append(lineSeparator).append(i + 1).append(") ").append(violation.getString("help"));

                data2[startindex][0] = violation.getString("help");
                data2[startindex][5] = violation.getString("impact");
                data2[startindex][6] = violation.getJSONArray("tags").toString();


                JSONArray all = null;
                JSONArray none = null, any = null;
                String failuresummary = null, html = null;
                if (violation.has("helpUrl")) {
                    String helpUrl = violation.getString("helpUrl");
                    data2[startindex][1] = helpUrl;

                    sb.append(": ").append(helpUrl);
                }

                JSONArray nodes = violation.getJSONArray("nodes");
                int fd = nodes.length();
                for (int j = 0; j < nodes.length(); j++) {

                    sb2.setLength(0);
                    node = nodes.getJSONObject(j);
                    sb.append(lineSeparator).append("  ").append(getOrdinal(j + 1)).append(") ")
                            .append(node.getJSONArray("target")).append(lineSeparator);
                    target = node.getJSONArray("target").toString() + target;

                    all = node.getJSONArray("all");
                    none = node.getJSONArray("none");
                    any = node.getJSONArray("any");
                    failuresummary = node.getString("failureSummary");
                    html = node.getString("html");

                    if (all.length() > 0) {
                        appendFixes(sb2, all, "Fix all of the following:");
                        int startindex_all = sb2.indexOf("Fix all of the following:");
                        All_Fixes = sb2.substring(startindex_all + 26) + All_Fixes;

                    }
                    if (any.length() > 0) {
                        appendFixes(sb2, any, "Fix any of the following:");
                        int startindex_any = sb2.indexOf("Fix any of the following:");
                        Any_Fixes = sb2.substring(startindex_any + 26) + Any_Fixes;

                    }

                }

                data2[startindex][2] = target;

                data2[startindex][3] = All_Fixes;

                data2[startindex][4] = Any_Fixes;

//				System.out.println("====================+++++++++++++++++++++====================");
//				System.out.println(violation.getString("help"));
//				System.out.println(violation.getString("helpUrl"));
//				System.out.println(violation.getString("impact"));
//				System.out.println(violation.getJSONArray("tags").toString());
//				System.out.println(target);
//				System.out.println(All_Fixes);
//				System.out.println(Any_Fixes);
//				System.out.println("====================+++++++++++++++++++++====================");

                target = "";
                All_Fixes = "";
                Any_Fixes = "";

            }

            return violations;
            // Assert.assertTrue(false, AXE.report(violations));

        } else {
            Assert.assertTrue(true);
        }

        if (violations.length() > 0) {
            Assert.assertTrue(false, AXE.report(violations));
        }

        return violations;

    }

    /**
     * These method used for Accessibility testing
     */
    private static void appendFixes(final StringBuilder sb, final JSONArray arr, final String heading) {
        if (arr != null && arr.length() > 0) {

            sb.append("    ").append(heading).append(lineSeparator);

            for (int i = 0; i < arr.length(); i++) {
                JSONObject fix = arr.getJSONObject(i);

                sb.append("      ").append(fix.get("message")).append(lineSeparator);
            }

            sb.append(lineSeparator);
        }
    }


    static String[][] data2;
    private static final String lineSeparator = System.getProperty("line.separator");

    public static String report(final JSONArray violations) {
        final StringBuilder sb = new StringBuilder();
        sb.append("Found ").append(violations.length()).append(" accessibility violations:");

        for (int i = 0; i < violations.length(); i++) {
            JSONObject violation = violations.getJSONObject(i);
            sb.append(lineSeparator).append(i + 1).append(") ").append(violation.getString("help"));

            if (violation.has("helpUrl")) {
                String helpUrl = violation.getString("helpUrl");
                sb.append(": ").append(helpUrl);
            }

            JSONArray nodes = violation.getJSONArray("nodes");

            for (int j = 0; j < nodes.length(); j++) {
                JSONObject node = nodes.getJSONObject(j);
                sb.append(lineSeparator).append("  ").append(getOrdinal(j + 1)).append(") ")
                        .append(node.getJSONArray("target")).append(lineSeparator);

                JSONArray all = node.getJSONArray("all");
                JSONArray none = node.getJSONArray("none");

                for (int k = 0; k < none.length(); k++) {
                    all.put(none.getJSONObject(k));
                }

                appendFixes(sb, all, "Fix all of the following:");
                appendFixes(sb, node.getJSONArray("any"), "Fix any of the following:");

            }
        }

        return sb.toString();
    }

    public static List<Integer> violationSeverityList(JSONArray violationsArray) {

        JSONArray violations = violationsArray;
        JSONObject violation = null;
        int length = violations.length();
        if (length != 0) {
            totalViolation = totalViolation + length;
            for (int j = 0; j < length; j++) {
                // System.out.println("#########################################");
                String k = violations.get(j).toString();
                // System.out.println(j + 1 + "-->");

                String[] arrOfStr1 = k.split("helpUrl");

                String[] fixes = arrOfStr1[0].split("message");
                // Fixes count

            }

            final StringBuilder sb = new StringBuilder();
            final StringBuilder sb2 = new StringBuilder();

            sb.append("Found ").append(violations.length()).append(" accessibility violations:");
            // data2 = new String[greaterfixedCount][7];
            String[][] data2 = new String[violations.length() + 1][7]; // Initialize data2 array
            data2[0][0] = "Violation";
            data2[0][1] = "Help URL";
            data2[0][2] = "Target";
            data2[0][3] = "Fix All of the following";
            data2[0][4] = "Fix Any of the following";

            data2[0][5] = "Severity/Impact";
            data2[0][6] = "Standard/Guidelines";

            String target = "", All_Fixes = "", Any_Fixes = "";
            for (int i = 0; i < violations.length(); i++) {

                int startindex = i + 1;
                JSONObject node = null;
                violation = violations.getJSONObject(i);
                // sb.append(lineSeparator).append(i + 1).append(")
                // ").append(violation.getString("help"));

                // data2[startindex][0] = violation.getString("help");
                data2[startindex][5] = violation.getString("impact");
                // data2[startindex][6] = violation.getJSONArray("tags").toString();

                JSONArray all = null;
                JSONArray none = null, any = null;
                if (violation.has("helpUrl")) {
                    String helpUrl = violation.getString("helpUrl");
                    data2[startindex][1] = helpUrl;

                    sb.append(": ").append(helpUrl);
                }

                JSONArray nodes = violation.getJSONArray("nodes");

                for (int j = 0; j < nodes.length(); j++) {

                    sb2.setLength(0);
                    node = nodes.getJSONObject(j);
                    sb.append(lineSeparator).append("  ").append(getOrdinal(j + 1)).append(") ")
                            .append(node.getJSONArray("target")).append(lineSeparator);
                    target = node.getJSONArray("target").toString() + target;

                    all = node.getJSONArray("all");
                    none = node.getJSONArray("none");
                    any = node.getJSONArray("any");

                    if (all.length() > 0) {
                        appendFixes(sb2, all, "Fix all of the following:");
                        int startindex_all = sb2.indexOf("Fix all of the following:");
                        All_Fixes = sb2.substring(startindex_all + 26) + All_Fixes;

                        All_Fixes = "<br>" + All_Fixes;

                    }
                    if (any.length() > 0) {
                        appendFixes(sb2, any, "Fix any of the following:");
                        int startindex_any = sb2.indexOf("Fix any of the following:");
                        Any_Fixes = sb2.substring(startindex_any + 26) + Any_Fixes;

                        Any_Fixes = "<br>" + Any_Fixes;

                        // Any_Fixes = "<br>"+Any_Fixes;

                    }

                }

                data2[startindex][2] = target;

                data2[startindex][3] = All_Fixes;

                data2[startindex][4] = Any_Fixes;

                System.out.println("full violation severity list ........." + data2[0][5]);
                String severity = violation.getString("impact");
                System.out.println(violation.getString("impact"));
                if (severity.equalsIgnoreCase("serious")) {
                    serious++;
                } else if (severity.equalsIgnoreCase("critical")) {
                    critical++;
                } else if (severity.equalsIgnoreCase("moderate")) {
                    moderate++;
                } else if (severity.equalsIgnoreCase("minor")) {
                    minor++;
                }

            }
            Severitylist.add(0, critical);
            Severitylist.add(1, serious);
            Severitylist.add(2, moderate);
            Severitylist.add(3, minor);

            System.out.println("values of the list");

            System.out.println(Severitylist.get(0));
            System.out.println(Severitylist.get(1));
            System.out.println(Severitylist.get(2));
            System.out.println(Severitylist.get(3));

        }
//		System.out.println("full violation severity list ........."+data2[0][5]);
//		System.out.println(violation.getString("impact"));
        System.out.println(Severitylist);
        return Severitylist;

    }

    private static String getOrdinal(int number) {
        String ordinal = "";

        int mod;

        while (number > 0) {
            mod = (number - 1) % 26;
            ordinal = (char) (mod + 97) + ordinal;
            number = (number - mod) / 26;
        }

        return ordinal;
    }

    /**
     * This method will generate report for Accessibility
     */
    public void createAccessibilityReport(JSONArray violationsArray, int indexCount, int passedCount, int failedCount,
                                          int skippedCount, int sevCritical, int sevSerious, int sevModerate, int sevMinor, int totalViolations, String url, WebDriver driver)
            throws Exception {
        int totalVio = violationsArray.length();
        driver.navigate().back();
        //String url = getCurrentUrl();
        System.out.println(url);
        SimpleDateFormat startDateTime = new SimpleDateFormat("MM-d-yyyy_HH-mm-ss");
        System.out.println(startDateTime);
        Date date = Calendar.getInstance().getTime();
        DateFormat dateFormat = new SimpleDateFormat("MM-d-yyyy_HH-mm-ss");
        String strDate = dateFormat.format(date);

        String fixesAll = "";
        String fixesAll2 = "";
        String finalAllFixes = "";
        String reportpath = System.getProperty("user.dir");
        String fileName = getFileNameFromUrl(driver.getCurrentUrl());
        //String resultFile = reportpath + "\\Reports\\accessibilitycustomreport_" + strDate + ".html";
        // Construct the full path and file name for the report file
        String resultFile = reportpath + "\\Reports\\accessibilitycustomreport_" + fileName + "_" +strDate +".html";
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
        if (indexCount == 1) {
            // BufferedWriter bw = new BufferedWriter(new FileWriter(file, true));
            bw.write("<!DOCTYPE HTML>" + "\n");
            bw.write("<html>" + "\n");

            bw.write("<style>" + "\n");

            // adding new codes 06/01/23
            bw.write(".triangle-right {" + "\n");
            bw.write("width: 0;" + "\n");
            bw.write("height: 0;" + "\n");

            bw.write(";border-left: 1450px solid white;" + "\n");
            bw.write(";border-bottom: 250px solid transparent;" + "\n");
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
            bw.write(".triangle-bottomright {" + "\n");
            bw.write("width: 0;" + "\n");
            bw.write("height: 0;" + "\n");
            bw.write("border-top: 150px solid transparent;" + "\n");
            bw.write("border-right: 850px solid #d3003f;" + "\n");
            bw.write("border-bottom: 4px solid transparent;" + "\n");
            bw.write("position: absolute;" + "\n");
            bw.write("right: 0px;" + "\n");
            bw.write("bottom: 0px;" + "\n");
            bw.write("}" + "\n");
            bw.write(".triangle-bottomleft {" + "\n");
            bw.write("width: 0;" + "\n");
            bw.write("height: 0px;" + "\n");
            bw.write("border-left: 1px solid transparent;" + "\n");
            bw.write("border-right: 850px solid transparent;" + "\n");
            bw.write("border-bottom: 200px solid #d3003f;" + "\n");
            bw.write("}" + "\n");
            bw.write(".logo{" + "\n");
            bw.write("position: absolute;" + "\n");
            bw.write("z-index: 1;" + "\n");
            bw.write("top: 30px;" + "\n");

            bw.write("}" + "\n");
            bw.write("body{" + "\n");

            bw.write("background-color: #1f1f1f;" + "\n");
            bw.write("background-repeat: no-repeat;" + "\n");
            bw.write("background-attachment: fixed;" + "\n");
            bw.write("background-size: cover;" + "\n");
            bw.write("}" + "\n");

            bw.write(".canvasjs-chart-credit{" + "\n");
            bw.write("content-visibility: hidden;" + "\n");
            bw.write("backface-visibility: hidden;" + "\n");
            bw.write("}" + "\n");
            bw.write("table {" + "\n");
            bw.write("border-collapse: collapse;" + "\n");
            bw.write("font-family: Tahoma, Geneva, sans-serif;" + "\n");
            bw.write("}" + "\n");
            bw.write("table td {" + "\n");
            bw.write("padding: 15px;" + "\n");
            bw.write("}" + "\n");
            bw.write("table th {" + "\n");
            bw.write("background-color: #db4e44;" + "\n");
            bw.write("}" + "\n");
            bw.write("able thead td {" + "\n");
            bw.write("background-color: #32CD32;" + "\n");
            bw.write("color: #ffffff;" + "\n");
            bw.write("font-weight: bold;" + "\n");
            bw.write("font-size: 13px;" + "\n");
            bw.write("border: 1px solid #54585d;" + "\n");
            bw.write("}" + "\n");
            bw.write("table tbody td {" + "\n");
            bw.write("color: #636363;" + "\n");
            bw.write("border: 1px solid #dddfe1;" + "\n");
            bw.write("}" + "\n");
            bw.write("table tbody tr {" + "\n");
            bw.write("background-color: #f9fafb;" + "\n");
            bw.write("}" + "\n");
            bw.write("table tbody tr:nth-child(odd) {" + "\n");
            bw.write("background-color: #ffffff;" + "\n");
            bw.write("}" + "\n");

            bw.write("h1 {" + "\n");
            bw.write("color: white;" + "\n");
            bw.write("font-family: Tahoma, Geneva, sans-serif;" + "\n");
            bw.write("font-size: 250%;" + "\n");
            bw.write("}" + "\n");

            bw.write(".skill-bars{width: 350px;background: transparent;border-radius: 10px;}" + "\n");
            bw.write(".skill-bars .bar{margin: 20px 0;}" + "\n");
            bw.write(".skill-bars .bar:first-child{margin-top: 0px;}" + "\n");
            bw.write(".skill-bars .bar .info{margin-bottom: 5px;}" + "\n");
            bw.write(
                    ".skill-bars .bar .info span{font-weight: 500;font-size: 17px;opacity: 0;animation: showText 0.5s 1s linear forwards;color: white;}"
                            + "\n");
            bw.write("@keyframes showText {100%{opacity: 1;}}" + "\n");
            bw.write(
                    ".skill-bars .bar .progress-line{height: 10px;width: 100%;background: #f0f0f0;position: relative;transform: scaleX(0);transform-origin: left;border-radius: 10px;box-shadow: inset 0 1px 1px rgba(0,0,0,0.05),0 1px rgba(255,255,255,0.8);animation: animate 1s cubic-bezier(1,0,0.5,1) forwards;}"
                            + "\n");
            bw.write("@keyframes animate {100%{transform: scaleX(1);}}" + "\n");
            bw.write(
                    ".bar .progress-line span{height: 100%;position: absolute;border-radius: 10px;transform: scaleX(0);transform-origin: left;animation: animate 1s 1s cubic-bezier(1,0,0.5,1) forwards;}"
                            + "\n");

            bw.write(".bar .progress-line.critical span{width: " + sevCritical * 10 + "px;background: red;}" + "\n");
            bw.write(".bar .progress-line.serious span{width: " + sevSerious * 10 + "px;background: orange;}" + "\n");
            bw.write(".bar .progress-line.moderate span{width: " + sevModerate * 10 + "px;background: yellow;}" + "\n");
            bw.write(".bar .progress-line.minor span{width: " + sevMinor * 10 + "px;background: blue;}" + "\n");

            bw.write(
                    ".progress-line span::before{position: absolute;content: \"\";top: -10px;right: 0;height: 0;width: 0;border: 7px solid transparent;border-bottom-width: 0px;border-right-width: 0px;border-top-color: #000;opacity: 0;animation: showText2 0.5s 1.5s linear forwards;}"
                            + "\n");
            bw.write(
                    ".progress-line span::after{position: absolute;top: -28px;right: -200px;font-weight: 500;background: white;color: black;padding: 1px 8px;font-size: 12px;border-radius: 3px;opacity: 0;animation: showText2 0.5s 1.5s linear forwards;}"
                            + "\n");
            bw.write("@keyframes showText2 {100%{opacity: 1;}}" + "\n");

            String criticalSevValue = Integer.toString(sevCritical);
            String seriousSevValue = Integer.toString(sevSerious);
            String moderateSevValue = Integer.toString(sevModerate);
            String minorSevValue = Integer.toString(sevMinor);

            bw.write(".progress-line.critical span::after{content: \"" + criticalSevValue + "\";}" + "\n");
            bw.write(".progress-line.serious span::after{content: \"" + seriousSevValue + "\";}" + "\n");
            bw.write(".progress-line.moderate span::after{content: \"" + moderateSevValue + "\";}" + "\n");
            bw.write(".progress-line.minor span::after{content: \"" + minorSevValue + "\";}" + "\n");

            // summary
            bw.write(".bloc3" + "\n");
            bw.write("{" + "\n");

            bw.write("width: 28%;" + "\n");
            bw.write("color: white;" + "\n");
            bw.write("float: left;" + "\n");
            bw.write("height:360px;" + "\n");
            bw.write("padding-bottom: 10px;" + "\n");
            bw.write("padding: 25px 30px;" + "\n");
            bw.write("background-color: transparent;" + "\n");
            bw.write("font-family: Tahoma, Geneva, sans-serif;" + "\n");
            bw.write("border-radius: 10px;" + "\n");
            bw.write("border-style: double;" + "\n");
            bw.write("border-color: white;" + "\n");
            bw.write("}" + "\n");

            // bar graph
            bw.write(".bloc1" + "\n");
            bw.write("{" + "\n");

            bw.write("width: 28%;" + "\n");
            bw.write("height:360px;" + "\n");
            bw.write("float: left;" + "\n");
            bw.write("padding-bottom: 10px;" + "\n");
            bw.write("padding: 25px 30px;" + "\n");
            bw.write("border-radius: 10px;" + "\n");
            bw.write("border-style: double;" + "\n");
            bw.write("border-color: white;" + "\n");
            bw.write("}" + "\n");
            // canvas chart
            bw.write(".bloc2" + "\n");
            bw.write("{" + "\n");
            bw.write("background-color: transparent; " + "\n");
            bw.write("width: 30%;" + "\n");
            bw.write("height:360px;" + "\n");
            bw.write("float:left;" + "\n");
            bw.write("padding-bottom: 10px;" + "\n");
            bw.write("overflow: hidden;" + "\n");
            bw.write("z-index:1;" + "\n");
            bw.write("border-style: double;" + "\n");
            bw.write("border-color: white;" + "\n");
            bw.write("padding: 25px 30px;" + "\n");
            bw.write("border-radius: 10px;" + "\n");
            bw.write("}" + "\n");

            bw.write(".dummy" + "\n");
            bw.write("{" + "\n");
            bw.write("width: 55%;" + "\n");
            bw.write("height:100px;" + "\n");
            bw.write("float:left;" + "\n");
            bw.write("background-color: #100014;" + "\n");
            bw.write("top: -20px;" + "\n");
            bw.write("z-index:2;" + "\n");
            bw.write("position: relative;" + "\n");
            bw.write("}" + "\n");

            bw.write("h3{" + "\n");
            bw.write("color: white;" + "\n");
            bw.write("font-family: Tahoma, Geneva, sans-serif;" + "\n");
            bw.write("}" + "\n");
            // bw.write(".pagecount span::after{content: \"5\";}"+"\n");
            // bw.write(".violationscount span::after{content: \"15\";}"+"\n");
            bw.write(".pagecount span::after{content: \"" + indexCount + "\";}" + "\n");
            bw.write(".violationscount span::after{content: \"" + totalViolations + "\";}" + "\n");

            bw.write("</style>" + "\n");
            bw.write("<head>" + "\n");

            bw.write("<div class=\"triangle-right\"></div>" + "\n");
            bw.write(
                    "<div class=\"logo\"><img src=\"https://www.testhouse.net/wp-content/uploads/2021/08/LOGO_Testhouse.png\" width=\"350\" alt=\"My Image\" /></div>"
                            + "\n");
            bw.write("<div class=\"triangle-left\" style=\"float:right;\"></div>" + "\n");

            bw.write("<h1 align =\"center\">Accessibility Violation Test Report </h1><br>" + "\n");

            bw.write("<div class=\"bloc3\">" + "\n");
            bw.write("<h3>Summary</h3>" + "\n");
            // date here
            Date d1 = new Date();
            // bw.write("<ul><li>Reviewed on:&nbsp; Mon May 17 19.00.00 IST 2021
            // </span></li><br>"+"\n");
            bw.write("<ul><li>Reviewed on:&nbsp;  " + d1 + " </span></li><br>" + "\n");
            // url here
            bw.write("<li>Analyzed Page URL: &nbsp;" + driver.getCurrentUrl() + " </span></li><br>" + "\n");
            // index count here
            // bw.write("<li>Total Pages Accessed:&nbsp; 3</span></li><br>"+"\n");
            // Total ule violation here
            // bw.write("<li>Total Rule Violations:&nbsp; 15</span></li></ul><br>"+"\n");

            bw.write(
                    "<li>Total Pages Accessed:&nbsp;<div class=\"pagecount\" style=\"display:inline;\"><span></span></div></li><br>"
                            + "\n");
            bw.write(
                    "<li>Total Rule Violations:&nbsp; <div class=\"violationscount\" style=\"display:inline;\"><span></span></div></li></ul><br>"
                            + "\n");

            bw.write("</div>" + "\n");

            bw.write("<div class=\"bloc1\"><div class=\"skill-bars\">" + "\n");
            bw.write("<h3>Accessibility Violation by Severity</h3>" + "\n");
            bw.write("<div class=\"bar\">" + "\n");
            bw.write("<div class=\"info\">" + "\n");
            bw.write("<span>Critical</span>" + "\n");
            bw.write("</div>" + "\n");
            bw.write("<div class=\"progress-line critical\">" + "\n");
            bw.write("<span></span>" + "\n");
            bw.write("</div>" + "\n");
            bw.write("</div>" + "\n");
            bw.write("<div class=\"bar\">" + "\n");
            bw.write("<div class=\"info\">" + "\n");
            bw.write("<span>Serious</span>" + "\n");
            bw.write("</div>" + "\n");
            bw.write("<div class=\"progress-line serious\">" + "\n");
            bw.write("<span></span>" + "\n");
            bw.write("</div>" + "\n");
            bw.write("</div>" + "\n");
            bw.write("<div class=\"bar\">" + "\n");
            bw.write("<div class=\"info\">" + "\n");
            bw.write("<span>Moderate</span>" + "\n");
            bw.write("</div>" + "\n");
            bw.write("<div class=\"progress-line moderate\">" + "\n");
            bw.write("<span></span>" + "\n");
            bw.write("</div>" + "\n");
            bw.write("</div>" + "\n");
            bw.write("<div class=\"bar\">" + "\n");
            bw.write("<div class=\"info\">" + "\n");
            bw.write("<span>Minor</span>" + "\n");
            bw.write("</div>" + "\n");
            bw.write("<div class=\"progress-line minor\">" + "\n");
            bw.write("<span></span>" + "\n");
            bw.write("</div>" + "\n");
            bw.write("</div>" + "\n");
            bw.write("</div>" + "\n");
            bw.write("</div></div>" + "\n");

            bw.write("<div class=\"bloc2\">" + "\n");
            bw.write("<h3>Overall Accessibility Coverage</h3>" + "\n");
            bw.write("<div id=\"chartContainer\" style=\"height: 350px; width:55%;\">" + "\n");
            bw.write("</div>" + "\n");
            bw.write("<div class=\"dummy\"></div>" + "\n");
            bw.write("</div>" + "\n");
            bw.write("<br>" + "\n");

            // 2/1/2023 edited
            bw.write("<br>" + "\n");
            bw.write("<br>" + "\n");
            bw.write("<br>" + "\n");

            int p = passedCount;
            int f = failedCount;
            int s = skippedCount;

            bw.write("<script type=\"text/javascript\">" + "\n");

            bw.write("window.onload = function () {" + "\n");

            bw.write("var chart = new CanvasJS.Chart(\"chartContainer\"," + "\n");
            bw.write("{" + "\n");
            /* removed from script and added new 04/01/20223 */
            bw.write("backgroundColor: \"transparent\"," + "\n");
            bw.write("legend:{" + "\n");
            bw.write("fontColor: 'white'," + "\n");
            bw.write("verticalAlign: \"top\"," + "\n");
            bw.write("horizontalAlign: \"center\"," + "\n");
            bw.write("fontstyle: 'Tahoma, Geneva, sans-serif'" + "\n");
            bw.write("}," + "\n");
            /* end */
            bw.write("data: [" + "\n");
            bw.write("{" + "\n");
            // startAngle: 45,
            bw.write("type: \"doughnut\"," + "\n");
            bw.write("showInLegend: true," + "\n");
            bw.write("dataPoints: [" + "\n");
//				bw.write("{  y: 5, legendText:\"Passed 5\", color: \"Green\"},"+"\n");
//				bw.write("{  y: 3, legendText:\"Failed 3\", color: \"#d3003f\" },"+"\n");
//				bw.write("{  y: 2, legendText:\"Skipped 2\", color: \"Yellow\" }"+"\n");
            bw.write("{  y: " + p + ", legendText:\"Passed " + p + "\"" + ", color: \"Green\"}," + "\n");
            bw.write("{  y: " + f + ", legendText:\"Failed " + f + "\"" + ", color: \"Red\" }," + "\n");
            bw.write("{  y: " + s + ", legendText:\"Skipped " + s + "\"" + ", color: \"Yellow\" }" + "\n");
            bw.write("]" + "\n");
            bw.write("}" + "\n");
            bw.write("]" + "\n");
            bw.write("});" + "\n");
            bw.write("chart.render();" + "\n");
            bw.write("}" + "\n");
            bw.write("</script>" + "\n");

            bw.write(
                    "<script type=\"text/javascript\" src=\"https://canvasjs.com/assets/script/canvasjs.min.js\"></script></head>"
                            + "\n");
            bw.write("<body>" + "\n");

            bw.write("<br> <br> <br>" + "\n");
            //String pageUrl = driver.getTitle();
            String pageUrl = driver.getCurrentUrl();
            bw.write("<h3>PageUrl::" + pageUrl + "</h3>");
            // start of table
            bw.write("<table align =\"center\">" + "\n");
            // heading row
            bw.write("<tr>" + "\n");
            bw.write("<th>Sl.No</th>" + "\n");
            bw.write("<th>Violations</th>" + "\n");
            bw.write("<th>Standards Violated</th>" + "\n");
            bw.write("<th>Severity</th>" + "\n");
            // bw.write("<th>Page url</th>"+"\n");
            bw.write("<th>Help URL</th>" + "\n");
            bw.write("<th>Fixes</th>" + "\n"); //--> removing for now
            bw.write("</tr>" + "\n");
            // heading row closed

            JSONArray violations = violationsArray;

            int fixesCount, greaterfixedCount = 0;
            int length = violations.length();
            int slNo = 0;
            String standards = "";
            if (length != 0) {
                for (int j = 0; j < length; j++) {
                    // System.out.println("#########################################");
                    String k = violations.get(j).toString();
                    // System.out.println(j + 1 + "-->");

                    String[] arrOfStr1 = k.split("helpUrl");

                    String[] fixes = arrOfStr1[0].split("message");
                    // Fixes count

                    fixesCount = fixes.length;
                    System.out.println("fixes count " + fixesCount);

                    if (fixesCount > greaterfixedCount)//

                    {
                        greaterfixedCount = fixesCount + 3;
                    }

                }

                final StringBuilder sb = new StringBuilder();
                final StringBuilder sb2 = new StringBuilder();

                sb.append("Found ").append(violations.length()).append(" accessibility violations:");
                data2 = new String[greaterfixedCount][7];
                data2[0][0] = "Violation";
                data2[0][1] = "Help URL";
                data2[0][2] = "Target";
                data2[0][3] = "Fix All of the following";
                data2[0][4] = "Fix Any of the following";

                data2[0][5] = "Severity/Impact";
                data2[0][6] = "Standard/Guidelines";

                String target = "", All_Fixes = "", Any_Fixes = "";
                for (int i = 0; i < violations.length(); i++) {

                    int startindex = i + 1;
                    JSONObject node = null;
                    JSONObject violation = violations.getJSONObject(i);
                    sb.append(lineSeparator).append(i + 1).append(") ").append(violation.getString("help"));

                    data2[startindex][0] = violation.getString("help");
                    data2[startindex][5] = violation.getString("impact");
                    data2[startindex][6] = violation.getJSONArray("tags").toString();

                    JSONArray all = null;
                    JSONArray none = null, any = null;
                    if (violation.has("helpUrl")) {
                        String helpUrl = violation.getString("helpUrl");
                        data2[startindex][1] = helpUrl;

                        sb.append(": ").append(helpUrl);
                    }

                    JSONArray nodes = violation.getJSONArray("nodes");

                    for (int j = 0; j < nodes.length(); j++) {

                        sb2.setLength(0);
                        node = nodes.getJSONObject(j);
                        sb.append(lineSeparator).append("  ").append(getOrdinal(j + 1)).append(") ")
                                .append(node.getJSONArray("target")).append(lineSeparator);
                        target = node.getJSONArray("target").toString();
                        //+ target;

                        all = node.getJSONArray("all");
                        none = node.getJSONArray("none");
                        any = node.getJSONArray("any");

                        //failuresummary = node.getString("failureSummary");//new add
                        //html = node.getString("html");

                        if (all.length() > 0) {
                            appendFixes(sb2, all, "Fix all of the following:");
                            int startindex_all = sb2.indexOf("Fix all of the following:");
                            //All_Fixes = sb2.substring(startindex_all + 26) + All_Fixes;
                            Any_Fixes = sb2.substring(startindex_all + 26) + "<br>" + "Target Element " + target + Any_Fixes;

                            All_Fixes = "<br>" + All_Fixes;

                        }
                        if (any.length() > 0) {
                            appendFixes(sb2, any, "Fix any of the following:");
                            int startindex_any = sb2.indexOf("Fix any of the following:");
                            Any_Fixes = sb2.substring(startindex_any + 26) + "<br>" + "Target Element " + target + Any_Fixes;
                            Any_Fixes = "<br>" + Any_Fixes;

                            // Any_Fixes = "<br>"+Any_Fixes;

                        }

                    }

                    data2[startindex][2] = target;

                    data2[startindex][3] = All_Fixes;

                    data2[startindex][4] = Any_Fixes;

//						System.out.println("====================+++++++++++++++++++++====================");
//						System.out.println(violation.getString("help"));
//						System.out.println(violation.getString("helpUrl"));
//						System.out.println(violation.getString("impact"));
//						System.out.println(violation.getJSONArray("tags").toString());
//						System.out.println(target);
//						System.out.println(All_Fixes);
//						System.out.println(Any_Fixes);
//						System.out.println("<<<<<<<<<<<<<<<<<<<<<<<<<<");

                    fixesAll2 = "<br>" + fixesAll2 + All_Fixes + "\n";

                    fixesAll = "<br>" + fixesAll + Any_Fixes + "\n";

                    if (All_Fixes.isBlank() && Any_Fixes.isBlank()) {
                        finalAllFixes = "";
                    }
                    if (All_Fixes.isBlank() == false && Any_Fixes.isBlank()) {
                        finalAllFixes = "Fix all of the following : \n" + fixesAll2;
                    }
                    if (All_Fixes.isBlank() && Any_Fixes.isBlank() == false) {
                        finalAllFixes = "Fix any of the following :\n" + fixesAll;
                    }
                    if (All_Fixes.isBlank() == false && Any_Fixes.isBlank() == false) {
                        finalAllFixes = "Fix all of the following : \n" + fixesAll2 + "Fix any of the following :\n"
                                + fixesAll;
                    }
                    // finalAllFixes = "Fix all of the following : \n"+fixesAll2 + "Fix any of the
                    // following :\n"+fixesAll;
//						System.out.println(Any_Fixes = "\n" + Any_Fixes + "\n");
//						System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>");
//						System.out.println("====================+++++++++++++++++++++====================");

                    slNo = slNo + 1;
                    bw.write("<tr>" + "\n");
                    bw.write("<td>" + slNo + ".</td>" + "\n");
                    // bw.write("<td>Elements must have sufficient color contrast</td>"+"\n");
                    bw.write("<td>" + violation.getString("help") + "</td>" + "\n");
//						bw.write("<td>"
//								+ "\"cat.forms\",\"wcag2a\",\"wcag332\",\"wcag131\",\"section508\",\"section508.22.n\""
//								+ ".</td>" + "\n");
                    Boolean flag = false;
                    String items = "";
                    for (int m = 1; m < violation.getJSONArray("tags").length(); m++) {
                        items = violation.getJSONArray("tags").getString(m);
                        if (m == 1) {
                            standards = standards + items;
                        } else {
                            standards = standards + "," + items;
                        }

                        if (m == violation.getJSONArray("tags").length() - 1) {
                            flag = true;
                        }
                    }
                    // System.out.println("****************************************************************"+standards);
                    standards = standards.toUpperCase();
                    // bw.write("<td>" + violation.getJSONArray("tags").toString() + "</td>" +
                    // "\n");
                    bw.write("<td>" + standards + "</td>" + "\n");
                    // bw.write("<td>" + violation.getString("impact") + "\n");
                    String impact = violation.getString("impact");
                    impact = impact.substring(0, 1).toUpperCase() + impact.substring(1);
                    // String res = s.substring(0, 1).toUpperCase() + s.substring(1);
                    // bw.write("<td>" + violation.getString("impact") + "\n");
                    bw.write("<td>" + impact + "\n");

                    bw.write("<td><a href=" + violation.getString("helpUrl") + ">Click here</a> </td>" + "\n");
                    bw.write("<td>" + finalAllFixes + "\n"); //--> removing for now

                    bw.write("</td>" + "\n");
                    bw.write("</tr>" + "\n");
//						System.out.println("*********************************");
//						System.out.println(fixesAll);
//						System.out.println("*********************************");
                    target = "";
                    All_Fixes = "";
                    Any_Fixes = "";

                    fixesAll = "";
                    if (flag == true) {
                        standards = "";
                    }

                }

                bw.write("</table>" + "\n");
                bw.write("</body>" + "\n");
                bw.write("</html>" + "\n");

            } else {
                Assert.assertTrue(true);
            }

        }

        if (indexCount > 1) {

            bw.write("<!DOCTYPE HTML>" + "\n");
            bw.write("<html>" + "\n");

            bw.write("<style>" + "\n");

            // adding new codes 06/01/23
            bw.write(".triangle-right {" + "\n");
            bw.write("width: 0;" + "\n");
            bw.write("height: 0;" + "\n");

            bw.write(";border-left: 1450px solid white;" + "\n");
            bw.write(";border-bottom: 250px solid transparent;" + "\n");
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
            bw.write(".triangle-bottomright {" + "\n");
            bw.write("width: 0;" + "\n");
            bw.write("height: 0;" + "\n");
            bw.write("border-top: 150px solid transparent;" + "\n");
            bw.write("border-right: 850px solid #d3003f;" + "\n");
            bw.write("border-bottom: 4px solid transparent;" + "\n");
            bw.write("position: absolute;" + "\n");
            bw.write("right: 0px;" + "\n");
            bw.write("bottom: 0px;" + "\n");
            bw.write("}" + "\n");
            bw.write(".triangle-bottomleft {" + "\n");
            bw.write("width: 0;" + "\n");
            bw.write("height: 0px;" + "\n");
            bw.write("border-left: 1px solid transparent;" + "\n");
            bw.write("border-right: 850px solid transparent;" + "\n");
            bw.write("border-bottom: 200px solid #d3003f;" + "\n");
            bw.write("}" + "\n");
            bw.write(".logo{" + "\n");
            bw.write("position: absolute;" + "\n");
            bw.write("z-index: 1;" + "\n");
            bw.write("top: 30px;" + "\n");

            bw.write("}" + "\n");
            bw.write("body{" + "\n");

            bw.write("background-color: #1f1f1f;" + "\n");
            bw.write("background-repeat: no-repeat;" + "\n");
            bw.write("background-attachment: fixed;" + "\n");
            bw.write("background-size: cover;" + "\n");
            bw.write("}" + "\n");

            bw.write(".canvasjs-chart-credit{" + "\n");
            bw.write("content-visibility: hidden;" + "\n");
            bw.write("backface-visibility: hidden;" + "\n");
            bw.write("}" + "\n");
            bw.write("table {" + "\n");
            bw.write("border-collapse: collapse;" + "\n");
            bw.write("font-family: Tahoma, Geneva, sans-serif;" + "\n");
            bw.write("}" + "\n");
            bw.write("table td {" + "\n");
            bw.write("padding: 15px;" + "\n");
            bw.write("}" + "\n");
            bw.write("table th {" + "\n");
            bw.write("background-color: #db4e44;" + "\n");
            bw.write("}" + "\n");
            bw.write("able thead td {" + "\n");
            bw.write("background-color: #32CD32;" + "\n");
            bw.write("color: #ffffff;" + "\n");
            bw.write("font-weight: bold;" + "\n");
            bw.write("font-size: 13px;" + "\n");
            bw.write("border: 1px solid #54585d;" + "\n");
            bw.write("}" + "\n");
            bw.write("table tbody td {" + "\n");
            bw.write("color: #636363;" + "\n");
            bw.write("border: 1px solid #dddfe1;" + "\n");
            bw.write("}" + "\n");
            bw.write("table tbody tr {" + "\n");
            bw.write("background-color: #f9fafb;" + "\n");
            bw.write("}" + "\n");
            bw.write("table tbody tr:nth-child(odd) {" + "\n");
            bw.write("background-color: #ffffff;" + "\n");
            bw.write("}" + "\n");

            bw.write("h1 {" + "\n");
            bw.write("color: white;" + "\n");
            bw.write("font-family: Tahoma, Geneva, sans-serif;" + "\n");
            bw.write("font-size: 250%;" + "\n");
            bw.write("}" + "\n");

            bw.write(".skill-bars{width: 350px;background: transparent;border-radius: 10px;}" + "\n");
            bw.write(".skill-bars .bar{margin: 20px 0;}" + "\n");
            bw.write(".skill-bars .bar:first-child{margin-top: 0px;}" + "\n");
            bw.write(".skill-bars .bar .info{margin-bottom: 5px;}" + "\n");
            bw.write(
                    ".skill-bars .bar .info span{font-weight: 500;font-size: 17px;opacity: 0;animation: showText 0.5s 1s linear forwards;color: white;}"
                            + "\n");
            bw.write("@keyframes showText {100%{opacity: 1;}}" + "\n");
            bw.write(
                    ".skill-bars .bar .progress-line{height: 10px;width: 100%;background: #f0f0f0;position: relative;transform: scaleX(0);transform-origin: left;border-radius: 10px;box-shadow: inset 0 1px 1px rgba(0,0,0,0.05),0 1px rgba(255,255,255,0.8);animation: animate 1s cubic-bezier(1,0,0.5,1) forwards;}"
                            + "\n");
            bw.write("@keyframes animate {100%{transform: scaleX(1);}}" + "\n");
            bw.write(
                    ".bar .progress-line span{height: 100%;position: absolute;border-radius: 10px;transform: scaleX(0);transform-origin: left;animation: animate 1s 1s cubic-bezier(1,0,0.5,1) forwards;}"
                            + "\n");

            bw.write(".bar .progress-line.critical span{width: " + sevCritical * 10 + "px;background: red;}" + "\n");
            bw.write(".bar .progress-line.serious span{width: " + sevSerious * 10 + "px;background: orange;}" + "\n");
            bw.write(".bar .progress-line.moderate span{width: " + sevModerate * 10 + "px;background: yellow;}" + "\n");
            bw.write(".bar .progress-line.minor span{width: " + sevMinor * 10 + "px;background: blue;}" + "\n");

            bw.write(
                    ".progress-line span::before{position: absolute;content: \"\";top: -10px;right: 0;height: 0;width: 0;border: 7px solid transparent;border-bottom-width: 0px;border-right-width: 0px;border-top-color: #000;opacity: 0;animation: showText2 0.5s 1.5s linear forwards;}"
                            + "\n");
            bw.write(
                    ".progress-line span::after{position: absolute;top: -28px;right: -200px;font-weight: 500;background: white;color: black;padding: 1px 8px;font-size: 12px;border-radius: 3px;opacity: 0;animation: showText2 0.5s 1.5s linear forwards;}"
                            + "\n");
            bw.write("@keyframes showText2 {100%{opacity: 1;}}" + "\n");

            String criticalSevValue = Integer.toString(sevCritical);
            String seriousSevValue = Integer.toString(sevSerious);
            String moderateSevValue = Integer.toString(sevModerate);
            String minorSevValue = Integer.toString(sevMinor);

            bw.write(".progress-line.critical span::after{content: \"" + criticalSevValue + "\";}" + "\n");
            bw.write(".progress-line.serious span::after{content: \"" + seriousSevValue + "\";}" + "\n");
            bw.write(".progress-line.moderate span::after{content: \"" + moderateSevValue + "\";}" + "\n");
            bw.write(".progress-line.minor span::after{content: \"" + minorSevValue + "\";}" + "\n");

            // summary
            bw.write(".bloc3" + "\n");
            bw.write("{" + "\n");

            bw.write("width: 28%;" + "\n");
            bw.write("color: white;" + "\n");
            bw.write("float: left;" + "\n");
            bw.write("height:360px;" + "\n");
            bw.write("padding-bottom: 10px;" + "\n");
            bw.write("padding: 25px 30px;" + "\n");
            bw.write("background-color: transparent;" + "\n");
            bw.write("font-family: Tahoma, Geneva, sans-serif;" + "\n");
            bw.write("border-radius: 10px;" + "\n");
            bw.write("border-style: double;" + "\n");
            bw.write("border-color: white;" + "\n");
            bw.write("}" + "\n");

            // bar graph
            bw.write(".bloc1" + "\n");
            bw.write("{" + "\n");

            bw.write("width: 28%;" + "\n");
            bw.write("height:360px;" + "\n");
            bw.write("float: left;" + "\n");
            bw.write("padding-bottom: 10px;" + "\n");
            bw.write("padding: 25px 30px;" + "\n");
            bw.write("border-radius: 10px;" + "\n");
            bw.write("border-style: double;" + "\n");
            bw.write("border-color: white;" + "\n");
            bw.write("}" + "\n");
            // canvas chart
            bw.write(".bloc2" + "\n");
            bw.write("{" + "\n");
            bw.write("background-color: transparent; " + "\n");
            bw.write("width: 30%;" + "\n");
            bw.write("height:360px;" + "\n");
            bw.write("float:left;" + "\n");
            bw.write("padding-bottom: 10px;" + "\n");
            bw.write("overflow: hidden;" + "\n");
            bw.write("z-index:1;" + "\n");
            bw.write("border-style: double;" + "\n");
            bw.write("border-color: white;" + "\n");
            bw.write("padding: 25px 30px;" + "\n");
            bw.write("border-radius: 10px;" + "\n");
            bw.write("}" + "\n");

            bw.write(".dummy" + "\n");
            bw.write("{" + "\n");
            bw.write("width: 55%;" + "\n");
            bw.write("height:100px;" + "\n");
            bw.write("float:left;" + "\n");
            bw.write("background-color: #100014;" + "\n");
            bw.write("top: -20px;" + "\n");
            bw.write("z-index:2;" + "\n");
            bw.write("position: relative;" + "\n");
            bw.write("}" + "\n");

            bw.write("h3{" + "\n");
            bw.write("color: white;" + "\n");
            bw.write("font-family: Tahoma, Geneva, sans-serif;" + "\n");
            bw.write("}" + "\n");

            bw.write(".pagecount span::after{content: \"" + indexCount + "\";}" + "\n");
            bw.write(".violationscount span::after{content: \"" + totalViolations + "\";}" + "\n");

            bw.write("</style>" + "\n");

            bw.write("<head>" + "\n");

            int p = passedCount;
            int f = failedCount;
            int s = skippedCount;

            bw.write("<script type=\"text/javascript\">" + "\n");

            bw.write("window.onload = function () {" + "\n");

            bw.write("var chart = new CanvasJS.Chart(\"chartContainer\"," + "\n");
            bw.write("{" + "\n");
            /* removed from script and added new 04/01/20223 */
            bw.write("backgroundColor: \"transparent\"," + "\n");
            bw.write("legend:{" + "\n");
            bw.write("fontColor: 'white'," + "\n");
            bw.write("verticalAlign: \"top\"," + "\n");
            bw.write("horizontalAlign: \"center\"," + "\n");
            bw.write("fontstyle: 'Tahoma, Geneva, sans-serif'" + "\n");
            bw.write("}," + "\n");
            /* end */
            bw.write("data: [" + "\n");
            bw.write("{" + "\n");
            // startAngle: 45,
            bw.write("type: \"doughnut\"," + "\n");
            bw.write("showInLegend: true," + "\n");
            bw.write("dataPoints: [" + "\n");
//				bw.write("{  y: 5, legendText:\"Passed 5\", color: \"Green\"},"+"\n");
//				bw.write("{  y: 3, legendText:\"Failed 3\", color: \"#d3003f\" },"+"\n");
//				bw.write("{  y: 2, legendText:\"Skipped 2\", color: \"Yellow\" }"+"\n");
            bw.write("{  y: " + p + ", legendText:\"Passed " + p + "\"" + ", color: \"Green\"}," + "\n");
            bw.write("{  y: " + f + ", legendText:\"Failed " + f + "\"" + ", color: \"Red\" }," + "\n");
            bw.write("{  y: " + s + ", legendText:\"Skipped " + s + "\"" + ", color: \"Yellow\" }" + "\n");
            bw.write("]" + "\n");
            bw.write("}" + "\n");
            bw.write("]" + "\n");
            bw.write("});" + "\n");
            bw.write("chart.render();" + "\n");
            bw.write("}" + "\n");
            bw.write("</script>" + "\n");

            bw.write(
                    "<script type=\"text/javascript\" src=\"https://canvasjs.com/assets/script/canvasjs.min.js\"></script></head>"
                            + "\n");
            bw.write("<body>" + "\n");

            bw.write("<br> <br> <br>" + "\n");

            String pageUrl = driver.getCurrentUrl();
            bw.write("<h3>PageUrl::" + pageUrl + "</h3>");
            // start of table
            bw.write("<table align =\"center\">" + "\n");
            // heading row
            bw.write("<tr>" + "\n");
            bw.write("<th>Sl.No</th>" + "\n");
            bw.write("<th>Violations</th>" + "\n");
            bw.write("<th>Standards Violated</th>" + "\n");
            bw.write("<th>Severity</th>" + "\n");
            // bw.write("<th>Page url</th>"+"\n");
            bw.write("<th>Help URL</th>" + "\n");
            bw.write("<th>Fixes</th>" + "\n"); //--> removing for now
            bw.write("</tr>" + "\n");
            // heading row closed

            JSONArray violations = violationsArray;

//					}
            int fixesCount, greaterfixedCount = 0;
            int length = violations.length();
            int slNo = 0;
            String standards1 = "";
            if (length != 0) {
                for (int j = 0; j < length; j++) {
                    // System.out.println("#########################################");
                    String k = violations.get(j).toString();
                    // System.out.println(j + 1 + "-->");

                    String[] arrOfStr1 = k.split("helpUrl");

                    String[] fixes = arrOfStr1[0].split("message");
                    // Fixes count

                    fixesCount = fixes.length;
                    // System.out.println("fixes count " + fixesCount);

                    if (fixesCount > greaterfixedCount)//

                    {
                        greaterfixedCount = fixesCount;
                    }

                }

                final StringBuilder sb = new StringBuilder();
                final StringBuilder sb2 = new StringBuilder();

                sb.append("Found ").append(violations.length()).append(" accessibility violations:");
                //data2 = new String[greaterfixedCount][7];
                data2[0][0] = "Violation";
                data2[0][1] = "Help URL";
                data2[0][2] = "Target";
                data2[0][3] = "Fix All of the following";
                data2[0][4] = "Fix Any of the following";

                data2[0][5] = "Severity/Impact";
                data2[0][6] = "Standard/Guidelines";

                String target = "", All_Fixes = "", Any_Fixes = "";
                for (int i = 0; i < violations.length(); i++) {

                    int startindex = i + 1;
                    JSONObject node = null;
                    JSONObject violation = violations.getJSONObject(i);
                    sb.append(lineSeparator).append(i + 1).append(") ").append(violation.getString("help"));

                    data2[startindex][0] = violation.getString("help");
                    data2[startindex][5] = violation.getString("impact");
                    data2[startindex][6] = violation.getJSONArray("tags").toString();

                    JSONArray all = null;
                    JSONArray none = null, any = null;
                    if (violation.has("helpUrl")) {
                        String helpUrl = violation.getString("helpUrl");
                        data2[startindex][1] = helpUrl;

                        sb.append(": ").append(helpUrl);
                    }

                    JSONArray nodes = violation.getJSONArray("nodes");

                    for (int j = 0; j < nodes.length(); j++) {

                        sb2.setLength(0);
                        node = nodes.getJSONObject(j);
                        sb.append(lineSeparator).append("  ").append(getOrdinal(j + 1)).append(") ")
                                .append(node.getJSONArray("target")).append(lineSeparator);
                        target = node.getJSONArray("target").toString() + target;

                        all = node.getJSONArray("all");
                        none = node.getJSONArray("none");
                        any = node.getJSONArray("any");

                        if (all.length() > 0) {
                            appendFixes(sb2, all, "Fix all of the following:");
                            int startindex_all = sb2.indexOf("Fix all of the following:");
                            All_Fixes = sb2.substring(startindex_all + 26) + All_Fixes;

                            All_Fixes = "<br>" + All_Fixes;

                        }
                        if (any.length() > 0) {
                            appendFixes(sb2, any, "Fix any of the following:");
                            int startindex_any = sb2.indexOf("Fix any of the following:");
                            Any_Fixes = sb2.substring(startindex_any + 26) + Any_Fixes;

                            Any_Fixes = "<br>" + Any_Fixes;

                            // Any_Fixes = "<br>"+Any_Fixes;

                        }

                    }

                    data2[startindex][2] = target;

                    data2[startindex][3] = All_Fixes;

                    data2[startindex][4] = Any_Fixes;

//						System.out.println("====================+++++++++++++++++++++====================");
//						System.out.println(violation.getString("help"));
//						System.out.println(violation.getString("helpUrl"));
//						System.out.println(violation.getString("impact"));
//						System.out.println(violation.getJSONArray("tags").toString());
//						System.out.println(target);
//						System.out.println(All_Fixes);
//						System.out.println(Any_Fixes);
//						System.out.println("<<<<<<<<<<<<<<<<<<<<<<<<<<");

                    fixesAll2 = "<br>" + fixesAll2 + All_Fixes + "\n";

                    fixesAll = "<br>" + fixesAll + Any_Fixes + "\n";

                    if (All_Fixes.isBlank() && Any_Fixes.isBlank()) {
                        finalAllFixes = "";
                    }
                    if (All_Fixes.isBlank() == false && Any_Fixes.isBlank()) {
                        finalAllFixes = "Fix all of the following : \n" + fixesAll2;
                    }
                    if (All_Fixes.isBlank() && Any_Fixes.isBlank() == false) {
                        finalAllFixes = "Fix any of the following :\n" + fixesAll;
                    }
                    if (All_Fixes.isBlank() == false && Any_Fixes.isBlank() == false) {
                        finalAllFixes = "Fix all of the following : \n" + fixesAll2 + "Fix any of the following :\n"
                                + fixesAll;
                    }

//						System.out.println(Any_Fixes = "\n" + Any_Fixes + "\n");
//						System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>");
//						System.out.println("====================+++++++++++++++++++++====================");

                    slNo = slNo + 1;
                    bw.write("<tr>" + "\n");
                    bw.write("<td>" + slNo + ".</td>" + "\n");
                    // bw.write("<td>Elements must have sufficient color contrast</td>"+"\n");
                    bw.write("<td>" + violation.getString("help") + "</td>" + "\n");

                    Boolean flag = false;
                    String items = "";
                    for (int m = 1; m < violation.getJSONArray("tags").length(); m++) {
                        items = violation.getJSONArray("tags").getString(m);
                        if (m == 1) {
                            standards1 = standards1 + items;
                        } else {
                            standards1 = standards1 + "," + items;
                        }

                        if (m == violation.getJSONArray("tags").length() - 1) {
                            flag = true;
                        }
                    }
                    standards1 = standards1.toUpperCase();
                    // bw.write("<td>" + violation.getJSONArray("tags").toString() + "</td>" +
                    // "\n");
                    bw.write("<td>" + standards1 + "</td>" + "\n");
                    String impact = violation.getString("impact");
                    impact = impact.substring(0, 1).toUpperCase() + impact.substring(1);
                    // String res = s.substring(0, 1).toUpperCase() + s.substring(1);
                    // bw.write("<td>" + violation.getString("impact") + "\n");
                    bw.write("<td>" + impact + "\n");

                    bw.write("<td><a href=" + violation.getString("helpUrl") + ">Click here</a> </td>" + "\n");
                    // bw.write("<td>" + finalAllFixes + "\n");--> removing for now

                    bw.write("</td>" + "\n");
                    bw.write("</tr>" + "\n");
//						System.out.println("*********************************");
//						System.out.println(fixesAll);
//						System.out.println("*********************************");
                    target = "";
                    All_Fixes = "";
                    Any_Fixes = "";

                    fixesAll = "";
                    if (flag == true) {
                        standards1 = "";
                    }

                }

                // createAccessibilityReport(length);

                // Markup m = MarkupHelper.createTable(data2);
                // TestBase.test.log(Status.FAIL, m);

                bw.write("</table>" + "\n");
                bw.write("</body>" + "\n");
                bw.write("</html>" + "\n");

            }
//				totalViolation =totalViolation+totalVio;
//				System.out.println("total violations are ................" +totalViolation);
        }

        bw.flush();
        bw.close();

    }


    public void generateAccessibilityReport(String url, Scenario scenario) throws Exception {
        // Initialize necessary variables
        JSONArray violationsArray = performAccessibility(this.driver);
        int totalViolations = violationsArray.length();
        int sevCritical = 0;
        int sevSerious = 0;
        int sevModerate = 0;
        int sevMinor = 0;
        int indexCount = 1;
        // Call the violationSeverityList method to get the severity counts
        List<Integer> Severitylist1 = violationSeverityList(violationsArray);

        // Assign the severity counts to their respective variables
        sevCritical = Severitylist1.get(0);
        sevSerious = Severitylist1.get(1);
        sevModerate = Severitylist1.get(2);
        sevMinor = Severitylist1.get(3);


        // Get the current test result
        String scenarioStatus = scenario.getStatus().toString();
        int passedCount = 0;
        int failedCount = 0;
        int skippedCount = 0;

        if (scenarioStatus.equals("PASSED")) {
            passedCount = 1;
        } else if (scenarioStatus.equals("FAILED")) {
            failedCount = 1;
        } else if (scenarioStatus.equals("SKIPPED")) {
            skippedCount = 1;
        }



        // Call createAccessibilityReport
        createAccessibilityReport(violationsArray, indexCount, passedCount, failedCount, skippedCount, sevCritical, sevSerious, sevModerate, sevMinor, totalViolations, url, this.driver);
    }
    public static String getFileNameFromUrl(String url) throws UnsupportedEncodingException {
        String fileName = url;

        // Handle query parameters
        int queryIndex = url.indexOf('?');
        if (queryIndex!= -1) {
            fileName = url.substring(0, queryIndex);
        }

        // Handle fragment identifiers
        int fragmentIndex = url.indexOf('#');
        if (fragmentIndex!= -1) {
            fileName = url.substring(0, fragmentIndex);
        }

        // Handle encoded characters
        fileName = URLDecoder.decode(fileName, "UTF-8");

        // Handle special characters
        fileName = fileName.replace(" ", "_"); // replace spaces with underscores
        fileName = fileName.replace("!", "_"); // replace! with underscores
        fileName = fileName.replace("*", "_"); // replace * with underscores
        fileName = fileName.replace("(", "_"); // replace ( with underscores
        fileName = fileName.replace(")", "_"); // replace ) with underscores
        fileName = fileName.replace("[", "_"); // replace [ with underscores
        fileName = fileName.replace("]", "_"); // replace ] with underscores

        // Split the URL into parts and take the last part as the file name
        String[] urlParts = fileName.split("/");
        fileName = urlParts[urlParts.length - 1];

        return fileName;
    }
}