package controller.join;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dto.Counselor;
import service.face.join.CounselorJoinService;
import service.impl.join.CounselorJoinServiceImpl;

/**
 * Servlet implementation class CounselorMailCheckViewController
 */
@WebServlet("/join/mailCheckView")
public class CounselorMailCheckViewController extends HttpServlet {

	private CounselorJoinService counselorJoinService = new CounselorJoinServiceImpl();
	
	@Override
	//메일에서 인증버튼 눌렀을때 동작
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		
		//상담원 해시코드 값 파싱
		Counselor counselor = counselorJoinService.getEmailHashParam(req);

		//해시코드를 통한 상담원 번호 담긴 객체 얻어오기
		Counselor pasingCounselor = counselorJoinService.serviceChangeHashCodeToCounselorNo(counselor);

		//이메일 Checked 업데이트
		counselorJoinService.serviceSetEmailChecked(pasingCounselor);
		
//		MODEL값으로 지정
		req.setAttribute("pasingCounselor", pasingCounselor);
		req.getRequestDispatcher("/WEB-INF/views/join/mailCheckView.jsp")
		.forward(req, resp);
	}
	
	//회원가입 버튼 누를때  동작 Post방식으로 옴
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		//VIEW JSP 지정하고 forwarding
		//이메일 인증 해달라는 VIEW로
		
		//요청 파라미터 얻기
		Counselor counselor = counselorJoinService.getCounselorParam(req);

		req.setAttribute("pasingCounselor", counselor);

		req.getRequestDispatcher("/WEB-INF/views/join/mailCheckView.jsp")
		.forward(req, resp);
	}
	
}
