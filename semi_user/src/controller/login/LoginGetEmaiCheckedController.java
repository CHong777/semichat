package controller.login;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dto.Counselor;
import service.face.join.CounselorJoinService;
import service.face.login.CounselorLoginService;
import service.impl.join.CounselorJoinServiceImpl;
import service.impl.login.CounselorLoginServiceImpl;

/**
 * Servlet implementation class CounselorGetEmaiCheckedl
 */
@WebServlet("/login/emailChecked")
public class LoginGetEmaiCheckedController extends HttpServlet {
	private CounselorLoginService counselorLoginService = new CounselorLoginServiceImpl();
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		req.setCharacterEncoding("UTF-8");
		resp.setContentType("text/html;charset=UTF-8");
		
		String counselorid = req.getParameter("counselorid");
		
		System.out.println("세션값 잘 가여오나? 마무리 : " + counselorid);

		Counselor counselor = new Counselor();
		counselor.setCounselorId(counselorid);

		Counselor emailChecked = counselorLoginService.getCounselorByCounselorid(counselor);
		
		System.out.println("이메일 체크 잘 가져옴? 로그인 : " + emailChecked);

		req.setAttribute("emailChecked", emailChecked);
		req.getRequestDispatcher("/WEB-INF/views/login/loginEmailChecked.jsp").forward(req, resp);


	}
}
