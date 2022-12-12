package com.hc.java.common.web;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.hc.java.common.constant.ResultCode;
import com.hc.java.common.page.Page;
import lombok.Data;

import java.io.Serializable;
import java.time.Instant;

/**
 * Web端异步响应消息
 *
 * @author wangxiaolei
 * @date 2020/9/22 12:42
 */
@Data
public class WebResult<T> implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 操作成功标识
     */
    private boolean success = true;

    /**
     * 响应时间戳
     */
    private long timestamp = Instant.now().toEpochMilli();

    /**
     * 响应码
     */
    private int code = 0;

    /**
     * 响应消息
     */
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String message;

    /**
     * 响应数据
     */
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private T data;

    /**
     * 分页信息
     */
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Page page;

    /**
     * 创建返回数据对象
     */
    private WebResult() {
    }

    private WebResult(boolean success) {
        this(success, success ? 0 : 1, null);
    }

    /**
     * 创建返回数据对象
     *
     * @param success 成功标识
     * @param code    状态码
     * @param message 消息信息
     */
    private WebResult(boolean success, int code, String message) {
        this.success = success;
        this.code = code;
        this.message = message;
    }

    /**
     * 构造响应结果
     *
     * @param code 响应码
     * @return 响应结果
     */
    public static WebResult build(boolean success, int code) {
        return new WebResult(success, code, "");
    }

    /**
     * 返回成功消息
     *
     * @return 成功消息
     */
    public static WebResult success() {
        return WebResult.success("操作成功");
    }

    /**
     * 返回成功消息
     *
     * @param message 返回内容
     * @return 成功消息
     */
    public static WebResult success(String message) {
        return new WebResult(true, ResultCode.SUCCESS.code(), message);
    }

    /**
     * 返回错误消息
     *
     * @return
     */
    public static WebResult error() {
        return WebResult.error("操作失败");
    }

    /**
     * 返回错误消息
     *
     * @param message 返回内容
     * @return
     */
    public static WebResult error(String message) {
        return new WebResult(false, ResultCode.FAIL.code(), message);
    }

    /**
     * 返回成功数据
     *
     * @param data 返回的数据
     * @return
     */
    public WebResult withData(T data) {
        this.data = data;
        return this;
    }

    public WebResult withPage(Page page) {
        this.page = page;
        return this;
    }
}
