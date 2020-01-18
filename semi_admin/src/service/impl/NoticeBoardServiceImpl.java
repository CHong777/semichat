package service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.face.NoticeBoardDao;
import dao.impl.NoticeBoardDaoImpl;
import dto.InquiryBoard;
import dto.NoticeBoard;
import service.face.NoticeBoardService;
import util.Paging;

public class NoticeBoardServiceImpl implements NoticeBoardService{
	private NoticeBoardDao noticeBoardDao = new NoticeBoardDaoImpl();
	private NoticeBoard noticeBoard = new NoticeBoard();
	
	@Override
	public Paging getPaging(HttpServletRequest req, String adminId) {
		// 요청파라미터 curPage를 파싱
		String param = req.getParameter("curPage");
		int curPage = 0;
		if (param != null && !"".equals(param)) {
			curPage = Integer.parseInt(param);
		}

		String searchType = req.getParameter("searchType");
		String search = req.getParameter("search");

		Map<String, String> map = new HashMap<String, String>();

		if (searchType != null & !"".equals(searchType)) {
			map.put("searchType", searchType);
		}

		if (search != null && !"".equals(search)) {
			map.put("search", search);
		}

		
		System.out.println("관리자 : "+adminId);
		// Board TB와 curPage 값을 이용한 Paging 객체를 생성하고 반환
		int totalCount = noticeBoardDao.selectCntAll(map, adminId);

//	      System.out.println(totalCount);
		// Paging 객체 생성
		Paging paging = new Paging(totalCount, curPage);

//	      System.out.println(paging);
		paging.setSearch(map);

		System.out.println("MAP : " + map);

		return paging;
	}

	@Override
	public List getList(Paging paging, String adminId) {
		return noticeBoardDao.selectAll(paging, adminId);
	}

	@Override
	public NoticeBoard getBoardno(HttpServletRequest req) {
		String param = null;
		int boardNo=0;
		
		param = req.getParameter("boardNo");
		if(param != null && !"".equals(param)) {
			boardNo = Integer.parseInt(param);
	     }
		
		noticeBoard.setnBoardNo(boardNo);
		
		return noticeBoard;
	}

	@Override
	public NoticeBoard view(NoticeBoard noticeBoard) {
		return noticeBoardDao.selectBoardByBoardno(noticeBoard);
	}

	@Override
	public void delete(NoticeBoard noticeBoard) {
		noticeBoardDao.delete(noticeBoard);		
	}

	@Override
	public void write(HttpServletRequest req, HttpServletResponse resp) {
		noticeBoard.setnBoardTitle( req.getParameter("title") );
		noticeBoard.setnBoardContent( req.getParameter("content") );

		//작성자id 처리
		noticeBoard.setCounselorId((String) req.getSession().getAttribute("adminId"));

		if(noticeBoard.getnBoardTitle()==null || "".equals(noticeBoard.getnBoardTitle())) {
			noticeBoard.setnBoardTitle("(제목없음)");
		}

		noticeBoardDao.insert(noticeBoard);
		
	}

	@Override
	public void noticeHit(NoticeBoard noticeBoard) {
		noticeBoardDao.hit(noticeBoard);
	}

	@Override
	public void update(HttpServletRequest req) {
		NoticeBoard noticeBoard = new NoticeBoard();
//		int iBoardNo= Integer.parseInt(req.getParameter("iboardNo"));
		System.out.println("111111 " + req.getParameter("nBoardNo"));
		
		String param=null;
//		System.out.println(req.getParameter("content"));
		param=req.getParameter("content");
		if(param!=null && !"".equals(param)) {
			noticeBoard.setnBoardContent(param);
	    }
		
		param=req.getParameter("title");
		if(param!=null && !"".equals(param)) {
			noticeBoard.setnBoardTitle(param);
		}
		
		param=req.getParameter("nBoardNo");
		if(param!=null && !"".equals(param)) {
			noticeBoard.setnBoardNo(Integer.parseInt(param));
		}
		
		param=req.getParameter("nBoardTitle");
		if(param!=null && !"".equals(param)) {
			noticeBoard.setnBoardTitle(param);
		}
		
//		inquiryBoard.setiBoardNo(iBoardNo);
		System.out.println(noticeBoard);
		noticeBoardDao.update(noticeBoard);
	}

}
