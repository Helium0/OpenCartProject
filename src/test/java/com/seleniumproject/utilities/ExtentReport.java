package com.seleniumproject.utilities;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import java.util.Date;

public class ExtentReport {

    public static ExtentReports extentReports;
    public static ExtentTest test;
    public static ExtentSparkReporter extentSparkReporter;

    public static void setExtentReport() {
        extentReports = new ExtentReports();
        extentSparkReporter = new ExtentSparkReporter(System.getProperty("user.dir") + "\\src\\test\\resources\\reports\\" + getTimeStampDate() + "_raport.html");
        extentReports.attachReporter(extentSparkReporter);
        extentSparkReporter.config().setReportName("Patrick`s Report");
        extentSparkReporter.config().setTimeStampFormat("MMM dd, yyyy HH:mm:ss");
        extentSparkReporter.config().setTheme(Theme.DARK);
    }

    public static void endReport()
    {
        extentReports.flush();
    }


    private static String getTimeStampDate() {
        Date date = new Date();
        return date.toString().replace(" ","_").replace(":","_");
    }


    public static String capturePhoto64(WebDriver driver) {
        TakesScreenshot screenshot = (TakesScreenshot) driver;
        return screenshot.getScreenshotAs(OutputType.BASE64);
    }

}
