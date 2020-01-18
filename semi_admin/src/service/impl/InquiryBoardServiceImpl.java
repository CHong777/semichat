package service.impl;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import dao.face.InquiryBoardDao;
import dao.impl.InquiryBoardDaoImpl;
import dto.InquiryBoard;
import dto.InquiryBoardFile;
import service.face.InquiryBoardService;
import util.Paging;

public class InquiryBoardServiceImpl implements InquiryBoardService {
	private InquiryBoardDao inquiryBoardDao = new InquiryBoardDaoImpl();
	private InquiryBoard inquiryBoard = new InquiryBoard();

	@Override
	public Paging getPaging(HttpServletRequest req) {
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
			// Board TB와 curPage 값을 이용한 Paging 객체를 생성하고 반환
			int totalCount = inquiryBoardDao.selectCntAll(map);

//			      System.out.println(totalCount);
			// Paging 객체 생성
			Paging paging = new Paging(totalCount, curPage);

//			      System.out.println(paging);
			paging.setSearch(map);

			System.out.println("MAP : " + map);

		return paging;
	}

	@Override
	public List getList(Paging paging) {
		return inquiryBoardDao.selectAll(paging);
	}

	@Override
	public InquiryBoard getBoardno(HttpServletRequest req) {
		String param = req.getParameter("iboardNo");

	    int iboardno = 0;
	    System.out.println("파람검사 서비스 : " + param);
	      
	    //전달 값이 없으면 종료
//	    if(param == null)   return null;
	      
	    // 전달 파라미터를 int형으로 변환
	    if(param != null && !"".equals(param)) {
	    	iboardno = Integer.parseInt(param);
	    }
	      System.out.println(iboardno);
	      //전달 파라미터를 DTO(모델)에 담기
	      
	    inquiryBoard.setiBoardNo(iboardno);
	      
	    System.out.println("getBoardno = " + iboardno);
	    System.out.println("getBoardno에서 board객체 = " + inquiryBoard); 
	    //객체 반환
	    return inquiryBoard;
	}

	@Override
	public InquiryBoard view(InquiryBoard board) {
		return inquiryBoardDao.selectBoardByBoardno(board);
	}

	@Override
	public InquiryBoardFile getFileInfo(InquiryBoard board) {
		return inquiryBoardDao.getFileInfo(board);
	}

	@Override
	public InquiryBoardFile getFile(HttpServletRequest req) {
		//요청파라미터 fileno 얻기
	      InquiryBoardFile downFile = getFileno(req);

	      //파일 정보 얻기
	      getFile(downFile);

	      //반환
	      return downFile;
	}

	private void getFile(InquiryBoardFile downFile) {
		inquiryBoardDao.selectByFileno(downFile);
	}

	private InquiryBoardFile getFileno(HttpServletRequest req) {
		//전달파라미터 받기
	      String param = req.getParameter("fileno");


	      //int형으로 형변환
	      int fileno = 0;
	      if(param!=null && !"".equals(param)) {
	         fileno = Integer.parseInt(param);
	      }


	      //DTO에 저장
	      InquiryBoardFile uploadFile = new InquiryBoardFile();
	      uploadFile.setFileNo(fileno);

	      return uploadFile;
	}

	@Override
	public void update(HttpServletRequest req) {
		InquiryBoard inquiryBoard = new InquiryBoard();
//		int iBoardNo= Integer.parseInt(req.getParameter("iboardNo"));
		System.out.println("111111 " + req.getParameter("iBoardNo"));
		
		String param=null;
//		System.out.println(req.getParameter("content"));
		param=req.getParameter("content");
		if(param!=null && !"".equals(param)) {
	         inquiryBoard.setiBoardContent(param);
	    }
		
		param=req.getParameter("iBoardNo");
		if(param!=null && !"".equals(param)) {
			inquiryBoard.setiBoardNo(Integer.parseInt(param));
		}
		
//		inquiryBoard.setiBoardNo(iBoardNo);
		System.out.println(inquiryBoard);
		inquiryBoardDao.update(inquiryBoard);
		
	}

	@Override
	public void hit(InquiryBoard board) {
		inquiryBoardDao.hit(board);
	}

}
