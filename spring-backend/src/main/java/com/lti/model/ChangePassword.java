package com.lti.model;

public class ChangePassword {
	private String email_id;
	private String newPassword;
	
	public ChangePassword() {
		
	}

	public String getEmail_id() {
		return email_id;
	}

	public void setEmail_id(String email_id) {
		this.email_id = email_id;
	}

	public String getNewPassword() {
		return newPassword;
	}

	public void setNewPassword(String newPassword) {
		this.newPassword = newPassword;
	}

	@Override
	public String toString() {
		return "ChangePassword [email_id=" + email_id + ", newPassword=" + newPassword + "]";
	}
}
