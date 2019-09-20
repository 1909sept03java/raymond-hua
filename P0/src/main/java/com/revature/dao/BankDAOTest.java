package com.revature.dao;

import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;

import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.Rule;
import org.junit.rules.ExpectedException;

import com.revature.beans.Account;
import com.revature.beans.Transaction;
import com.revature.beans.User;
import com.revature.util.ConnectionUtil;
import com.revature.util.IncorrectPasswordException;
import com.revature.util.OverdraftException;
import com.revature.util.UnknownUserException;


public class BankDAOTest {
	private static BankDAOImpl dao;

	@BeforeClass
	public static void initialize() {
		dao = new BankDAOImpl();
	}
	//Valid user, USERNAME: AAllison, PASSWORD: AAllisonP, USER_ID = 1
	@Test(expected = UnknownUserException.class)
	public void testUnknownUserException() throws IncorrectPasswordException, UnknownUserException {
		System.out.println("Unknown user test");
		dao.Validate("A", "A");
	}
	
	@Test(expected = IncorrectPasswordException.class)
	public void testIncorrectPasswordException() throws IncorrectPasswordException, UnknownUserException {
		System.out.println("Incorrect password test");
		dao.Validate("AAllison", "A");
	}
	
	@Test(expected = OverdraftException.class)
	public void testOverdraftException() throws OverdraftException {
		dao.UpdateAccount2(1, -1500);
	}
}

