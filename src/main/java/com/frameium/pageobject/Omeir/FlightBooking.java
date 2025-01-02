package com.frameium.pageobject.Omeir;

import com.frameium.genericfunctions.GenericFunctions;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class FlightBooking extends GenericFunctions {

    private WebDriver driver;
    private By FromCity = By.xpath("//input[@name=\"FromCity\"]");
    private By City = By.xpath("//*[@id=\"ngb-typeahead-0-0\"]");
    private By ToCity = By.xpath("//input[@name=\"ToCity\"]");
    private By City2 = By.xpath("//*[@id=\"ngb-typeahead-1-0\"]");
    private By fromDate = By.xpath("//*[@name=\"toDate\"]");
    private By NextBtn = By.xpath("//button[@class=\"next\"]\n");
    private By datePick1 = By.xpath("//bs-datepicker-container/div/div/div/div/bs-days-calendar-view[1]/bs-calendar-layout/div[2]/table/tbody/tr[2]/td[1]/span/text()");
    private By toDate = By.xpath("//input[@name=\"toDate\"]");
    private By datePick2 = By.xpath("//bs-datepicker-container/div/div/div/div/bs-days-calendar-view[1]/bs-calendar-layout/div[2]/table/tbody/tr[2]/td[5]/span/text()");
    private By search = By.xpath("//*[text()='Search']\n");
    private By BookBtn = By.xpath("//*[@id=\"accordion\"]/div[1]/div/div[1]/div[2]/button");
    private By Title = By.xpath("//*[@id=\"accordion\"]/form/div[1]/div[2]/div/div[3]/div/div[1]/div/select");
    private By Salute = By.xpath("//option[@value='Mr.']\n");
    private By Fname = By.xpath("//*[@id=\"accordion\"]/form/div[1]/div[2]/div/div[3]/div/div[2]/div/input\n");
    private By Lname = By.xpath("//*[@id=\"accordion\"]/form/div[1]/div[2]/div/div[5]/div/input\n");
    private By Dob = By.xpath("//input[@placeholder='yyyy-mm-dd']\n");
    private By Nationality = By.xpath("//*[@id=\"accordion\"]/form/div[1]/div[2]/div/div[7]/div/div[3]/div/select\n");
    private By Ncode = By.xpath("//option[@value='IN']\n");
    private By Passport = By.xpath("//*[@id=\"accordion\"]/form/div[1]/div[2]/div/div[7]/div/div[4]/div/input");
    private By IssueCon = By.xpath("//*[@id=\"accordion\"]/form/div[1]/div[2]/div/div[7]/div/div[5]/div/select\n");
    private By IssueVal = By.xpath("//option[@value='IN']\n");
    private By ExpDate = By.xpath("//*[@id=\"accordion\"]/form/div[1]/div[2]/div/div[7]/div/div[6]/div/div/input");
    private By Checkbox = By.xpath("//*[@id=\"mat-mdc-checkbox-1\"]/div/label");
    private By CallCode = By.xpath("//select[@name='callingCode']\n");
    private By CodeNo = By.xpath("//option[@value='+91']\n");
    private By ContactNO = By.xpath("//input[@name='contactNumber']\n");
    private By Email = By.xpath("//input[@name='ConfirmEmail']\n");
    private By PayBtn = By.xpath("//*[@id=\"accordion\"]/form/div[4]/div[2]/div/div[8]/button");
    private By Continue = By.xpath("//*[@id=\"sessionModel\"]/div/div/div/div/button[1]");

    public FlightBooking(WebDriver driver) {
        this.driver = driver;
    }

    public void Bookticket() throws InterruptedException {
        clickElement(FromCity);
        enterKeys(FromCity, "NYC");
        clickElement(City);
        clickElement(ToCity);
        enterKeys(ToCity, "LON");
        clickElement(City2);
        Thread.sleep(1000);
        clickElement(driver.findElement(By.xpath("/html/body/bs-datepicker-container/div/div/div/div/bs-days-calendar-view[1]/bs-calendar-layout/div[2]/table/tbody/tr[2]/td[1]/span")));
        clickElement(driver.findElement(By.xpath("/html/body/bs-datepicker-container/div/div/div/div/bs-days-calendar-view[1]/bs-calendar-layout/div[2]/table/tbody/tr[2]/td[5]/span")));
        clickElement(search);
        Thread.sleep(1000);
        clickElement(BookBtn);
        clickElement(Title);
        clickElement(Salute);
        clickElement(Fname);
        enterKeys(Fname, "Test");
        clickElement(Lname);
        enterKeys(Lname, "User");
        clickElement(Dob);
        Thread.sleep(1000);
        enterKeys(Dob, "1999-05-15");
        clickElement(Nationality);
        clickElement(Ncode);
        clickElement(Passport);
        enterKeys(Passport, "B8380701");
        clickElement(IssueCon);
        clickElement(IssueVal);
        clickElement(ExpDate);
        enterKeys(ExpDate, "2030-04-25");
        clickElement(driver.findElement(By.xpath("/html/body/app-root/app-passengerinfo/section[3]/div/div/div[1]/div[1]/div/div/form/div[4]/div[1]/a/div/mat-checkbox/div/label")));
        clickElement(CallCode);
        clickElement(CodeNo);
        clickElement(ContactNO);
        enterKeys(ContactNO, "9495935918");
        clickElement(Email);
        enterKeys(Email, "dsklhfj@gmail.com");
        clickElement(PayBtn);
    }

}
