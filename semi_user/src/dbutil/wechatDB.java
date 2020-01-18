package dbutil;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

//DB���ᰴü - �̱���
public class wechatDB {
	//DB ���� ����
	private static final String DRIVER = "oracle.jdbc.driver.OracleDriver";
	private static final String URL = "jdbc:oracle:thin:@192.168.10.32:1521:XE";
	private static final String USERNAME = "wechat";
	private static final String PASSWORD = "1234";

	//DB ���� ��ü
	private static Connection conn = null;
	
	//private ������
	private wechatDB() {}
	
	//Connection ��ü ��ȯ - Singleton Pattern
	public static Connection getConnection() {
		
		if(conn == null) {
			try {
				Class.forName(DRIVER); //����̹� �ε�
				
				//DB���ᰴü ����
				conn = DriverManager.getConnection(URL, USERNAME, PASSWORD);
				
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			} catch (SQLException e) {
				e.printStackTrace();
			} 
		}
		
		return conn; //DB���ᰴü ��ȯ
	}
}
