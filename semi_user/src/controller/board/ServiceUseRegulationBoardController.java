package controller.board;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/board/service")
public class ServiceUseRegulationBoardController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	public ServiceUseRegulationBoardController() {
		
	}
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.getRequestDispatcher("/WEB-INF/views/board/useRegulationShow.jsp").forward(req, resp);
		
	}
}
