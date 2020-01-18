package dao.impl.login;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import dao.face.login.CounselorLoginDao;
import dbutil.DBConn;
import dto.Counselor;

public class CounselorLoginDaoImpl implements CounselorLoginDao {
	
	//DB관련 객체
	private Connection conn = null; 
	private PreparedStatement ps = null;
	private ResultSet rs = null;
	
	@Override
	public int selectCntCounselorByCounseloridAndCounselorpassword(Counselor counselor) {
		
		conn = DBConn.getConnection();
		
		
		if( counselor.getCounselorId() == null || counselor.getCounselorPassword() == null ) {
			return -1;
			
		} 
		
		System.out.println("���޹��� id, pw " + counselor);
		
		//쿼리 작성
		String sql = "";
		sql += "SELECT COUNT(*) cnt FROM counselor";
		sql += " where counselorid = ?";
		sql += " AND counselorpassword = ?";
		
		System.out.println(sql);
		
		int cnt = -1;
		try {
			//DB 작업
			ps = conn.prepareStatement(sql);
			ps.setString(1, counselor.getCounselorId());
			ps.setString(2, counselor.getCounselorPassword());
			
			rs = ps.executeQuery();
			
			while( rs.next() ) {
				cnt = rs.getInt("cnt");
				System.out.println(cnt);
				
			}
			
		} catch (SQLException e) {
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

	@Override
	public Counselor selectCounselorByCounselorid(Counselor counselor) {
		
		conn = DBConn.getConnection();

		//쿼리 작성
		String sql = "";
		sql += "SELECT * FROM counselor";
		sql += " WHERE";
		sql += " counselorid = ?";
		
		try {
			//DB작업
			ps = conn.prepareStatement(sql);
			ps.setString(1, counselor.getCounselorId());
		
			rs = ps.executeQuery();
			
			while( rs.next() ) {
				counselor.setCounselorNo(rs.getInt("counselorno"));
				counselor.setCounselorName(rs.getString("counselorname"));
				counselor.setCounselorId( rs.getString("counselorid") );
				counselor.setCounselorPassword( rs.getString("counselorpassword") );
				counselor.setCounselorNickname( rs.getString("counselornickname") );
				counselor.setCounselorEmail(rs.getString("counseloremail"));
				counselor.setCounselorPhonenumber(rs.getString("counselorphonenumber"));
				counselor.setCounselorSigndate(rs.getString("counselorsigndate"));
				counselor.setCounselorPosition(rs.getString("counselorposition"));
				counselor.setCounselorMarketingagree(rs.getInt("counselormarketingagree"));
				counselor.setCounselorEmailchecked(rs.getInt("counseloremailchecked"));
				counselor.setManagerConfirm(rs.getInt("managerconfirm"));
				counselor.setCorporationNo(rs.getInt("corporationno"));
				counselor.setCounselorEmailHash(rs.getString("counselorEmailHash"));
			}
			
		} catch (SQLException e) {
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
		System.out.println("�����" + counselor);
		return counselor;
	}
}
