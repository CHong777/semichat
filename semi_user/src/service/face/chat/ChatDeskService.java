package service.face.chat;

import javax.servlet.http.HttpServletRequest;

import dto.Chat;
import dto.ChatProfile;

public interface ChatDeskService {

	/**
	 * 조홍철
	 * 2019-12-01
	 * 
	 * 채팅프로필 번호를 통해 클라이언트아이디를 가져온다
	 * 
	 * @param chatProfile - 채팅프로필번호가 담긴 객체
	 * @return ChatProfile - 클라이언트 아이디가 담긴 결과 객체
	 */
	public ChatProfile getClientIdByChatProfileNo(ChatProfile chatProfile);
	
	/**
	 * 조홍철
	 * 2019-12-01
	 * 
	 * 올바름 클라이언트에게 입력한 메시지를 보내기 위한 챗 셋팅 메서드
	 * 
	 * @param chatProfile - 챗 프로필 번호, 클라이언트 아이디가 담겨져 있는 chatProfile 객체
	 * @param chatContent - 상담원이 입력한 내용
	 * @param req - 세션 값 구하기 위한 객체
	 * @return Chat - 셋팅한 채팅 객체
	 */
	public Chat settingChat(ChatProfile chatProfile, String chatContent, HttpServletRequest req);
	
	/**
	 * 조홍철
	 * 2019-12-01
	 * 
	 * SemiChat, Client DB(카카오, 네이버톡톡, 인스타그램, 페이스북, 위챗)에  데이터 삽입
	 * 
	 * @param chat - chatProfileNo, fromID, toID, chatContent가 담긴 Chat 객체
	 * @param chatProfile - 메신저 번호가 담겨져 있는 객체(메신저 번호로 메신저 구분)
	 * @return - 성공/ 실패여부 리턴 값 
	 * 			1=> 성공, -1 => 실패
	 */
	public int multipleSubmit(Chat chat, ChatProfile chatProfile);
	
	/**
	 * 조홍철
	 * 2019-12-01
	 * 
	 * SemiChat, Cliend DB의  chatprofile테이블의 LASTCHATDATE 컬럼의 값을 메시지 전송시간으로 update
	 * 
	 * @param chatprofile - chatProfileNo를 갖고있는 chatProfile 객체
	 */
	public void setLastChatDate(ChatProfile chatProfile);
	
	/**
	 * 조홍철
	 * 2019-12-01
	 * 
	 * semiChat의 CHAT 테이블의 ToID가 semiChat인 ChatRead를 1로 변경
	 * 
	 * @param chatProfile -chatProFileNo가 담겨져 있는 객체
	 */
	public void readChat(ChatProfile chatProfile);
	
	/**
	 * 조홍철 - 피땀눈물
	 * 2019-12-03
	 * 
	 * 채팅 정보 구하는 메서드
	 * @param - 채팅 프로필번호
	 * @return json 결과 값
	 */
	public String getChat(int chatProfileNo);
	
	
	/**
	 * 조홍철
	 * 2019-12-06
	 * 
	 * 페이스북 DB의 챗 프로파일 넘버를 가져온다.
	 * 
	 * @param ClientId - 클라이언트 아이디
	 * @return int - 챗프로필 넘버
	 */
	public int getFaceBookChatProfileNo(String ClientId);
	
	/**
	 * 조홍철
	 * 2019-12-06
	 * 
	 * 인스타그램 DB의 챗 프로파일 넘버를 가져온다.
	 * 
	 * @param ClientId - 클라이언트 아이디
	 * @return int - 챗프로필 넘버
	 */
	public int getInstagramChatProfileNo(String ClientId);
	
	/**
	 * 조홍철
	 * 2019-12-06
	 * 
	 * 카카오 DB의 챗 프로파일 넘버를 가져온다.
	 * 
	 * @param ClientId - 클라이언트 아이디
	 * @return int - 챗프로필 넘버
	 */
	public int getKakaoChatProfileNo(String ClientId);
	
	/**
	 * 조홍철
	 * 2019-12-06
	 * 
	 * 라인 DB의 챗 프로파일 넘버를 가져온다.
	 * 
	 * @param ClientId - 클라이언트 아이디
	 * @return int - 챗프로필 넘버
	 */
	public int getLineChatProfileNo(String ClientId);
	
	/**
	 * 조홍철
	 * 2019-12-06
	 * 
	 * 위챗 DB의 챗 프로파일 넘버를 가져온다.
	 * 
	 * @param ClientId - 클라이언트 아이디
	 * @return int - 챗프로필 넘버
	 */
	public int getWeChatProfileNo(String ClientId);
}
