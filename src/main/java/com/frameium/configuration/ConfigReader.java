package com.frameium.configuration;

import com.frameium.browserconfiguration.BrowserType;

public interface ConfigReader {
	
	public int getImpliciteWait();
	public int getExplicitWait();
	public int getPageLoadTime();
	public BrowserType getBrowserType();
	public String getUrl();
	public String getUserName();
	public String getPassword();

    public String getClimateXUrl();
	public String getLightHouseReport();
	public String getAccessibilityReport();
	public String getClimateXTestDataPath();
	public String getHeadless();
	public String getSelfHealing();

	
	

}
