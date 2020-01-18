package service.face.chat;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import dto.ChatProfile;
import util.Paging;

public interface ChatProfileService {

	/**
	 * 서현석
	 * 2019-11-29
	 * 
	 * 채팅 프로필 리스트 불러오기(상담데스크 메인접속 doGet)
	 * @return List<ChatProfile> : 채팅 프로필 리스트
	 */
	public List<ChatProfile> getChatProfileList();
	
	/**
	 * 서현석
	 * 2019-11-29
	 * 
	 * new랑 현재일때 검색조건 가져오기
	 * @param req : 검색조건
	 * @return Map<String, String> : <key : 검색조건, value : 값>
	 */
	public Map<String, String> getParam(HttpServletRequest req);
	
	/**
	 * 서현석
	 * 2019-12-02
	 * 
	 * 채팅 프로필 리스트 불러오기(상담데스크 받아오기 doPost - 전달파라미터를 통해 가져옴)
	 * @param map : 전달파라미터
	 * @return List<ChatProfile> : 채팅 프로필 리스트
	 */
	public List<ChatProfile> getChatProfileList(Map<String, String> map);
	
	/**
	 * 서현석
	 * 2019-12-03
	 * 
	 * 채팅 프로필 넘버 가져오기(채팅 프로필 클릭시 해당 채팅프로필의 넘버를 불러온다)
	 * @param req : 전달 파라미터(채팅프로필 넘버)
	 * @return ChatProfile : 채팅프로필(채팅 프로필 넘버가 담긴 채팅프로필)
	 */
	public ChatProfile getChatProfileNo(HttpServletRequest req);
	
	/**
	 * 서현석
	 * 2019-12-03
	 * 
	 * 해당 채팅프로필의 상태를 1로 업데이트(NEW->진행중)
	 * @param chatProfile(채팅 프로필 넘버가 담긴 채팅프로필)
	 */
	public void updateStatus(ChatProfile chatProfile);
	
	/**
	 * 서현석
	 * 2019-12-05
	 * 
	 * 마지막 채팅 날짜 차이 계산해서 불러오기
	 * @param chatProfile
	 * @return chatProfile
	 */
	public List<ChatProfile> getDiffTime(List<ChatProfile> list);
	
	/**
	 * 서현석
	 * 2019-12-06
	 * 
	 * 채팅창 닫을 시 완료로 이동
	 * @param chatProfile
	 */
	public void closeTab(ChatProfile chatProfile);
	
	
	/**
	 * 서현석
	 * 2019-11-29
	 * 
	 * 검색 페이징 처리
	 * @param req
	 * @return
	 */
//	public Paging getPaging(HttpServletRequest req);
	

}
