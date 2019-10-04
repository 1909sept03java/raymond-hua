package com.revature.dao;

import java.io.IOException;
import java.util.ArrayList;

import com.revature.beans.Employee;
import com.revature.beans.Reimbursement;
import com.revature.util.NotManagerException;
import com.revature.util.UnknownEmployeeException;

public interface P1DAO {
		//Login
		public Employee Authenticate(Employee E) throws UnknownEmployeeException;
		public boolean isEmmMan(Employee E) throws NotManagerException;
		//Options
		public void newReimbursement(int EMPLOYEE_ID, double AMOUNT);
		public ArrayList<Reimbursement> getPendingReimbursements(int EMPLOYEE_ID);
		public ArrayList<Reimbursement> getOtherReimbursements(int EMPLOYEE_ID);
		public void updateEmployee(int EMPLOYEE_ID, String USERNAME, String PASSWORD);
		public ArrayList<Reimbursement> getEmployeeReimbursements(int MANAGER_ID, int EMPLOYEE_ID);
		public void approve(int REIMBURSEMENT_ID) throws IOException;
		public void deny(int REIMBURSEMENT_ID) throws IOException;
		public ArrayList<Reimbursement> getResolvedReimbursements();
		public ArrayList<Employee> getEmployees();
		//Optional Options
		//Reset Password
		public void resetPassword(String EMAIL, String USERNAME, String PASSWORD);
		//New Password
		public void newEmployee(String USERNAME, String PASSWORD, int MANAGER_ID, String EMAIL);
		//Optional Support
		public String randomString();
		public void sendEmail(String EMAIL, String USERNAME, String PASSWORD) throws IOException;
		
}
