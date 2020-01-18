package service.face.login;

import java.util.Properties;

import javax.mail.Address;
import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.servlet.http.HttpServletRequest;

import dto.Counselor;
import util.Gmail;
import util.SHA256;

public interface CounselorLoginService {

	/**
	 * 지재용
	 * 2019-11-23
	 * 
	 * 로그인 정보 파싱
	 * 
	 * @param req - 요청 정보 객체
	 * @return Counselor - 로그인 객체
	 */
	public Counselor getCounselorParam(HttpServletRequest req);
	
	
	/**
	 * 지재용
	 * 2019-11-23
	 * 
	 * 로그인 처리
	 * 
	 * @param counselor - 로그인 정보
	 * @return boolean - true(인증), false(비인증)
	 */
	public boolean login(Counselor counselor);
	
	
	/**
	 * 지재용
	 * 2019-11-23
	 * 
	 * 상담원 정보 가져오기
	 * 
	 * @param counselor - 상담원 아이디를 가진 객체
	 * @return Counselor - 조회된 상담원
	 */
	public Counselor getCounselorByCounselorid(Counselor counselor);
	
}
