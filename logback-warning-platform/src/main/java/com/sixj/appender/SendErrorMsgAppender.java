package com.sixj.appender;

import ch.qos.logback.classic.Level;
import ch.qos.logback.classic.spi.ILoggingEvent;
import ch.qos.logback.core.UnsynchronizedAppenderBase;
import com.sixj.rule.LogWaringRule;
import com.sixj.util.LogWarningInfoFactory;
import com.sixj.util.LogWarningInfo;
import com.sixj.util.RobotUtil;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.net.InetAddress;
import java.net.UnknownHostException;


/**
 * @author sixiaojie
 * @date 2021-06-03-13:30
 */
@Component
public class SendErrorMsgAppender extends UnsynchronizedAppenderBase<ILoggingEvent> {

    @Override
    public void append(ILoggingEvent event) {
        if (event.getLevel() == Level.ERROR) {

            LogWarningInfo logWaringInfo = LogWarningInfoFactory.getLogWarningInfo();
            String formattedMessage = event.getFormattedMessage();
            // 匹配规则
            LogWaringRule logWaringRule = logWaringInfo.getLogWaringRule();
            boolean verdict = logWaringRule.verdict(formattedMessage);
            if(!verdict){
                return;
            }

            StringBuilder sb = new StringBuilder();

            //获取服务器Ip，告知哪台服务器抛异常
            String ip = "";
            try {
                InetAddress address = InetAddress.getLocalHost();
                ip = address.getHostAddress();
            } catch (UnknownHostException e1) {
                e1.printStackTrace();
            }

            if (!StringUtils.isEmpty(ip)) {
                sb.append("主机：").append(ip).append("\n");
            }


            if(!StringUtils.isEmpty(logWaringInfo.getApplicationName())){
                sb.append("服务名：").append(logWaringInfo.getApplicationName()).append("\n");
            }

            sb.append("告警信息：").append(formattedMessage);

            String msg = sb.toString();
            if (!StringUtils.isEmpty(msg)) {
                RobotUtil.setMessage(false,msg,logWaringInfo.getWebHook(),logWaringInfo.getPhones());
            }
        }
    }


}

