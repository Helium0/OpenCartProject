package com.seleniumproject.testCases;

import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.Status;
import com.seleniumproject.pages.HomePage;
import com.seleniumproject.pages.SearchBarPage;
import com.seleniumproject.utilities.ExtentReport;
import com.seleniumproject.webBase.BasePage;
import com.seleniumproject.webBase.ProductComponent;
import org.testng.Assert;
import org.testng.annotations.Test;


public class SearchBarTest extends BasePage {

    private static final String MobilePhone = "Iphone";
    private static final String MONITOR2 = "Samsung SyncMaster 941BW";
    private static final String PC = "PC";

    @Test
    public void searchProduct() throws InterruptedException {
        test = extentReports.createTest("Search_Product_Test").log(Status.PASS,"Created Test");
        logger.info("*** Starting Search_Product_Test ***");
        HomePage homePage = new HomePage(driver);
        homePage.writeOnSearchBar(MobilePhone);
        homePage.clickOnTheButton();
        SearchBarPage searchPage = new SearchBarPage(driver);
        searchPage.getAllProducts();

        if(searchPage.getAllProducts().size() == 1) {
            Assert.assertEquals(searchPage.getAllProducts().size(), 1);
            test.log(Status.PASS, "Assertion passed").info(MediaEntityBuilder.createScreenCaptureFromBase64String(ExtentReport.capturePhoto64(driver)).build());
            logger.info("*** Finished Search_Product_Test ***");
        } else {
            test.log(Status.FAIL, "Assertions failed").fail(MediaEntityBuilder.createScreenCaptureFromBase64String(ExtentReport.capturePhoto64(driver)).build());
            logger.info("Search_Product_Test Failed");
            Assert.fail("Search_Product_Test Failed");
        }
    }

    @Test
    public void searchCameraByName() throws InterruptedException {
        test = extentReports.createTest("Search_Camera_By_Name_Test").log(Status.PASS,"Created Test");
        logger.info("*** Starting Search_Camera_By_Name_Test ***");
        HomePage homePage = new HomePage(driver);
        homePage.writeOnSearchBar("Samsung");
        homePage.clickOnTheButton();
        ProductComponent searchBarPage = new SearchBarPage(driver).getProduct(productComponent -> productComponent.getName().contains("SyncMaster"));


        if(searchBarPage.getName().equals(MONITOR2) && searchBarPage.getPrice().equals("$242.00")) {
            Assert.assertEquals(searchBarPage.getName(), MONITOR2);
            Assert.assertEquals(searchBarPage.getPrice(), "$242.00");
            test.log(Status.PASS, "Assertions passed").info(MediaEntityBuilder.createScreenCaptureFromBase64String(ExtentReport.capturePhoto64(driver)).build());
            logger.info("*** Finished Search_Camera_By_Name_Test ***");
        } else {
            test.log(Status.FAIL, "Assertions failed").fail(MediaEntityBuilder.createScreenCaptureFromBase64String(ExtentReport.capturePhoto64(driver)).build());
            logger.info("Search_Camera_By_Name_Test Failed");
            Assert.fail("Search_Camera_By_Name_Test Failed");
        }

    }

    @Test
    public void searchNotExistedProduct() {
        test = extentReports.createTest("Search_Not_Existed_Product_Test").log(Status.PASS,"Created Test");
        logger.info("*** Starting Search_Not_Existed_Product_Test ***");
        HomePage homePage = new HomePage(driver);
        homePage.writeOnSearchBar(PC);
        homePage.clickOnTheButton();
        SearchBarPage searchPage = new SearchBarPage(driver);
        searchPage.getAllProducts();

        if(searchPage.getAllProducts().size() == 0) {
            Assert.assertEquals(searchPage.getAllProducts().size(), 0);
            test.log(Status.PASS, "Assertion passed").info(MediaEntityBuilder.createScreenCaptureFromBase64String(ExtentReport.capturePhoto64(driver)).build());
            logger.info("*** Finished Search_Not_Existed_Product_Test ***");
        } else {
            test.log(Status.FAIL, "Assertions failed").fail(MediaEntityBuilder.createScreenCaptureFromBase64String(ExtentReport.capturePhoto64(driver)).build());
            logger.info("Search_Camera_By_Name_Test Failed");
            Assert.fail("Search_Camera_By_Name_Test Failed");
        }
    }

}
