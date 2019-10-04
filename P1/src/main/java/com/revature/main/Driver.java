package com.revature.main;

import java.io.IOException;
import java.sql.Connection;
import java.util.Random;
import java.util.Scanner;

import com.revature.dao.P1DaoImpl;
import com.revature.util.*;

public class Driver {
	public static Scanner scanner = new Scanner(System.in);

	private static P1DaoImpl dao = new P1DaoImpl();

	public static void main(String[] args) throws IOException {

		try {
			Connection conn = ConnectionUtil.getConnection();
			System.out.println(conn);
			System.out.println(conn.getMetaData().getDatabaseMajorVersion());
		} catch(Exception e) {
			e.printStackTrace();
		}	
		
		//Employee e = new Employee("HI", "BYE");
		//try {e = dao.Authenticate(e);}
		//catch (UnknownEmployeeException e1) {
		//	e1.printStackTrace();
		//}
		//System.out.println("Update whole account");
		//dao.updateEmployee( 0,  "HI",  "BYE");
		//System.out.println("Reset Password");
		//dao.resetPassword("raymondjhua@gmail.com", "HI", "YO");
		//dao.newReimbursement(e.getEmployee_id(), 99.99);
		//System.out.println(e.toString());
		//System.out.println(dao.getEmployeeReimbursements(1));
		//System.out.println(dao.getOtherReimbursements(1).toString());
//		String email = "raymondjhua@gmail.com";
//		String username ="REX";
//		email = email.toLowerCase();
//		String password = dao.randomString();
//		//dao.resetPassword(email, username, password);
//		dao.sendEmail(email, username, password);
		dao.approve(29);
		System.out.println("Done");
	}

	protected static String randomString() {
        String charOptions = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        StringBuilder str = new StringBuilder();
        Random rnd = new Random();
        while (str.length() < 10) { // length of the random string.
            int index = (int) (rnd.nextFloat() * charOptions.length());
            str.append(charOptions.charAt(index));
        }
        String saltStr = str.toString();
        return saltStr;

    }
}