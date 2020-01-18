package dao.impl.chat;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import dao.face.chat.ChatDeskDao;
import dbutil.DBConn;
import dto.Chat;
import dto.ChatProfile;

//사용자 DB 연결
import dbutil.facebookDB;
import dbutil.instagramDB;
import dbutil.kakaoDB;
import dbutil.lineDB;
import dbutil.wechatDB;

public class ChatDeskDaoImpl implements ChatDeskDao{

	//DB관련 객체
	private Connection conn = null; 
	private PreparedStatement ps = null;
	private ResultSet rs = null;
	
	private PreparedStatement ps2 = null;
	private ResultSet rs2 = null;

	@Override
	public ChatProfile selectClientIdByChatProfileNo(ChatProfile chatProfile) {
		conn = DBConn.getConnection();

		//쿼리 작성
		String sql = "";
		sql += "SELECT * FROM chatprofile";
		sql += " WHERE";
		sql += " chatprofileno = ?";

		try {
			//DB작업
			ps = conn.prepareStatement(sql);
			ps.setInt(1, chatProfile.getChatProfileNo());

			rs = ps.executeQuery();

			while( rs.next() ) {
				chatProfile.setChatProfileNo(rs.getInt("chatprofileno"));
				chatProfile.setMessengerNo(rs.getInt("messengerno"));
				chatProfile.setClientID(rs.getString("clientid"));
				chatProfile.setStatus(rs.getInt("status"));
				chatProfile.setCounselorID(rs.getString("counselorid"));
				chatProfile.setIsFixedMatch(rs.getInt("isfixedmatch"));
				chatProfile.setLastChatDate(rs.getString("lastchatdate"));
				chatProfile.setTopFixedNum(rs.getInt("topfixednum"));
				chatProfile.setClientInfoNo(rs.getInt("clientinfono"));

			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				//DB 객체 닫기
				if(rs!=null)	rs.close();
				if(ps!=null)	ps.close();

			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return chatProfile;
	}

	@Override
	public int insertSemiChat(Chat chat) {
		conn = DBConn.getConnection();

		//쿼리작성
		String sql = "";
		sql += "INSERT INTO chat";
		sql += " ( chatno, chatprofileno, fromid,";
		sql += " toid, chatcontent, messagetype,";
		sql += " chattime, chatread )";
		sql += " VALUES( CHAT_seq.nextval, ?, ?, ?, ?, 0, TO_CHAR(SYSDATE, 'YYYY.MM.DD HH24:MI:SS'), 0)";

		try {
			//DB작업
			ps = conn.prepareStatement(sql);
			ps.setInt(1, chat.getChatProfileNo());
			ps.setString(2, "semichat");
			ps.setString(3, chat.getToID());
			ps.setString(4, chat.getChatContent());
			ps.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				//DB객체 닫기
				if(ps!=null)	ps.close();

			} catch (SQLException e) {
				e.printStackTrace();
				return -1;
			}
		}

		return 1;
	}
	
	@Override
	public int insertfacebookDB(Chat chat, int chatProfileNo) {
		conn = facebookDB.getConnection();

		//쿼리작성
		String sql = "";
		sql += "INSERT INTO chat";
		sql += " ( chatno, chatprofileno, fromid,";
		sql += " toid, chatcontent, messagetype,";
		sql += " chattime, chatread )";
		sql += " VALUES( CHAT_seq.nextval, ?, ?, ?, ?, 0, TO_CHAR(SYSDATE, 'YYYY.MM.DD HH24:MI:SS'), 0)";

		try {
			//DB작업
			ps2 = conn.prepareStatement(sql);
			ps2.setInt(1, chatProfileNo);
			ps2.setString(2, "semichat");
			ps2.setString(3, chat.getToID());
			ps2.setString(4, chat.getChatContent());
			ps2.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				//DB객체 닫기
				if(ps2!=null)	ps2.close();

			} catch (SQLException e) {
				e.printStackTrace();
				return -1;
			}
		}

		return 1;
	}

	@Override
	public int insertkakaoDB(Chat chat, int chatProfileNo) {
		conn = kakaoDB.getConnection();

		//쿼리작성
		String sql = "";
		sql += "INSERT INTO chat";
		sql += " ( chatno, chatprofileno, fromid,";
		sql += " toid, chatcontent, messagetype,";
		sql += " chattime, chatread )";
		sql += " VALUES( CHAT_seq.nextval, ?, ?, ?, ?, 0, TO_CHAR(SYSDATE, 'YYYY.MM.DD HH24:MI:SS'), 0)";

		try {
			//DB작업
			ps2 = conn.prepareStatement(sql);
			ps2.setInt(1, chatProfileNo);
			ps2.setString(2, "semichat");
			ps2.setString(3, chat.getToID());
			ps2.setString(4, chat.getChatContent());
			ps2.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				//DB객체 닫기
				if(ps2!=null)	ps2.close();

			} catch (SQLException e) {
				e.printStackTrace();
				return -1;
			}
		}

