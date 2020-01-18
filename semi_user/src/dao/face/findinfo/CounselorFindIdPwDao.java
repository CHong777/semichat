package dao.face.findinfo;

import dto.Counselor;

public interface CounselorFindIdPwDao {
	
	/**
	 * ������
	 * 2019-11-25
	 * 
	 * ���̵� ã��
	 * 
	 * @param counselor - email, ��ȭ��ȣ�� ��� ���� ��ü
	 * @return 0:���� 1:����
	 */
	public int findId(Counselor counselor);
	
	/**
	 * ������
	 * 2019-11-25
	 * 
	 * ��й�ȣ ã��
	 * 
	 * @param counselor - ���̵�, �̸��� ��� ���� ��ü
	 * @return 0:���� 1:����
	 */
	public int findPw(Counselor counselor);
	
	/**
	 * 2019-11-27
	 * 조홍철
	 * 
	 * @param counselor - 아이디 찾기 요청한 상담원 객체
	 * @return Counselor - 찾은 아이디가 있는 상담원 객체
	 */
	public Counselor selectIdByPhone(Counselor counselor);
	
	/**
	 * 2019-11-27
	 * 조홍철
	 * 
	 * @param counselor - 비밀번호 찾기 요청한 상담원 객체
	 * @return Counselor - 이메일이 있는 상담원 객체
	 */
	public Counselor selectEmailById(Counselor counselor);
	
	/**
	 * 2019-11-27
	 * 조홍철
	 * 
	 * @param counselor - 임시비밀번호로 변경할 상담원 객체
	 */
	public void updatePw(Counselor counselor);
}
