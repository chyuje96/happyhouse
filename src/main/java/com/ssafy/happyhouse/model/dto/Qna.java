package com.ssafy.happyhouse.model.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(value = "Board (QnA게시판)", description = "글번호, 제목, 내용, 작성자아이디, 작성일을 가진  Domain Class")
public class Qna {
	@ApiModelProperty(value = "글번호")
	private int no;
	@ApiModelProperty(value = "작성자아이디")
	private String userId;
	@ApiModelProperty(value = "제목")
	private String title;
	@ApiModelProperty(value = "내용")
	private String content;
	@ApiModelProperty(value = "작성일")
	private String regtime;
	
	public Qna(int no, String userId, String title, String content, String regtime) {
		super();
		this.no = no;
		this.userId = userId;
		this.title = title;
		this.content = content;
		this.regtime = regtime;
	}
	
	public String getRegtime() {
		return regtime;
	}

	public void setRegtime(String regtime) {
		this.regtime = regtime;
	}

	public int getNo() {
		return no;
	}
	public void setNo(int no) {
		this.no = no;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}

	@Override
	public String toString() {
		return "Qna [no=" + no + ", userId=" + userId + ", title=" + title + ", content=" + content + "]";
	}	
}
