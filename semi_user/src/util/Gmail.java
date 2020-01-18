package util;

import javax.mail.Authenticator;
import javax.mail.PasswordAuthentication;

public class Gmail extends Authenticator {
		
	/**
	 * 조홍철
	 * 2019-11-23
	 * 
	 * Gmail SMTP를 사용하기위한  구글 계정 인증
	 * 자신의 Gmail 계정, 비밀번호를 넣어줘야함
	 */
	@Override
	protected PasswordAuthentication getPasswordAuthentication() {
        return new PasswordAuthentication("semichatchat@gmail.com","cho12345678!");		
	}
}