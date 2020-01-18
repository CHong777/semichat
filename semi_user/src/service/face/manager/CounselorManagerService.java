package service.face.manager;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.sun.corba.se.impl.ior.FreezableList;

import dto.Counselor;
import dto.FrequentReply;
import dto.InquiryBoard;
import util.Paging;

public interface CounselorManagerService {

	/**
	 * 조홍철
	 * 2019-12-04
	 * 
	 * 요청파라미터 curPage를 파싱한다
	 * counselor TB와 curPage 값을 이용한 Paging 객체를 생성하고 반환
	 * 
	 * @param req - 요청정보 객체
	 * @return Paging - 페이징 정보
	 */
	Paging getPaging(HttpServletRequest req);
	
	/**
	 * 조홍철
	 * 2019-12-04
	 * 
	 * 상담원 리스트를 얻어온다
	 * @param paging - 요청 페이징
	 * @return List - 리스트 정보
	 */
	public List getList(Paging paging);
	
	/**
	 * 조홍철
	 * 2019-12-04
	 * 
	 * 요청 파라미터 얻기
	 * 
	 * @param req - 요청정보 객체
	 * @return Counselor - 요청정보에 포함된 전달 파라미터
	 */
	public Counselor getCounselorProfileParam(HttpServletRequest req);
	
	/**
	 * 조홍철
	 * 2019-12-04
	 * 
	 * 상담원 번호를 통한 상담원 프로필 정보 가져오기
	 * 
	 * @param counselor - 상담원 번호가 담겨있는 객체
	 * @return Counselor - 상담원 프로필 정보가 담겨있는 결과 객체
	 */
	public Counselor getCounselorProfileByNo(Counselor counselor);
	
	/**
	 * 조홍철
	 * 2019-12-05
	 * 
	 * 계정관리 위한 상담원 리스트를 얻어온다
	 * 
	 * @param paging - 요청 페이징
	 * @return List - 리스트 정보
	 */
	public List getAccountList(Paging paging);

	/**
	 * 조홍철
	 * 2019-12-05
	 * 
	 * 기업매니저 승인 컬럼을 1로 변경시킨다(승인 완료)
	 * 
	 * @param counselorNo - 상담원 번호
	 */
	public void approveByManager(String counselorNo);

	/**
	 * 조홍철
	 * 2019-12-05
	 * 
	 * 상담원 번호를 이용해 채팅프로필(ChatProfile)테이블의 counselorID를 null 업데이트
	 * 
	 * @param counselorNo - 상담원 번호
	 */
	public void chatProfileToNull(String counselorNo);

	/**
	 * 조홍철
	 * 2019-12-05
	 * 
	 * 상담원 번호를 이용해 나만의상담자정보(MyClientInfo) Delete
	 * 
	 * @param counselorNo - 상담원 번호
	 */
	public void MyClientInfoDelete(String counselorNo);

	/**
	 * 조홍철
	 * 2019-12-05
	 * 
	 * 상담원 번호를 이용해 inquiryBoardFile 테이블 Delete
	 * 
	 * @param counselorNo - 상담원 번호
	 */
	public void inquiryBoardFileDelete(String counselorNo);

	/**
	 * 조홍철
	 * 2019-12-05
	 * 
	 * 상담원 번호를 이용해 inquiryBoard 테이블 Delete
	 * 
	 * @param counselorNo - 상담원 번호
	 */
	public void inquiryBoardDelete(String counselorNo);

	/**
	 * 조홍철
	 * 2019-12-05
	 * 
	 * 상담원 번호를 이용해 noticeBoard 테이블 Delete
	 * 
	 * @param counselorNo - 상담원 번호
	 */
	public void noticeBoardDelete(String counselorNo);

	/**
	 * 조홍철
	 * 2019-12-05
	 * 
	 * 상담원 번호를 이용해 ClientInfo 테이블의 counselorName을 null로 업데이트
	 * 
	 * @param counselorNo - 상담원 번호
	 */
	public void clientInfoToNull(String counselorNo);

	/**
	 * 조홍철
	 * 2019-12-05
	 * 
	 * 상담원 번호를 이용해 Counselor 테이블을 지움(탈퇴처리)
	 * 
	 * @param counselorNo - 상담원 번호
	 */
	public void counselorDelete(String counselorNo);
	
	/**
	 * 조홍철
	 * 2019-12-05
	 * 
	 * 상담원 번호를 이용해 CounselorID를 구함
	 * 
	 * @param counselorNo - 상담원 번호
	 */
	public Counselor getCounselorIdByNo(String counselorNo);
	
	/**
	 * 조홍철
	 * 2019-12-05
	 * 
	 * 상담원 아이디를 이용해 문의사항 번호 구하기
	 * 
	 * @param counselorId - 상담원 아이디
	 * @return List<InquiryBoard> - 문의사항 번호가 담긴 리스트
	 */
	public List<InquiryBoard> getIboardNo(String counselorId);

	/**
	 * 조홍철
	 * 2019-12-05
	 * 
	 * 자주쓰는 답변 리스트 조회
	 * 
	 * @return - 자주쓰는 답변 리스트
	 */
	public List<FrequentReply> getFrequentReplyList();

	/**
	 * 조홍철
	 * 2019-12-05
	 * 
	 * 자주쓰는 답변 등록
	 * 
	 * @param frequentReplyContent - 등록할 자주쓰는 답변
	 */
	public void registerFrequentReply(String frequentReplyContent);

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
