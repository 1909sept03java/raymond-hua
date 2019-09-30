package com.revature.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.revature.beans.Employee;
import com.revature.dao.P1DaoImpl;

@WebServlet("/option")
public class OptionServlet extends HttpServlet {

	private static final long serialVersionUID = -4219738150343355737L;

	private P1DaoImpl dao = new P1DaoImpl();

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.getRequestDispatcher("Option.html").forward(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		HttpSession session = req.getSession(false);
		int option = Integer.parseInt(session.getAttribute("option").toString());
		int employee_id = Integer.parseInt(session.getAttribute("employee_id").toString());
		
		switch(option) {
			case 1: double amount = Double.parseDouble(req.getParameter("amount"));	
				dao.newReimbursement(employee_id, amount); 
				break;
			case 5: String username = req.getParameter("newUsername");
				String password = req.getParameter("newPassword");
				dao.updateEmployee(employee_id, username, password);
				break;
		}
		if(Boolean.parseBoolean(session.getAttribute("isEmmMan").toString()))
			req.getRequestDispatcher("Manager.html").forward(req, resp);
		else
			req.getRequestDispatcher("Employee.html").forward(req, resp);	}
}