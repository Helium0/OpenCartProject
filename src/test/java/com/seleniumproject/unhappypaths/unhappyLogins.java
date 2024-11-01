package com.seleniumproject.unhappypaths;

import com.seleniumproject.webBase.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.time.Duration;

public class unhappyLogins extends BasePage {

    @Test
    public void loginWithoutCredentials() {
        driver.findElement(By.xpath("//span[text()='My Account']//following-sibling::i")).click();
        driver.findElement(By.xpath("//ul[@class='dropdown-menu dropdown-menu-right show']//a[text()='Login']")).click();
        driver.findElement(By.xpath("//button[text()='Login']")).click();

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//i[@class='fa-solid fa-circle-exclamation']")));
        WebElement elementAlert = driver.findElement(By.xpath("//dirv[@class='alert alert-danger alert-dismissible']"));

        Assert.assertEquals(elementAlert.getText(),"Warning: No match for E-Mail Address and/or Password.");

    }

    @Test
    public void loginWithoutPassword() {
        driver.findElement(By.xpath("//span[text()='My Account']//following-sibling::i")).click();
        driver.findElement(By.xpath("//ul[@class='dropdown-menu dropdown-menu-right show']//a[text()='Login']")).click();
        driver.findElement(By.id("input-email")).sendKeys("test@gmail.com");
        driver.findElement(By.xpath("//button[text()='Login']")).click();

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//i[@class='fa-solid fa-circle-exclamation']")));
        WebElement elementAlert = driver.findElement(By.xpath("//dirv[@class='alert alert-danger alert-dismissible']"));

        Assert.assertEquals(elementAlert.getText(),"Warning: No match for E-Mail Address and/or Password.");
    }

    @Test
    public void loginWithoutEmail() {
        driver.findElement(By.xpath("//span[text()='My Account']//following-sibling::i")).click();
        driver.findElement(By.xpath("//ul[@class='dropdown-menu dropdown-menu-right show']//a[text()='Login']")).click();
        driver.findElement(By.id("input-password")).sendKeys("test666");
        driver.findElement(By.xpath("//button[text()='Login']")).click();

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//i[@class='fa-solid fa-circle-exclamation']")));
        WebElement elementAlert = driver.findElement(By.xpath("//dirv[@class='alert alert-danger alert-dismissible']"));

        Assert.assertEquals(elementAlert.getText(),"Warning: No match for E-Mail Address and/or Password.");
    }

    @Test
    public void loginWithInvalidCredentials() {
        driver.findElement(By.xpath("//span[text()='My Account']//following-sibling::i")).click();
        driver.findElement(By.xpath("//ul[@class='dropdown-menu dropdown-menu-right show']//a[text()='Login']")).click();
        driver.findElement(By.id("input-email")).sendKeys("test@gmail.com");
        driver.findElement(By.id("input-password")).sendKeys("test666");
        driver.findElement(By.xpath("//button[text()='Login']")).click();

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//i[@class='fa-solid fa-circle-exclamation']")));
        WebElement elementAlert = driver.findElement(By.xpath("//dirv[@class='alert alert-danger alert-dismissible']"));

        Assert.assertEquals(elementAlert.getText(),"Warning: No match for E-Mail Address and/or Password.");
    }
}
