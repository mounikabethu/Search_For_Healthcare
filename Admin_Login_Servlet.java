import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class User_Login_Servlet
 */
@WebServlet("/Admin_Login_Servlet")
public class Admin_Login_Servlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PrintWriter out=response.getWriter();
		String userid=request.getParameter("username");
		String password=request.getParameter("password");
		HttpSession session=request.getSession();
		boolean flag=false;
		//JDBC code for connecting mysql
		try
		{
			Class.forName("com.mysql.jdbc.Driver");
			Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/search_for_health_care","root","mouni@66");
			Statement stmt=con.createStatement();
			ResultSet rs=stmt.executeQuery("select * from Admin_login_credentials");
			while(rs.next())
			{
				if(userid.equals(rs.getString(1)) && password.equals(rs.getString(2)))
				{
					session.setAttribute("user",userid);
					flag=true;
					response.sendRedirect("./Admin_Action.html");
				}
				else
				{
					out.print("invalid userid or password");
				}
			}
		}
		catch(Exception p)
		{
			out.print(p);
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