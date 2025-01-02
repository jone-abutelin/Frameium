package com.frameium.stepdef.Omeir;

import com.frameium.genericfunctions.GenericFunctions;
import com.frameium.pageobject.Omeir.FlightBooking;
import com.frameium.pageobject.Omeir.LoginPage;
import com.frameium.stepdef.Hooks;
import com.frameium.stepdef.TestSetUp;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.WebDriver;
import com.frameium.utilities.ClientSidePerformanceReportUtils;
import java.net.MalformedURLException;

public class FlightBook extends GenericFunctions {
    Hooks hooks = new Hooks();
    private WebDriver driver;
    TestSetUp setUp;
    private GenericFunctions genericFunctions;
    private FlightBooking fBook;
    private LoginPage login;

    public FlightBook(TestSetUp setUp) throws MalformedURLException {
        this.setUp = setUp;
        genericFunctions = new GenericFunctions(setUp.baseTest.driver);
        login = new LoginPage(setUp.baseTest.driver);
        fBook=new FlightBooking(setUp.baseTest.driver);
        // Assign the driver from setUp to the local driver variable
        this.driver = setUp.baseTest.driver;
    }
    @Given("I am on the flight booking page with username {string} and password {string}")
    public void i_am_on_the_flight_booking_page_with_username_and_password(String Username, String Password) {
        genericFunctions.getApplicationUrl("https://www.omeirtravel.com");
        login.clickSignin();
        login.EnterCredentials(Username, Password);
        login.clickLogin();
    }

    @When("I enter details about travel")
    public void i_enter_details_about_travel() throws InterruptedException {
        fBook.Bookticket();
    }

    @Then("I must be able to book flight tickets")
    public void i_must_be_able_to_book_flight_tickets() {
        ClientSidePerformanceReportUtils clientSidePerformanceReportUtils = new ClientSidePerformanceReportUtils(driver);
        clientSidePerformanceReportUtils.generateReport();
    }



}
