package com.seleniumproject.funcionaltests;

import com.seleniumproject.webBase.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

public class RegisterAccount extends BasePage {

    @Test
    public void moveToRegisterAccountTest() {
        driver.findElement(By.xpath("//span[text()='My Account']//following-sibling::i")).click();
        driver.findElement(By.xpath("//ul[@class='dropdown-menu dropdown-menu-right show']//a[text()='Register']")).click();
        String title = driver.getTitle();

        Assert.assertEquals(title,"Register Account");
    }

    @Test
    public void registerAccountTest() {
        driver.findElement(By.xpath("//span[text()='My Account']//following-sibling::i")).click();
        driver.findElement(By.xpath("//ul[@class='dropdown-menu dropdown-menu-right show']//a[text()='Register']")).click();
        String firstName = driver.findElement(By.cssSelector("#input-firstname")).getAttribute("placeholder");
        driver.findElement(By.cssSelector("#input-firstname")).sendKeys("Patryk");
        driver.findElement(By.cssSelector("#input-lastname")).sendKeys("Tester");
        driver.findElement(By.cssSelector("#input-email")).sendKeys("test666@gmail.com");

        driver.findElement(By.cssSelector("#input-password")).sendKeys("test");

        WebElement button = driver.findElement(By.cssSelector("[name='agree']"));
        button.click();

        Assert.assertEquals(firstName,"First Name");
        Assert.assertTrue(button.isEnabled());
    }

    @Test
    public void registerAccountWithoutData() {
        driver.findElement(By.xpath("//span[text()='My Account']//following-sibling::i")).click();
        driver.findElement(By.xpath("//ul[@class='dropdown-menu dropdown-menu-right show']//a[text()='Register']")).click();
        driver.findElement(By.xpath("//button[@class='btn btn-primary']")).click();

        WebElement errorFirstName = driver.findElement(By.id("error-firstname"));
        WebElement errorLastName = driver.findElement(By.id("error-lastname"));
        WebElement errorEmail = driver.findElement(By.id("error-email"));
        WebElement errorPassword = driver.findElement(By.id("error-password"));
        List<WebElement> lista = new ArrayList<>();
        lista.add(errorFirstName);
        lista.add(errorLastName);
        lista.add(errorEmail);
        lista.add(errorPassword);
        WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(5));
        wait.until(ExpectedConditions.visibilityOfAllElements(lista));

        Assert.assertEquals(errorFirstName.getText(), "First Name must be between 1 and 32 characters!");
        Assert.assertEquals(errorLastName.getText(), "Last Name must be between 1 and 32 characters!");
        Assert.assertEquals(errorEmail.getText(), "E-Mail Address does not appear to be valid!");
        Assert.assertEquals(errorPassword.getText(), "Password must be between 4 and 20 characters!");

    }

    @Test
    public void registerAccountWithoutPrivacyPolicy() {
        driver.findElement(By.xpath("//span[text()='My Account']//following-sibling::i")).click();
        driver.findElement(By.xpath("//ul[@class='dropdown-menu dropdown-menu-right show']//a[text()='Register']")).click();
        String firstName = driver.findElement(By.cssSelector("#input-firstname")).getAttribute("placeholder");
        driver.findElement(By.cssSelector("#input-firstname")).sendKeys("Patryk");
        driver.findElement(By.cssSelector("#input-lastname")).sendKeys("Tester");
        driver.findElement(By.cssSelector("#input-email")).sendKeys("test666@gmail.com");

        driver.findElement(By.cssSelector("#input-password")).sendKeys("test");
        driver.findElement(By.xpath("//button[@class='btn btn-primary']")).click();

        WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(5000));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//i[@class='fa-solid fa-circle-exclamation']")));
        WebElement alert = driver.findElement(By.xpath("//dirv[@class='alert alert-danger alert-dismissible']"));

        Assert.assertTrue(alert.isDisplayed());
        Assert.assertEquals(alert.getText(),"Warning: You must agree to the Privacy Policy!");

    }
}
