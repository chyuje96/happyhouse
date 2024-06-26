package com.ssafy.happyhouse.model.dto;

public class HouseInfo {
	private long aptCode;
	private int buildYear;
	private String roadName;
	private String roadNameBonbun;
	private String roadNameBubun;
	private String roadNameSeq;
	private String roadNameBasementCode;
	private String roadNameCode;
	private String dong;
	private String bonbun;
	private String bubun;
	private String sigunguCode;
	private String eubmyundongCode;
	private String dongCode;
	private String landCode;
	private String apartmentName;
	private String jibun;
	private String lat;
	private String lng;
	
	public HouseInfo() {}
	public HouseInfo(long aptCode, int buildYear, String roadName, String roadNameBonbun, String roadNameBubun,
			String roadNameSeq, String roadNameBasementCode, String roadNameCode, String dong, String bonbun,
			String bubun, String sigunguCode, String eubmyundongCode, String dongCode, String landCode,
			String apartmentName, String jibun, String lat, String lng) {
		this.aptCode = aptCode;
		this.buildYear = buildYear;
		this.roadName = roadName;
		this.roadNameBonbun = roadNameBonbun;
		this.roadNameBubun = roadNameBubun;
		this.roadNameSeq = roadNameSeq;
		this.roadNameBasementCode = roadNameBasementCode;
		this.roadNameCode = roadNameCode;
		this.dong = dong;
		this.bonbun = bonbun;
		this.bubun = bubun;
		this.sigunguCode = sigunguCode;
		this.eubmyundongCode = eubmyundongCode;
		this.dongCode = dongCode;
		this.landCode = landCode;
		this.apartmentName = apartmentName;
		this.jibun = jibun;
		this.lat = lat;
		this.lng = lng;
	}
	public long getAptCode() {
		return aptCode;
	}
	public void setAptCode(long aptCode) {
		this.aptCode = aptCode;
	}
	public int getBuildYear() {
		return buildYear;
	}
	public void setBuildYear(int buildYear) {
		this.buildYear = buildYear;
	}
	public String getRoadName() {
		return roadName;
	}
	public void setRoadName(String roadName) {
		this.roadName = roadName;
	}
	public String getRoadNameBonbun() {
		return roadNameBonbun;
	}
	public void setRoadNameBonbun(String roadNameBonbun) {
		this.roadNameBonbun = roadNameBonbun;
	}
	public String getRoadNameBubun() {
		return roadNameBubun;
	}
	public void setRoadNameBubun(String roadNameBubun) {
		this.roadNameBubun = roadNameBubun;
	}
	public String getRoadNameSeq() {
		return roadNameSeq;
	}
	public void setRoadNameSeq(String roadNameSeq) {
		this.roadNameSeq = roadNameSeq;
	}
	public String getRoadNameBasementCode() {
		return roadNameBasementCode;
	}
	public void setRoadNameBasementCode(String roadNameBasementCode) {
		this.roadNameBasementCode = roadNameBasementCode;
	}
	public String getRoadNameCode() {
		return roadNameCode;
	}
	public void setRoadNameCode(String roadNameCode) {
		this.roadNameCode = roadNameCode;
	}
	public String getDong() {
		return dong;
	}
	public void setDong(String dong) {
		this.dong = dong;
	}
	public String getBonbun() {
		return bonbun;
	}
	public void setBonbun(String bonbun) {
		this.bonbun = bonbun;
	}
	public String getBubun() {
		return bubun;
	}
	public void setBubun(String bubun) {
		this.bubun = bubun;
	}
	public String getSigunguCode() {
		return sigunguCode;
	}
	public void setSigunguCode(String sigunguCode) {
		this.sigunguCode = sigunguCode;
	}
	public String getEubmyundongCode() {
		return eubmyundongCode;
	}
	public void setEubmyundongCode(String eubmyundongCode) {
		this.eubmyundongCode = eubmyundongCode;
	}
	public String getDongCode() {
		return dongCode;
	}
	public void setDongCode(String dongCode) {
		this.dongCode = dongCode;
	}
	public String getLandCode() {
		return landCode;
	}
	public void setLandCode(String landCode) {
		this.landCode = landCode;
	}
	public String getApartmentName() {
		return apartmentName;
	}
	public void setApartmentName(String apartmentName) {
		this.apartmentName = apartmentName;
	}
	public String getJibun() {
		return jibun;
	}
	public void setJibun(String jibun) {
		this.jibun = jibun;
	}
	public String getLat() {
		return lat;
	}
	public void setLat(String lat) {
		this.lat = lat;
	}
	public String getLng() {
		return lng;
	}
	public void setLng(String lng) {
		this.lng = lng;
	}
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("HouseInfo [aptCode=");
		builder.append(aptCode);
		builder.append(", buildYear=");
		builder.append(buildYear);
		builder.append(", roadName=");
		builder.append(roadName);
		builder.append(", roadNameBonbun=");
		builder.append(roadNameBonbun);
		builder.append(", roadNameBubun=");
		builder.append(roadNameBubun);
		builder.append(", roadNameSeq=");
		builder.append(roadNameSeq);
		builder.append(", roadNameBasementCode=");
		builder.append(roadNameBasementCode);
		builder.append(", roadNameCode=");
		builder.append(roadNameCode);
		builder.append(", dong=");
		builder.append(dong);
		builder.append(", bonbun=");
		builder.append(bonbun);
		builder.append(", bubun=");
		builder.append(bubun);
		builder.append(", sigunguCode=");
		builder.append(sigunguCode);
		builder.append(", eubmyundongCode=");
		builder.append(eubmyundongCode);
		builder.append(", dongCode=");
		builder.append(dongCode);
		builder.append(", landCode=");
		builder.append(landCode);
		builder.append(", apartmentName=");
		builder.append(apartmentName);
		builder.append(", jibun=");
		builder.append(jibun);
		builder.append(", lat=");
		builder.append(lat);
		builder.append(", lng=");
		builder.append(lng);
		builder.append("]");
		return builder.toString();
	}
	
	
	
}
