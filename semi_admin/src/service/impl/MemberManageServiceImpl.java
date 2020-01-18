package service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import dao.face.MemberManageDao;
import dao.impl.MemberManageDaoImpl;
import service.face.MemberManageService;
import util.Paging;

public class MemberManageServiceImpl implements MemberManageService {
	private MemberManageDao memberManageDao = new MemberManageDaoImpl();
	
	@Override
	public Paging getPaging(HttpServletRequest req, int flag) {
		//요청파라미터 curPage를 파싱
	      
	      String param = null;
	      
	      int totalCount=-1;
	      int curPage = 0;
	      int corporationNo=-1;
	      
	      param=req.getParameter("curPage");
	      if(param!=null && !"".equals(param)) {
	         curPage = Integer.parseInt(param);
	      }
	      
	      param=req.getParameter("corporationNo");
	      if(param!=null && !"".equals(param)) {
	         corporationNo = Integer.parseInt(param);
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
	      if(flag==1) {
	    	  totalCount = memberManageDao.selectCorpCntAll(map);
	      } else {
	    	  totalCount = memberManageDao.selectCounselorCntAll(map, corporationNo);
	      }
	      
	         
//	      System.out.println(totalCount);
	      //Paging 객체 생성
	      Paging paging = new Paging(totalCount, curPage);
	      
//	      System.out.println(paging);
	      paging.setSearch(map);
	      paging.setCorporationNo(corporationNo);
	      System.out.println("MAP : " +map);
	      
	      return paging;
	}

	@Override
	public List getList(Paging paging, int flag) {
		if(flag==1) {
			System.out.println("기업");
			return memberManageDao.selectCorpAll(paging);
		} else {
			System.out.println("상담자");
			return memberManageDao.selectCounselorAll(paging);
		}
	}


}
