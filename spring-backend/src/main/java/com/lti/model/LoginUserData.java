package com.lti.model;

public class LoginUserData {
	private int user_id;
	private String firstName;
	private String lastName;
	private String user_name;
	private String role;

	public LoginUserData(int user_id, String firstName, String lastName, String user_name, String role) {
		super();
		this.user_id = user_id;
		this.firstName = firstName;
		this.lastName = lastName;
		this.user_name = user_name;
		this.role = role;
	}

	public int getUser_id() {
		return user_id;
	}

	public void setUser_id(int user_id) {
		this.user_id = user_id;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getUser_name() {
		return user_name;
	}

	public void setUser_name(String user_name) {
		this.user_name = user_name;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	@Override
	public String toString() {
		return "LoginUserData [user_id=" + user_id + ", firstName=" + firstName + ", lastName=" + lastName
				+ ", user_name=" + user_name + ", role=" + role + "]";
	}
}
