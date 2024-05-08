package com.ssafy.happyhouse.model.service;

import java.util.List;
import java.util.Map;

import com.ssafy.happyhouse.model.dto.UserInfo;

public interface UserService {
	UserInfo login(Map<String, String> map) throws Exception;

	int idCheck(String checkId) throws Exception;
	void registerMember(UserInfo userinfo) throws Exception;

	UserInfo[] listMember() throws Exception;
	UserInfo getMember(String userid) throws Exception;
	void updateMember(UserInfo userInfo) throws Exception;
	void deleteMember(String userid) throws Exception;
}
