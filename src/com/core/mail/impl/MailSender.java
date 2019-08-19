package com.core.mail.impl;

import java.util.Properties;

import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSenderImpl;

import com.core.mail.IMailSender;

public class MailSender implements IMailSender{

	@Override
	public void simpleMailSend(String email,String subject,String msg) {
	 	//创建邮件发送服务器
        JavaMailSenderImpl mailSender = new JavaMailSenderImpl();  
        mailSender.setHost("smtp.qq.com");
//      mailSender.setPort(465);
        mailSender.setUsername("2118724165@qq.com");
        mailSender.setPassword("qjkqsqxavtogbfee");
        //加认证机制
        Properties javaMailProperties = new Properties();
    	javaMailProperties.put("mail.smtp.auth", true);  //认证机制开关
    	javaMailProperties.put("mail.smtp.starttls.enable", true); //TLS通讯协议开关
    	javaMailProperties.put("mail.smtp.timeout", 5000); 
        mailSender.setJavaMailProperties(javaMailProperties);
        //创建邮件内容
        SimpleMailMessage message=new SimpleMailMessage();
        message.setFrom("2118724165@qq.com");
        message.setTo(email);
        message.setSubject(subject);
        message.setText(msg);
        //发送邮件
        mailSender.send(message);
	}
	

}
