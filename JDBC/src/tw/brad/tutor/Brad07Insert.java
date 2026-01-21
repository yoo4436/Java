package tw.brad.tutor;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Properties;

import org.json.JSONArray;
import org.json.JSONObject;

public class Brad07Insert {
	private static final String url_od = "https://data.moa.gov.tw/Service/OpenData/ODwsv/ODwsvAgriculturalProduce.aspx";

	private static final String url ="jdbc:mysql://localhost:3306/iii";
	private static final String user = "root";
	private static final String pw = "root";
	private static final String sql_insert = """ 
			insert into gift
				(name,feature,addr,tel,picurl,lat,lng)
			values
				(?,?,?,?,?,?,?)
			""";
	
	private static final String sql_del_all = """ 
			delete from gift
			""";
	
	private static final String sql_resetID = """ 
			alter table gift auto_increment = 1
			""";
	
	public static void main(String[] args) {
		String json;
		try {
			json = fetchFromURL(url_od);

			
			parseJSON(json);
			System.out.println("Finish");
		} catch (Exception e) {
			System.out.println(e);
		}
		
	}

	static String fetchFromURL(String url) throws Exception{
		URL url1 = new URL(url);
		URLConnection conn = url1.openConnection();
		
		
		try(BufferedInputStream bin = new BufferedInputStream(conn.getInputStream())){
			byte[] data = bin.readAllBytes();
			return new String(data);
		}
		
		
//		try (BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getInputStream()))){
//			String line = null;
//			StringBuffer sb = new StringBuffer();
//			while((line = reader.readLine())!= null) {
//				sb.append(line);
//			}
//			return sb.toString();
//		}
	}

	static void parseJSON(String json) throws Exception {
		JSONArray root = new JSONArray(json);
		System.out.println(root.length());
	
		Properties prop = new Properties();
		prop.put("user", user);
		prop.put("password", pw);
		
		try (Connection conn = DriverManager.getConnection(url, prop);
				PreparedStatement pstmt = conn.prepareStatement(sql_insert)){
			
			pstmt.execute(sql_del_all);
			pstmt.execute(sql_resetID);
			
			for ( int i=0; i<root.length(); i++) {
				JSONObject row = root.getJSONObject(i);
				
				String name = row.getString("Name");
				String feature = row.getString("Feature");
				String addr = row.getString("County") + 
							row.getString("Township") +
							row.getString("SalePlace");
				String tel = row.getString("ContactTel");
				String picurl = row.getString("Column1");
				String lat = row.getString("Latitude");
				String lng = row.getString("Longitude");
				
				pstmt.setString(1, name);
				pstmt.setString(2, feature);
				pstmt.setString(3, addr);
				pstmt.setString(4, tel);
				pstmt.setString(5, picurl);
				
				try {
					pstmt.setDouble(6, Double.parseDouble(lat));
					pstmt.setDouble(7, Double.parseDouble(lng));
				} catch (Exception e) {
					pstmt.setDouble(6, 0.0);
					pstmt.setDouble(7, 0.0);
				}
				pstmt.addBatch();
				pstmt.executeUpdate();
			}
			
		}
		

	}
}
