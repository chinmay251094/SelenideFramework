package com.utils;

import com.codeborne.selenide.WebDriverRunner;
import org.openqa.selenium.Capabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

public class BrowserInfoUtils {
    //private -> We do not want anyone to create the object of this class
    //Private constructor to avoid external instantiation
    private BrowserInfoUtils() {
    }

    public static String getBrowserInfo() {
        Capabilities capabilities = ((RemoteWebDriver) WebDriverRunner.getWebDriver()).getCapabilities();
        return capabilities.getBrowserName().toUpperCase();

    }

    public static String getBrowserVersionInfo() {
        Capabilities capabilities = ((RemoteWebDriver) WebDriverRunner.getWebDriver()).getCapabilities();
        return capabilities.getBrowserVersion();

    }
}
