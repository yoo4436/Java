package tw.brad.tutor;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Properties;

public class Brad03 {
	private static final String url ="jdbc:mysql://localhost:3306/iii";
	private static final String user = "root";
	private static final String pw = "root";
	private static final String sql_insert = """ 
			insert into cust 
				(cname, tel, birth)
			values
				('Amy', '123456', '2099-06-08')
			""";
	
	private static final String sql_update = """ 
			update cust
				set tel = '3333', birth = '2077-07-07'
			where
				id >= 4
			""";
	
	private static final String sql_delete = """ 
			delete from cust
			where
				id >= 5
			""";
	
	public static void main(String[] args) {
		Properties prop = new Properties();
		prop.put("user", user);
		prop.put("password", pw);

		try (Connection conn = DriverManager.getConnection(url, prop);
				Statement stmt = conn.createStatement()){
			int n = stmt.executeUpdate(sql_delete);
			System.out.println(n);
		} catch (Exception e) {
			System.out.println(e);
		}
	}

}
