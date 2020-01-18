package controller.findinfo;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dto.Counselor;
import service.face.findinfo.CounselorFindIdPwService;
import service.impl.findinfo.CounselorFindIdPwServiceImpl;

@WebServlet("/findinfo/findpw")
public class CounselorFindPwController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private CounselorFindIdPwService findIdPwService = new CounselorFindIdPwServiceImpl();
	
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		resp.setContentType("text/html;charset=UTF-8");

		Counselor counselor = findIdPwService.getCounselorParam(req);
		
//		System.out.println("비번 찾냐? :" + counselor);
		
		//찾을 계정 여부 검사
		int check = findIdPwService.serviceFindPw(counselor);
		
		System.out.println("비번찾냐? :" + counselor);
		
		//찾은 비밀번호 있으면 메일 전송
		if(check == 1) {
			counselor = findIdPwService.getEmailById(counselor);

			System.out.println("이멜 출력되냐 ? " + counselor);
			//메일 보내기
			findIdPwService.sendEmailPw(counselor);
		}
		
		req.setAttribute("check", check);
		
		req.getRequestDispatcher("/WEB-INF/views/findinfo/findPw.jsp").forward(req, resp);
	}
}
