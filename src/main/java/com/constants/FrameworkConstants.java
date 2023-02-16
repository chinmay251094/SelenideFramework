package com.constants;

import org.aeonbits.owner.ConfigFactory;
import com.utils.DateTimeUtils;
import com.utils.FrameworkConfig;

public class FrameworkConstants {
    private static final long WAIT_TIME = 20;
    public static final String BOLD_START = "<b>";
    public static final String BOLD_END = "</b>";
    /* TEAM_MEMBERS*/
    public static final String MANAGER = "Chinmay";
    public static final String MEMBER1 = "Bhargey";
    public static final String MEMBER2 = "Ankita";
    public static final String MEMBER3 = "Neelesh";
    public static final String ICON_AUTHOR = "&#9998;";
    public static final String ICON_CATEGORY = "&#128193;";
    public static final String ICON_MANAGER = "<i class='fa fa-users' style='font-size:18px'></i>";
    public static final String ICON_TEAM = "<i class='fa fa-user-circle' style='font-size:18px'></i>";
    public static final String ICON_SMILEY_PASS = "<i class='fa fa-smile-o' style='font-size:24px'></i>";
    public static final String ICON_SMILEY_SKIP = "<i class='fas fa-frown-open'></i>";
    public static final String ICON_SMILEY_FAIL = "<i class='fa fa-frown-o' style='font-size:24px'></i>";
    public static final String ICON_OS_WINDOWS = "<i class='fa fa-windows' ></i>";
    public static final String ICON_OS_MAC = "<i class='fa fa-apple' ></i>";
    /* ICONS - START */
    public static final String ASSERTION_FOR = "Assertion for ";
    public static final String LOGS = "logs";
    public static final String ICON_OS_LINUX = "<i class='fa fa-linux' ></i>";
    public static final String ICON_Navigate_Right = "<i class='fa fa-arrow-circle-right' ></i>";
    public static final String ICON_LAPTOP = "<i class='fa fa-laptop' style='font-size:18px'></i>";
    public static final String ICON_BUG = "<i class='fa fa-bug' style='font-size:18px'></i>";
    //public static final String ICON_SOCIAL_GITHUB_PAGE_URL = "https://github.com/chinmay251094";
    public static final String ICON_SOCIAL_LINKEDIN_URL = "https://in.linkedin.com/company/google";
    //	public static final String ICON_BROWSER_OPERA = "<i class=\"fa fa-opera\" aria-hidden=\"true\"></i>";
//	public static final String ICON_BROWSER_EDGE = "<i class=\"fa fa-edge\" aria-hidden=\"true\"></i>";
    public static final String ICON_BROWSER_CHROME = "<i class=\"fa fa-chrome\" aria-hidden=\"true\"></i>";
    public static final String ICON_BROWSER_FIREFOX = "<i class=\"fa fa-firefox\" aria-hidden=\"true\"></i>";
    //	public static final String ICON_BROWSER_SAFARI = "<i class=\"fa fa-safari\" aria-hidden=\"true\"></i>";
    public static final String ICON_ORGANIZATION_SITE = "https://www.google.com/";
    public static final String ICON_SOCIAL_LINKEDIN = "<a href='" + ICON_SOCIAL_LINKEDIN_URL
            + "'><i class='fa fa-linkedin-square' style='font-size:24px'></i></a>";
    public static final String ICON_CAMERA = "<i class=\"fa fa-camera\" aria-hidden=\"true\"></i>";
    public static final String ICON_BROWSER_EDGE = "edge";
    /* style="text-align:center;" */
    public static final String ICON_BROWSER_PREFIX = "<i class=\"fa fa-";
    public static final String ICON_BROWSER_SUFFIX = "\" aria-hidden=\"true\"></i>";
    public static final int WAIT_PAGE_LOADED = 30;
    public static final int WAIT_SLEEP_STEP = 0;
    //public static final String ICON_SOCIAL_GITHUB = "<a href='" + ICON_SOCIAL_GITHUB_URL
    //        + "'><i class='fa fa-github-square' style='font-size:24px'></i></a>";
    private static final String RESOURCEPATH = System.getProperty("user.dir");
    private static final String EXCELSHEETPATH = RESOURCEPATH + "/src/test/resources/TestingFile.xlsx";
    private static final String EXTENTREPORTFOLDERPATH = System.getProperty("user.dir") + "/extent-test-output/";
    private static final String CONFIGFILEPATH = RESOURCEPATH + "FrameworkConfig.properties";
    private static final String EXPORT_VIDEO_PATH = "/local-execution-videos";
    private static final String EXPORT_SCREENSHOT_PATH = RESOURCEPATH + "/screenshots";
    private static FrameworkConfig frameworkConfig = ConfigFactory.create(FrameworkConfig.class);
    public static final String ACTIVE_PAGE_LOADED = frameworkConfig.active_page_loaded();
    private static String extentReportFilePath = "";

    public static String getExcelSheetPath() {
        return EXCELSHEETPATH;
    }

    public static String getExtentReportFilePath() throws Exception {
        if (extentReportFilePath.isEmpty()) {
            extentReportFilePath = createReportPath();
        }
        return extentReportFilePath;
    }

    private static String createReportPath() throws Exception {
        if (frameworkConfig.overridereports().equalsIgnoreCase("no")) {
            return EXTENTREPORTFOLDERPATH + DateTimeUtils.getDateTime() + " Test Report.html";
        } else {
            return EXTENTREPORTFOLDERPATH + "/index.html";
        }
    }

    public static String getVideoRecordingFilePathFilePath() {
        return EXPORT_VIDEO_PATH.replace(":", "-");
    }

    public static String getScreenshotFilePathFilePath() {
        return EXPORT_SCREENSHOT_PATH;
    }

    public static String getPropertyFilePath() {
        return CONFIGFILEPATH;
    }


    public static long getWaitTime() {
        return WAIT_TIME;
    }
}
