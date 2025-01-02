package com.frameium.pageobject.D365_CRM;

import com.frameium.genericfunctions.GenericFunctions;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class D365SignOutPage extends GenericFunctions {

    public D365SignOutPage (WebDriver driver) {
        driver = this.driver;
    }


    /**
     * Method to create a locator using an aria-label.
     * @param ariaLabel The aria-label attribute value used to locate the element.
     * @return A By object representing the XPath locator for the button with the specified aria-label.
     */
    public By locatorByAriaLabel(String ariaLabel) {
        return By.xpath(String.format("//button[@aria-label='%s']", ariaLabel));
    }

    // Constant for the aria-label of the profile icon
    private final String PROFILE_ICON_LABEL = "Account manager for Auto TestUser6";
    // By object for locating the profile icon button using the aria-label
    private final By ProfileIcon = locatorByAriaLabel(PROFILE_ICON_LABEL);


    // Constant for the aria-label of the sign-out button
    private final String SIGN_OUT_LABEL = "Sign out of this account";
    // By object for locating the sign-out button using the aria-label
    private final By SignOutButton = locatorByAriaLabel(SIGN_OUT_LABEL);

    /**
     * Method to click the profile icon.
     * This method finds the profile icon using its locator and performs a click action.
     */
    public void ClickProfileIcon()
    {
        clickObject(ProfileIcon);
    }

    /**
     * Method to click the sign-out button.
     * This method finds the sign-out button using its locator and performs a click action.
     */
    public void ClickSignOutButton()
    {
        clickObject(SignOutButton);
    }

}
