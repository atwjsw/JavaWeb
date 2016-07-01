package com.netease.course.meta;

/**
 * @author: atwjsw
 * @Description: Data container for asynchronized api return value.
 * @Date: Apr 20, 2016 3:25:37 PM
 */
public class Result {

	int code;
	String message;
	boolean result;

	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public boolean getResult() {
		return result;
	}

	public void setResult(boolean result) {
		this.result = result;
	}

}
