package com.revature.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.revature.beans.Credentials; //
import com.revature.beans.Employee;
import com.revature.beans.User; //
import com.revature.dao.P1DaoImpl; 
import com.revature.service.AuthenticationService; //
/*
 * Remove Credentials, User, AuthenticationService, HelloWorld
 * ?SessionServlet, 	Profile HTML, JS
 */
public class LoginServlet2 extends HttpServlet{
	
	private static final long serialVersionUID =-4219738150343355737L;

	private P1DaoImpl dao = new P1DaoImpl();
/*
	private AuthenticationService authService = new AuthenticationService();
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// serve the login.html page as a response
		// RequestDispatcher is used to perform a 'forward' 
		// (pass the request to another resource without the client knowing)
		req.getRequestDispatcher("P1.html").forward(req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// check whether a session already exists, and create one if not 
		// overloaded version take a boolean parameter, if false returns null if not session exists
		// matching the incoming request's JSESSIONID token
		HttpSession session = req.getSession();
		// grab credentials from the request - use getParameter for form data
		Credentials creds = new Credentials();
		creds.setUsername(req.getParameter("username"));
		creds.setPassword(req.getParameter("password"));
		// pass responsibility for performing auth logic to a service
		User u = authService.authenticateUser(creds);
		if (u != null) {
			// they're real 
			// set user information as session attributes (not request attributes)
			session.setAttribute("userId", u.getId());
			session.setAttribute("firstname", u.getFirstName());
			session.setAttribute("lastname", u.getLastName());
			session.setAttribute("problem", null);
			// redirect to their profile
			resp.sendRedirect("profile");
		} else {
			// they're not real
			session.setAttribute("problem", "invalid credentials");
			// resp.getWriter().write("invalid credentials");
			// redirect back to login
			resp.sendRedirect("login");
		}
	}
}
*/
	
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
		e = dao.Authenticate(e);
		if (e.getEmployee_id() != 0) {
			session.setAttribute("employee_id", e.getEmployee_id());
			session.setAttribute("username",e.getUsername());
			session.setAttribute("password", e.getPassword());
			session.setAttribute("manager_id", e.getManager_id());
			if(dao.isEmmMan(e))
				req.getRequestDispatcher("Manager.html").forward(req, resp);
			else
				req.getRequestDispatcher("Employee.html").forward(req, resp);
		} else {
			//Alert "Invalid Information"
			resp.sendRedirect("login");
		}	
	}
}

