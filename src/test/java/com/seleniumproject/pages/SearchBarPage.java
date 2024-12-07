package com.seleniumproject.pages;

import com.seleniumproject.webBase.BasePage;
import com.seleniumproject.webBase.ProductComponent;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;
import java.util.function.Predicate;

public class SearchBarPage extends BasePage {

   private WebDriver driver;

    @FindBy(xpath = "//div[@class='content']")
    private List<WebElement> productElement;


    public SearchBarPage (WebDriver driver){
        PageFactory.initElements(driver,this);
        this.driver = driver;
    }

    public List<ProductComponent> getAllProducts() {
       return productElement.stream().map(element -> new ProductComponent(element))
                .toList();
    }

    public ProductComponent getProduct(Predicate<ProductComponent> condition){
        return getAllProducts().stream().filter(condition).findFirst().orElseThrow();
    }


}
