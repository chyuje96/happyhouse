package com.ssafy.happyhouse.model.mapper;

import java.sql.SQLException;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.ssafy.happyhouse.model.dto.Qna;

@Mapper
public interface QnaMapper {
	public List<Qna> selectQna() throws SQLException;
	public Qna selectQnaByNo(int no) throws SQLException;
	public int insertQna(Qna post) throws SQLException;
	public int updateQna(Qna post) throws SQLException;
	public int deleteQna(int no) throws SQLException;

}
