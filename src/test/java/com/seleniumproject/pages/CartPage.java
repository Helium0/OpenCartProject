package com.seleniumproject.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;

import java.time.Duration;

public class CartPage {

    WebDriver driver;

    @FindBy(xpath = "//a[text()='Cameras']")
    private WebElement camerasBreadCrumbl;

    @FindBy(xpath = "//a[text()='Nikon D300']")
    private WebElement nikonCamera;

    @FindBy(css = "#button-cart")
    private WebElement cartButton;

    @FindBy(xpath = "//div[@id='alert']//div[contains(text(),'Success: You have added')]")
    private WebElement productAdded;

    @FindBy(xpath = "//button[@class='btn btn-lg btn-inverse btn-block dropdown-toggle']")
    private WebElement itemButton;

    @FindBy(xpath = "//button[@class='btn btn-danger']")
    private WebElement removeItems;




    public CartPage(WebDriver driver) {
        PageFactory.initElements(driver,this);
        this.driver = driver;
    }

    public void getCamerasOption() {
        camerasBreadCrumbl.click();
    }

    public void getNikonCamera() {
        nikonCamera.click();
    }

    public void clickAddOnCartButton() {
        cartButton.click();
    }

    public String productHasBeenAdded() {
        return productAdded.getText();
    }

    public void clickOnItemsCart() {
        itemButton.click();
    }

    public void removeItemsFromCart() {
        removeItems.click();
    }

    public boolean waitUntilTextVisible(String text) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        return wait.until(ExpectedConditions.textToBePresentInElement(itemButton,text));
    }

}
