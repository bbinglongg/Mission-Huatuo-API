package com.hase.huatuo.healthcheck.utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

/**
 * send mail
 * @Author jason
 * @Date 20200202
 */
@Component
public class MailUtils {
    @Autowired
    private JavaMailSender jms;
    @Value("${spring.mail.username}")
    private String fromMail;
    private static MailUtils mailUtils;

    @PostConstruct
    public void init(){
        mailUtils=this;
        mailUtils.jms=this.jms;
        mailUtils.fromMail = this.fromMail;
    }

    public static void send(String[] toMail,String context){
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
