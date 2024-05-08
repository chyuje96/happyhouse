package com.ssafy.happyhouse.model.dto;

public class Comment {
	private int no;
	private String userId;
	private String comment;
	private String regtime;
	private int qnaboardno;
	
	public Comment() {
		// TODO Auto-generated constructor stub
	}

	public Comment(int no, String userId, String comment, String regtime, int qnaboardno) {
		super();
		this.no = no;
		this.userId = userId;
		this.comment = comment;
		this.regtime = regtime;
		this.qnaboardno = qnaboardno;
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
	public String getComment() {
		return comment;
	}
	public void setComment(String comment) {
		this.comment = comment;
	}
	public String getRegtime() {
		return regtime;
	}
	public void setRegtime(String regtime) {
		this.regtime = regtime;
	}
	public int getQnaboardno() {
		return qnaboardno;
	}
	public void setQnaboardno(int qnaboardno) {
		this.qnaboardno = qnaboardno;
	}
	
	@Override
	public String toString() {
		return "Comment [no=" + no + ", userId=" + userId + ", comment=" + comment + ", regtime=" + regtime
				+ ", qnaboardno=" + qnaboardno + "]";
	}
}
