/**
 * 
 */
package com.iamVip.mail2.rs.util;

/**
 * @author Colin
 */
public class StringHtmlUtil {

	/**
	 * 
	 */
	public StringHtmlUtil() {
	}

	public static String toHtml(String content, String enc) {
		if (EmptyUtil.notEmpty(content)) {
			content = content.replace("<", "&lt;").replace(">", "&gt;").replace("\"", "&quot;");
		}
		return content;
	}

}
