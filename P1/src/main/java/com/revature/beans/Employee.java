package com.revature.beans;

public class Employee {
	//VARIABLES
	private int employee_id;
	private String username;
	private String password;
	private int manager_id;
	//CONSTRUCTORS
	public Employee() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Employee(int emeployee_id, String username, String password, int manager_id) {
		super();
		this.employee_id = employee_id;
		this.username = username;
		this.password = password;
		this.manager_id = manager_id;
	}
	//METHODS
	public int getEmployee_id() {
		return employee_id;
	}
	public void setEmployee_id(int emeloyee_id) {
		this.employee_id = employee_id;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public int getManager_id() {
		return manager_id;
	}
	public void setManager_id(int manager_id) {
		this.manager_id = manager_id;
	}
	@Override
	public String toString() {
		return "Employee: EMPLOYEE_ID = " + employee_id + ", USERNAME = " + username + ", PASSWORD = " + password + ", MANAGER_ID = " + manager_id;
	}	
}
