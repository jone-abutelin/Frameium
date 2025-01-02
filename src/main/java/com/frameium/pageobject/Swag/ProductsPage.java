package com.frameium.pageobject.Swag;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

public class ProductsPage {

	private WebDriver driver;

	public ProductsPage(WebDriver driver) {
		this.driver = driver;
	}

	public void selectProduct(String productName) throws InterruptedException {
		Thread.sleep(2000);
		WebElement product = driver.findElement(By.xpath("//*[@id='item_4_title_link']"));
		WebElement addToCartButton = product.findElement(By.xpath("//*[@data-test='add-to-cart-sauce-labs-backpack']"));

		addToCartButton.click();
	}
	public void selectPrize() {
		WebElement sortDropdown = driver.findElement(By.className("product_sort_container"));
		Select sortSelect = new Select(sortDropdown);
		sortSelect.selectByValue("lohi");
	}
}
