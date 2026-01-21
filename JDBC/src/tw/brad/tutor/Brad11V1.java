package tw.brad.tutor;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Properties;
import java.util.Scanner;

import tw.brad.apis.BCrypt;
import tw.brad.apis.Member;

public class Brad11V1 {
	private static final String url ="jdbc:mysql://localhost:3306/iii";
	private static final String user = "root";
	private static final String pw = "root";
	private static final String sql_login = """
			select id, email, pw, name from member
			where email = ?
			""";
	
	private static final String sql_chpw = """
			update member
			set pw = ?
			where id = ?
			""";

	private static final Properties prop = new Properties();
		
	public static void main(String[] args) {
		prop.put("user", user);
		prop.put("password", pw);
		
		//Login
		Member member = login();
		if (member != null) {
			System.out.printf("Welcome, %s\n", member.getName());
			// Change PW
			boolean isOK = chPW(member);
		}
		
		
	}
	
	static Member login() {
		System.out.println("Member Login");
		Scanner scanner = new Scanner(System.in);
		System.out.println("Email: ");
		String email = scanner.nextLine();
		System.out.println("Password: ");
		String pwd = scanner.nextLine();
		
		
		try(Connection conn = DriverManager.getConnection(url, prop);
				PreparedStatement pstmt = conn.prepareStatement(sql_login)) {
			pstmt.setString(1, email);
			ResultSet rs = pstmt.executeQuery();
			if (rs.next()) {
				if (BCrypt.checkpw(pwd, rs.getString("pw"))) {
					Member member = new Member(
						rs.getLong("id"), 
						rs.getString("email"), 
						rs.getString("name"), 
						rs.getString("pw"));
					System.out.printf("Welcome, %s",member.getName());
//					System.out.println("Change Password: ");
//					String chPW = scanner.nextLine();
					return member;
				}else {
					System.out.println("Login Fail2");
				}
			}else {
				System.out.println("Login Fai1");
			}
		} catch (Exception e) {
			System.out.println(e);
		}

		return null;
	}
	
	static boolean chPW(Member member) {
		Scanner scanner = new Scanner(System.in);
		
		String chPW;
		
		String regex = "^(?=.*\\d)(?=.*[a-z])(?=.*[A-Z]).{8,}";
		
		
		
		while(true) {
			System.out.println("Change Password: (Must be 8+ chars, "
					+ "contain upper letter,lower letter and number)");
			System.out.println("Enter 'Space' to cancel");
			chPW = scanner.nextLine();
			
			if (chPW.equals(" ")) {
				System.out.println("Change Canceled");
				return false;
			}
			
			if (chPW.matches(regex)) {
				break;
			}else {
				System.out.println("Password Format Error");
			}
		}
		
		try(Connection conn = DriverManager.getConnection(url, prop);
				PreparedStatement pstmt = conn.prepareStatement(sql_chpw)) {
			
			pstmt.setString(1, BCrypt.hashpw(chPW, BCrypt.gensalt()));
			pstmt.setLong(2, member.getId());
			
			int n = pstmt.executeUpdate();
			if(n>0) {
				System.out.println("Change Success!");
				return true;
			}else {
				System.out.println("Change Fail2");
				return false;
			}
		} catch (Exception e) {
			System.out.println("Change Fai1" + e);
			return false;
		}
	}
}


