package com.pages;

import com.codeborne.selenide.*;
import com.codeborne.selenide.impl.Waiter;
import org.openqa.selenium.*;
import org.openqa.selenium.io.FileHandler;
import org.openqa.selenium.support.ui.Wait;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.function.Function;

import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selectors.withText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;
import static com.reports.ReportsLogger.pass;
import static org.openqa.selenium.By.xpath;

public class BasePage {
    private static SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy_HH'h'mm'm'ss's'");

    private static void highlightElement(SelenideElement element) {
        JavascriptExecutor js = (JavascriptExecutor) WebDriverRunner.getWebDriver();
        js.executeScript("arguments[0].style.border='3px solid red'", element);
    }

    private static void highlightElements(List<SelenideElement> elements) {
        JavascriptExecutor js = (JavascriptExecutor) WebDriverRunner.getWebDriver();
        for (SelenideElement element : elements) {
            js.executeScript("arguments[0].style.border='3px solid red'", element);
        }
    }

    protected static void click(By by, Condition wait, String value) {
        highlightElement($(by));
        $(by).click();
        pass(value + " has been clicked.");
    }

    protected static void click(By by, String element, String value) {
        highlightElement($(by));
        $(byText(element)).click();
        pass(value + " has been clicked.");
    }

    protected static void click(SelenideElement element, Condition wait, String value) {
        highlightElement(element);
        element.shouldBe(wait).clear();
        element.click();
        pass(value + " has been clicked.");
    }

    protected static void sendKeys(By by, Condition wait, String value) {
        highlightElement($(by));
        $(by).shouldBe(wait).clear();
        $(by).sendKeys(value);
        pass(value + " has been set in text-box");
    }

    protected static void sendKeys(By by, String element, String value) {
        highlightElement($(by));
        $(by).clear();
        $(byText(element)).shouldBe(Condition.visible).sendKeys(value);
        pass(value + " has been set in text-box");
    }

    protected static SelenideElement getElement(By by) {
        return $(by);
    }

    protected static ElementsCollection getElements(By by) {
        return $$(by);
    }

    protected static boolean validateElementPresent(By by) {
        return !$$(by).isEmpty();
    }

    protected static String getElementText(By by) {
        return $(by).getText();
    }

    protected static void scrollDown() {
        JavascriptExecutor js = (JavascriptExecutor) WebDriverRunner.getWebDriver();
        js.executeScript("window.scrollBy(0,500)");
    }

    protected static SelenideElement withTagAndText(String tagName, String text) {
        return $(withTagAndText(tagName, text));
    }

    protected static void clickDesiredElement(By by, String item) {
        ElementsCollection elements = $$(by);

        for (SelenideElement element : elements) {
            if (element.getText().equals(item)) {
                element.click();
                break;
            }
        }
    }

    protected static SelenideElement generateDynamicSelenideElement(String xpath, String value) {
        return $(xpath(String.format(xpath, value)));
    }

    public static void takeScreenshot(String testcase) {
        TakesScreenshot ts = (TakesScreenshot) WebDriverRunner.getWebDriver();
        File source = ts.getScreenshotAs(OutputType.FILE);

        String screenshotsDirPath = System.getProperty("user.dir") + "/screenshots/";
        File screenshotsDir = new File(screenshotsDirPath);
        if (!screenshotsDir.exists()) {
            screenshotsDir.mkdirs();
        }

        String screenshotFilePath = screenshotsDirPath + testcase + "_" + dateFormat.format(new Date()) + ".png";
        File destination = new File(screenshotFilePath);

        try {
            FileHandler.copy(source, destination);
        } catch (IOException e) {
            System.out.println("Failed to take screenshot: " + e.getMessage());
        }
    }
}
