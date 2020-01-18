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

@WebServlet("/admin/boarddelete")
public class BoardDeleteController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	public BoardDeleteController() { }
	
	BoardService boardService = new BoardServiceImpl();
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		AdminBoard deleteBoard = boardService.getBoardno(req);
		System.out.println(deleteBoard);
		
		deleteBoard = boardService.view(deleteBoard);
		
		req.setAttribute("board", deleteBoard);

		if(deleteBoard.getBoardSorting().equals("문의사항")) {
			InquiryBoardFile file = boardService.getFileInfo(deleteBoard);
			boardService.delete(file);
			boardService.inquiryDelete(deleteBoard);
		} else {
			boardService.noticeDelete(deleteBoard);			
		}
		
		resp.sendRedirect("/admin/boardmanage");		
	}
}
