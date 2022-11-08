package com.hc.java.common.utils;

import org.springframework.util.CollectionUtils;

import java.util.List;
import java.util.Objects;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * 转换工具类
 *
 * @author wangxiaolei
 * @since 2021/3/17 15:44
 */
public class ConvertUtils {

    /**
     * 转换列表
     *
     * @param source   原列表
     * @param function 转换函数
     * @param <A>      原列表中数据类型
     * @param <B>      结果列表中数据类型
     * @return 结果列表
     */
    public static <A, B> List<B> convertList(List<A> source, Function<A, B> function) {
        if (CollectionUtils.isEmpty(source)) {
            return null;
        }
        return source.stream().map(function).collect(Collectors.toList());
    }

    /**
     * 转换单个数据对象
     *
     * @param source   原数据
     * @param function 转换函数
     * @param <A>      原数据类型
     * @param <B>      结果数据类型
     * @return 结果数据
     */
    public static <A, B> B convertItem(A source, Function<A, B> function) {
        if (Objects.isNull(source)) {
            return null;
        }
        return function.apply(source);
    }
}
