package com.pages;

import org.openqa.selenium.By;

public class HomePage extends BasePage {
    private static final By BTN_LOGOUT = By.xpath("//a[@href='/logout' and normalize-space()='Logout']");

    public boolean validateLogout() {
        return validateElementPresent(BTN_LOGOUT);
    }
}
