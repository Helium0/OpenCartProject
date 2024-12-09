package com.seleniumproject.webBase;

import org.openqa.selenium.WebElement;

public class BaseComponentPage {

    protected WebElement rootElement;

    BaseComponentPage(WebElement rootElement) {
        this.rootElement = rootElement;
    }
}
