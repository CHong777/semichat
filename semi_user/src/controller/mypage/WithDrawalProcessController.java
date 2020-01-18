package controller.mypage;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dto.Counselor;
import service.face.mypage.MyInfoService;
import service.impl.mypage.MyInfoServiceImpl;

/**
 * Servlet implementation class WithDrawalProcessController
 */
@WebServlet("/mypage/withdrawalProcess")
public class WithDrawalProcessController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	MyInfoService myInfoService = new MyInfoServiceImpl();
    Counselor counselor = new Counselor();
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    	req.setCharacterEncoding("UTF-8");
    	resp.setContentType("text/html;charset=UTF-8");
    	HttpSession session = req.getSession();
    	
    	counselor = myInfoService.getCounselorInfo(req);
    	
    	counselor.setCounselorId((String)session.getAttribute("counselorid")); 

    	myInfoService.counselorWithdrawalUpdate(counselor);
		
		resp.sendRedirect("/mypage/withdrawal");
	}

}
