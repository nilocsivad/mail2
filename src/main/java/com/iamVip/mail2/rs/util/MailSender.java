package com.iamVip.mail2.rs.util;

import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSenderImpl;

/**
 * @author Colin
 */
public class MailSender extends JavaMailSenderImpl {

	private static String[] USERS, PWDS;

	public MailSender(String[] users, String[] pwds) {
		USERS = users;
		PWDS = pwds;
	}

	public void sendTextMail(String subject, String text, String[] to) {

		int index = (int) System.currentTimeMillis() % USERS.length;

		SimpleMailMessage mail = new SimpleMailMessage();
		mail.setTo(to);
		mail.setFrom(USERS[index]);
		mail.setSubject(subject + "-" + DTUtil.nowTime());
		mail.setText(text + " --- " + DTUtil.nowDT());

		this.setUsername(USERS[index]);
		this.setPassword(PWDS[index]);

		this.send(mail);

	}

}
