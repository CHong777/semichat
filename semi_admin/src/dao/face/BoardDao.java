package dao.face;

import java.util.List;
import java.util.Map;

import dto.AdminBoard;
import dto.InquiryBoardFile;
import util.Paging;
import util.back_Paging;

public interface BoardDao {

	public int selectCntAll(Map<String, String> map);

	public List selectAll(back_Paging paging);

	public AdminBoard selectBoardByBoardno(AdminBoard adminBoard);

	public InquiryBoardFile getFileInfo(AdminBoard adminBoard);

	public void delete(InquiryBoardFile file);

	public void inquiryDelete(AdminBoard deleteBoard);

	public void noticeDelete(AdminBoard deleteBoard);

	public void inquiryHit(AdminBoard adminBoard);

	public void noticeHit(AdminBoard adminBoard);

}
