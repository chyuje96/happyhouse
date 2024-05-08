package com.ssafy.happyhouse.model.service;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ssafy.happyhouse.model.dto.UserInfo;
import com.ssafy.happyhouse.model.mapper.UserMapper;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private SqlSession sqlSession;

	@Override
	public UserInfo login(Map<String, String> map) throws Exception {
		return sqlSession.getMapper(UserMapper.class).login(map);
	}

	@Override
	public int idCheck(String checkId) throws Exception {
		return sqlSession.getMapper(UserMapper.class).idCheck(checkId);
	}

	@Override
	public void registerMember(UserInfo userInfo) throws Exception {
		sqlSession.getMapper(UserMapper.class).registerMember(userInfo);

	}

	@Override
	public UserInfo[] listMember() throws Exception {
		List<UserInfo> userInfo = sqlSession.getMapper(UserMapper.class).listMember();
		
		int size = userInfo.size();
		
		UserInfo[] userList = new UserInfo[size];
		
		userInfo.toArray(userList);
		
		quickSort(userList, 0, userList.length-1);
		
		return userList;
	}

	@Override
	public UserInfo getMember(String userid) throws Exception {
		return sqlSession.getMapper(UserMapper.class).getMember(userid);
	}

	@Override
	public void updateMember(UserInfo userInfo) throws Exception {
		sqlSession.getMapper(UserMapper.class).updateMember(userInfo);

	}

	@Override
	public void deleteMember(String userid) throws Exception {
		sqlSession.getMapper(UserMapper.class).deleteMember(userid);

	}
	

	private void quickSort(UserInfo[] userInfo, int left, int right) { // left, right는 각 커서의 시작점
		int pl = left;
		int pr = right;
		int pivot = (pl + pr) / 2; // 피벗은 각 끝의 커서의 중간 값으로 선택

		do {
			while (userInfo[pl].compareTo(userInfo[pivot]) < 0) { // userInfo[pl] 값이 pivot보다 큰 수 탐색
				pl++;
			}
			while (userInfo[pr].compareTo(userInfo[pivot]) > 0) { // userInfo[pr] 값이 pivot보다 작은 수 탐색
				pr--;
			}
			if (pl <= pr) { // pl보다 pr이 크면 교환(오름차순)
				swap(userInfo, pl++, pr--);
			}
		} while (pl <= pr);

		// 정렬 끝난 후 나누어진 두개의 그룹에 데이터 수를 체크
		if (left < pr)
			quickSort(userInfo, left, pr); // left가 pr보다 작으면 그룹의 수가 1개 이상이기 때문에 다시 정렬
		if (pl < right)
			quickSort(userInfo, pl, right); // pl이 right보다 작으면 그룹의 수가 1개 이상이기 때문에 다시 정렬
	}

	// 배열(data)의 요소 userInfo[pl]과 userInfo[pr] 교환
	private void swap(UserInfo[] userInfo, int pl, int pr) {
		UserInfo temp = userInfo[pl];
		userInfo[pl] = userInfo[pr];
		userInfo[pr] = temp;
	}

}
