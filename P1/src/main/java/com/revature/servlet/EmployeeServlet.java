package com.revature.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.revature.beans.Employee;
import com.revature.dao.P1DaoImpl;

@WebServlet("/employee") 	
public class EmployeeServlet extends HttpServlet {
	
	private static final long serialVersionUID =-4219738150343355737L;

	private P1DaoImpl dao = new P1DaoImpl();

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		HttpSession session = req.getSession(false);
		Employee e = new Employee();
		try {
			e.setEmployee_id(Integer.parseInt(session.getAttribute("employee_id").toString()));
			e.setUsername(session.getAttribute("username").toString());
			e.setPassword(session.getAttribute("password").toString());
			e.setManager_id(Integer.parseInt(session.getAttribute("manager_id").toString()));
			//resp.getWriter().write((new ObjectMapper()).writeValueAsString(e));
		} catch (Exception e1) {
			e1.printStackTrace();
			resp.getWriter().write("{\"session\":null");
		}	
		e.setEmmMan(dao.isEmmMan(e));
		session.setAttribute("isEmmMan", dao.isEmmMan(e));
		if(dao.isEmmMan(e))
			req.getRequestDispatcher("Manager.html").forward(req, resp);
		else
			req.getRequestDispatcher("Employee.html").forward(req, resp);	
	}
	
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		HttpSession session = req.getSession(false);
		String option = req.getParameter("option");
		int employee_id = Integer.parseInt(req.getParameter("employee_id"));
		if (option.equals("0")) {
			session.invalidate();
			resp.sendRedirect("login");
		}
		else {
			session.setAttribute("option", option);
			session.setAttribute("selection", employee_id);
			resp.sendRedirect("option");
		}
	}
}
