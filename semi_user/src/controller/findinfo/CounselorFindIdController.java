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

@WebServlet("/findinfo/findid")
public class CounselorFindIdController extends HttpServlet {

	private static final long serialVersionUID = 1L;
	private CounselorFindIdPwService findIdPwService = new CounselorFindIdPwServiceImpl();
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		req.setCharacterEncoding("UTF-8");
		resp.setContentType("text/html;charset=UTF-8");

		Counselor counselor = findIdPwService.getCounselorParam(req);
		
		//찾을 계정 여부 검사
		int check = findIdPwService.serviceFindId(counselor);
		
		//찾은 아이디 있으면 메일 전송
		if(check == 1) {
			counselor = findIdPwService.getId(counselor);

			//메일 보내기
			findIdPwService.sendEmailId(counselor);
		}
		
		req.setAttribute("check", check);
		
		req.getRequestDispatcher("/WEB-INF/views/findinfo/findId.jsp").forward(req, resp);
	
	}
}
