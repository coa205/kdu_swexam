package kr.or.dgit.kdu_swexam.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionFactory {
	private static final ConnectionFactory instance = new ConnectionFactory();
	
	public static Connection getInstance(){
		return instance.createConnection();
	}
	
	public Connection createConnection(){
		Connection connection = null;
		try{
			String url = "jdbc:mysql://localhost:3306/ncs_erp";
			String user = "root";
			String password = "rootroot";
			connection = DriverManager.getConnection(url, user, password);
		} catch (SQLException e) {
			System.out.printf("Error : Unable to Connection DataBase");
		}
		return connection;
	}
	
	private ConnectionFactory(){
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			System.err.printf("MySQL Driver not Found!!");
			System.exit(-1);
		}
	}
}
