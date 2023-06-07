package com.atmdb;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class CustomerLoginRegister {

	
	private static Connection conn;
	private static Statement st;
	private static ResultSet rs;
	private static int accno;
	static {
		try {
			conn=DatabaseConnection.getDatabaseConnection();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	static Scanner sc=new Scanner(System.in);
	


	public static  void Register(String un, String pass) throws SQLException {
		
		st=conn.createStatement();
		String sql="Insert into Customer values('"+un+"','"+pass+"')";
		
		int res=st.executeUpdate(sql);
		 
		 if(res>0) {
			 
			 
			 System.out.println("*************************************************\n Registration Done Successfully..!\n***************************");
			
		 }else {
			 System.out.println("*************************************************\n Invalid UserName and Password..!\n***************************");
		 }
		
	}


//Customer login
	public static boolean login(String u, String p) throws SQLException {
		st=conn.createStatement();
		String sql="Select * from Customer where  username='"+u+"' and password='"+p+"'";
		ResultSet res=st.executeQuery(sql);
		if(res.next())
		{
			System.out.println("\n*************************************\n Login Successfully...!!!\n*************************************");
			return true;
		}
		return false;
	}

//login admin
	
}
