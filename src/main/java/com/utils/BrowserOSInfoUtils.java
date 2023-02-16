package com.utils;

import static com.utils.BrowserInfoUtils.getBrowserInfo;
import static com.utils.BrowserInfoUtils.getBrowserVersionInfo;

public class BrowserOSInfoUtils {
    private BrowserOSInfoUtils() {
    }

    public static String getOS_Browser_BrowserVersionInfo() {
        return OSInfoUtils.getOSInfo() + " & " + getBrowserInfo() + " - "
                + getBrowserVersionInfo();

    }
}
