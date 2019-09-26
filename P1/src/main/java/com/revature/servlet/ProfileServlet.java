package com.revature.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.revature.beans.Credentials;
import com.revature.beans.User;
import com.revature.service.AuthenticationService;

public class ProfileServlet extends HttpServlet{

	private static final long serialVersionUID =-4217738150343355737L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		HttpSession session = req.getSession(false);
		if (session != null && session.getAttribute("employee_id") != null) {
				req.getRequestDispatcher("Profile.html").forward(req, resp);
		} else {
			resp.sendRedirect("login");
		}
	}
}

