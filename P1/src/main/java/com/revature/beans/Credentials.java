package com.revature.beans;

public class Credentials {
	
	public Credentials() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	private int id;
	private int userId;
	private String username;
	private String password;
	
	public Credentials(int id, int userId, String username, String password) {
		super();
		this.id = id;
		this.userId = userId;
		this.username = username;
		this.password = password;
	}
	
	public Credentials(String username, String password) {
		super();
		this.id = 0;
		this.userId = 0;
		this.username = username;
		this.password = password;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
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
	public boolean equals(Object arg0) {
		// TODO Auto-generated method stub
		return super.equals(arg0);
	}

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return super.toString();
	}
	
	
}
