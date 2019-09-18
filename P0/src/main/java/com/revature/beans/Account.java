package com.revature.beans;

public class Account {
	//CONSTRUCTORS
	public Account() {
		super();
	}
	public Account(int ba_id, int user_id, double balance) {
		this();
		this.ba_id = ba_id;
		this.user_id = user_id;
		this.balance = balance;
	}
	//VARIABLES
	private int ba_id;	
	private int user_id;
	private double balance;
	//METHODS
	public int getBa_id() {
		return ba_id;
	}
	public void setBa_id(int ba_id) {
		this.ba_id = ba_id;
	}
	public int getUser_id() {
		return user_id;
	}
	public void setUser_id(int user_id) {
		this.user_id = user_id;
	}
	public double getBalance() {
		return balance;
	}
	public void setBalance(double balance) {
		this.balance = balance;
	}
	@Override
	public String toString() {
		return "Account: BANK_ACCOUNT_ID = " + ba_id + ", USER_ID = " + user_id + ", BALANCE = " + balance;
	}
	/*
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		User other = (User) obj;
		if (ba_id != other.ba_id)
			return false;
		return true;
	}
	*/
}
