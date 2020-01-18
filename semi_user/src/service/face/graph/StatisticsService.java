package service.face.graph;

import java.util.Map;

public interface StatisticsService {

	/**
	 * 서현석
	 * 2019-12-05
	 * 
	 * 통계 자료에 쓰이는 key값과 value값을 가져온다
	 * @return map : key-날짜/ 키워드 등, value-카운트
	 */
	public Map<String, String> getChart(String chartType);
	
}
