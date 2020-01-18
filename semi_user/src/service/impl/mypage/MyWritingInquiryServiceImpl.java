package service.impl.mypage;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import dao.face.mypage.MyWritingInquiryDao;
import dao.impl.mypage.MyWritingInquiryDaoImpl;
import dto.InquiryBoard;
import service.face.mypage.MyWritingInquiryService;
import util.Paging;

public class MyWritingInquiryServiceImpl implements MyWritingInquiryService{

	private MyWritingInquiryDao myWritingInquiryDao = new MyWritingInquiryDaoImpl();
	
	@Override
	public Paging getPaging(HttpServletRequest req) {
    		HttpSession session = req.getSession();
    		session.getAttribute("counselorid");


		//요청파라미터 curPage를 파싱
	      
	      String param = req.getParameter("curPage");
	      int curPage = 0;
	      if(param!=null && !"".equals(param)) {
	         curPage = Integer.parseInt(param);
	      }
	      
	      String searchType = req.getParameter("searchType");
	      String search = req.getParameter("search");
	      
	      Map<String, String> map = new HashMap<String, String>();
	      
	      if(searchType!=null & !"".equals(searchType)) {
	         map.put("searchType",searchType);
	      }
	      
	      if(search!=null && !"".equals(search)) {
	         map.put("search", search);
	      }
	      
	      //Board TB와 curPage 값을 이용한 Paging 객체를 생성하고 반환    
	      String counselorid=(String) session.getAttribute("counselorid");
	      int totalCount = myWritingInquiryDao.selectCntAll(map,counselorid);
//	    		  +myWritingNoticeDao.selectCntAll(map, counselorid);
	      Paging paging = new Paging(totalCount, curPage);
//	      System.out.println("전체쪽수 : "+totalCount );
//	      System.out.println(totalCount);
	      //Paging 객체 생성
	 
	      
	      System.out.println(paging);
	      paging.setSearch(map);
	      
//	      System.out.println("MAP : " +map);
	      
	      return paging;
	}

	@Override
	public List getList(Paging paging) {
		return myWritingInquiryDao.selectAll(paging);
	}




}
