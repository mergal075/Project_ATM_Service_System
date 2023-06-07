package com.atmdb;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {

	private	static String driver ="com.mysql.cj.jdbc.Driver";
	private	static String url="jdbc:mysql://localhost:3306/atmdata";
	private	static String un="root";
	private	static String pass="root";
	static Connection conn;

	public static Connection getDatabaseConnection() throws ClassNotFoundException, SQLException {
		
		Class.forName(driver);
		conn=DriverManager.getConnection(url,un,pass);
		if(conn==null) 
			System.out.println("Connection error");
		
	   return conn;
		
	}

}
