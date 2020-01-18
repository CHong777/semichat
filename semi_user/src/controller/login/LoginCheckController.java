package controller.login;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dto.Counselor;
import service.face.login.CounselorLoginService;
import service.impl.login.CounselorLoginServiceImpl;

/**
 * Servlet implementation class LoginCheckController
 */
@WebServlet("/login/loginCheck")
public class LoginCheckController extends HttpServlet {

	private CounselorLoginService counselorLoginService = new CounselorLoginServiceImpl();
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		req.setCharacterEncoding("UTF-8");
		resp.setContentType("text/html;charset=UTF-8");

		Counselor counselor = new Counselor();
		
		String counselorid = req.getParameter("counselorid");
		String counselorpassword = req.getParameter("counselorpassword");
		
		counselor.setCounselorId(counselorid);
		counselor.setCounselorPassword(counselorpassword);
		
		System.out.println(counselor + "입니다. 아이디체크");
		
		Boolean check = counselorLoginService.login(counselor);
		req.setAttribute("check", check);
		
		req.getRequestDispatcher("/WEB-INF/views/login/loginCheck.jsp").forward(req, resp);
		
	}
}
