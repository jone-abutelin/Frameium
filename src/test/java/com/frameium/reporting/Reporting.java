package com.frameium.reporting;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import net.masterthought.cucumber.Configuration;
import net.masterthought.cucumber.ReportBuilder;
import net.masterthought.cucumber.presentation.PresentationMode;
import net.masterthought.cucumber.sorting.SortingMethod;

public class Reporting {
	public static void generateJvmReport( ) {
		
		File reportdirectory = new File("target/maven-cucumber-report");
		List<String>jsonFiles= new ArrayList<String>();
		jsonFiles.add("target/cucumber-report/cucumber.json");
		Configuration config = new Configuration(reportdirectory,"equalseat");
		config.addClassifications("Platform", "Windows10");
		config.addClassifications("Browser", "Chrome");
		config.addClassifications("Build", "POC");
		config.setSortingMethod(SortingMethod.NATURAL);
		config.addPresentationModes(PresentationMode.EXPAND_ALL_STEPS);
		ReportBuilder reportBuild = new ReportBuilder(jsonFiles,config);
		reportBuild.generateReports();
		
		
	}

}
