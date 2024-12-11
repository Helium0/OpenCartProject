package com.seleniumproject.testCases;

import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.Status;
import com.seleniumproject.pages.CartPage;
import com.seleniumproject.utilities.ExtentReport;
import com.seleniumproject.webBase.BasePage;
import com.seleniumproject.webBase.ReadProperties;
import org.junit.Assert;
import org.openqa.selenium.*;
import org.testng.annotations.Test;
import java.io.IOException;



public class CartTest extends BasePage {

    private static final String ADDED_NIKON_TO_CART = "Success: You have added Nikon D300 to your shopping cart!";
    private static final String REMOVED_NIKON_TO_CART = "Success: You have removed an item from your shopping cart!";

    @Test
    public void addProductToTheCart() throws InterruptedException {
        test = extentReports.createTest("Add_Product_To_The_Cart_Test").log(Status.PASS, "Created Test");
        logger.info("*** Starting Add_Product_To_The_Cart_Test ***");
        CartPage cartPage = new CartPage(driver);
        cartPage.getCamerasOption();
        cartPage.getNikonCamera();
        cartPage.clickAddOnCartButton();

        if(cartPage.productHasBeenAdded().equals(ADDED_NIKON_TO_CART)) {
            test.log(Status.PASS,"Assertion passed").info(MediaEntityBuilder.createScreenCaptureFromBase64String(ExtentReport.capturePhoto64(driver)).build());
            logger.info("*** Finished Add_Product_To_The_Cart_Test ***");
            Assert.assertEquals(cartPage.productHasBeenAdded(),ADDED_NIKON_TO_CART);
        } else {
            test.log(Status.FAIL,"Assertion failed").info(MediaEntityBuilder.createScreenCaptureFromBase64String(ExtentReport.capturePhoto64(driver)).build());
            logger.info("Add_Product_To_The_Cart_Test Failed");
            Assert.fail("Add_Product_To_The_Cart_Test Failed");

        }
        Assert.assertEquals(cartPage.productHasBeenAdded(),ADDED_NIKON_TO_CART);


    }

    @Test
    public void addProductToTheCartAndDelete() throws InterruptedException {
        test = extentReports.createTest("Add_Product_To_The_Cart_And_Delete_Test").log(Status.PASS, "Created Test");
        logger.info("*** Starting Add_Product_To_The_Cart_And_Delete_Test ***");
        CartPage cartPage = new CartPage(driver);
        cartPage.getCamerasOption();
        cartPage.getNikonCamera();
        cartPage.clickAddOnCartButton();
        cartPage.waitUntilTextVisible("1 item(s) - $98.00");
        cartPage.clickOnItemsCart();
        cartPage.removeItemsFromCart();
        if(cartPage.productHasBeenRemoved().equals(REMOVED_NIKON_TO_CART)) {
            test.log(Status.PASS,"Assertion passed").info(MediaEntityBuilder.createScreenCaptureFromBase64String(ExtentReport.capturePhoto64(driver)).build());
            logger.info("*** Finished Add_Product_To_The_Cart_And_Delete_Test ***");
            Assert.assertEquals(cartPage.productHasBeenRemoved(),REMOVED_NIKON_TO_CART);
        } else {
            test.log(Status.FAIL,"Assertion failed").info(MediaEntityBuilder.createScreenCaptureFromBase64String(ExtentReport.capturePhoto64(driver)).build());
            logger.info("Add_Product_To_The_Cart_And_Delete_Test Failed");
            Assert.fail("Add_Product_To_The_Cart_And_Delete_Test Failed");
        }

    }

    @Test
    public void addProductToTheCardAndUpdateQuantity() throws InterruptedException {
        test = extentReports.createTest("Add_Product_To_The_Cart_And_Update_Quantity_Test").log(Status.PASS, "Created Test");
        logger.info("*** Starting Add_Product_To_The_Cart_And_Update_Quantity_Test ***");
        CartPage cartPage = new CartPage(driver);
        cartPage.getCamerasOption();
        cartPage.getNikonCamera();
        cartPage.clickAddOnCartButton();
        cartPage.updateQuantity("4");
        cartPage.clickAddOnCartButton();
        cartPage.waitUntilTextVisible("5 item(s) - $490.00");
        cartPage.clickOnItemsCart();

        if (cartPage.checkTotalPrice().equals("$490.00")) {
            test.log(Status.PASS,"Assertion passed").info(MediaEntityBuilder.createScreenCaptureFromBase64String(ExtentReport.capturePhoto64(driver)).build());
            logger.info("*** Finished Add_Product_To_The_Cart_And_Update_Quantity_Test ***");
            Assert.assertEquals(cartPage.checkTotalPrice(),"$490.00");
        } else {
            test.log(Status.FAIL,"Add_Product_To_The_Cart_And_Update_Quantity_Test failed").info(MediaEntityBuilder.createScreenCaptureFromBase64String(ExtentReport.capturePhoto64(driver)).build());
            logger.info("Add_Product_To_The_Cart_And_Update_Quantity_Test Failed");
            Assert.fail("Add_Product_To_The_Cart_And_Update_Quantity_Test Failed");

        }
    }

