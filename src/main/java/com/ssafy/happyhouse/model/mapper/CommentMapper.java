package com.ssafy.happyhouse.model.mapper;

import java.sql.SQLException;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.ssafy.happyhouse.model.dto.Comment;

@Mapper
public interface CommentMapper {
	public List<Comment> selectComment(int qnaboardno) throws Exception;
	public int insertComment(Comment post) throws Exception;
	public int updateComment(Comment post) throws Exception;
	public int deleteComment(int no) throws Exception;
}
