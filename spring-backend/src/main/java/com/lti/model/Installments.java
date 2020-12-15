package com.lti.model;

import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Installments {
	@Id
	private int mtxn_id;

	private int transaction_id;
	private String product_name;
	private double amount;
	private LocalDate mtxn_date;
	private String m_status;
	
	public Installments() {
		
	}
	
	public int getMtxn_id() {
		return mtxn_id;
	}
	public void setMtxn_id(int mtxn_id) {
		this.mtxn_id = mtxn_id;
	}
	public int getTransaction_id() {
		return transaction_id;
	}
	public void setTransaction_id(int transaction_id) {
		this.transaction_id = transaction_id;
	}
	public double getAmount() {
		return amount;
	}
	public void setAmount(double amount) {
		this.amount = amount;
	}
	public LocalDate getMtxn_date() {
		return mtxn_date;
	}
	public void setMtxn_date(LocalDate mtxn_date) {
		this.mtxn_date = mtxn_date;
	}
	public String getM_status() {
		return m_status;
	}
	public void setM_status(String m_status) {
		this.m_status = m_status;
	}
	public String getProduct_name() {
		return product_name;
	}
	public void setProduct_name(String product_name) {
		this.product_name = product_name;
	}

	@Override
	public String toString() {
		return "Installments [mtxn_id=" + mtxn_id + ", transaction_id=" + transaction_id + ", amount=" + amount
				+ ", mtxn_date=" + mtxn_date + ", m_status=" + m_status + ", product_name=" + product_name + "]";
	}
}
