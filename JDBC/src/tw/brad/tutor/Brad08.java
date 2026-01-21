package tw.brad.tutor;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Properties;
import java.util.Scanner;

public class Brad08 {
	private static final String url ="jdbc:mysql://localhost:3306/iii";
	private static final String user = "root";
	private static final String pw = "root";
	private static final String sql_query = """
			select id, name, tel, addr, feature from gift
			where name like ? or tel like ? or addr like ? or feature like?
			order by id limit ?, ?
			""";
	
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		System.out.print("Page: ");
		int page = scanner.nextInt();
		System.out.print("Keyword: ");
		String key = scanner.next();
		String skey = "%" + key + "%";
		
		final int rpp = 10;
		int start = (page - 1) * rpp;
		
		Properties prop = new Properties();
		prop.put("user", user);
		prop.put("password", pw);
		
		try (Connection conn = DriverManager.getConnection(url, prop);
			 PreparedStatement pstmt = conn.prepareStatement(sql_query)){
			
			pstmt.setString(1, skey);
			pstmt.setString(2, skey);
			pstmt.setString(3, skey);
			pstmt.setString(4, skey);
			pstmt.setInt(5, start);
			pstmt.setInt(6, rpp);
			
			ResultSet rs = pstmt.executeQuery();
			while(rs.next()) {
				String id = rs.getString("id");
				String name = rs.getString("name");
				String tel = rs.getString("tel");
				String addr = rs.getString("addr");
				String feature = rs.getString("feature");
				System.out.printf("%s:%s:%s:%s\n\t%s\n", id, name, tel, addr, feature);
			}
			
		
		}catch (Exception e) {
			System.out.println(e);
		}

	}

}
