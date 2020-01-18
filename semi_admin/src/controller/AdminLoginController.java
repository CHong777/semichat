package controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dto.Admin;
import service.face.AdminLoginService;
import service.impl.AdminLoginServiceImpl;

@WebServlet("/login/login")
public class AdminLoginController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private AdminLoginService adminLoginService = new AdminLoginServiceImpl();
	
	public AdminLoginController() { }
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		HttpSession session =  req.getSession();
		
		req.setCharacterEncoding("UTF-8");
		resp.setContentType("text/html;charset=UTF-8");
		
		Admin admin = adminLoginService.getAdminParam(req);
		System.out.println(admin);
		
		Boolean login = adminLoginService.serviceLogin(admin);
		
		if(login==true) {
			session.setAttribute("login", login);
			session.setAttribute("adminId", admin.getAdminId());
			session.setAttribute("adminPw", admin.getAdminPw());
			resp.sendRedirect("/admin/corporationmanage");
			return;
		} else {
			req.getRequestDispatcher("/login.jsp").forward(req, resp);
		}
		
	}
}
