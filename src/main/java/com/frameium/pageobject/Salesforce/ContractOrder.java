package com.frameium.pageobject.Salesforce;

import com.frameium.genericfunctions.GenericFunctions;
import org.apache.poi.util.SystemOutLogger;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

/**
 * This class contains methods for interacting with the Contract and Order pages in Salesforce.
 */
public class ContractOrder extends GenericFunctions {
    // Locators for various elements on the lead page

    private By moreBtn = By.xpath("/html/body/div[4]/div[1]/section/div[1]/div[1]/one-appnav/div/one-app-nav-bar/nav/div/div");
    private By contractBtn = By.xpath("//one-app-nav-bar-menu-item//a[span/span[text()='Contracts']]");
 //   private By newContractBtn = By.xpath("//div[@data-aura-class='forceListViewManagerHeader']/div/div[2]/ul/li/a");
    private By status = By.xpath("//button[contains(@aria-label,'Status')]");
    private By newContractBtn = By.xpath("//li[@data-target-selection-name='sfdc:StandardButton.Contract.New']/a[@title='New' and @role='button']");
    private By status1 = By.xpath("//lightning-base-combobox-item[contains(@data-value,'Draft')]");
    private By contractTermField = By.xpath("//input[@name='ContractTerm']");
    private By contractClickDate = By.xpath("//input[@name='StartDate']");
    private By clickYear = By.xpath("//div[@class='slds-select_container']//select");
    private By selectMonth = By.xpath("//h2[contains(@class, 'slds-align-middle')]");
    private By nextMonthButton = By.xpath("//button[@title='Next Month']");
    private By previousMonthButton = By.xpath("//button[@title='Previous Month']");
    private By contractSaveBtn = By.xpath("//button[@class='slds-button slds-button_brand' and @name='SaveEdit']");
    private By orderBtn = By.xpath("//one-app-nav-bar-menu-item//a[span/span[text()='Orders']]");
    private By contractNumField = By.xpath("//input[@placeholder='Search Contracts...']");
    private By newOrderBtn = By.xpath("//div[@class='slds-col slds-no-flex slds-grid slds-align-top slds-p-bottom--xx-small test-lvmForceActionsContainer']/ul/li/a");
    private By orderClickDate = By.xpath("//input[@name='EffectiveDate']");
    private By orderSaveBtn = By.xpath("//button[@name='SaveEdit']");
    private By opportunityBtn = By.xpath("/html/body/div[4]/div[1]/section/div[1]/div[1]/one-appnav/div/one-app-nav-bar/nav/div/one-app-nav-bar-item-root[2]");
    //    private By clickDetails = By.xpath("//flexipage-component2[@data-component-id='flexipage_tabset']/slot/flexipage-tabset2/div/lightning-tabset/div/lightning-tab-bar/ul/li[2]//a[@class='slds-tabs_default__link' and @id='detailTab__item' and text()='Details' and @tabindex='-1' and @aria-controls='tab-16']");
 //   private By clickDetails = By.xpath("//*[text()='Details']");
    private By clickDetails = By.xpath("(//li[@title='Details']/a[@data-label='Details'])[1]");

    private By orderPencilIcon = By.xpath("//button[@title='Edit Order']");
    private By orderBox = By.xpath("//input[@placeholder='Search Orders...']");
    private By crossButton = By.xpath("//label[text()='Order']/parent::*/div/div/lightning-base-combobox/div/div/div[1]/div[2]/button");

    private By selectClosedStage = By.xpath("//button[contains(@class,'slds-button slds-button--brand slds-path__mark-complete stepAction active uiButton') and //span[text()='Select Closed Stage']]");
    private By saveStageChanged = By.xpath("//button[@title='Save' and @type='button' and contains(@class, 'slds-button slds-button--neutral uiButton--default uiButton--brand uiButton forceActionButton') and //span[text()='Save']]");
    private By orderSave = By.xpath("//button[@class='slds-button slds-button_brand' and @name='SaveEdit' and text()='Save']");

