package controller.mypage;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dto.InquiryBoard;
import service.face.mypage.MyClientListService;
import service.impl.mypage.MyClientListServiceImpl;

/**
 * Servlet implementation class InquirySecretCheckController
 */
@WebServlet("/mypage/bookmark")
public class BoardBookMarkCheckController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Override
		protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		InquiryBoard inquiryBoard = new InquiryBoard();
		
		String book = req.getParameter("book");
		String no = req.getParameter("no");
		String type = req.getParameter("type");
		
		
		System.out.println("잘 받아오냐 ? 북마크 " + book);
		System.out.println("잘 받아오냐 ? 넘버 " + no);
		System.out.println(type);
		
		MyClientListService myClientListService = new MyClientListServiceImpl();
		
		String bookMark;
		if(book.equals("Y")) {
			bookMark = myClientListService.bookMarkMinus(book, no, type);
			System.out.println("즐겨찾기 취소");
		}
		
		else {
			bookMark = myClientListService.bookMarkPlus(book, no, type);
			System.out.println("즐겨찾기 추가");
		}
		
		System.out.println("바뀌기전 : " + book);
		System.out.println("북마크 예상 Y : " + bookMark);
//
//		req.setAttribute("result", bookMark);
//		
//		req.getRequestDispatcher("/WEB-INF/views/mypage/myBoardBookMarkCheck.jsp").forward(req, resp);
//		
		resp.sendRedirect("/mypage/boardlist");
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		
	}
	
	
}
