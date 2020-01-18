package dao.face.chat;

import java.util.ArrayList;
import java.util.List;

import dto.Chat;
import dto.ChatProfile;

public interface ChatDeskDao {

	/**
	 * 조홍철
	 * 2019-12-01
	 * 
	 * 채팅프로필 번호를 통해 클라이언트아이디를 DB에서 조회
	 * 
	 * @param chatProfile - 채팅프로필번호가 담긴 객체
	 * @return ChatProfile - 클라이언트 아이디가 담긴 결과 객체
	 */
	public ChatProfile selectClientIdByChatProfileNo(ChatProfile chatProfile);
	
	/**
	 * 조홍철
	 * 2019-12-01
	 * 
	 * 채팅 정보를 SemiChat DB에 삽입
	 * 
	 * @param chat - 채팅 정보가 담겨있는 Chat 객체
	 * @return int - 성공/ 실패여부 반환, 1이면 성공 -1이면 실패
	 */
	public int insertSemiChat(Chat chat);
	
	/**
	 * 조홍철
	 * 2019-12-01
	 * 
	 * 채팅 정보를 facebook DB에 삽입
	 * 
	 * @param chat - 채팅 정보가 담겨있는 Chat 객체
	 * @param chatProfileNo - 챗 프로필 넘버
	 * @return int - 성공/ 실패여부 반환, 1이면 성공 -1이면 실패
	 */
	public int insertfacebookDB(Chat chat, int chatProfileNo);
	
	/**
	 * 조홍철
	 * 2019-12-03
	 * 
	 * 채팅 정보를 kakao DB에 삽입
	 * 
	 * @param chat - 채팅 정보가 담겨있는 Chat 객체
	 * @param chatProfileNo - 챗 프로필 넘버
	 * @return int - 성공/ 실패여부 반환, 1이면 성공 -1이면 실패
	 */
	public int insertkakaoDB(Chat chat, int chatProfileNo);
	
	/**
	 * 조홍철
	 * 2019-12-03
	 * 
	 * 채팅 정보를 lineDB에 삽입
	 * 
	 * @param chat - 채팅 정보가 담겨있는 Chat 객체
	 *  * @param chatProfileNo - 챗 프로필 넘버
	 * @return int - 성공/ 실패여부 반환, 1이면 성공 -1이면 실패
	 */
	public int insertlineDB(Chat chat, int chatProfileNo);
	
	/**
	 * 조홍철
	 * 2019-12-03
	 * 
	 * 채팅 정보를 instagramDB에 삽입
	 * 
	 * @param chat - 채팅 정보가 담겨있는 Chat 객체
	 * @param chatProfileNo - 챗 프로필 넘버
	 * @return int - 성공/ 실패여부 반환, 1이면 성공 -1이면 실패
	 */
	public int insertinstagramDB(Chat chat, int chatProfileNo);
	
	/**
	 * 조홍철
	 * 2019-12-03
	 * 
	 * 채팅 정보를 wechatDB에 삽입
	 * 
	 * @param chat - 채팅 정보가 담겨있는 Chat 객체
	 * @param chatProfileNo - 챗 프로필 넘버
	 * @return int - 성공/ 실패여부 반환, 1이면 성공 -1이면 실패
	 */
	public int wechatDB(Chat chat, int chatProfileNo);
	
	
	/**
	 * 조홍철
	 * 2019-12-01
	 * 
	 * 채팅을 보낼때, 마지막 채팅 시간을 Update(SemiChat)
	 * 
	 * @param chatProfile - chatProfileNo 정보가 담겨 있는 ChatProfile 객체
	 */
	public void updateLastChatDateToSemiChat(ChatProfile chatProfile);
	
	/**
	 * 조홍철
	 * 2019-12-03
	 * 
	 * 채팅을 보낼때, 마지막 채팅 시간을 Update(kakao)
	 * 
	 * @param chatProfile - chatProfileNo 정보가 담겨 있는 ChatProfile 객체
	 */
	public void updateLastChatDateToKakao(ChatProfile chatProfile);
	
