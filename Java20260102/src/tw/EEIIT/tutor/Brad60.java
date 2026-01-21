package tw.EEIIT.tutor;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.FileOutputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

import javax.net.ssl.HttpsURLConnection;

public class Brad60 {
	public static void main(String[] args) {
		try {
			URL url = new URL("https://img-s-msn-com.akamaized.net/tenant/amp/entityid/AA1Ho4sx.img?w=768&h=512&m=6");
			URLConnection conn = url.openConnection();
			
			BufferedInputStream bin = new BufferedInputStream(conn.getInputStream());
			byte[] data = bin.readAllBytes();
					
			BufferedOutputStream bout = new BufferedOutputStream(new FileOutputStream("dir1/test.jpg"));
			bout.write(data);
			bout.flush();
			bout.close();
			
			System.out.println("OK");
		} catch (Exception e) {
			System.out.println(e);
		}
	}

}
