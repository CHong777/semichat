package dto;

public class MyClientInfo {
	private int myClientInfoNo;
	private int counselorNo;
	private String counselorId;
	private int isBlock;
	private int isFixedMatch;
	private String clientNick;
	private int clientInfoNo;
	private String clientName;
	private String clientPhoneNum;
	private String lastChatDate;
	private String chatMemo;

	@Override
	public String toString() {
		return "MyClientInfo [myClientInfoNo=" + myClientInfoNo + ", counselorNo=" + counselorNo + ", counselorId="
				+ counselorId + ", isBlock=" + isBlock + ", isFixedMatch=" + isFixedMatch + ", clientNick=" + clientNick
				+ ", clientInfoNo=" + clientInfoNo + ", clientName=" + clientName + ", clientPhoneNum=" + clientPhoneNum
				+ ", lastChatDate=" + lastChatDate + ", chatMemo=" + chatMemo + "]";
	}

	public int getMyClientInfoNo() {
		return myClientInfoNo;
	}

	public void setMyClientInfoNo(int myClientInfoNo) {
		this.myClientInfoNo = myClientInfoNo;
	}

	public int getCounselorNo() {
		return counselorNo;
	}

	public void setCounselorNo(int counselorNo) {
		this.counselorNo = counselorNo;
	}

	public String getCounselorId() {
		return counselorId;
	}

	public void setCounselorId(String counselorId) {
		this.counselorId = counselorId;
	}

	public int getIsBlock() {
		return isBlock;
	}

	public void setIsBlock(int isBlock) {
		this.isBlock = isBlock;
	}

	public int getIsFixedMatch() {
		return isFixedMatch;
	}

	public void setIsFixedMatch(int isFixedMatch) {
		this.isFixedMatch = isFixedMatch;
	}

	public String getClientNick() {
		return clientNick;
	}

	public void setClientNick(String param) {
		this.clientNick = param;
	}

	public int getClientInfoNo() {
		return clientInfoNo;
	}

	public void setClientInfoNo(int clientInfoNo) {
		this.clientInfoNo = clientInfoNo;
	}

	public String getClientName() {
		return clientName;
	}

	public void setClientName(String clientName) {
		this.clientName = clientName;
	}

	public String getClientPhoneNum() {
		return clientPhoneNum;
	}

	public void setClientPhoneNum(String clientPhoneNum) {
		this.clientPhoneNum = clientPhoneNum;
	}

	public String getLastChatDate() {
		return lastChatDate;
	}

	public void setLastChatDate(String lastChatDate) {
		this.lastChatDate = lastChatDate;
	}

	public String getChatMemo() {
		return chatMemo;
	}

	public void setChatMemo(String chatMemo) {
		this.chatMemo = chatMemo;
	}
	
	
}
