package tw.brad.tutor;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Properties;
import java.util.Scanner;

public class Brad05 {
	private static final String url ="jdbc:mysql://localhost:3306/iii";
	private static final String user = "root";
	private static final String pw = "root";
	private static final String sql_insert = """ 
			insert into cust
				(cname, tel, birth)
			values
				(?,?,?)
			""";
	
	public static void main(String[] args) {
		Properties prop = new Properties();
		prop.put("user", user);
		prop.put("password", pw);

		try (Connection conn = DriverManager.getConnection(url, prop);
				PreparedStatement pstmt = conn.prepareStatement(sql_insert)){

			Scanner scanner =new Scanner(System.in);
			System.out.println("Name:");
			String cname = scanner.next();
			System.out.println("Tel:");
			String tel = scanner.next();
			System.out.println("Birthday:");
			String birth = scanner.next();
			
			pstmt.setString(1, cname);
			pstmt.setString(2, tel);
			pstmt.setString(3, birth);
			
		} catch (Exception e) {
			System.out.println(e);
		}
	}

}
