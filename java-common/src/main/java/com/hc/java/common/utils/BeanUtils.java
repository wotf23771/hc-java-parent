package com.hc.java.common.utils;

import lombok.extern.slf4j.Slf4j;

import java.lang.reflect.InvocationTargetException;

/**
 * Bean 工具方法，封装了第三方工具类
 *
 * @author wangxiaolei
 * @since 2021/3/18 10:05
 */
@Slf4j
public class BeanUtils {

    /**
     * 复制对象属性
     *
     * @param source 原对象
     * @param dest   目标对象
     */
    public static void copyProperties(Object source, Object dest) {
        try {
            org.apache.commons.beanutils.BeanUtils.copyProperties(dest, source);
        } catch (IllegalAccessException e) {
            log.error("copy bean error", e);
        } catch (InvocationTargetException e) {
            log.error("copy bean error", e);
        }
    }

    public static <T> T toBean(Object source, Class<T> clazz) {
        return cn.hutool.core.bean.BeanUtil.toBean(source, clazz);
    }
}

