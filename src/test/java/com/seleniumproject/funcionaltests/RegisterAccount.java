package com.seleniumproject.funcionaltests;

import com.seleniumproject.pages.RegisterAccountPage;
import com.seleniumproject.webBase.BasePage;
import com.seleniumproject.webBase.ReadProperties;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.IOException;
import java.sql.*;


public class RegisterAccount extends BasePage {

    @Test
    public void moveToRegisterAccountTest() {
        RegisterAccountPage registerAccountPage = new RegisterAccountPage(driver);
        registerAccountPage.setMyAccount();
        registerAccountPage.setRegisterAccount();
        String title = driver.getTitle();

        Assert.assertEquals(title,"Register Account");
    }


    @Test(priority = 1)
    public void registerAccount() throws IOException {
        RegisterAccountPage registerAccountPage = new RegisterAccountPage(driver);
        ReadProperties readProperties = new ReadProperties();
        registerAccountPage.setMyAccount();
        registerAccountPage.setRegisterAccount();
        registerAccountPage.setFirstName(readProperties.getValues("firstName"));
        registerAccountPage.setLastName(readProperties.getValues("lastName"));
        registerAccountPage.setUserEmail(readProperties.getValues("userEmail"));
        registerAccountPage.setUserPassword(readProperties.getValues("userPassword"));
        registerAccountPage.setPrivacyPolicySlider();
        registerAccountPage.setContinueButton();
        registerAccountPage.waitObjMethod().until(ExpectedConditions.textToBePresentInElement(registerAccountPage.getAccountCreated(),"Your Account Has Been Created!"));

        Assert.assertEquals(registerAccountPage.getAccountCreated().getText(), "Your Account Has Been Created!");
        try {
            if(registerAccountPage.getAccountCreated().getText().equals("Your Account Has Been Created!")){
                System.out.println("Registration successfull from UI/Application");
            } else {
                System.out.println("Registration failed");
            }
        } catch (Exception e) {
            System.out.println("Another problem with application");
        }

    }

    @Test(dependsOnMethods = {"registerAccount"})
    public void validationRegisteredAccount() throws SQLException, IOException {
        String query = "SELECT firstname,lastname,email FROM oc_customer";
        RegisterAccountPage registerAccountPage = new RegisterAccountPage(driver);
        registerAccountPage.verify(registerAccountPage.database(query));

    }

    @Test(dependsOnMethods = {"validationRegisteredAccount"})
    public void registerAccountOnTheSameData() throws IOException {
        RegisterAccountPage registerAccountPage = new RegisterAccountPage(driver);
        ReadProperties readProperties = new ReadProperties();
        registerAccountPage.setMyAccount();
        registerAccountPage.setRegisterAccount();
        registerAccountPage.setFirstName(readProperties.getValues("firstName"));
        registerAccountPage.setLastName(readProperties.getValues("lastName"));
        registerAccountPage.setUserEmail(readProperties.getValues("userEmail"));
        registerAccountPage.setUserPassword(readProperties.getValues("userPassword"));
        registerAccountPage.setPrivacyPolicySlider();
        registerAccountPage.setContinueButton();
        registerAccountPage.waitObjMethod().until(ExpectedConditions.textToBePresentInElement(registerAccountPage.getAlert(),"Warning: E-Mail Address is already registered!"));

        Assert.assertEquals(registerAccountPage.getAlert().getText(), "Warning: E-Mail Address is already registered!");

    }

    @Test(dependsOnMethods = {"registerAccountOnTheSameData"})
    public void validationIfAccountHasBeenCreatedOnce() throws SQLException {
        RegisterAccountPage registerAccountPage = new RegisterAccountPage(driver);
        String query = "SELECT COUNT(email) AS email FROM oc_customer";
        ResultSet set = registerAccountPage.database(query);
        boolean recordInDataBase = true;
        while (set.next()) {
            int emailsNumber = set.getInt("email");
            String info = emailsNumber == 1 ? "One user email: "+recordInDataBase : "More user emails: "+recordInDataBase;
            System.out.println(info);
        }

    }

    @Test(dependsOnMethods = {"validationIfAccountHasBeenCreatedOnce"})
    public void deleteRegisteredAccount () throws SQLException, IOException {
        RegisterAccountPage registerAccountPage = new RegisterAccountPage(driver);
        ReadProperties readProperties = new ReadProperties();
        String ss = readProperties.getValues("userEmail");
        String query = "DELETE FROM oc_customer WHERE email = '" + ss + "'";
        if (registerAccountPage.databaseDelete(query) == 1) {
                System.out.println("Record has been deleted");
        } else {
                System.out.println("Record doesn`t exist in database");
        }
    }

    @Test(dependsOnMethods = {"deleteRegisteredAccount"})
    public void validationDeletedAccount() throws SQLException, IOException {
        Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/myshop", "root", "admin");
        Statement st = connection.createStatement();
        ReadProperties readProperties = new ReadProperties();
        String ss = readProperties.getValues("userEmail");
        String query = "SELECT COUNT(email) AS email FROM oc_customer WHERE email = '" + ss + "'";
        ResultSet set = st.executeQuery(query);
        boolean recordInDataBase = true;
        while (set.next()) {
            int email = set.getInt("email");
            String info = email == 0 ? "No record in DataBase: "+recordInDataBase : "Record is still inside DataBase: "+recordInDataBase;
            System.out.println(info);
        }
    }

    @Test
    public void registerAccountTestWithoutRegister() throws IOException {
        RegisterAccountPage registerAccountPage = new RegisterAccountPage(driver);
        ReadProperties readProperties = new ReadProperties();
        registerAccountPage.setMyAccount();
        registerAccountPage.setRegisterAccount();
        registerAccountPage.setFirstName(readProperties.getValues("firstName"));
        registerAccountPage.setLastName(readProperties.getValues("lastName"));
        registerAccountPage.setUserEmail(readProperties.getValues("userEmail"));
        registerAccountPage.setUserPassword(readProperties.getValues("userPassword"));
        registerAccountPage.setPrivacyPolicySlider();


        Assert.assertEquals(registerAccountPage.getFirstNameAttribute(),"First Name");
        Assert.assertTrue(registerAccountPage.getPrivacyPolicySlider().isEnabled());
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
    public void registerAccountWithoutPrivacyPolicy() throws IOException {
        RegisterAccountPage registerAccountPage = new RegisterAccountPage(driver);
        ReadProperties readProperties = new ReadProperties();
        registerAccountPage.setMyAccount();
        registerAccountPage.setRegisterAccount();
        registerAccountPage.setFirstName(readProperties.getValues("firstName"));
        registerAccountPage.setLastName(readProperties.getValues("lastName"));
        registerAccountPage.setUserEmail(readProperties.getValues("userEmail"));
        registerAccountPage.setUserPassword(readProperties.getValues("userPassword"));
        registerAccountPage.setContinueButton();
        registerAccountPage.waitMethodForWebelement();

        Assert.assertTrue(registerAccountPage.getAlert().isDisplayed());
        Assert.assertEquals(registerAccountPage.getAlert().getText(),"Warning: You must agree to the Privacy Policy!");

    }

}
