package com.seleniumproject.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class RegisterAccountPage {

    protected WebDriver driver;


    @FindBy(xpath = "//span[text()='My Account']//following-sibling::i")
    private WebElement myAccount;

    @FindBy(xpath = "//ul[@class='dropdown-menu dropdown-menu-right show']//a[text()='Register']")
    private WebElement registerAccount;

    @FindBy(css = "#input-firstname")
    private WebElement firstName;

    @FindBy(css = "#input-lastname")
    private WebElement lastName;

    @FindBy(css = "#input-email")
    private WebElement userEmail;

    @FindBy(css = "#input-password")
    private WebElement userPassword;



    public RegisterAccountPage(WebDriver driver){
        PageFactory.initElements(driver,this);
        this.driver=driver;
    }


}
