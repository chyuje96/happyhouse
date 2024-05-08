package com.ssafy.happyhouse.model.util;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import com.ssafy.happyhouse.model.dto.HouseInfo;

@Component
public class KMP {

	public int[] getPi(String pattern) { 									// 접두사와 접미사 매칭 Count
		int LenOfPattern = pattern.length(); 								// 찾을 Pattern's Length
		int[] pi = new int[LenOfPattern]; 									// pi[] 할당
		int j = 0; 															// 패턴을 탐색할 인덱스

		for (int i = 1; i < LenOfPattern; i++) {							// i = 1부터
			while (j > 0 && pattern.charAt(i) != pattern.charAt(j)) { 		// KMP알고리즘, 매칭되는 것 건너뛴다
				j = pi[j - 1]; 												// j를 재할당 (건너 뜀)
			}
			if (pattern.charAt(i) == pattern.charAt(j)) { 					// 접두사랑 접미사가 같다면
				pi[i] = ++j; 												// 해당 인덱스 카운트 (길이)
			}
		}
		return pi; 															// pi[] 반환
	}

	public ArrayList<HouseInfo> KMP(List<HouseInfo> aptlist, String aptname) { // 매칭되는 searchName을 ArrayList에 저장

		ArrayList<HouseInfo> aptnamelist = new ArrayList<>();				  // 매칭된 str을 저장할 ArrayList
		int[] pi = getPi(aptname); 										  // 찾을 패턴의 접두사와 접미사가 카운트된 배열 불러온다

		for (int i = 0; i < aptlist.size(); i++) {
			String num_apt = aptlist.get(i).getApartmentName();

			int LenOfStr = num_apt.length(); 							  // 원본 str의 길이
			int LenOfPattern = aptname.length(); 						  // 찾을 pattern의 길이
			int k = 0; 													  // pattern을 탐색시킬 인덱스

			for (int j = 0; j < LenOfStr; j++) { 						  // str의 0번째 (가장 처음부터) 탐색
				while (k > 0 && num_apt.charAt(j) != aptname.charAt(k)) { // KMP 알고리즘의 핵심
					k = pi[k - 1]; 										  // j 재할당 (건너 뛴다)
				}
				if (num_apt.charAt(j) == aptname.charAt(k)) { 			  // 문자가 같을 때
					if (k == LenOfPattern - 1) { 						  // 해당 인덱스가 패턴의 끝과 일치? 그럼 매칭 성공!
						aptnamelist.add(aptlist.get(i)); 				  // 찾은 패턴을 list에 순서대로 넣는다
						k = pi[k];										  // j 초기화
					} else { 											  // 해당 인덱스가 패턴의 끝과 일치하지 않으면 더 찾아야 함!
						k++; 											  // 탐색 인덱스 증가!
					}
				}
			}
		}
		return aptnamelist; 											  // 매칭된 패턴이 저장된 list 반환
	}
}

