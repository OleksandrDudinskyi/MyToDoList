package com.oleksandr.mytodolist.utils;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Locale;
import java.util.TimeZone;

/**
 * Created by Oleksandr on 8/12/2015.
 */
public class Utils {
    private static final String ISO_8601_FORMAT = "yyyy-MM-dd'T'HH:mm:ss'Z'";

    public static String getISO8601StringForDate(Date date) {
        DateFormat dateFormat = new SimpleDateFormat(ISO_8601_FORMAT, Locale.getDefault());
        dateFormat.setTimeZone(TimeZone.getTimeZone("UTC"));
        return dateFormat.format(date);
    }

    /**
     * Transform ISO 8601 string to Calendar.
     */
    public static Calendar toCalendar(final String iso8601string) throws ParseException {
        Calendar calendar = GregorianCalendar.getInstance();
        Date date = new SimpleDateFormat(ISO_8601_FORMAT, Locale.getDefault()).parse(iso8601string);
        calendar.setTime(date);
        return calendar;
    }
}
