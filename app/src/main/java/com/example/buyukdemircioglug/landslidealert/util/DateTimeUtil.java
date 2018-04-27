/*
 * Copyright 2003-2018 Monitise Group Limited. All Rights Reserved.
 *
 * Save to the extent permitted by law, you may not use, copy, modify,
 * distribute or create derivative works of this material or any part
 * of it without the prior written consent of Monitise Group Limited.
 * Any reproduction of this material must contain this notice.
 */

package com.example.buyukdemircioglug.landslidealert.util;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public final class DateTimeUtil {

    public static final String DEFAULT_DATE_PATTERN = "dd/MM/yyyy";
    public static final String DEFAULT_TIME_PATTERN = "HH:mm";

    /**
     * Default constructor disabled.
     */
    private DateTimeUtil() {
    }

    /**
     * Returns current year.
     *
     * @return current year
     */
    public static int getCurrentYear() {
        return getCalendarInstance().get(Calendar.YEAR);
    }

    /**
     * Returns current year month. Add 1 since months are started from zero.
     *
     * @return current month
     */
    public static int getCurrentMonth() {
        return getCalendarInstance().get(Calendar.MONTH) + 1;
    }

    /**
     * Returns current day.
     *
     * @return current day
     */
    public static int getCurrentDay() {
        return getCalendarInstance().get(Calendar.DAY_OF_MONTH);
    }

    /**
     * Returns current hour.
     *
     * @return current hour
     */
    public static int getCurrentHour() {
        return getCalendarInstance().get(Calendar.HOUR_OF_DAY);
    }

    /**
     * Returns current minute.
     *
     * @return current minute
     */
    public static int getCurrentMinute() {
        return getCalendarInstance().get(Calendar.MINUTE);
    }

    /**
     * Returns current day i.e. 27/04/2018.
     *
     * @return today in a string format
     */
    public static String getToday() {
        // Display a date in day, month, year format
        final DateFormat formatter = new SimpleDateFormat(
                DateTimeUtil.DEFAULT_DATE_PATTERN,
                ResourceRepository.getInstance().getDefaultLocale()
        );
        return formatter.format(getCalendarInstance().getTime());
    }

    /**
     * Returns current time i.e. 20:24.
     *
     * @return time in a string format
     */
    public static String getTime() {
        // Display a time in hour and minute format
        final DateFormat formatter = new SimpleDateFormat(
                DateTimeUtil.DEFAULT_TIME_PATTERN,
                ResourceRepository.getInstance().getDefaultLocale()
        );
        return formatter.format(getCalendarInstance().getTime());
    }

    /**
     * Returns current day and time i.e. 27/04/2018 - 20:24.
     *
     * @return time in a string format
     */
    public static String getTodayAndTime() {
        return String.format("%s - %s", getToday(), getTime());
    }


    private static Calendar getCalendarInstance() {
        return Calendar.getInstance();
    }

}
