package service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import dao.face.BoardDao;
import dao.impl.BoardDaoImpl;
import dto.AdminBoard;
import dto.InquiryBoardFile;
import service.face.BoardService;
import util.Paging;
import util.back_Paging;

public class BoardServiceImpl implements BoardService {
	private BoardDao boardDao = new BoardDaoImpl();
	private AdminBoard adminBoard = new AdminBoard();
	
	@Override
	public back_Paging getPaging(HttpServletRequest req) {
		// 요청파라미터 curPage를 파싱
		String param = null;
		int curPage = 0;

		param = req.getParameter("curPage");
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

		// Board TB와 curPage 값을 이용한 Paging 객체를 생성하고 반환
		int totalCount = boardDao.selectCntAll(map);
//	      System.out.println(totalCount);
		// Paging 객체 생성
		back_Paging paging = new back_Paging(totalCount, curPage);

//	      System.out.println(paging);
		paging.setSearch(map);

		System.out.println("MAP : " + map);

		return paging;
	}

	@Override
	public List getList(back_Paging paging) {
		return boardDao.selectAll(paging);
	}

	@Override
	public AdminBoard getBoardno(HttpServletRequest req) {
		String param = null;
		int boardNo=0;
		String boardSorting=null;
		
		param = req.getParameter("boardNo");
		if(param != null && !"".equals(param)) {
			boardNo = Integer.parseInt(param);
	     }
		param = req.getParameter("boardSorting");
		if(param != null && !"".equals(param)) {
			boardSorting = param;
		}
		
		adminBoard.setBoardNo(boardNo);
		adminBoard.setBoardSorting(boardSorting);
		
		return adminBoard;
	}

	@Override
	public AdminBoard view(AdminBoard adminBoard) {
		return boardDao.selectBoardByBoardno(adminBoard);
	}

	@Override
	public InquiryBoardFile getFileInfo(AdminBoard adminBoard) {
		return boardDao.getFileInfo(adminBoard);
	}

	@Override
	public void delete(InquiryBoardFile file) {
		boardDao.delete(file);
	}

	@Override
	public void inquiryDelete(AdminBoard deleteBoard) {
		boardDao.inquiryDelete(deleteBoard);
	}

	@Override
	public void noticeDelete(AdminBoard deleteBoard) {
		boardDao.noticeDelete(deleteBoard);
	}

	@Override
	public void inquiryHit(AdminBoard adminBoard) {
		boardDao.inquiryHit(adminBoard);
	}

	@Override
	public void noticeHit(AdminBoard adminBoard) {
		boardDao.noticeHit(adminBoard);
	}



}
