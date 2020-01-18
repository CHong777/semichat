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

@WebServlet("/mypage/withdrawal")
public class WithdrawalController extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
    public WithdrawalController() {}


    MyInfoService myInfoService = new MyInfoServiceImpl();
    Counselor counselor = new Counselor();
    
    
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    	HttpSession session = req.getSession();
    	
    	counselor.setCounselorId((String)session.getAttribute("counselorid")); 
//    	System.out.println("카운셀러아이디: " + counselor);
    	
    	counselor = myInfoService.getCounselorInfo(counselor);
    	
    	req.getRequestDispatcher("/WEB-INF/views/mypage/counselorWithdrawal.jsp").forward(req, resp);
    }
}
