package dao.face.mypage;

import java.util.List;
import java.util.Map;

import dto.InquiryBoard;
import dto.MyClientInfo;
import util.Paging;

public interface MyClientListDao {
	
	/**
	 * 강세미
	 * 2019-12-05
	 * 
	 * 페이징을 위한 총 회원 수 조회
	 * 
	 * @param map - 검색 쿼리문 요청 파라미터
	 * @return - 총 회원 수
	 */
	public int selectCntAll(Map<String, String> map, String counselorid);
	
	/**
	 * 강세미
	 * 2019-12-05
	 * 
	 * clientinfo 전체 목록 조회
	 * 
	 * @return - 조회된 게시글 목록
	 */
	public List selectAll(Paging paging);

	/**
	 * 강세미
	 * 2019-12-05
	 * 
	 * client 삭제
	 * @param deleteBoard - 요청 파라미터
	 */
	public void clientdelete(MyClientInfo deleteBoard);

	/**
	 * 강세미
	 * 2019-12-06
	 * 
	 * 공지사항 북마크 'y'로 INSERT
	 * @param deleteBoard - 요청 파라미터
	 */
	public String nBookMarkInsert(String book, String no);
	
	/**
	 * 강세미
	 * 2019-12-06
	 * 
	 * 문의사항 북마크 'y'로 INSERT
	 * @param deleteBoard - 요청 파라미터
	 */
	public String iBookMarkInsert(String book, String no);

	/**
	 * 강세미
	 * 2019-12-06
	 * 
	 * 공지사항 북마크 'N'으로 INSERT
	 * @param deleteBoard - 요청 파라미터
	 */
	public String nBookMarkDelete(String book, String no);
	
	/**
	 * 강세미
	 * 2019-12-06
	 * 
	 * 문의사항 북마크 'N'으로 INSERT
	 * @param deleteBoard - 요청 파라미터
	 */
	public String iBookMarkDelete(String book, String no);
	
	/**
	 * 강세미
	 * 2019-12-06
	 * 
	 * @param boardno - boardno
	 * @return
	 */
	public List bookMarkSelect(String[] boardno);


}
