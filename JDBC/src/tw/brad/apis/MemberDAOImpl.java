package tw.brad.apis;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class MemberDAOImpl implements MemberDAO {
	private static final String url ="jdbc:mysql://localhost:3306/iii";
	private static final String user = "root";
	private static final String pw = "root";
	private static final String sql_add = """
			insert into member
			(email, pw, name)
			values
			(?,?,?)
			""";	
	
	private static final String sql_update = """
			update member
			set email = ?, pw = ?, name = ?
			where id = ?
			""";
	
	private static final String sql_delete = """
			delete from member
			where id = ?
			""";
	
	private static final String sql_find_id = """
			select id, email, name
			from member
			where id = ?
			""";
	
	private static final String sql_find_email = """
			select id, email, pw, name
			from member
			where id = ?
			""";
	
	private static final String sql_find_all = """
			select id, email, name
			from member
			""";
	
	@Override
	public boolean addMember(Member member) throws Exception {
		try (Connection conn = DriverManager.getConnection(url, user, pw);
				PreparedStatement pstmt = conn.prepareStatement(sql_add)) {
			pstmt.setString(1, member.getEmail());
			pstmt.setString(2, BCrypt.hashpw(member.getPasswd(), BCrypt.gensalt()));
			pstmt.setString(3, member.getName());
			return pstmt.executeUpdate() > 0;
		}
	}

	@Override
	public boolean updateMember(Member member) throws Exception {
		try (Connection conn = DriverManager.getConnection(url, user, pw);
				PreparedStatement pstmt = conn.prepareStatement(sql_update)) {
			pstmt.setString(1, member.getEmail());
			pstmt.setString(2, BCrypt.hashpw(member.getPasswd(), BCrypt.gensalt()));
			pstmt.setString(3, member.getName());
			pstmt.setLong(4, member.getId());
			return pstmt.executeUpdate() > 0;
		}		
	}

	@Override
	public boolean delMember(long id) throws Exception {
		try (Connection conn = DriverManager.getConnection(url, user, pw);
				PreparedStatement pstmt = conn.prepareStatement(sql_delete)) {
			pstmt.setLong(1, id);
			return pstmt.executeUpdate() > 0;
		}
	}

	@Override
	public Member findById(long id) throws Exception {
		try (Connection conn = DriverManager.getConnection(url, user, pw);
				PreparedStatement pstmt = conn.prepareStatement(sql_find_id)) {
			pstmt.setLong(1, id);
			ResultSet rs = pstmt.executeQuery();
			if(rs.next()) {
				return new Member(rs.getLong("id"),
						rs.getString("email"),
						null,
						rs.getString("name"));
			}
		}
		return null;
	}

	public Member findByEmail(String email) throws SQLException {
		try (Connection conn = DriverManager.getConnection(url, user, pw);
				PreparedStatement pstmt = conn.prepareStatement(sql_find_email)) {
			pstmt.setString(1, email);
			ResultSet rs = pstmt.executeQuery();
			if(rs.next()) {
				return new Member(rs.getLong("id"),
						rs.getString("email"),
						null,
						rs.getString("name"));
			}
		}
		return null;
	}
	
	@Override
	public List<Member> findAll() throws Exception {
		try (Connection conn = DriverManager.getConnection(url, user, pw);
				PreparedStatement pstmt = conn.prepareStatement(sql_find_all)) {
			
			List<Member> list = new ArrayList<>();
			ResultSet rs = pstmt.executeQuery();
			while(rs.next()) {
				list.add(new Member(rs.getLong("id"),
						rs.getString("email"),
						rs.getString("pw"),
						rs.getString("name"))) ;
			}
			return list;
		}
	}

	@Override
	public Member login(String email, String pwd) throws Exception {
		Member member = findByEmail(email);
		if (member != null && BCrypt.checkpw(pwd, member.getPasswd())) {
			return member;
		}
		return null;
	}
}
