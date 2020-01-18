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
 * Servlet implementation class ManagerApproveController
 */
@WebServlet("/manager/approve")
public class ManagerApproveController extends HttpServlet {
	
	CounselorManagerService counselorManagerService = new CounselorManagerServiceImpl();
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		String counselorNo = req.getParameter("counselorNo");
		
		//승인 처리 서비스 호출
		counselorManagerService.approveByManager(counselorNo);
		
		//계정관리 페이지로 redirect
		resp.sendRedirect("/manager/accoountmanage");
	}
}
