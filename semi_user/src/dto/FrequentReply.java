package dto;

public class FrequentReply {
	private int frequentReplyNo;
	private String frequentReplyContent;
	private int frequentReplyInDesk;
	
	@Override
	public String toString() {
		return "FrequentReply [frequentReplyNo=" + frequentReplyNo + ", frequentReplyContent=" + frequentReplyContent
				+ ", frequentReplyInDesk=" + frequentReplyInDesk + "]";
	}

	public int getFrequentReplyNo() {
		return frequentReplyNo;
	}

	public void setFrequentReplyNo(int frequentReplyNo) {
		this.frequentReplyNo = frequentReplyNo;
	}

	public String getFrequentReplyContent() {
		return frequentReplyContent;
	}

	public void setFrequentReplyContent(String frequentReplyContent) {
		this.frequentReplyContent = frequentReplyContent;
	}

	public int getFrequentReplyInDesk() {
		return frequentReplyInDesk;
	}

	public void setFrequentReplyInDesk(int frequentReplyInDesk) {
		this.frequentReplyInDesk = frequentReplyInDesk;
	}
	
}
