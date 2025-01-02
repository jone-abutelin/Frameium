package com.frameium.pageobject.Tamm_Abudhabi;

import com.frameium.genericfunctions.GenericFunctions;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.HashMap;
import java.util.Map;

public class ThemeSwitchingPage extends GenericFunctions {

    private WebDriver driver;
    private WebDriverWait wait;

    private By themeMenuLocator = By.xpath("//button[@aria-label='header-theme-menu']");
    private By cookieTrayButtonLocator = By.xpath("//button[@aria-label='Accept']");
    private By otherButtonLocator = By.cssSelector(".ui-lib-button");

    // CSS variables for light and dark themes
    private String lightThemeCSS = ".ui-lib-new-typography-v2_2";
    private String darkThemeCSS = ".ui-lib_highContrast-v2_2";

    public ThemeSwitchingPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(60));
        PageFactory.initElements(driver, this); // Initialize PageFactory for WebElement initialization
    }

    /**
     * Opens the theme menu by clicking on the theme menu button in the header.
     * Closes the cookie tray if present before opening the theme menu.
     */
    public void switchToDarkTheme() {
        try {
            WebElement themeMenu = wait.until(ExpectedConditions.elementToBeClickable(themeMenuLocator));
            clickElement(themeMenu);
            // Wait for theme change to take effect
            Thread.sleep(3000); // Adjust the sleep duration as needed

            // Check and log body class for verification
            String bodyClass = driver.findElement(By.tagName("body")).getAttribute("class");
            System.out.println("Body class after theme switch: " + bodyClass);

        } catch (Exception e) {
            System.out.println("Failed to click the theme menu button: " + e.getMessage());
        }
    }

    /**
     * Retrieves CSS properties of an element for the specified theme.
     *
     * @param elementCssSelector CSS selector of the element whose properties need to be retrieved.
     * @param isDarkTheme       Flag indicating if the dark theme properties should be retrieved.
     * @return Map containing CSS property names and their corresponding values.
     */
    public Map<String, String> getCSSProperties(String elementCssSelector, boolean isDarkTheme) {
        try {
            String themeSelector = isDarkTheme ? darkThemeCSS : lightThemeCSS;
            WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(themeSelector + " " + elementCssSelector)));
            Map<String                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                          , String> cssProperties = new HashMap<>();

            cssProperties.put("background-color", element.getCssValue("background-color"));
            cssProperties.put("color", element.getCssValue("color"));
            // Add more properties as needed

            System.out.println("Captured CSS properties for " + (isDarkTheme ? "dark" : "light") + " theme: " + cssProperties);
            return cssProperties;
        } catch (Exception e) {
            System.out.println("Failed to get CSS properties for " + (isDarkTheme ? "dark" : "light") + " theme: " + e.getMessage());
            return null;
        }
    }

    /**
     * Closes the cookie tray by clicking on the Accept button.
     * Additionally, clicks on another button to ensure the cookie tray is closed properly.
     */
    public void closeCookieTray() {
        try {
            WebElement cookieTrayButton = wait.until(ExpectedConditions.elementToBeClickable(cookieTrayButtonLocator));
            clickElementUsingJavaScript(driver.findElement(cookieTrayButtonLocator));
            WebElement otherButton = wait.until(ExpectedConditions.elementToBeClickable(otherButtonLocator));
            clickElementUsingJavaScript(driver.findElement(otherButtonLocator));
        } catch (Exception e) {
            System.out.println("Failed to close the cookie tray: " + e.getMessage());
        }
    }

    /**
     * Compares CSS properties between light and dark themes.
     *
     * @param lightThemeCSS Map containing CSS properties for the light theme.
     * @param darkThemeCSS  Map containing CSS properties for the dark theme.
     * @return true if CSS properties are different between themes, false otherwise.
     */
    public boolean verifyThemeCSSProperties(Map<String, String> lightThemeCSS, Map<String, String> darkThemeCSS) {
        try {
            if (lightThemeCSS == null || darkThemeCSS == null) {
                System.out.println("One of the CSS property maps is null.");
                return false;
            }

            // Compare the light and dark theme CSS properties
            for (String key : lightThemeCSS.keySet()) {
                if (lightThemeCSS.get(key).equals(darkThemeCSS.get(key))) {
                    System.out.println("CSS property " + key + " is the same in both themes: " + lightThemeCSS.get(key));
                    return false;
                }
            }
            return true;
        } catch (Exception e) {
            System.out.println("Failed to verify theme CSS properties: " + e.getMessage());
            return false;
        }
    }

    /**
     * Prints color differences between light and dark theme CSS properties.
     *
     * @param lightThemeCSS Map containing CSS properties for the light theme.
     * @param darkThemeCSS  Map containing CSS properties for the dark theme.
     */
    public void printColorDifferences(Map<String, String> lightThemeCSS, Map<String, String> darkThemeCSS) {
        System.out.println("Comparing CSS properties:");
        for (String key : lightThemeCSS.keySet()) {
            System.out.println(key + ": Light theme - " + lightThemeCSS.get(key) + ", Dark theme - " + darkThemeCSS.get(key));
        }
    }

    /**
     * Verifies if color intensities differ significantly between light and dark themes.
     *
     * @param lightThemeCSS Map containing CSS properties for the light theme.
     * @param darkThemeCSS  Map containing CSS properties for the dark theme.
     * @return true if color intensities are significantly different, false otherwise.
     */
    public boolean verifyColorIntensities(Map<String, String> lightThemeCSS, Map<String, String> darkThemeCSS) {
        try {
            for (String key : lightThemeCSS.keySet()) {
                String lightColor = lightThemeCSS.get(key);
                String darkColor = darkThemeCSS.get(key);

                if (!compareColorIntensities(lightColor, darkColor)) {
                    System.out.println("Color intensities for property " + key + " are not significantly different.");
                    return false;
                }
            }
            return true;
        } catch (Exception e) {
            System.out.println("Failed to verify color intensities: " + e.getMessage());
            return false;
        }
    }

    /**
     * Compares the color intensities of two colors given in rgba format.
     *
     * @param color1 the first color in rgba format (e.g., "rgba(255, 255, 255, 1)")
     * @param color2 the second color in rgba format (e.g., "rgba(0, 0, 0, 1)")
     * @return true if the intensity difference is significant, false otherwise
     */
    private boolean compareColorIntensities(String color1, String color2) {
        int intensity1 = getColorIntensity(color1);
        int intensity2 = getColorIntensity(color2);

        // Define a threshold for significant difference in intensity
        int intensityDifferenceThreshold = 100;

        return Math.abs(intensity1 - intensity2) > intensityDifferenceThreshold;
    }

    /**
     * Calculates the intensity of a color given in rgba format.
     *
     * @param color the color in rgba format (e.g., "rgba(255, 255, 255, 1)")
     * @return the intensity of the color
     */
    private int getColorIntensity(String color) {
        String[] rgba = color.replace("rgba(", "").replace(")", "").split(",");
        int r = Integer.parseInt(rgba[0].trim());
        int g = Integer.parseInt(rgba[1].trim());
        int b = Integer.parseInt(rgba[2].trim());

        // Calculate the intensity as the average of the RGB components
        return (r + g + b) / 3;
    }
}
