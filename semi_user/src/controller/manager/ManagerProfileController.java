package controller.manager;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dto.Counselor;
import service.face.manager.CounselorManagerService;
import service.impl.manager.CounselorManagerServiceImpl;

/**
 * Servlet implementation class ManagerProfileController
 */
@WebServlet("/manager/profile")
public class ManagerProfileController extends HttpServlet {

	CounselorManagerService counselorManagerService = new CounselorManagerServiceImpl();
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		//요청 파라미터 처리(counselorNo)
		Counselor counselor = new Counselor();
		counselor = counselorManagerService.getCounselorProfileParam(req);
		
		//상담원 프로필 정보 얻어오기
		Counselor counselorProfile = counselorManagerService.getCounselorProfileByNo(counselor);
		
		//Model 값 설정
		req.setAttribute("counselorProfile", counselorProfile);
		req.getRequestDispatcher("/WEB-INF/views/manager/counselorProfile.jsp").forward(req, resp);
	}
}
