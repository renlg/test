package com.renlg.date;


import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

import com.renlg.exception.SysRunException;


/**
 * 日期工具类
 *
 * @author LeonYin
 *
 */
public final class DateUtil {

    /**
     * 日期格式化对象
     */
    private static DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
    /**
     * 日期时间格式化对象
     */
    private static DateFormat dateTimeFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm");

    /**
     * 时间格式化对象
     */
    private static DateFormat Time = new SimpleDateFormat("HH:mm");

    /**
     * 获取时间格式化对象 "yyyy-MM-dd"
     * @return DateFormat
     */
    public static DateFormat getDateFormat() {
        return dateFormat;
    }

    /**
     * 获取时间日期格式化对象 "yyyy-MM-dd HH:mm"
     * @return DateFormat
     */
    public static DateFormat getDateTimeFormat() {
        return dateTimeFormat;
    }

    /**
     * 获取当前时间的时间对象
     *
     * @param timeZone
     *            时区数 0，2，3...
     * @return Date
     */
    public static Date nowDate(int timeZone) {
        TimeZone.setDefault(TimeZone.getTimeZone("GMT+" + timeZone));
        return new Date();
    }

    /**
     * 获取当前时间的时间对象, 已经默认为中国时区
     * @return Date
     */
    public static Date nowDate() {
        TimeZone.setDefault(TimeZone.getTimeZone("GMT+8"));
        return new Date();
    }

    /**
     * 系统最小时间
     *
     * @return Date
     */
    public static Date minDate() {
        return dateBegin(getDate(1900, 1, 1));
    }

    /**
     * 系统最大时间
     *
     * @return Date
     */
    public static Date maxDate() {
        return dateEnd(getDate(2079, 1, 1));
    }

    /**
     * 获取指定时间的年
     *
     * @param date
     * @return int
     */
    public static int year(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        return calendar.get(Calendar.YEAR);
    }

    /**
     * 获取指定时间的月
     *
     * @param date
     * @return int
     */
    public static int month(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        return calendar.get(Calendar.MONTH) + 1;
    }

    /**
     * 获取指定时间的日
     *
     * @param date
     * @return int
     */
    public static int day(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        return calendar.get(Calendar.DATE);
    }

    /**
     * 获取一个时间对象
     *
     * @param year
     *            格式为：2004
     * @param month
     *            从1开始
     * @param date
     *            从1开始
     * @return Date
     */
    public static Date getDate(int year, int month, int date) {
        Calendar calendar = Calendar.getInstance();
        calendar.set(year, month - 1, date);
        return calendar.getTime();
    }

    /**
     * 获取一个时间对象
     *
     * @param year
     *            格式为：2004
     * @param month
     *            从1开始
     * @param date
     *            从1开始
     * @param hour
     * @param minute
     * @param second
     * @return Date
     */
    public static Date getDateTime(int year, int month, int date, int hour, int minute, int second) {
        Calendar calendar = Calendar.getInstance();
        calendar.set(year, month - 1, date, hour, minute, second);
        return calendar.getTime();
    }

    /**
     * 在一个已知时间的基础上增加指定的时间
     *
     * @param oldDate
     * @param year
     * @param month
     * @param date
     * @return Date
     */
    public static Date addDate(Date oldDate, int year, int month, int date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(oldDate);
        calendar.add(Calendar.YEAR, year);
        calendar.add(Calendar.MONTH, month);
        calendar.add(Calendar.DATE, date);
        return calendar.getTime();
    }

    /**
     * 返回两个时间相差的天数
     * @param a
     * @param b
     * @return int
     */
    public static int dateSub(Date a, Date b) throws SysRunException {
        if (a == null || b == null) {
            throw new SysRunException("参数不能为空值");
        }
        int date = (int) (a.getTime() / (24 * 60 * 60 * 1000) - b.getTime()
                / (24 * 60 * 60 * 1000));
        return date <= 0 ? 1 : date;
    }

    /**
     * 返回两个时间相差的天数,并多加一天
     * @param a 日期一
     * @param b 日期二
     * @return int
     * @throws SysRunException
     */
    public static int dateSubAddOne(Date a, Date b){
        int date = daysBetween(a, b);
        return date <= 0 ? 1 : date + 1;
    }

    /**
     * 返回两个日期相差多少天
     * @param smdate 日期一
     * @param bdate 日期二
     * @return
     */
    public static int daysBetween(Date smdate,Date bdate){
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        try {
            smdate = sdf.parse(sdf.format(smdate));
            bdate = sdf.parse(sdf.format(bdate));
        } catch (ParseException e) {
            throw new SysRunException("日期转换异常");
        }
        Calendar cal = Calendar.getInstance();
        cal.setTime(smdate);
        long time1 = cal.getTimeInMillis();
        cal.setTime(bdate);
        long time2 = cal.getTimeInMillis();
        long between_days=(time2-time1)/(1000*3600*24);
        return Integer.parseInt(String.valueOf(between_days));
    }

    /**
     * 返回两个时间相差多少分钟
     * @param a
     * @param b
     * @return int
     * @throws SysRunException
     */
    public static int subTime(Date a, Date b) throws SysRunException {
        if (a == null || b == null) {
            throw new SysRunException("参数不能为空值");
        }
        return (int) (a.getTime() / (60 * 60 * 1000) - b.getTime() / (60 * 60 * 1000));
    }

