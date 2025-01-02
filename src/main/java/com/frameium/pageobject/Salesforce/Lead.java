package com.frameium.pageobject.Salesforce;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import com.frameium.genericfunctions.GenericFunctions;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.w3c.dom.html.HTMLInputElement;

import java.time.Duration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * This class encapsulates the functionality for managing leads in Salesforce.
 */
public class Lead extends GenericFunctions {

    // Locators for various elements on the lead page

    private By saveButton = By.xpath("//button[contains(@name,'SaveEdit')]");
    private By leadsButton = By.xpath("/html/body/div[4]/div[1]/section/div[1]/div[1]/one-appnav/div/one-app-nav-bar/nav/div/one-app-nav-bar-item-root[3]");
    private By firstName = By.xpath("//input[@placeholder='First Name']");
    private By lastName = By.xpath("//input[@placeholder='Last Name']");
    private By company = By.xpath("//input[@name='Company']");
    private By eMail = By.xpath("//input[@inputmode='email']");
    private By appLauncher =By.xpath("//button[@title='App Launcher']");
    private By viewAll = By.xpath("//button[text()='View All']");
    private By salesSelect = By.xpath("//p[text()='Sales']");
    private By newButton = By.xpath("//*[@id=\"brandBand_1\"]/div/div/div/div/div[1]/div[1]/div[2]/ul/li[1]/a");
    private By sal = By.xpath("//button[contains(@aria-label,'Salutation')]");
    private By sal2 = By.xpath("//*[contains(@data-value,'Mx.')]");
    private By leadsrc = By.xpath("//button[contains(@aria-label,'Lead Source')]");
    private By leadsrc1 = By.xpath("//*[contains(@data-value,'Web')]");
    private By prim = By.xpath("//records-record-layout-item[@field-label='Primary']/div/span/slot/records-record-picklist/records-form-picklist/lightning-picklist/lightning-combobox/div/div/lightning-base-combobox/div/div/div[1]");
    private By prim1 = By.xpath("//*[contains(@title,'Yes')]");
    private By newTask = By.xpath("//button[contains(@aria-label,'New Task')]");
    private By clickSubject = By.xpath("//*[contains(@id,'combobox-input') and contains(@class,'slds-combobox__input')]");
    private By selectASubject = By.xpath("//*[contains(@data-value,'Call')]");
    private By clickDate = By.xpath("//lightning-input[@class='inputDate slds-form-element']//input[contains(@class, 'slds-input')]");
    private By clickYear = By.xpath("//div[@class='slds-select_container']//select");
    private By selectMonth = By.xpath("//h2[contains(@class, 'slds-align-middle')]");
    private By previousMonthButton = By.xpath("//button[@title='Previous Month']");
    private By clickSaveNewTask = By.xpath("//button[@class='slds-button slds-button--brand cuf-publisherShareButton uiButton']//span[text()='Save']");
    private By clickDetails = By.xpath("//a[@data-tab-value='detailTab']");
    private By leadStatusPencilIcon = By.xpath("//button[contains(@title,'Edit Lead Status')]");
    private By leadStatusBox = By.xpath("//button[contains(@aria-label,'Lead Status - Current Selection: Open - Not Contacted')]");
    private By noOfEmployeesPencilIcon = By.xpath("//button[contains(@title,'Edit No. of Employees')]");
    private By noOfEmployeesBox = By.xpath("//input[@name='NumberOfEmployees']");
    private By nextMonthButton = By.xpath("//button[@title='Next Month']");
    private By oppo = By.xpath("/html/body/div[4]/div[1]/section/div[1]/div[1]/one-appnav/div/one-app-nav-bar/nav/div/one-app-nav-bar-item-root[2]");
    private By close = By.xpath("//button[@title='Close this window']");

    private WebDriver driver;

    /**
     * Constructor for lead class.
     *
     * @param driver The WebDriver instance to interact with the browser.
     */
    public Lead(WebDriver driver) {

        this.driver = driver;
    }

