package com.braisedpanda.dooraccess.base.util;

import java.math.BigDecimal;

/**
 * @Description
 * @author JiC
 * @date 2020-01-09 11:07
 */
public final class ComputeUtils {

    public static double divide(String val) {
        return ComputeUtils.divide(val, "1000");
    }

    public static double divide(String val, String divisor) {
        return ComputeUtils.divide(val, divisor, 3);
    }

    /**
     * @Deprecated
     * @Author: JiC
     * @date: 2020/1/9
     */
    public static double divide(String val, String divisor, int scale) {
        return new BigDecimal(val).divide(new BigDecimal(divisor)).setScale(scale).doubleValue();
    }

    private ComputeUtils() {}
}
