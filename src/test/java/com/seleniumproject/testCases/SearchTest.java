package com.seleniumproject.testCases;

import com.seleniumproject.pages.HomePage;
import com.seleniumproject.pages.SearchPage;
import com.seleniumproject.webBase.BasePage;
import com.seleniumproject.webBase.ProductComponent;
import org.testng.Assert;
import org.testng.annotations.Test;


public class SearchTest extends BasePage {

    private static final String MobilePhone = "Iphone";
    private static final String MONITOR2 = "Samsung SyncMaster 941BW";
    private static final String PC = "PC";

    @Test
    public void searchProduct() throws InterruptedException {
        HomePage homePage = new HomePage(driver);
        homePage.writeOnSearchBar(MobilePhone);
        homePage.clickOnTheButton();
        SearchPage searchPage = new SearchPage(driver);
        searchPage.getAllProducts();

        Assert.assertEquals(searchPage.getAllProducts().size(),1 );

    }

    @Test
    public void searchCameraByName() throws InterruptedException {
        HomePage homePage = new HomePage(driver);
        homePage.writeOnSearchBar("Samsung");
        homePage.clickOnTheButton();
        ProductComponent searchPage = new SearchPage(driver).getProduct(productComponent -> productComponent.getName().contains("SyncMaster"));

        Assert.assertEquals(searchPage.getName(),MONITOR2);
        Assert.assertEquals(searchPage.getPrice(),"$242.00");


    }

    @Test
    public void searchNotExistedProduct() {
        HomePage homePage = new HomePage(driver);
        homePage.writeOnSearchBar(PC);
        homePage.clickOnTheButton();
        SearchPage searchPage = new SearchPage(driver);
        searchPage.getAllProducts();

        Assert.assertEquals(searchPage.getAllProducts().size(),0);

    }

}
