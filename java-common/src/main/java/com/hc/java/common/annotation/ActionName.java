package com.hc.java.common.annotation;

import java.lang.annotation.*;

/**
 * 动作名称
 *
 * @author wangxiaolei
 * @since 2021/8/19 11:54
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.METHOD})
@Documented
public @interface ActionName {

    /**
     * 动作名称
     *
     * @return
     */
    String value() default "";
}
