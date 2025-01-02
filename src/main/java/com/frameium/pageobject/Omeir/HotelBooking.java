package com.frameium.pageobject.Omeir;

import com.frameium.genericfunctions.GenericFunctions;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class HotelBooking extends GenericFunctions {
    private WebDriver driver;

    private By Hotel = By.xpath("//*[@href=\"#hotel\"]");
    private By fromCity = By.xpath("//input[@name=\"hotelFromCity\"]");
    private By SelectedCity = By.xpath("//*[@id=\"ngb-typeahead-0-2\"]/div/div[1]/div");
    private By Date = By.xpath("/html/body/app-root/app-homepage/app-searchpanel/section/section/div[2]/div[2]/form/div/div[2]/div[1]/input");
    private By FromDate = By.xpath("//html/body/bs-datepicker-container/div/div/div/div/bs-days-calendar-view[1]/bs-calendar-layout/div[2]/table/tbody/tr[2]/td[1]/span");
    private By Date2 = By.xpath("//html/body/app-root/app-homepage/app-searchpanel/section/section/div[2]/div[2]/form/div/div[2]/div[2]/div[2]");
    private By ToDate = By.xpath("//html/body/app-root/app-homepage/app-searchpanel/section/section/div[2]/div[2]/form/div/div[2]/div[2]");
    private By Search = By.xpath("//*[@id=\"hotel\"]/form/div/div[4]/button");
    private By SearchBar = By.xpath("/html/body/app-root/app-hotellisting/app-hotelfilter/section/section/section/div/form/ul/li[2]/input");
    private By HotelSelected = By.xpath("//*[@id=\"accordion\"]/div/div[1]/div/div[2]/div/div/div[1]/span/h5");
    private By ChooseRoom = By.xpath("(//a[text()='Choose Room'])[1]");
    private By BookRoom = By.xpath("/html/body/app-root/app-hoteldetail/div[1]/div[5]/div[2]/div[1]/div[21]/div/div/div/table/tr[3]/td[4]/div/button");
    private By Salute = By.xpath("div/div/div/form/div[1]/div[2]/div[2]/div[3]/div/div[1]/div/select");
    private By Title = By.xpath("//*[@id=\"collapseTwo\"]/div[2]/div[3]/div/div[1]/div/select/option[2]");
    private By Fname = By.xpath("//*[@id=\"collapseTwo\"]/div[2]/div[3]/div/div[2]/div/input");
    private By Lname = By.xpath("//*[@id=\"collapseTwo\"]/div[2]/div[5]/div/input");
    private By CheckBox = By.xpath("//*[@id=\"mat-mdc-checkbox-6\"]/div/label");
    private By CallCode = By.xpath("//select[@name='callingCode']");
    private By CodeNo = By.xpath("//option[@value='+91']");
    private By ContactNO = By.xpath("//input[@name='contactNumber']");
    private By Email = By.xpath("//input[@name='ConfirmEmail']");
    private By PayBtn = By.xpath("//*[@id=\"accordion\"]/div/form/div[2]/div[2]/div/div[8]/button");

    public HotelBooking(WebDriver driver) {
        this.driver = driver;
    }
    public void BookHotel() throws InterruptedException {
        clickElement(Hotel);
        clickElement(fromCity);
        enterKeys(fromCity, "London");
        clickElement(SelectedCity);
//        clickElement(FromDate);
//        clickElement(Date);
//        clickElement(ToDate);
//        clickElement(Date2);
        clickElement(Search);
        clickElement(SearchBar);
        enterKeys(SearchBar, "Limehouse Library Hotel");
        clickElement(HotelSelected);
        clickElement(ChooseRoom);
//        WebElement chooseRoom = driver.findElement(By.xpath("(//a[text()='Choose Room'])[1]"));
//        Thread.sleep(2000);
//        clickElement(chooseRoom);
        clickElement(BookRoom);
        clickElement(Salute);
        clickElement(Title);
        clickElement(Fname);
        enterKeys(Fname, "Test");
        clickElement(Lname);
        enterKeys(Lname, "User");
        clickElement(CheckBox);
        clickElement(CallCode);
        clickElement(CodeNo);
        clickElement(ContactNO);
        enterKeys(ContactNO, "9495935918");
        clickElement(Email);
        enterKeys(Email, "dsklhfj@gmail.com");
        clickElement(PayBtn);
    }
}
