package com.lti.model;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Registration {
	
	private String first_name;
	private String last_name;
	@Id
	private String email_id;
	private String phone_number;
	private String user_name;
	private String password_hash;
	private String bank_name;
	private String account_no;
	private String ifsc_code;
	private String address_line1;
	private String address_line2;
	private String city;
	private String state;
	private String zipcode;
	private String card_type;
	
	public Registration() {
		super();
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

	public String getEmail_id() {
		return email_id;
	}

	public void setEmail_id(String email_id) {
		this.email_id = email_id;
	}

	public String getPhone_number() {
		return phone_number;
	}

	public void setPhone_number(String phone_number) {
		this.phone_number = phone_number;
	}

	public String getUser_name() {
		return user_name;
	}

	public void setUser_name(String user_name) {
		this.user_name = user_name;
	}

	public String getPassword_hash() {
		return password_hash;
	}

	public void setPassword_hash(String password_hash) {
		this.password_hash = password_hash;
	}

	public String getBank_name() {
		return bank_name;
	}

	public void setBank_name(String bank_name) {
		this.bank_name = bank_name;
	}

	public String getAccount_no() {
		return account_no;
	}

	public void setAccount_no(String account_no) {
		this.account_no = account_no;
	}

	public String getIfsc_code() {
		return ifsc_code;
	}

	public void setIfsc_code(String ifsc_code) {
		this.ifsc_code = ifsc_code;
	}

	public String getAddress_line1() {
		return address_line1;
	}

	public void setAddress_line1(String address_line1) {
		this.address_line1 = address_line1;
	}

	public String getAddress_line2() {
		return address_line2;
	}

	public void setAddress_line2(String address_line2) {
		this.address_line2 = address_line2;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getZipcode() {
		return zipcode;
	}

	public void setZipcode(String zipcode) {
		this.zipcode = zipcode;
	}

	public String getCard_type() {
		return card_type;
	}

	public void setCard_type(String card_type) {
		this.card_type = card_type;
	}

	@Override
	public String toString() {
		return "Registration [first_name=" + first_name + ", last_name=" + last_name + ", email_id=" + email_id
				+ ", phone_number=" + phone_number + ", user_name=" + user_name + ", password_hash=" + password_hash
				+ ", bank_name=" + bank_name + ", account_no=" + account_no + ", ifsc_code=" + ifsc_code
				+ ", address_line1=" + address_line1 + ", address_line2=" + address_line2 + ", city=" + city
				+ ", state=" + state + ", zipcode=" + zipcode + ", card_type=" + card_type + "]";
	}
}
