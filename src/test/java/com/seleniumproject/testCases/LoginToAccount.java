package com.seleniumproject.testCases;

import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.Status;
import com.seleniumproject.pages.LoginAccountPage;
import com.seleniumproject.pages.RegisterAccountPage;
import com.seleniumproject.utilities.DataProviders;
import com.seleniumproject.utilities.ExtentReport;
import com.seleniumproject.webBase.BasePage;

import org.openqa.selenium.NoSuchElementException;
import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;


public class LoginToAccount extends BasePage {


    @Test
    public void loginWithoutCredentials() {
        ExtentTest test = extentReports.createTest("Login_Without_Credentials_Test");
        logger.info("*** Starting Login_Without_Credentials_Test ***");
        RegisterAccountPage registerAccountPage = new RegisterAccountPage(driver);
        registerAccountPage.setMyAccount();
        LoginAccountPage loginAccountPage = new LoginAccountPage(driver);
        loginAccountPage.clickLoginElementFromList();
        loginAccountPage.clickLoginElementFromPage();
        registerAccountPage.waitMethodForWebelement();

        if(loginAccountPage.getLoginAlert().getText().equals("Warning: No match for E-Mail Address and/or Password.")){
            Assert.assertEquals(loginAccountPage.getLoginAlert().getText(),"Warning: No match for E-Mail Address and/or Password.");
            test.log(Status.PASS,"Assertion passed").pass(MediaEntityBuilder.createScreenCaptureFromBase64String(ExtentReport.capturePhoto64(driver)).build());
            logger.info("*** Finished Login_Without_Credentials_Test ***");
        } else {
            test.log(Status.FAIL, "Assertion failed").fail(MediaEntityBuilder.createScreenCaptureFromBase64String(ExtentReport.capturePhoto64(driver)).build());
            logger.info("Login_Without_Credentials_Test Failed");
            Assert.fail("Login_Without_Credentials_Test Failed");
        }
    }

    @Test
    public void loginWithoutPassword() {
        ExtentTest test = extentReports.createTest("Login_Without_Password_Test");
        logger.info("*** Starting Login_Without_Password_Test ***");
        RegisterAccountPage registerAccountPage = new RegisterAccountPage(driver);
        registerAccountPage.setMyAccount();
        LoginAccountPage loginAccountPage = new LoginAccountPage(driver);
        loginAccountPage.clickLoginElementFromList();
        loginAccountPage.setLoginEmail();
        test.log(Status.PASS,"Set Login Passed").info(MediaEntityBuilder.createScreenCaptureFromBase64String(ExtentReport.capturePhoto64(driver)).build());
        loginAccountPage.clickLoginElementFromPage();
        registerAccountPage.waitMethodForWebelement();

        if(loginAccountPage.getLoginAlert().getText().equals("Warning: No match for E-Mail Address and/or Password.")){
            Assert.assertEquals(loginAccountPage.getLoginAlert().getText(),"Warning: No match for E-Mail Address and/or Password.");
            test.log(Status.PASS,"Assertion passed").pass(MediaEntityBuilder.createScreenCaptureFromBase64String(ExtentReport.capturePhoto64(driver)).build());
            logger.info("*** Finished Login_Without_Password_Test ***");
        } else {
            test.log(Status.FAIL, "Assertion failed").fail(MediaEntityBuilder.createScreenCaptureFromBase64String(ExtentReport.capturePhoto64(driver)).build());
            logger.info("Login_Without_Password_Test Failed");
            Assert.fail("Login_Without_Password_Test Failed");
        }
    }

    @Test
    public void loginWithoutEmail() {
        ExtentTest test = extentReports.createTest("Login_Without_Email_Test");
        logger.info("*** Starting Login_Without_Email_Test ***");
        RegisterAccountPage registerAccountPage = new RegisterAccountPage(driver);
        registerAccountPage.setMyAccount();
        LoginAccountPage loginAccountPage = new LoginAccountPage(driver);
        loginAccountPage.clickLoginElementFromList();
        loginAccountPage.setLoginPassword();
        test.log(Status.PASS,"Set Login and Password").info(MediaEntityBuilder.createScreenCaptureFromBase64String(ExtentReport.capturePhoto64(driver)).build());
        loginAccountPage.clickLoginElementFromPage();
        registerAccountPage.waitMethodForWebelement();

        if(loginAccountPage.getLoginAlert().getText().equals("Warning: No match for E-Mail Address and/or Password.")){
            Assert.assertEquals(loginAccountPage.getLoginAlert().getText(), "Warning: No match for E-Mail Address and/or Password.");
            test.log(Status.PASS,"Assertion passed").pass(MediaEntityBuilder.createScreenCaptureFromBase64String(ExtentReport.capturePhoto64(driver)).build());
            logger.info("*** Finished Login_Without_Email_Test ***");
        } else {
            test.log(Status.FAIL, "Assertion failed").fail(MediaEntityBuilder.createScreenCaptureFromBase64String(ExtentReport.capturePhoto64(driver)).build());
            logger.info("Login_Without_Email_Test Failed");
            Assert.fail("Login_Without_Email_Test Failed");
        }
    }

