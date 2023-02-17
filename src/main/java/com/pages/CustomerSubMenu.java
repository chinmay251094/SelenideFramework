package com.pages;

public enum CustomerSubMenu {
    CUSTOMER("Customer"),
    CUSTOMER_ROLE("CustomerRoles"),
    VENDOR("Vendor"),
    ACTIVITY_LOG("ActivityLog"),
    ACITIVITY_TYPES("ActivityTypes");

    public String getSubMenuCustomers() {
        return subMenuCustomers;
    }

    private final String subMenuCustomers;

    CustomerSubMenu(String subMenuCustomers) {
        this.subMenuCustomers = subMenuCustomers;
    }
}
