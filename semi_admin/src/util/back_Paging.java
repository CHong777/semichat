package util;

import java.util.Map;

public class back_Paging {
		
	private int curPage;//현재 페이지 번호(브라우저에서 선택한 페이지 번호)
	
	private int totalCount;//총 게시글 수(DB에서 조회된 전체 결과의 행 수)
	private int listCount;//한 페이지당 출력될 게시글 수(직접 설정할 수 있다)
	private int totalPage;//총 페이지 수(계산으로 알아낸다)
	private int pageCount;//한 화면에 출력될 페이지 수 (직접 설정할 수 있음)
	private int startPage;//화면에 보이는 시작 페이지 번호 (계산으로 알아냄)
	private int endPage;//화면에 보이는 마지막 페이지 번호(계산으로 알아냄)
	private int startNo;//화면에 보이는 페이지의 게시글 시작 번호(계산으로 알아냄)
	private int endNo;//화면에 보이는 페이지의 게시글 끝 번호(계산으로 알아냄)
//	private String search;
	private Map<String, String> search; //�˻�
	private int corporationNo;
	
	//총 게시글 수만 입력하는 생성자
	public back_Paging(int totalCount) {
		this.setTotalCount(totalCount);
		
		this.makePaging();
	}
	
	//총 게시글 수와 현재 페이지를 입력하는 생성자
	public back_Paging(int totalCount, int curPage) {
		this.setTotalCount(totalCount);
		this.setCurPage(curPage);
//		System.out.println(curPage);
		
		this.makePaging();
		
		
	}
	

	public back_Paging(int curPage, int totalCount, String search) {
		this.setTotalCount(totalCount);
		this.setCurPage(curPage);
		
		
//		this.setSearch(search);
		this.makePaging();
		
	}
	
	//총 게시글 수와 현재 페이지, 보여질 게시글 수를 입력하는 생성자
	public back_Paging(int totalCount, int curPage, int listCount) {
		this.setTotalCount(totalCount);
		this.setCurPage(curPage);
		this.setListCount(listCount);
		
		this.makePaging();
	}
	
	//총 게시글 수와 현재 페이지, 보여질 게시글 수, 보여질 페이지 수를 입력하는 생성자
	public back_Paging(int totalCount, int curPage, int listCount, int pageCount) {
		this.setTotalCount(totalCount);
		this.setCurPage(curPage);
		this.setListCount(listCount);
		this.setPageCount(pageCount);
		
		this.makePaging();
	}
	
	
	//페이징 정보 생성
	private void makePaging() {
		if(totalCount==0) return; //게시글이 없는 경우
		
		//기본값 설정
		if(curPage==0)	setCurPage(1); //첫 페이지 기본값 세팅
		if(pageCount==0) setPageCount(10); //화면에 보여질 페이지 수 기본값
		if(listCount==0) setListCount(10); //화면에 보여질 게시글수 기본값
		
		//총 페이지 수 계산
		totalPage = totalCount / listCount;
		if(totalCount % listCount >0)	totalPage++;
		
		//현재 페이지 보정
		//(총페이지 번호보다 현재 페이지번호가 높을 때 총 페이지 번호로 고정)
		if(totalPage<curPage) curPage = totalPage;
		
		//화면에 보여질 페이질 시작번호와 끝번호
		startPage = ( (curPage-1)/pageCount) * pageCount + 1;
		endPage = startPage + pageCount - 1;
		
		//계산된 끝 페이지 번호가 총 페이지수보다 클 때 보정
		if(endPage > totalPage) endPage = totalPage;
		
		//화면에 보여질 게시글의 시작번호와 끝번호
		startNo = totalCount - (listCount*(curPage-1))-listCount+1;
		endNo = totalCount - (listCount*(curPage-1));
	}

	@Override
	public String toString() {
		return "Paging [curPage=" + curPage + ", totalCount=" + totalCount + ", listCount=" + listCount + ", totalPage="
				+ totalPage + ", pageCount=" + pageCount + ", startPage=" + startPage + ", endPage=" + endPage
				+ ", startNo=" + startNo + ", endNo=" + endNo + ", search=" + search + ", corporationNo="
				+ corporationNo + "]";
	}

	public int getCurPage() {
		return curPage;
	}

	public void setCurPage(int curPage) {
		this.curPage = curPage;
	}

	public int getTotalCount() {
		return totalCount;
	}

	public void setTotalCount(int totalCount) {
		this.totalCount = totalCount;
	}

	public int getListCount() {
		return listCount;
	}

	public void setListCount(int listCount) {
		this.listCount = listCount;
	}

	public int getTotalPage() {
		return totalPage;
	}

	public void setTotalPage(int totalPage) {
		this.totalPage = totalPage;
	}

	public int getPageCount() {
		return pageCount;
	}

	public void setPageCount(int pageCount) {
		this.pageCount = pageCount;
	}

	public int getStartPage() {
		return startPage;
	}

	public void setStartPage(int startPage) {
		this.startPage = startPage;
	}

	public int getEndPage() {
		return endPage;
	}

	public void setEndPage(int endPage) {
		this.endPage = endPage;
	}

	public int getStartNo() {
		return startNo;
	}

	public void setStartNo(int startNo) {
		this.startNo = startNo;
	}

	public int getEndNo() {
		return endNo;
	}
	
	public void setEndNo(int endNo) {
		this.endNo = endNo;
	}
//	public String getSearch() {
//		return search;
//	}
//	
//	public void setSearch(String search) {
//		this.search = search;
//	}
	
	public Map<String, String> getSearch() {
		return search;
	}

	public void setSearch(Map<String, String> search) {
		this.search = search;
	}

	public int getCorporationNo() {
		return corporationNo;
	}

	public void setCorporationNo(int corporationNo) {
		this.corporationNo = corporationNo;
	}

	

}