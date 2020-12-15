package com.lti.model;

public class Login {
	private String user_name;
	private String password;

	public Login() {

	}

	public String getUser_name() {
		return user_name;
	}

	public void setUser_name(String user_name) {
		this.user_name = user_name;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@Override
	public String toString() {
		return "Login [user_name=" + user_name + ", password=" + password + "]";
	}

}
