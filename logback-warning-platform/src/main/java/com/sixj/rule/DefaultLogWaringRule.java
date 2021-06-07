package com.sixj.rule;

import org.springframework.stereotype.Component;

/**
 * 默认异常日志匹配规则，所有异常日志都告警
 * @author sixiaojie
 * @date 2021-06-04-20:07
 */
@Component("defaultLogWaringRule")
public class DefaultLogWaringRule implements LogWaringRule {

    @Override
    public boolean verdict(String errorMessage) {
        return true;
    }
}
