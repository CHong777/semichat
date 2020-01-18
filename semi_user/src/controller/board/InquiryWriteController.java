package controller.board;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dto.InquiryBoard;
import service.face.board.InquiryBoardService;
import service.impl.board.InquiryBoardServiceImpl;

/**
 * Servlet implementation class InquiryWriteController
 */
@WebServlet("/inquiry/write")
public class InquiryWriteController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	InquiryBoardService inquiryBoardService = new InquiryBoardServiceImpl();
	
	public InquiryWriteController() {}
	
	@Override
		protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		HttpSession session = req.getSession();

		
		session.getAttribute("counselorid");
		session.getAttribute("counselorpassword");
//		System.out.println(session.getAttribute("counselorid"));

		
		
		if(session.getAttribute("counselorid") != null) {
			req.getRequestDispatcher("/WEB-INF/views/board/inquiryWrite.jsp").forward(req, resp);
			return; 
		}
		
		req.getRequestDispatcher("/WEB-INF/views/login/login.jsp").forward(req, resp);
		
		
		}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		req.setCharacterEncoding("UTF-8");

		
		
		
		
		inquiryBoardService.write(req, resp);
		
		resp.sendRedirect("/inquiry/list");
		
	}
	
}
