package com.lti.resttemplates;

import org.springframework.http.HttpStatus;

public class BuyProductRestTemplate {
	private String status;
	private String message;
	private HttpStatus httpStatus;

	public BuyProductRestTemplate(String status, String message, HttpStatus httpStatus) {
		super();
		this.status = status;
		this.message = message;
		this.httpStatus = httpStatus;
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

	public HttpStatus getHttpStatus() {
		return httpStatus;
	}

	public void setHttpStatus(HttpStatus httpStatus) {
		this.httpStatus = httpStatus;
	}

	@Override
	public String toString() {
		return "BuyProductRestTemplate [status=" + status + ", message=" + message + ", httpStatus=" + httpStatus + "]";
	}
}
