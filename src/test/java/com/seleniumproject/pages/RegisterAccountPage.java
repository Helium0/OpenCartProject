package com.seleniumproject.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

public class RegisterAccountPage {

    private WebDriver driver;

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

    @FindBy(css = "[name='agree']")
    private WebElement registerButton;

    @FindBy(xpath = "//button[@class='btn btn-primary']")
    private WebElement continueButton;

    @FindBy(id = "error-firstname")
    private WebElement errorFirstName;

    @FindBy(id = "error-lastname")
    private WebElement errorLastName;

    @FindBy(id = "error-email")
    private WebElement errorEmail;

    @FindBy(id = "error-password")
    private WebElement errorPassword;

    @FindBy(xpath = "//i[@class='fa-solid fa-circle-exclamation']")
    private WebElement webElement;

    @FindBy(xpath = "//dirv[@class='alert alert-danger alert-dismissible']")
    private WebElement alert;


    public RegisterAccountPage(WebDriver driver){
        PageFactory.initElements(driver,this);
        this.driver=driver;
    }

    public void setMyAccount() {
        myAccount.click();
    }

    public void setRegisterAccount() {
        registerAccount.click();
    }

    public void setFirstName() {
        firstName.sendKeys("Patryk");
    }

    public String getFirstNameAttribute() {
        return firstName.getAttribute("placeholder");
    }

    public String getErrorFirstName() {
        return errorFirstName.getText();
    }

    public void setLastName() {
        lastName.sendKeys("Tester");
    }

    public String getErrorLastName() {
        return errorLastName.getText();
    }

    public void setUserEmail() {
        userEmail.sendKeys("test666@gmail.com");
    }

    public String getErrorUserEmail() {
        return errorEmail.getText();
    }

    public void setUserPassword() {
        userPassword.sendKeys("test");
    }

    public String getErrorUserPassword() {
        return errorPassword.getText();
    }

    public void setRegisterButton() {
        registerButton.click();
    }

    public WebElement getRegisterButton() {
        return registerButton;
    }

    public void setContinueButton() {
        continueButton.click();
    }

    public WebDriverWait waitObjMethod() {
        WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(5));
        return wait;
    }

    public void waitMethod(List<WebElement> lista) {
        waitObjMethod().until(ExpectedConditions.visibilityOfAllElements(lista));
    }

    public void waitMethodForWebelement() {
        waitObjMethod().until(ExpectedConditions.visibilityOf(webElement));
    }


    public List<WebElement> fullElementList() {
        List<WebElement> list = new ArrayList<>();
        list.add(errorFirstName);
        list.add(errorFirstName);
        list.add(errorLastName);
        list.add(errorEmail);
        list.add(errorPassword);
        return list;
    }

    public WebElement getAlert() {
        return alert;
    }

}
