package com.tcl;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class servlet2 extends HttpServlet{
	    int count=3;
        @Override
        protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        	String sql="select* from lumen where phonenumber=?";
           String number2 =req.getParameter("phonenumber");
           String password2 =req.getParameter("password");
           PrintWriter out=resp.getWriter();
           out.println("<html>");
           out.println("<head>");
           out.println("<title>Login Page</title>");
           out.println("<link rel='stylesheet' type='text/css' href='h4.css'>"); // Linking the CSS file
           out.println("</head>");
           out.println("<body>");
           String url="jdbc:mysql://localhost:3306/sep_b2";
         	String username="root";
         	String password="517257";
         	Connection con=null;
        	PreparedStatement st=null; 
        	resp.setContentType("text/html");
        	String number1="";
        	String password1="";
        	String name1="";
        	
      	 try {
   			Class.forName("com.mysql.cj.jdbc.Driver");
   			con=DriverManager.getConnection(url,username,password);
   			st =con.prepareStatement(sql);
   			  st.setString(1,req.getParameter("phonenumber"));
   			ResultSet re= st.executeQuery();
   		  if (re.next()) {
              number1 = re.getString("phonenumber");
              password1 = re.getString("password");
              name1=re.getString("name");

              if (number1.equals(number2) && password1.equals(password2)) {
                  out.print("<div class='success-msg'>Login successful!</div>");
                  out.print("hi<h3>"+ name1+">/h3>");
                  RequestDispatcher rd = req.getRequestDispatcher("index3.html");
                  rd.include(req, resp);
              } else {
                  if (count > 0) {
                      count--;
                      out.print("<div class='error-msg'>Invalid password. " + count + " attempts left.</div>");
                      RequestDispatcher rd = req.getRequestDispatcher("index2.html");
                      rd.include(req, resp);
                  } else {
                      out.print("<div class='error-msg'>Attempts over, please try again after 24 hours.</div>");
                      RequestDispatcher rd = req.getRequestDispatcher("index.html"); 
                      rd.include(req, resp);
                  }
              }
          } else {
              out.print("<div class='error-msg'>These details do not exist.</div>");
              RequestDispatcher rd = req.getRequestDispatcher("index.html"); 
              rd.include(req, resp);
          }
      } catch (Exception e) {
          out.print("<div class='error-msg'>Error: " + e.getMessage() + "</div>");
      }
      out.println("</body>");
       out.println("</html>");
    }
}
