package com.tests;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.WebDriverRunner;
import com.supplier.TestDataSupplier;
import com.utils.TestUtils;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import java.time.Duration;
import java.util.HashMap;
import java.util.Map;

import static com.constants.FrameworkConstants.getWaitTime;

public class BaseTest {
    @BeforeMethod
    public void setUp(Object[] data) {
        Map<String, TestDataSupplier> testDataMap = new HashMap<>();

        for (Object obj : data) {
            TestDataSupplier dataSupplier = (TestDataSupplier) obj;
            testDataMap.put(dataSupplier.getTestcase(), dataSupplier);
        }
        TestDataSupplier testDataSupplier = testDataMap.get(((TestDataSupplier) data[0]).getTestcase());
        TestUtils.setUrlForReports(testDataSupplier.getURL());
        // Set the browser configuration from the excel sheet
        Configuration.browser = String.valueOf(testDataSupplier.getBrowser()).toLowerCase();
        Configuration.baseUrl = testDataSupplier.getURL();

        // Use Selenide.open() method to open the URL
        Selenide.open(testDataSupplier.getURL());
        WebDriverRunner.getWebDriver().manage().window().maximize();
        WebDriverRunner.getWebDriver().manage().timeouts().implicitlyWait(Duration.ofSeconds(getWaitTime()));
        Configuration.screenshots = false;
        Configuration.savePageSource = false;
    }

    @AfterMethod
    public void tearDown() {
        Selenide.closeWebDriver();
    }
}



