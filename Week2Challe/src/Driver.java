import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class Driver {
	public static void main(String[] args) {
		/*
		try {
			Connection conn = ConnectionUtil.getConnection();
			System.out.println(conn);
			System.out.println(conn.getMetaData().getDatabaseMajorVersion());
		} catch(Exception e) {
			e.printStackTrace();
		}
		*/
		
		DBDAO cd = new DBDAOImpl();
//		for (DB c : cd.getDBs()) {
//			System.out.println(c);
//		}
//		System.out.println(cd.getDBById(1));
		int dept = 3;
		System.out.println("The current average salary for department " + dept + " is $"+ cd.averageDBDepartment(dept));
		cd.raise(dept);
		System.out.println("The average salary after a raise for department " + dept + " is $"+ cd.averageDBDepartment(dept));
	}

}