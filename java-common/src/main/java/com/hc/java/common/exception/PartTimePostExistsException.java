package com.hc.java.common.exception;

import com.hc.java.common.constant.ResultCode;

/**
 * 存在兼职异常
 *
 * @author wangxiaolei
 * @since 2021/10/21 17:45
 */
public class PartTimePostExistsException extends BizException {

    public PartTimePostExistsException(String message) {
        super(ResultCode.BIZ_DATA_ASSOCIATED.code(), message);
    }
}
