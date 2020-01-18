package dto;

public class Corporation {
	private int corporationNo;
	private String corporationName;
	private String ceo;
	private String registerDate;
	private String phoneNum;
	private int counselorCnt;
	private int sales;

	@Override
	public String toString() {
		return "Corporation [corporationNo=" + corporationNo + ", corporationName=" + corporationName + ", ceo=" + ceo
				+ ", registerDate=" + registerDate + ", phoneNum=" + phoneNum + ", counselorCnt=" + counselorCnt
				+ ", sales=" + sales + "]";
	}

	public int getCorporationNo() {
		return corporationNo;
	}

	public void setCorporationNo(int corporationNo) {
		this.corporationNo = corporationNo;
	}

	public String getCorporationName() {
		return corporationName;
	}

	public void setCorporationName(String corporationName) {
		this.corporationName = corporationName;
	}

	public String getCeo() {
		return ceo;
	}

	public void setCeo(String ceo) {
		this.ceo = ceo;
	}

	public String getRegisterDate() {
		return registerDate;
	}

	public void setRegisterDate(String registerDate) {
		this.registerDate = registerDate;
	}

	public String getPhoneNum() {
		return phoneNum;
	}

	public void setPhoneNum(String phoneNum) {
		this.phoneNum = phoneNum;
	}

	public int getCounselorCnt() {
		return counselorCnt;
	}

	public void setCounselorCnt(int counselorCnt) {
		this.counselorCnt = counselorCnt;
	}

	public int getSales() {
		return sales;
	}

	public void setSales(int sales) {
		this.sales = sales;
	}
	
	
}
