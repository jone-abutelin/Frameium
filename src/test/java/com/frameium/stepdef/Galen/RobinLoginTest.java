package com.frameium.stepdef.Galen;

import java.io.IOException;
import java.net.MalformedURLException;
import java.util.Collections;

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

public class RobinLoginTest extends GenericFunctions {
    
    private LayoutReport layoutReport;
	TestSetUp setUp;
	private WebDriver driver;

	public RobinLoginTest(TestSetUp setUp) throws MalformedURLException {
		this.setUp = setUp;
		// Assign the driver from setUp to the local driver variable
		this.driver = setUp.baseTest.driver;

	}
    @Given("Open the Robin login page  {string}")
    public void open_the_robin_login_page(String url) {

        driver.get(url);

    }

    @When("I check the elements on the login page")
    public void i_check_the_elements_on_the_login_page() throws IOException {

        layoutReport = Galen.checkLayout(driver, "src/test/resources/Specs_Galen/RobinLogin.gspec", null);

    }

    @Then("the Elements should match the specifications")
    public void the_elements_should_match_the_specifications() throws IOException {


        GalenTestInfo test = GalenTestInfo.fromString("Robin Login Page layout Testing");
        test.getReport().layout(layoutReport, "check layout on desktop");

        // Build the HTML report
        new HtmlReportBuilder().build(Collections.singletonList(test), "target/galen-html-reports");

    }
}
