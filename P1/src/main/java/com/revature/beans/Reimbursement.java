package com.revature.beans;

public class Reimbursement {
	//VARIABLES
	private int reimbursement_id;
	private int employee_id;
	private double amount;
	private int pad;
	//CONSTRUCTORS
	public Reimbursement() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Reimbursement(int reimbursement_id, int employee_id, double amount, int pad) {
		super();
		this.reimbursement_id = reimbursement_id;
		this.employee_id = employee_id;
		this.amount = amount;
		this.pad = pad;
	}
	//METHODS
	public int getReimbursement_id() {
		return reimbursement_id;
	}
	public void setReimbursement_id(int reimbursement_id) {
		this.reimbursement_id = reimbursement_id;
	}
	public int getEmployee_id() {
		return employee_id;
	}
	public void setEmployee_id(int employee_id) {
		this.employee_id = employee_id;
	}
	public double getAmount() {
		return amount;	
	}
	public void setAmount(double amount) {
		this.amount = amount;
	}
	public int getPad() {
		return pad;
	}
	public void setPad(int pad) {
		this.pad = pad;
	}
	@Override
	public String toString() {
		return "Transaction: REIMBURSEMENT_ID = " + reimbursement_id + ", 	EMPLOYEE_ID = " + employee_id + ", AMOUNT = " + amount + ", PAD = " + pad;
	}	
}