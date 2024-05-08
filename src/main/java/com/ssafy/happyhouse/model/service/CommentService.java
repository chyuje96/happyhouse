package com.ssafy.happyhouse.model.service;

import java.util.List;

import com.ssafy.happyhouse.model.dto.Comment;

public interface CommentService {
	public List<Comment> retrieveComment(int qnaboardno) throws Exception;
	public int writeComment(Comment post) throws Exception;
	public int updateComment(Comment post) throws Exception;
	public int deleteComment(int no) throws Exception;

}
