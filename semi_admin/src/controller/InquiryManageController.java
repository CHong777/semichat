package controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import service.face.InquiryBoardService;
import service.impl.InquiryBoardServiceImpl;
import util.Paging;

@WebServlet("/admin/inquirymanage")
public class InquiryManageController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private InquiryBoardService inquiryBoardService = new InquiryBoardServiceImpl();
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		Paging paging =  inquiryBoardService.getPaging(req);
		req.setAttribute("paging", paging);
		
		List noticeList = inquiryBoardService.getList(paging);
		
		req.setAttribute("list", noticeList);
		
		req.getRequestDispatcher("/WEB-INF/views/inquiryManage.jsp").forward(req, resp);
	}

}
