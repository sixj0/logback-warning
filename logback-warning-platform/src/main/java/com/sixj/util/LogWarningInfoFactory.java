package com.sixj.util;

import com.sixj.rule.LogWaringRule;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;

import org.springframework.context.ApplicationContext;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.Map;
import java.util.Objects;

/**
 * @author sixiaojie
 * @date 2021-06-04-20:13
 */
@Component
public class LogWarningInfoFactory implements ApplicationRunner {

    @Resource(name = "defaultLogWaringRule")
    private LogWaringRule defaultLogWaringRule;

    @Autowired
    private Environment environment;

    @Autowired
    private ApplicationContext applicationContext;

    private static LogWarningInfo logWarningInfo;

    @Override
    public void run(ApplicationArguments args) {

        logWarningInfo = new LogWarningInfo();

        String webHook = environment.getProperty("dingDing.logback.webHook");
        String phones = environment.getProperty("dingDing.logback.phones");
        String applicationName = environment.getProperty("spring.application.name");
        logWarningInfo.setWebHook(webHook);
        logWarningInfo.setPhones(phones);
        logWarningInfo.setApplicationName(applicationName);

        // 获取匹配规则
        Map<String, LogWaringRule> rules = applicationContext.getBeansOfType(LogWaringRule.class);
        rules.remove("defaultLogWaringRule");
        if(rules.size() == 0){
            logWarningInfo.setLogWaringRule(defaultLogWaringRule);
        }else {
            for (LogWaringRule logWaringRule : rules.values()) {
                logWarningInfo.setLogWaringRule(logWaringRule);
            }
        }

    }

    public static LogWarningInfo getLogWarningInfo(){
        return logWarningInfo;
    }
}
