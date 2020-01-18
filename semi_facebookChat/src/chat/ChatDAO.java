package chat;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import dbutil.DBConn_semichat;

public class ChatDAO {
	
	DataSource dataSource;  //로컬 DB 커넥션
	
	public ChatDAO() {
		try {
			InitialContext initContext = new InitialContext();
			Context envContext = (Context) initContext.lookup("java:/comp/env");
			dataSource = (DataSource) envContext.lookup("jdbc/UserChatTest");
		} catch (NamingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public ArrayList<ChatDTO> getChatListByID(String fromID, String toID, String chatNo) {
		ArrayList<ChatDTO> chatList = null;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		String SQL = "SELECT * FROM CHAT WHERE ((fromID=? AND toID=?) OR (fromID=? AND toID=?)) AND chatNo>? ORDER BY chatTIME";
		
		try {
			conn = dataSource.getConnection();
			pstmt = conn.prepareStatement(SQL);
			pstmt.setString(1, fromID);
			pstmt.setString(2, toID);
			pstmt.setString(3, toID);
			pstmt.setString(4, fromID);
			pstmt.setInt(5, Integer.parseInt(chatNo));
			rs = pstmt.executeQuery();
			chatList = new ArrayList<ChatDTO>();
			while(rs.next()) {
				ChatDTO chat = new ChatDTO();
				chat.setChatNo(rs.getInt("chatNo"));
				chat.setFromID(rs.getString("fromID").replaceAll("","&nbsp;").replaceAll("<","&lt;").replaceAll(">","&gt;").replaceAll("\n","<br>"));
				chat.setToID(rs.getString("toID").replaceAll("","&nbsp;").replaceAll("<","&lt;").replaceAll(">","&gt;").replaceAll("\n","<br>"));
				chat.setChatContent(rs.getString("chatContent").replaceAll("","&nbsp;").replaceAll("<","&lt;").replaceAll(">","&gt;").replaceAll("\n","<br>"));
				int chatTime = Integer.parseInt(rs.getString("chatTime").substring(11, 13));
				String timeType = "오전";
				if(chatTime > 12) {
					timeType = "오후";
					chatTime -= 12;
				}
				chat.setChatTime(rs.getString("chatTime").substring(0, 11) + " " + timeType + " " + chatTime + ":" + rs.getString("chatTime").substring(14, 16) + "");
				chatList.add(chat);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if(rs!=null) rs.close();
				if(pstmt!=null) pstmt.close();
				if(conn!=null) conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
//		System.out.println(chatList);
		return chatList;
	}
	
	public ArrayList<ChatDTO> getChatListByRecent(String fromID, String toID, int number) {
		ArrayList<ChatDTO> chatList = null;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String SQL = "SELECT * FROM CHAT WHERE ((fromID=? AND toID=?) OR (fromID=? AND toID=?)) AND "
				+ "chatNo>(SELECT MAX(chatNo)-? FROM CHAT WHERE (fromID=? AND  toID=?) OR (fromID=? AND toID=?)) ORDER BY chatTIME";
		try {
			conn = dataSource.getConnection();
			pstmt = conn.prepareStatement(SQL);
			pstmt.setString(1, fromID);
			pstmt.setString(2, toID);
			pstmt.setString(3, toID);
			pstmt.setString(4, fromID);
			pstmt.setInt(5, number);
			pstmt.setString(6, fromID);
			pstmt.setString(7, toID);
			pstmt.setString(8, toID);
			pstmt.setString(9, fromID);
			rs = pstmt.executeQuery();
			chatList = new ArrayList<ChatDTO>();
			while(rs.next()) {
				ChatDTO chat = new ChatDTO();
				chat.setChatNo(rs.getInt("chatNo"));
				chat.setFromID(rs.getString("fromID").replaceAll("","&nbsp;").replaceAll("<","&lt;").replaceAll(">","&gt;").replaceAll("\n","<br>"));
				chat.setToID(rs.getString("toID").replaceAll("","&nbsp;").replaceAll("<","&lt;").replaceAll(">","&gt;").replaceAll("\n","<br>"));
				chat.setChatContent(rs.getString("chatContent").replaceAll("","&nbsp;").replaceAll("<","&lt;").replaceAll(">","&gt;").replaceAll("\n","<br>"));
				int chatTime = Integer.parseInt(rs.getString("chatTime").substring(11, 13));
				String timeType = "오전";
				if(chatTime > 12) {
					timeType = "오후";
					chatTime -= 12;
				}
				chat.setChatTime(rs.getString("chatTime").substring(0, 11) + " " + timeType + " " + chatTime + ":" + rs.getString("chatTime").substring(14, 16) + "");
				chatList.add(chat);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if(rs!=null) rs.close();
				if(pstmt!=null) pstmt.close();
				if(conn!=null) conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return chatList;
	}
	
	public ArrayList<ChatDTO> getBox(String userID) {
		ArrayList<ChatDTO> chatList = null;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String SQL = "SELECT * FROM CHAT WHERE chatNo IN (SELECT MAX(chatNo) FROM CHAT WHERE toID=? OR fromID=? GROUP BY fromID, toID) ORDER BY chatTIME";
		try {
			conn = dataSource.getConnection();
			pstmt = conn.prepareStatement(SQL);
			pstmt.setString(1, userID);
			pstmt.setString(2, userID); 
			rs = pstmt.executeQuery();
			chatList = new ArrayList<ChatDTO>();
			while(rs.next()) {
				ChatDTO chat = new ChatDTO();
				chat.setChatNo(rs.getInt("chatNo"));
				chat.setFromID(rs.getString("fromID").replaceAll("","&nbsp;").replaceAll("<","&lt;").replaceAll(">","&gt;").replaceAll("\n","<br>"));
				chat.setToID(rs.getString("toID").replaceAll("","&nbsp;").replaceAll("<","&lt;").replaceAll(">","&gt;").replaceAll("\n","<br>"));
				chat.setChatContent(rs.getString("chatContent").replaceAll("","&nbsp;").replaceAll("<","&lt;").replaceAll(">","&gt;").replaceAll("\n","<br>"));
				int chatTime = Integer.parseInt(rs.getString("chatTime").substring(11, 13));
				String timeType = "오전";
				if(chatTime > 12) {
					timeType = "오후";
					chatTime -= 12;
				}
				chat.setChatTime(rs.getString("chatTime").substring(0, 11) + " " + timeType + " " + chatTime + ":" + rs.getString("chatTime").substring(14, 16) + "");
				chatList.add(chat);
			}
			for(int i=0;i<chatList.size(); i++) {
				ChatDTO x = chatList.get(i);
				for(int j=0;j<chatList.size();j++) {
					ChatDTO y = chatList.get(j);
					if(x.getFromID().equals(y.getToID()) && x.getToID().equals(y.getFromID())) {
						if(x.getChatNo() < y.getChatNo()) {
							chatList.remove(x);
							i--;
							break;
						} else {
							chatList.remove(y);
							j--;
						}
					}
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if(rs!=null) rs.close();
				if(pstmt!=null) pstmt.close();
				if(conn!=null) conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return chatList;
	}
//-------------------------------------------------------------------------------------------------------------------
	
//----------------------------------------------------------------------------------------------------------------------------	
	
	//2019-11-27 현석 // semiChat으로 들어오기 전에 상담자 정보 있는지 검사
	public int getClientInfoNo(String clientId) {
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		int clientInfoNo = -1;
		String sql = "";
		
		sql += "SELECT DISTINCT";
		sql += " a.clientInfoNo";
		sql += " from ClientInfo a, chatprofile b";
		sql += " where 1=1";
		sql += " and a.clientInfoNo = b.clientInfoNo"; 
		sql += " and b.clientId = ?";
		
		try {
			conn = DBConn_semichat.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, clientId);
			
			rs = pstmt.executeQuery();//SQL 수행 결과 얻기
			
			while(rs.next()) { 
				clientInfoNo = rs.getInt(1);
			}
			
			System.out.println("정보 검사 : " + clientInfoNo);

			return clientInfoNo;
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if(rs!= null) rs.close();
				if(pstmt!=null) pstmt.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		return -1;
	}
	
	//2019-11-27 clientInfoNo nextval
	public int getClientInfo_seq() {
		
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		int clientInfoNo = -1;
		
		//SQL 쿼리
		String sql = "";
		sql += "SELECT clientInfo_seq.nextval clientInfoNo FROM dual";
	
		try {
			conn = DBConn_semichat.getConnection(); //DB연결
			ps = conn.prepareStatement(sql); //쿼리 수행 객체 얻기
			rs = ps.executeQuery(); //SQL 수행결과 얻기
			
			//유저 넘버 구하기
			while(rs.next()) {
				clientInfoNo = rs.getInt("clientInfoNo");
				System.out.println(clientInfoNo);
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
		
		return clientInfoNo;
	}
	
	//2019-11-27 현석 // 첫 상담자일 경우 clientInfo에 데이터 삽입
	public void insertClientInfo(int clientInfoNo) {
		
		Connection conn = null;
		PreparedStatement ps = null;
		
		String sql = "";
		
		sql += "INSERT INTO ClientInfo(clientInfoNo) VALUES ( ? )";

		try {
			conn = DBConn_semichat.getConnection();
			ps = conn.prepareStatement(sql);
			ps.setInt(1, clientInfoNo);
			
			ps.executeUpdate();//SQL 수행 결과 얻기
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if(ps!=null) ps.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
	}
	
	//2019-11-27 현석 //메시지 이용가능한지?? -환경설정
	public Boolean isAvailableMessenger() {
		
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		int useStatus = -1;
		
		//사용가능여부 파악(홍철 4:페이스북)
		String sql = "";
		sql += " SELECT USESTATUS";
		sql += " FROM MESSENGER";
		sql += " WHERE MESSENGERNO = 4";
		
		try {
			conn = DBConn_semichat.getConnection(); //DB연결
			ps = conn.prepareStatement(sql); //쿼리 수행 객체 얻기
			rs = ps.executeQuery(); //SQL 수행결과 얻기
			
			//유저 넘버 구하기
			while(rs.next()) {
				useStatus = rs.getInt("usestatus"); 
			}
			
			if(useStatus == 1) return true;
			
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
		
		
		return false;
	}
	
	/***********************/
	/*********!!!!!!!!******/
	/***********************/
	
	//2019-11-27 현석 //semiChat으로 들어오는 메시지 + 메신저로 들어오는 메시지 
	public int multisubmit(String fromID, String toID, String chatContent) {
		
		int is_success_semichat = -1;
		int is_success_chat = -1;
		
		if(toID.equals("semichat")) 
			is_success_semichat = submit_to_semichat(fromID, chatContent);
		else if(fromID.equals("semichat"))
			is_success_semichat = submit_from_semichat(toID, chatContent);
		else is_success_semichat = 1;
		
		is_success_chat = submit(fromID, toID, chatContent);
		
		if((is_success_chat == 1) && (is_success_semichat == 1)) return 1;
		else return -1;
	}
	
	
	//2019-11-27 현석 // semiChat으로 들어오는 메시지 분리(일단은 아이디만검사)
	public int submit_to_semichat(String clientId, String chatContent) {
		
		//0. 메신저 넣기(넣기 전에 메신저 사용가능한지 확인)
		Boolean isAvailable = isAvailableMessenger();
		if(isAvailable == false) submit(clientId, "semichat", chatContent); //메신저 사용불가면 일반 서브밋으로 들어감
		
		System.out.println(isAvailable);
		System.out.println("0. clientId : "+clientId);
		
		//1. 상담자 정보 있는지 검사
		int clientInfoNo = getClientInfoNo(clientId);
		
		System.out.println("1. clientInfoNo : "+clientInfoNo);
		
		//2. 상담자 정보 없으면 clientInfoNo nextval한다음 인서트
		if(clientInfoNo < 1) {
			clientInfoNo = getClientInfo_seq();
			System.out.println("2. if문 안 clientInfoNo : "+clientInfoNo);
			insertClientInfo(clientInfoNo);
		}
		
		//3. 채팅 프로필에 데이터 넣기
		Boolean chatProfile_flag = true;	// false 시 insert
		chatProfile_flag = isChatRun(clientId);
		
		// 채팅 프로필에 데이터 있는지 검사
		int chatProfileNo = 0;
		if(chatProfile_flag == false) {
			insertChatProfile(clientId, clientInfoNo);
		}
		
		//4. 채팅 프로필 넘버 가져오기
		chatProfileNo = getChatProfileNo(clientId);
		
		//5. 채팅에 데이터 넣기
		Connection conn = null;
		PreparedStatement pstmt = null;
		
		String SQL = "INSERT INTO CHAT VALUES (chat_seq.nextval, ?, ?, 'semichat', ?, 0,TO_CHAR(SYSDATE, 'YYYY.MM.DD HH24:MI:SS'),0)";
		try {
			conn = DBConn_semichat.getConnection();
			pstmt = conn.prepareStatement(SQL);
			pstmt.setInt(1, chatProfileNo);
			pstmt.setString(2, clientId);
			pstmt.setString(3, chatContent);
				return pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if(pstmt!=null) pstmt.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return -1;	// 데이터베이스 오류
		
	}
	
	//세미쳇으로 상담원이 상담자에게 보냄
	public int submit_from_semichat(String clientId, String chatContent) {
		
		Connection conn = null;
		PreparedStatement ps = null;
		
		String SQL = "INSERT INTO CHAT VALUES (chat_seq.nextval, ?, 'semichat', ?, ?, 0,TO_CHAR(SYSDATE, 'YYYY.MM.DD HH24:MI:SS'),0)";
		
		int chatProfileNo = getChatProfileNo(clientId);
		
		try {
			conn = DBConn_semichat.getConnection();
			ps = conn.prepareStatement(SQL);
			ps.setInt(1, chatProfileNo);
			ps.setString(2, clientId);
			ps.setString(3, chatContent);
				return ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if(ps!=null) ps.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return -1;	// 데이터베이스 오류
	}
	
//	public int veryRealsubmit(String fromID, String toID, String chatContent) {
//		System.out.println(fromID);
//		System.out.println(toID);
//		// Boolean 변수 선언 -> 진행중:true new:false
//
//		// select()함수 : Boolean -- select clientID from chatProfile where clientID =
//		// fromID ;
//		// select 있으면 -> Boolean true 없으면 false
//
//		// if문 (Boolean: false)
//		// {
//		// int chatProfileNum = insert(chatProfile) : chatProfileNum -- nextval
//		// boolean = true;
//		// }
//		
//		Boolean chatProfile_flag = true;	// false 시 insert
//
//		//toId나 fromId가 semichat일때
//		//회원가입시 전화번호 입력하게 해야함, 전화번호 없으면 아이디만
//		
//		chatProfile_flag = select(fromID, toID);
//		System.out.println(chatProfile_flag);
//		
//		int chatProfileNo = 0;
//		
//		if(chatProfile_flag==false) {
//			submit(fromID, clientInfoNo);
//			chatProfileNo = get(fromID,toID);
//			System.out.println(chatProfileNo);
//		} else {
//			chatProfileNo = get(fromID,toID);
//			System.out.println(chatProfileNo);
//		}
//		
//		Connection conn = null;
//		PreparedStatement pstmt = null;
//		String SQL = "INSERT INTO CHAT VALUES (chat_seq.nextval, ?, ?, ?, ?, 0,TO_CHAR(SYSDATE, 'YYYY.MM.DD HH24:MI:SS'),0)";
//		try {
//			conn = dataSource.getConnection();
//			pstmt = conn.prepareStatement(SQL);
//			pstmt.setInt(1, chatProfileNo);
//			pstmt.setString(2, fromID);
//			pstmt.setString(3, toID);
//			pstmt.setString(4, chatContent);
//				return pstmt.executeUpdate();
//		} catch (SQLException e) {
//			e.printStackTrace();
//		} finally {
//			try {
//				if(pstmt!=null) pstmt.close();
//				if(conn!=null) conn.close();
//			} catch (SQLException e) {
//				e.printStackTrace();
//			}
//		}
//		return -1;	// 데이터베이스 오류
//		
//	}
	
	//2019-11-27 현석 현재 채팅이 진행중인지 새로 유입됬는지?? //select 메소드 변경함
	public boolean isChatRun(String clientId) {
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		String SQL = "SELECT fromID FROM CHAT WHERE 1=1";
		SQL += " AND fromID = ? AND toID = 'semichat' ";
		SQL += " AND ( SELECT COUNT(*) FROM CHATPROFILE WHERE clientID = ? ) > 0";
		
		try {
			
			conn = DBConn_semichat.getConnection();
			ps = conn.prepareStatement(SQL);
			ps.setString(1, clientId);
			ps.setString(2, clientId);
			
			rs=ps.executeQuery();
			
			while (rs.next()) {
				return true;
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if(ps!=null) ps.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return false;
	}
	
	//채팅 프로필 넘버 가져오기
	public int getChatProfileNo (String clientId) {
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;

		String SQL = "SELECT MAX(chatProfileNo) FROM CHATPROFILE WHERE clientID=?";
		try {
			conn = DBConn_semichat.getConnection();
			ps = conn.prepareStatement(SQL);
			ps.setString(1, clientId);
			rs = ps.executeQuery();
			while (rs.next()) {
				return rs.getInt(1); 
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if(ps!=null) ps.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return -1;
	}

	//세미쳇 서브밋  : 채팅 프로필 만들어주기1
	public void insertChatProfile(String fromID, int clientInfoNo) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		String SQL = "INSERT INTO CHATPROFILE VALUES (chatProfile_seq.nextval, 4, ?, 0, null, 0, TO_CHAR(SYSDATE, 'YYYY.MM.DD HH24:MI:SS'), 0, ?)";
		try {
			conn = DBConn_semichat.getConnection();
			pstmt = conn.prepareStatement(SQL);
			pstmt.setString(1, fromID);
			pstmt.setInt(2, clientInfoNo);
			
			pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if(pstmt!=null) pstmt.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
	
	
	//일반 서브밋 : 채팅 프로필 만들어주기2
	 public int insertChatProfile(String fromID) {
	      Connection conn = null;
	      PreparedStatement pstmt = null;
	      String SQL = "INSERT INTO CHATPROFILE VALUES (chatProfile_seq.nextval, 4, ?, 0, null, 0, TO_CHAR(SYSDATE, 'YYYY.MM.DD HH24:MI:SS'), 0)";
	      try {
	         conn = dataSource.getConnection();
	         pstmt = conn.prepareStatement(SQL);
	         pstmt.setString(1, fromID);
	            return pstmt.executeUpdate();
	      } catch (SQLException e) {
	         e.printStackTrace();
	      } finally {
	         try {
	            if(pstmt!=null) pstmt.close();
	            if(conn!=null) conn.close();
	         } catch (SQLException e) {
	            e.printStackTrace();
	         }
	      }
	      return -1;   // 데이터베이스 오류
	   }
	
	//일반 서브밋
	public int submit(String fromID, String toID, String chatContent) {
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		
		Boolean chatProfile_flag = true;	// false 시 insert

		//toId나 fromId가 semichat일때
		//회원가입시 전화번호 입력하게 해야함, 전화번호 없으면 아이디만
		
		chatProfile_flag = select(fromID, toID);
		System.out.println(chatProfile_flag);
		
		int chatProfileNo = 0;
		
		if(chatProfile_flag==false) {
			insertChatProfile(fromID);
			chatProfileNo = get(fromID,toID);
			System.out.println(chatProfileNo);
		} else {
			chatProfileNo = get(fromID,toID);
			System.out.println(chatProfileNo);
		}
		
		
		String SQL = "INSERT INTO CHAT VALUES (chat_seq.nextval, ?, ?, ?, ?, 4, TO_CHAR(SYSDATE, 'YYYY.MM.DD HH24:MI:SS'),0)";
		
		try {
			conn = dataSource.getConnection();
			pstmt = conn.prepareStatement(SQL);
			pstmt.setInt(1, chatProfileNo);
			pstmt.setString(2, fromID);
			pstmt.setString(3, toID);
			pstmt.setString(4, chatContent);
			
				return pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if(pstmt!=null) pstmt.close();
				if(conn!=null) conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return -1;	// 데이터베이스 오류
	}
	
	public int readChat(String fromID, String toID) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		String SQL = "UPDATE CHAT SET chatRead=1 WHERE (fromID=? AND  toID=?)";
		try {
			conn = dataSource.getConnection();
			pstmt = conn.prepareStatement(SQL);
			pstmt.setString(1, toID); 
			pstmt.setString(2, fromID);
			return pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if(rs!=null) rs.close();
				if(pstmt!=null) pstmt.close();
				if(conn!=null) conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return -1;	// 데이터베이스 오류
	}
	
	public int getAllUnreadChat(String userID) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		String SQL = "SELECT COUNT(chatNo) FROM CHAT WHERE toID=? AND chatRead=0 ";
		try {
			conn = dataSource.getConnection();
			pstmt = conn.prepareStatement(SQL);
			pstmt.setString(1,userID);
			rs=pstmt.executeQuery();
			if(rs.next()) {
				return rs.getInt("COUNT(chatNo)"); 
			}
			return 0;
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if(rs!=null) rs.close();
				if(pstmt!=null) pstmt.close();
				if(conn!=null) conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return -1;	// 데이터베이스 오류
	}
	
	public String getUnreadChat(String fromID, String toID) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		String SQL = "SELECT COUNT(chatNo) FROM CHAT WHERE fromID=? AND toID=? AND chatRead=0";
		try {
			conn = dataSource.getConnection();
			pstmt = conn.prepareStatement(SQL);
			pstmt.setString(1,fromID);
			pstmt.setString(2,toID);
			rs=pstmt.executeQuery();
			if(rs.next()) {
				return rs.getString("COUNT(chatNo)"); 
			}
			return "0";
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if(rs!=null) rs.close();
				if(pstmt!=null) pstmt.close();
				if(conn!=null) conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return null;	// 데이터베이스 오류
	}
	
	//이건 유진이꺼 // chatProfileNo가져옴 2019-11-27
	public int get(String fromID, String toID) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		String SQL = "SELECT chatProfileNo FROM CHATPROFILE WHERE (clientID=? OR clientID=?)";
		try {
			conn = dataSource.getConnection();
			pstmt = conn.prepareStatement(SQL);
			pstmt.setString(1, fromID);
			pstmt.setString(2, toID);
			rs=pstmt.executeQuery();
			while (rs.next()) {
				return rs.getInt("chatProfileNo"); 
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if(pstmt!=null) pstmt.close();
				if(conn!=null) conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return -1;
	}
	
	//2019-11-27 유진 - 피땀눈물
	public boolean select(String fromID, String toID) {
	      Connection conn = null;
	      PreparedStatement pstmt = null;
	      ResultSet rs = null;
	      
	      String SQL = "SELECT fromID FROM CHAT WHERE ((fromID=? AND toID=?) OR (fromID=? AND toID=?)) "
	            + "AND ((SELECT COUNT(*) FROM CHATPROFILE WHERE (clientID=? OR clientID=?)) > 0)";
	      
	      try {
	         conn = dataSource.getConnection();
	         pstmt = conn.prepareStatement(SQL);
	         pstmt.setString(1, fromID);
	         pstmt.setString(2, toID);
	         pstmt.setString(3, toID);
	         pstmt.setString(4, fromID);
	         pstmt.setString(5, fromID);
	         pstmt.setString(6, toID);
	         rs=pstmt.executeQuery();
	         while (rs.next()) {
	            return true;
	         }
	      } catch (SQLException e) {
	         e.printStackTrace();
	      } finally {
	         try {
	            if(pstmt!=null) pstmt.close();
	            if(conn!=null) conn.close();
	         } catch (SQLException e) {
	            e.printStackTrace();
	         }
	      }
	      return false;
	   }
	
}
