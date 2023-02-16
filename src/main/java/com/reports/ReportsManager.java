package com.reports;

import com.aventstack.extentreports.ExtentTest;

public class ReportsManager {
    private static ThreadLocal<ExtentTest> reports = new ThreadLocal<>();

    public static ExtentTest getReports() {
        return reports.get();
    }

    public static void setReports(ExtentTest report) {
        reports.set(report);
    }

    public static void unLoad() {
        reports.remove();
    }
}
