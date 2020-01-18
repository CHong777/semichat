package dto;

import java.sql.Date;

public class InquiryBoardFile {
	
	private int fileNo; //파일번호
	private String originalName; //기존이름
	private String storedName; //저장된 이름
	private int inquiryBoardno; //문의사항게시판번호
	private String fileType; //파일타입
	private int fileSize; //파일크기
	private String writeDate; //저장 날짜
	private int iBoardNo; //게시판번호
	
	public InquiryBoardFile() {
	
	}

	@Override
	public String toString() {
		return "InquiryBoardFile [fileNo=" + fileNo + ", originalName=" + originalName + ", storedName=" + storedName
				+ ", inquiryBoardno=" + inquiryBoardno + ", fileType=" + fileType + ", fileSize=" + fileSize
				+ ", writeDate=" + writeDate + ", iBoardNo=" + iBoardNo + "]";
	}

	public int getFileNo() {
		return fileNo;
	}

	public void setFileNo(int fileNo) {
		this.fileNo = fileNo;
	}

	public String getOriginalName() {
		return originalName;
	}

	public void setOriginalName(String originalName) {
		this.originalName = originalName;
	}

	public String getStoredName() {
		return storedName;
	}

	public void setStoredName(String storedName) {
		this.storedName = storedName;
	}

	public int getInquiryBoardno() {
		return inquiryBoardno;
	}

	public void setInquiryBoardno(int inquiryBoardno) {
		this.inquiryBoardno = inquiryBoardno;
	}

	public String getFileType() {
		return fileType;
	}

	public void setFileType(String fileType) {
		this.fileType = fileType;
	}

	public int getFileSize() {
		return fileSize;
	}

	public void setFileSize(int fileSize) {
		this.fileSize = fileSize;
	}

	public String getWriteDate() {
		return writeDate;
	}

	public void setWriteDate(String writeDate) {
		this.writeDate = writeDate;
	}

	public int getiBoardNo() {
		return iBoardNo;
	}

	public void setiBoardNo(int iBoardNo) {
		this.iBoardNo = iBoardNo;
	}
	
	
	
}
