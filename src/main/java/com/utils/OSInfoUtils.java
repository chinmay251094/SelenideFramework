package com.utils;

public class OSInfoUtils {
    /**
     * Private constructor to avoid external instantiation
     */
    //private -> We do not want anyone to create the object of this class
    private OSInfoUtils() {
    }

    public static String getOSInfo() {
        return System.getProperty("os.name").replace(" ", "_");
    }
}
