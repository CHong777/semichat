package controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dto.AdminBoard;
import dto.InquiryBoardFile;
import service.face.BoardService;
import service.impl.BoardServiceImpl;

@WebServlet("/admin/view")
public class BoardViewController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private BoardService boardService = new BoardServiceImpl();
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		AdminBoard adminBoard = boardService.getBoardno(req);
//		System.out.println(adminBoard);
		
		adminBoard = boardService.view(adminBoard);
		
		System.out.println(adminBoard);
		if(adminBoard.getBoardSorting().equals("문의사항")) {
			boardService.inquiryHit(adminBoard);
			InquiryBoardFile inquiryBoardfile = boardService.getFileInfo(adminBoard);
			req.setAttribute("file", inquiryBoardfile);
		} else {
			boardService.noticeHit(adminBoard);
		}
		req.setAttribute("adminBoard", adminBoard);
		
		req.getRequestDispatcher("/WEB-INF/views/boardView.jsp").forward(req, resp);

	}
}