    //JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
    private By contractName = By.xpath("(//records-highlights-details-item[@style='max-width: 158px;' and @slot='secondaryFields' and @role='listitem' and @class='slds-page-header__detail-block'])[2]/div/p[@class='fieldComponent slds-text-body--regular slds-show_inline-block slds-truncate']/slot/force-lookup/div/records-hoverable-link/div/a/slot/slot/span");
    private By moreButton = By.xpath("//one-app-nav-bar-menu-button//a[span[text()='More']]");
    private By accNameOrder = By.xpath("//lightning-base-combobox-item[@data-value='actionAdvancedSearch']/following-sibling::ul/li");
    private By contractNumberLocator = By.xpath("//records-entity-label[text()='Contract']//parent::slot//parent::div//following-sibling::slot//lightning-formatted-text");
    private By OrderNumberLocator = By.xpath("//records-entity-label[text()='Order']//parent::slot//parent::div//following-sibling::slot//lightning-formatted-text");

    private By accNameContract = By.xpath("//lightning-base-combobox-item[@data-value='actionAdvancedSearch']/following-sibling::ul/li");
    private WebDriver driver;

    /**
     * Constructor to initialize the WebDriver.
     *
     * @param driver The WebDriver instance used for interacting with the web page.
     */
    public ContractOrder(WebDriver driver) {
        this.driver = driver;
    }

    /**
     * Navigates to the Contracts page and clicks the "New" button to create a new contract.
     *
     * @throws InterruptedException If the thread is interrupted while sleeping.
     */
    public void goToContractPage() throws InterruptedException {
        clickElement(moreBtn);
        Thread.sleep(1000);
        clickElement(driver.findElement(contractBtn));
        clickElement(newContractBtn);
    }

    /**
     * Creates a new contract with the specified account name, date, and contract term.
     *
     * @param accountname  The name of the account.
     * @param date         The start date of the contract.
     * @param contractTerm The term of the contract.
     * @throws InterruptedException If the thread is interrupted while sleeping.
     */
    public void createContract(String accountname, String date, String contractTerm) throws InterruptedException {
        enterAccountNameForContract(accountname);
        selectStatus();
        enterContractTerm(contractTerm);
        enterDateCalendar(contractClickDate, clickYear, selectMonth, previousMonthButton, nextMonthButton, date);
    }

    /**
     * Enters the account name in the contract creation form.
     *
     * @param account The account name.
     * @throws InterruptedException If the thread is interrupted while sleeping.
     */
    public void enterAccountNameForContract(String account) throws InterruptedException {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(4000));
        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//input[@placeholder='Search Accounts...']")));

        driver.findElement(By.xpath("//input[@placeholder='Search Accounts...']")).click();
        driver.findElement(By.xpath("//input[@placeholder='Search Accounts...']")).sendKeys(account);
        wait.until(ExpectedConditions.presenceOfElementLocated(accNameContract));
        Thread.sleep(2000);
        List<WebElement> searchResults = driver.findElements(accNameContract);

