package com.pages;

public enum LeftMenuBarComponents {
    DASHBOARD("Dashboard"),
    CATALOG("Catalog"),
    SALES("Sales"),
    CUSTOMERS("Customers"),
    PROMOTIONS("Promotions");

    public String getMenuName() {
        return menuName;
    }

    private final String menuName;

    LeftMenuBarComponents(String menuName) {
        this.menuName = menuName;
    }
}
