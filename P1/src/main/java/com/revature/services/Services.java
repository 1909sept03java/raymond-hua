package com.revature.services;

import java.io.IOException;

import com.revature.beans.Employee;
import com.revature.dao.P1DaoImpl;
import com.revature.util.NotManagerException;
import com.revature.util.UnknownEmployeeException;

public class Services {

	private static P1DaoImpl dao = new P1DaoImpl();

	public Employee AuthenticateService(Employee e) throws UnknownEmployeeException {
		return dao.Authenticate(e);
	}
	public boolean isEmmManService(Employee e) throws NotManagerException {
		return dao.isEmmMan(e);
	}
	public void newReimbursementService(int EMPLOYEE_ID, double AMOUNT) {
		dao.newReimbursement(EMPLOYEE_ID, AMOUNT);
	}
	public void updateEmployeeService(int EMPLOYEE_ID, String USERNAME, String PASSWORD) {
		dao.updateEmployee(EMPLOYEE_ID, USERNAME, PASSWORD);
	}
	public void approveService(int REIMBURSEMENT_ID) throws IOException {
		dao.approve(REIMBURSEMENT_ID);
	}
	public void denyService(int REIMBURSEMENT_ID) throws IOException {
		dao.deny(REIMBURSEMENT_ID);
	}
	public String randomStringService() {
		return dao.randomString();
	}
	public void newEmployeeService(String USERNAME, String PASSWORD, int MANAGER_ID, String EMAIL) {
		dao.newEmployee(USERNAME, PASSWORD, MANAGER_ID, EMAIL);
	}
	public void sendEmailService(String EMAIL, String USERNAME, String PASSWORD) throws IOException {
		dao.sendEmail(EMAIL, USERNAME, PASSWORD);
	}
}
