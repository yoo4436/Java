package tw.brad.servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/Brad07")
public class Brad07 extends HttpServlet {
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		response.setCharacterEncoding("UTF-8");
		
		String x = request.getParameter("x");
		String y = request.getParameter("y");
	
		response.setContentType("text/html; charset=UTF-8");
		PrintWriter out = response.getWriter();
		
		try {
			int s = Integer.parseInt(x) + Integer.parseInt(y);
			System.out.printf("%s + %s = %d\n", x, y, s);
			out.printf("%s + %s = %d\n", x, y, s);
		} catch (Exception e) {
			out.printf("XXXXXXX");
		}
	
				
		response.flushBuffer();
	
	}
}
