package com.hc.java.common.utils;

import cn.hutool.core.lang.Snowflake;

/**
 * 通用 ID 生成器
 *
 * @author wangxiaolei
 * @since 2021/10/17 17:06
 */
public class UidGenerator {

    private static Snowflake snowflake = new Snowflake();

    /**
     * 获得下一个ID
     *
     * @return
     */
    public static long nextId() {
        return snowflake.nextId();
    }

    public static String nextIdStr() {
        return snowflake.nextIdStr();
    }

    public static void main(String[] args) {
        for (int i = 0; i < 10; i++) {
            System.out.println(nextId());
        }
    }
}
