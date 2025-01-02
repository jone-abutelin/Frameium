package com.frameium.pageobject.Swag;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import com.frameium.genericfunctions.GenericFunctions;

public class CartHandling extends GenericFunctions {

	private By cartIcon = By.className("shopping_cart_link");
	private By cartItem = By.xpath("//div[@class='cart_item']");

	
	public CartHandling(WebDriver driver) {
		driver = this.driver;
	}
	
	public void goToCart() throws InterruptedException {
		Thread.sleep(1000);
		clickObject(cartIcon);
	}
	
	public boolean isCartEmpty() {
		 try {
		        // Check if any cart items are present
		        return !isDisplayedBy(cartItem);
		    } catch (org.openqa.selenium.NoSuchElementException e) {
		        // If no cart item is found, return true (cart is empty)
		        return true;
		    }
	}
}
