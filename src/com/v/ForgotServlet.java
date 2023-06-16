package com.v;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ForgotServlet extends HttpServlet{
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
		

		try
		{
			Class.forName("oracle.jdbc.driver.OracleDriver");

			Connection con=(Connection) DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","system","Kartheek18$");
			PreparedStatement pstmt=con.prepareStatement("select pwd from register where uname=?");
			
			pstmt.setString(1,uname);
			

			ResultSet rs=pstmt.executeQuery();
			if(rs.next())
			{
				//our.println("Your password is:"+rs.getString(1));
				out.println("<form action=\"update\">");
				out.println("<input type=\"hidden\" name=\"uname\" value=\""+uname+"\"/>");
				out.println("Enter New Password");
				out.println("<input type=\"password\" name=\"pwd\"/>");
				out.println("<br/><br/>");
				out.println("Re-Enter Password");
				out.println("<input type=\"password\" name=\"rpwd\"/>");
				out.println("<br/><br/>");
				out.println("<input type=\"submit\" value=\"submit\">");
				out.println("<input type=\"reset\" value=\"reset\">");
				out.println("</form>");
			}
			else
			{
				out.println("invalid username");
				out.println("<a href=\"/Account/Forgot.html\">click here</a> go to forgot page");
			}
			con.close();
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
}
