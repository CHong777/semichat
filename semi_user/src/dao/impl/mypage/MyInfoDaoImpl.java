package dao.impl.mypage;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import dao.face.mypage.MyInfoDao;
import dbutil.DBConn;
import dto.Counselor;

public class MyInfoDaoImpl implements MyInfoDao {
	
	private Connection conn = null;	//DB연결 객체
	private PreparedStatement ps = null;	//SQL 수행 객체
	private ResultSet rs = null;	//SQL 수행 결과 객체
	Counselor counselor = new Counselor();
	
	@Override
	public String selectPassword(String id) {
		
		conn = DBConn.getConnection();	//DB 연결

		//수행할 SQL 쿼리
		String sql = "";
		sql += "SELECT";
		sql += " counselorPassword";
		sql += " FROM counselor";
		sql += " WHERE counselorid = ?";

		String pass = null;

		try {
			ps = conn.prepareStatement(sql);	//수행 객체 얻기
			ps.setString(1, id);	//SQL쿼리의 ? 채우기
			rs = ps.executeQuery();				//SQL 수행 결과 얻기

			//SQL 수행결과 처리
			if(rs.next()) {
				pass = rs.getString(1);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			try {
				if(rs!= null) rs.close();
				if(ps!= null) ps.close();
			}catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return pass;
		
	}

	@Override
	public Counselor selectCounselorInfo(Counselor counselor) {
		
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
				counselor.setCounselorPassword( rs.getString("counselorpassword") );
				counselor.setCounselorNickname( rs.getString("counselornickname") );
				counselor.setCounselorPhonenumber(rs.getString("counselorphonenumber"));
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
//		System.out.println("잘가져오냐 카운슬러?" + counselor);
		return counselor;
	}

	@Override
	public void UpdateCounselorInfo(Counselor counselor) {

		conn = DBConn.getConnection(); //DB연결
		
		//수행할 SQL 쿼리
		String sql = "";
		sql += "UPDATE counselor SET counselorName = ?, counselorPassword =?, ";
		sql	+= " counselorNickname = ?, counselorPhoneNumber =? WHERE counselorId=?";
		
		try {
			ps = conn.prepareStatement(sql); // SQL 수행 객체

			//SQL쿼리의 ?채우기
			
			ps.setString(1, counselor.getCounselorName());
			ps.setString(2, counselor.getCounselorPassword());
			ps.setString(3, counselor.getCounselorNickname());
			ps.setString(4, counselor.getCounselorPhonenumber());
			ps.setString(5, counselor.getCounselorId());
			
//			System.out.println(counselor.getCounselorName());
//			System.out.println(counselor.getCounselorId());
			
			ps.executeUpdate(); //SQL 수행
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if(ps!=null)
					ps.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

	}

	@Override
	public int selectNicknameForCheck(String nickname) {
		
		conn = DBConn.getConnection();	//DB 연결

		//수행할 SQL 쿼리
		String sql = "";
		sql += "SELECT";
		sql += " count(*)";
		sql += " FROM counselor";
		sql += " WHERE counselornickname = ?";

		int cnt = 0;

		try {
			ps = conn.prepareStatement(sql);	//수행 객체 얻기
			ps.setString(1, nickname);			//SQL쿼리의 ? 채우기
			rs = ps.executeQuery();				//SQL 수행 결과 얻기

			//SQL 수행결과 처리
			while(rs.next()) {
				cnt = rs.getInt(1);
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			try {
				if(rs!= null) rs.close();
				if(ps!= null) ps.close();
			}catch (SQLException e) {
				e.printStackTrace();
			}
		}
		System.out.println("cnt 갯수? " + cnt);
		return cnt;
	}

	@Override
	public void updateCounselorWithdrawalCheck(Counselor counselor) {
		conn = DBConn.getConnection(); //DB연결
		
		//수행할 SQL 쿼리
		String sql = "";
		sql += "UPDATE counselor SET counselorWithDrawalChecked = 1 ";
		sql	+= " WHERE counselorId=?";
		
		try {
			ps = conn.prepareStatement(sql); // SQL 수행 객체

			//SQL쿼리의 ?채우기
			
			ps.setString(1, counselor.getCounselorId());
			
//			System.out.println(counselor.getCounselorName());
//			System.out.println(counselor.getCounselorId());
			
			ps.executeUpdate(); //SQL 수행
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if(ps!=null)
					ps.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
	}

}
