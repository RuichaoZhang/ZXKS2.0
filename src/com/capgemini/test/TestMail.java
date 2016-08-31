package com.capgemini.test;

import javax.mail.MessagingException;
import javax.mail.internet.AddressException;

import com.capgemini.util.SendMail;

public class TestMail {
	public static void main(String[] args) throws AddressException, MessagingException {
		SendMail.mail("851986339@qq.com", "测试", "内容,测试");
	}
}
