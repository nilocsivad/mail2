/**
 * 
 */
package com.iamVip.mail2.request.req;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.iamVip.mail2.rs.util.DTUtil;
import com.iamVip.mail2.rs.util.MailSender;
import com.iamVip.mail2.rs.util.MapUtil;

/**
 * @author Colin
 */
@Controller
@RequestMapping(value = { "req/mail" })
public class ReqMailRequest extends __ReqRequest {

	@Autowired
	private MailSender mailSender;

	/**
	 * 
	 */
	public ReqMailRequest() {
	}

	@ResponseBody
	@RequestMapping(value = { "send/{subject}/{text}" })
	public Map<String, Object> mailNoLimit(String to, @PathVariable String subject, @PathVariable String text, HttpServletRequest request) throws Exception {
		mailSender.sendTextMail(subject, new String[] { to }, text);
		return MapUtil.mapThem(new String[] { "to", "subject", "text" }, to, subject, text);
	}

	@ResponseBody
	@RequestMapping(value = { "send/html/{subject}" })
	public Map<String, Object> mailWithHtmlNoLimit(String to, @PathVariable String subject, HttpServletRequest request) throws Exception {

		Map<String, Object> map = MapUtil.mapThem(new String[] { "to", "subject", "timeMillis", "datetime" }, to, subject, System.currentTimeMillis(), DTUtil.nowDT());
		mailSender.sendHtmlMail(subject, new String[] { to }, map);

		return map;
	}

}
