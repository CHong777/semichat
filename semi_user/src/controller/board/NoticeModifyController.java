package controller.board;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dto.NoticeBoard;
import service.face.board.NoticeBoardService;
import service.impl.board.NoticeBoardServiceImpl;

@WebServlet("/notice/modify")
public class NoticeModifyController extends HttpServlet {
	
	private NoticeBoardService noticeboardService = new NoticeBoardServiceImpl();
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		System.out.println("0000");
		
		//로그인한 사람의 글이 아니면 중단하고 목록으로 리다이렉트
		if( !noticeboardService.checkId(req) ) {
			System.out.println("1111");
			resp.sendRedirect("/notice/list");
			return;
		}
		
		System.out.println("2222");
		//게시글 번호 파싱
		NoticeBoard noticeboard = noticeboardService.getBoardno(req);
		System.out.println("수정컨트롤 게시글 번호 파싱" + noticeboard );
		//게시글 조회
		noticeboard = noticeboardService.show(noticeboard);
		
		//MODEL로 게시글 전달
		req.setAttribute("noticeboard", noticeboard);
		
		//VIEW 지정
		req.getRequestDispatcher("/WEB-INF/views/board/noticeModify.jsp").forward(req, resp);
		
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		//요청 파라미터 한글 인코딩 설정 : UTF-8
		req.setCharacterEncoding("UTF-8");
		
		req.getParameter("boardtitle");
		System.out.println("제목수정한거 ! " + req.getParameter("boardtitle"));
		
		noticeboardService.update(req);
		
		resp.sendRedirect("/notice/list");
	}
	

}
