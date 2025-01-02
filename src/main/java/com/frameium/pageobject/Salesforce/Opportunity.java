package com.frameium.pageobject.Salesforce;

import com.frameium.genericfunctions.GenericFunctions;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Represents the Opportunity page in Salesforce, providing methods to interact with various elements on the page.
 */
public class Opportunity extends GenericFunctions {
    // different locators

    //private By productsDropdown = By.xpath("//div[@class='slds-grid slds-page-header forceRelatedListCardHeader']//a[@title='Show 2 more actions']");
    private By productsDropdown1 = By.xpath("//slot/lst-related-list-container/div/div[1]/lst-related-list-single-container/laf-progressive-container/slot/lst-related-list-single-aura-wrapper/div/div/article/div[1]/div/div");

    private By productsDropdown = By.xpath("//flexipage-component2[@data-component-id='force_relatedListContainer']/slot/lst-related-list-container/div/div[1]/lst-related-list-single-container/laf-progressive-container/slot/lst-related-list-single-aura-wrapper/div/div/article/div[1]/div/div/div");
    private By choosePriceBookClick = By.xpath("//div[@role='menu']/ul/li/a[@title='Choose Price Book']");
    private By addProductsClick = By.xpath("//div[@role='menu']/ul/li/a[@title='Add Products']");

    private By savePriceBook = By.xpath("//button[@class='slds-button slds-button--neutral uiButton--default uiButton--brand uiButton']");
    //   private By next = By.xpath("//button[contains(@class,'slds-button slds-button--neutral uiButton--d//flexipage-component2[@data-component-id='force_relatedListContainer']/efault uiButton--brand uiButton forceActionButton') and //span[text()='Next']]");
    private By next = By.xpath("//button[contains(@class,'slds-button slds-button--neutral uiButton--d')]");

    private By rowProduct = By.xpath("//table[@aria-label='Recently Viewed']/tbody/tr");

    private By saveSelectedProducts = By.xpath("//button[contains(@class,'slds-button slds-button--neutral uiButton--default uiButton--brand uiButton forceActionButton') and //span[text()='Save']]");
    private By viewAllBtn = By.xpath("//div[@class='slds-card__footer']/span");
    private By amountField = By.xpath("//p[text()='Amount']/parent::*/p/slot/lightning-formatted-text");
    List<String> salesPrices = new ArrayList<>(); // List to store sales prices

    private By markCurrentStageBtn = By.xpath("//button[@class='slds-button slds-button--brand slds-path__mark-complete stepAction active uiButton']");
    private By detailsBtn = By.xpath("(//li[@title='Details']/a)[3]");
    private By valuePropositionClick = By.xpath("(//a[@data-tab-name='Value Proposition'])[1]");
    private String[] desiredTexts = {"", ""};
    private WebDriver driver;

    /**
     * Constructor for Opportunity page.
     *
     * @param driver The WebDriver instance used for browser automation.
     */
    public Opportunity(WebDriver driver) {

        this.driver = driver;
    }

    /**
     * Chooses a price book for the opportunity.
     *
     * @throws InterruptedException If the thread is interrupted while sleeping.
     */
    public void choosePriceBook() throws InterruptedException {
        Thread.sleep(4000);
        driver.findElement(productsDropdown).click();
        Thread.sleep(4000);
        driver.findElement(choosePriceBookClick).click();
    }

    /**
     * Selects a price book by calling the savePriceBook method.
     *
     * @return The result message from the savePriceBook method.
     */
    public String selectPriceBook() {
        return savePriceBook();
    }

    /**
     * Saves the selected price book.
     *
     * @return A message indicating the result of the save action.
     */
    public String savePriceBook() {
        try {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
            WebElement priceBook = wait.until(ExpectedConditions.elementToBeClickable(savePriceBook));
            JavascriptExecutor executor = (JavascriptExecutor) driver;
            executor.executeScript("arguments[0].click();", priceBook);
            return "Save button clicked successfully";
        } catch (Exception e) {
            e.printStackTrace();
            return "Save button not clicked";
        }
    }

