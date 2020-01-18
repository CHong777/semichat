package dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import dao.face.InquiryBoardDao;
import dbutil.DBConn;
import dto.InquiryBoard;
import dto.InquiryBoardFile;
import dto.NoticeBoard;
import util.Paging;

public class InquiryBoardDaoImpl implements InquiryBoardDao {

	private Connection conn = null; //DB연결 객체
	private PreparedStatement ps = null; //SQL 수행 객체
	private ResultSet rs = null; //SQL 수행 결과 객체
	
	@Override
	public int selectCntAll(Map<String, String> map) {
		conn = DBConn.getConnection();
		
		int cnt=0;
		
		String sql="SELECT COUNT(*) FROM inquiryBoard WHERE 1=1";
		
		if(!map.isEmpty()) {
			String searchType = "";
			String search = "";
			
			searchType = map.get("searchType");
			search = map.get("search");

			sql += " AND "+searchType+" LIKE '%"+search+"%'";
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
			sql_tmp += " AND inquiryboard."+searchType+ " like '%"+search+"%'";
		}
	
		//수행할 SQL 쿼리
		String sql = "";
		sql += "SELECT * FROM";  
		sql +=	" (SELECT rownum rnum, B.* FROM(SELECT iboardNo, iBoardInquiryType, iboardTitle, ";
		sql +=	" counselor.counselorId, iboardViews, iboardDate, counselorNickName, iBoardSecret, iBoardSecretPw, iBoardAnswer ";
		sql +=	" FROM counselor,inquiryboard WHERE 1=1";
		sql +=  sql_tmp;
		sql +=	" AND inquiryboard.counselorId = counselor.counselorId ORDER BY iboardNo DESC) ";
		sql +=  " B ORDER BY rnum) BOARD WHERE rnum BETWEEN ? AND ?";
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
				InquiryBoard board = new InquiryBoard(); //각 행을 처리할 DTO
				
				board.setiBoardNo(rs.getInt("iboardNo"));
				board.setiBoardInquiryType(rs.getString("iBoardInquiryType"));
				board.setiBoardTitle(rs.getString("iboardTitle"));
				board.setCounselorId(rs.getString("counselorId"));
				board.setiBoardViews(rs.getInt("iboardViews"));
				board.setiBoardDate(rs.getString("iboardDate"));
				board.setCounselorNickName(rs.getString("counselorNickName"));
				board.setiBoardSecret(rs.getString("iBoardSecret"));
				board.setiBoardSecretPw(rs.getString("iBoardSecretPw"));
				board.setiBoardAnswer(rs.getString("iBoardAnswer"));
				
//				System.out.println(rs.getDate("writtendate"));
				
				list.add(board);
				
//				System.out.println(list);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}

	@Override
	public InquiryBoard selectBoardByBoardno(InquiryBoard board) {
		conn = DBConn.getConnection(); //DB연결
		
		//수행할 SQL 쿼리
		String sql = "";
			sql += "SELECT *";
			sql += " FROM counselor, inquiryboard";
			sql += " WHERE counselor.counselorId = inquiryboard.counselorId AND iboardno= ?";

//		System.out.println("보드번호 : " + board.getiBoardNo());
		//SQL 수행 결과 저장 객체
		try {
			ps = conn.prepareStatement(sql); //SQL 수행 객체
			ps.setInt(1, board.getiBoardNo()); //SQL쿼리의 ? 채우기

			System.out.println("제발" + board.getiBoardNo());
			rs = ps.executeQuery(); //SQL 수행결과 얻기
			
			//SQL 수행 결과 처리
			while(rs.next()) {	
				
				board.setiBoardNo(rs.getInt("iboardNo"));
				board.setiBoardInquiryType(rs.getString("iBoardInquiryType"));
				board.setiBoardTitle(rs.getString("iBoardTitle"));
				board.setCounselorId(rs.getString("counselorId"));
				board.setiBoardContent(rs.getString("iBoardContent"));
				board.setiBoardViews(rs.getInt("iBoardViews"));
				board.setiBoardDate(rs.getString("iBoardDate"));
				board.setiBoardSecret(rs.getString("iBoardSecret"));
				board.setiBoardAnswer(rs.getString("iBoardAnswer"));
			}
			
//			System.out.println("디비는 됨" + board);
			
		} catch (SQLException e1) {
			e1.printStackTrace();
		} finally {
			try {
				if(rs!=null) rs.close(); 
				if(ps!=null) ps.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return board;
	}

	@Override
	public InquiryBoardFile getFileInfo(InquiryBoard board) {
		conn = DBConn.getConnection(); //DB연결
		
		//수행할 SQL 쿼리
		String sql = "";
		sql += "SELECT * FROM inquiryboardfile WHERE iboardno=?";
		
		InquiryBoardFile file = new InquiryBoardFile(); //각 행을 처리할 DTO
		
		
		try {
			ps = conn.prepareStatement(sql);//수행객체얻기
			ps.setInt(1, board.getiBoardNo());
			
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
	public void selectByFileno(InquiryBoardFile downFile) {
		//DB연결 객체
				conn = DBConn.getConnection();


				//수행할 SQL 쿼리
				String sql= "";
				sql +="SELECT fileno, originalname, storedname FROM inquiryboardfile WHERE fileno=?";

				try {
					ps = conn.prepareStatement(sql); //SQL쿼리 수행 객체
					ps.setInt(1, downFile.getFileNo()); // ? 채우기

					rs = ps.executeQuery(); //SQL 쿼리 수행 및 ResultSet 반환

					//SQL 수행 결과 처리
					while(rs.next()) {
						downFile.setOriginalName(rs.getString("originalname"));
						downFile.setStoredName(rs.getString("storedname"));
					}
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
	public void update(InquiryBoard inquiryBoard) {
		conn = DBConn.getConnection(); //DB연결		
		//수행할 SQL 쿼리
		String sql = "";
		sql += "UPDATE inquiryboard SET iBoardContent =?, iBoardAnswer='[답변 완료]' WHERE iBoardNo=?";
		
		try {
			ps = conn.prepareStatement(sql); // SQL 수행 객체

			//SQL쿼리의 ?채우기
			ps.setString(1, inquiryBoard.getiBoardContent());
			ps.setInt(2, inquiryBoard.getiBoardNo());			
			
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
	public void hit(InquiryBoard board) {
		conn=DBConn.getConnection();
		
		String sql="UPDATE INQUIRYBOARD SET iBoardViews=iBoardViews+1 WHERE iBoardNo=?";
				
		try {
			ps=conn.prepareStatement(sql);
			ps.setInt(1, board.getiBoardNo());
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
