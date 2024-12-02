package com.seleniumproject.testCases;

import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.Status;
import com.seleniumproject.pages.RegisterAccountPage;
import com.seleniumproject.utilities.ExtentReport;
import com.seleniumproject.webBase.BasePage;
import com.seleniumproject.webBase.ReadProperties;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.annotations.Test;
import java.io.IOException;
import java.sql.*;


public class RegistrationTest extends BasePage {

    @Test
    public void moveToRegisterAccountTest() {

        try {
            test = extentReports.createTest("Move_To_Register_Account_Test").log(Status.PASS,"Created Test");
            logger.info("*** Starting Move_To_Register_Account_Test***");
            RegisterAccountPage registerAccountPage = new RegisterAccountPage(driver);
            logger.info("Click on Register from Main menu");
            registerAccountPage.setMyAccount();
            test.log(Status.INFO, "Set My Account Done").info(MediaEntityBuilder.createScreenCaptureFromBase64String(ExtentReport.capturePhoto64(driver)).build());
            registerAccountPage.setRegisterAccount();
            test.log(Status.INFO, "Set Register Account Done").info(MediaEntityBuilder.createScreenCaptureFromBase64String(ExtentReport.capturePhoto64(driver)).build());
            String title = driver.getTitle();
            logger.info("Validating expected text");
            Assert.assertEquals(title, "Register Account");
            test.log(Status.INFO, "Assertion passed").info(MediaEntityBuilder.createScreenCaptureFromBase64String(ExtentReport.capturePhoto64(driver)).build());
        } catch (Exception e) {
            logger.info("Test failed");
            logger.info("Debug logs");
            test.log(Status.FAIL, "Test failed").fail(MediaEntityBuilder.createScreenCaptureFromBase64String(ExtentReport.capturePhoto64(driver)).build());
            Assert.fail();
        }
        logger.info("*** Finished Move_To_Register_Account_Test ***");
    }


    @Test(priority = 1)
    public void registerAccount() throws IOException {
        ExtentTest test = extentReports.createTest("Register_Account_Test");
        logger.info("*** Starting Register_Account_Test ***");
        RegisterAccountPage registerAccountPage = new RegisterAccountPage(driver);
        ReadProperties readProperties = new ReadProperties();
        registerAccountPage.setMyAccount();
        test.log(Status.PASS, "Set My Account Done");
        registerAccountPage.setRegisterAccount();
        test.log(Status.PASS, "Set Register Account Done");
        registerAccountPage.setFirstName(readProperties.getValues("firstName"));
        test.log(Status.PASS,"Set First Name Done");
        registerAccountPage.setLastName(readProperties.getValues("lastName"));
        test.log(Status.PASS,"Set Last Name Done");
        registerAccountPage.setUserEmail(readProperties.getValues("userEmail"));
        test.log(Status.PASS,"Set User Email Done");
        registerAccountPage.setUserPassword(readProperties.getValues("userPassword"));
        test.log(Status.PASS,"Set User Password Done").info(MediaEntityBuilder.createScreenCaptureFromBase64String(ExtentReport.capturePhoto64(driver)).build());
        registerAccountPage.setPrivacyPolicySlider();
        test.log(Status.PASS,"Set Privacy Policy Done").info(MediaEntityBuilder.createScreenCaptureFromBase64String(ExtentReport.capturePhoto64(driver)).build());
        registerAccountPage.setContinueButton();
        test.log(Status.PASS,"Set Continue Button").info(MediaEntityBuilder.createScreenCaptureFromBase64String(ExtentReport.capturePhoto64(driver)).build());
        registerAccountPage.waitObjMethod().until(ExpectedConditions.textToBePresentInElement(registerAccountPage.getAccountCreated(),"Your Account Has Been Created!"));
        Assert.assertEquals(registerAccountPage.getAccountCreated().getText(), "Your Account Has Been Created!");
            if(registerAccountPage.getAccountCreated().getText().equals("Your Account Has Been Created!")){
                System.out.println("Registration successfull from UI/Application");
                test.log(Status.PASS, "Assertion passed").info(MediaEntityBuilder.createScreenCaptureFromBase64String(ExtentReport.capturePhoto64(driver)).build());
                logger.info("*** Finished Register_Account_Test ***");
            } else {
                test.log(Status.FAIL,"Test failed").fail(MediaEntityBuilder.createScreenCaptureFromBase64String(ExtentReport.capturePhoto64(driver)).build());
                logger.info("Register_Account_Test Failed");
                Assert.fail("Register_Account_Test Failed");
            }
        }


