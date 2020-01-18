package controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dto.NoticeBoard;
import service.face.NoticeBoardService;
import service.impl.NoticeBoardServiceImpl;

@WebServlet("/admin/noticedelete")
public class NoticeDeleteController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	public NoticeDeleteController() { }
	
	NoticeBoardService noticeBoardService = new NoticeBoardServiceImpl();

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		NoticeBoard noticeBoard = noticeBoardService.getBoardno(req);
		System.out.println(noticeBoard);
		
		noticeBoard = noticeBoardService.view(noticeBoard);
		
		req.setAttribute("board", noticeBoard);

		noticeBoardService.delete(noticeBoard);
		
		resp.sendRedirect("/admin/noticemanage");		
	}
}
