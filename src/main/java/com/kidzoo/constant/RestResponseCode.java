package com.kidzoo.constant;

public enum RestResponseCode {

	SUCCESS("200");

	private String code;

	private RestResponseCode(String code) {
		this.code = code;
	}

	public String getCode() {
		return code;
	}
}
