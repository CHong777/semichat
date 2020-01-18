package service.face.chat;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import dto.ClientInfo;
import dto.FrequentReply;
import dto.MyClientInfo;

public interface MyClientProfileService {
	/**
	 * 장유진
	 * 2019-12-01
	 * 고객정보에 입력한 정보들 전달
	 * @param req 
	 * @return clientName, clientPhoneNo, lastChatDate, counselorName, chatMemo
	 */
	public ClientInfo getClientProfile(HttpServletRequest req);

	/**
	 * 장유진
	 * 2019-12-01
	 * client 정보를 DB에 저장
	 * @param clientInfo - clientName, clientPhoneNum, lastChatDate, counselorName, chatMemo
	 */
	public void registerCustomerInfo(ClientInfo clientInfo);

	/**
	 * 장유진
	 * 2019-12-01
	 * 입력한 nickname, sessionID로 counselorId 받아오기
	 * @param req
	 * @return nickname, counselorId
	 */
	public MyClientInfo getMyClientProfile(HttpServletRequest req);

	/**
	 * 장유진
	 * 2019-12-01
	 * DB에 client의 nickname 저장
	 * @param myClientInfo - counselorNo, couneslorId, isBlock, isFixedMatch, clientNick, clientInfoNo
	 */
	public void getMyClientProfile(MyClientInfo myClientInfo);

	/**
	 * 장유진
	 * 2019-12-01
	 * 고정 매칭하기
	 * @param myClientInfo - clientNick, clientInfoNo, counselorId가 set 되어 있는 myClientInfo
	 */
	public MyClientInfo fixedMatchingChat(MyClientInfo myClientInfo);

	/**
	 * 장유진
	 * 2019-12-01
	 * 차단하기 - 블랙리스트 추가
	 * @param myClientInfo - clientNick, clientInfoNo, counselorId가 set 되어 있는 myClientInfo
	 */
	public MyClientInfo addBlackList(MyClientInfo myClientInfo);

	/**
	 * 장유진
	 * 2019-12-01
	 * client의 정보 조회
	 * @param clientInfoNo - req에서 전달 되어 온 clientInfoNo
	 * @return clientInfoNo, clientName, clientPhoneNum, lastChatDate, counselorName, chatMemo
	 */
	public ClientInfo selectClientProfile(int clientInfoNo);

	/**
	 * 장유진
	 * 2019-12-01
	 * myClient의 정보 조회
	 * @param clientInfoNo - req에서 전달 되어 온 clientInfoNo
	 * @return myClientInfoNo, counselorNo, couneslorId, isBlock, isFixedMatch, clientNick, clientInfoNo
	 */
	public MyClientInfo selectMyClientProfile(int clientInfoNo);

	/**
	 * 장유진
	 * 2019-12-01
	 * DB에 등록되어 있는 자주 사용하는 답변 목록 select
	 * @return 사용 중인 답변들 (frequentReplyInDesk=1인 것 들)
	 */
	public List<FrequentReply> getReply();


}
