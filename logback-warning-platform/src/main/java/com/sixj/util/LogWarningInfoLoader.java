package com.sixj.util;

import com.sixj.rule.LogWaringRule;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;

import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

import java.util.Objects;

/**
 * @author sixiaojie
 * @date 2021-06-04-20:13
 */
@Component
public class LogWarningInfoLoader implements ApplicationRunner {

    @Autowired
    private LogWaringRule logWaringRule;

    @Autowired
    private Environment environment;

    @Override
    public void run(ApplicationArguments args) {

        LogWarningInfo logWarningInfo = new LogWarningInfo();

        String webHook = environment.getProperty("dingDing.webHook");
        String phones = environment.getProperty("dingDing.verifyClosedApplyOrder.phones");
        String applicationName = environment.getProperty("spring.application.name");
        logWarningInfo.setWebHook(webHook);
        logWarningInfo.setPhones(phones);
        logWarningInfo.setApplicationName(applicationName);

        // 获取匹配规则
        if(!Objects.isNull(logWaringRule)){
            logWarningInfo.setLogWaringRule(logWaringRule);
        }
        LogWarningInfoUtil.setLogWaringInfo(logWarningInfo);
    }
}
