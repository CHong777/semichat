package dto;

//���ǻ��� �Խ���
public class InquiryBoard {
	
	private int iBoardNo; //게시판번호
	private String iBoardTitle; //게시판제목
	private String iBoardDate; //작성날짜
	private String iBoardContent; //게시판내용
	private int iBoardViews; //조회수
	private String nBoardBookMark; //즐겨찾기설정
	private String iBoardInquiryType; //문의유형
	private String iBoardSecret; //비밀글여부
	private String iBoardSecretPw; //비밀번호
	private String iBoardAnswer; //답변여부
	private int counselorNo; //상담원번호
	private String counselorName; //상담원이름
	private String counselorNickName;
	private String counselorId;
	
	public InquiryBoard() {
	
	}


	@Override
	public String toString() {
		return "InquiryBoard [iBoardNo=" + iBoardNo + ", iBoardTitle=" + iBoardTitle + ", iBoardDate=" + iBoardDate
				+ ", iBoardContent=" + iBoardContent + ", iBoardViews=" + iBoardViews + ", nBoardBookMark="
				+ nBoardBookMark + ", iBoardInquiryType=" + iBoardInquiryType + ", iBoardSecret=" + iBoardSecret
				+ ", iBoardSecretPw=" + iBoardSecretPw + ", iBoardAnswer=" + iBoardAnswer + ", counselorNo="
				+ counselorNo + ", counselorName=" + counselorName + ",couselorNickName=" + counselorNickName +", counselorId= "+ counselorId + "]";
	}


	public int getiBoardNo() {
		return iBoardNo;
	}


	public void setiBoardNo(int iBoardNo) {
		this.iBoardNo = iBoardNo;
	}


	public String getiBoardTitle() {
		return iBoardTitle;
	}


	public void setiBoardTitle(String iBoardTitle) {
		this.iBoardTitle = iBoardTitle;
	}


	public String getiBoardDate() {
		return iBoardDate;
	}


	public void setiBoardDate(String iBoardDate) {
		this.iBoardDate = iBoardDate;
	}


	public String getiBoardContent() {
		return iBoardContent;
	}


	public void setiBoardContent(String iBoardContent) {
		this.iBoardContent = iBoardContent;
	}


	public int getiBoardViews() {
		return iBoardViews;
	}


	public void setiBoardViews(int iBoardViews) {
		this.iBoardViews = iBoardViews;
	}


	public String getnBoardBookMark() {
		return nBoardBookMark;
	}


	public void setnBoardBookMark(String nBoardBookMark) {
		this.nBoardBookMark = nBoardBookMark;
	}


	public String getiBoardInquiryType() {
		return iBoardInquiryType;
	}


	public void setiBoardInquiryType(String iBoardInquiryType) {
		this.iBoardInquiryType = iBoardInquiryType;
	}


	public String getiBoardSecret() {
		return iBoardSecret;
	}


	public void setiBoardSecret(String iBoardSecret) {
		this.iBoardSecret = iBoardSecret;
	}


	public String getiBoardSecretPw() {
		return iBoardSecretPw;
	}


	public void setiBoardSecretPw(String iBoardSecretPw) {
		this.iBoardSecretPw = iBoardSecretPw;
	}


	public String getiBoardAnswer() {
		return iBoardAnswer;
	}


	public void setiBoardAnswer(String iBoardAnswer) {
		this.iBoardAnswer = iBoardAnswer;
	}


	public int getCounselorNo() {
		return counselorNo;
	}


	public void setCounselorNo(int counselorNo) {
		this.counselorNo = counselorNo;
	}


	public String getCounselorName() {
		return counselorName;
	}


	public void setCounselorName(String counselorName) {
		this.counselorName = counselorName;
	}


	public String getCounselorNickName() {
		return counselorNickName;
	}


	public void setCounselorNickName(String counselorNickName) {
		this.counselorNickName = counselorNickName;
	}


	public String getCounselorId() {
		return counselorId;
	}


	public void setCounselorId(String counselorId) {
		this.counselorId = counselorId;
	}


	
	
}
