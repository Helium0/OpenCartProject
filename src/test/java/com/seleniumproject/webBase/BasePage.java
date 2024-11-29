package com.seleniumproject.webBase;

import com.seleniumproject.utilities.ExtentReport;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.*;

import java.io.IOException;
import java.time.Duration;


public class BasePage extends ExtentReport {

    protected static WebDriver driver;
    protected static Logger logger;



    @BeforeSuite
    public void beforeSuite() {
        ExtentReport.setExtentReport();

    }

    @AfterSuite
    public void afterSuite() throws IOException {
        ExtentReport.endReport();

    }

    @BeforeMethod
    public WebDriver basePage() {
        logger = LogManager.getLogger(this.getClass());
        driver = new ChromeDriver();
        driver.get("http://localhost/OpenShop/");
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.manage().window().maximize();
        return  driver;
    }

    @AfterMethod
    public void closeDown() {
        driver.quit();
    }

}
