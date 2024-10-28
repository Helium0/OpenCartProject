package com.seleniumproject.funcionaltests;


import com.seleniumproject.webBase.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.Cookie;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.List;
import java.util.Set;

public class HomePage extends BasePage {



    @Test
    public void homePageTest() {
        String pageTitle = driver.getTitle();
        String currentUrl = driver.getCurrentUrl();
        Set<Cookie> cookies = driver.manage().getCookies();


        Assert.assertEquals(pageTitle,"Your Store");
        Assert.assertEquals(currentUrl,"http://localhost/ProjectOpenShop/");
        Assert.assertEquals(cookies.size(),2);
    }

    @Test
    public void searchBarTest() {
        driver.findElement(By.name("search")).sendKeys("Apple");
        driver.findElement(By.xpath("//button[@type='button']")).click();
        String pageTitle = driver.getTitle();
        WebElement tv = driver.findElement(By.xpath("//a[contains(text(), 'Apple')]"));



        Assert.assertEquals(pageTitle, "Search - Apple");
        Assert.assertEquals(tv.getText(),"Apple Cinema 30\"");
    }

    @Test
    public void changeCurrencyTest() {
        driver.findElement(By.xpath("//span[text()='Currency']")).click();
        int ss = driver.findElements(By.xpath("//ul[@class='dropdown-menu show']//li")).size();
        List<WebElement> elements = driver.findElements(By.xpath("//ul[@class='dropdown-menu show']//li"));
        for(WebElement el : elements) {
            if(el.getText().equals("£ Pound Sterling")){
                el.click();
                break;
            }
        }
        String price = driver.findElement(By.xpath("//button[contains(text(), '£0.00')]")).getText();

        Assert.assertEquals(ss,3);
        Assert.assertEquals(price,"0 item(s) - £0.00");

    }
}
