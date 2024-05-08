package com.ssafy.happyhouse.model.service;

import java.util.List;
import java.util.Map;

import com.ssafy.happyhouse.model.dto.DongCode;
import com.ssafy.happyhouse.model.dto.HouseDeal;
import com.ssafy.happyhouse.model.dto.HouseInfo;
import com.ssafy.happyhouse.model.dto.Preferred;

public interface HouseMapService {

	List<DongCode> getSidoCode() throws Exception;
	List<DongCode> getGugunCode(String sido) throws Exception;
	List<DongCode> getDongCode(String sido, String gugun) throws Exception;
	List<HouseInfo> getAptInDong(String dong) throws Exception;
	List<HouseDeal> getAptDeal(String aptCode) throws Exception;
	
	Map<Preferred, HouseInfo> getPreferList(String userId) throws Exception;
	int addPreferList(Preferred preferred) throws Exception;
	int deletePreferList(int no) throws Exception;
	
}
