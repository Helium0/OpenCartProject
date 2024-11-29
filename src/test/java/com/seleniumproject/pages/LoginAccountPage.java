package com.seleniumproject.pages;


import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;


public class LoginAccountPage {

    @FindBy(xpath = "//ul[@class='dropdown-menu dropdown-menu-right show']//a[text()='Login']")
    private WebElement loginElementFromList;

    @FindBy(xpath = "//button[text()='Login']")
    private WebElement loginElementOnPage;

    @FindBy(xpath = "//dirv[@class='alert alert-danger alert-dismissible']")
    private WebElement loginAlert;

    @FindBy(id = "input-email")
    private WebElement loginEmail;

    @FindBy(id = "input-password")
    private WebElement loginPassword;

    @FindBy(xpath = "//h2[contains(normalize-space(),'My Orders')]")
    private WebElement myOrder;


    public LoginAccountPage(WebDriver driver) {
        PageFactory.initElements(driver,this);
    }

    public void clickLoginElementFromList() {
        loginElementFromList.click();
    }

    public void clickLoginElementFromPage() {
        loginElementOnPage.click();
    }

    public WebElement getLoginAlert() {
        return loginAlert;
    }

    public void setLoginEmail() {
        loginEmail.sendKeys("test@gmail.com");
    }

    public void setLoginEmail(String email) {
        loginEmail.sendKeys(email);
    }

    public void setLoginPassword() {
        loginPassword.sendKeys("test666");
    }

    public void setLoginPassword(String password) {
        loginPassword.sendKeys(password);
    }

    public String setMyOrder(){
        return myOrder.getText();
    }


}
