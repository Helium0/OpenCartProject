package com.seleniumproject.webBase;


import com.aventstack.extentreports.Status;
import com.seleniumproject.utilities.ExtentReport;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.ITestListener;
import org.testng.ITestResult;
import org.apache.commons.io.FileUtils;
import java.io.File;
import java.io.IOException;


public class MyListener extends BasePage implements ITestListener {


    @Override
    public void onTestStart(ITestResult result) {
        test = extentReports.createTest(result.getName());
        test.log(Status.INFO, "Start Test: "+ result.getName());
    }

    @Override
    public void onTestSuccess(ITestResult result) {
        test = extentReports.createTest(result.getName());
        test.log(Status.INFO, "Test Succeded: " + result.getName());
    }

//    @Override
//    public void onTestFailure(ITestResult result) {
//        TakesScreenshot screen = (TakesScreenshot) driver;
//        File fileU = screen.getScreenshotAs(OutputType.FILE);
//        int number = (int)(Math.random()*1000);
//        try {
//            FileUtils.copyFile(fileU,new File(".\\src\\test\\resources\\screenshoots\\fileName"+number+".png"));
//        } catch (IOException e) {
//            throw new RuntimeException(e);
//        }
//
//    }


    @Override
    public void onTestFailure(ITestResult result) {
        test = extentReports.createTest("Test failed: "+result.getName());


    }
}



