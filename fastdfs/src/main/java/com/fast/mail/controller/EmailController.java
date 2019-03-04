package com.fast.mail.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.fast.mail.controller.core.Config;
import com.fast.mail.controller.model.MailModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.mail.internet.MimeMessage;
import java.io.File;

/**
 * @author gs
 * 邮件系统封装调用
 */
@RestController
@RequestMapping("gs")
public class EmailController {
    @Autowired
    private JavaMailSender javaMailSender;

    /**
     * 单发邮件
     * 对文本不进行处理
     * eg:<p color='red'>消息<p/>==>处理成<p color="red">消息<p/>
     * 不会进行转义
     *
     * @param params
     * @return
     */
    @RequestMapping("/mail/sendMailMessage.do")
    public JSONObject sendMailMessage(@RequestBody JSONObject params) {
        JSONObject result = new JSONObject();
        System.out.println(params);
        String text = params.get("text").toString();
        String to = params.get("to").toString();
        String subJect = params.get("subJect").toString();
        SimpleMailMessage sendMessage = new SimpleMailMessage();
        if (result == null) {
        }
        if (StringUtils.isEmpty(subJect)
                || StringUtils.isEmpty(to) ||
                StringUtils.isEmpty(text)) {
            result.put("code",-200);
            return result;
        }
        sendMessage.setSubject(subJect);
        sendMessage.setText(text);
        sendMessage.setTo(to);
        sendMessage.setFrom(Config.USERNAME);
        javaMailSender.send(sendMessage);
        result.put("code",200);
        return result;
    }

    /**
     * 群发邮件
     * 不会转义
     *
     * @param params
     * @return
     */
    @RequestMapping("/mail/sendMailMessages.do")
    public JSONObject  sendMailMessages(@RequestBody JSONObject params) {
        JSONObject result = new JSONObject();
        System.out.println(params);
        String text = params.get("text").toString();
        String tos = params.get("tos").toString();
        String subJect = params.get("subJect").toString();
        SimpleMailMessage sendMessage = new SimpleMailMessage();
        if (result == null) {
        }
        if (StringUtils.isEmpty(subJect)
                || StringUtils.isEmpty(tos) ||
                StringUtils.isEmpty(text)) {
            result.put("code",-200);
        }
        String[] to = tos.split(",");
        sendMessage.setSubject(subJect);
        sendMessage.setText(text);
        sendMessage.setTo(to);
        sendMessage.setFrom(Config.USERNAME);
        javaMailSender.send(sendMessage);
        result.put("code",200);
        return result;
    }


    /**
     * 发送复杂邮件
     * 转义文本消息
     * 和发送文件
     *
     * @param params
     * @return
     */
    @RequestMapping("/mail/sendMailComplexMessage.do")
    public  JSONObject sendMailComplexMessage(@RequestBody JSONObject params) throws Exception {
        JSONObject result = new JSONObject();
        System.out.println(params);
        String text = params.get("text").toString();
        String to = params.get("to").toString();
        String subJect = params.get("subJect").toString();
        SimpleMailMessage sendMessage = new SimpleMailMessage();
        if (result == null) {
        }
        if (StringUtils.isEmpty(subJect)
                || StringUtils.isEmpty(to) ||
                StringUtils.isEmpty(text)) {
            result.put("code",-200);
        }
        MimeMessage mimeMessage =
                javaMailSender.createMimeMessage();
        MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(
                mimeMessage, true);
        mimeMessageHelper.setSubject(subJect);
        mimeMessageHelper.setText(text, true);
        mimeMessageHelper.setFrom(Config.USERNAME);
        mimeMessageHelper.setTo(to);
        mimeMessageHelper.addAttachment("", new File(""));
        result.put("code",200);
        javaMailSender.send(mimeMessage);
        return result;
    }

    @RequestMapping("/mail/sendMailComplexMessages.do")
    public JSONObject sendMailComplexMessages(@RequestBody JSONObject params) throws Exception {
        JSONObject result = new JSONObject();
        System.out.println(params);
        String text = params.get("text").toString();
        String tos = params.get("tos").toString();
        String subJect = params.get("subJect").toString();
        SimpleMailMessage sendMessage = new SimpleMailMessage();
        if (result == null) {
        }
        if (StringUtils.isEmpty(subJect)
                || StringUtils.isEmpty(tos) ||
                StringUtils.isEmpty(text)) {
            result.put("code", -200);
        }
        MimeMessage mimeMessage =
                javaMailSender.createMimeMessage();
        MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(
                mimeMessage, true);
        String[] to = tos.split(",");
        mimeMessageHelper.setSubject(subJect);
        mimeMessageHelper.setText(text, true);
        mimeMessageHelper.setFrom(Config.USERNAME);
        mimeMessageHelper.setTo(to);
        mimeMessageHelper.addAttachment("", new File(""));
        result.put("code", 200);
        javaMailSender.send(mimeMessage);
        return result;
    }
}
