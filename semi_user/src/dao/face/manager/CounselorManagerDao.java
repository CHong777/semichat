package dao.face.manager;

import java.util.List;
import java.util.Map;

import dto.Counselor;
import dto.FrequentReply;
import dto.InquiryBoard;
import util.Paging;

public interface CounselorManagerDao {
	
	/**
	 * 조홍철
	 * 2019-12-04
	 * 
	 * 페이징을 위한 총 게시글 수 조회
	 * 
	 * @return int - 총 게시글 수
	 */
	public int selectCntAll(Map<String, String> map);
	

	/**
	 * 조홍철
	 * 2019-12-04
	 * 
	 * 상담원 목록 조회
	 * 
	 * @return - 조회된 상담원 목록
	 */
	public List selectAll(Paging paging);
	
	/**
	 * 조홍철
	 * 2019-12-04
	 * 
	 * 상담원 프로필 정보를 DB에서 조회
	 * 
	 * @param counselor - 상담원 번호가 담긴 객체
	 * @return Counselor - 상담원 프로필 정보가 담긴 결과 객체
	 */
	public Counselor selectCounselorProfileByNo(Counselor counselor);


	/**
	 * 조홍철
	 * 2019-12-05
	 * 
	 * 계정관리 위한 상담원 전체 목록 조회
	 * 
	 * @return - 조회된 상담원 목록
	 */
	public List selectAccountAll(Paging paging);
	
	/**
	 * 조홍철
	 * 2019-12-05
	 * 
	 * 상담원 테이블의 managerconfirm을 1로 업데이트
	 * 
	 * @param counselorNo - 상담원 번호
	 */
	public void updateManagerConfirm(String counselorNo);


	/**
	 * 조홍철
	 * 2019-12-05
	 * 
	 * 상담원 아이디를 이용해 채팅프로필(ChatProfile)테이블의 counselorID를 null 업데이트
	 * 
	 * @param counselorNo - 상담원 아이디
	 */
	public void updateChatProfileToNull(String counselorId);

	/**
	 * 조홍철
	 * 2019-12-05
	 * 
	 * 상담원 아이디를 이용해 나만의상담자정보(MyClientInfo) Delete
	 * 
	 * @param counselorId - 상담원 아이디
	 */
	public void deleteMyClientInfo(String counselorId);

	/**
	 * 조홍철
	 * 2019-12-05
	 * 
	 * 공지사항 게시판 번호를 이용해 inquiryBoardFile 테이블 Delete
	 * 
	 * @param iBoardNo - 공지사항 게시판 번호
	 */
	public void deleteInquiryBoardFile(int iBoardNo);


	/**
	 * 조홍철
	 * 2019-12-05
	 * 
	 * 상담원 아이디를 이용해 inquiryBoard 테이블 Delete
	 * 
	 * @param counselorId - 상담원 아이디
	 */
	public void deleteinquiryBoard(String counselorId);

	/**
	 * 조홍철
	 * 2019-12-05
	 * 
	 * 상담원 아이디를 이용해 noticeBoard 테이블 Delete
	 * 
	 * @param counselorId - 상담원 아이디
	 */
	public void deletenoticeBoard(String counselorId);

	/**
	 * 조홍철
	 * 2019-12-05
	 * 
	 * 상담원 이름을 이용해 ClientInfo 테이블의 counselorName을 null로 업데이트
	 * 
	 * @param counselorName - 상담원 이름
	 */
	public void updateClientInfoToNull(String counselorName);


	/**
	 * 조홍철
	 * 2019-12-05
	 * 
	 * 상담원 번호를 이용해 Counselor 테이블을 지움(탈퇴처리)
	 * 
	 * @param counselorNo - 상담원 번호
	 */
	public void deleteCounselor(String counselorNo);

	/**
	 * 조홍철
	 * 2019-12-05
	 * 
	 * 상담원 번호를 이용해 CounselorID를 구함
	 * 
	 * @param counselorNo - 상담원 번호
	 * @return Counselor - 상담원 아이디가 담긴 객체
	 */
	public Counselor selectCounselorIdByNo(String counselorNo);
	
	/** 
	 * 조홍철
	 * 2019-12-05
	 * 
	 * 상담원 아이디를 통해 문의사항 번호 구함
	 * 
	 * @param counselorId - 상담원 아이디
	 * @return List<InquiryBoard> - 문의사항 번호가 담긴 리스트
	 */
	public List<InquiryBoard> selectIboardNoByCounselorId(String counselorId);


	/**
	 * 조홍철
	 * 2019-12-05
	 * 
	 * 상담원 번호를 이용해 CounselorName를 구함
	 * 
	 * @param counselorNo - 상담원 번호
	 * @return Counselor - 상담원 이름이 담긴 객체
	 */
	public Counselor selectCounselorNameByNo(String counselorNo);
	
	/**
	 * 조홍철
	 * 2019-12-05
	 * 
	 * 자주쓰는 답변 리스트 조회
	 * 
	 * @return - 자주쓰는 답변 리스트
	 */
	public List<FrequentReply> selectFrequentReply();
	
	/**
	 * 조홍철
	 * 2019-12-05
	 * 
	 * 자주쓰는 답변 DB에 삽입
	 * 
	 * @param frequentReplyContent - 등록 할 자주쓰는 답변
	 */
	public void insertFrequentReply(String frequentReplyContent);


	/**
	 * 조홍철
	 * 2019-12-05
	 * 
	 * 자주쓰는 답변 삭제
	 * 
	 * @param frequentReplyNo - 삭제할 자주쓰는 답변 번호
	 */
	public void deleteFrequentReply(String frequentReplyNo);
}
