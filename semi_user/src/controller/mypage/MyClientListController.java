package controller.mypage;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dto.InquiryBoard;
import service.face.mypage.MyClientListService;
import service.impl.mypage.MyClientListServiceImpl;
import util.Paging;

@WebServlet("/mypage/myclient")
public class MyClientListController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	MyClientListService myClientListService = new MyClientListServiceImpl();
    
	public MyClientListController() {}
    
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    	HttpSession session = req.getSession();
		
		session.getAttribute("counselorid");
//		System.out.println(session.getAttribute("counselorid"));

		
		
	
		//페이징
		Paging paging = myClientListService.getPaging(req);
		
//		System.out.println(req.getParameter("curPage"));
		paging.setCounselorId((String) session.getAttribute("counselorid"));
//		System.out.println("페이징 세션 아이디 넘어가냐?" + paging.getCounselorId());
		//문의사항게시글 목록 조회
		List clientList = myClientListService.getList(paging);
		//공지사항게시글 목록 조회 
		req.setAttribute("paging", paging);
		
		System.out.println("클라이언트리스트 : " + clientList);
	
		req.setAttribute("list", clientList);
//		System.out.println(req.getParameter("search"));
		
		if(session.getAttribute("counselorid") != null) {
			req.getRequestDispatcher("/WEB-INF/views/mypage/myClientList.jsp").forward(req, resp);
			return; 
		}
    	
    	req.getRequestDispatcher("/WEB-INF/views/login/login.jsp").forward(req, resp);
    }
    
}
