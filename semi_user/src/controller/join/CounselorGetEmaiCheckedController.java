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
 * Servlet implementation class CounselorGetEmaiCheckedl
 */
@WebServlet("/join/emailChecked")
public class CounselorGetEmaiCheckedController extends HttpServlet {
	private CounselorJoinService counselorJoinService = new CounselorJoinServiceImpl();
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		req.setCharacterEncoding("UTF-8");
		resp.setContentType("text/html;charset=UTF-8");

		//이메일 해시코드를 통한 이메일 인증여부 조회
		String code = req.getParameter("code");
		
		Counselor counselor = new Counselor();
		counselor.setCounselorEmailHash(code);

		Counselor emailChecked = counselorJoinService.serviceGetEmailChecked(counselor);

		req.setAttribute("emailChecked", emailChecked);
		req.getRequestDispatcher("/WEB-INF/views/join/emailChecked.jsp").forward(req, resp);

	}
}
