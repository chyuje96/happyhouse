package com.ssafy.happyhouse.controller;

import java.util.List;
import java.util.Map;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.happyhouse.model.dto.DongCode;
import com.ssafy.happyhouse.model.dto.UserInfo;
import com.ssafy.happyhouse.model.service.UserService;

@RestController
@CrossOrigin("*")
@RequestMapping("/user")
public class UserController {

	@Autowired
	private UserService userService;

	// 로그인
	@PostMapping("/login")
	public ResponseEntity<String> login(@RequestBody Map<String, String> map){
		UserInfo userInfo = null;
		try {
			userInfo = userService.login(map);
			if (userInfo != null) {
				return new ResponseEntity<String>("success",HttpStatus.OK);
			} else {
				return new ResponseEntity<String>("fail", HttpStatus.OK);
			}			
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<String>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	// 회원 정보 얻기
	@GetMapping("/getmember/{userId}")
	public ResponseEntity<UserInfo> getmember(@PathVariable String userId){
		try {
			return new ResponseEntity<UserInfo>(userService.getMember(userId), HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<UserInfo>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	// 회원 수정
	@PutMapping("/update")
	public ResponseEntity<UserInfo> updateMember(@RequestBody UserInfo userInfo){
		try {
			userService.updateMember(userInfo);
			return new ResponseEntity<UserInfo>(userService.getMember(userInfo.getUserId()), HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<UserInfo>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	// 회원 삭제
	@DeleteMapping("/delete/{userId}")
	public ResponseEntity<String> deleteMember(@PathVariable String userId){
		try {
			userService.deleteMember(userId);
			return new ResponseEntity<String>("delete_success", HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<String>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	// 회원가입
	@PostMapping("/register")
	public ResponseEntity<String> register(@RequestBody UserInfo userInfo){
		try {
			userService.registerMember(userInfo);
			return new ResponseEntity<String>("userRegister", HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<String>(HttpStatus.CONFLICT);
		}
	}

}
