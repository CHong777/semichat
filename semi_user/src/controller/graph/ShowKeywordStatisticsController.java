package controller.graph;

import java.io.IOException;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import service.face.graph.StatisticsService;
import service.impl.graph.StatisticsServiceImpl;

@WebServlet("/statistics/keyword")
public class ShowKeywordStatisticsController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private StatisticsService statisticsService = new StatisticsServiceImpl();
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		Map<String, String> map = statisticsService.getChart("keyword");
		
		req.setAttribute("chart",map);
		req.getRequestDispatcher("/WEB-INF/views/graph/keywordStatistics.jsp").forward(req, resp);
	
	}
}
