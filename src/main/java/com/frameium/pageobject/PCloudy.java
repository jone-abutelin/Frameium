package com.frameium.pageobject;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;

import com.frameium.genericfunctions.GenericFunctions;


public class PCloudy extends GenericFunctions{
	
	By AcceptBtn = By.id("com.pcloudy.appiumdemo:id/accept");
	By LoginBtn = By.id("com.pcloudy.appiumdemo:id/ecLoginButton");
	By EnterUserName = By.id("com.pcloudy.appiumdemo:id/username");
	By EnterPassword = By.id("com.pcloudy.appiumdemo:id/password");
	By Login = By.id("com.pcloudy.appiumdemo:id/loginbtn");
    By FromDropdown = By.id("com.pcloudy.appiumdemo:id/spinnerfrom");
    By FromSelect = By.xpath("/hierarchy/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.ListView/android.widget.CheckedTextView[2]");
    By ToDropdown = By.id("com.pcloudy.appiumdemo:id/spinnerto");
    By ToSelect = By.xpath("/hierarchy/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.ListView/android.widget.CheckedTextView[3]");
    By ReturnRadioBtn = By.id("com.pcloudy.appiumdemo:id/returnTrip");
    By Departs = By.id("com.pcloudy.appiumdemo:id/txtdepart");
    By DepartDate = By.xpath("//android.view.View[@content-desc=\"15 April 2023\"]");
    By OkBtn = By.id("android:id/button1");
    By Return = By.id("com.pcloudy.appiumdemo:id/txtreturn");
    By ReturnDate = By.xpath("//android.view.View[@content-desc=\"28 April 2023\"]");
    By Adult = By.id("com.pcloudy.appiumdemo:id/no_adults");
    By AdultCount = By.xpath("/hierarchy/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.ListView/android.widget.CheckedTextView[2]");
    By Children = By.id("com.pcloudy.appiumdemo:id/no_children");
    By ChildrenCount = By.xpath("/hierarchy/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.ListView/android.widget.CheckedTextView[1]");
    By Infants = By.id("com.pcloudy.appiumdemo:id/no_infants");
    By InfantsCount = By.xpath("/hierarchy/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.ListView/android.widget.CheckedTextView[1]");
    By FlightClass = By.id("com.pcloudy.appiumdemo:id/spinnerflight");
    By SelectFlightClass = By.xpath("/hierarchy/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.ListView/android.widget.CheckedTextView[2]");
    By TicketType = By.id("com.pcloudy.appiumdemo:id/spinnerticket");
    By SelectTicketType = By.xpath("/hierarchy/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.ListView/android.widget.CheckedTextView[2]");
    By SearchFlight = By.id("com.pcloudy.appiumdemo:id/searchFlights");
    By BookingConfirmation = By.id("com.pcloudy.appiumdemo:id/Sea");

    
    
public void pcloudyDemoScreen() 
{
	try
	{
		 androdiclickElement(AcceptBtn);
	}
	catch(Exception e)
	{
		 System.out.println(e);
	}
}

public void pcloudyLogin() 
{
	try
	{
		androdiclickElement(LoginBtn);
	}
	catch(Exception e)
	{
		 System.out.println(e);
	}
}

public void enterUserNameAndPassword(String username, String password) 
{
	try
	{
		//androdiclickElement(EnterUserName);
		androidenterKeys(EnterUserName, username);
		//androdiclickElement(EnterPassword);
		androidenterKeys(EnterPassword, password);
	    
	}
	catch(Exception e)
	{
		 System.out.println(e);
	}
}

public void selectLogin() 
{
	try
	{
    	androdiclickElement(Login);
    	System.out.println("Login to pCloudy application successfully");
	}
	catch(Exception e)
	{
		 System.out.println(e);
	}
}

public void requiredDetails() 
{ 
	try
	{
		androdiclickElement(FromDropdown);
	    androdiclickElement(FromSelect);
	    androdiclickElement(ToDropdown);
	    androdiclickElement(ToSelect);
	    androdiclickElement(ReturnRadioBtn);
	    androdiclickElement(Departs);
	    androdiclickElement(DepartDate);
	    androiddriver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	    androdiclickElement(OkBtn);
	    androdiclickElement(Return);
	    androdiclickElement(ReturnDate);
	    androiddriver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	    androdiclickElement(OkBtn);
	    androdiclickElement(Adult);
	    androdiclickElement(AdultCount);
	    androdiclickElement(Children);
	    androdiclickElement(ChildrenCount);
	    androdiclickElement(Infants);
	    androdiclickElement(InfantsCount);
	    androdiclickElement(FlightClass);
	    androdiclickElement(SelectFlightClass);
	    androdiclickElement(TicketType);
	    androdiclickElement(SelectTicketType);
	}
	catch(Exception e)
	{
		 System.out.println(e);
	}
}

public void searchFlight() 
{
    if(isDisplayedBy(SearchFlight)) 
    {
    	androdiclickElement(SearchFlight);
        System.out.println("Search Flight button clicked successfully");
    }
    else 
    {
        System.out.println("Search Flight button not displayed");
    }
}
}