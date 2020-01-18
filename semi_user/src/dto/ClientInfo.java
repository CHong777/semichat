package dto;

public class ClientInfo {
	private int clientInfoNo;
	private String clientName;
	private String clientPhoneNum;
	private String lastChatDate;
	private String counselorName;
	private String chatMemo;

	@Override
	public String toString() {
		return "ClientInfo [clientInfoNo=" + clientInfoNo + ", clientName=" + clientName + ", clientPhoneNum="
				+ clientPhoneNum + ", lastChatDate=" + lastChatDate + ", counselorName=" + counselorName + ", chatMemo="
				+ chatMemo + "]";
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

	public String getCounselorName() {
		return counselorName;
	}

	public void setCounselorName(String counselorName) {
		this.counselorName = counselorName;
	}

	public String getChatMemo() {
		return chatMemo;
	}

	public void setChatMemo(String chatMemo) {
		this.chatMemo = chatMemo;
	}
}