package dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import dao.face.AdminLoginDao;
import dbutil.DBConn;
import dto.Admin;

public class AdminLoginDaoImpl implements AdminLoginDao {
	
	private Connection conn = null; 
	private PreparedStatement ps = null;
	private ResultSet rs = null;
	
	@Override
	public int login(Admin admin) {
		conn=DBConn.getConnection();
		
		if(admin.getAdminId()==null || admin.getAdminPw()==null) {
			return -1;
		}
		
		String sql="SELECT COUNT(*) FROM counselor WHERE counselorID=? AND counselorPassWord=? AND counselorposition='admin'";
		
		int cnt=-1;
		try {
			ps=conn.prepareStatement(sql);
			ps.setString(1, admin.getAdminId());
			ps.setString(2, admin.getAdminPw());
			rs=ps.executeQuery();
			
			while(rs.next()) {
				cnt=rs.getInt(1);
				System.out.println(cnt);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				//DB 객체 닫기
				if(rs!=null)	rs.close();
				if(ps!=null)	ps.close();
				
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return cnt;
	}

}
