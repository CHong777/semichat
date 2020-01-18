package controller.mypage;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dto.NoticeBoard;
import service.face.board.NoticeBoardService;
import service.impl.board.NoticeBoardServiceImpl;

@WebServlet("/mypage/noticeshow")
public class MyNoticeShowController extends HttpServlet {

	private static final long serialVersionUID = 1L;
	
	private NoticeBoardService noticeboardService = new NoticeBoardServiceImpl();
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		HttpSession session = req.getSession();
		
		//게시글 번호 파싱
		NoticeBoard noticeboard = noticeboardService.getBoardno(req);
		System.out.println("no " + noticeboard );
		//게시글 조회
		noticeboard = noticeboardService.show(noticeboard);
		System.out.println("no2 " + noticeboard );
		
		//MODEL로 게시글 전달
		req.setAttribute("noticeboard", noticeboard);
		req.setAttribute("counselorid", session.getAttribute("counselorid"));
		
		
		System.out.println(session.getAttribute("counselorid"));
		//VIEW 지정
		req.getRequestDispatcher("/WEB-INF/views/mypage/myNoticeShow.jsp").forward(req, resp);
		
	}

	
}
