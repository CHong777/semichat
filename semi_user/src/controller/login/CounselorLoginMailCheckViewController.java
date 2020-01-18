package controller.login;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dto.Counselor;
import service.face.join.CounselorJoinService;
import service.face.login.CounselorLoginService;
import service.impl.join.CounselorJoinServiceImpl;
import service.impl.login.CounselorLoginServiceImpl;

/**
 * Servlet implementation class CounselorMailCheckViewController
 */
@WebServlet("/login/mailCheckView")
public class CounselorLoginMailCheckViewController extends HttpServlet {

	private CounselorJoinService counselorJoinService = new CounselorJoinServiceImpl();
	private CounselorLoginService counselorLoginService = new CounselorLoginServiceImpl();
	
	@Override
	//메일에서 인증버튼 눌렀을때 동작
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		
		//상담원 해시코드 값 파싱
		Counselor counselor = counselorLoginService.getCounselorParam(req);

		//로그인 사용자 정보 얻어오기
		Counselor pasingCounselor = counselorLoginService.getCounselorByCounselorid(counselor);

		//이메일 Checked 업데이트
		counselorJoinService.serviceSetEmailChecked(pasingCounselor);
		
//		MODEL값으로 지정
		req.setAttribute("pasingCounselor", pasingCounselor);
		req.getRequestDispatcher("/WEB-INF/views/login/loginMailCheckView.jsp")
		.forward(req, resp);
	}
	
	//회원가입 버튼 누를때  동작 Post방식으로 옴
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		//VIEW JSP 지정하고 forwarding
		//이메일 인증 해달라는 VIEW로
		
		//요청 파라미터 얻기
		Counselor counselor = counselorLoginService.getCounselorParam(req);

		System.out.println("요청 파라미터 잘 얻어지나 흠" + counselor);
		
		req.setAttribute("pasingCounselor", counselor);

		req.getRequestDispatcher("/WEB-INF/views/login/loginMailCheckView.jsp?code=" + counselor.getCounselorEmailchecked())
		.forward(req, resp);
	}
	
}
