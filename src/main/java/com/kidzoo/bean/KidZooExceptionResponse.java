package com.kidzoo.bean;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class KidZooExceptionResponse {

	private String errorCode;
	private String errorMessage;
	private String errorDetails;
	private Object errorCause;

	public KidZooExceptionResponse() {
	}

	public KidZooExceptionResponse(String errorCode, String errorMessage, String errorDetails, Object errorCause) {
		super();
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
