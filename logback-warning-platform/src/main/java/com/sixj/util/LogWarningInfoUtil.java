package com.sixj.util;

/**
 * @author sixiaojie
 * @date 2021-06-04-16:43
 */
public class LogWarningInfoUtil {

    private static final ThreadLocal<LogWarningInfo> ENVIRONMENT_INFO_THREAD_LOCAL = new InheritableThreadLocal<>();


    public static LogWarningInfo getLogWaringInfo() {
        return ENVIRONMENT_INFO_THREAD_LOCAL.get();
    }

    public static void removeLanguageInfo() {
        ENVIRONMENT_INFO_THREAD_LOCAL.remove();
    }


    public static void setLogWaringInfo(LogWarningInfo logWarningInfo) {
        ENVIRONMENT_INFO_THREAD_LOCAL.set(logWarningInfo);
    }



}
