package com.iamVip.mail2.rs.util;

import java.util.Properties;

import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSenderImpl;

/**
 * @author Colin
 */
public class MailUtil {

	private static String SENDHOST; /// 发送端域名 e.g. smtp.163.com ///
	private static String[] USERS, PWDS;
	private static boolean SMTPAUTH = true; /// 将这个参数设为true 让服务器认证用户名和密码是否正确 ///
	private static long TIMEOUT = 1000 * 60 * 6; /// 超时一分钟 ///

	public MailUtil(String sendHost, Boolean smtpAuth, Long timeout, String[] users, String[] pwds) {
		SENDHOST = sendHost;
		SMTPAUTH = smtpAuth;
		TIMEOUT = timeout;
		USERS = users;
		PWDS = pwds;
	}

	public static void sendTextMail(String subject, String text, String[] to) {

		int index = (int) System.currentTimeMillis() % USERS.length;

		SimpleMailMessage mail = new SimpleMailMessage();
		mail.setTo(to);
		mail.setFrom(USERS[index]);
		mail.setSubject(subject + "-" + DTUtil.nowTime());
		mail.setText(text + " --- " + DTUtil.nowDT());



		Properties props = new Properties();
		props.put("mail.smtp.auth", SMTPAUTH);
		props.put("mail.smtp.timeout", TIMEOUT);

		JavaMailSenderImpl sender = new JavaMailSenderImpl();
		sender.setHost(SENDHOST);
		sender.setUsername(USERS[index]);
		sender.setPassword(PWDS[index]);
		sender.setJavaMailProperties(props);

		sender.send(mail);

	}

}
