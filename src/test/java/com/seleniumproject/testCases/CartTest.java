package com.seleniumproject.testCases;

import com.seleniumproject.pages.CartPage;
import com.seleniumproject.webBase.BasePage;
import io.reactivex.rxjava3.exceptions.Exceptions;
import org.junit.Assert;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;

import java.time.Duration;

public class CartTest extends BasePage {


    @Test
    public void addProductToTheCart() throws InterruptedException {
        CartPage cartPage = new CartPage(driver);
        cartPage.getCamerasOption();
        cartPage.getNikonCamera();
        cartPage.clickAddOnCartButton();

        Assert.assertEquals(cartPage.productHasBeenAdded(),"Success: You have added Nikon D300 to your shopping cart!");


    }

    @Test
    public void addProductToTheCartAndDelete() throws InterruptedException {
        CartPage cartPage = new CartPage(driver);
        cartPage.getCamerasOption();
        cartPage.getNikonCamera();
        cartPage.clickAddOnCartButton();
        cartPage.waitUntilTextVisible("1 item(s) - $98.00");
        cartPage.clickOnItemsCart();
        cartPage.removeItemsFromCart();

    }

    @Test
    public void addProductToTheCardAndUpdateQuantity() throws InterruptedException {
        CartPage cartPage = new CartPage(driver);
        cartPage.getCamerasOption();
        cartPage.getNikonCamera();
        cartPage.clickAddOnCartButton();
        WebElement el = driver.findElement(By.name("quantity"));
        el.clear();
        el.sendKeys("4");
        cartPage.clickAddOnCartButton();
        cartPage.waitUntilTextVisible("5 item(s) - $490.00");
        cartPage.clickOnItemsCart();

        int rows = driver.findElements(By.xpath("//table[@class='table table-sm table-bordered mb-2']//tr")).size();
        System.out.println(rows);
        int cels = driver.findElements(By.xpath("//table[@class='table table-sm table-bordered mb-2']//tr//td")).size();
        System.out.println(cels);

        for (int i = 1; i <= rows; i++) {
            for (int j = 1; j <= cels; j++) {
                String celText = driver.findElement(By.xpath("//table[@class='table table-sm table-bordered mb-2']//tr[" + i + "]//td[" + j + "]")).getText();
                System.out.println(celText);

            }


            Thread.sleep(5000);

        }
    }
}

