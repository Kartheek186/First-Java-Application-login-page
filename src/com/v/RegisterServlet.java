package com.v;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class RegisterServlet extends HttpServlet{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public void service(HttpServletRequest req,HttpServletResponse res)throws IOException
	{
		PrintWriter out=res.getWriter();
		res.setContentType("text/html");

		String empno=req.getParameter("eno");
		int eno=Integer.parseInt(empno);

		String ename=req.getParameter("ename");

		String email=req.getParameter("email");

		String uname=req.getParameter("uname");

		String pwd=req.getParameter("pwd");

		String phno=req.getParameter("pno");
		long pno=Long.parseLong(phno);

		try
		{
			Class.forName("oracle.jdbc.driver.OracleDriver");

			Connection con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","system","Kartheek18$");

			PreparedStatement pstmt=con.prepareStatement("insert into register values(?,?,?,?,?,?)");
			//pstmt.setInt(1,req.getParameter("eno"));

			pstmt.setInt(1,eno);
			pstmt.setString(2,ename);
			pstmt.setString(3,email);
			pstmt.setString(4,uname);
			pstmt.setString(5,pwd);
			pstmt.setLong(6,pno);
			int n=pstmt.executeUpdate();
			if(n>0)
			{
				out.println("Registered Successfully...");
				out.println("<a href=\"/Account/login.html\">click here</a> to login from here");
			}
		}
		catch (ClassNotFoundException ce)
		{
			ce.printStackTrace();
		}
		catch (SQLException se)
		{
			se.printStackTrace();
		}

	}
}
