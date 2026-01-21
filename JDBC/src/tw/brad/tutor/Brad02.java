package tw.brad.tutor;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class Brad02 {

	public static void main(String[] args) {
		/*
		 * sql server
		 * url = "jdbc:sqlserver://localhost:1433;databaseName=iii;user=sa;password=123456" 
		 */
		String url ="jdbc:mysql://localhost:3306/iii";
		String user = "root";
		String pw = "root";
		
		Properties prop = new Properties();
		prop.put("user", user);
		prop.put("password", pw);
		
		try {
			Connection conn = DriverManager.getConnection(url, prop);
			conn.close();
			System.out.println("OK");
		} catch (SQLException e) {
			System.out.println(e);
		}
	}

}
