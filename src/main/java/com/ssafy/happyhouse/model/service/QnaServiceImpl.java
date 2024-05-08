package com.ssafy.happyhouse.model.service;

import java.sql.SQLException;
import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ssafy.happyhouse.model.dto.Qna;
import com.ssafy.happyhouse.model.mapper.QnaMapper;

@Service
public class QnaServiceImpl implements QnaService {
	
	@Autowired
	private SqlSession sqlSession;
	
	@Override
	public List<Qna> retrieveQna() throws SQLException {
		return sqlSession.getMapper(QnaMapper.class).selectQna();
	}

	@Override
	public Qna detailQna(int no) throws SQLException {
		return sqlSession.getMapper(QnaMapper.class).selectQnaByNo(no);
	}

	@Override
	public int writeQna(Qna post) throws SQLException {
		return sqlSession.getMapper(QnaMapper.class).insertQna(post);
	}

	@Override
	@Transactional
	public int updateQna(Qna post) throws SQLException {
		return sqlSession.getMapper(QnaMapper.class).updateQna(post);
	}

	@Override
	@Transactional
	public int deleteQna(int no) throws SQLException {
		return sqlSession.getMapper(QnaMapper.class).deleteQna(no);
	}

}
