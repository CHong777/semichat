package dto;

public class NoticeBoard {
	private int nBoardNo; //�Խ��� ��ȣ (�⺻Ű)
	private String nBoardTitle; //�Խ��� ����
	private String nBoardDate; // �ۼ� ��¥
	private String nBoardContent; // �Խ��� ����
	private int nBoardView; //��ȸ��
	private String counselorId; //������ȣ
	
	public NoticeBoard() {
		
	}

	@Override
	public String toString() {
		return "NoticeBoard [nBoardNo=" + nBoardNo + ", nBoardTitle=" + nBoardTitle + ", nBoardDate=" + nBoardDate
				+ ", nBoardContent=" + nBoardContent + ", nBoardView=" + nBoardView + ", counselorId=" + counselorId
				+ "]";
	}

	public int getnBoardNo() {
		return nBoardNo;
	}

	public void setnBoardNo(int nBoardNo) {
		this.nBoardNo = nBoardNo;
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

	public int getnBoardView() {
		return nBoardView;
	}

	public void setnBoardView(int nBoardView) {
		this.nBoardView = nBoardView;
	}

	public String getCounselorId() {
		return counselorId;
	}

	public void setCounselorId(String counselorId) {
		this.counselorId = counselorId;
	}
	
}
