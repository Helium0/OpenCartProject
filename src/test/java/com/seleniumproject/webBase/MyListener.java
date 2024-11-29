package com.seleniumproject.webBase;


import com.aventstack.extentreports.Status;
import com.seleniumproject.utilities.ExtentReport;
import org.testng.ITestListener;
import org.testng.ITestResult;


public class MyListener extends BasePage implements ITestListener {


    @Override
    public void onTestStart(ITestResult result) {
        test = extentReports.createTest("Start Test: "+result.getName());
        test.log(Status.INFO, "Start Test: "+ result.getName());
    }

    @Override
    public void onTestSuccess(ITestResult result) {
        test = extentReports.createTest("Test Succeded: " +result.getName());
        test.log(Status.INFO, "Test Succeded: " + result.getName());
    }


    @Override
    public void onTestFailure(ITestResult result) {
        test = extentReports.createTest("Test Failed: "+result.getName());
        test.log(Status.FAIL,"Test Failed: "+result.getName()).addScreenCaptureFromBase64String(ExtentReport.capturePhoto64(driver));
    }
}



