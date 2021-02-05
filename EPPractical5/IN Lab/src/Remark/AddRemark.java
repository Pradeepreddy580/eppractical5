package Remark;

import java.io.IOException;
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
 * Servlet implementation class AddRemark
 */
@WebServlet("/AddRemark")
public class AddRemark extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AddRemark() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}
	//190030061
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			
			Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","ep","ep123");
			System.out.println("Connection to database success");
			
			String q1 = request.getParameter("q1");
			String q2 = request.getParameter("q2");
			String q3 = request.getParameter("q3");
			String q4 = request.getParameter("q4");
			 Cookie c[]=request.getCookies();
			String studentid = c[0].getValue();
			String password = c[1].getValue();
			
			String sql = "INSERT INTO farewell VALUES (?,?,?,?,?,?)";
			PreparedStatement ps = con.prepareStatement(sql);
			
			ps.setString(1, studentid);
			ps.setString(2, password);
			ps.setString(3, q1);
			ps.setString(4, q2);
			ps.setString(5, q3);
			ps.setString(6, q4);
			
			int count = ps.executeUpdate();
			System.out.println("Remarks added successfull to 'FAREWELL' table");
			//190030061
			if(count > 0) {
				RequestDispatcher rd = request.getRequestDispatcher("remarkaddedsucc.html");
				rd.forward(request,response);
			}else {
				RequestDispatcher rd = request.getRequestDispatcher("remarkaddedfail.html");
				rd.forward(request,response);
			}
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();//190030061
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