    @Test
    public void investigateProductPictures() throws InterruptedException {
        test = extentReports.createTest("Investigate_Product_Pictures_Test").log(Status.PASS, "Created Test");
        logger.info("*** Starting Investigate_Product_Pictures_Test ***");
        CartPage cartPage = new CartPage(driver);
        cartPage.getCamerasOption();
        cartPage.getNikonCamera();
        cartPage.clickOnPicture();
        cartPage.picturesNumber();

        if(cartPage.picturesNumber() == 5) {
            test.log(Status.PASS,"Assertion passed").info(MediaEntityBuilder.createScreenCaptureFromBase64String(ExtentReport.capturePhoto64(driver)).build());
            logger.info("*** Finished Investigate_Product_Pictures_Test ***");
            Assert.assertEquals(cartPage.picturesNumber(), 5);
        } else {
            test.log(Status.FAIL,"Investigate_Product_Pictures_Test failed").info(MediaEntityBuilder.createScreenCaptureFromBase64String(ExtentReport.capturePhoto64(driver)).build());
            logger.info("Investigate_Product_Pictures_Test Failed");
            Assert.fail("Investigate_Product_Pictures_Test Failed");

        }
    }

    @Test
    public void writeReviewAndSetRate() throws InterruptedException, IOException {
        test = extentReports.createTest("Write_Review_And_Set_Rate_Test").log(Status.PASS, "Created Test");
        logger.info("*** Starting Write_Review_And_Set_Rate_Test ***");
        ReadProperties readProperties = new ReadProperties();
        CartPage cartPage = new CartPage(driver);
        cartPage.getCamerasOption();
        cartPage.getNikonCamera();
        driver.findElement(By.partialLinkText("Write")).click();
        driver.findElement(By.id("input-name")).sendKeys(readProperties.getValues("firstName"));
        driver.findElement(By.id("input-text")).sendKeys(readProperties.getValues("reviewText"));
        cartPage.clickOnRate("value", "5");

        if (cartPage.selectedRadiobutton().isSelected()) {
            test.log(Status.PASS, "Assertion passed").info(MediaEntityBuilder.createScreenCaptureFromBase64String(ExtentReport.capturePhoto64(driver)).build());
            logger.info("*** Finished Write_Review_And_Set_Rate_Test ***");
            Assert.assertTrue("Selected",cartPage.selectedRadiobutton().isSelected());
            Thread.sleep(4000);
        } else {
            test.log(Status.FAIL,"Write_Review_And_Set_Rate_Test failed").info(MediaEntityBuilder.createScreenCaptureFromBase64String(ExtentReport.capturePhoto64(driver)).build());
            logger.info("Write_Review_And_Set_Rate_Test Failed");
            Assert.fail("Write_Review_And_Set_Rate_Test Failed");
        }
    }




/*        driver.findElement(By.id("button-review")).click();  This test will always fail.

    This test will always fail. When I clicked the button I got:
    SyntaxError: Unexpected token '<', "<b>Error: "... is not valid JSON
    OK
    <b>Error: Could not load model Opencart\Catalog\Model\Product\Product
 */




    @Test
    public void compareTwoProducts() throws InterruptedException {
        test = extentReports.createTest("Compare_Two_Products_Test").log(Status.PASS, "Created Test");
        logger.info("*** Starting Compare_Two_Products_Test ***");
        CartPage cartPage = new CartPage(driver);
        cartPage.getCamerasOption();
        cartPage.clickCompareButton();
        cartPage.clickOnCompareButton();

        if(cartPage.countComparedItems() == 2) {
            test.log(Status.PASS,"Assertion passed").info(MediaEntityBuilder.createScreenCaptureFromBase64String(ExtentReport.capturePhoto64(driver)).build());
            logger.info("*** Finished Compare_Two_Products_Test ***");
            Assert.assertEquals(cartPage.countComparedItems(), 2);
        } else {
            test.log(Status.FAIL,"Compare_Two_Products_Test failed").info(MediaEntityBuilder.createScreenCaptureFromBase64String(ExtentReport.capturePhoto64(driver)).build());
            logger.info("Compare_Two_Products_Test Failed");
            Assert.fail("Compare_Two_Products_Test Failed");
        }
    }



}







