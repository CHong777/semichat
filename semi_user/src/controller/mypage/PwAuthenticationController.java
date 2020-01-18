package controller.mypage;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dto.ClientInfo;
import dto.Counselor;
import service.face.mypage.MyInfoService;
import service.impl.mypage.MyInfoServiceImpl;

@WebServlet("/pw/authentication")
public class PwAuthenticationController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	public PwAuthenticationController() {}
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		req.getRequestDispatcher("/WEB-INF/views/mypage/pwAuthentication.jsp").forward(req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		HttpSession session = req.getSession();
		
		MyInfoService myInfoService = new MyInfoServiceImpl();
//		Counselor counselor = myInfoService.getCounselorParam(req);
		
		//세션값에 저장되어있는 couselorid 받아오기
		String id = (String) session.getAttribute("counselorid");
		
		//패스워드 비교할 때 필요한 method호출,저장
		String check = myInfoService.getPassword(id);
		
		//view에서 파라미터 얻기
		String checkPass = req.getParameter("pass");
		
		System.out.println("받아온 비번 : " + checkPass);
		
		//비번 일치하면
		if(check.equals(checkPass)) {
//			System.out.println("먼대");
			req.setAttribute("check", 1);
		}
		else {
//			System.out.println("머냐고");
			req.setAttribute("check", 0);
		}
	
		req.getRequestDispatcher("/WEB-INF/views/mypage/pwCheckResult.jsp").forward(req, resp);
	}
	
}