    /**
     * 一天的开始时间
     *
     * @param date
     * @return Date
     */
    public static Date dateBegin(Date date) {
        if (date == null) {
            return null;
        }
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        dateBegin(calendar);
        return calendar.getTime();
    }

    /**
     * 一天的结束时间
     *
     * @param date
     * @return Date
     */
    public static Date dateEnd(Date date) {
        if (date == null) {
            return null;
        }
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        dateEnd(calendar);
        return calendar.getTime();
    }

    /**
     * 一天的结束时间
     *
     * @param calendar
     */
    public static void dateEnd(Calendar calendar) {
        if (calendar == null) {
            return;
        }
        calendar.set(Calendar.HOUR_OF_DAY, 23);
        calendar.set(Calendar.MINUTE, 59);
        calendar.set(Calendar.SECOND, 59);
        calendar.set(Calendar.MILLISECOND, 0);
    }

    /**
     * 一天的开始时间
     *
     * @param calendar
     */
    public static void dateBegin(Calendar calendar) {
        if (calendar == null) {
            return;
        }
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);
    }

    /**
     * 一周的开始时间
     * @param date
     * @return
     */
    public static Date weekBegin(Date date){
        if(date == null){
            return null;
        }
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        int day_of_week = c.get(Calendar.DAY_OF_WEEK) - 2;
        c.add(Calendar.DATE, -day_of_week);
        return c.getTime();
    }

    /**
     * 在指定日期上推算出上周或者下周..N周的周一
     * @param n 0表示本周 1下周 -1上周
     * @return
     */
    public static Date addWeek(Date oldDate,int n){
        Calendar cal = Calendar.getInstance();
        cal.setTime(oldDate);
        cal.add(Calendar.DATE, n * 7);
        cal.set(Calendar.DAY_OF_WEEK,Calendar.MONDAY);
        return cal.getTime();
    }

    /**
     * 一周的结束时间
     * @return
     */
    public static Date weekEnd(Date date){
        if(date == null){
            return null;
        }
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        int weekday = c.get(Calendar.DAY_OF_WEEK);
        c.add(Calendar.DATE, 8 - weekday);
        return c.getTime();
    }

    /**
     * 一月的开始时间
     *
     * @param date
     * @return Date
     */
    public static Date monthBegin(Date date) {
        if (date == null) {
            return null;
        }
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        int day = calendar.getActualMinimum(Calendar.DAY_OF_MONTH);
        calendar.set(Calendar.DATE, day);
        dateBegin(calendar);
        return calendar.getTime();
    }

    /**
     * 一月的结束时间
     *
     * @param date
     * @return Date
     */
    public static Date monthEnd(Date date) {
        if (date == null) {
            return null;
        }
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        int day = calendar.getActualMaximum(Calendar.DAY_OF_MONTH);
        calendar.set(Calendar.DATE, day);
        dateEnd(calendar);
        return calendar.getTime();
    }

    /**
     * 一年的开始时间
     *
     * @param date
     * @return Date
     */
    public static Date yearBegin(Date date) {
        if (date == null) {
            return null;
        }
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        int month = calendar.getActualMinimum(Calendar.DAY_OF_YEAR);
        calendar.set(Calendar.DATE, month);
        dateBegin(calendar);
        return calendar.getTime();
    }

    /**
     * 一年的结束时间
     *
     * @param date
     * @return Date
     */
    public static Date yearEnd(Date date) {
        if (date == null) {
            return null;
        }
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        int day = calendar.getActualMaximum(Calendar.DAY_OF_YEAR);
        calendar.set(Calendar.DATE, day);
        dateEnd(calendar);
        return calendar.getTime();
    }

    /**
     * 从字符串转换为date 默认格式为 "yyyy-MM-dd"
     *
     * @param source
     * @return Date
     */
    public static Date parseDate(String source) {
        if (source == null || source.length() == 0) {
            return null;
        }
        try {
            return dateFormat.parse(source);
        } catch (ParseException e) {
            return null;
        }
    }

    /**
     * 从字符串转为Date 默认格式为 "HH:mm"
     * @param source
     * @return
     */
    public static Date parseTime(String source){
        if(source == null || source.length() == 0){
            return null;
        }
        try {
            return Time.parse(source);
        } catch (ParseException e) {
            return null;
        }
    }

    /**
     * 从字符串转换为date 默认格式为 "yyyy-MM-dd HH:mm"
     *
     * @param source
     * @return Date
     */
    public static Date parseDateTime(String source) {
        if (source == null || source.length() == 0) {
            return null;
        }
        try {
            return dateTimeFormat.parse(source);
        } catch (ParseException e) {
            return null;
        }
    }

    /**
     * 格式化输出 默认格式为 "yyyy-MM-dd"
     *
     * @param date
     * @return String
     */
    public static String formatDate(Date date) {
        if (date == null) {
            return "";
        }
        return dateFormat.format(date);
    }

    /**
     * 格式化输出 默认格式为 "yyyy-MM-dd HH:mm"
     *
     * @param date
     * @return String
     */
    public static String formatDateTime(Date date) {
        if (date == null) {
            return "";
        }
        return dateTimeFormat.format(date);
    }
}
