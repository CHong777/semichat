package controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dto.InquiryBoard;
import dto.InquiryBoardFile;
import service.face.InquiryBoardService;
import service.impl.InquiryBoardServiceImpl;

@WebServlet("/admin/inquiryview")
public class InquiryViewController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private InquiryBoardService inquiryBoardService = new InquiryBoardServiceImpl();
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {	
		// 게시판 번호 파싱
		InquiryBoard board = inquiryBoardService.getBoardno(req);
				
		//게시글 조회
		board = inquiryBoardService.view(board);		
		inquiryBoardService.hit(board);
		InquiryBoardFile inquiryBoardfile = inquiryBoardService.getFileInfo(board);
		
		//VIEW 지정
		req.setAttribute("board",board);
		req.setAttribute("file", inquiryBoardfile);
//		System.out.println("sss "+session.getAttribute("counselorid"));
		InquiryBoardFile downFile = inquiryBoardService.getFile(req); 
	
		req.getRequestDispatcher("/WEB-INF/views/inquiryView.jsp").forward(req, resp); 
	}

}
