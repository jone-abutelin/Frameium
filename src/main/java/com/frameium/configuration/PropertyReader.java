package com.frameium.configuration;

import java.io.File;
import java.io.FileInputStream;
import java.util.Properties;

import com.frameium.browserconfiguration.BrowserType;
import com.frameium.resource.ResourceHelper;

public class PropertyReader implements ConfigReader {

	private static FileInputStream file;
	public static Properties OR;

	public PropertyReader() {
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

	public int getImpliciteWait() {
		return Integer.parseInt(OR.getProperty("implicitwait"));
	}

	public int getExplicitWait() {
		return Integer.parseInt(OR.getProperty("explicitwait"));
	}

	public int getPageLoadTime() {
		return Integer.parseInt(OR.getProperty("pageloadtime"));
	}

	public BrowserType getBrowserType() {
		return BrowserType.valueOf(OR.getProperty("browserType"));
	}

	public String getUrl() {
		if (System.getProperty("url") != null) {
			return System.getProperty("url");
		}
		return OR.getProperty("applicationUrl");
	}

	public String getClimateXUrl() {
		if (System.getProperty("climatexurl") != null) { //climatexurl
			return System.getProperty("climatexurl"); //climatexurl
		}
		System.out.println("getURL-->" + OR.getProperty("climatexurl")); //climatexurl
		return OR.getProperty("climatexurl"); //climatexurl
	}

	public String getUserName() {
		if (System.getProperty("userName") != null) {
			return System.getProperty("userName");
		}
		return OR.getProperty("userName");
	}

	public String getPassword() {
		if (System.getProperty("password") != null) {
			return System.getProperty("password");
		}
		return OR.getProperty("password");

	}

	public String getLightHouseReport() {
		if (System.getProperty("lighthousereport") != null) {
			return System.getProperty("lighthousereport");
		}
		return OR.getProperty("lighthousereport");
	}

	public String getAccessibilityReport() {
		if (System.getProperty("accessibilityreport") != null) {

			return System.getProperty("accessibilityreport");
		}
		return OR.getProperty("accessibilityreport");
	}


	public String getClimateXTestDataPath() {
		if (System.getProperty("climatextestdatapath") != null) {
			return System.getProperty("climatextestdatapath");
		}
		return OR.getProperty("climatextestdatapath");
	}

	public String getHeadless() {
		if (System.getProperty("headless") != null) {
			return System.getProperty("headless");
		}
		return OR.getProperty("headless");
	}

	@Override
	public String getSelfHealing() {
		if (System.getProperty("selfhealing") != null) {
			return System.getProperty("selfhealing");
		}
		return OR.getProperty("selfhealing");
	}

}
