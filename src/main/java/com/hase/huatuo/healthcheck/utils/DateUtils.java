package com.hase.huatuo.healthcheck.utils;

import java.time.Instant;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;

public class DateUtils {
    private final static String DEFAULT_DATE_FORMATE = "yyyy-MM-dd";
    public static String getCurrentDate(){
        return Instant.now().atZone(ZoneId.of("Asia/Shanghai")).toLocalDate()
                .format(DateTimeFormatter.ofPattern(DEFAULT_DATE_FORMATE));
    }
}
