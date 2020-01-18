package controller.board;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import service.face.board.NoticeBoardService;
import service.impl.board.NoticeBoardServiceImpl;

/**
 * Servlet implementation class NoticeWriteController
 */
@WebServlet("/notice/write")
public class NoticeWriteController extends HttpServlet {
	
	//NoticeBoardService 객체
	private NoticeBoardService noticeboardService = new NoticeBoardServiceImpl();
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		
		HttpSession session = req.getSession();

		session.getAttribute("counselorid");
		session.getAttribute("counselorpassword");
		System.out.println(session.getAttribute("counselorid"));
		
		//로그인 되어있지 않으면 리다이렉트 
		if( req.getSession().getAttribute("login") == null ) {
			resp.sendRedirect("/main");
			return;
		}
		
		//VIEW 지정
		req.getRequestDispatcher("/WEB-INF/views/board/noticeWrite.jsp")
			.forward(req, resp);
		
	}
	
		
		
		
	
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		req.setCharacterEncoding("UTF-8");
		
		noticeboardService.NoticeBoardWrite(req);
		
		//목록으로 리다이렉션
		resp.sendRedirect("/notice/list");
	}
	

}
