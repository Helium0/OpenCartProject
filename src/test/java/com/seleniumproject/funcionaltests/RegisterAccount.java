package com.seleniumproject.funcionaltests;

import com.seleniumproject.webBase.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

public class RegisterAccount extends BasePage {

    @Test
    public void moveToRegisterAccountTest() {
        driver.findElement(By.xpath("//span[text()='My Account']//following-sibling::i")).click();
        driver.findElement(By.xpath("//ul[@class='dropdown-menu dropdown-menu-right show']//a[text()='Register']")).click();
        String title = driver.getTitle();

        Assert.assertEquals(title,"Register Account");
    }

    @Test
    public void registerAccountTest() throws InterruptedException {
        driver.findElement(By.xpath("//span[text()='My Account']//following-sibling::i")).click();
        driver.findElement(By.xpath("//ul[@class='dropdown-menu dropdown-menu-right show']//a[text()='Register']")).click();
        String firstName = driver.findElement(By.cssSelector("#input-firstname")).getAttribute("placeholder");
        driver.findElement(By.cssSelector("#input-firstname")).sendKeys("Patryk");
        driver.findElement(By.cssSelector("#input-lastname")).sendKeys("Tester");
        driver.findElement(By.cssSelector("#input-email")).sendKeys("test666@gmail.com");

        driver.findElement(By.cssSelector("#input-password")).sendKeys("test");

        WebElement button = driver.findElement(By.cssSelector("[name='agree']"));
        button.click();
        Thread.sleep(5000);
        Assert.assertEquals(firstName,"First Name");
        Assert.assertTrue(button.isEnabled());
    }
}
