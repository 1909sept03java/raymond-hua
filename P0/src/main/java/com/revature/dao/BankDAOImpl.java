package com.revature.dao;

import java.io.IOException;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.Scanner;

import com.revature.beans.Account;
import com.revature.beans.Transaction;
import com.revature.beans.User;
import com.revature.util.ConnectionUtil;

public class BankDAOImpl implements BankDAO{
	Scanner scanner = new Scanner(System.in);
	
	@Override
	public void LogIn() {
		System.out.println("Please input a username");
		String USERNAME = scanner.nextLine();
		System.out.println("Please input a password");
		String PASSWORD = scanner.nextLine();
		int USER_ID = Validate(USERNAME, PASSWORD);
		Option(USER_ID);
		//System.out.println("Logging in to " + USERNAME);	//when validation is true
		//Custom exception when correct username but incorrect password to re-enter password
	}
	@Override
	public int Validate(String USERNAME, String PASSWORD) {
		User u = null;
		int user_id = -1;
		try {
			Properties prop = new Properties();
			prop.load(ConnectionUtil.class.getClassLoader().getResourceAsStream("connection.properties"));
			String superUsername = prop.getProperty("username");
			String superPassword = prop.getProperty("password");
			if (USERNAME.equals(superUsername) && PASSWORD.equals(superPassword))
				return 0;
		} catch (IOException e) {
			e.printStackTrace();
		}
		try (Connection conn = ConnectionUtil.getConnection()) {
			String sql = "SELECT * FROM USER_ WHERE USERNAME = ? AND PASSWORD = ?";
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, USERNAME);
			pstmt.setString(2, PASSWORD);
			ResultSet rs = pstmt.executeQuery();
			while(rs.next()) {
				user_id = rs.getInt("USER_ID");
				USERNAME = rs.getString("USERNAME");
				PASSWORD = rs.getString("PASSWORD");
				u = new User(user_id, USERNAME, PASSWORD);
			}
			return u.getUser_id();
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (IOException e1) {
			e1.printStackTrace();
		} catch (NullPointerException e2) {
			e2.printStackTrace();
		}
		return user_id;
	}
	////////////////////////////////////////////////////////////////////////////
	@Override
	public void Option(int USER_ID) {
		switch (USER_ID) {
		case 0: SuperOption(); break;
		case -1: System.out.println("Incorrect information"); 
				 System.out.println("Enter 1 if you'ld like to try again, or any key to return to the main menu");
				 char i = scanner.nextLine().charAt(0);
				 if (i == '1')
						LogIn();
				 break;
		default: UserOption(USER_ID); break;
		}
	}

	@Override
	public void SuperOption() {
		char option = '1';
		while(option != '0') {
			System.out.println("Select:\n1) Create user\n2) Update user\n3) Delete user\n4) View user\n0) Logging out");
			option = scanner.nextLine().charAt(0);
			switch (option) {
			case '1': CreateUser(); break;
			case '2': UpdateUser(); break;
			case '3': DeleteUser(); break;
			case '4': ViewUser(); break;
			case '0': System.out.println("Logging out"); break;
			default : System.out.println(option + " is not a valid option\n");
			}	
		}
	}
	
