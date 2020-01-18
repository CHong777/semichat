package dao.impl.board;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.face.board.InquiryBoardDao;
import dbutil.DBConn;
import dto.Counselor;
import dto.InquiryBoard;
import dto.InquiryBoardFile;
import util.Paging;


public class InquiryBoardDaoImpl implements InquiryBoardDao{

	private Connection conn = null; //DB연결 객체
	private PreparedStatement ps = null; //SQL 수행 객체
	private ResultSet rs = null; //SQL 수행 결과 객체
	
	
	@Override
	public int selectCntAll(Map<String, String> map) {

				conn = DBConn.getConnection(); 

				//count
				int cnt = 0;
				
				
				String sql = "";
				sql += "SELECT";
				sql += " count(*)";
				sql += " FROM inquiryboard";
				sql += " WHERE 1=1";
				
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
				
				if(key == "searchType" )
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
		sql +=	" counselor.counselorId, iboardViews, iboardDate, iboardBookMark, counselorNickName, iBoardSecret, iBoardSecretPw, iBoardAnswer ";
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
				board.setiBoardBookMark(rs.getString("iboardBookMark"));
				
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
	public void updateHit(InquiryBoard board) {
		
		
		conn = DBConn.getConnection(); //DB연결
		
		//수행할 SQL 쿼리
		String sql = "";
		sql += "UPDATE inquiryboard SET  iBoardViews =  iBoardViews+1  WHERE iboardNo = ? ";
		
		//수행결과를 담을 리스트
		
		
		try {
			ps = conn.prepareStatement(sql);//수행객체얻기
			
			ps.setInt(1, board.getiBoardNo());
			
			
			ps.executeUpdate(); 
			
		
			
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
	public InquiryBoard selectBoardByBoardno(InquiryBoard board) {
		
		conn = DBConn.getConnection(); //DB연결
		
		//수행할 SQL 쿼리
		String sql = "";
			sql += "SELECT *";
			sql += " FROM counselor, inquiryboard";
			sql += " WHERE counselor.counselorId = inquiryboard.counselorId AND iboardno= ?";

		
//		System.out.println("보드번호 : " + board.getiBoardNo());
		
		
		//SQL 수행 결과 저장 객체
//		
		
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
	public int selectBoardno() {
		conn = DBConn.getConnection(); //DB연결
		
		//수행할 SQL 쿼리
		String sql = "";
		sql += "SELECT iBoardNo_seq.nextval AS seq FROM dual";
		
		int seq=-1;
		
		//SQL 수행 결과 저장 객체
//		
		
		try {
			ps = conn.prepareStatement(sql); //SQL 수행 객체

			rs = ps.executeQuery(); //SQL 수행결과 얻기
			
			//SQL 수행 결과 처리
			while(rs.next()) {	
				
				seq= rs.getInt("seq");
			}
			
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
		
		
		return seq;
	}


	@Override
	public void insert(InquiryBoard inquiryBoard) {
		conn = DBConn.getConnection(); //DB연결

		//수행할 SQL 쿼리
		String sql = "";
		sql += "INSERT INTO inquiryboard(iBoardNo, iBoardTitle, iBoardDate, iBoardContent, iBoardViews,";
		sql	+= " iBoardInquiryType, iBoardSecret, iBoardSecretPw, counselorId, iBoardBookMark, corporationname)";
		sql	+= " VALUES (?,?,TO_CHAR(SYSDATE, 'YYYY-MM-DD'),?, 0, ?, ?, ?, ?, 'N',(SELECT corporationName FROM counselor, corporation WHERE counselor.corporationNo=corporation.corporationNo AND counselorId= ?))";
		
		try {
			ps = conn.prepareStatement(sql); // SQL 수행 객체

			//SQL쿼리의 ?채우기
			ps.setInt(1, inquiryBoard.getiBoardNo());
			ps.setString(2, inquiryBoard.getiBoardTitle());
			ps.setString(3, inquiryBoard.getiBoardContent());
			ps.setString(4, inquiryBoard.getiBoardInquiryType());
			ps.setString(5, inquiryBoard.getiBoardSecret());
			ps.setString(6, inquiryBoard.getiBoardSecretPw());
			ps.setString(7, inquiryBoard.getCounselorId());
			ps.setString(8, inquiryBoard.getCounselorId());
			
			ps.executeUpdate(); //SQL 수행
			
//			System.out.println(inquiryBoard.getiBoardNo());
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
	public void insertFile(InquiryBoardFile inquiryBoardFile) {
		conn = DBConn.getConnection(); //DB연결
		
		//수행할 SQL 쿼리
		String sql = "";
		sql += "INSERT INTO inquiryboardfile VALUES (fileno_seq.nextval, ?, ?, 'txt', ?,TO_CHAR(SYSDATE, 'YYYY-MM-DD'), ?)";

		try {
			ps = conn.prepareStatement(sql); // SQL 수행 객체
			
			//SQL쿼리의 ?채우기
			ps.setString(1, inquiryBoardFile.getOriginalName());
			ps.setString(2, inquiryBoardFile.getStoredName());
//			ps.setString(3, inquiryBoardFile.getFileType());
			ps.setInt(3, inquiryBoardFile.getFileSize());
			ps.setInt(4, inquiryBoardFile.getiBoardNo());

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
		
//		Inquiry
		
		conn = DBConn.getConnection(); //DB연결
		
		
		
		//수행할 SQL 쿼리
		String sql = "";
		sql += "UPDATE inquiryboard SET iBoardTitle = ?, iBoardContent =?, iBoardDate = TO_CHAR(SYSDATE, 'YYYY-MM-DD'), ";
		sql	+= " iBoardInquiryType = ?, iBoardSecret =?, iBoardSecretPw =? WHERE iBoardNo=?";
		
		try {
			ps = conn.prepareStatement(sql); // SQL 수행 객체

			//SQL쿼리의 ?채우기
			
			ps.setString(1, inquiryBoard.getiBoardTitle());
			ps.setString(2, inquiryBoard.getiBoardContent());
			ps.setString(3, inquiryBoard.getiBoardInquiryType());
			ps.setString(4, inquiryBoard.getiBoardSecret());
			ps.setString(5, inquiryBoard.getiBoardSecretPw());
//			ps.setString(6, inquiryBoard.getiBoardAnswer());
			ps.setInt(6, inquiryBoard.getiBoardNo());
			
			
			
			ps.executeUpdate(); //SQL 수행
			
			System.out.println(inquiryBoard.getiBoardSecret());
			System.out.println(inquiryBoard.getiBoardContent());
			System.out.println(inquiryBoard.getiBoardInquiryType());
			System.out.println(inquiryBoard.getiBoardSecret());
			System.out.println(inquiryBoard.getiBoardSecretPw());
//			System.out.println(inquiryBoard.getiBoardAnswer());
			System.out.println(inquiryBoard.getiBoardNo());
//			System.out.println(inquiryBoard.getiBoardNo());
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
	public void updateFile(InquiryBoardFile inquiryBoardFile) {
		conn = DBConn.getConnection(); //DB연결

		System.out.println("업데이트까지 오냐 ?" + inquiryBoardFile);
		
		//수행할 SQL 쿼리
		String sql = "";
		sql += "UPDATE inquiryboardfile SET originalName=?, storedName=?, fileSize=?, writedate=sysdate WHERE iboardno=?";

		try {
			ps = conn.prepareStatement(sql); // SQL 수행 객체
			
			//SQL쿼리의 ?채우기
			ps.setString(1, inquiryBoardFile.getOriginalName());
			ps.setString(2, inquiryBoardFile.getStoredName());
//			ps.setString(3, inquiryBoardFile.getFileType());
			ps.setInt(3, inquiryBoardFile.getFileSize());
			ps.setInt(4, inquiryBoardFile.getiBoardNo());

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
		
		System.out.println("업데이트 끝나고 변경 ?" + inquiryBoardFile);
		
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
	public void delete(InquiryBoard deleteBoard) {
		
		conn = DBConn.getConnection(); //DB연결
		
		//수행할 SQL 쿼리
		String sql = "";
		sql += "DELETE FROM inquiryboard WHERE iboardno=?";

		
		try {
			ps = conn.prepareStatement(sql); // SQL 수행 객체
			
			//SQL쿼리의 ?채우기
			ps.setInt(1, deleteBoard.getiBoardNo());
			

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
	public void updateInsultFile(InquiryBoardFile inquiryBoardFile) {
		conn = DBConn.getConnection(); //DB연결
		
		//수행할 SQL 쿼리
		String sql = "";
		sql += "INSERT INTO inquiryboardfile VALUES (?, ?, ?, 'txt', ?,TO_CHAR(SYSDATE, 'YYYY.MM.DD'), ?)";

		try {
			ps = conn.prepareStatement(sql); // SQL 수행 객체
			
			//SQL쿼리의 ?채우기
			ps.setInt(1, inquiryBoardFile.getFileNo());
			ps.setString(2, inquiryBoardFile.getOriginalName());
			ps.setString(3, inquiryBoardFile.getStoredName());
//			ps.setString(3, inquiryBoardFile.getFileType());
			ps.setInt(4, inquiryBoardFile.getFileSize());
			ps.setInt(5, inquiryBoardFile.getiBoardNo());

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



