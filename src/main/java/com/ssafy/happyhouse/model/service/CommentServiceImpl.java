package com.ssafy.happyhouse.model.service;

import java.sql.SQLException;
import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ssafy.happyhouse.model.dto.Comment;
import com.ssafy.happyhouse.model.mapper.CommentMapper;

@Service
public class CommentServiceImpl implements CommentService {
	
	@Autowired
	private SqlSession sqlSession;

	@Override
	public List<Comment> retrieveComment(int qnaboardno) throws Exception {
		return sqlSession.getMapper(CommentMapper.class).selectComment(qnaboardno);
	}

	@Override
	public int writeComment(Comment post) throws Exception {
		return sqlSession.getMapper(CommentMapper.class).insertComment(post);
	}

	@Override
	public int updateComment(Comment post) throws Exception {
		return sqlSession.getMapper(CommentMapper.class).updateComment(post);
	}

	@Override
	public int deleteComment(int no) throws Exception {
		return sqlSession.getMapper(CommentMapper.class).deleteComment(no);
	}

}
