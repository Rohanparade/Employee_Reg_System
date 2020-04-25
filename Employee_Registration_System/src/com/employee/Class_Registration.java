package com.employee;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class Class_Registration
 */
@WebServlet("/Class_Registration")
public class Class_Registration extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException

	{

		String emp_name = request.getParameter("username");
		String emp_email = request.getParameter("email");
		String emp_password = request.getParameter("password");
		String emp_password2 = request.getParameter("password2");
		
		  
		 PrintWriter out = response.getWriter();
		/* 
		 * out.println("Hello World");
		 * 
		 * out.println("Hello"+emp_name); out.println("Hello"+emp_email);
		 * out.println("Hello"+emp_password); out.println("Hello"+emp_password2);
		 */

		try {

			// step1 load the driver class
			Class.forName("oracle.jdbc.driver.OracleDriver");

			// step2 create the connection object
			Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "system", "1234567");

			// //step3 create the statement object //
			//Statement stmt = con.createStatement();
			
			String query = "insert into REG_EMP values('" + emp_name + "','" + emp_email + "','" + emp_password + "','"+ emp_password2 + "')";

			PreparedStatement pst = con.prepareStatement(query);
			pst.executeUpdate();

			out.println("Registration successfully Done !");
			// response.sendRedirect("wel_reg.html");
			con.close();

		} catch (Exception e) {
			System.out.println(e);
		}

	}

}
