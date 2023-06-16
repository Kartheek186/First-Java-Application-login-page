package com.v;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class UpdateServlet extends HttpServlet{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public void service(HttpServletRequest request,HttpServletResponse response)throws IOException
	{
		PrintWriter out=response.getWriter();
		response.setContentType("text/html");

		//getting data registration form

		String uname=request.getParameter("uname").toString();
		String pwd=request.getParameter("pwd").toString();

		try
		{
			Class.forName("oracle.jdbc.driver.OracleDriver");

			 Connection con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","system","Kartheek18$");

			PreparedStatement pstmt=con.prepareStatement("update register set pwd=? where uname=?");
			pstmt.setString(1,pwd);
			pstmt.setString(2,uname);
			

			int n=pstmt.executeUpdate();
			if(n>0)
			{
				out.println("your password updated successfully...");
				out.println("click here to <a href=\"/Account/login.html\">Login here</a>");
			}
			
			con.close();
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
}
