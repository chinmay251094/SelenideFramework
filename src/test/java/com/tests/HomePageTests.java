package com.tests;

import com.annotations.TestDescription;
import com.enums.Author;
import com.enums.Category;
import com.pages.CustomerSubMenu;
import com.pages.HomePage;
import com.pages.LeftMenuBarComponents;
import com.pages.LoginPage;
import com.supplier.SupplierReader;
import com.supplier.TestDataSupplier;
import org.testng.annotations.Test;

import static com.utils.VerificationUtils.validate;

public class HomePageTests extends BaseTest {
    private HomePageTests() {
    }

    @Test(dataProvider = "getDataFromExcel", dataProviderClass = SupplierReader.class)
    @TestDescription(description = "To test login feature", author = Author.CHINMAY, category = Category.SMOKE)
    void testCreateNewCustomer(TestDataSupplier dataSupplier) {
        LoginPage.getInstance()
                .setUsername(dataSupplier.getUsername())
                .setPassword(dataSupplier.getPassword())
                .clickLogin();

        HomePage.getInstance()
                .selectMenu(LeftMenuBarComponents.CUSTOMERS)
                .selectSubMenu(CustomerSubMenu.CUSTOMER);

        validate(HomePage.getInstance().validateAddNewCustomerPresence(), true, "Create New Customer option availability");
    }
}
