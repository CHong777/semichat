package dto;

public class ChatProfile {

	//chatProfile
	private int chatProfileNo;
	private int messengerNo;
	private String clientID;
	private int status;
	private String counselorID;
	private int isFixedMatch;
	private String lastChatDate;
	private int topFixedNum;
	private int clientInfoNo;
	private int myClientInfoNo;

	//chat
	private String lastChatContent; // 메시지 내용
	private int lasetMessageType; // 메시지 타입(0:텍스트 1:이미지)
	private String lastChatTime;    // 마지막 채팅 시간
	
	//chat group by chatProfile
	private int unReadChatCount;   // 읽은 채팅 갯수 (chatRead - 0:안읽음, 1:읽음)
	
	@Override
	public String toString() {
		return "ChatProfile [chatProfileNo=" + chatProfileNo + ", messengerNo=" + messengerNo + ", clientID=" + clientID
				+ ", status=" + status + ", counselorID=" + counselorID + ", isFixedMatch=" + isFixedMatch
				+ ", lastChatDate=" + lastChatDate + ", topFixedNum=" + topFixedNum + ", clientInfoNo=" + clientInfoNo
				+ ", myClientInfoNo=" + myClientInfoNo + ", lastChatContent=" + lastChatContent + ", lasetMessageType="
				+ lasetMessageType + ", lastChatTime=" + lastChatTime + ", unReadChatCount=" + unReadChatCount + "]";
	}

	public int getChatProfileNo() {
		return chatProfileNo;
	}

	public void setChatProfileNo(int chatProfileNo) {
		this.chatProfileNo = chatProfileNo;
	}

	public int getMessengerNo() {
		return messengerNo;
	}

	public void setMessengerNo(int messengerNo) {
		this.messengerNo = messengerNo;
	}

	public String getClientID() {
		return clientID;
	}

	public void setClientID(String clientID) {
		this.clientID = clientID;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public String getCounselorID() {
		return counselorID;
	}

	public void setCounselorID(String counselorID) {
		this.counselorID = counselorID;
	}

	public int getIsFixedMatch() {
		return isFixedMatch;
	}

	public void setIsFixedMatch(int isFixedMatch) {
		this.isFixedMatch = isFixedMatch;
	}

	public String getLastChatDate() {
		return lastChatDate;
	}

	public void setLastChatDate(String lastChatDate) {
		this.lastChatDate = lastChatDate;
	}

	public int getTopFixedNum() {
		return topFixedNum;
	}

	public void setTopFixedNum(int topFixedNum) {
		this.topFixedNum = topFixedNum;
	}

	public int getClientInfoNo() {
		return clientInfoNo;
	}

	public void setClientInfoNo(int clientInfoNo) {
		this.clientInfoNo = clientInfoNo;
	}

	public int getMyClientInfoNo() {
		return myClientInfoNo;
	}

	public void setMyClientInfoNo(int myClientInfoNo) {
		this.myClientInfoNo = myClientInfoNo;
	}

	public String getLastChatContent() {
		return lastChatContent;
	}

	public void setLastChatContent(String lastChatContent) {
		this.lastChatContent = lastChatContent;
	}

	public int getLasetMessageType() {
		return lasetMessageType;
	}

	public void setLasetMessageType(int lasetMessageType) {
		this.lasetMessageType = lasetMessageType;
	}

	public String getLastChatTime() {
		return lastChatTime;
	}

	public void setLastChatTime(String lastChatTime) {
		this.lastChatTime = lastChatTime;
	}

	public int getUnReadChatCount() {
		return unReadChatCount;
	}

	public void setUnReadChatCount(int unReadChatCount) {
		this.unReadChatCount = unReadChatCount;
	}
	
	
}