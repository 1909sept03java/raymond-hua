import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;
import java.util.*;

public class Driver {
	public static Scanner scanner = new Scanner(System.in);
	public static void main(String[] args) {
		/*
		try {
			Connection conn = ConnectionUtil.getConnection();
			System.out.println(conn);
			System.out.println(conn.getMetaData().getDatabaseMajorVersion());
		} catch(Exception e) {
			e.printStackTrace();
		}
		*/
		char option = '1';
		while(option != '0') {
			System.out.println("Select:\n1) Log in\n2) Register\n0) Exit");
			option = scanner.nextLine().charAt(0);
			switch (option) {
			case '1': LogIn(); break;
			case '2': Register(); break;
			case '0': System.out.println("Exiting"); break;
			default : System.out.println(option + " is not a valid option\n");
			}	
		}
	}
	
	public static void LogIn() {
		System.out.println("Please input a username");
		String USERNAME = scanner.nextLine();
		System.out.println("Please input a password");
		String PASSWORD = scanner.nextLine();
		int option = Validate(USERNAME, PASSWORD); //connect with DB to validate information is true and matches
		Option(option, USERNAME, PASSWORD);
		//System.out.println("Logging in to " + USERNAME);	//when validation is true
		//Custom exception when correct username but incorrect password to re-enter password
	}
	public static void Register() {
		System.out.println("Please input a username");
		String USERNAME = scanner.nextLine();
		System.out.println("Please input a password");
		String PASSWORD = scanner.nextLine();
		System.out.println("Please input your first name");
		String FIRST_NAME = scanner.nextLine();
		System.out.println("Please input you last name");
		String LAST_NAME = scanner.nextLine();
		//pass info to object/create row in DB
		//confirm when done
	}
	public static int Validate(String USERNAME, String PASSWORD) {
		//connect with DB to validate information is true and matches
		//return 1 if valid, return 2 if not valid, return 0 if super user
		return 2;
	}
	public static void Option(int option, String USERNAME, String PASSWORD) {
		switch (option) {
		case 1: UserOption(USERNAME, PASSWORD); break;
		case 2: System.out.println("Incorrect information"); 
				  System.out.println("Enter (1) if you'ld like to try again, or any key to return to main menu");
				  char i = scanner.nextLine().charAt(0);
				  if (i == '1')
						LogIn();
 			      break;
		case 0: SuperOption(USERNAME, PASSWORD); break;
		}
	}
	public static void UserOption(String USERNAME, String PASSWORD) {
		char option = '1';
		while(option != '0') {
			System.out.println("Select:\n1) View accounts and balances\n2) Create an account\n3) Delete account, if empty\n4) "
					+ "Deposit/Withdraw\n5) View transaction history\n0) Log out");
			option = scanner.nextLine().charAt(0);
			switch (option) {
			case '1': ViewAccountsBalances(); break;
			case '2': CreateAccount(); break;
			case '3': DeleteAccount(); break;
			case '4': DepositWithdraw(); break;
			case '5': ViewTransactions(); break;
			case '0': System.out.println("Logging out"); break;
			default : System.out.println(option + " is not a valid option\n");
			}	
		}
	}
	public static void SuperOption(String USERNAME, String PASSWORD) {
		char option = '1';
		while(option != '0') {
			System.out.println("Select:\n1) View accounts and balances\n2) Create an account\n3) Delete account, if empty\n4) "
					+ "Deposit/Withdraw\n5) View transaction history\n6) Create user\n7) Update user\n8) Delete all users\n9) View all users\n0) Logging out");
			option = scanner.nextLine().charAt(0);
			switch (option) {
			case '1': ViewAccountsBalances(); break;
			case '2': CreateAccount(); break;
			case '3': DeleteAccount(); break;
			case '4': DepositWithdraw(); break;
			case '5': ViewTransactions(); break;
			case '6': CreateUser(); break;
			case '7': UpdateUser(); break;
			case '8': DeleteUser(); break;
			case '9': ViewUser(); break;
			case '0': System.out.println("Logging out"); break;
			default : System.out.println(option + " is not a valid option\n");
			}	
		}
	}

	public static void ViewAccountsBalances() {
		// TODO Auto-generated method stub
		
	}

	public static void CreateAccount() {
		// TODO Auto-generated method stub
		
	}

	public static void DeleteAccount() {
		// TODO Auto-generated method stub
		
	}

	public static void DepositWithdraw() {
		// TODO Auto-generated method stub
		
	}

	public static void ViewTransactions() {
		// TODO Auto-generated method stub
		
	}

	public static void CreateUser() {
		// TODO Auto-generated method stub
		
	}

	public static void UpdateUser() {
		// TODO Auto-generated method stub
		
	}

	public static void DeleteUser() {
		// TODO Auto-generated method stub
		
	}

	public static void ViewUser() {
		// TODO Auto-generated method stub
		
	}
}