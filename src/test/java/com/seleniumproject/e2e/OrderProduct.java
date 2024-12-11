package com.seleniumproject.e2e;

import com.seleniumproject.pages.CartPage;
import com.seleniumproject.pages.LoginAccountPage;
import com.seleniumproject.pages.RegisterAccountPage;
import com.seleniumproject.webBase.BasePage;
import com.seleniumproject.webBase.ReadProperties;
import org.openqa.selenium.By;
import org.testng.annotations.Test;
import java.io.IOException;

public class OrderProduct extends BasePage {



    @Test
    public void buyProduct() throws IOException, InterruptedException {
        LoginAccountPage loginAccountPage = new LoginAccountPage(driver);
        RegisterAccountPage registerAccountPage = new RegisterAccountPage(driver);
        ReadProperties readProperties = new ReadProperties();
        registerAccountPage.setMyAccount();
        loginAccountPage.clickLoginElementFromList();
        loginAccountPage.setLoginEmail(readProperties.getValues("userEmailTest"));
        loginAccountPage.setLoginPassword(readProperties.getValues("userPasswordTest"));
        loginAccountPage.clickLoginElementFromPage();
        Thread.sleep(4000);
        CartPage cartPage = new CartPage(driver);
        cartPage.getCamerasOption();
        cartPage.getNikonCamera();
        cartPage.clickAddOnCartButton();
        cartPage.waitUntilTextVisible("1 item(s) - $98.00");
        cartPage.clickOnItemsCart();
        driver.findElement(By.xpath("//strong[text()=' View Cart']")).click();
        driver.findElement(By.xpath("//a[text()='Checkout']")).click();
        Thread.sleep(4000);
    }

}
