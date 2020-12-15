package com.lti.model;

public class Status {
	
	private int user_id;
	private String action;
	
	public int getUser_id() {
		return user_id;
	}
	public void setUser_id(int user_id) {
		this.user_id = user_id;
	}
	public String getAction() {
		return action;
	}
	public void setAction(String action) {
		this.action = action;
	}
	@Override
	public String toString() {
		return "Status [user_id=" + user_id + ", action=" + action + "]";
	}
	public Status(int user_id, String action) {
		super();
		this.user_id = user_id;
		this.action = action;
	}
	public Status(){}
	
}
