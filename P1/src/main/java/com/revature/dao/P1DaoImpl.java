package com.revature.dao;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.util.ArrayList;
import java.util.Random;

import com.revature.beans.Employee;
import com.revature.beans.Reimbursement;
import com.revature.util.ConnectionUtil;
import com.revature.util.NotManagerException;
import com.revature.util.UnknownEmployeeException;
import com.sendgrid.Method;
import com.sendgrid.Request;
import com.sendgrid.Response;
import com.sendgrid.SendGrid;
import com.sendgrid.helpers.mail.Mail;
import com.sendgrid.helpers.mail.objects.Content;
import com.sendgrid.helpers.mail.objects.Email;

public class P1DaoImpl implements P1DAO {

	@Override
	public Employee Authenticate(Employee E) throws UnknownEmployeeException{
		Employee result = new Employee(E.getUsername(), E.getPassword());
		try (Connection conn = ConnectionUtil.getConnection()) {
			String sql = "SELECT * FROM EMPLOYEE WHERE USERNAME = ? AND PASSWORD = ?";
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, result.getUsername());
			pstmt.setString(2, result.getPassword());
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				result.setEmployee_id(rs.getInt("EMPLOYEE_ID"));
				result.setUsername(rs.getString("USERNAME"));
				result.setPassword(rs.getString("PASSWORD"));
				result.setManager_id(rs.getInt("MANAGER_ID"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (IOException e1) {
			e1.printStackTrace();
		} catch (NullPointerException e2) {
			e2.printStackTrace();
		}
		if (result.getEmployee_id() == 0)
			throw new UnknownEmployeeException();
		return result;
	}

	public boolean isEmmMan(Employee E) throws NotManagerException{
		boolean isManager = false;
		try (Connection conn = ConnectionUtil.getConnection()) {
			String sql = "SELECT MANAGER_ID FROM EMPLOYEE WHERE MANAGER_ID = ?";
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, E.getEmployee_id());
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				if (rs.getInt("MANAGER_ID") == E.getEmployee_id());
				return true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (IOException e1) {
			e1.printStackTrace();
		} catch (NullPointerException e2) {
			e2.printStackTrace();
		}
		if (isManager == false)
			throw new NotManagerException();
		return isManager;
	}

	@Override
	public void newReimbursement(int EMPLOYEE_ID, double AMOUNT) {
		try (Connection conn = ConnectionUtil.getConnection()) {
			String sql = "INSERT INTO REIMBURSEMENT (EMPLOYEE_ID, AMOUNT, PAD) VALUES (?, ?, 0)";
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, EMPLOYEE_ID);
			pstmt.setDouble(2, AMOUNT);
			pstmt.executeUpdate();
		} catch (SQLIntegrityConstraintViolationException e) {
			e.printStackTrace();
		} catch (IOException e1) {
			e1.printStackTrace();
		} catch (SQLException e2) {
			e2.printStackTrace();
		}
	}
	@Override
	public ArrayList<Reimbursement> getPendingReimbursements(int EMPLOYEE_ID) {
		ArrayList<Reimbursement> results = new ArrayList<>();
		try (Connection conn = ConnectionUtil.getConnection()) {
			String sql = "SELECT * FROM REIMBURSEMENT WHERE EMPLOYEE_ID = ? AND PAD = 0 ORDER BY REIMBURSEMENT_ID";
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, EMPLOYEE_ID);
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				results.add(new Reimbursement(rs.getInt("REIMBURSEMENT_ID"), rs.getInt("EMPLOYEE_ID"), rs.getDouble("AMOUNT"), rs.getInt("PAD")));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (IOException e1) {
			e1.printStackTrace();
		} catch (NullPointerException e2) {
			e2.printStackTrace();
		}
		return results;
	}
	@Override
	public ArrayList<Reimbursement> getOtherReimbursements(int EMPLOYEE_ID) {
		ArrayList<Reimbursement> results = new ArrayList<>();
		try (Connection conn = ConnectionUtil.getConnection()) {
			String sql = "SELECT * FROM REIMBURSEMENT WHERE EMPLOYEE_ID = ? AND PAD = 1 ORDER BY REIMBURSEMENT_ID";
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, EMPLOYEE_ID);
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				results.add(new Reimbursement(rs.getInt("REIMBURSEMENT_ID"), rs.getInt("EMPLOYEE_ID"), rs.getDouble("AMOUNT"), rs.getInt("PAD")));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (IOException e1) {
			e1.printStackTrace();
		} catch (NullPointerException e2) {
			e2.printStackTrace();
		}
		try (Connection conn = ConnectionUtil.getConnection()) {
			String sql = "SELECT * FROM REIMBURSEMENT WHERE EMPLOYEE_ID = ? AND PAD = 2 ORDER BY REIMBURSEMENT_ID";
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, EMPLOYEE_ID);
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				results.add(new Reimbursement(rs.getInt("REIMBURSEMENT_ID"), rs.getInt("EMPLOYEE_ID"), rs.getDouble("AMOUNT"), rs.getInt("PAD")));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (IOException e1) {
			e1.printStackTrace();
		} catch (NullPointerException e2) {
			e2.printStackTrace();
		}
		return results;
	}
	@Override
	public void updateEmployee(int EMPLOYEE_ID, String USERNAME, String PASSWORD) {
		try (Connection conn = ConnectionUtil.getConnection()) {
			String sql = "UPDATE EMPLOYEE SET USERNAME = ?, PASSWORD = ? WHERE EMPLOYEE_ID = ?";
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, USERNAME);
			pstmt.setString(2, PASSWORD);
			pstmt.setInt(3,  EMPLOYEE_ID);
			pstmt.executeQuery();
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (IOException e1) {
			e1.printStackTrace();
		} catch (NullPointerException e2) {
			e2.printStackTrace();
		}
	}
	@Override
	public ArrayList<Reimbursement> getEmployeeReimbursements(int MANAGER_ID, int EMPLOYEE_ID) {
		ArrayList<Reimbursement> results = new ArrayList<Reimbursement>();
		ArrayList<Integer> employeeIDs = new ArrayList<Integer>();
		if (EMPLOYEE_ID == 0) {
			try (Connection conn = ConnectionUtil.getConnection()) {
				String sql = "SELECT EMPLOYEE_ID FROM EMPLOYEE WHERE MANAGER_ID = ?";
				PreparedStatement pstmt = conn.prepareStatement(sql);
				pstmt.setInt(1, MANAGER_ID);
				ResultSet rs = pstmt.executeQuery();
				while (rs.next()) {
					employeeIDs.add(rs.getInt("EMPLOYEE_ID"));
				}
			} catch (SQLException e) {
				e.printStackTrace();
			} catch (IOException e1) {
				e1.printStackTrace();
			} catch (NullPointerException e2) {
				e2.printStackTrace();
			}
		}
		else {
			employeeIDs.add(EMPLOYEE_ID);
		}
		for (int i : employeeIDs) {
			try (Connection conn = ConnectionUtil.getConnection()) {
				String sql = "SELECT * FROM REIMBURSEMENT WHERE EMPLOYEE_ID = ? AND PAD = 0 ORDER BY REIMBURSEMENT_ID";
				PreparedStatement pstmt = conn.prepareStatement(sql);
				pstmt.setInt(1, i);
				ResultSet rs = pstmt.executeQuery();
				while (rs.next()) {
					results.add(new Reimbursement(rs.getInt("REIMBURSEMENT_ID"), rs.getInt("EMPLOYEE_ID"), rs.getDouble("AMOUNT"), rs.getInt("PAD")));
				}
			} catch (SQLException e) {
				e.printStackTrace();
			} catch (IOException e1) {
				e1.printStackTrace();
			} catch (NullPointerException e2) {
				e2.printStackTrace();
			}
		}
		return results;
	}
	@Override
	public ArrayList<Reimbursement> getResolvedReimbursements() {
		ArrayList<Reimbursement> results = new ArrayList<>();
		try (Connection conn = ConnectionUtil.getConnection()) {
			String sql = "SELECT * FROM REIMBURSEMENT WHERE PAD = 1 ORDER BY REIMBURSEMENT_ID";
			PreparedStatement pstmt = conn.prepareStatement(sql);
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				results.add(new Reimbursement(rs.getInt("REIMBURSEMENT_ID"), rs.getInt("EMPLOYEE_ID"), rs.getDouble("AMOUNT"), rs.getInt("PAD")));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (IOException e1) {
			e1.printStackTrace();
		} catch (NullPointerException e2) {
			e2.printStackTrace();
		}
		try (Connection conn = ConnectionUtil.getConnection()) {
			String sql = "SELECT * FROM REIMBURSEMENT WHERE PAD = 2 ORDER BY REIMBURSEMENT_ID";
			PreparedStatement pstmt = conn.prepareStatement(sql);
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				results.add(new Reimbursement(rs.getInt("REIMBURSEMENT_ID"), rs.getInt("EMPLOYEE_ID"), rs.getDouble("AMOUNT"), rs.getInt("PAD")));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (IOException e1) {
			e1.printStackTrace();
		} catch (NullPointerException e2) {
			e2.printStackTrace();
		}
		return results;
	}
	@Override
	public ArrayList<Employee> getEmployees() {
		ArrayList<Employee> results = new ArrayList<>();
		try (Connection conn = ConnectionUtil.getConnection()) {
			String sql = "SELECT * FROM EMPLOYEE ORDER BY EMPLOYEE_ID";
			PreparedStatement pstmt = conn.prepareStatement(sql);
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				results.add(new Employee(rs.getInt("EMPLOYEE_ID"), rs.getString("USERNAME"), rs.getInt("MANAGER_ID")));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (IOException e1) {
			e1.printStackTrace();
		} catch (NullPointerException e2) {
			e2.printStackTrace();
		}
		return results;
	}
	@Override
	public void approve(int REIMBURSEMENT_ID) throws IOException {
		try (Connection conn = ConnectionUtil.getConnection()) {
			String sql = "UPDATE REIMBURSEMENT SET PAD = 1 WHERE REIMBURSEMENT_ID = ?";
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1,  REIMBURSEMENT_ID);
			pstmt.executeQuery();
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (IOException e1) {
			e1.printStackTrace();
		} catch (NullPointerException e2) {
			e2.printStackTrace();
		}	
		int EMPLOYEE_ID = 0;
		try (Connection conn = ConnectionUtil.getConnection()) {
			String sql = "SELECT EMPLOYEE_ID FROM REIMBURSEMENT WHERE REIMBURSEMENT_ID = ?";
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, REIMBURSEMENT_ID);
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				EMPLOYEE_ID = rs.getInt("EMPLOYEE_ID");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (IOException e1) {
			e1.printStackTrace();
		} catch (NullPointerException e2) {
			e2.printStackTrace();
		}
		if (EMPLOYEE_ID != 0)
			resolveEmail(EMPLOYEE_ID, REIMBURSEMENT_ID);
	}
	@Override
	public void deny(int REIMBURSEMENT_ID) throws IOException{
		try (Connection conn = ConnectionUtil.getConnection()) {
			String sql = "UPDATE REIMBURSEMENT SET PAD = 2 WHERE REIMBURSEMENT_ID = ?";
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1,  REIMBURSEMENT_ID);
			pstmt.executeQuery();
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (IOException e1) {
			e1.printStackTrace();
		} catch (NullPointerException e2) {
			e2.printStackTrace();
		}	
		int EMPLOYEE_ID = 0;
		try (Connection conn = ConnectionUtil.getConnection()) {
			String sql = "SELECT EMPLOYEE_ID FROM REIMBURSEMENT WHERE REIMBURSEMENT_ID = ?";
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, REIMBURSEMENT_ID);
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				EMPLOYEE_ID = rs.getInt("EMPLOYEE_ID");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (IOException e1) {
			e1.printStackTrace();
		} catch (NullPointerException e2) {
			e2.printStackTrace();
		}
		if (EMPLOYEE_ID != 0)
			resolveEmail(EMPLOYEE_ID, REIMBURSEMENT_ID);
	}
	@Override
	public void resetPassword(String EMAIL, String USERNAME, String PASSWORD) {
		try (Connection conn = ConnectionUtil.getConnection()) {
			String sql = "UPDATE EMPLOYEE SET PASSWORD = ? WHERE EMAIL = ? AND USERNAME = ?";
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, PASSWORD);
			pstmt.setString(2, EMAIL);
			pstmt.setString(3,  USERNAME);
			pstmt.executeQuery();
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (IOException e1) {
			e1.printStackTrace();
		} catch (NullPointerException e2) {
			e2.printStackTrace();
		}
	}
	@Override
	public String randomString() {
        String charOptions = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        StringBuilder str = new StringBuilder();
        Random rnd = new Random();
        while (str.length() < 10) { // length of the random string.
            int index = (int) (rnd.nextFloat() * charOptions.length());
            str.append(charOptions.charAt(index));
        }
        String saltStr = str.toString();
        return saltStr;
    }
	@Override
	public void sendEmail(String EMAIL, String USERNAME, String PASSWORD) throws IOException {
		Email from = new Email("raymondjhua@gmail.com");
	    String subject = "facebook Employee Updated Login Information";
	    Email to = new Email(EMAIL.toLowerCase());
	    Content content = new Content("text/plain", "Hello, your USERNAME is:" + USERNAME + ", and your new temporary PASWORD is:" + PASSWORD);
	    Mail mail = new Mail(from, subject, to, content);
	    System.out.println(PASSWORD);
	    SendGrid sg = new SendGrid(System.getenv("SENDGRID_API_KEY"));
	    Request request = new Request();
	    try {
	      request.setMethod(Method.POST);
	      request.setEndpoint("mail/send");
	      request.setBody(mail.build());
	      Response response = sg.api(request);
	      System.out.println(response.getStatusCode());
	      System.out.println(response.getBody());
	      System.out.println(response.getHeaders());
	    } catch (IOException ex) {
	      throw ex;
	    }
	    System.out.println("Done");
	}

	public void newEmployee(String USERNAME, String PASSWORD, int MANAGER_ID, String EMAIL) {
		try (Connection conn = ConnectionUtil.getConnection()) {
			String sql = "INSERT INTO EMPLOYEE (USERNAME, PASSWORD, MANAGER_ID, EMAIL) VALUES (?, ?, ?, ?)";
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, USERNAME);
			pstmt.setString(2, PASSWORD);
			pstmt.setInt(3, MANAGER_ID);
			pstmt.setString(4,  EMAIL);
			pstmt.executeQuery();
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (IOException e1) {
			e1.printStackTrace();
		} catch (NullPointerException e2) {
			e2.printStackTrace();
		}		
	}
	
	private void resolveEmail(int EMPLOYEE_ID, int REIMBURSEMENT_ID) throws IOException {
		String USERNAME = "";
		String EMAIL = "";
		try (Connection conn = ConnectionUtil.getConnection()) {
			String sql = "SELECT USERNAME, EMAIL FROM EMPLOYEE WHERE EMPLOYEE_ID = ?";
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, EMPLOYEE_ID);
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				USERNAME = rs.getString("USERNAME");
				EMAIL = rs.getString("EMAIL");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (IOException e1) {
			e1.printStackTrace();
		} catch (NullPointerException e2) {
			e2.printStackTrace();
		}
		Email from = new Email("raymondjhua@gmail.com");
	    String subject = "Resolved Reimbursement Request";
	    Email to = new Email(EMAIL.toLowerCase());
	    Content content = new Content("text/plain", "Hello, " + USERNAME + " your reimbursement request id#" + REIMBURSEMENT_ID + " has been resolved, please log into the employee portal to see its status.");
	    Mail mail = new Mail(from, subject, to, content);
	    SendGrid sg = new SendGrid(System.getenv("SENDGRID_API_KEY"));
	    Request request = new Request();
	    try {
	      request.setMethod(Method.POST);
	      request.setEndpoint("mail/send");
	      request.setBody(mail.build());
	      Response response = sg.api(request);
	      System.out.println(response.getStatusCode());
	      System.out.println(response.getBody());
	      System.out.println(response.getHeaders());
	    } catch (IOException ex) {
	      throw ex;
	    }
	    System.out.println("Done");
		
	}
}
