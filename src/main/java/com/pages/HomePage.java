package com.pages;

import com.codeborne.selenide.Condition;
import org.openqa.selenium.By;

public class HomePage extends BasePage {
    private HomePage() {
    }

    public static HomePage getInstance() {
        return new HomePage();
    }

    private static final By BTN_LOGOUT = By.xpath("//a[@href='/logout' and normalize-space()='Logout']");
    private static final By MENU_ITEMS = By.xpath("//ul[@role='menu']/li/a");
    private static String dynamicSubMenuOptions = "//li[@class='nav-item has-treeview menu-is-opening menu-open']//a[@href='/Admin/%s/List']";
    private static final By BTN_ADD_NEW_CUSTOMER = By.xpath("//a[@href='/Admin/Customer/Create']");

    public boolean validateLogoutPresence() {
        return validateElementPresent(BTN_LOGOUT);
    }

    public boolean validateAddNewCustomerPresence() {
        return validateElementPresent(BTN_ADD_NEW_CUSTOMER);
    }

    public HomePage selectMenu(LeftMenuBarComponents menu) {
        clickDesiredElement(MENU_ITEMS, menu.getMenuName());
        return this;
    }

    public HomePage selectSubMenu(CustomerSubMenu subMenu) {
        generateDynamicSelenideElement(dynamicSubMenuOptions, subMenu.getSubMenuCustomers()).shouldBe(Condition.interactable).click();
        return this;
    }
}
