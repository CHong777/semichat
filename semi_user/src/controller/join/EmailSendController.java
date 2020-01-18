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
 * Servlet implementation class EmailSendController
 */
@WebServlet("/join/emailSend")
public class EmailSendController extends HttpServlet {
	private CounselorJoinService counselorJoinService = new CounselorJoinServiceImpl();

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		String code = req.getParameter("code");
		
		//요청 파라미터 처리 - counselor객체를 통한 입력정보 가져오기
		Counselor counselor = counselorJoinService.getCounselorParam(req);
		
		//이메일 전송 서비스 호출
		counselorJoinService.serviceSendEmail(counselor);
		
		req.setAttribute("code", code);
		
		// 이메일 전송하는 컨트롤러로 포워딩
		// req를 유지해야하기 때문
		req.getRequestDispatcher("/join/mailCheckView?code=" + code)
		.forward(req, resp);
		
	}
}
