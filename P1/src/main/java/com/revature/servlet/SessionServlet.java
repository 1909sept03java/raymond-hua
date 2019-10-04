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
import com.revature.dao.P1DaoImpl;

@WebServlet("/session")
public class SessionServlet extends HttpServlet {

	private static final long serialVersionUID = -4219738150343355737L;

	private P1DaoImpl dao = new P1DaoImpl();

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		HttpSession session = req.getSession(false);

		ArrayList data = new ArrayList(); // get data
		data.add(session.getAttribute("option").toString());
		data.add(session.getAttribute("employee_id").toString());
		data.add(session.getAttribute("username").toString());
		data.add(session.getAttribute("password").toString());
		data.add(session.getAttribute("manager_id").toString());
		data.add(session.getAttribute("isEmmMan").toString());
		data.add(session.getAttribute("selection").toString());
		
		int employee_id = Integer.parseInt(session.getAttribute("employee_id").toString());
		int option = Integer.parseInt(session.getAttribute("option").toString());
		int selection = Integer.parseInt(session.getAttribute("selection").toString());

		switch(option) {
			case 2: data.add(dao.getPendingReimbursements(employee_id)); break;
			case 3: data.add(dao.getOtherReimbursements(employee_id)); break;
			case 6: data.add(dao.getEmployeeReimbursements(employee_id, 0)); break;
			case 7: data.add(dao.getEmployeeReimbursements(employee_id, selection)); break;
			case 8: data.add(dao.getResolvedReimbursements()); break;
			case 9: data.add(dao.getEmployees()); break;
		}
		try {
			resp.getWriter().write((new ObjectMapper()).writeValueAsString(data));
		} catch (Exception e1) {
			e1.printStackTrace();
			resp.getWriter().write("{\"session\":null");
		}
	}
}

