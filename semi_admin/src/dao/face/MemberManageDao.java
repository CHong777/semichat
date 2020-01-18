package dao.face;

import java.util.List;
import java.util.Map;

import util.Paging;

public interface MemberManageDao {

	public int selectCorpCntAll(Map<String, String> map);

	public List selectCorpAll(Paging paging);

	public List selectCounselorAll(Paging paging);

	public int selectCounselorCntAll(Map<String, String> map, int corporationNo);

}
