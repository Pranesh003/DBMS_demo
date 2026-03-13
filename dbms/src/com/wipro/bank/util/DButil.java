package com.wipro.bank.util;
 import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
//import java.sql.Statement;
public class DButil {
		
	public static Connection getDBConnection() {
		try {
		Class.forName("oracle.jdbc.driver.OracleDriver");
		String url="jdbc:oracle:thin:@localhost:1521:XE";
		String user="SYSTEM";
		String pass="84380";
		Connection connection=DriverManager.getConnection(url,user,pass);
		return connection;
		
	
}
		catch(ClassNotFoundException | SQLException e) {
			e.printStackTrace();
			return null;
		}
	
	}
}
