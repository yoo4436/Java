package tw.brad.apis;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;

public class GiftDAO {
	private static final String url ="jdbc:mysql://localhost:3306/iii";
	private static final String user = "root";
	private static final String pw = "root";
	
	private static final String sql_find_all = """
			select id 編號, name 名稱, feature 特色, addr 地址, tel 電話
			from gift
			order by id
			""";
	
	private static PreparedStatement pstmt;
	private static Connection conn;
	private static ResultSet rs;
	private static String[] fieldNames;
	
	public GiftDAO() throws Exception{
		conn = DriverManager.getConnection(url, user, pw);
		pstmt = conn.prepareStatement(sql_find_all,
				ResultSet.TYPE_SCROLL_SENSITIVE,
				ResultSet.CONCUR_UPDATABLE);
		
		rs = pstmt.executeQuery();
		ResultSetMetaData rsmd = rs.getMetaData();
		
		fieldNames = new String[rsmd.getColumnCount()];
		for (int i=0; i<fieldNames.length; i++) {
			fieldNames[i] = rsmd.getColumnLabel(i+1);
		}
	}
	
	public int getRows() {
		try {
			rs.last();
			return rs.getRow();
		} catch (SQLException e) {
			return 0;
		}
		
	}
	
	public int getCols() {
		return fieldNames.length;
	}
	
	//0-base
	public String getData(int row, int col) {
		try {
			rs.absolute(row+1);
			return rs.getString(col+1);
		} catch (SQLException e) {
			return "Err";
		}
	}
	
	public String[] getFields() {
		return fieldNames;
	}
	
	public void updateData(String newData, int row, int col) {
		
		try {
			rs.absolute(row+1);
			rs.updateString(col+1, newData);
			rs.updateRow();
		} catch (SQLException e) {
			System.out.println(e);
		}
	}
	
	public void delRow(int row) {
		try {
			rs.absolute(row+1);
			rs.deleteRow();
		} catch (SQLException e) {
			System.out.println(e);
		}
		
	}
}
