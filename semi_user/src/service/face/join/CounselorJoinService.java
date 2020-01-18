package service.face.join;

import javax.servlet.http.HttpServletRequest;

import dto.Counselor;

public interface CounselorJoinService {

	/**
	 * 조홍철
	 * 2019-11-23
	 * 
	 * 요청 파라미터 얻기
	 * 
	 * @param req - 요청정보 객체
	 * @return Counselor - 요청정보에 포함된 전달 파라미터
	 */
	public Counselor getCounselorParam(HttpServletRequest req);
	
	/**
	 * 조홍철
	 * 2019-11-24
	 * 
	 * 이메일 해시 값 파라미터 얻기
	 * 
	 * @param req - 요청정보 객체
	 * @return Counselor - 요청정보에 포함된 전달 파라미터(이메일 해시 값)
	 */
	public Counselor getEmailHashParam(HttpServletRequest req);
	
	/**
	 * 조홍철
	 * 2019-11-22
	 * 
	 * 아이디 중복 검사
	 * 
	 * @param id - 회원 가입 시 상담원이 입력한 아이디
	 * @return int - 1이면 중복, 0이면 중복 아님
	 */
	public int counselorIdCheck(String id);
	
	/**
	 * 조홍철
	 * 2019-11-22
	 * 
	 * 닉네임 중복 검사
	 * 
	 * @param nickname - 회원 가입 시 상담원이 입력한 닉네임
	 * @return int - 1이면 중복, 0이면 중복 아님
	 */
	public int counselorNicknameCheck(String nickname);
	
	/**
	 * 조홍철
	 * 2019-11-23
	 * 
	 * 회원가입
	 * 
	 * @param counselor - 회원가입 정보 객체
	 */
	public void serviceJoin(Counselor counselor);
	
	/**
	 * 조홍철
	 * 2019-11-23
	 * 
	 * 인증 이메일 전송
	 * 
	 * @param counselor - 회원가입 정보 객체
	 */
	public void serviceSendEmail(Counselor counselor);
	
	/**
	 * 조홍철
	 * 2019-11-24
	 * 
	 * 이메일 인증 컬럼을 1로 Update
	 * 1이면 이메일 인증 한것 , 0이면 이메일 인증 안한것
	 * 회원가입시 Default로 0으로 들어가도록 설정 되있음
	 * 
	 * @param counselor - 이메일의 해시코드 값이 담겨있는 상담원 객체
	 */
	public void serviceSetEmailChecked(Counselor counselor);
	
	
	/**
	 * 조홍철
	 * 2019-11-24
	 * 상담원이 입력한 이메일의 해시코드 값을  이용한 상담원 번호 조회
	 * 
	 * @param code - 상담원이 입력한 이메일의 해시코드 값
	 * @return Counselor - 조회된 상담원 객체 결과
	 */
	public Counselor serviceChangeHashCodeToCounselorNo(Counselor counselor);
	
	/**
	 * 조홍철
	 * 2019-11-24
	 * 
	 * 해시코드를 이용한 상담원의 이메일 인증 여부 조회
	 * 
	 * @param counselor - 해시코드가 있는 상담원 객체
	 * @return Counselor - 조회된 상담원 객체 결과
	 */
	public Counselor serviceGetEmailChecked(Counselor counselor);
	
}