		return 1;
	}
	
	@Override
	public int insertlineDB(Chat chat, int chatProfileNo) {
		conn = lineDB.getConnection();

		//쿼리작성
		String sql = "";
		sql += "INSERT INTO chat";
		sql += " ( chatno, chatprofileno, fromid,";
		sql += " toid, chatcontent, messagetype,";
		sql += " chattime, chatread )";
		sql += " VALUES( CHAT_seq.nextval, ?, ?, ?, ?, 0, TO_CHAR(SYSDATE, 'YYYY.MM.DD HH24:MI:SS'), 0)";

		try {
			//DB작업
			ps2 = conn.prepareStatement(sql);
			ps2.setInt(1, chatProfileNo);
			ps2.setString(2, "semichat");
			ps2.setString(3, chat.getToID());
			ps2.setString(4, chat.getChatContent());
			ps2.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				//DB객체 닫기
				if(ps2!=null)	ps2.close();

			} catch (SQLException e) {
				e.printStackTrace();
				return -1;
			}
		}

		return 1;

	}
	

	@Override
	public int insertinstagramDB(Chat chat, int chatProfileNo) {
		conn = instagramDB.getConnection();

		//쿼리작성
		String sql = "";
		sql += "INSERT INTO chat";
		sql += " ( chatno, chatprofileno, fromid,";
		sql += " toid, chatcontent, messagetype,";
		sql += " chattime, chatread )";
		sql += " VALUES( CHAT_seq.nextval, ?, ?, ?, ?, 0, TO_CHAR(SYSDATE, 'YYYY.MM.DD HH24:MI:SS'), 0)";

		try {
			//DB작업
			ps2 = conn.prepareStatement(sql);
			ps2.setInt(1, chatProfileNo);
			ps2.setString(2, "semichat");
			ps2.setString(3, chat.getToID());
			ps2.setString(4, chat.getChatContent());
			ps2.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				//DB객체 닫기
				if(ps2!=null)	ps2.close();

			} catch (SQLException e) {
				e.printStackTrace();
				return -1;
			}
		}

		return 1;
	}
	
	@Override
	public int wechatDB(Chat chat, int chatProfileNo) {
		conn = wechatDB.getConnection();

		//쿼리작성
		String sql = "";
		sql += "INSERT INTO chat";
		sql += " ( chatno, chatprofileno, fromid,";
		sql += " toid, chatcontent, messagetype,";
		sql += " chattime, chatread )";
		sql += " VALUES( CHAT_seq.nextval, ?, ?, ?, ?, 0, TO_CHAR(SYSDATE, 'YYYY.MM.DD HH24:MI:SS'), 0)";

		try {
			//DB작업
			ps2 = conn.prepareStatement(sql);
			ps2.setInt(1, chatProfileNo);
			ps2.setString(2, "semichat");
			ps2.setString(3, chat.getToID());
			ps2.setString(4, chat.getChatContent());
			ps2.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				//DB객체 닫기
				if(ps2!=null)	ps2.close();

			} catch (SQLException e) {
				e.printStackTrace();
				return -1;
			}
		}

		return 1;
	}


	
	@Override
	public void updateLastChatDateToSemiChat(ChatProfile chatProfile) {

		conn = DBConn.getConnection();	//DB 연결
		String sql = "";
		sql += "UPDATE chatprofile set lastchatdate = TO_CHAR(SYSDATE, 'YYYY.MM.DD HH24:MI:SS') where chatprofileno = ?";

		try {
			ps = conn.prepareStatement(sql);	//수행 객체 얻기
			ps.setInt(1, chatProfile.getChatProfileNo());	//SQL쿼리의 ? 채우기
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
	public void updateLastChatDateToKakao(ChatProfile chatProfile) {
		conn = kakaoDB.getConnection();
		String sql = "";
		sql += "UPDATE chatprofile set lastchatdate = TO_CHAR(SYSDATE, 'YYYY.MM.DD HH24:MI:SS') where chatprofileno = ?";

		try {
			ps2 = conn.prepareStatement(sql);	//수행 객체 얻기
			ps2.setInt(1, chatProfile.getChatProfileNo());	//SQL쿼리의 ? 채우기
			rs2 = ps2.executeQuery();				//SQL 수행 결과 얻기

		}catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			try {
				if(ps2!=null)	ps2.close();
			}catch (Exception e) {
				// TODO: handle exception
			}
		}	
	}

	@Override
	public void updateLastChatDateToLine(ChatProfile chatProfile) {
		conn =  lineDB.getConnection();
		String sql = "";
		sql += "UPDATE chatprofile set lastchatdate = TO_CHAR(SYSDATE, 'YYYY.MM.DD HH24:MI:SS') where chatprofileno = ?";

		try {
			ps2 = conn.prepareStatement(sql);	//수행 객체 얻기
			ps2.setInt(1, chatProfile.getChatProfileNo());	//SQL쿼리의 ? 채우기
			rs2 = ps2.executeQuery();				//SQL 수행 결과 얻기

		}catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			try {
				if(ps2!=null)	ps2.close();
			}catch (Exception e) {
				// TODO: handle exception
			}
		}	
	}

	@Override
	public void updateLastChatDateToInstagram(ChatProfile chatProfile) {
		conn = instagramDB.getConnection();
		String sql = "";
		sql += "UPDATE chatprofile set lastchatdate = TO_CHAR(SYSDATE, 'YYYY.MM.DD HH24:MI:SS') where chatprofileno = ?";

		try {
			ps2 = conn.prepareStatement(sql);	//수행 객체 얻기
			ps2.setInt(1, chatProfile.getChatProfileNo());	//SQL쿼리의 ? 채우기
			rs2 = ps2.executeQuery();				//SQL 수행 결과 얻기

		}catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			try {
				if(ps2!=null)	ps2.close();
			}catch (Exception e) {
				// TODO: handle exception
			}
		}	
	}

	@Override
	public void updateLastChatDateToFacebook(ChatProfile chatProfile) {
		conn = facebookDB.getConnection();
		String sql = "";
		sql += "UPDATE chatprofile set lastchatdate = TO_CHAR(SYSDATE, 'YYYY.MM.DD HH24:MI:SS') where chatprofileno = ?";

		try {
			ps2 = conn.prepareStatement(sql);	//수행 객체 얻기
			ps2.setInt(1, chatProfile.getChatProfileNo());	//SQL쿼리의 ? 채우기
			rs2 = ps2.executeQuery();				//SQL 수행 결과 얻기

		}catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			try {
				if(ps2!=null)	ps2.close();
			}catch (Exception e) {
				// TODO: handle exception
			}
		}	
	}
	
	@Override
	public void updateLastChatDateToWechat(ChatProfile chatProfile) {
		conn = wechatDB.getConnection();
		String sql = "";
		sql += "UPDATE chatprofile set lastchatdate = TO_CHAR(SYSDATE, 'YYYY.MM.DD HH24:MI:SS') where chatprofileno = ?";

		try {
			ps2 = conn.prepareStatement(sql);	//수행 객체 얻기
			ps2.setInt(1, chatProfile.getChatProfileNo());	//SQL쿼리의 ? 채우기
			rs2 = ps2.executeQuery();				//SQL 수행 결과 얻기

		}catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			try {
				if(ps2!=null)	ps2.close();
			}catch (Exception e) {
				// TODO: handle exception
			}
		}	
	}
	
	@Override
	public void updateChatRead(ChatProfile chatProfile) {
		conn = DBConn.getConnection();	//DB 연결
		String sql = "";
		sql += "UPDATE chat set chatread = 1 where chatprofileno = ? AND toID = ?";

		try {
			ps = conn.prepareStatement(sql);	//수행 객체 얻기
			ps.setInt(1, chatProfile.getChatProfileNo());	//SQL쿼리의 ? 채우기
			ps.setString(2, "semichat");	//SQL쿼리의 ? 채우기
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
	public ArrayList<Chat> selectChatListByRecent(String fromID, String toID, int number) {
		ArrayList<Chat> chatList = null;
		String SQL = "SELECT * FROM CHAT WHERE ((fromID=? AND toID=?) OR (fromID=? AND toID=?)) AND "
				+ "chatNo>(SELECT MAX(chatNo)-? FROM CHAT WHERE (fromID=? AND  toID=?) OR (fromID=? AND toID=?)) ORDER BY chatTIME";
		try {
			conn = DBConn.getConnection();
			ps = conn.prepareStatement(SQL);
			ps.setString(1, fromID);
			ps.setString(2, toID);
			ps.setString(3, toID);
			ps.setString(4, fromID);
			ps.setInt(5, number);
			ps.setString(6, fromID);
			ps.setString(7, toID);
			ps.setString(8, toID);
			ps.setString(9, fromID);
			rs = ps.executeQuery();
			chatList = new ArrayList<Chat>();
			while(rs.next()) {
				Chat chat = new Chat();
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
				chat.setChatTIME(rs.getString("chatTime").substring(0, 11) + " " + timeType + " " + chatTime + ":" + rs.getString("chatTime").substring(14, 16) + "");
				chatList.add(chat);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if(rs!=null) rs.close();
				if(ps!=null) ps.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return chatList;
	}

	@Override
	public ArrayList<Chat> selectChatListByID(int chatProfileNo) {
		ArrayList<Chat> chatList = null;
		
		String SQL = "SELECT * FROM CHAT WHERE chatProfileNo = ? ORDER BY chatTIME";
		
		try {
			conn = DBConn.getConnection();
			ps = conn.prepareStatement(SQL);
			ps.setInt(1, chatProfileNo);
			
			rs = ps.executeQuery();
			chatList = new ArrayList<Chat>();
			while(rs.next()) {
				Chat chat = new Chat();
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
				chat.setChatTIME(rs.getString("chatTime").substring(0, 11) + " " + timeType + " " + chatTime + ":" + rs.getString("chatTime").substring(14, 16) + "");
				chatList.add(chat);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if(rs!=null) rs.close();
				if(ps!=null) ps.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		System.out.println(chatList);
		return chatList;
	}

	@Override
	public int selectFaceBookChatProfileNo(String ClientId) {
		conn = facebookDB.getConnection();

		//쿼리 작성
		String sql = "";
		sql += "SELECT chatprofileno FROM chatprofile";
		sql += " WHERE";
		sql += " clientid = ?";
		
		int chatProfileNo = 0;

		try {
			//DB작업
			ps = conn.prepareStatement(sql);
			ps.setString(1, ClientId);

			rs = ps.executeQuery();

			while(rs.next()) {
			
				chatProfileNo = rs.getInt("chatprofileno");
			}


		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				//DB 객체 닫기
				if(rs!=null)	rs.close();
				if(ps!=null)	ps.close();

			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		System.out.println("클라 아이디 : " + ClientId);
		System.out.println("클라이언트 번호 뭔데 ? " + chatProfileNo);
		return chatProfileNo;
	}

	@Override
	public int selectInstagramChatProfileNo(String ClientId) {
		conn = instagramDB.getConnection();

		//쿼리 작성
		String sql = "";
		sql += "SELECT chatprofileno FROM chatprofile";
		sql += " WHERE";
		sql += " clientid = ?";
		
		int chatProfileNo = 0;

		try {
			//DB작업
			ps = conn.prepareStatement(sql);
			ps.setString(1, ClientId);

			rs = ps.executeQuery();

			while(rs.next()) {
				
				chatProfileNo = rs.getInt("chatprofileno");
			}


		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				//DB 객체 닫기
				if(rs!=null)	rs.close();
				if(ps!=null)	ps.close();

			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return chatProfileNo;
	}

	@Override
	public int selectKakaoChatProfileNo(String ClientId) {
		conn = kakaoDB.getConnection();

		//쿼리 작성
		String sql = "";
		sql += "SELECT chatprofileno FROM chatprofile";
		sql += " WHERE";
		sql += " clientid = ?";
		
		int chatProfileNo = 0;

		try {
			//DB작업
			ps = conn.prepareStatement(sql);
			ps.setString(1, ClientId);

			rs = ps.executeQuery();

			while(rs.next()) {
				
				chatProfileNo = rs.getInt("chatprofileno");
			}


		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				//DB 객체 닫기
				if(rs!=null)	rs.close();
				if(ps!=null)	ps.close();

			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return chatProfileNo;
	}

	@Override
	public int selectLineChatProfileNo(String ClientId) {
		conn = lineDB.getConnection();

		//쿼리 작성
		String sql = "";
		sql += "SELECT chatprofileno FROM chatprofile";
		sql += " WHERE";
		sql += " clientid = ?";
		
		int chatProfileNo = 0;

		try {
			//DB작업
			ps = conn.prepareStatement(sql);
			ps.setString(1, ClientId);

			rs = ps.executeQuery();

			while(rs.next()) {
				
				chatProfileNo = rs.getInt("chatprofileno");
			}


		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				//DB 객체 닫기
				if(rs!=null)	rs.close();
				if(ps!=null)	ps.close();

			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return chatProfileNo;
	}

	@Override
	public int selectWeChatProfileNo(String ClientId) {
		
		conn = wechatDB.getConnection();

		//쿼리 작성
		String sql = "";
		sql += "SELECT chatprofileno FROM chatprofile";
		sql += " WHERE";
		sql += " clientid = ?";
		
		int chatProfileNo = 0;

		try {
			//DB작업
			ps = conn.prepareStatement(sql);
			ps.setString(1, ClientId);

			rs = ps.executeQuery();
			
		
			while(rs.next()) {
				
				chatProfileNo = rs.getInt("chatprofileno");
			}


		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				//DB 객체 닫기
				if(rs!=null)	rs.close();
				if(ps!=null)	ps.close();

			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return chatProfileNo;
	}
}
