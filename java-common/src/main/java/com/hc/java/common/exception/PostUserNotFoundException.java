package com.hc.java.common.exception;

import com.hc.java.common.result.ResultCode;

/**
 * 岗位用户未找到异常
 */
public class PostUserNotFoundException extends BizException {

    public PostUserNotFoundException(String postId, String userId) {
        super(ResultCode.BIZ_DATA_NOT_EXISTS.code(), "postId:" + postId + ",userId:" + userId);
    }
}
