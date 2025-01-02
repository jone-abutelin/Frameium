package com.frameium.utilities.OutlookIntegration;

import com.frameium.genericfunctions.GenericFunctions;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class Login extends GenericFunctions {
    private String email;
    private By selectAccount;


    public Login(WebDriver driver, String email) {
        super(driver);
        this.email = email;
        this.selectAccount = By.xpath("//small[text()='" + email + "']");
    }

    public void loginToOutlook() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(60));
        wait.until(ExpectedConditions.presenceOfElementLocated(selectAccount));
        clickElement(selectAccount);
    }
}