    @Test(dependsOnMethods = {"registerAccount"})
    public void validationRegisteredAccount() throws SQLException, IOException {
        try {
            ExtentTest test = extentReports.createTest("Validate_Created_Account_On_DataBase");
            logger.info("*** Starting Validation_Registered_Account_On_DataBase_Test ***");
            String query = "SELECT firstname,lastname,email FROM oc_customer";
            test.log(Status.PASS, "Query: SELECT firstname,lastname,email FROM oc_customer");
            RegisterAccountPage registerAccountPage = new RegisterAccountPage(driver);
            registerAccountPage.verify(registerAccountPage.database(query));
            test.log(Status.PASS, "Assertion passed");
            logger.info("*** Finished Validation_Registered_Account_On_DataBase_Test ***");
        } catch (SQLException sql) {
            logger.info("SQL connetion failed");
            Assert.fail("Validation_Registered_Account_On_DataBase_Test Failed");
        } catch (IOException io) {
            logger.info("Cant read data");
            Assert.fail("Validation_Registered_Account_On_DataBase_Test Failed");
        }
    }


    @Test(dependsOnMethods = {"validationRegisteredAccount"})
    public void registerAccountOnTheSameData() throws IOException {
        ExtentTest test = extentReports.createTest("Register_Account_On_The_Same_Data");
        logger.info("*** Starting Register_Account_On_The_Same_Data_Test ***");
        RegisterAccountPage registerAccountPage = new RegisterAccountPage(driver);
        ReadProperties readProperties = new ReadProperties();
        registerAccountPage.setMyAccount();
        test.log(Status.PASS, "Set My Account Done");
        registerAccountPage.setRegisterAccount();
        test.log(Status.PASS, "Set Register Account Done");
        registerAccountPage.setFirstName(readProperties.getValues("firstName"));
        test.log(Status.PASS,"Set First Name Done");
        registerAccountPage.setLastName(readProperties.getValues("lastName"));
        test.log(Status.PASS,"Set Last Name Done");
        registerAccountPage.setUserEmail(readProperties.getValues("userEmail"));
        test.log(Status.PASS,"Set User Email Done");
        registerAccountPage.setUserPassword(readProperties.getValues("userPassword"));
        test.log(Status.PASS,"Set User Password Done");
        registerAccountPage.setPrivacyPolicySlider();
        test.log(Status.PASS,"Set Privacy Policy Done");
        registerAccountPage.setContinueButton();
        test.log(Status.PASS,"Set Continue Button");
        registerAccountPage.waitObjMethod().until(ExpectedConditions.textToBePresentInElement(registerAccountPage.getAlert(),"Warning: E-Mail Address is already registered!"));

        if(registerAccountPage.getAlert().getText().equals("Warning: E-Mail Address is already registered!")) {
            Assert.assertEquals(registerAccountPage.getAlert().getText(), "Warning: E-Mail Address is already registered!");
            test.log(Status.PASS, "Assertion passed").info(MediaEntityBuilder.createScreenCaptureFromBase64String(ExtentReport.capturePhoto64(driver)).build());
            logger.info("*** Finished Register_Account_On_The_Same_Data_Test ***");
        } else {
            test.log(Status.FAIL,"Test failed").fail(MediaEntityBuilder.createScreenCaptureFromBase64String(ExtentReport.capturePhoto64(driver)).build());
            logger.info("Register_Account_On_The_Same_Data_Test Failed");
            Assert.fail("Register_Account_On_The_Same_Data_Test Failed");
        }
    }

    @Test(dependsOnMethods = {"registerAccountOnTheSameData"})
    public void validationIfAccountHasBeenCreatedOnce() throws SQLException {
        ExtentTest test = extentReports.createTest("Validation_If_Account_Has_Been_Created_Once");
        logger.info("*** Starting Validation_If_Account_Has_Been_Created_Once_Test ***");
        RegisterAccountPage registerAccountPage = new RegisterAccountPage(driver);
        String query = "SELECT COUNT(email) AS email FROM oc_customer";
        ResultSet set = registerAccountPage.database(query);
        test.log(Status.PASS,"Query: SELECT COUNT(email) AS email FROM oc_customer");
        boolean recordInDataBase = true;
        while (set.next()) {
            int emailsNumber = set.getInt("email");
            String info = emailsNumber == 2 ? "Two user emails: "+recordInDataBase : "More user emails: "+recordInDataBase;
            if(info.contains("Two")){
                test.log(Status.PASS, "Validation passed");
                logger.info("*** Finished Validation_If_Account_Has_Been_Created_Once_Test ***");
            } else {
                test.log(Status.FAIL,"Test failed").fail(MediaEntityBuilder.createScreenCaptureFromBase64String(ExtentReport.capturePhoto64(driver)).build());
                logger.info("Validation_If_Account_Has_Been_Created_Once_Test Failed");
                Assert.fail("Validation_If_Account_Has_Been_Created_Once_Test Failed");
            }
        }

    }

