package com.revature.servlet;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.revature.beans.Employee;
import com.revature.beans.User;

@WebServlet("/session") 	
public class SessionServlet extends HttpServlet{
	
	private static final long serialVersionUID =-4219738150343355737L;
	
	/*
	// return a JSON representation of the currently authenticated user for a
		// request's JSESSIONID
		@Override
		protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
			// grab current session, if it exists, otherwise return null
			HttpSession session = req.getSession(false);
			try {
				// grab session attributes and place them within a user object
				int userId = Integer.parseInt(session.getAttribute("userId").toString());
				String firstname = session.getAttribute("firstname").toString();
				String lastname = session.getAttribute("lastname").toString();
				User u = new User(userId, firstname, lastname);
				// use ObjectMapper (part of the Jackson api) to convert Java object to JSON
				// representation
				resp.getWriter().write((new ObjectMapper()).writeValueAsString(u));
			} catch (Exception e) {
				e.printStackTrace();
				resp.getWriter().write("{\"session\":null}");
			}
		}

		@Override
		protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
			doGet(req, resp);
		}
}
	
	*/
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		HttpSession session = req.getSession(false);
//		Employee e = new Employee();
//		e.setEmployee_id(Integer.parseInt(session.getAttribute("employee_id").toString()));
//		e.setUsername(session.getAttribute("username").toString());
//		e.setPassword(session.getAttribute("password").toString());
//		e.setManager_id(Integer.parseInt(session.getAttribute("manager_id").toString()));
//		e.setEmmMan(Boolean.parseBoolean(session.getAttribute("isEmmMan").toString()));
//		e.setOption(Integer.parseInt(session.getAttribute("option").toString()));

		ArrayList data = new ArrayList(); //get data
		data.add((session.getAttribute("option").toString()));
		data.add((session.getAttribute("employee_id").toString()));
		data.add((session.getAttribute("username").toString()));
		data.add((session.getAttribute("password").toString()));
		data.add((session.getAttribute("manager_id").toString()));
		data.add((session.getAttribute("isEmmMan").toString()));
		try {
			resp.getWriter().write((new ObjectMapper()).writeValueAsString(data));
		} catch (Exception e1) {
			e1.printStackTrace();
			resp.getWriter().write("{\"session\":null");
		}
	} 
}
