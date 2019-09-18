package com.revature.beans;

public class User {
	//CONSTRUCTORS
	public User() {
		super();
	}
	public User(int user_id, String username, String password) {
		this();
		this.user_id = user_id;
		this.username = username;
		this.password = password;
	}
	//VARIABLES
	private int user_id;	
	private String username;
	private String password;
	//METHODS
	public int getUser_id() {
		return user_id;
	}
	public void setUser_id(int user_id) {
		this.user_id = user_id;
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
	@Override
	public String toString() {
		return "User: USER_ID = " + user_id + ", USERNAME = " + username + ", PASSWORD = " + password;
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
		if (user_id != other.user_id)
			return false;
		return true;
	}
	*/
}
