package dao.face.mypage;

import javax.servlet.http.HttpServletRequest;

import dto.Counselor;

public interface MyInfoDao {
	
	/**
	 * 강세미
	 * 2019-12-04
	 * 
	 * password 비교를 위한 counselor DB 조회
	 * @param String - sessionid 전달
	 */
	public String selectPassword(String id);
	
	/**
	 * 강세미
	 * 2019-12-04
	 * 
	 * couselor 정보 select
	 * 
	 * @param req - 
	 * @return Counselor - 
	 */
	public Counselor selectCounselorInfo(Counselor counselor);

	/**
	 * 강세미
	 * 2019-12-04
	 * 
	 * counselorInfo 업데이트
	 * @param req - 모델 파라미터
	 * 
	 */
	public void UpdateCounselorInfo(Counselor counselor);

	/**
	 * 강세미
	 * 2019-12-04
	 * 
	 * 입력한 닉네임 DB 조회
	 * @param nickname - 정보 수정시 입력한 닉네임
	 * @return int - 1이면중복, 0이면 중복 아님
	 */
	public int selectNicknameForCheck(String nickname);
	
	/**
	 * 강세미
	 * 2019-12-04
	 * 
	 * dto counselor 객체에 담긴 counselor 아이디로
	 * 회원탈퇴 신청 여부 update
	 * 
	 * @param counselor
	 */
	public void updateCounselorWithdrawalCheck(Counselor counselor);
	


}
