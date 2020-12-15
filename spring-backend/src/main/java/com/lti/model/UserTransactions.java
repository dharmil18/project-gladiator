package com.lti.model;

import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class UserTransactions {
	@Id
	private long transaction_id;
	private int product_id;
	private String product_name;
	private int product_cost;
	private double monthly_installment;
	private int tenure;
	private LocalDate transaction_date;
	private String status;
	private double processing_fee;

	public UserTransactions() {
		super();
	}

	public long getTransaction_id() {
		return transaction_id;
	}

	public void setTransaction_id(long transaction_id) {
		this.transaction_id = transaction_id;
	}

	public int getProduct_id() {
		return product_id;
	}

	public void setProduct_id(int product_id) {
		this.product_id = product_id;
	}

	public String getProduct_name() {
		return product_name;
	}

	public void setProduct_name(String product_name) {
		this.product_name = product_name;
	}

	public int getProduct_cost() {
		return product_cost;
	}

	public void setProduct_cost(int product_cost) {
		this.product_cost = product_cost;
	}

	public double getMonthly_installment() {
		return monthly_installment;
	}

	public void setMonthly_installment(double monthly_installment) {
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

	public void setProcessing_fee(double processing_fee) {
		this.processing_fee = processing_fee;
	}

	@Override
	public String toString() {
		return "UserTransactions [transaction_id=" + transaction_id + ", product_id=" + product_id + ", product_name="
				+ product_name + ", product_cost=" + product_cost + ", monthly_installment=" + monthly_installment
				+ ", tenure=" + tenure + ", transaction_date=" + transaction_date + ", status=" + status
				+ ", processing_fee=" + processing_fee + "]";
	}
}
