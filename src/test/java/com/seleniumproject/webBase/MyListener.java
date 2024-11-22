package com.seleniumproject.webBase;


import com.seleniumproject.utilities.ExtentReport;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.testng.ITestListener;
import org.testng.ITestResult;
import org.apache.commons.io.FileUtils;
import java.io.File;
import java.io.IOException;


public class MyListener extends BasePage implements ITestListener {



    @Override
    public void onTestSuccess(ITestResult result) {

        ExtentReport extentReport = new ExtentReport();
        extentReport.getExtentReport();
    }

    @Override
    public void onTestFailure(ITestResult result) {
        
        TakesScreenshot screen = (TakesScreenshot) driver;
        File fileU = screen.getScreenshotAs(OutputType.FILE);
        int number = (int)(Math.random()*1000);
        try {
            FileUtils.copyFile(fileU,new File(".\\src\\test\\resources\\screenshoots\\fileName"+number+".png"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }
}
