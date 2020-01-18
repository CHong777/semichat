package controller.board;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class InquirySecretCheckController
 */
@WebServlet("/inquiry/secret")
public class InquirySecretCheckController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Override
		protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		
		String result = req.getParameter("secretChecked");
		
//		System.out.println("잘 받아오냐 ? " + result);
		
		req.setAttribute("result", result);
		
		req.getRequestDispatcher("/WEB-INF/views/board/inquirySecretCheck.jsp").forward(req, resp);
	}
	
	
}
