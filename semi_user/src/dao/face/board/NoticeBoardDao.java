package dao.face.board;

import java.util.List;
import java.util.Map;

import dto.NoticeBoard;
import util.Paging;

public interface NoticeBoardDao {
	
	/**
	 * 지재용
	 * 2019-11-26
	 * 
	 * 총 게시글 수 조회
	 * 
	 * @return int - 총 게시글 수
	 */
	public int selectCntAll(Map<String, String> map);

	
	/**
	 * 지재용
	 * 2019-11-26
	 * 
	 * 페이징 정보를 활용하여 보여질 게시글 목록만 조회
	 *  
	 * @param Paging - 페이징 정보
	 * @return List - 게시글 목록
	 */
	public List selectAll(Paging paging);

	/**
	 * 지재용
	 * 2019-11-28
	 * 
	 * 게시글 입력
	 * 
	 * @param board - 삽입될 게시글 내용
	 */
	public void insert(NoticeBoard noticeboard);

	
	/**
	 * 지재용
	 * 2019-12-01
	 * 
	 * 조회되는 게시글의 조회수 +1
	 * 
	 * @param noticeboard - 조회 대상
	 */
	public void updateHit(NoticeBoard noticeboard);

	
	/**
	 * 지재용
	 * 2019-12-01
	 * 
	 * 상세보기 게시글 조회
	 * 
	 * @param noticeboard - 조회 대상
	 * @return NoticeBoard - 게시글 조회 결과
	 */
	public NoticeBoard selectNoticeBoardByNoticeBoardno(NoticeBoard noticeboard);

	
	/**
	 * 지재용
	 * 2019-12-01
	 * 
	 * 게시글 삭제
	 * 
	 * @param noticeboard - 삭제할 게시글번호를 담은 객체
	 */
	public void delete(NoticeBoard noticeboard);

	
	/**
	 * 지재용
	 * 2019-12-01
	 * 
	 * 게시글 수정 
	 * 
	 * @param noticeboard - 수정할 내용을 담은 객체
	 */
	public void update(NoticeBoard noticeboard);


	public NoticeBoard getCounserlorId(int noticeboardno);
	
	
	
	
	
	

	

	

}
