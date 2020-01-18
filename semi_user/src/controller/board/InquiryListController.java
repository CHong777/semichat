package controller.board;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dto.InquiryBoard;
import service.face.board.InquiryBoardService;
import service.impl.board.InquiryBoardServiceImpl;
import util.Paging;


@WebServlet("/inquiry/list")
public class InquiryListController extends HttpServlet {
	private static final long serialVersionUID = 1L;
		
		InquiryBoardService inquiryBoardService = new InquiryBoardServiceImpl();
		
		public InquiryListController() {}
		
		
	@Override
		protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
			
			HttpSession session = req.getSession();
			
			session.getAttribute("counselorid");
			session.getAttribute("counselorpassword");
//			System.out.println(session.getAttribute("counselorid"));

			
			
		
			//페이징
			Paging paging = inquiryBoardService.getPaging(req);
//			InquiryBoard board = inquiryBoardService.getBoardno(req);
//			System.out.println("보드너어어엄 = " + board);
//			System.out.println(req.getParameter("curPage"));
			
			//게시글 목록 조회
			List boardList = inquiryBoardService.getList(paging);
			InquiryBoard boardPw = new InquiryBoard();
			boardPw = inquiryBoardService.getBoardpw(boardPw); 
			req.setAttribute("paging", paging);
			
//			System.out.println(paging);

//			System.out.println(boardList);
			
			req.setAttribute("list", boardList);
//			System.out.println("22342342343" + boardPw);
			req.setAttribute("boardPw", boardPw);
//			System.out.println(req.getParameter("search"));
			
			
			if(session.getAttribute("counselorid") != null) {
				req.getRequestDispatcher("/WEB-INF/views/board/inquiryList.jsp").forward(req, resp);
				return; 
			}
			
			req.getRequestDispatcher("/WEB-INF/views/login/login.jsp").forward(req, resp);
			
		}

	
	@Override
		protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
			
		}
	
}