    /**
     * Initiates the process to add products to the opportunity.
     *
     * @throws InterruptedException If the thread is interrupted while sleeping.
     */
    public void addProducts() throws InterruptedException {
        Thread.sleep(4000);
        driver.findElement(productsDropdown1).click();
        Thread.sleep(4000);
        driver.findElement(addProductsClick).click();
    }

    /**
     * Selects the specified products from the opportunity.
     *
     * @param products The list of product names to select.
     */
    public void selectProducts(List<String> products) {

        for (String productName : products) {

            try {
                Thread.sleep(5000);
                List<WebElement> rows = driver.findElements(rowProduct);

                // Iterate over each row
                for (int i = 0; i < rows.size(); i++) {
                    // Extract the text from the row
                    String rowText = rows.get(i).getText();
                    System.out.println("List Items: >>> " + rowText); // Print the row text

                    if (rowText.contains(productName)) {
                        clickElement(driver.findElement(By.xpath("(//table[@aria-label='Recently Viewed']/tbody/tr//input[@type='checkbox']/following-sibling::*/span[1])[" + (i + 1) + "]")));
                        Thread.sleep(2000);
                        break; // Break inner loop after clicking a checkbox
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * Clicks the Next button to proceed to the next step in the process.
     */
    public void clickNext() {

        clickElement(next);
    }

    /**
     * Enters quantities for predefined products.
     *
     * @throws InterruptedException If the thread is interrupted while sleeping.
     */
    public void enterProductQuantity() throws InterruptedException {
        Map<String, Integer> productQuantities = new HashMap<>();
        productQuantities.put("GenWatt Diesel 1000kW", 1);
        productQuantities.put("GenWatt Diesel 10kW", 2); // Adjust quantity as needed

        for (Map.Entry<String, Integer> entry : productQuantities.entrySet()) {
            String productName = entry.getKey();
            int quantity = entry.getValue();
            enterQuantitiesForProducts(productName, quantity);
        }
    }

    /**
     * Enters the specified quantity for a given product.
     *
     * @param productName The name of the product.
     * @param quantity    The quantity to be entered for the product.
     * @throws InterruptedException If the thread is interrupted while sleeping.
     */
    public void enterQuantitiesForProducts(String productName, int quantity) throws InterruptedException {
        Thread.sleep(5000);
        List<WebElement> rowss = driver.findElements(By.xpath("(//table[@aria-label='Recently Viewed'])[2]/tbody/tr"));
        for (int i = 0; i < rowss.size(); i++) {
            // Extract the text from the row
            Thread.sleep(5000);
            String rowsText = driver.findElement(By.xpath("(//table[@aria-label='Recently Viewed'])[2]/tbody/tr[" + (i + 1) + "]/th/span/a")).getText();
            System.out.println("enterQuantitiesForProducts List Items: >>> " + rowsText); // Print the row text


            if (rowsText.contains(productName)) {

                clickElement(rowss.get(i).findElement(By.xpath("(//table[@aria-label='Recently Viewed'])[2]/tbody/tr[" + (i + 1) + "]/td[2]/span/span[2]/button")));
                WebElement quantityInput = rowss.get(i).findElement(By.xpath("(//table[@aria-label='Recently Viewed'])[2]/tbody/tr[" + (i + 1) + "]/td[2]/div/div/div[1]/div/div/div/input"));
                clickElement(quantityInput);
                quantityInput.sendKeys(Integer.toString(quantity));
                Thread.sleep(4000);
                // Get the sales price
                WebElement salesPriceElement = rowss.get(i).findElement(By.xpath("(//table[@aria-label='Recently Viewed'])[2]/tbody/tr[" + (i + 1) + "]/td[3]//span[contains(@class, 'forceOutputCurrency')]"));
                String salesPrice = salesPriceElement.getText();
                System.out.println("Sales Price for " + productName + ": " + salesPrice);

                // Store the sales price in the list
                salesPrices.add(salesPrice);
                Thread.sleep(1000);
                break; // Break inner loop after entering quantity for a product
            }
        }
    }

    /**
     * Clicks the Save Selected Products button to save the selected products.
     */
    public void clickSaveSelectedProducts() {
        clickElement(saveSelectedProducts);
    }

    /**
     * Proceeds to the Value Proposition stage, marks the current stage, and clicks on the details button.
     */
    public void goToValuePropositionStage() {
        valueProposition();
        markCurrentStage();
        detailsBtnClick();
    }
    /**
     * Clicks on the Value Proposition tab.
     */
    public void valueProposition() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(4000));
        wait.until(ExpectedConditions.presenceOfElementLocated(valuePropositionClick));
        WebElement valuePropositionBtn = driver.findElement(valuePropositionClick);
        clickElement(valuePropositionBtn);
    }

    /**
     * Marks the current stage as complete.
     */
    public void markCurrentStage() {
        waitForByElement(markCurrentStageBtn);
        clickElementUsingJavaScript(driver.findElement(markCurrentStageBtn));
    }

    /**
     * Clicks the Details button to view opportunity details.
     */
    public void detailsBtnClick() {
        waitForByElement(detailsBtn);
        clickElementUsingJavaScript(driver.findElement(detailsBtn));
    }

    /**
     * Compares the current opportunity amount with the expected amount.
     *
     * @return The parsed amount from the opportunity.
     */
    public double valueComparison() {
        String amount = driver.findElement(amountField).getText().replace("₹", "").replace(",", "");
        double amt = Double.parseDouble(amount);

        System.out.println("THE AMOUNT IS .....>>>>" + amt);
        return amt;
    }

    /**
     * Clicks the View All button to view all products.
     *
     * @throws InterruptedException If the thread is interrupted while sleeping.
     */
    public void clickViewAll() throws InterruptedException {

        Thread.sleep(1000);
        clickElement(driver.findElement(viewAllBtn));
        Thread.sleep(1000);

    }

    /**
     * Calculates the total amount from the product list.
     *
     * @return The total calculated amount.
     */
    public double calculateTotalAmount() {
        WebElement table = driver.findElement(By.xpath("//table[@aria-label='Products']/tbody"));
        List<WebElement> rows = table.findElements(By.tagName("tr"));

        double totalAmount = 0.00;
        for (WebElement row : rows) {
            List<WebElement> cells = row.findElements(By.tagName("td"));

            String productName = cells.get(0).getText();

            System.out.println("quantity..........." + cells.get(1).getText());

            double q = Double.parseDouble(cells.get(1).getText());
            System.out.println(" q >>> " + q);

            double price = Double.parseDouble(cells.get(2).getText().replace("₹", "").replace(",", ""));
            System.out.println("price..........." + price);
            double subtotal = q * price;
            System.out.println("Product: " + productName + ", Subtotal: ₹" + subtotal);
            totalAmount += subtotal;
        }
        System.out.println("Total Amount: ₹" + totalAmount);

        goBackToPreviousPage();
        return totalAmount;

    }

    /**
     * Navigates back to the previous page.
     */
    public void goBackToPreviousPage() {

        driver.navigate().back();
    }

    /**
     * Checks if the Products link is displayed on the page.
     *
     * @return true if the Products link is displayed; false otherwise.
     */
    public boolean isProductsLinkDisplayed() {
        String productsDisplay = "//flexipage-component2[@data-component-id='force_relatedListContainer']/slot/lst-related-list-container/div/div[1]/lst-related-list-single-container/laf-progressive-container/slot/lst-related-list-single-aura-wrapper/div/div/article/div[1]/header/div[2]/h2/a[span[@title='Products'] and span[@title='(2)']]";
        try {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
            WebElement productsLink = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(productsDisplay)));
            return productsLink.isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    /**
     * Checks if the Standard Price Book link is displayed on the page.
     *
     * @return true if the Standard Price Book link is displayed; false otherwise.
     */
    public boolean isStandardPriceBookDisplayed() {
        String priceBookDisplay = "//div[@class='uiPopupTrigger']//a[contains(text(), 'Standard')]";
        try {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
            WebElement priceBookLink = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(priceBookDisplay)));
            return priceBookLink.isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

}
