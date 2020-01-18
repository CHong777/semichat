package dao.face.mypage;

import java.util.List;
import java.util.Map;

import util.Paging;

public interface MyWritingInquiryDao {

	/**
	 * 강세미
	 * 2019-12-04
	 * 
	 * 페이징을 위한 총 게시글 수 조회
	 * 
	 * @param map - 검색 쿼리문 요청 파라미터
	 * @return - 총 게시글 수
	 */
	public int selectCntAll(Map<String, String> map, String counselorid);
	
	/**
	 * 강세미
	 * 2019-12-04
	 * 
	 * 문의사항 게시글 전체 목록 조회
	 * 
	 * @return - 조회된 게시글 목록
	 */
	public List selectAll(Paging paging);

}
