package com.mvc.util;

import org.apache.commons.lang3.StringUtils;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;

public final class CalendarUtil {
    private static final TimeZone TIME_ZONE_GMT_8 = TimeZone.getTimeZone("GMT+08:00");
    private static final long DEFAULT_MILLIS = 0L;
    private static final String DEFAULT_FORMAT_RESULT = "";
    public static final List<Integer> WEEKDAY_DAY_INDEX_IN_WEEK_LIST = Arrays.asList(1, 2, 3, 4, 5);
    public static final List<Integer> WEEKEND_DAY_INDEX_IN_WEEK_LIST = Arrays.asList(6, 7);

    public static final int MIN_YEAR = 1970;
    public static final int MAX_YEAR = 2050;
    public static final int MIN_MONTH = 1;
    public static final int MAX_MONTH = 12;
    public static final int MIN_DAY_OF_MONTH = 1;
    public static final int MAX_DAY_OF_MONTH = 31;
    public static final int MIN_DAY_OF_WEEK = 1;
    public static final int MAX_DAY_OF_WEEK = 7;
    public static final int MIN_HOUR = 0;
    public static final int MAX_HOUR = 12;
    public static final int MIN_MINUTE = 0;
    public static final int MAX_MINUTE = 59;
    public static final int MIN_SECOND = 0;
    public static final int MAX_SECOND = 59;
    public static final int MIN_MILLI_SECOND = 0;
    public static final int MAX_MILLI_SECOND = 999;
    public static final int MONTH_OF_LEAP_YEAR = 2;


    public static final long ONE_SECOND = 1000L;
    public static final long ONE_MINUTE = ONE_SECOND * 60;
    public static final long ONE_HOUR = ONE_MINUTE * 60;
    public static final long ONE_DAY = ONE_HOUR * 24;
    public static final long ONE_WEEK = ONE_DAY * 7;

    public static final String Y = "yyyy";
    public static final String Y_M = "yyyy-dd";
    public static final String M_D = "MM-dd";
    public static final String Y_M_D = "yyyy-MM-dd";
    public static final String H_M = "HH:mm";
    public static final String H_M_S = "HH:mm:ss";
    public static final String Y_M_D_H_M = "yyyy-MM-dd HH:mm";
    public static final String Y_M_D_H_M_S = "yyyy-MM-dd HH:mm:ss";
    public static final String Y_M_D_H_M_S_000 = "yyyy-MM-dd HH:mm:ss SSS";
    private static final boolean IGNORE_DST = true;

    private static TimeZone defaultTimeZone = TIME_ZONE_GMT_8;


    public static TimeZone getDefaultTimeZone() {
        return defaultTimeZone;
    }

    public static void setDefaultTimeZone(TimeZone timeZone) {
        if (timeZone == null) {
            return;
        }
        defaultTimeZone = timeZone;
    }


    public static long thisMonthStart() {
        return monthStart(millis());
    }

    public static long thisMonthEnd() {
        return monthEnd(millis());
    }


