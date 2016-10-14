/**
 * 
 */
package com.iamVip.mail2.rs.util;

/**
 * @author Colin
 */
public class ConstUtil {

	private static String CONTROL_PACKAGE = "";
	private static String CONTROL_SESSION_KEY = "";
	private static String CONTROL_NO_SESSION_TO = "";
	private static String WEB_VIEW_PACKAGE = "";
	private static String WEB_VIEW_SESSION_KEY = "";
	private static String WEB_VIEW_NO_SESSION_TO = "";

	private static boolean ON_SERVER = false; /// true:运行在服务器 false:测试环境 ///
	private static int VALID_CODE_SECOND = 120; /// 验证码重新发送时间间隔 ///
	private static String RESOURCEFOLDER = null; /// 资源文件夹 ///
	private static String RESOURCEPREFIX = null; /// 资源文件夹对外访问前缀,对应 Tomcat <Context path="" /> ///

	public ConstUtil(String controlPackage, String controlSessionKey, String controlNoSessionTo, String viewPackage, String viewSessionKey, String viewNoSessionTo, Integer validCodeSecond, String resourceFolder, String resourcePrefix, Boolean onServer) {

		CONTROL_PACKAGE = controlPackage;
		CONTROL_SESSION_KEY = controlSessionKey;
		CONTROL_NO_SESSION_TO = controlNoSessionTo;
		WEB_VIEW_PACKAGE = viewPackage;
		WEB_VIEW_SESSION_KEY = viewSessionKey;
		WEB_VIEW_NO_SESSION_TO = viewNoSessionTo;

		VALID_CODE_SECOND = validCodeSecond;
		ON_SERVER = onServer;
		RESOURCEFOLDER = resourceFolder;
		RESOURCEPREFIX = resourcePrefix;
	}

	public static String getControlPackage() {
		return CONTROL_PACKAGE;
	}

	public static String getControlSessionKey() {
		return CONTROL_SESSION_KEY;
	}

	public static String getControlNoSessionTo() {
		return CONTROL_NO_SESSION_TO;
	}

	public static String getWebViewPackage() {
		return WEB_VIEW_PACKAGE;
	}

	public static String getWebViewSessionKey() {
		return WEB_VIEW_SESSION_KEY;
	}

	public static String getWebViewNoSessionTo() {
		return WEB_VIEW_NO_SESSION_TO;
	}

	/**
	 * @return 验证码重新发送时间间隔
	 */
	public static int getValidCodeSecond() {
		return VALID_CODE_SECOND;
	}

	/**
	 * @return true:运行在服务器 false:测试环境
	 */
	public static boolean isOnServer() {
		return ON_SERVER;
	}

	/**
	 * @return 服务器本地资源文件夹
	 */
	public static String getResourceFolder() {
		return RESOURCEFOLDER;
	}

	/**
	 * @return 服务器本地资源文件夹对外访问前缀,对应 Tomcat <Context path="" />
	 */
	public static String getResourcePrefix() {
		return RESOURCEPREFIX;
	}

}
