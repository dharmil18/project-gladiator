package com.lti.model;

import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;

@Entity
public class Transactions {
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE,  generator = "transaction_generator")
	@SequenceGenerator(name="transaction_generator", sequenceName = "transactions_sequence", allocationSize = 1)
	private int transaction_id;
	
	private int user_id;
	private int product_id;
	private int monthly_installment;
	private int tenure;
	private LocalDate transaction_date;
	private String status;
	private double processing_fee;
	
	public Transactions() {
		
	}

	public int getTransaction_id() {
		return transaction_id;
	}

	public void setTransaction_id(int transaction_id) {
		this.transaction_id = transaction_id;
	}

	public int getUser_id() {
		return user_id;
	}

	public void setUser_id(int user_id) {
		this.user_id = user_id;
	}

	public int getProduct_id() {
		return product_id;
	}

	public void setProduct_id(int product_id) {
		this.product_id = product_id;
	}

	public int getMonthly_installment() {
		return monthly_installment;
	}

	public void setMonthly_installment(int monthly_installment) {
		this.monthly_installment = monthly_installment;
	}

	public int getTenure() {
		return tenure;
	}

	public void setTenure(int tenure) {
		this.tenure = tenure;
	}

	public LocalDate getTransaction_date() {
		return transaction_date;
	}

	public void setTransaction_date(LocalDate transaction_date) {
		this.transaction_date = transaction_date;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public double getProcessing_fee() {
		return processing_fee;
	}

	public void setProcessing_fee(double d) {
		this.processing_fee = d;
	}

	@Override
	public String toString() {
		return "Transactions [transaction_id=" + transaction_id + ", user_id=" + user_id + ", product_id=" + product_id
				+ ", monthly_installment=" + monthly_installment + ", tenure=" + tenure + ", transaction_date="
				+ transaction_date + ", status=" + status + ", processing_fee=" + processing_fee + "]";
	}
}
