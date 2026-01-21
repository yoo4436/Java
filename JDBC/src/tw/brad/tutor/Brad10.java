package tw.brad.tutor;

import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Properties;
import java.util.Scanner;

import tw.brad.apis.BCrypt;

public class Brad10 {
	private static final String url ="jdbc:mysql://localhost:3306/iii";
	private static final String user = "root";
	private static final String pw = "root";
	private static final String sql_reg = """ 
			insert into member
				(email, pw, name)
			values
				(?,?,?)
			""";
	
	private static final String sql_check1 = """ 
			select email from member
			where email = ?
			""";
	
	private static final String sql_check2 = """ 
			select count(email) count from member
			where email = ?
			""";
	
	private static final Properties prop = new Properties();
	
	public static void main(String[] args) {
		System.out.println("Member Register");
		Scanner scanner = new Scanner(System.in);
		System.out.println("Email: ");
		String email = scanner.nextLine();
		System.out.println("Password: ");
		String pwd = scanner.nextLine();
		System.out.println("Name: ");
		String name = scanner.nextLine();
		
		prop.put("user", user);
		prop.put("password", pw);

		
		try {
			if(!isEmailExist(email)) {
				if (regMember(email, pwd, name)) {
					System.out.println("Register Success");
				}else {
					System.out.println("Register Failure");
				}
			}else {
				System.out.println("Email XXX");
			}
		} catch (Exception e) {
			System.out.println("Error: 500");
		}

		
		

		
	}
	
	static boolean isEmailExist(String email) {
//		try(Connection conn = DriverManager.getConnection(url, prop);
//				PreparedStatement pstmt = conn.prepareStatement(sql_check1);) {
//			pstmt.setString(1, email);
//			ResultSet rs = pstmt.executeQuery();
//			return rs.next();
//		} catch (SQLException e) {
//			System.out.println(e);
//		}
		
		try(Connection conn = DriverManager.getConnection(url, prop);
				PreparedStatement pstmt = conn.prepareStatement(sql_check2);) {
			pstmt.setString(1, email);
			ResultSet rs = pstmt.executeQuery();
			rs.next();
			int count = rs.getInt("count");
			return count > 0;
		} catch (SQLException e) {
			System.out.println(e);
		}
		
		return true;
	}
	
	static boolean regMember(String email, String pwd, String name) {
		try(Connection conn = DriverManager.getConnection(url,prop);
				PreparedStatement pstmt = conn.prepareStatement(sql_reg)) {
			
			pstmt.setString(1, email);
			pstmt.setString(2, BCrypt.hashpw(pwd, BCrypt.gensalt()));
			pstmt.setString(3, name);
			int n = pstmt.executeUpdate();
			return n > 0;
		} catch (Exception e) {
			System.out.println(e);
		}
		return true;
	}
}
