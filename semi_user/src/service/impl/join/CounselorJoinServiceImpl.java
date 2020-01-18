package service.impl.join;

import java.util.Properties;

import javax.mail.Address;
import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.servlet.http.HttpServletRequest;

import dao.face.join.CounselorJoinDao;
import dao.impl.join.CounselorJoinDaoImpl;
import dto.Counselor;
import service.face.join.CounselorJoinService;
import util.Gmail;
import util.SHA256;

public class CounselorJoinServiceImpl implements CounselorJoinService {

   private CounselorJoinDao counselorJoinDao = new CounselorJoinDaoImpl();
   
   @Override
   public Counselor getCounselorParam(HttpServletRequest req) {
      
      //해시 값 얻기 위한 객체
      SHA256 sha256 = new SHA256();
      
      //전달 파라미터 얻기
      String counselorId = req.getParameter("id");
      String counselorPassword = req.getParameter("password");
      String counselorName = req.getParameter("name");
      String counselorNickname = req.getParameter("nickname");
      String counselorEmail = req.getParameter("email");
      String counselorPhonenumber = req.getParameter("phoneNumber");
      String enterprise = req.getParameter("enterprise");
      String privacy = req.getParameter("privacyInfoAgree");
      String marketing = req.getParameter("marketingAgree");
      String counselorEmailHash = sha256.getSHA256(counselorEmail);

      
      //요청파라미터 enterprise를 파싱한다
      int corporationNo = 0;
      if( enterprise!=null && !"".equals(enterprise) ) {
         corporationNo = Integer.parseInt(enterprise);
      }
      
      //요청파라미터 marketingAgree를 파싱한다
      // 1이면 수락 null이면 0으로 만들어주어서 수락 안할걸로 변환
      if(marketing == null)
         marketing = "0";
      
      int counselorMarketingagree = 0;
      if( marketing!=null && !"".equals(marketing) ) {
         counselorMarketingagree = Integer.parseInt(marketing);
      }
      
      
      // 14개 컬럼중 9개만 셋팅
      // 5개는 셋팅 안함
      
      // counselorNo => 자동증가
      // counselorSignDate => sysdate
      // counselorPosition => "counselor"이 기본 값
      // counselorEmailCheck => 0으로 기본값 , 이메일 체크 안된 것
      // managerConfirm => 0으로 기본값, 승인 안된 것
      
      //전달 파라미터를 DTO(모델)에 담기
      Counselor counselor = new Counselor();
      
      counselor.setCounselorId(counselorId);
      counselor.setCounselorPassword(counselorPassword);
      counselor.setCounselorName(counselorName);
      counselor.setCounselorNickname(counselorNickname);
      counselor.setCounselorEmail(counselorEmail);
      counselor.setCounselorPhonenumber(counselorPhonenumber);
      counselor.setCorporationNo(corporationNo);
      counselor.setCounselorMarketingagree(counselorMarketingagree);
      counselor.setCounselorEmailHash(counselorEmailHash);

      //객체 반환
      return counselor;
   }
   
   @Override
   public Counselor getEmailHashParam(HttpServletRequest req) {

      String code = req.getParameter("code");
      
      //전달 파라미터를 DTO(모델)에 담기
      Counselor counselor = new Counselor();
      counselor.setCounselorEmailHash(code);
      
      //객체 반환
      return counselor;
   }
   
   @Override
   public int counselorIdCheck(String id) {
      return counselorJoinDao.selectIdForCheck(id);
   }

   @Override
   public int counselorNicknameCheck(String nickname) {
      return counselorJoinDao.selectNicknameForCheck(nickname);
   }

   @Override
   public void serviceJoin(Counselor counselor) {
      counselorJoinDao.join(counselor);
   }

   @Override
   public void serviceSendEmail(Counselor counselor) {
      // 사용자에게 보낼 메시지를 기입합니다.
      String host = "http://192.168.10.241:8090/";
      String from = "semiChat@gmail.com";

      Counselor toObject = counselorJoinDao.selectEmailByCounselorNo(counselor);

      String subject = "SemiChat 회원가입을 위한 이메일 인증 입니다.";
      String content = "다음 링크에 접속하여 이메일 확인을 진행하세요. " +
            "<a href='" + host + "join/mailCheckView?code=" + new SHA256().getSHA256(toObject.getCounselorEmail()) + "'><br>이메일 인증하기</a>";

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
         Address toAddr = new InternetAddress(toObject.getCounselorEmail());
         msg.addRecipient(Message.RecipientType.TO, toAddr);
         msg.setContent(content, "text/html;charset=UTF-8");
         Transport.send(msg);
      } catch(Exception e){
         e.printStackTrace();
      }
   }

   @Override
   public void serviceSetEmailChecked(Counselor counselor) {
      //DB에 업데이트
      counselorJoinDao.updateEmailChecked(counselor);
   }

   @Override
   public Counselor serviceChangeHashCodeToCounselorNo(Counselor counselor) {
      //이메일 해시코드를 상담원번호로 변환(Update에서 where절을 기본키로하는게 맞을거 같아서 추가)
      return counselorJoinDao.selectCounselorNoByCounselorHashCode(counselor);
   }

   @Override
   public Counselor serviceGetEmailChecked(Counselor counselor) {
      return counselorJoinDao.selectEmailChecked(counselor);
   }

}