package com.seleniumproject.testCases;

import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.Status;
import com.seleniumproject.pages.LoginAccountPage;
import com.seleniumproject.pages.RegisterAccountPage;
import com.seleniumproject.utilities.DataProviders;
import com.seleniumproject.utilities.ExtentReport;
import com.seleniumproject.webBase.BasePage;
import com.seleniumproject.webBase.MyListener;
import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

@Listeners(value = {MyListener.class})
public class LoginToAccount extends BasePage {


    @Test
    public void loginWithoutCredentials() {
        ExtentTest test = extentReports.createTest("Login_Without_Credentials_Test");
        logger.info("*** Starting Login_Without_Credentials ***");
        RegisterAccountPage registerAccountPage = new RegisterAccountPage(driver);
        registerAccountPage.setMyAccount();
        LoginAccountPage loginAccountPage = new LoginAccountPage(driver);
        loginAccountPage.clickLoginElementFromList();
        loginAccountPage.clickLoginElementFromPage();
        registerAccountPage.waitMethodForWebelement();

        Assert.assertEquals(loginAccountPage.getLoginAlert().getText(),"Warning: No match for E-Mail Address and/or Password.");
        test.log(Status.PASS,"Assertion passed").pass(MediaEntityBuilder.createScreenCaptureFromBase64String(ExtentReport.capturePhoto64(driver)).build());
        logger.info("*** Finished Login_Without_Credentials ***");
    }

    @Test
    public void loginWithoutPassword() {
        ExtentTest test = extentReports.createTest("Login_Without_Password_Test");
        logger.info("*** Starting Login_Without_Password ***");
        RegisterAccountPage registerAccountPage = new RegisterAccountPage(driver);
        registerAccountPage.setMyAccount();
        LoginAccountPage loginAccountPage = new LoginAccountPage(driver);
        loginAccountPage.clickLoginElementFromList();
        loginAccountPage.setLoginEmail();
        test.log(Status.PASS,"Set Login Passed").info(MediaEntityBuilder.createScreenCaptureFromBase64String(ExtentReport.capturePhoto64(driver)).build());
        loginAccountPage.clickLoginElementFromPage();
        registerAccountPage.waitMethodForWebelement();

        Assert.assertEquals(loginAccountPage.getLoginAlert().getText(),"Warning: No match for E-Mail Address and/or Password.");
        test.log(Status.PASS,"Assertion passed").pass(MediaEntityBuilder.createScreenCaptureFromBase64String(ExtentReport.capturePhoto64(driver)).build());
        logger.info("*** Finished Login_Without_Password ***");
    }

    @Test
    public void loginWithoutEmail() {
        ExtentTest test = extentReports.createTest("Login_Without_Email_Test");
        logger.info("*** Starting Login_Without_Email ***");
        RegisterAccountPage registerAccountPage = new RegisterAccountPage(driver);
        registerAccountPage.setMyAccount();
        LoginAccountPage loginAccountPage = new LoginAccountPage(driver);
        loginAccountPage.clickLoginElementFromList();
        loginAccountPage.setLoginPassword();
        test.log(Status.PASS,"Set Login and Password").info(MediaEntityBuilder.createScreenCaptureFromBase64String(ExtentReport.capturePhoto64(driver)).build());
        loginAccountPage.clickLoginElementFromPage();
        registerAccountPage.waitMethodForWebelement();

        Assert.assertEquals(loginAccountPage.getLoginAlert().getText(), "Warning: No match for E-Mail Address and/or Password.");
        test.log(Status.PASS,"Assertion passed").pass(MediaEntityBuilder.createScreenCaptureFromBase64String(ExtentReport.capturePhoto64(driver)).build());
        logger.info("*** Finished Login_Without_Email ***");
    }

    @Test
    public void loginWithInvalidCredentials() {
        ExtentTest test = extentReports.createTest("Login_With_Invalid_Credentials_Test");
        logger.info("*** Starting Login_With_Invalid_Credentials ***");
        RegisterAccountPage registerAccountPage = new RegisterAccountPage(driver);
        registerAccountPage.setMyAccount();
        LoginAccountPage loginAccountPage = new LoginAccountPage(driver);
        loginAccountPage.clickLoginElementFromList();
        loginAccountPage.setLoginEmail();
        loginAccountPage.setLoginPassword();
        test.log(Status.PASS,"Set Invalid Login and Password").info(MediaEntityBuilder.createScreenCaptureFromBase64String(ExtentReport.capturePhoto64(driver)).build());
        loginAccountPage.clickLoginElementFromPage();
        registerAccountPage.waitMethodForWebelement();

        Assert.assertEquals(loginAccountPage.getLoginAlert().getText(), "Warning: No match for E-Mail Address and/or Password.");
        test.log(Status.PASS,"Assertion passed").pass(MediaEntityBuilder.createScreenCaptureFromBase64String(ExtentReport.capturePhoto64(driver)).build());
        logger.info("*** Finished Login_With_Invalid_Credentials ***");
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
