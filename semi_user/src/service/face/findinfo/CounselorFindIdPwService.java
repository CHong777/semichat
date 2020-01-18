package service.face.findinfo;

import javax.servlet.http.HttpServletRequest;

import dto.Counselor;

public interface CounselorFindIdPwService {
	
	/**
	 * ������
	 * 2019-11-24
	 * 
	 * ���޹��� �Ķ���͸� ���� Counselor ��ȯ
	 * 
	 * @param req - ���� �Ķ����(email, ��ȭ��ȣ ���)
	 * @return Counselor - email, ��ȭ��ȣ ����� ����ִ� ���� ��ü
	 */
	public Counselor getCounselorParam(HttpServletRequest req);
	
	/**
	 * ������
	 * 2019-11-25
	 * 
	 * ���̵� ã�� 
	 * 
	 * @param counselor - email, ��ȭ��ȣ�� ��� ���� ��ü
	 * @return 0:���� 1:����
	 */
	public int serviceFindId(Counselor counselor);
	
	/**
	 * ������
	 * 2019-11-25
	 * 
	 * ��й�ȣ ã��
	 * 
	 * @param counselor
	 * @return 0:���� 1:����
	 */
	public int serviceFindPw(Counselor counselor);
	
	
	/**
	 * 조홍철
	 * 
	 * 2019-11-27
	 * Id 이메일 전송
	 * 
	 * @param counselor - 아이디 찾기 요청한 상담원 객체
	 */
	public void sendEmailId(Counselor counselor);
	
	/**
	 * 조홍철
	 * 
	 * 2019-11-27
	 * 아이디 가져오는 메서드
	 * 
	 * @param counselor - 아이디 찾기 요청한 상담원 객체
	 * @return Counselor - 찾은 아이디가 담긴 상담원 객체
	 */
	public Counselor getId(Counselor counselor);
	
	/**
	 * 조홍철
	 * 
	 * 2019-11-27
	 * 이메일 찾는 메서드
	 * 
	 * @param counselor - 비밀번호 찾기 요청한 상담원 객체
	 * @return Counselor - 이메일이 담긴 상담원 객체
	 */
	public Counselor getEmailById(Counselor counselor);
	
	/**
	 * 조홍철
	 * 
	 * 2019-11-27
	 * 임시 비밀번호 이메일 전송
	 * 
	 * @param counselor - 비밀번호 찾기 요청한 상담원 객체
	 */
	public void sendEmailPw(Counselor counselor);
	
	/**
	 * 조홍철
	 * 
	 * 2019-11-27
	 * 랜덤 임시 비밀번호 생성 
	 * 
	 * @param len - 생성할 임시 비밀번호 길이 => 8글자로 해야쥥
	 * @return String - 임시 비밀번호
	 */
	public String getRamdomPassword(int len);
	
	/**
	 * 조홍철
	 * 
	 * 2019-11-27
	 * 임시비밀번호로 비밀번호를 변경
	 * 
	 * @param counselor - 임시 비밀번호가 저장될 상담원 객체
	 */
	public void ChangePw(Counselor counselor);

}
