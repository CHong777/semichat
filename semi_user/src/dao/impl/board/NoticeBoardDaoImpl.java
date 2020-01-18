package dao.impl.board;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import dao.face.board.NoticeBoardDao;
import dbutil.DBConn;
import dto.NoticeBoard;
import util.Paging;

public class NoticeBoardDaoImpl implements NoticeBoardDao{
	
	private Connection conn = null;	
	private PreparedStatement ps = null;
	private ResultSet rs = null;
	
	@Override
	public int selectCntAll(Map<String, String> map) {
		

		conn = DBConn.getConnection(); 

		//count
		int cnt = 0;
		
		
		String sql = "";
		sql += "SELECT";
		sql += " count(*)+2";
		sql += " FROM noticeboard, counselor";
		sql += " WHERE 1=1";
		sql += " AND noticeboard.counselorid=counselor.counselorid AND counselorposition!='admin'";
				
		if(!map.isEmpty()) {

			
			String searchType = "";
			String search = "";
			
			searchType = map.get("searchType");
			search = map.get("search");

			
			sql += " AND noticeboard."+searchType+" LIKE '%"+search+"%'";
		}

		try {
			//SQL
			ps = conn.prepareStatement(sql); 
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
	public List selectAll(Paging paging) {
		
		conn = DBConn.getConnection(); //DB 연결
		
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
			sql_tmp += " AND "+searchType+ " like '%"+search+"%'";
		}
		
		//수행할 SQL
		//2019 12 07 유진 : 관리자 공지사항 상단에 2개 나타내고 나머지 공지사항 나타내주기
		String sql = "";
		sql += "SELECT * FROM (SELECT rownum nnum, Q.* FROM (";
		sql	+= " SELECT * FROM (";
		sql += " SELECT rownum rnum, B.* FROM (";
		sql	+= " SELECT * FROM (";
		sql += " SELECT N.*, 1 sort FROM";
		sql	+= " noticeboard N,counselor WHERE N.counselorid=counselor.counselorid AND counselorposition='admin'";
		sql	+= " ORDER BY nboardno DESC) V WHERE rownum>=1 AND rownum<=2";
		sql += " UNION ALL";
		sql += " SELECT A.*, 2 sort FROM";
		sql	+= " noticeboard A,counselor WHERE A.counselorid=counselor.counselorid AND counselorposition!='admin')B";
		sql += " WHERE 1=1";
		sql += sql_tmp; 
		sql += " ORDER BY B.sort,nboardno DESC) NOTICEBOARD) Q";
		sql += " )WHERE nnum BETWEEN ? AND ?";

		
		System.out.println(sql);
		//최종 결과를 저장할 List
		List list = new ArrayList();
		
		try {
			//SQL 수행 객체
			ps = conn.prepareStatement(sql);
			
			ps.setInt(1, paging.getStartNo());
			ps.setInt(2, paging.getEndNo());
			
			//SQL 수행 및 결과 저장
			rs = ps.executeQuery();
			
			//SQL 수행 결과 처리
			while( rs.next() ) {
				
				NoticeBoard noticeboard = new NoticeBoard();
								
				noticeboard.setnBoardNo(rs.getInt("nnum"));
				noticeboard.setBoardNo(rs.getInt("nboardno"));
				noticeboard.setnBoardTitle( rs.getString("nboardtitle") );
				// 2019 12 07 유진 : 관리자가 작성한 공지 사항의 글쓴이는 ADMIN으로 나타나게
				if(rs.getInt("sort")==1) {
					noticeboard.setCounselorId("ADMIN");										
				} else {
					noticeboard.setCounselorId(rs.getString("counselorid"));					
				}
				noticeboard.setnBoardDate( rs.getString("nboarddate") );
				noticeboard.setnBoardViews(rs.getInt("nboardviews") );
				noticeboard.setnBoardContent(rs.getString("nBoardContent"));
				noticeboard.setnBoardBookMark(rs.getString("nBoardBookMark"));
				
				list.add(noticeboard);
				System.out.println("daoimpl" + list);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if(rs!=null)	rs.close();
				if(ps!=null)	ps.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		//최종 결과 반환
		return list;
	}

	@Override
	public void insert(NoticeBoard noticeboard) {
		
		conn = DBConn.getConnection(); //DB 연결
		
		System.out.println("인서트"+noticeboard);
		
		//다음 게시글 번호 조회 쿼리
		String sql = "";
		sql += "INSERT INTO noticeboard(nboardno, nboardtitle, nboardcontent, nBoardDate, nboardviews, nboardbookmark, counselorid, corporationName) ";
		sql += " VALUES (nboardno_seq.nextval, ?, ?, TO_CHAR(SYSDATE, 'YYYY-MM-DD'), 0, 'N', ?,(SELECT corporationName FROM counselor, corporation WHERE counselor.corporationNo=corporation.corporationNo AND counselorId=?))";
//		sql += " VALUES (nboardno_seq.nextval, ?, 'asasas', sysdate, 0, '북1', ?)";
		
		try {
			//DB작업
			ps = conn.prepareStatement(sql);
			ps.setString(1, noticeboard.getnBoardTitle());
			ps.setString(2, noticeboard.getnBoardContent());
			ps.setString(3, noticeboard.getCounselorId());
			ps.setString(4, noticeboard.getCounselorId());
//			ps.setString(2, noticeboard.getCounselorId());
			System.out.println("111"+noticeboard.getCounselorId());
			
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
	public void updateHit(NoticeBoard noticeboard) {
		
		conn = DBConn.getConnection(); //DB 연결

		//게시글 조회수 증가 쿼리
		String sql = "";
		sql+="UPDATE noticeboard";
		sql+=" SET nboardviews = nboardviews + 1";
		sql+=" WHERE nboardno = ?";
	
		try {
			ps = conn.prepareStatement(sql);
			
			ps.setInt(1, noticeboard.getnBoardNo());
			
			ps.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				// 자원 해제
				if(ps!=null)	ps.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
	}

	@Override
	public NoticeBoard selectNoticeBoardByNoticeBoardno(NoticeBoard noticeboard) {
		System.out.println("noticeboardBynoticeboardno" + noticeboard.getnBoardNo());
		//게시글 조회쿼리
		String sql = "";
		sql += "SELECT ";
		sql += "	nboardno";
		sql += "	, nboardtitle";
		sql += "	, counselorid";
		sql += "	, nboarddate";
		sql += "	, nboardviews";
		sql += "	, nboardcontent";
		sql += " FROM noticeboard";
		sql += " WHERE nboardno = ?";
	
		try {
			ps = conn.prepareStatement(sql);
			
			ps.setInt(1, noticeboard.getnBoardNo());
			
			rs = ps.executeQuery();
			
			while( rs.next() ) {
				
				noticeboard.setnBoardNo(rs.getInt("nboardno"));
				noticeboard.setnBoardTitle(rs.getString("nboardtitle"));
				noticeboard.setCounselorId(rs.getString("counselorid"));
				noticeboard.setnBoardDate(rs.getString("nboarddate"));
				noticeboard.setnBoardViews(rs.getInt("nboardviews"));
				noticeboard.setnBoardContent(rs.getString("nboardcontent"));
			}
			System.out.println("noticeboardBynoticeboardno" + noticeboard);

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				// 자원 해제
				if(rs!=null)	rs.close();
				if(ps!=null)	ps.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
				
		return noticeboard;
	}

	@Override
	public void delete(NoticeBoard noticeboard) {
		
		conn = DBConn.getConnection(); //DB 연결

		//삭제 쿼리
		String sql = "";
		sql += "DELETE noticeboard";
		sql += " WHERE nboardno = ?";
		
		//DB 객체
		PreparedStatement ps = null; 
		
		try {
			//DB작업
			ps = conn.prepareStatement(sql);
			ps.setInt(1, noticeboard.getnBoardNo());

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
	public void update(NoticeBoard noticeboard) {
		
		//다음 게시글 번호 조회 쿼리
		String sql = "";
		sql += "UPDATE noticeboard";
		sql += " SET nboardtitle = ?,";
		sql += " 	nboardcontent = ?";
		sql += " WHERE nboardno = ?";
		
		//DB 객체
		PreparedStatement ps = null; 
		
		try {
			//DB작업
			ps = conn.prepareStatement(sql);
			ps.setString(1, noticeboard.getnBoardTitle());
			ps.setString(2, noticeboard.getnBoardContent());
			ps.setInt(3, noticeboard.getnBoardNo());

			ps.executeUpdate();
			System.out.println("update" + sql);
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
	public NoticeBoard getCounserlorId(int noticeboardno) {
		conn=DBConn.getConnection();
		
		String sql="SELECT * FROM NOTICEBOARD WHERE nBoardNo = ?";
		
		NoticeBoard noticeBoard=new NoticeBoard();
		
		try {
			ps=conn.prepareStatement(sql);
			ps.setInt(1, noticeboardno);
			rs=ps.executeQuery();
			
			while(rs.next()) {
				noticeBoard.setCounselorId(rs.getString("COUNSELORID"));
				noticeBoard.setnBoardTitle(rs.getString("NBOARDTITLE"));
				noticeBoard.setnBoardContent(rs.getString("NBOARDCONTENT"));
				noticeBoard.setnBoardDate(rs.getString("NBOARDDATE"));
				noticeBoard.setnBoardTitle(rs.getString("NBOARDTITLE"));
				noticeBoard.setnBoardBookMark(rs.getString("NBOARDBOOKMARK"));
			}
			System.out.println("나오니" + noticeBoard);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
				
		return noticeBoard;
	}
	
}
