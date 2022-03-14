package com.kidzoo.constant;

public enum ErrorCode {
	
	GENERIC_EXCEPTION("ERROR-1001"), REST_CALL_EXCEPTION("ERROR-1002");

	private String errorCode;

	private ErrorCode(String errorCode) {
		this.errorCode = errorCode;
	}

	public String getErrorCode() {
		return errorCode;
	}

}
