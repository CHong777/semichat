package controller.board;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/board/privacy")
public class PrivacyRegulationBoardController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	public PrivacyRegulationBoardController() {
		
	}
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.getRequestDispatcher("/WEB-INF/views/board/privacyRegulationShow.jsp").forward(req, resp);
		
	}
}
