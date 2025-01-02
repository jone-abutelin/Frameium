package com.frameium.utilities;

import java.util.ArrayList;
import java.util.List;

public class TestCaseInformation {
    private static String testCaseName;
    private static String scenarioName;
    private static List<String> testSteps = new ArrayList<>();

    public static String getTestCaseName() {
        return testCaseName;
    }

    public static void setTestCaseName(String testCaseName) {
    	TestCaseInformation.testCaseName = testCaseName;
    }

    public static String getScenarioName() {
        return scenarioName;
    }

    public static void setScenarioName(String scenarioName) {
    	TestCaseInformation.scenarioName = scenarioName;
    }

    public static List<String> getTestEntries() {
        return testSteps;
    }

    public static void addTestEntry(String entry) {
    	testSteps.add(entry);
    }
}
