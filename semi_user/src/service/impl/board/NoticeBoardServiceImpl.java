package service.impl.board;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import dao.face.board.NoticeBoardDao;
import dao.impl.board.NoticeBoardDaoImpl;
import dto.NoticeBoard;
import service.face.board.NoticeBoardService;
import util.Paging;

public class NoticeBoardServiceImpl implements NoticeBoardService {
	
	private NoticeBoardDao noticeboardDao = new NoticeBoardDaoImpl();
	
	@Override
	public Paging getPaging(HttpServletRequest req) {
		
		//요청파라미터 curPage를 파싱한다
		String param = req.getParameter("curPage");
		int curPage = 0;
		if( param!=null && !"".equals(param) ) {
			curPage = Integer.parseInt(param);
		}
//		System.out.println("curPage : " + curPage);
		
		//검색어
		String searchType = req.getParameter("searchType");
		String search = (String)req.getParameter("search");
		
		Map<String, String> map = new HashMap<String, String>();

		if(searchType!=null & !"".equals(searchType)) {
			map.put("searchType",searchType);
		}

		if(search!=null && !"".equals(search)) {
			map.put("search", search);
		}
		
		
		//Board TB와 curPage 값을 이용한 Paging 객체를 생성하고 반환
		int totalCount = noticeboardDao.selectCntAll(map);
		System.out.println("총게시글수: " + totalCount);
		
		// Paging 객체 생성 
		Paging paging = new Paging(totalCount, curPage);
		
		paging.setSearch(map);
		
		return paging;
	}

	@Override
	public List getList(Paging paging) {
		// TODO Auto-generated method stub
		return noticeboardDao.selectAll(paging);
	}

	@Override
	public void NoticeBoardWrite(HttpServletRequest req) {
		
		NoticeBoard noticeboard = new NoticeBoard();

		noticeboard.setnBoardTitle( req.getParameter("title") );
		noticeboard.setnBoardContent( req.getParameter("content") );

		System.out.println("작성!!! : " + req.getParameter("title"));
		System.out.println("작성!!!22 : " + req.getParameter("content"));
		//작성자id 처리
		noticeboard.setCounselorId((String) req.getSession().getAttribute("counselorid"));

		if(noticeboard.getnBoardTitle()==null || "".equals(noticeboard.getnBoardTitle())) {
			noticeboard.setnBoardTitle("(제목없음)");
		}

		noticeboardDao.insert(noticeboard);
		
	}

	@Override
	public NoticeBoard getBoardno(HttpServletRequest req) {
		
		//요청파라미터 noticeboardno를 파싱한다
		String param = req.getParameter("noticeboardno");

		int noticeboardno = 0;
		if( param!=null && !"".equals(param) ) {
			noticeboardno = Integer.parseInt(param);
		}
		
		NoticeBoard board=noticeboardDao.getCounserlorId(noticeboardno);
		System.out.println("123456 "+board);
		//게시글 번호를 DTO에 넣기
		NoticeBoard noticeboard = new NoticeBoard();
		noticeboard.setnBoardNo(noticeboardno);
		noticeboard.setCounselorId(param);
		
		return noticeboard;
	}

	@Override
	public NoticeBoard show(NoticeBoard noticeboard) {
		
		//게시글 조회수 +1
		noticeboardDao.updateHit(noticeboard);
		
		//게시글 조회 반환
		return noticeboardDao.selectNoticeBoardByNoticeBoardno(noticeboard);
	}

	@Override
	public void delete(NoticeBoard noticeboard) {
		
		noticeboardDao.delete(noticeboard);
		
		
	}

	@Override
	public boolean checkId(HttpServletRequest req) {
		//로그인한 세션 ID 얻기
		String loginId = (String) req.getSession().getAttribute("counselorid");
		System.out.println("로그인한 아이디 체크: " + loginId);
		//작성한 게시글 번호 얻기
		NoticeBoard noticeboard = getBoardno(req);
		
		//게시글 얻어오기
		noticeboard = noticeboardDao.selectNoticeBoardByNoticeBoardno(noticeboard);
		System.out.println("서비스 " + noticeboard);
		//게시글의 작성자와 로그인 아이디 비교
		System.out.println(noticeboard.getCounselorId());
		System.out.println(loginId);
		if( !loginId.equals(noticeboard.getCounselorId()) ) {
			return false;
		}
		
		return true;
	}

	@Override
	public void update(HttpServletRequest req) {
		
		NoticeBoard noticeboard = new NoticeBoard();
		
		noticeboard.setnBoardTitle(req.getParameter("boardtitle"));
		noticeboard.setCounselorId((String) req.getSession().getAttribute("counselorid"));
		noticeboard.setnBoardContent(req.getParameter("content"));
		String param = req.getParameter("boardno");

		int noticeboardno = 0;
		noticeboardno = Integer.parseInt(param);
		
		noticeboard.setnBoardNo(noticeboardno);
		
		
		System.out.println("저장됨???" + noticeboard );
		
		
		if(noticeboard != null) {
			noticeboardDao.update(noticeboard);
		}
	}

}
