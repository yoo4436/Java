package tw.brad.servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.charset.Charset;

@WebServlet("/Brad08")
public class Brad08 extends HttpServlet {       

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setCharacterEncoding("UTF-8");
		
		String x,y,result,op;
		x = y = result = op = "";
		try {
			String tmpx = request.getParameter("x");
			String tmpy = request.getParameter("y");
			String tmpop = request.getParameter("op");
			System.out.println(tmpop);
			
			int s,s1;
			s=s1=0;
			switch (tmpop) {
				case "1": {
					s = Integer.parseInt(tmpx) + Integer.parseInt(tmpy);
					break;
				}
				case "2": {
					s = Integer.parseInt(tmpx) - Integer.parseInt(tmpy);
					break;
				}
				case "3": {
					s = Integer.parseInt(tmpx) * Integer.parseInt(tmpy);
					break;
				}
				case "4": {
					s = Integer.parseInt(tmpx) / Integer.parseInt(tmpy);
					s1 = Integer.parseInt(tmpx) % Integer.parseInt(tmpy);
					break;
				}
			}
			result += s + (s1 > 0 ? (" ...... " + s1) : "");
			x = tmpx; y = tmpy; op = tmpop;
			System.out.println(result);
		}catch (Exception e) {
			System.out.println(e);
		}
		
		//----------------
		response.setContentType("text/html; charset=UTF-8");
		PrintWriter out = response.getWriter();
		
		BufferedInputStream bin = new BufferedInputStream(
					new FileInputStream("C:\\Users\\User\\eclipse-workspace\\BradWeb\\src\\main\\webapp\\brad06.html"));
		byte[] data = bin.readAllBytes();
		String html = new String(data);
				
		out.print(String.format(html,x,
				op.equals("1")?"selected":"",
				op.equals("2")?"selected":"",		
				op.equals("3")?"selected":"",
				op.equals("4")?"selected":"",
				y, result));
		
		response.flushBuffer();
	}

}
