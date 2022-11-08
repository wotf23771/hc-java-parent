package com.hc.java.mybatis.entity;

import com.baomidou.mybatisplus.annotation.TableName;

import java.lang.annotation.Annotation;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

/**
 * 动态表名实体接口
 *
 * @author wangxiaolei
 * @since 2022/7/25 16:13
 */
public interface DynamicTableNameEntity<T> {

    /**
     * 获得逻辑表名，由实现类实体 {@link com.baomidou.mybatisplus.annotation.TableName}  注解来标识
     *
     * @return
     */
    default String getLogicTableName() {
        Type[] interfaces = DynamicTableNameEntity.this.getClass().getGenericInterfaces();
        if (interfaces != null && interfaces.length > 0) {
            for (Type type : interfaces) {
                Type[] types = ((ParameterizedType) type).getActualTypeArguments();
                if (types != null && types.length > 0) {
                    for (Type type1 : types) {
                        Class clazz = (Class) type1;
                        Annotation anno = clazz.getAnnotation(TableName.class);
                        if (anno != null) {
                            return ((TableName) anno).value();
                        }
                    }
                }
            }
        }
        return null;
    }

    /**
     * 获得物理表名
     *
     * @return
     */
    String getPhysicTableName();
}
