package com.listeners;

import com.annotations.TestDescription;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.Markup;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import com.codeborne.selenide.WebDriverRunner;
import com.enums.Author;
import com.enums.Category;
import com.utils.*;
import lombok.SneakyThrows;
import org.aeonbits.owner.ConfigFactory;
import org.testng.*;

import java.awt.*;
import java.io.IOException;
import java.lang.reflect.Method;
import java.util.Arrays;

import static com.constants.FrameworkConstants.*;
import static com.pages.BasePage.takeScreenshot;
import static com.reports.Reports.*;
import static com.reports.ReportsLogger.*;
import static com.reports.ReportsManager.getReports;

public class TestListener implements ITestListener, ISuiteListener {
    String url = TestUtils.getUrlForReports();
    static int count_passedTCs;
    static int count_skippedTCs;
    static int count_failedTCs;
    static int count_totalTCs;

    private static FrameworkConfig frameworkConfig = ConfigFactory.create(FrameworkConfig.class);

    private ScreenRecoderUtils screenRecorder;

    public TestListener() {
        try {
            screenRecorder = new ScreenRecoderUtils();
        } catch (IOException | AWTException e) {
            System.out.println(e.getMessage());
        }
    }

    @SneakyThrows
    @Override
    public void onStart(ISuite suite) {
        initReports();
    }

    @SneakyThrows
    @Override
    public void onFinish(ISuite suite) {
        flushReports();
    }

    @Override
    public void onTestStart(ITestResult result) {
        count_totalTCs = count_totalTCs + 1;
        Author[] authors = new Author[0];
        Category[] categories = new Category[0];
        Method method = result.getMethod().getConstructorOrMethod().getMethod();
        String description = method.getName();
        if (method.isAnnotationPresent(TestDescription.class)) {
            TestDescription testDescription = method.getAnnotation(TestDescription.class);
            description = testDescription.description();
            authors = testDescription.author();
            categories = testDescription.category();
            assignTestAttributes(description, authors, categories);
        }
        String browserNameAndVersion = getBrowserNameAndVersion(WebDriverRunner.getWebDriver());
        getReports().assignDevice(browserNameAndVersion);
        info(BOLD_START + IconUtils.getOSIcon() + "  &  " + IconUtils.getBrowserIcon() + " --------- "
                + BrowserOSInfoUtils.getOS_Browser_BrowserVersionInfo() + BOLD_END);
        info(ICON_AUTHOR + " Author(s): " + "<b>" + getAuthorList(authors) + "</b>");
        info(ICON_CATEGORY + " Category: " + "<b>" + getCategoryList(categories) + "</b>");
        info(ICON_Navigate_Right + "  Navigating to : <a href=" + url + "><b>" + url + "</b></a>");

        if (frameworkConfig.video_record().toLowerCase().trim().equals("yes")) {
            if (description != null && !description.isEmpty()) {
                screenRecorder.startRecording(description);
            }
        }
    }

    @SneakyThrows
    @Override
    public void onTestSuccess(ITestResult result) {
        String description = null;
        count_passedTCs = count_passedTCs + 1;
        Method method = result.getMethod().getConstructorOrMethod().getMethod();
        if (method.isAnnotationPresent(TestDescription.class)) {
            TestDescription testDescription = method.getAnnotation(TestDescription.class);
            description = testDescription.description();
            if (description != null && !description.isEmpty()) {
                String logText = "<b>" + description + " is passed.</b>" + "  " + ICON_SMILEY_PASS;
                Markup markup_message = MarkupHelper.createLabel(logText, ExtentColor.GREEN);
                pass(markup_message, false);
            } else {
                throw new IllegalArgumentException("Test Description must not be null or empty");
            }
        }

        if (frameworkConfig.takescreenshots().toLowerCase().trim().equals("yes")) {
            takeScreenshot("PASSED_" + description);
        }

        if (frameworkConfig.video_record().toLowerCase().trim().equals("yes")) {
            screenRecorder.stopRecording(true);
        }
    }

    @SneakyThrows
    @Override
    public void onTestFailure(ITestResult result) {
        String description = null;
        count_failedTCs = count_failedTCs + 1;
        Method method = result.getMethod().getConstructorOrMethod().getMethod();
        if (method.isAnnotationPresent(TestDescription.class)) {
            TestDescription testDescription = method.getAnnotation(TestDescription.class);
            description = testDescription.description();
            if (description != null && !description.isEmpty()) {
                try {
                    fail(ICON_BUG + "  " + "<b><i>" + result.getThrowable().toString() + "</i></b>");
                    String exceptionMessage = Arrays.toString(result.getThrowable().getStackTrace());
                    String message = "<details><summary><b><font color=red> Exception occured, click to see details: "
                            + ICON_SMILEY_FAIL + " </font></b>" + "</summary>" + exceptionMessage.replaceAll(",", "<br>")
                            + "</details> \n";

                    fail(message);
                    String logText = BOLD_START + description + " has failed." + BOLD_END + "  " + ICON_SMILEY_FAIL;
                    Markup markup_message = MarkupHelper.createLabel(logText, ExtentColor.RED);
                    fail(markup_message, true);
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            } else {
                throw new IllegalArgumentException("Test Description must not be null or empty");
            }
        }

        if (frameworkConfig.takescreenshots().toLowerCase().trim().equals("yes")) {
            takeScreenshot("FAILED_" + description);
        }

        if (frameworkConfig.video_record().toLowerCase().trim().equals("yes")) {
            screenRecorder.stopRecording(true);
        }
    }

    @SneakyThrows
    @Override
    public void onTestSkipped(ITestResult result) {
        String description = null;
        count_skippedTCs = count_skippedTCs + 1;
        Method method = result.getMethod().getConstructorOrMethod().getMethod();
        if (method.isAnnotationPresent(TestDescription.class)) {
            TestDescription testDescription = method.getAnnotation(TestDescription.class);
            description = testDescription.description();
            if (description != null && !description.isEmpty()) {
                skip(ICON_BUG + "  " + "<b><i>" + result.getThrowable().toString() + "</i></b>");
                skip(description + " has been skipped.", false);
                String logText = "<b>" + result.getMethod().getMethodName() + " is skipped.</b>" + "  " + ICON_SMILEY_SKIP;
                Markup markup_message = MarkupHelper.createLabel(logText, ExtentColor.YELLOW);
                skip(markup_message, true);
            } else {
                throw new IllegalArgumentException("Test Description must not be null or empty");
            }
        }

        if (frameworkConfig.takescreenshots().toLowerCase().trim().equals("yes")) {
            takeScreenshot("SKIPPED_" + description);
        }

        if (frameworkConfig.video_record().toLowerCase().trim().equals("yes")) {
            screenRecorder.stopRecording(true);
        }
    }

    @Override
    public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
        //not in use for now
    }

    @Override
    public void onStart(ITestContext context) {
        //not in use for now
    }

    @Override
    public void onFinish(ITestContext context) {
        //not in use for now
    }
}
