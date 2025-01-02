package com.frameium.stepdef;

import com.frameium.baseclass.BaseTest;

import java.net.MalformedURLException;

public class TestSetUp {

    public BaseTest baseTest;

    public TestSetUp() throws MalformedURLException {
        System.out.println("<<<<<<<<<<<<<<  TestSetUp class >>>>>>>>>>>> ");
        baseTest = new BaseTest();
        baseTest.getDriver();
    }

}
