package dao.impl.chat;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import dao.face.chat.MyClientProfileDao;
import dbutil.DBConn;
import dto.ClientInfo;
import dto.FrequentReply;
import dto.MyClientInfo;

public class MyClientProfileDaoImpl implements MyClientProfileDao {

	private Connection conn = null; // DB 연결 객체
	private PreparedStatement ps = null; // SQL 수행 객체
	private ResultSet rs = null;	// SQL 수행 결과 객체

	/* 유진 : client 정보 update */
	@Override
	public void updateClientInfo(ClientInfo clientInfo) {
		conn=DBConn.getConnection();
		
		String sql="UPDATE clientInfo SET clientName=?, clientPhoneNum=?, lastChatDate=?, counselorName=?, chatMemo=?"
				+ "WHERE clientInfoNo=?";
		
		try {
			ps=conn.prepareStatement(sql);
			ps.setString(1, clientInfo.getClientName());
			ps.setString(2, clientInfo.getClientPhoneNum());
			ps.setString(3, clientInfo.getLastChatDate());
			ps.setString(4, clientInfo.getCounselorName());
			ps.setString(5, clientInfo.getChatMemo());	
			ps.setInt(6, clientInfo.getClientInfoNo());	
			ps.executeUpdate();
			System.out.println(clientInfo);
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

	/* 유진 : clientInfoNo과 counselorNo를 이용해서 myClientInfo 테이블 조회 */
	@Override
	public MyClientInfo getMyClientInfoNo(MyClientInfo myClientInfo) {
		conn=DBConn.getConnection();
		
		String sql="SELECT myClientInfoNo FROM MYCLIENTINFO WHERE clientInfoNo=? AND counselorNo=?";

		
		try {
			ps=conn.prepareStatement(sql);
			ps.setInt(1, myClientInfo.getClientInfoNo());
			ps.setInt(2, myClientInfo.getCounselorNo());
			System.out.println("dao "+myClientInfo.getClientInfoNo());
			rs=ps.executeQuery();
			
			while(rs.next()) {
				myClientInfo.setMyClientInfoNo(rs.getInt("myClientInfoNo"));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				if(rs!= null) rs.close();		
				if(ps!= null) ps.close();		
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return myClientInfo;
	}

	/* 유진 : clientNick update*/
	@Override
	public void updateClientAlias(MyClientInfo myClientInfo) {
		conn = DBConn.getConnection();

		String sql = "UPDATE MYCLIENTINFO SET clientNick=? WHERE clientInfoNo=?";

		try {
			ps = conn.prepareStatement(sql);
			ps.setString(1, myClientInfo.getClientNick());
			ps.setInt(2, myClientInfo.getClientInfoNo());
			ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (ps != null)
					ps.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	/* 유진 : clientNick이 처음 등록되는 고객 insert*/
	@Override
	public void insertClientAlias(MyClientInfo myClientInfo) {
		conn = DBConn.getConnection();
		
		String sql="INSERT INTO MYCLIENTINFO VALUES (myclientinfono_seq.nextval,?,?,0,0,?,?)";
		
		try {
			ps=conn.prepareStatement(sql);
			ps.setInt(1, myClientInfo.getCounselorNo());	// session
			ps.setString(2, myClientInfo.getCounselorId());	
			ps.setString(3, myClientInfo.getClientNick());	
			ps.setInt(4, myClientInfo.getClientInfoNo());	// request
			ps.executeUpdate();
//			System.out.println(myClientInfo);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			try {
				if (ps != null)
					ps.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	/* 유진 : myClientInfoNo의 couselorId를 가지고 counselor 테이블 조회*/
	@Override
	public MyClientInfo getCounselorNo(MyClientInfo myClientInfo) {
		conn = DBConn.getConnection();
		
		String sql="SELECT counselorNo,counselorId FROM COUNSELOR WHERE counselorId=?";
//		int counselorno=0;
		MyClientInfo counselorInfo = new MyClientInfo();
		try {
			ps=conn.prepareStatement(sql);
			ps.setString(1, myClientInfo.getCounselorId());
//			System.out.println(myClientInfo.getCounselorId());
			rs=ps.executeQuery();
			
			while(rs.next()) {
//				System.out.println(rs.getInt("counselorNo"));
				counselorInfo.setCounselorNo(rs.getInt("counselorNo"));
				counselorInfo.setCounselorId(rs.getString("counselorId"));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				if(rs!= null) rs.close();		
				if(ps!= null) ps.close();		
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		return counselorInfo;
	}

	/* 유진 : 고정매칭 상태 변경 */
	@Override
	public MyClientInfo updateMatchingStatus(MyClientInfo myClientInfo, int fixedStatus) {
		conn=DBConn.getConnection();
		
		String sql="UPDATE MYCLIENTINFO SET isFixedMatch=? WHERE clientInfoNo=?";
		
		try {
			ps=conn.prepareStatement(sql);
			if(fixedStatus==0) {
				ps.setInt(1, 1);	// fixedStatus가 0이면 isFixedMatch의 값을 1로 세팅...		
				myClientInfo.setIsFixedMatch(1);
			} else {				
				ps.setInt(1, 0);	// fixedStatus가 1이면 isFixedMatch의 값을 0로 세팅...
				myClientInfo.setIsFixedMatch(0);
			}
			ps.setInt(2, myClientInfo.getClientInfoNo());
			ps.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			try {	
				if(ps!= null) ps.close();		
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return myClientInfo;
	}

	/* 유진 : 현재 고정매칭 상태 알기 */
	@Override
	public int getFixedStatus(MyClientInfo myClientInfo) {
		conn=DBConn.getConnection();
		
		String sql = "SELECT isFixedMatch FROM MYCLIENTINFO WHERE clientInfoNo=?";
		int status=0;
		try {
			ps=conn.prepareStatement(sql);
			ps.setInt(1, myClientInfo.getClientInfoNo());
			rs=ps.executeQuery();
			while(rs.next()) {
				status=rs.getInt("isFixedMatch");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}  finally {
			try {
				if(rs!= null) rs.close();		
				if(ps!= null) ps.close();		
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return status;
	}

	/* 유진 : 현재 차단 상태 알기 */
	@Override
	public int getBlackStatus(MyClientInfo myClientInfo) {
		conn=DBConn.getConnection();
		
		String sql = "SELECT isBlock FROM MYCLIENTINFO WHERE clientInfoNo=?";
		int status=0;
		try {
			ps=conn.prepareStatement(sql);
			ps.setInt(1, myClientInfo.getClientInfoNo());
			rs=ps.executeQuery();
			while(rs.next()) {
				status=rs.getInt("isBlock");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}  finally {
			try {
				if(rs!= null) rs.close();		
				if(ps!= null) ps.close();		
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return status;
	}
	
	/* 유진 : 차단 상태 변경하기 */
	@Override
	public MyClientInfo updateIsBlock(MyClientInfo myClientInfo, int blackStatus) {
		conn=DBConn.getConnection();
		
		String sql="UPDATE MYCLIENTINFO SET isBlock=? WHERE clientInfoNo=?";
		
		try {
			ps=conn.prepareStatement(sql);
			if(blackStatus==0) {
				ps.setInt(1, 1);	// blackStatus가 0이면 isBlock의 값을 1로 세팅...	
				myClientInfo.setIsBlock(1);
			} else {				
				ps.setInt(1, 0);	// blackStatus가 1이면 isBlock의 값을 0로 세팅...
				myClientInfo.setIsBlock(0);
			}
			ps.setInt(2, myClientInfo.getClientInfoNo());
			ps.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			try {	
				if(ps!= null) ps.close();		
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return myClientInfo;
	}

	/* 유진 : myClientInfo 알기*/
	@Override
	public MyClientInfo getMyInfo(int clientInfoNo) {
		conn=DBConn.getConnection();
		
		String sql="SELECT * FROM MYCLIENTINFO WHERE clientInfoNo=?";
		MyClientInfo myClient = new MyClientInfo();
		
		try {
			ps=conn.prepareStatement(sql);
			ps.setInt(1, clientInfoNo);
			rs=ps.executeQuery();
			
			while(rs.next()) {
				myClient.setMyClientInfoNo(rs.getInt("myClientInfoNo"));
				myClient.setCounselorNo(rs.getInt("counselorNo"));
				myClient.setCounselorId(rs.getString("counselorId"));
				myClient.setIsBlock(rs.getInt("isBlock"));
				myClient.setIsFixedMatch(rs.getInt("isFixedMatch"));
				myClient.setClientNick(rs.getString("clientNick"));
				myClient.setClientInfoNo(rs.getInt("clientInfoNo"));				
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				if (rs != null) rs.close();
				if (ps != null) ps.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return myClient;
	}

	/* 유진 : clientInfo 알기 */
	@Override
	public ClientInfo getInfo(int clientInfoNo) {
		conn=DBConn.getConnection();
		
		String sql="SELECT * FROM CLIENTINFO WHERE clientInfoNo=?";
		ClientInfo client = new ClientInfo();
		
		try {
			ps=conn.prepareStatement(sql);
			ps.setInt(1, clientInfoNo);
			rs=ps.executeQuery();
//			System.out.println(clientInfoNo);
			while(rs.next()) {
				client.setClientInfoNo(clientInfoNo);
				client.setClientName(rs.getString("clientName"));
//				System.out.println(rs.getString("clientName"));
				client.setClientPhoneNum(rs.getString("clientPhoneNum"));
				client.setLastChatDate(rs.getString("lastChatDate"));
				client.setCounselorName(rs.getString("counselorName"));
				client.setChatMemo(rs.getString("chatMemo"));
//				System.out.println("dao " + client);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				if (rs != null) rs.close();
				if (ps != null) ps.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return client;
	}

	/* 유진 : 자주 쓰는 답변 리스트 */
	@Override
	public List<FrequentReply> selectReply() {

		conn = DBConn.getConnection();
		
		String sql="SELECT * FROM FREQUENTREPLY WHERE frequentReplyInDesk=1";
		List list=new ArrayList();
		
		try {
			ps=conn.prepareStatement(sql);
			rs=ps.executeQuery();
			
			while(rs.next()) {
				FrequentReply frequentReply = new FrequentReply();
				frequentReply.setFrequentReplyNo(rs.getInt("frequentReplyNo"));
				frequentReply.setFrequentReplyContent(rs.getString("frequentReplyContent"));
				list.add(frequentReply);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			try {
				if (rs != null) rs.close();
				if (ps != null) ps.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return list;
	}
	

}
