package dao.face.board;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dto.InquiryBoard;
import dto.InquiryBoardFile;
import util.Paging;

public interface InquiryBoardDao {

	/**
	 * 강세미
	 * 2019-11-25
	 * 
	 * 페이징을 위한 총 게시글 수 조회
	 * 
	 * @param map - 검색 쿼리문  요청 파라미터
	 * @return int - 총 게시글 수
	 */
	public int selectCntAll(Map<String, String> map);
	
	
	/**
	 * 강세미
	 * 2019-11-25
	 * 
	 * 게시글 전체 목록 조회
	 * 
	 * @return - 조회된 게시글 목록
	 */
	public List selectAll(Paging paging);

	
	/**
	 * 강세미
	 * 2019-11-25
	 * 
	 * 게시글 조회수 업데이트
	 * @param board - 요청된 게시글 정보
	 */
	public void updateHit(InquiryBoard board);

	
	/**
	 * 강세미
	 * 2019-11-26
	 * 
	 * 게시글 번호 DAO에서 받아오기
	 * 
	 * @param showboard - 요청된 게시글 정보
	 * @return
	 */
	public InquiryBoard selectBoardByBoardno(InquiryBoard board);

	/**
	 * 강세미
	 * 2019-11-26
	 * 글작성시 글 번호 +1 해주기
	 * @return int - 글 번호 리턴
	 */
	public int selectBoardno();

	/**
	 * 강세미
	 * 2019-11-26
	 * 
	 * 작성글 insert
	 * @param inquiryBoard - 폼에서 받아온 글 작성 정보
	 */
	public void insert(InquiryBoard inquiryBoard);

	
	/**
	 * 강세미
	 * 2019-11-26
	 * 
	 * 작성글 중 파일 insert
	 * @param inquiryBoardFile
	 */
	public void insertFile(InquiryBoardFile inquiryBoardFile);

	
	/**
	 * 강세미
	 * 2019-11-27
	 * 
	 * 보여줄 파일 정보 받아오기
	 * 
	 * @param board - 
	 * @return
	 */
	public InquiryBoardFile getFileInfo(InquiryBoard board);

	
	/**
	 * 강세미
	 * 2019-11-27
	 * 
	 * 다운로드할 파일 번호 받아오기
	 * 
	 * @param downFile - 저장할 매개변수
	 */
	public void selectByFileno(InquiryBoardFile downFile);

	
	/**
	 * 강세미
	 * 2019-11-27
	 * 
	 * 게시글 수정 
	 * 
	 * @param inquiryBoard - 요청 파라미터
	 */
	public void update(InquiryBoard inquiryBoard);

	
	/**
	 * 강세미
	 * 2019-11-27
	 * 
	 * 게시글 파일 수정
	 * 
	 * @param inquiryBoardFile
	 */
	public void updateFile(InquiryBoardFile inquiryBoardFile);

	
	/**
	 * 강세미
	 * 2019-11-28
	 * 
	 * 게시글 파일 삭제
	 * 
	 * @param file - 요청 받은 파일
	 */
	public void delete(InquiryBoardFile file);

	
	/**
	 * 강세미
	 * 2019-11-28
	 * 
	 * 게시글  삭제
	 * 
	 * @param file - 요청 받은 게시글
	 */
	public void delete(InquiryBoard deleteBoard);

	
	/**
	 * 작성되었던 iboardno에 파일인서트
	 * @param req
	 * @param resp
	 */
	public void updateInsultFile(InquiryBoardFile inquiryBoardFile);

	

}
