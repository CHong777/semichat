package service.face.mypage;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import util.Paging;

public interface MyWritingInquiryService {
	
	/**
	 * 강세미
	 * 2019-12-04
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
	 * 2019-12-04
	 * 
	 * 문의사항 게시판 리스트를 얻어온다
	 * 
	 * @param paging - 요청 페이징
	 * @return List - 리스트 정보 반환
	 */
	public List getList(Paging paging);


}
