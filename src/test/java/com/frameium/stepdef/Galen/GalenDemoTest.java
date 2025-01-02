package com.frameium.stepdef.Galen;
import com.frameium.genericfunctions.GenericFunctions;
import com.frameium.stepdef.TestSetUp;
import com.galenframework.api.Galen;
import com.galenframework.reports.GalenTestInfo;
import com.galenframework.reports.HtmlReportBuilder;
import com.galenframework.reports.model.LayoutReport;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import org.openqa.selenium.WebDriver;

import java.io.IOException;
import java.net.MalformedURLException;
import java.util.Collections;
public class GalenDemoTest extends GenericFunctions{

	private LayoutReport layoutReport;
	TestSetUp setUp;
	private WebDriver driver;

	public GalenDemoTest(TestSetUp setUp) throws MalformedURLException {
		this.setUp = setUp;
		// Assign the driver from setUp to the local driver variable
		this.driver = setUp.baseTest.driver;

	}
	 @Given("I open the example website {string}")
	    public void iOpenTheExampleWebsite(String url) {
		 driver.get(url);
		
	    }
	 
	 @When("I check the header layout")
	    public void CheckTheHeaderLayout() throws IOException {
	        layoutReport = Galen.checkLayout(driver, "src/test/resources/specs/example.gspec", null);
	    }
	 @Then("the header layout should match the specifications")
	    public void theHeaderLayoutShouldMatchTheSpecifications() throws IOException {

		 
		  GalenTestInfo test = GalenTestInfo.fromString("Header Layout Test");
		  test.getReport().layout(layoutReport, "check layout on desktop");
		   
		  // Build the HTML report
		   new HtmlReportBuilder().build(Collections.singletonList(test), "target/galen-html-reports");
	    }
}
