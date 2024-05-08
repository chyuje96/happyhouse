package com.ssafy.happyhouse.model.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ssafy.happyhouse.model.dto.DongCode;
import com.ssafy.happyhouse.model.dto.HouseDeal;
import com.ssafy.happyhouse.model.dto.HouseInfo;
import com.ssafy.happyhouse.model.dto.Preferred;
import com.ssafy.happyhouse.model.mapper.AptDealMapper;

@Service
public class HouseMapServiceImpl implements HouseMapService {

	@Autowired
	private SqlSession sqlSession;
	
	@Override
	public List<DongCode> getSidoCode() throws Exception {
		return sqlSession.getMapper(AptDealMapper.class).getSidoCode();
	}

	@Override
	public List<DongCode> getGugunCode(String sido) throws Exception {
		return sqlSession.getMapper(AptDealMapper.class).getGugunCode(sido);
	}

	@Override
	public List<DongCode> getDongCode(String sido, String gugun) throws Exception {
		return sqlSession.getMapper(AptDealMapper.class).getDongCode(sido, gugun);
	}

	@Override
	public List<HouseInfo> getAptInDong(String dong) throws Exception {
		List<HouseInfo> houseList = sqlSession.getMapper(AptDealMapper.class).getAptInDong(dong);
		return houseList;
	}

	@Override
	public List<HouseDeal> getAptDeal(String aptCode) throws Exception {
		long aptCodeL = Long.parseLong(aptCode);
		return sqlSession.getMapper(AptDealMapper.class).getDealbyAptCode(aptCodeL);
	}

	@Override
	public Map<Preferred, HouseInfo> getPreferList(String userId) throws Exception {
		List<Preferred> preferList = sqlSession.getMapper(AptDealMapper.class).getPreferList(userId);
		Map<Preferred, HouseInfo> preferHouseInfo = new HashMap<>();
		for (Preferred preferred : preferList) {
			HouseInfo houseInfo = sqlSession.getMapper(AptDealMapper.class).getAptOne(preferred.getAptCode());
			preferHouseInfo.put(preferred, houseInfo);
		}
		return preferHouseInfo;
	}

	@Override
	public int addPreferList(Preferred preferred) throws Exception {
		int exec = sqlSession.getMapper(AptDealMapper.class).setPrefer(preferred);
		return exec;
	}

	@Override
	public int deletePreferList(int no) throws Exception {
		// TODO Auto-generated method stub
		int exec = sqlSession.getMapper(AptDealMapper.class).deletePrefer(no);
		return exec;
	}

}
