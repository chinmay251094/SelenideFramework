package com.extras;

import com.annotations.TestDescription;
import com.enums.Author;
import com.enums.Category;
import com.extras.HomePage;
import com.pages.LoginPage;
import com.supplier.SupplierReader;
import com.supplier.TestDataSupplier;
import com.tests.BaseTest;
import org.assertj.core.api.Assertions;
import org.testng.annotations.Test;

public class HomePageTest extends BaseTest {
    @Test(dataProvider = "getDataFromExcel", dataProviderClass = SupplierReader.class)
    @TestDescription(description = "To test whether admin is able to suspend a user", author = Author.CHINMAY, category = Category.SMOKE)
    public void testSuspendUser(TestDataSupplier dataSupplier) {
        int expectedUser = LoginPage.getInstance()
                .clickHomeLogin("Login")
                .setUsername(dataSupplier.getUsername())
                .setPassword(dataSupplier.getPassword())
                .clickLogin()
                .clickApprovedUser();

        new HomePage()
                .clickSuspendUser()
                .clickConfirmSuspendUser();
        int verficationOfUserSuspended = new HomePage().verifyUserSuspended("SUSPENDED");

        Assertions.assertThat(expectedUser).isPositive();
        Assertions.assertThat(verficationOfUserSuspended).isPositive();
    }
}
