package com.target.retail.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND, reason = "Data not Found Exception")
public class DataNotFoundException extends Exception {

	private static final long serialVersionUID = 1266361677362574589L;
	private int errorCode;
	private String errorMsg;

	public DataNotFoundException() {
	}

	public DataNotFoundException(int errorCode,String errorMsg) {
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