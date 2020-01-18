package dao.impl.chat;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import dao.face.chat.ChatProfileDao;
import dbutil.DBConn;
import dto.ChatProfile;

public class ChatProfileDaoImpl implements ChatProfileDao {

	private Connection conn = null; // DB 연결 객체
	private PreparedStatement ps = null; // SQL 수행 객체
	private ResultSet rs = null;	// SQL 수행 결과 객체
	
	@Override
	public List<ChatProfile> selectChatProfile() {

		List<ChatProfile> list = new ArrayList<ChatProfile>();
		
		conn=DBConn.getConnection();
		
		String sql="";
		
		sql += "SELECT";
		sql += " a.*";
		sql += " ,b.lastchattime";
		sql += " ,(select chatcontent from chat where chattime = b.lastchattime) lastchatcontent";
		sql += " ,(select messagetype from chat where chattime = b.lastchattime) lastmessagetype";
		sql += " ,b.unreadchatcount";
		
		sql += " FROM CHATPROFILE a";
		sql += " ,(";
		sql += "	select chatprofileno, max(chattime) lastchattime, count(case when chatread = 0 AND toID = 'semichat' then 1 end) unReadChatCount";
		sql += "	from chat";
		sql += "	WHERE 1=1";
		sql += "	AND fromID != 'semichat'";
		sql += "	group by chatprofileno";
		sql += " ) b";
		
		sql += " WHERE 1=1";
		sql += " AND a.chatprofileno = b.chatprofileno";
		sql += " AND a.STATUS = 0";
		sql += " AND a.messengerno = 1";
		sql += " ORDER BY lastchattime desc";
		
		try {
			ps=conn.prepareStatement(sql);
			rs=ps.executeQuery();
			
			while(rs.next()) {
				
				ChatProfile chatProfile = new ChatProfile();
				
				chatProfile.setChatProfileNo(rs.getInt("chatprofileno"));
				chatProfile.setMessengerNo(rs.getInt("messengerno"));
				chatProfile.setClientID(rs.getString("clientid"));
				chatProfile.setStatus(rs.getInt("status"));
				chatProfile.setCounselorID(rs.getString("counselorid"));
				chatProfile.setIsFixedMatch(rs.getInt("isfixedmatch"));
				chatProfile.setLastChatDate(rs.getString("lastchatdate"));
				chatProfile.setTopFixedNum(rs.getInt("topfixednum"));
				chatProfile.setClientInfoNo(rs.getInt("clientInfono"));
				
				chatProfile.setLastChatTime(rs.getString("lastChatTime"));
				chatProfile.setLastChatContent(rs.getString("lastChatContent"));
				chatProfile.setLasetMessageType(rs.getInt("lastMessageType"));
				
				chatProfile.setUnReadChatCount(rs.getInt("unReadChatCount"));
				
				list.add(chatProfile);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if(rs!= null) rs.close();		
				if(ps!= null) ps.close();		
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		return list;
	}

	@Override
	public List<ChatProfile> selectChatProfile(Map<String, String> map) {
		
		List<ChatProfile> list = new ArrayList<ChatProfile>();
		
		conn=DBConn.getConnection();
		
		String sql="";
		
		sql += "SELECT";
		sql += " a.*";
		sql += " ,b.lastchattime";
		sql += " ,(select chatcontent from chat where chattime = b.lastchattime) lastchatcontent";
		sql += " ,(select messagetype from chat where chattime = b.lastchattime) lastmessagetype";
		sql += " ,b.unreadchatcount";
		
		sql += " FROM CHATPROFILE a";
		sql += " ,(";
		sql += "	select chatprofileno, max(chattime) lastchattime, count(case when chatread = 0 AND toID = 'semichat' then 1 end) unReadChatCount";
		sql += "	from chat";
		sql += "	WHERE 1=1";
		sql += "	AND fromID != 'semichat'";
		sql += "	group by chatprofileno";
		sql += " ) b";
		
		sql += " WHERE 1=1";
		sql += " AND a.chatprofileno = b.chatprofileno";
		
		//검색조건 1 - 검색어(clientID, counselorID, content)
		if(map.get("search_str") != null && !"".equals(map.get("search_str"))) {
			sql += " AND (a.clientID like '%"+map.get("search_str")+"%'";
			sql += " OR a.counselorID like '%"+map.get("search_str")+"%')";
		}	
		//검색조건 2 - 메신저(카카오톡, 라인...)
		if(map.get("checkedMessengerNoArray") != null && !"".equals(map.get("checkedMessengerNoArray")))
			sql += " AND a.messengerno in ("+map.get("checkedMessengerNoArray")+")";
		else sql += " AND a.messengerno = 9999";
		
		//검색조건 3 - 상태(New, 진행중 ...)
		if(map.get("status") != null && !"".equals(map.get("status")))
			sql += " AND a.status = "+map.get("status");
		
		sql += " ORDER BY lastchattime desc";
		System.out.println("채팅프로필 검색조건 쿼리 : "+sql);
		try {
			ps=conn.prepareStatement(sql);
			rs=ps.executeQuery();
			
			while(rs.next()) {
				
				ChatProfile chatProfile = new ChatProfile();
				
				chatProfile.setChatProfileNo(rs.getInt("chatprofileno"));
				chatProfile.setMessengerNo(rs.getInt("messengerno"));
				chatProfile.setClientID(rs.getString("clientid"));
				chatProfile.setStatus(rs.getInt("status"));
				chatProfile.setCounselorID(rs.getString("counselorid"));
				chatProfile.setIsFixedMatch(rs.getInt("isfixedmatch"));
				chatProfile.setLastChatDate(rs.getString("lastchatdate"));
				chatProfile.setTopFixedNum(rs.getInt("topfixednum"));
				chatProfile.setClientInfoNo(rs.getInt("clientInfono"));
				
				chatProfile.setLastChatTime(rs.getString("lastChatTime"));
				chatProfile.setLastChatContent(rs.getString("lastChatContent"));
				chatProfile.setLasetMessageType(rs.getInt("lastMessageType"));
				
				chatProfile.setUnReadChatCount(rs.getInt("unReadChatCount"));
				
				list.add(chatProfile);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if(rs!= null) rs.close();		
				if(ps!= null) ps.close();		
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		return list;
	}

	@Override
	public void updateStatus(ChatProfile chatProfile, int status) {

		conn=DBConn.getConnection();
		
		String sql="";
		sql += "UPDATE CHATPROFILE";
		sql += " SET STATUS = ?";
		sql += " ,counselorid = ?";
		sql += " WHERE 1=1";
		sql += " AND chatProfileNo = ?";
		
		try {
			System.out.println(chatProfile.getCounselorID());
			ps=conn.prepareStatement(sql);
			ps.setInt(1, status);
			ps.setString(2, chatProfile.getCounselorID());
			ps.setInt(3, chatProfile.getChatProfileNo());
			ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if(ps!= null) ps.close();		
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	@Override
	public String selectBlockProfile(Map<String, String> map) {
		
		StringBuilder sb = new StringBuilder();
		String result = "";
		
		conn=DBConn.getConnection();
		String sql="";
		
		sql += "SELECT chatProfileno";
		sql += " FROM myclientinfo a";
		sql += " ,chatprofile b";
		sql += " WHERE 1=1";
		sql += " AND a.clientinfono = b.clientinfono";
		sql += " AND a.counselorid = ?";
		sql += " AND isblock = 1"; 
		
		try {
			ps=conn.prepareStatement(sql);
			ps.setString(1, map.get("counselorid"));
			rs=ps.executeQuery();
			
			while(rs.next()) {
				
				if(sb.length() > 0) {
					sb.append(',');
				}
				sb.append(rs.getInt("chatProfileno"));
				result = sb.toString();
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if(rs!= null) rs.close();		
				if(ps!= null) ps.close();		
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		return result;
	}

	@Override
	public List<ChatProfile> selectChatProfile(Map<String, String> map, String blockList) {
		List<ChatProfile> list = new ArrayList<ChatProfile>();
		
		conn=DBConn.getConnection();
		
		String sql="";
		
		sql += "SELECT";
		sql += " a.*";
		sql += " ,b.lastchattime";
		sql += " ,(select chatcontent from chat where chattime = b.lastchattime) lastchatcontent";
		sql += " ,(select messagetype from chat where chattime = b.lastchattime) lastmessagetype";
		sql += " ,b.unreadchatcount";
		
		sql += " FROM CHATPROFILE a";
		sql += " ,(";
		sql += "	select chatprofileno, max(chattime) lastchattime, count(case when chatread = 0 AND toID = 'semichat' then 1 end) unReadChatCount";
		sql += "	from chat";
		sql += "	WHERE 1=1";
		sql += "	AND fromID != 'semichat'";
		sql += "	group by chatprofileno";
		sql += " ) b";
		
		sql += " WHERE 1=1";
		sql += " AND a.chatprofileno = b.chatprofileno";
		
		//검색조건 1 - 검색어(clientID, counselorID, content)
		if(map.get("search_str") != null && !"".equals(map.get("search_str"))) {
			sql += " AND (a.clientID like '%"+map.get("search_str")+"%'";
			sql += " OR a.counselorID like '%"+map.get("search_str")+"%')";
		}	
		//검색조건 2 - 메신저(카카오톡, 라인...)
		if(map.get("checkedMessengerNoArray") != null && !"".equals(map.get("checkedMessengerNoArray")))
			sql += " AND a.messengerno in ("+map.get("checkedMessengerNoArray")+")";
		else sql += " AND a.messengerno = 9999";
		
		//검색조건 3 - 상태(New, 진행중 ...)
		if(map.get("status") != null && !"".equals(map.get("status")))
			sql += " AND a.status = "+map.get("status");
		
		sql += " AND a.chatProfileno not in ("+blockList+")";
		
		sql += " ORDER BY lastchattime desc";
		System.out.println("채팅프로필 검색조건 쿼리 : "+sql);
		try {
			ps=conn.prepareStatement(sql);
			rs=ps.executeQuery();
			
			while(rs.next()) {
				
				ChatProfile chatProfile = new ChatProfile();
				
				chatProfile.setChatProfileNo(rs.getInt("chatprofileno"));
				chatProfile.setMessengerNo(rs.getInt("messengerno"));
				chatProfile.setClientID(rs.getString("clientid"));
				chatProfile.setStatus(rs.getInt("status"));
				chatProfile.setCounselorID(rs.getString("counselorid"));
				chatProfile.setIsFixedMatch(rs.getInt("isfixedmatch"));
				chatProfile.setLastChatDate(rs.getString("lastchatdate"));
				chatProfile.setTopFixedNum(rs.getInt("topfixednum"));
				chatProfile.setClientInfoNo(rs.getInt("clientInfono"));
				
				chatProfile.setLastChatTime(rs.getString("lastChatTime"));
				chatProfile.setLastChatContent(rs.getString("lastChatContent"));
				chatProfile.setLasetMessageType(rs.getInt("lastMessageType"));
				
				chatProfile.setUnReadChatCount(rs.getInt("unReadChatCount"));
				
				list.add(chatProfile);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if(rs!= null) rs.close();		
				if(ps!= null) ps.close();		
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		return list;
	}
}
