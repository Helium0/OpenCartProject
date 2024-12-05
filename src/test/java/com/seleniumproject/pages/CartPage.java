package com.seleniumproject.pages;

import com.seleniumproject.webBase.SeleniumHelper;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.List;

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

    @FindBy(name = "quantity")
    private WebElement quantityProduct;

    @FindBy(xpath = "//button[@class='btn btn-danger']")
    private WebElement removeItems;

    @FindBy(xpath = "//table[@class='table table-sm table-bordered mb-2']//tr")
    private List<WebElement> rows;

    @FindBy(xpath = "//table[@class='table table-sm table-bordered mb-2']//tr[1]//td")
    private List<WebElement> cells;

    @FindBy(xpath = "//div[@class='image magnific-popup']//img")
    private List<WebElement> picture;

    @FindBy(xpath = "//div[@class='button-group']//button")
    private List<WebElement> cameras;

    @FindBy(xpath = "//button[@title='Next (Right arrow key)']")
    private WebElement nextImageArrow;

    @FindBy(id = "compare-total")
    private WebElement compareButton;


    public CartPage(WebDriver driver) {
        PageFactory.initElements(driver, this);
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

    public void clickOnPicture() {
        picture.get(0).click();
    }

    public void clickOnCompareButton() {
        compareButton.click();
    }

    public boolean waitUntilTextVisible(String text) {
        return SeleniumHelper.waitObjMethod().until(ExpectedConditions.textToBePresentInElement(itemButton, text));
    }

    public String checkTotalPrice() {
        String text = "";
        for (int i = 1; i <= rows.size(); i++) {
            for (int j = 1; j <= cells.size(); j++) {
               text = driver.findElement(By.xpath("//table[@class='table table-sm table-bordered mb-2']//tr[" + i + "]//td[" + j + "]")).getText();
            }
        }
        return text;
    }

    public int picturesNumber() {
        int number = 0;
        for (int i =0; i<picture.size(); i++){
            if(picture.get(i).isDisplayed()){
                nextImageArrow.click();
                number++;
            } else {
                System.out.println("Can`t click bad image: "+i);
            }
        }
        return number;
    }

    public void clickCompareButton() {
        cameras.stream()
                .filter(element -> element.getAttribute("title").equals("Compare this Product"))
                .forEach(element -> element.click());
    }

}
