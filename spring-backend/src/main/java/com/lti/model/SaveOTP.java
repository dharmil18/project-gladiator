package com.lti.model;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class SaveOTP {
	@Id
	private String email_id;
	private int otp;

	public SaveOTP() {

	}

	public String getEmail_id() {
		return email_id;
	}

	public void setEmail_id(String email_id) {
		this.email_id = email_id;
	}

	public int getOtp() {
		return otp;
	}

	public void setOtp(int otp) {
		this.otp = otp;
	}

	@Override
	public String toString() {
		return "OTPService [email_id=" + email_id + ", Otp=" + otp + "]";
	}
}
