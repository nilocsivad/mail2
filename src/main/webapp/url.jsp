<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page language="java" import="com.iamVip.mail2.rs.util.ConstUtil"%>

<%!//////////////////////////////////////////////////
	//////////
	//////////
	//////////
	String URL = null;
	String HTP = null;
	//////////
	//////////
	//////////
	//////////////////////////////////////////////////%>
<%
	URL = request.getAttribute("URL") == null ? null : request.getAttribute("URL") + "";
	if (URL == null) {

		int port = request.getServerPort();
		HTP = request.getScheme() + "://" + request.getServerName() + (port == 80 ? "" : ":" + port);


		// URL = URL.endsWith( "/" ) ? URL.substring( 0, URL.length() - 1 ) : URL;
		String path = request.getContextPath();
		URL = /* URL + */ path;

		request.setAttribute("URL", URL);
		request.setAttribute("HTP", HTP);
		request.setAttribute("RES", HTP + "/" + ConstUtil.getResourcePrefix());
	}
%>