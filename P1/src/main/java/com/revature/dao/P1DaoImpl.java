package com.revature.dao;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Properties;

import com.revature.beans.Credentials;
import com.revature.beans.Employee;
import com.revature.beans.User;
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
			while(rs.next()) {
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
			while(rs.next()) {
				if(rs.getInt("MANAGER_ID") == E.getEmployee_id());
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
}
