package com.kidzoo.customexception;

public class KidZooException extends RuntimeException {

	private static final long serialVersionUID = 1L;
	
	private String errorCode;
	private String errorMessage;
	private String errorDetails;
	private Object errorCause;

	public KidZooException() {
	}

	public KidZooException(String errorCode, String errorMessage, String errorDetails, Object errorCause) {
		super(errorMessage);
		this.errorCode = errorCode;
		this.errorMessage = errorMessage;
		this.errorDetails = errorDetails;
		this.errorCause = errorCause;
	}

	public String getErrorCode() {
		return errorCode;
	}

	public void setErrorCode(String errorCode) {
		this.errorCode = errorCode;
	}

	public String getErrorMessage() {
		return errorMessage;
	}

	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}

	public String getErrorDetails() {
		return errorDetails;
	}

	public void setErrorDetails(String errorDetails) {
		this.errorDetails = errorDetails;
	}

	public Object getErrorCause() {
		return errorCause;
	}

	public void setErrorCause(Object errorCause) {
		this.errorCause = errorCause;
	}

}
