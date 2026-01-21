package tw.brad.tutor;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

import tw.brad.apis.Bike;

public class Brad16 {
	private static final String url ="jdbc:mysql://localhost:3306/iii";
	private static final String user = "root";
	private static final String pw = "root";
	private static final String sql_update = """
			update member 
			set bike = ?
			where id = ?
			""";

	public static void main(String[] args) {
		Bike bike = new Bike();
		bike.accelerate().accelerate().accelerate().accelerate().accelerate();
		System.out.println(bike);
		
		try (Connection conn = DriverManager.getConnection(url, user, pw);
				PreparedStatement pstmt = conn.prepareStatement(sql_update)){
			
			pstmt.setObject(1, bike);
			pstmt.setInt(2, 1);
			int n = pstmt.executeUpdate();
			System.out.println(n);
			
		}catch (Exception e) {
			System.out.println(e);
		}
	}

}