        for (WebElement result : searchResults) {

            String text = result.getText();
            System.out.println("text >> " + text);
            if (text.equals(account)) {
                result.click();
            }
        }
    }

    /**
     * Selects the status of the contract.
     */
    public void selectStatus() {
        WebElement statusField = driver.findElement(status);
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", statusField);

        WebElement statusField1 = driver.findElement(status1);
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", statusField1);

    }

    /**
     * Enters the contract term in the contract form.
     *
     * @param contractterm The term of the contract.
     */
    public void enterContractTerm(String contractterm) {

        clickElement(contractTermField).sendKeys(contractterm);
    }

    /**
     * Clicks the save button to save the contract.
     */
    public void contractClickSave() {
        clickElementUsingJavaScript(driver.findElement(contractSaveBtn));
    }

    /**
     * Retrieves the contract number.
     *
     * @return The contract number as a String.
     */
    public String getContractNumber() {
       //By contractNumberLocator = By.xpath("//lightning-formatted-text[@slot='primaryField']");
        waitForByElement(contractNumberLocator); // Assuming this waits for the element to be present/visible
        WebElement contractNumElement = driver.findElement(contractNumberLocator);
        String contractNum = contractNumElement.getText();
        System.out.println("The contract number is >>>>>>" + contractNum);
        return contractNum;
    }

    //  ***      ORDER     ****

    /**
     * Navigates to the Orders page and clicks the "New" button to create a new order.
     *
     * @throws InterruptedException If the thread is interrupted while sleeping.
     */
    public void goToOrderrPage() throws InterruptedException {
        clickElement(moreButton);
        Thread.sleep(1000);
        clickElement(driver.findElement(orderBtn));
        Thread.sleep(4000);
        clickElement(newOrderBtn);
        Thread.sleep(4000);
    }

    /**
     * Creates a new order with the specified order name, date, and contract number.
     *
     * @param ordername   The name of the order.
     * @param date        The effective date of the order.
     * @param contractNum The contract number associated with the order.
     * @throws InterruptedException If the thread is interrupted while sleeping.
     */
    public void createOrder(String ordername, String date, String contractNum) throws InterruptedException {
        // fillContractNum();
        enterAccountName(ordername);
        enterDateCalendar(orderClickDate, clickYear, selectMonth, previousMonthButton, nextMonthButton, date);
        fillContractNum(contractNum);
    }

    /**
     * Enters the account name in the order form.
     *
     * @param account The account name.
     * @throws InterruptedException If the thread is interrupted while sleeping.
     */
    public void enterAccountName(String account) throws InterruptedException {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(3000));
        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//input[@placeholder='Search Accounts...']")));

        driver.findElement(By.xpath("//input[@placeholder='Search Accounts...']")).click();
        driver.findElement(By.xpath("//input[@placeholder='Search Accounts...']")).sendKeys(account);
        wait.until(ExpectedConditions.presenceOfElementLocated(accNameOrder));
        Thread.sleep(2000);
        List<WebElement> searchResults = driver.findElements(accNameOrder);

        for (WebElement result : searchResults) {

            String text = result.getText();
            System.out.println("text >> " + text);
            if (text.equals(account)) {
                result.click();
            }
        }
    }

    /**
     * Fills the contract number field with the provided contract number and selects the corresponding result.
     *
     * @param contractNum The contract number to be filled in.
     * @throws InterruptedException If the thread is interrupted while sleeping.
     */
    public void fillContractNum(String contractNum) throws InterruptedException {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(2000));
        wait.until(ExpectedConditions.presenceOfElementLocated(contractNumField));
        clickElement(driver.findElement(contractNumField));
        driver.findElement(By.xpath("//input[@placeholder='Search Contracts...']")).click();
        // System.out.println( "???? : " + driver.findElements(By.xpath("//input[@placeholder='Search Contracts...']")).size() );

        System.out.println("contractNum ??? " + contractNum);
        driver.findElement(By.xpath("//input[@placeholder='Search Contracts...']")).sendKeys(contractNum);

    //    WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(4000));
        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//lightning-base-combobox-item[@data-value='actionAdvancedSearch']/following-sibling::ul/li/lightning-base-combobox-item/span/following-sibling::span/span/lightning-base-combobox-formatted-text")));
        Thread.sleep(2000);
        List<WebElement> Results = driver.findElements(By.xpath("//lightning-base-combobox-item[@data-value='actionAdvancedSearch']/following-sibling::ul/li/lightning-base-combobox-item/span/following-sibling::span/span/lightning-base-combobox-formatted-text"));

        for (WebElement result : Results) {

            String text = result.getText();
            System.out.println("text >> " + text);
            if (text.equals(contractNum)) {
                result.click();
            }
        }
        Thread.sleep(2000);
    }

    /**
     * Clicks the save button to save the order.
     */
    public void saveOrder() {
        clickElement(orderSaveBtn);
    }

    /**
     * Retrieves the order number.
     *
     * @return The order number as a String.
     */
    public String getOrderNumber() {
    //    By OrderNumberLocator = By.xpath("(//slot[@name='primaryField']/lightning-formatted-text[@slot='primaryField'])[2]");
        waitForByElement(OrderNumberLocator); // Assuming this waits for the element to be present/visible
        WebElement contractNumElement = driver.findElement(OrderNumberLocator);
        String OrderNum = contractNumElement.getText();
        System.out.println("The order number is >>>>>>" + OrderNum);
        return OrderNum;
    }

    /*****    Link order with the opportunity   *******/

    /**
     * Navigates to the Opportunities page and clicks the Details tab.
     */
    public void goToOpportunities() {
        clickElement(opportunityBtn);
    }

    /**
     * Enters the given contact in the search bar and performs a search.
     *
     * @param contact The contact name to be searched.
     * @throws InterruptedException If the thread is interrupted while sleeping.
     */
    public void enterSearchBar(String contact) throws InterruptedException {
        Thread.sleep(4000);
        //clickElement(searchbar);
        clickElement(driver.findElement(By.xpath("//button[@aria-label='Search']")));
        waitForByElement(By.xpath("//input[contains(@class, 'slds-input') and \n" +
                "       ((contains(@placeholder, 'Search Opportunities and more...') or contains(@placeholder, 'Search...')) and\n" +
                "       (contains(@type, 'search')) and \n" +
                "       (contains(@part, 'input')))]"));
        driver.findElement(By.xpath("//input[contains(@class, 'slds-input') and \n" +
                "       ((contains(@placeholder, 'Search Opportunities and more...') or contains(@placeholder, 'Search...')) and\n" +
                "       (contains(@type, 'search')) and \n" +
                "       (contains(@part, 'input')))]")).click();
        driver.findElement(By.xpath("//input[contains(@class, 'slds-input') and \n" +
                "       ((contains(@placeholder, 'Search Opportunities and more...') or contains(@placeholder, 'Search...')) and\n" +
                "       (contains(@type, 'search')) and \n" +
                "       (contains(@part, 'input')))]")).sendKeys(contact);
        Thread.sleep(4000);
    }

    /**
     * Verifies the search results for the given contact name and role, and clicks the matching result.
     *
     * @param contactName The name of the contact to verify in the search results.
     * @param role The role of the contact to verify in the search results.
     */
    public void verifySearchResutlsAndClick(String contactName, String role) {

        List<WebElement> searchResults = driver.findElements(By.xpath("//div[contains(@id,'suggestionsList')]/search_dialog-instant-result-item/div/div[2]"));

        for (WebElement result : searchResults) {

            String nameDiv = result.findElement(By.xpath("span")).getText();
            System.out.println("nameDiv " + nameDiv);

            if (nameDiv.equals(contactName)) {

                String roleDiv = result.findElement(By.xpath("div")).getText();
                System.out.println("roleDiv " + roleDiv);

                if (roleDiv.contains(role)) {

                    clickElement(result.findElement(By.xpath("div")));

                    System.out.println();
                    break;
                }
            }
        }
    }

    /**
     * Links an order by its number.
     *
     * @param orderNum The order number to be linked.
     * @throws InterruptedException If the thread is interrupted while sleeping.
     */
    public void linkOrder(String orderNum) throws InterruptedException {
        Thread.sleep(2000);

        Thread.sleep(3000);
        waitForByElement(clickDetails);
        clickElement(driver.findElement(clickDetails));
        clickOrderBox();
        fillOrderNum(orderNum);
    }

    /**
     * Saves the order details.
     *
     * @throws InterruptedException If the thread is interrupted while sleeping.
     */
    public void saveOrderInDetails() throws InterruptedException {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(1000));
        wait.until(ExpectedConditions.elementToBeClickable(orderSave));
        clickElement(driver.findElement(orderSave));
    }

    /**
     * Clicks the order box using JavaScript executor.
     */
    private void clickOrderBox() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(1000));
        WebElement order = wait.until(ExpectedConditions.presenceOfElementLocated(orderPencilIcon));
        JavascriptExecutor executor = (JavascriptExecutor) driver;
        executor.executeScript("arguments[0].click();", order);
    }

    /**
     * Fills the order number field with the provided order number and selects the corresponding result.
     *
     * @param orderNum The order number to be filled in.
     * @throws InterruptedException If the thread is interrupted while sleeping.
     */
    private void fillOrderNum(String orderNum) throws InterruptedException {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(2000));
        wait.until(ExpectedConditions.presenceOfElementLocated(orderBox));
        clickElement(driver.findElement(orderBox));
        //click cross button to clear
     //   clickElement(driver.findElement(crossButton));

        //  order.sendKeys(Keys.chord(Keys.CONTROL, "a"), Keys.DELETE);
        System.out.println("orderNum??? " + orderNum);
        driver.findElement(orderBox).sendKeys(orderNum);

     //   WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(3000));
        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//lightning-base-combobox-item[@data-value='actionAdvancedSearch']/following-sibling::ul/li/lightning-base-combobox-item/span/following-sibling::span/span/lightning-base-combobox-formatted-text")));
        Thread.sleep(2000);
        List<WebElement> Result = driver.findElements(By.xpath("//lightning-base-combobox-item[@data-value='actionAdvancedSearch']/following-sibling::ul/li/lightning-base-combobox-item/span/following-sibling::span/span/lightning-base-combobox-formatted-text"));
        for (WebElement result : Result) {
            String text = result.getText();
            System.out.println("text >> " + text);
            if (text.equals(orderNum)) {
                result.click();
            }
        }
        Thread.sleep(1000);
    }

    /**
     * Waits for the contract name element to be present in the DOM.
     */
    public void waitForContractName() {
        waitForByElement(By.xpath("(//div[@class='slds-grid'])[2]/records-hoverable-link/div/a/slot/slot/span"));
    }

    /**
     * Gets the actual contract name from the contract name element.
     *
     * @return The text of the contract name element.
     */
    public String getActualContractName() {
        waitForByElement(contractName);
        return driver.findElement(contractName).getText();
    }

    /**
     * Clicks on the 'Closed' link.
     */
    public void clickClosed() {
        waitForByElement(By.xpath("//li[@data-name='Closed']/a[@title='Closed']"));
        WebElement element = driver.findElement(By.xpath("//li[@data-name='Closed']/a[@title='Closed']"));
        clickElement(element);
    }

    /**
     * Clicks on the select closed stage element.
     */
    public void clickSelectClosedStage() {
        clickElement(driver.findElement(selectClosedStage));
    }

    /**
     * Changes the stage to the specified stage.
     *
     * @param stage The stage to change to.
     */
    public void changeStage(String stage) {
        waitForByElement(By.xpath("//select[contains(@class, 'stepAction required-field select') and @aria-required='true']"));
        clickElementUsingJavaScript(driver.findElement(By.xpath("//select[contains(@class, 'stepAction required-field select') and @aria-required='true']")));

        driver.findElement(By.xpath("//select[contains(@class, 'select') and @aria-required='true']/option[@value='"+ stage+"']")).click();
    }

    /**
     * Saves the changed stage.
     *
     * @throws InterruptedException If the thread is interrupted while sleeping.
     */
    public void saveChangedStage() throws InterruptedException {
        Thread.sleep(1000);
        clickElement(saveStageChanged);
    }

    /**
     * Gets the actual contract number from the contract number element.
     *
     * @return The text of the contract number element.
     */
    public String getActualContractNumber() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(2000));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@title='Contract Number']/following-sibling::*/slot/force-lookup/div/records-hoverable-link/div/a/slot/slot/span")));
        return driver.findElement(By.xpath("//*[@title='Contract Number']/following-sibling::*/slot/force-lookup/div/records-hoverable-link/div/a/slot/slot/span")).getText();
    }
}
