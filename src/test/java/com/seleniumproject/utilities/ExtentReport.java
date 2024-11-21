package com.seleniumproject.utilities;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;


import java.util.Date;

public class ExtentReport {

    protected  static ExtentReports extentReports;

    public  static ExtentReports getExtentReport() {
        extentReports = new ExtentReports();
        ExtentSparkReporter extentSparkReporter = new ExtentSparkReporter(System.getProperty("user.dir") + "\\src\\test\\resources\\reports\\" + getTimeStampDate() + "raport.html");
        extentReports.attachReporter(extentSparkReporter);
        extentSparkReporter.config().setReportName("Patrick`s Report");
        extentSparkReporter.config().setTimeStampFormat("MMM dd, yyyy HH:mm:ss");
        extentSparkReporter.config().setTheme(Theme.DARK);
        return  extentReports;
    }

    public ExtentTest ext(String test) {
        return extentReports.createTest(test);
    }

    private static String getTimeStampDate() {
        Date date = new Date();
        return date.toString().replace(" ","_").replace(":","_");
    }

}
