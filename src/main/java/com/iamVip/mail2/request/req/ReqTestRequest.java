/**
 * 
 */
package com.iamVip.mail2.request.req;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author Colin
 */
@Controller
@RequestMapping(value = { "req/test" })
public class ReqTestRequest extends __ReqRequest {

	/**
	 * 
	 */
	public ReqTestRequest() {
	}

	@ResponseBody
	@RequestMapping(value = { "date/time" })
	public Map<String, Object> datetimeNoLimit(HttpServletRequest request) throws Exception {
		return super.datetime(request);
	}

}
