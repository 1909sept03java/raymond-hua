package com.revature.dao;

import static org.junit.Assert.assertEquals;

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
	//Valid user, USERNAME: AAllison, PASSWORD: AAllisonP, USER_ID = 1
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
		Employee e = dao.Authenticate(new Employee("FFrederick", "FFrederickP"));
		assertEquals(e.getEmployee_id(), 7);
	}
}

