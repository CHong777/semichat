package service.impl.graph;

import java.util.HashMap;
import java.util.Map;

import dao.face.graph.StatisticsDao;
import dao.impl.graph.StatisticsDaoImpl;
import service.face.graph.StatisticsService;

public class StatisticsServiceImpl implements StatisticsService {

	private StatisticsDao statisticsDao = new StatisticsDaoImpl(); 
	
	@Override
	public Map<String, String> getChart(String chartType) {

		Map<String, String> map = new HashMap<String, String>();
		
		switch(chartType) {
		case "monthlyChat" :
			map = statisticsDao.getChatCount("MONTH");
			break;
		case "dailyChat" :
			map = statisticsDao.getChatCount("DAY");
			break;
		case "keyword" :
			 map = statisticsDao.getKeywordCount();
			 break;
		default : 
			System.err.println("차트 타입 잘못 입력됨");
			return null;	 
		}
		
		return map;
	}
}
