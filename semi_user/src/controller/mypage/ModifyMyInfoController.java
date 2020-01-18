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

@WebServlet("/mypage/modifyinfo")
public class ModifyMyInfoController extends HttpServlet {
	private static final long serialVersionUID = 1L;
  
    public ModifyMyInfoController() {}
    
    MyInfoService myInfoService = new MyInfoServiceImpl();
    Counselor counselor = new Counselor();
    
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    	HttpSession session = req.getSession();
    	
    	counselor.setCounselorId((String)session.getAttribute("counselorid")); 
//    	System.out.println("카운셀러아이디: " + counselor);
    	
    	counselor = myInfoService.getCounselorInfo(counselor);
//    	System.out.println("카운셀러dto 최종: " + counselor);
    	
    	
    	req.getRequestDispatcher("/WEB-INF/views/mypage/modifyMyInfo.jsp").forward(req, resp);
    }
 

	@Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
    	resp.setContentType("text/html;charset=UTF-8");
    	HttpSession session = req.getSession();
    	
    	//사용자가 수정하기위해 입력한 정보 셋팅
    	counselor = myInfoService.getCounselorInfo(req);
    	
    	counselor.setCounselorId((String)session.getAttribute("counselorid")); 
    	
		myInfoService.counselorInfoUpdate(counselor);

		//미리 검사를 하기 때문에 무적권 성공할 수밖에 없음
		req.setAttribute("check", 1);
		//Ajax 응답 페이지로 포워딩
		req.getRequestDispatcher("/WEB-INF/views/mypage/modifyResult.jsp").forward(req, resp);
    	
    }
}
