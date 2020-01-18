package service.face.mypage;

import javax.servlet.http.HttpServletRequest;

import dto.ClientInfo;
import dto.Counselor;

public interface MyInfoService {

	/**
	 * 강세미
	 * 2019-12-04
	 * 
	 * 마이페이지에서 counselor의 비밀번호를 체크할 메소드
	 * @param id - sessionid
	 */
	public String getPassword(String id);
	
	/**
	 * 강세미
	 * 2019-12-04
	 * 
	 * counselor정보 get해오기
	 * @param counselor - dto에 저장된 id 넘기기
	 * @return Counselor - counselor 객체에 저장
	 */
	public Counselor getCounselorInfo(Counselor counselor);

	/**
	 * 강세미
	 * 2019-12-04
	 * 
	 * 전달파라미터 값 저장
	 * @param req 전달 파라미터
	 * @return Counselor - 객체에 저장
	 */
	public Counselor getCounselorInfo(HttpServletRequest req);
	
	/**
	 * 강세미
	 * 2019-12-04
	 * 
	 * counselor정보 업데이트
	 * 
	 * @param counselor - 파라미터 전달
	 */
	public void counselorInfoUpdate(Counselor counselor);
	
	/**
	 * 강세미
	 * 2019-12-04
	 * 
	 * 닉네임 중복 체크
	 * @param nickname - 전달된 닉네임 파라미터
	 * @return 중복여부
	 */
	public int counselorNicknameCheck(String nickname);

	/**
	 * 강세미
	 * 2019-12-04
	 * 
	 * 회원탈퇴 신청 여부 바꾸기 (0->1)
	 * 0: 탈퇴신청 안함
	 * 1: 탈퇴신청 함
	 * @param counselor - counselor 객체 정보
	 */
	public void counselorWithdrawalUpdate(Counselor counselor);


	
	
}
