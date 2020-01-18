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
import service.face.board.NoticeBoardService;
import service.face.mypage.MyWritingInquiryService;
import service.face.mypage.MyWritingNoticeService;
import service.impl.board.NoticeBoardServiceImpl;
import service.impl.mypage.MyWritingInquiryServiceImpl;
import service.impl.mypage.MyWritingNoticeServiceImpl;
import util.Paging;

@WebServlet("/mypage/boardlist")
public class MyBoardListController extends HttpServlet {
	private static final long serialVersionUID = 1L;
   
	private MyWritingInquiryService myWritingInquiryService = new MyWritingInquiryServiceImpl();
	private NoticeBoardService noticeboardService = new NoticeBoardServiceImpl();

	
	public MyBoardListController() {}
    
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    	HttpSession session = req.getSession();
		
		session.getAttribute("counselorid");
//		System.out.println(session.getAttribute("counselorid"));

		//페이징
		Paging paging = myWritingInquiryService.getPaging(req);

//		System.out.println("보드너어어엄 = " + board);
//		System.out.println(req.getParameter("curPage"));
		paging.setCounselorId((String) session.getAttribute("counselorid"));
//		System.out.println("페이징 세션 아이디 넘어가냐?" + paging.getCounselorId());
		//문의사항게시글 목록 조회
		List boardList = myWritingInquiryService.getList(paging);
//		System.out.println(boardList);
//		boardPw = myWritingInquiryService.getBoardpw(boardPw); 
		req.setAttribute("paging", paging);
		
//		System.out.println(paging);

		req.setAttribute("list", boardList);
//		req.setAttribute("list2", boardList2);
//		System.out.println("22342342343" + boardPw);
//		req.setAttribute("boardPw", boardPw);
//		System.out.println(req.getParameter("search"));
		
		if(session.getAttribute("counselorid") != null) {
			req.getRequestDispatcher("/WEB-INF/views/mypage/myBoardList.jsp").forward(req, resp);
			return; 
		}
    	
    	req.getRequestDispatcher("/WEB-INF/views/login/login.jsp").forward(req, resp);
    }

}