    @Test
    public void loginWithInvalidCredentials() {
        ExtentTest test = extentReports.createTest("Login_With_Invalid_Credentials_Test");
        logger.info("*** Starting Login_With_Invalid_Credentials_Test ***");
        RegisterAccountPage registerAccountPage = new RegisterAccountPage(driver);
        registerAccountPage.setMyAccount();
        LoginAccountPage loginAccountPage = new LoginAccountPage(driver);
        loginAccountPage.clickLoginElementFromList();
        loginAccountPage.setLoginEmail();
        loginAccountPage.setLoginPassword();
        test.log(Status.PASS,"Set Invalid Login and Password").info(MediaEntityBuilder.createScreenCaptureFromBase64String(ExtentReport.capturePhoto64(driver)).build());
        loginAccountPage.clickLoginElementFromPage();
        registerAccountPage.waitMethodForWebelement();

        if(loginAccountPage.getLoginAlert().getText().equals("Warning: No match for E-Mail Address and/or Password.")) {
            Assert.assertEquals(loginAccountPage.getLoginAlert().getText(), "Warning: No match for E-Mail Address and/or Password.");
            test.log(Status.PASS, "Assertion passed").pass(MediaEntityBuilder.createScreenCaptureFromBase64String(ExtentReport.capturePhoto64(driver)).build());
            logger.info("*** Finished Login_With_Invalid_Credentials ***");
        } else {
            test.log(Status.FAIL, "Assertion failed").fail(MediaEntityBuilder.createScreenCaptureFromBase64String(ExtentReport.capturePhoto64(driver)).build());
            logger.info("Login_With_Invalid_Credentials_Test Failed");
            Assert.fail("Login_With_Invalid_Credentials_Test Failed");
        }
    }

    @Test(dataProvider = "dp", dataProviderClass = DataProviders.class)
    public void loginsFromJSON(String data) throws InterruptedException {
        ExtentTest test = extentReports.createTest("Logins_From_JSON_File_Test");
        logger.info("*** Starting Logins_From_JSON_File_Test ***");
        LoginAccountPage loginAccountPage = new LoginAccountPage(driver);
        RegisterAccountPage registerAccountPage = new RegisterAccountPage(driver);
        registerAccountPage.setMyAccount();
        loginAccountPage.clickLoginElementFromList();
        String users[] = data.split(",");
        loginAccountPage.setLoginEmail(users[0]);
        loginAccountPage.setLoginPassword(users[1]);
        test.log(Status.PASS,"Succesfully Provided Credentials").info("Succesfully Provided Credentials");
        logger.info("Succesfully Provided Credentials");
        loginAccountPage.clickLoginElementFromPage();

        try {
            if (loginAccountPage.getLoginAlert().isDisplayed()) {
                Assert.assertEquals(loginAccountPage.getLoginAlert().getText(), "Warning: No match for E-Mail Address and/or Password.");
                test.log(Status.PASS, "Assertion passed").pass(MediaEntityBuilder.createScreenCaptureFromBase64String(ExtentReport.capturePhoto64(driver)).build());
                logger.info("*** Finished Logins_From_JSON_File_Test ***");
            } else {
                test.log(Status.FAIL, "Assertion failed").fail(MediaEntityBuilder.createScreenCaptureFromBase64String(ExtentReport.capturePhoto64(driver)).build());
                logger.info("Logins_From_JSON_File_Test Failed");
                Assert.fail("Logins_From_JSON_File_Test Failed");
            }
        } catch (NoSuchElementException e) {
            if (loginAccountPage.setMyOrder().equals("My Orders")) {
                Assert.assertEquals(loginAccountPage.setMyOrder(), "My Orders");
                test.log(Status.PASS, "Assertion passed").pass(MediaEntityBuilder.createScreenCaptureFromBase64String(ExtentReport.capturePhoto64(driver)).build());
                logger.info("*** Finished Logins_From_JSON_File_Test ***");
            } else {
                test.log(Status.FAIL, "Assertion failed").fail(MediaEntityBuilder.createScreenCaptureFromBase64String(ExtentReport.capturePhoto64(driver)).build());
                logger.info("Logins_From_JSON_File_Test Failed");
                Assert.fail("Logins_From_JSON_File_Test Failed");
            }
        }
    }
}
