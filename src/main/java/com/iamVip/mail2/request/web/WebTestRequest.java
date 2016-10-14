/**
 * 
 */
package com.iamVip.mail2.request.web;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author Colin
 */
@Controller
@RequestMapping(value = { "web/test" })
public class WebTestRequest extends __WebRequest {

	/**
	 * 
	 */
	public WebTestRequest() {
	}

	@ResponseBody
	@RequestMapping(value = { "date/time" })
	public Map<String, Object> datetimeNoLimit(HttpServletRequest request) throws Exception {
		return super.datetime(request);
	}

}
