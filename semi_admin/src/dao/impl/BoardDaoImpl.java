package dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import dao.face.BoardDao;
import dbutil.DBConn;
import dto.AdminBoard;
import dto.InquiryBoardFile;
import util.back_Paging;

public class BoardDaoImpl implements BoardDao {
	
	private Connection conn = null; //DB연결 객체
	private PreparedStatement ps = null; //SQL 수행 객체
	private ResultSet rs = null; //SQL 수행 결과 객체

	@Override
	public int selectCntAll(Map<String, String> map) {
		conn = DBConn.getConnection(); 
		//count
		int cnt = 0;
		
		String sql = "SELECT COUNT(*) FROM ";
		sql += "((SELECT nboardno, corporationname, '공지사항' AS boardSorting, nboardtitle, counselorid, nboarddate, nboardviews, nboardcontent from noticeboard) ";
		sql += "UNION ALL ";
		sql += "(SELECT iboardno, corporationname, '문의사항', iboardtitle, counselorid, iboarddate, iboardviews, iboardcontent from inquiryboard)) ";
		sql += "WHERE 1=1";
		
		if(!map.isEmpty()) {
			String searchType = "";
			String search = "";
			
			searchType = map.get("searchType");
			search = map.get("search");

			sql += " AND "+searchType+" LIKE '%"+search+"%'";
		}

		sql+="order by nboarddate";
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
	public List selectAll(back_Paging paging) {
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
		
		String sql = "SELECT * FROM (SELECT rownum rnum, B.* FROM (SELECT * FROM ((SELECT nboardno, corporationname, '공지사항' AS boardSorting, nboardtitle, counselorid, nboarddate, nboardviews, nboardcontent from noticeboard) UNION ALL (SELECT iboardno, corporationname, '문의사항', iboardtitle, counselorid, iboarddate, iboardviews, iboardcontent from inquiryboard)) C WHERE 1=1";
		sql += sql_tmp;
		sql += " ORDER BY NBOARDDATE, NBOARDNO)B ORDER BY rnum DESC)C WHERE rnum BETWEEN ? AND ?";

		//수행결과를 담을 리스트
		
		System.out.println(sql);
		List list = new ArrayList();
		
		try {
			ps = conn.prepareStatement(sql);//수행객체얻기
			ps.setInt(1, paging.getStartNo());
			ps.setInt(2, paging.getEndNo());
			
			rs = ps.executeQuery();//SQL수행결과얻기
			
//			System.out.println(paging.getStartNo());
			
			//SQL 수행결과 처리
			while(rs.next()) {
				AdminBoard admin = new AdminBoard();//각 행을 처리할 DTO
				
				admin.setBoardNo(rs.getInt("nBoardNo"));
				admin.setAdminBoardNo(rs.getInt("rnum"));
				admin.setCorporationname(rs.getString("corporationName"));
				admin.setBoardSorting(rs.getString("boardSorting"));
				admin.setTitle(rs.getString("nBoardTitle"));
//				if(!(rs.getString("corporationName").equals("ADMIN"))) {
					admin.setWriter(rs.getString("counselorId"));															
//				} else {
//					admin.setWriter(rs.getString("corporationName"));															
//				}
				admin.setWriteDate(rs.getString("nBoardDate"));
				admin.setViews(rs.getInt("nBoardViews"));
				admin.setBoardContent(rs.getString("nBoardContent"));
				admin.setInquiryAnswer("N");
				
				list.add(admin);
				
//				System.out.println(list);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}

	@Override
	public AdminBoard selectBoardByBoardno(AdminBoard adminBoard) {
		conn = DBConn.getConnection();
		
		String sql = "SELECT * FROM";
		sql += " ((SELECT nboardno AS boardNo, corporationname, '공지사항' AS boardSorting, nboardtitle AS title, counselorid, nboarddate AS writeDate, nboardviews AS views, nboardcontent AS boardContent from noticeboard)";
		sql += " UNION ALL";
		sql += " (SELECT iboardno, corporationname, '문의사항', iboardtitle, counselorid, iboarddate, iboardviews, iboardcontent from inquiryboard))";
		sql += " WHERE boardSorting=? AND boardNo=?";
		
		try {
			ps=conn.prepareStatement(sql);
			ps.setString(1, adminBoard.getBoardSorting());
			ps.setInt(2, adminBoard.getBoardNo());
			rs=ps.executeQuery();
			
			while(rs.next()) {
				adminBoard.setBoardContent(rs.getString("boardContent"));
				adminBoard.setCorporationname(rs.getString("corporationname"));
				adminBoard.setTitle(rs.getString("title"));
				adminBoard.setViews(rs.getInt("views"));
				adminBoard.setWriteDate(rs.getString("writeDate"));
				adminBoard.setWriter(rs.getString("counselorid"));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return adminBoard;
	}

	@Override
	public InquiryBoardFile getFileInfo(AdminBoard adminBoard) {
		conn = DBConn.getConnection(); //DB연결
		
		//수행할 SQL 쿼리
		String sql = "";
		sql += "SELECT * FROM inquiryboardfile WHERE iboardno=?";
		
		InquiryBoardFile file = new InquiryBoardFile(); //각 행을 처리할 DTO
		
		try {
			ps = conn.prepareStatement(sql);//수행객체얻기
			ps.setInt(1, adminBoard.getBoardNo());
			
			rs = ps.executeQuery();//SQL수행결과얻기
			
			//SQL 수행결과 처리
			while(rs.next()) {
				
				file.setiBoardNo(rs.getInt("iboardNo"));
				file.setFileNo(rs.getInt("fileNo"));
				file.setFileSize(rs.getInt("fileSize"));
				file.setOriginalName(rs.getString("originalName"));
				file.setStoredName(rs.getString("storedName"));
				
//				System.out.println(rs.getDate("writtendate")); 
				
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return file;
	}

	@Override
	public void delete(InquiryBoardFile file) {
		conn = DBConn.getConnection(); //DB연결
		
		//수행할 SQL 쿼리
		String sql = "";
		sql += "DELETE FROM inquiryboardfile WHERE iboardno = ?";
	
		try {
			ps = conn.prepareStatement(sql); // SQL 수행 객체
			//SQL쿼리의 ?채우기
			ps.setInt(1, file.getiBoardNo());
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
	public void inquiryDelete(AdminBoard deleteBoard) {
		conn = DBConn.getConnection(); //DB연결
		
		//수행할 SQL 쿼리
		String sql = "";
		sql += "DELETE FROM inquiryboard WHERE iboardno=?";
		try {
			ps = conn.prepareStatement(sql); // SQL 수행 객체
			
			//SQL쿼리의 ?채우기
			ps.setInt(1, deleteBoard.getBoardNo());
	
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
	public void noticeDelete(AdminBoard deleteBoard) {
		conn = DBConn.getConnection(); //DB연결
		
		//수행할 SQL 쿼리
		String sql = "";
		sql += "DELETE FROM noticeboard WHERE nboardno=?";
		try {
			ps = conn.prepareStatement(sql); // SQL 수행 객체
			
			//SQL쿼리의 ?채우기
			ps.setInt(1, deleteBoard.getBoardNo());
	
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
	public void inquiryHit(AdminBoard adminBoard) {
		conn=DBConn.getConnection();
		
		String sql="UPDATE INQUIRYBOARD SET iBoardViews=iBoardViews+1 WHERE iBoardNo=?";
				
		try {
			ps=conn.prepareStatement(sql);
			ps.setInt(1, adminBoard.getBoardNo());
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
	public void noticeHit(AdminBoard adminBoard) {
		conn=DBConn.getConnection();
		
		String sql="UPDATE NOTICEBOARD SET nBoardViews=nBoardViews+1 WHERE nBoardNo=?";
				
		try {
			ps=conn.prepareStatement(sql);
			ps.setInt(1, adminBoard.getBoardNo());
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
}
