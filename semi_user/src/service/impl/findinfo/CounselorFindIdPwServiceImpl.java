package service.impl.findinfo;

import java.util.Properties;

import javax.mail.Address;
import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.servlet.http.HttpServletRequest;

import dao.face.findinfo.CounselorFindIdPwDao;
import dao.impl.findinfo.CounselorFindIdPwDaoImpl;
import dto.Counselor;
import service.face.findinfo.CounselorFindIdPwService;
import util.Gmail;
import util.SHA256;

public class CounselorFindIdPwServiceImpl implements CounselorFindIdPwService {

	private CounselorFindIdPwDao findIdPwDao = new CounselorFindIdPwDaoImpl();
	
	@Override
	public Counselor getCounselorParam(HttpServletRequest req) {
		
		Counselor counselor = new Counselor();
		
//		private int counselorNo; //	���� ��ȣ(�⺻Ű)
		String counselorName = req.getParameter("counselorName");   //�̸�
		String counselorId = req.getParameter("counselorId");	    //���̵�
//		private String counselorPassword; // ��й�ȣ
//		private String counselorNickname; // �г���
		String counselorEmail = req.getParameter("counselorEmail"); // �̸���
		String counselorPhoneNumber = req.getParameter("counselorPhoneNumber");// �޴�����ȣ
//		private String counselorSignDate; // ���� ��¥
//		private String counselorPosition; // ���
//		private int counselorMarketingAgree; // ������ ����
//		private int counselorEmailChecked; // �̸��� ���� Ȯ��
//		private int managerConfirm; //��� �Ŵ��� ���� Ȯ��
//		private int corporationNo; //ȸ���ȣ(ȸ�����̺� �ܷ�Ű)
//		private String counselorMailHash; // �̸��� �ؽ� ��
		
		if(counselorName != null && !"".equals(counselorName))
			counselor.setCounselorName(counselorName);
		
		if(counselorId != null && !"".equals(counselorId))
			counselor.setCounselorId(counselorId);
		
		if(counselorEmail != null && !"".equals(counselorEmail))
			counselor.setCounselorEmail(counselorEmail);
		
		if(counselorPhoneNumber != null && !"".equals(counselorPhoneNumber))
			counselor.setCounselorPhonenumber(counselorPhoneNumber);
		
		
		return counselor;
	}

	@Override
	public int serviceFindId(Counselor counselor) {
		
		int isSuccess = findIdPwDao.findId(counselor);
		
		return isSuccess;
	}

	@Override
	public int serviceFindPw(Counselor counselor) {

		int isSuccess = findIdPwDao.findPw(counselor);
		
		return isSuccess;
	}

	@Override
	public void sendEmailId(Counselor counselor) {
		
		 // 사용자에게 보낼 메시지를 기입합니다.
	      String from = "cho97216@gmail.com";
	      
	      String originId = counselor.getCounselorId();
	      
	      //보여줄 Id
	      String showId = originId.substring(0,3);
	      System.out.println(showId);
	      
	      //글자 *로 바꾸기
	      for(int i = 3; i<originId.length(); i++) {
	    	  showId +="*";
	      }
	      
	      
	      System.out.println("아이디 찾기 : " + showId);
	      

	      String subject = "SemiChat 아이디 찾기 입니다.";
	      String content = "고객님의 아이디는 : " + showId + " 입니다.";

	      // SMTP에 접속하기 위한 정보를 기입합니다.
	      Properties p = new Properties();
	      p.put("mail.smtp.user", from);
	      p.put("mail.smtp.host", "smtp.googlemail.com");
	      p.put("mail.smtp.port", "465");
	      p.put("mail.smtp.starttls.enable", "true");
	      p.put("mail.smtp.auth", "true");
	      p.put("mail.smtp.debug", "true");
	      p.put("mail.smtp.socketFactory.port", "465");
	      p.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
	      p.put("mail.smtp.socketFactory.fallback", "false");

	      try{
	         Authenticator auth = new Gmail();
	         Session ses = Session.getInstance(p, auth);
	         ses.setDebug(true);
	         MimeMessage msg = new MimeMessage(ses); 
	         msg.setSubject(subject);
	         Address fromAddr = new InternetAddress(from);
	         msg.setFrom(fromAddr);
	         Address toAddr = new InternetAddress(counselor.getCounselorEmail());
	         msg.addRecipient(Message.RecipientType.TO, toAddr);
	         msg.setContent(content, "text/html;charset=UTF-8");
	         Transport.send(msg);
	      } catch(Exception e){
	         e.printStackTrace();
	      }
		
	}

	@Override
	public Counselor getId(Counselor counselor) {
		return findIdPwDao.selectIdByPhone(counselor);
	}

	@Override
	public Counselor getEmailById(Counselor counselor) {
		return findIdPwDao.selectEmailById(counselor);
	}

	@Override
	public void sendEmailPw(Counselor counselor) {
		
		 // 사용자에게 보낼 메시지를 기입합니다.
	      String from = "cho97216@gmail.com";
	      
	      //임시 비밀번호 생성
	      String pwTemp = getRamdomPassword(8);
	      
	      //임시 비밀번호 객체에 담기
	      counselor.setCounselorPassword(pwTemp);
	      
	      //비밀번호 Update
	      ChangePw(counselor);
	      
	      System.out.println("임시 비번 뭐냐? " + pwTemp);
	      
	      String subject = "SemiChat 비밀번호 찾기 입니다.";
	      String content = "고객님의 임시 비밀번호는 " + pwTemp +  " 입니다.";

	      // SMTP에 접속하기 위한 정보를 기입합니다.
	      Properties p = new Properties();
	      p.put("mail.smtp.user", from);
	      p.put("mail.smtp.host", "smtp.googlemail.com");
	      p.put("mail.smtp.port", "465");
	      p.put("mail.smtp.starttls.enable", "true");
	      p.put("mail.smtp.auth", "true");
	      p.put("mail.smtp.debug", "true");
	      p.put("mail.smtp.socketFactory.port", "465");
	      p.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
	      p.put("mail.smtp.socketFactory.fallback", "false");

	      try{
	         Authenticator auth = new Gmail();
	         Session ses = Session.getInstance(p, auth);
	         ses.setDebug(true);
	         MimeMessage msg = new MimeMessage(ses); 
	         msg.setSubject(subject);
	         Address fromAddr = new InternetAddress(from);
	         msg.setFrom(fromAddr);
	         Address toAddr = new InternetAddress(counselor.getCounselorEmail());
	         msg.addRecipient(Message.RecipientType.TO, toAddr);
	         msg.setContent(content, "text/html;charset=UTF-8");
	         Transport.send(msg);
	      } catch(Exception e){
	         e.printStackTrace();
	      }
	}

	@Override
	public String getRamdomPassword(int len) {
		char[] charSet = new char[] { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 
				'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 
				'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z' }; 
		int idx = 0;
		StringBuffer sb = new StringBuffer(); 
		for (int i = 0; i < len; i++) 
		{ 
			idx = (int) (charSet.length * Math.random()); 
			// 36 * 생성된 난수를 Int로 추출 (소숫점제거) 
			sb.append(charSet[idx]); }
		return sb.toString();
	}

	@Override
	public void ChangePw(Counselor counselor) {

		findIdPwDao.updatePw(counselor);
	}
}
