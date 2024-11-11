package com.seleniumproject.webBase;

import com.seleniumproject.funcionaltests.RegisterAccount;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

public class ReadProperties extends RegisterAccount {


    private FileInputStream fileInputStream() throws FileNotFoundException {
        FileInputStream file = new FileInputStream(System.getProperty("user.dir")+"\\src\\test\\resources\\dataVariables");
        return file;
    }

    public String getValues(String value) throws IOException {
        Properties property = new Properties();
        property.load(fileInputStream());
        String propertyFile = property.getProperty(value);
        return propertyFile;

    }
}
