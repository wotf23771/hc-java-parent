package com.hc.java.common.log;

/**
 * 系统日志
 *
 * @author wangxiaolei
 * @since 2022/12/13 1:02
 */
public class SystemLog {

    private SystemInfoLog infoLog = new SystemInfoLog();

    private SystemDebugLog debugLog = new SystemDebugLog();

    private SystemErrorLog errorLog = new SystemErrorLog();

    public void info(String format, String... args) {
        infoLog.info(format, args);
    }

    public void debug(String format, String... args) {
        debugLog.debug(format, args);
    }

    public void error(String format, String... args) {
        errorLog.error(format, args);
    }
}
