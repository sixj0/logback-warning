package com.sixj.test.rule;

import com.sixj.rule.LogWaringRule;
import org.springframework.stereotype.Component;

/**
 * @author sixiaojie
 * @date 2021-06-04-20:07
 */
@Component
public class MyLogWaringRule implements LogWaringRule {

    @Override
    public boolean verdict(String errorMessage) {

        return errorMessage.contains("url");
    }
}
