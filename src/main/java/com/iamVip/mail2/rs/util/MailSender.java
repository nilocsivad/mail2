package com.iamVip.mail2.rs.util;

import java.util.Map;

import javax.mail.internet.MimeMessage;

import org.apache.velocity.app.VelocityEngine;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.mail.javamail.MimeMessagePreparator;
import org.springframework.ui.velocity.VelocityEngineUtils;

/**
 * @author Colin
 */
public class MailSender extends JavaMailSenderImpl {

	@Autowired
	private VelocityEngine velocityEngine;

	private String[] users, pwds, templates;

	public MailSender(String[] users, String[] pwds, String[] templates) {
		this.users = users;
		this.pwds = pwds;
		this.templates = templates;
	}

	public void sendTextMail(String subject, String[] to, String text) {

		int index = (int) System.currentTimeMillis() % users.length;

		SimpleMailMessage mail = new SimpleMailMessage();
		mail.setTo(to);
		mail.setFrom(users[index]);
		mail.setSubject(subject + "-" + DTUtil.nowTime());
		mail.setText(text + " --- " + DTUtil.nowDT());

		this.setUsername(users[index]);
		this.setPassword(pwds[index]);
		this.send(mail);

	}

	public void sendHtmlMail(String subject, String[] to, Map<String, Object> map) {
		this.sendHtmlMail(subject, to, 0, map);
	}

	/**
	 * @param whichOne 0:注册验证码 1:找回密码验证码 ...其它待定
	 */
	public void sendHtmlMail(String subject, String[] to, int whichOne, Map<String, Object> map) {

		int index = (int) System.currentTimeMillis() % users.length;

		MimeMessagePreparator mail = new MimeMessagePreparator() {
			@Override
			public void prepare(MimeMessage mimeMessage) throws Exception {
				
				String body = VelocityEngineUtils.mergeTemplateIntoString(velocityEngine, templates[whichOne], "UTF-8", map);
				
				MimeMessageHelper message = new MimeMessageHelper(mimeMessage);
				message.setTo(to);
				message.setFrom(users[index]);
				message.setSubject(subject);
				message.setText(body, true);
			}
		};

		this.setUsername(users[index]);
		this.setPassword(pwds[index]);
		this.send(mail);

	}

}
