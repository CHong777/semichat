package dto;

public class NoticeBoard {
	private int nBoardNo; //�Խ��� ��ȣ (�⺻Ű) 20191207 유진 게시판에 나타내 줄 정리된 글번호
	private int boardNo; // 해당 게시글의 글 번호 20191207 유진 
	private String nBoardTitle; //�Խ��� ����
	private String nBoardDate; // �ۼ� ��¥
	private String nBoardContent; // �Խ��� ����
	private int nBoardViews; //��ȸ��
	private String nBoardBookMark; //���ã�� ����
	private String counselorId; //������ȣ
	private String boardSorting; // 공지사항 or 문의사항
	
	public NoticeBoard() {
		
	}
	
	@Override
	public String toString() {
		return "NoticeBoard [nBoardNo=" + nBoardNo + ", boardNo=" + boardNo + ", nBoardTitle=" + nBoardTitle
				+ ", nBoardDate=" + nBoardDate + ", nBoardContent=" + nBoardContent + ", nBoardViews=" + nBoardViews
				+ ", nBoardBookMark=" + nBoardBookMark + ", counselorId=" + counselorId + ", boardSorting="
				+ boardSorting + "]";
	}

	public int getnBoardNo() {
		return nBoardNo;
	}

	public void setnBoardNo(int nBoardNo) {
		this.nBoardNo = nBoardNo;
	}

	
	
	public int getBoardNo() {
		return boardNo;
	}

	public void setBoardNo(int boardNo) {
		this.boardNo = boardNo;
	}

	public String getnBoardTitle() {
		return nBoardTitle;
	}

	public void setnBoardTitle(String nBoardTitle) {
		this.nBoardTitle = nBoardTitle;
	}

	public String getnBoardDate() {
		return nBoardDate;
	}

	public void setnBoardDate(String nBoardDate) {
		this.nBoardDate = nBoardDate;
	}

	public String getnBoardContent() {
		return nBoardContent;
	}

	public void setnBoardContent(String nBoardContent) {
		this.nBoardContent = nBoardContent;
	}

	public int getnBoardViews() {
		return nBoardViews;
	}

	public void setnBoardViews(int nBoardViews) {
		this.nBoardViews = nBoardViews;
	}

	public String getnBoardBookMark() {
		return nBoardBookMark;
	}

	public void setnBoardBookMark(String nBoardBookMark) {
		this.nBoardBookMark = nBoardBookMark;
	}

	public String getCounselorId() {
		return counselorId;
	}

	public void setCounselorId(String counselorId) {
		this.counselorId = counselorId;
	}

	public String getBoardSorting() {
		return boardSorting;
	}

	public void setBoardSorting(String boardSorting) {
		this.boardSorting = boardSorting;
	}
	
	
}
