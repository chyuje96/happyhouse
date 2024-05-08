package com.ssafy.happyhouse.controller;

import java.util.List;
import java.util.Map;

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
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.happyhouse.model.dto.DongCode;
import com.ssafy.happyhouse.model.dto.HouseDeal;
import com.ssafy.happyhouse.model.dto.HouseInfo;
import com.ssafy.happyhouse.model.dto.Preferred;
import com.ssafy.happyhouse.model.service.HouseMapService;
import com.ssafy.happyhouse.model.util.KMP;

@RestController
@CrossOrigin("*")
@RequestMapping("/deal")
public class DealController {
	
	private final Logger logger = LoggerFactory.getLogger(DealController.class);
	
	@Autowired
	private HouseMapService hmService;
	
	//kmp알고리즘
	@Autowired
	private KMP kmp;
	
	// 시/도 정보 얻어오기
	@GetMapping("/sido")
	public ResponseEntity<List<DongCode>> sido(){
		List<DongCode> sidoList = null;
		try {
			sidoList = hmService.getSidoCode();
			logger.debug("sidoCode : {}", sidoList);
			return new ResponseEntity<List<DongCode>>(sidoList, HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<List<DongCode>>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	// 구/군 정보 얻어오기
	@GetMapping("/gugun")
	public ResponseEntity<List<DongCode>> gugun(@RequestParam("sido") String sido){
		List<DongCode> gugunList = null;
		try {
			gugunList = hmService.getGugunCode(sido);
			logger.debug("gugunCode : {}", gugunList);
			return new ResponseEntity<List<DongCode>>(gugunList, HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<List<DongCode>>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	//동 정보 얻어오기
	@GetMapping("/dong")
	public ResponseEntity<List<DongCode>> dong(@RequestParam("sido") String sido, @RequestParam("gugun") String gugun){
		List<DongCode> dongList = null;
		try {
			dongList = hmService.getDongCode(sido, gugun);
			logger.debug("dongCode : {}", dongList);
			return new ResponseEntity<List<DongCode>>(dongList, HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<List<DongCode>>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	//동에 따른 아파트 정보 얻어오기
	@GetMapping("/apt")
	public ResponseEntity<List<HouseInfo>> apt(@RequestParam("dong") String dong){
		try {
			return new ResponseEntity<List<HouseInfo>>(hmService.getAptInDong(dong), HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<List<HouseInfo>>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	//아파트 이름에 따른 아파트 거래정보 불러오기
	@GetMapping("/apt/{aptCode}")
	public ResponseEntity<List<HouseDeal>> aptAptCode(@PathVariable String aptCode){
		try {
			return new ResponseEntity<List<HouseDeal>>(hmService.getAptDeal(aptCode), HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<List<HouseDeal>>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	//동과 검색어로 아파트 정보 불러오기
	@GetMapping("/aptsearch/{dongCode}/{searchName}")
	public ResponseEntity<List<HouseInfo>> getDealInfowSearch(@PathVariable String dongCode, @PathVariable String searchName){
		List<HouseInfo> houseList = null;
		try {
			houseList = hmService.getAptInDong(dongCode);
			List<HouseInfo> searchList = kmp.KMP(houseList, searchName);
			return new ResponseEntity<List<HouseInfo>>(searchList, HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<List<HouseInfo>>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	//관심지역 아파트 정보 불러오기
	@GetMapping("/prefer/{userId}")
	public ResponseEntity<Map<Preferred, HouseInfo>> getPreferwUserId(@PathVariable String userId){
		Map<Preferred, HouseInfo> preferList = null;
		try {
			preferList = hmService.getPreferList(userId);
			return new ResponseEntity<Map<Preferred, HouseInfo>>(preferList, HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<Map<Preferred, HouseInfo>>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	//관심지역 아파트 등록
	@PostMapping("/prefer")
	public ResponseEntity<Integer> addPrefer(@RequestBody Preferred preferred){
		try {
			int exec = hmService.addPreferList(preferred);
			return new ResponseEntity<Integer>(exec, HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<Integer>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	//관심지역 아파트 삭제
	@DeleteMapping("/prefer/{no}")
	public ResponseEntity<Integer> deletePrefer(@PathVariable int no){
		try {
			int exec = hmService.deletePreferList(no);
			return new ResponseEntity<Integer>(exec, HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<Integer>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}	
}
