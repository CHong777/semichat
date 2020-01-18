package dao.face.graph;

import java.util.Map;

public interface StatisticsDao {

	/**
	 * 서현석
	 * 2019-12-05
	 * 
	 * 날짜값에 해당하는 개수를 가져온다
	 * @return
	 */
	public Map<String, String> getChatCount(String dateType);
	
	/**
	 * 서현석
	 * 2019-12-05
	 * 
	 * 키(키워드)값에 해당하는 개수를 가져온다
	 * @return
	 */
	public Map<String, String> getKeywordCount();

}
