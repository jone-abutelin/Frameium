package com.frameium.genericfunctions;

import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class MobileGenericFunctions {


    private WebDriver androiddriver;

    public MobileGenericFunctions(WebDriver androiddriver) {
        this.androiddriver = androiddriver;
    }

    /**
     * This method will click on element
     *
     * @param element
     */
    public void clickElement(WebElement element) {
        executeScript("arguments[0].click();", element);
        System.out.println("clickElement action is completed :::::::::    ");
    }

    /**
     * This method will execute Java script with multiple arguments
     *
     * @param script
     * @param args
     * @return
     */
    public Object executeScript(String script, Object... args) {
        JavascriptExecutor exe = (JavascriptExecutor) androiddriver;
        return exe.executeScript(script, args);
    }


    /**
     * This method will scroll till element
     * @param element
     */
    public void scrollToElement(WebElement element) {
        executeScript("window.scrollTo(arguments[0],arguments[1])", element.getLocation().x, element.getLocation().y);
    }

    /**
     * Scroll till element and click
     * @param element
     */
    public void scrollToElementAndClick(WebElement element) {
        scrollToElement(element);
        element.click();
    }

    /**
     * Scroll till element view
     *
     * @param element
     */
    public void scrollIntoView(WebElement element) {
        executeScript("arguments[0].scrollIntoView()", element);
    }


}
