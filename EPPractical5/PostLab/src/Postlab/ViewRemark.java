package Postlab;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


//190030061
/**
 * Servlet implementation class ViewRemark
 */
@WebServlet("/ViewRemark")
public class ViewRemark extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ViewRemark() {
        super();
        // TODO Auto-generated constructor stub
    }
//	190030061
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PrintWriter out = response.getWriter();  
        response.setContentType("text/html");  
        out.println("<html><body>");
		try {
			Cookie c[]=request.getCookies();
			String studentid = c[0].getValue();
			String password = c[1].getValue();
			
			Class.forName("oracle.jdbc.driver.OracleDriver");
			
			Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","ep","ep123");
//			190030061
			String sql = "SELECT * FROM farewell WHERE studentid = ?";
			PreparedStatement ps = con.prepareStatement(sql);
					
		    ps.setString(1, studentid);
		    
		   ResultSet rs =  ps.executeQuery();
		   out.println("<h1>Remarks</h1>");
		   out.println("<table border=1 width=50% height=25%>");  
           out.println("<tr><th>Question 1</th><th>Question 2</th><th>Question 3</th><th>Question 4</th><tr>"); 
		    
		   while(rs.next()) {
			   
			   String q1 = rs.getString("q1");
			   String q2 = rs.getString("q2");
			   String q3 = rs.getString("q3");
			   String q4 = rs.getString("q4");
			   
			   out.println("<tr><td>" + q1 + "</td><td>" + q2 + "</td><td>" + q3 + "</td><td>" + q4 + "</td></tr>"); 
		   }
		  
//			190030061
		   out.println("</table>");  
           out.println("</body></html>");  
           con.close();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

//	190030061

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}

}
