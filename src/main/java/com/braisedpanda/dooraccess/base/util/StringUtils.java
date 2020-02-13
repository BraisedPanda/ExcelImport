package com.braisedpanda.dooraccess.base.util;

/**
 * 字符串工具类 
 * @author JiC
 * @date 2019-07-09 19:44
 */
public final class StringUtils {

    /**
     * @Deprecated 截取字符串后面0
     * @param str
     * @return: java.lang.String
     * @Author: JiC
     * @date: 2019/9/21
     */
    public static String removeTailZero(String str) {
        if (str.endsWith("0")) {
            return removeTailZero(str.substring(0, str.length() -1));
        }
        return str;
    }

    private StringUtils() {throw new IllegalAccessError();}
}
