package com.lti.model;

import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;

@Entity
public class Cards {
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE,  generator = "card_generator")
	@SequenceGenerator(name="card_generator", sequenceName = "cards_sequence", allocationSize = 1)
	private int card_id;
	
	private String card_no;
	private String card_type;
	private LocalDate expiry_date;
	private int limit;
	private String status;
	private LocalDate issue_date;
	private int joining_fee;

	private int user_id;
	
	public Cards() {
		
	}

	public int getCard_id() {
		return card_id;
	}

	public void setCard_id(int card_id) {
		this.card_id = card_id;
	}

	public int getUser_id() {
		return user_id;
	}

	public void setUser_id(int user_id) {
		this.user_id = user_id;
	}

	public String getCard_no() {
		return card_no;
	}

	public void setCard_no(String card_no) {
		this.card_no = card_no;
	}

	public String getCard_type() {
		return card_type;
	}

	public void setCard_type(String card_type) {
		this.card_type = card_type;
	}

	public LocalDate getExpiry_date() {
		return expiry_date;
	}

	public void setExpiry_date(LocalDate expiry_date) {
		this.expiry_date = expiry_date;
	}

	public int getLimit() {
		return limit;
	}

	public void setLimit(int limit) {
		this.limit = limit;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public LocalDate getIssue_date() {
		return issue_date;
	}

	public void setIssue_date(LocalDate issue_date) {
		this.issue_date = issue_date;
	}

	public int getJoining_fee() {
		return joining_fee;
	}

	public void setJoining_fee(int joining_fee) {
		this.joining_fee = joining_fee;
	}

	@Override
	public String toString() {
		return "Cards [card_id=" + card_id + ", user_id=" + user_id + ", card_no=" + card_no + ", card_type="
				+ card_type + ", expiry_date=" + expiry_date + ", limit=" + limit + ", status=" + status
				+ ", issue_date=" + issue_date + ", joining_fee=" + joining_fee + "]";
	}
}

