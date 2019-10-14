package com.revature.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.revature.services.Services;

@WebServlet("/option")
public class OptionServlet extends HttpServlet {

	private static final long serialVersionUID = -4219738150343355737L;

	private Services s = new Services();
	
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
				if (amount == 0) break;
				s.newReimbursementService(employee_id, amount);
				break;
			case 5: String username = req.getParameter("newUsername");
				String password = req.getParameter("newPassword");
				s.updateEmployeeService(employee_id, username, password);
				break;
			case 6: 
				int i = Integer.parseInt(req.getParameter("approve"));
				if (i != 0) {
					s.approveService(Integer.parseInt(req.getParameter("approve")));
				}
				i = Integer.parseInt(req.getParameter("deny"));
				if (i != 0) {
					s.denyService(Integer.parseInt(req.getParameter("deny")));
				}
				break;
			case 10: String password2 = s.randomStringService();
				s.newEmployeeService(req.getParameter("username"), password2, 1, req.getParameter("email"));
				s.sendEmailService(req.getParameter("email"), req.getParameter("username"), password2);
				break;
		}
		if(Boolean.parseBoolean(session.getAttribute("isEmmMan").toString()))
			req.getRequestDispatcher("Manager.html").forward(req, resp);
		else
			req.getRequestDispatcher("Employee.html").forward(req, resp);	}
}