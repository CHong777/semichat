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

@WebServlet("/admin/noticeview")
public class NoticeViewController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private NoticeBoardService noticeBoardService = new NoticeBoardServiceImpl();
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		NoticeBoard noticeBoard = noticeBoardService.getBoardno(req);
		
		noticeBoard=noticeBoardService.view(noticeBoard);
		noticeBoardService.noticeHit(noticeBoard);
//		System.out.println(noticeBoard);
		req.setAttribute("noticeBoard", noticeBoard);
		
		req.getRequestDispatcher("/WEB-INF/views/noticeView.jsp").forward(req, resp);
	}
}
