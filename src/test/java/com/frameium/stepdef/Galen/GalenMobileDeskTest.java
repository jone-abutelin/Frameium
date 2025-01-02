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
	import org.openqa.selenium.Dimension;
	import org.openqa.selenium.WebDriver;

    import java.io.IOException;
	import java.net.MalformedURLException;
	import java.util.Collections;

	public class GalenMobileDeskTest extends GenericFunctions{

	   
	    private LayoutReport layoutReport;
		TestSetUp setUp;
		private WebDriver driver;

		public GalenMobileDeskTest(TestSetUp setUp) throws MalformedURLException {
			this.setUp = setUp;
			// Assign the driver from setUp to the local driver variable
			this.driver = setUp.baseTest.driver;

		}
	    @Given("open the example website {string}")
	    public void iOpenTheExampleWebsite(String url) {
	    	driver.get(url);
	    }

	    @When("check the header layout on {string}")
	    public void checkTheHeaderLayout(String device) throws IOException {
	        switch (device.toLowerCase()) {
	            case "desktop":
	                driver.manage().window().maximize();// Desktop size
	                layoutReport = Galen.checkLayout(driver, "src/test/resources/specs/RobinLogin.gspec", Collections.singletonList("desktop"));
	                break;
	            case "mobile":
                driver.manage().window().setSize(new Dimension(360, 800)); // Mobile size
	                layoutReport = Galen.checkLayout(driver, "src/test/resources/specs/RobinLogin.gspec", Collections.singletonList("mobile"));
	                break;
	            default:
	                throw new IllegalArgumentException("Unknown device: " + device);
	       }
	    }

	    @Then("the header layout should match the {string} specifications")
	    public void theHeaderLayoutShouldMatchTheSpecifications(String device) throws IOException {
	        GalenTestInfo testInfo = GalenTestInfo.fromString("Header Layout Test on " + device);
	        testInfo.getReport().layout(layoutReport, "Check layout on " + device);
	        new HtmlReportBuilder().build(Collections.singletonList(testInfo), "target/galen-html-reports");
	    }


	}