    /**
     * Opens the app launcher and selects the Sales page.
     * @throws InterruptedException if interrupted while performing the app launcher action.
     */
    public void appLauncherSearch() throws InterruptedException {

        clickElement(appLauncher);
        Thread.sleep(5000);
        clickElement(viewAll);
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(1000));
        WebElement sales = wait.until(ExpectedConditions.presenceOfElementLocated(salesSelect));
        clickElement(sales);

    }

    /**
     * Clicks on the Leads button to navigate to the Leads section.
     */
    public void leadslogin() {

        clickElement(leadsButton);
    }

    /**
     * Clicks on the New button to create a new lead.
     */
    public void clickNew() {

        clickElement(newButton);
    }

    /**
     * Creates a new lead with specified details and verifies its creation.
     *
     * @return The name of the created lead.
     */
    public String createAndVerifyLead() {
        createNewLead("John", "Thomas", "GetCloudy Logistics", "john.thomas@getcloudy.com");

        clickSave();
        waitForByElement(By.xpath("//div[@class='slds-media__body']//h1//div//following-sibling::slot//lightning-formatted-name[@slot='primaryField']"));

        return driver.findElement(By.xpath("//div[@class='slds-media__body']//h1//div//following-sibling::slot//lightning-formatted-name[@slot='primaryField']")).getText();

    }

    /**
     * Creates a new lead with provided first name, last name, company, and email.
     *
     * @param fname The first name of the lead.
     * @param lname The last name of the lead.
     * @param cmpny The company name of the lead.
     * @param email The email address of the lead.
     *
     */

    public void createNewLead(String fname, String lname, String cmpny, String email) {
        enterKeys(firstName, fname);
        selectSalutation();
        enterKeys(lastName, lname);
        enterKeys(company, cmpny);
        enterKeys(eMail, email);
        selectLeadSource();
        selectPrimary();
    }

    /**
     * Selects a salutation for the lead.
     */
    public void selectSalutation() {
        //  WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(2000));
        WebElement salutation = driver.findElement(sal);
        salutation.click();
        WebElement salutation2 = driver.findElement(sal2);

        salutation2.click();
    }

    /**
     * Selects the lead source option for the lead.
     */
    public void selectLeadSource() {
        WebElement leadSource = driver.findElement(leadsrc);
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", leadSource);

        WebElement leadSource1 = driver.findElement(leadsrc1);
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", leadSource1);
    }

    /**
     * Selects the primary option for the lead.
     */
    public void selectPrimary() {
        WebElement primary = driver.findElement(prim);
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", primary);

        WebElement primary1 = driver.findElement(prim1);
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", primary1);

    }

    /**
     * Clicks the Save button to save the lead.
     */
    public void clickSave() {

        clickElement(saveButton);
    }

    /**
     * Clicks the New Task button to create a new task.
     */
    public void clickNewTask() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(3000));
        WebElement task = wait.until(ExpectedConditions.presenceOfElementLocated(newTask));
        task.click();
    }

    /**
     * Fills in the task details with the specified subject and date.
     *
     * @param call The subject of the task.
     * @param date The date for the task in "dd-MM-yyyy" format.
     * @throws InterruptedException if interrupted while performing the task detail action.
     */
    public void fillTaskDetails(String call, String date) throws InterruptedException {
        selectSubject(call);
        enterDate(date);
    }

    /**
     * Selects the subject for the task.
     *
     * @param call The subject of the task.
     */
    private void selectSubject(String call) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(2000));
        WebElement subject = wait.until(ExpectedConditions.presenceOfElementLocated(clickSubject));
        subject.click();
        WebElement subject_2 = driver.findElement(selectASubject);
        subject_2.click();
    }

    /**
     * Enters the specified date for the task.
     *
     * @param quoteD The date string in "dd-MM-yyyy" format.
     * @throws InterruptedException if interrupted while performing the date entry action.
     */
    private void enterDate(String quoteD) throws InterruptedException {
        // Split the date string to extract day, month, and year
        String[] parts = quoteD.split("-");
        String day = parts[0];
        String month = parts[1];
        String year = parts[2];

        // Click on the date input field to open the date picker
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(2000));
        WebElement dateEnter = wait.until(ExpectedConditions.presenceOfElementLocated(clickDate));
        dateEnter.click();
        Thread.sleep(1000);

        // Click on the year dropdown to open the dropdown menu
        WebElement yearDropdown = wait.until(ExpectedConditions.elementToBeClickable(clickYear));
        yearDropdown.click();

        // Select the year
        By selectYear = By.xpath("//div[@class='slds-select_container']//select[@class='slds-select']/option[@value='" + year + "']");
        WebElement yearDropdownOption = wait.until(ExpectedConditions.presenceOfElementLocated(selectYear));
        yearDropdownOption.click();

        // Select the month
        Thread.sleep(1000);
        WebElement monthDropdown = driver.findElement(selectMonth);
        String currentMonth = monthDropdown.getText();
        while (!currentMonth.equalsIgnoreCase(month)) {
            // Check if we need to navigate to the previous or next month
            int currentMonthIndex = getMonthIndex(currentMonth);
            int targetMonthIndex = getMonthIndex(month);

            if (currentMonthIndex > targetMonthIndex) {
                driver.findElement(previousMonthButton).click();
            } else {
                driver.findElement(nextMonthButton).click();
            }

            currentMonth = monthDropdown.getText();
        }

        // Select the day
        Thread.sleep(3000);

        int monthNumber = getMonthInNum(month);
        String monthNum = monthNumber < 10 ? "0" + monthNumber : String.valueOf(monthNumber);
        String fDate = year + "-" + monthNum + "-" + day;
        System.out.println("fDate " + fDate);

        //driver.findElement(By.xpath("//table[contains(@class,'datepicker')]/tbody/tr/td[@data-value='" + fDate + "']")).click();
        WebElement dateElement = driver.findElement(By.xpath("//table[contains(@class,'datepicker')]/tbody/tr/td[@data-value='" + fDate + "']"));
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", dateElement);
        wait.until(ExpectedConditions.elementToBeClickable(dateElement)).click();
    }

    /**
     * Returns the numeric representation of a month given its name.
     *
     * @param month The name of the month.
     * @return The month number as an integer (01 for January, 12 for December).
     */
    private int getMonthInNum(String month) {
        // Create a mapping of month names to their corresponding numbers
        Map<String, String> monthMap = new HashMap<>();
        monthMap.put("January", "01");
        monthMap.put("February", "02");
        monthMap.put("March", "03");
        monthMap.put("April", "04");
        monthMap.put("May", "05");
        monthMap.put("June", "06");
        monthMap.put("July", "07");
        monthMap.put("August", "08");
        monthMap.put("September", "09");
        monthMap.put("October", "10");
        monthMap.put("November", "11");
        monthMap.put("December", "12");

        // Return the month number corresponding to the given month name
        return Integer.parseInt(monthMap.get(month));
    }

    /**
     * Returns the index of a month given its name.
     *
     * @param monthName The name of the month.
     * @return The index of the month (0 for January, 11 for December).
     */
    private int getMonthIndex(String monthName) {
        String[] months = {"January", "February", "March", "April", "May", "June", "July", "August", "September", "October", "November", "December"};
        for (int i = 0; i < months.length; i++) {
            if (months[i].equalsIgnoreCase(monthName)) {
                return i;
            }
        }
        return -1;
    }

    /**
     * Saves the created task.
     */
    public void saveNewTask() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(1000));
        WebElement saveButton = wait.until(ExpectedConditions.presenceOfElementLocated(clickSaveNewTask));
        JavascriptExecutor executor = (JavascriptExecutor) driver;
        executor.executeScript("arguments[0].click();", saveButton);
    }

    /**
     * Changes the status of the lead to the specified status.
     *
     * @param leadStatus The new status for the lead.
     */
    public void changeLeadStatus(String leadStatus) {
        navigateToDetail();
        editLeadStatus(leadStatus);
        clickSaveIcon();
    }

    /**
     * Clicks the save icon to save the changes made to the lead status.
     */
    private void clickSaveIcon() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement leadStatusChanged = wait.until(ExpectedConditions.presenceOfElementLocated(saveButton));
        JavascriptExecutor executor = (JavascriptExecutor) driver;
        executor.executeScript("arguments[0].click();", leadStatusChanged);
    }

    /**
     * Edits the lead status to the specified new status.
     *
     * @param leadStatusUpdate The new status for the lead.
     */
    private void editLeadStatus(String leadStatusUpdate) {
        By selectLeadStatusWorking = By.xpath("//lightning-base-combobox-item[@class='slds-media slds-listbox__option slds-media_center slds-media_small slds-listbox__option_plain']//span[@class='slds-truncate' and text()='" + leadStatusUpdate + "']");

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(1000));
        WebElement leadStatus = wait.until(ExpectedConditions.presenceOfElementLocated(leadStatusPencilIcon));
        JavascriptExecutor executor = (JavascriptExecutor) driver;
        executor.executeScript("arguments[0].click();", leadStatus);
        WebElement leadStatus_1 = wait.until(ExpectedConditions.presenceOfElementLocated(leadStatusBox));
        executor.executeScript("arguments[0].click();", leadStatus_1);
        WebElement leadStatusChange = wait.until(ExpectedConditions.presenceOfElementLocated(selectLeadStatusWorking));
        executor.executeScript("arguments[0].click();", leadStatusChange);
    }

    /**
     * Navigates to the details tab of the lead.
     */
    private void navigateToDetail() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(1000));
        WebElement details = wait.until(ExpectedConditions.presenceOfElementLocated(clickDetails));
        details.click();
    }

    /**
     * Updates the number of employees for the lead.
     *
     * @param number The new number of employees.
     */
    public void updateLeadDetails(String number) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(1000));
        WebElement noOfEmployees = wait.until(ExpectedConditions.presenceOfElementLocated(noOfEmployeesPencilIcon));
        JavascriptExecutor executor = (JavascriptExecutor) driver;
        executor.executeScript("arguments[0].click();", noOfEmployees);
        WebElement noOfEmployees_1 = wait.until(ExpectedConditions.presenceOfElementLocated(noOfEmployeesBox));
        noOfEmployees_1.sendKeys(number);
        clickSaveIcon();

    }

    /**
     * Clicks the Converted button to view converted leads.
     *
     * @throws InterruptedException if interrupted while performing the click action.
     */
    public void convertedButtonClick() throws InterruptedException {

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(4000));
        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("(//a[@data-tab-name='converted'])[1]")));
        WebElement convertedBtn = driver.findElement(By.xpath("(//a[@data-tab-name='converted'])[1]"));
        clickElement(convertedBtn);

    }

    /**
     * Clicks the Select Converted Status button to select the status of the converted lead.
     */
    public void selectConvertedStatusBtnClick() {

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(4000));
        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//span[text()='Select Converted Status']")));
        WebElement selectConvertedStatusButton = driver.findElement(By.xpath("//span[text()='Select Converted Status']"));
        clickElement(selectConvertedStatusButton);

    }

    /**
     * Clicks the Convert button to convert the lead.
     *
     * @throws InterruptedException if interrupted while performing the click action.
     */
    public void convertBtnClick() throws InterruptedException {

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(4000));
        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//button[contains(text(),'Convert')]")));
        WebElement convertBtn = driver.findElement(By.xpath("//button[contains(text(),'Convert')]"));
        Thread.sleep(2000);
        clickElement(convertBtn);
        Thread.sleep(2000);
    }

    /**
     * Clicks the Opportunities button to navigate to the Opportunities section.
     */
    public void oppClick() {
        clickElement(driver.findElement(oppo));
    }

    /**
     * Checks if the Working - Contacted lead status window is opened.
     *
     * @return true if the lead status window is displayed; false otherwise.
     */
    public boolean isWorkingContactedLeadStatusWindowOpened() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(1000));
        WebElement leadStatusElement = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//span[contains(@class, 'test-id__field-value') and .//lightning-formatted-text[text()='Working - Contacted']]")));

        return leadStatusElement.isDisplayed();
    }

    /**
     * Checks if the lead name is displayed.
     *
     * @param leadName The name of the lead to check.
     * @return true if the lead name is displayed; false otherwise.
     */
    public boolean isLeadNameDisplayed(String leadName) {
        String leadNameDisplay = "//span[@class='pillText' and text()='" + leadName + "']";
        try {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(2000));
            WebElement leadNameElement = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(leadNameDisplay)));
            return leadNameElement.isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    /**
     * Closes the lead converted window.
     */
    public void closeLeadConvertedWindow() {
        clickElementUsingJavaScript(driver.findElement(close));
    }

    /**
     * Checks if an element is present with a specified title.
     *
     * @param title The title to check for.
     * @return true if an element with the specified title is present; false otherwise.
     */
    public boolean isElementPresentWithTitle(String title) {
        List<WebElement> elements = driver.findElements(By.xpath("//a[contains(@title, '" + title + "')]"));
        return elements.stream().anyMatch(element -> element.getAttribute("title").contains(title));
    }
}