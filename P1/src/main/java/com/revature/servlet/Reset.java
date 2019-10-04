package com.revature.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.revature.dao.P1DaoImpl;

@WebServlet("/reset")
public class Reset extends HttpServlet{
	
	private static final long serialVersionUID =-4219738150343355737L;
	
	private P1DaoImpl dao = new P1DaoImpl();

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.getRequestDispatcher("Reset.html").forward(req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String email = req.getParameter("email");
		String username = req.getParameter("username");
		email = email.toLowerCase();
		String password = dao.randomString();
		dao.resetPassword(email, username, password);
		dao.sendEmail(email, username, password);
		resp.sendRedirect("login");
	}
}
