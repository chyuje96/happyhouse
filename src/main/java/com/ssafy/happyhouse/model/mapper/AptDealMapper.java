package com.ssafy.happyhouse.model.mapper;

import java.sql.SQLException;
import java.util.List;

import com.ssafy.happyhouse.model.dto.DongCode;
import com.ssafy.happyhouse.model.dto.HouseDeal;
import com.ssafy.happyhouse.model.dto.HouseInfo;
import com.ssafy.happyhouse.model.dto.Preferred;

public interface AptDealMapper {
	
	List<DongCode> getSidoCode() throws SQLException;
	List<DongCode> getGugunCode(String sido) throws SQLException;
	List<DongCode> getDongCode(String sido, String gugun) throws SQLException;
	List<HouseInfo> getAptInDong(String dong) throws SQLException;
	List<HouseDeal> getDealbyAptCode(long aptCode) throws SQLException;
	
	HouseInfo getAptOne(long aptCode) throws SQLException;
	
	List<Preferred> getPreferList(String userId) throws SQLException;
	int setPrefer(Preferred preferred) throws SQLException;
	int deletePrefer(int no) throws SQLException;

//	HouseInfo getHouseInfoOne(String dong, String aptName) throws SQLException;
}
