package com.lti.model;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class UserDetailsWithName {
	
	@Id
	private int User_Id;
	private String first_name;
	private String last_name;
	private String Bank_name;
	private String Account_No;
	private String Ifsc_Code;
	
	public int getUser_Id() {
		return User_Id;
	}
	public void setUser_Id(int user_Id) {
		User_Id = user_Id;
	}
	
	public String getBank_name() {
		return Bank_name;
	}
	public void setBank_name(String bank_name) {
		Bank_name = bank_name;
	}
	public String getAccount_No() {
		return Account_No;
	}
	public void setAccount_No(String account_No) {
		Account_No = account_No;
	}
	public String getIfsc_Code() {
		return Ifsc_Code;
	}
	public void setIfsc_Code(String ifsc_Code) {
		Ifsc_Code = ifsc_Code;
	}

	public String getFirst_name() {
		return first_name;
	}
	public void setFirst_name(String first_name) {
		this.first_name = first_name;
	}
	public String getLast_name() {
		return last_name;
	}
	public void setLast_name(String last_name) {
		this.last_name = last_name;
	}
	public UserDetailsWithName() {
		
	}
	@Override
	public String toString() {
		return "UserDetailsWithName [User_Id=" + User_Id + ", first_name=" + first_name + ", last_name=" + last_name
				+ ", Bank_name=" + Bank_name + ", Account_No=" + Account_No + ", Ifsc_Code=" + Ifsc_Code + "]";
	}
	public UserDetailsWithName(int user_Id, String first_name, String last_name, String bank_name, String account_No,
			String ifsc_Code) {
		super();
		User_Id = user_Id;
		this.first_name = first_name;
		this.last_name = last_name;
		Bank_name = bank_name;
		Account_No = account_No;
		Ifsc_Code = ifsc_Code;
	}
}
