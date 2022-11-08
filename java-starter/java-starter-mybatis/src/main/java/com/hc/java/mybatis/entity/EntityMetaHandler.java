package com.hc.java.mybatis.entity;

import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import com.baomidou.mybatisplus.core.metadata.TableInfo;
import com.hc.java.common.constant.BaseEntityField;
import com.hc.java.common.constant.GlobalConstants;
import com.hc.java.security.context.UserContext;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.MapUtils;
import org.apache.ibatis.reflection.MetaObject;
import org.springframework.context.ApplicationContext;

import java.util.Date;
import java.util.Map;

/**
 * 实体系统属性自动填充器
 *
 * @author wangxiaolei
 * @since 2021/3/20 1:30
 */
@Slf4j
public class EntityMetaHandler implements MetaObjectHandler {

    private ApplicationContext applicationContext;

    public EntityMetaHandler(ApplicationContext applicationContext) {
        this.applicationContext = applicationContext;
    }

    @Override
    public void insertFill(MetaObject metaObject) {
        // 设置创建时间
        this.strictInsertFill(metaObject, BaseEntityField.CREATE_DATE, Date.class, new Date());
        this.strictInsertFill(metaObject, BaseEntityField.IS_DELETED, Integer.class, GlobalConstants.DB_NOT_DELETED);
        this.strictInsertFill(metaObject, BaseEntityField.VERSION, Integer.class, 1);

        // 在当前上下文获取用户信息
        UserContext.User user = UserContext.get();
        if (user != null) {
            // 设置创建人ID
            this.strictInsertFill(metaObject, BaseEntityField.CREATE_USER_ID, String.class, user.getUserId());
            // 设置创建人姓名
            this.strictInsertFill(metaObject, BaseEntityField.CREATE_USER_NAME, String.class, user.getUserName());

            // 自定义处理类
            Map<String, MetaObjectHandlerCustomizer> customizerMap = applicationContext.getBeansOfType(MetaObjectHandlerCustomizer.class);
            if (MapUtils.isNotEmpty(customizerMap)) {
                customizerMap.values().forEach(metaObjectHandlerCustomizer -> metaObjectHandlerCustomizer.insertFill(metaObject));
            }
        }
        // 设置修改信息
        updateFill(metaObject);
    }

    @Override
    public void updateFill(MetaObject metaObject) {
        // 设置修改时间
        TableInfo tableInfo = findTableInfo(metaObject);
        tableInfo.getFieldList()
                .stream()
                .filter(field -> field.getProperty().equals(BaseEntityField.UPDATE_DATE)).findFirst()
                .ifPresent(p -> metaObject.setValue(p.getProperty(), new Date()));
        // 在当前上下文获取用户信息
        UserContext.User user = UserContext.get();
        if (user != null) {
            metaObject.setValue(BaseEntityField.UPDATE_USER_ID, user.getUserId());
            metaObject.setValue(BaseEntityField.UPDATE_USER_NAME, user.getUserName());

            // 自定义处理类
            Map<String, MetaObjectHandlerCustomizer> customizerMap = applicationContext.getBeansOfType(MetaObjectHandlerCustomizer.class);
            if (MapUtils.isNotEmpty(customizerMap)) {
                customizerMap.values().forEach(metaObjectHandlerCustomizer -> metaObjectHandlerCustomizer.updateFill(metaObject));
            }
        }
    }
}
