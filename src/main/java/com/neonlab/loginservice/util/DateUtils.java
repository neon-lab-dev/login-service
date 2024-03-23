package com.neonlab.loginservice.util;

import java.util.Date;

public class DateUtils {

    public static Date getDateAfterNMinutes(int n){
        Date currentTime = new Date();
        long timeAfter5MinutesMillis = currentTime.getTime() + (5 * 60 * 1000);
        return new Date(timeAfter5MinutesMillis);
    }
}
