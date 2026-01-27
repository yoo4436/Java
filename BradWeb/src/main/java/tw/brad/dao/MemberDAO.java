package tw.brad.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import tw.brad.apis.BCrypt;
import tw.brad.apis.Gift;
import tw.brad.apis.Member;

public class MemberDAO {
	private static final String url ="jdbc:mysql://localhost:3306/iii";
	private static final String user = "root";
	private static final String pw = "root";
	
	private static final String sql_login = """
			select id , email, pw, name
			from member
			where email = ?
			""";
	
	private static final String sql_query_email = """
			select id , email, pw, name
			from member
			where email = ?
			""";
	
	private static final String sql_add = """
			insert into member
				(email, pw, name)
			values
				(?,?,?)
			""";
	
	public Member findByEmail(String email) throws SQLException {
		ResultSet rs = null;
		try(Connection conn = DriverManager.getConnection(url, user, pw);
				PreparedStatement pstmt = conn.prepareStatement(sql_query_email);
				){
			pstmt.setString(1, email);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				Member member = new Member();
				member.setId(rs.getLong("id"));
				member.setEmail(rs.getString("email"));
				member.setPwd("pw");
				member.setName(rs.getString("name"));
				return member;
			}else {
				return null;
			}
			
		}catch (Exception e){
			System.out.println(e);
		}finally {
			rs.close();
		}
		return null;

	}
	
	public boolean addMember(Member member) {
		try(Connection conn = DriverManager.getConnection(url, user, pw);
				PreparedStatement pstmt = conn.prepareStatement(sql_add);
				){
			pstmt.setString(1, member.getEmail());
			pstmt.setString(2, BCrypt.hashpw(member.getPwd(), BCrypt.gensalt()) );
			pstmt.setString(3, member.getName());
			return pstmt.executeUpdate() > 0;
		}catch (Exception e) {
			return false;
		}
	}
	
	public Member login(String email, String pwd) throws Exception {
		ResultSet rs = null;
		try(Connection conn = DriverManager.getConnection(url, user, pw);
				PreparedStatement pstmt = conn.prepareStatement(sql_login);
				){
			pstmt.setString(1, email);
			rs = pstmt.executeQuery();
			if (rs.next() && BCrypt.checkpw(pwd, rs.getString("pw"))) {
				Member member = new Member();
				member.setId(rs.getLong("id"));
				member.setEmail(rs.getString("email"));
				member.setPwd("pw");
				member.setName(rs.getString("name"));
				return member;
			}else {
				return null;
			}
			
		}catch (Exception e){
			System.out.println(e);
		}finally {
			rs.close();
		}
		return null;
	}
	
}
