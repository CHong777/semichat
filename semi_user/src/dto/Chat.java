package dto;

public class Chat {

	private int chatNo;
	private int chatProfileNo;
	private String fromID;
	private String toID;
	private String chatContent;
	private int messageType;
	private String chatTIME;
	private int chatRead;
	
	@Override
	public String toString() {
		return "Chat [chatNo=" + chatNo + ", chatProfileNo=" + chatProfileNo + ", fromID=" + fromID + ", toID=" + toID
				+ ", chatContent=" + chatContent + ", messageType=" + messageType + ", chatTIME=" + chatTIME
				+ ", chatRead=" + chatRead + "]";
	}
	
	public int getChatNo() {
		return chatNo;
	}
	public void setChatNo(int chatNo) {
		this.chatNo = chatNo;
	}
	public int getChatProfileNo() {
		return chatProfileNo;
	}
	public void setChatProfileNo(int chatProfileNo) {
		this.chatProfileNo = chatProfileNo;
	}
	public String getFromID() {
		return fromID;
	}
	public void setFromID(String fromID) {
		this.fromID = fromID;
	}
	public String getToID() {
		return toID;
	}
	public void setToID(String toID) {
		this.toID = toID;
	}
	public String getChatContent() {
		return chatContent;
	}
	public void setChatContent(String chatContent) {
		this.chatContent = chatContent;
	}
	public int getMessageType() {
		return messageType;
	}
	public void setMessageType(int messageType) {
		this.messageType = messageType;
	}
	public String getChatTIME() {
		return chatTIME;
	}
	public void setChatTIME(String chatTIME) {
		this.chatTIME = chatTIME;
	}
	public int getChatRead() {
		return chatRead;
	}
	public void setChatRead(int chatRead) {
		this.chatRead = chatRead;
	}
	
	
}
