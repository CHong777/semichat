package controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import service.face.NoticeBoardService;
import service.impl.NoticeBoardServiceImpl;

@WebServlet("/admin/noticewrite")
public class NoticeWriteController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	NoticeBoardService noticeBoardService = new NoticeBoardServiceImpl();
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		HttpSession session = req.getSession();
		
		session.getAttribute("adminId");
//		System.out.println(session.getAttribute("adminId"));
		
		if(session.getAttribute("adminId") != null) {
			req.getRequestDispatcher("/WEB-INF/views/noticeWrite.jsp").forward(req, resp);
			return; 
		} else {
			req.getRequestDispatcher("/login.jsp").forward(req, resp);			
		}
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		noticeBoardService.write(req, resp);
		resp.sendRedirect("/admin/noticemanage");
	}
}
