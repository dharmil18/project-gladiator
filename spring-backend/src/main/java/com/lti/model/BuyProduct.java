package com.lti.model;

public class BuyProduct {
	private int user_id;
	private int product_id;
	private int tenure;

	public BuyProduct() {

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

	public int getTenure() {
		return tenure;
	}

	public void setTenure(int tenure) {
		this.tenure = tenure;
	}

	@Override
	public String toString() {
		return "BuyProduct [user_id=" + user_id + ", product_id=" + product_id + ", tenure=" + tenure + "]";
	}
}
