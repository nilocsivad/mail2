/**
 * 
 */
package com.iamVip.mail2.rs.util;

/**
 * @author Colin
 */
public class EmptyUtil {

	/**
	 * 
	 */
	public EmptyUtil() {
	}

	public static boolean notEmpty(String txt) {
		return txt != null && !"".equals(txt);
	}

}
