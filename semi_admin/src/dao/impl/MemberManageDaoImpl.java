package dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import dao.face.MemberManageDao;
import dbutil.DBConn;
import dto.Corporation;
import dto.Counselor;
import util.Paging;

public class MemberManageDaoImpl implements MemberManageDao {

	private Connection conn = null; //DB연결 객체
	private PreparedStatement ps = null; //SQL 수행 객체
	private ResultSet rs = null; //SQL 수행 결과 객체
	
	@Override
	public int selectCorpCntAll(Map<String, String> map) {
		conn = DBConn.getConnection(); 
		//count
		int cnt = 0;
		
		String sql = "SELECT COUNT(*) FROM CORPORATION WHERE CORPORATIONNO != 0";
		
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
	public List selectCorpAll(Paging paging) {
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
			sql_tmp += " AND corporation."+searchType+ " like '%"+search+"%'";
		}
		
		String sql = "SELECT * FROM(SELECT rownum rnum, B.* FROM (SELECT * FROM corporation WHERE CORPORATIONNO != 0";
		sql += sql_tmp;
		sql += "ORDER BY corporationNo)B ORDER BY rnum) coporation WHERE rnum BETWEEN ? AND ?";

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
				Corporation corp = new Corporation(); //각 행을 처리할 DTO
				
				corp.setCorporationNo(rs.getInt("corporationNo"));
				corp.setCorporationName(rs.getString("corporationName"));
				corp.setCeo(rs.getString("ceo"));
				corp.setRegisterDate(rs.getString("registerDate"));
				corp.setPhoneNum(rs.getString("phoneNum"));
				corp.setCounselorCnt(rs.getInt("counselorCnt"));
				corp.setSales(rs.getInt("sales"));
				
				list.add(corp);
				
//				System.out.println(list);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}

	@Override
	public List selectCounselorAll(Paging paging) {
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
			sql_tmp += " AND counselor."+searchType+ " like '%"+search+"%'";
		}
		
		String sql = "SELECT * FROM(SELECT rownum rnum, B.* FROM (SELECT * FROM counselor WHERE corporationNo=?";
		sql += sql_tmp;
		sql += "ORDER BY counselorNo)B ORDER BY rnum) counselor WHERE rnum BETWEEN ? AND ?";

		//수행결과를 담을 리스트
		
		List list = new ArrayList();
		
		try {
			ps = conn.prepareStatement(sql);//수행객체얻기			
			ps.setInt(1, paging.getCorporationNo());
			ps.setInt(2, paging.getStartNo());
			ps.setInt(3, paging.getEndNo());
			
			rs = ps.executeQuery();//SQL수행결과얻기
			
//			System.out.println(paging.getStartNo());
			System.out.println(sql);
			
			//SQL 수행결과 처리
			while(rs.next()) {
				Counselor counselor = new Counselor(); //각 행을 처리할 DTO
				
				counselor.setCorporationNo(rs.getInt("corporationNo"));
				counselor.setCounselorNo(rs.getInt("counselorNo"));
				counselor.setCounselorName(rs.getString("counselorName"));
				counselor.setCounselorId(rs.getString("counselorId"));
				counselor.setCounselorEmail(rs.getString("counselorEmail"));
				counselor.setCounselorPhonenumber(rs.getString("counselorPhonenumber"));
				counselor.setCounselorPosition(rs.getString("counselorPosition"));
				
				list.add(counselor);
				
//				System.out.println(list);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}

	@Override
	public int selectCounselorCntAll(Map<String, String> map, int corporationNo) {
		conn = DBConn.getConnection(); 
		//count
		int cnt = 0;
		
		String sql = "SELECT COUNT(*) FROM COUNSELOR WHERE corporationNo=?";
		
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
			ps.setInt(1, corporationNo);
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

}
