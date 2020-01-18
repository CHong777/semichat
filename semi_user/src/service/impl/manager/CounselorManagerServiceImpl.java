package service.impl.manager;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import dao.face.manager.CounselorManagerDao;
import dao.impl.manager.CounselorManagerDaoImpl;
import dto.Counselor;
import dto.FrequentReply;
import dto.InquiryBoard;
import service.face.manager.CounselorManagerService;
import util.Paging;

public class CounselorManagerServiceImpl implements CounselorManagerService{

	private CounselorManagerDao counselorManageDao = new CounselorManagerDaoImpl();
	
	@Override
	public Paging getPaging(HttpServletRequest req) {
		
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
	      int totalCount = counselorManageDao.selectCntAll(map);
	         
//	      System.out.println(totalCount);
	      //Paging 객체 생성
	      Paging paging = new Paging(totalCount, curPage);
	      
//	      System.out.println(paging);
	      paging.setSearch(map);
	      
	      System.out.println("MAP : " +map);
	      
	      return paging;

	}

	@Override
	public List getList(Paging paging) {
		return counselorManageDao.selectAll(paging);
	}

	@Override
	public Counselor getCounselorProfileParam(HttpServletRequest req) {

	      //전달 파라미터 얻기
	      String param = req.getParameter("counselorNo");
	      
	      int counselorNo = 0;
	      if( param!=null && !"".equals(param) ) {
	    	  counselorNo = Integer.parseInt(param);
	      }
	      
	      //전달 파라미터를 DTO(모델)에 담기
	      Counselor counselor = new Counselor();
	      counselor.setCounselorNo(counselorNo);
	     
	      //객체 반환
	      return counselor;
	}

	@Override
	public Counselor getCounselorProfileByNo(Counselor counselor) {
		return counselorManageDao.selectCounselorProfileByNo(counselor);
	}

	@Override
	public List getAccountList(Paging paging) {
		return counselorManageDao.selectAccountAll(paging);
	}

	@Override
	public void approveByManager(String counselorNo) {

		
		counselorManageDao.updateManagerConfirm(counselorNo);
	}

	@Override
	public void chatProfileToNull(String counselorNo) {

		Counselor counselor = new Counselor();
		counselor = getCounselorIdByNo(counselorNo);
		counselorManageDao.updateChatProfileToNull(counselor.getCounselorId());
	}

	@Override
	public void MyClientInfoDelete(String counselorNo) {

		//상담원 번호를 통한 상담원 아이디 구하기
		Counselor counselor = new Counselor();
		counselor = getCounselorIdByNo(counselorNo);
		
		counselorManageDao.deleteMyClientInfo(counselor.getCounselorId());
	}

	@Override
	public void inquiryBoardFileDelete(String counselorNo) {

		//상담원 번호를 통한 상담원 아이디 구하기
		Counselor counselor = new Counselor();
		counselor = getCounselorIdByNo(counselorNo);
		
		//상담원 아이디로 iboarNo 리스트 찾기
		List<InquiryBoard> inquiryBoardList = new ArrayList();
		inquiryBoardList = getIboardNo(counselor.getCounselorId());
		
		for(int i = 0; i < inquiryBoardList.size(); i++) {
			counselorManageDao.deleteInquiryBoardFile(inquiryBoardList.get(i).getiBoardNo());
		}
	}

	@Override
	public void inquiryBoardDelete(String counselorNo) {

		//상담원 번호를 통한 상담원 아이디 구하기
		Counselor counselor = new Counselor();
		counselor = getCounselorIdByNo(counselorNo);
		
		counselorManageDao.deleteinquiryBoard(counselor.getCounselorId());
	}

	@Override
	public void noticeBoardDelete(String counselorNo) {

		//상담원 번호를 통한 상담원 아이디 구하기
		Counselor counselor = new Counselor();
		counselor = getCounselorIdByNo(counselorNo);

		counselorManageDao.deletenoticeBoard(counselor.getCounselorId());
	}

	@Override
	public void clientInfoToNull(String counselorNo) {

		//상담원 번호를 통한 상담원 이름 구하기
		Counselor counselor = new Counselor();
		counselor = getCounselorNameByNo(counselorNo);
		counselorManageDao.updateClientInfoToNull(counselor.getCounselorName());
	}

	private Counselor getCounselorNameByNo(String counselorNo) {
		return counselorManageDao.selectCounselorNameByNo(counselorNo);
	}

	@Override
	public void counselorDelete(String counselorNo) {

		counselorManageDao.deleteCounselor(counselorNo);
	}

	@Override
	public Counselor getCounselorIdByNo(String counselorNo) {
		return counselorManageDao.selectCounselorIdByNo(counselorNo);
	}

	@Override
	public List<InquiryBoard> getIboardNo(String counselorId) {
		
		return counselorManageDao.selectIboardNoByCounselorId(counselorId);
	}

	@Override
	public List<FrequentReply> getFrequentReplyList() {
		
		return counselorManageDao.selectFrequentReply();
	}

	@Override
	public void registerFrequentReply(String frequentReplyContent) {

		counselorManageDao.insertFrequentReply(frequentReplyContent);
	}

	@Override
	public void deleteFrequentReply(String frequentReplyNo) {

		counselorManageDao.deleteFrequentReply(frequentReplyNo);
	}
}
