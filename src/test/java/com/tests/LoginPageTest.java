package com.tests;

import com.annotations.TestDescription;
import com.enums.Author;
import com.enums.Category;
import com.pages.HomePage;
import com.pages.LoginPage;
import com.supplier.SupplierReader;
import com.supplier.TestDataSupplier;
import org.testng.annotations.Test;

import static com.utils.VerificationUtils.validate;

public class LoginPageTest extends BaseTest {
    private LoginPageTest() {
    }

    @Test(dataProvider = "getDataFromExcel", dataProviderClass = SupplierReader.class)
    @TestDescription(description = "To test login feature", author = Author.CHINMAY, category = Category.SMOKE)
    void loginTest(TestDataSupplier dataSupplier) {
        LoginPage.getInstance()
                .setUsername(dataSupplier.getUsername())
                .setPassword(dataSupplier.getPassword())
                .clickLogin();

        validate(HomePage.getInstance().validateLogoutPresence(), true, "Validate successful login");
    }
}
