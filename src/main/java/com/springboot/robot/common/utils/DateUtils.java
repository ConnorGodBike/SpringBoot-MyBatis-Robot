package com.springboot.robot.common.utils;

import com.springboot.robot.common.exception.InvalidParameterException;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * @author Connor
 * 日期处理工具类
 */
public class DateUtils {

    /**
     * 时间格式(yyyy-MM-dd)
     */
    public final static String DATE_PATTERN = "yyyy-MM-dd";
    /**
     * 时间格式(yyyy-MM-dd HH:mm:ss)
     */
    public final static String DATE_TIME_PATTERN = "yyyy-MM-dd HH:mm:ss";

    public static String format(Date date) {
        return format(date, DATE_PATTERN);
    }

    public static String format(Date date, String pattern) {
        if (date != null) {
            SimpleDateFormat df = new SimpleDateFormat(pattern);
            return df.format(date);
        }
        return null;
    }

    /**
     * 得到现在时间
     *
     * @return 字符串 yyyyMMdd HHmmss
     */
    public static String getStringTodayForFileName() {
        Date currentTime = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMdd-HHmmss");
        String dateString = formatter.format(currentTime);
        return dateString + "-" + new Random().nextInt(10000);
    }

    /**
     * 得到今天开始的时间
     * @return
     */
    public static Date getTodayBegin() {
        DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        String date = format.format(new Date());
        format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
        date += " 00:00:00.000";
        try {
            return format.parse(date);
        } catch (ParseException e) {
            return new Date();
        }
    }

    /**
     * 获取今天结束的字符串
     * @return
     */
    public static Date getTodayEnd() {
        DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        String date = format.format(new Date());
        format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
        date += " 23:59:59.000";
        try {
            return format.parse(date);
        } catch (ParseException e) {
            return new Date();
        }
    }

    /**
     * 获取今天yyyy-MM-dd格式的字符串
     * @return
     */
    public static String getTodayStr() {
        DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        String date = format.format(new Date());
        return format.format(date);
    }

    /**
     * 获取昨天的时间
     * @return
     */
    public static final Date getYesterday() {
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.DAY_OF_MONTH, calendar.get(Calendar.DAY_OF_MONTH) - 1);
        Date date = calendar.getTime();
        DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        // 这样可以省去小时后面的设置
        String str = format.format(date);
        try {
            return format.parse(str);
        } catch (ParseException e) {
            return null;
        }
    }

    /**
     * 获取本月的第一天
     * @return
     */
    public static final String getMonthBegin() {
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.DAY_OF_MONTH, 1);
        Date date = calendar.getTime();
        DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        // 这样可以省去小时后面的设置
        String str = format.format(date);
        return str + " 00:00:00";
    }

    /**
     * 获取明天的时间
     * @param time
     * @return
     */
    public static final Date getTomorrow(Date time) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(time);
        calendar.set(Calendar.DAY_OF_MONTH, calendar.get(Calendar.DAY_OF_MONTH) + 1);
        Date date = calendar.getTime();
        DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        String str = format.format(date);
        try {
            return format.parse(str);
        } catch (ParseException e) {
            return null;
        }
    }

    /**
     * 根据偏移量获取之前的时间
     * @param offset
     * @return
     */
    public static String getTime(int offset) {
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.DAY_OF_MONTH, calendar.get(Calendar.DAY_OF_MONTH) - offset);
        DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        return format.format(calendar.getTime());
    }

    /**
     * 查询两个时间段内的所有时间，从小到大排序
     * @param timeFrom
     * @param timeTo
     * @return
     */
    public static List<String> getTimes(Date timeFrom, Date timeTo) {
        if (timeFrom.getTime() > timeTo.getTime()) {
            throw new InvalidParameterException("timeFrom 不能大于 timeTo");
        }
        List<String> result = new ArrayList<>();
        DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        while (timeFrom.getTime() < timeTo.getTime()) {
            result.add(format.format(timeFrom));
            timeFrom = getTomorrow(timeFrom);
        }
        result.add(format.format(timeTo));
        return result;
    }

    /**
     * 获取今天的时间
     * @return
     */
    public static final Date getToday() {
        Date date = new Date();
        DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        String str = format.format(date);
        try {
            return format.parse(str);
        } catch (ParseException e) {
            throw new InvalidParameterException("打卡服务繁忙");
        }
    }

    /**
     * 由出生日期获得年龄
     * @param birthDay
     * @return
     */
    public static int getAge(Date birthDay) {
        Calendar cal = Calendar.getInstance();
        if (cal.before(birthDay)) {
            throw new InvalidParameterException("请正确填写生日!");
        }
        int yearNow = cal.get(Calendar.YEAR);
        int monthNow = cal.get(Calendar.MONTH);
        int dayOfMonthNow = cal.get(Calendar.DAY_OF_MONTH);
        cal.setTime(birthDay);
        int yearBirth = cal.get(Calendar.YEAR);
        int monthBirth = cal.get(Calendar.MONTH);
        int dayOfMonthBirth = cal.get(Calendar.DAY_OF_MONTH);
        int age = yearNow - yearBirth;
        if (monthNow <= monthBirth) {
            if (monthNow == monthBirth) {
                if (dayOfMonthNow < dayOfMonthBirth) age--;
            } else {
                age--;
            }
        }
        return age;
    }
}