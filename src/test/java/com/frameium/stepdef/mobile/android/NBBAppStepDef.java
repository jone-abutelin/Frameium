package com.frameium.stepdef.mobile.android;

import com.frameium.pageobject.mobile.android.SaudiExchangePage;
import com.frameium.pageobject.mobile.android.SaudiLandingPage;
import com.frameium.stepdef.TestSetUp;
import io.appium.java_client.android.AndroidDriver;
import io.cucumber.java.en.Given;
import java.net.MalformedURLException;

public class NBBAppStepDef {
    TestSetUp setUp;

    public NBBAppStepDef(TestSetUp setUp) throws MalformedURLException {
        System.out.println("<<<<<<< NBBAppStepDef >>>>>>>>>>>");
        this.setUp = setUp;
        AndroidDriver androidDriver = (AndroidDriver) setUp.baseTest.driver;

    }


    @Given("user installs NBB app on Android device")
    public void userInstallsNBBAppOnAndroidDevice() {
            System.out.println( "<<<<<<<<<<< Success >>>>>>>>");
    }


}
