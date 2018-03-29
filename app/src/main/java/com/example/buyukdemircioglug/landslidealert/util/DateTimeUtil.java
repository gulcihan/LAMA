/*
 * Copyright 2003-2018 Monitise Group Limited. All Rights Reserved.
 *
 * Save to the extent permitted by law, you may not use, copy, modify,
 * distribute or create derivative works of this material or any part
 * of it without the prior written consent of Monitise Group Limited.
 * Any reproduction of this material must contain this notice.
 */

package com.example.buyukdemircioglug.landslidealert.util;

import android.text.TextUtils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public final class DateTimeUtil {

    public static final String DATE_FORMAT_ISO_8601 = "yyyy-MM-dd";
    public static final String DATE_PATTERN_SHORT_SPACE_M = "dd MMM yyyy";

    /**
     * Private constructor for util class.
     */
    private DateTimeUtil() {
    }

    /**
     * Converts given string to date in given format.
     *
     * @param date       to be converted to date
     * @param dateFormat date format to be used
     * @return date correspondent of string in given format or null
     */

    public static Date stringToDateWithFormat(String date, String dateFormat) {
        Date formattedDate = null;

        if (!TextUtils.isEmpty(date) && !TextUtils.isEmpty(dateFormat)) {
            final SimpleDateFormat format = new SimpleDateFormat(dateFormat);

            try {
                formattedDate = format.parse(date);
            } catch (ParseException e) {
                formattedDate = null;
            }

        }

        return formattedDate;
    }



    /**
     * Converts given date to string in given format.
     *
     * @param date       to be converted to string
     * @param dateFormat date format to be used
     * @return string correspondent of date in given format or null
     */
    public static String dateToString(Date date, String dateFormat) {
        if (date != null && !TextUtils.isEmpty(dateFormat)) {
            return new SimpleDateFormat(dateFormat).format(date);

        } else {
            return null;
        }

    }

    /**
     * Converts given date to string in default format.
     *
     * @param date       to be converted to string
     * @return string correspondent of date in default format or null
     */
    public static String dateToString(Date date) {
        return dateToString(date, DATE_FORMAT_ISO_8601);

    }

}
