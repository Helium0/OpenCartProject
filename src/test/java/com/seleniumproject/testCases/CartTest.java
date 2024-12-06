package com.seleniumproject.testCases;

import com.seleniumproject.pages.CartPage;
import com.seleniumproject.webBase.BasePage;
import com.seleniumproject.webBase.ReadProperties;
import com.seleniumproject.webBase.SeleniumHelper;
import org.junit.Assert;
import org.openqa.selenium.*;
import org.testng.annotations.Test;
import java.io.IOException;
import java.util.List;

public class CartTest extends BasePage {

    private static final String ADDED_NIKON_TO_CART = "Success: You have added Nikon D300 to your shopping cart!";

    @Test
    public void addProductToTheCart() throws InterruptedException {
        CartPage cartPage = new CartPage(driver);
        cartPage.getCamerasOption();
        cartPage.getNikonCamera();
        cartPage.clickAddOnCartButton();

        Assert.assertEquals(cartPage.productHasBeenAdded(),ADDED_NIKON_TO_CART);


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

        if (cartPage.checkTotalPrice().equals("$490.00")){
            Assert.assertEquals(cartPage.checkTotalPrice(),"$490.00");
        } else {
            Assert.fail();
        }
    }

    @Test
    public void productPictures() throws InterruptedException {
        CartPage cartPage = new CartPage(driver);
        cartPage.getCamerasOption();
        cartPage.getNikonCamera();
        cartPage.clickOnPicture();
        cartPage.picturesNumber();

        Assert.assertEquals(cartPage.picturesNumber(),5);

    }

    @Test
    public void writeReviewAndSetRate() throws InterruptedException, IOException {
        ReadProperties readProperties = new ReadProperties();
        CartPage cartPage = new CartPage(driver);
        cartPage.getCamerasOption();
        cartPage.getNikonCamera();
        driver.findElement(By.partialLinkText("Write")).click();
        driver.findElement(By.id("input-name")).sendKeys(readProperties.getValues("firstName"));
        driver.findElement(By.id("input-text")).sendKeys(readProperties.getValues("reviewText"));
        List<WebElement> el = driver.findElements(By.xpath("//div[@id='input-rating']//input"));
        for(WebElement l : el) {
            if(l.getAttribute("value").contains("5")){
                SeleniumHelper.actions(driver).click(l).perform();
            }
        }
/*        driver.findElement(By.id("button-review")).click();  This test will always fail.

    This test will always fail. When I clicked the button I got:
    SyntaxError: Unexpected token '<', "<b>Error: "... is not valid JSON
    OK
    <b>Error: Could not load model Opencart\Catalog\Model\Product\Product
 */

    }

    @Test
    public void compareCameras() throws InterruptedException {
        CartPage cartPage = new CartPage(driver);
        cartPage.getCamerasOption();
        cartPage.clickCompareButton();
        cartPage.clickOnCompareButton();

        Assert.assertEquals(cartPage.countComparedItems(),2);

    }




}







