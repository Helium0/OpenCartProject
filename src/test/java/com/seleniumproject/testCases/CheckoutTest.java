package com.seleniumproject.testCases;

import com.seleniumproject.pages.CartPage;
import com.seleniumproject.pages.LoginAccountPage;
import com.seleniumproject.pages.RegisterAccountPage;
import com.seleniumproject.webBase.BasePage;
import com.seleniumproject.webBase.ReadProperties;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.Test;

import java.io.IOException;

public class CheckoutTest extends BasePage {


    @Test
    public void fillTheCheckoutForm() throws InterruptedException, IOException {
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
        driver.findElement(By.id("input-shipping-firstname")).sendKeys(readProperties.getValues("firstName"));
        driver.findElement(By.id("input-shipping-lastname")).sendKeys(readProperties.getValues("lastName"));
        driver.findElement(By.id("input-shipping-address-1")).sendKeys(readProperties.getValues("userAdress"));
        driver.findElement(By.id("input-shipping-city")).sendKeys(readProperties.getValues("userCity"));
        driver.findElement(By.id("input-shipping-postcode")).sendKeys(readProperties.getValues("userPostCode"));
        WebElement country = driver.findElement(By.id("input-shipping-country"));
        Select chooseCountry = new Select(country);
        chooseCountry.getOptions().stream()
                .filter(element -> element.getText().equals("Barbados"))
                .forEach(element -> element.click());
        WebElement state = driver.findElement(By.id("input-shipping-zone"));
        Select chooseState = new Select(state);
        chooseState.getOptions().stream()
                .filter(element -> element.getText().equals("Cheshire"))
                .forEach(element -> element.click());
        Thread.sleep(4000);
    }
}
