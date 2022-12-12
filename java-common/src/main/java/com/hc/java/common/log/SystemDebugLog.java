package com.hc.java.common.log;

import lombok.extern.slf4j.Slf4j;

/**
 * 系统 debug 日志
 *
 * @author wangxiaolei
 * @since 2022/12/13 1:00
 */
@Slf4j
public class SystemDebugLog {

    public void debug(String format, String... args) {
        if (log.isDebugEnabled()) {
            log.debug(format, args);
        }
    }
}
