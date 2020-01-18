package service.face;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import dto.AdminBoard;
import dto.InquiryBoardFile;
import util.Paging;
import util.back_Paging;

public interface BoardService {

	public back_Paging getPaging(HttpServletRequest req);

	public List getList(back_Paging paging);

	public AdminBoard getBoardno(HttpServletRequest req);

	public AdminBoard view(AdminBoard adminBoard);

	public InquiryBoardFile getFileInfo(AdminBoard adminBoard);

	public void delete(InquiryBoardFile file);

	public void inquiryDelete(AdminBoard deleteBoard);

	public void noticeDelete(AdminBoard deleteBoard);

	public void inquiryHit(AdminBoard adminBoard);

	public void noticeHit(AdminBoard adminBoard);

}
