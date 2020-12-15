package com.lti.resttemplates;

import org.springframework.http.HttpStatus;

import com.lti.model.LoginUserData;

public class LoginRestTemplate {
	private String status;
	private String message;
	private HttpStatus statusCode;
	private LoginUserData loginUserData;

	public LoginRestTemplate(String status, String message, HttpStatus statusCode, LoginUserData loginUserData) {
		super();
		this.status = status;
		this.message = message;
		this.statusCode = statusCode;
		this.loginUserData = loginUserData;
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

	public LoginUserData getLoginUserData() {
		return loginUserData;
	}

	public void setLoginUserData(LoginUserData loginUserData) {
		this.loginUserData = loginUserData;
	}

	@Override
	public String toString() {
		return "LoginRestTemplate [status=" + status + ", message=" + message + ", statusCode=" + statusCode
				+ ", loginUserData=" + loginUserData + "]";
	}
}
