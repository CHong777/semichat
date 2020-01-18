package dao.face.chat;

import java.util.List;

import dto.ClientInfo;
import dto.FrequentReply;
import dto.MyClientInfo;

public interface MyClientProfileDao {
	/**
	 * 장유진
	 * 2019-12-01
	 * 저장되어 있는 손님이면 update
	 * @param clientInfo
	 */
	public void updateClientInfo(ClientInfo clientInfo);

	/**
	 * 장유진
	 * 2019-12-01
	 * myClientProfile에 있는 손님인지 flag로 이용
	 * @param myClientInfo - clientNick, counselorId
	 * @return 닉넴임이 등록된  고객이면 1, 등록되지 않은 고객이면 0
	 */
	public MyClientInfo getMyClientInfoNo(MyClientInfo myClientInfo);

	/**
	 * 장유진
	 * 2019-12-01
	 * 닉네임 업데이트
	 * @param myClientInfo
	 */
	public void updateClientAlias(MyClientInfo myClientInfo);

	/**
	 * 장유진
	 * 2019-12-01
	 * 신규 닉네임 저장
	 * @param myClientInfo
	 */
	public void insertClientAlias(MyClientInfo myClientInfo);

	/**
	 * 장유진
	 * 2019-12-01
	 * session으로 받아온 counselorId를 이용해 counselorNo 조회
	 * @param myClientInfo
	 * @return counselorNo
	 */
	public MyClientInfo getCounselorNo(MyClientInfo myClientInfo);

	/**
	 * 장유진
	 * 2019-12-01
	 * 고정 매칭 된 상태를 변경
	 * @param myClientInfo, fixedStatus - 현재 고정 상태
	 */
	public MyClientInfo updateMatchingStatus(MyClientInfo myClientInfo, int fixedStatus);

	/**
	 * 장유진
	 * 2019-12-01
	 * @param myClientInfo
	 * @return isFixed 상태
	 */
	public int getFixedStatus(MyClientInfo myClientInfo);

	/**
	 * 장유진
	 * 2019-12-01
	 * @param myClientInfo
	 * @return isBlock 상태
	 */
	public int getBlackStatus(MyClientInfo myClientInfo);

	/**
	 * 장유진
	 * 2019-12-01
	 * @param myClientInfo, blackStatus - 현재 차단 상태
	 * @return isBlock 상태
	 */
	public MyClientInfo updateIsBlock(MyClientInfo myClientInfo, int blackStatus);

	/**
	 * 장유진
	 * 2019-12-01
	 * client의 정보 가져오기
	 * @param clientInfoNo
	 * @return clientName, clientPhoneNum, lastChatDate, counselorName, chatMemo
	 */
	public ClientInfo getInfo(int clientInfoNo);
	
	/**
	 * 장유진
	 * 2019-12-01
	 * myclient의 정보 가져오기
	 * @param myClientInfoNo
	 * @return counselorNo, counselorId, isBlock, isFIxedMatch, clientNick, clientInfoNo
	 */
	public MyClientInfo getMyInfo(int clientInfoNo);
	
	/**
	 * 장유진
	 * 2019-12-01
	 * 자주 쓰는 답변 리스트 가져오기
	 * @return DB에 저장되어 있는 것들 중 사용하는 답변들 리스트
	 */
	public List<FrequentReply> selectReply();
}
