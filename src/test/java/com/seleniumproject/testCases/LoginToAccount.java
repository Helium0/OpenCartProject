package com.seleniumproject.testCases;

import com.seleniumproject.pages.LoginAccountPage;
import com.seleniumproject.pages.RegisterAccountPage;
import com.seleniumproject.utilities.DataProviders;
import com.seleniumproject.webBase.BasePage;
import com.seleniumproject.webBase.MyListener;
import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

@Listeners(value = {MyListener.class})
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

    @Test(dataProvider = "dp", dataProviderClass = DataProviders.class)
    public void loginsFromJSON(String data) throws InterruptedException {
        LoginAccountPage loginAccountPage = new LoginAccountPage(driver);
        RegisterAccountPage registerAccountPage = new RegisterAccountPage(driver);
        registerAccountPage.setMyAccount();
        loginAccountPage.clickLoginElementFromList();
        String users[] = data.split(",");
        loginAccountPage.setLoginEmail(users[0]);
        loginAccountPage.setLoginPassword(users[1]);
        loginAccountPage.clickLoginElementFromPage();
        Thread.sleep(5000);
    }

}