	@Override
	public void UserOption(int USER_ID) {
		char option = '1';
		while(option != '0') {
			System.out.println("Select:\n1) Create an account\n2) View accounts and balances\n3) Delete account, if empty\n4) "
					+ "Deposit/Withdraw\n5) View transaction history\n0) Log out");
			option = scanner.nextLine().charAt(0);
			switch (option) {
			case '1': CreateAccount(USER_ID); break;
			case '2': ViewAccounts(USER_ID); break;
			case '3': DeleteAccount(USER_ID); break;
			case '4': UpdateAccount(USER_ID); break;
			case '5': ViewTransactions(USER_ID); break;
			case '0': System.out.println("Logging out"); break;
			default : System.out.println(option + " is not a valid option\n");
			}	
		}
	}
	////////////////////////////////////////////////////////////////////////////
	@Override
	public void CreateUser() {
		System.out.println("Please input a username, or 0 to cancel");
		String USERNAME = scanner.nextLine();
		if ((Character.getNumericValue(USERNAME.charAt(0))) == 0)
				return;
		System.out.println("Please input a password");
		String PASSWORD = scanner.nextLine();
		try (Connection conn = ConnectionUtil.getConnection()) {
			String sql = "INSERT INTO USER_(USERNAME, PASSWORD) VALUES (?,?)";
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, USERNAME);
			pstmt.setString(2, PASSWORD);
			pstmt.executeUpdate();
		} catch (SQLIntegrityConstraintViolationException e) {
			e.printStackTrace();
			System.out.println("USERNAME is already taken, please use another");
			CreateUser();
		} catch (IOException e1) {
			e1.printStackTrace();
		} catch (SQLException e2) {
			e2.printStackTrace();
		}
		System.out.println("User has been created, returning to menu");
	}
	@Override
	public void UpdateUser() {
		System.out.println("Please input a USER_ID, or 0 to cancel");
		int USER_ID = Integer.parseInt(scanner.nextLine());
		if (USER_ID == 0)
				return;
		System.out.println("Please input a new USERNAME");
		String USERNAME = scanner.nextLine();
		System.out.println("Please input a new PASSWORD");
		String PASSWORD = scanner.nextLine();
		try (Connection conn = ConnectionUtil.getConnection()) {
			String sql = "UPDATE USER_ SET USERNAME = ?, PASSWORD = ? WHERE USER_ID = ?";
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, USERNAME);
			pstmt.setString(2, PASSWORD);
			pstmt.setInt(3, USER_ID);
			pstmt.executeUpdate();
		} catch (SQLIntegrityConstraintViolationException e) {
			e.printStackTrace();
			System.out.println("USERNAME is already taken, please use another");
			UpdateUser();
		} catch (IOException e1) {
			e1.printStackTrace();
		} catch (SQLException e2) {
			e2.printStackTrace();
		}
		System.out.println("User has been updated, returning to menu");
	}

	@Override
	public void DeleteUser() {
		System.out.println("Please input a USER_ID, or 0 to cancel");
		int USER_ID = Integer.parseInt(scanner.nextLine());
		if (USER_ID == 0)
				return;
		try (Connection conn = ConnectionUtil.getConnection()) {
			String sql = "DELETE FROM USER_ WHERE USER_ID = ?";
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, USER_ID);
			pstmt.executeUpdate();
		} catch (SQLIntegrityConstraintViolationException e) {
			e.printStackTrace();
			System.out.println("USER_ID has accounts, enter 1 to delete all records or any key to return to the menu");
			int option = Integer.parseInt(scanner.nextLine());
			if (option == 1) {
				Delete2(USER_ID);
				return;
			}
			else
				return;
		} catch (IOException e1) {
			e1.printStackTrace();
		} catch (SQLException e2) {
			e2.printStackTrace();
		}
		System.out.println("User has been deleted, returning to menu");
	}

	public void Delete2(int USER_ID) {	
		ArrayList<Integer> baids = new ArrayList<Integer>();
		ArrayList<Integer> tids = new ArrayList<Integer>();
		try (Connection conn = ConnectionUtil.getConnection()) {
			//FIND ALL ACCOUNT_IDs AND ADD TO AN ARRAYLIST
			String sql = "SELECT BANK_ACCOUNT_ID FROM ACCOUNT WHERE USER_ID = ?";
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, USER_ID);			
			ResultSet rs = pstmt.executeQuery();
			while(rs.next()) {
				int ba_id = rs.getInt("BANK_ACCOUNT_ID");
				baids.add(ba_id);
			}
		} catch (IOException e1) {
			e1.printStackTrace();
		} catch (SQLException e2) {
			e2.printStackTrace();
		}
		for (int i : baids) {
			try (Connection conn = ConnectionUtil.getConnection()) {
				//FIND ALL TRANSACTION_IDs AND ADD TO AN ARRAYLIST
				String sql = "SELECT TRANSACTION_ID FROM TRANSACTION WHERE BANK_ACCOUNT_ID = ?";
				PreparedStatement pstmt = conn.prepareStatement(sql);
				pstmt.setInt(1, i);			
				ResultSet rs = pstmt.executeQuery();
				while(rs.next()) {
					int t_id = rs.getInt("TRANSACTION_ID");
					tids.add(t_id);
				}
			} catch (IOException e1) {
				e1.printStackTrace();
			} catch (SQLException e2) {
				e2.printStackTrace();
			}
		}
		for (int i : tids) {
			try (Connection conn = ConnectionUtil.getConnection()) {
				//DELETE ALL TRANSACTIONS FROM ARRAYLIST
				String sql = "DELETE FROM TRANSACTION WHERE TRANSACTION_ID = ?";
				PreparedStatement pstmt = conn.prepareStatement(sql);
				pstmt.setInt(1, i);
				pstmt.executeUpdate();
			} catch (IOException e1) {
				e1.printStackTrace();
			} catch (SQLException e2) {
				e2.printStackTrace();
			}
		}
		for (int i : baids) {
			try (Connection conn = ConnectionUtil.getConnection()) {
				//DELETE ALL ACCOUNTS FROM ARRAYLIST
				String sql = "DELETE FROM ACCOUNT WHERE BANK_ACCOUNT_ID = ?";
				PreparedStatement pstmt = conn.prepareStatement(sql);
				pstmt.setInt(1, i);
				pstmt.executeUpdate();
			} catch (IOException e1) {
				e1.printStackTrace();
			} catch (SQLException e2) {
				e2.printStackTrace();
			}
		}
		try (Connection conn = ConnectionUtil.getConnection()) {
			String sql = "DELETE FROM USER_ WHERE USER_ID = ?";
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, USER_ID);
			pstmt.executeUpdate();
		} catch (IOException e1) {
			e1.printStackTrace();
		} catch (SQLException e2) {
			e2.printStackTrace();
		}
		System.out.println("User has been deleted, returning to menu");		
	}
	@Override
	public void ViewUser() {
		System.out.println("Please input a USER_ID, or 0 to see all");
		int USER_ID = Integer.parseInt(scanner.nextLine());
		if (USER_ID == 0) {
			try (Connection conn = ConnectionUtil.getConnection()) {
				User u = null;
				String sql = "SELECT * FROM USER_ ORDER BY USER_ID";
				PreparedStatement pstmt = conn.prepareStatement(sql);
				ResultSet rs = pstmt.executeQuery();
				while(rs.next()) {
					int user_id = rs.getInt("USER_ID");
					String username = rs.getString("USERNAME");
					String password = rs.getString("PASSWORD");
					u = new User(user_id, username, password);
					System.out.println(u);
				}
			} catch (IOException e1) {
				e1.printStackTrace();
			} catch (SQLException e2) {
				e2.printStackTrace();
			}
			return;
		}
		try (Connection conn = ConnectionUtil.getConnection()) {
			User u = null;
			String sql = "SELECT * FROM USER_ WHERE USER_ID = ?";
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, USER_ID);
			ResultSet rs = pstmt.executeQuery();
			while(rs.next()) {
				int user_id = rs.getInt("USER_ID");
				String username = rs.getString("USERNAME");
				String password = rs.getString("PASSWORD");
				u = new User(user_id, username, password);
				System.out.println(u);				
			}
		} catch (IOException e1) {
			e1.printStackTrace();
		} catch (SQLException e2) {
			e2.printStackTrace();
		}
	}
	@Override
	public void CreateAccount(int USER_ID) {
		System.out.println("How much would you like to open your account with?");
		double BALANCE = Double.parseDouble(scanner.nextLine());
		if (BALANCE <= 0) {
			System.out.println("Please enter an amount greater than $0.00");
			CreateAccount(USER_ID);
		}
		try (Connection conn = ConnectionUtil.getConnection()) {
			String sql = "INSERT INTO ACCOUNT (USER_ID, BALANCE) VALUES (?,?)";
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, USER_ID);
			pstmt.setDouble(2, BALANCE);
			pstmt.executeUpdate();
		} catch (SQLIntegrityConstraintViolationException e) {
			e.printStackTrace();
		} catch (IOException e1) {
			e1.printStackTrace();
		} catch (SQLException e2) {
			e2.printStackTrace();
		}
		System.out.println("Account has been created, returning to menu");
	}

	@Override
	public void ViewAccounts(int USER_ID) {
		Account a = null;
		try (Connection conn = ConnectionUtil.getConnection()) {
			String sql = "SELECT * FROM ACCOUNT WHERE USER_ID = ?";
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, USER_ID);
			pstmt.executeQuery();
			ResultSet rs = pstmt.executeQuery();
			while(rs.next()) {
				int ba_id = rs.getInt("BANK_ACCOUNT_ID");
				int user_id = rs.getInt("USER_ID");
				double balance = rs.getDouble("BALANCE");
				a = new Account(ba_id, user_id, balance);
				System.out.println(a);				
			}
		} catch (SQLIntegrityConstraintViolationException e) {
			e.printStackTrace();
		} catch (IOException e1) {
			e1.printStackTrace();
		} catch (SQLException e2) {
			e2.printStackTrace();
		}
	}

	@Override
	public void DeleteAccount(int USER_ID) {
		System.out.println("Which account would you like to delete?");
		int BANK_ACCOUNT_ID = Integer.parseInt(scanner.nextLine());
		//CHECK IF BANK_ACCOUNT_ID BELONGS TO USER_ID AND IS 0
		try (Connection conn = ConnectionUtil.getConnection()) {
			String sql = "SELECT * FROM ACCOUNT WHERE BANK_ACCOUNT_ID = ?";
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, BANK_ACCOUNT_ID);
			pstmt.executeQuery();
			ResultSet rs = pstmt.executeQuery();
			int user_id = 0;
			double balance = 0;
			while(rs.next()) {
				int ba_id = rs.getInt("BANK_ACCOUNT_ID");
				user_id = rs.getInt("USER_ID");
				balance = rs.getDouble("BALANCE");
			}
			if (user_id != USER_ID) {
				System.out.println("This account does not belong to you");
				return;
			}
			if (balance != 0) {
				System.out.println("This account can't be deleted, it has $" + balance);
				return;
			}
		} catch (SQLIntegrityConstraintViolationException e) {
			e.printStackTrace();
		} catch (IOException e1) {
			e1.printStackTrace();
		} catch (SQLException e2) {
			e2.printStackTrace();
		}
		ArrayList<Integer> tids = new ArrayList<Integer>();
		try (Connection conn = ConnectionUtil.getConnection()) {
			//FIND ALL TRANSACTION_IDs AND ADD TO AN ARRAYLIST
			String sql = "SELECT TRANSACTION_ID FROM TRANSACTION WHERE BANK_ACCOUNT_ID = ?";
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, BANK_ACCOUNT_ID);			
			ResultSet rs = pstmt.executeQuery();
			while(rs.next()) {
				int t_id = rs.getInt("TRANSACTION_ID");
				tids.add(t_id);
			}
		} catch (IOException e1) {
			e1.printStackTrace();
		} catch (SQLException e2) {
			e2.printStackTrace();
		}
		for (int i : tids) {
			try (Connection conn = ConnectionUtil.getConnection()) {
				//DELETE ALL TRANSACTIONS FROM ARRAYLIST
				String sql = "DELETE FROM TRANSACTION WHERE TRANSACTION_ID = ?";
				PreparedStatement pstmt = conn.prepareStatement(sql);
				pstmt.setInt(1, i);
				pstmt.executeUpdate();
			} catch (IOException e1) {
				e1.printStackTrace();
			} catch (SQLException e2) {
				e2.printStackTrace();
			}
		}
		try (Connection conn = ConnectionUtil.getConnection()) {
			String sql = "DELETE FROM ACCOUNT WHERE BANK_ACCOUNT_ID = ?";
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, BANK_ACCOUNT_ID);
			pstmt.executeUpdate();
		} catch (IOException e1) {
			e1.printStackTrace();
		} catch (SQLException e2) {
			e2.printStackTrace();
		}
		System.out.println("Account " + BANK_ACCOUNT_ID + " has been deleted, returning to menu");			
	}

	@Override
	public void UpdateAccount(int USER_ID) {
		System.out.println("Which account would you like to withdraw/deposit from?");
		int BANK_ACCOUNT_ID = Integer.parseInt(scanner.nextLine());
		//CHECK IF BANK_ACCOUNT_ID BELONGS TO USER_ID
		try (Connection conn = ConnectionUtil.getConnection()) {
			String sql = "SELECT * FROM ACCOUNT WHERE USER_ID = ?";
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, USER_ID);
			pstmt.executeQuery();
			ResultSet rs = pstmt.executeQuery();
			boolean flag = false;
			while(rs.next()) {
				int ba_id = rs.getInt("BANK_ACCOUNT_ID");
				int user_id = rs.getInt("USER_ID");
				double balance = rs.getDouble("BALANCE");
				if (BANK_ACCOUNT_ID == ba_id) {
					flag = true;
					break;
				}
			}
			if (flag == false) {
				System.out.println("This is not your account");
				return;
			}
		} catch (SQLIntegrityConstraintViolationException e) {
			e.printStackTrace();
		} catch (IOException e1) {
			e1.printStackTrace();
		} catch (SQLException e2) {
			e2.printStackTrace();
		}
		System.out.println("Select\n1) Withdraw\n2) Deposit\n0) Return to the menu");
		int option = Integer.parseInt(scanner.nextLine());
		double amount;
		switch(option) {
		case 0: return;
		case 1: System.out.println("How much would you like to withdraw?");
			amount = Double.parseDouble(scanner.nextLine()) * -1;
			UpdateAccount2(BANK_ACCOUNT_ID, amount);
			break;
		case 2: System.out.println("How much would you like to deposit?");
			amount = Double.parseDouble(scanner.nextLine());
			UpdateAccount2(BANK_ACCOUNT_ID, amount);
			break;
		default: System.out.println("Invalid option"); UpdateAccount(USER_ID);
		}
	}

	public void UpdateAccount2(int BANK_ACCOUNT_ID, double amount) {
		double balance = 0;
		try (Connection conn = ConnectionUtil.getConnection()) {
			String sql = "SELECT BALANCE FROM ACCOUNT WHERE BANK_ACCOUNT_ID = ?";
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, BANK_ACCOUNT_ID);
			pstmt.executeQuery();
			ResultSet rs = pstmt.executeQuery();
			while(rs.next()) {
				balance = rs.getDouble("BALANCE");
			}
		} catch (SQLIntegrityConstraintViolationException e) {
			e.printStackTrace();
		} catch (IOException e1) {
			e1.printStackTrace();
		} catch (SQLException e2) {
			e2.printStackTrace();
		}
		double newBalance = balance + amount;
		if (newBalance < 0) {
			System.out.println("Insufficient funds");
			return;
		}
		try (Connection conn = ConnectionUtil.getConnection()) {
			String sql = "UPDATE ACCOUNT SET BALANCE = ? WHERE BANK_ACCOUNT_ID = ?";
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setDouble(1, newBalance);
			pstmt.setInt(2, BANK_ACCOUNT_ID);
			pstmt.executeUpdate();
		} catch (SQLIntegrityConstraintViolationException e) {
			e.printStackTrace();
		} catch (IOException e1) {
			e1.printStackTrace();
		} catch (SQLException e2) {
			e2.printStackTrace();
		}	
	}
	
	@Override
	public void ViewTransactions(int USER_ID) {
		// TODO Auto-generated method stub
		
	}

}