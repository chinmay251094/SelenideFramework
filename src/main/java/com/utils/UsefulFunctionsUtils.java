package com.utils;

public class UsefulFunctionsUtils {
    public static String capitalize(String str) {
        return str.substring(0, 1).toUpperCase() + str.substring(1, str.length());
    }
}
