package com.hc.java.common.result;

/**
 * Web 响应状态码
 *
 * @auth wangxiaolei
 * @date 2020/11/3 20:35
 */
public enum ResultCode {

    SUCCESS(0, "成功"),
    UNAVAILABLE(-1, "服务不可用"),
    FAIL(1, "失败"),

    UNAUTHORIZED(401, "未认证"),

    FORBIDDEN(403, "未授权"),

    SERVICE_ERROR(50000, "服务异常"),

    // 参数类异常，通常由 Controller 抛出
    DATA_ERROR(50100, "参数异常"),
    PARAM_ERROR(50101, "请求参数异常"),
    FILE_SIZE_ERROR(50102, "文件大小超过限制"),

    // 业务类异常，通常由 Service 抛出
    BIZ_ERROR(50200, "业务异常"),
    BIZ_DATA_EXISTS(50201, "数据已存在"),
    BIZ_DATA_NOT_EXISTS(50202, "数据不存在"),
    BIZ_DATA_ASSOCIATED(50203, "存在关联数据"),
    BIZ_DATA_MODIFIED(50204, "数据已被修改"),
    BIZ_FORBIDDEN(50300, "业务禁止");

    private int code;

    private String message;

    ResultCode(int code, String message) {
        this.code = code;
        this.message = message;
    }

    public int code() {
        return code;
    }

    public String message() {
        return message;
    }

}
