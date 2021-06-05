package com.sixj.util;

import com.sixj.rule.LogWaringRule;

/**
 * @author sixiaojie
 * @date 2021-06-04-16:51
 */
public class LogWarningInfo {

    private String webHook;

    private String phones;

    private String applicationName;

    private LogWaringRule logWaringRule;


    public String getWebHook() {
        return webHook;
    }

    public void setWebHook(String webHook) {
        this.webHook = webHook;
    }

    public String getPhones() {
        return phones;
    }

    public void setPhones(String phones) {
        this.phones = phones;
    }

    public String getApplicationName() {
        return applicationName;
    }

    public void setApplicationName(String applicationName) {
        this.applicationName = applicationName;
    }

    public LogWaringRule getLogWaringRule() {
        return logWaringRule;
    }

    public void setLogWaringRule(LogWaringRule logWaringRule) {
        this.logWaringRule = logWaringRule;
    }
}

