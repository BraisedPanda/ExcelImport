package com.braisedpanda.dooraccess.base.util;

import com.google.common.collect.Lists;

import java.util.List;

/**
 * @Description 集合工具类
 * @author JiC
 * @date 2019-07-31 14:48
 */
public class ArrayUtils {

    /**
     * @Deprecated 将一组数据平均分成n组
     * @param source 要分组的数据源
     * @param number 每组数据值
     * @return: java.util.List<java.util.List<T>>
     * @Author: JiC
     * @date: 2019/7/31
     */
    public static <T> List<List<T>> averageAssign(List<T> source, int number) {

        List<List<T>> result = Lists.newArrayList();
        List<T> value;
        int group = source.size() / number;

        for (int i = 0; i < group; i++) {
            value = source.subList(i * number, (i + 1) * number);
            result.add(value);
        }

        int remainder = source.size() % number;
        if (remainder > 0) {
            value = source.subList(group * number, (group * number) + remainder);
            result.add(value);
        }

        return result;
    }

    /**
     * @Deprecated 数组值 是否等于length
     * @param indexs
     * @param length
     * @return: boolean
     * @Author: JiC
     * @date: 2019/7/25
     */
    public static boolean equals(Integer[] indexs, long length) {
        return sumArray(indexs) >= length;
    }

    /**
     * @Deprecated 统计数组数
     * @param indexs 数组
     * @return: int
     * @Author: JiC
     * @date: 2019/8/1
     */
    public static int sumArray(Integer[] indexs) {
        int sum = 0;
        for (Integer index : indexs) {
            sum += index == null ? 0 : index;
        }
        return sum;
    }

    /**
     * @Deprecated 判断数组是否为空
     * @param array
     * @return: boolean
     * @Author: JiC
     * @date: 2019/10/28
     */
    public static boolean isEmpty(int[] array) {
        return array == null || array.length <= 0;
    }

}
