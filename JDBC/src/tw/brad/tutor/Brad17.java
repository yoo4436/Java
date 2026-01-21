package tw.brad.tutor;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import tw.brad.apis.Bike;

public class Brad17 {
	private static final String url ="jdbc:mysql://localhost:3306/iii";
	private static final String user = "root";
	private static final String pw = "root";
	private static final String sql_query = """
			select id, email, name, bike 
			from member 
			where id = ?
			""";
	
	public static void main(String[] args) {
		try (Connection conn = DriverManager.getConnection(url, user, pw);
				PreparedStatement pstmt = conn.prepareStatement(sql_query)){
			
			pstmt.setInt(1, 1);
			ResultSet rs = pstmt.executeQuery();
			if(rs.next()) {
				InputStream in = rs.getBinaryStream("bike");
				ObjectInputStream oin = new ObjectInputStream(in);
				Object obj = oin.readObject();
				if (obj instanceof Bike) {
					Bike bike = (Bike)obj;
					System.out.println(bike);
				}
			}else {
				System.out.println("Member does not exist");
			}
			
		}catch (Exception e) {
			System.out.println(e);
		}
	}

}
