package controller.board;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dto.InquiryBoard;
import dto.InquiryBoardFile;
import service.face.board.InquiryBoardService;
import service.impl.board.InquiryBoardServiceImpl;

@WebServlet("/inquiry/delete")
public class InquiryDeleteController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public InquiryDeleteController() {};
	
	InquiryBoardService inquiryBoardService = new InquiryBoardServiceImpl();
	InquiryBoard inquiryBoard = new InquiryBoard();
	
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		InquiryBoard deleteBoard = inquiryBoardService.getBoardno(req);
		
//		System.out.println("딜리트 버튼 눌리냐");
//		System.out.println("딜리트 보드번호 가져오나 함 보자"  + deleteBoard);
		
		
		InquiryBoardFile file = inquiryBoardService.getFileInfo(deleteBoard);
//		
//		System.out.println("파일 가져오냐 " + file);
		
//		
		deleteBoard = inquiryBoardService.view(deleteBoard);
//		
		
		
//		inquiryBoardService.hit(deleteBoard);
		
		req.setAttribute("board", deleteBoard);
		
		
		inquiryBoardService.delete(file);
		inquiryBoardService.delete(deleteBoard);
		
		resp.sendRedirect("/inquiry/list");
	}
	
}
