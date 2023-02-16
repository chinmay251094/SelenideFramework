package com.extras;

import com.annotations.TestDescription;
import com.codeborne.selenide.SelenideElement;
import com.enums.Author;
import com.enums.Category;
import com.tests.BaseTest;
import org.testng.annotations.Test;
import com.supplier.SupplierReader;
import com.supplier.TestDataSupplier;

import static com.codeborne.selenide.Selenide.$;

public class FirstTest extends BaseTest {
    @Test(dataProvider = "getDataFromExcel", dataProviderClass = SupplierReader.class)
    @TestDescription(description = "To test search feature", author = Author.CHINMAY, category = Category.SMOKE)
    public void testSearch(TestDataSupplier dataSupplier) throws InterruptedException {
        SelenideElement searchInput = $("input[name='a']");
        searchInput.val("selenide").pressEnter();
        Thread.sleep(3000);
    }
}
