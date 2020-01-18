package dao.impl.mypage;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import dao.face.mypage.MyWritingInquiryDao;
import dbutil.DBConn;
import dto.InquiryBoard;
import util.Paging;

public class MyWritingInquiryDaoImpl implements MyWritingInquiryDao {

	private Connection conn = null; //DB연결 객체
	private PreparedStatement ps = null; //SQL 수행 객체
	private ResultSet rs = null; //SQL 수행 결과 객체
	
	@Override
	public int selectCntAll(Map<String, String> map, String counselorid) {
		conn = DBConn.getConnection(); 

		
		String search_sql = "";
		if(!map.isEmpty()) {
			
			
			String searchType = "";
			String search = "";
			
			searchType = map.get("searchType");
			search = map.get("search");
			
			search_sql = " AND "+searchType+" LIKE '%"+search+"%'";
		}
		//count
		int cnt = 0;

		String sql = "";
		sql += "SELECT";
		sql += " count(*)";
		sql += " FROM(SELECT * FROM (SELECT nboardno, corporationname, '공지사항', nboardtitle, counselorid, nboarddate, nboardviews, nboardcontent, nboardbookmark from noticeboard where counselorid = ?";
//		sql += search_sql;
		sql	+= " ) UNION ALL (SELECT iboardno, corporationname, '문의사항', iboardtitle, counselorid, iboarddate, iboardviews, iboardcontent, iboardbookmark from inquiryboard where counselorid = ?";
		sql	+= ")) where 1=1";
		sql += search_sql;

		
		try {
			//SQL
			ps = conn.prepareStatement(sql);
			ps.setString(1, counselorid);
			ps.setString(2, counselorid);
			rs = ps.executeQuery(); 

			System.out.println("gdsgdg : "+ sql);
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
		conn = DBConn.getConnection(); //DB연결
		
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
			 sql_tmp += " AND C."+searchType+ " like '%"+search+"%'";
	      }
	      
	      String sql = "SELECT * FROM (SELECT rownum rnum, B.* FROM (SELECT * FROM ((SELECT nboardno, corporationname, '공지사항', nboardtitle, counselorid, nboarddate, nboardviews, nboardcontent, nboardbookmark from noticeboard where counselorid = ?) UNION ALL (SELECT iboardno, corporationname, '문의사항', iboardtitle, counselorid, iboarddate, iboardviews, iboardcontent, iboardbookmark from inquiryboard where counselorid = ?)) C WHERE 1=1";
	      sql += sql_tmp;
	      sql += " ORDER BY NBOARDNO)B ORDER BY rnum) C WHERE rnum BETWEEN ? AND ?";

		//수행결과를 담을 리스트
		
		List list = new ArrayList();
		
		System.out.println("으아아아악 : " + sql);
		try {
			ps = conn.prepareStatement(sql);//수행객체얻기
			
			ps.setString(1, paging.getCounselorId());
			ps.setString(2, paging.getCounselorId());
			ps.setInt(3, paging.getStartNo());
			ps.setInt(4, paging.getEndNo());
			
			rs = ps.executeQuery();//SQL수행결과얻기
			
//			System.out.println(paging.getStartNo());
			
			//SQL 수행결과 처리
			while(rs.next()) {
				InquiryBoard board = new InquiryBoard(); //각 행을 처리할 DTO
				
				board.setiBoardNo(rs.getInt("nboardNo"));
				board.setiBoardTitle(rs.getString("nboardTitle"));
				board.setCounselorId(rs.getString("counselorId"));
				board.setiBoardViews(rs.getInt("nboardViews"));
				board.setiBoardDate(rs.getString("nboardDate"));
				board.setiBoardBookMark(rs.getString("nboardbookmark"));
				board.setBoardSorting(rs.getString("'공지사항'"));
//				board.setBoardSorting(rs.getString("문의사항"));
				
				System.out.println("북마크뭘까? : " + rs.getString("nboardbookmark"));
				
				list.add(board);
				
//				System.out.println(list);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}

}
