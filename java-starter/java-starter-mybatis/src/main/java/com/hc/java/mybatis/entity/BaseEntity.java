package com.hc.java.mybatis.entity;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.Version;
import lombok.Data;
import org.apache.ibatis.type.JdbcType;

import java.io.Serializable;
import java.util.Date;

/**
 * 数据库表映射实体基类
 */
@Data
public class BaseEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 创建日期
     */
    @TableField(jdbcType = JdbcType.TIMESTAMP, fill = FieldFill.INSERT)
    private Date createDate;

    /**
     * 创建人ID
     */
    @TableField(jdbcType = JdbcType.VARCHAR, fill = FieldFill.INSERT)
    private String createUserId;

    /**
     * 创建人姓名
     */
    @TableField(jdbcType = JdbcType.VARCHAR, fill = FieldFill.INSERT)
    private String createUserName;

    /**
     * 修改时间
     */
    @TableField(jdbcType = JdbcType.TIMESTAMP, fill = FieldFill.INSERT_UPDATE)
    private Date updateDate;

    /**
     * 修改人ID
     */
    @TableField(jdbcType = JdbcType.VARCHAR, fill = FieldFill.INSERT_UPDATE)
    private String updateUserId;

    /**
     * 修改人姓名
     */
    @TableField(jdbcType = JdbcType.VARCHAR, fill = FieldFill.INSERT_UPDATE)
    private String updateUserName;

    /**
     * 数据版本，乐观锁
     */
    @Version
    @TableField(jdbcType = JdbcType.NUMERIC, fill = FieldFill.INSERT)
    private Integer version;

}
