package dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import dao.face.NoticeBoardDao;
import dbutil.DBConn;
import dto.NoticeBoard;
import util.Paging;

public class NoticeBoardDaoImpl implements NoticeBoardDao{

	private Connection conn = null; //DB연결 객체
	private PreparedStatement ps = null; //SQL 수행 객체
	private ResultSet rs = null; //SQL 수행 결과 객체
	
	@Override
	public int selectCntAll(Map<String, String> map, String adminId) {
		conn = DBConn.getConnection();
		
		int cnt=0;
		
		String sql="SELECT COUNT(*) FROM NOTICEBOARD,COUNSELOR WHERE NOTICEBOARD.counselorId=COUNSELOR.counselorId AND counselorposition='admin'";
		
		if(!map.isEmpty()) {
			String searchType = "";
			String search = "";
			
			searchType = map.get("searchType");
			search = map.get("search");

			sql += " AND NOTICEBOARD."+searchType+" LIKE '%"+search+"%'";
		}

		try {
			//SQL
			ps = conn.prepareStatement(sql); 
//			ps.setString(1,adminId);
			rs = ps.executeQuery(); 
			
			while(rs.next()) {
				cnt = rs.getInt(1);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if(rs!=null) rs.close();
				if(ps!=null) ps.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return cnt;
	}

	@Override
	public List selectAll(Paging paging, String adminId) {
		conn = DBConn.getConnection();
		
		Map<String, String>map = paging.getSearch();
		Iterator<String> iter =map.keySet().iterator();
		
		String searchType = "";
		String search = "";
		String sql_tmp = "";
		
		if(!map.isEmpty()) { 
			while(iter.hasNext()) {
				String key = iter.next();
				String value = paging.getSearch().get(key);
				
				if(key == "searchType")
					searchType = value;
				else if(key == "search")
					search = value;
				
			}
			sql_tmp += " AND N."+searchType+ " like '%"+search+"%'";
		}
		
		String sql = "SELECT * FROM(SELECT rownum rnum, B.* FROM (SELECT N.* FROM NOTICEBOARD N,COUNSELOR WHERE N.counselorId=COUNSELOR.counselorId AND counselorposition='admin'";
		sql += sql_tmp;
		sql	+= " ORDER BY nboardno DESC)B ORDER BY rnum) BOARD WHERE rnum BETWEEN ? AND ?";
		
		List list = new ArrayList();
		
		try {
			ps=conn.prepareStatement(sql);
//			ps.setString(1, adminId);
			ps.setInt(1, paging.getStartNo());
			ps.setInt(2, paging.getEndNo());
			rs = ps.executeQuery();
			
			while(rs.next()) {
				NoticeBoard board = new NoticeBoard();
				
				board.setCounselorId(rs.getString("counselorid"));
				board.setnBoardNo(rs.getInt("nBoardNo"));
				board.setnBoardTitle(rs.getString("nBoardTitle"));
				board.setnBoardDate(rs.getString("nBoardDate"));
				board.setnBoardContent(rs.getString("nBoardContent"));
				board.setnBoardView(rs.getInt("nBoardViews"));
				
				list.add(board);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				if(rs!=null) rs.close();
				if(ps!=null) ps.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return list;
	}

	@Override
	public NoticeBoard selectBoardByBoardno(NoticeBoard noticeBoard) {
		conn = DBConn.getConnection(); // DB연결

		// 수행할 SQL 쿼리
		String sql = "";
		sql += "SELECT *";
		sql += " FROM NOTICEBOARD";
		sql += " WHERE nboardno= ?";

//		System.out.println("보드번호 : " + board.getiBoardNo());

		try {
			ps = conn.prepareStatement(sql); // SQL 수행 객체
			ps.setInt(1, noticeBoard.getnBoardNo()); // SQL쿼리의 ? 채우기
			rs = ps.executeQuery(); // SQL 수행결과 얻기

			// SQL 수행 결과 처리
			while (rs.next()) {
				noticeBoard.setCounselorId(rs.getString("counselorid"));
				noticeBoard.setnBoardDate(rs.getString("nBoardDate"));
				noticeBoard.setnBoardNo(rs.getInt("nBoardNo"));
				noticeBoard.setnBoardTitle(rs.getString("nBoardTitle"));
				noticeBoard.setnBoardView(rs.getInt("nBoardViews"));
				noticeBoard.setnBoardContent(rs.getString("nBoardContent"));
			}

//			System.out.println("디비는 됨" + board);

		} catch (SQLException e1) {
			e1.printStackTrace();
		} finally {
			try {
				if (rs != null)
					rs.close();
				if (ps != null)
					ps.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		return noticeBoard;
	}

	@Override
	public void delete(NoticeBoard noticeBoard) {
		conn = DBConn.getConnection(); //DB연결
		
		//수행할 SQL 쿼리
		String sql = "";
		sql += "DELETE FROM NOTICEBOARD WHERE nBoardNo=?";
		try {
			ps = conn.prepareStatement(sql); // SQL 수행 객체
			
			//SQL쿼리의 ?채우기
			ps.setInt(1, noticeBoard.getnBoardNo());
	
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
	public void insert(NoticeBoard noticeBoard) {
		conn = DBConn.getConnection(); //DB 연결
		
		System.out.println("인서트"+noticeBoard);
		
		//다음 게시글 번호 조회 쿼리
		String sql = "";
		sql += "INSERT INTO noticeboard(nboardno, nboardtitle, nboardcontent, nBoardDate, nboardviews, nboardbookmark, counselorid, corporationName) ";
		sql += " VALUES (nboardno_seq.nextval, ?, ?, TO_CHAR(SYSDATE, 'YYYY-MM-DD'), 0, '북1', ?, (SELECT corporationName FROM counselor, corporation WHERE counselor.corporationNo=corporation.corporationNo AND counselorId=?))";
		
		try {
			//DB작업
			ps = conn.prepareStatement(sql);
			ps.setString(1, noticeBoard.getnBoardTitle());
			ps.setString(2, noticeBoard.getnBoardContent());
			ps.setString(3, noticeBoard.getCounselorId());
			ps.setString(4, noticeBoard.getCounselorId());
			
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
	public void hit(NoticeBoard noticeBoard) {
		conn=DBConn.getConnection();
		
		String sql="UPDATE NOTICEBOARD SET nBoardViews=nBoardViews+1 WHERE nBoardNo=?";
				
		try {
			ps=conn.prepareStatement(sql);
			ps.setInt(1, noticeBoard.getnBoardNo());
			ps.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
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
	public void update(NoticeBoard noticeBoard) {
		conn = DBConn.getConnection(); //DB연결		
		//수행할 SQL 쿼리
		String sql = "";
		sql += "UPDATE noticeboard SET nBoardContent =?, nBoardTitle=? WHERE nBoardNo=?";
		
		try {
			ps = conn.prepareStatement(sql); // SQL 수행 객체

			//SQL쿼리의 ?채우기
			ps.setString(1, noticeBoard.getnBoardContent());
			ps.setString(2, noticeBoard.getnBoardTitle());			
			ps.setInt(3, noticeBoard.getnBoardNo());			
			
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
