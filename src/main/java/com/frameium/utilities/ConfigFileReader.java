
//dfdf
package com.frameium.utilities;

import java.io.File;
import java.io.FileInputStream;
import java.util.Properties;

import com.frameium.resource.ResourceHelper;

public class ConfigFileReader {

	
	private static FileInputStream file;
	public static Properties OR;

	public void PropertyReader() {
		try {
			String filePath = ResourceHelper.getResourcePath("src/main/resources/ConfigFiles/config.properties");
			file = new FileInputStream(new File(filePath));
			OR = new Properties();
			OR.load(file);
			
			/*
			 * String filePath1 = ResourceHelper.getResourcePath(
			 * "src/main/resources/ConfigFiles/config1.properties"); file = new
			 * FileInputStream(new File(filePath1)); OR = new Properties(); OR.load(file);
			 */
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
	}
	
public String getReportConfigPath(){
	String reportConfigPath = OR.getProperty("reportConfigPath");
	if(reportConfigPath!= null) return reportConfigPath;
	else throw new RuntimeException("Report Config Path not specified in the Configuration.properties file for the Key:reportConfigPath");		
}



}