package service.face;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dto.InquiryBoard;
import dto.InquiryBoardFile;
import util.Paging;

public interface InquiryBoardService {

	public Paging getPaging(HttpServletRequest req);

	public List getList(Paging paging);

	public InquiryBoard getBoardno(HttpServletRequest req);

	public InquiryBoard view(InquiryBoard board);

	public InquiryBoardFile getFileInfo(InquiryBoard board);

	public InquiryBoardFile getFile(HttpServletRequest req);

	public void update(HttpServletRequest req);

	public void hit(InquiryBoard board);

}
