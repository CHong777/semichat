package controller.join;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import service.face.join.CounselorJoinService;
import service.impl.join.CounselorJoinServiceImpl;

/**
 * Servlet implementation class CounselorNickCheckController
 */
@WebServlet("/join/nickCheck")
public class CounselorNickCheckController extends HttpServlet {
private CounselorJoinService counselorJoinService = new CounselorJoinServiceImpl();
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		req.setCharacterEncoding("UTF-8");
		resp.setContentType("text/html;charset=UTF-8");

		String nickname = req.getParameter("nickname");
		int check = counselorJoinService.counselorNicknameCheck(nickname);
		req.setAttribute("check", check);
		
		req.getRequestDispatcher("/WEB-INF/views/join/nicknameCheck.jsp").forward(req, resp);
		
	}
}
