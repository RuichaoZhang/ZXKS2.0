package com.capgemini.util;

import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

/**
 * 发送邮件的工具类
 * @author chao538
 *
 */
public class SendMail {
	
	/**
	 * 发送邮件的方法
	 * @param examineeEmail
	 * @param subject
	 * @param content
	 * @throws AddressException
	 * @throws MessagingException
	 */
	public static void mail(String examineeEmail, String subject, String content) throws AddressException, MessagingException{
		//环境变量设置。发送邮件时才需要
		Properties props = new Properties();
		//发送使用的协议
		props.setProperty("mail.transport.protocol", "smtp");
		//发送服务器的主机地址
		props.setProperty("mail.host", "smtp.163.com");
		//请求身份验证
		props.setProperty("mail.smtp.auth", "true");
		//调试模式
		props.setProperty("mail.debug", "true");
		Session session = Session.getDefaultInstance(props,new Authenticator() {
			@Override
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication("18309286581","6.110000");
			}
		});
		//代表一封邮件
		MimeMessage message = new MimeMessage(session);
		//设置发件人
		message.setFrom(new InternetAddress("18309286581@163.com"));
		//设置收件人
		message.setRecipients(Message.RecipientType.TO, examineeEmail);
		//设置主题
		message.setSubject(subject);
		
		//设置邮件的正文内容
		message.setText(content);
		message.saveChanges();
		Transport.send(message);
	}
}
