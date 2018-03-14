package com.target.retail.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST, reason = "Bad Request Exception")
public class BadRequestException extends Exception {

	private static final long serialVersionUID = 321609620348197565L;
	private int errorCode;
	private String errorMsg;

	public BadRequestException() {
	}

	public BadRequestException(int errorCode,String errorMsg) {
		super(errorMsg);
		this.errorMsg = errorMsg;
		this.errorCode= errorCode;
	}

	public String getErrorMsg() {
		return errorMsg;
	}

	public void setErrorMsg(String errorMsg) {
		this.errorMsg = errorMsg;
	}
	
	public int getErrorCode() {
		return errorCode;
	}

	public void setErrorCode(int errorCode) {
		this.errorCode = errorCode;
	}
}