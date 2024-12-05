package com.seleniumproject.webBase;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.Duration;

public class SeleniumHelper  {

    private static WebDriver driver;


    public static WebDriverWait waitObjMethod() {
        return new WebDriverWait(driver, Duration.ofSeconds(5));
    }

    public static Actions actions(WebDriver driver) {
        return new Actions(driver);
    }

    public Statement connectionStatement() throws SQLException {
        Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/myshop", "root","admin");
        return connection.createStatement();
    }



}
