package com.pages;

import com.codeborne.selenide.Condition;
import com.extras.HomePage;
import org.openqa.selenium.By;

public class LoginPage extends BasePage {
    private final By TXT_EMAIL = By.id("Email");
    private final By TXT_PASSWORD = By.id("Password");
    private final By BTN_LOGIN = By.xpath("//button[normalize-space()='Log in']");
    private String btnHomeLogin = "//app-header-before-login//button[normalize-space()='%s']";

    public LoginPage clickHomeLogin(String button) {
        click(generateDynamicElement(btnHomeLogin, button), Condition.appear, "Login - from Home Page");
        return this;
    }

    public LoginPage setUsername(String value) {
        sendKeys(TXT_EMAIL, Condition.editable, value);
        return this;
    }

    public LoginPage setPassword(String value) {
        sendKeys(TXT_PASSWORD, Condition.exist, value);
        return this;
    }

    public HomePage clickLogin() {
        click(BTN_LOGIN, Condition.enabled, "Login");
        return new HomePage();
    }
}
