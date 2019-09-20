package com.revature.main;

import java.sql.Connection;
import java.util.Scanner;

import com.revature.beans.*;
import com.revature.dao.*;
import com.revature.util.ConnectionUtil;

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
		BankDAO dao = new BankDAOImpl();
		char option = '1';
		System.out.println("Welcome to American Bank");
		while(option != '0') {
			System.out.println("Select:\n1) Log in\n2) Register\n0) Exit");
			try { 
				option = scanner.nextLine().charAt(0);
			} catch (StringIndexOutOfBoundsException e) {
				//e.printStackTrace();
				continue;
			}
			switch (option) {
			case '1': dao.LogIn(); break;
			case '2': try {
				dao.CreateUser();
			} catch (StringIndexOutOfBoundsException e) {
				//e.printStackTrace();
			}
			break;
			case '0': System.out.println("Exiting"); break;
			default : System.out.println(option + " is not a valid option\n");
			}	
		}
	}
}