package dao.face.login;

import dto.Counselor;

public interface CounselorLoginDao {
	
	/**
	 * 지재용
	 * 2019-11-23
	 * 
	 * counselorid와 counselorpassword가 일치하는 회원수 조회
	 * 
	 * @param counselor - counselorid와 counselorpw를 가진 회원
	 * @return int - 1(존재하는 회원), 0(존재하지 않는 회원), -1(에러)
	 */
	public int selectCntCounselorByCounseloridAndCounselorpassword(Counselor counselor);
	
	
	/**
	 * userid로 유저정보 조회
	 * 
	 * @param counselor - 조회할 상담원
	 * @return Counselor - 조회된 결과
	 */
	public Counselor selectCounselorByCounselorid(Counselor counselor);
	
	

}
