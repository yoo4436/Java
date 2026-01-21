package tw.brad.tutor;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Iterator;
import java.util.List;

import tw.brad.apis.Gift;
import tw.brad.apis.JdbcTool;
import tw.brad.apis.Member;
import tw.brad.apis.RowMapper;

public class Brad13 {

	public static void main(String[] args) {
		JdbcTool tool = new JdbcTool();
		String sql = """
				select email, name
				from member
				""";
		
		List<Member> gifts = tool.query(sql, rs -> {
			Member member = new Member();
			member.setEmail(rs.getString("email"));
			member.setName(rs.getString("name"));
			return member;
		});
		
		gifts.forEach(System.out::println);
	}

}
