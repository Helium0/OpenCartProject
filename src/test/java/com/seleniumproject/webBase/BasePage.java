package com.seleniumproject.webBase;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;



public class BasePage {

    protected static WebDriver driver;

    @BeforeMethod
    public static WebDriver basePage() {
        driver = new ChromeDriver();
        driver.get("http://localhost/ProjectOpenShop/");
        driver.manage().window().maximize();
        return  driver;
    }

    @AfterMethod
    public static void closeDown() {
        driver.quit();
        System.out.println("Test passed");
    }

}
