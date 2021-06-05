package com.sixj.test.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author sixiaojie
 * @date 2021-06-05-11:43
 */
@RestController
@RequestMapping("/logBackWarning")
@Slf4j
public class TestLogbackWarningController {

    @GetMapping("/test")
    public String test(){
        String url = "www.baidu.com";
        String type = "GET";
        String param = "{'id':'123'}";
        log.error("[调用设备中台获取设备信息]调用异常url：{};请求方式：{};入参：{}",url,type,param);
        log.error("[调用设备中台获取设备信息]调用异常url：{};入参：{}",url,param);
        log.error("[调用设备中台获取设备信息]调用异常u->：{};请求方式：{};入参：{}",url,type,param);

        log.info("测试info异常日志拦截");

        return "success";
    }
}
