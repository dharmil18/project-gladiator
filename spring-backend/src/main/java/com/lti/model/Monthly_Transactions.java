package com.lti.model;

import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;

@Entity
public class Monthly_Transactions {
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "monthly_Txn_generator")
	@SequenceGenerator(name = "monthly_Txn_generator", sequenceName = "monthly_transactions_sequence", allocationSize = 1)
	private int mtxn_id;

	private int transaction_id;
	private double amount;
	private LocalDate mtxn_date;
	private String m_status;

	public Monthly_Transactions() {

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

	@Override
	public String toString() {
		return "Monthly_Transactions [mtxn_id=" + mtxn_id + ", transaction_id=" + transaction_id + ", amount=" + amount
				+ ", mtxn_date=" + mtxn_date + ", status=" + m_status + "]";
	}
}
