package com.hc.java.common.exception;

import com.hc.java.common.constant.ResultCode;

/**
 * 业务异常
 *
 * @author wangxiaolei
 * @since 2021/5/24 10:14
 */
public class BizException extends BaseException {

    private static final long serialVersionUID = 1L;

    /**
     * 所属模块
     */
    private String module;

    /**
     * 错误码对应的参数，JSON格式
     */
    private String args;

    /**
     * @param code 异常代码
     */
    public BizException(ResultCode code) {
        this(code.code(), code.message());
    }

    /**
     * @param message 异常信息
     */
    public BizException(String message) {
        this(ResultCode.BIZ_ERROR.code(), message);
    }

    /**
     * @param code    异常码
     * @param message 异常信息
     */
    public BizException(Integer code, String message) {
        this(code, message, null, null);
    }

    /**
     * @param code    异常码
     * @param message 异常信息
     * @param module  所属模块
     */
    public BizException(Integer code, String message, String module) {
        this(code, message, module, null);
    }

    /**
     * @param message 异常信息
     * @param module  所属模块
     * @param args    异常参数
     */
    public BizException(String message, String module, String args) {
        super(message);
        this.module = module;
        this.args = args;
    }

    /**
     * @param code    异常码
     * @param message 异常信息
     * @param module  所属模块
     * @param args    异常参数
     */
    public BizException(Integer code, String message, String module, String args) {
        super(code, message);
        this.module = module;
        this.args = args;
    }

    @Override
    public String getMessage() {
        return super.getMessage();
    }

    public String getModule() {
        return module;
    }

    public void setModule(String module) {
        this.module = module;
    }

    public String getArgs() {
        return args;
    }

    public void setArgs(String args) {
        this.args = args;
    }
}
