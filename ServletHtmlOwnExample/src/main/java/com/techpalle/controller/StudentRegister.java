package com.techpalle.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;


import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/stdreg")
public class StudentRegister extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		//READ THE DATA FROM HTML WITH THEIR CONTROL NAMES(request.getParameter("") )
		String n=request.getParameter("tbName");
		String n1=request.getParameter("tbName1");
		String f=request.getParameter("tbFname");
		String e=request.getParameter("tbEmail");
		String p=request.getParameter("tbPass");
		String p1=request.getParameter("tbPass1");
		
		
		//JDBC to store above data into student table
				Connection cn=null;
				PreparedStatement ps=null;
				//step 1:load the driver class
				try
				{
					Class.forName("com.mysql.cj.jdbc.Driver");
					
				//step 2:Establish the connection
				cn	=DriverManager.getConnection("jdbc:mysql://localhost:3306/j2ee","root","Giri@226");
				
				//step 3:prepared Statement
				ps=cn.prepareStatement("insert into student1(fname,lname,fathername,email,password,confitmPass) values(?,?,?,?,?,?)");
				ps.setString(1,n);
				ps.setString(2,n1);
				ps.setString(3,f);
				ps.setString(4,e);
				ps.setString(5,p);
				ps.setString(6, p1);
				
				//step 4:execute Query
				ps.executeUpdate();
				} 
				catch (ClassNotFoundException | SQLException e1)
				{
					e1.printStackTrace();
				}
				//step 5:close the connection
				finally
				{
					if(ps!=null)
					{
						try
						{
							ps.close();
						} 
						catch (SQLException e1)
						{
							e1.printStackTrace();
						}
					}
					if(cn!=null)
					{
						try
						{
							cn.close();
						}
						catch (SQLException e1) 
						{
							e1.printStackTrace();
						}
					}
					
	
				}
	/*  //DISPLAY-->RESPONSE USE "getWriter()"
		PrintWriter pw=response.getWriter();
		pw.append("<h1 style='text-color:green'>Registration is Successful.....</h1>");*/
				
				// After successful registration, redirect to the success page
		        response.sendRedirect("success.html");
		
	}

}
