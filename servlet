package com.tcl;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class servlet extends HttpServlet{
     @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    	String sql="insert into lumen(name,phonenumber,email,password) values(?,?,?,?)";
    	String url="jdbc:mysql://localhost:3306/sep_b2";
    	String username="root";
    	String password="517257";
    	Connection con=null;
    	PreparedStatement pre=null;
    	PrintWriter out=null;
    	out=resp.getWriter();
    	out.println("<html>");
        out.println("<head>");
        out.println("<title>Login Page</title>");
        out.println("<link rel='stylesheet' type='text/css' href='h4.css'>"); // Linking the CSS file
        out.println("</head>");
        out.println("<body>");
    	resp.setContentType("text/html");
    	 try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			con=DriverManager.getConnection(url,username,password);
		    pre=con.prepareStatement(sql);
		    pre.setString(1,req.getParameter("name"));
		    pre.setString(2,req.getParameter("phonenumber"));
		    pre.setString(3,req.getParameter("email"));
		    pre.setString(4,req.getParameter("password"));
		    int i=pre.executeUpdate();
		    if(i==1)
		    {
		    	out.print("<div class='success-msg'>rigistration successful! please login</div>");
		    	RequestDispatcher rd=req.getRequestDispatcher("index.html");
		    	rd.include(req,resp);
		    }
		    else
		    {
		    	 out.print("<div class='error-msg'>rigistration not successful</div>");
		    }
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			out.print("<div class='error-msg'>given details are already exist! please update your password</div>");
			RequestDispatcher rd=req.getRequestDispatcher("index.html");
	    	rd.include(req,resp);
			
		}
    	out.println("</body>");
        out.println("</html>");
    }
}
