package tw.brad.tutor;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class Brad15 {
	private static final String url ="jdbc:mysql://localhost:3306/iii";
	private static final String user = "root";
	private static final String pw = "root";
	private static final String sql_query = """
			select id, email, name, icon 
			from member 
			where id = ?
			""";
	
	public static void main(String[] args) {
		try (Connection conn = DriverManager.getConnection(url, user, pw);
				PreparedStatement pstmt = conn.prepareStatement(sql_query)){
			
			pstmt.setInt(1, 2);
			ResultSet rs = pstmt.executeQuery();
			if(rs.next()) {
				String email = rs.getString("email");
				String fname = String.format("dir2/%s.png", email);
				InputStream in = rs.getBinaryStream("icon");
				byte[] data = in.readAllBytes();
				
				try (FileOutputStream fout = new FileOutputStream(fname)){
					fout.write(data);	
					fout.flush();
				}
				System.out.println("OK2");
			}else {
				System.out.println("Member does not exist");
			}
			
		}catch (Exception e) {
			System.out.println(e);
		}
	}

}
