package com.hc.java.mybatis.entity;

import org.apache.ibatis.reflection.MetaObject;

/**
 * 自定义保存、更新实体时处理器
 */
public interface MetaObjectHandlerCustomizer {

    void insertFill(MetaObject metaObject);

    void updateFill(MetaObject metaObject);
}
