package Postlab;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class UpdateRemark
 */
@WebServlet("/UpdateRemark")
public class UpdateRemark extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UpdateRemark() {
        super();
        // TODO Auto-generated constructor stub
    }
//	190030061
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
//			190030061
			PrintWriter out = response.getWriter();  
	        response.setContentType("text/html");  
			
			Class.forName("oracle.jdbc.driver.OracleDriver");
			
			Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","ep","ep123");
			System.out.println("Connection to database success");
			
			String q1 = request.getParameter("q1");
			String q2 = request.getParameter("q2");
			String q3 = request.getParameter("q3");
			String q4 = request.getParameter("q4");
			 Cookie c[]=request.getCookies();
			String studentid = c[0].getValue();
			
			String sql = "UPDATE farewell SET Q1 = ?, Q2 = ?, Q3 = ?, Q4 = ? WHERE studentid = ? ";
			PreparedStatement ps = con.prepareStatement(sql);
			
			ps.setString(1, q1);
			ps.setString(2, q2);
			ps.setString(3, q3);
			ps.setString(4, q4);
			ps.setString(5, studentid);
//			190030061
			int count = ps.executeUpdate();
			System.out.println("Student update to remark in 'FAREWELL' table Successfull!");
			if(count > 0) {
				out.println("<html><body><h3>Update Success Full</h3><br><a href='ViewRemark'>View Remarks</a></body></html>");
			}else {
				out.println("<html><body><h3>Update Unsuccessfull</h3><br><a href='ViewRemark'>View Remarks</a></body></html>");
			}
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}//			190030061
	}

}
