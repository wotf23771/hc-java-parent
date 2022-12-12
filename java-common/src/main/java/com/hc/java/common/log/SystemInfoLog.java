package com.hc.java.common.log;

import lombok.extern.slf4j.Slf4j;

/**
 * 系统 info  日志
 *
 * @author wangxiaolei
 * @since 2022/12/13 1:01
 */
@Slf4j
public class SystemInfoLog {

    public void info(String format, String... args) {
        log.info(format, args);
    }
}
