package tw.brad.tutor;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class Brad20 {
	private static final String url ="jdbc:mysql://localhost:3306/northwind";
	private static final String user = "root";
	private static final String pw = "root";
	private static final String sql_query = """
			SELECT e.EmployeeID id,e.LastName name,SUM(od.UnitPrice*od.Quantity) sum
			FROM `orders` o 
				JOIN employees e on o.EmployeeID = e.EmployeeID
			    JOIN orderdetails od ON o.OrderID = od.OrderID
			GROUP BY o.EmployeeID
			ORDER BY sum DESC
			""";
	
	public static void main(String[] args) {
		try (Connection conn = DriverManager.getConnection(url, user, pw);
				PreparedStatement pstmt = conn.prepareStatement(sql_query);
				ResultSet rs = pstmt.executeQuery()){
				
			while (rs.next()) {
				String id = rs.getString("id");
				String name = rs.getString("name");
				String sum = rs.getString("sum");
				System.out.printf("%s:%s:%s\n", id, name, sum);
			}
		}catch (Exception e) {
			System.out.println(e);
		}
	}

}
