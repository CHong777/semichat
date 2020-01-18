package service.impl.chat;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import dao.face.chat.MyClientProfileDao;
import dao.impl.chat.MyClientProfileDaoImpl;
import dto.ClientInfo;
import dto.FrequentReply;
import dto.MyClientInfo;
import service.face.chat.MyClientProfileService;

public class MyClientProfileServiceImpl implements MyClientProfileService{

	private MyClientProfileDao myClientProfileDao = new MyClientProfileDaoImpl();
	
	/* 
	 * 유진 : 고객 정보 form에 입력한 정보 req에서 얻어와서 set 
	 *	RegisterCustomerInfoController 
	 */
	@Override
	public ClientInfo getClientProfile(HttpServletRequest req) {
		ClientInfo clientInfo = new ClientInfo();	// 요청 파라미터 저장 객체
		String param=null;	// 요청 파라미터 처리용 변수
				
		// clientName
		param=req.getParameter("clientName");
		if(param!=null && !"".equals(param)) {
			clientInfo.setClientName(param);
		}
		
		// clientPhoneNum
		param=req.getParameter("clientPhoneNum");
		if(param!=null && !"".equals(param)) {
			clientInfo.setClientPhoneNum(param);
		}
		
		// lastChatDate
		param=req.getParameter("lastChatDate");
		if(param!=null && !"".equals(param)) {
			clientInfo.setLastChatDate(param);
		}
		
		// counselorName
		param=req.getParameter("counselorName");
		if(param!=null && !"".equals(param)) {
			clientInfo.setCounselorName(param);
		}
		
		// chatMemo
		param=req.getParameter("chatMemo");
		if(param!=null && !"".equals(param)) {
			clientInfo.setChatMemo(param);
		}
		return clientInfo;
	}

	/* 
	 * 유진 : form에 입력되어 있는 정보들 DB에 update 
	 * 	RegisterCustomerInfoController 
	 */
	@Override
	public void registerCustomerInfo(ClientInfo clientInfo) {
		// DB에 update
		myClientProfileDao.updateClientInfo(clientInfo);
	}

	/* 
	 * 유진 : form에 입력 된 닉네임, 해당 client를 myclient로 등록하는 counselorId
	 * 	RegisterCustomerAliasController, AddBlackListController, FixedMatchingChatController
	 */ 
	@Override
	public MyClientInfo getMyClientProfile(HttpServletRequest req) {
		MyClientInfo myClientInfo = new MyClientInfo();
		HttpSession session=req.getSession();
		String param=null;	// 요청 파라미터 처리용 변수
		
		// clientNick
		param=req.getParameter("clientNick");
		if(param!=null && !"".equals(param)) {
			myClientInfo.setClientNick(param);
		}
		
		//	counselorId : myClientProfile테이블에 넣어줄 counselorId=sessionId
		String counselorId=(String) session.getAttribute("counselorid");
		myClientInfo.setCounselorId(counselorId);
		
		return myClientInfo;
	}

	/*
	 * 유진 : myClient의 nickname 등록해주기
	 * 	RegisterCustomerAliasController
	 */
	@Override
	public void getMyClientProfile(MyClientInfo myClientInfo) {
//		myClientInfo안에 있는 counselorId를 이용해서 couselorNo select
		MyClientInfo counselorNo = myClientProfileDao.getCounselorNo(myClientInfo);
		myClientInfo.setCounselorNo(counselorNo.getCounselorNo());
		myClientInfo.setCounselorId(counselorNo.getCounselorId());
//		System.out.println("qq"+myClientInfo);
		
//		이미 등록되어 있는 손님인지 새로운 손님인지 (1:기존-update , 0:신규-insert)
		myClientInfo=myClientProfileDao.getMyClientInfoNo(myClientInfo);
//		System.out.println("서비스"+myClientInfo);
		if(myClientInfo.getMyClientInfoNo()>0) {
//			System.out.println("up");
			myClientProfileDao.updateClientAlias(myClientInfo);
		} else {
//			System.out.println("in");
			myClientProfileDao.insertClientAlias(myClientInfo);
		}
	}

	/*
	 * 유진 : myClient의 isFixedMatch 상태 바꿔주기
	 * 	FixedMatchingChatController
	 */
	@Override
	public MyClientInfo fixedMatchingChat(MyClientInfo myClientInfo) {
//		myClientInfo안에 있는 counselorId를 이용해서 couselorNo select
		MyClientInfo counselorNo = myClientProfileDao.getCounselorNo(myClientInfo);
		myClientInfo.setCounselorNo(counselorNo.getCounselorNo());
		myClientInfo.setCounselorId(counselorNo.getCounselorId());
		
		// 현재 상태를 받아온다 (고정 매칭 중 인지 아닌지)
		int fixedStatus = myClientProfileDao.getFixedStatus(myClientInfo);
//		System.out.println(fixedStatus);

		// 상태 update하기
		myClientInfo =myClientProfileDao.updateMatchingStatus(myClientInfo,fixedStatus);
		
		return myClientInfo;
	}

	/*
	 * 유진 : blackList에 추가하기
	 * 	AddBlackListController
	 */
	@Override
	public MyClientInfo addBlackList(MyClientInfo myClientInfo) {
//		myClientInfo안에 있는 counselorId를 이용해서 couselorNo select
		MyClientInfo counselorNo = myClientProfileDao.getCounselorNo(myClientInfo);
		myClientInfo.setCounselorNo(counselorNo.getCounselorNo());
		myClientInfo.setCounselorId(counselorNo.getCounselorId());
		
		// 현재 상태를 받아온다 (차단 중 인지 아닌지)
		int blackStatus = myClientProfileDao.getBlackStatus(myClientInfo);
//		System.out.println(fixedStatus);

		// 상태 update하기
		myClientInfo=myClientProfileDao.updateIsBlock(myClientInfo,blackStatus);
		
		return myClientInfo;
	}

	/*
	 * 유진 : clientInfoNo를 이용해서 client 정보 조회
	 * 	RegisterCustomerInfoController
	 */
	@Override
	public ClientInfo selectClientProfile(int clientInfoNo) {
		return myClientProfileDao.getInfo(clientInfoNo);
	}

	/*
	 * 유진 : clientInfoNo을 이용해 DB에 저장되어 있는 정보 조회
	 * 	RegusterCustomerAliasController
	 */
	@Override
	public MyClientInfo selectMyClientProfile(int clientInfoNo) {
		return myClientProfileDao.getMyInfo(clientInfoNo);
	}

	/*
	 * 유진 : DB에 등록되어 있는 자주 쓰는 답변 가져오기
	 * 	ChatMainController
	 */
	@Override
	public List<FrequentReply> getReply() {
		return myClientProfileDao.selectReply();
	}

	

}
