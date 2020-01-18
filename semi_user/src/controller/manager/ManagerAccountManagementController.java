package controller.manager;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import service.face.manager.CounselorManagerService;
import service.impl.manager.CounselorManagerServiceImpl;
import util.Paging;

/**
 * Servlet implementation class ManagerAccountManagementController
 */
@WebServlet("/manager/accoountmanage")
public class ManagerAccountManagementController extends HttpServlet {

	CounselorManagerService counselorManagerService = new CounselorManagerServiceImpl();
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		HttpSession session = req.getSession();

		session.getAttribute("counselorid");
		session.getAttribute("counselorpassword");

		//페이징
		Paging paging = counselorManagerService.getPaging(req);

		//상담원 목록 조회
		List counselorList = counselorManagerService.getAccountList(paging);
		
		System.out.println("리스트 : " + counselorList);

		req.setAttribute("paging", paging);


		req.setAttribute("list", counselorList);


		if(session.getAttribute("counselorid") != null) {
			req.getRequestDispatcher("/WEB-INF/views/manager/accountManageList.jsp").forward(req, resp);
			return; 
		}

		req.getRequestDispatcher("/WEB-INF/views/login/login.jsp").forward(req, resp);
	}
}
