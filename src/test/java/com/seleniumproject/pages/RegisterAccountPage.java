package com.seleniumproject.pages;

import com.seleniumproject.webBase.ReadProperties;
import com.seleniumproject.webBase.SeleniumHelper;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class RegisterAccountPage {

    private final WebDriver driver;

    @FindBy(xpath = "//span[text()='My Account']//following-sibling::i")
    private WebElement myAccount;

    @FindBy(xpath = "//ul[@class='dropdown-menu dropdown-menu-right show']//a[text()='Register']")
    private WebElement registerAccount;

    @FindBy(css = "#input-firstname")
    private WebElement userFirstName;

    @FindBy(css = "#input-lastname")
    private WebElement userLastName;

    @FindBy(css = "#input-email")
    private WebElement userEmail;

    @FindBy(css = "#input-password")
    private WebElement userPassword;

    @FindBy(css = "[name='agree']")
    private WebElement privacyPolicySlider;

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

    @FindBy(xpath = "//h1")
    private WebElement accountCreated;


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

    public void setFirstName(String name) {
        userFirstName.sendKeys(name);
    }

    public String getFirstNameAttribute() {
        return userFirstName.getAttribute("placeholder");
    }

    public String getErrorFirstName() {
        return errorFirstName.getText();
    }

    public void setLastName(String lastName) {
        userLastName.sendKeys(lastName);
    }

    public String getErrorLastName() {
        return errorLastName.getText();
    }

    public void setUserEmail(String email) {
        userEmail.sendKeys(email);
    }

    public String getErrorUserEmail() {
        return errorEmail.getText();
    }

    public void setUserPassword(String password) {
        userPassword.sendKeys(password);
    }

    public String getErrorUserPassword() {
        return errorPassword.getText();
    }

    public void setPrivacyPolicySlider() {
        privacyPolicySlider.click();
    }

    public WebElement getPrivacyPolicySlider() {
        return privacyPolicySlider;
    }

    public void setContinueButton() {
        continueButton.click();
    }



    public void waitMethod(List<WebElement> lista) {
        SeleniumHelper.waitObjMethod().until(ExpectedConditions.visibilityOfAllElements(lista));
    }

    public void waitMethodForWebelement() {
        SeleniumHelper.waitObjMethod().until(ExpectedConditions.visibilityOf(webElement));
    }


    public List<WebElement> fullElementList() {
        List<WebElement> list = new ArrayList<>();
        list.add(errorFirstName);
        list.add(errorLastName);
        list.add(errorEmail);
        list.add(errorPassword);
        return list;
    }

    public WebElement getAlert() {
        return alert;
    }

    public WebElement getAccountCreated() {
        return accountCreated;
    }


    public ResultSet database(String query) throws SQLException {
        return SeleniumHelper.connectionStatement().executeQuery(query);
    }


    public int databaseDelete(String query) throws SQLException {
        Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/myshop", "root","admin");
        Statement statement = connection.createStatement();
        return statement.executeUpdate(query);
    }


    public void verify (ResultSet set) throws SQLException, IOException {
        ReadProperties readProperties = new ReadProperties();
        boolean recordInDataBase = false;
        while (set.next()){
            String name = set.getString("firstname");
            String lastname = set.getString("lastname");
            String email = set.getString("email");

            if(readProperties.getValues("firstName").equals(name)
                    && readProperties.getValues("lastName").equals(lastname)
                    && readProperties.getValues("userEmail").equals(email)) {
                recordInDataBase = true;
                System.out.println("Record found in table: " +recordInDataBase);
                break;
            }
            else {
                System.out.println("Record found in table: "+recordInDataBase);
            }
        }

    }

}
