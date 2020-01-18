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


@WebServlet("/inquiry/listdelete")
public class InquiryListDelete extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public InquiryListDelete() {}
	
	private InquiryBoardService inquiryBoardService = new InquiryBoardServiceImpl(); 

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		InquiryBoard deleteBoard = new InquiryBoard();
		InquiryBoardFile file= new InquiryBoardFile(); 

		String[] arr = req.getParameterValues("checkRow");


		for(int i=0; i<arr.length; i++) {

			file.setiBoardNo(Integer.parseInt(arr[i]));
			deleteBoard.setiBoardNo(Integer.parseInt(arr[i]));
			inquiryBoardService.delete(file);
			inquiryBoardService.delete(deleteBoard);

		}
		resp.sendRedirect("/inquiry/list");


	}
}
