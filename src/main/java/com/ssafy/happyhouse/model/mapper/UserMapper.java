package com.ssafy.happyhouse.model.mapper;

import java.util.List;
import java.util.Map;

import com.ssafy.happyhouse.model.dto.UserInfo;


public interface UserMapper {

	UserInfo login(Map<String, String> map) throws Exception;
	
	int idCheck(String checkId) throws Exception;
	void registerMember(UserInfo userInfo) throws Exception;
	
	List<UserInfo> listMember() throws Exception;
	UserInfo getMember(String userid) throws Exception;
	void updateMember(UserInfo userInfo) throws Exception;
	void deleteMember(String userid) throws Exception;
	
}
