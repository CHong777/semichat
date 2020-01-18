package service.face.board;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import dto.NoticeBoard;
import util.Paging;

public interface NoticeBoardService {
	
	/**
	 * 지재용
	 * 2019-11-26
	 * 
	 * 요청파라미터 curPage를 파싱한다
	 * NoticeBoard TB와 curPage 값을 이용한 Paging 객체를 생성하고 반환
	 * 
	 * @param req - 요청정보 객체
	 * @return Paging - 페이징 정보
	 */
	public Paging getPaging(HttpServletRequest req);
	
	
	/**
	 * 지재용
	 * 2019-11-26
	 * 
	 * 페이징 정보를 활용하여 보여질 게시글 목록만 조회
	 *  
	 * @param Paging - 페이징 정보
	 * @return List - 게시글 목록
	 */
	public List getList(Paging paging);


	/**
	 * 지재용
	 * 2019-11-28
	 * 
	 * 게시글 작성
	 * 	입력한 게시글 내용을 DB에 저장
	 * 
	 *  
	 * 
	 * @param req - 요청정보 객체(게시글내용)
	 * 
	 */
	public void NoticeBoardWrite(HttpServletRequest req);

	
	/**
	 * 지재용
	 * 2019-12-01
	 * 
	 * 요청 파라미터 게시글 번호 파싱
	 * 
	 * @param req - 요청 정보 객체
	 * @return NoticeBoard - 게시글 번호를 가진 객체
	 */
	public NoticeBoard getBoardno(HttpServletRequest req);

	
	/**
	 * 지재용
	 * 2019-12-01
	 * 
	 * 상세보기 게시글 조회
	 * 
	 * @param noticeboard - 상세보기할 noticeboardno를 가진 객체
	 * @return NoticeBoard - 상세보기할 게시글 조회 결과
	 */
	public NoticeBoard show(NoticeBoard noticeboard);
	
	
	/**
	 * 지재용
	 * 2019-12-01
	 * 
	 * 게시글 삭제
	 * 
	 * @param noticeboard - 삭제할 게시글 번호를 가진 객체
	 */
	public void delete(NoticeBoard noticeboard);

	
	/**
	 * 지재용
	 * 2019-12-01
	 * 
	 * 글 작성자인지 판단하기
	 * 
	 * @param req - 요청 정보 객체
	 * @return boolean - true : 로그인한 사람이 글 작성자
	 */
	public boolean checkId(HttpServletRequest req);

	
	/**
	 * 지재용
	 * 2019-12-01
	 * 
	 * 게시글 수정
	 * 
	 * @param req - 요청 정보 객체
	 */
	public void update(HttpServletRequest req);
	
	
	
}
