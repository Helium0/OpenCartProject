package com.seleniumproject.funcionaltests;


import com.seleniumproject.webBase.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.Cookie;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.List;
import java.util.Set;

public class Home extends BasePage {


    @Test
    public void homePageTest() {
        String pageTitle = driver.getTitle();
        String currentUrl = driver.getCurrentUrl();
        Set<Cookie> cookies = driver.manage().getCookies();


        Assert.assertEquals(pageTitle, "Your Store");
        Assert.assertEquals(currentUrl, "http://localhost/ProjectOpenShop/");
        Assert.assertEquals(cookies.size(), 2);
    }

    @Test
    public void searchBarTest() {
        driver.findElement(By.name("search")).sendKeys("Apple");
        driver.findElement(By.xpath("//button[@type='button']")).click();
        String pageTitle = driver.getTitle();
        WebElement tv = driver.findElement(By.xpath("//a[contains(text(), 'Apple')]"));


        Assert.assertEquals(pageTitle, "Search - Apple");
        Assert.assertEquals(tv.getText(), "Apple Cinema 30\"");
    }

    @Test
    public void changeCurrencyTest() {
        driver.findElement(By.xpath("//span[text()='Currency']")).click();
        int ss = driver.findElements(By.xpath("//ul[@class='dropdown-menu show']//li")).size();
        List<WebElement> elements = driver.findElements(By.xpath("//ul[@class='dropdown-menu show']//li"));
        for (WebElement el : elements) {
            if (el.getText().equals("£ Pound Sterling")) {
                el.click();
                break;
            }
        }
        String price = driver.findElement(By.xpath("//button[contains(text(), '£0.00')]")).getText();

        Assert.assertEquals(ss, 3);
        Assert.assertEquals(price, "0 item(s) - £0.00");

    }

    @Test
    public void nintendoCheck() {
        int howManyLogos = driver.findElements(By.xpath("//div[@class='col-2 text-center']//img")).size();
        List<WebElement> logos = driver.findElements(By.xpath("//div[@id='carousel-banner-1']//div[@class='carousel-inner']//img"));
        boolean logoIsDisplayed = false;
            for(WebElement log : logos) {
                String name2 = "Nintendo";
                if(log.getAttribute("alt").equals(name2)!= log.isDisplayed()){
                    Actions actions = new Actions(driver);
                    WebElement arrow = driver.findElement(By.xpath("//button[@data-bs-target='#carousel-banner-1']//span[@class='fa-solid fa-chevron-right']"));
                    actions.click(arrow).perform();
                } else {
                    logoIsDisplayed = true;
                    break;
                }
            }
        Assert.assertEquals(howManyLogos,11);
        Assert.assertTrue(logoIsDisplayed,"Logo is displayed");
        }

    }

