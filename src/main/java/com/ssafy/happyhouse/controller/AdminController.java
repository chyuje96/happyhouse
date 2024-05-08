package com.ssafy.happyhouse.controller;

import java.util.Collections;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.happyhouse.model.dto.UserInfo;
import com.ssafy.happyhouse.model.service.UserService;

@RestController
@RequestMapping("/admin")
@CrossOrigin("*")
public class AdminController {

	private static final Logger logger = LoggerFactory.getLogger(AdminController.class);
	
	@Autowired
	private UserService userService;

	@GetMapping(value = "/user")
	public ResponseEntity<UserInfo[]> userList() throws Exception {
		UserInfo[] list  = userService.listMember();
		if(list != null && !(list.length == 0)) {
			return new ResponseEntity<UserInfo[]>(list, HttpStatus.OK);
		} else {
			return new ResponseEntity(HttpStatus.NO_CONTENT);
		}
	}
	
	@PostMapping(value = "/user")
	public ResponseEntity<UserInfo[]> userRegister(@RequestBody UserInfo userInfo) throws Exception {
		userService.registerMember(userInfo);
		UserInfo[] list = userService.listMember();
		return new ResponseEntity<UserInfo[]>(list, HttpStatus.OK);
	}
	
	@GetMapping(value = "/user/{userId}")
	public ResponseEntity<UserInfo> userInfo(@PathVariable("userId") String userId) throws Exception {
		logger.debug("파라미터 : {}", userId);
		UserInfo userInfo = userService.getMember(userId);
		if(userInfo != null)
			return new ResponseEntity<UserInfo>(userInfo, HttpStatus.OK);
		else
			return new ResponseEntity(HttpStatus.NO_CONTENT);
	}
	
	@PutMapping(value = "/user")
	public ResponseEntity<UserInfo[]> userModify(@RequestBody UserInfo userInfo) throws Exception {
		userService.updateMember(userInfo);
		UserInfo[] list = userService.listMember();
		return new ResponseEntity<UserInfo[]>(list, HttpStatus.OK);
	}
	
	@DeleteMapping(value = "/user/{userId}")
	public ResponseEntity<UserInfo[]> userDelete(@PathVariable("userId") String userId) throws Exception {
		userService.deleteMember(userId);
		UserInfo[] list = userService.listMember();
		return new ResponseEntity<UserInfo[]>(list, HttpStatus.OK);
	}

}


