package com.utils;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class DateTimeUtils {
    private DateTimeUtils() {}

    public static String getDateTime() {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("ddMMyyyy HH'h'mm'm'ss's'");
        LocalDateTime now = LocalDateTime.now();
        return dtf.format(now);
    }

}
