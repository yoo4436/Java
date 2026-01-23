package tw.brad.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import tw.brad.apis.Gift;

public class GiftDAO {
	private static final String url ="jdbc:mysql://localhost:3306/iii";
	private static final String user = "root";
	private static final String pw = "root";
	
	private static final String sql_find_all = """
			select id , name , feature , addr , tel 
			from gift
			order by id
			""";
	

	
	public GiftDAO(){

	}
	
	public List<Gift> findAll() throws Exception {
		List<Gift> gifts = new ArrayList<Gift>();
		try(Connection conn = DriverManager.getConnection(url, user, pw);
				PreparedStatement pstmt = conn.prepareStatement(sql_find_all);
				ResultSet rs = pstmt.executeQuery();
				){
			while (rs.next()) {
				Gift gift = new Gift();
				gift.setId(rs.getLong("id"));
				gift.setName(rs.getString("name"));
				gift.setFeature(rs.getString("feature"));
				gift.setAddr(rs.getString("addr"));
				gift.setTel(rs.getString("tel"));
				gift.setPicurl(rs.getString("picurl"));
				gifts.add(gift);
			}
		}
		return gifts;
	}
	
}
