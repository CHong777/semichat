package dao.face;

import java.util.List;
import java.util.Map;

import dto.NoticeBoard;
import util.Paging;

public interface NoticeBoardDao {

	public int selectCntAll(Map<String, String> map, String adminId);

	public List selectAll(Paging paging, String adminId);

	public NoticeBoard selectBoardByBoardno(NoticeBoard noticeBoard);

	public void delete(NoticeBoard noticeBoard);

	public void insert(NoticeBoard noticeBoard);

	public void hit(NoticeBoard noticeBoard);

	public void update(NoticeBoard noticeBoard);

}
