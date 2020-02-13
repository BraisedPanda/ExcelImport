package com.braisedpanda.dooraccess.base.util;

import com.braisedpanda.dooraccess.base.exception.NotFoundException;
import com.braisedpanda.dooraccess.base.exception.ParamConvertException;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.*;
import java.time.format.DateTimeFormatter;
import java.time.temporal.Temporal;
import java.util.Date;
import java.util.Locale;

/**
 * 日期公共类
 *
 * @author JiC
 * @date 2019-07-02 11:29
 */
public final class DateUtil {

    public static final String PATTERN_STANDARD = "yyyy-MM-dd HH:mm:ss";

    public static final String PATTERN_STANDARD1 = "yyyyMMddHHmmss";

    public static final String PATTERN_DATE = "yyyy-MM-dd";

    public static final String PATTERN_DATE1 = "yyyyMMdd";

    public static final String PATTERN_DATE2 = "yyMMddhhmmss";

    public static final String PATTERN_TIME = "HH:mm:ss";

    public static final String PATTERN_DATE_MOUNTH = "yyyy-MM";

    public static final String PATTERN_DATE_TZ = "yyyyMMdd'T'HHmmss'Z'";

    public static final DateFormat DEFAULT_DATE_FORMAT = DateFormat.getDateTimeInstance(DateFormat.DEFAULT, DateFormat.DEFAULT, Locale.CHINA);

    /**
     * @param date    日期
     * @param pattern 格式
     * @Description: 日期转字符串
     * @author JiC
     * @date 2019/3/1
     */
    public static String dateToString(Date date, String pattern) {
        SimpleDateFormat format = new SimpleDateFormat(pattern);
        return format.format(date);
    }

    /**
     * @Deprecated 格式化Date 日期
     * @param date
     * @param pattern
     * @return: java.util.Date
     * @Author: JiC
     * @date: 2019/9/19
     */
    public static Date dateFormat(Date date, String pattern) {
        String format1 = dateToString(date, pattern);
        return stringToDate(format1, pattern);
    }

    /**
     * @Description: 当前时间转字符串
     * @author JiC
     * @date 2019/7/2
     */
    public static String currentDateTime() {
        return dateToString(LocalDateTime.now(), PATTERN_STANDARD);
    }

    /**
    　* @description: 当前时间转字符串
    　* @param formatter
    　* @return String
    　* @author gaogq
    　* @date 2019/7/26 10:39
    　*/
    public static String currentDateTime(String formatter) {
        return dateToString(LocalDateTime.now(), formatter);
    }

    /**
     * @Description: 日期转字符串
     * temporal: 实现类
     * LocalDate： 本地日期 -> 2019-04-10
     * LocalTime： 本地时间 -> 23:14:42.994
     * LocalDateTime： 本地日期时间 -> 2019-04-10T23:14:42.994
     * @author JiC
     * @date 2019/7/2
     */
    public static String dateToString(Temporal temporal) {
        String formatter;
        if (temporal instanceof LocalDateTime) {
            formatter = PATTERN_STANDARD;
        } else if (temporal instanceof LocalDate) {
            formatter = PATTERN_DATE;
        } else if (temporal instanceof LocalTime) {
            formatter = PATTERN_TIME;
        } else {
            throw new NotFoundException("时间类型错误");
        }
        return dateToString(temporal, formatter);
    }

    /**
     * @Description: 日期转字符串
     * @author JiC
     * @date 2019/7/2
     */
    public static String dateToString(Temporal temporal, String formatter) {
        return dateToString(temporal, DateTimeFormatter.ofPattern(formatter));
    }

    /**
     * @Description: 日期转字符串
     * @author JiC
     * @date 2019/7/2
     */
    public static String dateToString(Temporal temporal, DateTimeFormatter formatter) {
        return formatter.format(temporal);
    }

    /**
     * @Deprecated 从天数字段减去指定数量
     * @param days 天数
     * @return: java.lang.String
     * @Author: JiC
     * @date: 2019/9/4
     */
    public static LocalDateTime minusDays(long days) {
        LocalDateTime localDateTime = LocalDateTime.now().minusDays(days);
        return localDateTime;
    }

    /**
     * @Description: 字符串转日期
     * @Param:
     * @return:
     * @Author: JiC
     * @date: 2019/7/10
     */
    public static Date stringToDate(String time, String formatter) {
        try {
            SimpleDateFormat sdf = new SimpleDateFormat(formatter);
            return sdf.parse(time);
        } catch (ParseException e) {
            throw new ParamConvertException(String.format("字符串转日期异常【time:%s, formatter:%s】", time, formatter));
        }
    }

    /**
     * @Description: 获取当前日期前N天的日期字符串(包括当前日期共N天)
     * @Author: DoubleC
     * @date: 2019/09/15
     */
    public static String getPastDateString(Integer days){
        LocalDateTime localDateTime = minusDays(days-1);
        ZoneId zoneId = ZoneId.systemDefault();
        ZonedDateTime zonedDateTime = localDateTime.atZone(zoneId);
        Date pastDate = Date.from(zonedDateTime.toInstant());
        String pastTime = dateToString(pastDate, DateUtil.PATTERN_DATE);
        return pastTime;
    }

    /**
     * @Description:获取当前时间
     * @Author: sds
     * @Return:
     * @Param:  * @param null
     * @Date: 2019/11/5
     */
    public static String getTimeForm(String pattern){
        Date date = new Date();
        String times = DateUtil.dateToString(date,"yyMMdd");
        return times;

    }

    /**
     * @Deprecated 获取yyyyMMddHHmmss格式字符串日期
     * @param: []
     * @return: java.lang.String
     * @Author: WangKang
     * @date: 2019/11/20
     */
    public static String getStringDate(Date date){
        return dateToString(date, PATTERN_STANDARD1);
    }

    /** 
     * 字符串转换成日期 
     * @param string 
     * @return date 
     */
    public static Date stringToDate(String string) {
        return stringToDate(string,PATTERN_STANDARD);
    }


    private DateUtil() {
        throw new IllegalAccessError();
    }



}
