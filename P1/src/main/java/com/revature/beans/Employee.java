package com.revature.beans;

public class Employee {
	//VARIABLES
	private int employee_id = 0;
	private String username;
	private String password;
	private int manager_id = 0;
	private int option = 0;
	private boolean isEmmMan = false;
	//CONSTRUCTORS
	public Employee() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Employee(int employee_id, String username, String password, int manager_id) {
		super();
		this.employee_id = employee_id;
		this.username = username;
		this.password = password;
		this.manager_id = manager_id;
	}
	public Employee(String username, String password) {
		super();
		this.username = username;
		this.password = password;
	}
	
	public Employee(int employee_id, String username, String password, int manager_id, int option, boolean isEmmMan) {
		super();
		this.employee_id = employee_id;
		this.username = username;
		this.password = password;
		this.manager_id = manager_id;
		this.option = option;
		this.isEmmMan = isEmmMan;
	}
	public Employee(int employee_id, String username, int manager_id) {
		super();
		this.employee_id = employee_id;
		this.username = username;
		this.manager_id = manager_id;
	}
	//METHODS
	public int getEmployee_id() {
		return employee_id;
	}
	public void setEmployee_id(int employee_id) {
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
	public int getOption() {
		return option;
	}
	public void setOption(int option) {
		this.option = option;
	}
	public boolean isEmmMan() {
		return isEmmMan;
	}
	public void setEmmMan(boolean isEmmMan) {
		this.isEmmMan = isEmmMan;
	}
	@Override
	public String toString() {
		return "Employee: EMPLOYEE_ID = " + employee_id + ", USERNAME = " + username + ", PASSWORD = " + password + ", MANAGER_ID = " + manager_id;
	}	
}
