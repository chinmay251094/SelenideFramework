package com.reports;

import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.markuputils.Markup;
import com.utils.FrameworkConfig;
import com.utils.ScreenshotUtils;
import org.aeonbits.owner.ConfigFactory;

import static com.aventstack.extentreports.MediaEntityBuilder.createScreenCaptureFromBase64String;
import static com.reports.ReportsManager.getReports;
import static com.utils.ScreenshotUtils.getBase64;

public class ReportsLogger {
    private static FrameworkConfig frameworkConfig = ConfigFactory.create(FrameworkConfig.class);

    public static void pass(String message) {
        getReports().pass(message);
    }

    public static void pass(Markup message) {
        getReports().pass(message);
    }

    public static void fail(String message) {
        getReports().fail(message);
    }

    public static void fail(Markup message) {
        getReports().fail(message);
    }

    public static void skip(String message) {
        getReports().skip(message);
    }

    public static void skip(Markup message) {
        getReports().skip(message);
    }

    public static void info(Markup message) {
        getReports().info(message);
    }

    public static void info(String message) {
        getReports().info(message);
    }

    public static void pass(String message, boolean isScreenshotRequired) {
        if (frameworkConfig.passedstepscreenshot().equalsIgnoreCase("yes") && isScreenshotRequired) {
            getReports().pass(message, createScreenCaptureFromBase64String(getBase64()).build());
        } else {
            pass(message);
        }
    }

    public static void pass(Markup message, boolean isScreeshotNeeded) {
        if (frameworkConfig.passedstepscreenshot().equalsIgnoreCase("yes") && isScreeshotNeeded) {
            getReports().pass(createScreenCaptureFromBase64String(getBase64()).build());
            getReports().pass(message);
        } else {
            pass(message);
        }
    }

    public static void fail(String message, boolean isScreenshotRequired) {
        if (frameworkConfig.failedstepscreenshot().equalsIgnoreCase("yes") && isScreenshotRequired) {
            getReports().pass(message, createScreenCaptureFromBase64String(getBase64()).build());
        } else {
            fail(message);
        }
    }

    public static void fail(Markup message, boolean isScreeshotNeeded) {
        if (frameworkConfig.failedstepscreenshot().equalsIgnoreCase("yes") && isScreeshotNeeded) {
            getReports().fail(createScreenCaptureFromBase64String(getBase64()).build());
            getReports().fail(message);
        } else {
            fail(message);
        }
    }

    public static void skip(String message, boolean isScreenshotRequired) {
        if (frameworkConfig.skippedstepscreenshot().equalsIgnoreCase("yes") && isScreenshotRequired) {
            getReports().pass(message, createScreenCaptureFromBase64String(getBase64()).build());
        } else {
            skip(message);
        }
    }

    public static void skip(Markup message, boolean isScreeshotNeeded) {
        if (frameworkConfig.skippedstepscreenshot().equalsIgnoreCase("yes") && isScreeshotNeeded) {
            getReports().skip(createScreenCaptureFromBase64String(getBase64()).build());
            getReports().skip(message);
        } else {
            skip(message);
        }
    }
}
