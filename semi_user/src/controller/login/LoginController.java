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

@WebServlet("/login/login")
public class LoginController extends HttpServlet {
	
	//CounselorLoginService 객체
	private CounselorLoginService counselorLoginService = new CounselorLoginServiceImpl();
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		//로그인 페이지 view 지정
		req.getRequestDispatcher("/WEB-INF/views/login/login.jsp").forward(req, resp);
			
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		System.out.println("Post가 불리는지 보자");
		
		//로그인 정보 얻어오기(id, pw)
		Counselor counselor = counselorLoginService.getCounselorParam(req);
		
		System.out.println(counselor);
				
		//로그인 인증
		boolean login = counselorLoginService.login(counselor);
		
		if(login) {
			
			//로그인 사용자 정보 얻어오기
			counselor = counselorLoginService.getCounselorByCounselorid(counselor);
		
			//세션 정보 저장
			req.getSession().setAttribute("login", login);
			req.getSession().setAttribute("counselorid", counselor.getCounselorId());
			//비밀번호를 세션에 넣어줘도 되나,,,? 
			req.getSession().setAttribute("counselorpassword", counselor.getCounselorPassword());
			req.getSession().setAttribute("counselornickname", counselor.getCounselorNickname());
			
			//홍철 추가, 12-04
			req.getSession().setAttribute("counselorPosition", counselor.getCounselorPosition());
			req.getSession().setAttribute("managerConfirm", counselor.getManagerConfirm());
			
			
			//--- 홍철 추가  메일 체크 여부로 main or mail로 이동시키기 위해---
			
			//이메일 인증 안한 경우, 메일인증 요청 페이지로 포워딩
			if(counselor.getCounselorEmailchecked() == 0) {
				req.getRequestDispatcher("/login/mailCheckView")
				.forward(req, resp);
			}
			 // 
			else {
				//로그인 성공시 
				resp.sendRedirect("/main.jsp");
			}
			
		} else {
			
			//로그인 실패시
			resp.sendRedirect("/login/login");
			
		}
		
			
		
	}
	
}
