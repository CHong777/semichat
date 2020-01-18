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
 * Servlet implementation class CounselorJoinController
 */
@WebServlet("/join/join")
public class CounselorJoinController extends HttpServlet {

	private CounselorJoinService counselorJoinService = new CounselorJoinServiceImpl();
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		//VIEW JSP 지정하고 forwarding
		req.getRequestDispatcher("/WEB-INF/views/join/join.jsp")
		.forward(req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		req.setCharacterEncoding("UTF-8");
		resp.setContentType("text/html;charset=UTF-8");
		
		//요청 파라미터 처리
		Counselor counselor = counselorJoinService.getCounselorParam(req);
		
		//회원 가입
		counselorJoinService.serviceJoin(counselor);
		
		// 이메일 전송하는 컨트롤러로 포워딩
		// req를 유지해야하기 때문
		req.getRequestDispatcher("/join/emailSend")
		.forward(req, resp);
	
	}
	
}
