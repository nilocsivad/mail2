/**
 * 
 */
package com.iamVip.mail2.request;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.iamVip.mail2.rs.c.IAPP;
import com.iamVip.mail2.rs.util.DTUtil;
import com.iamVip.mail2.rs.util.MapUtil;

/**
 * @author Colin
 */
public class __Request implements IAPP {

	/**
	 * 
	 */
	public __Request() {
	}

	protected Map<String, Object> datetime(HttpServletRequest request) {
		return MapUtil.mapThem(new String[] { "datetime", "url" }, DTUtil.nowDT(), request.getRequestURL());
	}

	/**
	 * @param find
	 */
	protected String getSuffixOfURL(HttpServletRequest request, String find) {
		String uri = request.getRequestURI();
		if (uri.contains(";")) {
			uri = uri.substring(0, uri.indexOf(";"));
		}
		return uri.substring(uri.indexOf(find) + find.length());
	}

}
