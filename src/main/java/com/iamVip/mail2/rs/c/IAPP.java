/**
 * 
 */
package com.iamVip.mail2.rs.c;

import com.google.gson.Gson;

/**
 * @author Colin
 */
public interface IAPP {

	String LINE = "\r\n";

	String JSON_KEY = "sign";
	String JSON_RESULT = "result";
	String JSON_MSG = "message";

	String DEFAULT_IMAGE_SUFFIX = "jpg";
	int DEFAULT_PORTRAIT_SIZE = 40;
	
	Gson gson = new Gson();

}
