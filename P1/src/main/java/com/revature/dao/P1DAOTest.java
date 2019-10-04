package com.revature.dao;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;

import org.junit.BeforeClass;
import org.junit.Test;

import com.revature.beans.*;
import com.revature.util.*;


public class P1DAOTest {
	private static P1DaoImpl dao;

	@BeforeClass
	public static void initialize() {
		dao = new P1DaoImpl();
	}

	@Test(expected = UnknownEmployeeException.class)
	public void testUnknownEmployeeException() throws UnknownEmployeeException{
		System.out.println("Unknown employee test");
		dao.Authenticate(new Employee("HHI", "BYE"));
	}
	
	@Test(expected = NotManagerException.class)
	public void testNotManagerException() throws NotManagerException, UnknownEmployeeException{
		System.out.println("Not manager test");
		Employee e = dao.Authenticate(new Employee("BBenson", "BBensonP"));
		dao.isEmmMan(e);
	}

	@Test
	public void EmployeeSevenIsFFrederick() throws UnknownEmployeeException{
		System.out.println("Is FFrederick employee #7?");
		Employee e = dao.Authenticate(new Employee("FFrederick", "FFrederickP"));
		assertEquals(e.getEmployee_id(), 7);
	}
	
	@Test
	public void testGetPendingReimbursement() {
		System.out.println("Assert reimbursement #29 is in the array list");
		ArrayList<Reimbursement> test = dao.getPendingReimbursements(1);
		boolean isThere = false;
		for (Reimbursement i : test) {
			if (i.getReimbursement_id() == 29)	
				isThere = true;
		}
		assertEquals(isThere, true);
	}
	
	@Test
	public void testGetOtherReimbursement() {
		System.out.println("Assert reimbursement #2 is in the array list");
		ArrayList<Reimbursement> test = dao.getOtherReimbursements(1);
		boolean isThere = false;
		for (Reimbursement i : test) {
			if (i.getReimbursement_id() == 50)
				isThere = true;
		}
		assertEquals(isThere, true);
	}
	
	@Test
	public void testGetEmployeeReimbursements() {
		System.out.println("Assert reimbursement #18 is in the array list");
		ArrayList<Reimbursement> test = dao.getEmployeeReimbursements(1, 0);
		boolean isThere = false;
		for (Reimbursement i : test) {
			if (i.getReimbursement_id() == 18)
				isThere = true;
		}
		assertEquals(isThere, true);
	}
	
	@Test
	public void testGetResolvedReimbursements() {
		System.out.println("Assert reimbursement #18 is in the array list");
		ArrayList<Reimbursement> test = dao.getResolvedReimbursements();
		boolean isThere = false;
		for (Reimbursement i : test) {
			if (i.getReimbursement_id() == 37)
				isThere = true;
		}
		assertEquals(isThere, true);
	}

	@Test
	public void testGetEmployees() {
		System.out.println("Assert employee #7 is in the array list");
		ArrayList<Employee> test = dao.getEmployees();
		boolean isThere = false;
		for (Employee i : test) {
			if (i.getEmployee_id() == 7)
				isThere = true;
		}
		assertEquals(isThere, true);
	}
}


