package com.hc.java.redis.cache;

import org.springframework.lang.Nullable;

/**
 * 序列化工具类
 *
 * @author wangxiaolei
 * @since 2022/11/7 16:31
 */
public class SerializationUtils {

    public static final byte[] EMPTY_ARRAY = new byte[0];

    public static boolean isEmpty(@Nullable byte[] data) {
        return (data == null || data.length == 0);
    }
}
