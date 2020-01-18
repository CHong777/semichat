package dao.impl.mypage;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import dao.face.mypage.MyClientListDao;
import dbutil.DBConn;
import dto.InquiryBoard;
import dto.MyClientInfo;
import util.Paging;

public class MyClientListDaoImpl implements MyClientListDao {

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
		sql += " FROM myclientinfo, clientinfo where myclientinfo.clientinfono=clientinfo.clientinfono AND counselorid = ?";
		sql += search_sql;
	

		try {
			//SQL
			ps = conn.prepareStatement(sql);
			ps.setString(1, counselorid);
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
		System.out.println("페ㅇㅣ징카운트 : " + cnt);
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
			 sql_tmp += " AND "+searchType+ " like '%"+search+"%'";
	      }
	      
		String sql = "";
		sql += "SELECT * FROM";  
		sql +=	" (SELECT rownum rnum, B.* FROM(SELECT myclientinfono, counselorno, counselorid, isblock, isfixedmatch, clientnick, myclientinfo.clientinfono, clientname, clientphonenum, lastchatdate, chatmemo";   
		sql +=	" FROM myclientinfo, clientinfo WHERE myclientinfo.clientinfono = clientinfo.clientinfono AND counselorid = ?";
		sql +=  sql_tmp;
		sql +=	" ORDER BY myclientinfono DESC) ";
		sql +=  " B ORDER BY rnum) BOARD WHERE rnum BETWEEN ? AND ?";
//		수행결과를 담을 리스트
		
		List list = new ArrayList();
		System.out.println("13424234" + sql);
		try {
			ps = conn.prepareStatement(sql);//수행객체얻기
			
			ps.setString(1, paging.getCounselorId());
			ps.setInt(2, paging.getStartNo());
			ps.setInt(3, paging.getEndNo());
			
//			System.out.println(sql);
			rs = ps.executeQuery();//SQL수행결과얻기
			
//			System.out.println(paging.getStartNo());
			
			//SQL 수행결과 처리
			while(rs.next()) {
				MyClientInfo myClientInfo = new MyClientInfo();
				
				myClientInfo.setMyClientInfoNo(rs.getInt("myclientinfono"));
				myClientInfo.setCounselorNo(rs.getInt("counselorno"));
				myClientInfo.setCounselorId(rs.getString("counselorid"));
				myClientInfo.setIsBlock(rs.getInt("isblock"));
				myClientInfo.setIsFixedMatch(rs.getInt("isfixedmatch"));
				myClientInfo.setClientNick(rs.getString("clientnick"));
				myClientInfo.setClientInfoNo(rs.getInt("clientinfono"));
				myClientInfo.setClientName(rs.getString("clientname"));
				myClientInfo.setClientPhoneNum(rs.getString("clientphonenum"));
				myClientInfo.setLastChatDate(rs.getString("lastchatdate"));
				myClientInfo.setChatMemo(rs.getString("chatmemo"));
				
				list.add(myClientInfo);
				
				System.out.println("리스트나와주어어어어어 : " + list);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		System.out.println("리스트왜안나ㅗ아아아아아앙" + list);
		return list;
		
	}

	@Override
	public void clientdelete(MyClientInfo deleteBoard) {
		conn = DBConn.getConnection(); //DB연결
		
		//수행할 SQL 쿼리
		String sql = "";
		sql += "DELETE FROM myclientinfo WHERE clientinfono=?";

		
		try {
			ps = conn.prepareStatement(sql); // SQL 수행 객체
			
			//SQL쿼리의 ?채우기
			ps.setInt(1, deleteBoard.getClientInfoNo());
			

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
	public List bookMarkSelect(String[] boardno) {
		
		conn = DBConn.getConnection(); 

		List list = new ArrayList();
	
		String boardnoList = "";
		
		Iterator iter = list.iterator();
		
		int i =0;
		for(String number: boardno) {
			
			if(i == 0)
				boardnoList += number;
			else
				boardnoList += ", "+number;

			i++;
		}
		
		System.out.println("야식이 : "+boardnoList);
		
		String sql = "";
		sql += "SELECT";
		sql += " iboardbookmark";
		sql += " FROM inquiryboard";
		
		if(boardno != null)
			sql += " WHERE iboardno IN("+boardnoList+")";
		
		System.out.println("lego : "+sql);
		
		try {
			//SQL
			ps = conn.prepareStatement(sql); 
			rs = ps.executeQuery(); 

		
			while(rs.next()) {
				list.add(rs.getInt("iboarabookmark"));
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

		return list;
	}

	@Override
	public String nBookMarkInsert(String book, String no) {
		conn = DBConn.getConnection(); //DB연결
		
		String bookMark ="";
		
		String sql = "";
		sql += "UPDATE noticeboard SET nBoardBookMark = ? WHERE nboardno = ?";
		
		//수행결과를 담을 리스트
		
		
		try {
			ps = conn.prepareStatement(sql);//수행객체얻기
			
			ps.setString(1, "Y");
			ps.setInt(2, Integer.parseInt(no));
			
			rs = ps.executeQuery(); 
			
			bookMark = "Y";
			
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
		return bookMark;
	}

	@Override
	public String iBookMarkInsert(String book, String no) {
		conn = DBConn.getConnection(); //DB연결
		
		String bookMark ="";
		
		//수행할 SQL 쿼리
		String sql = "";
		sql += "UPDATE inquiryboard SET iBoardBookMark = ? WHERE iboardno = ?";
		
		try {
			ps = conn.prepareStatement(sql);//수행객체얻기
			
			ps.setString(1, "Y");
			ps.setInt(2, Integer.parseInt(no));
			
			rs = ps.executeQuery(); 
			
			bookMark = "Y";
			
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
		return bookMark;
	}

	@Override
	public String nBookMarkDelete(String book, String no) {
		conn = DBConn.getConnection(); //DB연결
		
		String bookMark ="";
		
		String sql = "";
		sql += "UPDATE noticeboard SET nBoardBookMark = ? WHERE nboardno = ?";
		
		//수행결과를 담을 리스트
		
		
		try {
			ps = conn.prepareStatement(sql);//수행객체얻기
			
			ps.setString(1, "N");
			ps.setInt(2, Integer.parseInt(no));
			
			rs = ps.executeQuery(); 
			
			bookMark = "N";
			
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
		return bookMark;
	}

	@Override
	public String iBookMarkDelete(String book, String no) {
		conn = DBConn.getConnection(); //DB연결
		
		String bookMark ="";
		
		//수행할 SQL 쿼리
		String sql = "";
		sql += "UPDATE inquiryboard SET iBoardBookMark = ? WHERE iboardno = ?";
		
		//수행결과를 담을 리스트
		
		
		try {
			ps = conn.prepareStatement(sql);//수행객체얻기
			
			ps.setString(1, "N");
			ps.setInt(2, Integer.parseInt(no));
			
			rs = ps.executeQuery(); 
			
			bookMark = "N";
			
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
		return bookMark;
	}

}
