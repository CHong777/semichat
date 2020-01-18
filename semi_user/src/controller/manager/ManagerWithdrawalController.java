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
 * Servlet implementation class ManagerWithdrawalController
 */
@WebServlet("/manager/withdrawal")
public class ManagerWithdrawalController extends HttpServlet {

	CounselorManagerService counselorManagerService = new CounselorManagerServiceImpl();
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		String counselorNo = req.getParameter("counselorNo");

		//탈퇴 처리 서비스 호출(7단계)
		// 1. ChatProfile 테이블의 counselorID 컬럼을 null로 업데이트
		counselorManagerService.chatProfileToNull(counselorNo);
		
		// 2. MyClientInfo 테이블 Delete
		counselorManagerService.MyClientInfoDelete(counselorNo);
		
		
		// 3. inquiryBoardFile 테이블 Delete
		counselorManagerService.inquiryBoardFileDelete(counselorNo);
		
		// 4. inquiryBoard  테이블 Delete
		counselorManagerService.inquiryBoardDelete(counselorNo);


		// 5. noticeBoard 테이블 Delete
		counselorManagerService.noticeBoardDelete(counselorNo);
		
		
		// 6. ClientInfo 테이블의 counselorName을 null로 업데이트
		counselorManagerService.clientInfoToNull(counselorNo);
		
		// 7. 드디어 비로소 Counselor  Delete
		counselorManagerService.counselorDelete(counselorNo);
		
		System.out.println("제발 되라");

		//계정관리 페이지로 redirect
		resp.sendRedirect("/manager/accoountmanage");
	
	}
}
