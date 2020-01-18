package util;

import java.security.MessageDigest;

public class SHA256 {
	
	/**
	 * 조홍철
	 * 2019-11-23
	 * 
	 * SHA256를 이용해 입력한 이메일을 해시값으로 변환
	 * 이메일에 해시값을 적용해서 이용하기 위함
	 * 
	 * @param input - 상담원의 이메일
	 * @return String - 변경된 해시 값
	 */
	public static String getSHA256(String input) {
		StringBuffer result = new StringBuffer();
		try {
			MessageDigest digest = MessageDigest.getInstance("SHA-256");
			byte[] salt = "Hello! This is Salt.".getBytes();
			digest.reset();
			digest.update(salt);
			byte[] chars = digest.digest(input.getBytes("UTF-8"));
			for(int i=0; i<chars.length; i++) {
				String hex = Integer.toHexString(0xff & chars[i]);
					if(hex.length() == 1) result.append("0");
					result.append(hex);
			}
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		return result.toString();
	}
}