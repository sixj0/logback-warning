package com.sixj.util;



import cn.hutool.core.util.StrUtil;
import cn.hutool.http.HttpUtil;
import cn.hutool.json.JSONUtil;

import java.util.Collections;
import java.util.List;


/**
 * 钉钉机器人消息发送工具类
 * 钉钉开发平台地址：https://ding-doc.dingtalk.com/doc#/serverapi2/qf2nxq
 * @author sixiaojie
 * @date 2020-07-30-10:09
 */
public class RobotUtil {
    /**
     * 钉钉群设置的关键字，只有包含关键字的内容才会发送
     */
    private static final String KEYWORD = "告警：\n";



    /**
     * 组装 发送的信息
     *
     * @param isAtAll       是否需要 @所有人
     * @param msgContent 要发送信息的主体
     * @param url Webhook 地址
     * @param phones 要@人的手机号，多个手机号之间用英文逗号隔开
     */
    public static void setMessage(boolean isAtAll, String msgContent,String url,String phones) {

        TextRebootModel model = new TextRebootModel();
        AtMobilesModel atMobiles = new AtMobilesModel();
        atMobiles.setIsAtAll(isAtAll);
        //要@人的手机号码，默认不@
        if(StrUtil.isBlank(phones)){
            atMobiles.setAtMobiles(Collections.singletonList(""));
        }else{
            atMobiles.setAtMobiles(StrUtil.split(phones, ',', true, true));
        }

        ContentModel contentModel = new ContentModel();
        contentModel.setContent(KEYWORD+msgContent);

        model.setAt(atMobiles);
        model.setText(contentModel);

        String toMsg = JSONUtil.toJsonStr(model);
        try {
            RobotUtil.sendErrorReboot(toMsg,url);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    /**
     * 组装 发送的信息
     *
     * @param isAtAll       是否需要 @所有人
     * @param title      标题
     * @param msgContent 要发送信息的主体
     * @param telList    要 @人的电话号码,如果@单独的几个人，就传一个空list，而不是 null
     */
    public static void setMarkDown(boolean isAtAll, String title, String msgContent, List<String> telList,String url) {

        MarkDownRebootModel model = new MarkDownRebootModel();
        AtMobilesModel atMobiles = new AtMobilesModel();
        atMobiles.setIsAtAll(isAtAll);
        atMobiles.setAtMobiles(telList);

        MarkDownModel markDownModel = new MarkDownModel();
        markDownModel.setTitle(title);
        markDownModel.setText(KEYWORD+msgContent);

        model.setAt(atMobiles);
        model.setMarkdown(markDownModel);

        String message = JSONUtil.toJsonStr(model);

        try {
            RobotUtil.sendErrorReboot(message,url);
        }catch (Exception e){
            e.printStackTrace();
        }
    }


    /**
     * 发送消息
     * @param message
     * @param url
     */
    private static void sendErrorReboot(String message, String url) {
        String response = HttpUtil.post(url, message);
    }

    /**
     * 艾特信息
     *
     * @author wangyt
     * @date 2019年05月05日 11:07:05
     */

    private static class AtMobilesModel {

        /**
         * 被@人的手机号
         */
        private List<String> atMobiles;

        /**
         * 在@所有人时:true,否则为:false
         */
        private Boolean isAtAll;

        public List<String> getAtMobiles() {
            return atMobiles;
        }

        public void setAtMobiles(List<String> atMobiles) {
            this.atMobiles = atMobiles;
        }

        public Boolean getIsAtAll() {
            return isAtAll;
        }

        public void setIsAtAll(Boolean atAll) {
            isAtAll = atAll;
        }
    }

    /**
     * 消息内容
     *
     * @author wangyt
     * @date 2019年05月05日 11:07:05
     */
    private static class ContentModel {
        /**
         * 消息内容
         */
        private String content;

        public String getContent() {
            return content;
        }

        public void setContent(String content) {
            this.content = content;
        }
    }
    /**
     * markdown信息
     *
     * @author wangyt
     * @date 2019年05月05日 11:07:05
     */
    private static class MarkDownModel {
        /**
         * 首屏会话透出的展示内容
         */
        private String title;

        /**
         * markdown格式的消息
         */
        private String text;

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getText() {
            return text;
        }

        public void setText(String text) {
            this.text = text;
        }
    }

    /**
     * markDown机器人发送模版
     *
     * @author wangyt
     * @date 2019年05月05日 11:07:05
     */
    private static class MarkDownRebootModel {
        /**
         * 此消息类型为固定markdown
         */
        public String msgtype = "markdown";

        /**
         * markdown内容
         */
        public MarkDownModel markdown;

        /**
         * 艾特model
         */
        public AtMobilesModel at;

        public String getMsgtype() {
            return msgtype;
        }

        public void setMsgtype(String msgtype) {
            this.msgtype = msgtype;
        }

        public MarkDownModel getMarkdown() {
            return markdown;
        }

        public void setMarkdown(MarkDownModel markdown) {
            this.markdown = markdown;
        }

        public AtMobilesModel getAt() {
            return at;
        }

        public void setAt(AtMobilesModel at) {
            this.at = at;
        }
    }

    /**
     * 文本文档发送模版
     *
     * @author wangyt
     * @date 2019年05月05日 11:07:05
     */
    private static class TextRebootModel {

        /**
         * 此消息类型为固定text
         */
        public String msgtype = "text";

        /**
         * 模版信息
         */
        public ContentModel text;

        /**
         * 艾特model
         */
        public AtMobilesModel at;

        public String getMsgtype() {
            return msgtype;
        }

        public void setMsgtype(String msgtype) {
            this.msgtype = msgtype;
        }

        public ContentModel getText() {
            return text;
        }

        public void setText(ContentModel text) {
            this.text = text;
        }

        public AtMobilesModel getAt() {
            return at;
        }

        public void setAt(AtMobilesModel at) {
            this.at = at;
        }
    }


}
