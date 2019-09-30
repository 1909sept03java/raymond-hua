package com.revature.dao;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.util.ArrayList;
import java.util.Properties;

import com.revature.beans.Employee;
import com.revature.beans.Reimbursement;
import com.revature.util.ConnectionUtil;

public class P1DaoImpl implements P1DAO {

	@Override
	public Employee Authenticate(Employee E) {
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
		return result;
	}

	public boolean isEmmMan(Employee E) {
		boolean isManager = false;
		try (Connection conn = ConnectionUtil.getConnection()) {
			String sql = "SELECT MANAGER_ID FROM EMPLOYEE WHERE MANAGER_ID = ?";
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, E.getEmployee_id());
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				if (rs.getInt("MANAGER_ID") == E.getEmployee_id())
					;
				return true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (IOException e1) {
			e1.printStackTrace();
		} catch (NullPointerException e2) {
			e2.printStackTrace();
		}
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
			ResultSet rs = pstmt.executeQuery();
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
}