    @Test(dependsOnMethods = {"validationIfAccountHasBeenCreatedOnce"})
    public void deleteRegisteredAccountFromDataBase () throws SQLException, IOException {
        ExtentTest test = extentReports.createTest("Delete_Registered_Account_From_DataBase");
        logger.info("*** Starting Delete_Registered_Account_From_DataBase_Test ***");
        RegisterAccountPage registerAccountPage = new RegisterAccountPage(driver);
        ReadProperties readProperties = new ReadProperties();
        String ss = readProperties.getValues("userEmail");
        String query = "DELETE FROM oc_customer WHERE email = '" + ss + "'";
        test.log(Status.PASS, "Query: DELETE FROM oc_customer WHERE email = '" + ss + "'");
        if (registerAccountPage.databaseDelete(query) == 1) {
                test.log(Status.PASS,"Record has been deleted");
            logger.info("*** Finished Delete_Registered_Account_From_DataBase ***");
        } else {
                test.log(Status.FAIL,"Record doesn`t exist in database");
            logger.info("Delete_Registered_Account_From_DataBase Failed");
            Assert.fail("Delete_Registered_Account_From_DataBase Failed");
        }
    }

    @Test(dependsOnMethods = {"deleteRegisteredAccountFromDataBase"})
    public void validationDeletedAccount() throws SQLException, IOException {
        ExtentTest test = extentReports.createTest("Validation_Deleted_Account");
        logger.info("*** Starting Validation_Deleted_Account_Test ***");
        ReadProperties readProperties = new ReadProperties();
        RegisterAccountPage registerAccountPage = new RegisterAccountPage(driver);

        String ss = readProperties.getValues("userEmail");
        test.log(Status.PASS,"Set User Email In to Variable");
        String query = "SELECT COUNT(email) AS email FROM oc_customer WHERE email = '" + ss + "'";
        ResultSet set = registerAccountPage.database(query);
        test.log(Status.PASS,"SELECT COUNT(email) AS email FROM oc_customer WHERE email = '" + ss + "'");
        boolean recordInDataBase = true;
        while (set.next()) {
            int email = set.getInt("email");
            test.log(Status.PASS,"Set Email Number In To Variable");
            String info = email == 0 ? "No record in DataBase: "+recordInDataBase : " Record is still in DataBase: "+recordInDataBase;
            test.log(Status.PASS,info);
            if(info.contains("No record")){
                test.log(Status.PASS, "Validation passed");
                logger.info("*** Finished Validation_Deleted_Account_Test ***");
            } else {
                test.log(Status.FAIL,info);
                logger.info("Validation_Deleted_Account_Test Failed "+info);
                Assert.fail("Validation_Deleted_Account_Test Failed");
            }
        }
    }

    @Test
    public void registerAccountTestWithoutRegister() throws IOException {
        ExtentTest test = extentReports.createTest("Register_Account_Test_Without_Register");
        try {
            logger.info("*** Starting Register_Account_Test_Without_Register_Test ***");
            RegisterAccountPage registerAccountPage = new RegisterAccountPage(driver);
            ReadProperties readProperties = new ReadProperties();
            registerAccountPage.setMyAccount();
            test.log(Status.PASS, "Set My Account Done");
            registerAccountPage.setRegisterAccount();
            test.log(Status.PASS, "Set Register Account Done");
            registerAccountPage.setFirstName(readProperties.getValues("firstName"));
            test.log(Status.PASS, "Set First Name Done");
            registerAccountPage.setLastName(readProperties.getValues("lastName"));
            test.log(Status.PASS, "Set Last Name Done");
            registerAccountPage.setUserEmail(readProperties.getValues("userEmail"));
            test.log(Status.PASS, "Set User Email Done");
            registerAccountPage.setUserPassword(readProperties.getValues("userPassword"));
            test.log(Status.PASS, "Set User Password Done").info(MediaEntityBuilder.createScreenCaptureFromBase64String(ExtentReport.capturePhoto64(driver)).build());
            registerAccountPage.setPrivacyPolicySlider();
            test.log(Status.PASS, "Set Privacy Policy Done").info(MediaEntityBuilder.createScreenCaptureFromBase64String(ExtentReport.capturePhoto64(driver)).build());

            if(registerAccountPage.getFirstNameAttribute().equals("First Name") && registerAccountPage.getPrivacyPolicySlider().isEnabled()) {
                Assert.assertEquals(registerAccountPage.getFirstNameAttribute(), "First Name");
                Assert.assertTrue(registerAccountPage.getPrivacyPolicySlider().isEnabled());
                test.log(Status.PASS, "Assertions Passed").info(MediaEntityBuilder.createScreenCaptureFromBase64String(ExtentReport.capturePhoto64(driver)).build());
                logger.info("*** Finished Register_Account_Test_Without_Register_Test ***");
            } else {
                test.log(Status.FAIL,"Test failed").fail(MediaEntityBuilder.createScreenCaptureFromBase64String(ExtentReport.capturePhoto64(driver)).build());
                logger.info("Register_Account_Test_Without_Register_Test Failed");
                Assert.fail("Register_Account_Test_Without_Register_Test Failed");
            }
        } catch (Exception e){
            test.log(Status.FAIL,"Test failed").fail(MediaEntityBuilder.createScreenCaptureFromBase64String(ExtentReport.capturePhoto64(driver)).build());
            logger.info("Register_Account_Test_Without_Register_Test Failed");
            Assert.fail("Register_Account_Test_Without_Register_Test Failed");
        }
    }

