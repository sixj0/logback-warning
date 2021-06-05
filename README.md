# logback-warning

#### 功能介绍：

配合logback日志使用，通过扩展Appender组件，实现在打印error日志时告警的功能，目前告警是使用钉钉通知的方式，默认情况是所有error日志都会告警，可以实现`LogWaringRule`接口，自定义一个匹配规则，根据error日志信息决定是否需要告警。

#### 使用方式：

1. 引入依赖

   ```xml
   <dependency>
     <groupId>com.sixj</groupId>
     <artifactId>logback-warning-platform</artifactId>
     <version>1.0.0-SNAPSHOT</version>
   </dependency>
   ```

2. 添加配置

   ```properties
   #配置钉钉群机器人生成的webHook
   dingDing.logback.webHook=https://oapi.dingtalk.com/robot/send?access_token
   #告警时需要@的用户手机号，多个手机号之间使用英文逗号隔开
   dingDing.logback.phones=15501176233
   ```

3. 在`logback-spring.xml`文件中添加Appender

   ```xml
   <!--异常日志监控钉钉告警-->
   <appender name="SendErrorMsgAppender"
             class="com.sixj.appender.SendErrorMsgAppender">
   </appender>
   
   <logger name="root" level="info" additivity="false">
     <appender-ref ref="SendErrorMsgAppender"/>
   </logger>
   ```

4. 默认所有error日志都会告警，如果需要指定某一些error日志内容才告警的话，需要实现`LogWaringRule`接口的`verdict`方法，自定义一个匹配规则，当日志信息符合规则时才会触发告警，比如：

   ```java
   @Component
   public class MyLogWaringRule implements LogWaringRule {
   
       @Override
       public boolean verdict(String errorMessage) {
           // error日志信息中包含'url'时触发告警
           return errorMessage.contains("url");
       }
   }
   ```

   