package dao.face;

import java.util.List;
import java.util.Map;

import dto.InquiryBoard;
import dto.InquiryBoardFile;
import util.Paging;

public interface InquiryBoardDao {

	public int selectCntAll(Map<String, String> map);

	public List selectAll(Paging paging);

	public InquiryBoard selectBoardByBoardno(InquiryBoard board);

	public InquiryBoardFile getFileInfo(InquiryBoard board);

	public void selectByFileno(InquiryBoardFile downFile);

	public void update(InquiryBoard inquiryBoard);

	public void hit(InquiryBoard board);
}

