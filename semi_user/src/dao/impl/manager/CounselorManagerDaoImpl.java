package dao.impl.manager;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import dao.face.manager.CounselorManagerDao;
import dbutil.DBConn;
import dto.Counselor;
import dto.FrequentReply;
import dto.InquiryBoard;
import util.Paging;

public class CounselorManagerDaoImpl implements CounselorManagerDao{

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
		sql += " FROM counselor";
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
				
				if(key == "searchType")
					searchType = value;
				else if(key == "search")
					search = value;
				
			}
			sql_tmp += " AND "+searchType+ " like '%"+search+"%'";
		}
	
		//수행할 SQL 쿼리
		String sql = "";
		sql += "SELECT * FROM";  
		sql +=	" (SELECT rownum rnum, B.* FROM(select counselorno, counselorname, counselorposition, counselorsigndate from counselor";
		sql += " WHERE 1 = 1";
		sql +=  sql_tmp;
		sql +=	" ORDER BY counselorno DESC)";
		sql +=  " B ORDER BY rnum) counselor WHERE rnum BETWEEN ? AND ?";
		//수행결과를 담을 리스트
		
		System.out.println(sql);
		List list = new ArrayList();
		
		try {
			ps = conn.prepareStatement(sql);//수행객체얻기
			ps.setInt(1, paging.getStartNo());
			ps.setInt(2, paging.getEndNo());
			
			rs = ps.executeQuery();//SQL수행결과얻기
			
			//SQL 수행결과 처리
			while(rs.next()) {
				Counselor counselor = new Counselor(); //각 행을 처리할 DTO
				
				counselor.setRnum(rs.getInt("rnum"));
				counselor.setCounselorNo(rs.getInt("counselorno"));
				counselor.setCounselorName(rs.getString("counselorname"));
				counselor.setCounselorPosition(rs.getString("counselorposition"));
				counselor.setCounselorSigndate(rs.getString("counselorsigndate"));

				list.add(counselor);
				
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}

	@Override
	public Counselor selectCounselorProfileByNo(Counselor counselor) {

		conn = DBConn.getConnection(); //DB연결

		//수행할 SQL 쿼리
		String sql = "";
		sql += "SELECT *";
		sql += " FROM counselor";
		sql += " WHERE counselorno = ?";

		try {
			ps = conn.prepareStatement(sql); //SQL 수행 객체

			ps.setInt(1, counselor.getCounselorNo()); //SQL쿼리의 ? 채우기
			rs = ps.executeQuery(); //SQL 수행결과 얻기

			//SQL 수행 결과 처리
			while(rs.next()) {	
				counselor.setCounselorNo(rs.getInt("counselorno"));
				counselor.setCounselorName(rs.getString("counselorname"));
				counselor.setCounselorId( rs.getString("counselorid") );
				counselor.setCounselorPassword( rs.getString("counselorpassword") );
				counselor.setCounselorNickname( rs.getString("counselornickname") );
				counselor.setCounselorEmail(rs.getString("counseloremail"));
				counselor.setCounselorPhonenumber(rs.getString("counselorphonenumber"));
				counselor.setCounselorSigndate(rs.getString("counselorsigndate"));
				counselor.setCounselorPosition(rs.getString("counselorposition"));
				counselor.setCounselorMarketingagree(rs.getInt("counselormarketingagree"));
				counselor.setCounselorEmailchecked(rs.getInt("counseloremailchecked"));
				counselor.setManagerConfirm(rs.getInt("managerconfirm"));
				counselor.setCorporationNo(rs.getInt("corporationno"));
				counselor.setCounselorEmailHash(rs.getString("counselorEmailHash"));
				counselor.setCounselorWithdrawalchecked(rs.getInt("counselorWithdrawalchecked"));
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
		return counselor;
	}

	@Override
	public List selectAccountAll(Paging paging) {

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

		//수행할 SQL 쿼리
		String sql = "";
		sql += "SELECT * FROM";  
		sql +=	" (SELECT rownum rnum, B.* FROM(select counselorno, counselorname, counselorposition, counselorsigndate, managerconfirm, counselorwithdrawalchecked from counselor";
		sql += " WHERE 1 = 1";
		sql +=  sql_tmp;
		sql +=	" ORDER BY managerconfirm, counselorwithdrawalchecked DESC, counselorno DESC)";
		sql +=  " B ORDER BY rnum) counselor WHERE rnum BETWEEN ? AND ?";
		//수행결과를 담을 리스트

		System.out.println(sql);
		List list = new ArrayList();

		try {
			ps = conn.prepareStatement(sql);//수행객체얻기
			ps.setInt(1, paging.getStartNo());
			ps.setInt(2, paging.getEndNo());

			rs = ps.executeQuery();//SQL수행결과얻기

			//SQL 수행결과 처리
			while(rs.next()) {
				Counselor counselor = new Counselor(); //각 행을 처리할 DTO

				counselor.setRnum(rs.getInt("rnum"));
				counselor.setCounselorNo(rs.getInt("counselorno"));
				counselor.setCounselorName(rs.getString("counselorname"));
				counselor.setCounselorPosition(rs.getString("counselorposition"));
				counselor.setCounselorSigndate(rs.getString("counselorsigndate"));
				counselor.setManagerConfirm(rs.getInt("managerconfirm"));
				counselor.setCounselorWithdrawalchecked(rs.getInt("counselorwithdrawalchecked"));
				
				list.add(counselor);

			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}

	@Override
	public void updateManagerConfirm(String counselorNo) {
		conn = DBConn.getConnection();	//DB 연결
		String sql = "";
		sql += "UPDATE counselor set managerconfirm = 1 where counselorno = ?";

		try {
			ps = conn.prepareStatement(sql);	//수행 객체 얻기
			ps.setString(1, counselorNo);	//SQL쿼리의 ? 채우기
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

	@Override
	public void updateChatProfileToNull(String counselorId) {
		
		conn = DBConn.getConnection();	//DB 연결
		String sql = "";
		sql += "UPDATE chatprofile set counselorid = null where counselorid = ?";

		try {
			ps = conn.prepareStatement(sql);	//수행 객체 얻기
			ps.setString(1, counselorId);	//SQL쿼리의 ? 채우기
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

	@Override
	public void deleteMyClientInfo(String counselorId) {

		conn = DBConn.getConnection(); //DB연결

		//수행할 SQL 쿼리
		String sql = "";
		sql += "DELETE FROM myclientinfo WHERE counselorid=?";

		try {
			ps = conn.prepareStatement(sql); // SQL 수행 객체

			//SQL쿼리의 ?채우기
			ps.setString(1, counselorId);

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
	public void deleteInquiryBoardFile(int iBoardNo) {

		conn = DBConn.getConnection(); //DB연결

		//수행할 SQL 쿼리
		String sql = "";
		sql += "DELETE FROM inquiryboardfile WHERE iboardno=?";

		try {
			ps = conn.prepareStatement(sql); // SQL 수행 객체

			//SQL쿼리의 ?채우기
			ps.setInt(1, iBoardNo);

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
	public void deleteinquiryBoard(String counselorId) {
		conn = DBConn.getConnection(); //DB연결

		//수행할 SQL 쿼리
		String sql = "";
		sql += "DELETE FROM inquiryboard WHERE counselorid=?";

		try {
			ps = conn.prepareStatement(sql); // SQL 수행 객체

			//SQL쿼리의 ?채우기
			ps.setString(1, counselorId);

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
	public void deletenoticeBoard(String counselorId) {
		conn = DBConn.getConnection(); //DB연결

		//수행할 SQL 쿼리
		String sql = "";
		sql += "DELETE FROM noticeboard WHERE counselorid=?";

		try {
			ps = conn.prepareStatement(sql); // SQL 수행 객체

			//SQL쿼리의 ?채우기
			ps.setString(1, counselorId);

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
	public void updateClientInfoToNull(String counselorName) {

		System.out.println("카운셀러 네임 뭔데 ? " +counselorName);

		conn = DBConn.getConnection();	//DB 연결
		String sql = "";
		sql += "UPDATE clientinfo set counselorname = null where counselorname = ?";

		try {
			ps = conn.prepareStatement(sql);	//수행 객체 얻기
			ps.setString(1, counselorName);	//SQL쿼리의 ? 채우기
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

	@Override
	public void deleteCounselor(String counselorNo) {
		conn = DBConn.getConnection(); //DB연결

		//수행할 SQL 쿼리
		String sql = "";
		sql += "DELETE FROM counselor WHERE counselorno=?";

		try {
			ps = conn.prepareStatement(sql); // SQL 수행 객체

			//SQL쿼리의 ?채우기
			ps.setInt(1, Integer.parseInt(counselorNo));
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
	public Counselor selectCounselorIdByNo(String counselorNo) {
	
		conn = DBConn.getConnection();	//DB 연결
		
		//쿼리 작성
		String sql = "";
		sql += "SELECT counselorid FROM counselor";
		sql += " WHERE counselorno=?";
		
		Counselor counselor = new Counselor();

		try {
			//DB작업
			ps = conn.prepareStatement(sql);
			ps.setString(1, counselorNo);

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
	public List<InquiryBoard> selectIboardNoByCounselorId(String counselorId) {
		conn = DBConn.getConnection();	//DB 연결

		//쿼리 작성
		String sql = "";
		sql += "SELECT iboardno FROM inquiryboard";
		sql += " WHERE counselorid=?";

		List list = new ArrayList<>();

		try {
			//DB작업
			ps = conn.prepareStatement(sql);
			ps.setString(1, counselorId);

			rs = ps.executeQuery();

			while( rs.next() ) {
				InquiryBoard inquiryBoard = new InquiryBoard();
				inquiryBoard.setiBoardNo(rs.getInt("iBoardNo"));
				list.add(inquiryBoard);
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

		return list;
	}

	@Override
	public Counselor selectCounselorNameByNo(String counselorNo) {
		conn = DBConn.getConnection();	//DB 연결

		//쿼리 작성
		String sql = "";
		sql += "SELECT counselorname FROM counselor";
		sql += " WHERE counselorno=?";

		Counselor counselor = new Counselor();

		try {
			//DB작업
			ps = conn.prepareStatement(sql);
			ps.setString(1, counselorNo);

			rs = ps.executeQuery();

			while( rs.next() ) {
				counselor.setCounselorName(rs.getString("counselorName"));
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
	public List<FrequentReply> selectFrequentReply() {
		conn = DBConn.getConnection();	//DB 연결

		//쿼리 작성
		String sql = "";
		sql += "SELECT frequentReplyNo, frequentreplycontent FROM frequentreply";
		sql += " WHERE frequentreplyindesk=1";
		sql += " ORDER BY frequentreplyno";

		List list = new ArrayList<>();

		try {
			//DB작업
			ps = conn.prepareStatement(sql);
			rs = ps.executeQuery();

			while( rs.next() ) {
				FrequentReply frequentReply = new FrequentReply();
				frequentReply.setFrequentReplyNo(rs.getInt("frequentReplyNo"));
				frequentReply.setFrequentReplyContent(rs.getString("frequentReplyContent"));
				list.add(frequentReply);
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

		return list;
	}

	@Override
	public void insertFrequentReply(String frequentReplyContent) {
		
		conn = DBConn.getConnection();

		//쿼리작성
		String sql = "";
		sql += "INSERT INTO FREQUENTREPLY";
		sql += " VALUES( frequentReply_seq.nextval, ?, 1)";
		
		try {
			//DB작업
			ps = conn.prepareStatement(sql);
			ps.setString(1, frequentReplyContent);
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
	public void deleteFrequentReply(String frequentReplyNo) {

		conn = DBConn.getConnection(); //DB연결

		//수행할 SQL 쿼리
		String sql = "";
		sql += "DELETE FROM frequentreply WHERE frequentreplyno=?";

		try {
			ps = conn.prepareStatement(sql); // SQL 수행 객체

			//SQL쿼리의 ?채우기
			ps.setInt(1, Integer.parseInt(frequentReplyNo));

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
