package com.kidzoo.bean;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class KidZooRestResponse {

	private String status;
	private String statusCode;
	private String message;
	private List<Toy> data;

	public KidZooRestResponse() {
	}

	public KidZooRestResponse(String status, String statusCode, String message, List<Toy> data) {
		this.status = status;
		this.statusCode = statusCode;
		this.message = message;
		this.data = data;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getStatusCode() {
		return statusCode;
	}

	public void setStatusCode(String statusCode) {
		this.statusCode = statusCode;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public List<Toy> getData() {
		return data;
	}

	public void setData(List<Toy> data) {
		this.data = data;
	}

}
