package com.fast.mail.controller.utils;

import com.fast.mail.controller.core.Config;
import com.fast.mail.controller.model.MailModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import javax.mail.internet.MimeMessage;
import java.io.File;
import java.security.spec.ECField;
import java.util.Date;

public class EmailUtils {
    @Autowired
    private static JavaMailSender javaMailSender;

    /**
     * 单发邮件
     * 对文本不进行处理
     * eg:<p color='red'>消息<p/>==>处理成<p color="red">消息<p/>
     * 不会进行转义
     *
     * @param mailModel
     * @return
     */
    public static boolean sendMailMessage(MailModel mailModel) {
        SimpleMailMessage sendMessage = new SimpleMailMessage();
        if (mailModel == null) {
            return false;
        }
        if (StringUtils.isEmpty(mailModel.getText())
                || StringUtils.isEmpty(mailModel.getTo()) ||
                StringUtils.isEmpty(mailModel.getSubJect())) {
            return false;
        }
        sendMessage.setSubject(mailModel.getSubJect());
        sendMessage.setText(mailModel.getText());
        sendMessage.setTo(mailModel.getTo());
        sendMessage.setFrom(Config.USERNAME);
        javaMailSender.send(sendMessage);
        return true;
    }

    /**
     * 群发邮件
     * 不会转义
     *
     * @param mailModel
     * @return
     */
    public static boolean sendMailMessages(MailModel mailModel) {
        SimpleMailMessage sendMessage = new SimpleMailMessage();
        if (mailModel == null) {
            return false;
        }
        if (StringUtils.isEmpty(mailModel.getText())
                || StringUtils.isEmpty(mailModel.getTos()) ||
                StringUtils.isEmpty(mailModel.getSubJect())) {
            return false;
        }
        sendMessage.setSubject(mailModel.getSubJect());
        sendMessage.setText(mailModel.getText());
        sendMessage.setTo(mailModel.getTos());
        sendMessage.setFrom(Config.USERNAME);
        javaMailSender.send(sendMessage);
        return true;
    }

    /**
     * 发送复杂邮件
     * 转义文本消息
     * 和发送文件
     *
     * @param mailModel
     * @param fileName  文件名称 带扩展名
     * @return
     */
    public static boolean sendMailComplexMessage(MailModel mailModel, String fileName, File file
    ) throws Exception {
        if (mailModel == null) {
            return false;
        }
        if (StringUtils.isEmpty(mailModel.getText())
                || StringUtils.isEmpty(mailModel.getTo()) ||
                StringUtils.isEmpty(mailModel.getSubJect())) {
            return false;
        }
        MimeMessage mimeMessage =
                javaMailSender.createMimeMessage();
        MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(
                mimeMessage, true);
        mimeMessageHelper.setSubject(mailModel.getSubJect());
        mimeMessageHelper.setText(mailModel.getText(), true);
        mimeMessageHelper.setFrom(Config.USERNAME);
        mimeMessageHelper.setTo(mailModel.getTo());
        mimeMessageHelper.addAttachment(fileName, file);
        return true;
    }


    /**
     * 发送复杂邮件(群发)
     * 转义文本消息
     * 和发送文件
     *
     * @param mailModel
     * @param fileName  文件名称 带扩展名
     * @return
     */
    public static boolean sendMailComplexMessages(MailModel mailModel, String fileName, File file
    ) throws Exception {
        if (mailModel == null) {
            return false;
        }
        if (StringUtils.isEmpty(mailModel.getText())
                || StringUtils.isEmpty(mailModel.getTos()) ||
                StringUtils.isEmpty(mailModel.getSubJect())) {
            return false;
        }
        MimeMessage mimeMessage =
                javaMailSender.createMimeMessage();
        MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(
                mimeMessage, true);
        mimeMessageHelper.setSubject(mailModel.getSubJect());
        mimeMessageHelper.setText(mailModel.getText(), true);
        mimeMessageHelper.setFrom(Config.USERNAME);
        mimeMessageHelper.setTo(mailModel.getTos());
        mimeMessageHelper.addAttachment(fileName, file);
        return true;
    }
}
