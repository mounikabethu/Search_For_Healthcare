package com.health;
import java.sql.*;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class View_Services
 */
@WebServlet("/View_Services_Servlet")
public class View_Services_Servlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PrintWriter out = response.getWriter();  
        response.setContentType("text/html");  
        out.println("<html><body>");  
        try 
        {  
            Class.forName("com.mysql.jdbc.Driver");  
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/search_for_health_care", "root", "mouni@66");    
            Statement stmt = con.createStatement();  
            ResultSet rs = stmt.executeQuery("select * from service_categories");  
            out.println("<table border=1 width=50% height=50%>");  
            out.println("<tr><th>Service Id</th><th>Service Name</th><th>Service Type</th><tr>");  
            while (rs.next()) 
            {  
                String n = rs.getString("service_id");  
                String nm = rs.getString("service_name");   
                String p=rs.getString("service_type");
                out.println("<tr><td>" + n + "</td><td>" + nm + "</td><td>" + p);   
            }  
            out.println("</table>");  
            out.println("</html></body>");  
            con.close();  
           }  
            catch (Exception e) 
           {  
            out.println("error");  
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