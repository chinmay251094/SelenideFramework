package com.reports;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
import com.enums.Author;
import com.enums.Category;
import com.utils.IconUtils;
import org.openqa.selenium.Capabilities;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.awt.*;
import java.io.File;
import java.util.Objects;

import static com.constants.FrameworkConstants.*;
import static com.reports.ReportsManager.getReports;
import static com.reports.ReportsManager.setReports;

public class Reports {
    static ExtentReports extentReports;

    public static void initReports() throws Exception {
        if (Objects.isNull(extentReports)) {
            extentReports = new ExtentReports();

            ExtentSparkReporter sparkReporter = new ExtentSparkReporter(getExtentReportFilePath());
            extentReports.attachReporter(sparkReporter);
            sparkReporter.config().setTheme(Theme.STANDARD);
            sparkReporter.config().setReportName("Test Automation Reports");
            sparkReporter.config().setDocumentTitle("Google Inc.");
            extentReports.setSystemInfo("Organization", "<img src='" + System.getProperty("user.dir") + "/src/test/resources/Google.png' style='float:left; width:50px; height:50px; vertical-align:middle;'>&nbsp;&nbsp;" + "<b style='vertical-align:middle;'> Google Inc. </b>" + "<a href='" + ICON_ORGANIZATION_SITE + "'>" + ICON_ORGANIZATION_SITE + "</a>");
            extentReports.setSystemInfo("Department", "<i><b>Engineering (Automation Testing)</i></b>" + "  " + ICON_BUG);
            extentReports.setSystemInfo("Manager", "<i><b>" + MANAGER + "</i></b>" + " " + ICON_MANAGER);
            extentReports.setSystemInfo("Team Members", "<i><b>" + MEMBER1 + "</i></b> " + ICON_TEAM + "<br>" +
                    "<i><b>" + MEMBER2 + "</i></b> " + ICON_TEAM + "<br>" +
                    "<i><b>" + MEMBER3 + "</i></b> " + ICON_TEAM + "<br>");
        }
    }

    public static void flushReports() throws Exception {
        if (Objects.nonNull(extentReports)) {
            extentReports.flush();
        }
        ReportsManager.unLoad();
        Desktop.getDesktop().browse(new File(getExtentReportFilePath()).toURI());
    }

    public static void createTests(String testName) {
        setReports(extentReports.createTest("<span style='font-size:14px; font-weight:bold;'>" + IconUtils.getBrowserIcon() + ": " + testName + "</span>"));
    }

    public static String getBrowserNameAndVersion(WebDriver driver) {
        Capabilities cap = ((RemoteWebDriver) driver).getCapabilities();
        String browserName = cap.getBrowserName();
        String browserVersion = cap.getBrowserVersion();
        String browserIcon = "";

        switch (browserName) {
            case "chrome":
                browserIcon = "<i class='fa fa-chrome' style='font-size:18px'></i>";
                break;
            case "firefox":
                browserIcon = "<i class='fa fa-firefox' style='font-size:18px'></i>";
                break;
            case "safari":
                browserIcon = "<i class='fa fa-safari' style='font-size:18px'></i>";
                break;
            case "internet explorer":
                browserIcon = "<i class='fa fa-internet-explorer' style='font-size:18px'></i>";
                break;
            default:
                browserIcon = "<i class='fa fa-browser' style='font-size:18px'></i>";
                break;
        }

        return browserIcon + " " + browserName + " " + browserVersion;
    }


    public static void assignTestAttributes(String description, Author[] authors, Category[] categories) {
        if (description != null && !description.isEmpty()) {
            createTests(description);
            for (Author author : authors) {
                getReports().assignAuthor(author.name());
            }
            for (Category category : categories) {
                getReports().assignCategory(category.name());
            }
        } else {
            throw new IllegalArgumentException("Test Description must not be null or empty");
        }
    }

    public static String getAuthorList(Author[] authors) {
        StringBuilder authorList = new StringBuilder();
        for (Author author : authors) {
            authorList.append(author.name()).append(", ");
        }
        return authorList.toString().substring(0, authorList.length() - 2);
    }

    public static String getCategoryList(Category[] categories) {
        StringBuilder categoryList = new StringBuilder();
        for (Category category : categories) {
            categoryList.append(category.name()).append(", ");
        }
        return categoryList.toString().substring(0, categoryList.length() - 2);
    }
}
