package dao.impl.graph;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

import dao.face.graph.StatisticsDao;
import dbutil.DBConn;

public class StatisticsDaoImpl implements StatisticsDao {

	//DB관련 객체
	private Connection conn = null; 
	private PreparedStatement ps = null;
	private ResultSet rs = null;

	@Override
	public Map<String, String> getChatCount(String dateType) {

		Map<String, String> map = new LinkedHashMap<String, String>();
		conn = DBConn.getConnection();

		//날짜 길이
		int str_length = -1;
		
		if(dateType == "MONTH") str_length = 7; 
		else if(dateType == "DAY") str_length = 10;
		
		//쿼리 작성
		String sql = "";
		sql += "select substr(chattime,1,"+str_length+") chatdate";
		sql += " , nvl(count(1),0) chatcnt";
		sql += " FROM CHAT";
		sql += " where 1=1";
		sql += " AND to_date(chattime,'YYYY.MM.DD HH24:MI:SS') >= (SYSDATE - (INTERVAL '5' "+dateType+"))";
		sql += " group by substr(chattime,1,"+str_length+")";
		sql += " order by chatdate";
		
		System.out.println("날짜 통계 쿼리 : "+sql);
		
		try {
			ps = conn.prepareStatement(sql);
			rs = ps.executeQuery();

			while( rs.next() ) {
				map.put(rs.getString("chatdate"),rs.getString("chatcnt"));
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
		
		return map;
	}
	
	@Override
	public Map<String, String> getKeywordCount() {

		Map<String, String> map = new LinkedHashMap<String, String>();
		
		conn = DBConn.getConnection();

		//쿼리 작성
		String sql = "";
		sql += "SELECT A.* FROM (";
		sql += "	SELECT CHATCONTENT, COUNT(*) CHATRANK";
		sql += "	FROM CHAT";
		sql += "	GROUP BY CHATCONTENT";
		sql += "	ORDER BY CHATRANK DESC) A";
		sql += " WHERE 1=1";
		sql += " AND ROWNUM <= 5";
		
		System.out.println(sql);
		
		try {
			ps = conn.prepareStatement(sql);
			rs = ps.executeQuery();

			while( rs.next() ) {
				map.put(rs.getString("chatcontent"),rs.getString("chatrank"));
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
		
		return map;
	}

	
}
