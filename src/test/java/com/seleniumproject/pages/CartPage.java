package com.seleniumproject.pages;

import com.seleniumproject.webBase.BasePage;
import com.seleniumproject.webBase.ProductComponent;
import com.seleniumproject.webBase.SeleniumHelper;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import java.util.List;

public class CartPage extends BasePage {

    WebDriver driver;

    @FindBy(xpath = "//a[text()='Cameras']")
    private WebElement camerasBreadCrumbl;

    @FindBy(xpath = "//a[text()='Nikon D300']")
    private WebElement nikonCamera;

    @FindBy(css = "#button-cart")
    private WebElement cartButton;

    @FindBy(xpath = "//div[@id='alert']//div[contains(text(),'Success: You have added')]")
    private WebElement productAdded;

    @FindBy(xpath = "//div[@id='alert']//div[contains(text(),'Success: You have removed')]")
    private WebElement productRemoved;

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

    @FindBy(xpath = "//table[@class='table table-bordered']//img")
    private List<WebElement> cameraPhotos;
    @FindBy(xpath = "//div[@id='input-rating']//input")
    private List<WebElement> itemRates;


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

    public String productHasBeenRemoved() {
        return productRemoved.getText();
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
    public void updateQuantity(String howMany) {
        quantityProduct.clear();
        quantityProduct.sendKeys(howMany);
    }

    public boolean waitUntilTextVisible(String text) {
        return SeleniumHelper.waitObjMethod().until(ExpectedConditions.textToBePresentInElement(itemButton, text));
    }

//    public List<WebElement> waitUntilClickable() {
//        return SeleniumHelper.waitObjMethod().until(ExpectedConditions.(itemRates));
//    }

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

    public List<ProductComponent> compareProducts(){
        return cameras.stream().map(element -> new ProductComponent(element))
                .toList();
    }
    public void clickOnRate(String attributeName, String value) {
        for (WebElement el : itemRates) {
            if(el.getAttribute(attributeName).equals(value)) {
                SeleniumHelper.actions(driver).click(el).perform();
            }
        }
    }

    public WebElement selectedRadiobutton () {
        return itemRates.stream().filter(element -> element.isSelected())
                .findAny().orElseThrow();
    }




    public int countComparedItems() {
        return cameraPhotos.size();
    }

}
