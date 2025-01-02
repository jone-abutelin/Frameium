package com.frameium.stepdef.Tamm_Abudhabi;

import com.frameium.genericfunctions.GenericFunctions;
import com.frameium.pageobject.Tamm_Abudhabi.ThemeSwitchingPage;
import com.frameium.stepdef.TestSetUp;
import io.cucumber.java.en.*;
import org.openqa.selenium.WebDriver;

import java.net.MalformedURLException;
import java.util.Map;

import static org.junit.Assert.assertTrue;

public class ThemeSwitchingTest extends GenericFunctions {
    private TestSetUp setUp;
    private ThemeSwitchingPage themeSwitchingPage;
    private WebDriver driver;

    // Store the light theme CSS properties
    private Map<String, String> lightThemeCSS;

    public ThemeSwitchingTest(TestSetUp setUp) throws MalformedURLException {
        this.setUp = setUp;
        this.driver = setUp.baseTest.driver;
        themeSwitchingPage = new ThemeSwitchingPage(this.driver);
    }

    @Given("User captures the header CSS properties for the light theme")
    public void userCapturesTheHeaderCSSPropertiesForTheLightTheme() {
        lightThemeCSS = themeSwitchingPage.getCSSProperties("header", false);
        System.out.println("Captured CSS properties for light theme: " + lightThemeCSS);
    }

    @When("User Closes the cookie tray by clicking on the Accept button")
    public void userClosesTheCookieTrayByClickingOnTheAcceptButton() {
        themeSwitchingPage.closeCookieTray();
    }

    @When("User clicks on the Theme menu button to switch to Dark theme")
    public void userClicksOnTheThemeMenuButtonToSwitchToDarkTheme() {
        themeSwitchingPage.switchToDarkTheme();
    }

    @Then("User captures the header CSS properties for the dark theme")
    public void userCapturesTheHeaderCSSPropertiesForTheDarkTheme() {
        // Capture CSS properties after theme change (dark theme)
        Map<String, String> darkThemeCSS = themeSwitchingPage.getCSSProperties("header", true);
        System.out.println("Captured CSS properties for dark theme: " + darkThemeCSS);
    }

    @Then("verify that the header CSS properties change after switching themes")
    public void verify_that_the_header_css_properties_change_after_switching_themes() {
        // Capture CSS properties after theme change (dark theme)
        Map<String, String> darkThemeCSS = themeSwitchingPage.getCSSProperties("header", true);
        if (darkThemeCSS == null) {
            System.out.println("Failed to capture dark theme CSS properties.");
        }

        // Print color differences (for debugging purposes)
        themeSwitchingPage.printColorDifferences(lightThemeCSS, darkThemeCSS);

        // Verify that the CSS properties have changed between light and dark themes
        boolean arePropertiesChanged = themeSwitchingPage.verifyThemeCSSProperties(lightThemeCSS, darkThemeCSS);
        assertTrue("The CSS properties should change between light and dark themes", arePropertiesChanged);

        // Verify that the color intensities differ significantly between themes
        boolean areColorIntensitiesDifferent = themeSwitchingPage.verifyColorIntensities(lightThemeCSS, darkThemeCSS);
        assertTrue("The color intensities should be significantly different between light and dark themes", areColorIntensitiesDifferent);
    }
}
