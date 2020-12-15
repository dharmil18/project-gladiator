package com.lti.model;

import org.springframework.http.HttpStatus;

public class StandardRestTemplate {
	private String status;
	private String message;
	private HttpStatus statusCode;
	
	public StandardRestTemplate(String status, String message, HttpStatus statusCode) {
		
		this.status = status;
		this.message = message;
		this.statusCode = statusCode;
	}
	
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public HttpStatus getStatusCode() {
		return statusCode;
	}
	public void setStatusCode(HttpStatus statusCode) {
		this.statusCode = statusCode;
	}
	@Override
	public String toString() {
		return "EditUserRestTemplate [status=" + status + ", message=" + message + ", statusCode=" + statusCode + "]";
	}
	

}
