package service.face;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dto.NoticeBoard;
import util.Paging;

public interface NoticeBoardService {

	public Paging getPaging(HttpServletRequest req, String adminId);

	public List getList(Paging paging, String adminId);

	public NoticeBoard getBoardno(HttpServletRequest req);

	public NoticeBoard view(NoticeBoard noticeBoard);

	public void delete(NoticeBoard noticeBoard);

	public void write(HttpServletRequest req, HttpServletResponse resp);

	public void noticeHit(NoticeBoard noticeBoard);

	public void update(HttpServletRequest req);

}
