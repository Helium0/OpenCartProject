package com.seleniumproject.webBase;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;

import java.time.Duration;


public class BasePage {

    protected static WebDriver driver;
    protected static Logger logger;
    protected static ExtentHtmlReporter extentHtmlReporter;
    protected static ExtentReports extentReports;



    @BeforeSuite
    public void beforeSuite() {
        extentHtmlReporter = new ExtentHtmlReporter("raport.html");
        extentReports = new ExtentReports();
        extentReports.attachReporter(extentHtmlReporter);
        extentHtmlReporter.config().setTimeStampFormat("MMM dd, yyyy HH:mm:ss");
        extentHtmlReporter.config().setTheme(Theme.DARK);
        extentHtmlReporter.config().setReportName("Patrick`s AutoTesting");

    }

    @AfterSuite
    public void afterSuite() {
        extentReports.flush();
        extentHtmlReporter.flush();
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
        System.out.println("Test passed");
    }

}
