package com.ssafy.happyhouse.model.dto;

public class DongCode {
	private String dongCode;
	private String sidoName;
	private String gugunName;
	private String dongName;
	
	public DongCode() {
		// TODO Auto-generated constructor stub
	}

	public DongCode(String dongCode, String sidoName, String gugunName, String dongName) {
		super();
		this.dongCode = dongCode;
		this.sidoName = sidoName;
		this.gugunName = gugunName;
		this.dongName = dongName;
	}

	public String getDongCode() {
		return dongCode;
	}

	public String getSidoName() {
		return sidoName;
	}

	public String getGugunName() {
		return gugunName;
	}

	public String getDongName() {
		return dongName;
	}

	@Override
	public String toString() {
		return "[dongCode=" + dongCode + ", sidoName=" + sidoName + ", gugunName=" + gugunName + ", dongName="
				+ dongName + "]";
	}
	
	
}
