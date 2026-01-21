package tw.brad.apis;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class JdbcTool {
	private static final String url ="jdbc:mysql://localhost:3306/iii";
	private static final String user = "root";
	private static final String pw = "root";

	public <T> List<T> query(String sql, RowMapper<T> rowMapper, Object... args) {
		List<T> list = new ArrayList<>();
		
		try (Connection conn = DriverManager.getConnection(url, user, pw);
				PreparedStatement pstmt = conn.prepareStatement(sql)){
			for(int i=0; i<args.length; i++) {
				pstmt.setObject(i+1, args[i]);
			}
			
			try (ResultSet rs = pstmt.executeQuery()){
				while (rs.next()) {
					T item = rowMapper.mapRow(rs);
					list.add(item);
				}
			}
			
			
		} catch (Exception e) {
			System.out.println(e);
		}
		
		
		return list;
	}
	
}
