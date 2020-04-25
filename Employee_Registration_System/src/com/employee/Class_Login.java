package com.employee;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class Class_Login
 */
@WebServlet("/Class_Login")
public class Class_Login extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		

		String emp_name = request.getParameter("username");
		String emp_password = request.getParameter("password");
		
		PrintWriter out=response.getWriter();
		//out.println("Hello"+emp_name);
		//out.println("Hello"+emp_password);
		
		try{  
//			
//			//step1 load the driver class  
			Class.forName("oracle.jdbc.driver.OracleDriver");  
//			  
//			//step2 create  the connection object  
			Connection con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","system","1234567");  
//			
			String query ="select REG_USER_NAME,PASSWORD from REG_EMP where REG_USER_NAME=? and PASSWORD=?";
			
			PreparedStatement pst= con.prepareStatement(query);
			
			pst.setString(1,emp_name);
			pst.setString(2,emp_password);
			
          ResultSet rs=pst.executeQuery();
			
			if(rs.next())
			{
				response.sendRedirect("Emp_info.html");
			}
			
			else
			{
				out.println("User Or Password Wrong");
			}
			

			
			con.close();  
			  
			}
		catch(Exception e)
		{ 
			System.out.println(e);
		}  



	}

}
