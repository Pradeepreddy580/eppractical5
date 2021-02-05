package Login;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class LogOut
 */
@WebServlet("/LogOut")
public class LogOut extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LogOut() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Cookie [] cookies = request.getCookies();
		PrintWriter out = response.getWriter();
		out.println("<a href=\"login.html\">Login</a> |\r\n"
				+ "	<a href=\"register.html\">Register</a> |\r\n"
				+ "	<a href=\"remark.html\">Add Remark</a> |\r\n"
				+ "	<a href=\"Profile\">Profile</a>\r\n"
				+ "	<a href=\"LogOut\">Logout</a> |\r\n"
				+ "	<hr>	");
		if(cookies != null) {
			for(int i = 0; i < cookies.length;i++) {
				Cookie c = cookies[i];
				
				c.setMaxAge(0);
				
			}
			out.println("<h1>Logout Success</h1>");
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
