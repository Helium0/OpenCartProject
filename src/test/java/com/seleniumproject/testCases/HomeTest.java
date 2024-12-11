package com.seleniumproject.testCases;

import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.Status;
import com.seleniumproject.pages.HomePage;
import com.seleniumproject.utilities.ExtentReport;
import com.seleniumproject.webBase.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.Cookie;
import org.testng.Assert;
import org.testng.annotations.Test;
import java.util.Set;


public class HomeTest extends BasePage {



    @Test
    public void homePageTest() {
        HomePage homePage = new HomePage(driver);
        test = extentReports.createTest("Home_Page_Test").log(Status.PASS,"Created Test");
        logger.info("*** Starting Home_Page_Test ***");
        String currentUrl = driver.getCurrentUrl();
        Set<Cookie> cookies = driver.manage().getCookies();

        if (homePage.getTitle().equals("Your Store") && currentUrl.equals("http://localhost/OpenShop/") && cookies.size() == 2) {
            Assert.assertEquals(homePage.getTitle(), "Your Store");
            Assert.assertEquals(currentUrl, "http://localhost/OpenShop/");
            Assert.assertEquals(cookies.size(), 2);
            test.log(Status.PASS, "Assertions passed").info(MediaEntityBuilder.createScreenCaptureFromBase64String(ExtentReport.capturePhoto64(driver)).build());
            logger.info("*** Finished Home_Page_Test ***");
        } else {
            test.log(Status.FAIL, "Assertions failed").fail(MediaEntityBuilder.createScreenCaptureFromBase64String(ExtentReport.capturePhoto64(driver)).build());
            logger.info("Home_Page_Test Failed");
            Assert.fail("Home_Page_Test Failed");
        }
    }


    @Test
    public void changeCurrencyTest() {
        HomePage homePage = new HomePage(driver);
        test = extentReports.createTest("Change_Currency_Test").log(Status.PASS,"Created Test");
        logger.info("*** Starting Change_Currency_Test ***");
        homePage.getCurrency();
        int ss = driver.findElements(By.xpath("//ul[@class='dropdown-menu show']//li")).size();
        homePage.getDemandCurrency("£ Pound Sterling");
        test.log(Status.PASS, "Set Currency").info(MediaEntityBuilder.createScreenCaptureFromBase64String(ExtentReport.capturePhoto64(driver)).build());
        String price = driver.findElement(By.xpath("//button[contains(text(), '£0.00')]")).getText();

        if (ss == 3 && price.equals("0 item(s) - £0.00")) {
            Assert.assertEquals(ss, 3);
            Assert.assertEquals(price, "0 item(s) - £0.00");
            test.log(Status.PASS, "Assertions passed").info(MediaEntityBuilder.createScreenCaptureFromBase64String(ExtentReport.capturePhoto64(driver)).build());
            logger.info("*** Finished Change_Currency_Test ***");
        } else {
            test.log(Status.FAIL, "Assertions failed").fail(MediaEntityBuilder.createScreenCaptureFromBase64String(ExtentReport.capturePhoto64(driver)).build());
            logger.info("Change_Currency_Test Failed");
            Assert.fail("Change_Currency_Test Failed");
        }
    }

    @Test
    public void logoCheck() {
        HomePage homePage = new HomePage(driver);
        test = extentReports.createTest("Logo_Check_Test").log(Status.PASS,"Created Test");
        logger.info("*** Starting Logo_Check_Test ***");
        int howManyLogos = driver.findElements(By.xpath("//div[@class='col-2 text-center']//img")).size();

        if(howManyLogos == 11 && homePage.showLogo("Dell")) {

            Assert.assertEquals(howManyLogos, 11);
            Assert.assertTrue(homePage.showLogo("Dell"), "Logo has been displayed");
            test.log(Status.PASS, "Assertions passed").info(MediaEntityBuilder.createScreenCaptureFromBase64String(ExtentReport.capturePhoto64(driver)).build());
            logger.info("*** Finished Logo_Check_Test ***");
        } else {
            test.log(Status.FAIL, "Assertions failed").fail(MediaEntityBuilder.createScreenCaptureFromBase64String(ExtentReport.capturePhoto64(driver)).build());
            logger.info("Logo_Check_Test Failed");
            Assert.fail("Logo_Check_Test Failed");
        }
    }

}

