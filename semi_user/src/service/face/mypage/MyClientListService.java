package service.face.mypage;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import dto.InquiryBoard;
import dto.MyClientInfo;
import util.Paging;

public interface MyClientListService {
	

	/**
	 * 강세미
	 * 2019-12-05
	 * 
	 * curPage 파싱
	 * Board 테이블과 curPage값을 이용한 Paging 객체 생성하고 반환
	 * 
	 * @param req - 요청정보 객체
	 * @return Paging - 페이징 정보
	 */
	public Paging getPaging(HttpServletRequest req);
	
	/**
	 * 강세미
	 * 2019-12-05
	 * 
	 * clientinfo 리스트를 얻어온다
	 * 
	 * @param paging - 요청 페이징
	 * @return List - 리스트 정보 반환
	 */
	public List getList(Paging paging);
	
	/**
	 * 강세미
	 * 2019-12-05
	 * 
	 * clientList 체크 삭제 메소드
	 * @param deleteBoard - 요청 파라미터
	 */
	public void delete(MyClientInfo deleteBoard);
	
	/**
	 * 강세미
	 * 2019-12-05
	 * 
	 * bookmark 추가 해주기위한 메소드 ('N' -> 즐찾안됨 / 'Y' -> 즐찾됨)
	 * 
	 * @param book - 요청파라미터
	 * @param no - 요청파라미터
	 * @param type - 요청파라미터
	 * @return String - 반환값
	 */
	
	public String bookMarkPlus(String book, String no, String type);
	
	
	/**
	 * 강세미
	 * 2019-12-05
	 * 
	 * bookmark 삭제 해주기위한 메소드 ('N' -> 즐찾안됨 / 'Y' -> 즐찾됨)
	 * 
	 * @param book - 요청파라미터
	 * @param no - 요청파라미터
	 * @param type - 요청파라미터
	 * @return String - 반환값
	 */
	public String bookMarkMinus(String book, String no, String type);
	
	/**
	 * 강세미
	 * 2019-12-05
	 * 
	 * 즐겨찾기 여부 가져오는 메소드
	 * 
	 * @param boardno - 요청파라미터(boardno)
	 * @return
	 */
	public List getBookMarkCheck(String[] boardno);

	
}
