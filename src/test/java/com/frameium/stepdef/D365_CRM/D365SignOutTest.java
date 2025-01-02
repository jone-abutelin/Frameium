package com.frameium.stepdef.D365_CRM;

import com.frameium.genericfunctions.GenericFunctions;
import com.frameium.pageobject.D365_CRM.D365LoginPage;
import com.frameium.pageobject.D365_CRM.D365SignOutPage;
import com.frameium.stepdef.Hooks;
import com.frameium.stepdef.TestSetUp;
import cucumber.annotation.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;

import java.net.MalformedURLException;


public class D365SignOutTest {

    Hooks hooks = new Hooks();

    TestSetUp setUp;
    private GenericFunctions genericFunctions;
    private D365SignOutPage d365SignOutPage;
    private WebDriver driver;

    public D365SignOutTest(TestSetUp setUp) throws MalformedURLException {
        this.setUp = setUp;
        genericFunctions = new GenericFunctions(setUp.baseTest.driver);
        d365SignOutPage=new D365SignOutPage(setUp.baseTest.driver);
        // Assign the driver from setUp to the local driver variable
        this.driver = setUp.baseTest.driver;

    }



    @When("User clicks on the profile icon")
    public void userClicksOnTheProfileIcon() {
        d365SignOutPage.ClickProfileIcon();
    }

    @When("User clicks on the sign out option")
    public void userClicksOnTheSignOutOption() {
        d365SignOutPage.ClickSignOutButton();
    }

    @Then("User should be redirected to the sign out page")
    public void userShouldBeRedirectedToTheSignOutPage() {
        // Assert the redirection to the sign-out page
        Assert.assertTrue(driver.getCurrentUrl().contains("https://login.microsoftonline.com"));

    }


}
