package dao.impl.join;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import dao.face.join.CounselorJoinDao;
import dbutil.DBConn;
import dto.Counselor;

public class CounselorJoinDaoImpl implements CounselorJoinDao {

	private Connection conn = null;	//DB연결 객체
	private PreparedStatement ps = null;	//SQL 수행 객체
	private ResultSet rs = null;	//SQL 수행 결과 객체
	
	@Override
	public int selectIdForCheck(String id) {
		
		conn = DBConn.getConnection();	//DB 연결

		//수행할 SQL 쿼리
		String sql = "";
		sql += "SELECT";
		sql += " count(*)";
		sql += " FROM counselor";
		sql += " WHERE counselorid = ?";

		int cnt = 0;

		try {
			ps = conn.prepareStatement(sql);	//수행 객체 얻기
			ps.setString(1, id);	//SQL쿼리의 ? 채우기
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
		return cnt;
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
		return cnt;
	}

	@Override
	public void join(Counselor counselor) {
		
		conn = DBConn.getConnection();

		//쿼리작성
		String sql = "";
		sql += "INSERT INTO counselor";
		sql += " ( counselorNo, counselorName, counselorId,";
		sql += " counselorPassword, counselorNickname, counselorEmail,";
		sql += " counselorPhonenumber, counselorSigndate, counselorMarketingagree, corporationNo, counseloremailhash )";
		sql += " VALUES( counselorNo_seq.nextval, ?, ?, ?, ?, ?, ?, sysdate, ?, ?, ?)";
		
		try {
			//DB작업
			ps = conn.prepareStatement(sql);
			ps.setString(1, counselor.getCounselorName());
			ps.setString(2, counselor.getCounselorId());
			ps.setString(3, counselor.getCounselorPassword());
			ps.setString(4, counselor.getCounselorNickname());
			ps.setString(5, counselor.getCounselorEmail());
			ps.setString(6, counselor.getCounselorPhonenumber());
			ps.setInt(7, counselor.getCounselorMarketingagree());
			ps.setInt(8, counselor.getCorporationNo());
			ps.setString(9, counselor.getCounselorEmailHash());
			ps.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				//DB객체 닫기
				if(ps!=null)	ps.close();
				
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	@Override
	public Counselor selectEmailByCounselorNo(Counselor counselor) {

		conn = DBConn.getConnection();

		//쿼리 작성
		String sql = "";
		sql += "SELECT counseloremail FROM counselor";
		sql += " WHERE counselorno=?";

		try {
			//DB작업
			ps = conn.prepareStatement(sql);
			ps.setInt(1, counselor.getCounselorNo());

			rs = ps.executeQuery();

			while( rs.next() ) {
				counselor.setCounselorEmail(rs.getString("counselorEmail"));
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
	public Counselor selectCounselorNoByCounselorHashCode(Counselor counselor) {
		
		conn = DBConn.getConnection();

		//쿼리 작성
		String sql = "";
		sql += "SELECT counselorno FROM counselor";
		sql += " WHERE counseloremailhash=?";

		try {
			//DB작업
			ps = conn.prepareStatement(sql);
			ps.setString(1, counselor.getCounselorEmailHash());

			rs = ps.executeQuery();

			while( rs.next() ) {
				counselor.setCounselorNo(rs.getInt("counselorNo"));
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
	public void updateEmailChecked(Counselor counselor) {

		conn = DBConn.getConnection(); //DB연결

		//게시글 조회수 증가 쿼리
		String sql = "";
		sql += "UPDATE counselor";
		sql += " SET counseloremailchecked = 1 ";
		sql += " WHERE counselorno = ? ";

		try {
			ps = conn.prepareStatement(sql); //수행객체 얻기
			ps.setInt(1, counselor.getCounselorNo());
			ps.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				//자원 해제
				if(rs!=null) rs.close();
				if(ps!=null) ps.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	@Override
	public Counselor selectEmailChecked(Counselor counselor) {
		
		conn = DBConn.getConnection();

		//쿼리 작성
		String sql = "";
		sql += "SELECT counseloremailchecked FROM counselor";
		sql += " WHERE counseloremailhash=?";

		try {
			//DB작업
			ps = conn.prepareStatement(sql);
			ps.setString(1, counselor.getCounselorEmailHash());

			rs = ps.executeQuery();

			while( rs.next() ) {
				counselor.setCounselorEmailchecked(rs.getInt("counselorEmailchecked"));
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
}
