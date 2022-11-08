package com.hc.java.common.result;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.hc.java.common.page.Page;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * Web端异步响应消息
 *
 * @author wangxiaolei
 * @date 2020/9/22 12:42
 */
@Data
public class WebResult implements Serializable {

    private static final long serialVersionUID = 1L;

    private boolean success = true;

    private int code = 0;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String message;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Object data;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Page page;

    /**
     * 创建返回数据对象
     */
    private WebResult() {
    }

    private WebResult(boolean success) {
        this(success, success ? 0 : 1, null, null);
    }

    /**
     * 创建返回数据对象
     *
     * @param success 成功标识
     * @param code    状态码
     * @param message 消息信息
     */
    private WebResult(boolean success, int code, String message) {
        this(success, code, message, null);
    }

    /**
     * 创建返回数据对象
     *
     * @param success 成功标识
     * @param code    状态码
     * @param message 消息信息
     * @param data    返回数据对象
     */
    private WebResult(boolean success, int code, String message, Object data) {
        this.success = success;
        this.code = code;
        this.message = message;
        this.data = data;
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
     * 构造响应结果
     *
     * @param code    响应码
     * @param message 响应消息
     * @return 响应结果
     */
    public static WebResult build(boolean success, int code, String message) {
        return new WebResult(success, code, message);
    }

    /**
     * 构造响应结果
     *
     * @param code 响应码
     * @param data 响应数据
     * @return 响应结果
     */
    public static WebResult build(boolean success, int code, Object data) {
        return new WebResult(success, code, "", data);
    }

    /**
     * 构造响应结果
     *
     * @param code    响应码
     * @param message 响应结果
     * @param data    响应数据
     * @return 响应结果
     */
    public static WebResult build(boolean success, int code, String message, Object data) {
        return new WebResult(success, code, message, data);
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
     * 返回成功数据
     *
     * @return 成功消息
     */
    public static WebResult success(Object data) {
        return WebResult.success("操作成功", data);
    }

    public static <T> WebResult successPage(List<T> data, Page page) {
        WebResult result = WebResult.success("操作成功", data);
        result.page = page;
        return result;
    }

    /**
     * 返回成功消息
     *
     * @param message 返回内容
     * @return 成功消息
     */
    public static WebResult success(String message) {
        return WebResult.success(message, null);
    }

    /**
     * 返回成功消息
     *
     * @param msg  返回内容
     * @param data 数据对象
     * @return 成功消息
     */
    public static WebResult success(String msg, Object data) {
        return new WebResult(true, ResultCode.SUCCESS.code(), msg, data);
    }

    /**
     * 返回成功数据
     *
     * @param data 返回的数据
     * @return
     */
    public static WebResult data(Object data) {
        return new WebResult(true, ResultCode.SUCCESS.code(), "", data);
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
     * @param msg 返回内容
     * @return 警告消息
     */
    public static WebResult error(String msg) {
        return WebResult.error(msg, null);
    }

    /**
     * 返回错误消息
     *
     * @param msg  返回内容
     * @param data 数据对象
     * @return 警告消息
     */
    public static WebResult error(String msg, Object data) {
        return new WebResult(false, ResultCode.FAIL.code(), msg, data);
    }

    /**
     * 返回错误消息
     *
     * @param code 状态码
     * @param msg  返回内容
     * @return 警告消息
     */
    public static WebResult error(int code, String msg) {
        return new WebResult(false, code, msg, null);
    }
}
