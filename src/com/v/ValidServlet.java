package com.v;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public class ValidServlet extends HttpServlet{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public void service(HttpServletRequest req,HttpServletResponse res) throws IOException{
		PrintWriter out=res.getWriter();
		res.setContentType("text/html");

		String uname=req.getParameter("uname").toString();

		String pwd=req.getParameter("pass").toString();
		
		try
		{
			Class.forName("oracle.jdbc.driver.OracleDriver");

			Connection con=(Connection) DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","system","Kartheek18$");
			Statement stmt=((java.sql.Connection) con).createStatement();

			ResultSet rs=stmt.executeQuery("select * from register where uname='"+uname+"' and pwd='"+pwd+"'");

			if(rs.next())
			{
				out.println("welcome:"+uname);
			}
			else
			{
				out.println("invalid username or password");
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