    @Test
    public void registerAccountWithoutData() {
        ExtentTest test = extentReports.createTest("Register_Account_Without_Data");
        try {
            RegisterAccountPage registerAccountPage = new RegisterAccountPage(driver);
            registerAccountPage.setMyAccount();
            logger.info("*** Starting Register_Account_Without_Data_Test ***");
            test.log(Status.PASS, "Set My Account Done");
            registerAccountPage.setRegisterAccount();
            test.log(Status.PASS, "Set Register Account Done");
            registerAccountPage.setContinueButton();
            test.log(Status.PASS, "Set Continue Button").info(MediaEntityBuilder.createScreenCaptureFromBase64String(ExtentReport.capturePhoto64(driver)).build());
            registerAccountPage.waitMethod(registerAccountPage.fullElementList());

            Assert.assertEquals(registerAccountPage.getErrorFirstName(), "First Name must be between 1 and 32 characters!");
            Assert.assertEquals(registerAccountPage.getErrorLastName(), "Last Name must be between 1 and 32 characters!");
            Assert.assertEquals(registerAccountPage.getErrorUserEmail(), "E-Mail Address does not appear to be valid!");
            Assert.assertEquals(registerAccountPage.getErrorUserPassword(), "Password must be between 4 and 20 characters!");
            test.log(Status.PASS, "Assertions passed").info(MediaEntityBuilder.createScreenCaptureFromBase64String(ExtentReport.capturePhoto64(driver)).build());
            logger.info("*** Finished Register_Account_Without_Data_Test ***");
        } catch (Exception e){
            test.log(Status.FAIL,"Test Failed").fail(MediaEntityBuilder.createScreenCaptureFromBase64String(ExtentReport.capturePhoto64(driver)).build());
            logger.info("Register_Account_Without_Data_Test Failed");
            Assert.fail("Register_Account_Without_Data_Test Failed");
        }
    }

    @Test
    public void registerAccountWithoutPrivacyPolicy() throws IOException {
        ExtentTest test = extentReports.createTest("Register_Account_Without_Privacy_Policy");
        try {
            logger.info("*** Starting Register_Account_Without_Privacy_Policy_Test ***");
            RegisterAccountPage registerAccountPage = new RegisterAccountPage(driver);
            ReadProperties readProperties = new ReadProperties();
            registerAccountPage.setMyAccount();
            test.log(Status.PASS, "Set My Account Done");
            registerAccountPage.setRegisterAccount();
            test.log(Status.PASS, "Set Register Account Done");
            registerAccountPage.setFirstName(readProperties.getValues("firstName"));
            test.log(Status.PASS, "Set First Name Done");
            registerAccountPage.setLastName(readProperties.getValues("lastName"));
            test.log(Status.PASS, "Set Last Name Done");
            registerAccountPage.setUserEmail(readProperties.getValues("userEmail"));
            test.log(Status.PASS, "Set User Email Done");
            registerAccountPage.setUserPassword(readProperties.getValues("userPassword"));
            test.log(Status.PASS, "Set User Password Done");
            registerAccountPage.setContinueButton();
            test.log(Status.PASS, "Set Continue Button").info(MediaEntityBuilder.createScreenCaptureFromBase64String(ExtentReport.capturePhoto64(driver)).build());
            registerAccountPage.waitMethodForWebelement();

            Assert.assertTrue(registerAccountPage.getAlert().isDisplayed());
            Assert.assertEquals(registerAccountPage.getAlert().getText(), "Warning: You must agree to the Privacy Policy!");
            test.log(Status.PASS,"Assertions passed").info(MediaEntityBuilder.createScreenCaptureFromBase64String(ExtentReport.capturePhoto64(driver)).build());
            logger.info("*** Finished Register_Account_Without_Privacy_Policy_Test ***");
        } catch (Exception e){
            test.log(Status.FAIL,"Test failed").fail(MediaEntityBuilder.createScreenCaptureFromBase64String(ExtentReport.capturePhoto64(driver)).build());
            logger.info("Register_Account_Without_Privacy_Policy_Test Failed");
            Assert.fail("Register_Account_Without_Privacy_Policy_Test Failed");
        }
    }

}
