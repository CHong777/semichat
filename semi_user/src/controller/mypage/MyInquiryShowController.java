package controller.mypage;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dto.InquiryBoard;
import dto.InquiryBoardFile;
import service.face.board.InquiryBoardService;
import service.impl.board.InquiryBoardServiceImpl;



/**
 * Servlet implementation class InquiryShowController
 */
@WebServlet("/mypage/inquiryshow")
public class MyInquiryShowController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private InquiryBoardService inquiryBoardService = new InquiryBoardServiceImpl();
	
	
	public MyInquiryShowController() {}
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		HttpSession session = req.getSession();
		
		// 게시판 번호 파싱
		InquiryBoard board = inquiryBoardService.getBoardno(req);
				
		//게시글 조회
		board = inquiryBoardService.view(board);
		inquiryBoardService.hit(board); 
		

		InquiryBoardFile inquiryBoardfile = inquiryBoardService.getFileInfo(board);
		
		
		//VIEW 지정
		req.setAttribute("board",board);
		req.setAttribute("file", inquiryBoardfile);
		session.setAttribute("counselorid", session.getAttribute("counselorid"));
//		System.out.println("sss "+session.getAttribute("counselorid"));
		InquiryBoardFile downFile = inquiryBoardService.getFile(req); 
		
		
		req.getRequestDispatcher("/WEB-INF/views/mypage/myInquiryShow.jsp").forward(req, resp);
	
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

	}
	
}
