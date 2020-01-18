package dbutil;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConn_semichat {

	private static final String DRIVER = "oracle.jdbc.driver.OracleDriver";
	private static final String URL = "jdbc:oracle:thin:@localhost:1521:XE";
	private static final String USERNAME = "semichat";
	private static final String PASSWORD = "1234";

	private static Connection conn = null;
	
	private DBConn_semichat() {}
	
	public static Connection getConnection() {
		
		if(conn == null) {
			try {
				Class.forName(DRIVER); //����̹� �ε�
				
				conn = DriverManager.getConnection(URL, USERNAME, PASSWORD);
				
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			} catch (SQLException e) {
				e.printStackTrace();
			} 
		}
		
		return conn;
	}
}
