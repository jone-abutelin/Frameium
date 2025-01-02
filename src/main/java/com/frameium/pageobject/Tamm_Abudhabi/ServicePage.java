package com.frameium.pageobject.Tamm_Abudhabi;

import com.frameium.genericfunctions.GenericFunctions;
import org.apache.xmlbeans.GDuration;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.util.List;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;


public class ServicePage extends GenericFunctions {

    public ServicePage(WebDriver driver) {
        this.driver = driver;
    }

    //By serviceLocator = By.xpath("//div//ul/li/a/h5[text()='Services']");
    By serviceLocator = By.xpath("//h5[text()='Services']");
    By agricultureLocator = By.xpath("//div[contains(@class, 'ui-lib-link-card__description') and contains(text(), 'Farms, Livestock and Animals')]");
    By agricultureTextLocator = By.xpath("//div//h1[text()='Agriculture & Livestock']");
    By cookieTrayButtonLocator = By.xpath("//button[@aria-label='Accept']");////.ui-lib-cookie-tray button
    By otherButtonLocator = By.cssSelector(".ui-lib-button");
    By workEducationLocator = By.xpath("//div[text()='Work & Education']");
    By typeLocator = By.xpath("//button[@aria-label='button']/div[text()='Type']");
    By emiratiLocator = By.xpath("//label[@for='Emirati']//span[@class='ui-lib-checkbox__label-text' and starts-with(text(), 'Emirati')]");
    By calculatePreviousServiceAmountLocator= By.xpath("//div[text()='Calculate Previous Service Amount']");
    By calculatePreviousServiceAmountTextLocator=By.xpath("//div//h1[text()='Calculate Previous Service Amount']");
    By popupLocator = By.xpath("//div[text()='Done']");
    By dropdownLocator = By.xpath("//label[text()='ADD SERVICE PERIOD']//parent::div//following-sibling::div/div/button[@aria-label='button']");
    By addPeriodOnebyOneLocator = By.xpath("//div[text()='Add Period one by one']");
    By calenderIconLocator = By.xpath("//label[text()='From Date']//parent::div//following-sibling::div//div//button");
    By clickYearLocator = By.xpath("//label[text()='From Date']//parent::div//following-sibling::div[2]//div/div/div/div//div/button[2]");
    By clickMonthIcon = By.xpath("//label[text()='From Date']//parent::div//following-sibling::div//div[2]//div//button[1]");
    By addButtonLocator = By.xpath("//div[text()='Add']");
    By amountLocator = By.xpath("//input[@id='amount']");
    By calculateButtonLocator = By.xpath("//button//div[text()='Calculate']");
    By nextButtonLocator = By.xpath("//div[text()='Next']");
    By provideFeedbackLocator = By.xpath("//textarea[@aria-label='Text area']");
    By confirmButtonLocator = By.xpath("//div[text()='Confirm']");
    By My_TammButtonLocator = By.xpath("//span[text()='My TAMM']");

