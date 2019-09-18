package com.revature.beans;

public class Transaction {
	//CONSTRUCTORS
	public Transaction() {
		super();
	}
	public Transaction(int t_id, int ba_id, double amount) {
		this();
		this.t_id = t_id;
		this.ba_id = ba_id;
		this.amount = amount;
	}
	//VARIABLES
	private int t_id;	
	private int ba_id;
	private double amount;
	//METHODS
	public int getT_id() {
		return t_id;
	}
	public void setT_id(int t_id) {
		this.t_id = t_id;
	}
	public int getBa_id() {
		return ba_id;
	}
	public void setBa_id(int ba_id) {
		this.ba_id = ba_id;
	}
	public double getAmount() {
		return amount;
	}
	public void setAmount(double amount) {
		this.amount = amount;
	}
	@Override
	public String toString() {
		return "Transaction: TRANSACTION_ID = " + t_id + ", BANK_ACCOUNT_ID = " + ba_id + ", AMOUNT = " + amount;
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
		if (t_id != other.t_id)
			return false;
		return true;
	}
	*/
}
