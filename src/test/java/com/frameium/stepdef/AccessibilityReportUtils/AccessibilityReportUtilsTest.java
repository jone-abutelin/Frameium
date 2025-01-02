package com.frameium.stepdef.AccessibilityReportUtils;

import com.frameium.genericfunctions.GenericFunctions;
import com.frameium.pageobject.Tamm_Abudhabi.GovernmentEntitiesPage;
import com.frameium.stepdef.Hooks;
import com.frameium.stepdef.TestSetUp;
import com.frameium.utilities.AccessibilityReportUtils;
import org.openqa.selenium.WebDriver;
import io.cucumber.java.en.*;
import java.net.MalformedURLException;
import static org.junit.Assert.assertTrue;


public class AccessibilityReportUtilsTest extends GenericFunctions {
    Hooks hooks = new Hooks();

    TestSetUp setUp;
    private GenericFunctions genericFunctions;
    private GovernmentEntitiesPage governmentEntitiesPage;
    private WebDriver driver;
    public AccessibilityReportUtilsTest(TestSetUp setUp) throws MalformedURLException {
        this.setUp = setUp;
        genericFunctions = new GenericFunctions(setUp.baseTest.driver);
        governmentEntitiesPage= new  GovernmentEntitiesPage(setUp.baseTest.driver);
        // Assign the driver from setUp to the local driver variable
       // this.driver = setUp.baseTest.driver;

    }
    @When("User click Government Entities button")
    public void user_click_government_entities_button() {
        governmentEntitiesPage.GovernmentEntities();
    }
    @And("User click Department of Health button")
    public void user_click_department_of_health_button() {
        governmentEntitiesPage.DepartmentOfHealth();
    }
    @And("User click  Health button")
    public void user_click_health_button() {
        governmentEntitiesPage.Health();
    }
    @And("User click  initial Approval button")
    public void user_click_initial_approval_button() throws InterruptedException {
        governmentEntitiesPage.initialApproval();
    }
    @Then("User should be successfully navigated and see the heading {string}")
    public void user_should_be_successfully_navigated_and_see_the_heading(String expectedText) throws Exception {
        boolean isTextDisplayed = governmentEntitiesPage.isInitialApprovalTextDisplayed();
        assertTrue("Expected text '" + expectedText + "' is not displayed.", isTextDisplayed);
        String url = setUp.baseTest.driver.getCurrentUrl();
        AccessibilityReportUtils reportUtils = new AccessibilityReportUtils(setUp.baseTest.getDriver());
        reportUtils.generateAccessibilityReport(url,Hooks.scenario);
    }


}
