package com.hase.huatuo.healthcheck.utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

/**
 * send mail
 *
 * @Author jason
 * @Date 20200202
 */
@Component
public class MailUtils {
    @Autowired
    private JavaMailSender jms;
    @Value("${spring.mail.username}")
    private String fromMail;
    @Value("${spring.mail.mail.subject}")
    private String worningSubject;

    private static MailUtils mailUtils;

    @PostConstruct
    public void init() {
        mailUtils = this;
        mailUtils.jms = this.jms;
        mailUtils.fromMail = this.fromMail;
        mailUtils.worningSubject = this.worningSubject;
        mailUtils.fromMail = this.fromMail;
    }

    public static void sendNotifyEmail(String[] toMail, String status, String position) {
        String contentTemplate =
                "Dear Sir/Madam,\n" +
                "\n" +
                "Please be aware that there is a new coronavirus {status} reported case in ({city_workplace}).\n" +
                "Please refer to system to find more details.\n" +
                "\n" +
                "Best Regards.\n" +
                "HUATUO APP";
        String content = contentTemplate.replaceAll("\\{status}", status).replaceAll("\\{city_workplace}", position);
        send(toMail, mailUtils.worningSubject,content);
    }

    public static void send(String[] toMail, String subject, String context) {
        //建立邮件消息
        SimpleMailMessage mainMessage = new SimpleMailMessage();
        //发送者
        mainMessage.setFrom(mailUtils.fromMail);
        //接收者
        mainMessage.setTo(toMail);
        //发送的标题
        mainMessage.setSubject(subject);
        //发送的内容
        mainMessage.setText(context);
        mailUtils.jms.send(mainMessage);
    }

    public static void send(String[] toMail, String context) {
        //建立邮件消息
        SimpleMailMessage mainMessage = new SimpleMailMessage();
        //发送者
        mainMessage.setFrom(mailUtils.fromMail);
        //接收者
        mainMessage.setTo(toMail);
        //发送的标题
        mainMessage.setSubject("health check report");
        //发送的内容
        mainMessage.setText(context);
        mailUtils.jms.send(mainMessage);
    }
}
