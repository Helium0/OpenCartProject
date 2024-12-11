package com.seleniumproject.webBase;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;


public class ProductComponent extends BaseComponentPage {

    private static final By PRODUCT_BY_NAME = By.xpath("//div[@class='description']//a");

    private static final By CURRENT_PRICE_BY = By.xpath("//span[@class='price-new']");


    public ProductComponent(WebElement rootElement) {
        super(rootElement);
    }

    public String getName() {
        return rootElement.findElement(PRODUCT_BY_NAME).getText();
    }

    public String getPrice() {
        return rootElement.findElement(CURRENT_PRICE_BY).getText();
    }
}