    By thankYouTextLocator = By.xpath("//h4[text()='Thank you for your feedback!']");
    private By fromDateCalenderIconLocator = By.xpath("//label[text()='From Date']//parent::div//following-sibling::div//div//button");
    private By fromDateClickYearLocator = By.xpath("//label[text()='From Date']//parent::div//following-sibling::div[2]//div/div/div/div//div/button[2]");
    private By fromDateClickMonthIcon = By.xpath("//label[text()='From Date']//parent::div//following-sibling::div//div[2]//div//button[1]");
    private By fromDatePreviousYearIconLocator = By.xpath("//label[text()='From Date']//parent::div//following-sibling::div[2]//div/div//following-sibling::div/div//div//following-sibling::div//div//button[1]");
    private By fromDateNextYearIconLocator = By.xpath("//label[text()='From Date']//parent::div//following-sibling::div[2]//div/div//following-sibling::div/div//div//following-sibling::div//div//button[2]");
    private By toDateCalenderIconLocator = By.xpath("//label[text()='To Date']//parent::div//following-sibling::div//div//button");
    private By toDateClickYearLocator = By.xpath("//label[text()='To Date']//parent::div//following-sibling::div[2]//div/div/div/div//div/button[2]");
    private By toDateClickMonthIcon = By.xpath("//label[text()='To Date']//parent::div//following-sibling::div//div[2]//div//button[1]");
    private By toDatePreviousYearIconLocator = By.xpath("//label[text()='To Date']//parent::div//following-sibling::div[2]//div/div//following-sibling::div/div//div//following-sibling::div//div//button[1]");
    private By toDateNextYearIconLocator = By.xpath("//label[text()='To Date']//parent::div//following-sibling::div[2]//div/div//following-sibling::div/div//div//following-sibling::div//div//button[2]");
    private By dateOfBirthCalenderIconLocator = By.xpath("//label[text()='Date of Birth']//parent::div//following-sibling::div//div//div//button");
    private By dateOfBirthClickYearLocator = By.xpath("//label[text()='Date of Birth']//parent::div//following-sibling::div//div/div/div/div/div/button[2]");
    private By dateOfBirthClickMonthIcon = By.xpath("//label[text()='Date of Birth']//parent::div/following-sibling::div/div/div/div/div/div/button[1]");
    private By dateOfBirthPreviousYearIconLocator = By.xpath("//label[text()='Date of Birth']//parent::div//following-sibling::div[2]//div/div//following-sibling::div/div//div//following-sibling::div//div[1]//button[1]");
    private By dateOfBirthNextYearIconLocator = By.xpath("//label[text()='Date of Birth']//parent::div//following-sibling::div[2]//div/div//following-sibling::div/div//div//following-sibling::div//div[2]//button[1]");
    private By pensionCalculatorLocator = By.xpath("//div[text()='Pension Calculator']");
    private By pensionCalculatorTextLocator=By.xpath("//h1[text()='Pension Calculator']");
////h4[text()='Rate Your Experience']/parent::*/parent::*/parent::*/div[2]/div[1]/div[1]//div[2]/div[1]//*[local-name()='svg' and @id='smiley_4']
    private By Gender=By.xpath("//div[text()='Male']");
    private By numberOfYearsLocator=By.xpath("//label[text()='Number of Years']//parent::div/following-sibling::div/div/input");
    private By numberOfMonthsLocator=By.xpath("//label[text()='Number of Months']//parent::div/following-sibling::div/div/input");
    private By numberOfDaysLocator=By.xpath("//label[text()='Number of Days']//parent::div/following-sibling::div/div/input");
    private By lastpesionsalry=By.xpath("//label[text()='Last Pensionable Salary']//parent::div/following-sibling::div/div/input");
    private By avgsalrypension=By.xpath("//label[text()='Average Pensionable Salary']//parent::div/following-sibling::div/div/input");
    private By BussinessTextLocator=By.xpath("//div[text()='Business']");
    private By StartBusinessTextLocator=By.xpath("//div[text()='Start a Business']");
    private By StartaBusinessTextLocator=By.xpath("//div[text()='Start a Business']");
    private By StartaJourneyTextLocator=By.xpath("//h4[text()='Start a journey with new or existing licence']");
    private By CommercialTextLocator=By.xpath("//h4[text()='Commercial']");
    private By ProceedtoJourneyIconLocator=By.xpath("//button[text()='PROCEED TO JOURNEY']");
    private By comparisonToolLocator=By.xpath("//div[text()='Comparison Tool']");
    private By LicenceTextLocator=By.xpath("//div[text()='Licences']");
    private By Compare=By.xpath("//div[text()='Compare']");


    public void clickService() {
        closeCookieTray(); // Close the cookie tray before clicking
        //waitForElementToBeClickable(serviceLocator);
        // clickObject(serviceLocator);
        WebElement serviceElement = driver.findElement(serviceLocator);
        retryFindElement(serviceLocator);
        clickElement(serviceElement);

    }

