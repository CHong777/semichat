package dao.face.join;

import dto.Counselor;

public interface CounselorJoinDao {

	/**
	 * 조홍철
	 * 2019-11-22
	 * 
	 *  입력한 아이디를 DB에서 조회
	 *  
	 * @param id - 회원가입 시 상담원이 입력한 아이디
	 * @return int - 1이면 중복, 0이면 중복 아님
	 */
	public int selectIdForCheck(String id);
	
	/**
	 * 조홍철
	 * 2019-11-22
	 * 
	 *  입력한 닉네임을 DB에서 조회
	 *  
	 * @param nickname - 회원가입 시 상담원이 입력한 닉네임
	 * @return int - 1이면 중복, 0이면 중복 아님
	 */
	public int selectNicknameForCheck(String nickname);
	
	/**
	 * 조홍철
	 * 2019-11-23
	 * 
	 * 입력한 상담원의 회원가입 정보를 DB에 insert
	 * 
	 * @param counselor - 상담원의 회원가입 정보 객체
	 */
	public void join(Counselor counselor);
	
	/**
	 * 조홍철
	 * 2019-11-23
	 * 
	 * 상담원이 입력한 이메일을 상담원번호를 통해 DB에서 이메일 조회
	 * 
	 * @param counselor - 회원가입 정보가 있는 삼담원 객체
	 * @return Counselor - 조회된 상담원 객체 결과
	 */
	public Counselor selectEmailByCounselorNo(Counselor counselor);

	/**
	 * 조홍철
	 * 2019-11-24
	 * 상담원이 입력한 이메일의 해시코드 값을 통해 DB에서  상담원 번호 조회
	 * 
	 * @param counselor - 상담원이 입력한 이메일의 해시코드 값이 담긴 상담원 객체
	 * @return Counselor - 조회된 상담원 객체 결과
	 */
	public Counselor selectCounselorNoByCounselorHashCode(Counselor counselor);
	
	/**
	 * 조홍철
	 * 2019-11-24
	 * 상담원번호를 통해 DB에서 EmailChecked 값을 1로 Update
	 * 1 - 이메일 인증 완료
	 * 0 - 이메일 인증 안한상태, Default 값
	 * 
	 * @param counselor - 상담원번호가 담겨있는 상담원 객체
	 */
	public void updateEmailChecked(Counselor counselor);
	
	/**
	 * 조홍철
	 * 2019-11-24
	 * 상담원의 이메일 해시 값을 통해 DB에서 EmailChecked(인증 여부)를 조회
	 * 
	 * @param counselor - 이메일 해시 값이 있는 상담원 객체
	 * @return Counselor - 조회된 상담원 객체 결과
	 */
	public Counselor selectEmailChecked(Counselor counselor);
}
