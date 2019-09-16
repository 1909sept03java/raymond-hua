import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class DBDAOImpl implements DBDAO {

	@Override
	public List<DB> getDBs() {
		List<DB> cl = new ArrayList<>();
		// try-with-resources... resources included in the try args will be closed at the end of the block
		// works with all AutoCloseable resources
		try (Connection conn = ConnectionUtil.getConnection()) {
			String sql = "SELECT * FROM EMPLOYEE";
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(sql);
			while(rs.next()) {
				int eID = rs.getInt("EMPLOYEE_ID");
				String eFName = rs.getString("EMP_FIRSTNAME");
				String eLName = rs.getString("EMP_LASTNAME");
				int eDID = rs.getInt("DEPARTMENT_ID");
				int eSalary = rs.getInt("SALARY");
				String eEmail = rs.getString("EMP_EMAIL");
				cl.add(new DB(eID, eFName, eLName, eDID, eSalary, eEmail));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		return cl;
	}

	@Override
	public DB getDBById(int id) {
		DB c = null;
		// try-with-resources... resources included in the try args will be closed at the end of the block
		// works with all AutoCloseable resources
		try (Connection conn = ConnectionUtil.getConnection()) {
			String sql = "SELECT * FROM EMPLOYEE WHERE EMPLOYEE_ID = ?";
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, id);
			ResultSet rs = pstmt.executeQuery();
			while(rs.next()) {
				int eID = rs.getInt("EMPLOYEE_ID");
				String eFName = rs.getString("EMP_FIRSTNAME");
				String eLName = rs.getString("EMP_LASTNAME");
				int eDID = rs.getInt("DEPARTMENT_ID");
				int eSalary = rs.getInt("SALARY");
				String eEmail = rs.getString("EMP_EMAIL");
				c = new DB(eID, eFName, eLName, eDID, eSalary, eEmail);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		return c;
	}

	@Override
	public void createDB(DB cave) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void updateDB(DB cave) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deleteDB(DB cave) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public double averageDBDepartment(int id) {
		double average = 0.000f;
		List<DB> cl = new ArrayList<>();
		// try-with-resources... resources included in the try args will be closed at the end of the block
		// works with all AutoCloseable resources
		try (Connection conn = ConnectionUtil.getConnection()) {
			String sql = "SELECT * FROM EMPLOYEE WHERE DEPARTMENT_ID = ?";
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, id);
			ResultSet rs = pstmt.executeQuery();	
			while(rs.next()) {
				int eID = rs.getInt("EMPLOYEE_ID");
				String eFName = rs.getString("EMP_FIRSTNAME");
				String eLName = rs.getString("EMP_LASTNAME");
				int eDID = rs.getInt("DEPARTMENT_ID");
				double eSalary = rs.getInt("SALARY");
				String eEmail = rs.getString("EMP_EMAIL");
				cl.add(new DB(eID, eFName, eLName, eDID, eSalary, eEmail));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		DB c = null;
		for (int i = 0; i < cl.size(); i++) {
			c = cl.get(i);
			average += c.getSalary();
		}
		average = ((double)average/cl.size());
		return average;
	}

	@Override
	public void raise(int id) {
		try (Connection conn = ConnectionUtil.getConnection()) {
			String sql = "EXECUTE SP_GIVE_RAISE(?)";
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, id);
			//ResultSet rs = pstmt.executeQuery();	
			pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (IOException e1) {
			e1.printStackTrace();
		}
	}

}