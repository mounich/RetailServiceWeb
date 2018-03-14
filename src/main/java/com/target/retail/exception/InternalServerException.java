package com.target.retail.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR, reason = "Internal  Server Error Exception")
public class InternalServerException extends Exception {

	private static final long serialVersionUID = -7304402996287945488L;
	private int errorCode;
	private String errorMsg;

	public InternalServerException() {
	}

	public InternalServerException(int errorCode,String errorMsg) {
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