    public static long monthStart(long millis) {
        Calendar calendar = calendar(millis);
        calendar.set(Calendar.DAY_OF_MONTH, 1);
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);
        return calendar.getTimeInMillis();
    }

    public static long monthEnd(long millis) {
        Calendar calendar = calendar(millis);
        return millis(calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH) + 2) - 1;
    }

    public static long todayStart() {
        return dayStart(millis());
    }

    public static long todayEnd() {
        return dayEnd(millis());
    }

    public static long dayStart(long millis) {
        Calendar calendar = calendar(millis);
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);
        return calendar.getTimeInMillis();
    }

    public static long dayEnd(long millis) {
        return dayStart(millis) + ONE_DAY - 1;
    }


    public static long thisWeekStart() {
        return weekStart(millis());
    }

    public static long thisWeekEnd() {
        return weekEnd(millis());
    }

    public static long weekStart(long millis) {
        Calendar calendar = calendar(millis);
        calendar.setFirstDayOfWeek(Calendar.MONDAY);
        calendar.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);
        return calendar.getTimeInMillis();
    }

    public static long weekEnd(long millis) {
        return weekStart(millis) + ONE_WEEK - 1;
    }

    public static Calendar calendar() {
        return Calendar.getInstance(defaultTimeZone);
    }

    public static Calendar calendar(long millis) {
        Calendar calendar = Calendar.getInstance(defaultTimeZone);
        calendar.setFirstDayOfWeek(Calendar.MONDAY);
        calendar.setTimeInMillis(millis);
        return calendar;
    }

    public static long millis() {
        return calendar().getTimeInMillis();
    }

    public static long millis(Calendar calendar) {
        try {
            return calendar.getTimeInMillis();
        } catch (Throwable e) {
            return DEFAULT_MILLIS;
        }
    }

    public static long millis(String value) {
        return millis(value, Y_M_D_H_M_S_000);
    }

    public static long millis(String value, String pattern) {
        if (StringUtils.isEmpty(value) || StringUtils.isEmpty(pattern)) {
            return DEFAULT_MILLIS;
        }
        try {
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
            simpleDateFormat.setTimeZone(defaultTimeZone);
            Date date = simpleDateFormat.parse(value);
            if (date == null) {
                return 0;
            }
            return date.getTime();
        } catch (Throwable e) {
            return DEFAULT_MILLIS;
        }
    }

    public static long dayMillis(String value, String pattern) {
        long millis = millis(value, pattern);
        if (millis == DEFAULT_MILLIS) {
            return DEFAULT_MILLIS;
        }
        Calendar calendar = calendar(millis);
        calendar.set(Calendar.YEAR, MIN_YEAR);
        setMonth(calendar, MIN_MONTH);
        calendar.set(Calendar.DAY_OF_MONTH, MIN_DAY_OF_MONTH);
        return calendar.getTimeInMillis();
    }

    public static String format(Calendar calendar) {
        return format(calendar, Y_M_D_H_M_S_000);
    }

    public static String format(Calendar calendar, String format) {
        if (calendar == null) {
            return DEFAULT_FORMAT_RESULT;
        }
        return format(calendar.getTime(), format);
    }

    public static String format(long millis) {
        return format(millis, Y_M_D_H_M_S_000);
    }

    public static String format(long millis, String format) {
        return format(calendar(millis), format);
    }

    public static long add(long... millis) {
        long value = defaultTimeZone.getRawOffset();
        for (long milli : millis) {
            value += milli;
        }
        return value;
    }

    public static String format(Date date) {
        return format(date, Y_M_D_H_M_S_000);
    }

    public static String format(Date date, String format) {
        if (date == null || format == null) {
            return "";
        }
        try {
            DateFormat dateFormat = new SimpleDateFormat(format);
            dateFormat.setTimeZone(defaultTimeZone);
            return dateFormat.format(date);
        } catch (Throwable e) {
            return "";
        }
    }


    public static boolean sameYear(long... millisArray) {
        if (millisArray == null || millisArray.length < 1) {
            return false;
        }
        int year = millisArray.length == 1 ? calendar().get(Calendar.YEAR) : calendar(millisArray[0]).get(Calendar.YEAR);
        for (long millis : millisArray) {
            if (year == calendar(millis).get(Calendar.YEAR)) {
                continue;
            }
            return false;
        }
        return true;
    }

    public static boolean sameMonth(long... millisArray) {
        if (millisArray == null || millisArray.length < 1) {
            return false;
        }
        Calendar calendar = millisArray.length == 1 ? calendar() : calendar(millisArray[0]);
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        for (long millis : millisArray) {
            Calendar current = calendar(millis);
            if (year == current.get(Calendar.YEAR)
                    && month == current.get(Calendar.MONTH)) {
                continue;
            }
            return false;
        }
        return true;
    }

    public static boolean sameWeek(long... millisArray) {
        if (millisArray == null || millisArray.length < 1) {
            return false;
        }
        Calendar calendar = millisArray.length == 1 ? calendar() : calendar(millisArray[0]);
        int year = calendar.get(Calendar.YEAR);
        int week = calendar.get(Calendar.WEEK_OF_YEAR);
        for (long millis : millisArray) {
            Calendar current = calendar(millis);
            if (year == current.get(Calendar.YEAR)
                    && week == current.get(Calendar.WEEK_OF_YEAR)) {
                continue;
            }
            return false;
        }
        return true;
    }


    public static boolean sameDay(long... millisArray) {
        if (millisArray == null || millisArray.length < 1) {
            return false;
        }
        Calendar calendar = millisArray.length == 1 ? calendar() : calendar(millisArray[0]);
        int year = calendar.get(Calendar.YEAR);
        int dayOfYear = calendar.get(Calendar.DAY_OF_YEAR);
        for (long millis : millisArray) {
            Calendar current = calendar(millis);
            if (year == current.get(Calendar.YEAR)
                    && dayOfYear == current.get(Calendar.DAY_OF_YEAR)) {
                continue;
            }
            return false;
        }
        return true;
    }

    public static boolean sameHour(long... millisArray) {
        if (millisArray == null || millisArray.length < 1) {
            return false;
        }
        Calendar calendar = millisArray.length == 1 ? calendar() : calendar(millisArray[0]);
        int year = calendar.get(Calendar.YEAR);
        int dayOfYear = calendar.get(Calendar.DAY_OF_YEAR);
        int hour = calendar.get(Calendar.HOUR_OF_DAY);
        for (long millis : millisArray) {
            Calendar current = calendar(millis);
            if (year == current.get(Calendar.YEAR)
                    && dayOfYear == current.get(Calendar.DAY_OF_YEAR)
                    && hour == current.get(Calendar.HOUR_OF_DAY)) {
                continue;
            }
            return false;
        }
        return true;
    }

    public static boolean sameMinute(long... millisArray) {
        if (millisArray == null || millisArray.length < 1) {
            return false;
        }
        Calendar calendar = millisArray.length == 1 ? calendar() : calendar(millisArray[0]);
        int year = calendar.get(Calendar.YEAR);
        int dayOfYear = calendar.get(Calendar.DAY_OF_YEAR);
        int hour = calendar.get(Calendar.HOUR_OF_DAY);
        int minute = calendar.get(Calendar.MINUTE);
        for (long millis : millisArray) {
            Calendar current = calendar(millis);
            if (year == current.get(Calendar.YEAR)
                    && dayOfYear == current.get(Calendar.DAY_OF_YEAR)
                    && hour == current.get(Calendar.HOUR_OF_DAY)
                    && minute == current.get(Calendar.MINUTE)) {
                continue;
            }
            return false;
        }
        return true;
    }

    public static void setMonth(Calendar calendar, int month) {
        if (calendar != null) {
            calendar.set(Calendar.MONTH, month - 1);
        }
    }

    public static int getMonth(Calendar calendar) {
        return calendar == null ? MIN_MONTH : calendar.get(Calendar.MONTH) + 1;
    }

    public static long millis(int year) {
        return millis(year, MIN_MONTH);
    }

    public static long millis(int year, int month) {
        return millis(year, month, MIN_DAY_OF_MONTH);
    }

    public static long millis(int year, int month, int dayOfMonth) {
        return millis(year, month, dayOfMonth, MIN_HOUR);
    }

    public static long millis(int year, int month, int dayOfMonth, int hour) {
        return millis(year, month, dayOfMonth, hour, MIN_MINUTE);
    }

    public static long millis(int year, int month, int dayOfMonth, int hour, int minute) {
        return millis(year, month, dayOfMonth, hour, minute, MIN_SECOND);
    }

    public static long millis(int year, int month, int dayOfMonth, int hour, int minute, int second) {
        Calendar calendar = calendar(year, month, dayOfMonth, hour, minute, second);
        return calendar.getTimeInMillis();
    }

    public static Calendar calendar(int year) {
        return calendar(year, MIN_MONTH);
    }

    public static Calendar calendar(int year, int month) {
        return calendar(year, month, MIN_DAY_OF_MONTH);
    }

    public static Calendar calendar(int year, int month, int dayOfMonth) {
        return calendar(year, month, dayOfMonth, MIN_HOUR);
    }

    public static Calendar calendar(int year, int month, int dayOfMonth, int hour) {
        return calendar(year, month, dayOfMonth, hour, MIN_MINUTE);
    }

    public static Calendar calendar(int year, int month, int dayOfMonth, int hour, int minute) {
        return calendar(year, month, dayOfMonth, hour, minute, MIN_SECOND);
    }

    public static Calendar calendar(int year, int month, int dayOfMonth, int hour, int minute, int second) {
        Calendar calendar = calendar();
        calendar.set(Calendar.YEAR, year);
        setMonth(calendar, month);
        calendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
        calendar.set(Calendar.HOUR_OF_DAY, hour);
        calendar.set(Calendar.MINUTE, minute);
        calendar.set(Calendar.SECOND, second);
        calendar.set(Calendar.MILLISECOND, MIN_MILLI_SECOND);
        return calendar;
    }

    public static int age(long birthday) {
        Calendar now = CalendarUtil.calendar();
        Calendar birthdayDate = CalendarUtil.calendar(birthday);
        int diffYear = now.get(Calendar.YEAR) - birthdayDate.get(Calendar.YEAR);
        int diffMonth = now.get(Calendar.MONTH) - birthdayDate.get(Calendar.MONTH);
        int diffDay = now.get(Calendar.DATE) - birthdayDate.get(Calendar.DATE);
        return diffYear + (diffMonth > 0 ? 1 : diffDay >= 0 ? 1 : 0);
    }

    public static boolean isBirthday(long birthday) {
        return sameDay(birthday);
    }

    public static int dayIndexInWeek(long millis) {
        int dayOfWeek = calendar(millis).get(Calendar.DAY_OF_WEEK) - MIN_DAY_OF_WEEK;
        return dayOfWeek == 0 ? MAX_DAY_OF_WEEK : dayOfWeek;
    }

    public static long millisInDay(long millis) {
        return millis - dayStart(millis);
    }

    public static boolean isWeekday(long millis) {
        return !isWeekend(millis);
    }

    public static boolean isWeekend(long millis) {
        return WEEKEND_DAY_INDEX_IN_WEEK_LIST.contains(dayIndexInWeek(millis));
    }

    public static int diffWeek(long millis1, long millis2) {
        return (int) (Math.abs(millis1 - millis2) / ONE_WEEK);
    }

    public static int diffDay(long millis1, long millis2) {
        return (int) (Math.abs(millis1 - millis2) / ONE_DAY);
    }

    public static long moveToThisWeek(long millis) {
        long thisWeekStart = thisWeekStart();
        long millisWeekStart = weekStart(millis);
        int diffWeek = diffWeek(thisWeekStart, millisWeekStart);
        return millis + (thisWeekStart > millisWeekStart ? 1 : -1) * diffWeek * ONE_WEEK;
    }

    public static boolean isDate(String value, String format) {
        try {
            return new SimpleDateFormat(format).parse(value) != null;
        } catch (Throwable e) {
            return false;
        }
    }

    public static boolean isSafeDayIndexInWeek(int dayIndexInWeek) {
        return MIN_DAY_OF_WEEK <= dayIndexInWeek && MAX_DAY_OF_WEEK >= dayIndexInWeek;
    }
}
