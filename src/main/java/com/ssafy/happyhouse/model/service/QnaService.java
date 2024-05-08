package com.ssafy.happyhouse.model.service;

import java.util.List;

import com.ssafy.happyhouse.model.dto.Qna;

public interface QnaService {
	public List<Qna> retrieveQna() throws Exception;
	public Qna detailQna(int no) throws Exception;
	public int writeQna(Qna post) throws Exception;
	public int updateQna(Qna post) throws Exception;
	public int deleteQna(int no) throws Exception;
}
