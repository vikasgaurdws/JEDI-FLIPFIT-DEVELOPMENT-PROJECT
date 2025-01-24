package com.flipkart.jdbc;

import java.sql.*;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

public class DemoJdbc {

	public static void main(String args[]){  
		try{ 
			Class.forName("com.mysql.cj.jdbc.Driver");  
			Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/FlipFitSchema","root","Sovik@S24+");  					
		
			PreparedStatement stmt=con.prepareStatement("insert into Emp values(?,?)");  
			stmt.setInt(1,102); //1 specifies the first parameter in the query  
			stmt.setString(2,"Sandeep");  
			  
			int i=stmt.executeUpdate();  
			System.out.println(i+" records inserted");  
			  
			con.close();  
			  
			}
		catch(Exception e){ System.out.println(e);
		}  
		  
		}  

}
