package controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import service.face.NoticeBoardService;
import service.impl.NoticeBoardServiceImpl;
import util.Paging;

@WebServlet("/admin/noticemanage")
public class NoticeManageController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private NoticeBoardService noticeBoardService = new NoticeBoardServiceImpl();
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String adminId=(String) req.getSession().getAttribute("adminId");
		Paging paging =  noticeBoardService.getPaging(req, adminId);
		req.setAttribute("paging", paging);
		
		
		List noticeList = noticeBoardService.getList(paging, adminId);
		
		req.setAttribute("list", noticeList);
		
		req.getRequestDispatcher("/WEB-INF/views/noticeManage.jsp").forward(req, resp);
	}
}
