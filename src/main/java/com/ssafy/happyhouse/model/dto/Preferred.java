package com.ssafy.happyhouse.model.dto;

public class Preferred {
	private int no;
	private String sidoName;
	private String gugunName;
	private String dongName;
	private String dongCode;
	private String userId;
	private long aptCode;
	
	public Preferred() {}

	public Preferred(int no, String sidoName, String gugunName, String dongName, String dongCode, String userId,
			long aptCode) {
		super();
		this.no = no;
		this.sidoName = sidoName;
		this.gugunName = gugunName;
		this.dongName = dongName;
		this.dongCode = dongCode;
		this.userId = userId;
		this.aptCode = aptCode;
	}

	public int getNo() {
		return no;
	}

	public void setNo(int no) {
		this.no = no;
	}

	public String getSidoName() {
		return sidoName;
	}

	public void setSidoName(String sidoName) {
		this.sidoName = sidoName;
	}

	public String getGugunName() {
		return gugunName;
	}

	public void setGugunName(String gugunName) {
		this.gugunName = gugunName;
	}

	public String getDongName() {
		return dongName;
	}

	public void setDongName(String dongName) {
		this.dongName = dongName;
	}

	public String getDongCode() {
		return dongCode;
	}

	public void setDongCode(String dongCode) {
		this.dongCode = dongCode;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public long getAptCode() {
		return aptCode;
	}

	public void setAptCode(long aptCode) {
		this.aptCode = aptCode;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Preferred [no=");
		builder.append(no);
		builder.append(", sidoName=");
		builder.append(sidoName);
		builder.append(", gugunName=");
		builder.append(gugunName);
		builder.append(", dongName=");
		builder.append(dongName);
		builder.append(", dongCode=");
		builder.append(dongCode);
		builder.append(", userId=");
		builder.append(userId);
		builder.append(", aptCode=");
		builder.append(aptCode);
		builder.append("]");
		return builder.toString();
	}
	
	
	
}
