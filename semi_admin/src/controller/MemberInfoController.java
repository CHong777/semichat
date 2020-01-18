package controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import service.face.MemberManageService;
import service.impl.MemberManageServiceImpl;
import util.Paging;

@WebServlet("/admin/membermanage")
public class MemberInfoController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private MemberManageService memberMangeService = new MemberManageServiceImpl();

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// 요청파라미터에서 curPage를 구하고 Paging 객체 반환
		Paging paging = memberMangeService.getPaging(req, 2);
//		System.out.println(paging);
		// Paging 객체를 MODEL 값으로 지정
		req.setAttribute("paging", paging);

		List list = memberMangeService.getList(paging, 2);
//		System.out.println(list);
		req.setAttribute("list", list);

		req.getRequestDispatcher("/WEB-INF/views/memberInfo.jsp").forward(req, resp);
	}
}
