package controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import service.face.BoardService;
import service.impl.BoardServiceImpl;
import util.back_Paging;

@WebServlet("/admin/boardmanage")
public class BoardManageController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private BoardService boardService = new BoardServiceImpl();
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		back_Paging paging = boardService.getPaging(req);
		req.setAttribute("paging", paging);
		System.out.println(paging);
		
		List noticeList=boardService.getList(paging);
		
		req.setAttribute("list", noticeList);

		req.getRequestDispatcher("/WEB-INF/views/boardManage.jsp").forward(req, resp);
	}
}
