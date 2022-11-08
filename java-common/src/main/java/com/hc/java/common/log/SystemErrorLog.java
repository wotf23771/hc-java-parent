package com.hc.java.common.log;

import lombok.extern.slf4j.Slf4j;

/**
 * 系统业务日志
 *
 * @author wangxiaolei
 * @since 2022/5/19 16:17
 */
@Slf4j
public class SystemErrorLog {

    public void error(String format, String... args) {
        log.error(format, args);
    }
}
