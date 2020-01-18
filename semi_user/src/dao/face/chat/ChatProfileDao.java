package dao.face.chat;

import java.util.List;
import java.util.Map;

import dto.ChatProfile;

public interface ChatProfileDao {

	/**
	 * 서현석
	 * 2019-11-29
	 * 
	 * chatProfile 에서 채팅 프로필 리스트 select하기(상담데스크 메인 페이지 접속 doGet)
	 * @return List<ChatProfile> : 채팅 프로필 리스트(상태 : NEW, 검색창 : X, 체크박스 : X)
	 */
	public List<ChatProfile> selectChatProfile();
	
	/**
	 * 서현석
	 * 2019-12-02
	 * 
	 * 검색조건을 통한 채팅 프로필 리스트 select하기(상담데스크 Ajax doPost)
	 * @param map : 전달 파라미터들 (검색창, 체크박스 체크된 값들, 상태)
	 * @return List<ChatProfile> : 채팅 프로필 리스트
	 */
	public List<ChatProfile> selectChatProfile(Map<String, String> map);

	/**
	 * 서현석
	 * 2019-12-02
	 * 
	 * 검색조건을 통한 채팅 프로필 리스트 select하기 + 차단된 리스트는 제거(상담데스크 Ajax doPost)
	 * @param map : 전달 파라미터들 (검색창, 체크박스 체크된 값들, 상태)
	 * @return List<ChatProfile> : 채팅 프로필 리스트
	 */
	public List<ChatProfile> selectChatProfile(Map<String, String> map, String blockList);
	
	/**
	 * 서현석
	 * 2019-12-03
	 * 
	 * 해당 채팅프로필의 상태를 업데이트
	 * @param chatProfile, status(0:new, 1:진행중, 2:완료)
	 */
	public void updateStatus(ChatProfile chatProfile, int status);
	
	/**
	 * 서현석
	 * 2019-12-09
	 * 
	 * 세션정보를 이용하여 차단된 채팅 프로필 리스트를 가져온다
	 * @param map : 세션정보
	 * @return String : 차단된 채팅 프로필 리스트
	 */
	public String selectBlockProfile(Map<String, String> map);

}
