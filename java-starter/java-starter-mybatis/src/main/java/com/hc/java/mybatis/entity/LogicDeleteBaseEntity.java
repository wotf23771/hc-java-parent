package com.hc.java.mybatis.entity;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.apache.ibatis.type.JdbcType;

/**
 * 带有逻辑删除位的实体
 *
 * @author wangxiaolei
 * @since 2021/10/28 11:36
 */
@EqualsAndHashCode(callSuper = false)
@Data
public class LogicDeleteBaseEntity extends BaseEntity {

    /**
     * 删除标识，0：未删除，1：已删除
     */
    @JsonIgnore
    @TableField(jdbcType = JdbcType.NUMERIC, fill = FieldFill.INSERT)
    private Integer isDeleted;
}
