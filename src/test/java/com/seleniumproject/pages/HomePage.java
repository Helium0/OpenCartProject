package com.seleniumproject.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class HomePage {

    WebDriver driver;

    @FindBy(name = "search")
    private WebElement searchBar;

    @FindBy(xpath = "//button[@type='button']")
    private WebElement clickButton;

    @FindBy(xpath = "//a[contains(text(), 'Apple')]")
    private WebElement appleProduct;

    @FindBy(xpath = "//span[text()='Currency']")
    private WebElement currency;

    @FindBy(xpath = "//ul[@class='dropdown-menu show']//li")
    private int howManyCurrencies;

    @FindBy(xpath = "//ul[@class='dropdown-menu show']//li")
    private List<WebElement> currenciesList;

    @FindBy(xpath = "//div[@id='carousel-banner-1']//div[@class='carousel-inner']//img")
    private List<WebElement> logos;



    public HomePage(WebDriver driver) {
        PageFactory.initElements(driver,this);
        this.driver = driver;

    }

    public void writeOnSearchBar() {
        searchBar.sendKeys("Apple");
    }

    public void clickOnTheButton() {
        clickButton.click();
    }

    public String getAppleProduct() {
        return appleProduct.getText();
    }

    public String getTitle() {
        return driver.getTitle();
    }

    public void getCurrency() {
        currency.click();
    }

    public void getDemandCurrency(String text) {
       for (WebElement element : currenciesList){
           if (element.getText().equals(text)){
               element.click();
               break;
           }
       }
    }

    public boolean showLogo(String logoName) {
        boolean logoIsDisplayed = false;
        for(WebElement log : logos) {
            if(log.getAttribute("alt").equals(logoName)!= log.isDisplayed()){
                WebElement arrow = driver.findElement(By.xpath("//button[@data-bs-target='#carousel-banner-1']//span[@class='fa-solid fa-chevron-right']"));
                actionsInstance(driver).click(arrow).perform();
            } else {
                logoIsDisplayed = true;
                break;
            }
        }
        return logoIsDisplayed;
    }

    private Actions actionsInstance(WebDriver driver){
        return new Actions(driver);
    }


}
