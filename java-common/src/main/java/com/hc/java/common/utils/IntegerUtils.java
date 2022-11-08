package com.hc.java.common.utils;

/**
 * Integer 数据工具类
 *
 * @author wangxiaolei
 * @since 2022/7/20 22:48
 */
public class IntegerUtils {

    public static int getIntValue(Integer integer, int defaultValue) {
        if (integer == null) {
            return defaultValue;
        } else {
            return integer.intValue();
        }
    }

    public static int getIntValue(Integer integer) {
        if (integer == null) {
            return 0;
        } else {
            return integer.intValue();
        }
    }
}