    public void clickAgricutlure() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(60));
        //scrollToElement(agricultureLocator); // Scroll to Agriculture button
        wait.until(ExpectedConditions.presenceOfElementLocated(agricultureLocator));
        driver.findElement(agricultureLocator).click();

    }
    public void closeCookieTray() {
        try {

            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(60));
            clickElementUsingJavaScript(driver.findElement(cookieTrayButtonLocator));

            clickElementUsingJavaScript(driver.findElement(otherButtonLocator));

        } catch (Exception e) {
            // Handle exceptions if the cookie tray or other elements are not found
          //  e.printStackTrace();
        }
    }

    public void scrollToTop() {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("window.scrollTo(0, 0);");
    }

    public boolean isAgricultureTextDisplayed() {
        scrollToTop();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(1000));
        try {
            WebElement agricultureText = wait.until(ExpectedConditions.visibilityOfElementLocated(agricultureTextLocator));
            return agricultureText.isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    public void scrollToElement(By locator) {
        WebElement element = driver.findElement(locator);
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", element);
    }

    public void clickWorkEducation() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(60));
        wait.until(ExpectedConditions.presenceOfElementLocated(workEducationLocator));
        driver.findElement(workEducationLocator).click();

    }

    public void clickType() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(60));
        wait.until(ExpectedConditions.presenceOfElementLocated(typeLocator));
        driver.findElement(typeLocator).click();
    }
    public void clickEmirati() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(60));
        wait.until(ExpectedConditions.presenceOfElementLocated(emiratiLocator));
        driver.findElement(emiratiLocator).click();

    }
    public void clickCalculatePreviousServiceAmount() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(60));
        wait.until(ExpectedConditions.presenceOfElementLocated(calculatePreviousServiceAmountLocator));
        driver.findElement(calculatePreviousServiceAmountLocator).click();

    }
    public boolean iscalculatePreviousServiceAmountTextDisplayed() {
        scrollToTop();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(1000));
        try {
            WebElement agricultureText = wait.until(ExpectedConditions.visibilityOfElementLocated(calculatePreviousServiceAmountTextLocator));
            return agricultureText.isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    public void clickPopup() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(60));
        wait.until(ExpectedConditions.presenceOfElementLocated(popupLocator));
        driver.findElement(popupLocator).click();

    }
    public void clickDropdownicon() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(60));
        wait.until(ExpectedConditions.presenceOfElementLocated(dropdownLocator));
        driver.findElement(dropdownLocator).click();

    }
    public void clickAddPeriodOnebyOne() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(60));
        wait.until(ExpectedConditions.presenceOfElementLocated(addPeriodOnebyOneLocator));
        driver.findElement(addPeriodOnebyOneLocator).click();

    }
    // Date Picker methods

    public void selectFromDate(String inputDate) {
        selectDate(inputDate, fromDateCalenderIconLocator, fromDateClickYearLocator, fromDateClickMonthIcon, fromDatePreviousYearIconLocator, fromDateNextYearIconLocator);
    }

    public void selectToDate(String inputDate) {
        selectDate(inputDate, toDateCalenderIconLocator, toDateClickYearLocator, toDateClickMonthIcon, toDatePreviousYearIconLocator, toDateNextYearIconLocator);
    }

    public void clickAddButton() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(60));
        wait.until(ExpectedConditions.presenceOfElementLocated(addButtonLocator));
        driver.findElement(addButtonLocator).click();

    }
    public void clickAmount() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(60));
        wait.until(ExpectedConditions.presenceOfElementLocated(amountLocator));
        driver.findElement(amountLocator).click();

    }
    public void enterAmount(String amount){
        WebElement amountElement = driver.findElement(amountLocator);
        amountElement.clear();
        enterKeys(amountElement, amount);
    }

    public void clickCalculate() {
        WebElement calculate = findElement(calculateButtonLocator);
        //scrollIntoView(calculate);
        clickElementUsingJavaScript(calculate);

    }
    public By getSmileyLocator1(String smileyNumber) {
        String xpathExpression = String.format("//h4[text()='Rate Your Experience']/parent::*/parent::*/parent::*/div[2]/div[1]/div[1]//div[2]/div[1]//*[local-name()='svg' and @id='smiley_%s']", smileyNumber);
        return By.xpath(xpathExpression);
    }
    public void clickOnSmileyIcon1(String smileyNumber) {
        By smileExpressionLocator = getSmileyLocator1(smileyNumber);
        retryFindElement(smileExpressionLocator);
        WebElement smiley = driver.findElement(smileExpressionLocator);

        // Debugging information
        System.out.println("Element tag name: " + smiley.getTagName());
        //System.out.println("Element attributes: " + smile.getAttribute("outerHTML"));
        //scrollIntoView(smiley);
        //clickElement(smiley);
        //clickElementUsingJavaScript(smiley);
        smiley.click();

        System.out.println( );
    }
    public By getSmileyLocator2(String smileyNumber) {
        String xpathExpression = String.format("//h4[text()='Rate Your Experience']/parent::*/parent::*/parent::*/div[2]/div[1]/div[2]/div[2]/div[1]//*[local-name()='svg' and @id='smiley_%s']", smileyNumber);
        return By.xpath(xpathExpression);
    }

    public void clickOnSmileyIcon2(String smileyNumber) {
        By smileExpressionLocator = getSmileyLocator2(smileyNumber);
        retryFindElement(smileExpressionLocator);
        WebElement smiley = driver.findElement(smileExpressionLocator);

        // Debugging information
        System.out.println("Element tag name: " + smiley.getTagName());
        //System.out.println("Element attributes: " + smile.getAttribute("outerHTML"));
        //scrollIntoView(smiley);
         //clickElement(smiley);
        //clickElementUsingJavaScript(smiley);
         smiley.click();

        System.out.println( );
    }

    public void clickNextButton(){
        WebElement next = findElement(nextButtonLocator);
        scrollIntoView(next);
        clickElement(next);
    }
    public void selectFeedbackReason(String reason) {
        System.out.println("User provided feedback reason: " + reason);
        WebElement reasonElement = findElementByReason(reason);
        scrollIntoView(reasonElement);
        clickElement(reasonElement);
    }

    // This method finds an element dynamically based on the reason
    public WebElement findElementByReason(String reason) {
        String xpath = String.format("//div[text()='%s']", reason);
        return driver.findElement(By.xpath(xpath));
    }
    public void provideFeedbackReason(String comment) {

        WebElement reasonElement = driver.findElement(provideFeedbackLocator);
        enterKeys(reasonElement, comment);
    }

    public void clickConfirmButton(){
        WebElement confirm = findElement(confirmButtonLocator);
        scrollIntoView(confirm);
        clickElement(confirm);
    }
    public boolean isThankyouTextDisplayed() {
        scrollToTop();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(60));
        try {
            WebElement thankYouText = wait.until(ExpectedConditions.visibilityOfElementLocated(thankYouTextLocator));
            return thankYouText.isDisplayed();
        } catch (Exception e) {
            System.out.println("Exception caught: " + e.getMessage());
            return false;
        }
    }
    public void clickMyTamm() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(2000));
        WebElement myTamm = wait.until(ExpectedConditions.presenceOfElementLocated(My_TammButtonLocator));
        scrollIntoView(myTamm);
        clickElement(myTamm);
    }
    private void selectDate(String inputDate, By calenderIconLocator, By clickYearLocator, By clickMonthIcon, By previousYearIconLocator, By nextYearIconLocator) {
        scrollToTop();
        // Example inputDate format: "MM/dd/yyyy"
        String[] dateParts = inputDate.split("/");
        int month = Integer.parseInt(dateParts[0]);
        int day = Integer.parseInt(dateParts[1]);
        int year = Integer.parseInt(dateParts[2]);

        // Click on the calendar icon to open the date picker
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(60));
        WebElement calendarIcon = retryFindElement(calenderIconLocator);
        calendarIcon.click();
        // Click the year button to open the year list
        WebElement yearButton = retryFindElement(clickYearLocator);
        yearButton.click();

        boolean yearFound = false;
        while (!yearFound) {
            try {


                // Check if the desired year is present in the list
                List<WebElement> yearElements = driver.findElements(By.xpath("//table//tr//td[@role='gridcell' and @title='" + year + "']"));
                if (!yearElements.isEmpty()) {
                    for (WebElement yearElement : yearElements) {
                        if (Integer.parseInt(yearElement.getText()) == year) {
                            yearElement.click();
                            yearFound = true;
                            break;
                        }
                    }
                }

                // If the desired year is not found, navigate to the previous or next year
                if (!yearFound) {
                    WebElement yearList = retryFindElement(By.xpath("//table//tr//td[@role='gridcell']"));
                    int displayedYear = Integer.parseInt(yearList.getText());

                    if (year < displayedYear) {
                        WebElement prevYearButton = retryFindElement(previousYearIconLocator);
                        prevYearButton.click();
                    } else if (year > displayedYear) {
                        WebElement nextYearButton = retryFindElement(nextYearIconLocator);
                        nextYearButton.click();
                    }
                }
            } catch (StaleElementReferenceException e) {
                // Handle stale element exception by retrying
            }
        }
        System.out.println("Selected Year: " + year);

        // Click month icon to open month selector
        WebElement monthIcon = retryFindElement(clickMonthIcon);
        monthIcon.click();

        // Locate and click desired month
        By desiredMonthLocator = By.xpath("//table//tr//td[@role='gridcell' and @title='" + getMonthTitle(month) + "']");
        WebElement desiredMonthElement = retryFindElement(desiredMonthLocator);
        desiredMonthElement.click();
        System.out.println("Selected Month: " + getMonthTitle(month));

        // Locate and click desired day
        By desiredDayLocator = By.xpath("//table//tr//td[@role='gridcell' and not(contains(@class, 'ui-lib-dp-calendar-last-month-day')) and not(contains(@class, 'ui-lib-dp-calendar-next-month-day')) and @title='" + day + "']");

       // By desiredDayLocator = By.xpath("//table//tr//td[@role='gridcell' and text()='" + day + "']");

                retryFindElement(desiredDayLocator);
        WebElement desiredDayElement = driver.findElement(desiredDayLocator);
                desiredDayElement.click();
        System.out.println("Selected Day: " + day);
    }

    // Utility method to get month title based on month number
    private String getMonthTitle(int month) {
        switch (month) {
            case 1: return "January";
            case 2: return "February";
            case 3: return "March";
            case 4: return "April";
            case 5: return "May";
            case 6: return "June";
            case 7: return "July";
            case 8: return "August";
            case 9: return "September";
            case 10: return "October";
            case 11: return "November";
            case 12: return "December";
            default: throw new IllegalArgumentException("Invalid month: " + month);
        }
    }

    // Retry method to handle stale elements
    private WebElement retryFindElement(By locator) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(60));
        int attempts = 0;
        while (attempts < 3) {
            try {
                return wait.until(ExpectedConditions.presenceOfElementLocated(locator));
            } catch (StaleElementReferenceException e) {
                attempts++;
            }
        }
        throw new NoSuchElementException("Element not found: " + locator.toString());
    }

    public void clickPensionCalculator() {
        WebElement calculator = findElement(pensionCalculatorLocator);
        scrollIntoView(calculator);
        clickElement(calculator);
    }
    public boolean isPensionTextDisplayed(String text) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(1000));
        try {
            WebElement pensioncalculatorText = wait.until(ExpectedConditions.visibilityOfElementLocated(pensionCalculatorTextLocator));
            return pensioncalculatorText.isDisplayed();
        } catch (Exception e) {
            return false;
        }

    }
    public void selectDateOfBirth(String inputDate) {
        selectDate(inputDate, dateOfBirthCalenderIconLocator, dateOfBirthClickYearLocator, dateOfBirthClickMonthIcon, dateOfBirthPreviousYearIconLocator, dateOfBirthNextYearIconLocator);
    }
    public void gender(){
        WebElement  gender= findElement(Gender);
        scrollIntoView(gender);
        clickElement(gender);
    }
    public void years(){
        WebElement enteryear= findElement(numberOfYearsLocator);
        scrollIntoView(enteryear);
        enteryear.sendKeys("7");
    }
    public void months(){
        WebElement months= findElement(numberOfMonthsLocator);
        scrollIntoView(months);
        months.clear();
        months.sendKeys("5");
    }
    public void Days(){
        WebElement  days= findElement(numberOfDaysLocator);
        scrollIntoView(days);
        days.clear();
        days.sendKeys("15");
    }

    public void enterpensionsalary(String amount){
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(60));
        WebElement amountElement = driver.findElement(lastpesionsalry);
        scrollIntoView(amountElement);
        amountElement.clear();
        enterKeys(amountElement, amount);
    }

    public void enteravgsalary(String amount){
        WebElement amountElement = driver.findElement(avgsalrypension);
        amountElement.clear();
        scrollIntoView(amountElement);
        enterKeys(amountElement, amount);
    }
    public void Business() {
        WebElement Business = findElement(BussinessTextLocator);
        scrollIntoView(Business);
        clickElement(Business);
    }
    public void StartBusiness(){
        WebElement StartBusiness = findElement(StartBusinessTextLocator);
        scrollIntoView(StartBusiness);
        clickElement(StartBusiness);
    }
    public void StartaBusiness() {
        WebElement StartaBussiness = findElement(StartaBusinessTextLocator);
        scrollIntoView(StartaBussiness);
        clickElement(StartaBussiness);
    }
    public void StartJourney() {
        WebElement startjourney = findElement(StartaJourneyTextLocator);
        scrollIntoView(startjourney);
        clickElement(startjourney);
    }
    public void Commercial() {
        WebElement commercial = findElement(CommercialTextLocator);
        scrollIntoView(commercial);
        clickElement(commercial);
    }
    public void ProceedButton() {
        WebElement Proceed = findElement(ProceedtoJourneyIconLocator);
        // scrollIntoView(Proceed);
        Proceed.click();
        // clickElementUsingJavaScript(Proceed);
    }
    public void ComparisonTool() {
        WebElement comparison = findElement(comparisonToolLocator);
        // scrollIntoView(comparison);
        clickElementUsingJavaScript(comparison);

    }
    public void LicenceTex1() throws InterruptedException {
        //Thread.sleep(20000);
        Thread.sleep(2000);
        WebElement licence  = findElement(LicenceTextLocator);
        //  scrollIntoView(licence);
        licence.click();
        //licence.click();
    }
    public void selectLicenseCheckBox(String boxTitle){
        List<WebElement> allBoxes = driver.findElements(By.xpath("//form[@class='ui-lib-form qa-form']/div/div/div/div/div"));

        for(int i=1;i<=allBoxes.size();i++){

            String uiBoxTitle = driver.findElement(By.xpath("//form[@class='ui-lib-form qa-form']/div/div/div/div/div["+i+"]/div/div/div/div[1]/div/div/div/h5")).getText();

            System.out.println("uiBoxTitle "+ uiBoxTitle );

            if (uiBoxTitle.equals(boxTitle)){

                System.out.println("uiBoxTitle >>>> #### "+ uiBoxTitle );

                clickElement(driver.findElement(By.xpath("//form[@class='ui-lib-form qa-form']/div/div/div/div/div["+i+"]/div/div/div/div[3]/div/div/div/div/label/span[1]")));
                break;
            }
        }
    }
    public void Compar(){
        WebElement comp = findElement(Compare);
        scrollIntoView(comp);
        clickElement(comp);
    }


}
