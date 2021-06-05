package com.sixj.rule;

/**
 * @author sixiaojie
 * @date 2021-06-04-19:35
 */
public interface LogWaringRule {

    /**
     * 匹配规则，根据日志格式判断是否需要提醒
     * @param errorMessage
     * @return
     */
    default boolean verdict(String errorMessage){
        return true;
    }
}
