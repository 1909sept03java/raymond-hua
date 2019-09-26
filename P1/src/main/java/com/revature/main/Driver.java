package com.revature.main;

import java.sql.Connection;
import java.util.Scanner;

import com.revature.beans.Employee;
import com.revature.dao.P1DaoImpl;
import com.revature.util.ConnectionUtil;

public class Driver {
	public static Scanner scanner = new Scanner(System.in);

	private static P1DaoImpl dao = new P1DaoImpl();

	public static void main(String[] args) {

		try {
			Connection conn = ConnectionUtil.getConnection();
			System.out.println(conn);
			System.out.println(conn.getMetaData().getDatabaseMajorVersion());
		} catch(Exception e) {
			e.printStackTrace();
		}
		
		Employee e = new Employee("HI", "BYE");
		e = dao.Authenticate(e);
		System.out.println(e.toString());
	}
}