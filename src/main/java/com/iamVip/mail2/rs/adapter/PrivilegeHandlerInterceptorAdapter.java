/**
 * 
 */
package com.iamVip.mail2.rs.adapter;

import java.io.IOException;
import java.util.Enumeration;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.iamVip.mail2.rs.c.IAPP;
import com.iamVip.mail2.rs.util.ConstUtil;
import com.iamVip.mail2.rs.util.DTUtil;

/**
 * @author Colin
 */
public class PrivilegeHandlerInterceptorAdapter extends HandlerInterceptorAdapter implements IAPP {

	private String _package = ConstUtil.getControlPackage();
	private String webPackage = ConstUtil.getWebViewPackage();

	private String sessionKey = ConstUtil.getControlSessionKey();
	private String webSessionKey = ConstUtil.getWebViewSessionKey();

	private String noSessionTo = ConstUtil.getControlNoSessionTo();
	private String webNoSessionTo = ConstUtil.getWebViewNoSessionTo();

	/**
	 * 
	 */
	public PrivilegeHandlerInterceptorAdapter() {
	}

	/*
	 * (non-Javadoc)
	 * @see org.springframework.web.servlet.handler.HandlerInterceptorAdapter# preHandle(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse, java.lang.Object)
	 */
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

		// ** 匹配到需要拦截的 URL
		if (handler instanceof HandlerMethod) {

			HandlerMethod method = (HandlerMethod) handler;
			String className = method.getBean().getClass().getName();
			String methodName = method.getMethod().getName();

			StringBuffer buf = new StringBuffer();
			// buf.append("RequestURI " + request.getRequestURI() + LINE);
			buf.append("RequestURL " + request.getRequestURL() + LINE);
			buf.append("Execute " + className + "." + methodName + LINE);

			Enumeration<String> names = request.getParameterNames();
			int c = 0;
			while (names.hasMoreElements()) {
				c++;
				String key = names.nextElement();
				String value = request.getParameter(key);
				buf.append(String.format("%s --> %s \r\n", key, value));
			}
			buf.append("Parameter Length " + c + "\r\n");

			String userAgent = request.getHeader("User-Agent");
			buf.append("userAgent ? " + userAgent + "\r\n");
			String from = "Other";
			if (userAgent != null) {
				if (userAgent.contains("Android")) {
					from = "Android";
				}
				if (userAgent.contains("iPhone")) {
					from = "iPhone";
				}
			}
			buf.append("is from phone ? " + from + "\r\n");

			Locale locale = request.getLocale();
			if (locale != null) {
				buf.append("Language ? " + locale.getLanguage() + "  Country ? " + locale.getCountry() + "\r\n");
			}

			buf.append("Request at: " + DTUtil.nowDT());

			System.out.println(buf);



			/// *Controller.java / *Request.java 中方法以 ViewPath/NoLimit 结尾的均可通过 ///
			if (methodName.endsWith("ViewPath") || methodName.endsWith("NoLimit")) {
				// System.out.println("The method is end with 'ViewPath' or 'NoLimit'.");
				return true;
			}



			if (className.startsWith(_package)) {
				return this.controlURL(request, response, sessionKey, noSessionTo);
			}
			else if (className.startsWith(webPackage)) {
				return this.controlURL(request, response, webSessionKey, webNoSessionTo);
			}
			else {
				return true;
			}


		}

		return super.preHandle(request, response, handler);
	}

	/**
	 * @param request
	 * @param response
	 * @return
	 * @throws IOException
	 */
	private boolean controlURL(HttpServletRequest request, HttpServletResponse response, String sessionKey, String noSessionTo) throws IOException {

		String contextPath = request.getContextPath() + noSessionTo;

		HttpSession httpSession = request.getSession(false);
		// ** 未登录则不通过
		if (httpSession == null) {
			System.out.println("111111111111111111111111111111111111  httpSession == null");
			response.sendRedirect(contextPath);
			return false;
		}

		// ** 判断是否登录
		Object onlineObj = httpSession.getAttribute(sessionKey);
		if (onlineObj == null) {
			System.out.println("222222222222222222222222222222222222 onlineObj == null");
			response.sendRedirect(contextPath);
			return false;
		}

		// ** 最后一次访问时间大于 30 分钟
		long lastAccessed = httpSession.getLastAccessedTime();
		if (System.currentTimeMillis() - lastAccessed > 30 * 60 * 1000) {
			System.out.println("333333333333333333333333333333333333 lastAccessed > 30 * 60 * 1000");
			response.sendRedirect(contextPath);
			return false;
		}

		return true;
	}

	/*
	 * (non-Javadoc)
	 * @see org.springframework.web.servlet.handler.HandlerInterceptorAdapter# postHandle(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse, java.lang.Object, org.springframework.web.servlet.ModelAndView)
	 */
	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {

		super.postHandle(request, response, handler, modelAndView);
	}

	/*
	 * (non-Javadoc)
	 * @see org.springframework.web.servlet.handler.HandlerInterceptorAdapter# afterCompletion(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse, java.lang.Object, java.lang.Exception)
	 */
	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {

		super.afterCompletion(request, response, handler, ex);
	}

	/*
	 * (non-Javadoc)
	 * @see org.springframework.web.servlet.handler.HandlerInterceptorAdapter# afterConcurrentHandlingStarted(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse, java.lang.Object)
	 */
	@Override
	public void afterConcurrentHandlingStarted(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

		super.afterConcurrentHandlingStarted(request, response, handler);
	}

}
