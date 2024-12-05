package com.seleniumproject.webBase;

import com.seleniumproject.testCases.RegistrationTest;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

public class ReadProperties extends RegistrationTest {


    private FileInputStream fileInputStream() throws FileNotFoundException {
        return new FileInputStream(System.getProperty("user.dir")+"\\src\\test\\resources\\dataVariables");
    }

    public String getValues(String value) throws IOException {
        Properties property = new Properties();
        property.load(fileInputStream());
        return  property.getProperty(value);
    }
}
