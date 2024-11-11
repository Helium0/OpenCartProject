package com.seleniumproject.funcionaltests;

import com.seleniumproject.pages.LoginAccountPage;
import com.seleniumproject.pages.RegisterAccountPage;
import com.seleniumproject.webBase.BasePage;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;


public class LoginToAccount extends BasePage {

    @Test
    public void loginWithoutCredentials() {
        RegisterAccountPage registerAccountPage = new RegisterAccountPage(driver);
        registerAccountPage.setMyAccount();
        LoginAccountPage loginAccountPage = new LoginAccountPage(driver);
        loginAccountPage.clickLoginElementFromList();
        loginAccountPage.clickLoginElementFromPage();
        registerAccountPage.waitMethodForWebelement();

        Assert.assertEquals(loginAccountPage.getLoginAlert().getText(),"Warning: No match for E-Mail Address and/or Password.");

    }

    @Test
    public void loginWithoutPassword() {
        RegisterAccountPage registerAccountPage = new RegisterAccountPage(driver);
        registerAccountPage.setMyAccount();
        LoginAccountPage loginAccountPage = new LoginAccountPage(driver);
        loginAccountPage.clickLoginElementFromList();
        loginAccountPage.setLoginEmail();
        loginAccountPage.clickLoginElementFromPage();
        registerAccountPage.waitMethodForWebelement();

        Assert.assertEquals(loginAccountPage.getLoginAlert().getText(),"Warning: No match for E-Mail Address and/or Password.");
    }

    @Test
    public void loginWithoutEmail() {
        RegisterAccountPage registerAccountPage = new RegisterAccountPage(driver);
        registerAccountPage.setMyAccount();
        LoginAccountPage loginAccountPage = new LoginAccountPage(driver);
        loginAccountPage.clickLoginElementFromList();
        loginAccountPage.setLoginPassword();
        loginAccountPage.clickLoginElementFromPage();
        registerAccountPage.waitMethodForWebelement();

        Assert.assertEquals(loginAccountPage.getLoginAlert().getText(), "Warning: No match for E-Mail Address and/or Password.");
    }

    @Test
    public void loginWithInvalidCredentials() {
        RegisterAccountPage registerAccountPage = new RegisterAccountPage(driver);
        registerAccountPage.setMyAccount();
        LoginAccountPage loginAccountPage = new LoginAccountPage(driver);
        loginAccountPage.clickLoginElementFromList();
        loginAccountPage.setLoginEmail();
        loginAccountPage.setLoginPassword();
        loginAccountPage.clickLoginElementFromPage();
        registerAccountPage.waitMethodForWebelement();

        Assert.assertEquals(loginAccountPage.getLoginAlert().getText(), "Warning: No match for E-Mail Address and/or Password.");

    }

    @Test(dataProvider = "dp")
    public void loginsFromJSON(String data) throws IOException, ParseException {
        JSONParser jsonParser = new JSONParser();
        FileReader file = new FileReader(".\\src\\test\\resources\\login.json");
        Object obj = jsonParser.parse(file);
        JSONObject jsonObject = (JSONObject) obj;
        JSONArray jsonArray = (JSONArray) jsonObject.get("userLogins");
    }
}
