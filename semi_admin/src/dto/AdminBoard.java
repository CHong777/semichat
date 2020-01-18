package dto;

public class AdminBoard {
	private int adminBoardNo;
	private int boardNo;
	private String corporationname;
	private String boardSorting;
	private String title;
	private String writer;
	private String writeDate;
	private int views;
	private String boardContent;
	private String inquiryAnswer;
	
	@Override
	public String toString() {
		return "AdminBoard [adminBoardNo=" + adminBoardNo + ", boardNo=" + boardNo + ", corporationname="
				+ corporationname + ", boardSorting=" + boardSorting + ", title=" + title + ", writer=" + writer
				+ ", writeDate=" + writeDate + ", views=" + views + ", boardContent=" + boardContent
				+ ", inquiryAnswer=" + inquiryAnswer + "]";
	}

	public int getAdminBoardNo() {
		return adminBoardNo;
	}

	public void setAdminBoardNo(int adminBoardNo) {
		this.adminBoardNo = adminBoardNo;
	}

	public int getBoardNo() {
		return boardNo;
	}

	public void setBoardNo(int boardNo) {
		this.boardNo = boardNo;
	}

	public String getCorporationname() {
		return corporationname;
	}

	public void setCorporationname(String corporationname) {
		this.corporationname = corporationname;
	}

	public String getBoardSorting() {
		return boardSorting;
	}

	public void setBoardSorting(String boardSorting) {
		this.boardSorting = boardSorting;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getWriter() {
		return writer;
	}

	public void setWriter(String writer) {
		this.writer = writer;
	}

	public String getWriteDate() {
		return writeDate;
	}

	public void setWriteDate(String writeDate) {
		this.writeDate = writeDate;
	}

	public int getViews() {
		return views;
	}

	public void setViews(int views) {
		this.views = views;
	}

	public String getBoardContent() {
		return boardContent;
	}

	public void setBoardContent(String boardContent) {
		this.boardContent = boardContent;
	}

	public String getInquiryAnswer() {
		return inquiryAnswer;
	}

	public void setInquiryAnswer(String inquiryAnswer) {
		this.inquiryAnswer = inquiryAnswer;
	}
	
	
	
}
