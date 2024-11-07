package com.seleniumproject.funcionaltests;

import com.seleniumproject.pages.RegisterAccountPage;
import com.seleniumproject.webBase.BasePage;
import org.testng.Assert;
import org.testng.annotations.Test;


public class RegisterAccount extends BasePage {

    @Test
    public void moveToRegisterAccountTest() {
        RegisterAccountPage registerAccountPage = new RegisterAccountPage(driver);
        registerAccountPage.setMyAccount();
        registerAccountPage.setRegisterAccount();
        String title = driver.getTitle();

        Assert.assertEquals(title,"Register Account");
    }

    @Test
    public void registerAccountTest() {
        RegisterAccountPage registerAccountPage = new RegisterAccountPage(driver);
        registerAccountPage.setMyAccount();
        registerAccountPage.setRegisterAccount();
        registerAccountPage.setFirstName();
        registerAccountPage.setLastName();
        registerAccountPage.setUserEmail();
        registerAccountPage.setUserPassword();
        registerAccountPage.setRegisterButton();

        Assert.assertEquals(registerAccountPage.getFirstNameAttribute(),"First Name");
        Assert.assertTrue(registerAccountPage.getRegisterButton().isEnabled());
    }

    @Test
    public void registerAccountWithoutData() {
        RegisterAccountPage registerAccountPage = new RegisterAccountPage(driver);
        registerAccountPage.setMyAccount();
        registerAccountPage.setRegisterAccount();
        registerAccountPage.setContinueButton();
        registerAccountPage.waitMethod(registerAccountPage.fullElementList());

        Assert.assertEquals(registerAccountPage.getErrorFirstName(), "First Name must be between 1 and 32 characters!");
        Assert.assertEquals(registerAccountPage.getErrorLastName(), "Last Name must be between 1 and 32 characters!");
        Assert.assertEquals(registerAccountPage.getErrorUserEmail(), "E-Mail Address does not appear to be valid!");
        Assert.assertEquals(registerAccountPage.getErrorUserPassword(), "Password must be between 4 and 20 characters!");

    }

    @Test
    public void registerAccountWithoutPrivacyPolicy() {
        RegisterAccountPage registerAccountPage = new RegisterAccountPage(driver);
        registerAccountPage.setMyAccount();
        registerAccountPage.setRegisterAccount();
        registerAccountPage.setFirstName();
        registerAccountPage.setLastName();
        registerAccountPage.setUserEmail();
        registerAccountPage.setUserPassword();
        registerAccountPage.setContinueButton();
        registerAccountPage.waitMethodForWebelement();

        Assert.assertTrue(registerAccountPage.getAlert().isDisplayed());
        Assert.assertEquals(registerAccountPage.getAlert().getText(),"Warning: You must agree to the Privacy Policy!");

    }
}
