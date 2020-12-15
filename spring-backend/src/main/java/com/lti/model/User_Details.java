package com.lti.model;

import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;

@Entity
public class User_Details {
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "userdetails_generator")
	@SequenceGenerator(name = "userdetails_generator", sequenceName = "user_details_sequence", allocationSize = 1)
	private int user_detail_id;

	private String bank_name;
	private String account_no;
	private String ifsc_code;
	private String address_line1;
	private String address_line2;
	private String city;
	private String state;
	private String zipcode;
	private LocalDate joined_date;
	
	private int user_id;

	public User_Details() {

	}

	public int getUser_id() {
		return user_id;
	}

	public void setUser_id(int user_id) {
		this.user_id = user_id;
	}

	public int getUser_detail_id() {
		return user_detail_id;
	}

	public void setUser_detail_id(int user_detail_id) {
		this.user_detail_id = user_detail_id;
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

	public LocalDate getJoined_date() {
		return joined_date;
	}

	public void setJoined_date(LocalDate joined_date) {
		this.joined_date = joined_date;
	}

	@Override
	public String toString() {
		return "User_Details [user_detail_id=" + user_detail_id + ", user_id=" + user_id + ", bank_name=" + bank_name
				+ ", account_no=" + account_no + ", ifsc_code=" + ifsc_code + ", address_line1=" + address_line1
				+ ", address_line2=" + address_line2 + ", city=" + city + ", state=" + state + ", zipcode=" + zipcode
				+ ", joined_date=" + joined_date + "]";
	}
}
