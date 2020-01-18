package controller.manager;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import service.face.manager.CounselorManagerService;
import service.impl.manager.CounselorManagerServiceImpl;

/**
 * Servlet implementation class ManagerFrequentlyReplyDelete
 */
@WebServlet("/manager/replyDelete")
public class ManagerFrequentlyReplyDeleteController extends HttpServlet {

	CounselorManagerService counselorManagerService = new CounselorManagerServiceImpl();

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		String frequentReplyNo = req.getParameter("frequentReplyNo");

		//자주쓰는 답변 삭제 서비스 호출
		counselorManagerService.deleteFrequentReply(frequentReplyNo);

		//자주쓰는 답변 페이지로 redirect
		resp.sendRedirect("/manager/frequentReply");
	}
}
