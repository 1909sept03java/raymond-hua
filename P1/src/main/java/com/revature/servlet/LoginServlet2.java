package com.revature.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.revature.beans.Employee;
import com.revature.services.Services;
import com.revature.util.UnknownEmployeeException; 

public class LoginServlet2 extends HttpServlet{
	
	private static final long serialVersionUID =-4219738150343355737L;

	private Services s = new Services();
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.getRequestDispatcher("P1.html").forward(req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		HttpSession session = req.getSession();
		
		Employee e = new Employee();
		e.setUsername(req.getParameter("username"));
		e.setPassword(req.getParameter("password"));
		try{e = s.AuthenticateService(e);}
		catch (UnknownEmployeeException e1) {
			e1.printStackTrace();
		}
		if (e.getEmployee_id() != 0) {
			session.setAttribute("employee_id", e.getEmployee_id());
			session.setAttribute("username",e.getUsername());
			session.setAttribute("password", e.getPassword());
			session.setAttribute("manager_id", e.getManager_id());
			resp.sendRedirect("employee");
		}
		else {
			//Alert "Invalid Information"
			resp.sendRedirect("login");
		}	
	}
}

