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

@WebServlet("/admin/noticemodify")
public class NoticeModifyController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private NoticeBoardService noticeBoardService = new NoticeBoardServiceImpl();
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		NoticeBoard noticeBoard = noticeBoardService.getBoardno(req);
//		System.out.println("파일번호 가져와라 1 : " + inquiryBoardService.getFileNo(req));
		//게시글 조회
		noticeBoard = noticeBoardService.view(noticeBoard);
		System.out.println("답변하자 "+ noticeBoard);
		req.setAttribute("board", noticeBoard);
		
//		System.out.println("파일번호가져오냐 2: " + inquiryBoardFile);
		req.getRequestDispatcher("/WEB-INF/views/noticeModify.jsp").forward(req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		noticeBoardService.update(req);
		
		resp.sendRedirect("/admin/noticemanage");
	}
}
