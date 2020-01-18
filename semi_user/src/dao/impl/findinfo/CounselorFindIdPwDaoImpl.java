package dao.impl.findinfo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import dao.face.findinfo.CounselorFindIdPwDao;
import dbutil.DBConn;
import dto.Counselor;

public class CounselorFindIdPwDaoImpl implements CounselorFindIdPwDao {

	//DB ���� ��ü
	private Connection conn = null;//DB���� ��ü
	private PreparedStatement ps = null;//SQL ���� ��ü
	private ResultSet rs = null; //SQL���� ��� ��ü
	
	@Override
	public int findId(Counselor counselor) {

		conn = DBConn.getConnection();	//DB 연결

		//수행할 SQL 쿼리
		String sql = "";
		sql += "SELECT";
		sql += " count(*)";
		sql += " FROM counselor";
		sql += " WHERE counseloremail = ? AND counselorphonenumber = ? ";

		int cnt = 0;

		try {
			ps = conn.prepareStatement(sql);	//수행 객체 얻기
			ps.setString(1, counselor.getCounselorEmail());			//SQL쿼리의 ? 채우기
			ps.setString(2, counselor.getCounselorPhonenumber());	//SQL쿼리의 ? 채우기
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
		System.out.println("cnt 갯수 : " + cnt);
		return cnt;
	}

	@Override
	public int findPw(Counselor counselor) {
		
		conn = DBConn.getConnection();	//DB 연결

		//수행할 SQL 쿼리
		String sql = "";
		sql += "SELECT";
		sql += " count(*)";
		sql += " FROM counselor";
		sql += " WHERE counselorname = ? AND counselorid = ? ";

		int cnt = 0;

		try {
			ps = conn.prepareStatement(sql);	//수행 객체 얻기
			ps.setString(1, counselor.getCounselorName());			//SQL쿼리의 ? 채우기
			ps.setString(2, counselor.getCounselorId());	//SQL쿼리의 ? 채우기
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
		System.out.println("비번찾기 cnt 갯수 : " + cnt);
		return cnt;
	}

	@Override
	public Counselor selectIdByPhone(Counselor counselor) {

		//쿼리 작성
		String sql = "";
		sql += "SELECT counselorid FROM counselor";
		sql += " WHERE counselorphonenumber=?";

		try {
			//DB작업
			ps = conn.prepareStatement(sql);
			ps.setString(1, counselor.getCounselorPhonenumber());

			rs = ps.executeQuery();

			while( rs.next() ) {
				counselor.setCounselorId(rs.getString("counselorid"));
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				//DB객체 닫기
				if(rs!=null)	rs.close();
				if(ps!=null)	ps.close();

			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		return counselor;
	}

	@Override
	public Counselor selectEmailById(Counselor counselor) {
		//쿼리 작성
		String sql = "";
		sql += "SELECT counseloremail FROM counselor";
		sql += " WHERE counselorid=?";

		try {
			//DB작업
			ps = conn.prepareStatement(sql);
			ps.setString(1, counselor.getCounselorId());

			rs = ps.executeQuery();

			while( rs.next() ) {
				counselor.setCounselorEmail(rs.getString("counseloremail"));
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				//DB객체 닫기
				if(rs!=null)	rs.close();
				if(ps!=null)	ps.close();

			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return counselor;
	}

	@Override
	public void updatePw(Counselor counselor) {
		conn = DBConn.getConnection();	//DB 연결
		String sql = "";
		sql += "UPDATE counselor set counselorpassword = ? where counselorid = ?";

		try {
			ps = conn.prepareStatement(sql);	//수행 객체 얻기
			ps.setString(1, counselor.getCounselorPassword());	//SQL쿼리의 ? 채우기
			ps.setString(2, counselor.getCounselorId());	//SQL쿼리의 ? 채우기
			rs = ps.executeQuery();				//SQL 수행 결과 얻기

		}catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			try {
				if(ps!=null)	ps.close();
			}catch (Exception e) {
				// TODO: handle exception
			}
		}		
	}
}
