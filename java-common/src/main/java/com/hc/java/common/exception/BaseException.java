package com.hc.java.common.exception;

import com.hc.java.common.constant.ResultCode;

/**
 * 基础异常
 *
 * @author wangxiaolei
 */
public class BaseException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    private Integer code;

    private String message;

    public BaseException(String message) {
        this(ResultCode.FAIL.code(), message);
    }

    public BaseException(Integer code, String message) {
        super(message);
        this.code = code;
        this.message = message;
    }

    @Override
    public String getMessage() {
        return this.message;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
