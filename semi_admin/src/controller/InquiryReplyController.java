package controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dto.InquiryBoard;
import dto.InquiryBoardFile;
import service.face.InquiryBoardService;
import service.impl.InquiryBoardServiceImpl;

/**
 * Servlet implementation class InquiryReplyController
 */
@WebServlet("/admin/reply")
public class InquiryReplyController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private InquiryBoardService inquiryBoardService = new InquiryBoardServiceImpl();

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		InquiryBoard inquiryBoard = inquiryBoardService.getBoardno(req);
		InquiryBoardFile inquiryBoardFile = inquiryBoardService.getFileInfo(inquiryBoard); 
//		System.out.println("파일번호 가져와라 1 : " + inquiryBoardService.getFileNo(req));
		//게시글 조회
		inquiryBoard = inquiryBoardService.view(inquiryBoard);
		System.out.println("답변하자 "+ inquiryBoard);
		req.setAttribute("board", inquiryBoard);
		
		req.setAttribute("file", inquiryBoardFile);
		
//		System.out.println("파일번호가져오냐 2: " + inquiryBoardFile);
		req.getRequestDispatcher("/WEB-INF/views/inquiryReply.jsp").forward(req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		inquiryBoardService.update(req);
		
		resp.sendRedirect("/admin/inquirymanage");
	}
}