	/**
	 * 조홍철
	 * 2019-12-03
	 * 
	 * 채팅을 보낼때, 마지막 채팅 시간을 Update(line)
	 * 
	 * @param chatProfile - chatProfileNo 정보가 담겨 있는 ChatProfile 객체
	 */
	public void updateLastChatDateToLine(ChatProfile chatProfile);
	
	/**
	 * 조홍철
	 * 2019-12-03
	 * 
	 * 채팅을 보낼때, 마지막 채팅 시간을 Update(instagram)
	 * 
	 * @param chatProfile - chatProfileNo 정보가 담겨 있는 ChatProfile 객체
	 */
	public void updateLastChatDateToInstagram(ChatProfile chatProfile);
	
	/**
	 * 조홍철
	 * 2019-12-03
	 * 
	 * 채팅을 보낼때, 마지막 채팅 시간을 Update(facebook)
	 * 
	 * @param chatProfile - chatProfileNo 정보가 담겨 있는 ChatProfile 객체
	 */
	public void updateLastChatDateToFacebook(ChatProfile chatProfile);
	
	/**
	 * 조홍철
	 * 2019-12-03
	 * 
	 * 채팅을 보낼때, 마지막 채팅 시간을 Update(wechat)
	 * 
	 * @param chatProfile - chatProfileNo 정보가 담겨 있는 ChatProfile 객체
	 */
	public void updateLastChatDateToWechat(ChatProfile chatProfile);
	
	/**
	 *  조홍철
	 *  2019-12-01
	 *  
	 *  세미챗에서 채팅 읽었을 때, semiChat의 Chat테이블의 CHATREAD를 1로 update
	 *  
	 * @param chatProfile - chatProfileNo가 담겨있는 객체
	 */
	public void updateChatRead(ChatProfile chatProfile);
	
	/**
	 * 조홍철
	 * 2019-12-01
	 * 
	 * 최근순으로 채팅 리스트 가져오기
	 * 
	 * @param fromID -  semichat
	 * @param toID - 상담자 아이디
	 * @param number - 가져올 최대 갯수(MAX)
	 * @return ArrayList - 채팅 리스트
	 */
	public ArrayList<Chat> selectChatListByRecent(String fromID, String toID, int number);
	
	
	/**
	 * 조홍철 - 피땀눈물
	 * 2019-12-03
	 * 
	 * 채팅 리스트를 채팅 번호로 가져오기
	 * 
	 * @param chatProfileNo - 챗 프로필 번호
	 * @return ArrayList - 채팅 리스트
	 */
	public ArrayList<Chat> selectChatListByID(int chatProfileNo);
	
	/**
	 * 조홍철
	 * 2019-12-06
	 * 
	 * 페이스북 DB에서 chatProfileNo 가져오기
	 * 
	 * @param ClientId - 클라이언트 Id
	 * @return int - chatProfileNo 
	 */
	public int selectFaceBookChatProfileNo(String ClientId);
	
	/**
	 * 조홍철
	 * 2019-12-06
	 * 
	 * 인트사 DB에서 chatProfileNo 가져오기
	 * 
	 * @param ClientId - 클라이언트 Id
	 * @return int - chatProfileNo 
	 */
	public int selectInstagramChatProfileNo(String ClientId);
	
	/**
	 * 조홍철
	 * 2019-12-06
	 * 
	 * 카카오 DB에서 chatProfileNo 가져오기
	 * 
	 * @param ClientId - 클라이언트 Id
	 * @return int - chatProfileNo 
	 */
	public int selectKakaoChatProfileNo(String ClientId);
	
	/**
	 * 조홍철
	 * 2019-12-06
	 * 
	 * 라인 DB에서 chatProfileNo 가져오기
	 * 
	 * @param ClientId - 클라이언트 Id
	 * @return int - chatProfileNo 
	 */
	public int selectLineChatProfileNo(String ClientId);
	
	/**
	 * 조홍철
	 * 2019-12-06
	 * 
	 * 위챗 DB에서 chatProfileNo 가져오기
	 * 
	 * @param ClientId - 클라이언트 Id
	 * @return int - chatProfileNo 
	 */
	public int selectWeChatProfileNo(String ClientId);
	
}
