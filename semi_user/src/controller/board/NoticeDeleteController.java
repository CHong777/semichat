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

@WebServlet("/notice/delete")
public class NoticeDeleteController extends HttpServlet {
	
	private NoticeBoardService noticeboardService = new NoticeBoardServiceImpl();
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		NoticeBoard noticeboard = noticeboardService.getBoardno(req);
		
		noticeboardService.delete(noticeboard);
		
		//목록으로 리다이렉트
		resp.sendRedirect("/notice/list");
		
	}

}
