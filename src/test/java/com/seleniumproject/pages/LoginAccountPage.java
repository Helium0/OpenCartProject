package com.seleniumproject.pages;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.DataProvider;
import java.io.FileReader;
import java.io.IOException;

public class LoginAccountPage {

    private WebDriver driver;

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

    @DataProvider(name = "dp")
    public String[] readJson() throws IOException, ParseException {
        JSONParser jsonParser = new JSONParser();
        FileReader file = new FileReader(".\\src\\test\\resources\\login.json");
        Object obj = jsonParser.parse(file);
        JSONObject jsonObject = (JSONObject) obj;
        JSONArray jsonArray = (JSONArray) jsonObject.get("userLogins");
        String arr [] = new String[jsonArray.size()];
        for (int i =0; i<jsonArray.size(); i++){
            JSONObject logins = (JSONObject) jsonArray.get(i);
            String email = (String) logins.get("email");
            String psw = (String) logins.get("password");
            arr[i] = email +","+psw;
        }
        return arr;
    }

    public LoginAccountPage(WebDriver driver) {
        PageFactory.initElements(driver,this);
        this.driver = driver;
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

}
