package tw.brad.tutor;

import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

public class Brad14 {
	private static final String url ="jdbc:mysql://localhost:3306/iii";
	private static final String user = "root";
	private static final String pw = "root";
	private static final String sql_update = """
			update member 
			set icon = ?
			where id = ?
			""";
	
	public static void main(String[] args) {
		try (FileInputStream fin =new FileInputStream("dir1/ball2.png");
				Connection conn = DriverManager.getConnection(url, user, pw);
				PreparedStatement pstmt = conn.prepareStatement(sql_update)){
			pstmt.setBinaryStream(1, fin);
			pstmt.setInt(2, 2);
			int n = pstmt.executeUpdate();
			System.out.println(n);
			
		}catch (Exception e) {
			System.out.println(e);
		}
	}

}
